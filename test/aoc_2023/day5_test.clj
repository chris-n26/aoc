(ns aoc-2023.day5-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-2023.day5 :refer [soln-p1 soln-p2 parse-data]]))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [[test-seeds & test-map-data] (parse-data "data/test_day5.txt")]
      (is (= (soln-p1 test-seeds test-map-data) 35)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [[test-seeds & test-map-data] (parse-data "data/test_day5.txt")
          test-seed-intvls (map #(list (first %) (dec (reduce + %)))
                       (partition 2 test-seeds))]
      (is (= (soln-p2 test-seed-intvls test-map-data) 46)))))
