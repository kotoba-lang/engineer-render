(ns engineer-render-test
  (:require [clojure.test :refer [deftest is testing]]
            [engineer-render]))
(deftest namespace-loads
  (testing "the restored CLJC namespace loads"
    (is (some? engineer-render))))
