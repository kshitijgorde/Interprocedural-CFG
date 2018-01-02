// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.jdom;

import org.jdom.Element;
import java.util.Iterator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import java.util.Collections;
import org.jdom.JDOMFactory;
import org.jdom.DefaultJDOMFactory;
import java.util.List;
import com.stonewall.parser.Dictionary;
import org.jdom.Namespace;

public class SAXHandler extends org.jdom.input.SAXHandler
{
    public static final Namespace saxns;
    public static final String locAttr = "location";
    private static Dictionary<String> specialTags;
    private ParserInput input;
    private List<Namespace> nsfilter;
    
    static {
        saxns = Namespace.getNamespace("sax", "http://org.parser/sax");
        (SAXHandler.specialTags = new Dictionary<String>()).put("switch", "pswitch");
        SAXHandler.specialTags.put("case", "pcase");
        SAXHandler.specialTags.put("while", "pwhile");
    }
    
    SAXHandler() {
        super((JDOMFactory)new DefaultJDOMFactory());
        this.input = null;
        this.nsfilter = Collections.emptyList();
    }
    
    void setInput(final ParserInput input) {
        this.input = input;
    }
    
    void setFilter(final List<Namespace> nsfilter) {
        this.nsfilter = nsfilter;
    }
    
    public void startElement(final String ns, final String n, final String qn, final Attributes a) throws SAXException {
        final AttributesImpl aList = new AttributesImpl(a);
        if (this.apply(ns)) {
            final String p = String.valueOf(SAXHandler.saxns.getPrefix()) + ":";
            final String uri = SAXHandler.saxns.getURI();
            aList.addAttribute(uri, "location", String.valueOf(p) + "location", "", this.input.location());
        }
        final String[] xn = this.xlate(qn);
        super.startElement(ns, xn[0], xn[1], (Attributes)aList);
    }
    
    String[] xlate(final String qn) {
        final String[] result = { qn, qn };
        final String[] parts = qn.split(":");
        if (parts.length == 2) {
            final String p = parts[0];
            final String n = SAXHandler.specialTags.get(parts[1], parts[1]);
            result[0] = n;
            result[1] = String.valueOf(p) + ":" + n;
        }
        return result;
    }
    
    boolean apply(final String uri) {
        for (final Namespace ns : this.nsfilter) {
            if (ns.getURI().equals(uri)) {
                return true;
            }
        }
        return false;
    }
    
    public static String location(final Element tag) {
        return "<" + tag.getQualifiedName() + "/>" + " @ " + tag.getAttributeValue("location", SAXHandler.saxns);
    }
}
