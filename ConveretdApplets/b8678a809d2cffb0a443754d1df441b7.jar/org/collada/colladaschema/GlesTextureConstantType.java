// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texture_constant_type")
public class GlesTextureConstantType
{
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String param;
    @XmlAttribute(name = "value")
    protected List<Double> values;
    
    public String getParam() {
        return this.param;
    }
    
    public void setParam(final String value) {
        this.param = value;
    }
    
    public List<Double> getValues() {
        if (this.values == null) {
            this.values = new ArrayList<Double>();
        }
        return this.values;
    }
}
