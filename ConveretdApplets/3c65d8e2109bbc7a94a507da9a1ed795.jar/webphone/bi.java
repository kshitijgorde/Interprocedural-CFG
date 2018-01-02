// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class bi extends Thread
{
    public List try;
    boolean new;
    boolean a;
    aw int;
    bc do;
    private Object if;
    int for;
    
    public bi(final bc do1) {
        this.try = null;
        this.new = false;
        this.a = false;
        this.int = null;
        this.do = null;
        this.if = null;
        this.for = 6000;
        this.do = do1;
        this.int = this.do.f;
        this.try = Collections.synchronizedList(new LinkedList<Object>());
    }
    
    public void do() {
        try {
            this.new = true;
            this.if();
        }
        catch (Exception ex) {}
    }
    
    public void if() {
    }
    
    boolean do(final bf bf) {
        final long do1 = this.int.do();
        final boolean a = this.a(bf);
        if (this.int.eK > 3 && this.int.do() - do1 > 800L) {
            this.int.a(4, "WARNING, sender send took " + this.int.if((this.int.do() - do1) / 1000L) + " sec for " + this.int.c(bf.a) + " bytes");
        }
        return a;
    }
    
    boolean a(final bf bf) {
        try {
            if (bf == null) {
                return false;
            }
            if (this.int.dK == 0 || (bf.for && this.int.dK != 3 && this.int.dK != 4 && this.int.dK != 5)) {
                if (this.do.l == null) {
                    this.int.a(4, "ERROR,mainudp is invalid when try to send " + this.int.c(bf.a) + " bytes");
                    return false;
                }
                return this.do.l.a(bf.try, bf.do, bf.int, bf.a, bf.if);
            }
            else if (this.int.dK == 3 && this.int.aX > 0) {
                if (this.do.new == null) {
                    this.int.a(4, "ERROR,mainhttp is invalid when try to send " + this.int.c(bf.a) + " bytes");
                    return false;
                }
                return this.do.new.a(bf.try, bf.do, bf.int, bf.a, bf.if, bf.for);
            }
            else {
                if (this.do.m == null) {
                    this.int.a(4, "ERROR,maintcp is invalid when try to send " + this.int.c(bf.a) + " bytes");
                    return false;
                }
                return this.do.m.a(bf.try, bf.do, bf.int, bf.a, bf.if, bf.for);
            }
        }
        catch (Exception ex) {
            this.int.a(2, "senderSend", ex);
            return false;
        }
    }
    
    public boolean if(final bf bf) {
        if (this.try == null) {
            return false;
        }
        synchronized (this.try) {
            this.try.add(bf);
            this.try.notifyAll();
        }
        return true;
    }
    
    public bf a() throws InterruptedException {
        if (this.try == null) {
            return null;
        }
        synchronized (this.try) {
            while (this.try.isEmpty()) {
                this.try.wait();
            }
            return this.try.remove(0);
        }
    }
    
    public void run() {
        Label_0045: {
            if (this.int.dK != 3 && this.int.dK != 4) {
                if (this.int.dK != 5) {
                    break Label_0045;
                }
            }
            try {
                this.setPriority(10);
            }
            catch (Exception ex2) {}
        }
        while (!this.a) {
            if (this.new) {
                this.a = true;
            }
            int n = 2;
            try {
                n = 3;
                if (this.int.dK == 4 || this.int.dK == 5 || (this.int.dK == 3 && this.int.aX > 0)) {
                    n = 4;
                    int n2 = 1;
                    final bf bf = new bf();
                    bf.new = this.int;
                    bf.int = new byte[this.for + 700];
                    n = 5;
                    if (this.new) {
                        break;
                    }
                    synchronized (this.try) {
                        n = 6;
                        while (this.try.isEmpty()) {
                            this.try.wait();
                        }
                        n = 7;
                        for (int i = 0; i < this.try.size(); ++i) {
                            n = 8;
                            final bf bf2 = this.try.get(i);
                            if (bf2 == null) {
                                n = 9;
                                this.try.remove(i);
                                --i;
                                this.int.a(4, "WARNING, sender buff null");
                            }
                            else {
                                n = 10;
                                if (n2 != 0 || (bf.do == bf2.do && bf.for == bf2.for && bf.if == bf2.if && bf.try.equals(bf2.try))) {
                                    n = 11;
                                    if (n2 != 0) {
                                        n = 12;
                                        n2 = 0;
                                        bf.try = bf2.try;
                                        bf.do = bf2.do;
                                        bf.if = bf2.if;
                                        bf.for = bf2.for;
                                    }
                                    if (bf2.a > this.for) {
                                        bf2.a = 160;
                                        this.int.a(4, "WARNING, sender buff size overflow1");
                                    }
                                    n = 13;
                                    if (bf.a + bf2.a >= this.for) {
                                        this.int.a(4, "WARNING, sender buff size overflow2 " + this.int.c(bf.a) + " " + this.int.c(bf2.a));
                                        break;
                                    }
                                    n = 14;
                                    System.arraycopy(bf2.int, 0, bf.int, bf.a, bf2.a);
                                    final bf bf3 = bf;
                                    bf3.a += bf2.a;
                                    this.try.remove(i);
                                    --i;
                                }
                            }
                        }
                    }
                    n = 15;
                    this.do(bf);
                    n = 16;
                }
                else {
                    n = 17;
                    final bf a = this.a();
                    n = 18;
                    if (a != null) {
                        n = 19;
                        this.do(a);
                    }
                    n = 20;
                }
                n = 21;
            }
            catch (Exception ex) {
                this.int.if(3, "senderthread " + this.int.c(n), ex);
            }
        }
        this.do();
    }
}
