// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_cleardepth_common", propOrder = { "value" })
public class FxCleardepthCommon
{
    @XmlValue
    protected double value;
    @XmlAttribute
    protected BigInteger index;
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
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
}
