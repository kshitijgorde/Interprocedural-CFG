// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.Attributes;
import java.util.Hashtable;
import org.xml.sax.helpers.AttributesImpl;

public class AttributesImplSerializer extends AttributesImpl
{
    private Hashtable m_indexFromQName;
    public static final int MAX = 12;
    private static final int MAXMinus1 = 11;
    
    public AttributesImplSerializer() {
        this.m_indexFromQName = new Hashtable();
    }
    
    public int getIndex(final String qname) {
        if (super.getLength() < 12) {
            final int index = super.getIndex(qname);
            return index;
        }
        final Integer i = this.m_indexFromQName.get(qname);
        int index;
        if (i == null) {
            index = -1;
        }
        else {
            index = i;
        }
        return index;
    }
    
    public void addAttribute(final String uri, final String local, final String qname, final String type, final String val) {
        final int index = super.getLength();
        super.addAttribute(uri, local, qname, type, val);
        if (index < 11) {
            return;
        }
        if (index == 11) {
            this.switchOverToHash(12);
        }
        else {
            final Integer i = new Integer(index);
            this.m_indexFromQName.put(qname, i);
        }
    }
    
    private void switchOverToHash(final int numAtts) {
        for (int index = 0; index < numAtts; ++index) {
            final String qName = super.getQName(index);
            final Integer i = new Integer(index);
            this.m_indexFromQName.put(qName, i);
        }
    }
    
    public void clear() {
        final int len = super.getLength();
        super.clear();
        if (12 <= len) {
            this.m_indexFromQName.clear();
        }
    }
    
    public void setAttributes(final Attributes atts) {
        super.setAttributes(atts);
        final int numAtts = atts.getLength();
        if (12 <= numAtts) {
            this.switchOverToHash(numAtts);
        }
    }
}
