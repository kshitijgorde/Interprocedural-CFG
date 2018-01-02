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
@XmlType(name = "", propOrder = { "sources", "targets", "extras" })
@XmlRootElement(name = "morph")
public class Morph
{
    @XmlElement(name = "source", required = true)
    protected List<Source> sources;
    @XmlElement(required = true)
    protected Targets targets;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    protected MorphMethodType method;
    @XmlAttribute(required = true)
    protected String source;
    
    public List<Source> getSources() {
        if (this.sources == null) {
            this.sources = new ArrayList<Source>();
        }
        return this.sources;
    }
    
    public Targets getTargets() {
        return this.targets;
    }
    
    public void setTargets(final Targets value) {
        this.targets = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public MorphMethodType getMethod() {
        if (this.method == null) {
            return MorphMethodType.NORMALIZED;
        }
        return this.method;
    }
    
    public void setMethod(final MorphMethodType value) {
        this.method = value;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "inputs", "extras" })
    public static class Targets
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
