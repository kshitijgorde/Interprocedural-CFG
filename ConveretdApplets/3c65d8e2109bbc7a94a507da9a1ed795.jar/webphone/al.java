// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import d.b.a.b;
import c.f;
import b.a;
import d.b.a.q;

public class al
{
    aw h;
    q r;
    q void;
    q for;
    a4 i;
    a n;
    f s;
    b d;
    b b;
    b long;
    int l;
    int a;
    byte[] f;
    int c;
    byte[] o;
    byte[] try;
    int if;
    byte[] k;
    byte[] q;
    byte[] j;
    int int;
    int m;
    int e;
    boolean byte;
    byte[] goto;
    boolean g;
    int new;
    int p;
    int else;
    byte[] char;
    byte[] case;
    int do;
    t t;
    
    public al(final t t) {
        this.h = null;
        this.r = null;
        this.void = null;
        this.for = null;
        this.i = null;
        this.n = null;
        this.s = null;
        this.d = null;
        this.b = null;
        this.long = null;
        this.l = 0;
        this.a = 4;
        this.f = null;
        this.c = 4;
        this.o = null;
        this.try = null;
        this.if = 4;
        this.k = null;
        this.q = null;
        this.j = null;
        this.int = 0;
        this.m = -8;
        this.e = -9;
        this.byte = true;
        this.goto = null;
        this.g = false;
        this.new = 2;
        this.p = 320;
        this.else = 640;
        this.char = null;
        this.case = null;
        this.do = 240;
        this.t = null;
        try {
            this.t = t;
            this.h = this.t.d;
            this.byte = this.h.bl;
            this.goto = new byte[30000];
            this.f = new byte[this.a];
            this.o = new byte[this.c];
            this.try = new byte[8];
            this.k = new byte[this.if];
            this.q = new byte[8];
            this.j = new byte[16500];
            this.char = new byte[this.else * 2 + 32];
            this.case = new byte[this.else * 2 + 32];
            this.t.bs = this.h.for(1, 89999);
            this.t.V = this.h.for(1, 5000);
            this.t.aX = this.h.for(1, 99999);
            this.h.ey = this.t.aX;
        }
        catch (Exception ex) {
            this.h.a(3, "CodecDecode.Construnct ", ex);
        }
    }
    
