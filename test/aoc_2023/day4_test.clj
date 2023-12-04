(ns aoc-2023.day4-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day4 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-data (parse-data "data/test_day4.txt")]
      (is (= (soln-p1 test-data) 13)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-data (parse-data "data/test_day4.txt")]
      (is (= (soln-p2 test-data) 30)))))
