// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.util.ResourceBundle;
import java.math.BigInteger;
import java.io.Serializable;

public class Frac implements MyOrder, Serializable
{
    public static final Frac Zero;
    public static final Frac Unit;
    public static final Frac Two;
    public static final int fraction = 0;
    public static final int decimal = 1;
    public static final int intfrac = 2;
    private int format;
    private BigInteger num;
    private BigInteger denom;
    
    static {
        Zero = new Frac(0L);
        Unit = new Frac(1L);
        Two = new Frac(2L);
    }
    
    private BigInteger gcm(final BigInteger a, final BigInteger b) {
        return a.gcd(b);
    }
    
    public void setFormat(final int i) {
        switch (i) {
            case 0: {
                this.format = 0;
                break;
            }
            case 1: {
                this.format = 1;
                break;
            }
            case 2: {
                this.format = 2;
                break;
            }
        }
    }
    
    public int getFormat() {
        return this.format;
    }
    
    public Frac() {
        this.format = 0;
        this.num = BigInteger.ZERO;
        this.denom = BigInteger.ONE;
    }
    
    private void myfrac(final BigInteger i) {
        this.num = i;
        this.denom = BigInteger.ONE;
    }
    
    private void myfrac(final long i) {
        this.myfrac(BigInteger.valueOf(i));
    }
    
    public Frac(final long i) {
        this.format = 0;
        this.myfrac(BigInteger.valueOf(i));
    }
    
    public Frac(final Frac f) {
        this.format = 0;
        this.num = f.num;
        this.denom = f.denom;
    }
    
    public Frac(final BigInteger n) {
        this.format = 0;
        this.myfrac(n);
    }
    
    public Frac(final long n, final long d) throws ArithmeticException {
        this.format = 0;
        this.myfrac(BigInteger.valueOf(n), BigInteger.valueOf(d));
    }
    
    public Frac(final BigInteger n, final BigInteger d) throws ArithmeticException {
        this.format = 0;
        this.myfrac(n, d);
    }
    
    private void myfrac(BigInteger bn, BigInteger bd) throws ArithmeticException {
        if (bd.equals(BigInteger.ZERO)) {
            throw new ArithmeticException(ResourceBundle.getBundle("org.namekata.fundamental.Frac").getString("CannotDivideByZero"));
        }
        if (bn.equals(BigInteger.ZERO)) {
            this.myfrac(0L);
        }
        else if (bn.signum() > 0 && bd.signum() > 0) {
            final BigInteger bg = this.gcm(bn, bd);
            this.num = bn.divide(bg);
            this.denom = bd.divide(bg);
        }
        else if (bn.signum() < 0 && bd.signum() < 0) {
            bn = bn.negate();
            bd = bd.negate();
            final BigInteger bg = this.gcm(bn, bd);
            this.num = bn.divide(bg);
            this.denom = bd.divide(bg);
        }
        else if (bn.signum() > 0 && bd.signum() < 0) {
            bn = bn.negate();
            bd = bd.negate();
            final BigInteger bg = this.gcm(bn, bd);
            this.num = bn.divide(bg);
            this.denom = bd.divide(bg);
        }
        else {
            final BigInteger bg = this.gcm(bn, bd);
            this.num = bn.divide(bg);
            this.denom = bd.divide(bg);
        }
    }
    
    public BigInteger intPart() {
        final BigInteger ret = this.num.divide(this.denom);
        return ret;
    }
    
    public Frac fracPart() {
        return this.sub(this.intPart());
    }
    
    public Frac add(final Frac x) {
        final BigInteger i = this.gcm(this.denom, x.denom);
        final BigInteger d = this.denom.divide(i);
        final BigInteger dd = x.denom.divide(i);
        BigInteger nnum = this.num.multiply(dd);
        nnum = nnum.add(x.num.multiply(d));
        final BigInteger ndenom = i.multiply(d).multiply(dd);
        return new Frac(nnum, ndenom);
    }
    
    public Frac add(final BigInteger x) {
        return new Frac(x).add(this);
    }
    
    public Frac add(final long x) {
        return this.add(BigInteger.valueOf(x));
    }
    
    public Frac sub(final Frac x) {
        return new Frac(this).add(x.mul(-1L));
    }
    
