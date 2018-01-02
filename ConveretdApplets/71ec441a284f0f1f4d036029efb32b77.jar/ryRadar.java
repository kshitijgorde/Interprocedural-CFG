// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ryRadar
{
    private static String \u0161;
    
    public static synchronized void transmitName(final String \u0161) {
        ryRadar.\u0161 = \u0161;
    }
    
    public static synchronized String getName() {
        return ryRadar.\u0161;
    }
}
