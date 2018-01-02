// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texture_pipeline", propOrder = { "texcombinersAndTexenvsAndExtras" })
public class GlesTexturePipeline
{
    @XmlElements({ @XmlElement(name = "texenv", type = GlesTexenvCommandType.class), @XmlElement(name = "extra", type = Extra.class), @XmlElement(name = "texcombiner", type = GlesTexcombinerCommandType.class) })
    protected List<Object> texcombinersAndTexenvsAndExtras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    
    public List<Object> getTexcombinersAndTexenvsAndExtras() {
        if (this.texcombinersAndTexenvsAndExtras == null) {
            this.texcombinersAndTexenvsAndExtras = new ArrayList<Object>();
        }
        return this.texcombinersAndTexenvsAndExtras;
    }
    
    public String getSid() {
        return this.sid;
    }
    
    public void setSid(final String value) {
        this.sid = value;
    }
}
