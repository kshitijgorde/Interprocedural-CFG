// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Font;
import JAVACharter.StyleManage.a;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.awt.Rectangle;

public class j
{
    private Rectangle void;
    private Rectangle d;
    private DecimalFormat case;
    private float do;
    private float new;
    private float null;
    private float for;
    private float byte;
    private boolean goto;
    private boolean char;
    private boolean try;
    private float if;
    private float e;
    private float int;
    private float long;
    private float a;
    private int else;
    public static final int c = 0;
    public static final int b = 1;
    
    public j(final Rectangle d, final Rectangle void1) {
        this.goto = false;
        this.char = false;
        this.try = true;
        this.if = 9.223372E18f;
        this.e = 9.223372E18f;
        this.int = 9.223372E18f;
        this.long = 9.223372E18f;
        this.a = 1.0f;
        this.else = 0;
        this.void = void1;
        this.d = d;
        this.case = new DecimalFormat("##############.####");
    }
    
    public void a(final String s) {
        this.case = new DecimalFormat(s);
    }
    
    public DecimalFormat new() {
        return this.case;
    }
    
    public void a(final boolean goto1) {
        this.goto = goto1;
    }
    
    public boolean int() {
        return this.goto;
    }
    
    public void if(final boolean char1) {
        this.char = char1;
        if (this.char) {
            this.else = 1;
        }
        else {
            this.else = 0;
        }
    }
    
    public boolean try() {
        return this.char;
    }
    
    public void a(float n, float n2) {
        if (n == n2 && n > 0.0f) {
            n2 = 0.0f;
        }
        else if (n == n2 && n < 0.0f) {
            n = 0.0f;
        }
        if (this.if == 9.223372E18f) {
            this.if = n;
        }
        else if (n > this.if) {
            this.if = n;
        }
        if (this.e == 9.223372E18f) {
            this.e = n2;
        }
        else if (n2 < this.e) {
            this.e = n2;
        }
        if (this.if != 9.223372E18f && this.e != 9.223372E18f) {
            this.if((this.if - this.a) / this.a * 100.0f, (this.e - this.a) / this.a * 100.0f);
        }
    }
    
    public void if(float n, float n2) {
        if (n == n2 && n > 0.0f) {
            n2 = 0.0f;
        }
        else if (n == n2 && n < 0.0f) {
            n = 0.0f;
        }
        if (this.int == 9.223372E18f) {
            this.int = n;
        }
        else if (n > this.int) {
            this.int = n;
        }
        if (this.long == 9.223372E18f) {
            this.long = n2;
        }
        else if (n2 < this.long) {
            this.long = n2;
        }
        if (this.int != 9.223372E18f && this.long != 9.223372E18f) {
            final float if1 = this.int / 100.0f * this.a + this.a;
            final float e = this.long / 100.0f * this.a + this.a;
            if (if1 > this.if) {
                this.if = if1;
            }
            if (e < this.e) {
                this.e = e;
            }
        }
    }
    
    public void if(final float a) {
        this.a = a;
    }
    
    public void if() {
        this.if = 9.223372E18f;
        this.e = 9.223372E18f;
        this.int = 9.223372E18f;
        this.long = 9.223372E18f;
    }
    
    public void if(final int else1) {
        this.else = else1;
        this.do();
    }
    
    public int do(final float n) {
        int n2;
        if (this.goto) {
            n2 = this.d.height + this.d.y - this.a(this.byte, this.new, n, this.d.height);
        }
        else {
            n2 = this.d.height + this.d.y - (int)(this.for * (n - this.byte));
        }
        return n2;
    }
    
    public int a(final float n, final float n2, final float n3, final float n4) {
        int n5;
        if (this.goto) {
            n5 = this.d.height + this.d.y - this.a(n2, n3, n, this.d.height);
        }
        else {
            n5 = this.d.height + this.d.y - (int)(n4 * (n - n2));
        }
        return n5;
    }
    
    public float a(final int n) {
        final float n2 = this.d.height + this.d.y - n;
        float a;
        if (this.goto) {
            a = this.a((int)n2, this.d.height);
        }
        else {
            a = n2 / this.for + this.byte;
        }
        return a;
    }
    
    public float a(final int n, final int n2) {
        if (n2 > 0) {
            final float a = this.a(this.do, (double)this.new, n2);
            final float a2 = this.a(this.do, this.new, n2);
            return (float)Math.pow(2.718281828459045, (a - a2) * n / n2 + a2);
        }
        return -4.6f;
    }
    
