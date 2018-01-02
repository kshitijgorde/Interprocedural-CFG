import java.util.Hashtable;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class au
{
    private String a;
    private String b;
    private int c;
    private BufferedReader d;
    private boolean e;
    private String f;
    private String g;
    
    public au(final BufferedReader d) {
        this.a = "";
        this.b = "";
        this.f = "";
        this.g = "";
        this.d = d;
        this.a();
    }
    
    public au() {
        this.a = "";
        this.b = "";
        this.f = "";
        this.g = "";
        this.a();
    }
    
    public String a(final String s) {
        String string = new String();
        for (int i = 0; i < s.length(); ++i) {
            char char1 = s.charAt(i);
            if (char1 == '&') {
                final int index = s.indexOf(59, i);
                if (index != -1) {
                    final String substring = s.substring(i + 1, index);
                    i = index;
                    if (substring.equals("lt")) {
                        char1 = '<';
                    }
                    else if (substring.equals("amp")) {
                        char1 = '&';
                    }
                    else if (substring.equals("gt")) {
                        char1 = '>';
                    }
                    else if (substring.equals("quot")) {
                        char1 = '\"';
                    }
                    else if (substring.equals("apos")) {
                        char1 = '\'';
                    }
                    else if (substring.equals("nbsp")) {
                        char1 = ' ';
                    }
                    else if (substring.equals("cr")) {
                        char1 = '\n';
                    }
                }
            }
            string += char1;
        }
        return string;
    }
    
    public void b(final String f) {
        this.a();
        this.f = f;
    }
    
    public boolean a(final at at) {
        boolean b;
        do {
            b = false;
            at.a = 0;
            at.b = this.g;
            at.c = "";
            at.d = "";
            (at.e = new Hashtable()).clear();
            int n = 0;
            boolean b2 = false;
            int n2 = 0;
            int n3 = 0;
            String s = "";
            String s2 = "";
            while (true) {
                char char1 = 'z';
                int n4;
                if (this.f.length() > 0) {
                    char1 = this.f.charAt(0);
                    this.f = this.f.substring(1);
                    n4 = 1;
                }
                else {
                    try {
                        char1 = (char)this.d.read();
                        n4 = 1;
                    }
                    catch (Exception ex) {
                        n4 = 0;
                    }
                }
                if (n4 != 1) {
                    if (at.a != 0) {
                        this.c("Unexpected End of File");
                    }
                    return false;
                }
                boolean b3 = false;
                if (char1 == '\n') {
                    ++this.c;
                }
                if (at.a == 4) {
                    boolean b4 = true;
                    String s3 = at.c;
                    if ((at.d != null && at.d.length() != 0) || Character.isWhitespace(char1)) {
                        b4 = false;
                        s3 = at.d;
                        if (s3 == null) {
                            s3 = "";
                        }
                    }
                    final String string = s3 + char1;
                    final int length = string.length();
                    if (char1 == '>' && length >= 2 && string.charAt(length - 2) == '?') {
                        final String substring = string.substring(0, length - 2);
                        if (b4) {
                            at.c = substring;
                            break;
                        }
                        at.d = substring;
                        break;
                    }
                    else if (b4) {
                        at.c = string;
                    }
                    else {
                        at.d = string;
                    }
                }
                else {
                    if (char1 == '\"') {
                        b2 = !b2;
                    }
                    else if (!b2 || at.a == 3) {
                        if (char1 == '<') {
                            if (at.a == 3) {
                                this.f = "" + char1 + this.f;
                                break;
                            }
                            if (at.a != 0) {
                                this.c("Unexpected '<'");
                                return false;
                            }
                            at.a = 1;
                        }
                        else if (char1 == '>') {
                            if (at.a == 1) {
                                if (at.c.equals("!--")) {
                                    b = true;
                                    break;
                                }
                                final int length2 = at.c.length();
                                if (length2 > 0 && at.c.charAt(length2 - 1) == '/') {
                                    at.c = at.c.substring(0, length2 - 1);
                                    this.f = "</" + at.c + ">" + this.f;
                                }
                                if (this.g.length() != 0) {
                                    this.g += "/";
                                }
                                this.g += at.c;
                                break;
                            }
                            else {
                                if (at.a != 2) {
                                    this.c("Unexpected '>'");
                                    return false;
                                }
                                final int lastIndex = this.g.lastIndexOf(47);
                                if (lastIndex == -1 && this.g.length() == 0) {
                                    this.c("Unexpected End");
                                    return false;
                                }
                                final String substring2 = this.g.substring(lastIndex + 1);
                                if (!substring2.equals(at.c)) {
                                    this.c("End '" + at.c + "' Doesn't Match Start '" + substring2 + "'");
                                    return false;
                                }
                                if (lastIndex == -1) {
                                    this.g = "";
                                    break;
                                }
                                this.g = this.g.substring(0, lastIndex);
                                break;
                            }
                        }
                        else if (char1 == '/' && at.a == 1 && at.c == "") {
                            at.a = 2;
                        }
                        else if (char1 == '?' && at.a == 1 && at.c == "") {
                            at.a = 4;
                        }
                        else if (Character.isWhitespace(char1)) {
                            if (!at.c.equals("")) {
                                n = 1;
                            }
                        }
                        else {
                            if (char1 <= ' ') {
                                this.c("Illegal Character");
                                return false;
                            }
                            b3 = true;
                        }
                    }
                    else {
                        b3 = true;
                    }
                    if (!b3) {
                        continue;
                    }
                    if (at.a == 0) {
                        at.a = 3;
                    }
                    if (at.a == 1) {
                        if (n != 0) {
                            if (n2 == 0 || (n3 != 0 && s2.length() > 0)) {
                                if (n2 != 0) {
                                    at.e.put(this.a(s), this.a(s2));
                                    s = "";
                                    s2 = "";
                                }
                                else {
                                    n2 = 1;
                                }
                                n3 = 0;
                            }
                            n = 0;
                        }
                        String c = "";
                        int n5 = 0;
                        if (n2 == 0) {
                            c = at.c;
                            n5 = 0;
                        }
                        else if (char1 == '=') {
                            n3 = 1;
                        }
                        else if (n3 == 0) {
                            c = s;
                            n5 = 1;
                        }
                        else {
                            n5 = 2;
                            c = s2;
                        }
                        final String string2 = c + char1;
                        if (n5 == 0 && !string2.equals("=")) {
                            at.c = string2;
                        }
                        else if (n5 == 1 && !string2.equals("=")) {
                            s = string2;
                        }
                        else {
                            if (string2.equals("=")) {
                                continue;
                            }
                            s2 = string2;
                        }
                    }
                    else {
                        if (n != 0) {
                            at.c += " ";
                            n = 0;
                        }
                        at.c += char1;
                    }
                }
            }
            if (s.length() > 0) {
                at.e.put(this.a(s), this.a(s2));
            }
            at.c = this.a(at.c);
        } while (b);
        return true;
    }
    
    public void c(final String b) {
        this.e = true;
        this.b = b;
    }
    
    public void a() {
        this.g = "";
        this.c = 1;
        this.e = false;
        this.b = "";
    }
}
