// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang.unicode;

public abstract class a
{
    private static final a a;
    
    static {
        try {
            a = (a)Class.forName(b()).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static a a() {
        return cue.lang.unicode.a.a;
    }
    
    public abstract String a(final String p0);
    
    private static String b() {
        try {
            Class.forName("java.text.Normalizer");
            return "cue.lang.unicode.Normalizer6";
        }
        catch (Exception ex) {
            return "cue.lang.unicode.Normalizer5";
        }
    }
}
