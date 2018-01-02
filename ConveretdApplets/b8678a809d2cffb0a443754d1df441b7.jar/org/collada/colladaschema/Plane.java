// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "equation", "extras" })
@XmlRootElement(name = "plane")
public class Plane
{
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> equation;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public List<Double> getEquation() {
        if (this.equation == null) {
            this.equation = new ArrayList<Double>();
        }
        return this.equation;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
