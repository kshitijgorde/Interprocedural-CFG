// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.Attributes;
import java.util.Hashtable;
import org.xml.sax.helpers.AttributesImpl;

public final class AttributesImplSerializer extends AttributesImpl
{
    private final Hashtable m_indexFromQName;
    private final StringBuffer m_buff;
    private static final int MAX = 12;
    private static final int MAXMinus1 = 11;
    
    public AttributesImplSerializer() {
        this.m_indexFromQName = new Hashtable();
        this.m_buff = new StringBuffer();
    }
    
    public final int getIndex(final String qname) {
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
    
    public final void addAttribute(final String uri, final String local, final String qname, final String type, final String val) {
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
            this.m_buff.setLength(0);
            this.m_buff.append('{').append(uri).append('}').append(local);
            final String key = this.m_buff.toString();
            this.m_indexFromQName.put(key, i);
        }
    }
    
    private void switchOverToHash(final int numAtts) {
        for (int index = 0; index < numAtts; ++index) {
            final String qName = super.getQName(index);
            final Integer i = new Integer(index);
            this.m_indexFromQName.put(qName, i);
            final String uri = super.getURI(index);
            final String local = super.getLocalName(index);
            this.m_buff.setLength(0);
            this.m_buff.append('{').append(uri).append('}').append(local);
            final String key = this.m_buff.toString();
            this.m_indexFromQName.put(key, i);
        }
    }
    
    public final void clear() {
        final int len = super.getLength();
        super.clear();
        if (12 <= len) {
            this.m_indexFromQName.clear();
        }
    }
    
    public final void setAttributes(final Attributes atts) {
        super.setAttributes(atts);
        final int numAtts = atts.getLength();
        if (12 <= numAtts) {
            this.switchOverToHash(numAtts);
        }
    }
    
    public final int getIndex(final String uri, final String localName) {
        if (super.getLength() < 12) {
            final int index = super.getIndex(uri, localName);
            return index;
        }
        this.m_buff.setLength(0);
        this.m_buff.append('{').append(uri).append('}').append(localName);
        final String key = this.m_buff.toString();
        final Integer i = this.m_indexFromQName.get(key);
        int index;
        if (i == null) {
            index = -1;
        }
        else {
            index = i;
        }
        return index;
    }
}
