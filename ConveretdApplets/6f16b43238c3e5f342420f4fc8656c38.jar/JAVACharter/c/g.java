// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Color;
import JAVACharter.StyleManage.a;
import JAVACharter.util.b;
import java.awt.Graphics;
import java.util.GregorianCalendar;
import JAVACharter.a.c;
import java.awt.FontMetrics;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import JAVACharter.b.f;
import java.awt.Rectangle;

public class g
{
    private Rectangle new;
    private Rectangle do;
    f b;
    private int f;
    private Date goto;
    private SimpleDateFormat void;
    private int byte;
    private static int long;
    private boolean try;
    private String null;
    private String c;
    private int int;
    private String e;
    int else;
    int a;
    private Font case;
    private FontMetrics for;
    private boolean if;
    private c char;
    private e d;
    
    public g(final Rectangle new1, final Rectangle do1, final Font case1, final FontMetrics for1, final c char1, final e d) {
        this.goto = null;
        this.void = new SimpleDateFormat();
        this.byte = 0;
        this.try = false;
        this.int = 0;
        this.e = " ";
        this.a = 1;
        this.case = null;
        this.if = false;
        this.new = new1;
        this.do = do1;
        this.case = case1;
        this.for = for1;
        this.char = char1;
        this.d = d;
    }
    