    public int a(final byte[] array, final int n, final byte[] array2, final int n2, final int l) {
        try {
            if (n < 160) {
                return n2;
            }
            this.l = l;
            this.do = 8 * this.new * this.h.for(this.h.a(this.l, false));
            int n3;
            if (this.l == 8) {
                n3 = n / 2;
                ah.if(array, 0, this.char, 0, n3, false);
            }
            else if (this.l == 0) {
                n3 = n / 2;
                ah.try(array, 0, this.char, 0, n3, false);
            }
            else if (this.l == 3 && this.h.e5 > 0) {
                if (this.i == null) {
                    this.i = new a4(this.h);
                }
                n3 = this.i.if(array, n, this.char);
            }
            else if (this.l == 18 && this.h.bY > 0) {
                if (this.n == null) {
                    (this.n = new a(this.h)).a(true);
                }
                n3 = this.n.if(array, n, this.char);
            }
            else if (this.l == this.h.p && this.h.bv > 0) {
                if (this.s == null) {
                    (this.s = new f(this.h)).a(true);
                }
                n3 = this.s.if(array, n, this.char);
            }
            else if (this.l == this.h.T && this.h.z > 0) {
                if (this.d == null) {
                    (this.d = new b()).a(0, 3, 8000, 1);
                    this.d.int().int(3);
                }
                this.d.a(array, 0, n);
                n3 = this.d.a(this.char, 0);
            }
            else if (this.l == this.h.s && this.h.dl > 0 && this.byte) {
                if (this.b == null) {
                    (this.b = new b()).a(1, 6, 16000, 1);
                    this.b.int().int(3);
                }
                this.b.a(array, 0, n);
                n3 = this.b.a(this.char, 0);
            }
            else if (this.l == this.h.ea && this.h.cL > 0 && this.byte) {
                if (this.long == null) {
                    (this.long = new b()).a(2, 8, 32000, 1);
                    this.long.int().int(3);
                }
                this.long.a(array, 0, n);
                n3 = this.long.a(this.char, 0);
            }
            else if (this.l == 104 && this.h.z > 0) {
                if (this.d == null) {
                    (this.d = new b()).a(0, 3, 8000, 1);
                    this.d.int().int(3);
                }
                this.d.a(array, 0, n);
                n3 = this.d.a(this.char, 0);
            }
            else if (this.l == 105 && this.h.dl > 0 && this.byte) {
                if (this.b == null) {
                    (this.b = new b()).a(1, 6, 16000, 1);
                    this.b.int().int(3);
                }
                this.b.a(array, 0, n);
                n3 = this.b.a(this.char, 0);
            }
            else if (this.l == 106 && this.h.cL > 0 && this.byte) {
                if (this.long == null) {
                    (this.long = new b()).a(2, 8, 32000, 1);
                    this.long.int().int(3);
                }
                this.long.a(array, 0, n);
                n3 = this.long.a(this.char, 0);
            }
            else {
                n3 = n / 2;
                ah.try(array, 0, this.char, 0, n3, false);
            }
            final t t = this.t;
            ++t.V;
            if (this.t.V > 65533) {
                this.t.V = 2;
            }
            final int n4 = 2;
            final boolean b = false;
            final boolean b2 = false;
            final boolean b3 = false;
            final boolean b4 = false;
            final boolean b5 = false;
            final t t2 = this.t;
            t2.bs += this.do;
            final byte[] for1 = webphone.r.for((((((((b5 ? 1 : 0) | n4) << 1 | (b ? 1 : 0)) << 1 | (b2 ? 1 : 0)) << 4 | (b3 ? 1 : 0)) << 1 | (b4 ? 1 : 0)) << 7 | this.l) << 16 | this.t.V);
            final byte[] for2 = webphone.r.for((int)this.t.bs);
            final byte[] for3 = webphone.r.for((int)this.t.aX);
            final int n5 = 0;
            System.arraycopy(for1, 0, this.case, n5, for1.length);
            final int n6 = n5 + for1.length;
            System.arraycopy(for2, 0, this.case, n6, for2.length);
            final int n7 = n6 + for2.length;
            System.arraycopy(for3, 0, this.case, n7, for3.length);
            final int n8 = n7 + for3.length;
            System.arraycopy(this.case, 0, array2, n2, n8);
            System.arraycopy(this.char, 0, array2, n2 + n8, n3);
            return n2 + n8 + n3;
        }
        catch (Exception ex) {
            this.h.a(3, "CodecDecode.Encode ", ex);
            return n2;
        }
    }
    
