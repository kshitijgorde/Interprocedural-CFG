// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texture_unit", propOrder = { "surface", "samplerState", "texcoord", "extras" })
public class GlesTextureUnit
{
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String surface;
    @XmlElement(name = "sampler_state")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String samplerState;
    protected Texcoord texcoord;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
    public String getSurface() {
        return this.surface;
    }
    
    public void setSurface(final String value) {
        this.surface = value;
    }
    
    public String getSamplerState() {
        return this.samplerState;
    }
    
    public void setSamplerState(final String value) {
        this.samplerState = value;
    }
    
    public Texcoord getTexcoord() {
        return this.texcoord;
    }
    
    public void setTexcoord(final Texcoord value) {
        this.texcoord = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Texcoord
    {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String semantic;
        
        public String getSemantic() {
            return this.semantic;
        }
        
        public void setSemantic(final String value) {
            this.semantic = value;
        }
    }
}
