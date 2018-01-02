// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import java.awt.image.IndexColorModel;
import ji.v1event.a6;
import ji.io.h;
import ji.util.e;
import java.awt.Component;
import ji.util.i;
import ji.awt.c;
import ji.res.s;
import java.awt.image.ColorModel;
import ji.util.d;
import ji.annotate.dg;
import ji.awt.dc;
import ji.awt.da;
import ji.document.ad;
import ji.v1event.af;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import ji.image.dw;

public class go
{
    public static int a;
    static final int b;
    
    public static final dw a(final dw dw, String s, final d1 d1, final boolean b, final boolean b2, final Color color, final Color color2, final int n, final int n2, final boolean b3, final double n3, final Dimension dimension, final Dimension dimension2, final Rectangle rectangle, final Rectangle rectangle2, final boolean b4, final int[] array, final int[] array2, final int[] array3, final int[] array4, final String[] array5, final int n4, final int n5, final boolean b5, final af af, final ad ad, final String s2, final boolean b6, final int n6, final boolean b7, final boolean b8, final Dimension dimension3, final da da, final double n7, final dc dc, final double n8, final double n9, final dc dc2, final double n10, final dg dg, final int n11, final int n12, final double n13, final double n14, final boolean b9, final boolean b10, final int n15) throws Exception {
        if (d.by(s)) {
            s = " ";
        }
        dw a = null;
        try {
            a = a(dw, s, d1, n, n2, b3, n3, dimension, dimension2, rectangle, rectangle2, color, color2, b, b2, b4, array, array2, array3, array4, array5, n4, n5, b5, af, ad, s2, b6, n6, b7, b8, dimension3, da, n7, dc, n8, n9, dc2, n10, dg, n11, n12, n13, n14, b9, b10, n15);
        }
        catch (Exception ex) {
            d.a(ex);
        }
        return a;
    }
    
    public static final ColorModel a(final boolean b, final boolean b2, final Color color, final Color color2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        return a(b, b2, color, color2, b3, true, b4, b5, b6);
    }
    
