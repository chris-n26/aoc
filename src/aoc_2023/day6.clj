(ns aoc-2023.day6
  (:require [clojure.string :as str]
            [clojure.math :as math]))

;; helpers
(defn parse-nums [s] (map #(Integer/parseInt %) (map last (re-seq #"(\d+)" s))))
(defn parse-data
  [path]
  (apply map list (map parse-nums (str/split-lines (slurp path)))))
(defn merge-stats
  [stats]
  (map #(Long/parseLong %) (map (partial apply str) (apply map list stats))))

;; part 1
(defn ways-win
  [duration record-dist]
  (count (filter identity
           (map (partial < record-dist)
             (map #(* % (- duration %)) (range duration))))))
(defn soln-p1 [stats] (reduce * (map (partial apply ways-win) stats)))

;; part2
(defn soln-p2 [stat] (long (apply #(math/sqrt (- (* % %) (* 4 %2))) stat)))

(defn -main
  "Evaluate solutions"
  [& args]
  (let [race-stats (parse-data "data/day6.txt")]
    (println (str "solution for part 1: " (soln-p1 race-stats)))
    (let [merge-race-stat (merge-stats race-stats)]
      (println (str "solution for part 2: " (soln-p2 merge-race-stat))))))