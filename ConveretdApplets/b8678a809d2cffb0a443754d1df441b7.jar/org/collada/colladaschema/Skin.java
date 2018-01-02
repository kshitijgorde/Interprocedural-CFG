// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "bindShapeMatrix", "sources", "joints", "vertexWeights", "extras" })
@XmlRootElement(name = "skin")
public class Skin
{
    @XmlList
    @XmlElement(name = "bind_shape_matrix", type = Double.class)
    protected List<Double> bindShapeMatrix;
    @XmlElement(name = "source", required = true)
    protected List<Source> sources;
    @XmlElement(required = true)
    protected Joints joints;
    @XmlElement(name = "vertex_weights", required = true)
    protected VertexWeights vertexWeights;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
    protected String source;
    
    public List<Double> getBindShapeMatrix() {
        if (this.bindShapeMatrix == null) {
            this.bindShapeMatrix = new ArrayList<Double>();
        }
        return this.bindShapeMatrix;
    }
    
    public List<Source> getSources() {
        if (this.sources == null) {
            this.sources = new ArrayList<Source>();
        }
        return this.sources;
    }
    
    public Joints getJoints() {
        return this.joints;
    }
    
    public void setJoints(final Joints value) {
        this.joints = value;
    }
    
    public VertexWeights getVertexWeights() {
        return this.vertexWeights;
    }
    
    public void setVertexWeights(final VertexWeights value) {
        this.vertexWeights = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "inputs", "extras" })
    public static class Joints
    {
        @XmlElement(name = "input", required = true)
        protected List<InputLocal> inputs;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        
        public List<InputLocal> getInputs() {
            if (this.inputs == null) {
                this.inputs = new ArrayList<InputLocal>();
            }
            return this.inputs;
        }
        
        public List<Extra> getExtras() {
            if (this.extras == null) {
                this.extras = new ArrayList<Extra>();
            }
            return this.extras;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "inputs", "vcount", "v", "extras" })
    public static class VertexWeights
    {
        @XmlElement(name = "input", required = true)
        protected List<InputLocalOffset> inputs;
        @XmlList
        protected List<BigInteger> vcount;
        @XmlList
        @XmlElement(type = Long.class)
        protected List<Long> v;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        @XmlAttribute(required = true)
        protected BigInteger count;
        
        public List<InputLocalOffset> getInputs() {
            if (this.inputs == null) {
                this.inputs = new ArrayList<InputLocalOffset>();
            }
            return this.inputs;
        }
        
        public List<BigInteger> getVcount() {
            if (this.vcount == null) {
                this.vcount = new ArrayList<BigInteger>();
            }
            return this.vcount;
        }
        
        public List<Long> getV() {
            if (this.v == null) {
                this.v = new ArrayList<Long>();
            }
            return this.v;
        }
        
        public List<Extra> getExtras() {
            if (this.extras == null) {
                this.extras = new ArrayList<Extra>();
            }
            return this.extras;
        }
        
        public BigInteger getCount() {
            return this.count;
        }
        
        public void setCount(final BigInteger value) {
            this.count = value;
        }
    }
}
