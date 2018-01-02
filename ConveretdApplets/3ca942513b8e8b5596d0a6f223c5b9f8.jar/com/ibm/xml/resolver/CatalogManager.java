// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver;

import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.InputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import com.ibm.xml.resolver.helpers.Debug;
import java.net.URL;
import java.util.ResourceBundle;
import com.ibm.xml.resolver.helpers.BootstrapResolver;

public class CatalogManager
{
    private static String pFiles;
    private static String pVerbosity;
    private static String pPrefer;
    private static String pStatic;
    private static String pAllowPI;
    private static String pClassname;
    private static String pIgnoreMissing;
    private static CatalogManager staticManager;
    private BootstrapResolver bResolver;
    private boolean ignoreMissingProperties;
    private ResourceBundle resources;
    private String propertyFile;
    private URL propertyFileURI;
    private String defaultCatalogFiles;
    private String catalogFiles;
    private boolean fromPropertiesFile;
    private int defaultVerbosity;
    private Integer verbosity;
    private boolean defaultPreferPublic;
    private Boolean preferPublic;
    private boolean defaultUseStaticCatalog;
    private Boolean useStaticCatalog;
    private static Catalog staticCatalog;
    private boolean defaultOasisXMLCatalogPI;
    private Boolean oasisXMLCatalogPI;
    private boolean defaultRelativeCatalogs;
    private Boolean relativeCatalogs;
    private String catalogClassName;
    public Debug debug;
    static /* synthetic */ Class class$com$ibm$xml$resolver$CatalogManager;
    
    public CatalogManager() {
        this.bResolver = new BootstrapResolver();
        this.ignoreMissingProperties = (System.getProperty(CatalogManager.pIgnoreMissing) != null || System.getProperty(CatalogManager.pFiles) != null);
        this.propertyFile = "CatalogManager.properties";
        this.propertyFileURI = null;
        this.defaultCatalogFiles = "./xcatalog";
        this.catalogFiles = null;
        this.fromPropertiesFile = false;
        this.defaultVerbosity = 1;
        this.verbosity = null;
        this.defaultPreferPublic = true;
        this.preferPublic = null;
        this.defaultUseStaticCatalog = true;
        this.useStaticCatalog = null;
        this.defaultOasisXMLCatalogPI = true;
        this.oasisXMLCatalogPI = null;
        this.defaultRelativeCatalogs = true;
        this.relativeCatalogs = null;
        this.catalogClassName = null;
        this.debug = null;
        this.debug = new Debug();
    }
    
    public CatalogManager(final String propertyFile) {
        this.bResolver = new BootstrapResolver();
        this.ignoreMissingProperties = (System.getProperty(CatalogManager.pIgnoreMissing) != null || System.getProperty(CatalogManager.pFiles) != null);
        this.propertyFile = "CatalogManager.properties";
        this.propertyFileURI = null;
        this.defaultCatalogFiles = "./xcatalog";
        this.catalogFiles = null;
        this.fromPropertiesFile = false;
        this.defaultVerbosity = 1;
        this.verbosity = null;
        this.defaultPreferPublic = true;
        this.preferPublic = null;
        this.defaultUseStaticCatalog = true;
        this.useStaticCatalog = null;
        this.defaultOasisXMLCatalogPI = true;
        this.oasisXMLCatalogPI = null;
        this.defaultRelativeCatalogs = true;
        this.relativeCatalogs = null;
        this.catalogClassName = null;
        this.debug = null;
        this.propertyFile = propertyFile;
        this.debug = new Debug();
    }
    
    public void setBootstrapResolver(final BootstrapResolver bResolver) {
        this.bResolver = bResolver;
    }
    
    public BootstrapResolver getBootstrapResolver() {
        return this.bResolver;
    }
    
    private synchronized void readProperties() {
        try {
            this.propertyFileURI = ((CatalogManager.class$com$ibm$xml$resolver$CatalogManager == null) ? (CatalogManager.class$com$ibm$xml$resolver$CatalogManager = class$("com.ibm.xml.resolver.CatalogManager")) : CatalogManager.class$com$ibm$xml$resolver$CatalogManager).getResource("/" + this.propertyFile);
            final InputStream resourceAsStream = ((CatalogManager.class$com$ibm$xml$resolver$CatalogManager == null) ? (CatalogManager.class$com$ibm$xml$resolver$CatalogManager = class$("com.ibm.xml.resolver.CatalogManager")) : CatalogManager.class$com$ibm$xml$resolver$CatalogManager).getResourceAsStream("/" + this.propertyFile);
            if (resourceAsStream == null) {
                if (!this.ignoreMissingProperties) {
                    System.err.println("Cannot find " + this.propertyFile);
                    this.ignoreMissingProperties = true;
                }
                return;
            }
            this.resources = new PropertyResourceBundle(resourceAsStream);
        }
        catch (MissingResourceException ex) {
            if (!this.ignoreMissingProperties) {
                System.err.println("Cannot read " + this.propertyFile);
            }
        }
        catch (IOException ex2) {
            if (!this.ignoreMissingProperties) {
                System.err.println("Failure trying to read " + this.propertyFile);
            }
        }
        if (this.verbosity == null) {
            try {
                final int int1 = Integer.parseInt(this.resources.getString("verbosity").trim());
                this.debug.setDebug(int1);
                this.verbosity = new Integer(int1);
            }
            catch (Exception ex3) {}
        }
    }
    
