// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_newparam_common", propOrder = { "annotates", "semantic", "modifier", "sampler1D", "_float", "float1X4", "float2X4", "float3X1", "int4", "float4", "float4X1", "float2X2", "int3", "float3X3", "float2", "float4X2", "float3", "bool", "sampler3D", "float4X3", "bool3", "float4X4", "samplerCUBE", "samplerRECT", "samplerDEPTH", "sampler2D", "float1X2", "float2X3", "_enum", "bool4", "bool2", "float3X4", "float3X2", "float1X3", "_int", "int2", "surface", "float2X1", "float1X1" })
public class FxNewparamCommon
{
    @XmlElement(name = "annotate")
    protected List<FxAnnotateCommon> annotates;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    protected FxModifierEnumCommon modifier;
    protected FxSampler1DCommon sampler1D;
    @XmlElement(name = "float")
    protected Double _float;
    @XmlList
    @XmlElement(name = "float1x4", type = Double.class)
    protected List<Double> float1X4;
    @XmlList
    @XmlElement(name = "float2x4", type = Double.class)
    protected List<Double> float2X4;
    @XmlList
    @XmlElement(name = "float3x1", type = Double.class)
    protected List<Double> float3X1;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int4;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float4;
    @XmlList
    @XmlElement(name = "float4x1", type = Double.class)
    protected List<Double> float4X1;
    @XmlList
    @XmlElement(name = "float2x2", type = Double.class)
    protected List<Double> float2X2;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int3;
    @XmlList
    @XmlElement(name = "float3x3", type = Double.class)
    protected List<Double> float3X3;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float2;
    @XmlList
    @XmlElement(name = "float4x2", type = Double.class)
    protected List<Double> float4X2;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float3;
    protected Boolean bool;
    protected FxSampler3DCommon sampler3D;
    @XmlList
    @XmlElement(name = "float4x3", type = Double.class)
    protected List<Double> float4X3;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool3;
    @XmlList
    @XmlElement(name = "float4x4", type = Double.class)
    protected List<Double> float4X4;
    protected FxSamplerCUBECommon samplerCUBE;
    protected FxSamplerRECTCommon samplerRECT;
    protected FxSamplerDEPTHCommon samplerDEPTH;
    protected FxSampler2DCommon sampler2D;
    @XmlList
    @XmlElement(name = "float1x2", type = Double.class)
    protected List<Double> float1X2;
    @XmlList
    @XmlElement(name = "float2x3", type = Double.class)
    protected List<Double> float2X3;
    @XmlElement(name = "enum")
    protected String _enum;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool4;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool2;
    @XmlList
    @XmlElement(name = "float3x4", type = Double.class)
    protected List<Double> float3X4;
    @XmlList
    @XmlElement(name = "float3x2", type = Double.class)
    protected List<Double> float3X2;
    @XmlList
    @XmlElement(name = "float1x3", type = Double.class)
    protected List<Double> float1X3;
    @XmlElement(name = "int")
    protected Long _int;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int2;
    protected FxSurfaceCommon surface;
    @XmlList
    @XmlElement(name = "float2x1", type = Double.class)
    protected List<Double> float2X1;
    @XmlElement(name = "float1x1")
    protected Double float1X1;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
    public List<FxAnnotateCommon> getAnnotates() {
        if (this.annotates == null) {
            this.annotates = new ArrayList<FxAnnotateCommon>();
        }
        return this.annotates;
    }
    
    public String getSemantic() {
        return this.semantic;
    }
    
    public void setSemantic(final String value) {
        this.semantic = value;
    }
    
    public FxModifierEnumCommon getModifier() {
        return this.modifier;
    }
    
    public void setModifier(final FxModifierEnumCommon value) {
        this.modifier = value;
    }
    
    public FxSampler1DCommon getSampler1D() {
        return this.sampler1D;
    }
    
    public void setSampler1D(final FxSampler1DCommon value) {
        this.sampler1D = value;
    }
    
    public Double getFloat() {
        return this._float;
    }
    
    public void setFloat(final Double value) {
        this._float = value;
    }
    
    public List<Double> getFloat1X4() {
        if (this.float1X4 == null) {
            this.float1X4 = new ArrayList<Double>();
        }
        return this.float1X4;
    }
    
    public List<Double> getFloat2X4() {
        if (this.float2X4 == null) {
            this.float2X4 = new ArrayList<Double>();
        }
        return this.float2X4;
    }
    
    public List<Double> getFloat3X1() {
        if (this.float3X1 == null) {
            this.float3X1 = new ArrayList<Double>();
        }
        return this.float3X1;
    }
    
    public List<Long> getInt4() {
        if (this.int4 == null) {
            this.int4 = new ArrayList<Long>();
        }
        return this.int4;
    }
    
    public List<Double> getFloat4() {
        if (this.float4 == null) {
            this.float4 = new ArrayList<Double>();
        }
        return this.float4;
    }
    
    public List<Double> getFloat4X1() {
        if (this.float4X1 == null) {
            this.float4X1 = new ArrayList<Double>();
        }
        return this.float4X1;
    }
    
