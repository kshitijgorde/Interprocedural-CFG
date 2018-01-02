// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_surface_common", propOrder = { "initPlanar", "initVolume", "initAsNull", "initFroms", "initAsTarget", "initCube", "format", "formatHint", "size", "viewportRatio", "mipLevels", "mipmapGenerate", "extras" })
public class FxSurfaceCommon
{
    @XmlElement(name = "init_planar")
    protected FxSurfaceInitPlanarCommon initPlanar;
    @XmlElement(name = "init_volume")
    protected FxSurfaceInitVolumeCommon initVolume;
    @XmlElement(name = "init_as_null")
    protected Object initAsNull;
    @XmlElement(name = "init_from")
    protected List<FxSurfaceInitFromCommon> initFroms;
    @XmlElement(name = "init_as_target")
    protected Object initAsTarget;
    @XmlElement(name = "init_cube")
    protected FxSurfaceInitCubeCommon initCube;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String format;
    @XmlElement(name = "format_hint")
    protected FxSurfaceFormatHintCommon formatHint;
    @XmlList
    @XmlElement(type = Long.class, defaultValue = "0 0 0")
    protected List<Long> size;
    @XmlList
    @XmlElement(name = "viewport_ratio", type = Double.class, defaultValue = "1 1")
    protected List<Double> viewportRatio;
    @XmlElement(name = "mip_levels", defaultValue = "0")
    protected Long mipLevels;
    @XmlElement(name = "mipmap_generate")
    protected Boolean mipmapGenerate;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
    protected String type;
    
    public FxSurfaceInitPlanarCommon getInitPlanar() {
        return this.initPlanar;
    }
    
    public void setInitPlanar(final FxSurfaceInitPlanarCommon value) {
        this.initPlanar = value;
    }
    
    public FxSurfaceInitVolumeCommon getInitVolume() {
        return this.initVolume;
    }
    
    public void setInitVolume(final FxSurfaceInitVolumeCommon value) {
        this.initVolume = value;
    }
    
    public Object getInitAsNull() {
        return this.initAsNull;
    }
    
    public void setInitAsNull(final Object value) {
        this.initAsNull = value;
    }
    
    public List<FxSurfaceInitFromCommon> getInitFroms() {
        if (this.initFroms == null) {
            this.initFroms = new ArrayList<FxSurfaceInitFromCommon>();
        }
        return this.initFroms;
    }
    
    public Object getInitAsTarget() {
        return this.initAsTarget;
    }
    
    public void setInitAsTarget(final Object value) {
        this.initAsTarget = value;
    }
    
    public FxSurfaceInitCubeCommon getInitCube() {
        return this.initCube;
    }
    
    public void setInitCube(final FxSurfaceInitCubeCommon value) {
        this.initCube = value;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    public void setFormat(final String value) {
        this.format = value;
    }
    
    public FxSurfaceFormatHintCommon getFormatHint() {
        return this.formatHint;
    }
    
    public void setFormatHint(final FxSurfaceFormatHintCommon value) {
        this.formatHint = value;
    }
    
    public List<Long> getSize() {
        if (this.size == null) {
            this.size = new ArrayList<Long>();
        }
        return this.size;
    }
    
    public List<Double> getViewportRatio() {
        if (this.viewportRatio == null) {
            this.viewportRatio = new ArrayList<Double>();
        }
        return this.viewportRatio;
    }
    
    public Long getMipLevels() {
        return this.mipLevels;
    }
    
    public void setMipLevels(final Long value) {
        this.mipLevels = value;
    }
    
    public Boolean isMipmapGenerate() {
        return this.mipmapGenerate;
    }
    
    public void setMipmapGenerate(final Boolean value) {
        this.mipmapGenerate = value;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String value) {
        this.type = value;
    }
}
