// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sources", "vertices", "trianglesAndLinestripsAndPolygons", "extras" })
@XmlRootElement(name = "mesh")
public class Mesh
{
    @XmlElement(name = "source", required = true)
    protected List<Source> sources;
    @XmlElement(required = true)
    protected Vertices vertices;
    @XmlElements({ @XmlElement(name = "linestrips", type = Linestrips.class), @XmlElement(name = "trifans", type = Trifans.class), @XmlElement(name = "triangles", type = Triangles.class), @XmlElement(name = "tristrips", type = Tristrips.class), @XmlElement(name = "polylist", type = Polylist.class), @XmlElement(name = "polygons", type = Polygons.class), @XmlElement(name = "lines", type = Lines.class) })
    protected List<Object> trianglesAndLinestripsAndPolygons;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
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
    
    public List<Object> getTrianglesAndLinestripsAndPolygons() {
        if (this.trianglesAndLinestripsAndPolygons == null) {
            this.trianglesAndLinestripsAndPolygons = new ArrayList<Object>();
        }
        return this.trianglesAndLinestripsAndPolygons;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
