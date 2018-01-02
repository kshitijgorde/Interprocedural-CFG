// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_surface_init_cube_common", propOrder = { "all", "primary", "faces" })
public class FxSurfaceInitCubeCommon
{
    protected All all;
    protected Primary primary;
    @XmlElement(name = "face")
    protected List<Face> faces;
    
    public All getAll() {
        return this.all;
    }
    
    public void setAll(final All value) {
        this.all = value;
    }
    
    public Primary getPrimary() {
        return this.primary;
    }
    
    public void setPrimary(final Primary value) {
        this.primary = value;
    }
    
    public List<Face> getFaces() {
        if (this.faces == null) {
            this.faces = new ArrayList<Face>();
        }
        return this.faces;
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
    public static class Face
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
    @XmlType(name = "", propOrder = { "orders" })
    public static class Primary
    {
        @XmlElement(name = "order")
        protected List<FxSurfaceFaceEnum> orders;
        @XmlAttribute(required = true)
        @XmlIDREF
        protected Object ref;
        
        public List<FxSurfaceFaceEnum> getOrders() {
            if (this.orders == null) {
                this.orders = new ArrayList<FxSurfaceFaceEnum>();
            }
            return this.orders;
        }
        
        public Object getRef() {
            return this.ref;
        }
        
        public void setRef(final Object value) {
            this.ref = value;
        }
    }
}
