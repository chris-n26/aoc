(ns aoc-2023.day7-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day7 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-hands (parse-data "data/aoc_2023/test_day7.txt")]
      (is (= (soln-p1 test-hands) 6440)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-hands (parse-data "data/aoc_2023/test_day7.txt")]
      (is (= (soln-p2 test-hands) 5905)))))