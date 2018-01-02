// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class nb extends kb
{
    private static f f;
    public static final Object g;
    public boolean h;
    public long i;
    public volatile long j;
    public long k;
    public long l;
    public long m;
    private static /* synthetic */ Class n;
    private static String[] z;
    
    public void a(final long m) {
        if (m < 10L) {
            throw new RuntimeException(nb.z[3]);
        }
        this.m = m;
    }
    
    public void b(final long i) {
        if (i <= 0L || i > 100L) {
            throw new RuntimeException(nb.z[0] + i);
        }
        this.i = i;
    }
    
    protected void j() {
        this.c(nb.g);
        this.h = true;
        this.j = System.currentTimeMillis();
        this.k = 0L;
        this.l = 0L;
    }
    
    protected void k() {
        if (this.h) {
            final long currentTimeMillis = System.currentTimeMillis();
            long i = currentTimeMillis - this.j;
            if (i < this.i) {
                this.j = currentTimeMillis - this.i;
                i = this.i;
            }
            else if (i > 100L) {
                this.j += i - 100L;
                i = 100L;
            }
            this.k += (i - this.k) / 3L;
            if (this.k < this.i) {
                this.k = this.i;
            }
            final long n = this.j + this.k;
            Thread.yield();
            try {
                Thread.sleep(this.i);
            }
            catch (InterruptedException ex) {}
            this.h = false;
            this.a(r.a(this.l));
            long n2 = 0L;
            while (this.j + 10L <= n) {
                n2 += 10L;
                if (n2 >= this.m) {
                    this.a(t.a(this.l, this.m));
                    ++this.l;
                    n2 -= this.m;
                }
                this.j += 10L;
            }
            if (n2 != 0L) {
                this.a(t.a(this.l, n2));
                ++this.l;
            }
            this.a(s.a(this.l));
            this.a(q.a(this.l));
        }
        this.m();
    }
    
    public void receiveEvent(final q q) {
        if (this.h) {
            throw new RuntimeException(nb.z[1] + q + nb.z[2]);
        }
        this.h = true;
    }
    
    public static nb a(final kb kb) {
        final nb nb = (nb)neat.system.nb.f.a();
        nb.a(kb, neat.system.nb.g);
        return nb;
    }
    
    public void f() {
        nb.f.a(this);
    }
    
    public void g() {
        super.g();
        this.h = false;
        this.i = 20L;
        this.m = 10L;
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        nb.z = new String[] { z(z("7\"KV\u0002@<KW\u0015@$MU\u0000@=MV\f\r%I\u0002")), z(z("48MKE\t#\u0004L\r\u0005pW]\u0006\u000f>@\u0018)\u000f?T}\u000b\u0004pAN\u0000\u000e$\u001e")), z(z("@q")), z(z("-1\\Q\b\u0015=\u0004W\u0003@$M[\u000e@3EVB\u0014pF]E\f5WKE\u00148AVE\u00149GSE\u0015>ML_Q`")), z(z("\u000e5ELK\u0013)WL\u0000\r~JZ")) };
        nb.f = new f((nb.n != null) ? nb.n : (nb.n = a(nb.z[4])));
        g = z(z("4\u0019`\rP"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'e';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '`';
                            break;
                        }
                        case 1: {
                            c2 = 'P';
                            break;
                        }
                        case 2: {
                            c2 = '$';
                            break;
                        }
                        case 3: {
                            c2 = '8';
                            break;
                        }
                        default: {
                            c2 = 'e';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
