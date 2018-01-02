// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sources", "controlVertices", "extras" })
@XmlRootElement(name = "spline")
public class Spline
{
    @XmlElement(name = "source", required = true)
    protected List<Source> sources;
    @XmlElement(name = "control_vertices", required = true)
    protected ControlVertices controlVertices;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    protected Boolean closed;
    
    public List<Source> getSources() {
        if (this.sources == null) {
            this.sources = new ArrayList<Source>();
        }
        return this.sources;
    }
    
    public ControlVertices getControlVertices() {
        return this.controlVertices;
    }
    
    public void setControlVertices(final ControlVertices value) {
        this.controlVertices = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public boolean isClosed() {
        return this.closed != null && this.closed;
    }
    
    public void setClosed(final Boolean value) {
        this.closed = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "inputs", "extras" })
    public static class ControlVertices
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
}
