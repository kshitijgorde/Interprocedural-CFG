// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.math.BigInteger;
import java.util.ArrayList;
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
@XmlType(name = "", propOrder = { "binds", "bindVertexInputs", "extras" })
@XmlRootElement(name = "instance_material")
public class InstanceMaterial
{
    @XmlElement(name = "bind")
    protected List<Bind> binds;
    @XmlElement(name = "bind_vertex_input")
    protected List<BindVertexInput> bindVertexInputs;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String symbol;
    @XmlAttribute(required = true)
    protected String target;
    
    public List<Bind> getBinds() {
        if (this.binds == null) {
            this.binds = new ArrayList<Bind>();
        }
        return this.binds;
    }
    
    public List<BindVertexInput> getBindVertexInputs() {
        if (this.bindVertexInputs == null) {
            this.bindVertexInputs = new ArrayList<BindVertexInput>();
        }
        return this.bindVertexInputs;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
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
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(final String value) {
        this.symbol = value;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(final String value) {
        this.target = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Bind
    {
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String semantic;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String target;
        
        public String getSemantic() {
            return this.semantic;
        }
        
        public void setSemantic(final String value) {
            this.semantic = value;
        }
        
        public String getTarget() {
            return this.target;
        }
        
        public void setTarget(final String value) {
            this.target = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BindVertexInput
    {
        @XmlAttribute(name = "input_semantic", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String inputSemantic;
        @XmlAttribute(name = "input_set")
        protected BigInteger inputSet;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String semantic;
        
        public String getInputSemantic() {
            return this.inputSemantic;
        }
        
        public void setInputSemantic(final String value) {
            this.inputSemantic = value;
        }
        
        public BigInteger getInputSet() {
            return this.inputSet;
        }
        
        public void setInputSet(final BigInteger value) {
            this.inputSet = value;
        }
        
        public String getSemantic() {
            return this.semantic;
        }
        
        public void setSemantic(final String value) {
            this.semantic = value;
        }
    }
}
