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
@XmlType(name = "", propOrder = { "halfExtents", "extras" })
@XmlRootElement(name = "box")
public class Box
{
    @XmlList
    @XmlElement(name = "half_extents", type = Double.class)
    protected List<Double> halfExtents;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public List<Double> getHalfExtents() {
        if (this.halfExtents == null) {
            this.halfExtents = new ArrayList<Double>();
        }
        return this.halfExtents;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
