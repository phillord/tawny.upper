
(ns tawny.upper.entity
  (:use [tawny.owl])
  (:require [tawny.upper.top :as top]))


(defontology entity
  :iri "http://www.russet.org.uk/extent"
  :prefix "extent:"
  )

(owlimport tawny.upper.top/top)

(as-disjoint-subclasses
 top/Entity

 (defclass Continuant)

 (defclass Occurrent)
 )

(as-disjoint-subclasses
 Continuant
 (defclass IndependentContinuant)

 (defclass DependentContinuant))


(as-disjoint-subclasses
 IndependentContinuant
 (defclass MassContinuant)

 (defclass EnergyContinuant)

 (defclass InformationContinuant)

 )

(as-disjoint-subclasses
 DependentContinuant

 (defclass Role)

 (defclass Function)

 (defclass Quality)

 )