    public float a(float n, final float n2, final int n3) {
        float n4 = -4.6f;
        if (n >= 0.0f) {
            if (n > 0.0) {
                n4 = (float)Math.log(n);
            }
            return n4;
        }
        n = -n;
        if (n > 0.0f) {
            n4 = (float)Math.log(n);
        }
        return n4;
    }
    
    public float a(double n, final double n2, final int n3) {
        float n4 = -4.6f;
        float n5 = -4.6f;
        if (n2 > 0.0) {
            return (float)Math.log(n2);
        }
        if (n2 > 0.0) {
            n4 = (float)Math.log(n2);
        }
        n = -n;
        if (n > 0.0) {
            n5 = (float)Math.log(n);
        }
        if (n4 + n5 != 0.0f) {
            return n4;
        }
        return -4.6f;
    }
    
    public int a(float n, final float n2, float n3, final int n4) {
        float n5 = -4.6f;
        float n6 = -4.6f;
        if (n <= 0.0f) {
            n = 0.1f;
        }
        if (n3 <= 0.0f) {
            n3 = 0.1f;
        }
        if (n3 < n) {
            n3 = n;
        }
        if (n3 > n2) {
            n3 = n2;
        }
        if (n3 == n || n == n2) {
            return 0;
        }
        if (n2 > 0.0 && n >= 0.0) {
            final float n7 = (float)Math.log(n2);
            if (n > 0.0) {
                n6 = (float)Math.log(n);
            }
            if (n7 != 0.0) {
                return (int)(n4 * ((float)Math.log(n3) - n6) / (n7 - n6));
            }
        }
        else if (n2 > 0.0) {
            if (n2 > 0.0) {
                n5 = (float)Math.log(n2);
            }
            n = -n;
            if (n > 0.0) {
                n6 = (float)Math.log(n);
            }
            if (n5 + n6 != 0.0) {
                if (n3 <= 0.0) {
                    double n8;
                    if (n3 == 0.0) {
                        n8 = n6;
                    }
                    else {
                        n8 = (float)Math.log(Math.abs(n3));
                    }
                    return (int)(n4 * n8 / (n5 + n6));
                }
                final double n9 = (float)Math.log(n3);
            }
        }
        return 0;
    }
    
    public int a(final long n) {
        return this.d.height + this.d.y - (int)(this.for * (n - this.byte));
    }
    
    public void a(final Graphics graphics, final a a) {
        final Font font = new Font("xFont", 0, 10);
        graphics.setFont(font);
        final int size = font.getSize();
        int n = this.d.y + this.d.height;
        if (this.char) {
            this.if(1);
        }
        else {
            this.if(0);
        }
        final float do1 = this.do;
        final int n2 = this.d.height + this.d.y;
        graphics.setColor(a.void);
        final int do2 = this.do(do1);
        String s = this.a(do1);
        if (this.char) {
            s = s.concat("%");
            if (do1 > 0.0f) {
                s = "+".concat(s);
            }
        }
        graphics.drawString(s, this.void.x, do2 + size / 2 - 2);
        float n3;
        if (this.goto) {
            n3 = do1 + (this.null - (int)do1);
        }
        else {
            n3 = do1 + this.null;
        }
        if (this.new != 9.223372E18f) {
            while (n3 <= this.new) {
                final int do3 = this.do(n3);
                if (n3 != this.new) {
                    String s2 = this.a(n3);
                    graphics.setColor(a.j);
                    graphics.drawLine(this.d.x, do3, this.d.x + this.d.width, do3);
                    graphics.setColor(a.void);
                    if (this.char) {
                        s2 = s2.concat("%");
                        if (n3 > 0.0f) {
                            s2 = "+".concat(s2);
                        }
                    }
                    if (n - (do3 + size / 2) >= 2) {
                        graphics.drawString(s2, this.void.x, do3 + size / 2);
                        n = do3 - size / 2;
                    }
                }
                n3 += this.null;
                if (n3 == this.new) {
                    final int do4 = this.do(n3);
                    String s3 = this.a(n3);
                    if (this.char) {
                        s3 = s3.concat("%");
                        if (n3 > 0.0f) {
                            s3 = "+".concat(s3);
                        }
                    }
                    graphics.drawString(s3, this.void.x, do4 + size / 2);
                }
            }
        }
    }
    
    public DecimalFormat do(final float n, final float n2) {
        final float n3 = this.null / n2 % 1.0f;
        if (n3 != 0.0f) {
            int n4 = 0;
            String string = "##############.";
            ++n4;
            for (float n5 = n3 * 10.0f; n5 < 1.0f; n5 *= 10.0f) {
                ++n4;
            }
            for (int i = 0; i < n4; ++i) {
                string += "#";
            }
            return new DecimalFormat(string);
        }
        return new DecimalFormat("##############");
    }
    
