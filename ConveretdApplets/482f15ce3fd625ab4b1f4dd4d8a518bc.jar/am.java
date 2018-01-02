import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public final class am
{
    public int a;
    private aE a;
    private au a;
    private int[] a;
    public boolean a;
    public boolean b;
    private int b;
    private int c;
    public String a;
    private String b;
    private boolean c;
    private Font a;
    
    private boolean a() {
        return this.a.a.b[0] == 93 && this.a[770] != 3;
    }
    
    private String a() {
        int n = this.a[1];
        if (this.a.a.a.a == 2460096604L && n == 13) {
            ++n;
        }
        switch (n) {
            case 0: {
                return "Glass Joe";
            }
            case 1: {
                return "Von Kaiser";
            }
            case 2: {
                return "Piston Honda";
            }
            case 3: {
                return "Don Flamenco";
            }
            case 4: {
                return "King Hippo";
            }
            case 5: {
                return "Great Tiger";
            }
            case 6: {
                return "Bald Bull";
            }
            case 7: {
                return "Piston Honda";
            }
            case 8: {
                return "Soda Popinski";
            }
            case 9: {
                return "Bald Bull";
            }
            case 10: {
                return "Don Flamenco";
            }
            case 11: {
                return "Mr Sandman";
            }
            case 12: {
                return "Super Macho Man";
            }
            case 13: {
                return "Mr Dream";
            }
            case 14: {
                return "Mike Tyson";
            }
            default: {
                return "Opponent";
            }
        }
    }
    
    public final void a(final Graphics graphics) {
        if (!this.b) {
            return;
        }
        switch (this.a) {
            case 1: {
                graphics.setFont(this.a);
                if (this.a()) {
                    final boolean b = this.b > this.c - 5;
                    Color color = new Color(200, 200, 200);
                    if (b) {
                        color = new Color(255, 255, 255);
                    }
                    aK.a(graphics, this.a[913] + " pts", 100, 23, color, 1);
                    aK.a(graphics, this.a[920] + " pts", 157, 23, color, 1);
                    break;
                }
                break;
            }
        }
    }
    
    public final void a(final Graphics graphics, final int n, final int n2) {
        if (!this.b) {
            return;
        }
        switch (this.a) {
            case 1: {
                graphics.setFont(this.a);
                if (this.b > 0 && !this.b.equals("")) {
                    final int n3 = n2 - 16;
                    final boolean b = this.b > this.c - 5;
                    if (!this.c) {
                        if (b) {
                            graphics.setColor(new Color(255, 255, 255, 200));
                        }
                        else {
                            graphics.setColor(new Color(255, 128, 128, 200));
                        }
                        graphics.fillRect(1, n3, n, 15);
                        graphics.setColor(new Color(255, 0, 0));
                        graphics.drawRect(1, n3, n, 15);
                        graphics.setColor(new Color(128, 0, 0));
                    }
                    else {
                        if (b) {
                            graphics.setColor(new Color(255, 255, 255, 200));
                        }
                        else {
                            graphics.setColor(new Color(128, 255, 128, 200));
                        }
                        graphics.fillRect(1, n3, n, 15);
                        graphics.setColor(new Color(0, 255, 0));
                        graphics.drawRect(1, n3, n, 15);
                        graphics.setColor(new Color(0, 128, 0));
                    }
                    graphics.drawString(this.b, 6, 12 + n3);
                    --this.b;
                    break;
                }
                break;
            }
        }
    }
    
    public final void a(final int n, final int n2) {
        if (!this.b) {
            return;
        }
        switch (this.a) {
            case 1: {
                if (n == 913 && this.a()) {
                    if (this.a[913] > n2 && this.a[914] > n2) {
                        this.b = this.a() + " hit: " + (this.a[913] - n2) + " pts";
                        this.c = false;
                        this.b = this.c;
                        return;
                    }
                    if (this.a[913] == 0 && n2 > 0) {
                        this.b = "Little Mac's energy set to " + n2 + " pts";
                        this.c = true;
                        this.b = this.c;
                        return;
                    }
                    break;
                }
                else {
                    if (n != 920 || !this.a()) {
                        break;
                    }
                    if (this.a[920] > n2 && this.a[921] > n2) {
                        this.b = "Little Mac hit: " + (this.a[920] - n2) + " pts";
                        this.c = true;
                        this.b = this.c;
                        return;
                    }
                    if (this.a[920] == 0 && n2 > 0) {
                        this.b = this.a() + "'s energy set to " + n2 + " pts";
                        this.c = false;
                        this.b = this.c;
                        break;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public am(final au a, final long n) {
        this.a = 0;
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = false;
        this.b = false;
        this.b = 0;
        this.c = 120;
        this.a = "";
        this.b = "";
        this.c = true;
        this.a = new Font("Dialog", 0, 10);
        this.a = a;
        this.a = a.a;
        this.a = a.a.a;
        if (n == 3197345742L) {
            this.a = "Punch-Out";
            ((aE)(this.a = 1)).b("Mr Dream's Punch-Out Loaded");
            this.a = true;
            return;
        }
        if (n == 2460096604L) {
            this.a = "Punch-Out";
            ((aE)(this.a = 1)).b("Mike Tyson's Punch-Out Loaded");
            this.a = true;
        }
    }
}
