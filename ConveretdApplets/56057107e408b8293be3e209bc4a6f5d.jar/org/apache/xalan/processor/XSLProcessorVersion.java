// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

public class XSLProcessorVersion
{
    public static String S_VERSION;
    public static final String PRODUCT = "Xalan";
    public static String LANGUAGE;
    public static int VERSION;
    public static int RELEASE;
    public static int MAINTENANCE;
    public static int DEVELOPMENT;
    
    static {
        XSLProcessorVersion.S_VERSION = "2.0.0";
        XSLProcessorVersion.LANGUAGE = "Java";
        XSLProcessorVersion.VERSION = 2;
        XSLProcessorVersion.RELEASE = 0;
        XSLProcessorVersion.MAINTENANCE = 0;
        XSLProcessorVersion.DEVELOPMENT = 0;
    }
    
    public static void main(final String[] argv) {
        System.out.println("Xalan " + XSLProcessorVersion.LANGUAGE + " Version " + XSLProcessorVersion.S_VERSION);
    }
}
