import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class d
{
    private e a;
    
    protected e a() {
        return this.a;
    }
    
    d(final InputStream inputStream) throws Exception {
        int n = 0;
        int n2 = 300;
        this.a = new e(".pdb", n2, n2);
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                Label_0084: {
                    try {
                        final String trim = dataInputStream.readLine().trim();
                        if (trim.length() > 1) {
                            if (trim.substring(0, 3).indexOf("END") == -1) {
                                break Label_0084;
                            }
                        }
                    }
                    catch (IOException ex) {}
                    catch (NullPointerException ex2) {}
                    break;
                    try {
                        final String trim;
                        if (trim.length() < 4) {
                            continue;
                        }
                        if (trim.substring(0, 6).indexOf("COMPND") != -1) {
                            this.a.h = String.valueOf(trim.substring(10)) + ".pdb";
                            continue;
                        }
                        if (trim.substring(0, 4).indexOf("ATOM") == -1 && trim.substring(0, 6).indexOf("HETATM") == -1) {
                            continue;
                        }
                        this.a.l[n] = trim.substring(12, 16).trim();
                        this.a.n[n] = trim.substring(17, 20);
                        this.a.o[n] = trim.substring(21, 22).charAt(0);
                        this.a.m[n] = (int)(Object)Float.valueOf(trim.substring(23, 26).trim());
                        this.a.c[n] = Float.valueOf(trim.substring(30, 38).trim());
                        this.a.b[n] = Float.valueOf(trim.substring(38, 46).trim());
                        this.a.a[n] = Float.valueOf(trim.substring(46, 54).trim());
                        if (++n >= n2) {
                            n2 += 100;
                            final e a = this.a.a(n2, n2);
                            this.a = null;
                            this.a = a;
                            continue;
                        }
                        continue;
                    }
                    catch (NumberFormatException ex3) {}
                }
                break;
            }
            dataInputStream.close();
        }
        catch (IOException ex4) {
            System.err.println("Cannot access data.");
        }
        final e a2 = this.a.a(n, (int)(n * 1.2f));
        this.a = null;
        this.a = a2;
    }
    
    d() {
        this.a = null;
    }
}
