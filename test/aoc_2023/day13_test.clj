(ns aoc-2023.day13-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day13 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-all-grids (parse-data "data/aoc_2023/test_day13.txt")]
      (is (= (soln-p1 test-all-grids) 405)))))

(deftest p2-examples
  (testing "using first example provided in question"
    (let [test-all-grids (parse-data "data/aoc_2023/test_day13.txt")]
      (is (= (soln-p2 test-all-grids) 400)))))
