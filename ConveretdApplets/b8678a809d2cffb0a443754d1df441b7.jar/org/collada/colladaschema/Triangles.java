// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "inputs", "p", "extras" })
@XmlRootElement(name = "triangles")
public class Triangles
{
    @XmlElement(name = "input")
    protected List<InputLocalOffset> inputs;
    @XmlList
    protected List<BigInteger> p;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
    protected BigInteger count;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String material;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public List<InputLocalOffset> getInputs() {
        if (this.inputs == null) {
            this.inputs = new ArrayList<InputLocalOffset>();
        }
        return this.inputs;
    }
    
    public List<BigInteger> getP() {
        if (this.p == null) {
            this.p = new ArrayList<BigInteger>();
        }
        return this.p;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public BigInteger getCount() {
        return this.count;
    }
    
    public void setCount(final BigInteger value) {
        this.count = value;
    }
    
    public String getMaterial() {
        return this.material;
    }
    
    public void setMaterial(final String value) {
        this.material = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
