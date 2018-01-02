import java.io.IOException;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class abljems extends Thread
{
    public boolean a;
    public long b;
    private abljema c;
    private Object d;
    private int e;
    private Vector f;
    private Vector g;
    private Vector h;
    private int i;
    private boolean j;
    private boolean k;
    private long l;
    private int m;
    private boolean n;
    private String o;
    
    abljems(final abljema c) {
        super(String.valueOf(c.l) + "abljems");
        this.a = false;
        this.d = new Object();
        this.i = 10;
        this.j = false;
        this.k = false;
        this.l = 3L;
        this.m = 300;
        this.n = false;
        this.o = "ARC1800818007";
        this.c = c;
        this.e = abljema.t() + this.i;
        this.f = new Vector();
        this.g = new Vector();
        this.h = new Vector();
    }
    
    public void run() {
        if (this.c.d0) {
            return;
        }
        while (this.c.az) {
            this.i = (this.c.hp ? 10 : 60);
            if (this.c.gb || this.c.df || this.c.c0) {
                this.j = true;
                if (this.c.dd) {
                    this.b("NEDCAP1");
                    this.b(new String("NEDVER").concat(this.c.g));
                    if (this.c.h2) {
                        this.b("NEDGSA");
                    }
                    if (this.c.w) {
                        this.b("NEDOPT" + this.d());
                    }
                    this.c.dd = false;
                }
                if (this.c.de) {
                    if (this.c.dx != null) {
                        this.c(this.c.dx.insert(0, "NEDUAG"));
                    }
                    this.c.dx = new StringBuffer("");
                    this.c.de = false;
                }
                if (this.c.dc) {
                    this.g();
                    this.c.dc = false;
                }
                if (this.c.db) {
                    this.b("NEDSOS");
                    this.c.db = false;
                }
                if (this.c.c9) {
                    this.e();
                    this.c.c9 = false;
                }
                this.j = false;
                if (this.a) {
                    this.f();
                    this.a = false;
                }
                if (this.h() > 0) {
                    this.e = abljema.t() + this.i;
                }
            }
            if (abljema.t() > this.e) {
                if (!this.c.bd) {
                    if (abljema.t() > this.e + 60000) {
                        abljem.d("Initial TCP/IP timeout");
                        this.c.az = false;
                    }
                }
                else {
                    if (!this.c.ie) {
                        this.c();
                    }
                    this.e = abljema.t() + this.i;
                }
            }
            try {
                this.a(System.currentTimeMillis());
            }
            catch (Throwable t) {
                abljem.d("delayed dequeue failed");
                t.printStackTrace();
            }
            if (!abljema.a(100L)) {
                return;
            }
        }
        if (this.c.ie && this.c.a2 == null) {
            this.j = true;
            this.b("NEDEND");
        }
    }
    
    public boolean a(final long n) {
        boolean b = false;
        synchronized (this.g) {
            for (int size = this.g.size(), n2 = 0; n2 < this.g.size() && size-- > 0; ++n2) {
                if (n == 0L || n >= (long)this.g.elementAt(n2)) {
                    this.b((byte[])this.h.elementAt(n2));
                    b = true;
                    this.g.removeElementAt(n2);
                    this.h.removeElementAt(n2);
                    --n2;
                }
            }
        }
        // monitorexit(this.g)
        return b;
    }
    
    private void c() {
        try {
            this.c.at.write(this.c.dm, 0, 4);
            this.c.at.writeInt(0);
            this.c.at.write(this.c.dn, 0, 4);
            this.c.at.flush();
        }
        catch (IOException ex) {
            abljem.d("IOException 20");
            this.c.az = false;
        }
    }
    
    private String d() {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append(this.c.f()).toString())).append(this.c.g()).toString()) + (this.c.eb ? "E" : " ");
    }
    
    private void e() {
        String concat = "NEDNMN";
        if (this.c.gv != null) {
            concat = concat.concat(this.c.gv);
        }
        this.b(String.valueOf(concat) + this.c.g());
    }
    
    private void f() {
        String s = "NEDNAM";
        int n = 1;
        final byte[] array = new byte[1000];
        if (this.c.g8 == null) {
            return;
        }
        if (!this.c.gb) {
            n = 2;
            s = "NEDNMA";
        }
        if (this.c.gn && this.c.go && this.c.gu) {
            n = 3;
            s = "NEDNMP";
        }
        final int ab = this.c.g8.ab;
        final int n2 = abljema.n(this.c.g8.p, 0, this.c.g8.p.length);
        final int length = s.length();
        s.getBytes(0, length, array, 0);
        System.arraycopy(this.c.g8.o, 0, array, length, ab);
        int n3 = length + ab;
        array[n3++] = 0;
        switch (n) {
            case 1: {
                array[n3++] = (byte)n2;
                array[n3++] = (byte)(n2 >> 8);
                break;
            }
            case 2: {
                array[n3++] = (byte)Character.forDigit(n2 >> 12 & 0xF, 16);
                array[n3++] = (byte)Character.forDigit(n2 >> 8 & 0xF, 16);
                array[n3++] = (byte)Character.forDigit(n2 >> 4 & 0xF, 16);
                array[n3++] = (byte)Character.forDigit(n2 & 0xF, 16);
                break;
            }
            case 3: {
                System.arraycopy(this.c.g8.p, 0, array, n3, 40);
                n3 += 40;
                array[n3++] = 0;
                break;
            }
        }
        if (this.c.bp) {
            array[n3++] = (byte)(this.c.bo ? 49 : 56);
        }
        else if (n == 3) {
            array[n3++] = 32;
        }
        if (n3 > 0) {
            final byte[] array2 = new byte[n3];
            System.arraycopy(array, 0, array2, 0, n3);
            this.b(array2);
        }
    }
    
    public void a() {
        this.a("NEDOON");
    }
    
    public void a(final StringBuffer sb) {
        this.b(sb.insert(0, "NEDUAS"));
    }
    
    public void a(final String s, final int n) {
        if (s == null || s.length() == 0) {
            return;
        }
        final String concat = new String("NEDDLT").concat(s);
        int length = concat.length();
        final byte[] array = new byte[length + 1];
        concat.getBytes(0, length, array, 0);
        array[length++] = 0;
        this.a(array, n);
    }
    
    public void b() {
        this.a("NEDLST");
    }
    
    private void g() {
        String concat = "NEDRCI";
        if (this.c.gv != null) {
            concat = concat.concat(this.c.gv);
        }
        this.b(concat.concat(new String(this.c.gx, 0)));
        this.c.k(this.c.gy, 0, this.c.gz);
        this.c.df = true;
    }
    
    public void b(final StringBuffer sb) {
        this.a(new String(sb));
    }
    
    public void a(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length];
        s.getBytes(0, length, array, 0);
        this.b(array);
    }
    
    public void a(final byte[] array) {
        this.b(array);
    }
    
    private void a(final byte[] array, final int n) {
        if (n < 1) {
            this.b(array);
            return;
        }
        final Long n2 = new Long(System.currentTimeMillis() + n * 1000);
        synchronized (this.g) {
            this.g.addElement(n2);
            this.h.addElement(array);
        }
        // monitorexit(this.g)
    }
    
    private void b(final byte[] array) {
        if (this.c.j.dy[this.c.j.ag] != null && !abljema.a(array, 0, "NEDCSR")) {
            if (array.length >= 3 && array[0] != 42 && !abljema.a(array, 0, "INJ")) {
                this.c.bn.a(new String(array, 0, 0, 3));
            }
            return;
        }
        synchronized (this.d) {
            this.f.addElement(array);
        }
        // monitorexit(this.d)
    }
    
    private int h() {
        int n = 0;
        while (this.f.size() > 0) {
            this.a(this.f.elementAt(0), true);
            synchronized (this.d) {
                this.f.removeElementAt(0);
            }
            // monitorexit(this.d)
            ++n;
        }
        return n;
    }
    
    private void c(final StringBuffer sb) {
        this.b(new String(sb));
    }
    
    private void b(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length];
        s.getBytes(0, length, array, 0);
        this.c(array);
    }
    
    private void c(final byte[] array) {
        if (!this.j) {
            abljem.d("Error 27");
            this.c.az = false;
            return;
        }
        this.a(array, true);
    }
    
    private void a(byte[] array, final boolean b) {
        int length = array.length;
        if (length < 3) {
            abljem.d("Send length=" + length + " data=" + ((length > 0) ? array[0] : 0) + " " + ((length > 1) ? array[1] : 0));
        }
        if (this.c.v && length >= 50) {
            final byte[] f = abljema.f(array, 0, length);
            length = f.length + 3;
            array = new byte[length];
            array[0] = 88;
            array[1] = 48;
            array[2] = 50;
            System.arraycopy(f, 0, array, 3, f.length);
        }
        if (!this.c.gb && !this.c.df && length > 0 && !this.c.c0) {
            abljem.d("IOException 25");
            this.c.az = false;
            return;
        }
        if (this.c.gb) {
            this.c.h(array, 0, length);
        }
        if (this.c.df) {
            this.c.l(array, 0, length);
        }
        if (this.c.ie) {
            this.c.a(array);
        }
        else {
            this.d(array);
        }
    }
    
    private void d(final byte[] array) {
        try {
            this.c.at.write(this.c.dm, 0, 4);
            this.c.at.writeInt(array.length);
            this.c.at.write(array, 0, array.length);
            this.c.at.write(this.c.dn, 0, 4);
            this.c.at.flush();
        }
        catch (IOException ex) {
            abljem.d("IOException 26");
            this.c.az = false;
        }
    }
}
