import java.awt.Shape;
import java.applet.Applet;
import java.awt.Rectangle;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.Stack;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class d implements ImageObserver
{
    private final int if = 1100;
    private final int b = 5;
    private int e;
    private boolean d;
    private under a;
    public int int;
    private int char;
    private Image do;
    private Image[] try;
    private Color null;
    private Stack byte;
    private a void;
    private Graphics else;
    public int c;
    public dynamisk[] new;
    public statisk[] goto;
    private AudioClip long;
    private AudioClip for;
    Rectangle case;
    
    d(final int e, final boolean d) {
        this.e = 400;
        this.d = true;
        this.int = 0;
        this.char = 0;
        this.null = new Color(40, 0, 180);
        this.byte = new Stack();
        this.c = 0;
        this.case = new Rectangle(20, 255, 30, 30);
        this.void = new a();
        this.e = e;
        this.d = d;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public boolean a(final boolean b, final dynamisk dynamisk) {
        boolean b2 = true;
        dynamisk dynamisk2;
        if (b) {
            dynamisk2 = this.new[this.c];
        }
        else {
            dynamisk2 = dynamisk;
        }
        dynamisk2.char();
        if (this.a(dynamisk2)) {
            dynamisk2.byte();
            b2 = false;
        }
        if (b2 && this.a(dynamisk2, b, 2)) {
            dynamisk2.byte();
            b2 = false;
        }
        if (b2) {
            this.byte.push(new a(dynamisk2, 2));
            this.char = 1;
            this.new();
        }
        return b2;
    }
    
    public boolean do(final boolean b, final dynamisk dynamisk) {
        boolean b2 = true;
        dynamisk dynamisk2;
        if (b) {
            dynamisk2 = this.new[this.c];
        }
        else {
            dynamisk2 = dynamisk;
        }
        dynamisk2.byte();
        if (this.a(dynamisk2)) {
            dynamisk2.char();
            b2 = false;
        }
        if (b2 && this.a(dynamisk2, b, 0)) {
            dynamisk2.char();
            b2 = false;
        }
        if (b2) {
            this.byte.push(new a(dynamisk2, 0));
            this.char = 1;
            this.new();
        }
        return b2;
    }
    
    public boolean if(final boolean b, final dynamisk dynamisk) {
        boolean b2 = true;
        dynamisk dynamisk2;
        if (b) {
            dynamisk2 = this.new[this.c];
            if (dynamisk2.new() != 0) {
                dynamisk2.do(0);
            }
        }
        else {
            dynamisk2 = dynamisk;
        }
        dynamisk2.try();
        if (this.a(dynamisk2)) {
            dynamisk2.case();
            b2 = false;
        }
        if (b2 && this.a(dynamisk2, b, 1)) {
            dynamisk2.case();
            b2 = false;
        }
        if (b2) {
            this.byte.push(new a(dynamisk2, 1));
            this.char = 1;
            this.new();
        }
        return b2;
    }
    
    public boolean for(final boolean b, final dynamisk dynamisk) {
        boolean b2 = true;
        dynamisk dynamisk2;
        if (b) {
            dynamisk2 = this.new[this.c];
            if (dynamisk2.new() != 1) {
                dynamisk2.do(1);
            }
        }
        else {
            dynamisk2 = dynamisk;
        }
        dynamisk2.case();
        if (b2 && this.a(dynamisk2, b, 3)) {
            dynamisk2.try();
            b2 = false;
        }
        if (this.a(dynamisk2)) {
            dynamisk2.try();
            b2 = false;
        }
        if (b2) {
            this.byte.push(new a(dynamisk2, 3));
            this.char = 1;
            this.new();
        }
        return b2;
    }
    
    private boolean a(final e e) {
        final dynamisk dynamisk = this.new[0];
        final dynamisk dynamisk2 = this.new[1];
        if (dynamisk.for() == 15 && dynamisk2.for() == 15 && dynamisk.if() + dynamisk.int() > 290 && dynamisk2.if() + dynamisk2.int() > 300) {
            this.try();
            this.for.play();
        }
        return this.void.a(e);
    }
    
    public int do() {
        final int char1 = this.char;
        this.char = 0;
        return char1;
    }
    
    private boolean a(final e e, final boolean b, final int n) {
        boolean b2 = false;
        int n2 = 0;
        final Rectangle rectangle = new Rectangle(e.for(), e.if(), e.do(), e.int());
        dynamisk dynamisk = null;
        for (int n3 = 0; this.new[n3] != null; ++n3) {
            if (e != this.new[n3]) {
                final dynamisk dynamisk2 = this.new[n3];
                if (rectangle.intersects(new Rectangle(dynamisk2.for(), dynamisk2.if(), dynamisk2.do(), dynamisk2.int()))) {
                    if (++n2 > 1) {
                        return true;
                    }
                    dynamisk = dynamisk2;
                }
            }
        }
        if (n2 == 1) {
            if (b) {
                if (e.getClass().getName().compareTo("lSkib") == 0) {
                    if (dynamisk.getClass().getName().compareTo("lKlods") != 0) {
                        return true;
                    }
                    switch (n) {
                        case 0: {
                            b2 = !this.do(false, dynamisk);
                            break;
                        }
                        case 1: {
                            b2 = !this.if(false, dynamisk);
                            break;
                        }
                        case 2: {
                            b2 = !this.a(false, dynamisk);
                            break;
                        }
                        case 3: {
                            b2 = !this.for(false, dynamisk);
                            break;
                        }
                    }
                    if (!b2) {
                        this.long.play();
                    }
                }
                else if (e.getClass().getName().compareTo("sSkib") == 0) {
                    if (dynamisk.getClass().getName().compareTo("sKlods") != 0) {
                        return true;
                    }
                    switch (n) {
                        case 0: {
                            b2 = !this.do(false, dynamisk);
                            break;
                        }
                        case 1: {
                            b2 = !this.if(false, dynamisk);
                            break;
                        }
                        case 2: {
                            b2 = !this.a(false, dynamisk);
                            break;
                        }
                        case 3: {
                            b2 = !this.for(false, dynamisk);
                            break;
                        }
                    }
                    if (!b2) {
                        this.long.play();
                    }
                }
            }
            else {
                b2 = true;
            }
        }
        return b2;
    }
    
    public Graphics a() {
        return this.else;
    }
    
    public void a(final Image[] try1, final Graphics else1, final AudioClip long1, final AudioClip for1, final under a) {
        this.a = a;
        this.long = long1;
        this.for = for1;
        this.try = try1;
        this.do = try1[8];
        this.else = else1;
        this.goto = this.void.a(this.int, try1);
        this.new = this.void.for();
        this.long.play();
        this.else.drawImage(try1[8], 0, -20, this);
        for (int i = 5; i < 490; i += 10) {
            this.else.drawImage(try1[4], i, 5, this);
        }
        for (int j = 5; j < 30; j += 10) {
            this.else.drawImage(try1[4], 5, j, this);
            this.else.drawImage(try1[4], 485, j, this);
            this.else.drawImage(try1[4], 445, j, this);
        }
        this.byte();
        this.new();
    }
    
    public void int() {
        this.goto = this.void.if(this.int);
        this.new = this.void.for();
        this.new();
        this.long.play();
    }
    
    public void try() {
        ++this.int;
        if (this.int < 5) {
            this.int();
            this.a.char();
            this.a.repaint();
        }
        else if (this.a.for() / 10 < 1100 && this.d) {
            new f(this.a, this.e, this.a.for() / 10);
        }
        else {
            this.a.byte();
        }
    }
    
    public void if() {
        this.goto = this.void.if();
        this.new = this.void.for();
        this.new();
    }
    
    public void a(final Graphics graphics) {
        final dynamisk dynamisk = this.new[this.c];
        graphics.clipRect(dynamisk.for() - 30, dynamisk.if() + 0, 90, 80);
    }
    
    private void new() {
        final dynamisk dynamisk = this.new[this.c];
        for (int i = 35; i < 350; i += 35) {
            this.else.drawImage(this.do, 0, i, null);
        }
        this.else.drawImage(this.try[8], -45, -10, this);
        for (int j = 5; j < 490; j += 10) {
            this.else.drawImage(this.try[4], j, 5, null);
        }
        for (int k = 5; k < 30; k += 10) {
            this.else.drawImage(this.try[4], 5, k, null);
            this.else.drawImage(this.try[4], 485, k, null);
            this.else.drawImage(this.try[4], 445, k, null);
        }
        this.else.setColor(Color.black);
        this.else.drawString("Level : " + (this.int + 1), 20, 30);
        this.else.drawString("Moves : " + this.a.new / 10, 85, 30);
        this.else.drawImage(this.try[11], 15, 285, null);
        for (int n = 0; this.goto[n] != null; ++n) {
            final statisk statisk = this.goto[n];
            this.else.drawImage(statisk.a(), statisk.for(), statisk.if(), null);
        }
        for (int n2 = 0; this.new[n2] != null; ++n2) {
            final dynamisk dynamisk2 = this.new[n2];
            this.else.drawImage(dynamisk2.a(), dynamisk2.for(), dynamisk2.if(), null);
        }
    }
    
    public void byte() {
        if (this.c == 0) {
            this.c = 1;
        }
        else {
            this.c = 0;
        }
        final int n = 455;
        final int n2 = 15;
        this.else.clipRect(n, n2, 30, 20);
        this.else.drawImage(this.do, 5, 0, this);
        if (this.c == 1) {
            this.else.drawImage(this.new[1].a(), n, n2, this);
        }
        else {
            this.else.drawImage(this.new[0].a(), n, n2 + 5, this);
        }
        this.a.char();
        this.else.setClip(null);
    }
    
    public boolean for() {
        if (!this.byte.empty()) {
            final a a = this.byte.pop();
            a a2 = null;
            if (!this.byte.empty()) {
                a2 = this.byte.peek();
            }
            if (a.a().getClass().toString().endsWith("lSkib")) {
                this.c = 0;
            }
            else if (a.a().getClass().toString().endsWith("sSkib")) {
                this.c = 1;
            }
            switch (a.if()) {
                case 1: {
                    a.a().case();
                    if (a2 != null && a2.a().getClass().toString().endsWith("Klods")) {
                        this.for();
                    }
                    this.new();
                    break;
                }
                case 2: {
                    a.a().byte();
                    if (a2 != null && a2.a().getClass().toString().endsWith("Klods")) {
                        this.for();
                    }
                    this.new();
                    break;
                }
                case 3: {
                    a.a().try();
                    if (a2 != null && a2.a().getClass().toString().endsWith("Klods")) {
                        this.for();
                    }
                    this.new();
                    break;
                }
                case 0: {
                    a.a().char();
                    if (a2 != null && a2.a().getClass().toString().endsWith("Klods")) {
                        this.for();
                    }
                    this.new();
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public class a
    {
        int if;
        dynamisk a;
        
        a(final dynamisk a, final int if1) {
            this.if = if1;
            this.a = a;
        }
        
        public int if() {
            return this.if;
        }
        
        public dynamisk a() {
            return this.a;
        }
    }
}
