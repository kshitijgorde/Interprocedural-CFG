// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.b;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import buddysoft.a.c;

public abstract class b extends a implements Runnable
{
    protected Thread for;
    protected c[] new;
    protected int int;
    protected String try;
    
    protected abstract void if();
    
    public b() {
        this.try = "play";
    }
    
    private void a(final Graphics graphics, final String s, final int n) {
        if (s.equals("sd") || s.equals("pd")) {
            int i = -super.height;
            while (i <= 0) {
                if (i > -super.height / 4) {
                    ++i;
                }
                else {
                    i += 2;
                }
                if (s.startsWith("p")) {
                    graphics.drawImage(this.new[n].for, -2, super.height + i, null);
                }
                graphics.drawImage(this.new[this.int].for, -2, i, null);
                this.a(10);
            }
        }
        else if (s.equals("su") || s.equals("pu")) {
            int j = super.height;
            while (j >= 0) {
                if (j < super.height / 4) {
                    --j;
                }
                else {
                    j -= 2;
                }
                if (s.startsWith("p")) {
                    graphics.drawImage(this.new[n].for, -2, -super.height + j, null);
                }
                graphics.drawImage(this.new[this.int].for, -2, j, null);
                this.a(10);
            }
        }
        else if (s.equals("sl") || s.equals("pl")) {
            int k = super.width;
            while (k >= 0) {
                if (k < super.width / 4) {
                    --k;
                }
                else {
                    k -= 2;
                }
                if (s.startsWith("p")) {
                    graphics.drawImage(this.new[n].for, -super.width + k, -2, null);
                }
                graphics.drawImage(this.new[this.int].for, k, -2, null);
                this.a(10);
            }
        }
        else if (s.equals("sr") || s.equals("pr")) {
            int l = -super.width;
            while (l <= 0) {
                if (l > -super.width / 4) {
                    ++l;
                }
                else {
                    l += 2;
                }
                if (s.startsWith("p")) {
                    graphics.drawImage(this.new[n].for, super.width + l, -2, null);
                }
                graphics.drawImage(this.new[this.int].for, l, -2, null);
                this.a(10);
            }
        }
        else {
            graphics.drawImage(this.new[this.int].for, -2, -2, null);
            this.repaint();
        }
    }
    
    private void a(final int n) {
        this.repaint();
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        this.int = 0;
        int n = 0;
        while (super.if == null) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
        if (!buddysoft.a.a.f) {
            super.if.setFont(new Font("Helvetica", 1, 12));
            final FontMetrics fontMetrics = this.getFontMetrics(super.if.getFont());
            super.if.setColor(Color.black);
            super.if.fillRect(super.width / 2 - 115, super.height / 2 - 10, 230, 20);
            super.if.setColor(Color.white);
            super.if.drawString(super.a.null, super.width / 2 - fontMetrics.stringWidth(super.a.null) / 2, super.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
            this.a(3000);
        }
        while (true) {
            if (this.int >= this.new.length) {
                this.int = 0;
            }
            else if (this.int < 0) {
                this.int = this.new.length - 1;
            }
            if (this.new[this.int].int) {
                this.if();
                this.do();
                if (this.try.equals("play")) {
                    try {
                        Thread.sleep(this.new[this.int].do);
                    }
                    catch (InterruptedException ex2) {}
                }
                ++this.int;
                ++n;
                if (this.try.equals("stop") || this.try.equals("next") || this.try.equals("prev")) {
                    this.for.suspend();
                }
            }
            else {
                this.a(super.if, "Please wait! Loading images...");
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex3) {}
            }
            if (n > 10) {
                System.gc();
                n = 0;
            }
        }
    }
    
    private void do() {
        String s = this.new[this.int].if;
        int int1 = this.int - 1;
        if (int1 < 0) {
            int1 = this.new.length - 1;
        }
        if (this.try.equals("prev")) {
            --this.int;
            if (this.int < 0) {
                this.int = this.new.length - 1;
            }
            s = this.new[this.int].if;
            if (s.startsWith("p")) {
                if (s.equals("pd")) {
                    s = "pu";
                }
                else if (s.equals("pu")) {
                    s = "pd";
                }
                else if (s.equals("pl")) {
                    s = "pr";
                }
                else if (s.equals("pr")) {
                    s = "pl";
                }
                int1 = this.int;
                --this.int;
                if (this.int < 0) {
                    this.int = this.new.length - 1;
                }
                if (int1 < 0) {
                    int1 = this.new.length - 1;
                }
            }
            else {
                --this.int;
                if (this.int < 0) {
                    this.int = this.new.length - 1;
                }
                s = this.new[this.int].if;
            }
        }
        this.a(super.if, s, int1);
    }
}
