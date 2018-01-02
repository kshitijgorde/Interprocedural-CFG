// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a7
{
    h for;
    ae do;
    int if;
    boolean a;
    ac int;
    
    public a7() {
        this.for = null;
        this.do = null;
        this.if = -1;
        this.a = false;
    }
    
    public boolean a(final ae do1, final ac int1) {
        this.int = int1;
        this.do = do1;
        this.for = new h(this.do);
        if (this.for.i() != 0) {
            this.for = null;
            return false;
        }
        if (this.for.u() == 0) {
            this.do.char = new af();
            return true;
        }
        if (this.for.i() == -234) {
            System.out.println("Error: progressive Jpeg not supported.");
            return false;
        }
        this.for = null;
        return false;
    }
    
    public boolean a() {
        final int o = this.for.o();
        final int a = this.for.a();
        if (o * 6 == a) {
            this.a = true;
        }
        boolean b = true;
        int do1 = o;
        int else1 = a;
        int n = 9;
        if (ac.b != 0) {
            final float n2 = o / a;
            do1 = (int)Math.sqrt(ac.b * n2);
            if (this.a) {
                else1 = do1 * 6;
            }
            else {
                else1 = (int)(do1 / n2);
            }
        }
        System.gc();
        while (b && n > 0) {
            b = false;
            try {
                this.do.char.new = new int[do1 * else1];
            }
            catch (OutOfMemoryError outOfMemoryError) {
                if (!this.do.h) {
                    System.out.println("Not enough memory to open " + this.do.n);
                    this.do.char.new = new int[1];
                    this.do.char.do = 0;
                    this.do.char.else = 0;
                    return false;
                }
                this.do.char.new = null;
                System.gc();
                b = true;
                do1 = o * n / 10;
                if (this.a) {
                    else1 = do1 * 6;
                }
                else {
                    else1 = a * n / 10;
                }
                --n;
            }
        }
        if (o != do1) {
            final char[] charArray = "imagedegraded()".toCharArray();
            if (this.int.case.G != null) {
                this.int.case.G.a(charArray);
            }
            if (this.int.case.D != null) {
                this.int.case.D.if(charArray);
            }
        }
        this.do.char.do = do1;
        this.do.char.else = else1;
        if (this.do.goto != null && this.do.goto.char != null && this.do.goto.char.int) {
            ap.a(this.do.goto.char, this.do.char);
        }
        else {
            int n3 = 0;
            boolean b2 = false;
            int n4 = 0;
            for (int i = 0; i < else1; ++i) {
                if (n4 == 100) {
                    b2 = !b2;
                    n4 = 0;
                }
                int n5 = 0;
                boolean b3 = false;
                for (int j = 0; j < do1; ++j) {
                    if (n5 == 100) {
                        b3 = !b3;
                        n5 = 0;
                    }
                    this.do.char.new[n3++] = ((b2 == b3) ? -3355444 : -1);
                    ++n5;
                }
                ++n4;
            }
        }
        this.do.char.for = true;
        this.do.new = true;
        if (do1 == o && else1 == a) {
            int n6 = 0;
            int n7 = 0;
            final byte[][] array = { null };
            final int[] array2 = { 0 };
            int n8 = 0;
            for (int k = 0; k < else1; ++k) {
                if (this.for.a(array, array2) != 0) {
                    break;
                }
                ++n6;
                final byte[] array3 = array[0];
                int n9 = 0;
                final int s = this.for.s();
                if (this.for.w() == 3) {
                    for (int l = 0; l < do1; ++l, n9 += s) {
                        this.do.char.new[l + n8] = (0xFF000000 | (array3[n9] & 0xFF) << 16 | (array3[1 + n9] & 0xFF) << 8 | (array3[2 + n9] & 0xFF));
                    }
                }
                else if (this.for.w() == 1) {
                    for (int do2 = this.do.char.do; do2 > 0; --do2, n9 += s) {
                        this.do.char.new[this.do.char.do - do2 + n8] = (0xFF000000 | (array3[n9] & 0xFF) << 16 | (array3[n9] & 0xFF) << 8 | (array3[n9] & 0xFF));
                    }
                }
                ++n7;
                n8 += do1;
                if (this.int.G) {
                    return false;
                }
            }
        }
        else {
            if (this.a) {
                int n10 = 0;
                int n11 = 0;
                for (int n12 = 0; n12 < 6; ++n12) {
                    this.a(o, o, n10, do1, do1, n11);
                    n10 += o;
                    n11 += do1;
                    if (this.int.G) {
                        return false;
                    }
                }
            }
            else {
                this.a(o, a, 0, do1, else1, 0);
            }
            if (this.int.G) {
                return false;
            }
        }
        this.for = null;
        this.do.char.int = true;
        this.do.case = true;
        this.do.char.a();
        return true;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final byte[][] array = { null };
        final int[] array2 = { 0 };
        boolean b = false;
        final byte[][] array3 = null;
        int n7 = n / n4;
        byte[][] array4;
        if (n7 >= 2) {
            b = true;
            array4 = new byte[n7][];
        }
        else {
            n7 = 0;
            array4 = new byte[2][];
        }
        final int n8 = (n << 16) / n4;
        final int n9 = (n2 << 16) / n5;
        int n10 = 0;
        int n11 = n4 * n6;
        this.for.a(array, array2);
        ++this.if;
        for (int i = 0; i < n5; ++i) {
            final int n12 = (n10 >> 16) + n3;
            n10 += n9;
            while (this.if < n12) {
                this.for.a(array, array2);
                ++this.if;
                if (this.int.G) {
                    return;
                }
            }
            array4[0] = array[0];
            int n13 = 0;
            for (int j = 1; j < n7; ++j) {
                if (this.for.a(array, array2) == 0) {
                    array4[j] = array[0];
                    ++this.if;
                    ++n13;
                }
                else {
                    array4[j] = null;
                }
                if (this.int.G) {
                    return;
                }
            }
            final int s = this.for.s();
            if (this.for.w() == 3) {
                int n14 = 0;
                if (!b) {
                    for (int k = 0; k < n4; ++k) {
                        final int n15 = (n14 >> 16) * s;
                        if (array4[1] == null || k == n4) {
                            this.do.char.new[k + n11] = (0xFF000000 | (array4[0][n15] & 0xFF) << 16 | (array4[0][1 + n15] & 0xFF) << 8 | (array4[0][2 + n15] & 0xFF));
                        }
                        else {
                            final int n16 = n15 + s;
                            this.do.char.new[k + n11] = if((array4[0][n15] & 0xFF) << 16 | (array4[0][1 + n15] & 0xFF) << 8 | (array4[0][2 + n15] & 0xFF), (array4[0][n16] & 0xFF) << 16 | (array4[0][1 + n16] & 0xFF) << 8 | (array4[0][2 + n16] & 0xFF), (array4[1][n15] & 0xFF) << 16 | (array4[1][1 + n15] & 0xFF) << 8 | (array4[1][2 + n15] & 0xFF), (array4[1][n16] & 0xFF) << 16 | (array4[1][1 + n16] & 0xFF) << 8 | (array4[1][2 + n16] & 0xFF), n14, n10);
                        }
                        n14 += n8;
                    }
                }
                else {
                    for (int l = 0; l < n4; ++l) {
                        final int n17 = (n14 >> 16) * s;
                        int n18 = 0;
                        int n19 = 0;
                        int n20 = 0;
                        int n21 = 0;
                        for (int n22 = 0; n22 < n13; ++n22) {
                            int n23 = n17;
                            for (int n24 = 0; n24 < n13; ++n24) {
                                n18 += (array4[n22][n23] & 0xFF);
                                n19 += (array4[n22][n23 + 1] & 0xFF);
                                n20 += (array4[n22][n23 + 2] & 0xFF);
                                n23 += s;
                                ++n21;
                            }
                        }
                        this.do.char.new[l + n11] = (0xFF000000 | (n18 / n21 & 0xFF) << 16 | (n19 / n21 & 0xFF) << 8 | (n20 / n21 & 0xFF));
                        n14 += n8;
                    }
                }
            }
            else if (this.for.w() == 1) {
                int n25 = 0;
                if (!b) {
                    for (int n26 = 0; n26 < n4; ++n26) {
                        final int n27 = (n25 >> 16) * s;
                        if (array4[1] == null || n26 == n4) {
                            this.do.char.new[n26 + n11] = (0xFF000000 | (array4[0][n27] & 0xFF) << 16 | (array4[0][n27] & 0xFF) << 8 | (array4[0][n27] & 0xFF));
                        }
                        else {
                            final int n28 = n27 + s;
                            final int n29 = array4[0][n27] & 0xFF;
                            final int n30 = array4[0][n28] & 0xFF;
                            final int n31 = array4[1][n27] & 0xFF;
                            final int n32 = array4[1][n28] & 0xFF;
                            this.do.char.new[n26 + n11] = if(n29 << 16 | n29 << 8 | n29, n30 << 16 | n30 << 8 | n30, n31 << 16 | n31 << 8 | n31, n32 << 16 | n32 << 8 | n32, n25, n10);
                        }
                        n25 += n8;
                    }
                }
                else {
                    for (int n33 = 0; n33 < n4; ++n33) {
                        final int n34 = (n25 >> 16) * s;
                        int n35 = 0;
                        int n36 = 0;
                        for (int n37 = 0; n37 < n13; ++n37) {
                            int n38 = n34;
                            for (int n39 = 0; n39 < n13; ++n39) {
                                n35 += (array4[n37][n38] & 0xFF);
                                n38 += s;
                                ++n36;
                            }
                        }
                        final int n40 = n35 / n36 & 0xFF;
                        this.do.char.new[n33 + n11] = (0xFF000000 | n40 << 16 | n40 << 8 | (n40 & 0xFF));
                        n25 += n8;
                    }
                }
            }
            n11 += n4;
        }
    }
    
    private static int if(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n5 >> 8 & 0xFF;
        final int n8 = n6 >> 8 & 0xFF;
        final int n9 = (256 - n7) * (256 - n8);
        final int n10 = n7 * (256 - n8);
        final int n11 = (256 - n7) * n8;
        final int n12 = n7 * n8;
        return (n9 * (n >> 16 & 0xFF) + n10 * (n2 >> 16 & 0xFF) + n11 * (n3 >> 16 & 0xFF) + n12 * (n4 >> 16 & 0xFF) & 0xFF0000) + ((n9 * (n >> 8 & 0xFF) + n10 * (n2 >> 8 & 0xFF) + n11 * (n3 >> 8 & 0xFF) + n12 * (n4 >> 8 & 0xFF) & 0xFF0000) >> 8) + (n9 * (n & 0xFF) + n10 * (n2 & 0xFF) + (n11 * (n3 & 0xFF) + n12 * (n4 & 0xFF)) >> 16) - 16777216;
    }
}