    public void a(final Date date, final Date date2, final JAVACharter.b.e e) {
        this.b = (f)e;
        this.f = this.b.a();
        final int n = (int)((date2.getTime() - date.getTime()) / 1000L / 60L);
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        final int goto1 = this.d.j.goto();
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int n5 = -1;
        for (int i = 0; i < this.f; ++i) {
            gregorianCalendar.setTime(this.b.do(i));
            final int value = gregorianCalendar.get(6);
            if (n5 != value) {
                n5 = value;
                ++n2;
            }
        }
        switch (goto1) {
            case 9: {
                n3 = 1440;
                n4 = 1;
                break;
            }
            case 6: {
                n3 = 288;
                n4 = 5;
                break;
            }
            case 7: {
                n3 = 96;
                n4 = 15;
                break;
            }
            case 8: {
                n3 = 24;
                n4 = 60;
                break;
            }
        }
        final int n6 = this.f * n4;
        if (n3 != 0) {
            final double n7 = n2 * n3;
            if (n7 >= this.f && this.f / n7 > 0.85) {
                this.try = true;
            }
            if (this.f > n7 && n7 / this.f > 0.85) {
                this.try = true;
            }
        }
        else {
            this.try = false;
        }
        if (this.try) {
            if (n6 < 130) {
                this.else = 10;
                this.null = "h";
                this.c = "mm";
            }
            else if (n6 < 300) {
                this.else = 9;
                this.null = "h";
                this.c = "mm";
            }
            else if (n6 < 2000) {
                this.else = 16;
                this.null = "E";
                this.c = "h";
            }
            else if (n6 < 4000) {
                this.else = 15;
                this.null = "E";
                this.c = "h";
                this.a = 10;
            }
            else if (n6 < 10000) {
                this.else = 13;
                this.null = "E";
                this.c = "h";
            }
            else if (n6 < 18000) {
                this.else = 14;
                this.null = "E";
                this.c = "h";
            }
        }
        else if (n < 130) {
            this.else = 10;
            this.null = "h";
            this.c = "mm";
        }
        else if (n < 300) {
            this.else = 9;
            this.null = "h";
            this.c = "mm";
        }
        else if (n < 2000) {
            if (this.char.for()) {
                this.else = 2;
                this.null = "MMM";
                this.c = "d";
                this.if = true;
            }
            else {
                this.else = 8;
                this.null = "E";
                this.c = "h";
            }
        }
        else if (n < 3000) {
            if (this.char.for()) {
                this.else = 2;
                this.null = "MMM";
                this.c = "d";
                this.if = true;
            }
            else {
                this.else = 7;
                this.null = "E";
                this.c = "h";
            }
        }
        else if (n < 7080) {
            if (this.char.for()) {
                this.else = 2;
                this.null = "MMM";
                this.c = "d";
                this.if = true;
            }
            else {
                this.else = 6;
                this.null = "E";
                this.c = "h";
                this.a = 10;
            }
        }
        else if (n < 18000) {
            if (this.char.for()) {
                this.else = 2;
                this.null = "MMM";
                this.c = "d";
                this.if = true;
            }
            else {
                this.else = 5;
                this.null = "E";
                this.c = "h";
            }
        }
        else if (n < 25000) {
            if (this.char.for()) {
                this.else = 2;
                this.null = "MMM";
                this.c = "d";
                this.if = true;
            }
            else if (this.do.width < 460) {
                this.else = 12;
                this.null = "E";
                this.c = "h";
            }
            else {
                this.else = 5;
                this.null = "E";
                this.c = "h";
            }
        }
        else if (n < 57600) {
            this.else = 2;
            this.null = "MMM";
            this.c = "d";
            this.if = true;
        }
        else if (n < 128160) {
            this.else = 2;
            this.null = "MMM";
            this.c = "d";
            this.if = false;
        }
        else if (n < 576000) {
            this.else = 3;
            this.null = "yy";
            this.c = "dd MMM yyyy";
        }
        else if (n < 1152000) {
            this.else = 11;
            this.null = "yy";
            this.c = "MMM";
        }
        else {
            this.else = 4;
            this.null = "yy";
            this.c = "yy";
        }
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        graphics.setFont(this.case);
        new SimpleDateFormat().applyPattern(this.null);
        new SimpleDateFormat().applyPattern(this.c);
        final Date do1 = this.b.do(-1);
        if (do1 != null) {
            gregorianCalendar.setTime(do1);
        }
        final a do2 = this.d.do();
        final Color byte1 = do2.byte;
        final Color g = do2.g;
        boolean b = false;
        if (do2.a()) {
            b = true;
        }
        int n3 = -1;
        int n4 = -1;
        int value = -1;
        int value2 = -1;
        int value3 = -1;
        int value4 = -1;
        if (do1 != null) {
            final int value5 = gregorianCalendar.get(12);
            n4 = value5 / 30;
            n3 = value5 / 15;
            value = gregorianCalendar.get(11);
            value2 = gregorianCalendar.get(5);
            value3 = gregorianCalendar.get(2);
            value4 = gregorianCalendar.get(1);
        }
        int n5 = 0;
        int n6 = 1;
        final Font font = new Font("bolder", 1, 11);
        for (int i = 0; i < this.f; ++i) {
            final int a = this.a(i);
            final int y = this.do.y;
            final Date do3 = this.b.do(i);
            gregorianCalendar.setTime(do3);
            switch (this.else) {
                case 2: {
                    final int value6 = gregorianCalendar.get(2);
                    if (value6 != value3) {
                        value3 = value6;
                        this.a(graphics, byte1, g, JAVACharter.util.b.a(value6, 3), a, y, b);
                        if (!this.if) {
                            n5 = 1;
                            break;
                        }
                        break;
                    }
                    else {
                        if (n6 != 0 && n5 == 0) {
                            if (this.if) {
                                n6 = 1;
                            }
                            else {
                                n6 = 0;
                            }
                            this.a(graphics, byte1, g, String.valueOf(do3.getDate()), a, y, b);
                            break;
                        }
                        n5 = 0;
                        n6 = 1;
                        break;
                    }
                    break;
                }
                case 3: {
                    final int value7 = gregorianCalendar.get(2);
                    final int value8 = gregorianCalendar.get(1);
                    if (value7 != value3 || value8 != value4) {
                        value4 = value8;
                        String s;
                        if ((value3 = value7) == 0) {
                            final int n7 = value8 - value8 / 100 * 100;
                            if (n7 < 10) {
                                s = "0" + String.valueOf(n7);
                            }
                            else {
                                s = String.valueOf(n7);
                            }
                        }
                        else {
                            s = JAVACharter.util.b.a(value7, 3);
                        }
                        this.a(graphics, byte1, g, s, a, y, b);
                        break;
                    }
                    break;
                }
                case 4: {
                    final int value9 = gregorianCalendar.get(1);
                    if (value9 != value4) {
                        value4 = value9;
                        final int n8 = value9 - value9 / 100 * 100;
                        String s2;
                        if (n8 < 10) {
                            s2 = "0" + String.valueOf(n8);
                        }
                        else {
                            s2 = String.valueOf(n8);
                        }
                        this.a(graphics, byte1, g, s2, a, y, b);
                        break;
                    }
                    break;
                }
                case 5: {
                    final int value10 = gregorianCalendar.get(5);
                    final int value11 = gregorianCalendar.get(11);
                    if (value10 != value2) {
                        value2 = value10;
                        value = value11;
                        final String if1 = JAVACharter.util.b.if(do3.getDay(), 1);
                        if1.substring(0, 1);
                        graphics.setFont(font);
                        this.a(graphics, byte1, g, if1, a, y, b);
                        graphics.setFont(this.case);
                        break;
                    }
                    if ((value11 == 11 || value11 == 13 || value11 == 15) && value11 != value) {
                        value = value11;
                        int hours = do3.getHours();
                        if (hours > 12) {
                            hours -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours), a, y, b);
                        break;
                    }
                    break;
                }
                case 6: {
                    final int value12 = gregorianCalendar.get(5);
                    final int value13 = gregorianCalendar.get(11);
                    if (value12 != value2) {
                        value2 = value12;
                        value = value13;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if ((value13 == 11 || value13 == 13 || value13 == 15) && value13 != value) {
                        value = value13;
                        int hours2 = do3.getHours();
                        if (hours2 > 12) {
                            hours2 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours2), a, y, b);
                        break;
                    }
                    break;
                }
                case 7: {
                    final int value14 = gregorianCalendar.get(5);
                    final int value15 = gregorianCalendar.get(11);
                    if (value14 != value2) {
                        value2 = value14;
                        value = value15;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if (value15 != value && (this.try || (value15 != 16 && value15 != 9))) {
                        value = value15;
                        int hours3 = do3.getHours();
                        if (hours3 > 12) {
                            hours3 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours3), a, y, b);
                        break;
                    }
                    break;
                }
                case 8: {
                    final int value16 = gregorianCalendar.get(5);
                    final int value17 = gregorianCalendar.get(11);
                    if (value16 != value2) {
                        value2 = value16;
                        value = value17;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if (value17 != value && value17 != 16 && value17 != 9) {
                        value = value17;
                        int hours4 = do3.getHours();
                        if (hours4 > 12) {
                            hours4 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours4), a, y, b);
                        break;
                    }
                    value = value17;
                    break;
                }
                case 9: {
                    final int value18 = gregorianCalendar.get(11);
                    final int n9 = gregorianCalendar.get(12) / 30;
                    if (value18 != value) {
                        value = value18;
                        n4 = n9;
                        int hours5 = do3.getHours();
                        if (hours5 > 12) {
                            hours5 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours5), a, y, b);
                        break;
                    }
                    if (n9 != n4) {
                        n4 = n9;
                        int hours6 = do3.getHours();
                        if (hours6 > 12) {
                            hours6 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours6) + ":" + String.valueOf(do3.getMinutes()), a, y, b);
                        break;
                    }
                    break;
                }
                case 10: {
                    final int value19 = gregorianCalendar.get(11);
                    final int n10 = gregorianCalendar.get(12) / 15;
                    if (value19 != value) {
                        value = value19;
                        n3 = n10;
                        int hours7 = do3.getHours();
                        if (hours7 > 12) {
                            hours7 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours7), a, y, b);
                        break;
                    }
                    if (n10 != n3) {
                        n3 = n10;
                        int hours8 = do3.getHours();
                        if (hours8 > 12) {
                            hours8 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours8) + ":" + String.valueOf(do3.getMinutes()), a, y, b);
                        break;
                    }
                    break;
                }
                case 11: {
                    final int value20 = gregorianCalendar.get(2);
                    final int value21 = gregorianCalendar.get(1);
                    if (value20 != value3 || value21 != value4) {
                        value3 = value20;
                        value4 = value21;
                        String s3;
                        if (value20 == 0) {
                            final int n11 = value21 - value21 / 100 * 100;
                            if (n11 < 10) {
                                s3 = "0" + String.valueOf(n11);
                            }
                            else {
                                s3 = String.valueOf(n11);
                            }
                        }
                        else {
                            s3 = JAVACharter.util.b.a(value20, 1);
                        }
                        this.a(graphics, byte1, g, s3, a, y, b);
                        break;
                    }
                    break;
                }
                case 12: {
                    final int value22 = gregorianCalendar.get(5);
                    if (value22 != value2) {
                        value2 = value22;
                        final String if2 = JAVACharter.util.b.if(do3.getDay(), 1);
                        if2.substring(0, 1);
                        graphics.setFont(font);
                        this.a(graphics, byte1, g, if2, a, y, b);
                        graphics.setFont(this.case);
                        break;
                    }
                    break;
                }
                case 13: {
                    final int value23 = gregorianCalendar.get(5);
                    final int value24 = gregorianCalendar.get(11);
                    if (value23 != value2) {
                        value2 = value23;
                        value = value24;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if ((value24 == 4 || value24 == 8 || value24 == 12 || value24 == 16 || value24 == 20) && value24 != value) {
                        value = value24;
                        int hours9 = do3.getHours();
                        if (hours9 > 12) {
                            hours9 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours9), a, y, b);
                        break;
                    }
                    break;
                }
                case 14: {
                    final int value25 = gregorianCalendar.get(5);
                    final int value26 = gregorianCalendar.get(11);
                    if (value25 != value2) {
                        value2 = value25;
                        value = value26;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if (value26 == 12 && value26 != value) {
                        value = value26;
                        int hours10 = do3.getHours();
                        if (hours10 > 12) {
                            hours10 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours10), a, y, b);
                        break;
                    }
                    break;
                }
                case 15: {
                    final int value27 = gregorianCalendar.get(5);
                    final int value28 = gregorianCalendar.get(11);
                    if (value27 != value2) {
                        value2 = value27;
                        value = value28;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if (value28 % 2 == 1 && value28 != value) {
                        value = value28;
                        int hours11 = do3.getHours();
                        if (hours11 > 12) {
                            hours11 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours11), a, y, b);
                        break;
                    }
                    break;
                }
                case 16: {
                    final int value29 = gregorianCalendar.get(5);
                    final int value30 = gregorianCalendar.get(11);
                    if (value29 != value2) {
                        value2 = value29;
                        value = value30;
                        this.a(graphics, byte1, g, JAVACharter.util.b.if(do3.getDay(), 1), a, y, b);
                        break;
                    }
                    if (value30 != value) {
                        value = value30;
                        int hours12 = do3.getHours();
                        if (hours12 > 12) {
                            hours12 -= 12;
                        }
                        this.a(graphics, byte1, g, String.valueOf(hours12), a, y, b);
                        break;
                    }
                    value = value30;
                    break;
                }
            }
        }
    }
    
    public void a(final Graphics graphics, final Color color, final Color color2, final String s, final int n, final int n2, final boolean b) {
        graphics.setColor(color);
        graphics.drawLine(n, this.new.y + this.new.height, n, this.new.y);
        graphics.setColor(color2);
        if (b) {
            graphics.drawString(s, n - this.for.stringWidth(s) / 2, n2);
        }
    }
    
    public int a(final int n) {
        return this.new.x + this.new.width / (this.f + 1) * (n + 1);
    }
    
    public int if(final int n) {
        final float n2 = (n - this.new.x) / (this.new.width / (this.f + 1)) - 1.0f;
        int n3;
        if (n2 % 1.0f >= 0.5) {
            n3 = (int)n2 + 1;
        }
        else {
            n3 = (int)n2;
        }
        if (n3 > this.f) {
            n3 = this.f - 1;
        }
        return n3;
    }
    
    static {
        g.long = 0;
    }
}
