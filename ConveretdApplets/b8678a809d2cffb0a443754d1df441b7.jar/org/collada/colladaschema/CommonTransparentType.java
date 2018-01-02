// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "common_transparent_type")
public class CommonTransparentType extends CommonColorOrTextureType
{
    @XmlAttribute
    protected FxOpaqueEnum opaque;
    
    public FxOpaqueEnum getOpaque() {
        if (this.opaque == null) {
            return FxOpaqueEnum.A_ONE;
        }
        return this.opaque;
    }
    
    public void setOpaque(final FxOpaqueEnum value) {
        this.opaque = value;
    }
}
