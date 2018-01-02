// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.KeyManager;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.axes.LocPathIterator;
import org.w3c.dom.Node;
import java.util.Hashtable;
import org.apache.xpath.DOMHelper;
import org.apache.xpath.axes.UnionPathIterator;
import org.apache.xml.utils.QName;
import org.w3c.dom.Document;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.Function2Args;

public class FuncKey extends Function2Args
{
    private static Boolean ISTRUE;
    
    static {
        FuncKey.ISTRUE = new Boolean(true);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final TransformerImpl transformer = (TransformerImpl)xctxt.getOwnerObject();
        XNodeSet nodes = null;
        final Node context = xctxt.getCurrentNode();
        final Document docContext = (Document)((context.getNodeType() == 9) ? context : context.getOwnerDocument());
        final String xkeyname = this.getArg0().execute(xctxt).str();
        final QName keyname = new QName(xkeyname, xctxt.getNamespaceContext());
        final XObject arg = this.getArg1().execute(xctxt);
        final boolean argIsNodeSet = arg.getType() == 4;
        final KeyManager kmgr = transformer.getKeyManager();
        if (argIsNodeSet) {
            Hashtable usedrefs = null;
            final NodeIterator ni = arg.nodeset();
            final UnionPathIterator upi = new UnionPathIterator();
            Node pos;
            while ((pos = ni.nextNode()) != null) {
                final String ref = DOMHelper.getNodeData(pos);
                if (ref != null) {
                    if (usedrefs == null) {
                        usedrefs = new Hashtable();
                    }
                    if (usedrefs.get(ref) != null) {
                        continue;
                    }
                    usedrefs.put(ref, FuncKey.ISTRUE);
                    final LocPathIterator nl = kmgr.getNodeSetByKey(xctxt, docContext, keyname, ref, xctxt.getNamespaceContext());
                    try {
                        upi.addIterator((LocPathIterator)nl.clone());
                    }
                    catch (CloneNotSupportedException ex) {}
                }
            }
            upi.initContext(xctxt);
            nodes = new XNodeSet(upi);
        }
        else {
            final String ref2 = arg.str();
            final LocPathIterator nl2 = kmgr.getNodeSetByKey(xctxt, docContext, keyname, ref2, xctxt.getNamespaceContext());
            try {
                nodes = new XNodeSet(nl2.cloneWithReset());
            }
            catch (CloneNotSupportedException ex2) {}
        }
        return nodes;
    }
}
