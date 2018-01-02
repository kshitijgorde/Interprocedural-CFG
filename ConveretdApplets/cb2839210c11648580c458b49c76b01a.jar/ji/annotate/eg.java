// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import ji.io.ac;
import ji.v1event.ao;
import java.net.URL;
import ji.io.h;
import ji.util.i;
import ji.util.d;
import ji.net.a0;
import ji.v1event.af;
import java.awt.Component;
import ji.awt.c;

public class eg
{
    boolean a;
    Object b;
    String c;
    c d;
    
    public eg(final String c) {
        this.a = false;
        this.b = null;
        this.c = "";
        this.d = null;
        this.c = c;
    }
    
    public final boolean a(final Component component, final af af, final a0 a0, final String s) throws Exception {
        final Object a2 = ji.util.d.a1();
        if (a2 == null) {
            ji.util.d.a("Security Problem", "Security Access Undefined\nUnable to retrieve required security information", component, 30, null, af, this.c);
        }
        else {
            final boolean c = i.c(57);
            if (c) {
                h.d(this.c, "jiFNSecurityUsers1");
            }
            String s2;
            if (a2 instanceof URL) {
                final URL b = new URL(String.valueOf(String.valueOf(((URL)a2).toString())).concat("&User=true&Group=true"));
                this.b = b;
                s2 = a0.a(b, false, true, "", component, true, af, null);
            }
            else {
                this.b = a2;
                s2 = a0.a((String)a2, "", component, af);
            }
            if (c) {
                h.d(this.c, "jiFNSecurityUsers2");
            }
            if (ji.util.d.by(s2)) {
                ji.util.d.a("Security Problem", "Unable to save information", component, 30, null, af, this.c);
            }
            else {
                if (c) {
                    h.d(this.c, "jiFNSecurityUsers3");
                }
                final ac ac = new ac(s2, false, false, 0, true, component, this.c);
                final byte[] array = new byte[(int)ac.v()];
                ac.a(array);
                ac.a(component);
                if (c) {
                    h.d(this.c, "jiFNSecurityUsers4");
                }
                this.a = this.b(array, s, component);
                if (c) {
                    h.d(this.c, "jiFNSecurityUsers5");
                }
                if (!this.a) {
                    ji.util.d.a("Security Problem", "Unable to parse information", component, 30, null, af, this.c);
                }
                try {
                    ji.io.ac.c(s2, this.c);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return this.a;
    }
    
    private final boolean b(final byte[] array, final String s, final Component component) {
        boolean b = false;
        final String[] a = this.a(array, s, component);
        boolean b2 = false;
        if (a != null) {
            for (int i = 0; i < a.length; ++i) {
                final String b3 = ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(a[i], "  ", " "), " = ", "="), " =", "="), "= ", "=");
                final String lowerCase = b3.toLowerCase();
                String b4 = null;
                String b5 = null;
                int n = 0;
                int n2 = lowerCase.indexOf("<user ");
                if (n2 >= 0) {
                    b2 = true;
                    n = 1;
                }
                if (n == 0) {
                    n2 = lowerCase.indexOf("<group ");
                    if (n2 >= 0) {
                        b2 = false;
                        n = 1;
                    }
                }
                if (n != 0) {
                    final String substring = b3.substring(n2);
                    final String substring2 = lowerCase.substring(n2);
                    final int index = substring2.indexOf("id=");
                    if (index >= 0) {
                        final String substring3 = substring.substring(index + 3);
                        final int index2 = substring3.indexOf(" ");
                        if (index2 >= 0) {
                            b4 = ji.util.d.b(substring3.substring(0, index2), "\"", "");
                        }
                        else {
                            b4 = null;
                        }
                    }
                    final int index3 = substring2.indexOf("name=");
                    if (index3 >= 0) {
                        final String substring4 = substring.substring(index3 + 5);
                        final int index4 = substring4.indexOf("/>");
                        if (index4 >= 0) {
                            b5 = substring4.substring(1, index4 - 2);
                        }
                        else {
                            final int index5 = substring4.indexOf(">");
                            if (index5 >= 0) {
                                b5 = substring4.substring(1, index5 - 1);
                            }
                            else {
                                b5 = null;
                            }
                        }
                    }
                    if (b4 != null && b5 != null) {
                        if (this.d == null) {
                            this.d = new c("jiFNSecurityUsers3");
                        }
                        final acg acg = new acg();
                        acg.a = b4;
                        acg.b = b5;
                        acg.c = !b2;
                        this.d.c(acg);
                    }
                    else {
                        h.d(this.c, "Unable to parse: ".concat(String.valueOf(String.valueOf(a[i]))));
                    }
                }
            }
        }
        if (this.d != null) {
            if (this.d.b() == 0) {
                this.d = null;
                b = false;
            }
            else {
                b = true;
            }
        }
        return b;
    }
    
