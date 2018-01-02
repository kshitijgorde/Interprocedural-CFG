// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

public class JCVersion
{
    public static final int major = 2;
    public static final int minor = 0;
    public static final int release = 4;
    public static final boolean transitional = false;
    static String vStr;
    
    public static final String getVersionString() {
        if (JCVersion.vStr == null) {
            JCVersion.vStr = "JClass BWT 2.0.4";
        }
        return JCVersion.vStr;
    }
    
    public static final void main(final String[] array) {
        System.out.println(getVersionString());
    }
    
    static {
        JCVersion.vStr = null;
    }
}
