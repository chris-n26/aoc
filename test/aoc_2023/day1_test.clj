(ns aoc-2023.day1-test
  (:require [clojure.test :refer [deftest testing are is]]
            [aoc-2023.day1 :refer [soln-p1 soln-p2]]
            [clojure.string :as str]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-data (str/split-lines (slurp "data/aoc_2023/test_day1_p1.txt"))]
      (is (= (soln-p1 test-data) 142)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-data (str/split-lines (slurp "data/aoc_2023/test_day1_p2.txt"))]
      (is (= (soln-p2 test-data) 281)))))

(deftest p2-edge-cases
  (testing "consider overlapping words"
    (are [string number] (= (soln-p2 (list string)) number)
      "twone"     21
      "threeight" 38
      "fiveight"  58
      "sevenine"  79
      "eightwo"   82
      "eighthree" 83
      "nineight"  98)))
