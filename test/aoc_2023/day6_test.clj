(ns aoc-2023.day6-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day6 :refer [soln-p1 soln-p2 parse-data merge-stats]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-race-stats (parse-data "data/test_day6.txt")]
      (is (= (soln-p1 test-race-stats) 288)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-race-stats (parse-data "data/test_day6.txt")
          test-merge-race-stat (merge-stats test-race-stats)]
      (is (= (soln-p2 test-merge-race-stat) 71503)))))