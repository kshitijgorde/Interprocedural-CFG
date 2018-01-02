// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.sec.u;
import ji.io.h;
import ji.util.d;
import java.io.DataInputStream;
import ji.util.e;
import java.util.Hashtable;

public class o
{
    Hashtable a;
    String b;
    
    public o(final String s) {
        this.a = null;
        this.b = null;
        try {
            if (e.u(s)) {
                this.a = new Hashtable(50);
                final String[] array = new String[3];
                if (e.t(s) || e.v(s)) {
                    array[0] = "command.com";
                    array[1] = "/c";
                    array[2] = "set";
                }
                else {
                    array[0] = "cmd.exe";
                    array[1] = "/c";
                    array[2] = "set ";
                }
                final Process exec = Runtime.getRuntime().exec(array);
                DataInputStream dataInputStream = null;
                boolean b = false;
                try {
                    dataInputStream = new DataInputStream(exec.getInputStream());
                    boolean b2 = false;
                    int n = 0;
                    String line;
                    while ((line = dataInputStream.readLine()) != null) {
                        ++n;
                        try {
                            final int index = line.indexOf(61);
                            if (index >= 0) {
                                this.a.put(line.substring(0, index).trim().toLowerCase(), line.substring(index + 1).trim());
                                b2 = true;
                            }
                        }
                        catch (Exception ex) {
                            d.a(ex);
                        }
                        if ((e.t(s) || e.v(s)) && dataInputStream.available() == 0) {
                            break;
                        }
                        if (n > 1 && !b2) {
                            b = true;
                            h.d(s, "Windows Command prompt has been disabled so cannot locate Windows TEMP directory, defaulting to Java user.home path.");
                            break;
                        }
                    }
                }
                catch (Exception ex2) {
                    d.a(ex2);
                }
                finally {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                }
                if (!b) {
                    exec.waitFor();
                }
            }
        }
        catch (Exception ex3) {
            d.a(ex3);
        }
    }
    
    public final String a(final String s) {
        try {
            if (this.a != null) {
                return this.a.get(s.toLowerCase());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public final String a() {
        return this.a("userdnsdomain");
    }
    
    public final String b() {
        String s = this.a("temp");
        if (s == null) {
            s = this.a("tmp");
        }
        if (s != null) {
            try {
                s = new u(s, this.b).b();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            s = d.b(s, "\\", "/");
        }
        return s;
    }
    
    public final String b(final String s) {
        String s2 = this.b();
        if (s2 != null) {
            if (s2.endsWith("/") || s2.endsWith("\\")) {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s)));
            }
            else {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf("/".concat(String.valueOf(String.valueOf(s))))));
            }
        }
        return s2;
    }
}