    public Frac sub(final BigInteger x) {
        return new Frac(this).add(new Frac(x.negate()));
    }
    
    public Frac sub(final long x) {
        return this.sub(BigInteger.valueOf(x));
    }
    
    public Frac mul(final Frac x) {
        return new Frac(this.num.multiply(x.num), this.denom.multiply(x.denom));
    }
    
    public Frac mul(final BigInteger x) {
        return new Frac(x).mul(this);
    }
    
    public Frac mul(final long x) {
        return this.mul(BigInteger.valueOf(x));
    }
    
    public Frac div(final Frac x) {
        return new Frac(this.num.multiply(x.denom), this.denom.multiply(x.num));
    }
    
    public Frac div(final BigInteger x) {
        return new Frac(this).div(new Frac(x));
    }
    
    public Frac div(final long x) {
        return this.div(BigInteger.valueOf(x));
    }
    
    public BigInteger getNum() {
        return this.num;
    }
    
    public BigInteger getDenom() {
        return this.denom;
    }
    
    public boolean equals(final Frac x) {
        return x != null && (this.num.equals(x.num) && this.denom.equals(x.denom));
    }
    
    public boolean equals(final BigInteger x) {
        return this.equals(new Frac(x));
    }
    
    public boolean equals(final long x) {
        return this.equals(BigInteger.valueOf(x));
    }
    
    @Override
    public boolean equals(final Object x) {
        return x != null && x.getClass() == Frac.class && this.equals((Frac)x);
    }
    
    @Override
    public int hashCode() {
        return (int)Double.doubleToLongBits(this.doubleValue());
    }
    
    public boolean isGreater(final Frac x) {
        final BigInteger g = this.gcm(this.denom, x.denom);
        final BigInteger d = this.denom.divide(g);
        final BigInteger dx = x.denom.divide(g);
        BigInteger tmp = this.num.multiply(dx);
        tmp = tmp.subtract(x.num.multiply(d));
        return tmp.signum() > 0;
    }
    
    public boolean isGreater(final BigInteger x) {
        return this.isGreater(new Frac(x));
    }
    
    public boolean isGreater(final long x) {
        return this.isGreater(BigInteger.valueOf(x));
    }
    
    @Override
    public boolean isGreater(final Object x) {
        return this.isGreater((Frac)x);
    }
    
    public boolean isLess(final Frac x) {
        final BigInteger g = this.gcm(this.denom, x.denom);
        final BigInteger d = this.denom.divide(g);
        final BigInteger dx = x.denom.divide(g);
        BigInteger tmp = this.num.multiply(dx);
        tmp = tmp.subtract(x.num.multiply(d));
        return tmp.signum() < 0;
    }
    
    public boolean isLess(final BigInteger x) {
        return this.isLess(new Frac(x));
    }
    
    public boolean isLess(final long x) {
        return this.isLess(BigInteger.valueOf(x));
    }
    
    @Override
    public boolean isLess(final Object x) {
        return this.isLess((Frac)x);
    }
    
    public boolean isInt() {
        return this.denom.equals(BigInteger.ONE);
    }
    
    @Override
    public String toString() {
        String str = "";
        if (this.isInt()) {
            return this.num.toString();
        }
        switch (this.format) {
            case 0: {
                str = String.valueOf(this.num.toString()) + "/" + this.denom.toString();
                break;
            }
            case 1: {
                final double n = this.num.doubleValue();
                final double d = this.denom.doubleValue();
                str = new StringBuilder().append(n / d).toString();
                break;
            }
            case 2: {
                final BigInteger bi = this.intPart();
                final int sig = bi.signum();
                if (sig > 0) {
                    str = String.valueOf(bi.toString()) + "+";
                }
                else if (sig < 0) {
                    str = bi.toString();
                }
                else {
                    str = "";
                }
                str = String.valueOf(str) + this.fracPart().num.toString() + "/" + this.fracPart().denom.toString();
                break;
            }
        }
        return str;
    }
    
    public double doubleValue() {
        return this.num.doubleValue() / this.denom.doubleValue();
    }
    
    public Frac max(final Frac x) {
        if (this.isLess(x)) {
            final Frac f = new Frac(x);
            return f;
        }
        final Frac f = new Frac(this);
        return f;
    }
    
