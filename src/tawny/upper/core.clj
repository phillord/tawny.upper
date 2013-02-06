
(ns tawny.upper.core
  (:use [tawny.owl])
  (:require [tawny.upper top extent entity]))


(defn save-and-import-ontology [[name ontology]]
  (owlimport ontology)
    
  (with-ontology ontology
    (save-ontology (str name ".omn") :omn)
    (save-ontology (str name ".owl") :owl)))

(defn save-all []

  ;; redefine core ontology
  (defontology core
    :iri "http://www.russet.org.uk/upper/core"
    :prefix "core:"
    )
  (doall
   (map 
    save-and-import-ontology
    [
     ["top"  tawny.upper.top/top]
     ["extent" tawny.upper.extent/extent]
     ["entity"  tawny.upper.entity/entity]]))

  (save-ontology "core.omn" :omn)
  (save-ontology "core.owl" :owl)

  ;; return true if we got this far
  true
)
