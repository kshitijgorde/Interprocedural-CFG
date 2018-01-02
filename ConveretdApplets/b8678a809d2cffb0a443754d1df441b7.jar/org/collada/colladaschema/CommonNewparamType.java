// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "common_newparam_type", propOrder = { "semantic", "float4", "float2", "sampler2D", "float3", "_float", "surface" })
public class CommonNewparamType
{
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float4;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float2;
    protected FxSampler2DCommon sampler2D;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float3;
    @XmlElement(name = "float")
    protected Double _float;
    protected FxSurfaceCommon surface;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
    public String getSemantic() {
        return this.semantic;
    }
    
    public void setSemantic(final String value) {
        this.semantic = value;
    }
    
    public List<Double> getFloat4() {
        if (this.float4 == null) {
            this.float4 = new ArrayList<Double>();
        }
        return this.float4;
    }
    
    public List<Double> getFloat2() {
        if (this.float2 == null) {
            this.float2 = new ArrayList<Double>();
        }
        return this.float2;
    }
    
    public FxSampler2DCommon getSampler2D() {
        return this.sampler2D;
    }
    
    public void setSampler2D(final FxSampler2DCommon value) {
        this.sampler2D = value;
    }
    
    public List<Double> getFloat3() {
        if (this.float3 == null) {
            this.float3 = new ArrayList<Double>();
        }
        return this.float3;
    }
    
    public Double getFloat() {
        return this._float;
    }
    
    public void setFloat(final Double value) {
        this._float = value;
    }
    
    public FxSurfaceCommon getSurface() {
        return this.surface;
    }
    
    public void setSurface(final FxSurfaceCommon value) {
        this.surface = value;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
}
