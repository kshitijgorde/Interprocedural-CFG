// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cg_surface_type", propOrder = { "generator" })
public class CgSurfaceType extends FxSurfaceCommon
{
    protected Generator generator;
    
    public Generator getGenerator() {
        return this.generator;
    }
    
    public void setGenerator(final Generator value) {
        this.generator = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "annotates", "codesAndIncludes", "name", "setparams" })
    public static class Generator
    {
        @XmlElement(name = "annotate")
        protected List<FxAnnotateCommon> annotates;
        @XmlElements({ @XmlElement(name = "include", type = FxIncludeCommon.class), @XmlElement(name = "code", type = FxCodeProfile.class) })
        protected List<Object> codesAndIncludes;
        @XmlElement(required = true)
        protected Name name;
        @XmlElement(name = "setparam")
        protected List<CgSetparamSimple> setparams;
        
        public List<FxAnnotateCommon> getAnnotates() {
            if (this.annotates == null) {
                this.annotates = new ArrayList<FxAnnotateCommon>();
            }
            return this.annotates;
        }
        
        public List<Object> getCodesAndIncludes() {
            if (this.codesAndIncludes == null) {
                this.codesAndIncludes = new ArrayList<Object>();
            }
            return this.codesAndIncludes;
        }
        
        public Name getName() {
            return this.name;
        }
        
        public void setName(final Name value) {
            this.name = value;
        }
        
        public List<CgSetparamSimple> getSetparams() {
            if (this.setparams == null) {
                this.setparams = new ArrayList<CgSetparamSimple>();
            }
            return this.setparams;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "value" })
        public static class Name
        {
            @XmlValue
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String value;
            @XmlAttribute
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String source;
            
            public String getValue() {
                return this.value;
            }
            
            public void setValue(final String value) {
                this.value = value;
            }
            
            public String getSource() {
                return this.source;
            }
            
            public void setSource(final String value) {
                this.source = value;
            }
        }
    }
}
