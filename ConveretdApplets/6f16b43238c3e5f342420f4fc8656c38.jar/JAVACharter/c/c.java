// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Rectangle;
import java.util.TimeZone;
import java.util.Date;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Component;
import JAVACharter.StyleManage.b;
import java.awt.LayoutManager;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Label;
import JAVACharter.StyleManage.StyleCache;
import JAVACharter.Charter;
import java.awt.Panel;

public class c extends Panel
{
    Charter w;
    f c;
    StyleCache j;
    e[] k;
    boolean null;
    boolean case;
    boolean z;
    int o;
    int D;
    int n;
    int b;
    int u;
    int h;
    int q;
    int g;
    int goto;
    int t;
    int int;
    Label char;
    Label v;
    Label byte;
    Label if;
    Label void;
    Label try;
    Label f;
    Label i;
    Label e;
    Label l;
    Color p;
    DecimalFormat for;
    SimpleDateFormat A;
    SimpleDateFormat d;
    Color else;
    Color B;
    Font r;
    Font do;
    Font new;
    Hashtable C;
    Hashtable s;
    Hashtable m;
    Vector a;
    Vector long;
    
    public c(final Charter w, final f c) {
        this.null = true;
        this.case = true;
        this.z = false;
        this.o = 496;
        this.D = 9;
        this.n = 60;
        this.b = 17;
        this.u = 16;
        this.h = 10;
        this.q = this.h - 1;
        this.g = 0;
        this.goto = 0;
        this.t = 30;
        this.int = 50;
        this.for = new DecimalFormat("###,###,###,###,###,###.###");
        this.A = new SimpleDateFormat("M/dd/yy");
        this.d = new SimpleDateFormat("M/dd/yy hh:mm:ss aaa");
        this.else = new Color(208, 208, 208);
        this.B = new Color(255, 255, 204);
        this.r = new Font("smallFont", 0, 11);
        this.do = new Font("boldFont", 1, 11);
        this.new = new Font("verySmallFont", 0, 9);
        this.C = new Hashtable();
        this.s = new Hashtable();
        this.m = new Hashtable();
        this.a = new Vector();
        this.long = new Vector();
        this.w = w;
        this.c = c;
        this.k = this.w.getChartArray();
        this.setLayout(null);
        this.setFont(new Font("xFont", 0, 11));
        this.setBackground(this.p = new Color(255, 255, 255));
        this.s.put("smallfont", this.r);
        this.s.put("boldfont", this.do);
        this.s.put("verysmallfont", this.new);
    }
    
    public void a(final int t, final int int1) {
        this.t = t;
        this.int = int1;
    }
    
    public int getWidth() {
        return this.t;
    }
    
    public int getHeight() {
        return this.int;
    }
    
    public void a(final String s, final String s2, final int n, final int n2, final int n3, final int n4, final Color background, final Color foreground, final String s3, final int n5, final int n6) {
        final b b = new b(s2, n6);
        b.setBounds(n, n2, n3, n4);
        b.setBackground(background);
        b.setForeground(foreground);
        if (s3 == null) {
            b.setFont(this.do);
        }
        else {
            final Font font = this.s.get(s3);
            if (font != null) {
                b.setFont(font);
            }
            else {
                b.setFont(this.do);
            }
        }
        b.a(n5);
        this.C.put(s, b);
    }
    
    public void a(final String s, final int n, final int n2) {
        this.s.put(s, new Font(s, n, n2));
    }
    
    public Font int(final String s) {
        final Font font = this.s.get(s);
        if (font != null) {
            return font;
        }
        return this.r;
    }
    
    public void a(final String s, final int n, final int n2, final int n3, final int n4, final Color background, final Color foreground, final String s2, final int n5, final int n6) {
        final b b = new b(s, n6);
        b.setBounds(n, n2, n3, n4);
        b.setBackground(background);
        b.setForeground(foreground);
        if (s2 == null) {
            b.setFont(this.r);
        }
        else {
            final Font font = this.s.get(s2);
            if (font != null) {
                b.setFont(font);
            }
            else {
                b.setFont(this.r);
            }
        }
        b.a(n5);
        this.a.addElement(b);
    }
    
    public void a(final int do1, final int int1, final int if1, final int for1, final Color a) {
        final a a2 = new a();
        a2.do = do1;
        a2.int = int1;
        a2.if = if1;
        a2.for = for1;
        a2.a = a;
        this.long.addElement(a2);
    }
    
