// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.awt.Color;
import java.util.Hashtable;

public class Parametres
{
    static Hashtable hash;
    
    public static void addParam(final String s, final Object o) {
        Parametres.hash.put(s, o);
    }
    
    public static void addParamInt(final String s, final int n) {
        Parametres.hash.put(s, new Integer(n));
    }
    
    public static void addParamCol(final String s, final Color color) {
        Parametres.hash.put(s, color);
    }
    
    public static Object getParam(final String s) {
        return Parametres.hash.get(s);
    }
    
    public static int getParamInt(final String s) {
        return (int)getParam(s);
    }
    
    public static String getParamStr(final String s) {
        return Parametres.hash.get(s);
    }
    
    public static Color getParamCol(final String s) {
        return Parametres.hash.get(s);
    }
    
    static {
        Parametres.hash = new Hashtable();
    }
}
