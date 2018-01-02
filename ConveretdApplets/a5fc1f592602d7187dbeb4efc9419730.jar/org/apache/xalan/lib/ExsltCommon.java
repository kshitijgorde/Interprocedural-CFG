// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import org.apache.xpath.NodeSet;
import org.apache.xalan.extensions.ExpressionContext;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.axes.RTFIterator;
import org.apache.xml.dtm.ref.DTMNodeIterator;

public class ExsltCommon
{
    public static String objectType(final Object obj) {
        if (obj instanceof String) {
            return "string";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        if (obj instanceof Number) {
            return "number";
        }
        if (!(obj instanceof DTMNodeIterator)) {
            return "unknown";
        }
        final DTMIterator dtmI = ((DTMNodeIterator)obj).getDTMIterator();
        if (dtmI instanceof RTFIterator) {
            return "RTF";
        }
        return "node-set";
    }
    
    public static NodeSet nodeSet(final ExpressionContext myProcessor, final Object rtf) {
        return Extensions.nodeset(myProcessor, rtf);
    }
}
