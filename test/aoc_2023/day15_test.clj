(ns aoc-2023.day15-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day15 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-ops (parse-data "data/aoc_2023/test_day15.txt")]
      (is (= (soln-p1 test-ops) 1320)))))

(deftest p2-examples
  (testing "using first example provided in question"
    (let [test-ops (parse-data "data/aoc_2023/test_day15.txt")]
      (is (= (soln-p2 test-ops) 145)))))
