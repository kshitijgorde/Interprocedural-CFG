// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import ji.filter.n6;
import ji.filter.n7;
import ji.io.ac;
import java.util.HashMap;
import java.util.Vector;

public class n5
{
    private Vector a;
    private boolean b;
    private HashMap c;
    
    public n5(final ac ac) throws Exception {
        this.b = false;
        this.c = new HashMap();
        final long r = ac.r();
        String substring = null;
        try {
            ac.a(0L);
            this.a(ac);
            if (this.c.containsKey("From") && this.c.containsKey("To") && this.c.containsKey("Subject") && this.c.containsKey("Content-Type")) {
                final String s = this.c.get("Content-Type");
                if ("multipart/mixed".equals(s.substring(s.indexOf(":") + 1, s.indexOf(";")).trim())) {
                    final int n = s.indexOf("boundary=") + 10;
                    if (n != -1) {
                        substring = s.substring(n, s.length() - 1);
                    }
                    this.a = new Vector();
                    this.a(ac, substring);
                }
                this.b = true;
            }
            else {
                this.b = false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b = false;
        }
        finally {
            ac.a(r);
        }
    }
    
    boolean a() {
        return this.b;
    }
    
    boolean b() {
        return this.a != null && this.a.size() > 0;
    }
    
    public n7[] c() {
        return this.a.toArray(new n7[this.a.size()]);
    }
    
    private void a(final ac ac) throws Exception {
        String concat = null;
        String n;
        while ((n = ac.n()) != null) {
            if (n.startsWith(" ") || n.startsWith("\t")) {
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(n)));
                n = null;
            }
            else {
                if ("".equals(n)) {
                    break;
                }
                if (concat == null) {
                    concat = n;
                    n = null;
                }
                else {
                    this.a(concat);
                    concat = null;
                }
            }
            if (n != null) {
                concat = n;
            }
        }
        if (concat != null) {
            this.a(concat);
        }
    }
    
    private void a(final String s) {
        if (s.startsWith("From:")) {
            this.c.put("From", s);
        }
        else if (s.startsWith("To:")) {
            this.c.put("To", s);
        }
        else if (s.startsWith("Subject:")) {
            this.c.put("Subject", s);
        }
        else if (s.startsWith("Content-Type:")) {
            this.c.put("Content-Type", s);
        }
    }
    
    private void a(final ac ac, final String s) throws Exception {
        String n;
        while ((n = ac.n()) != null) {
            if (n.length() >= s.length() && n.endsWith(s)) {
                this.b(ac);
            }
        }
    }
    
    private void b(final ac ac) throws Exception {
        String concat = null;
        final n6 n6 = new n6();
        String n7;
        while (!"".equals(n7 = ac.n()) && n7 != null) {
            if (n7.startsWith(" ") || n7.startsWith("\t")) {
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(n7)));
            }
            else if (concat == null) {
                concat = n7;
            }
            else {
                this.a(n6, concat);
                concat = n7;
            }
        }
        if (concat != null) {
            this.a(n6, concat);
        }
        if (!"multipart/alternative".equals(n6.b()) && !"multipart/related".equals(n6.b()) && !"".equals(n6.b())) {
            this.a.add(n6);
        }
    }
    
    private void a(final n6 n6, String s) {
        if (s.startsWith("Content-Type:")) {
            s = s.substring(s.indexOf(":") + 1).trim();
            if (s.indexOf(";") != -1) {
                s = s.substring(0, s.indexOf(";"));
            }
            n6.b(s);
        }
        else if (s.startsWith("Content-Disposition")) {
            n6.a(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName());
        sb.append(": hasAttachments=".concat(String.valueOf(String.valueOf(this.b())))).append("[");
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.a.elementAt(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
