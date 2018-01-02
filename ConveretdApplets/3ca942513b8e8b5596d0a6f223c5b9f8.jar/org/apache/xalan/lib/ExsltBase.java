// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import org.w3c.dom.NodeList;
import org.apache.xml.dtm.ref.DTMNodeProxy;
import org.w3c.dom.Node;

public abstract class ExsltBase
{
    protected static String toString(final Node n) {
        if (n instanceof DTMNodeProxy) {
            return ((DTMNodeProxy)n).getStringValue();
        }
        final String value = n.getNodeValue();
        if (value == null) {
            final NodeList nodelist = n.getChildNodes();
            final StringBuffer buf = new StringBuffer();
            for (int i = 0; i < nodelist.getLength(); ++i) {
                final Node childNode = nodelist.item(i);
                buf.append(toString(childNode));
            }
            return buf.toString();
        }
        return value;
    }
    
    protected static double toNumber(final Node n) {
        double d = 0.0;
        final String str = toString(n);
        try {
            d = Double.valueOf(str);
        }
        catch (NumberFormatException e) {
            d = Double.NaN;
        }
        return d;
    }
}
