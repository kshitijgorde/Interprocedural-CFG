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

class z3 extends Canvas
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
        if (z1.z56 != null) {
            graphics.drawImage(z1.z56, 0, -z1.z69, this);
        }
        String z27 = z1.z27;
        if (z27.indexOf("£") != -1) {
            z27 = "£";
        }
        if (z27.indexOf("$") != -1) {
            z27 = "$";
        }
        if (!z27.equals("£") && !z27.equals("$")) {
            z27 = "";
        }
        graphics.translate(-this.z0, -this.dy);
        final int n = 10;
        int n2 = 0;
        int n3 = 1;
        final int n4 = 0;
        final int z28 = z1.z186;
        final int z29 = z1.z185;
        final Font font = new Font("Helvetica", 0, this.font_height);
        final Font font2 = new Font("Helvetica", 1, this.font_height);
        graphics.setFont(font2);
        this.z1 = graphics.getFontMetrics(font2);
        this.point_height = this.z1.getHeight();
        String item = "||||||||||x|x|x|x|x|x|x|x|x|x|||";
        String substring = "";
        graphics.setColor(z1.z61);
        int n5 = 0;
        z1.z113.clear();
        while (n5 < z1.z134.countItems() || (n5 == z1.z134.countItems() && !substring.trim().equals(""))) {
            String z30;
            String concat;
            boolean b;
            if (substring.trim().equals("")) {
                final String s = item;
                item = z1.z134.getItem(n5);
                if (!z1.z0(s, 13).equals(z1.z0(item, 13))) {
                    graphics.setFont(font2);
                    final int n6 = n2 + this.point_height;
                    graphics.setColor(z1.z149);
                    if (z1.z23) {
                        graphics.drawString(z1.z15.getItem(n3).replace('_', ' '), n, n6);
                    }
                    else {
                        int selectedIndex = z1.z15.getSelectedIndex();
                        if (selectedIndex == 0) {
                            selectedIndex = 1;
                        }
                        graphics.drawString(z1.z15.getItem(selectedIndex).replace('_', ' '), n, n6);
                    }
                    ++n3;
                    n2 = n6 + this.point_height;
                    graphics.setColor(z1.z61);
                }
                z1.z113.addItem(String.valueOf("").concat(String.valueOf(n2)));
                z30 = z1.z0(item, 1);
                concat = String.valueOf(z27).concat(String.valueOf(z1.z0(item, 3).trim()));
                ++n5;
                b = true;
            }
            else {
                z30 = substring;
                concat = "";
                z1.z113.replaceItem(String.valueOf("").concat(String.valueOf(n2)), z1.z113.countItems() - 1);
                b = false;
            }
            final int z31 = this.z0(z30, z1.z185 - 80);
            if (n2 > this.dy && n2 < z1.z65 + this.dy) {
                if (z1.z113.countItems() - 1 == z1.z180.getSelectedIndex()) {
                    graphics.setFont(font2);
                    graphics.setColor(z1.z55);
                }
                else {
                    graphics.setFont(font2);
                }
                if (z31 != 0) {
                    graphics.drawString(z30.substring(0, z31).trim(), n, n2);
                    graphics.drawString(concat, n4 + z29 - this.z1.stringWidth(concat) - 23, n2);
                }
                else {
                    graphics.drawString(z30.trim(), n, n2);
                    graphics.drawString(concat, n4 + z29 - this.z1.stringWidth(concat) - 23, n2);
                }
                if (b) {
                    graphics.drawString("°", 0, n2);
                }
                graphics.setColor(z1.z61);
            }
            if (z31 != 0) {
                substring = z30.substring(z31);
            }
            else {
                substring = "";
            }
            n2 += this.point_height;
        }
        if (n2 >= z1.z65) {
            final int n7 = n2 - this.point_height - this.point_height;
            final Scrollbar scrollbar = new Scrollbar(0, 0, 10, 0, 100);
            scrollbar.setValues(90, 10, 0, 90);
            final int n8 = n7 / this.point_height + 1;
            if (scrollbar.getValue() == 90) {
                z1.z159.setValues(z1.z159.getValue(), 1, 0, n8 - z1.z65 / this.point_height);
            }
            else {
                z1.z159.setValues(z1.z159.getValue(), 1, 0, n8 - z1.z65 / this.point_height + 1);
            }
            z1.z159.show();
            z1.z54 = false;
            z1.z70.reshape(10, z1.z69, z1.z185 - this.z4, z1.z186);
            z1.z159.reshape(10 + z1.z185 - z1.z124, z1.z69, z1.z124, z1.z186);
        }
        else {
            z1.z159.hide();
            z1.z54 = true;
            z1.z70.reshape(10, z1.z69, z1.z185, z1.z186);
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
    
    z3() {
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
