// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

public class u extends Canvas
{
    private Color new;
    int long;
    int goto;
    Image e;
    Image a;
    boolean null;
    boolean byte;
    boolean c;
    boolean for;
    boolean j;
    boolean void;
    boolean g;
    boolean do;
    boolean h;
    boolean d;
    boolean char;
    boolean i;
    boolean case;
    boolean[] if;
    Principale else;
    j int;
    private int x;
    int b;
    int f;
    private int y;
    int try;
    
    public u(final Principale else1, final j int1, final Image a, final Image e) {
        this.if = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, false };
        this.b = 5;
        this.try = 5;
        this.f = 8;
        this.long = 3;
        this.goto = 3;
        this.else = else1;
        this.int = int1;
        this.a = a;
        this.e = e;
        this.new = Color.black;
        try {
            this.do();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public u() {
        this.if = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, false };
        this.b = 5;
        this.try = 5;
        this.f = 8;
        this.long = 3;
        this.goto = 3;
        try {
            this.do();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void a() {
        final c c = new c(this.else, this.int);
        c.setSize(320, 300);
        this.else.setEnabled(false);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        c.setLocation((screenSize.width - c.getSize().width) / 2, (screenSize.height - c.getSize().height) / 2);
        try {
            c.setLocation((screenSize.width - c.getSize().width) / 2, (screenSize.height - c.getSize().height) / 2);
            c.show();
        }
        finally {}
    }
    
    private void do() throws Exception {
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                u.this.if(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                u.this.a(mouseEvent);
            }
        });
    }
    
