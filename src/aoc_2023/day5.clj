(ns aoc-2023.day5
  (:require [clojure.string :as str]))

;; helpers
(defn parse-nums [s] (map #(Long/parseLong %) (map last (re-seq #"(\d+)" s))))
(defn parse-data
  [path]
  (let [raw-strings (str/split (slurp path) #"\n\n")
        seeds (parse-nums (first raw-strings))
        map-data (map (comp (partial map parse-nums) rest str/split-lines)
                   (rest raw-strings))]
    (cons seeds map-data)))

;; part 1
(defn mapped
  [num data]
  (let [rel (filter #(<= (second %) num (dec (reduce + (rest %)))) data)]
    (if (empty? rel) num (#(+ (first %) (- num (second %))) (first rel)))))
(defn soln-p1
  [seeds map-data]
  (reduce min (map #(reduce mapped % map-data) seeds)))

;; part 2
(defn intvl-slicer
  [intvl intvl-grates]
  (if (empty? intvl-grates)
    (list intvl)
    (let [[a b] intvl
          [c d] (first intvl-grates)]
      (if (< b c)
        (list intvl)
        (if (<= b d)
          (if (<= c a) (list intvl) (list (list a (dec c)) (list c b)))
          (if (< a c)
            (concat (list (list a (dec c)) (first intvl-grates))
                    (intvl-slicer (list (inc d) b) (rest intvl-grates)))
            (if (<= a d)
              (cons (list a d)
                    (intvl-slicer (list (inc d) b) (rest intvl-grates)))
              (intvl-slicer intvl (rest intvl-grates)))))))))
(defn shift-intvl
  [map-cfgs intvl]
  (let [[a b] intvl
        cfg (first (filter #(<= (first %) a b (second %)) map-cfgs))]
    (if (empty? cfg) intvl (map (partial + (last cfg)) intvl))))
(defn intvl-mapped
  [data intvl]
  (let [map-cfgs (map (juxt second
                            (comp dec (partial reduce +) (partial rest))
                            (comp (partial apply -) (partial take 2)))
                   data)]
    (map #(shift-intvl map-cfgs %)
      (intvl-slicer intvl (sort-by first (map (partial take 2) map-cfgs))))))
(defn soln-p2
  [seed-intvls map-data]
  (letfn [(multi-intvl-mapped [intvls data]
            (reduce concat (map (partial intvl-mapped data) intvls)))]
    (reduce min
      (map (partial reduce min)
        (reduce multi-intvl-mapped seed-intvls map-data)))))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [[seeds & map-data] (parse-data "data/day5.txt")]
    (println (str "solution for part 1: " (soln-p1 seeds map-data)))
    (let [seed-intvls (map #(list (first %) (dec (reduce + %)))
                           (partition 2 seeds))]
      (println (str "solution for part 2: " (soln-p2 seed-intvls map-data))))))