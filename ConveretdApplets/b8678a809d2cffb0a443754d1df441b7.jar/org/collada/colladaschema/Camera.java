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
@XmlType(name = "", propOrder = { "asset", "optics", "imager", "extras" })
@XmlRootElement(name = "camera")
public class Camera
{
    protected Asset asset;
    @XmlElement(required = true)
    protected Optics optics;
    protected Imager imager;
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
    
    public Optics getOptics() {
        return this.optics;
    }
    
    public void setOptics(final Optics value) {
        this.optics = value;
    }
    
    public Imager getImager() {
        return this.imager;
    }
    
    public void setImager(final Imager value) {
        this.imager = value;
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
    @XmlType(name = "", propOrder = { "techniques", "extras" })
    public static class Imager
    {
        @XmlElement(name = "technique", required = true)
        protected List<Technique> techniques;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        
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
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "techniqueCommon", "techniques", "extras" })
    public static class Optics
    {
        @XmlElement(name = "technique_common", required = true)
        protected TechniqueCommon techniqueCommon;
        @XmlElement(name = "technique")
        protected List<Technique> techniques;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        
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
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "orthographic", "perspective" })
        public static class TechniqueCommon
        {
            protected Orthographic orthographic;
            protected Perspective perspective;
            
            public Orthographic getOrthographic() {
                return this.orthographic;
            }
            
            public void setOrthographic(final Orthographic value) {
                this.orthographic = value;
            }
            
            public Perspective getPerspective() {
                return this.perspective;
            }
            
            public void setPerspective(final Perspective value) {
                this.perspective = value;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "xmag", "ymag", "aspectRatio", "znear", "zfar" })
            public static class Orthographic
            {
                protected TargetableFloat xmag;
                protected TargetableFloat ymag;
                @XmlElement(name = "aspect_ratio")
                protected TargetableFloat aspectRatio;
                @XmlElement(required = true)
                protected TargetableFloat znear;
                @XmlElement(required = true)
                protected TargetableFloat zfar;
                
                public TargetableFloat getXmag() {
                    return this.xmag;
                }
                
                public void setXmag(final TargetableFloat value) {
                    this.xmag = value;
                }
                
                public TargetableFloat getYmag() {
                    return this.ymag;
                }
                
                public void setYmag(final TargetableFloat value) {
                    this.ymag = value;
                }
                
                public TargetableFloat getAspectRatio() {
                    return this.aspectRatio;
                }
                
                public void setAspectRatio(final TargetableFloat value) {
                    this.aspectRatio = value;
                }
                
                public TargetableFloat getZnear() {
                    return this.znear;
                }
                
                public void setZnear(final TargetableFloat value) {
                    this.znear = value;
                }
                
                public TargetableFloat getZfar() {
                    return this.zfar;
                }
                
                public void setZfar(final TargetableFloat value) {
                    this.zfar = value;
                }
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "xfov", "yfov", "aspectRatio", "znear", "zfar" })
            public static class Perspective
            {
                protected TargetableFloat xfov;
                protected TargetableFloat yfov;
                @XmlElement(name = "aspect_ratio")
                protected TargetableFloat aspectRatio;
                @XmlElement(required = true)
                protected TargetableFloat znear;
                @XmlElement(required = true)
                protected TargetableFloat zfar;
                
                public TargetableFloat getXfov() {
                    return this.xfov;
                }
                
                public void setXfov(final TargetableFloat value) {
                    this.xfov = value;
                }
                
                public TargetableFloat getYfov() {
                    return this.yfov;
                }
                
                public void setYfov(final TargetableFloat value) {
                    this.yfov = value;
                }
                
                public TargetableFloat getAspectRatio() {
                    return this.aspectRatio;
                }
                
                public void setAspectRatio(final TargetableFloat value) {
                    this.aspectRatio = value;
                }
                
                public TargetableFloat getZnear() {
                    return this.znear;
                }
                
                public void setZnear(final TargetableFloat value) {
                    this.znear = value;
                }
                
                public TargetableFloat getZfar() {
                    return this.zfar;
                }
                
                public void setZfar(final TargetableFloat value) {
                    this.zfar = value;
                }
            }
        }
    }
}
