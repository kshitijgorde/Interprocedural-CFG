// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc;

public class ProcessorVersion
{
    private static int MAJOR;
    private static int MINOR;
    private static int DELTA;
    
    public static void main(final String[] args) {
        System.out.println("XSLTC version " + ProcessorVersion.MAJOR + "." + ProcessorVersion.MINOR + ((ProcessorVersion.DELTA > 0) ? ("." + ProcessorVersion.DELTA) : ""));
    }
    
    static {
        ProcessorVersion.MAJOR = 1;
        ProcessorVersion.MINOR = 0;
        ProcessorVersion.DELTA = 0;
    }
}
