// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.tools;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Source;
import java.net.URL;
import org.xml.sax.InputSource;
import java.io.IOException;
import java.net.MalformedURLException;
import com.ibm.xml.resolver.CatalogManager;
import com.ibm.xml.resolver.Catalog;
import javax.xml.transform.URIResolver;
import org.xml.sax.EntityResolver;

public class CatalogResolver implements EntityResolver, URIResolver
{
    public boolean namespaceAware;
    public boolean validating;
    private Catalog catalog;
    private CatalogManager catalogManager;
    
    public CatalogResolver() {
        this.namespaceAware = true;
        this.validating = false;
        this.catalog = null;
        this.catalogManager = CatalogManager.getStaticManager();
        this.initializeCatalogs(false);
    }
    
    public CatalogResolver(final boolean b) {
        this.namespaceAware = true;
        this.validating = false;
        this.catalog = null;
        this.catalogManager = CatalogManager.getStaticManager();
        this.initializeCatalogs(b);
    }
    
    public CatalogResolver(final CatalogManager catalogManager) {
        this.namespaceAware = true;
        this.validating = false;
        this.catalog = null;
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogManager = catalogManager;
        this.initializeCatalogs(!this.catalogManager.getUseStaticCatalog());
    }
    
    private void initializeCatalogs(final boolean b) {
        this.catalog = this.catalogManager.getCatalog();
    }
    
    public Catalog getCatalog() {
        return this.catalog;
    }
    
    public String getResolvedEntity(final String s, final String s2) {
        String s3 = null;
        if (this.catalog == null) {
            this.catalogManager.debug.message(1, "Catalog resolution attempted with null catalog; ignored");
            return null;
        }
        if (s2 != null) {
            try {
                s3 = this.catalog.resolveSystem(s2);
            }
            catch (MalformedURLException ex) {
                this.catalogManager.debug.message(1, "Malformed URL exception trying to resolve", s);
                s3 = null;
            }
            catch (IOException ex2) {
                this.catalogManager.debug.message(1, "I/O exception trying to resolve", s);
                s3 = null;
            }
        }
        if (s3 == null) {
            if (s != null) {
                try {
                    s3 = this.catalog.resolvePublic(s, s2);
                }
                catch (MalformedURLException ex3) {
                    this.catalogManager.debug.message(1, "Malformed URL exception trying to resolve", s);
                }
                catch (IOException ex4) {
                    this.catalogManager.debug.message(1, "I/O exception trying to resolve", s);
                }
            }
            if (s3 != null) {
                this.catalogManager.debug.message(2, "Resolved public", s, s3);
            }
        }
        else {
            this.catalogManager.debug.message(2, "Resolved system", s2, s3);
        }
        return s3;
    }
    
    public InputSource resolveEntity(final String publicId, final String s) {
        final String resolvedEntity = this.getResolvedEntity(publicId, s);
        if (resolvedEntity != null) {
            try {
                final InputSource inputSource = new InputSource(resolvedEntity);
                inputSource.setPublicId(publicId);
                inputSource.setByteStream(new URL(resolvedEntity).openStream());
                return inputSource;
            }
            catch (Exception ex) {
                this.catalogManager.debug.message(1, "Failed to create InputSource", resolvedEntity);
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
        try {
            s3 = this.catalog.resolveURI(s);
        }
        catch (Exception ex2) {}
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
        this.catalogManager.debug.message(2, "Resolved URI", s, s3);
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
