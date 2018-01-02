// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.StringTokenizer;
import java.util.Vector;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementation;

public class DOMLSImplementationSourceImpl extends DOMImplementationSourceImpl
{
    public DOMImplementation getDOMImplementation(final String s) {
        final DOMImplementation domImplementation = DOMLSImplementationImpl.getDOMImplementation();
        if (this.testImpl(domImplementation, s)) {
            return domImplementation;
        }
        final DOMImplementation domImplementation2 = super.getDOMImplementation(s);
        if (domImplementation2 != null) {
            return domImplementation2;
        }
        return null;
    }
    
    public DOMImplementationList getDOMImplementationList(final String s) {
        final Vector<DOMImplementation> vector = new Vector<DOMImplementation>();
        final DOMImplementation domImplementation = DOMLSImplementationImpl.getDOMImplementation();
        if (this.testImpl(domImplementation, s)) {
            vector.addElement(domImplementation);
        }
        final DOMImplementationList domImplementationList = super.getDOMImplementationList(s);
        for (int i = 0; i < domImplementationList.getLength(); ++i) {
            vector.addElement(domImplementationList.item(i));
        }
        return new DOMImplementationListImpl(vector);
    }
    
    boolean testImpl(final DOMImplementation domImplementation, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        String s2 = null;
        if (stringTokenizer.hasMoreTokens()) {
            s2 = stringTokenizer.nextToken();
        }
        while (s2 != null) {
            boolean b = false;
            String nextToken;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
                switch (nextToken.charAt(0)) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        b = true;
                        break;
                    }
                }
            }
            else {
                nextToken = null;
            }
            if (b) {
                if (!domImplementation.hasFeature(s2, nextToken)) {
                    return false;
                }
                if (stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                }
                else {
                    s2 = null;
                }
            }
            else {
                if (!domImplementation.hasFeature(s2, null)) {
                    return false;
                }
                s2 = nextToken;
            }
        }
        return true;
    }
}
