// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_sampler_state", propOrder = { "wrapS", "wrapT", "minfilter", "magfilter", "mipfilter", "mipmapMaxlevel", "mipmapBias", "extras" })
public class GlesSamplerState
{
    @XmlElement(name = "wrap_s", defaultValue = "REPEAT")
    protected GlesSamplerWrap wrapS;
    @XmlElement(name = "wrap_t", defaultValue = "REPEAT")
    protected GlesSamplerWrap wrapT;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon minfilter;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon magfilter;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon mipfilter;
    @XmlElement(name = "mipmap_maxlevel", defaultValue = "255")
    protected Short mipmapMaxlevel;
    @XmlElement(name = "mipmap_bias", defaultValue = "0.0")
    protected Float mipmapBias;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
    public GlesSamplerWrap getWrapS() {
        return this.wrapS;
    }
    
    public void setWrapS(final GlesSamplerWrap value) {
        this.wrapS = value;
    }
    
    public GlesSamplerWrap getWrapT() {
        return this.wrapT;
    }
    
    public void setWrapT(final GlesSamplerWrap value) {
        this.wrapT = value;
    }
    
    public FxSamplerFilterCommon getMinfilter() {
        return this.minfilter;
    }
    
    public void setMinfilter(final FxSamplerFilterCommon value) {
        this.minfilter = value;
    }
    
    public FxSamplerFilterCommon getMagfilter() {
        return this.magfilter;
    }
    
    public void setMagfilter(final FxSamplerFilterCommon value) {
        this.magfilter = value;
    }
    
    public FxSamplerFilterCommon getMipfilter() {
        return this.mipfilter;
    }
    
    public void setMipfilter(final FxSamplerFilterCommon value) {
        this.mipfilter = value;
    }
    
    public Short getMipmapMaxlevel() {
        return this.mipmapMaxlevel;
    }
    
    public void setMipmapMaxlevel(final Short value) {
        this.mipmapMaxlevel = value;
    }
    
    public Float getMipmapBias() {
        return this.mipmapBias;
    }
    
    public void setMipmapBias(final Float value) {
        this.mipmapBias = value;
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
}
