(ns aoc-2023.day8
  (:require [clojure.string :as str]))

;; helpers
(defn parse-data
  [path]
  (let [[instructs & nodes] (str/split (slurp path) #"\n+")]
    (list instructs
          (into (hash-map)
                (map (juxt peek pop)
                  (map (partial apply list)
                    (map #(map last (re-seq #"(\w{3})" %)) nodes)))))))
(defn next-node
  [transition node instruct]
  (case instruct
    \L (first (transition node))
    \R (last (transition node))))
(defn generate-path
  [instructs nodes start]
  (iterate #(concat (drop-last %)
                    (reductions (partial next-node nodes) (last %) instructs))
           (list start)))

;; part 1
(defn soln-p1
  [instructs nodes]
  ((comp dec count first)
    (filter #(= "ZZZ" (last %)) (generate-path instructs nodes "AAA"))))

;; part 2
(defn count-terminate
  [instructs nodes start]
  ((comp dec count first)
    (filter #(= \Z (last (last %)))
      (rest (generate-path instructs nodes start)))))
(defn gcd [a b] (if (zero? b) a (recur b (mod a b))))
(defn lcm [a b] (/ (* a b) (gcd a b)))
(defn soln-p2
  [instructs nodes]
  (let [starts (filter #(= \A (last %)) (map first nodes))]
    (reduce lcm (map (partial count-terminate instructs nodes) starts))))

(defn -main
  "Evaluate solutions"
  []
  (let [[instructs nodes] (parse-data "data/aoc_2023/day8.txt")]
    (println (str "Solution for part 1: " (time (soln-p1 instructs nodes))))
    (println (str "Solution for part 2: " (time (soln-p2 instructs nodes))))))
