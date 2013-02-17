
(ns tawny.upper.entity
  (:use [tawny.owl])
  (:require [tawny.upper.top :as top]))


(defontology entity
  :iri "http://www.russet.org.uk/entity"
  :prefix "entity:"
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
