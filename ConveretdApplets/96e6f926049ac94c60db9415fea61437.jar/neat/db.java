// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public abstract class db extends bb
{
    private static f d;
    static final kb MEMBER_DEFAULT_TYPE;
    private j e;
    private static /* synthetic */ Class f;
    public static boolean g;
    private static String[] z;
    
    public void a(final kb kb, final bb bb) {
        if (kb == null) {
            throw new RuntimeException(db.z[0]);
        }
        this.e.a(kb.b(), bb);
    }
    
    public void a(final kb kb, final kb kb2) {
        if (kb == null) {
            throw new RuntimeException(db.z[0]);
        }
        this.e.a(kb.b(), kb2);
    }
    
    public boolean a(final kb kb) {
        return this.e.a(kb);
    }
    
    public boolean equals(final Object o) {
        return o instanceof db && ((db)o).e.equals(this.e);
    }
    
    public int a() {
        return this.e.c();
    }
    
    public r b() {
        return this.e.a();
    }
    
    public r c() {
        return this.e.b();
    }
    
    public kb a(final int n) {
        return (kb)this.e.a(n);
    }
    
    public Object b(final kb kb) {
        if (kb == null) {
            return null;
        }
        return this.e.b(kb);
    }
    
    public Object b(final int n) {
        return this.e.b(n);
    }
    
    public bb c(final kb kb) {
        final Object b = this.b(kb);
        if (b instanceof bb) {
            return (bb)b;
        }
        return null;
    }
    
    public bb c(final int n) {
        final Object b = this.b(n);
        if (b instanceof bb) {
            return (bb)b;
        }
        return null;
    }
    
    public kb d(final kb kb) {
        final Object b = this.b(kb);
        if (b instanceof kb) {
            return (kb)b;
        }
        return null;
    }
    
    public kb d(final int n) {
        final Object b = this.b(n);
        if (b instanceof kb) {
            return (kb)b;
        }
        return null;
    }
    
    public void d() {
        this.e.d();
    }
    
    public void b() {
        db.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)db.d.a();
    }
    
    public static db e() {
        return (db)db.d.a();
    }
    
    public void g() {
        super.g();
        this.e = j.e();
    }
    
    public void h() {
        super.h();
        this.d();
        this.e.f();
        this.e = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public db() {
        this.e = null;
    }
    
    static {
        db.z = new String[] { z(z("\u007f\u0010\u0006`\u000fA\u0000\u00014N\u0012\u000b\u0014yJ\u0012\u0003\u001af\u000fA\r\u0014p@EET")), z(z("\\\u0000\u0014`\u0001V\u0007")) };
        db.d = new f((db.f != null) ? db.f : (db.f = a(db.z[1])));
        MEMBER_DEFAULT_TYPE = kb.a(z(z("m\u0001\u0010rNG\t\u0001")));
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
        charArray[n] ^= '/';
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
                            c2 = '2';
                            break;
                        }
                        case 1: {
                            c2 = 'e';
                            break;
                        }
                        case 2: {
                            c2 = 'u';
                            break;
                        }
                        case 3: {
                            c2 = '\u0014';
                            break;
                        }
                        default: {
                            c2 = '/';
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
