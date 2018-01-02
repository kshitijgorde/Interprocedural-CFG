// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
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
@XmlType(name = "", propOrder = { "techniqueCommon", "techniques", "extras" })
@XmlRootElement(name = "rigid_body")
public class RigidBody
{
    @XmlElement(name = "technique_common", required = true)
    protected TechniqueCommon techniqueCommon;
    @XmlElement(name = "technique")
    protected List<Technique> techniques;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
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
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "dynamic", "mass", "massFrame", "inertia", "instancePhysicsMaterial", "physicsMaterial", "shapes" })
    public static class TechniqueCommon
    {
        protected Dynamic dynamic;
        protected TargetableFloat mass;
        @XmlElement(name = "mass_frame")
        protected MassFrame massFrame;
        protected TargetableFloat3 inertia;
        @XmlElement(name = "instance_physics_material")
        protected InstanceWithExtra instancePhysicsMaterial;
        @XmlElement(name = "physics_material")
        protected PhysicsMaterial physicsMaterial;
        @XmlElement(name = "shape", required = true)
        protected List<Shape> shapes;
        
        public Dynamic getDynamic() {
            return this.dynamic;
        }
        
        public void setDynamic(final Dynamic value) {
            this.dynamic = value;
        }
        
        public TargetableFloat getMass() {
            return this.mass;
        }
        
        public void setMass(final TargetableFloat value) {
            this.mass = value;
        }
        
        public MassFrame getMassFrame() {
            return this.massFrame;
        }
        
        public void setMassFrame(final MassFrame value) {
            this.massFrame = value;
        }
        
        public TargetableFloat3 getInertia() {
            return this.inertia;
        }
        
        public void setInertia(final TargetableFloat3 value) {
            this.inertia = value;
        }
        
        public InstanceWithExtra getInstancePhysicsMaterial() {
            return this.instancePhysicsMaterial;
        }
        
        public void setInstancePhysicsMaterial(final InstanceWithExtra value) {
            this.instancePhysicsMaterial = value;
        }
        
        public PhysicsMaterial getPhysicsMaterial() {
            return this.physicsMaterial;
        }
        
        public void setPhysicsMaterial(final PhysicsMaterial value) {
            this.physicsMaterial = value;
        }
        
        public List<Shape> getShapes() {
            if (this.shapes == null) {
                this.shapes = new ArrayList<Shape>();
            }
            return this.shapes;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "value" })
        public static class Dynamic
        {
            @XmlValue
            protected boolean value;
            @XmlAttribute
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String sid;
            
            public boolean isValue() {
                return this.value;
            }
            
            public void setValue(final boolean value) {
                this.value = value;
            }
            
            public String getSid() {
                return this.sid;
            }
            
            public void setSid(final String value) {
                this.sid = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "translatesAndRotates" })
        public static class MassFrame
        {
            @XmlElements({ @XmlElement(name = "translate", type = TargetableFloat3.class), @XmlElement(name = "rotate", type = Rotate.class) })
            protected List<Object> translatesAndRotates;
            
            public List<Object> getTranslatesAndRotates() {
                if (this.translatesAndRotates == null) {
                    this.translatesAndRotates = new ArrayList<Object>();
                }
                return this.translatesAndRotates;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "hollow", "mass", "density", "physicsMaterial", "instancePhysicsMaterial", "instanceGeometry", "cylinder", "taperedCylinder", "plane", "box", "sphere", "taperedCapsule", "capsule", "translatesAndRotates", "extras" })
        public static class Shape
        {
            protected Hollow hollow;
            protected TargetableFloat mass;
            protected TargetableFloat density;
            @XmlElement(name = "physics_material")
            protected PhysicsMaterial physicsMaterial;
            @XmlElement(name = "instance_physics_material")
            protected InstanceWithExtra instancePhysicsMaterial;
            @XmlElement(name = "instance_geometry")
            protected InstanceGeometry instanceGeometry;
            protected Cylinder cylinder;
            @XmlElement(name = "tapered_cylinder")
            protected TaperedCylinder taperedCylinder;
            protected Plane plane;
            protected Box box;
            protected Sphere sphere;
            @XmlElement(name = "tapered_capsule")
            protected TaperedCapsule taperedCapsule;
            protected Capsule capsule;
            @XmlElements({ @XmlElement(name = "rotate", type = Rotate.class), @XmlElement(name = "translate", type = TargetableFloat3.class) })
            protected List<Object> translatesAndRotates;
            @XmlElement(name = "extra")
            protected List<Extra> extras;
            
            public Hollow getHollow() {
                return this.hollow;
            }
            
            public void setHollow(final Hollow value) {
                this.hollow = value;
            }
            
            public TargetableFloat getMass() {
                return this.mass;
            }
            
            public void setMass(final TargetableFloat value) {
                this.mass = value;
            }
            
            public TargetableFloat getDensity() {
                return this.density;
            }
            
            public void setDensity(final TargetableFloat value) {
                this.density = value;
            }
            
            public PhysicsMaterial getPhysicsMaterial() {
                return this.physicsMaterial;
            }
            
            public void setPhysicsMaterial(final PhysicsMaterial value) {
                this.physicsMaterial = value;
            }
            
            public InstanceWithExtra getInstancePhysicsMaterial() {
                return this.instancePhysicsMaterial;
            }
            
            public void setInstancePhysicsMaterial(final InstanceWithExtra value) {
                this.instancePhysicsMaterial = value;
            }
            
            public InstanceGeometry getInstanceGeometry() {
                return this.instanceGeometry;
            }
            
            public void setInstanceGeometry(final InstanceGeometry value) {
                this.instanceGeometry = value;
            }
            
            public Cylinder getCylinder() {
                return this.cylinder;
            }
            
            public void setCylinder(final Cylinder value) {
                this.cylinder = value;
            }
            
            public TaperedCylinder getTaperedCylinder() {
                return this.taperedCylinder;
            }
            
            public void setTaperedCylinder(final TaperedCylinder value) {
                this.taperedCylinder = value;
            }
            
            public Plane getPlane() {
                return this.plane;
            }
            
            public void setPlane(final Plane value) {
                this.plane = value;
            }
            
            public Box getBox() {
                return this.box;
            }
            
            public void setBox(final Box value) {
                this.box = value;
            }
            
            public Sphere getSphere() {
                return this.sphere;
            }
            
            public void setSphere(final Sphere value) {
                this.sphere = value;
            }
            
            public TaperedCapsule getTaperedCapsule() {
                return this.taperedCapsule;
            }
            
            public void setTaperedCapsule(final TaperedCapsule value) {
                this.taperedCapsule = value;
            }
            
            public Capsule getCapsule() {
                return this.capsule;
            }
            
            public void setCapsule(final Capsule value) {
                this.capsule = value;
            }
            
            public List<Object> getTranslatesAndRotates() {
                if (this.translatesAndRotates == null) {
                    this.translatesAndRotates = new ArrayList<Object>();
                }
                return this.translatesAndRotates;
            }
            
            public List<Extra> getExtras() {
                if (this.extras == null) {
                    this.extras = new ArrayList<Extra>();
                }
                return this.extras;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "value" })
            public static class Hollow
            {
                @XmlValue
                protected boolean value;
                @XmlAttribute
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String sid;
                
                public boolean isValue() {
                    return this.value;
                }
                
                public void setValue(final boolean value) {
                    this.value = value;
                }
                
                public String getSid() {
                    return this.sid;
                }
                
                public void setSid(final String value) {
                    this.sid = value;
                }
            }
        }
    }
}
