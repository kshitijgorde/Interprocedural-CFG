// 
// Decompiled by Procyon v0.5.30
// 

package com.lotus.sametime.util;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.net.URL;
import java.util.Properties;
import java.applet.Applet;

public class STVMVerifier extends Applet
{
    public static final String VALID_VM_REDIRECT_URL;
    public static final String INVALID_VM_REDIRECT_URL;
    public static final String PROPERTY_FILE_NAME;
    public static final String PROPERTY_FILE_RESOURCE_NAMESPACE;
    public static final String JAVA_SCRIPT_FRAME_NAME;
    public static final String PROPERTY_DIRECTORY;
    public static final String VM_COUNT;
    public static final String VENDOR;
    public static final String VERSION;
    public static final String VERSIONCOMPARE;
    public static final String OS_NAME;
    public static final String OS_ARCH;
    public static final String OS_VERSION;
    public static final String OSVERSIONCOMPARE;
    public static final String MAJOR_VERSION;
    public static final String MINOR_VERSION;
    public static final String BUILD_NUMBER;
    public static final String BUILD_INCREMENT;
    private Properties VMProperties;
    private final int numberVersionParts = 5;
    private String vendor;
    private String version;
    private String osName;
    private String osArch;
    private String osVersion;
    private String documentTarget;
    static /* synthetic */ Class class$com$lotus$sametime$util$STVMVerifier;
    
    public STVMVerifier() {
        this.VMProperties = null;
        this.vendor = null;
        this.version = null;
        this.osName = null;
        this.osArch = null;
        this.osVersion = null;
        this.documentTarget = null;
    }
    
    public void init() {
        this.vendor = System.getProperty(STVMVerifier.VENDOR);
        this.version = System.getProperty(STVMVerifier.VERSION);
        this.osName = System.getProperty(STVMVerifier.OS_NAME);
        this.osArch = System.getProperty(STVMVerifier.OS_ARCH);
        this.osVersion = System.getProperty(STVMVerifier.OS_VERSION);
        System.out.println(this.getAppletInfo());
        if (this.vendor != null && this.vendor.indexOf("Microsoft") != -1) {
            try {
                final Class<?> forName = Class.forName("com.ms.util.SystemVersionManager");
                final Properties properties = (Properties)forName.getMethod("getVMVersion", (Class[])null).invoke(forName, (Object[])null);
                this.version = new String("" + ((Hashtable<K, Object>)properties).get(STVMVerifier.MAJOR_VERSION) + "." + ((Hashtable<K, Object>)properties).get(STVMVerifier.MINOR_VERSION) + "." + ((Hashtable<K, Object>)properties).get(STVMVerifier.BUILD_INCREMENT) + "." + ((Hashtable<K, Object>)properties).get(STVMVerifier.BUILD_NUMBER));
            }
            catch (Exception ex) {
                System.out.println("STVMVerifier.init():  Unable to determine Microsoft VM version");
            }
        }
        this.documentTarget = this.getParameter(STVMVerifier.JAVA_SCRIPT_FRAME_NAME);
        this.loadVMProperties();
    }
    
    public void start() {
        this.displayRedirectURL();
    }
    
    public boolean isVMValid() {
        boolean b = true;
        int intValue = 0;
        final Properties vmProperties = this.getVMProperties();
        try {
            intValue = new Integer(((Hashtable<K, String>)vmProperties).get(STVMVerifier.VM_COUNT));
        }
        catch (Exception ex) {}
        for (int i = 0; i < intValue; ++i) {
            b = (b && this.checkVM(vmProperties, i));
            if (!b) {
                System.out.println("STVMVerifier:  Invalid VM");
                break;
            }
        }
        return b;
    }
    
