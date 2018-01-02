// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.JAXBElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "glsl_setarray_type", propOrder = { "samplerDEPTHsAndBool2SAndFloats" })
public class GlslSetarrayType
{
    @XmlElementRefs({ @XmlElementRef(name = "sampler3D", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "samplerDEPTH", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "int4", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "int", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float3x3", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "enum", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "bool", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "bool3", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float3", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "sampler2D", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "int3", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "bool4", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "bool2", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "samplerRECT", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float2x2", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "int2", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float4x4", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "sampler1D", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "surface", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float4", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "samplerCUBE", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "array", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class), @XmlElementRef(name = "float2", namespace = "http://www.collada.org/2005/11/COLLADASchema", type = JAXBElement.class) })
    protected List<JAXBElement<?>> samplerDEPTHsAndBool2SAndFloats;
    @XmlAttribute
    protected BigInteger length;
    
    public List<JAXBElement<?>> getSamplerDEPTHsAndBool2sAndFloats() {
        if (this.samplerDEPTHsAndBool2SAndFloats == null) {
            this.samplerDEPTHsAndBool2SAndFloats = new ArrayList<JAXBElement<?>>();
        }
        return this.samplerDEPTHsAndBool2SAndFloats;
    }
    
    public BigInteger getLength() {
        return this.length;
    }
    
    public void setLength(final BigInteger value) {
        this.length = value;
    }
}
