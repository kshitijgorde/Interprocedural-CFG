// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_surface_init_from_common", propOrder = { "value" })
public class FxSurfaceInitFromCommon
{
    @XmlValue
    @XmlIDREF
    protected Object value;
    @XmlAttribute
    protected FxSurfaceFaceEnum face;
    @XmlAttribute
    protected Long mip;
    @XmlAttribute
    protected Long slice;
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public FxSurfaceFaceEnum getFace() {
        if (this.face == null) {
            return FxSurfaceFaceEnum.POSITIVE_X;
        }
        return this.face;
    }
    
    public void setFace(final FxSurfaceFaceEnum value) {
        this.face = value;
    }
    
    public long getMip() {
        if (this.mip == null) {
            return 0L;
        }
        return this.mip;
    }
    
    public void setMip(final Long value) {
        this.mip = value;
    }
    
    public long getSlice() {
        if (this.slice == null) {
            return 0L;
        }
        return this.slice;
    }
    
    public void setSlice(final Long value) {
        this.slice = value;
    }
}
