// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

final class Context2
{
    private static final Enumeration EMPTY_ENUMERATION;
    Hashtable prefixTable;
    Hashtable uriTable;
    Hashtable elementNameTable;
    Hashtable attributeNameTable;
    String defaultNS;
    private Vector declarations;
    private boolean tablesDirty;
    private Context2 parent;
    private Context2 child;
    
    Context2(final Context2 parent) {
        this.defaultNS = null;
        this.declarations = null;
        this.tablesDirty = false;
        this.parent = null;
        this.child = null;
        if (parent == null) {
            this.prefixTable = new Hashtable();
            this.uriTable = new Hashtable();
            this.elementNameTable = null;
            this.attributeNameTable = null;
        }
        else {
            this.setParent(parent);
        }
    }
    
    Context2 getChild() {
        return this.child;
    }
    
    Context2 getParent() {
        return this.parent;
    }
    
    void setParent(final Context2 parent) {
        this.parent = parent;
        parent.child = this;
        this.declarations = null;
        this.prefixTable = parent.prefixTable;
        this.uriTable = parent.uriTable;
        this.elementNameTable = parent.elementNameTable;
        this.attributeNameTable = parent.attributeNameTable;
        this.defaultNS = parent.defaultNS;
        this.tablesDirty = false;
    }
    
    void declarePrefix(String prefix, String uri) {
        if (!this.tablesDirty) {
            this.copyTables();
        }
        if (this.declarations == null) {
            this.declarations = new Vector();
        }
        prefix = prefix.intern();
        uri = uri.intern();
        if ("".equals(prefix)) {
            if ("".equals(uri)) {
                this.defaultNS = null;
            }
            else {
                this.defaultNS = uri;
            }
        }
        else {
            this.prefixTable.put(prefix, uri);
            this.uriTable.put(uri, prefix);
        }
        this.declarations.addElement(prefix);
    }
    
    String[] processName(final String qName, final boolean isAttribute) {
        Hashtable table;
        if (isAttribute) {
            if (this.elementNameTable == null) {
                this.elementNameTable = new Hashtable();
            }
            table = this.elementNameTable;
        }
        else {
            if (this.attributeNameTable == null) {
                this.attributeNameTable = new Hashtable();
            }
            table = this.attributeNameTable;
        }
        String[] name = table.get(qName);
        if (name != null) {
            return name;
        }
        name = new String[3];
        final int index = qName.indexOf(58);
        if (index == -1) {
            if (isAttribute || this.defaultNS == null) {
                name[0] = "";
            }
            else {
                name[0] = this.defaultNS;
            }
            name[2] = (name[1] = qName.intern());
        }
        else {
            final String prefix = qName.substring(0, index);
            final String local = qName.substring(index + 1);
            String uri;
            if ("".equals(prefix)) {
                uri = this.defaultNS;
            }
            else {
                uri = this.prefixTable.get(prefix);
            }
            if (uri == null) {
                return null;
            }
            name[0] = uri;
            name[1] = local.intern();
            name[2] = qName.intern();
        }
        table.put(name[2], name);
        this.tablesDirty = true;
        return name;
    }
    
    String getURI(final String prefix) {
        if ("".equals(prefix)) {
            return this.defaultNS;
        }
        if (this.prefixTable == null) {
            return null;
        }
        return this.prefixTable.get(prefix);
    }
    
    String getPrefix(final String uri) {
        if (this.uriTable == null) {
            return null;
        }
        return this.uriTable.get(uri);
    }
    
    Enumeration getDeclaredPrefixes() {
        if (this.declarations == null) {
            return Context2.EMPTY_ENUMERATION;
        }
        return this.declarations.elements();
    }
    
    Enumeration getPrefixes() {
        if (this.prefixTable == null) {
            return Context2.EMPTY_ENUMERATION;
        }
        return this.prefixTable.keys();
    }
    
    private void copyTables() {
        this.prefixTable = (Hashtable)this.prefixTable.clone();
        this.uriTable = (Hashtable)this.uriTable.clone();
        if (this.elementNameTable != null) {
            this.elementNameTable = new Hashtable();
        }
        if (this.attributeNameTable != null) {
            this.attributeNameTable = new Hashtable();
        }
        this.tablesDirty = true;
    }
    
    static {
        EMPTY_ENUMERATION = new Vector().elements();
    }
}
