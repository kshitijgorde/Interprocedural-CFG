// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import javax.xml.transform.TransformerException;

public class SystemIDResolver
{
    public static String getAbsoluteURI(String urlString, String base) throws TransformerException {
        if (urlString.indexOf(58) < 0 && base != null && base.indexOf(58) < 0) {
            base = getAbsoluteURIFromRelative(base);
        }
        if (base != null && urlString.startsWith("file:") && urlString.charAt(5) != '/') {
            if (base.equals(urlString)) {
                base = "";
            }
            else {
                urlString = urlString.substring(5);
            }
        }
        if (base != null && base.indexOf(92) > -1) {
            base = base.replace('\\', '/');
        }
        if (urlString != null && urlString.indexOf(92) > -1) {
            urlString = urlString.replace('\\', '/');
        }
        URI uri;
        try {
            if (base == null || base.length() == 0) {
                uri = new URI(urlString);
            }
            else {
                final URI baseURI = new URI(base);
                uri = new URI(baseURI, urlString);
            }
        }
        catch (URI.MalformedURIException mue) {
            throw new TransformerException(mue);
        }
        final String uriStr = uri.toString();
        return uriStr;
    }
    
    public static String getAbsoluteURIFromRelative(String uri) {
        final String curdir = System.getProperty("user.dir");
        if (curdir != null) {
            uri = "file:///" + curdir + System.getProperty("file.separator") + uri;
        }
        if (uri != null && uri.indexOf(92) > -1) {
            uri = uri.replace('\\', '/');
        }
        return uri;
    }
}
