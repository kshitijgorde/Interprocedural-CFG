// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "annotates", "images", "newparams", "fxProfileAbstracts", "extras" })
@XmlRootElement(name = "effect")
public class Effect
{
    protected Asset asset;
    @XmlElement(name = "annotate")
    protected List<FxAnnotateCommon> annotates;
    @XmlElement(name = "image")
    protected List<Image> images;
    @XmlElement(name = "newparam")
    protected List<FxNewparamCommon> newparams;
    @XmlElementRef(name = "fx_profile_abstract", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class)
    protected List<JAXBElement<?>> fxProfileAbstracts;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
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
    
    public List<FxAnnotateCommon> getAnnotates() {
        if (this.annotates == null) {
            this.annotates = new ArrayList<FxAnnotateCommon>();
        }
        return this.annotates;
    }
    
    public List<Image> getImages() {
        if (this.images == null) {
            this.images = new ArrayList<Image>();
        }
        return this.images;
    }
    
    public List<FxNewparamCommon> getNewparams() {
        if (this.newparams == null) {
            this.newparams = new ArrayList<FxNewparamCommon>();
        }
        return this.newparams;
    }
    
    public List<JAXBElement<?>> getFxProfileAbstracts() {
        if (this.fxProfileAbstracts == null) {
            this.fxProfileAbstracts = new ArrayList<JAXBElement<?>>();
        }
        return this.fxProfileAbstracts;
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
