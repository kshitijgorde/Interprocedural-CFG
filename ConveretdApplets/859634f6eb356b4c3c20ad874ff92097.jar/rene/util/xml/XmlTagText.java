// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

public class XmlTagText extends XmlTag
{
    String Content;
    
    public XmlTagText(final String content) {
        super("#PCDATA");
        this.Content = content;
    }
    
    public String getContent() {
        return this.Content;
    }
}
