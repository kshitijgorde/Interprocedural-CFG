// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "nodes", "evaluateScenes", "extras" })
@XmlRootElement(name = "visual_scene")
public class VisualScene
{
    protected Asset asset;
    @XmlElement(name = "node", required = true)
    protected List<Node> nodes;
    @XmlElement(name = "evaluate_scene")
    protected List<EvaluateScene> evaluateScenes;
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
    
    public List<Node> getNodes() {
        if (this.nodes == null) {
            this.nodes = new ArrayList<Node>();
        }
        return this.nodes;
    }
    
    public List<EvaluateScene> getEvaluateScenes() {
        if (this.evaluateScenes == null) {
            this.evaluateScenes = new ArrayList<EvaluateScene>();
        }
        return this.evaluateScenes;
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
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "renders" })
    public static class EvaluateScene
    {
        @XmlElement(name = "render", required = true)
        protected List<Render> renders;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String name;
        
        public List<Render> getRenders() {
            if (this.renders == null) {
                this.renders = new ArrayList<Render>();
            }
            return this.renders;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setName(final String value) {
            this.name = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "layers", "instanceEffect" })
        public static class Render
        {
            @XmlElementRef(name = "layer", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class)
            protected List<JAXBElement<String>> layers;
            @XmlElement(name = "instance_effect")
            protected InstanceEffect instanceEffect;
            @XmlAttribute(name = "camera_node", required = true)
            protected String cameraNode;
            
            public List<JAXBElement<String>> getLayers() {
                if (this.layers == null) {
                    this.layers = new ArrayList<JAXBElement<String>>();
                }
                return this.layers;
            }
            
            public InstanceEffect getInstanceEffect() {
                return this.instanceEffect;
            }
            
            public void setInstanceEffect(final InstanceEffect value) {
                this.instanceEffect = value;
            }
            
            public String getCameraNode() {
                return this.cameraNode;
            }
            
            public void setCameraNode(final String value) {
                this.cameraNode = value;
            }
        }
    }
}
