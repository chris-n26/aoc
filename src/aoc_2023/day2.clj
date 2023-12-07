(ns aoc-2023.day2
  (:require [clojure.string :as str]))

;; helpers
(defn parse-game
  [game]
  (let [game-re #"(\d+)\s(blue|red|green)"]
    (update-vals (group-by last (map rest (re-seq game-re game)))
                 (partial map (comp #(Integer/parseInt %) first)))))

;; part 1
(def bag-conts (hash-map "red" 12 "green" 13 "blue" 14))
(defn valid-game
  [game]
  (let [game-maxes (update-vals (parse-game game) (partial apply max))]
    (not (reduce #(or % %2)
           (map neg? (vals (merge-with - bag-conts game-maxes)))))))
(defn soln-p1
  [data]
  (reduce +
    (map #(Integer/parseInt (last (re-find #"(?:Game)\s(\d+)" %)))
      (map first (filter #(valid-game (last %)) data)))))

;; part 2
(defn soln-p2
  [data]
  (reduce +
    (map #(reduce * (vals %))
      (map #(update-vals (parse-game %) (partial apply max)) (map last data)))))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [data (map #(str/split % #": ")
               (str/split-lines (slurp "data/aoc_2023/day2.txt")))]
    (println (str "solution for part 1: " (soln-p1 data)))
    (println (str "solution for part 2: " (soln-p2 data)))))
