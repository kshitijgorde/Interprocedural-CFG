// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_sampler2D_common", propOrder = { "source", "wrapS", "wrapT", "minfilter", "magfilter", "mipfilter", "borderColor", "mipmapMaxlevel", "mipmapBias", "extras" })
public class FxSampler2DCommon
{
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String source;
    @XmlElement(name = "wrap_s", defaultValue = "WRAP")
    protected FxSamplerWrapCommon wrapS;
    @XmlElement(name = "wrap_t", defaultValue = "WRAP")
    protected FxSamplerWrapCommon wrapT;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon minfilter;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon magfilter;
    @XmlElement(defaultValue = "NONE")
    protected FxSamplerFilterCommon mipfilter;
    @XmlList
    @XmlElement(name = "border_color", type = Double.class)
    protected List<Double> borderColor;
    @XmlElement(name = "mipmap_maxlevel", defaultValue = "255")
    protected Short mipmapMaxlevel;
    @XmlElement(name = "mipmap_bias", defaultValue = "0.0")
    protected Float mipmapBias;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
    
    public FxSamplerWrapCommon getWrapS() {
        return this.wrapS;
    }
    
    public void setWrapS(final FxSamplerWrapCommon value) {
        this.wrapS = value;
    }
    
    public FxSamplerWrapCommon getWrapT() {
        return this.wrapT;
    }
    
    public void setWrapT(final FxSamplerWrapCommon value) {
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
    
    public List<Double> getBorderColor() {
        if (this.borderColor == null) {
            this.borderColor = new ArrayList<Double>();
        }
        return this.borderColor;
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
}
