// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.XPath;
import org.apache.xpath.XPathContext;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.KeyDeclaration;
import java.util.Vector;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.OneStepIteratorForward;

public class KeyIterator extends OneStepIteratorForward
{
    static final long serialVersionUID = -1349109910100249661L;
    private QName m_name;
    private Vector m_keyDeclarations;
    
    public QName getName() {
        return this.m_name;
    }
    
    public Vector getKeyDeclarations() {
        return this.m_keyDeclarations;
    }
    
    KeyIterator(final QName name, final Vector keyDeclarations) {
        super(16);
        this.m_keyDeclarations = keyDeclarations;
        this.m_name = name;
    }
    
    public short acceptNode(final int testNode) {
        boolean foundKey = false;
        final KeyIterator ki = (KeyIterator)super.m_lpi;
        final XPathContext xctxt = ki.getXPathContext();
        final Vector keys = ki.getKeyDeclarations();
        final QName name = ki.getName();
        try {
            for (int nDeclarations = keys.size(), i = 0; i < nDeclarations; ++i) {
                final KeyDeclaration kd = keys.elementAt(i);
                if (kd.getName().equals(name)) {
                    foundKey = true;
                    final XPath matchExpr = kd.getMatch();
                    final double matchScore;
                    final double score = matchScore = matchExpr.getMatchScore(xctxt, testNode);
                    kd.getMatch();
                    if (matchScore != Double.NEGATIVE_INFINITY) {
                        return 1;
                    }
                }
            }
        }
        catch (TransformerException ex) {}
        if (!foundKey) {
            throw new RuntimeException(XSLMessages.createMessage("ER_NO_XSLKEY_DECLARATION", new Object[] { name.getLocalName() }));
        }
        return 2;
    }
}
