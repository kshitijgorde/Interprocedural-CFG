// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "values" })
@XmlRootElement(name = "int_array")
public class IntArray
{
    @XmlValue
    protected List<Long> values;
    @XmlAttribute(required = true)
    protected BigInteger count;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    protected BigInteger maxInclusive;
    @XmlAttribute
    protected BigInteger minInclusive;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public List<Long> getValues() {
        if (this.values == null) {
            this.values = new ArrayList<Long>();
        }
        return this.values;
    }
    
    public BigInteger getCount() {
        return this.count;
    }
    
    public void setCount(final BigInteger value) {
        this.count = value;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public BigInteger getMaxInclusive() {
        if (this.maxInclusive == null) {
            return new BigInteger("2147483647");
        }
        return this.maxInclusive;
    }
    
    public void setMaxInclusive(final BigInteger value) {
        this.maxInclusive = value;
    }
    
    public BigInteger getMinInclusive() {
        if (this.minInclusive == null) {
            return new BigInteger("-2147483648");
        }
        return this.minInclusive;
    }
    
    public void setMinInclusive(final BigInteger value) {
        this.minInclusive = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
