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
@XmlRootElement(name = "physics_material")
public class PhysicsMaterial
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
    @XmlType(name = "", propOrder = { "dynamicFriction", "restitution", "staticFriction" })
    public static class TechniqueCommon
    {
        @XmlElement(name = "dynamic_friction")
        protected TargetableFloat dynamicFriction;
        protected TargetableFloat restitution;
        @XmlElement(name = "static_friction")
        protected TargetableFloat staticFriction;
        
        public TargetableFloat getDynamicFriction() {
            return this.dynamicFriction;
        }
        
        public void setDynamicFriction(final TargetableFloat value) {
            this.dynamicFriction = value;
        }
        
        public TargetableFloat getRestitution() {
            return this.restitution;
        }
        
        public void setRestitution(final TargetableFloat value) {
            this.restitution = value;
        }
        
        public TargetableFloat getStaticFriction() {
            return this.staticFriction;
        }
        
        public void setStaticFriction(final TargetableFloat value) {
            this.staticFriction = value;
        }
    }
}
