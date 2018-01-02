// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.w3c.dom.Text;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentFragment;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;

public class XString extends XObject
{
    public static XString EMPTYSTRING;
    
    static {
        XString.EMPTYSTRING = new XString("");
    }
    
    public XString(final String val) {
        super(val);
    }
    
    public boolean bool() {
        return this.str().length() > 0;
    }
    
    public static double castToNum(final String s) {
        double result;
        if (s == null) {
            result = 0.0;
        }
        else {
            try {
                result = Double.valueOf(s.trim());
            }
            catch (NumberFormatException ex) {
                result = Double.NaN;
            }
        }
        return result;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        return this.str().equals(obj2.str());
    }
    
    public int getType() {
        return 3;
    }
    
    public String getTypeString() {
        return "#STRING";
    }
    
    public double num() {
        return castToNum((String)super.m_obj);
    }
    
    public DocumentFragment rtree(final XPathContext support) {
        final DocumentFragment df = support.getDOMHelper().getDOMFactory().createDocumentFragment();
        final Text textNode = support.getDOMHelper().getDOMFactory().createTextNode(this.str());
        df.appendChild(textNode);
        return df;
    }
    
    public String str() {
        return (String)((super.m_obj != null) ? super.m_obj : "");
    }
}
