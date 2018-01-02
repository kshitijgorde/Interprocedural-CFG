// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

import JAVACharter.util.b;
import java.util.Date;

public class f extends e
{
    private Date[] new;
    
    public f() {
        this.new = new Date[0];
    }
    
    public Date try() {
        if (this.new.length > 0) {
            return this.new[this.new.length - 1];
        }
        return null;
    }
    
    public Date do(final int n) {
        try {
            if (super.if + n < 0) {
                return null;
            }
            return this.new[super.if + n];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Date if(final int n) {
        try {
            if (n < 0) {
                return null;
            }
            return this.new[n];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Date[] int() {
        final Date[] array = new Date[this.a()];
        System.arraycopy(this.new, super.if, array, 0, this.a());
        return array;
    }
    
    public int do(final Date date) {
        if (this.new.length == 0) {
            return -3;
        }
        if (b.a(date, this.new[0]) == -1) {
            return -2;
        }
        if (b.a(date, this.new[0]) == 0) {
            return -1;
        }
        if (b.a(date, this.new[0]) == 1 && b.a(date, this.new[this.new.length - 1]) == -1) {
            return 0;
        }
        if (b.a(date, this.new[this.new.length - 1]) == 0) {
            return 1;
        }
        if (b.a(date, this.new[this.new.length - 1]) == 1) {
            return 2;
        }
        return -3;
    }
    
    public int a(final Date date, final Date[] array) {
        final int a = b.a(date, array[0]);
        if (a == 0) {
            return 0;
        }
        for (int i = 0; i < array.length; ++i) {
            final int a2 = b.a(date, array[i]);
            if (a2 == 0) {
                return i;
            }
            if (a2 != a) {
                return i;
            }
        }
        return -8;
    }
    
    public int if(final Date date, final Date[] array) {
        int i = 0;
        int n = array.length - 1;
        while (i <= n) {
            final int n2 = i + (n - i) / 2;
            final int a = b.a(date, array[n2]);
            if (a < 0) {
                n = n2 - 1;
            }
            else {
                if (a <= 0) {
                    return n2;
                }
                i = n2 + 1;
            }
        }
        return i;
    }
    
    public int if(final Date date) {
        switch (this.do(date)) {
            case -3: {
                return -3;
            }
            case -2: {
                return -2;
            }
            case 2: {
                return -1;
            }
            default: {
                return this.if(date, this.new);
            }
        }
    }
    
    public int a(final Date date) {
        return this.if(date) - super.if;
    }
    
    public Date a(final int n) {
        if (this.new.length > 0) {
            return this.new[n];
        }
        return null;
    }
    
    public int for() {
        return this.new.length;
    }
    
    public int new() {
        return this.new.length;
    }
    
    public void if(final int n, final Object o) {
        final Date[] array = (Date[])o;
        final Date[] new1 = new Date[array.length + this.new.length];
        System.arraycopy(this.new, 0, new1, 0, n);
        System.arraycopy(array, 0, new1, n, array.length);
        System.arraycopy(this.new, n, new1, n + array.length, this.new.length - n);
        this.new = new1;
    }
    
    public void a(final int n, final Object o) {
        if (n < this.new.length) {
            this.new[n] = (Date)o;
        }
    }
    
    public void a(final Object o, final int n, final int n2) {
        final Date[] array = (Date[])o;
        final Date[] new1 = new Date[n2 + this.new.length];
        System.arraycopy(this.new, 0, new1, 0, this.new.length);
        System.arraycopy(array, n, new1, this.new.length, n2);
        this.new = new1;
    }
    
    public void a(final Object o, final int n) {
        final Date[] array = (Date[])o;
        final Date[] new1 = new Date[n + this.new.length];
        System.arraycopy(array, 0, new1, 0, n);
        System.arraycopy(this.new, 0, new1, n, this.new.length);
        this.new = new1;
    }
    
    public void if() {
        final Date[] array = this.new;
        final Date[] new1 = new Date[this.new.length - 1];
        System.arraycopy(array, 1, new1, 0, array.length - 1);
        this.new = new1;
        if (super.int >= array.length - 1) {
            this.a(super.if - 1, super.int);
        }
    }
    
    public void do() {
        final Date[] array = this.new;
        final Date[] new1 = new Date[this.new.length - 1];
        System.arraycopy(array, 0, new1, 0, array.length - 1);
        this.new = new1;
        if (super.int > array.length - 1) {
            this.a(super.if, super.int - 1);
        }
    }
}
