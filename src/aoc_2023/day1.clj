(ns aoc-2023.day1
  (:require [clojure.string :as str]))

;; helpers
(defn first-last [s] (Integer/parseInt (apply str ((juxt first last) s))))

;; part 1
(defn soln-p1
  [data]
  (reduce +
    (map first-last (map (partial filter #(Character/isDigit %)) data))))

;; part 2
(def letters '("one" "two" "three" "four" "five" "six" "seven" "eight" "nine"))
(defn let-to-num
  [s]
  (let [num-map (reduce conj (map hash-map letters (map str (range 1 10))))]
    (str/replace s (re-pattern (str/join "|" letters)) num-map)))
(defn soln-p2
  [data]
  (let [pattern (re-pattern (str "(?=(" (str/join "|" letters) "|\\d))"))]
    (reduce +
      (map first-last
        (map #(map (comp let-to-num last) (re-seq pattern %)) data)))))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [data (str/split-lines (slurp "data/day1.txt"))]
    (println (str "solution for part 1: " (soln-p1 data)))
    (println (str "solution for part 2: " (soln-p2 data)))))
