(ns aoc-2023.day3
  (:require [clojure.string :as str]))

;; helpers
(def directions
  (list '(0 1) '(0 -1) '(1 0) '(-1 0) '(1 1) '(1 -1) '(-1 1) '(-1 -1)))
(defn is-digit [s] (str/includes? "0123456789" s))
(defn is-symbol [s] (str/includes? "*#-+@%&=$/" s))
(defn read-grid [grid pt] (nth (nth grid (last pt)) (first pt)))
(defn parse-data
  [path]
  (let [raw-strings (str/split-lines (slurp path))
        pad (reduce str (repeat (count raw-strings) "."))]
    (map (partial map str)
      (map #(str "." % ".") (concat (list pad) raw-strings (list pad))))))

;; part 1
(defn adjacent
  [grid pt]
  (if (is-digit (read-grid grid (map + pt '(1 0))))
    (concat (map (partial read-grid grid) (map #(map + pt %) directions))
            (adjacent grid (map + pt '(1 0))))
    (map (partial read-grid grid) (map #(map + pt %) directions))))
(defn is-valid
  [grid pt]
  (if (is-digit (read-grid grid (map + pt '(-1 0))))
    (is-valid grid (map + pt '(-1 0)))
    (reduce #(or % %2) (map is-symbol (adjacent grid pt)))))
(defn extract-part-no
  [grid row]
  (#(map last (re-seq #"(\d+)" %))
   (reduce str (map #(if (is-valid grid %) (read-grid grid %) ".") row))))
(defn soln-p1
  [grid]
  (let [labels (range 1 (dec (count grid)))
        pts (map #(map (comp reverse (partial list %)) labels) labels)]
    (reduce +
      (map #(Integer/parseInt %)
        (flatten (map (partial extract-part-no grid) pts))))))

;; part 2
(defn look-forward
  [grid pt]
  (when (is-digit (read-grid grid pt))
    (str (read-grid grid pt)
         (when (is-digit (read-grid grid (map + pt '(1 0))))
           (look-forward grid (map + pt '(1 0)))))))
(defn look-back
  [grid pt]
  (if (is-digit (read-grid grid pt))
    (if (is-digit (read-grid grid (map + pt '(-1 0))))
      (look-back grid (map + pt '(-1 0)))
      pt)
    pt))
(defn gear-ratio
  [grid pt]
  (let [nums (filter identity
               (map (partial look-forward grid)
                 (distinct (map (partial look-back grid)
                             (map #(map + pt %) directions)))))]
    (when (= 2 (count nums)) (apply * (map #(Integer/parseInt %) nums)))))
(defn soln-p2
  [grid]
  (let [labels (range 1 (dec (count grid)))
        pts (map #(map (comp reverse (partial list %)) labels) labels)]
    (reduce +
      (filter identity
        (map (partial gear-ratio grid)
          (filter #(= "*" (read-grid grid %)) (reduce concat pts)))))))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [schematics (parse-data "data/day3.txt")]
    (println (str "solution for part 1: " (soln-p1 schematics)))
    (println (str "solution for part 2: " (soln-p2 schematics)))))
