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
@XmlType(name = "", propOrder = { "asset", "rigidBodies", "rigidConstraints", "instancePhysicsModels", "extras" })
@XmlRootElement(name = "physics_model")
public class PhysicsModel
{
    protected Asset asset;
    @XmlElement(name = "rigid_body")
    protected List<RigidBody> rigidBodies;
    @XmlElement(name = "rigid_constraint")
    protected List<RigidConstraint> rigidConstraints;
    @XmlElement(name = "instance_physics_model")
    protected List<InstancePhysicsModel> instancePhysicsModels;
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
    
    public List<RigidBody> getRigidBodies() {
        if (this.rigidBodies == null) {
            this.rigidBodies = new ArrayList<RigidBody>();
        }
        return this.rigidBodies;
    }
    
    public List<RigidConstraint> getRigidConstraints() {
        if (this.rigidConstraints == null) {
            this.rigidConstraints = new ArrayList<RigidConstraint>();
        }
        return this.rigidConstraints;
    }
    
    public List<InstancePhysicsModel> getInstancePhysicsModels() {
        if (this.instancePhysicsModels == null) {
            this.instancePhysicsModels = new ArrayList<InstancePhysicsModel>();
        }
        return this.instancePhysicsModels;
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
}
