// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import org.w3c.dom.Element;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "anies" })
@XmlRootElement(name = "technique")
public class Technique
{
    @XmlAnyElement
    protected List<Element> anies;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String profile;
    
    public List<Element> getAnies() {
        if (this.anies == null) {
            this.anies = new ArrayList<Element>();
        }
        return this.anies;
    }
    
    public String getProfile() {
        return this.profile;
    }
    
    public void setProfile(final String value) {
        this.profile = value;
    }
}
