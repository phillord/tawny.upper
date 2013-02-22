;; The contents of this file are subject to the LGPL License, Version 3.0.
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

(ns tawny.upper.entity
  (:use [tawny.owl])
  (:require [tawny.upper.top :as top]))

;; ## Introduction
;;
;; This part of the upper design pattern that describes the things in the
;; universe rather than the space in which the universe. 

(defontology entity
  :iri "http://www.russet.org.uk/entity"
  :prefix "entity:"
  )

(owlimport tawny.upper.top/top)

;; ## Continuant and Occurrent
;; 
;; Here we make the distinction between things and processes. Continuants can
;; change over time, but despite this can be considered to be complete at any 
;; point that they exist. This can be compared to Occurrents which necessarily
;; change over time, and only make sense if considered over an extended
;; period. 
(as-disjoint-subclasses
 top/Entity

 (defclass Continuant
   :comment "An entity that is complete at any point in time."
   )

 (defclass Occurrent
   :comment "An entity that must be considered over time to be complete."
   )
 )


(as-subclasses
 Continuant
 :disjoint :cover
 (defclass IndependentContinuant
   :comment "A continuant that exists independently of another.")

 (defclass DependentContinuant
   :comment "A continuant that depends on another for its existance."))


(as-disjoint-subclasses
 IndependentContinuant
 (with-suffix Continuant
   (defclass Mass)

   (defclass Energy)

   (defclass Force)

   (defclass Information))
 )

(as-disjoint-subclasses
 DependentContinuant

 (defclass Role)
 (defclass Quality)
 )


(as-subclasses
 Role
 (defclass Function)
 (defclass SocialRole)

 )

(as-subclasses
 Function
 (defclass BiologicalFunction)
 (defclass MechanicalFunction)
 )
