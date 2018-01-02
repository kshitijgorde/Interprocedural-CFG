// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Insets;

class p extends o
{
    static int b;
    static int c;
    static int d;
    static int e;
    private int[] f;
    private int g;
    private int h;
    private int i;
    private int j;
    private Insets k;
    private Image[] l;
    private Object[] m;
    private int[] n;
    
    double a(final double[] array) {
        final boolean g = GraphSerie.G;
        double n = array[0];
        int n2 = 1;
        while (true) {
            while (true) {
                Label_0029: {
                    if (!g) {
                        break Label_0029;
                    }
                    Math.max(array[n2], n);
                    final double n3;
                    n = n3;
                    ++n2;
                }
                if (n2 < array.length) {
                    continue;
                }
                break;
            }
            final double n3 = n;
            if (!g) {
                return n3;
            }
            continue;
        }
    }
    
    double b(final double[] array) {
        final boolean g = GraphSerie.G;
        double n = array[0];
        int n2 = 1;
        while (true) {
            while (true) {
                Label_0029: {
                    if (!g) {
                        break Label_0029;
                    }
                    Math.min(array[n2], n);
                    final double n3;
                    n = n3;
                    ++n2;
                }
                if (n2 < array.length) {
                    continue;
                }
                break;
            }
            final double n3 = n;
            if (!g) {
                return n3;
            }
            continue;
        }
    }
    
    int a(final int n, final double[] array, final double n2, final double n3, final double n4) {
        final boolean g = GraphSerie.G;
        double a = this.a(array);
        double b = this.b(array);
        Label_0076: {
            if (!super.a.cj) {
                a = ((a < 0.0) ? 0.0 : a);
                if (!g) {
                    break Label_0076;
                }
            }
            a = ((a < super.a.ci) ? super.a.ci : a);
        }
        Label_0133: {
            if (!super.a.cj) {
                b = ((b > 0.0) ? 0.0 : b);
                if (!g) {
                    break Label_0133;
                }
            }
            b = ((b > super.a.ch) ? super.a.ch : b);
        }
        final double n5 = a - b;
        double n6 = 0.0;
        Label_0185: {
            if (a == n4 && n == p.b) {
                n6 = n2;
                if (!g) {
                    break Label_0185;
                }
            }
            if (a == n4 && n == p.c) {
                n6 = n3;
            }
        }
        Label_0227: {
            if (b == n4 && n == p.b) {
                n6 = n3;
                if (!g) {
                    break Label_0227;
                }
            }
            if (b == n4 && n == p.c) {
                n6 = n2;
            }
        }
        if (a != n4 && b != n4) {
            double n7 = 0.0;
            Label_0279: {
                if (n == p.b) {
                    n7 = a - n4;
                    if (!g) {
                        break Label_0279;
                    }
                }
                if (n == p.c) {
                    n7 = n4 - b;
                }
            }
            n6 = (n3 - n2) * (n7 * 100.0 / n5) / 100.0 + n2;
        }
        return super.a.bf ? ((int)Math.ceil(n6)) : ((int)Math.floor(n6));
    }
    
    int[] e() {
        final boolean g = GraphSerie.G;
        final Rectangle j = this.j();
        final Rectangle k = this.k();
        final double n = super.a.g.length;
        final int n2 = super.a.bf ? (k.y + k.height) : j.x;
        final double n3 = super.a.bf ? (k.height / n) : (j.width / n);
        final int[] array = new int[(int)n];
        int n4 = 0;
        while (true) {
            Label_0213: {
                if (!g) {
                    break Label_0213;
                }
                Label_0210: {
                    if (!super.a.bg) {
                        array[n4] = (int)Math.floor(super.a.bf ? (n2 - (n4 + 1) * n3) : (n2 + (n4 + 1) * n3));
                        if (!g) {
                            break Label_0210;
                        }
                    }
                    array[n4] = (int)Math.floor(super.a.bf ? (n2 - n4 * n3) : (n2 + n4 * n3));
                }
                ++n4;
            }
            if (n4 >= n) {
                return array;
            }
            continue;
        }
    }
    
    int a(final Object o) {
        final boolean g = GraphSerie.G;
        final Axis axis = super.a.bf ? super.a.bF : super.a.bE;
        final Axis axis2 = super.a.bf ? super.a.bE : super.a.bF;
        if (!super.a.bf && !axis.l) {
            return 0;
        }
        final Graphics graphics = super.a.getGraphics();
        int n = 0;
        DecimalFormat decimalFormat = null;
        String bw = null;
        double[] array = null;
        String[] array2 = null;
        if (o instanceof double[]) {
            final FontMetrics fontMetrics = graphics.getFontMetrics(axis.n);
            array = (double[])o;
            bw = super.a.bw;
            if (bw != null) {
                decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(bw);
            }
        }
        else {
            final FontMetrics fontMetrics = graphics.getFontMetrics(axis2.n);
            array2 = (String[])o;
        }
        int n2 = 0;
        if (o instanceof double[]) {
            n2 = (super.a.cj ? (array.length + 1) : array.length);
        }
        int n3 = 0;
        Label_0357: {
            break Label_0357;
            int i;
            int length;
            do {
                String s = null;
                Label_0329: {
                    if (o instanceof double[]) {
                        Double n4 = null;
                        Label_0295: {
                            if (n3 < array.length) {
                                n4 = new Double(array[n3]);
                                final double ci = array[n3];
                                if (!g) {
                                    break Label_0295;
                                }
                            }
                            n4 = new Double(super.a.ci);
                            final double ci = super.a.ci;
                        }
                        double ci;
                        s = ((bw != null) ? decimalFormat.format(n4) : Double.toString(ci));
                        if (!g) {
                            break Label_0329;
                        }
                    }
                    s = array2[n3];
                }
                final FontMetrics fontMetrics;
                final int stringWidth = fontMetrics.stringWidth(s);
                n = ((stringWidth > n) ? stringWidth : n);
                ++n3;
                i = n3;
                if (o instanceof double[]) {
                    length = n2;
                    if (!g) {
                        continue;
                    }
                    continue;
                }
                else {
                    length = array2.length;
                }
            } while (i < length);
        }
        return n;
    }
    
