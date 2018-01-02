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
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "imagesAndNewparams", "technique", "extras" })
public class ProfileCOMMON
{
    protected Asset asset;
    @XmlElements({ @XmlElement(name = "newparam", type = CommonNewparamType.class), @XmlElement(name = "image", type = Image.class) })
    protected List<Object> imagesAndNewparams;
    @XmlElement(required = true)
    protected Technique technique;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<Object> getImagesAndNewparams() {
        if (this.imagesAndNewparams == null) {
            this.imagesAndNewparams = new ArrayList<Object>();
        }
        return this.imagesAndNewparams;
    }
    
    public Technique getTechnique() {
        return this.technique;
    }
    
    public void setTechnique(final Technique value) {
        this.technique = value;
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
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "asset", "imagesAndNewparams", "constant", "blinn", "lambert", "phong", "extras" })
    public static class Technique
    {
        protected Asset asset;
        @XmlElements({ @XmlElement(name = "newparam", type = CommonNewparamType.class), @XmlElement(name = "image", type = Image.class) })
        protected List<Object> imagesAndNewparams;
        protected Constant constant;
        protected Blinn blinn;
        protected Lambert lambert;
        protected Phong phong;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sid;
        
        public Asset getAsset() {
            return this.asset;
        }
        
        public void setAsset(final Asset value) {
            this.asset = value;
        }
        
        public List<Object> getImagesAndNewparams() {
            if (this.imagesAndNewparams == null) {
                this.imagesAndNewparams = new ArrayList<Object>();
            }
            return this.imagesAndNewparams;
        }
        
        public Constant getConstant() {
            return this.constant;
        }
        
        public void setConstant(final Constant value) {
            this.constant = value;
        }
        
        public Blinn getBlinn() {
            return this.blinn;
        }
        
        public void setBlinn(final Blinn value) {
            this.blinn = value;
        }
        
        public Lambert getLambert() {
            return this.lambert;
        }
        
        public void setLambert(final Lambert value) {
            this.lambert = value;
        }
        
        public Phong getPhong() {
            return this.phong;
        }
        
        public void setPhong(final Phong value) {
            this.phong = value;
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
        
        public String getSid() {
            return this.sid;
        }
        
        public void setSid(final String value) {
            this.sid = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "emission", "ambient", "diffuse", "specular", "shininess", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction" })
        public static class Blinn
        {
            protected CommonColorOrTextureType emission;
            protected CommonColorOrTextureType ambient;
            protected CommonColorOrTextureType diffuse;
            protected CommonColorOrTextureType specular;
            protected CommonFloatOrParamType shininess;
            protected CommonColorOrTextureType reflective;
            protected CommonFloatOrParamType reflectivity;
            protected CommonTransparentType transparent;
            protected CommonFloatOrParamType transparency;
            @XmlElement(name = "index_of_refraction")
            protected CommonFloatOrParamType indexOfRefraction;
            
            public CommonColorOrTextureType getEmission() {
                return this.emission;
            }
            
            public void setEmission(final CommonColorOrTextureType value) {
                this.emission = value;
            }
            
            public CommonColorOrTextureType getAmbient() {
                return this.ambient;
            }
            
            public void setAmbient(final CommonColorOrTextureType value) {
                this.ambient = value;
            }
            
            public CommonColorOrTextureType getDiffuse() {
                return this.diffuse;
            }
            
            public void setDiffuse(final CommonColorOrTextureType value) {
                this.diffuse = value;
            }
            
            public CommonColorOrTextureType getSpecular() {
                return this.specular;
            }
            
            public void setSpecular(final CommonColorOrTextureType value) {
                this.specular = value;
            }
            
            public CommonFloatOrParamType getShininess() {
                return this.shininess;
            }
            
            public void setShininess(final CommonFloatOrParamType value) {
                this.shininess = value;
            }
            
            public CommonColorOrTextureType getReflective() {
                return this.reflective;
            }
            
            public void setReflective(final CommonColorOrTextureType value) {
                this.reflective = value;
            }
            
            public CommonFloatOrParamType getReflectivity() {
                return this.reflectivity;
            }
            
            public void setReflectivity(final CommonFloatOrParamType value) {
                this.reflectivity = value;
            }
            
            public CommonTransparentType getTransparent() {
                return this.transparent;
            }
            
            public void setTransparent(final CommonTransparentType value) {
                this.transparent = value;
            }
            
            public CommonFloatOrParamType getTransparency() {
                return this.transparency;
            }
            
            public void setTransparency(final CommonFloatOrParamType value) {
                this.transparency = value;
            }
            
            public CommonFloatOrParamType getIndexOfRefraction() {
                return this.indexOfRefraction;
            }
            
            public void setIndexOfRefraction(final CommonFloatOrParamType value) {
                this.indexOfRefraction = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "emission", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction" })
        public static class Constant
        {
            protected CommonColorOrTextureType emission;
            protected CommonColorOrTextureType reflective;
            protected CommonFloatOrParamType reflectivity;
            protected CommonTransparentType transparent;
            protected CommonFloatOrParamType transparency;
            @XmlElement(name = "index_of_refraction")
            protected CommonFloatOrParamType indexOfRefraction;
            
            public CommonColorOrTextureType getEmission() {
                return this.emission;
            }
            
            public void setEmission(final CommonColorOrTextureType value) {
                this.emission = value;
            }
            
            public CommonColorOrTextureType getReflective() {
                return this.reflective;
            }
            
            public void setReflective(final CommonColorOrTextureType value) {
                this.reflective = value;
            }
            
            public CommonFloatOrParamType getReflectivity() {
                return this.reflectivity;
            }
            
            public void setReflectivity(final CommonFloatOrParamType value) {
                this.reflectivity = value;
            }
            
            public CommonTransparentType getTransparent() {
                return this.transparent;
            }
            
            public void setTransparent(final CommonTransparentType value) {
                this.transparent = value;
            }
            
            public CommonFloatOrParamType getTransparency() {
                return this.transparency;
            }
            
            public void setTransparency(final CommonFloatOrParamType value) {
                this.transparency = value;
            }
            
            public CommonFloatOrParamType getIndexOfRefraction() {
                return this.indexOfRefraction;
            }
            
            public void setIndexOfRefraction(final CommonFloatOrParamType value) {
                this.indexOfRefraction = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "emission", "ambient", "diffuse", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction" })
        public static class Lambert
        {
            protected CommonColorOrTextureType emission;
            protected CommonColorOrTextureType ambient;
            protected CommonColorOrTextureType diffuse;
            protected CommonColorOrTextureType reflective;
            protected CommonFloatOrParamType reflectivity;
            protected CommonTransparentType transparent;
            protected CommonFloatOrParamType transparency;
            @XmlElement(name = "index_of_refraction")
            protected CommonFloatOrParamType indexOfRefraction;
            
            public CommonColorOrTextureType getEmission() {
                return this.emission;
            }
            
            public void setEmission(final CommonColorOrTextureType value) {
                this.emission = value;
            }
            
            public CommonColorOrTextureType getAmbient() {
                return this.ambient;
            }
            
            public void setAmbient(final CommonColorOrTextureType value) {
                this.ambient = value;
            }
            
            public CommonColorOrTextureType getDiffuse() {
                return this.diffuse;
            }
            
            public void setDiffuse(final CommonColorOrTextureType value) {
                this.diffuse = value;
            }
            
            public CommonColorOrTextureType getReflective() {
                return this.reflective;
            }
            
            public void setReflective(final CommonColorOrTextureType value) {
                this.reflective = value;
            }
            
            public CommonFloatOrParamType getReflectivity() {
                return this.reflectivity;
            }
            
            public void setReflectivity(final CommonFloatOrParamType value) {
                this.reflectivity = value;
            }
            
            public CommonTransparentType getTransparent() {
                return this.transparent;
            }
            
            public void setTransparent(final CommonTransparentType value) {
                this.transparent = value;
            }
            
            public CommonFloatOrParamType getTransparency() {
                return this.transparency;
            }
            
            public void setTransparency(final CommonFloatOrParamType value) {
                this.transparency = value;
            }
            
            public CommonFloatOrParamType getIndexOfRefraction() {
                return this.indexOfRefraction;
            }
            
            public void setIndexOfRefraction(final CommonFloatOrParamType value) {
                this.indexOfRefraction = value;
            }
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "emission", "ambient", "diffuse", "specular", "shininess", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction" })
        public static class Phong
        {
            protected CommonColorOrTextureType emission;
            protected CommonColorOrTextureType ambient;
            protected CommonColorOrTextureType diffuse;
            protected CommonColorOrTextureType specular;
            protected CommonFloatOrParamType shininess;
            protected CommonColorOrTextureType reflective;
            protected CommonFloatOrParamType reflectivity;
            protected CommonTransparentType transparent;
            protected CommonFloatOrParamType transparency;
            @XmlElement(name = "index_of_refraction")
            protected CommonFloatOrParamType indexOfRefraction;
            
            public CommonColorOrTextureType getEmission() {
                return this.emission;
            }
            
            public void setEmission(final CommonColorOrTextureType value) {
                this.emission = value;
            }
            
            public CommonColorOrTextureType getAmbient() {
                return this.ambient;
            }
            
            public void setAmbient(final CommonColorOrTextureType value) {
                this.ambient = value;
            }
            
            public CommonColorOrTextureType getDiffuse() {
                return this.diffuse;
            }
            
            public void setDiffuse(final CommonColorOrTextureType value) {
                this.diffuse = value;
            }
            
            public CommonColorOrTextureType getSpecular() {
                return this.specular;
            }
            
            public void setSpecular(final CommonColorOrTextureType value) {
                this.specular = value;
            }
            
            public CommonFloatOrParamType getShininess() {
                return this.shininess;
            }
            
            public void setShininess(final CommonFloatOrParamType value) {
                this.shininess = value;
            }
            
            public CommonColorOrTextureType getReflective() {
                return this.reflective;
            }
            
            public void setReflective(final CommonColorOrTextureType value) {
                this.reflective = value;
            }
            
            public CommonFloatOrParamType getReflectivity() {
                return this.reflectivity;
            }
            
            public void setReflectivity(final CommonFloatOrParamType value) {
                this.reflectivity = value;
            }
            
            public CommonTransparentType getTransparent() {
                return this.transparent;
            }
            
            public void setTransparent(final CommonTransparentType value) {
                this.transparent = value;
            }
            
            public CommonFloatOrParamType getTransparency() {
                return this.transparency;
            }
            
            public void setTransparency(final CommonFloatOrParamType value) {
                this.transparency = value;
            }
            
            public CommonFloatOrParamType getIndexOfRefraction() {
                return this.indexOfRefraction;
            }
            
            public void setIndexOfRefraction(final CommonFloatOrParamType value) {
                this.indexOfRefraction = value;
            }
        }
    }
}
