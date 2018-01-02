// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_clearcolor_common", propOrder = { "values" })
public class FxClearcolorCommon
{
    @XmlValue
    protected List<Double> values;
    @XmlAttribute
    protected BigInteger index;
    
    public List<Double> getValues() {
        if (this.values == null) {
            this.values = new ArrayList<Double>();
        }
        return this.values;
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
