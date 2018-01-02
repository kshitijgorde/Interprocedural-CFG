import java.io.IOException;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.Hashtable;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class this extends throw
{
    private static String o = "";
    private static String p = "\udcc4\udcc4";
    private static String q = "\udc85\udc9e\udc87\udc87\udcb4\udc98\udc8e\udc88\udc9f\udc82\udc84\udc85";
    
    public boolean b(final URL url, final String s, final boolean mb) {
        super.mb = mb;
        super.zoa = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new URL(url, s).openStream())));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(this.o) && !trim.startsWith(this.p)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (super.mb && this.a(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.a(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!super.mb && s2 == null) {
                            s2 = this.q;
                            vector = new Vector<String>();
                        }
                        vector.addElement(new String(trim));
                    }
                }
            }
            if (s2 != null) {
                this.a(s2, vector);
            }
        }
        catch (IOException ex) {
            return false;
        }
        finally {
            switch.b(bufferedReader);
        }
        return true;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\udceb');
        }
        return new String(array);
    }
    
    static {
        this.o = _(this.o);
        this.p = _(this.p);
        this.q = _(this.q);
    }
}
