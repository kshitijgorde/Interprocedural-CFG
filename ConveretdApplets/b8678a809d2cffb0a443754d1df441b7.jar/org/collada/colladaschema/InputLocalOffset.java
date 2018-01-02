// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputLocalOffset")
public class InputLocalOffset
{
    @XmlAttribute(required = true)
    protected BigInteger offset;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    @XmlAttribute
    protected BigInteger set;
    @XmlAttribute(required = true)
    protected String source;
    
    public BigInteger getOffset() {
        return this.offset;
    }
    
    public void setOffset(final BigInteger value) {
        this.offset = value;
    }
    
    public String getSemantic() {
        return this.semantic;
    }
    
    public void setSemantic(final String value) {
        this.semantic = value;
    }
    
    public BigInteger getSet() {
        return this.set;
    }
    
    public void setSet(final BigInteger value) {
        this.set = value;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String value) {
        this.source = value;
    }
}
