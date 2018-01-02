// 
// Decompiled by Procyon v0.5.30
// 

class super implements Runnable
{
    private final double X = 0.2;
    private double Y;
    private double Z;
    private double _a;
    private boolean aa;
    private final return S;
    private static String T = "\ub5f3\ub5dc\ub5db\ub5df\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5d4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c6\ub5db\ub5df\ub5d7\ub588\ub592";
    private static String U = "\ub5df\ub5c1";
    private static String V = "\ub5f4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5c1\ub5c6\ub5dd\ub5c2\ub5c2\ub5d7\ub5d6\ub593";
    private static String W = "\ub5f4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5d6\ub5db\ub5c0\ub5d7\ub5d1\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5db\ub5c1\ub592\ub5dc\ub5d7\ub5d5\ub5d3\ub5c6\ub5db\ub5c4\ub5d7";
    private static String ba = "\ub5f4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5d1\ub5dd\ub5df\ub5db\ub5dc\ub5d5\ub592\ub5c6\ub5dd\ub592\ub5d3\ub5dc\ub592\ub5d7\ub5dc\ub5d6\ub593\ub592\ub5f4\ub5db\ub5dc\ub5d3\ub5de\ub592\ub5c1\ub5c6\ub5d7\ub5c2\ub588\ub592";
    private static String ca = "\ub5f6\ub5d7\ub5d1\ub5c0\ub5d7\ub5d3\ub5c1\ub5d7\ub592\ub5d4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5c1\ub5c2\ub5d7\ub5d7\ub5d6\ub593";
    private static String da = "\ub5fb\ub5dc\ub5d1\ub5c0\ub5d7\ub5d3\ub5c1\ub5d7\ub592\ub5d4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5c1\ub5c2\ub5d7\ub5d7\ub5d6\ub593";
    private static String ea = "\ub5fc\ub5dd\ub5c0\ub5df\ub5d3\ub5de\ub592\ub5d4\ub5c0\ub5d3\ub5df\ub5d7\ub592\ub5c0\ub5dd\ub5c6\ub5d3\ub5c6\ub5db\ub5dd\ub5dc\ub592\ub5c1\ub5c6\ub5d7\ub5c2\ub588\ub592";
    
    public super(final return s, final double y) {
        this.S = s;
        this.aa = true;
        this.Y = y;
        this._a = 0.0;
        this.Z = 0.0;
    }
    
    public void run() {
        final long n = 42L;
        this._(new static(3));
        while (this.aa) {
            final long currentTimeMillis = System.currentTimeMillis();
            this._a += this._(this.Y, this._a);
            return.a(this.S).b(this._a);
            this._(new static(2));
            final long currentTimeMillis2 = System.currentTimeMillis();
            return.b(this.S, String.valueOf(String.valueOf(new StringBuffer(super.T).append(currentTimeMillis2 - currentTimeMillis).append(super.U))));
            try {
                Thread.sleep(Math.max(0L, n - (currentTimeMillis2 - currentTimeMillis)));
            }
            catch (InterruptedException ex) {}
        }
        this._(new static(4));
        return.a(this.S, null);
    }
    
    private void _(final static static1) {
        return.b(this.S).a(static1);
    }
    
    private double _(double n, double n2) {
        final double n3 = n;
        if (n2 == n) {
            return.b(this.S, super.V);
            this.aa = false;
            return 0.0;
        }
        if (n < 0.0) {
            return.b(this.S, super.W);
            n = -n;
            n2 = -n2;
        }
        if (n - n2 >= this.Z) {
            int n4;
            if (n2 > n / 2) {
                return.b(this.S, super.ca);
                n4 = -1;
            }
            else {
                return.b(this.S, super.da);
                n4 = 1;
            }
            if (n3 < 0) {
                if (this.Z - 0.2 * n4 > 0) {
                    return -0.01;
                }
                this.Z -= 0.2 * n4;
            }
            else {
                if (this.Z + 0.2 * n4 < 0) {
                    return 0.01;
                }
                this.Z += 0.2 * n4;
            }
            return.b(this.S, super.ea.concat(String.valueOf(String.valueOf(this.Z))));
            return this.Z;
        }
        this.Z = n - n2;
        return.b(this.S, super.ba.concat(String.valueOf(String.valueOf(this.Z))));
        if (n3 < 0) {
            return -this.Z;
        }
        return this.Z;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFB5B2);
        }
        return new String(array);
    }
    
    static {
        super.T = a(super.T);
        super.U = a(super.U);
        super.V = a(super.V);
        super.W = a(super.W);
        super.ba = a(super.ba);
        super.ca = a(super.ca);
        super.da = a(super.da);
        super.ea = a(super.ea);
    }
}
