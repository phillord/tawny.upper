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

(ns tawny.upper.minimal
  (:use [tawny.owl] [tawny.upper.annotate]))

(defontology minimal
  :iri "http://www.russet.org.uk/minimal"
  :prefix "mini:"
  :comment "A minimal upper design pattern.

This is defined after the PIMPS ontology pattern of EFO, by James Malone and colleages.")


(owlimport tawny.upper.annotate/annotate)

;; obo:IAO_0000030
(defclass InformationEntity
  :comment "An information entity is an entity that represents information about some other entity.  For example, a measurement, a clustered data set.")

;; snap:MaterialEntity
(defclass MaterialEntity
:comment
"A material entity is an entity that exists in full during the length of time of its existence, persists through this time while maintaining its identity and has no temporal parts. For example a heart, a human, a fly, a microarray.")

;; snap:SpecificallyDependentContinuant
(defclass MaterialProperty
  :comment "An entity which is a property or characteristic of another entity.
  For example, a mouse has the colour white.")




;; oh dear. java.lang.Process nameclash.
(ns-unmap *ns* 'Process)
;; snap:ProcessualEntity
(defclass Process
  :comment "A process is an entity that exists in time by occurring or happening, has temporal parts and always involves and depends on some entity during the time it occurs.")

;; snap:Site
(defclass Site
  :comment 
  "A site is an entity which consists of a characteristic spatial shape in relation to some arrangement of other material entities.")
