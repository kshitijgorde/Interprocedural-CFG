// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "techniqueCommon", "techniques", "extras" })
@XmlRootElement(name = "light")
public class Light
{
    protected Asset asset;
    @XmlElement(name = "technique_common", required = true)
    protected TechniqueCommon techniqueCommon;
    @XmlElement(name = "technique")
    protected List<Technique> techniques;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public TechniqueCommon getTechniqueCommon() {
        return this.techniqueCommon;
    }
    
    public void setTechniqueCommon(final TechniqueCommon value) {
        this.techniqueCommon = value;
    }
    
    public List<Technique> getTechniques() {
        if (this.techniques == null) {
            this.techniques = new ArrayList<Technique>();
        }
        return this.techniques;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "spot", "point", "ambient", "directional" })
    public static class TechniqueCommon
    {
        protected Spot spot;
        protected Point point;
        protected Ambient ambient;
        protected Directional directional;
        
        public Spot getSpot() {
            return this.spot;
        }
        
        public void setSpot(final Spot value) {
            this.spot = value;
        }
        
        public Point getPoint() {
            return this.point;
        }
        
        public void setPoint(final Point value) {
            this.point = value;
        }
        
        public Ambient getAmbient() {
            return this.ambient;
        }
        
        public void setAmbient(final Ambient value) {
            this.ambient = value;
        }
        
        public Directional getDirectional() {
            return this.directional;
        }
        
        public void setDirectional(final Directional value) {
            this.directional = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "color" })
        public static class Ambient
        {
            @XmlElement(required = true)
            protected TargetableFloat3 color;
            
            public TargetableFloat3 getColor() {
                return this.color;
            }
            
            public void setColor(final TargetableFloat3 value) {
                this.color = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "color" })
        public static class Directional
        {
            @XmlElement(required = true)
            protected TargetableFloat3 color;
            
            public TargetableFloat3 getColor() {
                return this.color;
            }
            
            public void setColor(final TargetableFloat3 value) {
                this.color = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "color", "constantAttenuation", "linearAttenuation", "quadraticAttenuation" })
        public static class Point
        {
            @XmlElement(required = true)
            protected TargetableFloat3 color;
            @XmlElement(name = "constant_attenuation", defaultValue = "1.0")
            protected TargetableFloat constantAttenuation;
            @XmlElement(name = "linear_attenuation", defaultValue = "0.0")
            protected TargetableFloat linearAttenuation;
            @XmlElement(name = "quadratic_attenuation", defaultValue = "0.0")
            protected TargetableFloat quadraticAttenuation;
            
            public TargetableFloat3 getColor() {
                return this.color;
            }
            
            public void setColor(final TargetableFloat3 value) {
                this.color = value;
            }
            
            public TargetableFloat getConstantAttenuation() {
                return this.constantAttenuation;
            }
            
            public void setConstantAttenuation(final TargetableFloat value) {
                this.constantAttenuation = value;
            }
            
            public TargetableFloat getLinearAttenuation() {
                return this.linearAttenuation;
            }
            
            public void setLinearAttenuation(final TargetableFloat value) {
                this.linearAttenuation = value;
            }
            
            public TargetableFloat getQuadraticAttenuation() {
                return this.quadraticAttenuation;
            }
            
            public void setQuadraticAttenuation(final TargetableFloat value) {
                this.quadraticAttenuation = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "color", "constantAttenuation", "linearAttenuation", "quadraticAttenuation", "falloffAngle", "falloffExponent" })
        public static class Spot
        {
            @XmlElement(required = true)
            protected TargetableFloat3 color;
            @XmlElement(name = "constant_attenuation", defaultValue = "1.0")
            protected TargetableFloat constantAttenuation;
            @XmlElement(name = "linear_attenuation", defaultValue = "0.0")
            protected TargetableFloat linearAttenuation;
            @XmlElement(name = "quadratic_attenuation", defaultValue = "0.0")
            protected TargetableFloat quadraticAttenuation;
            @XmlElement(name = "falloff_angle", defaultValue = "180.0")
            protected TargetableFloat falloffAngle;
            @XmlElement(name = "falloff_exponent", defaultValue = "0.0")
            protected TargetableFloat falloffExponent;
            
            public TargetableFloat3 getColor() {
                return this.color;
            }
            
            public void setColor(final TargetableFloat3 value) {
                this.color = value;
            }
            
            public TargetableFloat getConstantAttenuation() {
                return this.constantAttenuation;
            }
            
            public void setConstantAttenuation(final TargetableFloat value) {
                this.constantAttenuation = value;
            }
            
            public TargetableFloat getLinearAttenuation() {
                return this.linearAttenuation;
            }
            
            public void setLinearAttenuation(final TargetableFloat value) {
                this.linearAttenuation = value;
            }
            
            public TargetableFloat getQuadraticAttenuation() {
                return this.quadraticAttenuation;
            }
            
            public void setQuadraticAttenuation(final TargetableFloat value) {
                this.quadraticAttenuation = value;
            }
            
            public TargetableFloat getFalloffAngle() {
                return this.falloffAngle;
            }
            
            public void setFalloffAngle(final TargetableFloat value) {
                this.falloffAngle = value;
            }
            
            public TargetableFloat getFalloffExponent() {
                return this.falloffExponent;
            }
            
            public void setFalloffExponent(final TargetableFloat value) {
                this.falloffExponent = value;
            }
        }
    }
}
