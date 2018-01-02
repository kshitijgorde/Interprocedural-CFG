// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

public class JumpLoaderVersion
{
    public static final String APP_NAME = "JumpLoader";
    public static final String VER_MAJOR = "2";
    public static final String VER_MINOR = "12";
    public static final String VER_REVISION = "7";
    public static final String OWNER_HOST = "jumploader.com";
    public static final boolean CHECK_ALLOWED_HOSTS = false;
    public static final String[] ALLOWED_HOSTS;
    public static boolean REGISTERED;
    
    public static String getApplicationName() {
        return "JumpLoader v2.12.7";
    }
    
    static {
        ALLOWED_HOSTS = new String[] { "localhost", "jumploader.com" };
        JumpLoaderVersion.REGISTERED = true;
    }
}
