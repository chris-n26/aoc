(ns aoc-2023.day9
  (:require [clojure.string :as str]))

;; helpers
(defn extract-nums
  [s]
  (map #(Integer/parseInt %) (map last (re-seq #"(-?\d+)" s))))
(defn parse-data [path] (map extract-nums (str/split-lines (slurp path))))
(defn step-diff [coll] (map (partial apply #(- %2 %)) (partition 2 1 coll)))
(defn pyramid
  [coll]
  (first (filter #(every? zero? (first %))
           (iterate #(conj % (step-diff (first %))) (list coll)))))

;; part 1
(defn extrap-end [coll1 coll2] (conj (vec coll2) (+ (last coll2) (last coll1))))
(defn soln-p1
  [data]
  (reduce + (map #(last (reduce extrap-end (list 0) (pyramid %))) data)))

;; part 2
(defn extrap-start [coll1 coll2] (conj coll2 (- (first coll2) (first coll1))))
(defn soln-p2
  [data]
  (reduce + (map #(first (reduce extrap-start (list 0) (pyramid %))) data)))

(defn -main
  "Evaluate solutions"
  []
  (let [histories (parse-data "data/aoc_2023/day9.txt")]
    (println (str "Solution for part 1: " (time (soln-p1 histories))))
    (println (str "Solution for part 2: " (time (soln-p2 histories))))))
