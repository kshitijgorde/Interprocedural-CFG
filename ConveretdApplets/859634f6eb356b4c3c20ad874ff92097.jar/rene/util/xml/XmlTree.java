// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

import rene.util.list.ListElement;
import java.util.Enumeration;
import rene.util.list.Tree;

public class XmlTree extends Tree implements Enumeration
{
    ListElement Current;
    
    public XmlTree(final XmlTag xmlTag) {
        super(xmlTag);
    }
    
    public XmlTag getTag() {
        return (XmlTag)this.content();
    }
    
    public XmlTree xmlFirstContent() {
        if (this.firstchild() != null) {
            return (XmlTree)this.firstchild();
        }
        return null;
    }
    
    public boolean isText() {
        return !this.haschildren() || (this.firstchild() == this.lastchild() && ((XmlTree)this.firstchild()).getTag() instanceof XmlTagText);
    }
    
    public String getText() {
        if (!this.haschildren()) {
            return "";
        }
        return ((XmlTagText)((XmlTree)this.firstchild()).getTag()).getContent();
    }
    
    public Enumeration getContent() {
        this.Current = this.children().first();
        return this;
    }
    
    public boolean hasMoreElements() {
        return this.Current != null;
    }
    
    public Object nextElement() {
        if (this.Current == null) {
            return null;
        }
        final XmlTree xmlTree = (XmlTree)this.Current.content();
        this.Current = this.Current.next();
        return xmlTree;
    }
}
