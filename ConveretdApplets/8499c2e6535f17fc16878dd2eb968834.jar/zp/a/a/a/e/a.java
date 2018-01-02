// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.e;

import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Dimension;

public class a implements Runnable
{
    private static final String e = "LP Core Error: Connection with IIP Server failed.";
    private static final String c = "LP Core Error: Authorization failed.";
    private static final String null = "Exception in Level";
    private static final String a = "fif=";
    private static final String void = "&til=";
    private static final String char = "&obj=iip,1.0";
    private static final char byte = ',';
    private static final char long = '-';
    private int try;
    public e[][] do;
    public Dimension int;
    public Dimension for;
    private b f;
    private URL b;
    private String new;
    private byte[][] goto;
    private f g;
    private StringBuffer case;
    public int if;
    private boolean else;
    private boolean d;
    
    public a(final int try1, final Dimension int1, final Dimension for1, final b f, final String new1, final f g, final byte[][] goto1) {
        this.try = try1;
        this.do = new e[int1.width][int1.height];
        this.int = int1;
        this.for = for1;
        this.f = f;
        this.b = this.f.a();
        this.new = new1;
        this.g = g;
        this.goto = goto1;
        this.case = new StringBuffer(4096).append("fif=").append(this.new);
        this.if = 0;
    }
    
    public boolean a(final int n, final int n2, final int[] array) {
        return this.do[n][n2] != null && this.do[n][n2].a(this.b, array);
    }
    
    public void a(final int n, final int n2, final int n3) {
        final int n4 = n * this.int.width;
        int n5 = -1;
        if (this.do[n2][n] == null && n2 == n3) {
            this.a(n4 + n2);
            return;
        }
        for (int i = n2; i <= n3; ++i) {
            if (this.do[i][n] == null && n5 == -1) {
                n5 = n4 + i;
            }
            if ((this.do[i][n] != null || i == n3) && n5 > -1) {
                int n6;
                if (i == n3 && this.do[i][n] == null) {
                    n6 = n4 + i;
                }
                else {
                    n6 = n4 + i - 1;
                }
                if (n6 > n5) {
                    this.if(n5, n6);
                }
                else {
                    this.a(n6);
                }
                n5 = -1;
            }
        }
    }
    
    private void if(final int n, final int n2) {
        this.case.append("&til=");
        this.case.append(this.try);
        this.case.append(',');
        this.case.append(n);
        this.case.append('-');
        this.case.append(n2);
        this.if += n2 - n + 1;
    }
    
    public void a(final int n, final int n2) {
        final int n3 = n2 * this.int.width;
        if (this.do[n][n2] == null) {
            this.a(n3 + n);
        }
    }
    
    private void a(final int n) {
        this.case.append("&til=");
        this.case.append(this.try);
        this.case.append(',');
        this.case.append(n);
        ++this.if;
    }
    
    private DataInputStream a() {
        DataInputStream a = null;
        try {
            this.case.append("&obj=iip,1.0");
            a = this.f.a(this.case.toString());
            this.case.setLength(0);
            this.case.append("fif=").append(this.new);
        }
        catch (IOException ex) {
            System.err.println("LP Core Error: Connection with IIP Server failed.");
        }
        if (a == null) {
            System.err.println("LP Core Error: Authorization failed.");
        }
        return a;
    }
    
    private void a(final DataInputStream dataInputStream) throws Exception {
        final int if1 = this.if;
        for (int n = 1; n <= this.if && !this.else; ++n) {
            final e e = new e(dataInputStream, this.goto, this.try);
            if (e != null) {
                final int a = e.a();
                this.do[a % this.int.width][a / this.int.width] = e;
                this.g.do(100 * n / if1);
            }
        }
        this.g.do(100);
        try {
            dataInputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public void do() {
        if (!this.d) {
            this.else = false;
            this.d = true;
            new Thread(this).start();
        }
    }
    
    public void for() {
        this.else = true;
        while (this.d) {
            Thread.yield();
        }
    }
    
    public void if() {
        this.if = 0;
    }
    
    public void run() {
        try {
            DataInputStream a = null;
            if (this.if > 0) {
                a = this.a();
            }
            if (a != null) {
                this.a(a);
            }
        }
        catch (Exception ex) {
            System.err.println("Exception in Level" + this.try);
            this.if = 0;
        }
        this.d = false;
    }
}
