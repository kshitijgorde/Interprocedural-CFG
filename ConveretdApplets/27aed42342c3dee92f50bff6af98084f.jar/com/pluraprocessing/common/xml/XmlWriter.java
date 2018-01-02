// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

public class XmlWriter
{
    private StringBuilder buffer;
    
    public XmlWriter() {
        this.buffer = new StringBuilder();
    }
    
    public String GetXml() {
        return this.buffer.toString();
    }
    
    public XmlWriter error() {
        this.partialOpenTag("failure").partialCloseTag();
        return this;
    }
    
    public XmlWriter success() {
        this.partialOpenTag("success").partialCloseTag();
        return this;
    }
    
    public XmlWriter reload(final String versionId) {
        this.partialOpenTag("reload").property("v", versionId).partialCloseTag();
        return this;
    }
    
    public XmlWriter openBracket() {
        this.buffer.append("<");
        return this;
    }
    
    public XmlWriter closeBracket() {
        this.buffer.append(">");
        return this;
    }
    
    public XmlWriter completeOpenTag(final String tagName) {
        this.openBracket().appendString(tagName).closeBracket();
        return this;
    }
    
    public XmlWriter completeOpenWithId(final String tagName, final String id) {
        this.partialOpenTag(tagName).id(id).closeBracket();
        return this;
    }
    
    public XmlWriter completeEmptyTagSetWithId(final String tagName, final String id) {
        this.partialOpenTag(tagName).id(id).partialCloseTag();
        return this;
    }
    
    public XmlWriter completeTagSetWithId(final String tagName, final String id, final String content) {
        this.completeOpenWithId(tagName, id).appendString(content).completeCloseTag(tagName);
        return this;
    }
    
    public XmlWriter completeTagSet(final String tagName, final String content) {
        this.completeOpenTag(tagName).appendString(content).completeCloseTag(tagName);
        return this;
    }
    
    public XmlWriter partialOpenTag(final String tagName) {
        this.openBracket().appendString(tagName);
        return this;
    }
    
    public XmlWriter id(final String value) {
        this.property("id", value);
        return this;
    }
    
    public XmlWriter property(final String name, final String value) {
        this.openProperty(name).appendString(value).closeProperty();
        return this;
    }
    
    public XmlWriter openProperty(final String name) {
        this.buffer.append(" ").append(name).append("=\"");
        return this;
    }
    
    public XmlWriter closeProperty() {
        this.buffer.append("\"");
        return this;
    }
    
    public XmlWriter partialCloseTag() {
        this.appendString(" /").closeBracket();
        return this;
    }
    
    public XmlWriter completeCloseTag(final String tagName) {
        this.openBracket().appendString("/").appendString(tagName).closeBracket();
        return this;
    }
    
    public XmlWriter appendString(final String content) {
        this.buffer.append(content);
        return this;
    }
}
