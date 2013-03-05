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

(ns tawny.upper.top
  (:use [tawny.owl]
        [tawny.upper.annotate]))


;; ## Introduction
;;
;; This is the tawny upper design pattern. It's purpose is to provide some
;; basic structure to an ontology which uses it, and to do so in a way which
;; is re-useable and familiar. 

;; ## Documentation Principles
;;
;; Firstly, all the documentation for this ontology is here, in place. There
;; is no external manual, and everything that should be necessary to
;; understand this ontology is, or should be here. Secondly, both in the
;; written "literate" documentation and for the documentation and comments in
;; ontology itself, the desire for simple and plain English has been placed
;; above other requirements, including strict technical correctness. 

;; ## Implementation 

;; # Ontology
;; 
;; The IRI is a temporary one and will be replaced later. 

(defontology top
  :iri "http://www.russet.org.uk/top"
  :prefix "top:"
  :comment "An upper design pattern."
  :annotation (scope "This design pattern is provides a simple upper pattern
for ontology building. It is not designed to provide a complete description of
the universe."))

;; # Import
;;
;; We import only the annotation properties for more structured comments on
;; the concepts. 
(owlimport tawny.upper.annotate/annotate)

;; # Extent and Entity
;;
;; We have choosen a container view, largely for simplicity. In general, this
;; is rich enough for most of the intended use cases, it although it would be
;; limiting for physics beyond Newtonian. Entities sit within the universe and
;; do not interact with it heyond this.

(as-disjoint 

 (defclass Extent
   :comment 
   "Things that define a location in time or place within the universe."
   :annotation
   (scope "The extent of the universe is viewed as a simple container in which
things are located."))

 (defclass Entity
   :comment
   "Everything in the universe."
   :annotation
   (scope "Entities sit within the extent of the universe. No attempt is made
 to describe the interaction between the entities and extent, as happens
 relativistically ")))


;; ## Contributing
;;
;; Contributions are welcome. The prefered format is via a pull request,
;; either through email (phillip.lord@newcastle.ac.uk) or through github.
;; Other comments or suggestions will be accepted also. 

;; ## Typographical Notes
;;
;; Documentation is written in markdown format. It can be turned into an HTML
;; using the lein-marginalia plugin. When used, first person is always in
;; plural, and does not indicate multiple authorship or otherwise. 

;; ## Authors
;; 
;; The author is Phillip Lord (phillip.lord@newcastle.ac.uk). 
