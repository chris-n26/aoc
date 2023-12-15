(ns aoc-2023.day15
  (:require [clojure.string :as str]))

;; helpers
(defn parse-data [path] (str/split (slurp path) #"[,\n]"))
(defn hash-alg [s] (reduce #(mod (* 17 (+ % (int %2))) 256) 0 s))

;; part 1
(defn soln-p1 [ops] (reduce + (map hash-alg ops)))

;; part 2
(defn parse-op [s] (str/split s #"[-=]"))
(defn insert-lens
  [lens box]
  (let [match-lens (first (filter #(= (first lens) (first %)) box))]
    (if match-lens (replace {match-lens lens} box) (conj box lens))))
(defn remove-lens [lens box] (filterv #(not= (first lens) (first %)) box))
(defn operation
  [box-map lens]
  (let [bn (hash-alg (first lens))]
    (if (second lens)
      (update-in box-map [bn] (partial insert-lens lens))
      (update-in box-map [bn] (partial remove-lens lens)))))
(defn hashmap-alg
  [ops]
  (reduce operation
    (reduce conj (map #(hash-map % (vector)) (range 256)))
    (map parse-op ops)))
(defn focus-power
  [bn lenses]
  (* (inc bn)
     (reduce +
       (map *
         (map #(Integer/parseInt (last %)) lenses)
         (range 1 (inc (count lenses)))))))
(defn soln-p2
  [ops]
  (reduce + (map (partial apply focus-power) (hashmap-alg ops))))

(defn -main
  "Evaluate solutions"
  []
  (let [ops (parse-data "data/aoc_2023/day15.txt")]
    (println (str "Solution for part 1: " (time (soln-p1 ops))))
    (println (str "Solution for part 2: " (time (soln-p2 ops))))))
