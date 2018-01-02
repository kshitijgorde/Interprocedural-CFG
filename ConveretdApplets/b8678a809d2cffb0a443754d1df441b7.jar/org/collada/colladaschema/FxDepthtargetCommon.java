// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_depthtarget_common", propOrder = { "value" })
public class FxDepthtargetCommon
{
    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String value;
    @XmlAttribute
    protected FxSurfaceFaceEnum face;
    @XmlAttribute
    protected BigInteger index;
    @XmlAttribute
    protected BigInteger mip;
    @XmlAttribute
    protected BigInteger slice;
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public FxSurfaceFaceEnum getFace() {
        if (this.face == null) {
            return FxSurfaceFaceEnum.POSITIVE_X;
        }
        return this.face;
    }
    
    public void setFace(final FxSurfaceFaceEnum value) {
        this.face = value;
    }
    
    public BigInteger getIndex() {
        if (this.index == null) {
            return new BigInteger("0");
        }
        return this.index;
    }
    
    public void setIndex(final BigInteger value) {
        this.index = value;
    }
    
    public BigInteger getMip() {
        if (this.mip == null) {
            return new BigInteger("0");
        }
        return this.mip;
    }
    
    public void setMip(final BigInteger value) {
        this.mip = value;
    }
    
    public BigInteger getSlice() {
        if (this.slice == null) {
            return new BigInteger("0");
        }
        return this.slice;
    }
    
    public void setSlice(final BigInteger value) {
        this.slice = value;
    }
}
