// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_surface_init_volume_common", propOrder = { "primary", "all" })
public class FxSurfaceInitVolumeCommon
{
    protected Primary primary;
    protected All all;
    
    public Primary getPrimary() {
        return this.primary;
    }
    
    public void setPrimary(final Primary value) {
        this.primary = value;
    }
    
    public All getAll() {
        return this.all;
    }
    
    public void setAll(final All value) {
        this.all = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class All
    {
        @XmlAttribute(required = true)
        @XmlIDREF
        protected Object ref;
        
        public Object getRef() {
            return this.ref;
        }
        
        public void setRef(final Object value) {
            this.ref = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Primary
    {
        @XmlAttribute(required = true)
        @XmlIDREF
        protected Object ref;
        
        public Object getRef() {
            return this.ref;
        }
        
        public void setRef(final Object value) {
            this.ref = value;
        }
    }
}
