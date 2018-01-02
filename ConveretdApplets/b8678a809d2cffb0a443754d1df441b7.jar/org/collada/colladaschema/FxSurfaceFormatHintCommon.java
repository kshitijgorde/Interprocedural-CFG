// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_surface_format_hint_common", propOrder = { "channels", "range", "precision", "options", "extras" })
public class FxSurfaceFormatHintCommon
{
    @XmlElement(required = true)
    protected FxSurfaceFormatHintChannelsEnum channels;
    @XmlElement(required = true)
    protected FxSurfaceFormatHintRangeEnum range;
    protected FxSurfaceFormatHintPrecisionEnum precision;
    @XmlElement(name = "option")
    protected List<FxSurfaceFormatHintOptionEnum> options;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public FxSurfaceFormatHintChannelsEnum getChannels() {
        return this.channels;
    }
    
    public void setChannels(final FxSurfaceFormatHintChannelsEnum value) {
        this.channels = value;
    }
    
    public FxSurfaceFormatHintRangeEnum getRange() {
        return this.range;
    }
    
    public void setRange(final FxSurfaceFormatHintRangeEnum value) {
        this.range = value;
    }
    
    public FxSurfaceFormatHintPrecisionEnum getPrecision() {
        return this.precision;
    }
    
    public void setPrecision(final FxSurfaceFormatHintPrecisionEnum value) {
        this.precision = value;
    }
    
    public List<FxSurfaceFormatHintOptionEnum> getOptions() {
        if (this.options == null) {
            this.options = new ArrayList<FxSurfaceFormatHintOptionEnum>();
        }
        return this.options;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
}
