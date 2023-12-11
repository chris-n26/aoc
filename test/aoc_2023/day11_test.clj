(ns aoc-2023.day11-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day11 :refer [soln-p1 pairwise-dist parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [grid (parse-data "data/aoc_2023/test_day11.txt")]
      (is (= (soln-p1 grid) 374)))))

(deftest p2-examples
  (testing "using first example provided in question"
    (let [grid (parse-data "data/aoc_2023/test_day11.txt")]
      (is (= (pairwise-dist 10 grid) 1030))))
(testing "using second example provided in question"
   (let [grid (parse-data "data/aoc_2023/test_day11.txt")]
     (is (= (pairwise-dist 100 grid) 8410)))))
