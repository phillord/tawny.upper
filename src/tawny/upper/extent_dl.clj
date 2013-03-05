
(ns tawny.upper.extent-dl
  (:use [tawny.owl]
        [tawny.upper.annotate])
  (:require [tawny.upper.top]))



(defontology extent 
  :iri "http://www.russet.org.uk/extent"
  :prefix "extent:"
  )

(owlimport tawny.upper.top/top)
(owlimport tawny.upper.annotate/annotate)


(declare-classes Dimension Region ReferenceFrame)
(declare-classes SpatialDimension TemporalDimension)

(as-inverse
  (defoproperty hasDimension)
  (defoproperty isDimensionOf))

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
   :annotation
   (scope 
"No attempt is made to describe the properties of dimensions beyond that given
in the definition. In particular, there is no assumption of a particular
geometry. So, with respect to the earth, up and down, north and south, east
and west would be all be valid dimensions."))
 
 ;; thinking of ditching this...
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
   (atmost 4 hasDimension Dimension)
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
 (defclass SpatialTemporalRegion
  :comment 
  "A region in spacetime"
  :equivalent
  (owland
   (owlsome hasDimension SpatialDimension)
   (owlsome hasDimension TemporalDimension))
  )

 (defclass SpatialRegion
   :comment 
   "A region in space."
   :equivalent
   (owlsome hasDimension SpatialDimension)
   )
   
 (defclass TemporalRegion
   :comment 
   "A region in time."
   :equivalent
   (owlsome hasDimension TemporalDimension)
   ))



(as-subclasses 
 Region
 :disjoint
 
 (with-suffix
   DimensionalRegion

   (defclass One
     :equivalent
     (exactly 1 hasDimension Dimension)
     )
   
   (defclass Two
     :equivalent
     (exactly 2 hasDimension Dimension)
     )
   
   (defclass Three
     :equivalent 
     (exactly 3 hasDimension Dimension)
     )

   (defclass Four
     :subclass SpatialTemporalRegion     
     :equivalent
     (exactly 4 hasDimension Dimension)
    )))

(defclass NamedRegion
  :subclass Region)

(defclass Line
  :subclass NamedRegion
  (exactly 1 hasDimension Dimension)
  (only hasDimension SpatialDimension)
  )

(defclass Duration
  :subclass NamedRegion
  (only hasDimension TemporalDimension)
  (exactly 1 hasDimension TemporalDimension))

(defclass Area 
  :subclass NamedRegion
  (only hasDimension SpatialDimension)
  (exactly 2 hasDimension SpatialDimension))

(defclass Volume
  :subclass NamedRegion
  (only hasDimension SpatialDimension)
  (exactly 3 hasDimension SpatialDimension))


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
 (defclass Unbounded
   :annotation
   (scope "As we do not presuppose any particular geometry for the universe,
   this is orthogonal to, and not presuppose Infinite. The surface of the
   earth is finite, yet unbounded.")))

(as-disjoint-subclasses
 Finiteness
 (defclass Finite)
 (defclass Infinite
   :annotation 
   (scope "The universe is finite is size, so the interpretation of this is
   dependent on the circumstances. This reflects common usage: infinity can
   actually be quite small, with optical infinity meaning any distance creater
   than a few metres.")

   )
)



