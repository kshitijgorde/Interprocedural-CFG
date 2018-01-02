// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texcombiner_commandAlpha_type", propOrder = { "arguments" })
public class GlesTexcombinerCommandAlphaType
{
    @XmlElement(name = "argument", required = true)
    protected List<GlesTexcombinerArgumentAlphaType> arguments;
    @XmlAttribute
    protected GlesTexcombinerOperatorAlphaEnums operator;
    @XmlAttribute
    protected Float scale;
    
    public List<GlesTexcombinerArgumentAlphaType> getArguments() {
        if (this.arguments == null) {
            this.arguments = new ArrayList<GlesTexcombinerArgumentAlphaType>();
        }
        return this.arguments;
    }
    
    public GlesTexcombinerOperatorAlphaEnums getOperator() {
        return this.operator;
    }
    
    public void setOperator(final GlesTexcombinerOperatorAlphaEnums value) {
        this.operator = value;
    }
    
    public Float getScale() {
        return this.scale;
    }
    
    public void setScale(final Float value) {
        this.scale = value;
    }
}
