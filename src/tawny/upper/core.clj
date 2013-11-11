
(ns tawny.upper.core
  (:use [tawny.owl])
  (:require [tawny.upper top extent-el extent-dl entity minimal])
  (:gen-class))


(defn save-and-import-ontology [[name ontology]]
  (owl-import ontology)
    
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
     ["annotate" tawny.upper.annotate/annotate]
     ["extent-el" tawny.upper.extent-el/extent]
     ["extent-dl" tawny.upper.extent-dl/extent]
     ["entity"  tawny.upper.entity/entity]
     ["minimal" tawny.upper.minimal/minimal]
     ]))

  (save-ontology "core.omn" :omn)
  (save-ontology "core.owl" :owl)

  ;; return true if we got this far
  true
)



(defn -main [& args]
  (save-all))
