// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texenv_command_type", propOrder = { "constant" })
public class GlesTexenvCommandType
{
    protected GlesTextureConstantType constant;
    @XmlAttribute
    protected GlesTexenvModeEnums operator;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String unit;
    
    public GlesTextureConstantType getConstant() {
        return this.constant;
    }
    
    public void setConstant(final GlesTextureConstantType value) {
        this.constant = value;
    }
    
    public GlesTexenvModeEnums getOperator() {
        return this.operator;
    }
    
    public void setOperator(final GlesTexenvModeEnums value) {
        this.operator = value;
    }
    
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(final String value) {
        this.unit = value;
    }
}
