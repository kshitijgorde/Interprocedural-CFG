// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.XMLString;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPathContext;
import java.util.Vector;

public class KeyManager
{
    private transient Vector m_key_tables;
    
    public KeyManager() {
        this.m_key_tables = null;
    }
    
    public XNodeSet getNodeSetDTMByKey(final XPathContext xctxt, final int doc, final QName name, final XMLString ref, final PrefixResolver nscontext) throws TransformerException {
        XNodeSet nl = null;
        final ElemTemplateElement template = (ElemTemplateElement)nscontext;
        if (null != template && null != template.getStylesheetRoot().getKeysComposed()) {
            boolean foundDoc = false;
            if (null == this.m_key_tables) {
                this.m_key_tables = new Vector(4);
            }
            else {
                for (int nKeyTables = this.m_key_tables.size(), i = 0; i < nKeyTables; ++i) {
                    final KeyTable kt = this.m_key_tables.elementAt(i);
                    if (kt.getKeyTableName().equals(name) && doc == kt.getDocKey()) {
                        nl = kt.getNodeSetDTMByKey(name, ref);
                        if (nl != null) {
                            foundDoc = true;
                            break;
                        }
                    }
                }
            }
            if (null == nl && !foundDoc) {
                final KeyTable kt2 = new KeyTable(doc, nscontext, name, template.getStylesheetRoot().getKeysComposed(), xctxt);
                this.m_key_tables.addElement(kt2);
                if (doc == kt2.getDocKey()) {
                    foundDoc = true;
                    nl = kt2.getNodeSetDTMByKey(name, ref);
                }
            }
        }
        return nl;
    }
}
