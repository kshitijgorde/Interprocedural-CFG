// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class f extends b
{
    private int R;
    private int S;
    private double T;
    private double U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private boolean ba;
    private Color bb;
    private int bc;
    private int bd;
    private int be;
    private double bf;
    private double bg;
    private int bh;
    private String bi;
    private String bj;
    private String bk;
    private String bl;
    private String bm;
    private String bn;
    private String bo;
    
    public f(final int n, final int n2, final int a, final int b, final Color g, final Color h, final int c) {
        this.ba = false;
        this.bb = new Color(0, 0, 192);
        this.bc = 4;
        this.bh = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = n + 10;
        this.S = n2 + 10;
        super.c = c;
        this.V = super.b - 30;
        this.W = super.a - 20;
    }
    
    public boolean a(final int l, final o j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        double[] array = new double[0];
        int n = 0;
        int n2 = 0;
        final Font font = new Font(super.f, 0, super.c);
        if (super.D != null) {
            this.V -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.W -= 15 + (super.c + 2) / 2;
        }
        if (!super.p) {
            for (int i = 0; i < super.l; ++i) {
                if (super.j.a[i] != null && !super.j.a[i].k) {
                    if (n2 == 0) {
                        this.T = super.j.a[i].a;
                        this.U = this.T;
                        n2 = 1;
                    }
                    if (super.j.a[i].a > this.T) {
                        this.T = super.j.a[i].a;
                    }
                    if (super.j.a[i].a < this.U) {
                        this.U = super.j.a[i].a;
                    }
                }
            }
            if (this.T == 0.0 && this.U == 0.0) {
                this.T = 6.0;
            }
            if (this.U >= 0.0) {
                this.bh = 1;
            }
            else if (this.T < 0.0) {
                this.bh = 3;
            }
            else {
                this.bh = 2;
            }
            switch (this.bh) {
                case 1: {
                    this.U = 0.0;
                    array = super.v.a(7, this.U, this.T, true, super.H);
                    this.T = array[6];
                    n = (int)array[7];
                    break;
                }
                case 2: {
                    this.T = Math.max(Math.abs(this.U), Math.abs(this.T));
                    array = super.v.a(4, 0.0, this.T, true, super.H);
                    this.T = array[3];
                    n = (int)array[4];
                    break;
                }
                case 3: {
                    this.T = Math.abs(this.U);
                    this.U = 0.0;
                    array = super.v.a(7, this.U, this.T, true, super.H);
                    this.T = 0.0;
                    this.U = -array[6];
                    n = (int)array[7];
                    break;
                }
            }
        }
        else {
            array = super.v.a(7, super.n, super.o, false, super.H);
            this.U = super.n;
            this.T = array[6];
            n = (int)array[7];
            if (this.U >= 0.0) {
                this.bh = 1;
            }
            else if (this.T < 0.0) {
                this.bh = 3;
            }
            else {
                this.bh = 2;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        switch (this.bh) {
            case 1: {
                try {
                    this.bo = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bk = String.valueOf(s2) + instance.format(array[2]) + s;
                    this.bl = String.valueOf(s2) + instance.format(array[3]) + s;
                    this.bm = String.valueOf(s2) + instance.format(array[4]) + s;
                    this.bn = String.valueOf(s2) + instance.format(array[5]) + s;
                    this.bi = String.valueOf(s2) + instance.format(this.T) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bo = String.valueOf(s2) + instance.format(-this.T) + s;
                    this.bl = String.valueOf(s2) + instance.format(0L) + s;
                    this.bi = String.valueOf(s2) + instance.format(this.T) + s;
                    if (super.p) {
                        this.bj = String.valueOf(s2) + instance.format(array[2]) + s;
                        this.bk = String.valueOf(s2) + instance.format(array[1]) + s;
                        this.bm = String.valueOf(s2) + instance.format(-array[1]) + s;
                        this.bn = String.valueOf(s2) + instance.format(-array[2]) + s;
                    }
                    else {
                        this.bj = String.valueOf(s2) + instance.format(-array[2]) + s;
                        this.bk = String.valueOf(s2) + instance.format(-array[1]) + s;
                        this.bm = String.valueOf(s2) + instance.format(array[1]) + s;
                        this.bn = String.valueOf(s2) + instance.format(array[2]) + s;
                    }
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bo = String.valueOf(s2) + instance.format(this.U) + s;
                    this.bj = String.valueOf(s2) + instance.format(-array[5]) + s;
                    this.bk = String.valueOf(s2) + instance.format(-array[4]) + s;
                    this.bl = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bm = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bn = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(this.T) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        if (this.bh != 3) {
            this.bd = super.v.b(this.bi, font);
        }
        else {
            this.bd = super.v.b(this.bo, font);
        }
        if (super.j.a[0] != null && super.j.a[0].b != null && super.v.b(super.j.a[0].b, font) > this.be) {
            this.be = super.v.b(super.j.a[0].b, font);
        }
        if (!super.O) {
            this.R += this.be / 2;
        }
        else {
            this.R = this.R + this.be / 2 + this.bd;
        }
        this.W = this.W - this.be / 2 - this.bd;
        this.Z = this.V / 6;
        this.V = this.Z * 6;
        this.X = this.W / (super.l - 1);
        this.Y = this.X * (super.l - 1);
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.j, super.l, this.X, super.c);
            }
            if (super.A) {
                this.V -= super.c + 5;
                if (super.D != null) {
                    this.V -= super.c + 2;
                }
                this.Z = this.V / 6;
                this.V = this.Z * 6;
            }
        }
        switch (this.bh) {
            case 1: {
                for (int k = 0; k < super.l; ++k) {
                    super.j.a[k].j = (int)(this.V - (super.j.a[k].a - this.U) / (this.T - this.U) * this.V);
                }
                break;
            }
            case 2: {
                for (int n3 = 0; n3 < super.l; ++n3) {
                    super.j.a[n3].j = (int)(this.V - (super.j.a[n3].a + this.T) / (this.T * 2.0) * this.V);
                }
                break;
            }
            case 3: {
                for (int n4 = 0; n4 < super.l; ++n4) {
                    super.j.a[n4].j = (int)(this.V - (super.j.a[n4].a - this.U) / (this.T - this.U) * this.V);
                }
                break;
            }
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.x);
        if (super.N) {
            graphics.drawLine(this.R, this.S, this.R + this.Y, this.S);
            graphics.drawLine(this.R, this.S + 1, this.R + this.Y, this.S + 1);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.V);
            graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.V);
            for (int i = 1; i < 6; ++i) {
                graphics.drawLine(this.R, this.S + this.Z * i, this.R + this.Y, this.S + this.Z * i);
                graphics.drawLine(this.R, this.S + 1 + this.Z * i, this.R + this.Y, this.S + 1 + this.Z * i);
            }
            for (int j = 1; j < super.l; ++j) {
                if (super.L == 1 || j % super.L == 0) {
                    graphics.drawLine(this.R + this.X * j, this.S, this.R + this.X * j, this.S + this.V);
                    graphics.drawLine(this.R + 1 + this.X * j, this.S, this.R + 1 + this.X * j, this.S + this.V);
                }
            }
        }
        else {
            graphics.drawLine(this.R, this.S, this.R + this.Y, this.S);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.V);
            for (int k = 1; k < 6; ++k) {
                graphics.drawLine(this.R, this.S + this.Z * k, this.R + this.Y, this.S + this.Z * k);
            }
            for (int l = 1; l < super.l; ++l) {
                if (super.L == 1 || l % super.L == 0) {
                    graphics.drawLine(this.R + this.X * l, this.S, this.R + this.X * l, this.S + this.V);
                }
            }
        }
        graphics.setColor(this.bb);
        if (super.q) {
            final Polygon polygon = new Polygon();
            polygon.addPoint(this.R, this.S + super.j.a[0].j);
            if (!super.y) {
                super.j.a[0].l = this.R - this.bc;
                super.j.a[0].m = this.S + super.j.a[0].j - this.bc;
                super.j.a[0].n = this.R + this.bc;
                super.j.a[0].o = this.S + super.j.a[0].j + this.bc;
            }
            for (int n2 = 1; n2 < super.l; ++n2) {
                polygon.addPoint(this.R + n2 * this.X, this.S + super.j.a[n2].j);
                if (!super.y) {
                    super.j.a[n2].l = this.R + n2 * this.X - this.bc;
                    super.j.a[n2].m = this.S + super.j.a[n2].j - this.bc;
                    super.j.a[n2].n = this.R + n2 * this.X + this.bc;
                    super.j.a[n2].o = this.S + super.j.a[n2].j + this.bc;
                }
            }
            if (!this.ba) {
                polygon.addPoint(this.R + this.Y, this.S + this.V);
                polygon.addPoint(this.R, this.S + this.V);
            }
            else if (this.T == Math.abs(this.U)) {
                polygon.addPoint(this.R + this.Y, this.S + this.V / 2);
                polygon.addPoint(this.R, this.S + this.V / 2);
            }
            else {
                polygon.addPoint(this.R + this.Y, this.S);
                polygon.addPoint(this.R, this.S);
            }
            graphics.fillPolygon(polygon);
        }
        else {
            int r = this.R;
            int n3 = this.S + super.j.a[0].j;
            if (!super.y && !super.j.a[0].k) {
                super.j.a[0].l = r - this.bc;
                super.j.a[0].m = n3 - this.bc;
                super.j.a[0].n = r + this.bc;
                super.j.a[0].o = n3 + this.bc;
            }
            for (int n4 = 1; n4 < super.l; ++n4) {
                final int n5 = this.R + n4 * this.X;
                final int n6 = this.S + super.j.a[n4].j;
                if (!super.j.a[n4].k) {
                    if ((super.z && n4 != 1) || !super.j.a[n4 - 1].k) {
                        super.v.a(graphics, r, n3, n5, n6, super.I, graphics.getColor());
                    }
                    r = n5;
                    n3 = n6;
                }
                else if (!super.z) {
                    r = n5;
                    n3 = n6;
                }
                if (!super.y && !super.j.a[n4].k) {
                    super.j.a[n4].l = r - this.bc;
                    super.j.a[n4].m = n3 - this.bc;
                    super.j.a[n4].n = r + this.bc;
                    super.j.a[n4].o = n3 + this.bc;
                }
            }
        }
        graphics.setColor(super.g);
        graphics.fillRect(0, 0, super.d + 1, this.S);
        graphics.fillRect(0, this.S + this.V, super.d + 1, super.e - (this.S + this.V));
        graphics.setColor(super.h);
        if (super.N) {
            graphics.drawLine(this.R, this.S + this.V, this.R + this.Y, this.S + this.V);
            graphics.drawLine(this.R, this.S + 1 + this.V, this.R + this.Y, this.S + 1 + this.V);
            if (!super.O) {
                graphics.drawLine(this.R + this.Y, this.S, this.R + this.Y, this.S + this.V);
                graphics.drawLine(this.R + 1 + this.Y, this.S, this.R + 1 + this.Y, this.S + this.V + 1);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.V);
                graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.V + 1);
            }
        }
        else {
            graphics.drawLine(this.R, this.S + this.V, this.R + this.Y, this.S + this.V);
            if (!super.O) {
                graphics.drawLine(this.R + this.Y, this.S, this.R + this.Y, this.S + this.V);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.V);
            }
        }
        switch (this.bh) {
            case 2: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S + this.Z * 3, this.R + this.Y, this.S + this.Z * 3);
                    graphics.drawLine(this.R, this.S + 1 + this.Z * 3, this.R + this.Y, this.S + 1 + this.Z * 3);
                    for (int n7 = 0; n7 < super.l; ++n7) {
                        graphics.drawLine(this.R + this.X * n7, this.S + this.Z * 3 - 2, this.R + this.X * n7, this.S + this.Z * 3 + 3);
                        graphics.drawLine(this.R + 1 + this.X * n7, this.S + this.Z * 3 - 2, this.R + 1 + this.X * n7, this.S + this.Z * 3 + 3);
                    }
                    break;
                }
                graphics.drawLine(this.R, this.S + this.Z * 3, this.R + this.Y, this.S + this.Z * 3);
                for (int n8 = 0; n8 < super.l; ++n8) {
                    graphics.drawLine(this.R + this.X * n8, this.S + this.Z * 3 - 2, this.R + this.X * n8, this.S + this.Z * 3 + 2);
                }
                break;
            }
            case 3: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S, this.R + this.Y, this.S);
                    graphics.drawLine(this.R, this.S + 1, this.R + this.Y, this.S + 1);
                    for (int n9 = 0; n9 < super.l; ++n9) {
                        graphics.drawLine(this.R + this.X * n9, this.S - 2, this.R + this.X * n9, this.S + 3);
                        graphics.drawLine(this.R + 1 + this.X * n9, this.S - 2, this.R + 1 + this.X * n9, this.S + 3);
                    }
                    break;
                }
                graphics.drawLine(this.R, this.S, this.R + this.Y, this.S);
                for (int n10 = 0; n10 < super.l; ++n10) {
                    graphics.drawLine(this.R + this.X * n10, this.S - 2, this.R + this.X * n10, this.S + 2);
                }
                break;
            }
        }
        if (super.N) {
            for (int n11 = 0; n11 < 7; ++n11) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.Y - 2, this.S + this.Z * n11, this.R + this.Y + 3, this.S + this.Z * n11);
                    graphics.drawLine(this.R + this.Y - 2, this.S + 1 + this.Z * n11, this.R + this.Y + 3, this.S + 1 + this.Z * n11);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.Z * n11, this.R + 3, this.S + this.Z * n11);
                    graphics.drawLine(this.R - 2, this.S + 1 + this.Z * n11, this.R + 3, this.S + 1 + this.Z * n11);
                }
            }
            for (int n12 = 0; n12 < super.l; ++n12) {
                if (super.J == 1 || n12 % super.J == 0) {
                    graphics.drawLine(this.R + this.X * n12, this.S + this.V - 2, this.R + this.X * n12, this.S + this.V + 3);
                    graphics.drawLine(this.R + 1 + this.X * n12, this.S + this.V - 2, this.R + 1 + this.X * n12, this.S + this.V + 3);
                }
            }
        }
        else {
            for (int n13 = 0; n13 < 7; ++n13) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.Y - 2, this.S + this.Z * n13, this.R + this.Y + 2, this.S + this.Z * n13);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.Z * n13, this.R + 2, this.S + this.Z * n13);
                }
            }
            for (int n14 = 0; n14 < super.l; ++n14) {
                if (super.J == 1 || n14 % super.J == 0) {
                    graphics.drawLine(this.R + this.X * n14, this.S + this.V - 2, this.R + this.X * n14, this.S + this.V + 2);
                }
            }
        }
        if (!super.O) {
            graphics.drawString(this.bo, this.R + this.Y + 5, this.S + this.V + 4);
            graphics.drawString(this.bj, this.R + this.Y + 5, this.S + this.Z * 5 + 4);
            graphics.drawString(this.bk, this.R + this.Y + 5, this.S + this.Z * 4 + 4);
            graphics.drawString(this.bl, this.R + this.Y + 5, this.S + this.Z * 3 + 4);
            graphics.drawString(this.bm, this.R + this.Y + 5, this.S + this.Z * 2 + 4);
            graphics.drawString(this.bn, this.R + this.Y + 5, this.S + this.Z + 4);
            graphics.drawString(this.bi, this.R + this.Y + 5, this.S + 4);
        }
        else {
            graphics.drawString(this.bo, this.R - 5 - super.v.b(this.bo, font), this.S + this.V + 4);
            graphics.drawString(this.bj, this.R - 5 - super.v.b(this.bj, font), this.S + this.Z * 5 + 4);
            graphics.drawString(this.bk, this.R - 5 - super.v.b(this.bk, font), this.S + this.Z * 4 + 4);
            graphics.drawString(this.bl, this.R - 5 - super.v.b(this.bl, font), this.S + this.Z * 3 + 4);
            graphics.drawString(this.bm, this.R - 5 - super.v.b(this.bm, font), this.S + this.Z * 2 + 4);
            graphics.drawString(this.bn, this.R - 5 - super.v.b(this.bn, font), this.S + this.Z + 4);
            graphics.drawString(this.bi, this.R - 5 - super.v.b(this.bi, font), this.S + 4);
        }
        for (int n15 = 0; n15 < super.l; ++n15) {
            if (super.j.a[n15].b != null && (super.K == 1 || n15 % super.K == 0)) {
                if (!super.B) {
                    if (!super.A) {
                        graphics.drawString(super.j.a[n15].b, this.R + n15 * this.X - super.v.a(super.j.a[n15].b, font), this.S + this.V + 17);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.j.a[n15].b, this.R + n15 * this.X - super.v.a(super.j.a[n15].b, font), this.S + this.V + 17);
                        n = 0;
                    }
                    else {
                        graphics.drawString(super.j.a[n15].b, this.R + n15 * this.X - super.v.a(super.j.a[n15].b, font), this.S + this.V + 23 + super.c);
                        n = 1;
                    }
                }
                else {
                    final Image a = super.v.a(super.j.a[n15].b, font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, this.R + n15 * this.X - super.c / 2, this.S + this.V + 10, null);
                    }
                }
            }
        }
        if (super.r) {
            graphics.setColor(this.bb);
            for (int n16 = 0; n16 < super.l; ++n16) {
                if (!super.j.a[n16].k && ((super.j.a[n16].a <= super.o && super.j.a[n16].a >= super.n) || !super.p)) {
                    if (super.j.a[n16].c != null) {
                        graphics.setColor(super.j.a[n16].c);
                        graphics.fillRect(super.j.a[n16].l, super.j.a[n16].m, this.bc * 2, this.bc * 2);
                        graphics.setColor(this.bb);
                    }
                    else {
                        graphics.fillRect(super.j.a[n16].l, super.j.a[n16].m, this.bc * 2, this.bc * 2);
                    }
                }
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        graphics.setColor(super.w);
        for (int i = 0; i < super.l; ++i) {
            if (n == super.j.a[i]) {
                graphics.fillRect(super.j.a[i].l, super.j.a[i].m, this.bc * 2, this.bc * 2);
                return;
            }
        }
    }
    
    public n a(final int n, final int n2) {
        int i = 0;
        while (i < super.l) {
            if (super.j.a[i] != null && n >= super.j.a[i].l && n <= super.j.a[i].n && n2 >= super.j.a[i].m && n2 <= super.j.a[i].o) {
                if ((super.j.a[i].a <= super.o && super.j.a[i].a >= super.n) || !super.p) {
                    return super.j.a[i];
                }
                return null;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public void b(final Graphics graphics) {
        final Font font = new Font(super.f, 1, super.c + 2);
        graphics.setFont(font);
        graphics.setColor(super.h);
        if (super.D != null) {
            if (super.B) {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.W), this.S + this.V + super.C + (super.c + 15));
            }
            else {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.W), this.S + super.b - (super.c + 2));
            }
        }
        if (super.E != null) {
            super.v.a(super.a - (super.c + 2) / 2, this.S, graphics, super.E, super.c + 2, this.V);
        }
    }
    
    public void a(final Color bb) {
        this.bb = bb;
    }
}
