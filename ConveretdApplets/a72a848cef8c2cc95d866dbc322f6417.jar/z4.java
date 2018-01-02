import java.awt.Scrollbar;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class z4 extends Canvas
{
    private int z0;
    public int dy;
    private FontMetrics z1;
    public int font_height;
    private int z2;
    private String z3;
    public int point_height;
    private int z4;
    private boolean z5;
    private int z6;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= 32) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    private int z0(String substring, final int n) {
        int lastIndex;
        for (lastIndex = 0; this.z1.stringWidth(substring) > n; substring = substring.substring(0, lastIndex)) {
            lastIndex = substring.lastIndexOf(" ");
            if (lastIndex == -1) {
                lastIndex = 0;
                break;
            }
        }
        return lastIndex;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (z1.z58 != null) {
            graphics.drawImage(z1.z58, 0, -z1.z74, this);
        }
        String z29 = z1.z29;
        final String trim = "\u20ac ".trim();
        if (z29.indexOf("£") != -1) {
            z29 = "£";
        }
        if (z29.indexOf("$") != -1) {
            z29 = "$";
        }
        if (z29.indexOf(172) != -1) {
            z29 = trim;
        }
        if (z29.indexOf(trim) != -1) {
            z29 = trim;
        }
        if (!z29.equals("£") && !z29.equals("$") && !z29.equals(trim)) {
            z29 = "";
        }
        graphics.translate(-this.z0, -this.dy);
        final int n = 10;
        int n2 = 0;
        int selectedIndex = z1.z15.getSelectedIndex();
        if (selectedIndex == 0) {
            selectedIndex = 1;
        }
        final int n3 = 0;
        final int z30 = z1.z192;
        final int z31 = z1.z191;
        final Font font = new Font("Helvetica", 0, this.font_height);
        final Font font2 = new Font("Helvetica", 1, this.font_height);
        graphics.setFont(font2);
        this.z1 = graphics.getFontMetrics(font2);
        this.point_height = this.z1.getHeight();
        String item = "||||||||||x|x|x|x|x|x|x|x|x|x|||";
        String substring = "";
        graphics.setColor(z1.z64);
        int n4 = 0;
        z1.z116.clear();
        while (n4 < z1.z137.countItems() || (n4 == z1.z137.countItems() && !substring.trim().equals(""))) {
            String z32;
            String concat;
            boolean b;
            if (substring.trim().equals("")) {
                final String s = item;
                item = z1.z137.getItem(n4);
                if (!z1.z0(s, 13).equals(z1.z0(item, 13))) {
                    graphics.setFont(font2);
                    final int n5 = n2 + this.point_height;
                    graphics.setColor(z1.z153);
                    graphics.drawString(z1.z15.getItem(selectedIndex).replace('_', ' '), n, n5);
                    ++selectedIndex;
                    n2 = n5 + this.point_height;
                    graphics.setColor(z1.z64);
                }
                z1.z116.addItem(String.valueOf("").concat(String.valueOf(n2)));
                z32 = z1.z0(item, 1);
                concat = String.valueOf(z29).concat(String.valueOf(z1.z0(item, 3).trim()));
                if (z29.equals(concat)) {
                    concat = "";
                }
                ++n4;
                b = true;
            }
            else {
                z32 = substring;
                concat = "";
                z1.z116.replaceItem(String.valueOf("").concat(String.valueOf(n2)), z1.z116.countItems() - 1);
                b = false;
            }
            final int z33 = this.z0(z32, z1.z191 - 80);
            if (n2 > this.dy && n2 < z1.z70 + this.dy) {
                if (z1.z152) {
                    return;
                }
                if (z1.z116.countItems() - 1 == z1.z185.getSelectedIndex()) {
                    graphics.setFont(font2);
                    graphics.setColor(z1.z57);
                }
                else {
                    graphics.setFont(font2);
                }
                if (z33 != 0) {
                    graphics.drawString(z32.substring(0, z33).trim(), n, n2);
                    graphics.drawString(concat, n3 + z31 - this.z1.stringWidth(concat) - 23, n2);
                }
                else {
                    graphics.drawString(z32.trim(), n, n2);
                    graphics.drawString(concat, n3 + z31 - this.z1.stringWidth(concat) - 23, n2);
                }
                if (b) {
                    graphics.drawString("°", 0, n2);
                }
                graphics.setColor(z1.z64);
            }
            if (z33 != 0) {
                substring = z32.substring(z33);
            }
            else {
                substring = "";
            }
            n2 += this.point_height;
        }
        if (n2 >= z1.z70) {
            final int n6 = n2 - this.point_height - this.point_height;
            final Scrollbar scrollbar = new Scrollbar(0, 0, 10, 0, 100);
            scrollbar.setValues(90, 10, 0, 90);
            final int n7 = n6 / this.point_height + 1;
            if (scrollbar.getValue() == 90) {
                z1.z163.setValues(z1.z163.getValue(), 1, 0, n7 - z1.z70 / this.point_height);
            }
            else {
                z1.z163.setValues(z1.z163.getValue(), 1, 0, n7 - z1.z70 / this.point_height + 1);
            }
            z1.z163.show();
            z1.z56 = false;
            z1.z75.reshape(10, z1.z74, z1.z191 - this.z4, z1.z192);
            z1.z163.reshape(10 + z1.z191 - z1.z127, z1.z74, z1.z127, z1.z192);
        }
        else {
            z1.z163.hide();
            z1.z56 = true;
            z1.z75.reshape(10, z1.z74, z1.z191, z1.z192);
        }
    }
    
    public void setText1() {
        this.repaint();
    }
    
    public void translate(final int z0, final int dy) {
        this.z0 = z0;
        this.dy = dy;
        this.repaint();
    }
    
    z4() {
        this.z3 = "";
        this.z0 = 0;
        this.dy = 0;
        this.z6 = 1000;
        this.z2 = 500;
        this.font_height = 12;
        this.point_height = 15;
        this.z4 = 16;
        this.z5 = false;
    }
}
