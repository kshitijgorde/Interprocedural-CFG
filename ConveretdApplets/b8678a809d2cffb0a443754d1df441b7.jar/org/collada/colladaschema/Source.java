// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "boolArray", "floatArray", "nameArray", "intArray", "idrefArray", "techniqueCommon", "techniques" })
@XmlRootElement(name = "source")
public class Source
{
    protected Asset asset;
    @XmlElement(name = "bool_array")
    protected BoolArray boolArray;
    @XmlElement(name = "float_array")
    protected FloatArray floatArray;
    @XmlElement(name = "Name_array")
    protected NameArray nameArray;
    @XmlElement(name = "int_array")
    protected IntArray intArray;
    @XmlElement(name = "IDREF_array")
    protected IDREFArray idrefArray;
    @XmlElement(name = "technique_common")
    protected TechniqueCommon techniqueCommon;
    @XmlElement(name = "technique")
    protected List<Technique> techniques;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public BoolArray getBoolArray() {
        return this.boolArray;
    }
    
    public void setBoolArray(final BoolArray value) {
        this.boolArray = value;
    }
    
    public FloatArray getFloatArray() {
        return this.floatArray;
    }
    
    public void setFloatArray(final FloatArray value) {
        this.floatArray = value;
    }
    
    public NameArray getNameArray() {
        return this.nameArray;
    }
    
    public void setNameArray(final NameArray value) {
        this.nameArray = value;
    }
    
    public IntArray getIntArray() {
        return this.intArray;
    }
    
    public void setIntArray(final IntArray value) {
        this.intArray = value;
    }
    
    public IDREFArray getIDREFArray() {
        return this.idrefArray;
    }
    
    public void setIDREFArray(final IDREFArray value) {
        this.idrefArray = value;
    }
    
    public TechniqueCommon getTechniqueCommon() {
        return this.techniqueCommon;
    }
    
    public void setTechniqueCommon(final TechniqueCommon value) {
        this.techniqueCommon = value;
    }
    
    public List<Technique> getTechniques() {
        if (this.techniques == null) {
            this.techniques = new ArrayList<Technique>();
        }
        return this.techniques;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "accessor" })
    public static class TechniqueCommon
    {
        @XmlElement(required = true)
        protected Accessor accessor;
        
        public Accessor getAccessor() {
            return this.accessor;
        }
        
        public void setAccessor(final Accessor value) {
            this.accessor = value;
        }
    }
}
