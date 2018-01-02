// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sources", "vertices", "tristripsAndLinesAndPolylists", "extras" })
@XmlRootElement(name = "convex_mesh")
public class ConvexMesh
{
    @XmlElement(name = "source")
    protected List<Source> sources;
    protected Vertices vertices;
    @XmlElements({ @XmlElement(name = "polygons", type = Polygons.class), @XmlElement(name = "tristrips", type = Tristrips.class), @XmlElement(name = "triangles", type = Triangles.class), @XmlElement(name = "linestrips", type = Linestrips.class), @XmlElement(name = "polylist", type = Polylist.class), @XmlElement(name = "lines", type = Lines.class), @XmlElement(name = "trifans", type = Trifans.class) })
    protected List<Object> tristripsAndLinesAndPolylists;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(name = "convex_hull_of")
    protected String convexHullOf;
    
    public List<Source> getSources() {
        if (this.sources == null) {
            this.sources = new ArrayList<Source>();
        }
        return this.sources;
    }
    
    public Vertices getVertices() {
        return this.vertices;
    }
    
    public void setVertices(final Vertices value) {
        this.vertices = value;
    }
    
    public List<Object> getTristripsAndLinesAndPolylists() {
        if (this.tristripsAndLinesAndPolylists == null) {
            this.tristripsAndLinesAndPolylists = new ArrayList<Object>();
        }
        return this.tristripsAndLinesAndPolylists;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getConvexHullOf() {
        return this.convexHullOf;
    }
    
    public void setConvexHullOf(final String value) {
        this.convexHullOf = value;
    }
}