    private void if() {
        for (int i = 0; i < 12; ++i) {
            if (i == 0) {
                this.if[i] = this.d;
            }
            else if (i == 1) {
                this.if[i] = this.c;
            }
            else if (i == 2) {
                this.if[i] = this.g;
            }
            else if (i == 3) {
                this.if[i] = this.null;
            }
            else if (i == 4) {
                this.if[i] = this.char;
            }
            else if (i == 5) {
                this.if[i] = this.do;
            }
            else if (i == 6) {
                this.if[i] = this.h;
            }
            else if (i == 7) {
                this.if[i] = this.for;
            }
            else if (i == 8) {
                this.if[i] = this.j;
            }
            else if (i == 9) {
                this.if[i] = this.i;
            }
            else if (i == 10) {
                this.if[i] = this.case;
            }
            else if (i == 11) {
                this.if[i] = this.void;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 500, 100);
        graphics.setColor(Color.gray);
        if (this.d) {
            graphics.fill3DRect(this.b - this.long, this.try - this.goto, 26, 26, true);
        }
        else if (this.c) {
            graphics.fill3DRect(this.b - this.long + 1 * this.f + 20, this.try - this.goto, 26, 26, true);
        }
        else if (this.g) {
            graphics.fill3DRect(this.b - this.long + 2 * this.f + 40, this.try - this.goto, 26, 26, true);
        }
        else if (this.null) {
            graphics.fill3DRect(this.b - this.long + 3 * this.f + 60, this.try - this.goto, 26, 26, true);
        }
        else if (this.char) {
            graphics.fill3DRect(this.b - this.long + 4 * this.f + 80, this.try - this.goto, 26, 26, true);
        }
        else if (this.do) {
            graphics.fill3DRect(this.b - this.long + 5 * this.f + 100, this.try - this.goto, 26, 26, true);
        }
        else if (this.h) {
            graphics.fill3DRect(this.b - this.long + 6 * this.f + 120, this.try - this.goto, 26, 26, true);
        }
        else if (this.for) {
            graphics.fill3DRect(this.b - this.long + 7 * this.f + 140, this.try - this.goto, 26, 26, true);
        }
        else if (this.j) {
            graphics.fill3DRect(this.b - this.long + 8 * this.f + 160, this.try - this.goto, 26, 26, true);
        }
        else if (this.i) {
            graphics.fill3DRect(this.b - this.long + 9 * this.f + 180, this.try - this.goto, 26, 26, true);
        }
        else if (this.case) {
            graphics.fill3DRect(this.b - this.long + 10 * this.f + 200, this.try - this.goto, 26, 26, true);
        }
        else if (this.void) {
            graphics.fill3DRect(this.b - this.long + 11 * this.f + 220, this.try - this.goto, 26, 26, true);
        }
        if (this.byte) {
            graphics.fill3DRect(this.b - this.long + 12 * this.f + 240, this.try - this.goto, 26, 26, true);
        }
        graphics.setColor(Color.black);
        graphics.drawRoundRect(this.b - 3, this.try - 3, 362, 25, 5, 5);
        for (int i = 1; i <= 12; ++i) {
            graphics.fillRect(this.b + i * this.f - 5 + i * 20, this.try - 3, 2, 25);
        }
        graphics.drawImage(this.a, this.b, this.try, this);
        graphics.drawLine(this.b + 1 * this.f + 20 + 3, this.try + 17, this.b + 1 * this.f + 20 + 20 - 3, this.try + 3);
        graphics.drawArc(this.b + 2 * this.f + 40 + 3, this.try + 2, 10, 7, 90, 180);
        graphics.drawArc(this.b + 2 * this.f + 40 + 2, this.try + 9, 10, 7, -90, 180);
        graphics.drawArc(this.b + 3 * this.f + 60 + 3, this.try + 3, 14, 18, 0, 180);
        graphics.setFont(new Font("TimesRoman", 1, 22));
        graphics.drawString("A", this.b + 4 * this.f + 80 + 2, this.try + 17);
        graphics.drawRect(this.b + 5 * this.f + 100 + 3, this.try + 3, 12, 12);
        graphics.fillRect(this.b + 6 * this.f + 120 + 3, this.try + 3, 14, 14);
        graphics.drawOval(this.b + 7 * this.f + 140 + 3, this.try + 3, 14, 14);
        graphics.fillOval(this.b + 8 * this.f + 160 + 3, this.try + 3, 15, 15);
        graphics.drawPolygon(new int[] { this.b + 9 * this.f + 180 + 2, this.b + 9 * this.f + 180 + 3 + 7, this.b + 9 * this.f + 180 + 3 + 15 }, new int[] { this.try + 3 + 14, this.try + 3, this.try + 3 + 14 }, 3);
        graphics.fillPolygon(new int[] { this.b + 10 * this.f + 200 + 2, this.b + 10 * this.f + 200 + 3 + 7, this.b + 10 * this.f + 200 + 3 + 15 }, new int[] { this.try + 3 + 15, this.try + 2, this.try + 3 + 15 }, 3);
        graphics.setColor(Color.blue);
        graphics.fillRect(this.b + 11 * this.f + 220 + 6, this.try + 6, 6, 6);
        graphics.drawImage(this.e, this.b + 12 * this.f + 240, this.try, this);
        Color color = Color.black;
        for (int j = 0; j < 11; ++j) {
            if (j == 1) {
                color = Color.gray;
            }
            else if (j == 2) {
                color = Color.lightGray;
            }
            else if (j == 3) {
                color = Color.blue;
            }
            else if (j == 4) {
                color = Color.cyan;
            }
            else if (j == 5) {
                color = Color.green;
            }
            else if (j == 6) {
                color = Color.magenta;
            }
            else if (j == 7) {
                color = Color.orange;
            }
            else if (j == 8) {
                color = Color.pink;
            }
            else if (j == 9) {
                color = Color.red;
            }
            else if (j == 10) {
                color = Color.yellow;
            }
            graphics.setColor(color);
            graphics.fillRect(380 + j * 10, 5, 10, 10);
        }
        graphics.setColor(this.new);
        graphics.fillRect(380, 16, 110, 8);
    }
    
    void if(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.x >= this.b && this.x <= this.b + 12 * this.f + 240 - 7 && this.y >= this.try && this.y <= this.try + 20) {
            this.d = (this.x >= this.b && this.x <= this.b + 20 && this.y >= this.try && this.y <= this.try + 20);
            this.c = (this.x >= this.b + 1 * this.f + 20 && this.x <= this.b + 20 + 1 * this.f + 20 && this.y >= this.try && this.y <= this.try + 20);
            this.g = (this.x >= this.b + 2 * this.f + 40 && this.x <= this.b + 20 + 2 * this.f + 40 && this.y >= this.try && this.y <= this.try + 20);
            this.null = (this.x >= this.b + 3 * this.f + 60 && this.x <= this.b + 20 + 3 * this.f + 60 && this.y >= this.try && this.y <= this.try + 20);
            this.char = (this.x >= this.b + 4 * this.f + 80 && this.x <= this.b + 20 + 4 * this.f + 80 && this.y >= this.try && this.y <= this.try + 20);
            this.do = (this.x >= this.b + 5 * this.f + 100 && this.x <= this.b + 20 + 5 * this.f + 100 && this.y >= this.try && this.y <= this.try + 20);
            this.h = (this.x >= this.b + 6 * this.f + 120 && this.x <= this.b + 20 + 6 * this.f + 120 && this.y >= this.try && this.y <= this.try + 20);
            this.for = (this.x >= this.b + 7 * this.f + 140 && this.x <= this.b + 20 + 7 * this.f + 140 && this.y >= this.try && this.y <= this.try + 20);
            this.j = (this.x >= this.b + 8 * this.f + 160 && this.x <= this.b + 20 + 8 * this.f + 160 && this.y >= this.try && this.y <= this.try + 20);
            this.i = (this.x >= this.b + 9 * this.f + 180 && this.x <= this.b + 20 + 9 * this.f + 180 && this.y >= this.try && this.y <= this.try + 20);
            this.case = (this.x >= this.b + 10 * this.f + 200 && this.x <= this.b + 20 + 10 * this.f + 200 && this.y >= this.try && this.y <= this.try + 20);
            this.void = (this.x >= this.b + 11 * this.f + 220 && this.x <= this.b + 20 + 11 * this.f + 220 && this.y >= this.try && this.y <= this.try + 20);
            this.if();
            if (this.char) {
                this.a();
            }
            else {
                this.int.a(this.if);
            }
        }
        this.byte = (this.x >= this.b + 12 * this.f + 240 && this.x <= this.b + 20 + 12 * this.f + 240 && this.y >= this.try && this.y <= this.try + 20);
        if (this.byte) {
            this.int.new();
        }
        if (this.x >= 380 && this.x <= 490 && this.y >= 5 && this.y <= 15) {
            if (this.x >= 380 && this.x <= 390 && this.y >= 5 && this.y <= 15) {
                this.new = Color.black;
            }
            else if (this.x >= 390 && this.x <= 400 && this.y >= 5 && this.y <= 15) {
                this.new = Color.gray;
            }
            else if (this.x >= 400 && this.x <= 410 && this.y >= 5 && this.y <= 15) {
                this.new = Color.lightGray;
            }
            else if (this.x >= 410 && this.x <= 420 && this.y >= 5 && this.y <= 15) {
                this.new = Color.blue;
            }
            else if (this.x >= 420 && this.x <= 430 && this.y >= 5 && this.y <= 15) {
                this.new = Color.cyan;
            }
            else if (this.x >= 430 && this.x <= 440 && this.y >= 5 && this.y <= 15) {
                this.new = Color.green;
            }
            else if (this.x >= 440 && this.x <= 450 && this.y >= 5 && this.y <= 15) {
                this.new = Color.magenta;
            }
            else if (this.x >= 450 && this.x <= 460 && this.y >= 5 && this.y <= 15) {
                this.new = Color.orange;
            }
            else if (this.x >= 460 && this.x <= 470 && this.y >= 5 && this.y <= 15) {
                this.new = Color.pink;
            }
            else if (this.x >= 470 && this.x <= 480 && this.y >= 5 && this.y <= 15) {
                this.new = Color.red;
            }
            else if (this.x >= 480 && this.x <= 490 && this.y >= 5 && this.y <= 15) {
                this.new = Color.yellow;
            }
            this.int.a(this.new);
        }
        this.repaint();
    }
    
    void a(final MouseEvent mouseEvent) {
        if (this.byte) {
            this.byte = false;
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
