// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Graphics;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.InputStream;

public class EadStruct
{
    c[] byte;
    e g;
    int long;
    String char;
    int[] for;
    int[] try;
    int f;
    int j;
    int case;
    double[] if;
    int[] else;
    int[] b;
    int[] new;
    int void;
    int int;
    int[] do;
    int[] goto;
    int[] a;
    boolean k;
    double null;
    double c;
    double d;
    double e;
    double h;
    double i;
    
    public EadStruct(final String char1) {
        this.char = char1;
        this.try();
    }
    
    EadStruct(final String s, final int n) {
        final double n2 = 0.0;
        double n3 = 0.0;
        final double n4 = 0.0;
        final int[] array = new int[6];
        final int[] array2 = { 1, 1, 1, 1, 0, 0 };
        (this.g = new e()).for(0.0);
        this.g.if(0.0);
        if (s.indexOf("polyketide: ") < 0) {
            this.long = n * 3 + 2;
            this.byte = new c[this.long];
            final int n5 = 3;
            final int n6 = 4;
            array[0] = 1;
            array[2] = (array[1] = n) + 1;
            array[3] = n + 2;
            this.byte[0] = new c(n2, n3, n4, n5, n6, array, array2);
            for (int i = 1; i < n; ++i) {
                array[0] = i - 1;
                array[1] = i + 1;
                array[2] = n + 2 * i + 1;
                array[3] = n + 2 * i + 2;
                final double n7 = i * 1.245;
                n3 = 0.9114999771118164 - n3;
                this.byte[i] = new c(n7, n3, n4, n5, n6, array, array2);
            }
            final int n8 = 41;
            final boolean b = true;
            this.byte[n - 1].if[1] = this.long - 1;
            array[0] = 0;
            array[2] = (array[1] = 0);
            array2[1] = (array[3] = 0);
            array2[3] = (array2[2] = 0);
            this.byte[n] = new c(-0.908, 0.654, n4, n8, b ? 1 : 0, array, array2);
            double n9 = 1.5585;
            for (int j = 0; j < n; ++j) {
                array[0] = j;
                final double n10 = j * 1.245;
                n9 = 0.9114999771118164 - n9;
                final double n11 = 0.913;
                this.byte[n + 2 * j + 1] = new c(n10, n9, n11, n8, b ? 1 : 0, array, array2);
                this.byte[n + 2 * j + 2] = new c(n10, n9, -n11, n8, b ? 1 : 0, array, array2);
            }
            final double n12 = this.byte[n - 1].char + 0.908;
            double n13;
            if (this.byte[n - 1].case < 0.1) {
                n13 = 0.654;
            }
            else {
                n13 = 0.2577;
            }
            this.byte[this.long - 1] = new c(n12, n13, 0.0, n8, b ? 1 : 0, array, array2);
            this.int();
        }
        else {
            this.a(s, n);
        }
    }
    
    EadStruct(final int f) {
        this.f = f;
        this.for = new int[f + 1];
        this.try = new int[f + 1];
    }
    
    EadStruct(final InputStream inputStream, final int n) throws IOException, d {
        (this.g = new e()).for(0.0);
        this.g.if(0.0);
        final StreamTokenizer streamTokenizer = new StreamTokenizer(inputStream);
        streamTokenizer.eolIsSignificant(true);
        streamTokenizer.commentChar(35);
        if (n == 1) {
            this.if(streamTokenizer);
        }
        else if (n == 2) {
            this.a(streamTokenizer);
        }
        inputStream.close();
        if (streamTokenizer.ttype != -1) {
            throw new d(streamTokenizer.toString());
        }
    }
    
