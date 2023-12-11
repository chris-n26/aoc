(ns aoc-2023.day11
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defn parse-data [path] (map (partial map str) (str/split-lines (slurp path))))
(defn is-void? [coll] (every? true? (map #(not (= "#" %)) coll)))
(defn fill-void
  [scale coll]
  (if (is-void? coll)
    (map #(if (not (= "#" %)) scale %) coll)
    (map #(if (not (= "#" %)) 1 %) coll)))
(defn expand-grid
  [scale grid]
  (apply map
    list
    (map (partial fill-void scale)
      (apply map list (map (partial fill-void scale) grid)))))
(defn find-galaxy
  [row rn]
  (last (reduce #(if (= "#" %2)
                   (list (inc (first %)) (conj (last %) (list (first %) rn)))
                   (list (+ %2 (first %)) (last %)))
          (list 0 (list))
          row)))
(defn all-galaxies
  [scale grid]
  (reduce concat
    (last (reduce #(if (is-void? %2)
                     (list (+ scale (first %)) (last %))
                     (list (inc (first %))
                           (conj (last %) (find-galaxy %2 (first %)))))
            (list 0 (list))
            (expand-grid scale grid)))))
(defn abs-dist [pt1 pt2] (reduce + (map abs (map - pt1 pt2))))
(defn pairwise-dist
  [scale grid]
  (reduce +
    (map (partial apply abs-dist)
      (combo/combinations (all-galaxies scale grid) 2))))

;; part 1
(defn soln-p1 [grid] (pairwise-dist 2 grid))

;; part 2
(defn soln-p2 [grid] (pairwise-dist (int 1e6) grid))

(defn -main
  "Evaluate solutions"
  []
  (let [grid (parse-data "data/aoc_2023/day11.txt")]
    (println (str "Solution for part 1: " (time (soln-p1 grid))))
    (println (str "Solution for part 2: " (time (soln-p2 grid))))))
