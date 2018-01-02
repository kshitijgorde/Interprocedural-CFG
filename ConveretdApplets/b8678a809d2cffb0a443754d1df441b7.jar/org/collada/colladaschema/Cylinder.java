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
@XmlType(name = "", propOrder = { "height", "radius", "extras" })
@XmlRootElement(name = "cylinder")
public class Cylinder
{
    protected double height;
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> radius;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public double getHeight() {
        return this.height;
    }
    
    public void setHeight(final double value) {
        this.height = value;
    }
    
    public List<Double> getRadius() {
        if (this.radius == null) {
            this.radius = new ArrayList<Double>();
        }
        return this.radius;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