    void if(final StreamTokenizer streamTokenizer) throws IOException, d {
        while (true) {
            switch (streamTokenizer.nextToken()) {
                default: {}
                case 10: {
                    continue;
                }
                case -2: {
                    int n = 0;
                    final int[] array = new int[6];
                    final int[] array2 = new int[6];
                    this.long = (int)streamTokenizer.nval;
                    this.byte = new c[this.long];
                    for (int i = 0; i < this.long; ++i) {
                        while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                            streamTokenizer.nextToken();
                        }
                        streamTokenizer.nextToken();
                        final int n2 = (int)streamTokenizer.nval;
                        int n3 = 0;
                        for (int j = 0; j < 6; ++j) {
                            streamTokenizer.nextToken();
                            array[n3] = (int)streamTokenizer.nval - 1;
                            streamTokenizer.nextToken();
                            array2[n3] = (int)streamTokenizer.nval;
                            if (array[n3] >= 0) {
                                ++n3;
                            }
                        }
                        streamTokenizer.nextToken();
                        final double nval = streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        final double nval2 = streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        this.byte[n] = new c(nval, nval2, streamTokenizer.nval, n2, n3, array, array2);
                        ++n;
                    }
                    while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                        streamTokenizer.nextToken();
                    }
                    continue;
                }
            }
        }
    }
    
    void a(final StreamTokenizer streamTokenizer) throws IOException, d {
        while (streamTokenizer.lineno() < 3) {
            streamTokenizer.nextToken();
        }
        while (true) {
            switch (streamTokenizer.nextToken()) {
                default: {}
                case 10: {
                    continue;
                }
                case -3: {
                    continue;
                }
                case -2: {
                    this.long = (int)streamTokenizer.nval;
                    this.byte = new c[this.long];
                    streamTokenizer.nextToken();
                    final int n = (int)streamTokenizer.nval;
                    while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                        streamTokenizer.nextToken();
                    }
                    for (int i = 0; i < this.long; ++i) {
                        streamTokenizer.nextToken();
                        final double nval = streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        final double nval2 = streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        this.byte[i] = new c(nval, nval2, streamTokenizer.nval);
                        streamTokenizer.nextToken();
                        this.byte[i].a(streamTokenizer.sval);
                        this.byte[i].a();
                        while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                            streamTokenizer.nextToken();
                        }
                    }
                    for (int j = 0; j < n; ++j) {
                        streamTokenizer.nextToken();
                        final int n2 = (int)streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        final int n3 = (int)streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        int n4 = (int)streamTokenizer.nval;
                        streamTokenizer.nextToken();
                        if ((int)streamTokenizer.nval == 1) {
                            n4 = 5;
                        }
                        else if ((int)streamTokenizer.nval == 4) {
                            n4 = 6;
                        }
                        else if ((int)streamTokenizer.nval == 6) {
                            n4 = 7;
                        }
                        this.a(n2, n3, n4);
                        while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                            streamTokenizer.nextToken();
                        }
                    }
                    continue;
                }
            }
        }
    }
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 1999-2006");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
    }
    
    void a(int n, int n2, final int n3) {
        --n;
        --n2;
        this.if(n, n2, n3);
        this.if(n2, n, n3);
    }
    
    void if(final int n, final int n2, final int n3) {
        final c c = this.byte[n];
        ++c.new;
        if (this.byte[n].new > 1) {
            final int[] if1 = new int[this.byte[n].new];
            System.arraycopy(this.byte[n].if, 0, if1, 0, this.byte[n].if.length);
            this.byte[n].if = if1;
            final int[] a = new int[this.byte[n].new];
            System.arraycopy(this.byte[n].a, 0, a, 0, this.byte[n].a.length);
            this.byte[n].a = a;
        }
        else {
            this.byte[n].if = new int[1];
            this.byte[n].a = new int[1];
        }
        final int n4 = this.byte[n].new - 1;
        this.byte[n].if[n4] = n2;
        this.byte[n].a[n4] = n3;
    }
    
    void int() {
        final int[] array = new int[this.long];
        for (int i = 0; i < this.long; ++i) {
            for (int j = 0; j < this.byte[i].new; ++j) {
                final int[] array2 = array;
                final int n = i;
                array2[n] += this.byte[this.byte[i].if[j]].new - 1;
            }
        }
        for (int k = 0; k < this.long; ++k) {
            if (array[k] > 0) {
                final int[] array3 = new int[array[k]];
                int n2 = 0;
                for (int l = 0; l < this.byte[k].new; ++l) {
                    for (int n3 = this.byte[k].if[l], n4 = 0; n4 < this.byte[n3].new; ++n4) {
                        final int n5 = this.byte[n3].if[n4];
                        if (n5 != k) {
                            array3[n2] = n5;
                            ++n2;
                        }
                    }
                }
                this.byte[k].a(array3);
            }
        }
    }
    
    void case() {
        for (int i = 0; i < this.long; ++i) {
            this.a(i, this.byte[i].char, this.byte[i].case, this.byte[i].byte, this.byte[i].int, this.byte[i].else);
        }
        this.j = this.long;
    }
    
    void a(final int n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        if (n >= this.case) {
            if (this.if == null) {
                this.case = 100;
                this.if = new double[this.case * 3];
                this.b = new int[this.case];
                this.new = new int[this.case];
            }
            else {
                this.case *= 2;
                final double[] if1 = new double[this.case * 3];
                System.arraycopy(this.if, 0, if1, 0, this.if.length);
                this.if = if1;
                final int[] b = new int[this.case];
                System.arraycopy(this.b, 0, b, 0, this.b.length);
                this.b = b;
                final int[] new1 = new int[this.case];
                System.arraycopy(this.new, 0, new1, 0, this.new.length);
                this.new = new1;
            }
        }
        this.b[n] = n5;
        this.new[n] = n6;
        final int n7 = n * 3;
        this.if[n7] = n2;
        this.if[n7 + 1] = n3;
        this.if[n7 + 2] = n4;
    }
    
    void a() {
        this.void = 0;
        for (int i = 0; i < this.long; ++i) {
            for (int j = 0; j < this.byte[i].new; ++j) {
                if (i < this.byte[i].if[j]) {
                    this.a(i, this.byte[i].if[j], this.byte[i].int, this.byte[i].a[j]);
                    ++this.void;
                }
            }
        }
        this.j = this.long;
    }
    
    void a(int n, int n2, final int n3, final int n4) {
        final int void1 = this.void;
        if (n > this.j || n2 > this.j) {
            return;
        }
        if (void1 >= this.int) {
            if (this.do == null) {
                this.int = 100;
                this.do = new int[this.int];
                this.goto = new int[this.int];
                this.a = new int[this.int];
            }
            else {
                this.int *= 2;
                final int[] do1 = new int[this.int];
                System.arraycopy(this.do, 0, do1, 0, this.do.length);
                this.do = do1;
                final int[] goto1 = new int[this.int];
                System.arraycopy(this.goto, 0, goto1, 0, this.goto.length);
                this.goto = goto1;
                final int[] a = new int[this.int];
                System.arraycopy(this.a, 0, a, 0, this.a.length);
                this.a = a;
            }
        }
        if (n > n2) {
            final int n5 = n;
            n = n2;
            n2 = n5;
        }
        this.do[void1] = (n << 16 | n2);
        this.goto[void1] = n3;
        this.a[void1] = n4;
    }
    
    void a(final boolean b) {
        this.j = this.long;
        for (int i = 0; i < this.long; ++i) {
            for (int j = 0; j < this.byte[i].new; ++j) {
                if (i < this.byte[i].if[j] && (b || this.byte[i].int != this.byte[this.byte[i].if[j]].int)) {
                    this.a(this.j, (this.byte[i].char + this.byte[this.byte[i].if[j]].char) / 2.0, (this.byte[i].case + this.byte[this.byte[i].if[j]].case) / 2.0, (this.byte[i].byte + this.byte[this.byte[i].if[j]].byte) / 2.0, -1, -1);
                    final int n = i << 16 | this.byte[i].if[j];
                    for (int k = 0; k < this.void; ++k) {
                        if (n == this.do[k]) {
                            this.do[k] = (i << 16 | this.j);
                            this.a(this.byte[i].if[j], this.j, this.byte[this.byte[i].if[j]].int, this.a[k]);
                            ++this.void;
                            k = this.void;
                        }
                        else if (k == this.void - 1) {
                            System.out.println("Error: no match " + Integer.toString(n));
                        }
                    }
                    ++this.j;
                }
            }
        }
    }
    
    void byte() {
        if (this.k || this.j <= 0) {
            return;
        }
        if (this.else == null || this.else.length < this.j * 3 + 3) {
            this.else = new int[this.j * 3 + 3];
        }
        this.g.a(this.if, this.else, this.j);
        this.k = true;
    }
    
    private void a(final int[] array, final int n, final int n2) {
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
    }
    
    void new() {
        if (this.j <= 0) {
            return;
        }
        final double[] if1 = this.if;
        double c;
        double null = c = if1[0];
        double e;
        double d = e = if1[1];
        double i;
        double h = i = if1[2];
        int n = this.j * 3;
        while (true) {
            n -= 3;
            if (n <= 0) {
                break;
            }
            final double n2 = if1[n];
            if (n2 < null) {
                null = n2;
            }
            if (n2 > c) {
                c = n2;
            }
            final double n3 = if1[n + 1];
            if (n3 < d) {
                d = n3;
            }
            if (n3 > e) {
                e = n3;
            }
            final double n4 = if1[n + 2];
            if (n4 < h) {
                h = n4;
            }
            if (n4 <= i) {
                continue;
            }
            i = n4;
        }
        this.c = c;
        this.null = null;
        this.e = e;
        this.d = d;
        this.i = i;
        this.h = h;
    }
    
    void a(final Graphics graphics, final boolean b, final boolean b2) {
        if (this.if == null || this.j <= 0) {
            return;
        }
        this.byte();
        if (this.void <= 0 || this.j <= 0) {
            return;
        }
        for (int i = 0; i < this.void; ++i) {
            final int n = this.do[i];
            final int n2 = (n >> 16 & 0xFFFF) * 3;
            final int n3 = (n & 0xFFFF) * 3;
            graphics.setColor(magnus.c.do[this.goto[i]]);
            if (b | this.a[i] == 1) {
                graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3], this.else[n3 + 1]);
            }
            else {
                int n4;
                int n5;
                if (Math.abs(this.else[n2] - this.else[n3]) <= Math.abs(this.else[n2 + 1] - this.else[n3 + 1])) {
                    n4 = 1;
                    n5 = 0;
                }
                else {
                    n4 = 0;
                    n5 = 1;
                }
                if (this.a[i] == 2) {
                    final int n6 = n4 * 2;
                    final int n7 = n5 * 2;
                    graphics.drawLine(this.else[n2] - n6, this.else[n2 + 1] - n7, this.else[n3] - n6, this.else[n3 + 1] - n7);
                    graphics.drawLine(this.else[n2] + n6, this.else[n2 + 1] + n7, this.else[n3] + n6, this.else[n3 + 1] + n7);
                }
                else if (this.a[i] == 3) {
                    final int n8 = n4 * 3;
                    final int n9 = n5 * 3;
                    graphics.drawLine(this.else[n2] - n8, this.else[n2 + 1] - n9, this.else[n3] - n8, this.else[n3 + 1] - n9);
                    graphics.drawLine(this.else[n2] + n8, this.else[n2 + 1] + n9, this.else[n3] + n8, this.else[n3 + 1] + n9);
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3], this.else[n3 + 1]);
                }
                else if (this.a[i] == 5) {
                    if (n5 > 0) {
                        final int n10 = (this.else[n3] - this.else[n2]) / 4;
                        final double n11 = (this.else[n3 + 1] - this.else[n2 + 1]) / n10;
                        if (n10 <= 0) {
                            for (int j = 0; j >= n10; --j) {
                                final int n12 = this.else[n2 + 1] + (int)(n11 * j);
                                graphics.drawLine(4 * j + this.else[n2], n12 - 3, 4 * j + this.else[n2], n12 + 3);
                            }
                        }
                        else {
                            for (int k = 0; k <= n10; ++k) {
                                final int n13 = this.else[n2 + 1] + (int)(n11 * k);
                                graphics.drawLine(4 * k + this.else[n2], n13 - 3, 4 * k + this.else[n2], n13 + 3);
                            }
                        }
                    }
                    else {
                        final int n14 = (this.else[n3 + 1] - this.else[n2 + 1]) / 4;
                        final double n15 = (this.else[n3] - this.else[n2]) / n14;
                        if (n14 <= 0) {
                            for (int l = 0; l >= n14; --l) {
                                final int n16 = this.else[n2] + (int)(n15 * l);
                                graphics.drawLine(n16 - 3, 4 * l + this.else[n2 + 1], n16 + 3, 4 * l + this.else[n2 + 1]);
                            }
                        }
                        else {
                            for (int n17 = 0; n17 <= n14; ++n17) {
                                final int n18 = this.else[n2] + (int)(n15 * n17);
                                graphics.drawLine(n18 - 3, 4 * n17 + this.else[n2 + 1], n18 + 3, 4 * n17 + this.else[n2 + 1]);
                            }
                        }
                    }
                }
                else if (this.a[i] == 6) {
                    if (n5 > 0) {
                        final int n19 = (this.else[n3] - this.else[n2]) / 6;
                        final double n20 = (this.else[n3 + 1] - this.else[n2 + 1]) / n19;
                        if (n19 <= 0) {
                            for (int n21 = 0; n21 >= n19 + 1; --n21) {
                                final int n22 = this.else[n2 + 1] + (int)(n20 * n21);
                                final int n23 = this.else[n2 + 1] + (int)(n20 * (n21 - 1));
                                final int n24 = (n22 + n23) / 2;
                                graphics.drawLine(6 * n21 + this.else[n2], n22 - 3, 6 * n21 + this.else[n2] - 3, n24 + 3);
                                graphics.drawLine(6 * n21 + this.else[n2] - 3, n24 + 3, 6 * n21 + this.else[n2] - 6, n23 - 3);
                            }
                        }
                        else {
                            for (int n25 = 0; n25 <= n19 - 1; ++n25) {
                                final int n26 = this.else[n2 + 1] + (int)(n20 * n25);
                                final int n27 = this.else[n2 + 1] + (int)(n20 * (n25 + 1));
                                final int n28 = (n26 + n27) / 2;
                                graphics.drawLine(6 * n25 + this.else[n2], n26 - 3, 6 * n25 + this.else[n2] + 3, n28 + 3);
                                graphics.drawLine(6 * n25 + this.else[n2] + 3, n28 + 3, 6 * n25 + this.else[n2] + 6, n27 - 3);
                            }
                        }
                    }
                    else {
                        final int n29 = (this.else[n3 + 1] - this.else[n2 + 1]) / 6;
                        final double n30 = (this.else[n3] - this.else[n2]) / n29;
                        if (n29 <= 0) {
                            for (int n31 = 0; n31 >= n29 + 1; --n31) {
                                final int n32 = this.else[n2] + (int)(n30 * n31);
                                final int n33 = this.else[n2] + (int)(n30 * (n31 - 1));
                                final int n34 = (n32 + n33) / 2;
                                graphics.drawLine(n32 - 3, 6 * n31 + this.else[n2 + 1], n34 + 3, 6 * n31 + this.else[n2 + 1] - 3);
                                graphics.drawLine(n34 + 3, 6 * n31 + this.else[n2 + 1] - 3, n33 - 3, 6 * n31 + this.else[n2 + 1] - 6);
                            }
                        }
                        else {
                            for (int n35 = 0; n35 <= n29 - 1; ++n35) {
                                final int n36 = this.else[n2] + (int)(n30 * n35);
                                final int n37 = this.else[n2] + (int)(n30 * (n35 + 1));
                                final int n38 = (n36 + n37) / 2;
                                graphics.drawLine(n36 - 3, 6 * n35 + this.else[n2 + 1], n38 + 3, 6 * n35 + this.else[n2 + 1] + 3);
                                graphics.drawLine(n38 + 3, 6 * n35 + this.else[n2 + 1] + 3, n37 - 3, 6 * n35 + this.else[n2 + 1] + 6);
                            }
                        }
                    }
                }
                else if (this.a[i] == 7) {
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3] - n4, this.else[n3 + 1] - n5);
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3] + n4, this.else[n3 + 1] + n5);
                    final int n39 = n4 * 2;
                    final int n40 = n5 * 2;
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3] - n39, this.else[n3 + 1] - n40);
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3] + n39, this.else[n3 + 1] + n40);
                    graphics.drawLine(this.else[n2], this.else[n2 + 1], this.else[n3], this.else[n3 + 1]);
                }
            }
        }
        if (b2) {
            for (int n41 = 0; n41 < this.long; ++n41) {
                if (this.byte[n41].try > 0 && this.byte[n41].try != 6) {
                    graphics.setColor(magnus.c.do[this.byte[n41].int]);
                    graphics.clearRect(this.else[3 * n41] - 4, this.else[3 * n41 + 1] - 4, 8, 8);
                    graphics.drawString(magnus.a.if[this.byte[n41].try], this.else[3 * n41] - 4, this.else[3 * n41 + 1] + 4);
                }
            }
        }
    }
    
    void for() {
        this.char = null;
        System.out.println("Begin findMolForm");
        final int[] array = new int[magnus.a.if.length];
        for (int i = 0; i < this.long; ++i) {
            if (this.byte[i].try > 0) {
                final int[] array2 = array;
                final int try1 = this.byte[i].try;
                ++array2[try1];
            }
            if (this.byte[i].try == 6) {
                int n = 0;
                for (int j = 0; j < this.byte[i].new; ++j) {
                    int n2 = this.byte[i].a[j];
                    if (n2 > 4) {
                        n2 = 1;
                    }
                    n += n2;
                }
                if (n < 4) {
                    array[1] = array[1] + 4 - n;
                }
            }
        }
        this.f = 0;
        for (int k = 0; k < magnus.a.if.length; ++k) {
            if (array[k] > 0) {
                ++this.f;
            }
        }
        this.for = new int[this.f + 1];
        this.try = new int[this.f + 1];
        final int n3 = 1;
        for (int l = 0; l < magnus.a.if.length; ++l) {
            if (array[l] > 0) {
                this.for[n3] = l;
                final int[] try2 = this.try;
                final int n4 = n3;
                ++try2[n4];
            }
        }
    }
    
    public void setupMolForm() {
        this.char = "";
        for (int i = 0; i < magnus.a.for.length; ++i) {
            int j = 1;
            while (j <= this.f) {
                if (this.for[j] == magnus.a.for[i]) {
                    if (this.try[j] > 1) {
                        this.char = this.char + magnus.a.if[this.for[j]] + Integer.toString(this.try[j]) + " ";
                        break;
                    }
                    if (this.try[j] == 1) {
                        this.char = this.char + magnus.a.if[this.for[j]] + " ";
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    public String elePercentage() {
        String string = "";
        final double molWeight = this.molWeight("Average");
        for (int i = 0; i < magnus.a.for.length; ++i) {
            int j = 1;
            while (j <= this.f) {
                if (this.for[j] == magnus.a.for[i]) {
                    if (this.try[j] >= 1) {
                        string = string + magnus.a.if[this.for[j]] + " " + EadUtil.printNumber(100.0 * this.try[j] * magnus.a.a(this.for[j], "Average") / molWeight, 3) + "; ";
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        return string;
    }
    
    void do() {
        for (boolean b = false; !b; b = this.a("{", "}")) {
            b = this.a("(", ")");
            if (b) {
                b = this.a("[", "]");
            }
            if (b) {}
        }
    }
    
    boolean a(final String s, final String s2) {
        final int index = this.char.indexOf(s);
        if (index > 0) {
            final int index2 = this.char.indexOf(s, index + 1);
            int n = this.char.indexOf(s2);
            if (index2 > 0 && n > index2) {
                final int n2 = index2;
                final int n3 = n;
                final int index3 = this.char.indexOf(s, n2 + 1);
                n = this.char.indexOf(s2, n3 + 1);
                if (index3 > 0 && n > index3) {
                    final int n4 = index3;
                    final int n5 = n;
                    final int index4 = this.char.indexOf(s, n4 + 1);
                    n = this.char.indexOf(s2, n5 + 1);
                    if (index4 <= 0 || n > index4) {}
                }
            }
            final String substring = this.char.substring(index + 1, n);
            int numericValue = 1;
            int n6 = n + 1;
            if (n6 < this.char.length() && Character.isDigit(this.char.charAt(n6))) {
                numericValue = Character.getNumericValue(this.char.charAt(n6));
                while (Character.isDigit(this.char.charAt(n6)) && n6 + 1 < this.char.length()) {
                    ++n6;
                    if (Character.isDigit(this.char.charAt(n6))) {
                        numericValue = numericValue * 10 + Character.getNumericValue(this.char.charAt(n6));
                    }
                }
            }
            String string = "";
            for (int i = 0; i < numericValue; ++i) {
                string += substring;
            }
            String substring2 = "";
            if (index > 0) {
                substring2 = this.char.substring(0, index);
            }
            String substring3 = "";
            if (n6 + 1 < this.char.length()) {
                substring3 = this.char.substring(n6);
            }
            this.char = substring2 + string + substring3;
            return false;
        }
        return true;
    }
    
    void try() {
        if (this.char == null) {
            this.for();
            this.setupMolForm();
        }
        this.do();
        final int[] array = new int[magnus.a.if.length];
        int i = 0;
        while (i < this.char.length()) {
            if (Character.isUpperCase(this.char.charAt(i))) {
                String s;
                if (++i < this.char.length()) {
                    if (Character.isLowerCase(this.char.charAt(i))) {
                        s = String.valueOf(this.char.charAt(i - 1)) + String.valueOf(this.char.charAt(i));
                        ++i;
                    }
                    else {
                        s = String.valueOf(this.char.charAt(i - 1));
                    }
                }
                else {
                    s = String.valueOf(this.char.charAt(i - 1));
                }
                int n = 0;
                for (int j = 0; j < magnus.a.if.length; ++j) {
                    if (s.equals(magnus.a.if[j])) {
                        n = j;
                    }
                }
                int numericValue = 1;
                if (i < this.char.length() && Character.isDigit(this.char.charAt(i))) {
                    numericValue = Character.getNumericValue(this.char.charAt(i));
                    while (Character.isDigit(this.char.charAt(i)) && i + 1 < this.char.length()) {
                        ++i;
                        if (Character.isDigit(this.char.charAt(i))) {
                            numericValue = numericValue * 10 + Character.getNumericValue(this.char.charAt(i));
                        }
                    }
                }
                array[n] += numericValue;
            }
            else {
                ++i;
            }
        }
        this.f = 0;
        for (int k = 0; k < magnus.a.if.length; ++k) {
            if (array[k] > 0) {
                ++this.f;
            }
        }
        this.for = new int[this.f + 1];
        this.try = new int[this.f + 1];
        int n2 = 1;
        for (int l = 1; l < magnus.a.if.length; ++l) {
            if (array[l] > 0) {
                this.for[n2] = l;
                this.try[n2] = array[l];
                ++n2;
            }
        }
    }
    
    public double molWeight(final String s) {
        double n = 0.0;
        boolean b = true;
        for (int i = 1; i <= this.f; ++i) {
            final double a = magnus.a.a(this.for[i], s);
            if (a < 0.0) {
                b = false;
            }
            n += a * this.try[i];
        }
        if (b) {
            return n;
        }
        return -1.0;
    }
    
    int if() {
        int n = 0;
        int n2 = 0;
        for (int i = 1; i <= this.f; ++i) {
            n += this.try[i] * (int)(0.5 + magnus.a.a(this.for[i], "Minimum"));
            n2 += this.try[i] * (int)(0.5 + magnus.a.a(this.for[i], "Maximum"));
        }
        return n2 - n + 1;
    }
    
    int a(final double[] array, final int n) {
        final double[] array2 = new double[n];
        boolean b = true;
        array[0] = 1.0;
        int n2 = 1;
        int n3 = 0;
        int n4 = 0;
        for (int i = 1; i <= this.f; ++i) {
            final int n5 = (int)(0.5 + magnus.a.a(this.for[i], "Minimum"));
            final int n6 = (int)(0.5 + magnus.a.a(this.for[i], "Maximum"));
            n3 += this.try[i] * n5;
            n4 += this.try[i] * n6;
            final int n7 = n6 - n5 + 1;
            final double[] array3 = new double[n7];
            for (int j = 0; j < n7; ++j) {
                array3[j] = 0.0;
            }
            for (int k = 0; k < magnus.a.do[this.for[i]].length; ++k) {
                array3[(int)(0.5 + magnus.a.do[this.for[i]][k]) - n5] = magnus.a.try[this.for[i]][k];
            }
            for (int l = 0; l < this.try[i]; ++l) {
                for (int n8 = 0; n8 < n2; ++n8) {
                    for (int n9 = 0; n9 < n7; ++n9) {
                        if (b) {
                            final double[] array4 = array2;
                            final int n10 = n8 + n9;
                            array4[n10] += array[n8] * array3[n9];
                        }
                        else {
                            final int n11 = n8 + n9;
                            array[n11] += array2[n8] * array3[n9];
                        }
                    }
                }
                b = !b;
                n2 += n7 - 1;
                for (int n12 = 0; n12 < n2; ++n12) {
                    if (b) {
                        array2[n12] = 0.0;
                    }
                    else {
                        array[n12] = 0.0;
                    }
                }
            }
        }
        if (!b) {
            System.arraycopy(array2, 0, array, 0, n);
        }
        return n3;
    }
    
    void char() {
        System.out.println("Molecule Dump");
        System.out.println("Number of atoms: " + this.long);
        for (int i = 0; i < this.long; ++i) {
            final String string = "      " + i + "  ";
            final String string2 = "    " + string.substring(string.length() - 6) + this.byte[i].else + " : ";
            String s = string2.substring(string2.length() - 10) + this.byte[i].new + " : ";
            for (int j = 0; j < this.byte[i].new; ++j) {
                s = s + this.byte[i].if[j] + "  " + this.byte[i].a[j] + " ; ";
            }
            System.out.println(s + this.byte[i].char + "  " + this.byte[i].case + "  " + this.byte[i].byte);
        }
        System.out.println("Molecule Dump Done");
    }
    
    void a(final int n, final int n2, final boolean[] array) {
        int i = -1;
        int n3 = 0;
        final int[] array2 = new int[this.long + 1];
        array2[0] = n2;
        for (int j = 0; j < this.long; ++j) {
            array[j] = false;
        }
        array[n2] = true;
        while (i < n3) {
            ++i;
            for (int k = 0; k < this.byte[array2[i]].new; ++k) {
                int n4 = this.byte[array2[i]].if[k];
                if (n4 == n) {
                    if (i >= 2) {
                        System.out.println("***Ring error***");
                    }
                    else {
                        n4 = 0;
                    }
                }
                else if (array[n4]) {
                    n4 = 0;
                }
                if (n4 > 0) {
                    ++n3;
                    array[array2[n3] = n4] = true;
                }
            }
        }
    }
    
    e a(final int n, final int n2, final double n3, final double long1) {
        final e e = new e();
        final e e2 = new e();
        final e e3 = new e();
        final e e4 = new e();
        magnus.e.a(e, e2, this.byte[n].char, this.byte[n].case, this.byte[n].byte, this.byte[n2].char, this.byte[n2].case, this.byte[n2].byte);
        e3.a = n3;
        e3.long = long1;
        e3.char = -long1;
        e3.case = n3;
        e4.a(e);
        e4.a(e3);
        e4.a(e2);
        return e4;
    }
    
    void a(final e e, final boolean[] array) {
        for (int i = 0; i < this.long; ++i) {
            if (array[i]) {
                final double char1 = this.byte[i].char;
                final double case1 = this.byte[i].case;
                final double byte1 = this.byte[i].byte;
                this.byte[i].char = char1 * e.a + case1 * e.long + byte1 * e.goto + e.new;
                this.byte[i].case = char1 * e.char + case1 * e.case + byte1 * e.byte + e.if;
                this.byte[i].byte = char1 * e.int + case1 * e.for + byte1 * e.do + e.else;
            }
        }
    }
    
    void a(final String s, final int n) {
        final int[] array = { 1, 0, 0, 0 };
        final int[] array2 = { 1, 1, 1, 1 };
        String s2 = "";
        for (int i = 12; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                s2 += s.substring(i, i + 1);
            }
        }
        System.out.println(s2);
        double n2 = 0.0;
        int n3 = 0;
        int n4 = 1;
        int long1 = 1;
        boolean b = false;
        this.long = 3 * s2.length();
        final c[] array3 = new c[this.long];
        int n5;
        if (s2.lastIndexOf("z") == s2.length() - 1) {
            n5 = 15;
        }
        else {
            n5 = 3;
        }
        final boolean b2 = true;
        double n6 = 0.0;
        double n7 = -0.25;
        final double n8 = 0.0;
        array3[0] = new c(n6, n7, n8, n5, b2 ? 1 : 0, array, array2);
        array2[1] = 1;
        while (s2.length() > 0) {
            ++n2;
            n5 = 3;
            b = !b;
            if (b) {
                n7 = 0.25;
            }
            else {
                n7 = -0.25;
            }
            n6 = -n2 * 0.866;
            if ("ijkq".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                final int n9 = 4;
                array[0] = n3;
                array2[0] = n4;
                array[1] = long1 + 3;
                array[2] = long1 + (array2[1] = 1);
                if (b && "j".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 5;
                }
                else if (b && "i".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 7;
                }
                else if (!b && "j".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 7;
                }
                else if (!b && "i".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 5;
                }
                else {
                    array2[2] = 1;
                }
                array[3] = long1 + 2;
                array2[3] = 12 - array2[2];
                if (array2[3] == 11) {
                    array2[3] = 1;
                }
                array3[long1] = new c(n6, n7, n8, n5, n9, array, array2);
                n3 = long1;
                n4 = array2[1];
                final boolean b3 = true;
                array2[0] = array2[2];
                array[0] = n3;
                ++long1;
                if ("ij".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    n5 = 15;
                }
                if (b) {
                    n7 = 1.116;
                }
                else {
                    n7 = -1.116;
                }
                final double n10 = n6 - 0.5;
                array3[long1] = new c(n10, n7, n8, n5, b3 ? 1 : 0, array, array2);
                ++long1;
                n5 = 3;
                n6 = n10 + 1.0;
                array2[0] = array2[3];
                array3[long1] = new c(n6, n7, n8, n5, b3 ? 1 : 0, array, array2);
                ++long1;
            }
            else if ("grs".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                final int n11 = 2;
                array[0] = n3;
                array2[0] = n4;
                array[1] = long1 + 1;
                if (s2.charAt(s2.length() - 1) == 'g' || n4 == 2) {
                    array2[1] = 1;
                }
                else {
                    array2[1] = 2;
                }
                array3[long1] = new c(n6, n7, n8, n5, n11, array, array2);
                n3 = long1;
                n4 = array2[1];
                ++long1;
            }
            else {
                final int n12 = 3;
                array[0] = n3;
                array2[0] = n4;
                array[1] = long1 + 2;
                if ("t".indexOf(s2.charAt(s2.length() - 1)) >= 0 && n4 != 2) {
                    array2[1] = 2;
                }
                else {
                    array2[1] = 1;
                }
                array[2] = long1 + 1;
                if ("hmz".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 2;
                }
                else if (b && "beo".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 5;
                }
                else if (b && "adn".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 7;
                }
                else if (!b && "beo".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 7;
                }
                else if (!b && "adn".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    array2[2] = 5;
                }
                else {
                    array2[2] = 1;
                }
                array3[long1] = new c(n6, n7, n8, n5, n12, array, array2);
                n3 = long1;
                n4 = array2[1];
                final boolean b4 = true;
                array2[0] = array2[2];
                array[0] = n3;
                ++long1;
                if ("defhnopz".indexOf(s2.charAt(s2.length() - 1)) >= 0) {
                    n5 = 15;
                }
                if (b) {
                    n7 = 1.25;
                }
                else {
                    n7 = -1.25;
                }
                array3[long1] = new c(n6, n7, n8, n5, b4 ? 1 : 0, array, array2);
                ++long1;
            }
            s2 = s2.substring(0, s2.length() - 1);
        }
        final boolean b5 = true;
        array[0] = n3;
        array3[long1] = new c(n6, n7, n8, n5, b5 ? 1 : 0, array, array2);
        ++long1;
        this.long = long1;
        this.byte = new c[this.long];
        for (int j = 0; j < this.long; ++j) {
            this.byte[j] = new c(array3[j]);
        }
        this.int();
    }
    
    public String getMolFormula() {
        return this.char;
    }
}
