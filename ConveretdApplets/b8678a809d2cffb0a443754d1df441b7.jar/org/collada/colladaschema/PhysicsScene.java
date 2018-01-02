// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "instanceForceFields", "instancePhysicsModels", "techniqueCommon", "techniques", "extras" })
@XmlRootElement(name = "physics_scene")
public class PhysicsScene
{
    protected Asset asset;
    @XmlElement(name = "instance_force_field")
    protected List<InstanceWithExtra> instanceForceFields;
    @XmlElement(name = "instance_physics_model")
    protected List<InstancePhysicsModel> instancePhysicsModels;
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
    
    public List<InstanceWithExtra> getInstanceForceFields() {
        if (this.instanceForceFields == null) {
            this.instanceForceFields = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceForceFields;
    }
    
    public List<InstancePhysicsModel> getInstancePhysicsModels() {
        if (this.instancePhysicsModels == null) {
            this.instancePhysicsModels = new ArrayList<InstancePhysicsModel>();
        }
        return this.instancePhysicsModels;
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
    @XmlType(name = "", propOrder = { "gravity", "timeStep" })
    public static class TechniqueCommon
    {
        protected TargetableFloat3 gravity;
        @XmlElement(name = "time_step")
        protected TargetableFloat timeStep;
        
        public TargetableFloat3 getGravity() {
            return this.gravity;
        }
        
        public void setGravity(final TargetableFloat3 value) {
            this.gravity = value;
        }
        
        public TargetableFloat getTimeStep() {
            return this.timeStep;
        }
        
        public void setTimeStep(final TargetableFloat value) {
            this.timeStep = value;
        }
    }
}