    public int a(final byte[] array, final int n, final byte[] array2, final int n2) {
        int n3 = 0;
        try {
            if (n < 20 || n > 36000) {
                if (this.h.eK > 4) {
                    this.h.a(4, "WARNING, (rtp) packet_length is " + this.h.c(n));
                }
                return n2;
            }
            n3 = 1;
            System.arraycopy(array, 0, this.f, 0, this.a);
            n3 = 2;
            final int a = webphone.r.a(this.f);
            n3 = 3;
            this.l = (a >>> 16 & 0x7F);
            final int a2 = this.a;
            n3 = 4;
            System.arraycopy(array, a2, this.o, 0, this.c);
            System.arraycopy(this.o, 0, this.try, 4, 4);
            webphone.r.do(this.try);
            final int n4 = a2 + this.c;
            n3 = 5;
            n3 = 6;
            System.arraycopy(array, n4, this.k, 0, this.if);
            System.arraycopy(this.k, 0, this.q, 4, 4);
            final long do1 = webphone.r.do(this.q);
            final int n5 = n4 + this.if;
            n3 = 7;
            final int n6 = n - n5;
            if (n6 < 10) {
                if (this.h.eK > 4) {
                    this.h.a(4, "WARNING, (rtp) payload_length is " + this.h.c(n6));
                }
                return n2;
            }
            if (this.int < 15 && this.h.ey == do1) {
                ++this.int;
                if (this.h.eK > 4) {
                    this.h.a(4, "WARNING, (rtp) media loopback detected with ssrc " + this.h.if(do1));
                }
                return n2;
            }
            this.int = 0;
            final byte[] array3 = new byte[n6];
            n3 = 8;
            System.arraycopy(array, n5, array3, 0, n6);
            n3 = 9;
            Label_0914: {
                if (n6 == this.e) {
                    if (this.m >= 0) {
                        if (this.l != 0) {
                            if (this.l != 8) {
                                if (this.l != 3) {
                                    if (this.l != 18) {
                                        if (this.l != this.h.p || this.h.bv <= 0) {
                                            if (this.l != this.h.T || this.h.z <= 0) {
                                                if (this.l != this.h.ea || this.h.cL <= 0) {
                                                    if (this.l != this.h.s || this.h.dl <= 0) {
                                                        if (this.l != 97 || this.h.bv <= 0) {
                                                            if (this.l != 104 || this.h.z <= 0) {
                                                                if (this.l != 105 || this.h.dl <= 0) {
                                                                    if (this.l != 106 || this.h.cL <= 0) {
                                                                        if (this.h.dl <= 0 || (this.l != this.h.s && (this.l != 105 || this.l == this.h.ea || this.l == this.h.T)) || !this.byte) {
                                                                            if (this.h.cL <= 0 || (this.l != this.h.ea && (this.l != 106 || this.l == this.h.s || this.l == this.h.T)) || !this.byte) {
                                                                                if (this.h.z > 0) {
                                                                                    if (this.l == this.h.T) {
                                                                                        break Label_0914;
                                                                                    }
                                                                                    if (this.l == 104) {
                                                                                        break Label_0914;
                                                                                    }
                                                                                }
                                                                                if (this.l != 19) {
                                                                                    if (this.l != 13) {
                                                                                        this.l = this.m;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int e;
            if (this.l == 0) {
                ah.int(array3, 0, this.goto, 0, n6, false);
                e = n6 * 2;
            }
            else if (this.l == 8) {
                ah.for(array3, 0, this.goto, 0, n6, false);
                e = n6 * 2;
            }
            else if (this.l == 3) {
                if (this.i == null) {
                    this.g = true;
                    this.i = new a4(this.h);
                }
                e = this.i.a(array3, n6, this.goto);
            }
            else if (this.l == 18) {
                if (this.n == null) {
                    this.g = true;
                    (this.n = new a(this.h)).a(false);
                }
                e = this.n.a(array3, n6, this.goto);
            }
            else if (this.l == this.h.p) {
                if (this.s == null) {
                    this.g = true;
                    (this.s = new f(this.h)).a(false);
                }
                e = this.s.do(array3, n6, this.goto);
            }
            else if (this.h.dl > 0 && (this.l == this.h.s || (this.l == 105 && this.l != this.h.ea && this.l != this.h.T)) && this.byte) {
                if (this.void == null) {
                    this.g = true;
                    (this.void = new q()).a(1, 16000, 1, true);
                }
                this.void.a(array3, 0, n6);
                e = this.void.a(this.goto, 0);
            }
            else if (this.h.cL > 0 && (this.l == this.h.ea || (this.l == 106 && this.l != this.h.s && this.l != this.h.T)) && this.byte) {
                if (this.for == null) {
                    this.g = true;
                    (this.for = new q()).a(2, 32000, 1, true);
                }
                this.for.a(array3, 0, n6);
                e = this.for.a(this.goto, 0);
            }
            else if (this.h.z > 0 && (this.l == this.h.T || this.l == 104)) {
                if (this.r == null) {
                    this.g = true;
                    (this.r = new q()).a(0, 8000, 1, true);
                }
                this.r.a(array3, 0, n6);
                e = this.r.a(this.goto, 0);
            }
            else if (this.l > 96 && (this.h.z > 0 || this.l == this.h.T) && this.r != null) {
                this.r.a(array3, 0, n6);
                e = this.r.a(this.goto, 0);
            }
            else if (this.l > 96 && (this.h.dl > 0 || this.l == this.h.s) && this.void != null && this.byte) {
                this.void.a(array3, 0, n6);
                e = this.void.a(this.goto, 0);
            }
            else if (this.l > 96 && (this.h.cL > 0 || this.l == this.h.ea) && this.for != null && this.byte) {
                this.for.a(array3, 0, n6);
                e = this.for.a(this.goto, 0);
            }
            else {
                if (this.l == 19 || this.l == 13) {
                    this.g = true;
                    return n2;
                }
                this.g = true;
                return n2;
            }
            this.m = this.l;
            this.e = e;
            System.arraycopy(this.goto, 0, array2, n2, e);
            return n2 + e;
        }
        catch (Exception ex) {
            this.h.a(3, "codecdecodedecode " + this.h.c(n3), ex);
            return n2;
        }
    }
}
