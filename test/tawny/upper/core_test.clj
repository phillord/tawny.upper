

(ns tawny.upper.core-test
  (:use [clojure.test])
  (:require [tawny.upper.core])
  )


(deftest save-all
  (is (tawny.upper.core/save-all)))
