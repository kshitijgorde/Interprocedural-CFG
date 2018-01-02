import java.util.Vector;
import java.io.IOException;
import java.util.Hashtable;
import java.io.StreamTokenizer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class c
{
    private Applet d;
    private StreamTokenizer a;
    private Hashtable b;
    private String c;
    
    c(final StreamTokenizer a, final String c, Hashtable b, final Applet d) throws IOException {
        this.d = d;
        if (b == null) {
            b = new Hashtable<String, String>();
        }
        if (c.equals("B")) {
            b.put("BOLD", "true");
        }
        if (c.equals("I")) {
            b.put("ITALIC", "true");
        }
        this.a = a;
        this.c = c;
        this.b = b;
    }
    
    public Vector a() throws IOException {
        final Vector<a> vector = new Vector<a>();
        Hashtable hashtable = (Hashtable)this.b.clone();
        boolean b = false;
        int n = 0;
        String upperCase = "";
        String s = "";
        int n2 = this.a.nextToken();
        int n3 = -1;
        for (boolean b2 = false; n2 != -1 && !b2; n2 = this.a.nextToken()) {
            switch (n2) {
                case 60: {
                    if (s.length() > 0) {
                        vector.addElement(new a(s, this.b, this.d));
                    }
                    b = true;
                    n = 0;
                    break;
                }
                case 62: {
                    b = false;
                    s = "";
                    if (n != 0 && upperCase.equals(this.c)) {
                        b2 = true;
                        break;
                    }
                    if (upperCase.equals("IMG")) {
                        vector.addElement((a)new e(this.d, hashtable));
                    }
                    else {
                        final Vector a = new c(this.a, upperCase, (Hashtable)hashtable.clone(), this.d).a();
                        for (int i = 0; i < a.size(); ++i) {
                            vector.addElement(a.elementAt(i));
                        }
                    }
                    hashtable = (Hashtable)this.b.clone();
                    upperCase = "";
                    break;
                }
                case 47: {
                    if (n3 == 60) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 61: {
                    if (b && s.length() > 0) {
                        this.a.nextToken();
                        hashtable.put(s.toUpperCase(), (this.a.sval != null) ? this.a.sval : String.valueOf((int)this.a.nval));
                        break;
                    }
                    break;
                }
                case 38: {
                    this.a.nextToken();
                    final String sval = this.a.sval;
                    if (this.a.nextToken() != 59) {
                        break;
                    }
                    if (sval.equalsIgnoreCase("nbsp")) {
                        s = String.valueOf(s) + " ";
                        break;
                    }
                    if (sval.equalsIgnoreCase("quot")) {
                        s = String.valueOf(s) + "\"";
                        break;
                    }
                    break;
                }
                case -3: {
                    if (b) {
                        if (upperCase.equals("")) {
                            upperCase = this.a.sval.toUpperCase();
                        }
                        s = this.a.sval;
                        break;
                    }
                    s = String.valueOf(s) + " " + this.a.sval;
                    break;
                }
                default: {
                    s = String.valueOf(s) + (char)n2;
                    break;
                }
            }
            if (!b2) {
                n3 = n2;
            }
        }
        if (s.length() > 0) {
            vector.addElement(new a(s, this.b, this.d));
        }
        return vector;
    }
}
