// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "params" })
@XmlRootElement(name = "accessor")
public class Accessor
{
    @XmlElement(name = "param")
    protected List<Param> params;
    @XmlAttribute(required = true)
    protected BigInteger count;
    @XmlAttribute
    protected BigInteger offset;
    @XmlAttribute
    protected String source;
    @XmlAttribute
    protected BigInteger stride;
    
    public List<Param> getParams() {
        if (this.params == null) {
            this.params = new ArrayList<Param>();
        }
        return this.params;
    }
    
    public BigInteger getCount() {
        return this.count;
    }
    
    public void setCount(final BigInteger value) {
        this.count = value;
    }
    
    public BigInteger getOffset() {
        if (this.offset == null) {
            return new BigInteger("0");
        }
        return this.offset;
    }
    
    public void setOffset(final BigInteger value) {
        this.offset = value;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
    
    public BigInteger getStride() {
        if (this.stride == null) {
            return new BigInteger("1");
        }
        return this.stride;
    }
    
    public void setStride(final BigInteger value) {
        this.stride = value;
    }
}
