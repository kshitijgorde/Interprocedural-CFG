// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlElements;
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
@XmlType(name = "", propOrder = { "refAttachment", "attachment", "techniqueCommon", "techniques", "extras" })
@XmlRootElement(name = "rigid_constraint")
public class RigidConstraint
{
    @XmlElement(name = "ref_attachment", required = true)
    protected RefAttachment refAttachment;
    @XmlElement(required = true)
    protected Attachment attachment;
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
    
    public RefAttachment getRefAttachment() {
        return this.refAttachment;
    }
    
    public void setRefAttachment(final RefAttachment value) {
        this.refAttachment = value;
    }
    
    public Attachment getAttachment() {
        return this.attachment;
    }
    
    public void setAttachment(final Attachment value) {
        this.attachment = value;
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
    @XmlType(name = "", propOrder = { "rotatesAndExtrasAndTranslates" })
    public static class Attachment
    {
        @XmlElements({ @XmlElement(name = "extra", type = Extra.class), @XmlElement(name = "rotate", type = Rotate.class), @XmlElement(name = "translate", type = TargetableFloat3.class) })
        protected List<Object> rotatesAndExtrasAndTranslates;
        @XmlAttribute(name = "rigid_body")
        protected String rigidBody;
        
        public List<Object> getRotatesAndExtrasAndTranslates() {
            if (this.rotatesAndExtrasAndTranslates == null) {
                this.rotatesAndExtrasAndTranslates = new ArrayList<Object>();
            }
            return this.rotatesAndExtrasAndTranslates;
        }
        
        public String getRigidBody() {
            return this.rigidBody;
        }
        
        public void setRigidBody(final String value) {
            this.rigidBody = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "extrasAndTranslatesAndRotates" })
    public static class RefAttachment
    {
        @XmlElements({ @XmlElement(name = "translate", type = TargetableFloat3.class), @XmlElement(name = "rotate", type = Rotate.class), @XmlElement(name = "extra", type = Extra.class) })
        protected List<Object> extrasAndTranslatesAndRotates;
        @XmlAttribute(name = "rigid_body")
        protected String rigidBody;
        
        public List<Object> getExtrasAndTranslatesAndRotates() {
            if (this.extrasAndTranslatesAndRotates == null) {
                this.extrasAndTranslatesAndRotates = new ArrayList<Object>();
            }
            return this.extrasAndTranslatesAndRotates;
        }
        
        public String getRigidBody() {
            return this.rigidBody;
        }
        
        public void setRigidBody(final String value) {
            this.rigidBody = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "enabled", "interpenetrate", "limits", "spring" })
    public static class TechniqueCommon
    {
        @XmlElement(defaultValue = "true")
        protected Enabled enabled;
        @XmlElement(defaultValue = "false")
        protected Interpenetrate interpenetrate;
        protected Limits limits;
        protected Spring spring;
        
        public Enabled getEnabled() {
            return this.enabled;
        }
        
        public void setEnabled(final Enabled value) {
            this.enabled = value;
        }
        
        public Interpenetrate getInterpenetrate() {
            return this.interpenetrate;
        }
        
        public void setInterpenetrate(final Interpenetrate value) {
            this.interpenetrate = value;
        }
        
        public Limits getLimits() {
            return this.limits;
        }
        
        public void setLimits(final Limits value) {
            this.limits = value;
        }
        
        public Spring getSpring() {
            return this.spring;
        }
        
        public void setSpring(final Spring value) {
            this.spring = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "value" })
        public static class Enabled
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
        @XmlType(name = "", propOrder = { "value" })
        public static class Interpenetrate
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
        @XmlType(name = "", propOrder = { "swingConeAndTwist", "linear" })
        public static class Limits
        {
            @XmlElement(name = "swing_cone_and_twist")
            protected SwingConeAndTwist swingConeAndTwist;
            protected Linear linear;
            
            public SwingConeAndTwist getSwingConeAndTwist() {
                return this.swingConeAndTwist;
            }
            
            public void setSwingConeAndTwist(final SwingConeAndTwist value) {
                this.swingConeAndTwist = value;
            }
            
            public Linear getLinear() {
                return this.linear;
            }
            
            public void setLinear(final Linear value) {
                this.linear = value;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "min", "max" })
            public static class Linear
            {
                @XmlElement(defaultValue = "0.0 0.0 0.0")
                protected TargetableFloat3 min;
                @XmlElement(defaultValue = "0.0 0.0 0.0")
                protected TargetableFloat3 max;
                
                public TargetableFloat3 getMin() {
                    return this.min;
                }
                
                public void setMin(final TargetableFloat3 value) {
                    this.min = value;
                }
                
                public TargetableFloat3 getMax() {
                    return this.max;
                }
                
                public void setMax(final TargetableFloat3 value) {
                    this.max = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "min", "max" })
            public static class SwingConeAndTwist
            {
                @XmlElement(defaultValue = "0.0 0.0 0.0")
                protected TargetableFloat3 min;
                @XmlElement(defaultValue = "0.0 0.0 0.0")
                protected TargetableFloat3 max;
                
                public TargetableFloat3 getMin() {
                    return this.min;
                }
                
                public void setMin(final TargetableFloat3 value) {
                    this.min = value;
                }
                
                public TargetableFloat3 getMax() {
                    return this.max;
                }
                
                public void setMax(final TargetableFloat3 value) {
                    this.max = value;
                }
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "angular", "linear" })
        public static class Spring
        {
            protected Angular angular;
            protected Linear linear;
            
            public Angular getAngular() {
                return this.angular;
            }
            
            public void setAngular(final Angular value) {
                this.angular = value;
            }
            
            public Linear getLinear() {
                return this.linear;
            }
            
            public void setLinear(final Linear value) {
                this.linear = value;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "stiffness", "damping", "targetValue" })
            public static class Angular
            {
                @XmlElement(defaultValue = "1.0")
                protected TargetableFloat stiffness;
                @XmlElement(defaultValue = "0.0")
                protected TargetableFloat damping;
                @XmlElement(name = "target_value", defaultValue = "0.0")
                protected TargetableFloat targetValue;
                
                public TargetableFloat getStiffness() {
                    return this.stiffness;
                }
                
                public void setStiffness(final TargetableFloat value) {
                    this.stiffness = value;
                }
                
                public TargetableFloat getDamping() {
                    return this.damping;
                }
                
                public void setDamping(final TargetableFloat value) {
                    this.damping = value;
                }
                
                public TargetableFloat getTargetValue() {
                    return this.targetValue;
                }
                
                public void setTargetValue(final TargetableFloat value) {
                    this.targetValue = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "stiffness", "damping", "targetValue" })
            public static class Linear
            {
                @XmlElement(defaultValue = "1.0")
                protected TargetableFloat stiffness;
                @XmlElement(defaultValue = "0.0")
                protected TargetableFloat damping;
                @XmlElement(name = "target_value", defaultValue = "0.0")
                protected TargetableFloat targetValue;
                
                public TargetableFloat getStiffness() {
                    return this.stiffness;
                }
                
                public void setStiffness(final TargetableFloat value) {
                    this.stiffness = value;
                }
                
                public TargetableFloat getDamping() {
                    return this.damping;
                }
                
                public void setDamping(final TargetableFloat value) {
                    this.damping = value;
                }
                
                public TargetableFloat getTargetValue() {
                    return this.targetValue;
                }
                
                public void setTargetValue(final TargetableFloat value) {
                    this.targetValue = value;
                }
            }
        }
    }
}
