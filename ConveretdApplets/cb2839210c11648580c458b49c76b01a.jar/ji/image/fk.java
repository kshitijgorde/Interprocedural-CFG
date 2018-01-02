// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Color;
import ji.util.d;
import java.util.Vector;
import java.awt.Dimension;
import ji.v1event.a6;
import ji.v1event.af;
import ji.adjustment.eh;
import java.awt.Component;

public class fk
{
    static final int a;
    static final int b;
    
    public static final int[] a(final ds ds, final ds ds2, final int[] array, final int n, final int n2, final int n3, final int n4) {
        if (ds2 != null && n2 >= 0 && n3 >= 0) {
            try {
                if (n3 < n2) {
                    return array;
                }
                if (ds2.v == 1) {
                    return d(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 4 || ds2.v == 0) {
                    return e(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 3) {
                    return b(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 2) {
                    return c(ds, ds2, array, n, n2, n3, n4);
                }
                return array;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return array;
            }
        }
        return null;
    }
    
    public static final boolean a(final Component component, final ds ds, final ds ds2, final ds ds3, final ds ds4, final ds ds5, final eh eh, final int n, final af af, final boolean b) {
        boolean b2 = true;
        try {
            ds.a((ds)null);
            if (af != null) {
                af.a(new a6(component, 1, "Adjusting image"));
                af.a(new a6(component, 9, "adjusting image"));
                af.a(new a6(component, 4, 0));
            }
            ds a;
            if (eh.b()) {
                a = ds3.a(eh.u(), component, eh.q(), false, -eh.i(), eh.j(), af, new Dimension((int)ds2.j, (int)ds2.k), null, b);
                ds.a(a, component);
                eh.b(false);
            }
            else if ((eh.u() != 0 || eh.i() != 0 || eh.j() != 0) && ds5 != null) {
                a = ds5;
            }
            else {
                a = ds3;
            }
            ds a2;
            if (eh.c()) {
                a2 = ds2.a(eh.v(), component, eh.r(), false, -eh.k(), eh.l(), af, null, null, b);
                ds.b(a2, component);
                eh.a(false);
            }
            else if ((eh.v() != 0 || eh.k() != 0 || eh.l() != 0) && ds4 != null) {
                a2 = ds4;
            }
            else {
                a2 = ds2;
            }
            if (a2 == null || a == null) {
                b2 = false;
            }
            if (b2) {
                try {
                    a2.e(component);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (a2.t == 1) {
                    try {
                        final byte[] array = new byte[(int)a2.j];
                        final Vector ai = a2.ai();
                        final Vector<short[]> y = new Vector<short[]>();
                        long q = 0L;
                        if (a2.j()) {
                            for (int i = 0; i < (int)a2.k; ++i) {
                                a2.b(i, array);
                                final short[] array2 = new short[(int)a2.j];
                                for (int j = 0; j < (int)a2.j; ++j) {
                                    array2[j] = (short)(array[j] & 0xFF);
                                }
                                final short[] a3 = a(a2, a, array2, (int)a2.j, i, n);
                                y.addElement(a3);
                                q += a3.length;
                            }
                        }
                        else {
                            for (int k = 0; k < ai.size(); ++k) {
                                final short[] a4 = a(a2, a, ai.elementAt(k), (int)a2.j, k, n);
                                y.addElement(a4);
                                q += a4.length;
                            }
                        }
                        ds.y = y;
                        ds.q = q;
                        ds.j = a2.j;
                        ds.k = a2.k;
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                else if (a2.t <= 8) {
                    try {
                        final byte[] array3 = a2.ag().clone();
                        byte[] array4;
                        if (a2.t == 4) {
                            array4 = a(a2, a, array3, array3.length, 0, (int)(a2.k - 1), n);
                        }
                        else {
                            array4 = a(a2, a, array3, array3.length, 0, (int)(a2.k - 1), 0, n);
                        }
                        ds.a((int)a2.j, (int)a2.k, component);
                        ds.a(array4);
                        ds.c(component);
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
                else {
                    try {
                        int[] a5 = new int[(int)a2.j];
                        ds.a((int)a2.j, (int)a2.k, component);
                        for (int n2 = 0; n2 < a2.k; ++n2) {
                            a2.c(n2);
                            a2.a(a5, (int)a2.j);
                            a5 = a(a2, a, a5, a5.length, n2, n2, n);
                            ds.a(a5, (int)a2.j, component, n2 + 1, n2 + 2, true);
                        }
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex5) {
            if (d.cy()) {
                ex5.printStackTrace();
            }
        }
        finally {
            try {
                ds.e(component);
            }
            catch (Exception ex6) {}
            ds.a(ds2);
        }
        return b2;
    }
    
    public static final byte[] a(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (ds2 != null && n2 >= 0 && n3 >= 0) {
            try {
                if (n3 < n2) {
                    return array;
                }
                if (ds2.v == 1) {
                    return b(ds, ds2, array, n, n2, n3, n4, n5);
                }
                if (ds2.v == 2) {
                    return d(ds, ds2, array, n, n2, n3, n5);
                }
                if (ds2.v == 3) {
                    return f(ds, ds2, array, n, n2, n3, n5);
                }
                if (ds2.v == 4 || ds2.v == 0) {
                    return h(ds, ds2, array, n, n2, n3, n5);
                }
                return array;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return array;
            }
        }
        return null;
    }
    
    public static final byte[] a(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) {
        if (ds2 != null && n2 >= 0 && n3 >= 0) {
            try {
                if (n3 < n2) {
                    return array;
                }
                if (ds2.v == 1) {
                    return b(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 2) {
                    return c(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 3) {
                    return e(ds, ds2, array, n, n2, n3, n4);
                }
                if (ds2.v == 4 || ds2.v == 0) {
                    return g(ds, ds2, array, n, n2, n3, n4);
                }
                return array;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return array;
            }
        }
        return null;
    }
    
    public static final short[] a(final ds ds, final ds ds2, final short[] array, final int n, final int n2, final int n3) {
        if (ds2 != null) {
            try {
                if (ds2.v == 1) {
                    return e(ds, ds2, array, n, n2, n3);
                }
                if (ds2.v == 4 || ds2.v == 0) {
                    return b(ds, ds2, array, n, n2, n3);
                }
                if (ds2.v == 3) {
                    return c(ds, ds2, array, n, n2, n3);
                }
                if (ds2.v == 2) {
                    return d(ds, ds2, array, n, n2, n3);
                }
                return array;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return array;
            }
        }
        return null;
    }
    
    private static final short[] b(final ds ds, final ds ds2, final short[] array, final int n, final int n2, final int n3) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 >= ds2.k && n3 != 1) {
            return array;
        }
        int n4 = (int)ds2.j;
        final int n5 = (int)ds2.k;
        if (n3 == 1) {
            n4 = (int)ds.j;
            final int n6 = (int)ds.k;
        }
        final int n7 = (int)ds2.j;
        final int[] array2 = new int[n7];
        int n8;
        if (n3 == 1) {
            n8 = n2 * (int)ds2.j / (int)ds.j;
        }
        else {
            n8 = n2;
        }
        if (n8 >= 0 && n8 < ds2.k) {
            ds2.c(n8);
            ds2.a(array2, n7);
            final byte[] array3 = new byte[n7];
            for (int i = 0; i < n7; ++i) {
                final int n9 = array2[i];
                final int n10 = (n9 & 0xFF000000) >> 24 & 0xFF;
                final int n11 = (n9 & 0xFF0000) >> 16 & 0xFF;
                final int n12 = (n9 & 0xFF00) >> 8 & 0xFF;
                final int n13 = n9 & 0xFF;
                if (n10 > 127 && (n11 + n12 + n13) / 3 < 64) {
                    array3[i] = -1;
                }
            }
            return a(array3, d.a(array, (int)ds.j, -1), n4);
        }
        return array;
    }
    
    private static int[] a(final ds ds) {
        int[] l = ds.l();
        if (l == null) {
            l = new int[256];
            for (int i = 0; i < 256; ++i) {
                l[i] = (0xFF000000 | i << 16 | i << 8 | i);
            }
        }
        else {
            for (int j = 0; j < l.length; ++j) {
                l[j] |= 0xFF000000;
            }
        }
        return l;
    }
    
    private static final int[] b(final ds ds, final ds ds2, int[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 < ds2.k && n3 < ds2.k) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds2);
            final int[] array2 = new int[(int)ds.j];
            final int n8 = (int)ds2.j;
            final int[] array3 = new int[n8];
            final byte[] array4 = new byte[n8];
            final byte[] ag = ds2.ag();
            boolean b = false;
            if (array == null) {
                array = new int[n];
                b = true;
            }
            for (int i = n2; i <= n3; ++i) {
                final int n9 = (i - n2) * (int)ds.j;
                if (!b) {
                    System.arraycopy(array, n9, array2, 0, (int)ds.j);
                }
                int n10;
                if (n4 == 1) {
                    n10 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n10 = i;
                }
                if (n10 >= 0 && n10 < ds2.k) {
                    if (ag == null) {
                        ds2.a(n10, array4);
                    }
                    else {
                        System.arraycopy(ag, n10 * n8, array4, 0, n8);
                    }
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a[array4[j] & 0xFF];
                    }
                    int[] array5;
                    if (b) {
                        array5 = b(array3, null, n5);
                    }
                    else {
                        array5 = b(array3, array2, n5);
                    }
                    System.arraycopy(array5, 0, array, n9, (int)ds.j);
                }
                else if (array != null) {
                    final int n11 = (int)ds.j;
                    final int[] array6 = new int[n11];
                    if (b) {
                        for (int k = 0; k < n11; ++k) {
                            if (b) {
                                array6[k] = fk.a;
                            }
                            else {
                                final int n12 = k;
                                if (n12 >= 0 && n12 < n11) {
                                    array6[k] = array2[n12];
                                }
                                else {
                                    array6[k] = fk.a;
                                }
                            }
                        }
                        System.arraycopy(array6, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                    else {
                        System.arraycopy(array2, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final short[] c(final ds ds, final ds ds2, final short[] array, final int n, final int n2, final int n3) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 >= ds2.k && n3 != 1) {
            return array;
        }
        int n4 = (int)ds2.j;
        final int n5 = (int)ds2.k;
        if (n3 == 1) {
            n4 = (int)ds.j;
            final int n6 = (int)ds.k;
        }
        final int[] a = a(ds2);
        final int n7 = (int)ds2.j;
        final byte[] array2 = new byte[n7];
        int n8;
        if (n3 == 1) {
            n8 = n2 * (int)ds2.j / (int)ds.j;
        }
        else {
            n8 = n2;
        }
        final byte[] ag = ds2.ag();
        if (n8 >= 0 && n8 < ds2.k) {
            if (ag == null) {
                ds2.a(n8, array2);
            }
            else {
                System.arraycopy(ag, n8 * n7, array2, 0, n7);
            }
            final byte[] array3 = new byte[n7];
            final int k4 = d.k4;
            for (int i = 0; i < n7; ++i) {
                final int n9 = a[array2[i] & 0xFF];
                if ((((n9 & 0xFF0000) >> 16 & 0xFF) + ((n9 & 0xFF00) >> 8 & 0xFF) + (n9 & 0xFF)) / 3 < k4) {
                    array3[i] = -1;
                }
            }
            return a(array3, d.a(array, (int)ds.j, -1), n4);
        }
        return array;
    }
    
    private static int[] b(final ds ds) {
        int[] l = ds.l();
        if (l == null) {
            l = new int[16];
            final int red = Color.white.getRed();
            final int n = 15;
            int n2 = red;
            for (int i = 0; i < 16; ++i) {
                n2 -= Math.max(red - n, 0);
                l[i] = (0xFF000000 | n2 << 16 | n2 << 8 | n2);
            }
        }
        return l;
    }
    
    private static final int[] c(final ds ds, final ds ds2, int[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 < ds2.k && n3 < ds2.k) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] b = b(ds2);
            final int[] array2 = new int[(int)ds.j];
            final int n8 = (int)ds2.j;
            final int n9 = (n8 + 1) / 2;
            final byte[] array3 = new byte[n9];
            final int[] array4 = new int[n8];
            final byte[] ag = ds2.ag();
            boolean b2 = false;
            if (array == null) {
                b2 = true;
                array = new int[n];
            }
            for (int i = n2; i <= n3; ++i) {
                final int n10 = (i - n2) * (int)ds.j;
                System.arraycopy(array, n10, array2, 0, (int)ds.j);
                int n11;
                if (n4 == 1) {
                    n11 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n11 = i;
                }
                if (n11 >= 0 && n11 < ds2.k) {
                    System.arraycopy(ag, n11 * n9, array3, 0, n9);
                    int n12 = 4;
                    for (int j = 0; j < n8; ++j) {
                        array4[j] = b[(array3[j / 2] & 0xFF) >> n12 & 0xF];
                        n12 = 4 - n12;
                    }
                    int[] array5;
                    if (b2) {
                        array5 = b(array4, null, n5);
                    }
                    else {
                        array5 = b(array4, array2, n5);
                    }
                    System.arraycopy(array5, 0, array, n10, (int)ds.j);
                }
                else if (array != null) {
                    final int n13 = (int)ds.j;
                    final int[] array6 = new int[n13];
                    if (b2) {
                        for (int k = 0; k < n13; ++k) {
                            if (b2) {
                                array6[k] = fk.a;
                            }
                            else {
                                final int n14 = k;
                                if (n14 >= 0 && n14 < n13) {
                                    array6[k] = array2[n14];
                                }
                                else {
                                    array6[k] = fk.a;
                                }
                            }
                        }
                        System.arraycopy(array6, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                    else {
                        System.arraycopy(array2, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final short[] d(final ds ds, final ds ds2, final short[] array, final int n, final int n2, final int n3) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 >= ds2.k && n3 != 1) {
            return array;
        }
        int n4 = (int)ds2.j;
        final int n5 = (int)ds2.k;
        if (n3 == 1) {
            n4 = (int)ds.j;
            final int n6 = (int)ds.k;
        }
        final int[] b = b(ds2);
        final int n7 = (int)ds2.j;
        final int n8 = (n7 + 1) / 2;
        final byte[] array2 = new byte[n8];
        int n9;
        if (n3 == 1) {
            n9 = n2 * (int)ds2.j / (int)ds.j;
        }
        else {
            n9 = n2;
        }
        if (n9 >= 0 && n9 < ds2.k) {
            System.arraycopy(ds2.ag(), n9 * n8, array2, 0, n8);
            final byte[] array3 = new byte[n7];
            int n10 = 4;
            for (int i = 0; i < n7; ++i) {
                final int n11 = b[(array2[i / 2] & 0xFF) >> n10 & 0xF];
                if ((((n11 & 0xFF0000) >> 16 & 0xFF) + ((n11 & 0xFF00) >> 8 & 0xFF) + (n11 & 0xFF)) / 3 < 64) {
                    array3[i] = -1;
                }
                n10 = 4 - n10;
            }
            return a(array3, d.a(array, (int)ds.j, -1), n4);
        }
        return array;
    }
    
    private static final short[] e(final ds ds, final ds ds2, final short[] array, final int n, final int n2, final int n3) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if (n2 < 0 || (n2 >= ds2.k && n3 != 1)) {
            return array;
        }
        int n4 = (int)ds2.j;
        final int n5 = (int)ds2.k;
        if (n3 == 1) {
            n4 = (int)ds.j;
            final int n6 = (int)ds.k;
        }
        short[] array2;
        if (n3 == 1) {
            array2 = a(ds2, n2 * (int)ds2.j / (int)ds.j);
        }
        else {
            array2 = a(ds2, n2);
        }
        if (array2 != null) {
            return a(d.a(array2, (int)ds2.j, (int)ds.j), d.a(array, (int)ds.j, -1), n4);
        }
        return array;
    }
    
    private static final short[] a(byte[] array, final byte[] array2, final int n) {
        if (n != array.length) {
            final byte[] array3 = new byte[n];
            final int length = array.length;
            for (int i = 0; i < n; ++i) {
                array3[i] = array[i * length / n];
            }
            array = array3;
        }
        final int min = Math.min(array.length, array2.length);
        final byte[] array4 = new byte[min];
        for (int j = 0; j < min; ++j) {
            array4[j] = (byte)((array2[j] & 0xFF) | (array[j] & 0xFF));
        }
        return d.d(array4);
    }
    
    private static final int[] d(final ds ds, final ds ds2, int[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] array2 = new int[(int)ds.j];
            final int[] array3 = new int[n5];
            int min = Math.min((int)ds.j, array2.length);
            boolean b = false;
            if (array == null) {
                array = new int[n];
                b = true;
            }
            for (int i = n2; i <= n3; ++i) {
                final int n8 = (i - n2) * (int)ds.j;
                if (n8 >= array.length) {
                    break;
                }
                if (n8 + min >= array.length) {
                    min = array.length - n8;
                }
                if (min <= 0) {
                    break;
                }
                System.arraycopy(array, n8, array2, 0, min);
                int n9;
                if (n4 == 1) {
                    n9 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n9 = i;
                }
                if (n9 >= 0 && n9 < ds2.k) {
                    final short[] a = a(ds2, n9);
                    if (a == null) {
                        return array;
                    }
                    final byte[] a2 = d.a(a, (int)ds2.j, n5);
                    final int c = c(ds2);
                    for (int min2 = Math.min(a2.length, array3.length), j = 0; j < min2; ++j) {
                        if (a2[j] == 0) {
                            array3[j] = fk.a;
                        }
                        else {
                            array3[j] = c;
                        }
                    }
                    int[] array4;
                    if (b) {
                        array4 = a(array3, null, n5);
                    }
                    else {
                        array4 = a(array3, array2, n5);
                    }
                    System.arraycopy(array4, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                }
                else if (array != null) {
                    System.arraycopy(array2, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                }
            }
            return array;
        }
        return array;
    }
    
    private static final int c(final ds ds) {
        if (ds.d() != null) {
            return ds.d().getRGB();
        }
        return fk.b;
    }
    
    private static final int a(final int[] array, final ds ds) {
        return a(array, ds, false);
    }
    
    private static final int b(final int[] array, final ds ds) {
        return a(array, ds, true);
    }
    
    private static final int a(final int[] array, final ds ds, final boolean b) {
        int n = 0;
        final int c = c(ds);
        int n3;
        final int n2 = n3 = ((c & 0xFF0000) >> 16 & 0xFF) + ((c & 0xFF00) >> 8 & 0xFF) + (c & 0xFF);
        for (int i = 0; i < array.length; ++i) {
            final int abs = Math.abs(((array[i] & 0xFF0000) >> 16 & 0xFF) + ((array[i] & 0xFF00) >> 8 & 0xFF) + (array[i] & 0xFF) - n2);
            if (b) {
                if (abs < n3) {
                    n3 = abs;
                    n = i;
                }
            }
            else if (abs > n3) {
                n3 = abs;
                n = i;
            }
        }
        return n;
    }
    
    private static final byte[] b(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            final int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                final int n7 = (int)ds.j;
                final int n8 = (int)ds.k;
            }
            b(ds);
            final int n9 = (int)ds.j;
            final int n10 = (int)ds2.j;
            final int n11 = (n9 + 1) / 2;
            final byte[] array2 = new byte[n11];
            final byte[] ag = ds.ag();
            byte[] array3 = new byte[n9];
            final int b = b(b(ds), ds2);
            final int a = a(b(ds), ds2);
            for (int i = n2; i <= n3; ++i) {
                final int n12 = i;
                boolean b2;
                if (n12 >= 0 && n12 * (n11 + 1) < ag.length) {
                    b2 = false;
                    System.arraycopy(ag, n12 * n11, array2, 0, n11);
                    int n13 = 4;
                    for (int j = 0; j < n9; ++j) {
                        array3[j] = (byte)((array2[j / 2] & 0xFF) >> n13 & 0xF);
                        n13 = 4 - n13;
                    }
                }
                else {
                    b2 = true;
                }
                short[] array4 = null;
                final int n14 = i;
                if (n14 >= 0 && n14 < ds2.k) {
                    if (n4 == 1) {
                        array4 = a(ds2, n14 * (int)ds2.j / (int)ds.j);
                    }
                    else {
                        array4 = a(ds2, n14);
                    }
                }
                if (array4 == null) {
                    final int length = array3.length;
                    final byte[] array5 = new byte[length];
                    for (int k = 0; k < length; ++k) {
                        final int n15 = k;
                        if (b2) {
                            array5[k] = (byte)(a & 0xFF);
                        }
                        else if (n15 >= 0 && n15 < length) {
                            array5[k] = array3[n15];
                        }
                        else {
                            array5[k] = (byte)(a & 0xFF);
                        }
                    }
                    array3 = array5;
                }
                else {
                    final byte[] a2 = d.a(array4, (int)ds2.j, n9);
                    final int min = Math.min(a2.length, array3.length);
                    final byte[] array6 = new byte[min];
                    for (int l = 0; l < min; ++l) {
                        final int n16 = l;
                        final int n17 = l;
                        if (n17 >= 0 && n17 < min && !b2) {
                            if (n16 >= 0 && n16 < min) {
                                if (a2[n16] != 0) {
                                    array6[l] = (byte)(b & 0xFF);
                                }
                                else {
                                    array6[l] = array3[n17];
                                }
                            }
                            else {
                                array6[l] = array3[n17];
                            }
                        }
                        else if (n16 >= 0 && n16 < min) {
                            if (a2[n16] != 0) {
                                array6[l] = (byte)(b & 0xFF);
                            }
                            else {
                                array6[l] = (byte)(a & 0xFF);
                            }
                        }
                        else {
                            array6[l] = (byte)(a & 0xFF);
                        }
                    }
                    array3 = array6;
                }
                int n18 = 0;
                final int min2 = Math.min(n11, array3.length / 2);
                final byte[] array7 = new byte[n11];
                for (int n19 = 0; n19 < min2; ++n19) {
                    final byte[] array8 = array7;
                    final int n20 = n19;
                    array8[n20] |= (byte)((array3[n18++] & 0xF) << 4);
                    final byte[] array9 = array7;
                    final int n21 = n19;
                    array9[n21] |= (byte)(array3[n18++] & 0xF);
                }
                if (ag != null) {
                    System.arraycopy(array7, 0, array, i * n11, n11);
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] b(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n5 == 1) {
            int n6 = (int)ds2.j;
            final int n7 = (int)ds2.k;
            if (n5 == 1) {
                n6 = (int)ds.j;
                final int n8 = (int)ds.k;
            }
            final int n9 = (int)ds.j;
            final byte[] array2 = new byte[n9];
            final int[] a = a(ds);
            final int b = b(a, ds2);
            final int a2 = a(a, ds2);
            for (int i = n2; i <= n3; ++i) {
                final int n10 = i;
                boolean b2;
                if (n10 >= 0 && n10 < ds.k) {
                    b2 = false;
                    if (array == null) {
                        ds.a(n10, array2);
                    }
                    else {
                        System.arraycopy(array, (n10 - n4) * n9, array2, 0, n9);
                    }
                }
                else {
                    b2 = true;
                }
                int n11;
                if (n5 == 1) {
                    n11 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n11 = i;
                }
                if (n11 >= 0 && n11 < ds2.k) {
                    final short[] a3 = a(ds2, n11);
                    if (a3 == null) {
                        return array;
                    }
                    final byte[] a4 = d.a(a3, (int)ds2.j, n6);
                    final int min = Math.min(a4.length, array2.length);
                    final byte[] array3 = new byte[min];
                    for (int j = 0; j < min; ++j) {
                        final int n12 = j;
                        final int n13 = j;
                        if (n13 >= 0 && n13 < min && !b2) {
                            if (n12 >= 0 && n12 < min && a4[n12] != 0) {
                                array3[j] = (byte)(b & 0xFF);
                            }
                            else {
                                array3[j] = array2[n13];
                            }
                        }
                        else if (n12 >= 0 && n12 < min) {
                            if (a4[n12] != 0) {
                                array3[j] = (byte)(b & 0xFF);
                            }
                            else {
                                array3[j] = (byte)(a2 & 0xFF);
                            }
                        }
                        else {
                            array3[j] = (byte)(a2 & 0xFF);
                        }
                    }
                    if (array != null) {
                        System.arraycopy(array3, 0, array, (i - n2) * n9, array2.length);
                    }
                }
                else if (b2) {
                    final int length = array2.length;
                    final byte[] array4 = new byte[length];
                    for (int k = 0; k < length; ++k) {
                        array4[k] = (byte)(a2 & 0xFF);
                    }
                    if (array != null) {
                        System.arraycopy(array4, 0, array, i * n9, array2.length);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final int[] a(final int[] array) {
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = ((array[i] & 0xFF0000) >> 16 & 0xFF) + ((array[i] & 0xFF00) >> 8 & 0xFF) + (array[i] & 0xFF);
        }
        return array2;
    }
    
    private static final byte[] a(final int[] array, final int[] array2, final int[] array3, final int n) {
        final byte[] array4 = new byte[n];
        final int length = array.length;
        for (int i = 0; i < n; ++i) {
            int n2 = 765;
            int n3 = 0;
            boolean b = false;
            final int n4 = array3[i] | 0xFF000000;
            for (int j = 0; j < length; ++j) {
                if (array[j] == n4) {
                    n3 = j;
                    b = true;
                    break;
                }
            }
            if (!b) {
                final int n5 = ((n4 & 0xFF0000) >> 16 & 0xFF) + ((n4 & 0xFF00) >> 8 & 0xFF) + (n4 & 0xFF);
                for (int k = 0; k < length; ++k) {
                    final int abs = Math.abs(array2[k] - n5);
                    if (abs < n2) {
                        n2 = abs;
                        n3 = k;
                    }
                }
            }
            final int n6 = (n4 & 0xFF0000) >> 16 & 0xFF;
            final int n7 = (n4 & 0xFF00) >> 8 & 0xFF;
            final int n8 = n4 & 0xFF & 0xFF;
            final int n9 = array[n3];
            array[n3] = (0xFF000000 | (n6 + ((n9 & 0xFF0000) >> 16 & 0xFF)) / 2 << 16 | (n7 + ((n9 & 0xFF00) >> 8 & 0xFF)) / 2 << 8 | (n8 + (n9 & 0xFF & 0xFF)) / 2);
            array4[i] = (byte)(n3 & 0xFF);
        }
        return array4;
    }
    
    private static final byte[] c(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds2);
            final int[] a2 = a(ds);
            final int n8 = (int)ds.j;
            final int n9 = (n8 + 1) / 2;
            final byte[] array2 = new byte[n9];
            final int[] array3 = new int[n8];
            final int n10 = (int)ds2.j;
            final int n11 = (n10 + 1) / 2;
            final byte[] array4 = new byte[n11];
            final byte[] ag = ds2.ag();
            final int[] array5 = new int[n10];
            final int length = a2.length;
            final int[] a3 = a(a2);
            for (int i = n2; i <= n3; ++i) {
                final int n12 = i;
                int n13 = 4;
                boolean b;
                if (n12 >= 0 && n12 < ds.k) {
                    b = false;
                    if (array == null) {
                        ds.a(n12, array2);
                    }
                    else {
                        System.arraycopy(array, n12 * n9, array2, 0, n9);
                    }
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a2[(array2[j / 2] & 0xFF) >> n13 & 0xF];
                        n13 = 4 - n13;
                    }
                }
                else {
                    b = true;
                }
                int n14;
                if (n4 == 1) {
                    n14 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n14 = i;
                }
                if (n14 >= 0 && n14 < ds2.k) {
                    System.arraycopy(ag, n14 * n11, array4, 0, n11);
                    int n15 = 4;
                    for (int k = 0; k < n10; ++k) {
                        array5[k] = a[(array4[k / 2] & 0xFF) >> n15 & 0xF];
                        n15 = 4 - n15;
                    }
                    int[] array6;
                    if (b) {
                        array6 = b(array5, null, n5);
                    }
                    else {
                        array6 = b(array5, array3, n5);
                    }
                    final byte[] a4 = a(a2, a3, array6, Math.min(array6.length, n8));
                    int n16 = 0;
                    final int min = Math.min(n9, a4.length / 2);
                    final byte[] array7 = new byte[n9];
                    for (int l = 0; l < min; ++l) {
                        final byte[] array8 = array7;
                        final int n17 = l;
                        array8[n17] |= (byte)((a4[n16++] & 0xF) << 4);
                        final byte[] array9 = array7;
                        final int n18 = l;
                        array9[n18] |= (byte)(a4[n16++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array7, 0, array, i * n9, n9);
                    }
                }
                else {
                    final int length2 = array3.length;
                    final int[] array10 = new int[length2];
                    for (int n19 = 0; n19 < length2; ++n19) {
                        if (b) {
                            array10[n19] = fk.a;
                        }
                        else {
                            final int n20 = n19;
                            if (n20 >= 0 && n20 < length2) {
                                array10[n19] = array3[n20];
                            }
                            else {
                                array10[n19] = fk.a;
                            }
                        }
                    }
                    final byte[] a5 = a(a2, a3, array10, length2);
                    int n21 = 0;
                    final int min2 = Math.min(n9, a5.length / 2);
                    final byte[] array11 = new byte[n9];
                    for (int n22 = 0; n22 < min2; ++n22) {
                        final byte[] array12 = array11;
                        final int n23 = n22;
                        array12[n23] |= (byte)((a5[n21++] & 0xF) << 4);
                        final byte[] array13 = array11;
                        final int n24 = n22;
                        array13[n24] |= (byte)(a5[n21++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array11, 0, array, i * n9, array2.length);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] d(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds2);
            final int[] a2 = a(ds);
            final int n8 = (int)ds.j;
            final byte[] array2 = new byte[n8];
            final int[] array3 = new int[(int)ds.j];
            final int n9 = (int)ds2.j;
            final int n10 = (n9 + 1) / 2;
            final byte[] array4 = new byte[n10];
            final byte[] ag = ds2.ag();
            final int[] array5 = new int[n9];
            final int length = a2.length;
            final int[] a3 = a(a2);
            for (int i = n2; i <= n3; ++i) {
                final int n11 = i;
                boolean b;
                if (n11 >= 0 && n11 < ds.k) {
                    b = false;
                    if (array == null) {
                        ds.a(n11, array2);
                    }
                    else {
                        System.arraycopy(array, n11 * n8, array2, 0, n8);
                    }
                }
                else {
                    b = true;
                }
                int n12;
                if (n4 == 1) {
                    n12 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n12 = i;
                }
                if (n12 >= 0 && n12 < ds2.k) {
                    System.arraycopy(ag, n12 * n10, array4, 0, n10);
                    int n13 = 4;
                    for (int j = 0; j < n9; ++j) {
                        array5[j] = a[(array4[j / 2] & 0xFF) >> n13 & 0xF];
                        n13 = 4 - n13;
                    }
                    if (!b) {
                        for (int k = 0; k < n8; ++k) {
                            array3[k] = a2[array2[k] & 0xFF];
                        }
                    }
                    int[] array6;
                    if (b) {
                        array6 = b(array5, null, n5);
                    }
                    else {
                        array6 = b(array5, array3, n5);
                    }
                    final byte[] a4 = a(a2, a3, array6, Math.min(array6.length, n8));
                    if (array != null) {
                        System.arraycopy(a4, 0, array, i * n8, n8);
                    }
                }
                else if (b) {
                    final int n14 = (int)ds.j;
                    final int[] array7 = new int[n14];
                    for (int l = 0; l < n14; ++l) {
                        array7[l] = fk.a;
                    }
                    final byte[] a5 = a(a2, a3, array7, n14);
                    if (array != null) {
                        System.arraycopy(a5, 0, array, i * n8, n14);
                    }
                }
                else if (array != null) {
                    System.arraycopy(array2, 0, array, i * n8, array2.length);
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] e(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds2);
            final int[] a2 = a(ds);
            final int n8 = (int)ds.j;
            final int n9 = (n8 + 1) / 2;
            final byte[] array2 = new byte[n9];
            final int[] array3 = new int[n8];
            final int n10 = (int)ds2.j;
            final byte[] array4 = new byte[n10];
            final byte[] ag = ds2.ag();
            final int[] array5 = new int[n10];
            final int length = a2.length;
            final int[] a3 = a(a2);
            for (int i = n2; i <= n3; ++i) {
                final int n11 = i;
                boolean b;
                if (n11 >= 0 && n11 < ds.k) {
                    b = false;
                    if (array == null) {
                        ds.a(n11, array2);
                    }
                    else {
                        System.arraycopy(array, n11 * n9, array2, 0, n9);
                    }
                    int n12 = 4;
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a2[(array2[j / 2] & 0xFF) >> n12 & 0xF];
                        n12 = 4 - n12;
                    }
                }
                else {
                    b = true;
                }
                int n13;
                if (n4 == 1) {
                    n13 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n13 = i;
                }
                if (n13 >= 0 && n13 < ds2.k) {
                    if (ag == null) {
                        ds2.a(n13, array4);
                    }
                    else {
                        System.arraycopy(ag, n13 * n10, array4, 0, n10);
                    }
                    for (int k = 0; k < n10; ++k) {
                        array5[k] = a[array4[k] & 0xFF];
                    }
                    int[] array6;
                    if (b) {
                        array6 = b(array5, null, n5);
                    }
                    else {
                        array6 = b(array5, array3, n5);
                    }
                    final byte[] a4 = a(a2, a3, array6, Math.min(array6.length, n8));
                    int n14 = 0;
                    final int min = Math.min(n9, a4.length / 2);
                    final byte[] array7 = new byte[n9];
                    for (int l = 0; l < min; ++l) {
                        final byte[] array8 = array7;
                        final int n15 = l;
                        array8[n15] |= (byte)((a4[n14++] & 0xF) << 4);
                        final byte[] array9 = array7;
                        final int n16 = l;
                        array9[n16] |= (byte)(a4[n14++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array7, 0, array, i * n9, n9);
                    }
                }
                else {
                    final int length2 = array3.length;
                    final int[] array10 = new int[length2];
                    for (int n17 = 0; n17 < length2; ++n17) {
                        if (b) {
                            array10[n17] = fk.a;
                        }
                        else {
                            final int n18 = n17;
                            if (n18 >= 0 && n18 < length2) {
                                array10[n17] = array3[n18];
                            }
                            else {
                                array10[n17] = fk.a;
                            }
                        }
                    }
                    final byte[] a5 = a(a2, a3, array10, length2);
                    int n19 = 0;
                    final int min2 = Math.min(n9, a5.length / 2);
                    final byte[] array11 = new byte[n9];
                    for (int n20 = 0; n20 < min2; ++n20) {
                        final byte[] array12 = array11;
                        final int n21 = n20;
                        array12[n21] |= (byte)((a5[n19++] & 0xF) << 4);
                        final byte[] array13 = array11;
                        final int n22 = n20;
                        array13[n22] |= (byte)(a5[n19++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array11, 0, array, i * n9, array2.length);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] f(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds2);
            final int[] a2 = a(ds);
            final int n8 = (int)ds.j;
            final byte[] array2 = new byte[n8];
            final int[] array3 = new int[(int)ds.j];
            final int n9 = (int)ds2.j;
            final int[] array4 = new int[n9];
            final byte[] array5 = new byte[n9];
            final byte[] ag = ds2.ag();
            final int length = a2.length;
            final int[] a3 = a(a2);
            for (int i = n2; i <= n3; ++i) {
                boolean b = false;
                final int n10 = i;
                if (n10 >= 0 && n10 < ds.k) {
                    if (array == null) {
                        ds.a(n10, array2);
                    }
                    else {
                        System.arraycopy(array, n10 * n8, array2, 0, n8);
                    }
                }
                else {
                    b = true;
                }
                if (!b) {
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a2[array2[j] & 0xFF];
                    }
                }
                int n11;
                if (n4 == 1) {
                    n11 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n11 = i;
                }
                if (n11 >= 0 && n11 < ds2.k) {
                    if (ag == null) {
                        ds2.a(n11, array5);
                    }
                    else {
                        System.arraycopy(ag, n11 * n9, array5, 0, n9);
                    }
                    for (int k = 0; k < n9; ++k) {
                        array4[k] = a[array5[k] & 0xFF];
                    }
                    int[] array6;
                    if (b) {
                        array6 = b(array4, null, n5);
                    }
                    else {
                        array6 = b(array4, array3, n5);
                    }
                    final byte[] a4 = a(a2, a3, array6, Math.min(array6.length, n8));
                    if (array != null) {
                        System.arraycopy(a4, 0, array, i * n8, n8);
                    }
                }
                else if (array != null) {
                    System.arraycopy(array2, 0, array, i * n8, array2.length);
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] g(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            a(ds2);
            final int[] a = a(ds);
            final int n8 = (int)ds.j;
            final int n9 = (n8 + 1) / 2;
            final byte[] array2 = new byte[n9];
            final int[] array3 = new int[n8];
            final int[] array4 = new int[(int)ds2.j];
            final int length = a.length;
            final int[] a2 = a(a);
            for (int i = n2; i <= n3; ++i) {
                final int n10 = i;
                int n11 = 4;
                boolean b;
                if (n10 >= 0 && n10 < ds.k) {
                    b = false;
                    if (array == null) {
                        ds.a(n10, array2);
                    }
                    else {
                        System.arraycopy(array, n10 * n9, array2, 0, n9);
                    }
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a[(array2[j / 2] & 0xFF) >> n11 & 0xF];
                        n11 = 4 - n11;
                    }
                }
                else {
                    b = true;
                }
                int n12;
                if (n4 == 1) {
                    n12 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n12 = i;
                }
                if (n12 >= 0 && n12 < ds2.k) {
                    ds2.c(n12);
                    ds2.a(array4, (int)ds2.j);
                    int[] array5;
                    if (b) {
                        array5 = b(array4, null, n5);
                    }
                    else {
                        array5 = b(array4, array3, n5);
                    }
                    final byte[] a3 = a(a, a2, array5, Math.min(array5.length, n8));
                    int n13 = 0;
                    final int min = Math.min(n9, a3.length / 2);
                    final byte[] array6 = new byte[n9];
                    for (int k = 0; k < min; ++k) {
                        final byte[] array7 = array6;
                        final int n14 = k;
                        array7[n14] |= (byte)((a3[n13++] & 0xF) << 4);
                        final byte[] array8 = array6;
                        final int n15 = k;
                        array8[n15] |= (byte)(a3[n13++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array6, 0, array, i * n9, n9);
                    }
                }
                else {
                    final int length2 = array3.length;
                    final int[] array9 = new int[length2];
                    for (int l = 0; l < length2; ++l) {
                        if (b) {
                            array9[l] = fk.a;
                        }
                        else {
                            final int n16 = l;
                            if (n16 >= 0 && n16 < length2) {
                                array9[l] = array3[n16];
                            }
                            else {
                                array9[l] = fk.a;
                            }
                        }
                    }
                    final byte[] a4 = a(a, a2, array9, length2);
                    int n17 = 0;
                    final int min2 = Math.min(n9, a4.length / 2);
                    final byte[] array10 = new byte[n9];
                    for (int n18 = 0; n18 < min2; ++n18) {
                        final byte[] array11 = array10;
                        final int n19 = n18;
                        array11[n19] |= (byte)((a4[n17++] & 0xF) << 4);
                        final byte[] array12 = array10;
                        final int n20 = n18;
                        array12[n20] |= (byte)(a4[n17++] & 0xF);
                    }
                    if (array != null) {
                        System.arraycopy(array10, 0, array, i * n9, array2.length);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final byte[] h(final ds ds, final ds ds2, final byte[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] a = a(ds);
            final int n8 = (int)ds.j;
            final byte[] array2 = new byte[n8];
            final int[] array3 = new int[(int)ds.j];
            final int[] array4 = new int[(int)ds2.j];
            final int length = a.length;
            final int[] a2 = a(a);
            for (int i = n2; i <= n3; ++i) {
                final int n9 = i;
                boolean b;
                if (n9 >= 0 && n9 < ds.k) {
                    b = false;
                    if (array == null) {
                        ds.a(n9, array2);
                    }
                    else {
                        System.arraycopy(array, n9 * n8, array2, 0, n8);
                    }
                    for (int j = 0; j < n8; ++j) {
                        array3[j] = a[array2[j] & 0xFF];
                    }
                }
                else {
                    b = true;
                }
                int n10;
                if (n4 == 1) {
                    n10 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n10 = i;
                }
                if (n10 >= 0 && n10 < ds2.k) {
                    ds2.c(n10);
                    ds2.a(array4, (int)ds2.j);
                    int[] array5;
                    if (b) {
                        array5 = b(array4, null, n5);
                    }
                    else {
                        array5 = b(array4, array3, n5);
                    }
                    final byte[] a3 = a(a, a2, array5, Math.min(array5.length, n8));
                    if (array != null) {
                        System.arraycopy(a3, 0, array, i * n8, n8);
                    }
                }
                else if (array != null) {
                    System.arraycopy(array2, 0, array, i * n8, array2.length);
                }
            }
            return array;
        }
        return array;
    }
    
    private static final int[] e(final ds ds, final ds ds2, int[] array, final int n, final int n2, final int n3, final int n4) throws Exception {
        if (ds2 == null) {
            return array;
        }
        if ((n2 < ds2.k && n3 < ds2.k) || n4 == 1) {
            int n5 = (int)ds2.j;
            final int n6 = (int)ds2.k;
            if (n4 == 1) {
                n5 = (int)ds.j;
                final int n7 = (int)ds.k;
            }
            final int[] array2 = new int[(int)ds.j];
            final int[] array3 = new int[(int)ds2.j];
            boolean b = false;
            if (array == null) {
                b = true;
                array = new int[n];
            }
            for (int i = n2; i < n3; ++i) {
                System.arraycopy(array, (i - n2) * (int)ds.j, array2, 0, (int)ds.j);
                int n8;
                if (n4 == 1) {
                    n8 = i * (int)ds2.j / (int)ds.j;
                }
                else {
                    n8 = i;
                }
                if (n8 >= 0 && n8 < ds2.k) {
                    ds2.c(n8);
                    ds2.a(array3, (int)ds2.j);
                    int[] array4;
                    if (b) {
                        array4 = a(array3, null, n5);
                    }
                    else {
                        array4 = a(array3, array2, n5);
                    }
                    System.arraycopy(array4, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                }
                else if (array != null) {
                    final int n9 = (int)ds.j;
                    final int[] array5 = new int[n9];
                    if (b) {
                        for (int j = 0; j < n9; ++j) {
                            if (b) {
                                array5[j] = fk.a;
                            }
                            else {
                                final int n10 = j;
                                if (n10 >= 0 && n10 < n9) {
                                    array5[j] = array2[n10];
                                }
                                else {
                                    array5[j] = fk.a;
                                }
                            }
                        }
                        System.arraycopy(array5, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                    else {
                        System.arraycopy(array2, 0, array, (i - n2) * (int)ds.j, (int)ds.j);
                    }
                }
            }
            return array;
        }
        return array;
    }
    
    private static final short[] a(final ds ds, final int n) throws Exception {
        short[] array = null;
        if (ds != null && n < ds.k) {
            if (ds.j()) {
                final int u = ds.u();
                final byte[] array2 = new byte[u];
                ds.b(n, array2);
                array = new short[u];
                for (int i = 0; i < u; ++i) {
                    array[i] = (short)(array2[i] & 0xFF);
                }
            }
            else {
                if (n >= ds.y.size()) {
                    return null;
                }
                array = ds.y.elementAt(n);
            }
        }
        return array;
    }
    
    private static final int[] a(int[] array, final int[] array2, final int n) {
        if (n != array.length) {
            final int[] array3 = new int[n];
            final int length = array.length;
            for (int i = 0; i < n; ++i) {
                array3[i] = array[i * length / n];
            }
            array = array3;
        }
        if (array2 == null) {
            final int length2 = array.length;
            final int[] array4 = new int[length2];
            for (int j = 0; j < length2; ++j) {
                array4[j] = array[j];
            }
            return array4;
        }
        final int min = Math.min(array.length, array2.length);
        final int[] array5 = new int[min];
        for (int k = 0; k < min; ++k) {
            array5[k] = (array2[k] & array[k]);
        }
        return array5;
    }
    
    private static final int[] b(int[] array, final int[] array2, final int n) {
        if (n != array.length) {
            final int[] array3 = new int[n];
            final int length = array.length;
            for (int i = 0; i < n; ++i) {
                array3[i] = array[i * length / n];
            }
            array = array3;
        }
        if (array2 == null) {
            final int length2 = array.length;
            final int[] array4 = new int[length2];
            for (int j = 0; j < length2; ++j) {
                array4[j] = array[j];
            }
            return array4;
        }
        final int min = Math.min(array.length, array2.length);
        final int[] array5 = new int[min];
        for (int k = 0; k < min; ++k) {
            final int n2 = array2[k];
            final int n3 = array[k];
            array5[k] = (0xFF000000 | (((n2 & 0xFF0000) >> 16 & 0xFF) + ((n3 & 0xFF0000) >> 16 & 0xFF)) / 2 << 16 | (((n2 & 0xFF00) >> 8 & 0xFF) + ((n3 & 0xFF00) >> 8 & 0xFF)) / 2 << 8 | ((n2 & 0xFF) + (n3 & 0xFF)) / 2);
        }
        return array5;
    }
    
    static {
        a = Color.white.getRGB();
        b = Color.black.getRGB();
    }
}
