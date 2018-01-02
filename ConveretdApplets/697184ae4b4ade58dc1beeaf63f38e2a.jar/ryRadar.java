// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ryRadar
{
    private static String \u0173;
    
    public static synchronized void transmitName(final String \u0173) {
        ryRadar.\u0173 = \u0173;
    }
    
    public static synchronized String getName() {
        return ryRadar.\u0173;
    }
}
