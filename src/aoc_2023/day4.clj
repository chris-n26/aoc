(ns aoc-2023.day4
  (:require [clojure.string :as str]))

;; helpers
(defn parse-data
  [path]
  (letfn [(get-nums [s] (map #(Integer/parseInt (last %)) (re-seq #"(\d+)" s)))
          (parse-line [line]
            (map #(if (= 1 (count %)) (first %) %)
              (map get-nums (str/split line #"[\|\:]"))))]
    (map parse-line (str/split-lines (slurp path)))))

;; part 1
(defn points
  [cards i]
  (let [[win-nums nums] (rest (first (filter #(= i (first %)) cards)))
        matches (filter #(.contains win-nums %) nums)]
    (if (= 0 (count matches))
      0
      (nth (iterate (partial * 2) 1) (dec (count matches))))))
(defn soln-p1 [cards] (reduce + (map (partial points cards) (map first cards))))

;; part 2
(defn get-copy
  [cards i]
  (let [[win-nums nums] (rest (first (filter #(= i (first %)) cards)))
        matches (filter #(.contains win-nums %) nums)]
    (hash-map i (map (partial + (inc i)) (range (count matches))))))
(defn soln-p2
  [cards]
  (let [match-table (reduce conj
                      (map (partial get-copy cards) (map first cards)))]
    (letfn [(new-map [old-map j]
              (conj old-map
                    (hash-map j
                              (inc (reduce +
                                     (map (comp old-map first)
                                       (filter #(.contains (second %) j)
                                         match-table)))))))]
      (reduce +
        (vals (nth (iterate #(new-map % (inc (reduce max (keys %))))
                            (hash-map 1 1))
                   (dec (count cards))))))))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [cards (parse-data "data/day4.txt")]
    (println (str "solution for part 1: " (soln-p1 cards)))
    (println (str "solution for part 2: " (soln-p2 cards)))))
