// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.helpers;

import javax.xml.transform.sax.SAXSource;
import java.net.MalformedURLException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Source;
import org.xml.sax.InputSource;
import java.net.URL;
import java.util.Hashtable;
import javax.xml.transform.URIResolver;
import org.xml.sax.EntityResolver;

public class BootstrapResolver implements EntityResolver, URIResolver
{
    public static final String xmlCatalogXSD = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.xsd";
    public static final String xmlCatalogRNG = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.rng";
    public static final String xmlCatalogPubId = "-//OASIS//DTD XML Catalogs V1.0//EN";
    public static final String xmlCatalogSysId = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd";
    private Hashtable publicMap;
    private Hashtable systemMap;
    private Hashtable uriMap;
    
    public BootstrapResolver() {
        this.publicMap = new Hashtable();
        this.systemMap = new Hashtable();
        this.uriMap = new Hashtable();
        final URL resource = this.getClass().getResource("/org/apache/xml/resolver/etc/catalog.dtd");
        if (resource != null) {
            this.publicMap.put("-//OASIS//DTD XML Catalogs V1.0//EN", resource.toString());
            this.systemMap.put("http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd", resource.toString());
        }
        final URL resource2 = this.getClass().getResource("/org/apache/xml/resolver/etc/catalog.rng");
        if (resource2 != null) {
            this.uriMap.put("http://www.oasis-open.org/committees/entity/release/1.0/catalog.rng", resource2.toString());
        }
        final URL resource3 = this.getClass().getResource("/org/apache/xml/resolver/etc/catalog.xsd");
        if (resource3 != null) {
            this.uriMap.put("http://www.oasis-open.org/committees/entity/release/1.0/catalog.xsd", resource3.toString());
        }
    }
    
    public InputSource resolveEntity(final String publicId, final String s) {
        String s2 = null;
        if (s != null && this.systemMap.containsKey(s)) {
            s2 = this.systemMap.get(s);
        }
        else if (publicId != null && this.publicMap.containsKey(publicId)) {
            s2 = this.publicMap.get(publicId);
        }
        if (s2 != null) {
            try {
                final InputSource inputSource = new InputSource(s2);
                inputSource.setPublicId(publicId);
                inputSource.setByteStream(new URL(s2).openStream());
                return inputSource;
            }
            catch (Exception ex) {
                return null;
            }
        }
        return null;
    }
    
    public Source resolve(final String s, final String s2) throws TransformerException {
        String substring = s;
        final int index = s.indexOf("#");
        if (index >= 0) {
            substring = s.substring(0, index);
            s.substring(index + 1);
        }
        String s3 = null;
        if (s != null && this.uriMap.containsKey(s)) {
            s3 = this.uriMap.get(s);
        }
        if (s3 == null) {
            try {
                if (s2 == null) {
                    s3 = new URL(substring).toString();
                }
                else {
                    final URL url = new URL(s2);
                    s3 = ((s.length() == 0) ? url : new URL(url, substring)).toString();
                }
            }
            catch (MalformedURLException ex) {
                final String absolute = this.makeAbsolute(s2);
                if (!absolute.equals(s2)) {
                    return this.resolve(s, absolute);
                }
                throw new TransformerException("Malformed URL " + s + "(base " + s2 + ")", ex);
            }
        }
        final SAXSource saxSource = new SAXSource();
        saxSource.setInputSource(new InputSource(s3));
        return saxSource;
    }
    
    private String makeAbsolute(String s) {
        if (s == null) {
            s = "";
        }
        try {
            return new URL(s).toString();
        }
        catch (MalformedURLException ex) {
            final String property = System.getProperty("user.dir");
            String s2;
            if (property.endsWith("/")) {
                s2 = "file://" + property + s;
            }
            else {
                s2 = "file://" + property + "/" + s;
            }
            try {
                return new URL(s2).toString();
            }
            catch (MalformedURLException ex2) {
                return s;
            }
        }
    }
}
