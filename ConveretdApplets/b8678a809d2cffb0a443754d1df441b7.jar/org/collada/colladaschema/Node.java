// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "translatesAndMatrixesAndLookats", "instanceCameras", "instanceControllers", "instanceGeometries", "instanceLights", "instanceNodes", "nodes", "extras" })
@XmlRootElement(name = "node")
public class Node
{
    protected Asset asset;
    @XmlElementRefs({ @XmlElementRef(name = "rotate", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = Rotate.class), @XmlElementRef(name = "translate", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "skew", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = Skew.class), @XmlElementRef(name = "scale", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "matrix", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = Matrix.class), @XmlElementRef(name = "lookat", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = Lookat.class) })
    protected List<Object> translatesAndMatrixesAndLookats;
    @XmlElement(name = "instance_camera")
    protected List<InstanceWithExtra> instanceCameras;
    @XmlElement(name = "instance_controller")
    protected List<InstanceController> instanceControllers;
    @XmlElement(name = "instance_geometry")
    protected List<InstanceGeometry> instanceGeometries;
    @XmlElement(name = "instance_light")
    protected List<InstanceWithExtra> instanceLights;
    @XmlElement(name = "instance_node")
    protected List<InstanceWithExtra> instanceNodes;
    @XmlElement(name = "node")
    protected List<Node> nodes;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "layer")
    protected List<String> layers;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    @XmlAttribute
    protected NodeType type;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<Object> getTranslatesAndMatrixesAndLookats() {
        if (this.translatesAndMatrixesAndLookats == null) {
            this.translatesAndMatrixesAndLookats = new ArrayList<Object>();
        }
        return this.translatesAndMatrixesAndLookats;
    }
    
    public List<InstanceWithExtra> getInstanceCameras() {
        if (this.instanceCameras == null) {
            this.instanceCameras = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceCameras;
    }
    
    public List<InstanceController> getInstanceControllers() {
        if (this.instanceControllers == null) {
            this.instanceControllers = new ArrayList<InstanceController>();
        }
        return this.instanceControllers;
    }
    
    public List<InstanceGeometry> getInstanceGeometries() {
        if (this.instanceGeometries == null) {
            this.instanceGeometries = new ArrayList<InstanceGeometry>();
        }
        return this.instanceGeometries;
    }
    
    public List<InstanceWithExtra> getInstanceLights() {
        if (this.instanceLights == null) {
            this.instanceLights = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceLights;
    }
    
    public List<InstanceWithExtra> getInstanceNodes() {
        if (this.instanceNodes == null) {
            this.instanceNodes = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceNodes;
    }
    
    public List<Node> getNodes() {
        if (this.nodes == null) {
            this.nodes = new ArrayList<Node>();
        }
        return this.nodes;
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
    
    public List<String> getLayers() {
        if (this.layers == null) {
            this.layers = new ArrayList<String>();
        }
        return this.layers;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
    
    public NodeType getType() {
        if (this.type == null) {
            return NodeType.NODE;
        }
        return this.type;
    }
    
    public void setType(final NodeType value) {
        this.type = value;
    }
}