    public String a(final float n) {
        float n2;
        String s;
        if (n >= 0.0f) {
            if (n >= 1.0E12f) {
                n2 = 1.0E12f;
                s = " T";
            }
            else if (n >= 1.0E9f) {
                n2 = 1.0E9f;
                s = " B";
            }
            else if (n >= 1000000.0f) {
                n2 = 1000000.0f;
                s = " M";
            }
            else if (n >= 1000.0f) {
                n2 = 1000.0f;
                s = " K";
            }
            else {
                n2 = 1.0f;
                s = "";
            }
        }
        else if (n <= -1.0E12f) {
            n2 = 1.0E12f;
            s = " T";
        }
        else if (n <= -1.0E9f) {
            n2 = 1.0E9f;
            s = " B";
        }
        else if (n <= -1000000.0f) {
            n2 = 1000000.0f;
            s = " M";
        }
        else if (n <= -1000.0f) {
            n2 = 1000.0f;
            s = " K";
        }
        else {
            n2 = 1.0f;
            s = "";
        }
        String string;
        if (n == 0.0f) {
            string = "0";
        }
        else {
            this.case = this.do(n, n2);
            string = this.case.format(n / n2) + s;
        }
        return string;
    }
    
    public void do(final boolean b) {
        if (this.try) {
            this.byte = 0.0f;
            if (this.else == 0) {
                this.do = this.e;
                this.new = this.if;
            }
            else if (this.else == 1) {
                this.do = this.long;
                this.new = this.int;
            }
            if (this.do != 9.223372E18f && this.new != 9.223372E18f) {
                if (this.new == this.do) {
                    this.do = 0.0f;
                }
                this.new += 0.02f * (this.new - this.do);
                final float n = this.new - this.do;
                if (b) {
                    this.do = 0.0f;
                }
                this.for = 0.0f;
                this.null = n / (this.d.height / 15);
                long n2 = 0L;
                if (this.null < 1.0 && this.null != 0.0f) {
                    while (this.null < 1.0) {
                        this.null *= 10.0f;
                        --n2;
                    }
                }
                else if (this.null > 1.0) {
                    while (this.null > 1.0) {
                        this.null /= 10.0f;
                        ++n2;
                    }
                    this.null *= 10.0f;
                    --n2;
                }
                if (this.null >= 1.0f && this.null < 2.0f) {
                    this.null = 2.0f;
                }
                else if (this.null >= 2.0f && this.null < 5.0f) {
                    this.null = 5.0f;
                }
                else if (this.null >= 5.0f) {
                    this.null = 10.0f;
                }
                else {
                    this.null = 1.0f;
                }
                this.null *= (float)Math.pow(10.0, n2);
                if (this.null > 1.0f && (long)this.do % 2L != 0L) {
                    --this.do;
                }
                if (this.do % this.null != 0.0f && !b) {
                    if (this.do < 0.0f) {
                        this.do -= this.null - this.do * -1.0f % this.null;
                    }
                    else {
                        this.do -= this.do % this.null;
                    }
                }
                if (this.new % this.null != 0.0f) {
                    this.new += this.null - this.new % this.null;
                }
                if (b) {
                    this.do = 0.0f;
                }
                if (this.goto && this.e > 1.0f) {
                    this.do = (int)this.e;
                }
                if (this.else == 1) {
                    this.int = this.new;
                    this.long = this.do;
                    this.if = this.new / 100.0f * this.a + this.a;
                    this.e = this.do / 100.0f * this.a + this.a;
                }
                else if (this.else == 0) {
                    this.if = this.new;
                    this.e = this.do;
                    this.int = (this.new - this.a) / this.a * 100.0f;
                    this.long = (this.new - this.a) / this.a * 100.0f;
                }
                final float n3 = this.new - this.do;
                this.byte = this.do;
                this.for = this.d.height / n3;
            }
            else {
                this.byte = 9.223372E18f;
                this.for = 9.223372E18f;
                this.new = 9.223372E18f;
                this.do = 0.0f;
            }
        }
    }
    
    public void do() {
        if (this.else == 0) {
            final float n = this.if - this.e;
            this.byte = this.e;
            this.for = this.d.height / n;
        }
        else if (this.else == 1) {
            final float n2 = this.int - this.long;
            this.byte = this.long;
            this.for = this.d.height / n2;
        }
    }
    
    public float a() {
        if (this.else == 0) {
            return this.e;
        }
        return this.long;
    }
    
    public float for() {
        if (this.else == 0) {
            return this.if;
        }
        return this.int;
    }
}
