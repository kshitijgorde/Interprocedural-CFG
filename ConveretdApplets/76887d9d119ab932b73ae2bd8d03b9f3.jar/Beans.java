// 
// Decompiled by Procyon v0.5.30
// 

public class Beans
{
    private static boolean designTime;
    private static boolean guiAvailable;
    
    public static boolean isDesignTime() {
        return Beans.designTime;
    }
    
    public static boolean isGuiAvailable() {
        return Beans.guiAvailable;
    }
    
    public static void setDesignTime(final boolean isDesignTime) {
        Beans.designTime = isDesignTime;
    }
    
    public static void setGuiAvailable(final boolean isGuiAvailable) {
        Beans.guiAvailable = isGuiAvailable;
    }
}
