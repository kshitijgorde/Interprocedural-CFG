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
@XmlType(name = "", propOrder = { "height", "radius1", "radius2", "extras" })
@XmlRootElement(name = "tapered_cylinder")
public class TaperedCylinder
{
    protected double height;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> radius1;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> radius2;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public double getHeight() {
        return this.height;
    }
    
    public void setHeight(final double value) {
        this.height = value;
    }
    
    public List<Double> getRadius1() {
        if (this.radius1 == null) {
            this.radius1 = new ArrayList<Double>();
        }
        return this.radius1;
    }
    
    public List<Double> getRadius2() {
        if (this.radius2 == null) {
            this.radius2 = new ArrayList<Double>();
        }
        return this.radius2;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
