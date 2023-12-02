(ns aoc-2023.day2-test
  (:require [clojure.test :refer :all]
            [aoc-2023.day2 :refer :all]
            [clojure.string :as str]))

(deftest parse-game-test
  (testing "no delimiters"
    (are [game parsed-map] (= (parse-game game) parsed-map)
      "2 blue" {"blue" '(2)}
      "1 red" {"red" '(1)}
      "3 green" {"green" '(3)}))
  (testing "with comma"
    (are [game parsed-map] (= (parse-game game) parsed-map)
      "3 blue, 1 green" {"blue" '(3), "green" '(1)}
      "3 blue, 1 green, 2 red" {"blue" '(3), "green" '(1), "red" '(2)}))
  (testing "all delimiters"
    (are [game parsed-map] (= (parse-game game) parsed-map)
      "2 green, 1 blue; 2 blue, 1 green" {"green" '(2 1), "blue" '(1 2)})))

(deftest valid-games-test
  (testing "using examples provided in question"
    (are [game bool] (= bool (valid-game game))
      "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" true
      "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue" true
      "8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red" false
      "1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red" false
      "6 red, 1 blue, 3 green))); 2 blue, 1 red, 2 green" true)))

(deftest p1-examples
  (testing "using examples provided in question"
    (let [test-data (map #(str/split % #": ") (str/split-lines (slurp "data/test_day2.txt")))]
      (is (= (soln-p1 test-data) 8)))))

(deftest p2-examples
  (testing "using examples provided in question"
    (let [test-data (map #(str/split % #": ") (str/split-lines (slurp "data/test_day2.txt")))]
      (is (= (soln-p2 test-data) 2286)))))