    public List<Double> getFloat2X2() {
        if (this.float2X2 == null) {
            this.float2X2 = new ArrayList<Double>();
        }
        return this.float2X2;
    }
    
    public List<Long> getInt3() {
        if (this.int3 == null) {
            this.int3 = new ArrayList<Long>();
        }
        return this.int3;
    }
    
    public List<Double> getFloat3X3() {
        if (this.float3X3 == null) {
            this.float3X3 = new ArrayList<Double>();
        }
        return this.float3X3;
    }
    
    public List<Double> getFloat2() {
        if (this.float2 == null) {
            this.float2 = new ArrayList<Double>();
        }
        return this.float2;
    }
    
    public List<Double> getFloat4X2() {
        if (this.float4X2 == null) {
            this.float4X2 = new ArrayList<Double>();
        }
        return this.float4X2;
    }
    
    public List<Double> getFloat3() {
        if (this.float3 == null) {
            this.float3 = new ArrayList<Double>();
        }
        return this.float3;
    }
    
    public Boolean isBool() {
        return this.bool;
    }
    
    public void setBool(final Boolean value) {
        this.bool = value;
    }
    
    public FxSampler3DCommon getSampler3D() {
        return this.sampler3D;
    }
    
    public void setSampler3D(final FxSampler3DCommon value) {
        this.sampler3D = value;
    }
    
    public List<Double> getFloat4X3() {
        if (this.float4X3 == null) {
            this.float4X3 = new ArrayList<Double>();
        }
        return this.float4X3;
    }
    
    public List<Boolean> getBool3() {
        if (this.bool3 == null) {
            this.bool3 = new ArrayList<Boolean>();
        }
        return this.bool3;
    }
    
    public List<Double> getFloat4X4() {
        if (this.float4X4 == null) {
            this.float4X4 = new ArrayList<Double>();
        }
        return this.float4X4;
    }
    
    public FxSamplerCUBECommon getSamplerCUBE() {
        return this.samplerCUBE;
    }
    
    public void setSamplerCUBE(final FxSamplerCUBECommon value) {
        this.samplerCUBE = value;
    }
    
    public FxSamplerRECTCommon getSamplerRECT() {
        return this.samplerRECT;
    }
    
    public void setSamplerRECT(final FxSamplerRECTCommon value) {
        this.samplerRECT = value;
    }
    
    public FxSamplerDEPTHCommon getSamplerDEPTH() {
        return this.samplerDEPTH;
    }
    
    public void setSamplerDEPTH(final FxSamplerDEPTHCommon value) {
        this.samplerDEPTH = value;
    }
    
    public FxSampler2DCommon getSampler2D() {
        return this.sampler2D;
    }
    
    public void setSampler2D(final FxSampler2DCommon value) {
        this.sampler2D = value;
    }
    
    public List<Double> getFloat1X2() {
        if (this.float1X2 == null) {
            this.float1X2 = new ArrayList<Double>();
        }
        return this.float1X2;
    }
    
    public List<Double> getFloat2X3() {
        if (this.float2X3 == null) {
            this.float2X3 = new ArrayList<Double>();
        }
        return this.float2X3;
    }
    
    public String getEnum() {
        return this._enum;
    }
    
    public void setEnum(final String value) {
        this._enum = value;
    }
    
    public List<Boolean> getBool4() {
        if (this.bool4 == null) {
            this.bool4 = new ArrayList<Boolean>();
        }
        return this.bool4;
    }
    
    public List<Boolean> getBool2() {
        if (this.bool2 == null) {
            this.bool2 = new ArrayList<Boolean>();
        }
        return this.bool2;
    }
    
    public List<Double> getFloat3X4() {
        if (this.float3X4 == null) {
            this.float3X4 = new ArrayList<Double>();
        }
        return this.float3X4;
    }
    
    public List<Double> getFloat3X2() {
        if (this.float3X2 == null) {
            this.float3X2 = new ArrayList<Double>();
        }
        return this.float3X2;
    }
    
    public List<Double> getFloat1X3() {
        if (this.float1X3 == null) {
            this.float1X3 = new ArrayList<Double>();
        }
        return this.float1X3;
    }
    
    public Long getInt() {
        return this._int;
    }
    
    public void setInt(final Long value) {
        this._int = value;
    }
    
    public List<Long> getInt2() {
        if (this.int2 == null) {
            this.int2 = new ArrayList<Long>();
        }
        return this.int2;
    }
    
    public FxSurfaceCommon getSurface() {
        return this.surface;
    }
    
    public void setSurface(final FxSurfaceCommon value) {
        this.surface = value;
    }
    
    public List<Double> getFloat2X1() {
        if (this.float2X1 == null) {
            this.float2X1 = new ArrayList<Double>();
        }
        return this.float2X1;
    }
    
    public Double getFloat1X1() {
        return this.float1X1;
    }
    
    public void setFloat1X1(final Double value) {
        this.float1X1 = value;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
}