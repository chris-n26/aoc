(ns aoc-2023.day7
  (:require [clojure.string :as str]))

;; helpers
(defn parse-data
  [path]
  (map #(str/split % #"\s") (str/split-lines (slurp path))))
(defn classify
  [hand]
  (apply str
    (reverse (sort (vals (update-vals (group-by identity hand) count))))))
(defn hand-comparator
  [card-order hand1 hand2]
  (letfn [(card-compare [s t]
            (apply - (map #(str/index-of card-order %) [s t])))]
    (neg? (first (filter #(not (= 0 %)) (map card-compare hand1 hand2))))))
(defn total-wins
  [hand-classifier hand-order hands]
  (reduce +
    (map *
      (range 1 (inc (count hands)))
      (map #(Integer/parseInt (last %))
        (reduce concat
          (vals (into (sorted-map-by (comp neg? compare))
                      (update-vals (group-by (comp hand-classifier first) hands)
                                   (partial sort-by first hand-order)))))))))
(defn soln-p1
  [hands]
  (total-wins classify (partial hand-comparator "23456789TJQKA") hands))

;; part 2
(defn wild-match
  [hand]
  (first (sort-by classify
                  (comp pos? compare)
                  (map #(str/escape hand %)
                    (map (partial hash-map \J) (distinct hand))))))
(defn soln-p2
  [hands]
  (total-wins (comp classify wild-match)
              (partial hand-comparator "J23456789TQKA")
              hands))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [hands (parse-data "data/aoc_2023/day7.txt")]
    (println (str "solution for part 1: " (soln-p1 hands)))
    (println (str "solution for part 2: " (soln-p2 hands)))))