    public static final dw a(final dw e, final String s, final d1 d1, final int n, final int n2, final boolean b, double n3, final Dimension dimension, final Dimension dimension2, final Rectangle rectangle, final Rectangle rectangle2, final Color color, Color e2, final boolean b2, final boolean b3, final boolean b4, final int[] array, final int[] array2, final int[] array3, final int[] array4, final String[] array5, final int n4, final int n5, boolean b5, final af af, final ad ad, final String s2, final boolean b6, final int n6, final boolean b7, final boolean b8, final Dimension dimension3, final da da, final double n7, dc dc, double n8, double n9, final dc dc2, final double n10, final dg dg, final int n11, final int n12, final double n13, final double n14, final boolean b9, final boolean b10, final int n15) throws Exception {
        final boolean b11 = true;
        final boolean b12 = false;
        boolean b13 = false;
        String f = s;
        final long n16 = 1048576L;
        boolean i = s.i(s2);
        final double t = j.t();
        if (d1.a()) {
            i = true;
        }
        switch (n) {
            case 90:
            case 270: {
                if (dc != null) {
                    final long a = dc.a;
                    dc.a = dc.b;
                    dc.b = a;
                }
                if (dc2 != null) {
                    final long a2 = dc2.a;
                    dc2.a = dc2.b;
                    dc2.b = a2;
                    break;
                }
                break;
            }
        }
        if (t > 0.0) {
            n3 *= t;
        }
        final d2 d2 = new d2();
        if (d.by(f)) {
            f = " ";
        }
        boolean[] array6 = null;
        if (!d.by(f)) {
            array6 = new boolean[f.length() + 1];
        }
        if (!d.by(f) && d1 != null) {
            final boolean b14 = false;
            final boolean b15 = false;
            final boolean b16 = (n2 & 0x1) > 0;
            final boolean b17 = (n2 & 0x2) > 0;
            char[] array7 = f.toCharArray();
            final int length = array7.length;
            if (b15) {
                final c c = new c("jiUtil9");
                String concat = "";
                for (int j = 0; j < length; ++j) {
                    if (array7[j] == '\n' || array7[j] == '\r') {
                        c.c(concat);
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(array7[j])));
                    }
                }
                if (!concat.equals("")) {
                    c.c(concat);
                }
                f = "";
                for (int k = 0; k < c.b(); ++k) {
                    if (!f.equals("")) {
                        f = String.valueOf(String.valueOf(f)).concat("\n");
                    }
                    f = String.valueOf(String.valueOf(f)).concat(String.valueOf(String.valueOf(c.b(c.b() - k - 1))));
                }
                array7 = f.toCharArray();
                c.c();
            }
            int n17 = 0;
            int max = 0;
            final int max2 = Math.max(d1.g() / go.a, go.a);
            int max3 = d1.h() + max2;
            int n18 = max2 / 2;
            int n19 = max2 / 2;
            boolean c2 = ji.util.i.c(42);
            final boolean[] array8 = new boolean[array7.length];
            int n20 = 0;
            final int n21 = (d1.a(ad, "W", false, af) - 4) / 4;
            final int n22 = (d1.h() - 4) / 4;
            int l;
            for (l = n11 - dg.b4(); l < 0; l += 360) {}
            if (dc == null) {
                if (dg.b4() != 0.0) {
                    c2 = false;
                }
                if (l != 0) {
                    c2 = false;
                }
                if (dg.b3() != 0) {
                    c2 = false;
                }
            }
            da da2 = null;
            if (da != null) {
                da2 = new da(da);
            }
            da da3 = null;
            if (dimension3 != null) {
                da3 = new da(0L, 0L, (int)(dimension3.width * n13), (int)(dimension3.height * n14));
            }
            for (int n23 = 0; n23 < length; ++n23) {
                array6[n23] = true;
                if (n23 < array4.length) {
                    array4[n23] = d1.h();
                }
                if (n23 < array.length) {
                    array[n23] = n18;
                }
                if (n23 < array2.length) {
                    array2[n23] = n19;
                }
                if (ad != null && ad.bi(26)) {
                    try {
                        if (dc != null && dc2 != null && n8 > 0.0 && n9 > 0.0 && dg != null) {
                            if ((int)Math.ceil(n19 * n7) > dc2.b) {
                                array6[n23] = false;
                            }
                            else if (!b16 && (int)(Math.ceil(n18 * n7) + dg.fn().a) > dimension3.width) {
                                array6[n23] = false;
                            }
                        }
                    }
                    catch (Exception ex10) {}
                }
                if (array7[n23] == '\n' || array7[n23] == '\r') {
                    n17 = 0;
                    max3 += d1.h();
                    n19 += d1.h();
                    n18 = max2 / 2;
                    if (n23 < array3.length) {
                        array3[n23] = 0;
                    }
                }
                else {
                    int n24 = d1.a(ad, "".concat(String.valueOf(String.valueOf(array7[n23]))), i, af) - 4;
                    if (c2 && da != null && dimension3 != null && n23 > 0) {
                        boolean b18 = false;
                        final da da4 = new da(da2.a, da2.b, (long)(n7 * (n17 + n24 + n21)), (long)(n7 * (max3 + d1.h() + n22)));
                        final da b19 = da3.b(da4);
                        if (b19.a != da4.a) {
                            b18 = true;
                        }
                        if (b19.b != da4.b) {
                            b18 = true;
                        }
                        if (b19.c != da4.c) {
                            b18 = true;
                        }
                        if (b18) {
                            int n25 = -1;
                            for (int n26 = n23 - 1; n26 > 0; --n26) {
                                if (array7[n26] == ' ' || array7[n26] == '\0') {
                                    n25 = n26 + 1;
                                    break;
                                }
                                if (array8[n26]) {
                                    break;
                                }
                                if (array7[n26] == '\n') {
                                    break;
                                }
                            }
                            if (n25 > 0) {
                                n23 = n25;
                                n24 = d1.a(ad, "".concat(String.valueOf(String.valueOf(array7[n23]))), i, af) - 4;
                            }
                            if (!i || n25 > 0 || dc2 == null) {
                                n17 = 0;
                                n19 += d1.h();
                                n18 = max2 / 2;
                                array2[n23] = n19;
                                array[n23] = n18;
                                array8[n23] = true;
                                if (dc2 != null) {
                                    if (n19 < dc2.b) {
                                        array6[n23] = true;
                                        max3 += d1.h();
                                    }
                                    else if (!ji.util.i.c(247)) {
                                        max3 += d1.h();
                                    }
                                }
                                else if (ji.util.i.c(247)) {
                                    array6[n23] = true;
                                    max3 += d1.h();
                                }
                                else {
                                    max3 += d1.h();
                                }
                                ++n20;
                            }
                        }
                    }
                    if (c2 && dc != null && n8 > 0.0 && n23 > 0) {
                        int n27;
                        if (ji.util.i.c(247)) {
                            n27 = n24 / 16;
                        }
                        else {
                            n27 = 0;
                        }
                        if ((int)(n3 * (n17 + n24 + n21 / 2 - n27)) > (int)dc.a) {
                            int n28 = -1;
                            for (int n29 = n23 - 1; n29 > 0; --n29) {
                                if (array7[n29] == ' ') {
                                    n28 = n29 + 1;
                                    break;
                                }
                                if (array8[n29]) {
                                    break;
                                }
                                if (array7[n29] == '\n') {
                                    break;
                                }
                            }
                            if (n28 > 0) {
                                n23 = n28;
                                n24 = d1.a(ad, "".concat(String.valueOf(String.valueOf(array7[n23]))), i, af) - 4;
                            }
                            if (!i || n28 > 0 || ji.util.i.c(247)) {
                                n17 = 0;
                                n19 += d1.h();
                                n18 = max2 / 2;
                                array2[n23] = n19;
                                array[n23] = n18;
                                if (dc2 != null) {
                                    if (n19 < dc2.b) {
                                        array6[n23] = true;
                                        max3 += d1.h();
                                    }
                                    else if (!ji.util.i.c(247)) {
                                        max3 += d1.h();
                                    }
                                }
                                else {
                                    if (ji.util.i.c(247)) {
                                        array6[n23] = true;
                                    }
                                    max3 += d1.h();
                                }
                                array8[n23] = true;
                                ++n20;
                            }
                        }
                    }
                    if (n23 < array3.length) {
                        array3[n23] = n24;
                    }
                    n18 += n24;
                    n17 += n24;
                }
                max = Math.max(max, n17);
            }
            try {
                array[array.length - 1] = n18;
                array2[array.length - 1] = n19;
                array4[array.length - 1] = d1.h();
                array3[array.length - 1] = 0;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (dc != null && dc2 != null && n8 > 0.0 && n9 > 0.0 && n19 > dc2.b) {
                    array6[array.length - 1] = false;
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            final int n30 = max2 / 2;
            boolean b20 = false;
            switch (n15) {
                case 1: {
                    b20 = true;
                    break;
                }
            }
            int n31 = 0;
            if (dc != null && dc.a != 0 && n3 != 0) {
                n31 = (int)(dc.a / n3);
            }
            if (b20) {
                if (n31 > 0) {
                    max = n31;
                }
                int n32 = 0;
                for (int n33 = 0; n33 < array.length - 1; ++n33) {
                    final boolean b21 = array7[n33] == '\n' || array7[n33] == '\r';
                    if (b21 || array8[n33]) {
                        int n34 = n33;
                        if (array8[n33]) {
                            n34 = n33 - 1;
                        }
                        int n35 = max - (array[n34] + array3[n34]) - n30;
                        int n36 = 0;
                        for (int n37 = n34; n37 >= n32; --n37) {
                            if (n36 == 0 && array7[n37] == ' ') {
                                n35 += array3[n37];
                                array3[n37] = 0;
                                n36 = 1;
                            }
                            else {
                                n36 = 1;
                            }
                            array[n37] += n35;
                            if (array[n37] < 0) {
                                array[n37] = 0;
                            }
                        }
                        n32 = n33;
                        if (b21) {
                            ++n32;
                        }
                    }
                }
                if (array != null && array.length > 0) {
                    final int n38 = max - array[array.length - 1] - n30;
                    for (int n39 = array.length - 1; n39 >= n32; --n39) {
                        array[n39] += n38;
                        if (array[n39] < 0) {
                            array[n39] = 0;
                        }
                    }
                }
            }
            final int n40 = 0;
            int n41;
            if (array.length > 0) {
                n41 = n40 + array[0];
            }
            else {
                n41 = n40 + n30;
            }
            int max4 = max + max2 + 2;
            int n42 = max3;
            if (dc == null && ji.util.i.c(247)) {
                n8 = (int)(max4 * n3);
                n9 = (int)(max3 * n3);
                dc = new dc((int)n8, (int)n9);
                max4 += d1.g();
            }
            else if (ji.util.i.c(247)) {
                max4 += d1.g();
            }
            final int n43 = max4;
            if (dc != null && n8 > 0.0 && n9 > 0.0) {
                max4 = Math.max(Math.min(max4, (int)(dc.a / n3)), 1);
                Math.min(max, (int)(dc.a / n3));
                n42 = (max3 = Math.max(Math.min(max3, (int)(dc.b / n3)), 1));
            }
            rectangle2.width = max4;
            rectangle2.height = max3;
            if (e != null) {
                if (b5) {
                    b13 = true;
                }
                d2.e = e;
                b5 = false;
            }
            if (b5) {
                if (d2.e == null) {
                    d2.e = new dw(max3, max4, ad, n16, s2, b6, 1);
                    b13 = true;
                }
                d2.e.m();
                if (dg != null) {
                    dg.p(false);
                }
            }
            if (d2.e != null) {
                d2.e.a(b4);
            }
            d2.i = max4;
            d2.j = max3;
            d2.f = f;
            dimension.width = d2.i;
            dimension.height = d2.j;
            int n44 = n41;
            int n45 = 0;
            int n46 = 0;
            dw dw = null;
            int n47 = 0;
            final int n48 = n43 * d1.h();
            byte[] array9 = null;
            byte[] array10 = null;
            if (!ji.util.i.c(247)) {
                array9 = new byte[n48];
                array10 = new byte[n48];
            }
            for (int n49 = 0; n49 < length; ++n49) {
                if (array[n49] >= 0) {
                    if (array2[n49] >= 0) {
                        final boolean b22 = n49 >= n4 && n49 <= n5;
                        char c3;
                        if (n49 >= length) {
                            c3 = ' ';
                        }
                        else {
                            final boolean b23 = array7[n49] == '\n' || array7[n49] == '\r';
                            if (b23 || array8[n49]) {
                                if (b20) {
                                    if (b23) {
                                        if (n49 == array.length) {
                                            n41 = n31 - array[n49];
                                        }
                                        else {
                                            n41 = array[n49 + 1];
                                        }
                                    }
                                    else if (array8[n49]) {
                                        if (n49 == array.length) {
                                            n41 = n31 - array[n49];
                                        }
                                        else {
                                            n41 = array[n49];
                                        }
                                    }
                                }
                                if (b5) {
                                    try {
                                        boolean b24 = b9;
                                        if (ji.util.i.c(247) && e.av()) {
                                            b24 = false;
                                        }
                                        if (b24) {
                                            final int n50 = d1.h() / 30;
                                            final int n51 = d1.h() / 2 - n50;
                                            final int n52 = d1.h() / 2 + n50;
                                            final int n53 = n47 - 1;
                                            for (int n54 = n51; n54 < n52; ++n54) {
                                                final int n55 = n54 * n43;
                                                for (int n56 = n41; n56 < n53; ++n56) {
                                                    array9[n56 + n55] = 1;
                                                }
                                            }
                                        }
                                        Label_3399: {
                                            if (dc != null && n9 > 0) {
                                                try {
                                                    if (array9 == null && dw == null) {
                                                        break Label_3399;
                                                    }
                                                    int h;
                                                    if (n46 + d1.h() > n42 + d1.h() / 2) {
                                                        if (dg != null) {
                                                            dg.p(true);
                                                        }
                                                        h = n42 - n46;
                                                    }
                                                    else if (n46 + d1.h() > n42) {
                                                        h = n42 - n46;
                                                    }
                                                    else {
                                                        h = d1.h();
                                                    }
                                                    if (array9 != null) {
                                                        d2.e.a(n46, array9, n43, h);
                                                        break Label_3399;
                                                    }
                                                    if (dw != null) {
                                                        byte[] array11 = null;
                                                        for (int n57 = 0; n57 < h; ++n57) {
                                                            if (n41 > 0) {
                                                                final byte[] b25 = dw.b(n57);
                                                                if (array11 == null) {
                                                                    array11 = new byte[Math.max(b25.length, n43) + n41];
                                                                }
                                                                System.arraycopy(b25, 0, array11, n41, b25.length);
                                                                d2.e.a(n46 + n57, array11, n43, 1);
                                                            }
                                                            else {
                                                                d2.e.a(n46 + n57, dw.b(n57), n43, 1);
                                                            }
                                                            if (b11) {
                                                                a(af, 100 * n57 / h, ad);
                                                            }
                                                        }
                                                    }
                                                    break Label_3399;
                                                }
                                                catch (Exception ex3) {
                                                    if (!d.cy()) {}
                                                    ex3.printStackTrace();
                                                    break Label_3399;
                                                }
                                            }
                                            if (array9 != null) {
                                                d2.e.a(n46, array9, n47, d1.h());
                                            }
                                            else if (dw != null) {
                                                final int h2 = d1.h();
                                                byte[] array12 = null;
                                                for (int n58 = 0; n58 < h2; ++n58) {
                                                    if (n41 > 0) {
                                                        final byte[] b26 = dw.b(n58);
                                                        if (array12 == null) {
                                                            array12 = new byte[b26.length + n41];
                                                        }
                                                        System.arraycopy(b26, 0, array12, n41, b26.length);
                                                        d2.e.a(n46 + n58, array12, n47, 1);
                                                    }
                                                    else {
                                                        d2.e.a(n46 + n58, dw.b(n58), n47, 1);
                                                    }
                                                    if (b11) {
                                                        a(af, 100 * n58 / h2, ad);
                                                    }
                                                }
                                            }
                                        }
                                        if (array9 != null) {
                                            System.arraycopy(array10, 0, array9, 0, n48);
                                        }
                                    }
                                    catch (Exception ex4) {
                                        ex4.printStackTrace();
                                    }
                                }
                                n47 = 0;
                                n45 += n43 * d1.h();
                                n44 = n41;
                                n46 += d1.h();
                                if (b23 || !array8[n49]) {
                                    continue;
                                }
                                c3 = array7[n49];
                            }
                            else {
                                c3 = array7[n49];
                            }
                        }
                        int n59 = 0;
                        StringBuffer sb = null;
                        int n60 = 0;
                        int n61 = -1;
                        int n62 = -1;
                        if (i) {
                            sb = new StringBuffer();
                            sb.append(array7[n49]);
                            int n63 = 0;
                            if (n49 >= n4 && n49 <= n5) {
                                n61 = n63;
                            }
                            ++n63;
                            if (array6[n49]) {
                                n60 = 1;
                            }
                            while (n49 + 1 < length && array7[n49 + 1] != '\n' && array7[n49 + 1] != '\r' && !array8[n49 + 1]) {
                                ++n49;
                                if (array6[n49]) {
                                    if (n49 >= n4 && n49 <= n5) {
                                        if (n61 < 0) {
                                            n61 = n63;
                                        }
                                        else {
                                            n62 = n63;
                                        }
                                    }
                                    sb.append(array7[n49]);
                                    n60 = 1;
                                    ++n63;
                                }
                            }
                            if (n61 > -1 && n62 < 0) {
                                n62 = n61;
                            }
                            if (n60 != 0) {
                                n59 = d1.a(ad, sb.toString(), true, true, af);
                            }
                        }
                        else {
                            n60 = 1;
                            n59 = d1.a(ad, "".concat(String.valueOf(String.valueOf(c3))), false, af);
                        }
                        final int n64 = n59 - 4;
                        final char c4 = c3;
                        if (b5) {
                            final int h3 = d1.h();
                            Object o = null;
                            if (i) {
                                if (n60 != 0) {
                                    d1.a(go.b, n61, n62);
                                    final d2 a3 = d1.a(ad, sb.toString(), true, b9, b10, true, af);
                                    if (a3 == null) {
                                        h.d(s2, "*** HELP! *** no font item!");
                                    }
                                    else {
                                        d1.a(go.b, -1, -1);
                                        if (a3.c != null) {
                                            dw = a3.c;
                                        }
                                        else if (a3.b != null) {
                                            final byte[] b27 = a3.b;
                                            o = new byte[b27.length];
                                            System.arraycopy(b27, 0, o, 0, b27.length);
                                        }
                                    }
                                }
                            }
                            else {
                                final d2 a4 = d1.a(ad, "".concat(String.valueOf(String.valueOf(c4))), false, b9, b10, false, af);
                                if (a4.c != null) {
                                    dw = a4.c;
                                }
                                else {
                                    o = new byte[a4.b.length];
                                    System.arraycopy(a4.b, 0, o, 0, o.length);
                                }
                            }
                            if (b15) {
                                if (b22 && !i) {
                                    if (b14) {
                                        if (n60 != 0) {
                                            for (int n65 = 0; n65 < h3; ++n65) {
                                                final int n66 = n65 * n59;
                                                final int n67 = (h3 - n65 - 1) * n43 + n43 - n44;
                                                for (int n68 = 0; n68 < n64; ++n68) {
                                                    byte b28 = o[n66 + n68];
                                                    if (b28 == 0) {
                                                        b28 = -56;
                                                    }
                                                    array9[n67 - n68 - 1] = b28;
                                                }
                                            }
                                        }
                                        n47 = Math.max(n47, n43 - (n44 + n64) - 1);
                                    }
                                    else {
                                        if (n60 != 0) {
                                            for (int n69 = 0; n69 < h3; ++n69) {
                                                final int n70 = n69 * n59;
                                                final int n71 = (h3 - n69 - 1) * n43 + n44;
                                                for (int n72 = 0; n72 < n64; ++n72) {
                                                    byte b29 = o[n70 + n72];
                                                    if (b29 == 0) {
                                                        b29 = -56;
                                                    }
                                                    array9[n71 + n72] = b29;
                                                }
                                            }
                                        }
                                        n47 = Math.max(n47, n44 + n64);
                                    }
                                }
                                else if (b14) {
                                    if (n60 != 0) {
                                        for (int n73 = 0; n73 < h3; ++n73) {
                                            final int n74 = n73 * n59;
                                            final int n75 = (h3 - n73 - 1) * n43 + n43 - n44;
                                            for (int n76 = 0; n76 < n64; ++n76) {
                                                array9[n75 - n76 - 1] = o[n74 + n76];
                                            }
                                        }
                                    }
                                    n47 = Math.max(n47, n43 - (n44 + n64) - 1);
                                }
                                else {
                                    if (n60 != 0) {
                                        for (int n77 = 0; n77 < h3; ++n77) {
                                            final int n78 = n77 * n59;
                                            final int n79 = (h3 - n77 - 1) * n43 + n44;
                                            for (int n80 = 0; n80 < n64; ++n80) {
                                                array9[n79 + n80] = o[n78 + n80];
                                            }
                                        }
                                    }
                                    n47 = Math.max(n47, n44 + n64);
                                }
                            }
                            else if (b14) {
                                if (b22 && !i) {
                                    if (n60 != 0) {
                                        for (int n81 = 0; n81 < h3; ++n81) {
                                            final int n82 = n81 * n59;
                                            final int n83 = n81 * n43 + n43 - n44 - 1;
                                            for (int n84 = 0; n84 < n64; ++n84) {
                                                byte b30 = o[n82 + n84];
                                                if (b30 == 0) {
                                                    b30 = -56;
                                                }
                                                array9[n83 - n84] = b30;
                                            }
                                        }
                                    }
                                    n47 = Math.max(n47, n43 - (n44 + n64) - 1);
                                }
                                else {
                                    if (n60 != 0) {
                                        for (int n85 = 0; n85 < h3; ++n85) {
                                            final int n86 = n85 * n59;
                                            final int n87 = n85 * n43 + n43 - n44 - 1;
                                            for (int n88 = 0; n88 < n64; ++n88) {
                                                array9[n87 - n88] = o[n86 + n88];
                                            }
                                        }
                                    }
                                    n47 = Math.max(n47, n43 - (n44 + n64) - 1);
                                }
                            }
                            else {
                                if (n60 != 0) {
                                    if (b22 && !i) {
                                        for (int n89 = 0; n89 < h3; ++n89) {
                                            final int n90 = n89 * n59;
                                            final int n91 = n89 * n43 + n44;
                                            try {
                                                System.arraycopy(o, n90, array9, n91, n64);
                                            }
                                            catch (Exception ex11) {}
                                            for (int n92 = 0; n92 < n64; ++n92) {
                                                if (array9[n91 + n92] == 0) {
                                                    array9[n91 + n92] = -56;
                                                }
                                            }
                                        }
                                    }
                                    else if (array9 != null) {
                                        for (int n93 = 0; n93 < h3; ++n93) {
                                            final int n94 = n93 * n59;
                                            final int n95 = n93 * n43 + n44;
                                            try {
                                                System.arraycopy(o, n94, array9, n95, n64);
                                            }
                                            catch (Exception ex12) {
                                                break;
                                            }
                                        }
                                    }
                                    else if (dw != null) {
                                        byte[] array13 = null;
                                        for (int n96 = 0; n96 < h3; ++n96) {
                                            if (n41 > 0) {
                                                final byte[] b31 = dw.b(n96);
                                                if (array13 == null) {
                                                    array13 = new byte[b31.length + n41];
                                                }
                                                System.arraycopy(b31, 0, array13, n41, b31.length);
                                                d2.e.a(n46 + n96, array13, n59, 1);
                                            }
                                            else {
                                                d2.e.a(n46 + n96, dw.b(n96), n59, 1);
                                            }
                                            if (b11) {
                                                a(af, 100 * n96 / h3, ad);
                                            }
                                        }
                                    }
                                }
                                n47 = Math.max(n47, n44 + n64);
                            }
                        }
                        n44 += n64;
                    }
                }
            }
            Label_5671: {
                if (b5 && n47 > 0) {
                    if (array9 == null) {
                        if (dw == null) {
                            break Label_5671;
                        }
                    }
                    try {
                        final int min = Math.min(max3 - n46 - d1.h(), d1.h());
                        if (array10 != null) {
                            d2.e.a(n46 + d1.h(), array10, n47, min);
                        }
                        else {
                            final byte[] array14 = new byte[n47];
                            for (int n97 = 0; n97 < min; ++n97) {
                                d2.e.a(n46 + d1.h(), array14, n47, 1);
                            }
                        }
                    }
                    catch (Exception ex5) {
                        ex5.printStackTrace();
                    }
                    try {
                        boolean b32 = b9;
                        if (ji.util.i.c(247) && e.av()) {
                            b32 = false;
                        }
                        if (b32) {
                            int n98 = d1.h() / 30;
                            if (ji.util.i.c(265) && n3 < 0.16) {
                                n98 = d1.h() / 10 / 2;
                            }
                            final int n99 = d1.h() / 2 - n98;
                            final int n100 = d1.h() / 2 + n98;
                            final int n101 = n47 - 1;
                            for (int n102 = n99; n102 < n100; ++n102) {
                                final int n103 = n102 * n43;
                                for (int n104 = n41; n104 < n101; ++n104) {
                                    array9[n104 + n103] = 1;
                                }
                            }
                        }
                        Label_5658: {
                            if (dc != null && n9 > 0) {
                                try {
                                    int h4;
                                    if (n46 + d1.h() > n42 + d1.h() / 2) {
                                        if (dg != null) {
                                            dg.p(true);
                                        }
                                        h4 = n42 - n46;
                                    }
                                    else if (n46 + d1.h() > n42) {
                                        h4 = n42 - n46;
                                    }
                                    else {
                                        h4 = d1.h();
                                    }
                                    if (array9 != null) {
                                        d2.e.a(n46, array9, n43, h4);
                                        break Label_5658;
                                    }
                                    if (dw != null) {
                                        byte[] array15 = null;
                                        for (int n105 = 0; n105 < h4; ++n105) {
                                            if (n41 > 0) {
                                                final byte[] b33 = dw.b(n105);
                                                if (array15 == null) {
                                                    array15 = new byte[b33.length + n41];
                                                }
                                                System.arraycopy(b33, 0, array15, n41, b33.length);
                                                d2.e.a(n46 + n105, array15, n47, 1);
                                            }
                                            else {
                                                d2.e.a(n46 + n105, dw.b(n105), n47, 1);
                                            }
                                            if (b11) {
                                                a(af, 100 * n105 / h4, ad);
                                            }
                                        }
                                    }
                                    break Label_5658;
                                }
                                catch (Exception ex6) {
                                    if (!d.cy()) {}
                                    ex6.printStackTrace();
                                    break Label_5658;
                                }
                            }
                            if (array9 != null) {
                                d2.e.a(n46, array9, n47, d1.h());
                            }
                            else if (dw != null) {
                                final int h5 = d1.h();
                                byte[] array16 = null;
                                for (int n106 = 0; n106 < h5; ++n106) {
                                    if (n41 > 0) {
                                        final byte[] b34 = dw.b(n106);
                                        if (array16 == null) {
                                            array16 = new byte[b34.length + n41];
                                        }
                                        System.arraycopy(b34, 0, array16, n41, b34.length);
                                        d2.e.a(n46 + n106, array16, n47, 1);
                                    }
                                    else {
                                        d2.e.a(n46 + n106, dw.b(n106), n47, 1);
                                    }
                                    if (b11) {
                                        a(af, 100 * n106 / h5, ad);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex7) {
                        ex7.printStackTrace();
                    }
                }
                try {
                    if (n3 != 0.0) {
                        if (d2.i > 1 && d2.j > 1) {
                            final int m = (int)Math.round(d2.i * n3);
                            final int j2 = (int)Math.round(d2.j * n3);
                            for (int n107 = 0; n107 < array.length; ++n107) {
                                array[n107] = (int)Math.round(n3 * array[n107]);
                                array2[n107] = (int)Math.round(n3 * array2[n107]);
                                array3[n107] = (int)Math.round(n3 * array3[n107]);
                                array4[n107] = (int)Math.round(n3 * array4[n107]);
                            }
                            d2.i = m;
                            d2.j = j2;
                            if (n == 90 || n == 270) {
                                final int i2 = d2.i;
                                d2.i = d2.j;
                                d2.j = i2;
                            }
                            dimension2.width = d2.i;
                            dimension2.height = d2.j;
                        }
                        else {
                            if (n == 90 || n == 270) {
                                final int i3 = d2.i;
                                d2.i = d2.j;
                                d2.j = i3;
                            }
                            dimension2.width = d2.i;
                            dimension2.height = d2.j;
                        }
                    }
                    if (d2.e != null) {
                        if (d2.i > 1 && d2.j > 1) {
                            d2.e.a(n, n2, n3);
                        }
                        else if (n3 < 1.0) {
                            d2.e.a(n, n2, n3);
                        }
                        else {
                            d2.e.a(n, n2, 1.0);
                        }
                    }
                    if (d2.e != null) {
                        d2.b = d2.e.o();
                    }
                }
                catch (Exception ex8) {
                    if (!d.cy()) {}
                    ex8.printStackTrace();
                }
            }
            Label_6463: {
                try {
                    if (array.length > 0) {
                        int min2 = array[0];
                        int max5 = array[0] + array3[0];
                        int min3 = array2[0];
                        int max6 = array2[0] + array4[0];
                        for (int n108 = 0; n108 < array.length; ++n108) {
                            min2 = Math.min(min2, array[n108]);
                            max5 = Math.max(max5, array[n108] + array3[n108]);
                            min3 = Math.min(min3, array2[n108]);
                            max6 = Math.max(max6, array2[n108] + array4[n108]);
                            try {
                                array5[n108] = String.valueOf(String.valueOf(new StringBuffer("").append(array7[n108] & '\u00ff').append(array[n108]).append(array2[n108]).append(array3[n108]).append(array4[n108])));
                                if (n108 >= n4 && n108 <= n5) {
                                    final int n109 = n108;
                                    array5[n109] = String.valueOf(String.valueOf(array5[n109])).concat("1002");
                                }
                            }
                            catch (Exception ex13) {
                                array5[n108] = "".concat(String.valueOf(String.valueOf(Math.random() * 10000)));
                            }
                        }
                        rectangle.x = min2;
                        rectangle.y = min3;
                        rectangle.width = max5 - min2;
                        rectangle.height = max6 - min3;
                    }
                    else {
                        rectangle.x = 0;
                        rectangle.y = 0;
                        rectangle.width = 0;
                        rectangle.height = 0;
                    }
                    if (b15) {
                        for (int n110 = 0; n110 < array2.length; ++n110) {
                            array2[n110] = rectangle.height - array2[n110];
                        }
                    }
                    if (b14) {
                        for (int n111 = 0; n111 < array.length; ++n111) {
                            array[n111] = rectangle.width - array[n111];
                        }
                    }
                    break Label_6463;
                }
                catch (Exception ex9) {
                    if (!d.cy()) {}
                    ex9.printStackTrace();
                    break Label_6463;
                }
                return null;
            }
            if (b12) {
                d.a(af, 0, s2);
            }
            if (b13) {
                if (n6 == 1 && b6 && !b2) {
                    e2 = d.e4();
                }
                d2.e.a(a(b2, b3, color, e2, b, b7, b6, b8));
            }
            d2.b = null;
            if (b5) {
                d1.l();
            }
            if (af != null && b11) {
                af.a(new a6(ad, 4, "0"));
            }
            return d2.e;
        }
        return null;
    }
    
    public static final void a(final af af, final int n, final Object o) {
        if (af != null) {
            af.a(new a6(o, 4, "".concat(String.valueOf(String.valueOf(n)))));
        }
    }
    
    public static final ColorModel a(final boolean b, final boolean b2, Color color, Color color2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7) {
        final byte[] array = new byte[256];
        final byte[] array2 = new byte[256];
        final byte[] array3 = new byte[256];
        final byte[] array4 = new byte[256];
        IndexColorModel indexColorModel;
        IndexColorModel indexColorModel2;
        if (b) {
            if (color == null) {
                color = Color.black;
            }
            final int n = color.getRed() & 0xFF;
            final int n2 = color.getGreen() & 0xFF;
            final int n3 = color.getBlue() & 0xFF;
            if (!b4) {
                for (int i = 1; i < 255; ++i) {
                    array[i] = -1;
                    array2[i] = (byte)n;
                    array3[i] = (byte)n2;
                    array4[i] = (byte)n3;
                }
                array[255] = -1;
                array2[255] = (byte)n;
                array3[255] = (byte)n2;
                array4[255] = (byte)n3;
                array[0] = 0;
                array2[0] = (byte)n;
                array3[0] = (byte)n2;
                array4[0] = (byte)n3;
                if (!b7) {
                    array[255] = 127;
                }
            }
            else if (b5) {
                for (int j = 0; j < 256; ++j) {
                    if (j > 0) {
                        array[j] = -1;
                    }
                    else {
                        array[j] = (byte)j;
                    }
                    array2[j] = (byte)n;
                    array3[j] = (byte)n2;
                    array4[j] = (byte)n3;
                }
            }
            else {
                if (color == null) {
                    color = Color.black;
                }
                if (color2 == null) {
                    color2 = Color.white;
                }
                final int n4 = color.getRed() - color2.getRed();
                final int n5 = color.getGreen() - color2.getGreen();
                final int n6 = color.getBlue() - color2.getBlue();
                final int n7 = color2.getRed() & 0xFF;
                final int n8 = color2.getGreen() & 0xFF;
                final int n9 = color2.getBlue() & 0xFF;
                for (int k = 1; k < 255; ++k) {
                    array[k] = -1;
                    array2[k] = (byte)(n7 + k * n4 / 255);
                    array3[k] = (byte)(n8 + k * n5 / 255);
                    array4[k] = (byte)(n9 + k * n6 / 255);
                }
                array[255] = -1;
                array2[255] = (byte)n;
                array3[255] = (byte)n2;
                array4[255] = (byte)n3;
                array[0] = 0;
                array2[0] = (byte)n;
                array3[0] = (byte)n2;
                array4[0] = (byte)n3;
            }
            if (!b7) {
                array[255] = 127;
            }
            indexColorModel = new IndexColorModel(8, 256, array2, array3, array4, array);
            for (int l = 0; l < 256; ++l) {
                array2[l] = (byte)(255 - array2[l]);
                array3[l] = (byte)(255 - array3[l]);
                array4[l] = (byte)(255 - array4[l]);
            }
            indexColorModel2 = new IndexColorModel(8, 256, array2, array3, array4, array);
        }
        else {
            if (color == null) {
                color = Color.black;
            }
            if (color2 == null) {
                color2 = Color.white;
            }
            final int n10 = color.getRed() & 0xFF;
            final int n11 = color.getGreen() & 0xFF;
            final int n12 = color.getBlue() & 0xFF;
            if (!b4 || (b2 && b5)) {
                for (int n13 = 1; n13 < 255; ++n13) {
                    array[n13] = -1;
                    array2[n13] = (byte)n10;
                    array3[n13] = (byte)n11;
                    array4[n13] = (byte)n12;
                }
                array[255] = -1;
                array2[255] = (byte)n10;
                array3[255] = (byte)n11;
                array4[255] = (byte)n12;
                array[0] = -1;
                array2[0] = (byte)color2.getRed();
                array3[0] = (byte)color2.getGreen();
                array4[0] = (byte)color2.getBlue();
            }
            else {
                final int n14 = color.getRed() - color2.getRed();
                final int n15 = color.getGreen() - color2.getGreen();
                final int n16 = color.getBlue() - color2.getBlue();
                final int n17 = color2.getRed() & 0xFF;
                final int n18 = color2.getGreen() & 0xFF;
                final int n19 = color2.getBlue() & 0xFF;
                for (int n20 = 1; n20 < 255; ++n20) {
                    array[n20] = -1;
                    array2[n20] = (byte)(n17 + n20 * n14 / 255);
                    array3[n20] = (byte)(n18 + n20 * n15 / 255);
                    array4[n20] = (byte)(n19 + n20 * n16 / 255);
                }
                array[255] = -1;
                array2[255] = (byte)n10;
                array3[255] = (byte)n11;
                array4[255] = (byte)n12;
                array[0] = -1;
                array2[0] = (byte)color2.getRed();
                array3[0] = (byte)color2.getGreen();
                array4[0] = (byte)color2.getBlue();
            }
            if (!b7) {
                array[0] = 127;
            }
            else {
                array[0] = -1;
            }
            indexColorModel = new IndexColorModel(8, 256, array2, array3, array4, array);
            for (int n21 = 0; n21 < 256; ++n21) {
                array2[n21] = (byte)(255 - array2[n21]);
                array3[n21] = (byte)(255 - array3[n21]);
                array4[n21] = (byte)(255 - array4[n21]);
            }
            indexColorModel2 = new IndexColorModel(8, 256, array2, array3, array4, array);
        }
        if (b3) {
            return indexColorModel2;
        }
        return indexColorModel;
    }
    
    static {
        go.a = 4;
        b = new Color(23, 63, 109).getRGB();
    }
}
