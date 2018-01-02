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
@XmlType(name = "", propOrder = { "size" })
@XmlRootElement(name = "ellipsoid")
public class Ellipsoid
{
    @XmlList
    @XmlElement(type = Double.class)
    protected List<Double> size;
    
    public List<Double> getSize() {
        if (this.size == null) {
            this.size = new ArrayList<Double>();
        }
        return this.size;
    }
}
