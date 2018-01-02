// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "inputs", "ps", "extras" })
@XmlRootElement(name = "tristrips")
public class Tristrips
{
    @XmlElement(name = "input")
    protected List<InputLocalOffset> inputs;
    @XmlElementRef(name = "p", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class)
    protected List<JAXBElement<List<BigInteger>>> ps;
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
    
    public List<JAXBElement<List<BigInteger>>> getPS() {
        if (this.ps == null) {
            this.ps = new ArrayList<JAXBElement<List<BigInteger>>>();
        }
        return this.ps;
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