    public void a(final String s, final Color color, final Font font, final int[] array, final int[] array2) {
        this.m.put(s, new JAVACharter.StyleManage.b(s, color, font, array, array2));
    }
    
    public JAVACharter.StyleManage.b new(final String s) {
        JAVACharter.StyleManage.b b = this.m.get(s);
        if (b == null) {
            final Font font = new Font("MarkerDefault", 0, 10);
            if (s.equals("uparrow")) {
                b = new JAVACharter.StyleManage.b("uparrow", new Color(0, 204, 0), font, new int[] { -6, 0, 6 }, new int[] { 5, -5, 5 });
                this.m.put(s, b);
            }
            else if (s.equals("downarrow")) {
                b = new JAVACharter.StyleManage.b("downarrow", Color.red, font, new int[] { -6, 0, 6 }, new int[] { -5, 5, -5 });
                this.m.put(s, b);
            }
            else if (s.equals("rollup")) {
                b = new JAVACharter.StyleManage.b("default", Color.blue, font, new int[] { -5, 5, 5, -5 }, new int[] { -5, -5, 5, 5 });
                this.m.put(s, b);
            }
            else {
                b = new JAVACharter.StyleManage.b("default", Color.blue, font, new int[] { -5, 5, 5, -5 }, new int[] { -5, -5, 5, 5 });
            }
        }
        return b;
    }
    
