;; The contents of this file are subject to the GPL License, Version 3.0.
;; 
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;; 
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;; 
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;; 
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.


(ns tawny.upper.annotate
  (:use [tawny.owl]))

(defontology annotate
  :iri "http://www.russet.org.uk/annotate"
  :prefix "annotate:"
  )

;; ## Scope
;;
;; A class or a concept often has a number of naturalistic interpretations;
;; that is a definition that an experiment in the domain would be likely to
;; come up with. Scope is a way of describing restrictions to these
;; interpretations; in essence, it a mechanism for the ontology developer to
;; say, that more complex definitions have already been considered


(defannotationproperty Scope
  :comment "The scope provides context to the definition and describes that
  parts of the domain which are (or are not) intended to be described by definition." )

(def scope
  (partial tawny.owl/annotation Scope))




(save-ontology "annotate.owl" :owl)
(save-ontology "annotate.omn" :omn)
