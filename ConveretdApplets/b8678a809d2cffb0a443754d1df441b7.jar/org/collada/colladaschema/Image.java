// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "initFrom", "data", "extras" })
@XmlRootElement(name = "image")
public class Image
{
    protected Asset asset;
    @XmlElement(name = "init_from")
    protected String initFrom;
    @XmlList
    protected List<String> data;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    protected BigInteger depth;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String format;
    @XmlAttribute
    protected BigInteger height;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute
    protected BigInteger width;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public String getInitFrom() {
        return this.initFrom;
    }
    
    public void setInitFrom(final String value) {
        this.initFrom = value;
    }
    
    public List<String> getData() {
        if (this.data == null) {
            this.data = new ArrayList<String>();
        }
        return this.data;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public BigInteger getDepth() {
        if (this.depth == null) {
            return new BigInteger("1");
        }
        return this.depth;
    }
    
    public void setDepth(final BigInteger value) {
        this.depth = value;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    public void setFormat(final String value) {
        this.format = value;
    }
    
    public BigInteger getHeight() {
        return this.height;
    }
    
    public void setHeight(final BigInteger value) {
        this.height = value;
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
    
    public BigInteger getWidth() {
        return this.width;
    }
    
    public void setWidth(final BigInteger value) {
        this.width = value;
    }
}