    public void a(final StyleCache j) {
        if (this.j == null) {
            this.j = j;
        }
        switch (this.j.int()) {
            case 0: {
                this.z = false;
                break;
            }
            case 1: {
                this.z = false;
                break;
            }
            case 2: {
                this.z = true;
                break;
            }
            default: {
                this.z = false;
                break;
            }
        }
        this.k = this.w.getChartArray();
        if (!this.z) {
            for (int i = 0; i < this.a.size(); ++i) {
                final b b = this.a.elementAt(i);
                final int a = b.a();
                if (a == -1) {
                    this.add(b);
                }
                else {
                    this.k[a].add(b);
                }
            }
            final Enumeration<String> keys = this.C.keys();
            while (keys.hasMoreElements()) {
                final b b2 = this.C.get(keys.nextElement());
                final int a2 = b2.a();
                if (a2 == -1) {
                    this.add(b2);
                }
                else {
                    this.k[a2].add(b2);
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.long.size(); ++i) {
            final a a = this.long.elementAt(i);
            graphics.setColor(a.a);
            graphics.drawLine(a.do, a.int, a.if, a.for);
        }
    }
    
    public void do(final String text) {
        if (this.try == null) {
            this.try = this.C.get("lowerindicval");
        }
        if (this.try != null) {
            this.try.setText(text);
        }
    }
    
    public void a(final String text) {
        if (this.f == null) {
            this.f = this.C.get("lowerindictitle");
        }
        if (this.f != null) {
            this.f.setText(text);
        }
    }
    
    public void a(final String s, final String text) {
        final Label label = this.C.get(s);
        if (label != null) {
            label.setText(text);
        }
    }
    
    public void a(final float n, final float n2, final float n3, final float n4, final Date date) {
        if (this.char == null) {
            this.char = this.C.get("date");
        }
        if (this.v == null) {
            this.v = this.C.get("open");
        }
        if (this.byte == null) {
            this.byte = this.C.get("high");
        }
        if (this.if == null) {
            this.if = this.C.get("low");
        }
        if (this.void == null) {
            this.void = this.C.get("close");
        }
        if (this.v != null) {
            if (n != 0.0f) {
                this.v.setText(this.for.format(n));
            }
            else {
                this.v.setText("--");
            }
        }
        if (this.byte != null) {
            if (n2 != 0.0f) {
                this.byte.setText(this.for.format(n2));
            }
            else {
                this.byte.setText("--");
            }
        }
        if (this.if != null) {
            if (n3 != 0.0f) {
                this.if.setText(this.for.format(n3));
            }
            else {
                this.if.setText("--");
            }
        }
        if (this.void != null) {
            if (n4 != 0.0f) {
                this.void.setText(this.for.format(n4));
            }
            else {
                this.void.setText("--");
            }
        }
        try {
            if (this.char != null) {
                if (date != null) {
                    if (date.getHours() != Integer.parseInt(new SimpleDateFormat("H").format(date))) {
                        System.out.println("correcting for MS JVM bug...");
                        System.out.println("original" + date);
                        final TimeZone default1 = TimeZone.getDefault();
                        final TimeZone timeZone = TimeZone.getTimeZone("PST");
                        long n5 = timeZone.getRawOffset();
                        long n6 = default1.getRawOffset();
                        if (timeZone.inDaylightTime(date)) {
                            n5 += 3600000L;
                        }
                        if (default1.inDaylightTime(date)) {
                            n6 += 3600000L;
                        }
                        Date date2;
                        if (n6 > 0L) {
                            date2 = new Date(date.getTime() + n6 + n5 * -1L);
                        }
                        else {
                            date2 = new Date(date.getTime() + (n6 - n5));
                        }
                        System.out.println("shifted: " + date2);
                        if (this.w.innerIsHistorical()) {
                            this.char.setText(this.A.format(date2));
                        }
                        else {
                            this.char.setText(this.d.format(date2));
                        }
                    }
                    else if (this.w.innerIsHistorical()) {
                        this.char.setText(this.A.format(date));
                    }
                    else {
                        this.char.setText(this.d.format(date));
                    }
                }
                else {
                    this.char.setText("--");
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.repaint();
    }
    
    public void a() {
        final Enumeration<String> keys = (Enumeration<String>)this.C.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final b b = this.C.get(s);
            if (s.indexOf("field") != -1) {
                b.setText("--");
            }
        }
        this.a(0.0f, 0.0f, 0.0f, 0.0f, null);
    }
    
    public void a(final Date date, final Date date2) {
        if (this.i == null) {
            this.i = this.C.get("lowerdate");
        }
        if (this.e == null) {
            this.e = this.C.get("upperdate");
        }
        try {
            final Date date3 = new Date();
            final boolean b = date3.getHours() != Integer.parseInt(new SimpleDateFormat("H").format(date3));
            if (this.i != null) {
                if (b) {
                    System.out.println("correcting for MS JVM bug...");
                    final Date a = JAVACharter.util.b.a(date);
                    System.out.println("original" + date + " Eastern:" + a);
                    final Date for1 = JAVACharter.util.b.for(a, 3);
                    System.out.println("shifted: " + for1);
                    this.i.setText(this.A.format(for1) + " to");
                }
                else {
                    this.i.setText(this.A.format(date) + " to");
                }
            }
            if (this.e != null) {
                if (b) {
                    System.out.println("correcting for MS JVM bug...");
                    final Date for2 = JAVACharter.util.b.for(JAVACharter.util.b.a(date2), 3);
                    System.out.println("original" + date2 + " Eastern:" + for2);
                    final Date for3 = JAVACharter.util.b.for(for2, 3);
                    System.out.println("shifted: " + for3);
                    this.e.setText(this.A.format(for3));
                }
                else {
                    this.e.setText(this.A.format(date2));
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final Rectangle rectangle, final e e) {
        if (this.z) {
            if (this.char == null) {
                this.char = this.C.get("date");
            }
            if (this.v == null) {
                this.v = this.C.get("open");
            }
            if (this.byte == null) {
                this.byte = this.C.get("high");
            }
            if (this.if == null) {
                this.if = this.C.get("low");
            }
            if (this.void == null) {
                this.void = this.C.get("close");
            }
            final int n3 = 20;
            final int n4 = 14;
            graphics.setColor(Color.white);
            if (e.n == 0) {
                this.t = 100;
                this.int = 72;
                final int n5 = (rectangle.y + rectangle.height) / 2 - this.int / 2;
                graphics.setXORMode(new Color(128, 128, 128));
                graphics.setColor(new Color(0, 0, 0));
                if (n < rectangle.x + rectangle.width - this.t - 5) {
                    graphics.fillRect(n + n3, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                    graphics.setPaintMode();
                    graphics.setColor(Color.black);
                    graphics.drawRect(n + n3, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                    graphics.setColor(Color.white);
                    if (this.char != null) {
                        graphics.drawString("Date: " + this.char.getText(), n + n3 + 2, n5 + n4 * 1);
                    }
                    if (this.v != null) {
                        graphics.drawString("Open: " + this.v.getText(), n + n3 + 2, n5 + n4 * 2);
                    }
                    if (this.byte != null) {
                        graphics.drawString("High: " + this.byte.getText(), n + n3 + 2, n5 + n4 * 3);
                    }
                    if (this.if != null) {
                        graphics.drawString("Low: " + this.if.getText(), n + n3 + 2, n5 + n4 * 4);
                    }
                    if (this.void != null) {
                        graphics.drawString("Close: " + this.void.getText(), n + n3 + 2, n5 + n4 * 5);
                    }
                }
                else {
                    final int n6 = n - this.t - n3;
                    final int n7 = n6 + 2;
                    graphics.fillRect(n - this.t - n3, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                    graphics.setPaintMode();
                    graphics.setColor(Color.black);
                    graphics.drawRect(n6, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                    graphics.setColor(Color.white);
                    if (this.char != null) {
                        graphics.drawString("Date: " + this.char.getText(), n7, n5 + n4 * 1);
                    }
                    if (this.v != null) {
                        graphics.drawString("Open: " + this.v.getText(), n7, n5 + n4 * 2);
                    }
                    if (this.byte != null) {
                        graphics.drawString("High: " + this.byte.getText(), n7, n5 + n4 * 3);
                    }
                    if (this.if != null) {
                        graphics.drawString("Low: " + this.if.getText(), n7, n5 + n4 * 4);
                    }
                    if (this.void != null) {
                        graphics.drawString("Close: " + this.void.getText(), n7, n5 + n4 * 5);
                    }
                }
            }
            else {
                this.t = 100;
                this.int = 14;
                final int n8 = (rectangle.y + rectangle.height) / 2 - this.int / 2;
                final String string = String.valueOf(e.n) + "label0";
                final String string2 = String.valueOf(e.n) + "field0";
                final b b = this.C.get(string);
                final b b2 = this.C.get(string2);
                if (b != null && b2 != null) {
                    graphics.setXORMode(new Color(128, 128, 128));
                    graphics.setColor(new Color(0, 0, 0));
                    if (n < rectangle.x + rectangle.width - this.t - 5) {
                        graphics.fillRect(n + n3, n8, this.t, this.int);
                        graphics.setPaintMode();
                        graphics.setColor(Color.black);
                        graphics.drawRect(n + n3, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                        graphics.setColor(Color.white);
                        graphics.drawString(b.getText() + " " + b2.getText(), n + n3 + 2, n8 + 12);
                    }
                    else {
                        graphics.fillRect(n - this.t - n3, n8, this.t, this.int);
                        graphics.setPaintMode();
                        graphics.setColor(Color.black);
                        graphics.drawRect(n - this.t - n3, (rectangle.y + rectangle.height) / 2 - this.int / 2, this.t, this.int);
                        graphics.setColor(Color.white);
                        graphics.drawString(b.getText() + " " + b2.getText(), n - this.t - n3 + 2, n8 + 12);
                    }
                    graphics.setColor(Color.black);
                }
            }
        }
    }
    
    public void a(final String s, final Date date, final Date date2) {
        if (this.l == null) {
            this.l = this.C.get("charttitle");
        }
        if (this.l != null) {
            if (this.w.innerIsHistorical()) {
                this.l.setText("Historical Chart of " + s);
            }
            else {
                this.l.setText("Intraday Chart of " + s);
            }
        }
    }
    
    public void for(final String s) {
        this.A = new SimpleDateFormat(s);
    }
    
    public void if(final String s) {
        this.d = new SimpleDateFormat(s);
    }
    
    public void a(final Date date) {
        if (this.i == null) {
            this.i = this.C.get("lowerdate");
        }
        if (this.i != null) {
            this.i.setText(this.A.format(date) + " to");
        }
    }
    
    public void if(final Date date) {
        if (this.e == null) {
            this.e = this.C.get("lowerdate");
        }
        if (this.e != null) {
            this.e.setText(this.A.format(date));
        }
    }
    
    class b extends Label
    {
        int a;
        
        public b(final String s, final int n) {
            super(s, n);
            this.a = -1;
        }
        
        public int a() {
            return this.a;
        }
        
        public void a(final int a) {
            this.a = a;
        }
    }
    
    class a
    {
        public int do;
        public int int;
        public int if;
        public int for;
        public Color a;
    }
}
