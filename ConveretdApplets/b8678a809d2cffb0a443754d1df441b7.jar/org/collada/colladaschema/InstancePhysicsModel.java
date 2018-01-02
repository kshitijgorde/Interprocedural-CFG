// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
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
@XmlType(name = "", propOrder = { "instanceForceFields", "instanceRigidBodies", "instanceRigidConstraints", "extras" })
@XmlRootElement(name = "instance_physics_model")
public class InstancePhysicsModel
{
    @XmlElement(name = "instance_force_field")
    protected List<InstanceWithExtra> instanceForceFields;
    @XmlElement(name = "instance_rigid_body")
    protected List<InstanceRigidBody> instanceRigidBodies;
    @XmlElement(name = "instance_rigid_constraint")
    protected List<InstanceRigidConstraint> instanceRigidConstraints;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute
    protected String parent;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    @XmlAttribute(required = true)
    protected String url;
    
    public List<InstanceWithExtra> getInstanceForceFields() {
        if (this.instanceForceFields == null) {
            this.instanceForceFields = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceForceFields;
    }
    
    public List<InstanceRigidBody> getInstanceRigidBodies() {
        if (this.instanceRigidBodies == null) {
            this.instanceRigidBodies = new ArrayList<InstanceRigidBody>();
        }
        return this.instanceRigidBodies;
    }
    
    public List<InstanceRigidConstraint> getInstanceRigidConstraints() {
        if (this.instanceRigidConstraints == null) {
            this.instanceRigidConstraints = new ArrayList<InstanceRigidConstraint>();
        }
        return this.instanceRigidConstraints;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    public String getParent() {
        return this.parent;
    }
    
    public void setParent(final String value) {
        this.parent = value;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String value) {
        this.url = value;
    }
}
