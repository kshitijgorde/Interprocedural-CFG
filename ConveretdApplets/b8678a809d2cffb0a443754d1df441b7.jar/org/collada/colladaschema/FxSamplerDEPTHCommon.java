// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_samplerDEPTH_common", propOrder = { "source", "wrapS", "wrapT", "minfilter", "magfilter", "extras" })
public class FxSamplerDEPTHCommon
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
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
