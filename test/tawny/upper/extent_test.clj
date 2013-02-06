;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2011, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns tawny.upper.extent-test
  (:use [clojure.test])
  (:require 
   [tawny.upper.extent :as e]
   [tawny.owl :as o]
   [tawny.reasoner :as r]))

(defn ontology-reasoner-fixture [tests]
  ;; this should kill the reasoner factory and all reasoners which is the
  ;; safest, but slowest way to start.
  (r/reasoner-factory :hermit)
  
  ;; inject the pizzaontology into the current namespace, which saves the
  ;; hassle of using with ontology every where. set this up each time in case
  ;; pizzaontology has been re-evaled
  (o/ontology-to-namespace e/extent)
  (binding [r/*reasoner-progress-monitor*
            r/reasoner-progress-monitor-silent]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

(deftest consistent
  (is (r/coherent?))
  (is (r/consistent?)))
