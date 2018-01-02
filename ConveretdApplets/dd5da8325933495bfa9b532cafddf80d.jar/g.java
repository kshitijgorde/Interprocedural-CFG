import java.io.InputStream;
import java.io.DataInputStream;
import java.util.Vector;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    private Hashtable a;
    public String b;
    private d c;
    
    public g(final d c) {
        this.a = null;
        this.b = null;
        this.a = null;
        this.c = c;
    }
    
    public void a() {
        if (this.a != null) {
            this.a.clear();
            this.a = null;
            this.c.bn();
        }
    }
    
    public String a(final String s, final int n) {
        if (this.a == null) {
            return null;
        }
        return this.a.get(s + "_" + n);
    }
    
    private boolean a(final String[] array) {
        final boolean dj = p.dJ;
        String upperCase = null;
        boolean b = false;
        if (this.a == null) {
            this.a = new Hashtable(8, 0.75f);
        }
        for (int i = 0; i < array.length; ++i) {
            final String trim;
            if (!(trim = array[i].trim()).equals("") && !trim.startsWith(a("|y"))) {
                if (trim.startsWith("[") && trim.endsWith("]")) {
                    upperCase = trim.toUpperCase();
                    if (!dj) {
                        continue;
                    }
                }
                if (upperCase == null) {
                    b = true;
                    this.b = a("\u001e?\u00055 =1V5,0\"\u001f)'s>\u0013'-6$V)'slV") + trim;
                    if (!dj) {
                        break;
                    }
                }
                String string = null;
                String substring = trim;
                final int index;
                if (trim.startsWith("#") && trim.length() > 1 && !trim.substring(1, 2).equals("#") && (index = trim.indexOf(",")) > 1 && index < trim.length() - 1) {
                    final String substring2 = trim.substring(1, index);
                    if (this.c.S(substring2)) {
                        final String string2 = upperCase + "_" + substring2;
                        if (this.a.get(string2) == null) {
                            string = string2;
                            substring = trim.substring(index + 1);
                        }
                    }
                }
                if (string == null) {
                    int n = 0;
                    do {
                        string = upperCase + "_" + n++;
                    } while (this.a.get(string) != null);
                }
                this.a.put(string, substring);
            }
        }
        return b;
    }
    
    public boolean b(final String[] array) {
        this.a(array);
        return true;
    }
    
    public boolean a(final URL url, final String s) {
        final boolean dj = p.dJ;
        final Vector vector = new Vector<String>();
        boolean a = false;
        Label_0181: {
            try {
                final InputStream openStream = new URL(url, s).openStream();
                final DataInputStream dataInputStream = new DataInputStream(openStream);
                while (true) {
                    Label_0044: {
                        if (dj) {
                            break Label_0044;
                        }
                    Label_0118_Outer:
                        while (dataInputStream.available() <= 0) {
                            dataInputStream.close();
                            openStream.close();
                            final String[] array = new String[vector.size()];
                            int n = 0;
                            if (!dj) {
                                while (true) {
                                    while (true) {
                                        Label_0121: {
                                            if (!dj) {
                                                break Label_0121;
                                            }
                                            array[n] = vector.elementAt(n);
                                            ++n;
                                        }
                                        if (n < vector.size()) {
                                            continue Label_0118_Outer;
                                        }
                                        break;
                                    }
                                    a = this.a(array);
                                    if (!dj) {
                                        this.c.bn();
                                        break Label_0181;
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                    final String line;
                    if ((line = dataInputStream.readLine()) != null) {
                        vector.addElement(line);
                    }
                    continue;
                }
            }
            catch (Exception ex) {
                this.c.a(this, a("?9\u0017\""), ex);
                this.b = ex.toString();
                a = true;
            }
        }
        vector.removeAllElements();
        if (a) {
            this.a();
        }
        return !a;
    }
    
    private static String a(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'S';
                    break;
                }
                case 1: {
                    c2 = 'V';
                    break;
                }
                case 2: {
                    c2 = 'v';
                    break;
                }
                case 3: {
                    c2 = 'F';
                    break;
                }
                default: {
                    c2 = 'I';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
