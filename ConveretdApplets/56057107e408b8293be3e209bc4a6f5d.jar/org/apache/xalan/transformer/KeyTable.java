// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.axes.LocPathIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.PrefixResolver;
import java.util.Hashtable;
import org.w3c.dom.Node;

public class KeyTable
{
    private Node m_docKey;
    private KeyIterator m_keyIter;
    private Hashtable m_defsTable;
    
    public KeyTable(final Node doc, final PrefixResolver nscontext, final QName name, final Vector keyDeclarations, final XPathContext xmlLiaison) throws TransformerException {
        this.m_docKey = doc;
        (this.m_keyIter = new KeyIterator(doc, nscontext, name, keyDeclarations, xmlLiaison)).setKeyTable(this);
    }
    
    void addRefNode(final String ref, final Node node) {
        KeyRefIterator kiRef = null;
        Hashtable refsTable = null;
        if (this.m_defsTable != null) {
            refsTable = this.m_defsTable.get(this.getKeyTableName());
            if (refsTable != null) {
                final Object kiObj = refsTable.get(ref);
                if (kiObj != null) {
                    kiRef = (KeyRefIterator)kiObj;
                }
            }
        }
        if (kiRef == null) {
            if (this.m_defsTable == null) {
                this.m_defsTable = new Hashtable();
            }
            if (refsTable == null) {
                refsTable = new Hashtable();
                this.m_defsTable.put(this.getKeyTableName(), refsTable);
            }
            kiRef = new KeyRefIterator(ref, this.m_keyIter);
            refsTable.put(ref, kiRef);
        }
        kiRef.addNode(node);
    }
    
    public Node getDocKey() {
        return this.m_docKey;
    }
    
    public QName getKeyTableName() {
        return this.m_keyIter.getName();
    }
    
    public LocPathIterator getNodeSetByKey(final QName name, final String ref) {
        Hashtable refsTable = null;
        if (this.m_defsTable != null) {
            refsTable = this.m_defsTable.get(name);
            if (refsTable != null) {
                final Object kiObj = refsTable.get(ref);
                if (kiObj != null) {
                    try {
                        final KeyRefIterator kiRef = (KeyRefIterator)((KeyRefIterator)kiObj).clone();
                        return kiRef;
                    }
                    catch (CloneNotSupportedException ex) {
                        final KeyIterator ki = null;
                    }
                }
            }
        }
        if (this.m_defsTable == null) {
            this.m_defsTable = new Hashtable();
        }
        if (refsTable == null) {
            refsTable = new Hashtable();
        }
        if (this.m_keyIter.getFirstWalker().getRoot() == null) {
            this.m_keyIter.setLookupKey(ref);
        }
        else {
            ((KeyWalker)this.m_keyIter.getFirstWalker()).m_lookupKey = ref;
        }
        final KeyRefIterator kiRef = new KeyRefIterator(ref, this.m_keyIter);
        refsTable.put(ref, kiRef);
        this.m_defsTable.put(name, refsTable);
        return kiRef;
    }
}
