
(ns tawny.upper.top-test
  (:use [clojure.test])
  (:require 
   [tawny.owl]
   [tawny.reasoner]
   [tawny.upper.top]))

(defn top-fixtures [tests]
  (tawny.owl/ontology-to-namespace tawny.upper.top/top)
  (tawny.reasoner/reasoner-factory :hermit)
  (binding [tawny.reasoner/*reasoner-progress-monitor*
            (atom tawny.reasoner/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once top-fixtures)

(deftest loadtest
  (is true))

(deftest consistent
  (is (tawny.reasoner/coherent?)
      (tawny.reasoner/consistent?)))
