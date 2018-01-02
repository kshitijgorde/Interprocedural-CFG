// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "glsl_setparam_simple", propOrder = { "annotates", "sampler3D", "_float", "float4", "samplerDEPTH", "float3", "bool4", "surface", "int4", "bool2", "_int", "float4X4", "float2", "_enum", "samplerRECT", "bool3", "int3", "sampler2D", "bool", "float3X3", "int2", "sampler1D", "float2X2", "samplerCUBE" })
public class GlslSetparamSimple
{
    @XmlElement(name = "annotate")
    protected List<FxAnnotateCommon> annotates;
    protected GlSampler3D sampler3D;
    @XmlElement(name = "float")
    protected Float _float;
    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> float4;
    protected GlSamplerDEPTH samplerDEPTH;
    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> float3;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool4;
    protected GlslSurfaceType surface;
    @XmlList
    @XmlElement(type = Integer.class)
    protected List<Integer> int4;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool2;
    @XmlElement(name = "int")
    protected Integer _int;
    @XmlList
    @XmlElement(name = "float4x4", type = Float.class)
    protected List<Float> float4X4;
    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> float2;
    @XmlElement(name = "enum")
    protected String _enum;
    protected GlSamplerRECT samplerRECT;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool3;
    @XmlList
    @XmlElement(type = Integer.class)
    protected List<Integer> int3;
    protected GlSampler2D sampler2D;
    protected Boolean bool;
    @XmlList
    @XmlElement(name = "float3x3", type = Float.class)
    protected List<Float> float3X3;
    @XmlList
    @XmlElement(type = Integer.class)
    protected List<Integer> int2;
    protected GlSampler1D sampler1D;
    @XmlList
    @XmlElement(name = "float2x2", type = Float.class)
    protected List<Float> float2X2;
    protected GlSamplerCUBE samplerCUBE;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ref;
    
    public List<FxAnnotateCommon> getAnnotates() {
        if (this.annotates == null) {
            this.annotates = new ArrayList<FxAnnotateCommon>();
        }
        return this.annotates;
    }
    
    public GlSampler3D getSampler3D() {
        return this.sampler3D;
    }
    
    public void setSampler3D(final GlSampler3D value) {
        this.sampler3D = value;
    }
    
    public Float getFloat() {
        return this._float;
    }
    
    public void setFloat(final Float value) {
        this._float = value;
    }
    
    public List<Float> getFloat4() {
        if (this.float4 == null) {
            this.float4 = new ArrayList<Float>();
        }
        return this.float4;
    }
    
    public GlSamplerDEPTH getSamplerDEPTH() {
        return this.samplerDEPTH;
    }
    
    public void setSamplerDEPTH(final GlSamplerDEPTH value) {
        this.samplerDEPTH = value;
    }
    
    public List<Float> getFloat3() {
        if (this.float3 == null) {
            this.float3 = new ArrayList<Float>();
        }
        return this.float3;
    }
    
    public List<Boolean> getBool4() {
        if (this.bool4 == null) {
            this.bool4 = new ArrayList<Boolean>();
        }
        return this.bool4;
    }
    
    public GlslSurfaceType getSurface() {
        return this.surface;
    }
    
    public void setSurface(final GlslSurfaceType value) {
        this.surface = value;
    }
    
    public List<Integer> getInt4() {
        if (this.int4 == null) {
            this.int4 = new ArrayList<Integer>();
        }
        return this.int4;
    }
    
    public List<Boolean> getBool2() {
        if (this.bool2 == null) {
            this.bool2 = new ArrayList<Boolean>();
        }
        return this.bool2;
    }
    
    public Integer getInt() {
        return this._int;
    }
    
    public void setInt(final Integer value) {
        this._int = value;
    }
    
    public List<Float> getFloat4X4() {
        if (this.float4X4 == null) {
            this.float4X4 = new ArrayList<Float>();
        }
        return this.float4X4;
    }
    
    public List<Float> getFloat2() {
        if (this.float2 == null) {
            this.float2 = new ArrayList<Float>();
        }
        return this.float2;
    }
    
    public String getEnum() {
        return this._enum;
    }
    
    public void setEnum(final String value) {
        this._enum = value;
    }
    
    public GlSamplerRECT getSamplerRECT() {
        return this.samplerRECT;
    }
    
    public void setSamplerRECT(final GlSamplerRECT value) {
        this.samplerRECT = value;
    }
    
    public List<Boolean> getBool3() {
        if (this.bool3 == null) {
            this.bool3 = new ArrayList<Boolean>();
        }
        return this.bool3;
    }
    
    public List<Integer> getInt3() {
        if (this.int3 == null) {
            this.int3 = new ArrayList<Integer>();
        }
        return this.int3;
    }
    
    public GlSampler2D getSampler2D() {
        return this.sampler2D;
    }
    
    public void setSampler2D(final GlSampler2D value) {
        this.sampler2D = value;
    }
    
    public Boolean isBool() {
        return this.bool;
    }
    
    public void setBool(final Boolean value) {
        this.bool = value;
    }
    
    public List<Float> getFloat3X3() {
        if (this.float3X3 == null) {
            this.float3X3 = new ArrayList<Float>();
        }
        return this.float3X3;
    }
    
    public List<Integer> getInt2() {
        if (this.int2 == null) {
            this.int2 = new ArrayList<Integer>();
        }
        return this.int2;
    }
    
    public GlSampler1D getSampler1D() {
        return this.sampler1D;
    }
    
    public void setSampler1D(final GlSampler1D value) {
        this.sampler1D = value;
    }
    
    public List<Float> getFloat2X2() {
        if (this.float2X2 == null) {
            this.float2X2 = new ArrayList<Float>();
        }
        return this.float2X2;
    }
    
    public GlSamplerCUBE getSamplerCUBE() {
        return this.samplerCUBE;
    }
    
    public void setSamplerCUBE(final GlSamplerCUBE value) {
        this.samplerCUBE = value;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public void setRef(final String value) {
        this.ref = value;
    }
}
