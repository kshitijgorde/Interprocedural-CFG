// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import java.util.Vector;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;

public class XMLElementDecl
{
    public static final int TYPE_EMPTY = 0;
    public static final int TYPE_ANY = 1;
    public static final int TYPE_MIXED = 2;
    public static final int TYPE_CHILDREN = 3;
    public static final int TYPE_SIMPLE = 4;
    public final QName name;
    public int type;
    public boolean list;
    public DatatypeValidator datatypeValidator;
    public int contentSpecIndex;
    public int enclosingScope;
    public final Vector unique;
    public final Vector key;
    public final Vector keyRef;
    
    public XMLElementDecl() {
        this.name = new QName();
        this.unique = new Vector();
        this.key = new Vector();
        this.keyRef = new Vector();
        this.clear();
    }
    
    public XMLElementDecl(final XMLElementDecl values) {
        this.name = new QName();
        this.unique = new Vector();
        this.key = new Vector();
        this.keyRef = new Vector();
        this.setValues(values);
    }
    
    public void clear() {
        this.name.clear();
        this.type = -1;
        this.list = false;
        this.datatypeValidator = null;
        this.contentSpecIndex = -1;
        this.enclosingScope = -1;
        this.unique.removeAllElements();
        this.key.removeAllElements();
        this.keyRef.removeAllElements();
    }
    
    public void setValues(final XMLElementDecl xmlElementDecl) {
        this.name.setValues(xmlElementDecl.name);
        this.type = xmlElementDecl.type;
        this.list = xmlElementDecl.list;
        this.datatypeValidator = xmlElementDecl.datatypeValidator;
        this.contentSpecIndex = xmlElementDecl.contentSpecIndex;
        this.enclosingScope = xmlElementDecl.enclosingScope;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean equals(final Object o) {
        return super.equals(o);
    }
}
