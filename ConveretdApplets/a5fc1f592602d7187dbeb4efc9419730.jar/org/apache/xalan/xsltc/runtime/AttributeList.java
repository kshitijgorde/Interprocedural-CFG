// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.Vector;
import org.xml.sax.Attributes;

public class AttributeList implements Attributes
{
    private static final String EMPTYSTRING = "";
    private static final String CDATASTRING = "CDATA";
    private Hashtable _attributes;
    private Vector _names;
    private Vector _qnames;
    private Vector _values;
    private Vector _uris;
    private int _length;
    
    public AttributeList() {
        this._length = 0;
    }
    
    public AttributeList(final Attributes attributes) {
        this();
        if (attributes != null) {
            for (int count = attributes.getLength(), i = 0; i < count; ++i) {
                this.add(attributes.getQName(i), attributes.getValue(i));
            }
        }
    }
    
    private void alloc() {
        this._attributes = new Hashtable();
        this._names = new Vector();
        this._values = new Vector();
        this._qnames = new Vector();
        this._uris = new Vector();
    }
    
    public int getLength() {
        return this._length;
    }
    
    public String getURI(final int index) {
        if (index < this._length) {
            return this._uris.elementAt(index);
        }
        return null;
    }
    
    public String getLocalName(final int index) {
        if (index < this._length) {
            return this._names.elementAt(index);
        }
        return null;
    }
    
    public String getQName(final int pos) {
        if (pos < this._length) {
            return this._qnames.elementAt(pos);
        }
        return null;
    }
    
    public String getType(final int index) {
        return "CDATA";
    }
    
    public int getIndex(final String namespaceURI, final String localPart) {
        return -1;
    }
    
    public int getIndex(final String qname) {
        return -1;
    }
    
    public String getType(final String uri, final String localName) {
        return "CDATA";
    }
    
    public String getType(final String qname) {
        return "CDATA";
    }
    
    public String getValue(final int pos) {
        if (pos < this._length) {
            return this._values.elementAt(pos);
        }
        return null;
    }
    
    public String getValue(final String qname) {
        if (this._attributes == null) {
            return null;
        }
        final Integer obj = (Integer)this._attributes.get(qname);
        if (obj == null) {
            return null;
        }
        return this.getValue(obj);
    }
    
    public String getValue(final String uri, final String localName) {
        return this.getValue(uri + ':' + localName);
    }
    
    public void add(final String qname, final String value) {
        if (this._attributes == null) {
            this.alloc();
        }
        Integer obj = (Integer)this._attributes.get(qname);
        if (obj == null) {
            this._attributes.put(qname, obj = new Integer(this._length++));
            this._qnames.addElement(qname);
            this._values.addElement(value);
            final int col = qname.lastIndexOf(58);
            if (col > -1) {
                this._uris.addElement(qname.substring(0, col));
                this._names.addElement(qname.substring(col + 1));
            }
            else {
                this._uris.addElement("");
                this._names.addElement(qname);
            }
        }
        else {
            final int index = obj;
            this._values.set(index, value);
        }
    }
    
    public void clear() {
        this._length = 0;
        if (this._attributes != null) {
            this._attributes.clear();
            this._names.removeAllElements();
            this._values.removeAllElements();
            this._qnames.removeAllElements();
            this._uris.removeAllElements();
        }
    }
}
