import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class eu extends cr
{
    public static Class a;
    public static final eu b;
    public static /* synthetic */ Class c;
    
    public Class a() {
        return eu.a;
    }
    
    public Object a(final String s, final u u) throws InstantiationException {
        Color decode = null;
        try {
            decode = (Color)((eu.c == null) ? (eu.c = class$("java.awt.Color")) : eu.c).getField(s).get(null);
        }
        catch (Exception ex) {}
        if (decode == null) {
            try {
                decode = Color.decode("#" + s);
            }
            catch (Exception ex2) {}
        }
        if (decode == null) {
            throw new InstantiationException("Can not convert " + s + " to a color");
        }
        return decode;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        b = new eu();
        try {
            eu.a = Class.forName("java.awt.Color");
        }
        catch (Exception ex) {}
    }
}
