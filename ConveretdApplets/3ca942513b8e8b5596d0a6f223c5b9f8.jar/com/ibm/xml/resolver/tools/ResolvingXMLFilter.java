// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.tools;

import java.net.MalformedURLException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import com.ibm.xml.resolver.Catalog;
import org.xml.sax.XMLReader;
import java.net.URL;
import com.ibm.xml.resolver.CatalogManager;
import org.xml.sax.helpers.XMLFilterImpl;

public class ResolvingXMLFilter extends XMLFilterImpl
{
    public static boolean suppressExplanation;
    private CatalogManager catalogManager;
    private CatalogResolver catalogResolver;
    private CatalogResolver piCatalogResolver;
    private boolean allowXMLCatalogPI;
    private boolean oasisXMLCatalogPI;
    private URL baseURL;
    
    public ResolvingXMLFilter() {
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }
    
    public ResolvingXMLFilter(final XMLReader xmlReader) {
        super(xmlReader);
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }
    
    public ResolvingXMLFilter(final CatalogManager catalogManager) {
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogManager = catalogManager;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }
    
    public ResolvingXMLFilter(final XMLReader xmlReader, final CatalogManager catalogManager) {
        super(xmlReader);
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogManager = catalogManager;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }
    
    public Catalog getCatalog() {
        return this.catalogResolver.getCatalog();
    }
    
    public void parse(final InputSource inputSource) throws IOException, SAXException {
        this.allowXMLCatalogPI = true;
        this.setupBaseURI(inputSource.getSystemId());
        try {
            super.parse(inputSource);
        }
        catch (InternalError internalError) {
            this.explain(inputSource.getSystemId());
            throw internalError;
        }
    }
    
    public void parse(final String s) throws IOException, SAXException {
        this.allowXMLCatalogPI = true;
        this.setupBaseURI(s);
        try {
            super.parse(s);
        }
        catch (InternalError internalError) {
            this.explain(s);
            throw internalError;
        }
    }
    
    public InputSource resolveEntity(final String publicId, final String s) {
        this.allowXMLCatalogPI = false;
        String s2 = this.catalogResolver.getResolvedEntity(publicId, s);
        if (s2 == null && this.piCatalogResolver != null) {
            s2 = this.piCatalogResolver.getResolvedEntity(publicId, s);
        }
        if (s2 != null) {
            try {
                final InputSource inputSource = new InputSource(s2);
                inputSource.setPublicId(publicId);
                inputSource.setByteStream(new URL(s2).openStream());
                return inputSource;
            }
            catch (Exception ex) {
                this.catalogManager.debug.message(1, "Failed to create InputSource", s2);
                return null;
            }
        }
        return null;
    }
    
    public void notationDecl(final String s, final String s2, final String s3) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.notationDecl(s, s2, s3);
    }
    
    public void unparsedEntityDecl(final String s, final String s2, final String s3, final String s4) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.unparsedEntityDecl(s, s2, s3, s4);
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.startElement(s, s2, s3, attributes);
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        if (s.equals("oasis-xml-catalog")) {
            URL url = null;
            final int index = s2.indexOf("catalog=");
            if (index >= 0) {
                final String substring = s2.substring(index + 8);
                if (substring.length() > 1) {
                    final String substring2 = substring.substring(0, 1);
                    final String substring3 = substring.substring(1);
                    final int index2 = substring3.indexOf(substring2);
                    if (index2 >= 0) {
                        final String substring4 = substring3.substring(0, index2);
                        try {
                            if (this.baseURL != null) {
                                url = new URL(this.baseURL, substring4);
                            }
                            else {
                                url = new URL(substring4);
                            }
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
            }
            if (this.allowXMLCatalogPI) {
                if (this.catalogManager.allowOasisXMLCatalogPI()) {
                    this.catalogManager.debug.message(4, "oasis-xml-catalog PI", s2);
                    if (url != null) {
                        try {
                            this.catalogManager.debug.message(4, "oasis-xml-catalog", url.toString());
                            this.oasisXMLCatalogPI = true;
                            if (this.piCatalogResolver == null) {
                                this.piCatalogResolver = new CatalogResolver(true);
                            }
                            this.piCatalogResolver.getCatalog().parseCatalog(url.toString());
                        }
                        catch (Exception ex2) {
                            this.catalogManager.debug.message(3, "Exception parsing oasis-xml-catalog: " + url.toString());
                        }
                    }
                    else {
                        this.catalogManager.debug.message(3, "PI oasis-xml-catalog unparseable: " + s2);
                    }
                }
                else {
                    this.catalogManager.debug.message(4, "PI oasis-xml-catalog ignored: " + s2);
                }
            }
            else {
                this.catalogManager.debug.message(3, "PI oasis-xml-catalog occurred in an invalid place: " + s2);
            }
        }
        else {
            super.processingInstruction(s, s2);
        }
    }
    
    private void setupBaseURI(final String s) {
        final String property = System.getProperty("user.dir");
        property.replace('\\', '/');
        URL url;
        try {
            url = new URL("file:///" + property + "/basename");
        }
        catch (MalformedURLException ex) {
            url = null;
        }
        try {
            this.baseURL = new URL(s);
        }
        catch (MalformedURLException ex2) {
            if (url != null) {
                try {
                    this.baseURL = new URL(url, s);
                }
                catch (MalformedURLException ex3) {
                    this.baseURL = null;
                }
            }
            else {
                this.baseURL = null;
            }
        }
    }
    
    private void explain(final String s) {
        if (!ResolvingXMLFilter.suppressExplanation) {
            System.out.println("XMLReader probably encountered bad URI in " + s);
            System.out.println("For example, replace '/some/uri' with 'file:/some/uri'.");
        }
        ResolvingXMLFilter.suppressExplanation = true;
    }
    
    static {
        ResolvingXMLFilter.suppressExplanation = false;
    }
}
