// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class i extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private double X;
    private double Y;
    private boolean Z;
    private boolean ba;
    private int bb;
    private int bc;
    private int bd;
    private int be;
    private int bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private String bl;
    private String bm;
    
    public i(final int r, final int n, final int a, final int b, final Color g, final Color h, final int c) {
        this.Z = false;
        this.ba = false;
        this.bc = 4;
        this.bf = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = r;
        this.S = n + 10;
        super.c = c;
        this.V = super.a - 10;
        this.W = super.b - 30;
    }
    
    public boolean a(final int l, final m k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        double[] array = new double[0];
        int n = 0;
        if (super.l < 2) {
            return false;
        }
        final Font font = new Font(super.f, 0, super.c);
        if (super.D != null) {
            this.W -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.V -= 15 + (super.c + 2) / 2;
        }
        if (!super.p) {
            for (int i = 0; i < super.k.a.size(); ++i) {
                final o o = super.k.a.elementAt(i);
                for (int j = 0; j < super.k.c; ++j) {
                    if (o.a[j] != null && !o.a[j].k) {
                        if (!this.ba) {
                            this.ba = true;
                            this.X = o.a[j].a;
                            this.Y = this.X;
                        }
                        else {
                            if (o.a[j].a > this.X) {
                                this.X = o.a[j].a;
                            }
                            if (o.a[j].a < this.Y) {
                                this.Y = o.a[j].a;
                            }
                        }
                    }
                }
            }
            if (!this.ba || (this.X == 0.0 && this.Y == 0.0)) {
                this.X = 6.0;
                this.Y = 0.0;
            }
            if (this.Y >= 0.0) {
                this.bf = 1;
            }
            else if (this.X < 0.0) {
                this.bf = 3;
            }
            else {
                this.bf = 2;
            }
            switch (this.bf) {
                case 1: {
                    this.Y = 0.0;
                    array = super.v.a(7, this.Y, this.X, true, super.H);
                    this.X = array[6];
                    n = (int)array[7];
                    break;
                }
                case 2: {
                    this.X = Math.max(Math.abs(this.Y), Math.abs(this.X));
                    array = super.v.a(4, 0.0, this.X, true, super.H);
                    this.X = array[3];
                    n = (int)array[4];
                    break;
                }
                case 3: {
                    this.X = Math.abs(this.Y);
                    this.Y = 0.0;
                    array = super.v.a(7, this.Y, this.X, true, super.H);
                    this.X = 0.0;
                    this.Y = -array[6];
                    n = (int)array[7];
                    break;
                }
            }
        }
        else {
            array = super.v.a(7, super.n, super.o, false, super.H);
            this.Y = super.n;
            this.X = array[6];
            n = (int)array[7];
            if (this.Y >= 0.0) {
                this.bf = 1;
            }
            else if (this.X < 0.0) {
                this.bf = 3;
            }
            else {
                this.bf = 2;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        switch (this.bf) {
            case 1: {
                try {
                    this.bm = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bh = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[3]) + s;
                    this.bk = String.valueOf(s2) + instance.format(array[4]) + s;
                    this.bl = String.valueOf(s2) + instance.format(array[5]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.X) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bm = String.valueOf(s2) + instance.format(-this.X) + s;
                    this.bj = String.valueOf(s2) + instance.format(0L) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.X) + s;
                    if (super.p) {
                        this.bh = String.valueOf(s2) + instance.format(array[2]) + s;
                        this.bi = String.valueOf(s2) + instance.format(array[1]) + s;
                        this.bk = String.valueOf(s2) + instance.format(-array[1]) + s;
                        this.bl = String.valueOf(s2) + instance.format(-array[2]) + s;
                    }
                    else {
                        this.bh = String.valueOf(s2) + instance.format(-array[2]) + s;
                        this.bi = String.valueOf(s2) + instance.format(-array[1]) + s;
                        this.bk = String.valueOf(s2) + instance.format(array[1]) + s;
                        this.bl = String.valueOf(s2) + instance.format(array[2]) + s;
                    }
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bm = String.valueOf(s2) + instance.format(this.Y) + s;
                    this.bh = String.valueOf(s2) + instance.format(-array[5]) + s;
                    this.bi = String.valueOf(s2) + instance.format(-array[4]) + s;
                    this.bj = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bk = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bl = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.X) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        if (this.bf != 3) {
            this.bd = super.v.b(this.bg, font);
        }
        else {
            this.bd = super.v.b(this.bm, font);
        }
        if (super.k.b[0] != null && super.v.b(super.k.b[0], font) > this.be) {
            this.be = super.v.b(super.k.b[0], font);
        }
        if (!super.O) {
            this.R += this.be / 2;
        }
        else {
            this.R = this.R + this.be / 2 + this.bd;
        }
        this.V = this.V - this.be / 2 - this.bd;
        this.bb = this.W / 6;
        this.W = this.bb * 6;
        this.U = this.V / (super.l - 1);
        this.T = this.U * (super.l - 1);
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.k, super.l, this.U, super.c);
            }
            if (super.A) {
                this.W -= super.c + 5;
                if (super.D != null) {
                    this.W -= super.c + 2;
                }
                this.bb = this.W / 6;
                this.W = this.bb * 6;
            }
        }
        switch (this.bf) {
            case 1: {
                for (int n2 = 0; n2 < super.k.a.size(); ++n2) {
                    final o o2 = super.k.a.elementAt(n2);
                    for (int n3 = 0; n3 < super.k.c; ++n3) {
                        if (o2.a[n3] != null) {
                            if (!o2.a[n3].k && this.X - this.Y != 0.0) {
                                o2.a[n3].j = (int)(this.W - (o2.a[n3].a - this.Y) / (this.X - this.Y) * this.W);
                            }
                            else {
                                o2.a[n3].j = -1;
                            }
                        }
                    }
                }
                break;
            }
            case 2: {
                for (int n4 = 0; n4 < super.k.a.size(); ++n4) {
                    final o o3 = super.k.a.elementAt(n4);
                    for (int n5 = 0; n5 < super.k.c; ++n5) {
                        if (o3.a[n5] != null) {
                            if (!o3.a[n5].k && this.X * 2.0 != 0.0) {
                                o3.a[n5].j = (int)(this.W - (o3.a[n5].a + this.X) / (this.X * 2.0) * this.W);
                            }
                            else {
                                o3.a[n5].j = -1;
                            }
                        }
                    }
                }
                break;
            }
            case 3: {
                for (int n6 = 0; n6 < super.k.a.size(); ++n6) {
                    final o o4 = super.k.a.elementAt(n6);
                    for (int n7 = 0; n7 < super.k.c; ++n7) {
                        if (o4.a[n7] != null) {
                            if (!o4.a[n7].k && this.X - this.Y != 0.0) {
                                o4.a[n7].j = (int)(this.W - (o4.a[n7].a - this.Y) / (this.X - this.Y) * this.W);
                            }
                            else {
                                o4.a[n7].j = -1;
                            }
                        }
                    }
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
            graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
            graphics.drawLine(this.R, this.S + 1, this.R + this.T, this.S + 1);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.W);
            graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.W);
            for (int i = 1; i < 6; ++i) {
                graphics.drawLine(this.R, this.S + this.bb * i, this.R + this.T, this.S + this.bb * i);
                graphics.drawLine(this.R, this.S + 1 + this.bb * i, this.R + this.T, this.S + 1 + this.bb * i);
            }
            for (int j = 1; j < super.l; ++j) {
                if (super.L == 1 || j % super.L == 0) {
                    graphics.drawLine(this.R + this.U * j, this.S, this.R + this.U * j, this.S + this.W);
                    graphics.drawLine(this.R + 1 + this.U * j, this.S, this.R + 1 + this.U * j, this.S + this.W);
                }
            }
        }
        else {
            graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.W);
            for (int k = 1; k < 6; ++k) {
                graphics.drawLine(this.R, this.S + this.bb * k, this.R + this.T, this.S + this.bb * k);
            }
            for (int l = 1; l < super.l; ++l) {
                if (super.L == 1 || l % super.L == 0) {
                    graphics.drawLine(this.R + this.U * l, this.S, this.R + this.U * l, this.S + this.W);
                }
            }
        }
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 0; n4 < super.k.a.size(); ++n4) {
            int n5 = 1;
            this.Z = true;
            final o o = super.k.a.elementAt(n4);
            final Color c = o.c;
            for (int n6 = 0; n6 < super.k.c; ++n6) {
                if (o.a[n6] != null) {
                    final int n7 = this.R + n6 * this.U;
                    final int n8 = this.S + o.a[n6].j;
                    if (o.a[n6].c != null) {
                        graphics.setColor(o.a[n6].c);
                    }
                    else {
                        graphics.setColor(c);
                    }
                    if (!o.a[n6].k) {
                        if (!super.s) {
                            if (super.z) {
                                if (n5 == 0) {
                                    super.v.a(graphics, n2, n3, n7, n8, super.I, graphics.getColor());
                                }
                                else {
                                    n5 = 0;
                                }
                            }
                            else {
                                if (!this.Z) {
                                    super.v.a(graphics, n2, n3, n7, n8, super.I, graphics.getColor());
                                }
                                this.Z = false;
                            }
                        }
                        n2 = n7;
                        n3 = n8;
                        if (!super.y) {
                            o.a[n6].l = n2 - this.bc;
                            o.a[n6].m = n3 - this.bc;
                            o.a[n6].n = n2 + this.bc;
                            o.a[n6].o = n3 + this.bc;
                        }
                    }
                }
                else {
                    this.Z = true;
                }
            }
        }
        graphics.setColor(super.g);
        graphics.fillRect(0, 0, super.d + 1, this.S);
        graphics.fillRect(0, this.S + this.W, super.d + 1, super.e - (this.S + this.W));
        graphics.setColor(super.h);
        if (super.N) {
            graphics.drawLine(this.R, this.S + this.W, this.R + this.T, this.S + this.W);
            graphics.drawLine(this.R, this.S + 1 + this.W, this.R + this.T, this.S + 1 + this.W);
            if (!super.O) {
                graphics.drawLine(this.R + this.T, this.S, this.R + this.T, this.S + this.W);
                graphics.drawLine(this.R + 1 + this.T, this.S, this.R + 1 + this.T, this.S + this.W + 1);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.W);
                graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.W + 1);
            }
        }
        else {
            graphics.drawLine(this.R, this.S + this.W, this.R + this.T, this.S + this.W);
            if (!super.O) {
                graphics.drawLine(this.R + this.T, this.S, this.R + this.T, this.S + this.W);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.W);
            }
        }
        switch (this.bf) {
            case 2: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S + this.bb * 3, this.R + this.T, this.S + this.bb * 3);
                    graphics.drawLine(this.R, this.S + 1 + this.bb * 3, this.R + this.T, this.S + 1 + this.bb * 3);
                    for (int n9 = 0; n9 < super.l; ++n9) {
                        graphics.drawLine(this.R + this.U * n9, this.S + this.bb * 3 - 2, this.R + this.U * n9, this.S + this.bb * 3 + 3);
                        graphics.drawLine(this.R + 1 + this.U * n9, this.S + this.bb * 3 - 2, this.R + 1 + this.U * n9, this.S + this.bb * 3 + 3);
                    }
                    break;
                }
                graphics.drawLine(this.R, this.S + this.bb * 3, this.R + this.T, this.S + this.bb * 3);
                for (int n10 = 0; n10 < super.l; ++n10) {
                    graphics.drawLine(this.R + this.U * n10, this.S + this.bb * 3 - 2, this.R + this.U * n10, this.S + this.bb * 3 + 2);
                }
                break;
            }
            case 3: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
                    graphics.drawLine(this.R, this.S + 1, this.R + this.T, this.S + 1);
                    for (int n11 = 0; n11 < super.l; ++n11) {
                        graphics.drawLine(this.R + this.U * n11, this.S - 2, this.R + this.U * n11, this.S + 2);
                        graphics.drawLine(this.R + 1 + this.U * n11, this.S - 2, this.R + 1 + this.U * n11, this.S + 2);
                    }
                    break;
                }
                graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
                for (int n12 = 0; n12 < super.l; ++n12) {
                    graphics.drawLine(this.R + this.U * n12, this.S - 2, this.R + this.U * n12, this.S + 2);
                }
                break;
            }
        }
        if (super.N) {
            for (int n13 = 0; n13 < 7; ++n13) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.T - 2, this.S + this.bb * n13, this.R + this.T + 3, this.S + this.bb * n13);
                    graphics.drawLine(this.R + this.T - 2, this.S + 1 + this.bb * n13, this.R + this.T + 3, this.S + 1 + this.bb * n13);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.bb * n13, this.R + 3, this.S + this.bb * n13);
                    graphics.drawLine(this.R - 2, this.S + 1 + this.bb * n13, this.R + 3, this.S + 1 + this.bb * n13);
                }
            }
            for (int n14 = 0; n14 < super.l; ++n14) {
                if (super.J == 1 || n14 % super.J == 0) {
                    graphics.drawLine(this.R + this.U * n14, this.S + this.W - 2, this.R + this.U * n14, this.S + this.W + 3);
                    graphics.drawLine(this.R + 1 + this.U * n14, this.S + this.W - 2, this.R + 1 + this.U * n14, this.S + this.W + 3);
                }
            }
        }
        else {
            for (int n15 = 0; n15 < 7; ++n15) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.T - 2, this.S + this.bb * n15, this.R + this.T + 2, this.S + this.bb * n15);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.bb * n15, this.R + 2, this.S + this.bb * n15);
                }
            }
            for (int n16 = 0; n16 < super.l; ++n16) {
                if (super.J == 1 || n16 % super.J == 0) {
                    graphics.drawLine(this.R + this.U * n16, this.S + this.W - 2, this.R + this.U * n16, this.S + this.W + 2);
                }
            }
        }
        if (!super.O) {
            graphics.drawString(this.bm, this.R + this.T + 5, this.S + this.W + 4);
            graphics.drawString(this.bh, this.R + this.T + 5, this.S + this.bb * 5 + 4);
            graphics.drawString(this.bi, this.R + this.T + 5, this.S + this.bb * 4 + 4);
            graphics.drawString(this.bj, this.R + this.T + 5, this.S + this.bb * 3 + 4);
            graphics.drawString(this.bk, this.R + this.T + 5, this.S + this.bb * 2 + 4);
            graphics.drawString(this.bl, this.R + this.T + 5, this.S + this.bb + 4);
            graphics.drawString(this.bg, this.R + this.T + 5, this.S + 4);
        }
        else {
            graphics.drawString(this.bm, this.R - 5 - super.v.b(this.bm, font), this.S + this.W + 4);
            graphics.drawString(this.bh, this.R - 5 - super.v.b(this.bh, font), this.S + this.bb * 5 + 4);
            graphics.drawString(this.bi, this.R - 5 - super.v.b(this.bi, font), this.S + this.bb * 4 + 4);
            graphics.drawString(this.bj, this.R - 5 - super.v.b(this.bj, font), this.S + this.bb * 3 + 4);
            graphics.drawString(this.bk, this.R - 5 - super.v.b(this.bk, font), this.S + this.bb * 2 + 4);
            graphics.drawString(this.bl, this.R - 5 - super.v.b(this.bl, font), this.S + this.bb + 4);
            graphics.drawString(this.bg, this.R - 5 - super.v.b(this.bg, font), this.S + 4);
        }
        for (int n17 = 0; n17 < super.l; ++n17) {
            if (super.K == 1 || n17 % super.K == 0) {
                if (!super.B) {
                    if (super.k.b[n17] != null) {
                        if (!super.A) {
                            graphics.drawString(super.k.b[n17], this.R + n17 * this.U - super.v.a(super.k.b[n17], font), this.S + this.W + 17);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n17], this.R + n17 * this.U - super.v.a(super.k.b[n17], font), this.S + this.W + 17);
                        }
                        else {
                            graphics.drawString(super.k.b[n17], this.R + n17 * this.U - super.v.a(super.k.b[n17], font), this.S + this.W + 23 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n17] != null) {
                    final Image a = super.v.a(super.k.b[n17], font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, this.R + n17 * this.U - super.c / 2, this.S + this.W + 10, null);
                    }
                }
            }
        }
        if (super.r) {
            for (int n18 = 0; n18 < super.k.a.size(); ++n18) {
                final o o2 = super.k.a.elementAt(n18);
                graphics.setColor(o2.c);
                for (int n19 = 0; n19 < super.k.c; ++n19) {
                    if (o2.a[n19] != null && !o2.a[n19].k && ((o2.a[n19].a <= super.o && o2.a[n19].a >= super.n) || !super.p)) {
                        if (o2.a[n19].c != null) {
                            graphics.setColor(o2.a[n19].c);
                            graphics.fillRect(o2.a[n19].l, o2.a[n19].m, this.bc * 2, this.bc * 2);
                            graphics.setColor(o2.c);
                        }
                        else {
                            graphics.fillRect(o2.a[n19].l, o2.a[n19].m, this.bc * 2, this.bc * 2);
                        }
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
        for (int i = 0; i < super.k.a.size(); ++i) {
            final o o = super.k.a.elementAt(i);
            for (int j = 0; j < super.k.c; ++j) {
                if (n == o.a[j]) {
                    graphics.fillRect(o.a[j].l, o.a[j].m, this.bc * 2, this.bc * 2);
                    break;
                }
            }
        }
    }
    
    public n a(final int n, final int n2) {
        for (int i = 0; i < super.m; ++i) {
            final o o = super.k.a.elementAt(i);
            int j = 0;
            while (j < super.l) {
                if (o.a[j] != null && n >= o.a[j].l && n <= o.a[j].n && n2 >= o.a[j].m && n2 <= o.a[j].o) {
                    if ((o.a[j].a <= super.o && o.a[j].a >= super.n) || !super.p) {
                        return o.a[j];
                    }
                    return null;
                }
                else {
                    ++j;
                }
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
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.V), this.S + this.W + super.C + (super.c + 17));
            }
            else {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.V), this.S + super.b - (super.c + 2));
            }
        }
        if (super.E != null) {
            if (!super.O) {
                super.v.a(super.a - (super.c + 2) / 2, this.S, graphics, super.E, super.c + 2, this.W);
                return;
            }
            super.v.a((super.c + 2) / 2, this.S, graphics, super.E, super.c + 2, this.W);
        }
    }
}
