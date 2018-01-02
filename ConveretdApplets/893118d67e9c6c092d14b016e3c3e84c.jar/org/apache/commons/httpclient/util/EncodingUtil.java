// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.util;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.NameValuePair;
import java.util.BitSet;

public class EncodingUtil
{
    private static final BitSet WWW_FORM_URL;
    
    static {
        WWW_FORM_URL = new BitSet(256);
        for (int i = 97; i <= 122; ++i) {
            EncodingUtil.WWW_FORM_URL.set(i);
        }
        for (int i = 65; i <= 90; ++i) {
            EncodingUtil.WWW_FORM_URL.set(i);
        }
        for (int i = 48; i <= 57; ++i) {
            EncodingUtil.WWW_FORM_URL.set(i);
        }
        EncodingUtil.WWW_FORM_URL.set(32);
        EncodingUtil.WWW_FORM_URL.set(45);
        EncodingUtil.WWW_FORM_URL.set(95);
        EncodingUtil.WWW_FORM_URL.set(46);
        EncodingUtil.WWW_FORM_URL.set(42);
    }
    
    public static String formUrlEncode(final NameValuePair[] pairs, final String charset) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pairs.length; ++i) {
            if (pairs[i].getName() != null) {
                if (i > 0) {
                    buf.append("&");
                }
                String queryName = pairs[i].getName();
                try {
                    queryName = URIUtil.encode(queryName, EncodingUtil.WWW_FORM_URL, charset).replace(' ', '+');
                }
                catch (URIException urie) {
                    LOG.error("Error encoding pair name: " + queryName, urie);
                }
                buf.append(queryName);
                buf.append("=");
                if (pairs[i].getValue() != null) {
                    String queryValue = pairs[i].getValue();
                    try {
                        queryValue = URIUtil.encode(queryValue, EncodingUtil.WWW_FORM_URL, charset).replace(' ', '+');
                    }
                    catch (URIException urie2) {
                        LOG.error("Error encoding pair value: " + queryValue, urie2);
                    }
                    buf.append(queryValue);
                }
            }
        }
        return buf.toString();
    }
}
