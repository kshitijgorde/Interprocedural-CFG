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
@XmlType(name = "InputLocal")
public class InputLocal
{
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    @XmlAttribute(required = true)
    protected String source;
    
    public String getSemantic() {
        return this.semantic;
    }
    
    public void setSemantic(final String value) {
        this.semantic = value;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
}
