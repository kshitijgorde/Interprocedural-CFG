// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texcombiner_command_type", propOrder = { "constant", "rgb", "alpha" })
public class GlesTexcombinerCommandType
{
    protected GlesTextureConstantType constant;
    @XmlElement(name = "RGB")
    protected GlesTexcombinerCommandRGBType rgb;
    protected GlesTexcombinerCommandAlphaType alpha;
    
    public GlesTextureConstantType getConstant() {
        return this.constant;
    }
    
    public void setConstant(final GlesTextureConstantType value) {
        this.constant = value;
    }
    
    public GlesTexcombinerCommandRGBType getRGB() {
        return this.rgb;
    }
    
    public void setRGB(final GlesTexcombinerCommandRGBType value) {
        this.rgb = value;
    }
    
    public GlesTexcombinerCommandAlphaType getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final GlesTexcombinerCommandAlphaType value) {
        this.alpha = value;
    }
}