    int b(final Object o) {
        final boolean g = GraphSerie.G;
        final Axis axis = super.a.bf ? super.a.bF : super.a.bE;
        final String[] array = (o instanceof double[]) ? this.a(axis, (double[])o) : o;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0085: {
                    if (!g) {
                        break Label_0085;
                    }
                    Math.max(array[n2].length(), n);
                    final int n3;
                    n = n3;
                    ++n2;
                }
                if (n2 < array.length) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!g) {
                return n3;
            }
            continue;
        }
    }
    
    String[] f() {
        final boolean g = GraphSerie.G;
        final Object[] n = this.n();
        final Object o = super.a.bf ? super.a.g : n[1];
        final int b = this.b(o);
        String[] a;
        if (o instanceof double[]) {
            a = this.a(super.a.bE, (double[])o);
        }
        else {
            a = (String[])o;
        }
        final String[] array = new String[a.length];
        int n2 = 0;
    Label_0137_Outer:
        while (true) {
            Label_0186: {
                if (!g) {
                    break Label_0186;
                }
                final String[] array2;
                final String s = array2[n2];
                if (s.length() < b) {
                    final StringBuffer sb = new StringBuffer(s);
                    int n3 = 1;
                    while (true) {
                        while (true) {
                            Label_0140: {
                                if (!g) {
                                    break Label_0140;
                                }
                                sb.insert(0, ' ');
                                ++n3;
                            }
                            if (n3 <= b - s.length()) {
                                continue Label_0137_Outer;
                            }
                            break;
                        }
                        array[n2] = sb.toString();
                        if (g) {
                            if (g) {
                                continue;
                            }
                        }
                        break;
                    }
                }
                else {
                    array[n2] = s;
                }
                ++n2;
            }
            if (n2 < a.length) {
                continue;
            }
            final String[] array2 = array;
            if (!g) {
                return array2;
            }
            continue;
        }
    }
    
    int g() {
        int n = 0;
        if ((!super.a.bf && super.a.bF.m) || (super.a.bf && super.a.bE.l)) {
            final FontMetrics fontMetrics = super.a.getGraphics().getFontMetrics(super.a.getXAxis().n);
            n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        }
        return n;
    }
    
    int a(final int n) {
        final boolean g = GraphSerie.G;
        final Vector i = super.a.i;
        super.a.getGraphics();
        DecimalFormat decimalFormat = null;
        int max = 0;
        for (int j = 0; j < i.size(); ++j) {
            final GraphSerie graphSerie = i.elementAt(j);
            if (graphSerie.p || g) {
                final double[] a = graphSerie.a;
                final String bw = super.a.bw;
                if (bw != null) {
                    if (decimalFormat == null) {
                        decimalFormat = new DecimalFormat();
                    }
                    decimalFormat.applyPattern(bw);
                }
                final FontMetrics fontMetrics = super.a.getGraphics().getFontMetrics(graphSerie.c);
                int n2 = 0;
                while (true) {
                    Label_0251: {
                        if (!g) {
                            break Label_0251;
                        }
                        if ((n == p.d) ? (a[n2] < 0.0) : (a[n2] >= 0.0)) {
                            final String s = (bw != null) ? decimalFormat.format(a[n2]) : Double.toString(a[n2]);
                            int stringWidth = 0;
                            Label_0239: {
                                if (graphSerie instanceof LineSerie) {
                                    stringWidth = fontMetrics.stringWidth(s) / 2;
                                    if (!g) {
                                        break Label_0239;
                                    }
                                }
                                if (graphSerie instanceof BarSerie) {
                                    stringWidth = fontMetrics.stringWidth(s);
                                }
                            }
                            max = Math.max(stringWidth, max);
                        }
                        ++n2;
                    }
                    if (n2 < a.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        return max;
    }
    
    int h() {
        final boolean g = GraphSerie.G;
        final FontMetrics fontMetrics = super.a.getGraphics().getFontMetrics(super.a.bF.n);
        int n = 0;
        DecimalFormat decimalFormat = null;
        if (!super.a.bf && super.a.bF.m) {
            final String[] g2 = super.a.g;
            final String s = g2[g2.length - 1];
            Label_0104: {
                if (!super.a.bi) {
                    n = fontMetrics.stringWidth(s);
                    if (!g) {
                        break Label_0104;
                    }
                }
                n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
            }
            if (!g) {
                return n;
            }
        }
        if (super.a.bf && super.a.bE.l) {
            final double a = this.a(this.i());
            final String bw = super.a.bw;
            if (bw != null) {
                decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(bw);
            }
            if (decimalFormat != null) {
                n = fontMetrics.stringWidth(decimalFormat.format(a));
                if (!g) {
                    return n;
                }
            }
            n = fontMetrics.stringWidth(Double.toString(a));
        }
        return n;
    }
    
    double[] i() {
        final boolean g = GraphSerie.G;
        double[] array = null;
        final Vector i = super.a.i;
        int n = 1;
        if (i != null) {
            int n2 = 0;
            int n3 = 0;
            double[] array2;
            while (true) {
                while (true) {
                    Label_0126: {
                        if (!g) {
                            break Label_0126;
                        }
                        final Object element = i.elementAt(n3);
                        final GraphSerie graphSerie = (GraphSerie)element;
                        if (!(graphSerie instanceof StackBarSerie) || (graphSerie instanceof StackBarSerie && n != 0)) {
                            if (graphSerie instanceof OHLCSerie) {
                                int n4 = 0;
                                while (true) {
                                    Label_0088: {
                                        if (!g) {
                                            break Label_0088;
                                        }
                                        n2 += 4;
                                        ++n4;
                                    }
                                    if (n4 < graphSerie.b.length) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            else {
                                n2 += graphSerie.a.length;
                            }
                            if (graphSerie instanceof StackBarSerie) {
                                n = 0;
                            }
                        }
                        ++n3;
                    }
                    if (n3 < i.size()) {
                        continue;
                    }
                    break;
                }
                Object element;
                array2 = (double[])(element = new double[n2]);
                if (g) {
                    continue;
                }
                break;
            }
            array = array2;
            int n5 = 0;
            int n6 = 0;
            double[] array4;
            while (true) {
                while (true) {
                    Label_0295: {
                        if (!g) {
                            break Label_0295;
                        }
                        final Object o = i.elementAt(n6);
                        final GraphSerie graphSerie2 = (GraphSerie)o;
                        if (!(graphSerie2 instanceof StackBarSerie)) {
                            if (graphSerie2 instanceof OHLCSerie) {
                                int n7 = 0;
                            Label_0221_Outer:
                                while (true) {
                                    Label_0240: {
                                        if (!g) {
                                            break Label_0240;
                                        }
                                        final double[] array3 = graphSerie2.b[n7];
                                        int n8 = 0;
                                        while (true) {
                                            while (true) {
                                                Label_0224: {
                                                    if (!g) {
                                                        break Label_0224;
                                                    }
                                                    array[n5++] = array3[n8];
                                                    ++n8;
                                                }
                                                if (n8 < array3.length) {
                                                    continue Label_0221_Outer;
                                                }
                                                break;
                                            }
                                            if (g) {
                                                continue;
                                            }
                                            break;
                                        }
                                        ++n7;
                                    }
                                    if (n7 < graphSerie2.b.length) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            else {
                                final double[] a = graphSerie2.a;
                                int n9 = 0;
                                while (true) {
                                    Label_0284: {
                                        if (!g) {
                                            break Label_0284;
                                        }
                                        array[n5++] = a[n9];
                                        ++n9;
                                    }
                                    if (n9 < a.length) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                        }
                        ++n6;
                    }
                    if (n6 < i.size()) {
                        continue;
                    }
                    break;
                }
                Object o;
                array4 = (double[])(o = this.r());
                if (g) {
                    continue;
                }
                break;
            }
            final double[] array5 = array4;
            if (array5 != null) {
                int n10 = 0;
                while (true) {
                    Label_0343: {
                        if (!g) {
                            break Label_0343;
                        }
                        array[n5++] = array5[n10];
                        ++n10;
                    }
                    if (n10 < array5.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        return array;
    }
    
    Rectangle j() {
        this.a();
        final Insets k = this.k;
        final int q = this.q();
        final int n = super.a.bf ? super.a.p : super.a.o;
        int left = k.left;
        if (super.a.bc && super.a.bf) {
            left -= n * q;
        }
        final double l = this.l();
        return new Rectangle(left, (super.a.bf ? (super.a.getSize().height - k.bottom) : this.a(p.b, this.i(), k.top, super.a.getSize().height - k.bottom, l)) + (super.a.bc ? (n * q) : 0), super.a.getSize().width - k.right - k.left - (super.a.bf ? 0 : (super.a.bc ? (n * q) : 0)), 1);
    }
    
    Rectangle k() {
        this.a();
        final Insets k = this.k;
        final int q = this.q();
        final int n = super.a.bf ? super.a.p : super.a.o;
        final double l = this.l();
        int n2 = super.a.bf ? this.a(p.c, this.i(), k.left, super.a.getSize().width - k.right, l) : k.left;
        if (super.a.bc && super.a.bf) {
            n2 -= n * q;
        }
        return new Rectangle(n2, super.a.bc ? (k.top + n * q) : k.top, 1, super.a.getSize().height - k.top - k.bottom);
    }
    
    private double l() {
        final boolean g = GraphSerie.G;
        final double b = this.b(this.i());
        double ch = 0.0;
        if (super.a.cj) {
            if (super.a.ch >= 0.0) {
                Label_0078: {
                    if (b < super.a.ch) {
                        Label_0065: {
                            if (b < 0.0) {
                                ch = 0.0;
                                if (!g) {
                                    break Label_0065;
                                }
                            }
                            ch = b;
                        }
                        if (!g) {
                            break Label_0078;
                        }
                    }
                    ch = super.a.ch;
                }
                if (!g) {
                    return ch;
                }
            }
            ch = 0.0;
        }
        return ch;
    }
    
    private void m() {
        Label_0036: {
            if (!super.a.bf && !super.a.bi) {
                this.a(true);
                if (!GraphSerie.G) {
                    break Label_0036;
                }
            }
            this.a(false);
        }
        this.n[0] = this.a((Object)this.i());
        this.n[1] = this.a(super.a.g);
        this.n[2] = this.a(p.d);
        this.n[3] = this.a(p.e);
        this.n[4] = this.h() / 2;
    }
    
    void a() {
        final boolean g = GraphSerie.G;
        Label_0723: {
            if (super.a.cg || super.a.D || super.a.cd) {
                super.a.cd = false;
                if (super.a.cg) {
                    this.m();
                }
                final int n = this.n[0];
                final int n2 = this.n[1];
                final int n3 = this.n[2];
                final int n4 = this.n[3];
                final int n5 = this.n[4];
                super.a.getLegend().g();
                this.g = 0;
                this.j = 0;
                this.h = 0;
                this.i = 0;
                final boolean v = this.v();
                final int n6 = v ? 0 : super.a.bG.j();
                final int k = super.a.k;
                final int n7 = super.a.n;
                final int n8 = v ? 0 : super.a.bG.h();
                final int l = super.a.l;
                final int n9 = v ? 0 : super.a.bG.i();
                int q = 0;
                int n10 = 0;
                Label_0275: {
                    if (super.a.bc) {
                        q = this.q();
                        if (!super.a.bf) {
                            n10 = super.a.o;
                            if (!g) {
                                break Label_0275;
                            }
                        }
                        n10 = super.a.p;
                    }
                }
                final FontMetrics fontMetrics = super.a.getToolkit().getFontMetrics(super.a.bE.r);
                final int g2 = this.o() / 2;
                if (super.a.bf) {
                    this.g = g2;
                }
                this.g += super.a.m + this.d();
                this.i = (((super.a.bf ? super.a.v : super.a.s) != null) ? (10 + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) : 0);
                Label_0493: {
                    if (!super.a.bf) {
                        this.i += k + n6 + n + 5;
                        if (!g) {
                            break Label_0493;
                        }
                    }
                    this.i += super.a.k + super.a.getLegend().j() + ((n2 > n3) ? n2 : n3);
                    if (super.a.bc) {
                        this.i += n10 * q;
                    }
                }
                final FontMetrics fontMetrics2 = super.a.getToolkit().getFontMetrics(super.a.getXAxis().r);
                this.h = (((super.a.bf ? super.a.s : super.a.v) != null) ? (fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent() + 10) : 0);
                int g3 = this.g();
                if (super.a.bi && !super.a.bf) {
                    g3 = n2;
                }
                this.h += n7 + n8 + (g3 + 5);
                this.h += (super.a.bc ? (n10 * q) : 0);
                final int max = Math.max(Math.max(n5, g2), n4);
                if (!super.a.bf) {
                    this.j = l + n9 + max;
                    if (!g) {
                        break Label_0723;
                    }
                }
                this.j = l + n9 + ((n5 > n4) ? n5 : (n4 + 15));
            }
        }
        this.k.top = this.g;
        this.k.left = this.i;
        this.k.bottom = this.h;
        this.k.right = this.j;
    }
    
    Object[] n() {
        if (!super.a.cg && !super.a.D && !super.a.C) {
            return this.m;
        }
        final boolean bf = super.a.bf;
        final Vector vector = new Vector<Double>();
        final Vector vector2 = new Vector<Integer>();
        final Rectangle j = this.j();
        final Rectangle k = this.k();
        final double[] i = this.i();
        final double a = this.a(i);
        double n = this.b(i);
        int a2;
        int n2;
        if (!bf) {
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(super.a.getYAxis().n);
            a2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
            if (a >= Math.abs(n)) {
                n2 = j.y - k.y;
            }
            else {
                n2 = k.y + k.height - j.y;
            }
        }
        else {
            Toolkit.getDefaultToolkit().getFontMetrics(super.a.getXAxis().n);
            a2 = this.a((Object)this.i());
            if (a >= Math.abs(n)) {
                n2 = j.width - (k.x - j.x);
            }
            else {
                n2 = k.x - j.x;
            }
        }
        double n3;
        if (!super.a.cj) {
            n3 = ((a < 0.0) ? 0.0 : a);
        }
        else {
            n3 = ((a < super.a.ci) ? super.a.ci : a);
        }
        double a3 = 0.0;
        if (!super.a.cj) {
            a3 = this.a(Math.max(Math.abs(n), n3));
        }
        double l;
        double ck;
        if (!super.a.cj) {
            ck = (l = this.a(a2, a3, n2));
        }
        else {
            ck = super.a.ck;
            l = this.l();
        }
        if (!bf && ck > 0.0) {
            while (l <= n3) {
                final int a4 = this.a(p.b, i, k.y, k.y + k.height, l);
                vector.addElement(new Double(l));
                vector2.addElement(new Integer(a4));
                l += ck;
            }
            if (n < 0.0 || (super.a.cj && super.a.ch < n)) {
                double n4 = -ck;
                if (super.a.cj) {
                    n = Math.min(n, super.a.ch);
                }
                while (n4 >= n) {
                    final int a5 = this.a(p.b, i, k.y, k.y + k.height, n4);
                    vector.addElement(new Double(n4));
                    vector2.addElement(new Integer(a5));
                    n4 -= ck;
                }
            }
        }
        else if (bf && ck > 0.0) {
            while (l <= n3) {
                final int a6 = this.a(p.c, i, j.x, j.x + j.width, l);
                vector.addElement(new Double(l));
                vector2.addElement(new Integer(a6));
                l += ck;
            }
            if (n < 0.0 || (super.a.cj && super.a.ch < n)) {
                double n5 = -ck;
                if (super.a.cj) {
                    n = Math.min(n, super.a.ch);
                }
                while (n5 >= n) {
                    final int a7 = this.a(p.c, i, j.x, j.x + j.width, n5);
                    vector.addElement(new Double(n5));
                    vector2.addElement(new Integer(a7));
                    n5 -= ck;
                }
            }
        }
        final double[] array = new double[vector.size()];
        final int[] array2 = new int[vector2.size()];
        for (int n6 = 0; n6 < vector.size(); ++n6) {
            array[n6] = vector.elementAt(n6);
        }
        for (int n7 = 0; n7 < vector2.size(); ++n7) {
            array2[n7] = vector2.elementAt(n7);
        }
        this.m[0] = array2;
        this.m[1] = array;
        return this.m;
    }
    
    long a(final int n, final double n2, final int n3) {
        final boolean g = GraphSerie.G;
        final double n4 = 10.0;
        long n5 = 0L;
        if (n2 >= 10.0) {
            Label_0098: {
                if (n3 / n4 >= n) {
                    n5 = (long)(n2 / n4);
                    if (!g) {
                        break Label_0098;
                    }
                }
                final double n6 = 5.0;
                if (n3 / n6 >= n) {
                    n5 = (long)(n2 / n6);
                    if (!g) {
                        break Label_0098;
                    }
                }
                final double n7 = 2.0;
                if (n3 / n7 >= n) {
                    n5 = (long)(n2 / n7);
                }
            }
            if (!g) {
                return n5;
            }
        }
        n5 = 1L;
        double n8 = n2;
        while (true) {
            Label_0155: {
                if (!g) {
                    break Label_0155;
                }
                if (n3 / n8 >= n && !g) {
                    return n5;
                }
                --n8;
                if (n8 <= 0.0 && !g) {
                    return n5;
                }
                ++n5;
            }
            if (!g) {
                continue;
            }
            break;
        }
        return n5;
    }
    
    double a(final double n) {
        final boolean g = GraphSerie.G;
        double n2 = 0.0;
        if (n < 10.0) {
            n2 = 1.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 10.0 && n < 100.0) {
            n2 = 10.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 100.0 && n < 1000.0) {
            n2 = 100.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1000.0 && n < 10000.0) {
            n2 = 1000.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 10000.0 && n < 100000.0) {
            n2 = 10000.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 100000.0 && n < 1000000.0) {
            n2 = 100000.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1000000.0 && n < 1.0E7) {
            n2 = 1000000.0;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1.0E7 && n < 1.0E8) {
            n2 = 1.0E7;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1.0E8 && n < 1.0E9) {
            n2 = 1.0E8;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1.0E9 && n < 1.0E10) {
            n2 = 1.0E9;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 1.0E10 && n < 9.9999997952E10) {
            n2 = 1.0E10;
            if (!g) {
                return Math.ceil(n / n2) * n2;
            }
        }
        if (n >= 9.9999997952E10 && n < 9.99999995904E11) {
            n2 = 9.9999997952E10;
        }
        return Math.ceil(n / n2) * n2;
    }
    
    String[] a(final Axis axis, final double[] array) {
        final boolean g = GraphSerie.G;
        final String bw = super.a.bw;
        final String[] array2 = new String[array.length];
        DecimalFormat decimalFormat = null;
        if (bw != null) {
            decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern(bw);
        }
        int n = 0;
        while (true) {
            Label_0088: {
                if (!g) {
                    break Label_0088;
                }
                final String[] array3;
                array3[n] = ((bw != null) ? decimalFormat.format(new Double(array[n])) : Double.toString(array[n]));
                ++n;
            }
            if (n < array.length) {
                continue;
            }
            final String[] array3 = array2;
            if (!g) {
                return array3;
            }
            continue;
        }
    }
    
    private int o() {
        int n = 0;
        int n2 = 1;
        final Vector i = super.a.i;
        int n3 = 0;
        while (true) {
            Label_0088: {
                if (!GraphSerie.G) {
                    break Label_0088;
                }
                final GraphSerie graphSerie = i.elementAt(n3);
                if ((graphSerie instanceof BarSerie && !(graphSerie instanceof StackBarSerie)) || (graphSerie instanceof StackBarSerie && n2 != 0)) {
                    n += i.elementAt(n3).g();
                    if (graphSerie instanceof StackBarSerie) {
                        n2 = 0;
                    }
                }
                ++n3;
            }
            if (n3 >= i.size()) {
                return n;
            }
            continue;
        }
    }
    
    Vector p() {
        final boolean g = GraphSerie.G;
        final Vector vector = new Vector<GraphSerie>();
        final Vector i = super.a.i;
        int n = 0;
        while (true) {
            Label_0055: {
                if (!g) {
                    break Label_0055;
                }
                final GraphSerie graphSerie = i.elementAt(n);
                if (graphSerie instanceof StackBarSerie) {
                    vector.addElement(graphSerie);
                }
                ++n;
            }
            if (n < i.size()) {
                continue;
            }
            break;
        }
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0105: {
                    if (!g) {
                        break Label_0105;
                    }
                    final Object element = i.elementAt(n2);
                    final GraphSerie graphSerie2 = (GraphSerie)element;
                    if (graphSerie2 instanceof BarSerie && !(graphSerie2 instanceof StackBarSerie)) {
                        vector.addElement(graphSerie2);
                    }
                    ++n2;
                }
                if (n2 < i.size()) {
                    continue;
                }
                break;
            }
            Object element;
            final GraphSerie graphSerie3 = (GraphSerie)(element = vector);
            if (!g) {
                return (Vector)graphSerie3;
            }
            continue;
        }
    }
    
    private int[] a(final BarSerie barSerie) {
        final boolean g = GraphSerie.G;
        int[] array = null;
        final int o = this.o();
        this.s();
        if (o > 0) {
            final int[] e = this.e();
            final Vector p = this.p();
            array = new int[e.length];
            if (this.f == null) {
                this.f = new int[e.length];
            }
            int n = 0;
            while (true) {
                Label_0292: {
                    if (!g) {
                        break Label_0292;
                    }
                    double n2 = 0.0;
                    Label_0121: {
                        if (!super.a.bc || (super.a.bc && !super.a.bh)) {
                            n2 = o / 2.0;
                            if (!g) {
                                break Label_0121;
                            }
                        }
                        n2 = barSerie.g() / 2.0;
                    }
                    int n3 = 0;
                Label_0289:
                    while (true) {
                        Label_0279: {
                            if (!g) {
                                break Label_0279;
                            }
                            final BarSerie barSerie2 = p.elementAt(n3);
                            if (barSerie2 == barSerie) {
                                final double n4 = e[n] - n2;
                                Label_0203: {
                                    if (barSerie2 instanceof StackBarSerie && this.b((StackBarSerie)barSerie2)) {
                                        this.f[n] = (int)n4;
                                        if (!g) {
                                            break Label_0203;
                                        }
                                    }
                                    array[n] = (int)n4;
                                }
                                if (!g) {
                                    break Label_0289;
                                }
                            }
                            if (n3 + 1 < p.size() && !(p.elementAt(n3 + 1) instanceof StackBarSerie) && (!super.a.bc || (super.a.bc && !super.a.bh))) {
                                n2 -= barSerie2.g();
                            }
                            ++n3;
                        }
                        if (n3 < p.size()) {
                            continue;
                        }
                        break;
                    }
                    ++n;
                }
                if (n < e.length) {
                    continue;
                }
                break;
            }
        }
        return (barSerie instanceof StackBarSerie) ? this.f : array;
    }
    
    Rectangle[] b(final BarSerie barSerie) {
        final boolean g = GraphSerie.G;
        this.a();
        final Insets k = this.k;
        final double[] a = barSerie.a;
        final double[] i = this.i();
        final int[] a2 = this.a(barSerie);
        final Rectangle j = this.j();
        final Rectangle l = this.k();
        final Rectangle[] array = new Rectangle[a.length];
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0354: {
                    if (!g) {
                        break Label_0354;
                    }
                    int n3 = 0;
                    int g2 = 0;
                    int g3 = 0;
                    Label_0327: {
                        if (!super.a.bf) {
                            n3 = a2[n2];
                            Label_0188: {
                                if (a[n2] >= 0.0) {
                                    n = this.a(p.b, i, l.y, l.y + l.height, a[n2]);
                                    g2 = j.y - n;
                                    if (!g) {
                                        break Label_0188;
                                    }
                                }
                                n = j.y;
                                g2 = this.a(p.b, i, l.y, l.y + l.height, a[n2]) - n;
                            }
                            g3 = barSerie.g();
                            if (!g) {
                                break Label_0327;
                            }
                        }
                        n = a2[n2];
                        Label_0321: {
                            if (a[a.length - 1 - n2] >= 0.0) {
                                n3 = l.x;
                                g3 = this.a(p.c, i, j.x, j.x + j.width, a[a.length - 1 - n2]) - n3;
                                if (!g) {
                                    break Label_0321;
                                }
                            }
                            n3 = this.a(p.c, i, j.x, j.x + j.width, a[a.length - 1 - n2]);
                            g3 = l.x - n3;
                        }
                        g2 = barSerie.g();
                    }
                    array[n2] = new Rectangle(n3, n, g3, g2);
                    ++n2;
                }
                if (n2 < a.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return array;
            }
            continue;
        }
    }
    
    int b() {
        final boolean g = GraphSerie.G;
        final Vector i = super.a.i;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(super.a.getLegend().a());
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0141: {
                if (!g) {
                    break Label_0141;
                }
                final GraphSerie graphSerie = i.elementAt(n2);
                final boolean b = graphSerie instanceof OHLCSerie && (((OHLCSerie)graphSerie).J == 4 || ((OHLCSerie)graphSerie).J == 5);
                if ((!(graphSerie instanceof ImageSerie) && !b) || g) {
                    final int stringWidth = fontMetrics.stringWidth(graphSerie.x);
                    n = ((stringWidth > n) ? stringWidth : n);
                }
                ++n2;
            }
            if (n2 >= i.size()) {
                return n;
            }
            continue;
        }
    }
    
    void b(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        double[] array = null;
        final Rectangle j = this.j();
        final Color c = super.a.bF.c;
        graphics.setColor(c);
        final Font n = super.a.getXAxis().n;
        graphics.setFont(n);
        final FontMetrics fontMetrics = graphics.getFontMetrics(n);
        String[] u = null;
        int[] e;
        if (!super.a.bf) {
            u = this.u();
            e = this.e();
        }
        else {
            final Object[] n2 = this.n();
            e = (int[])n2[0];
            array = (double[])n2[1];
        }
        String[] array2;
        if (!super.a.bf) {
            array2 = super.a.g;
        }
        else {
            array2 = this.a(super.a.bF, array);
        }
        final int a = this.a(e, array2, fontMetrics, p.b);
        if ((!super.a.bf && super.a.bF.m) || (super.a.bf && super.a.bF.l)) {
            if (super.a.bc) {
                graphics.setColor(Color.black);
            }
            int n3 = 0;
            while (true) {
                while (true) {
                    Label_0327: {
                        if (!g) {
                            break Label_0327;
                        }
                        Label_0320: {
                            if (!super.a.bf && u[n3] == null && !g) {
                                break Label_0320;
                            }
                            final int n4 = e[n3];
                            if ((super.a.bf && super.a.A) || (!super.a.bf && super.a.z)) {
                                graphics.fillRect(n4 - 1, j.y - 1, 3, 3);
                            }
                        }
                        n3 += a;
                    }
                    if (n3 < e.length) {
                        continue;
                    }
                    break;
                }
                if (g) {
                    continue;
                }
                break;
            }
            graphics.setColor(c);
        }
        if (super.a.bi && (this.l == null || super.a.cg)) {
            this.l = new Image[e.length];
        }
        int n5 = 0;
        while (true) {
            while (true) {
                Label_0770: {
                    if (!g) {
                        break Label_0770;
                    }
                    Label_0763: {
                        if (!super.a.bf && u[n5] == null && !g) {
                            break Label_0763;
                        }
                        final int stringWidth = fontMetrics.stringWidth(array2[n5]);
                        final int n6 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                        int n7 = 0;
                        Label_0495: {
                            if (super.a.bi && !super.a.bf) {
                                n7 = j.y + 5;
                                if (!g) {
                                    break Label_0495;
                                }
                            }
                            n7 = j.y + 5 + fontMetrics.getMaxAscent() - fontMetrics.getLeading();
                        }
                        final int n8 = e[n5] - stringWidth / 2;
                        if ((!super.a.bf && super.a.bF.m) || (super.a.bf && super.a.bF.l)) {
                            if (super.a.bi && !super.a.bf) {
                                if (this.l[n5] == null) {
                                    final Image a2 = this.a(array2[n5], n, fontMetrics, c);
                                    final a a3 = new a(1.5707963267948966);
                                    final Image image = super.a.createImage(new FilteredImageSource(a2.getSource(), new b(c)));
                                    FilteredImageSource filteredImageSource;
                                    if (super.a.bj) {
                                        filteredImageSource = new FilteredImageSource(a2.getSource(), a3);
                                    }
                                    else {
                                        filteredImageSource = new FilteredImageSource(image.getSource(), a3);
                                    }
                                    this.l[n5] = super.a.createImage(filteredImageSource);
                                }
                                graphics.drawImage(this.l[n5], e[n5] - this.l[n5].getWidth(null) / 2, n7, null);
                                if (!g) {
                                    break Label_0763;
                                }
                            }
                            graphics.drawString(array2[n5], n8, n7);
                        }
                    }
                    n5 += a;
                }
                if (n5 < e.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    Image a(final String s, final Font font, final FontMetrics fontMetrics, final Color color) {
        final boolean g = GraphSerie.G;
        final int stringWidth = fontMetrics.stringWidth(s);
        final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final Image image = super.a.createImage(stringWidth, n);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        Label_0108: {
            if (super.a.bj) {
                graphics.setColor(super.a.getBackground());
                if (!g) {
                    break Label_0108;
                }
            }
            if (color.equals(Color.white)) {
                graphics.setColor(Color.black);
                if (!g) {
                    break Label_0108;
                }
            }
            graphics.setColor(Color.white);
        }
        graphics.fillRect(0, 0, stringWidth, n);
        graphics.setColor(color);
        graphics.drawString(s, 0, fontMetrics.getMaxAscent());
        return image;
    }
    
    void c(final Graphics graphics) {
        final Rectangle k = this.k();
        String[] u = null;
        if (super.a.bf) {
            u = this.u();
        }
        final Color c = super.a.bE.c;
        graphics.setColor(c);
        final Font n = super.a.bE.n;
        graphics.setFont(n);
        final FontMetrics fontMetrics = graphics.getFontMetrics(n);
        int[] e;
        if (!super.a.bf) {
            final Object[] n2 = this.n();
            e = (int[])n2[0];
            final double[] array = (double[])n2[1];
        }
        else {
            e = this.e();
        }
        final String[] f = this.f();
        final int a = this.a(e, f, fontMetrics, p.c);
        if ((super.a.bf && super.a.bE.m) || (!super.a.bf && super.a.bE.l)) {
            if (super.a.bc) {
                graphics.setColor(Color.black);
            }
            for (int i = 0; i < e.length; i += a) {
                if (!super.a.bf || u[i] != null) {
                    if ((!super.a.bf && super.a.A) || (super.a.bf && super.a.z)) {
                        graphics.fillRect(k.x - 1, e[e.length - 1 - i] - 1, 3, 3);
                    }
                }
            }
            graphics.setColor(c);
        }
        for (int j = 0; j < e.length; j += a) {
            if (!super.a.bf || u[j] != null) {
                final int n3 = (fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) / 2 - fontMetrics.getMaxDescent();
                int n4;
                if (!super.a.bf) {
                    n4 = e[j];
                }
                else {
                    n4 = e[e.length - 1 - j];
                }
                if ((super.a.bf && super.a.bE.m) || (!super.a.bf && super.a.bE.l)) {
                    graphics.drawString(f[j], k.x - 5 - fontMetrics.stringWidth(f[j]), n4 + n3);
                }
            }
        }
    }
    
    private int a(final int[] array, final String[] array2, final FontMetrics fontMetrics, final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 1;
        if (super.a.bl) {
            if (!super.a.bf && n == p.b) {
                int stringWidth = 0;
                Label_0074: {
                    if (!super.a.bi) {
                        stringWidth = fontMetrics.stringWidth(array2[0]);
                        if (!g) {
                            break Label_0074;
                        }
                    }
                    stringWidth = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                }
                final int n3 = array[0] + stringWidth / 2 + 2;
                int n4 = 1;
            Label_0169:
                while (true) {
                    Label_0162: {
                        if (!g) {
                            break Label_0162;
                        }
                        int stringWidth2 = 0;
                        Label_0133: {
                            if (!super.a.bi) {
                                stringWidth2 = fontMetrics.stringWidth(array2[n4]);
                                if (!g) {
                                    break Label_0133;
                                }
                            }
                            stringWidth2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                        }
                        if (n3 < array[n4] - stringWidth2 / 2 && !g) {
                            break Label_0169;
                        }
                        ++n2;
                        ++n4;
                    }
                    if (n4 < array2.length) {
                        continue;
                    }
                    break;
                }
                if (!g) {
                    return n2;
                }
            }
            if (super.a.bf && n == p.c) {
                final int n5 = (fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) / 2 - fontMetrics.getMaxDescent();
                final int n6 = array[array.length - 1] + n5 + fontMetrics.getMaxDescent();
                int n7 = 1;
                while (true) {
                    Label_0279: {
                        if (!g) {
                            break Label_0279;
                        }
                        if (n6 < array[array.length - 1 - n7] + n5 - fontMetrics.getMaxAscent() && !g) {
                            return n2;
                        }
                        ++n2;
                        ++n7;
                    }
                    if (n7 < array2.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        return n2;
    }
    
    int a(final GraphSerie graphSerie) {
        final boolean g = GraphSerie.G;
        int n = 0;
        if (super.a.bh) {
            Vector<GraphSerie> t = (Vector<GraphSerie>)this.t();
            final Vector s = this.s();
            if (t != null && s != null) {
                int n2 = 0;
                while (true) {
                    Label_0059: {
                        if (!g) {
                            break Label_0059;
                        }
                        t.addElement(s.elementAt(n2));
                        ++n2;
                    }
                    if (n2 < s.size()) {
                        continue;
                    }
                    break;
                }
            }
            else if (s != null) {
                t = (Vector<GraphSerie>)s;
            }
            if (t != null) {
                int n3 = 0;
                while (true) {
                    Label_0133: {
                        if (!g) {
                            break Label_0133;
                        }
                        final GraphSerie graphSerie2 = t.elementAt(n3);
                        Label_0130: {
                            if (graphSerie2 != graphSerie && !(graphSerie2 instanceof StackBarSerie)) {
                                ++n;
                                if (!g) {
                                    break Label_0130;
                                }
                            }
                            if (!g) {
                                return n;
                            }
                        }
                        ++n3;
                    }
                    if (n3 < t.size()) {
                        continue;
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    int q() {
        final boolean g = GraphSerie.G;
        int n = 0;
        if (super.a.bh) {
            final Vector t = this.t();
            final Vector s = this.s();
            if (t != null) {
                int n2 = 0;
                while (true) {
                    Label_0056: {
                        if (!g) {
                            break Label_0056;
                        }
                        final GraphSerie graphSerie = t.elementAt(n2);
                        ++n;
                        ++n2;
                    }
                    if (n2 < t.size()) {
                        continue;
                    }
                    break;
                }
            }
            if (s != null) {
                ++n;
            }
            if (!g) {
                return n;
            }
        }
        n = 1;
        return n;
    }
    
    Rectangle[] a(final StackBarSerie stackBarSerie) {
        final boolean g = GraphSerie.G;
        this.a();
        final Insets k = this.k;
        final Vector s = this.s();
        final double[] a = stackBarSerie.a;
        double n = 0.0;
        final Rectangle j = this.j();
        final Rectangle i = this.k();
        final Rectangle[] array = new Rectangle[a.length];
        final int[] a2 = this.a((BarSerie)stackBarSerie);
        int n2 = 0;
    Label_0094_Outer:
        while (true) {
            Label_0447: {
                if (!g) {
                    break Label_0447;
                }
                int n3 = s.size() - 1;
                Graph graph;
                while (true) {
                Label_0215:
                    while (true) {
                        Label_0210: {
                            if (!g) {
                                break Label_0210;
                            }
                            final StackBarSerie stackBarSerie2 = s.elementAt(n3);
                            final StackBarSerie stackBarSerie3 = stackBarSerie2;
                            Label_0207: {
                                if (!super.a.bf) {
                                    if (n2 < stackBarSerie3.E && !g) {
                                        break Label_0207;
                                    }
                                    n += stackBarSerie3.a[n2];
                                    if (stackBarSerie3 == stackBarSerie && !g) {
                                        break Label_0215;
                                    }
                                    if (!g) {
                                        break Label_0207;
                                    }
                                }
                                if (a.length - 1 - n2 >= stackBarSerie3.E || g) {
                                    if (stackBarSerie3 == stackBarSerie && !g) {
                                        break Label_0215;
                                    }
                                    n += stackBarSerie3.a[stackBarSerie3.a.length - 1 - n2];
                                }
                            }
                            --n3;
                        }
                        if (n3 >= 0) {
                            continue Label_0094_Outer;
                        }
                        break;
                    }
                    StackBarSerie stackBarSerie2;
                    graph = (Graph)(stackBarSerie2 = (StackBarSerie)super.a);
                    if (g) {
                        continue;
                    }
                    break;
                }
                int a3 = 0;
                int g2 = 0;
                int a4 = 0;
                int g3 = 0;
                Label_0417: {
                    if (!graph.bf) {
                        a3 = this.a(p.b, this.i(), i.y, i.y + i.height, n);
                        g2 = j.y - this.a(p.b, this.i(), i.y, i.y + i.height, a[n2]);
                        a4 = a2[n2];
                        g3 = stackBarSerie.g();
                        if (!g) {
                            break Label_0417;
                        }
                    }
                    a4 = this.a(p.c, this.i(), j.x, j.x + j.width, n);
                    a3 = a2[n2];
                    g3 = this.a(p.c, this.i(), j.x, j.x + j.width, a[a.length - 1 - n2]) - i.x;
                    g2 = stackBarSerie.g();
                }
                array[n2] = new Rectangle(a4, a3, g3, g2);
                n = 0.0;
                ++n2;
            }
            if (n2 >= a.length) {
                return array;
            }
            continue;
        }
    }
    
    double[] r() {
        final boolean g = GraphSerie.G;
        final Vector s = this.s();
        double[] array = null;
        if (s != null) {
            array = new double[s.elementAt(0).a.length];
            int n = 0;
        Label_0071_Outer:
            while (true) {
                Label_0093: {
                    if (!g) {
                        break Label_0093;
                    }
                    final StackBarSerie stackBarSerie = s.elementAt(n);
                    int n2 = 0;
                    while (true) {
                        while (true) {
                            Label_0074: {
                                if (!g) {
                                    break Label_0074;
                                }
                                final double[] array2 = array;
                                final int n3 = n2;
                                array2[n3] += stackBarSerie.a[n2];
                                ++n2;
                            }
                            if (n2 < stackBarSerie.a.length) {
                                continue Label_0071_Outer;
                            }
                            break;
                        }
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    ++n;
                }
                if (n < s.size()) {
                    continue;
                }
                break;
            }
        }
        return array;
    }
    
    Vector s() {
        Object o = null;
        final Vector i = super.a.i;
        int n = 0;
        Vector<GraphSerie> vector;
        while (true) {
            Object element;
            if (n >= i.size()) {
                vector = (Vector<GraphSerie>)(element = o);
                if (!GraphSerie.G) {
                    break;
                }
            }
            else {
                element = i.elementAt(n);
            }
            final GraphSerie graphSerie = (GraphSerie)element;
            if (graphSerie instanceof StackBarSerie) {
                if (o == null) {
                    o = new Vector<GraphSerie>();
                }
                ((Vector<GraphSerie>)o).addElement(graphSerie);
            }
            ++n;
        }
        return vector;
    }
    
    boolean b(final StackBarSerie stackBarSerie) {
        final Vector s = this.s();
        return s.indexOf(stackBarSerie) == s.size() - 1;
    }
    
    Vector t() {
        Vector<Object> vector = null;
        final Vector i = super.a.i;
        while (true) {
            Label_0120: {
                if (!super.a.bf) {
                    for (int j = i.size() - 1; j >= 0; --j) {
                        if (!(i.elementAt(j) instanceof StackBarSerie)) {
                            if (vector == null) {
                                vector = new Vector<Object>();
                            }
                            vector.addElement(i.elementAt(j));
                        }
                    }
                    break Label_0120;
                }
                int j = 0;
                Label_0112: {
                    break Label_0112;
                    final Object element;
                    if (!(element instanceof StackBarSerie)) {
                        if (vector == null) {
                            vector = new Vector<Object>();
                        }
                        vector.addElement(i.elementAt(j));
                    }
                    ++j;
                }
                if (j < i.size()) {
                    final Object element = i.elementAt(j);
                    continue;
                }
            }
            Object element;
            final Vector<Object> vector2 = (Vector<Object>)(element = vector);
            if (!GraphSerie.G) {
                return vector2;
            }
            continue;
        }
    }
    
    String[] u() {
        final boolean g = GraphSerie.G;
        final int n = (super.a.bC == 0 || super.a.bl) ? 0 : (super.a.g.length / super.a.bC);
        final int n2 = (n == 0) ? 1 : n;
        final String[] array = new String[super.a.g.length];
        int n3 = 0;
        while (true) {
            Label_0091: {
                if (!g) {
                    break Label_0091;
                }
                final String[] array2;
                array2[n3] = super.a.g[n3];
                n3 += n2;
            }
            if (n3 < array.length) {
                continue;
            }
            final String[] array2 = array;
            if (!g) {
                return array2;
            }
            continue;
        }
    }
    
    void a(final GraphSerie graphSerie, final int n, final int n2) {
        final boolean g = GraphSerie.G;
        final boolean bf = super.a.bf;
        final int n3 = bf ? this.j().width : this.k().height;
        final int y = graphSerie.y;
        int n4 = 0;
        Label_0090: {
            if (!bf) {
                n4 = n3 + this.k().y - y;
                if (!g) {
                    break Label_0090;
                }
            }
            n4 = n3 + this.j().x + y;
        }
        double a = this.a(this.i());
        double b = this.b(this.i());
        Label_0194: {
            if (!super.a.cj) {
                b = ((b < 0.0) ? b : 0.0);
                if (!g) {
                    break Label_0194;
                }
            }
            a = ((a > super.a.ci) ? a : super.a.ci);
            b = ((b < super.a.ch) ? b : super.a.ch);
        }
        final double n5 = a - b;
        double n6 = 0.0;
        Label_0253: {
            if (!bf) {
                n6 = b + (n4 - n2) * n5 / n3;
                if (!g) {
                    break Label_0253;
                }
            }
            n6 = b + (n - this.j().x - y) * n5 / n3;
        }
        final double[] values = graphSerie.getValues();
        if (n6 <= super.a.K && n6 >= super.a.J) {
            values[graphSerie.getElementIndex()] = n6;
        }
    }
    
    boolean v() {
        final boolean g = GraphSerie.G;
        boolean b = true;
        int n = 0;
        while (true) {
            Label_0106: {
                if (!g) {
                    break Label_0106;
                }
                final GraphSerie graphSerie = super.a.i.elementAt(n);
                if (!(graphSerie instanceof ImageSerie) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 4) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 5) && (!(graphSerie instanceof BarSerie) || graphSerie instanceof StackBarSerie || ((BarSerie)graphSerie).U == null)) {
                    b = false;
                    if (!g) {
                        return b;
                    }
                }
                ++n;
            }
            if (n < super.a.i.size()) {
                continue;
            }
            break;
        }
        return b;
    }
    
    private void a(final boolean b) {
        final boolean g = GraphSerie.G;
        final String[] g2 = super.a.g;
        while (true) {
            Label_0194: {
                if (b) {
                    final int b2 = this.b(g2);
                    if (super.a.bl) {
                        int n = 0;
                        while (true) {
                            Label_0157: {
                                if (!g) {
                                    break Label_0157;
                                }
                                final int n2 = b2 - g2[n].length();
                                if (n2 > 0) {
                                    int n3 = 1;
                                    while (true) {
                                        Label_0147: {
                                            if (!g) {
                                                break Label_0147;
                                            }
                                            String s = null;
                                            String s2 = null;
                                            Label_0113: {
                                                if (n3 == 1 || n3 % 2 != 0) {
                                                    s = " ";
                                                    s2 = "";
                                                    if (!g) {
                                                        break Label_0113;
                                                    }
                                                }
                                                s = "";
                                                s2 = " ";
                                            }
                                            g2[n] = s + g2[n] + s2;
                                            ++n3;
                                        }
                                        if (n3 <= n2) {
                                            continue;
                                        }
                                        break;
                                    }
                                }
                                ++n;
                            }
                            if (n < g2.length) {
                                continue;
                            }
                            break;
                        }
                    }
                    if (!g) {
                        break Label_0194;
                    }
                }
                int b2 = 0;
                while (true) {
                    Label_0188: {
                        if (!g) {
                            break Label_0188;
                        }
                        g2[b2] = g2[b2].trim();
                        ++b2;
                    }
                    if (b2 < g2.length) {
                        continue;
                    }
                    break;
                }
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final boolean g = GraphSerie.G;
        if (n5 != 1) {
            int n6 = 0;
            int n7 = 0;
            int n8 = 0;
            int n9 = 0;
            Label_0072: {
                if (!super.a.bf) {
                    n6 = n;
                    n7 = n3;
                    n8 = n2;
                    n9 = n4;
                    if (!g) {
                        break Label_0072;
                    }
                }
                n6 = n2;
                n7 = n4;
                n8 = n;
                n9 = n3;
            }
            final double n10 = ((n9 > n8) ? (n9 - n8) : (n8 - n9)) / (n7 - n6);
            int n11 = -1;
            int n12 = n6;
            while (true) {
                Label_0372: {
                    if (!g) {
                        break Label_0372;
                    }
                    final double n13 = n12 - n6;
                    final int n14 = (int)((n9 > n8) ? (n8 + n10 * n13) : (n8 - n10 * n13));
                    if (n11 != -1 && ((n11 > n14) ? (n11 - n14) : (n14 - n11)) > 1) {
                        int n15 = (n11 > n14) ? (n14 + 1) : (n11 + 1);
                        while (true) {
                            Label_0282: {
                                if (!g) {
                                    break Label_0282;
                                }
                                Label_0279: {
                                    if (!super.a.bf) {
                                        graphics.fillRect(n12 - n5 / 2, n15 - n5 / 2, n5, n5);
                                        if (!g) {
                                            break Label_0279;
                                        }
                                    }
                                    graphics.fillRect(n15 - n5 / 2, n12 - n5 / 2, n5, n5);
                                }
                                ++n15;
                            }
                            final int n16 = n15;
                            int n17;
                            if (n11 > n14) {
                                n17 = n11;
                                if (!g) {}
                            }
                            else {
                                n17 = n14;
                            }
                            if (n16 < n17) {
                                continue;
                            }
                            break;
                        }
                    }
                    n11 = n14;
                    Label_0369: {
                        if (!super.a.bf) {
                            graphics.fillRect(n12 - n5 / 2, n14 - n5 / 2, n5, n5);
                            if (!g) {
                                break Label_0369;
                            }
                        }
                        graphics.fillRect(n14 - n5 / 2, n12 - n5 / 2, n5, n5);
                    }
                    ++n12;
                }
                if (n12 <= n7) {
                    continue;
                }
                break;
            }
        }
        else {
            graphics.drawLine(n, n2, n3, n4);
        }
    }
    
    p(final Graph a) {
        this.k = new Insets(0, 0, 0, 0);
        this.m = new Object[2];
        this.n = new int[5];
        super.a = a;
    }
    
    static {
        p.b = 0;
        p.c = 1;
        p.d = 0;
        p.e = 1;
    }
}
