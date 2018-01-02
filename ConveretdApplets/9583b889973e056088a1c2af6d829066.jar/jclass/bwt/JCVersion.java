// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

public class JCVersion
{
    public static final int major = 2;
    public static final int minor = 0;
    public static final int release = 9;
    public static final boolean transitional = false;
    static String str;
    private static String version;
    
    public static final String getVersionString() {
        if (JCVersion.str == null) {
            JCVersion.str = "JClass BWT " + 2 + "." + 0 + "." + 9;
        }
        return JCVersion.str;
    }
    
    public static final String getVersionNumber() {
        if (JCVersion.version == null) {
            JCVersion.version = new StringBuffer().append(2).append(0).append(9).toString();
        }
        return JCVersion.version;
    }
    
    public static final void main(final String[] array) {
        System.out.println(getVersionString());
    }
    
    static {
        JCVersion.str = null;
        JCVersion.version = null;
    }
}
