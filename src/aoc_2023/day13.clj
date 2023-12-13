(ns aoc-2023.day13
  (:require [clojure.string :as str]))

;; helpers
(defn parse-data [path] (map str/split-lines (str/split (slurp path) #"\n\n")))
(defn subtotals
  [metric grid]
  (+ (metric (apply map list grid)) (* 100 (metric grid))))

;; part 1
(defn reflects-at
  [grid]
  (reduce +
    (map first
      (filter #(every? true? (last %))
        (map #(list (count (first %)) (apply map = %))
          (map #((juxt (comp reverse first) last) (split-at % grid))
            (range 1 (count grid))))))))
(defn soln-p1
  [all-grids]
  (reduce + (map (partial subtotals reflects-at) all-grids)))

;; part 2
(defn reflects-smudge-at
  [grid]
  (reduce +
    (map first
      (filter #(= 1 (count (filter false? (last %))))
        (map #(list (count (first %)) (flatten (apply map (partial map =) %)))
          (map #((juxt (comp reverse first) last) (split-at % grid))
            (range 1 (count grid))))))))
(defn soln-p2
  [all-grids]
  (reduce + (map (partial subtotals reflects-smudge-at) all-grids)))

(defn -main
  "Evaluate solutions"
  []
  (let [all-grids (parse-data "data/aoc_2023/day13.txt")]
    (println (str "Solution for part 1: " (time (soln-p1 all-grids))))
    (println (str "Solution for part 2: " (time (soln-p2 all-grids))))))

