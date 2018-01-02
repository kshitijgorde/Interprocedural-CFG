// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "common_color_or_texture_type", propOrder = { "param", "color", "texture" })
public class CommonColorOrTextureType
{
    protected Param param;
    protected Color color;
    protected Texture texture;
    
    public Param getParam() {
        return this.param;
    }
    
    public void setParam(final Param value) {
        this.param = value;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color value) {
        this.color = value;
    }
    
    public Texture getTexture() {
        return this.texture;
    }
    
    public void setTexture(final Texture value) {
        this.texture = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "values" })
    public static class Color
    {
        @XmlValue
        protected List<Double> values;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sid;
        
        public List<Double> getValues() {
            if (this.values == null) {
                this.values = new ArrayList<Double>();
            }
            return this.values;
        }
        
        public String getSid() {
            return this.sid;
        }
        
        public void setSid(final String value) {
            this.sid = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Param
    {
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String ref;
        
        public String getRef() {
            return this.ref;
        }
        
        public void setRef(final String value) {
            this.ref = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "extra" })
    public static class Texture
    {
        protected Extra extra;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String texcoord;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String texture;
        
        public Extra getExtra() {
            return this.extra;
        }
        
        public void setExtra(final Extra value) {
            this.extra = value;
        }
        
        public String getTexcoord() {
            return this.texcoord;
        }
        
        public void setTexcoord(final String value) {
            this.texcoord = value;
        }
        
        public String getTexture() {
            return this.texture;
        }
        
        public void setTexture(final String value) {
            this.texture = value;
        }
    }
}
