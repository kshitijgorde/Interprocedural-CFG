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
@XmlRootElement(name = "float_array")
public class FloatArray
{
    @XmlValue
    protected List<Double> values;
    @XmlAttribute(required = true)
    protected BigInteger count;
    @XmlAttribute
    protected Short digits;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    protected Short magnitude;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public List<Double> getValues() {
        if (this.values == null) {
            this.values = new ArrayList<Double>();
        }
        return this.values;
    }
    
    public BigInteger getCount() {
        return this.count;
    }
    
    public void setCount(final BigInteger value) {
        this.count = value;
    }
    
    public short getDigits() {
        if (this.digits == null) {
            return 6;
        }
        return this.digits;
    }
    
    public void setDigits(final Short value) {
        this.digits = value;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public short getMagnitude() {
        if (this.magnitude == null) {
            return 38;
        }
        return this.magnitude;
    }
    
    public void setMagnitude(final Short value) {
        this.magnitude = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
