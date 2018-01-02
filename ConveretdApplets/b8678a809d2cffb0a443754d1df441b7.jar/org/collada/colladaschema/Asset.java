// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "contributors", "created", "keywords", "modified", "revision", "subject", "title", "unit", "upAxis" })
@XmlRootElement(name = "asset")
public class Asset
{
    @XmlElement(name = "contributor")
    protected List<Contributor> contributors;
    @XmlElement(required = true)
    protected XMLGregorianCalendar created;
    protected String keywords;
    @XmlElement(required = true)
    protected XMLGregorianCalendar modified;
    protected String revision;
    protected String subject;
    protected String title;
    protected Unit unit;
    @XmlElement(name = "up_axis", defaultValue = "Y_UP")
    protected UpAxisType upAxis;
    
    public List<Contributor> getContributors() {
        if (this.contributors == null) {
            this.contributors = new ArrayList<Contributor>();
        }
        return this.contributors;
    }
    
    public XMLGregorianCalendar getCreated() {
        return this.created;
    }
    
    public void setCreated(final XMLGregorianCalendar value) {
        this.created = value;
    }
    
    public String getKeywords() {
        return this.keywords;
    }
    
    public void setKeywords(final String value) {
        this.keywords = value;
    }
    
    public XMLGregorianCalendar getModified() {
        return this.modified;
    }
    
    public void setModified(final XMLGregorianCalendar value) {
        this.modified = value;
    }
    
    public String getRevision() {
        return this.revision;
    }
    
    public void setRevision(final String value) {
        this.revision = value;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(final String value) {
        this.subject = value;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String value) {
        this.title = value;
    }
    
    public Unit getUnit() {
        return this.unit;
    }
    
    public void setUnit(final Unit value) {
        this.unit = value;
    }
    
    public UpAxisType getUpAxis() {
        return this.upAxis;
    }
    
    public void setUpAxis(final UpAxisType value) {
        this.upAxis = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "author", "authoringTool", "comments", "copyright", "sourceData" })
    public static class Contributor
    {
        protected String author;
        @XmlElement(name = "authoring_tool")
        protected String authoringTool;
        protected String comments;
        protected String copyright;
        @XmlElement(name = "source_data")
        protected String sourceData;
        
        public String getAuthor() {
            return this.author;
        }
        
        public void setAuthor(final String value) {
            this.author = value;
        }
        
        public String getAuthoringTool() {
            return this.authoringTool;
        }
        
        public void setAuthoringTool(final String value) {
            this.authoringTool = value;
        }
        
        public String getComments() {
            return this.comments;
        }
        
        public void setComments(final String value) {
            this.comments = value;
        }
        
        public String getCopyright() {
            return this.copyright;
        }
        
        public void setCopyright(final String value) {
            this.copyright = value;
        }
        
        public String getSourceData() {
            return this.sourceData;
        }
        
        public void setSourceData(final String value) {
            this.sourceData = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Unit
    {
        @XmlAttribute
        protected Double meter;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String name;
        
        public double getMeter() {
            if (this.meter == null) {
                return 1.0;
            }
            return this.meter;
        }
        
        public void setMeter(final Double value) {
            this.meter = value;
        }
        
        public String getName() {
            if (this.name == null) {
                return "meter";
            }
            return this.name;
        }
        
        public void setName(final String value) {
            this.name = value;
        }
    }
}
