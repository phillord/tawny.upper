
(ns tawny.upper.extent
  (:use [tawny.owl])
  (:require [tawny.upper.top]))


(defontology extent 
  :iri "http://www.russet.org.uk/extent"
  :prefix "extent:"
  )

(owlimport tawny.upper.top/top)

(declare-classes Dimension Region ReferenceFrame)
(declare-classes SpatialDimension TemporalDimension)

(as-inverse
  (defoproperty hasDimension
   ;;:domain Region
   ;;:range Dimension
   )
 (defoproperty isDimensionOf
   ;;:domain Dimension
   ;;:range Region
   ))

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
 (defclass Dimension
   ;; :subclass 
   ;; (owlsome withRespectTo ReferenceFrame)
   :comment 
   "A axis along which entities can progress in a direction orthogonal to any other."
   )
 (defclass ReferenceFrame
   ;; :subclass 
   ;; (owlsome givesRespectTo Dimension)
   :comment
   "A central point against which an Extent is defined."
   )

 (defclass Point
   :comment
   "A location within spacetime with no dimensions."
   )

 (defclass Region
   :comment
   "A physical extent in time or space."
   :subclass
   (owlsome hasDimension Dimension)
   (atmost 3 hasDimension SpatialDimension)
   (atmost 1 hasDimension TemporalDimension)
   )
 
 (defclass ExtentProperty)

 )

(as-subclasses 
 Dimension
 :disjoint :cover                       
 (defclass SpatialDimension
   :comment 
   "A dimension which defines space."
   )
 (defclass TemporalDimension
   :comment
   "A dimension which defines time"
   )
)

(as-disjoint-subclasses 
 SpatialDimension
 :disjoint :cover
 
 (let [comment
       "A Spatial Dimension in a given Reference Frame which is orthogonal to 
%s and %s."]

   (defclass XDimension
     :comment
     (format comment "YDimension" "ZDimension")
     )
   (defclass YDimension
     :comment
     (format comment "XDimension" "ZDimension")
     )
   (defclass ZDimension
     :comment
     (format comment "XDimension" "YDimension"))))

(as-subclasses 
 Region
 (defclass KindOfRegion)

 (defclass NDimensionalRegion)
 )


(as-subclasses 
 KindOfRegion
 (defclass SpatialTemporalRegion
  :comment 
  "A region in spacetime"
  :equivalent
  (owlsome hasDimension 
           SpatialDimension 
           TemporalDimension))

 (defclass SpatialRegion
   :comment 
   "A region in space."
   :equivalent
   (only hasDimension SpatialDimension)
   )
 (defclass TemporalRegion
   :comment 
   "A region in time."
   :equivalent
   (only hasDimension TemporalDimension)
   )
 )

(as-subclasses 
 NDimensionalRegion
 
 (with-suffix
   DimensionalRegion

   ;; Regions are defined as something with some dimension, so we can't have a
   ;; zero dimension region. Need to think about this. 

   ;; (defclass Zero)

   
   (defclass One
     :equivalent
     (exactly 1 hasDimension Dimension))
   
   (defclass Two
     :equivalent
     (exactly 2 hasDimension Dimension))
   
   (defclass Three
     :equivalent 
     (exactly 3 hasDimension Dimension)
     )

   (defclass Four
     :equivalent
     (exactly 4 hasDimension Dimension)))
 )


(defclass Line
  :equivalent 
  (exactly 1 hasDimension SpatialDimension))

(as-disjoint-subclasses
 ExtentProperty
 (defclass Continuity)
 
 (defclass Boundedness)

 (defclass Finiteness)
 )

(as-disjoint-subclasses
 Continuity
 (defclass Continuous)
 (defclass Discontinuous))


(as-disjoint-subclasses
 Boundedness

 (defclass Bounded)
 (defclass Unbounded)
 )

(as-disjoint-subclasses
 Finiteness
 (defclass Finite)

 (defclass Infinite)
)



