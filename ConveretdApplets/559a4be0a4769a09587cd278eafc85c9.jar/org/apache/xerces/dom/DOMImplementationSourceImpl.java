// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.StringTokenizer;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.dom3.DOMImplementationSource;

public class DOMImplementationSourceImpl implements DOMImplementationSource
{
    public DOMImplementation getDOMImplementation(final String features) {
        DOMImplementation impl = CoreDOMImplementationImpl.getDOMImplementation();
        if (this.testImpl(impl, features)) {
            return impl;
        }
        impl = DOMImplementationImpl.getDOMImplementation();
        if (this.testImpl(impl, features)) {
            return impl;
        }
        impl = PSVIDOMImplementationImpl.getDOMImplementation();
        if (this.testImpl(impl, features)) {
            return impl;
        }
        return null;
    }
    
    boolean testImpl(final DOMImplementation impl, final String features) {
        final StringTokenizer st = new StringTokenizer(features);
        String feature = null;
        String version = null;
        if (st.hasMoreTokens()) {
            feature = st.nextToken();
        }
        while (feature != null) {
            boolean isVersion = false;
            if (st.hasMoreTokens()) {
                version = st.nextToken();
                final char c = version.charAt(0);
                switch (c) {
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
                        isVersion = true;
                        break;
                    }
                }
            }
            else {
                version = null;
            }
            if (isVersion) {
                if (!impl.hasFeature(feature, version)) {
                    return false;
                }
                if (st.hasMoreTokens()) {
                    feature = st.nextToken();
                }
                else {
                    feature = null;
                }
            }
            else {
                if (!impl.hasFeature(feature, null)) {
                    return false;
                }
                feature = version;
            }
        }
        return true;
    }
}