    public final String[] a() {
        final c c = new c("jiFNSecurityUsers4");
        if (this.d != null) {
            for (int i = 0; i < this.d.b(); ++i) {
                final acg acg = (acg)this.d.b(i);
                if (!acg.c) {
                    c.c(acg.b);
                }
            }
        }
        if (c.b() > 0) {
            final String[] array = new String[c.b()];
            for (int j = 0; j < c.b(); ++j) {
                array[j] = (String)c.b(j);
            }
            return array;
        }
        return null;
    }
    
    public final String[] b() {
        final c c = new c("jiFNSecurityUsers5");
        if (this.d != null) {
            for (int i = 0; i < this.d.b(); ++i) {
                final acg acg = (acg)this.d.b(i);
                if (acg.c) {
                    c.c(acg.b);
                }
            }
        }
        if (c.b() > 0) {
            final String[] array = new String[c.b()];
            for (int j = 0; j < c.b(); ++j) {
                array[j] = (String)c.b(j);
            }
            return array;
        }
        return null;
    }
    
    public String[] a(final byte[] array, final String s, final Component component) {
        String[] array2 = null;
        try {
            c c = new c("jiFNSecurity1");
            String s2;
            if (s != null) {
                s2 = s;
            }
            else {
                s2 = ji.util.d.bm(this.c);
            }
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            LineNumberReader lineNumberReader = null;
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = new InputStreamReader(byteArrayInputStream, s2);
            }
            catch (Exception ex) {
                h.d(this.c, "Error processing security file with encoding: ".concat(String.valueOf(String.valueOf(s2))));
                ex.printStackTrace();
                s2 = ji.util.d.bm(this.c);
                h.d(this.c, "Using default encoding : ".concat(String.valueOf(String.valueOf(s2))));
                inputStreamReader = new InputStreamReader(byteArrayInputStream, s2);
            }
            if (ji.util.d.cs()) {
                h.d(this.c, "Security encoding: ".concat(String.valueOf(String.valueOf(s2))));
            }
            try {
                lineNumberReader = new LineNumberReader(inputStreamReader);
                String s3 = lineNumberReader.readLine();
                int n = 0;
                while (s3 != null) {
                    if (s3 != null) {
                        ++n;
                        c.c(s3);
                    }
                    s3 = lineNumberReader.readLine();
                }
            }
            catch (Exception ex2) {
                h.d(this.c, "Error processing security file with encoding: ".concat(String.valueOf(String.valueOf(s2))));
                ex2.printStackTrace();
            }
            if (lineNumberReader != null) {
                lineNumberReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (c.b() > 0) {
                final c c2 = new c("jiFNSecurity2");
                for (int i = 0; i < c.b(); ++i) {
                    final String s4 = (String)c.b(i);
                    if (!ji.util.d.by(ji.util.d.bd(ji.util.d.bc(s4)))) {
                        c2.c(s4);
                    }
                }
                c = c2;
            }
            if (c.b() > 0) {
                array2 = new String[c.b()];
                for (int j = 0; j < c.b(); ++j) {
                    String s5 = ji.util.d.bd(ji.util.d.bc((String)c.b(j)));
                    for (int k = s5.indexOf("&quot;"); k > 0; k = s5.indexOf("&quot")) {
                        s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s5.substring(0, k)))).append("\"").append(s5.substring(k + 6))));
                    }
                    for (int l = s5.indexOf("&amp;"); l > 0; l = s5.indexOf("&amp;")) {
                        s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s5.substring(0, l)))).append("&").append(s5.substring(l + 5))));
                    }
                    if (!ji.util.d.by(s5)) {
                        if (s5.startsWith("//")) {
                            array2[j] = "";
                        }
                        else {
                            array2[j] = s5;
                        }
                    }
                }
            }
            else {
                ji.util.d.a(this.a(479), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append(": ").append(this.a(286)).append(" ----- (no user data)"))), component, 60, null, null, this.c);
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return array2;
    }
    
    public final boolean c() {
        return this.a;
    }
    
    private String a(final int n) {
        return ji.util.d.b(n, this.c);
    }
    
    public final void d() {
        try {
            if (this.d != null) {
                this.d.c();
                this.d = null;
            }
            this.a = false;
            this.b = null;
        }
        catch (Exception ex) {}
    }
    
    class acg
    {
        String a;
        String b;
        boolean c;
        
        acg(final eg eg) {
        }
    }
}
