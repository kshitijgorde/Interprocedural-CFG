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
@XmlType(name = "", propOrder = { "asset", "instanceAnimations", "extras" })
@XmlRootElement(name = "animation_clip")
public class AnimationClip
{
    protected Asset asset;
    @XmlElement(name = "instance_animation", required = true)
    protected List<InstanceWithExtra> instanceAnimations;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    protected Double end;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute
    protected Double start;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<InstanceWithExtra> getInstanceAnimations() {
        if (this.instanceAnimations == null) {
            this.instanceAnimations = new ArrayList<InstanceWithExtra>();
        }
        return this.instanceAnimations;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public Double getEnd() {
        return this.end;
    }
    
    public void setEnd(final Double value) {
        this.end = value;
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
    
    public double getStart() {
        if (this.start == null) {
            return 0.0;
        }
        return this.start;
    }
    
    public void setStart(final Double value) {
        this.start = value;
    }
}