    private boolean checkVM(final Properties properties, final int n) {
        int n2 = 1;
        final String s = ((Hashtable<K, String>)properties).get("" + STVMVerifier.VENDOR + n);
        final String s2 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.VERSION + n);
        final String s3 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.OS_NAME + n);
        final String s4 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.OS_ARCH + n);
        final String s5 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.OS_VERSION + n);
        String s6 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.VERSIONCOMPARE + n);
        String s7 = ((Hashtable<K, String>)properties).get("" + STVMVerifier.OSVERSIONCOMPARE + n);
        if (null != s && null != this.vendor && this.vendor.indexOf(s) == -1) {
            n2 = 0;
        }
        if (n2 == 1 && null != s2 && null != this.version) {
            if (null == s6) {
                s6 = "=";
            }
            n2 = (this.isVersionInvalid(s2, this.version, s6) ? 1 : 0);
        }
        if (n2 == 1 && null != s3 && null != this.osName && this.osName.indexOf(s3) == -1) {
            n2 = 0;
        }
        if (n2 == 1 && null != s4 && null != this.osArch && this.osArch.indexOf(s4) == -1) {
            n2 = 0;
        }
        if (n2 == 1 && null != s5 && null != this.osVersion) {
            if (null == s7) {
                s7 = "=";
            }
            n2 = (this.isVersionInvalid(s5, this.osVersion, s7) ? 1 : 0);
        }
        return n2 == 0;
    }
    
    private Properties getVMProperties() {
        if (null == this.VMProperties) {
            this.VMProperties = new Properties();
        }
        return this.VMProperties;
    }
    
    private void loadVMProperties() {
        final Properties vmProperties = this.getVMProperties();
        final String propertyFilePath = this.getPropertyFilePath();
        if (null != propertyFilePath) {
            try {
                vmProperties.load(((STVMVerifier.class$com$lotus$sametime$util$STVMVerifier == null) ? (STVMVerifier.class$com$lotus$sametime$util$STVMVerifier = class$("com.lotus.sametime.util.STVMVerifier")) : STVMVerifier.class$com$lotus$sametime$util$STVMVerifier).getResourceAsStream(propertyFilePath));
            }
            catch (Exception ex) {
                System.out.println("STVMVerifier: Unable to load VM properties: " + ex);
            }
        }
    }
    
    private String getPropertyFilePath() {
        final String parameter = this.getParameter(STVMVerifier.PROPERTY_FILE_RESOURCE_NAMESPACE);
        final String parameter2 = this.getParameter(STVMVerifier.PROPERTY_FILE_NAME);
        String s;
        if (null != parameter) {
            s = new String("/" + parameter + "/" + STVMVerifier.PROPERTY_DIRECTORY + "/" + parameter2);
        }
        else {
            s = new String("/" + STVMVerifier.PROPERTY_DIRECTORY + "/" + parameter2);
        }
        System.out.println("PROPERTIES PATH: " + s);
        return s;
    }
    
    public void displayRedirectURL() {
        final URL redirectURL = this.getRedirectURL();
        if (null != redirectURL) {
            if (this.documentTarget != null) {
                this.getAppletContext().showDocument(redirectURL, this.documentTarget);
            }
            else {
                this.getAppletContext().showDocument(redirectURL);
            }
        }
    }
    
    private URL getRedirectURL() {
        URL url;
        if (this.isVMValid()) {
            url = this.createRedirectURL(this.getParameter(STVMVerifier.VALID_VM_REDIRECT_URL));
        }
        else {
            url = this.createRedirectURL(this.getParameter(STVMVerifier.INVALID_VM_REDIRECT_URL));
        }
        return url;
    }
    
    private URL createRedirectURL(final String s) {
        URL url = null;
        if (null != s) {
            try {
                url = new URL(this.getDocumentBase(), s);
            }
            catch (Exception ex) {}
        }
        return url;
    }
    
    private String[] parseVersion(final String s) {
        final String[] array = new String[5];
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "._,", false);
        for (int i = 0; i < 5; ++i) {
            array[i] = "";
        }
        for (int n = 0; stringTokenizer.hasMoreTokens() && n < 5; ++n) {
            array[n] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private boolean isVersionInvalid(final String s, final String s2, final String s3) {
        final String[] array = new String[5];
        final String[] array2 = new String[5];
        boolean b = false;
        boolean b2 = true;
        final String[] version = this.parseVersion(s);
        final String[] version2 = this.parseVersion(s2);
        for (int i = 0; i < 5; ++i) {
            final int compareTo = version2[i].compareTo(version[i]);
            if (s3.indexOf("<") != -1) {
                if (compareTo < 0) {
                    b = true;
                    break;
                }
            }
            else if (s3.indexOf(">") != -1 && compareTo > 0) {
                b = true;
                break;
            }
            if (compareTo != 0) {
                b2 = false;
                break;
            }
        }
        return (s3.indexOf("=") != -1 && b2) || b;
    }
    
    public String getVMVendor() {
        return this.vendor;
    }
    
    public String getVMVersion() {
        return this.version;
    }
    
    public String getOSName() {
        return this.osName;
    }
    
    public String getOSArchitecture() {
        return this.osArch;
    }
    
    public String getOSVersion() {
        return this.osVersion;
    }
    
    public String getAppletInfo() {
        return "<STVMVerifier> JAVA VM VENDOR: " + this.vendor + "\n" + "<STVMVerifier> JAVA VM VERSION: " + this.version + "\n" + "<STVMVerifier> OS NAME: " + this.osName + "\n" + "<STVMVerifier> OS ARCH: " + this.osArch + "\n" + "<STVMVerifier> OS VERSION: " + this.osVersion + "\n";
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
        VALID_VM_REDIRECT_URL = new String("RedirectNoMatchURL");
        INVALID_VM_REDIRECT_URL = new String("RedirectURL");
        PROPERTY_FILE_NAME = new String("VMVersionFile");
        PROPERTY_FILE_RESOURCE_NAMESPACE = new String("ResourceNamespace");
        JAVA_SCRIPT_FRAME_NAME = new String("jsTarget");
        PROPERTY_DIRECTORY = new String("properties");
        VM_COUNT = new String("VMCount");
        VENDOR = new String("java.vendor");
        VERSION = new String("java.version");
        VERSIONCOMPARE = new String("java.versioncompare");
        OS_NAME = new String("os.name");
        OS_ARCH = new String("os.arch");
        OS_VERSION = new String("os.version");
        OSVERSIONCOMPARE = new String("os.versioncompare");
        MAJOR_VERSION = new String("MajorVersion");
        MINOR_VERSION = new String("MinorVersion");
        BUILD_NUMBER = new String("BuildNumber");
        BUILD_INCREMENT = new String("BuildIncrement");
    }
}
