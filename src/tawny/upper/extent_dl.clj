
(ns tawny.upper.extent-dl
  (:use [tawny.owl]
        [tawny.upper.annotate])
  (:require [tawny.upper.top]
            [tawny.upper.extent-el :as el]
            ))


(defontology extent 
  :iri "http://www.russet.org.uk/extent-dl"
  :prefix "extentdl:"
  )

(owlimport tawny.upper.top/top)
(owlimport tawny.upper.annotate/annotate)

(declare-classes Dimension Region ReferenceFrame)
(declare-classes SpatialDimension TemporalDimension)

(as-inverse
  (defrefine el/hasDimension)
  (defrefine el/isDimensionOf))

;; ;; (as-inverse 
;; ;;  (defoproperty withRespectTo
;; ;;    :domain Dimension
;; ;;    :range ReferenceFrame)
;; ;;  (defoproperty givesRespectTo
;; ;;    :domain ReferenceFrame
;; ;;    :range Dimension))


(as-subclasses 
 tawny.upper.top/Extent
 :disjoint :cover
 (defrefine el/Dimension)
 
 ;; thinking of ditching this...
 (defrefine el/ReferenceFrame)

 (defrefine el/Point)

 (defrefine el/Region
   :subclass
   (atmost 4 hasDimension Dimension)
   )
 
 (defrefine el/ExtentProperty))

(as-subclasses 
 Dimension
 :disjoint :cover                       
 (defrefine el/SpatialDimension)
 (defrefine el/TemporalDimension)
)

(as-subclasses 
 SpatialDimension
 :disjoint :cover
 
 (defrefine el/XDimension)
 (defrefine el/YDimension)
 (defrefine el/ZDimension))


(defrefine el/SpatialRegion)
(defrefine el/TemporalRegion)
(defrefine el/SpatialTemporalRegion)


(as-subclasses 
 Region
 :disjoint
 
 (defrefine el/OneDimensionalRegion
   :equivalent
   (exactly 1 hasDimension Dimension)
   )
 
 (defrefine el/TwoDimensionalRegion
   :equivalent
   (exactly 2 hasDimension Dimension)
   )
 
 (defrefine el/ThreeDimensionalRegion
   :equivalent 
   (exactly 3 hasDimension Dimension)
   )

 (defrefine el/FourDimensionalRegion
   :subclass SpatialTemporalRegion     
   :equivalent
   (exactly 4 hasDimension Dimension)
   ))

(defrefine el/NamedRegion)

(defrefine el/Line
  :subclass
  (exactly 1 hasDimension Dimension)
  (only hasDimension SpatialDimension)
  )

(defrefine el/Duration
  :subclass NamedRegion
  (only hasDimension TemporalDimension)
  (exactly 1 hasDimension TemporalDimension))

(defrefine el/Area 
  :subclass NamedRegion
  (only hasDimension SpatialDimension)
  (exactly 2 hasDimension SpatialDimension))

(defrefine el/Volume
  :subclass NamedRegion
  (only hasDimension SpatialDimension)
  (exactly 3 hasDimension SpatialDimension))


(as-disjoint-subclasses
 ExtentProperty
 (defrefine el/Continuity)
 
 (defrefine el/Boundedness)

 (defrefine el/Finiteness)
 )

(as-disjoint-subclasses
 Continuity
 (defrefine el/Continuous)
 (defrefine el/Discontinuous))


(as-disjoint-subclasses
 Boundedness
 (defrefine el/Bounded)
 (defrefine el/Unbounded))

(as-disjoint-subclasses
 Finiteness
 (defrefine el/Finite)
 (defrefine el/Infinite))



