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
@XmlType(name = "gles_texcombiner_commandRGB_type", propOrder = { "arguments" })
public class GlesTexcombinerCommandRGBType
{
    @XmlElement(name = "argument", required = true)
    protected List<GlesTexcombinerArgumentRGBType> arguments;
    @XmlAttribute
    protected GlesTexcombinerOperatorRGBEnums operator;
    @XmlAttribute
    protected Float scale;
    
    public List<GlesTexcombinerArgumentRGBType> getArguments() {
        if (this.arguments == null) {
            this.arguments = new ArrayList<GlesTexcombinerArgumentRGBType>();
        }
        return this.arguments;
    }
    
    public GlesTexcombinerOperatorRGBEnums getOperator() {
        return this.operator;
    }
    
    public void setOperator(final GlesTexcombinerOperatorRGBEnums value) {
        this.operator = value;
    }
    
    public Float getScale() {
        return this.scale;
    }
    
    public void setScale(final Float value) {
        this.scale = value;
    }
}
