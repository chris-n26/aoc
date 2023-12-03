(ns aoc-2023.day3-test
  (:require [clojure.test :refer :all]
            [aoc-2023.day3 :refer :all]
            [clojure.string :as str]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-data (parse-data "data/test_day3.txt")]
      (is (= (soln-p1 test-data) 4361)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-data (parse-data "data/test_day3.txt")]
      (is (= (soln-p2 test-data) 467835)))))
