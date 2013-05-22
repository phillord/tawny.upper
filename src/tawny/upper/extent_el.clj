(ns tawny.upper.extent-el
  (:use [tawny.owl]
        [tawny.upper.annotate])
  (:require [tawny.upper.top]))


(defontology extent 
  :iri "http://www.russet.org.uk/extent-el"
  :prefix "extentel:"
  )

(owlimport tawny.upper.top/top)
(owlimport tawny.upper.annotate/annotate)


(defoproperty hasDimension)
(defoproperty isDimensionOf)

(as-subclasses 
 tawny.upper.top/Extent
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
   )
 
 (defclass ExtentProperty))


(as-subclasses 
 Dimension
 (defclass SpatialDimension
   :comment 
   "A dimension which defines space."
   )
 (defclass TemporalDimension
   :comment
   "A dimension which defines time"
   )
)

(as-subclasses 
 SpatialDimension
 
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
 (with-suffix
   DimensionalRegion

   (defclass One)
   
   (defclass Two)
   
   (defclass Three)

   (defclass Four
     :subclass SpatialTemporalRegion)))

(defclass NamedRegion
  :subclass Region)

(as-subclasses 
 NamedRegion
 (defclass Line)
 
 (defclass Duration)
 
 (defclass Area)
 
 (defclass Volume))

(as-subclasses
 ExtentProperty
 (defclass Continuity)
 
 (defclass Boundedness)

 (defclass Finiteness)
 )

(as-subclasses
 Continuity
 (defclass Continuous)
 (defclass Discontinuous))

(as-subclasses
 Boundedness

 (defclass Bounded)
 (defclass Unbounded
   :annotation
   (scope "As we do not presuppose any particular geometry for the universe,
this is orthogonal to, and not presuppose Infinite. The surface of the
earth is finite, yet unbounded.")))

(as-subclasses
 Finiteness
 (defclass Finite)
 (defclass Infinite
   :annotation 
   (scope "The universe is finite is size, so the interpretation of this is
dependent on the circumstances. This reflects common usage: infinity can
actually be quite small, with optical infinity meaning any distance greater
than a few metres.")))