    public Frac min(final Frac x) {
        if (this.isLess(x)) {
            final Frac f = new Frac(this);
            return f;
        }
        final Frac f = new Frac(x);
        return f;
    }
    
    public static Frac min(final Frac x, final Frac y) {
        return x.min(y);
    }
    
    public static Frac min(final Frac x, final int y) {
        return x.min(new Frac(y));
    }
    
    public static Frac min(final int x, final Frac y) {
        return y.min(new Frac(x));
    }
    
    public static Frac max(final Frac x, final Frac y) {
        return x.max(y);
    }
    
    public static Frac max(final Frac x, final BigInteger y) {
        return x.max(new Frac(y));
    }
    
    public static Frac max(final Frac x, final long y) {
        return max(x, BigInteger.valueOf(y));
    }
    
    public static Frac max(final BigInteger x, final Frac y) {
        return y.max(new Frac(x));
    }
    
    public static Frac max(final long x, final Frac y) {
        return max(BigInteger.valueOf(x), y);
    }
    
    public static Frac parseFrac(final String str) throws ArithmeticException, NumberFormatException {
        final BigInteger TEN = BigInteger.valueOf(10L);
        char[] chr = str.toCharArray();
        int ii = str.length();
        int type = 0;
        int i;
        for (i = 0; i < ii && chr[i] != '/'; ++i) {}
        String str2;
        String str3;
        if (i == ii) {
            type = 1;
            str2 = str;
            str3 = "";
        }
        else {
            str2 = str.substring(0, i);
            str3 = str.substring(i + 1);
            ++i;
            while (i < ii && chr[i] != '/') {
                ++i;
            }
            if (i == ii) {
                type = 2;
            }
        }
        switch (type) {
            case 0: {
                throw new NumberFormatException(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("NumberFormatException"));
            }
            case 1: {
                boolean minus = false;
                chr = str2.toCharArray();
                i = 0;
                ii = str2.length();
                int start = -1;
                while (i < ii) {
                    if (chr[i] != ' ' || start != -1) {
                        if (start == -1) {
                            start = i;
                        }
                        if (!Character.isDigit(chr[i])) {
                            if (i == start && chr[i] == '-') {
                                minus = true;
                            }
                            else {
                                if (chr[i] != '.') {
                                    throw new NumberFormatException(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("NumberFormatException"));
                                }
                                break;
                            }
                        }
                    }
                    ++i;
                }
                if (i == ii) {
                    return new Frac(new BigInteger(str2));
                }
                str3 = str2.substring(i + 1);
                str2 = str2.substring(0, i);
                BigInteger ret1;
                if (str2.equals("")) {
                    ret1 = BigInteger.ZERO;
                }
                else {
                    ret1 = new BigInteger(str2);
                }
                chr = str3.toCharArray();
                int div = 1;
                for (int ij = 0; ij < str3.length() && chr[ij] == '0'; ++ij) {
                    ret1 = ret1.multiply(TEN);
                    div *= 10;
                }
                BigInteger ret2 = new BigInteger(str3);
                if (ret2.signum() < 0) {
                    ret1 = ret1.negate();
                    ret2 = ret2.negate();
                }
                str3 = ret2.toString();
                if (ret2.signum() == 0) {
                    return new Frac(ret1);
                }
                for (int ij = 0; ij < str3.length(); ++ij) {
                    div *= 10;
                    ret1 = ret1.multiply(TEN);
                }
                if (ret1.signum() == 0) {
                    if (minus) {
                        ret1 = ret2.negate();
                    }
                    else {
                        ret1 = ret2;
                    }
                }
                else if (ret1.signum() < 0) {
                    ret1 = ret1.subtract(ret2);
                }
                else {
                    ret1 = ret1.add(ret2);
                }
                return new Frac(ret1, BigInteger.valueOf(div));
            }
            case 2: {
                BigInteger ret1;
                BigInteger ret2;
                try {
                    ret1 = new BigInteger(str2);
                    ret2 = new BigInteger(str3);
                }
                catch (Exception ex) {
                    throw new NumberFormatException(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("NumberFormatException"));
                }
                return new Frac(ret1, ret2);
            }
            default: {
                throw new NumberFormatException(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("UnkownError"));
            }
        }
    }
}
