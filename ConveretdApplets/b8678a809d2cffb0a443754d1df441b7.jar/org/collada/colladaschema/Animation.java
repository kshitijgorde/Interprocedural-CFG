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
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "sources", "samplers", "channels", "animations", "extras" })
@XmlRootElement(name = "animation")
public class Animation
{
    protected Asset asset;
    @XmlElement(name = "source")
    protected List<Source> sources;
    @XmlElement(name = "sampler")
    protected List<Sampler> samplers;
    @XmlElement(name = "channel")
    protected List<Channel> channels;
    @XmlElement(name = "animation")
    protected List<Animation> animations;
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
    
    public List<Source> getSources() {
        if (this.sources == null) {
            this.sources = new ArrayList<Source>();
        }
        return this.sources;
    }
    
    public List<Sampler> getSamplers() {
        if (this.samplers == null) {
            this.samplers = new ArrayList<Sampler>();
        }
        return this.samplers;
    }
    
    public List<Channel> getChannels() {
        if (this.channels == null) {
            this.channels = new ArrayList<Channel>();
        }
        return this.channels;
    }
    
    public List<Animation> getAnimations() {
        if (this.animations == null) {
            this.animations = new ArrayList<Animation>();
        }
        return this.animations;
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
