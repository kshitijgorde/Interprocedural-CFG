// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ryRadar
{
    private static String \u0152;
    
    public static synchronized void transmitName(final String \u0153) {
        ryRadar.\u0152 = \u0153;
    }
    
    public static synchronized String getName() {
        return ryRadar.\u0152;
    }
}
