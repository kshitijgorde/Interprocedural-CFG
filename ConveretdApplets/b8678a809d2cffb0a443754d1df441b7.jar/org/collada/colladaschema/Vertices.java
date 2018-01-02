// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "inputs", "extras" })
@XmlRootElement(name = "vertices")
public class Vertices
{
    @XmlElement(name = "input", required = true)
    protected List<InputLocal> inputs;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    
    public List<InputLocal> getInputs() {
        if (this.inputs == null) {
            this.inputs = new ArrayList<InputLocal>();
        }
        return this.inputs;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
}
