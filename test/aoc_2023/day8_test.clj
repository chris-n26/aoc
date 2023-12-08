(ns aoc-2023.day8-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day8 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using first example provided in question"
    (let [[test-instructs test-nodes] (parse-data
                                        "data/aoc_2023/test_day8_p1_1.txt")]
      (is (= (soln-p1 test-instructs test-nodes) 2))))
  (testing "using second example provided in question"
    (let [[test-instructs test-nodes] (parse-data
                                        "data/aoc_2023/test_day8_p1_2.txt")]
      (is (= (soln-p1 test-instructs test-nodes) 6)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [[test-instructs test-nodes] (parse-data
                                        "data/aoc_2023/test_day8_p2.txt")]
      (is (= (soln-p2 test-instructs test-nodes) 6)))))