    public static CatalogManager getStaticManager() {
        return CatalogManager.staticManager;
    }
    
    public boolean getIgnoreMissingProperties() {
        return this.ignoreMissingProperties;
    }
    
    public void setIgnoreMissingProperties(final boolean ignoreMissingProperties) {
        this.ignoreMissingProperties = ignoreMissingProperties;
    }
    
    public void ignoreMissingProperties(final boolean ignoreMissingProperties) {
        this.setIgnoreMissingProperties(ignoreMissingProperties);
    }
    
    private int queryVerbosity() {
        String s = System.getProperty(CatalogManager.pVerbosity);
        if (s == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources == null) {
                return this.defaultVerbosity;
            }
            try {
                s = this.resources.getString("verbosity");
            }
            catch (MissingResourceException ex) {
                return this.defaultVerbosity;
            }
        }
        try {
            return Integer.parseInt(s.trim());
        }
        catch (Exception ex2) {
            System.err.println("Cannot parse verbosity: \"" + s + "\"");
            return this.defaultVerbosity;
        }
    }
    
    public int getVerbosity() {
        if (this.verbosity == null) {
            this.verbosity = new Integer(this.queryVerbosity());
        }
        return this.verbosity;
    }
    
    public void setVerbosity(final int debug) {
        this.verbosity = new Integer(debug);
        this.debug.setDebug(debug);
    }
    
    public int verbosity() {
        return this.getVerbosity();
    }
    
    private boolean queryRelativeCatalogs() {
        if (this.resources == null) {
            this.readProperties();
        }
        if (this.resources == null) {
            return this.defaultRelativeCatalogs;
        }
        try {
            final String string = this.resources.getString("relative-catalogs");
            return string.equalsIgnoreCase("true") || string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("1");
        }
        catch (MissingResourceException ex) {
            return this.defaultRelativeCatalogs;
        }
    }
    
    public boolean getRelativeCatalogs() {
        if (this.relativeCatalogs == null) {
            this.relativeCatalogs = new Boolean(this.queryRelativeCatalogs());
        }
        return this.relativeCatalogs;
    }
    
    public void setRelativeCatalogs(final boolean b) {
        this.relativeCatalogs = new Boolean(b);
    }
    
    public boolean relativeCatalogs() {
        return this.getRelativeCatalogs();
    }
    
    private String queryCatalogFiles() {
        String s = System.getProperty(CatalogManager.pFiles);
        this.fromPropertiesFile = false;
        if (s == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources != null) {
                try {
                    s = this.resources.getString("catalogs");
                    this.fromPropertiesFile = true;
                }
                catch (MissingResourceException ex) {
                    System.err.println(this.propertyFile + ": catalogs not found.");
                    s = null;
                }
            }
        }
        if (s == null) {
            s = this.defaultCatalogFiles;
        }
        return s;
    }
    
    public Vector getCatalogFiles() {
        if (this.catalogFiles == null) {
            this.catalogFiles = this.queryCatalogFiles();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.catalogFiles, ";");
        final Vector<String> vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken();
            if (this.fromPropertiesFile && !this.relativeCatalogs()) {
                try {
                    s = new URL(this.propertyFileURI, s).toString();
                }
                catch (MalformedURLException ex) {}
            }
            vector.add(s);
        }
        return vector;
    }
    
    public void setCatalogFiles(final String catalogFiles) {
        this.catalogFiles = catalogFiles;
        this.fromPropertiesFile = false;
    }
    
    public Vector catalogFiles() {
        return this.getCatalogFiles();
    }
    
    private boolean queryPreferPublic() {
        String s = System.getProperty(CatalogManager.pPrefer);
        if (s == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources == null) {
                return this.defaultPreferPublic;
            }
            try {
                s = this.resources.getString("prefer");
            }
            catch (MissingResourceException ex) {
                return this.defaultPreferPublic;
            }
        }
        if (s == null) {
            return this.defaultPreferPublic;
        }
        return s.equalsIgnoreCase("public");
    }
    
    public boolean getPreferPublic() {
        if (this.preferPublic == null) {
            this.preferPublic = new Boolean(this.queryPreferPublic());
        }
        return this.preferPublic;
    }
    
    public void setPreferPublic(final boolean b) {
        this.preferPublic = new Boolean(b);
    }
    
    public boolean preferPublic() {
        return this.getPreferPublic();
    }
    
    private boolean queryUseStaticCatalog() {
        String s = System.getProperty(CatalogManager.pStatic);
        if (this.useStaticCatalog == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources == null) {
                return this.defaultUseStaticCatalog;
            }
            try {
                s = this.resources.getString("static-catalog");
            }
            catch (MissingResourceException ex) {
                return this.defaultUseStaticCatalog;
            }
        }
        if (s == null) {
            return this.defaultUseStaticCatalog;
        }
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("1");
    }
    
    public boolean getUseStaticCatalog() {
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(this.queryUseStaticCatalog());
        }
        return this.useStaticCatalog;
    }
    
    public void setUseStaticCatalog(final boolean b) {
        this.useStaticCatalog = new Boolean(b);
    }
    
    public boolean staticCatalog() {
        return this.getUseStaticCatalog();
    }
    
    public Catalog getPrivateCatalog() {
        Catalog staticCatalog = CatalogManager.staticCatalog;
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(this.getUseStaticCatalog());
        }
        if (staticCatalog != null) {
            if (this.useStaticCatalog) {
                return staticCatalog;
            }
        }
        try {
            final String catalogClassName = this.getCatalogClassName();
            if (catalogClassName == null) {
                staticCatalog = new Catalog();
            }
            else {
                try {
                    staticCatalog = (Catalog)Class.forName(catalogClassName).newInstance();
                }
                catch (ClassNotFoundException ex2) {
                    this.debug.message(1, "Catalog class named '" + catalogClassName + "' could not be found. Using default.");
                    staticCatalog = new Catalog();
                }
                catch (ClassCastException ex3) {
                    this.debug.message(1, "Class named '" + catalogClassName + "' is not a Catalog. Using default.");
                    staticCatalog = new Catalog();
                }
            }
            staticCatalog.setCatalogManager(this);
            staticCatalog.setupReaders();
            staticCatalog.loadSystemCatalogs();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.useStaticCatalog) {
            CatalogManager.staticCatalog = staticCatalog;
        }
        return staticCatalog;
    }
    
    public Catalog getCatalog() {
        Catalog staticCatalog = CatalogManager.staticCatalog;
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(this.getUseStaticCatalog());
        }
        if (staticCatalog == null || !this.useStaticCatalog) {
            staticCatalog = this.getPrivateCatalog();
            if (this.useStaticCatalog) {
                CatalogManager.staticCatalog = staticCatalog;
            }
        }
        return staticCatalog;
    }
    
    public boolean queryAllowOasisXMLCatalogPI() {
        String s = System.getProperty(CatalogManager.pAllowPI);
        if (s == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources == null) {
                return this.defaultOasisXMLCatalogPI;
            }
            try {
                s = this.resources.getString("allow-oasis-xml-catalog-pi");
            }
            catch (MissingResourceException ex) {
                return this.defaultOasisXMLCatalogPI;
            }
        }
        if (s == null) {
            return this.defaultOasisXMLCatalogPI;
        }
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("1");
    }
    
    public boolean getAllowOasisXMLCatalogPI() {
        if (this.oasisXMLCatalogPI == null) {
            this.oasisXMLCatalogPI = new Boolean(this.queryAllowOasisXMLCatalogPI());
        }
        return this.oasisXMLCatalogPI;
    }
    
    public void setAllowOasisXMLCatalogPI(final boolean b) {
        this.oasisXMLCatalogPI = new Boolean(b);
    }
    
    public boolean allowOasisXMLCatalogPI() {
        return this.getAllowOasisXMLCatalogPI();
    }
    
    public String queryCatalogClassName() {
        final String property = System.getProperty(CatalogManager.pClassname);
        if (property == null) {
            if (this.resources == null) {
                this.readProperties();
            }
            if (this.resources == null) {
                return null;
            }
            try {
                return this.resources.getString("catalog-class-name");
            }
            catch (MissingResourceException ex) {
                return null;
            }
        }
        return property;
    }
    
    public String getCatalogClassName() {
        if (this.catalogClassName == null) {
            this.catalogClassName = this.queryCatalogClassName();
        }
        return this.catalogClassName;
    }
    
    public void setCatalogClassName(final String catalogClassName) {
        this.catalogClassName = catalogClassName;
    }
    
    public String catalogClassName() {
        return this.getCatalogClassName();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        CatalogManager.pFiles = "xml.catalog.files";
        CatalogManager.pVerbosity = "xml.catalog.verbosity";
        CatalogManager.pPrefer = "xml.catalog.prefer";
        CatalogManager.pStatic = "xml.catalog.staticCatalog";
        CatalogManager.pAllowPI = "xml.catalog.allowPI";
        CatalogManager.pClassname = "xml.catalog.className";
        CatalogManager.pIgnoreMissing = "xml.catalog.ignoreMissing";
        CatalogManager.staticManager = new CatalogManager();
        CatalogManager.staticCatalog = null;
    }
}
