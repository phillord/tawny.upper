(ns tawny.upper.top
  (:use [tawny.owl]))


(defontology top
  :iri "http://www.russet.org.uk/top"
  :prefix "top:"
)

(as-disjoint 
 (defclass Extent
   :comment 
   "Things that define a location in time or place within the universe.")
 (defclass Entity
   :comment
   "Everything in the universe."))
  
