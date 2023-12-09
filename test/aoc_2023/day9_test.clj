(ns aoc-2023.day9-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day9 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using first example provided in question"
    (let [histories (parse-data "data/aoc_2023/test_day9.txt")]
      (is (= (soln-p1 histories) 114)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [histories (parse-data "data/aoc_2023/test_day9.txt")]
      (is (= (soln-p2 histories) 2)))))
