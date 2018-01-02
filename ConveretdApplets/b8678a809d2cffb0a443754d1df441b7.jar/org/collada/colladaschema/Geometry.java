// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "mesh", "spline", "convexMesh", "extras" })
@XmlRootElement(name = "geometry")
public class Geometry
{
    protected Asset asset;
    protected Mesh mesh;
    protected Spline spline;
    @XmlElement(name = "convex_mesh")
    protected ConvexMesh convexMesh;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public Mesh getMesh() {
        return this.mesh;
    }
    
    public void setMesh(final Mesh value) {
        this.mesh = value;
    }
    
    public Spline getSpline() {
        return this.spline;
    }
    
    public void setSpline(final Spline value) {
        this.spline = value;
    }
    
    public ConvexMesh getConvexMesh() {
        return this.convexMesh;
    }
    
    public void setConvexMesh(final ConvexMesh value) {
        this.convexMesh = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
