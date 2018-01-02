// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_annotate_common", propOrder = { "float3X3", "_int", "float2", "string", "bool2", "float3", "_float", "bool4", "int4", "bool", "float2X2", "int3", "float4", "bool3", "int2", "float4X4" })
public class FxAnnotateCommon
{
    @XmlList
    @XmlElement(name = "float3x3", type = Double.class)
    protected List<Double> float3X3;
    @XmlElement(name = "int")
    protected Long _int;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float2;
    protected String string;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool2;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float3;
    @XmlElement(name = "float")
    protected Double _float;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool4;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int4;
    protected Boolean bool;
    @XmlList
    @XmlElement(name = "float2x2", type = Double.class)
    protected List<Double> float2X2;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int3;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> float4;
    @XmlList
    @XmlElement(type = Boolean.class)
    protected List<Boolean> bool3;
    @XmlList
    @XmlElement(type = Long.class)
    protected List<Long> int2;
    @XmlList
    @XmlElement(name = "float4x4", type = Double.class)
    protected List<Double> float4X4;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public List<Double> getFloat3X3() {
        if (this.float3X3 == null) {
            this.float3X3 = new ArrayList<Double>();
        }
        return this.float3X3;
    }
    
    public Long getInt() {
        return this._int;
    }
    
    public void setInt(final Long value) {
        this._int = value;
    }
    
    public List<Double> getFloat2() {
        if (this.float2 == null) {
            this.float2 = new ArrayList<Double>();
        }
        return this.float2;
    }
    
    public String getString() {
        return this.string;
    }
    
    public void setString(final String value) {
        this.string = value;
    }
    
    public List<Boolean> getBool2() {
        if (this.bool2 == null) {
            this.bool2 = new ArrayList<Boolean>();
        }
        return this.bool2;
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
    
    public List<Boolean> getBool4() {
        if (this.bool4 == null) {
            this.bool4 = new ArrayList<Boolean>();
        }
        return this.bool4;
    }
    
    public List<Long> getInt4() {
        if (this.int4 == null) {
            this.int4 = new ArrayList<Long>();
        }
        return this.int4;
    }
    
    public Boolean isBool() {
        return this.bool;
    }
    
    public void setBool(final Boolean value) {
        this.bool = value;
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
    
    public List<Double> getFloat4() {
        if (this.float4 == null) {
            this.float4 = new ArrayList<Double>();
        }
        return this.float4;
    }
    
    public List<Boolean> getBool3() {
        if (this.bool3 == null) {
            this.bool3 = new ArrayList<Boolean>();
        }
        return this.bool3;
    }
    
    public List<Long> getInt2() {
        if (this.int2 == null) {
            this.int2 = new ArrayList<Long>();
        }
        return this.int2;
    }
    
    public List<Double> getFloat4X4() {
        if (this.float4X4 == null) {
            this.float4X4 = new ArrayList<Double>();
        }
        return this.float4X4;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
