// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ryEVCatcher
{
    private static String \u0165;
    private static String \u0164;
    private static String \u0163;
    static boolean \u0162;
    static boolean \u0161;
    static String \u0160;
    
    public static synchronized void send(final String \u0165, final String \u01652, final String \u0163) {
        ryEVCatcher.\u0165 = \u0165;
        ryEVCatcher.\u0164 = \u01652;
        ryEVCatcher.\u0163 = \u0163;
        ryEVCatcher.\u0160 = String.valueOf(\u0165) + \u01652 + \u0163;
    }
    
    public static synchronized String[] receive() {
        return new String[] { ryEVCatcher.\u0165, ryEVCatcher.\u0164, ryEVCatcher.\u0163 };
    }
    
    static {
        ryEVCatcher.\u0165 = null;
        ryEVCatcher.\u0164 = null;
        ryEVCatcher.\u0163 = null;
        ryEVCatcher.\u0162 = false;
        ryEVCatcher.\u0161 = false;
        ryEVCatcher.\u0160 = null;
    }
}
