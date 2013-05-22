(ns tawny.upper.extent-el-test
  (:use [clojure.test])
  (:require 
   [tawny.upper.extent-el :as e]
   [tawny.owl :as o]
   [tawny.reasoner :as r]
   [tawny.profile]))
 

(defn ontology-reasoner-fixture [tests]
  ;; this should kill the reasoner factory and all reasoners which is the
  ;; safest, but slowest way to start.
  (r/reasoner-factory :hermit)
  
  ;; inject the pizzaontology into the current namespace, which saves the
  ;; hassle of using with ontology every where. set this up each time in case
  ;; pizzaontology has been re-evaled
  (o/ontology-to-namespace e/extent)
  (binding [r/*reasoner-progress-monitor*
            (atom r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

(deftest consistent
  (is (r/coherent?))
  (is (r/consistent?)))


(deftest profile
  (is (tawny.profile/inprofile? tawny.profile/profile-owl2el)))

(deftest SpatialTemporalRegion
  (is 
   (r/isuperclass? e/SpatialTemporalRegion e/SpatialRegion))
  (is
   (r/isuperclass? e/SpatialTemporalRegion e/TemporalRegion)))
