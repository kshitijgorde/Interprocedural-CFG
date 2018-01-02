import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawPanel extends Panel
{
    Image offScreenImage;
    int xOffset;
    int yOffset;
    int dotDiameter;
    int diskDiameter;
    int centerDiameter;
    int pivotWidth;
    int rimThickness;
    WindmillPanel parent;
    Polygon poly;
    Color[] color;
    Color backgroundColor;
    
    DrawPanel(final WindmillPanel parent) {
        this.offScreenImage = null;
        this.xOffset = 220;
        this.yOffset = 190;
        this.dotDiameter = 6;
        this.diskDiameter = 20;
        this.centerDiameter = 68;
        this.pivotWidth = 4;
        this.rimThickness = 8;
        this.backgroundColor = Color.white;
        this.parent = parent;
        this.setColorArray();
        this.setBackground(this.backgroundColor);
    }
    
    void setColorArray() {
        (this.color = new Color[7])[0] = new Color(223, 223, 223);
        this.color[1] = new Color(225, 245, 0);
        this.color[2] = new Color(218, 37, 29);
        this.color[3] = new Color(153, 71, 121);
        this.color[4] = new Color(0, 147, 221);
        this.color[5] = new Color(188, 131, 92);
        this.color[6] = new Color(78, 73, 93);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreenImage == null) {
            this.offScreenImage = this.createImage(1000, 1000);
        }
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.parent.controls.isSolved) {
            this.backgroundColor = Color.pink;
        }
        else {
            this.backgroundColor = Color.white;
        }
        final Graphics graphics2 = this.offScreenImage.getGraphics();
        graphics2.setColor(this.backgroundColor);
        graphics2.fillRect(0, 0, 1000, 1000);
        final int noOfDisks = this.parent.controls.noOfDisks;
        final double alpha = this.parent.controls.alpha;
        final int pivotAngle = this.parent.controls.pivotAngle;
        this.drawWings(noOfDisks, alpha);
        this.drawPivot(alpha + pivotAngle);
        for (int i = 0; i < this.parent.controls.disk.length; ++i) {
            this.drawDisk(this.parent.controls.disk[i].getPoint(alpha), this.diskDiameter - 2, this.color[i]);
        }
        for (int j = 0; j < this.parent.controls.goal.length; ++j) {
            this.drawDisk(this.parent.controls.goal[j].getPoint(alpha), 6, this.color[j]);
        }
        if (this.parent.controls.isSolved) {
            this.drawSolvedMessage();
        }
        if (!this.parent.controls.dontDraw) {
            graphics.drawImage(this.offScreenImage, 0, 0, this);
        }
    }
    
    void drawDisk(final double[] array, final int n, final Color color) {
        final Graphics graphics = this.offScreenImage.getGraphics();
        final int n2 = 36;
        final int[] array2 = new int[n2 + 1];
        final int[] array3 = new int[n2 + 1];
        for (int i = 0; i < n2 + 1; ++i) {
            array2[i] = (int)Math.round(this.xOffset + array[0] + n / 2 * Math.cos(6.283185307179586 * i / n2));
            array3[i] = (int)Math.round(this.yOffset + array[1] + n / 2 * Math.sin(6.283185307179586 * i / n2));
        }
        final Polygon polygon = new Polygon(array2, array3, n2 + 1);
        graphics.setColor(color);
        graphics.fillPolygon(polygon);
        graphics.setColor(Color.black);
        graphics.drawPolygon(polygon);
    }
    
    void drawWings(final int n, final double n2) {
        final Graphics graphics = this.offScreenImage.getGraphics();
        this.poly = getWindmillPoly(this.xOffset, this.yOffset, this.diskDiameter + 2 + 2 * this.rimThickness, this.centerDiameter + 2 + 2 * this.rimThickness, this.centerDiameter / 2 + n * this.diskDiameter - this.diskDiameter / 2, n2);
        graphics.setColor(new Color(0, 166, 70));
        graphics.fillPolygon(this.poly);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.poly);
        this.poly = getWindmillPoly(this.xOffset, this.yOffset, this.diskDiameter + 2, this.centerDiameter + 2, this.centerDiameter / 2 + n * this.diskDiameter - this.diskDiameter / 2, n2);
        graphics.setColor(this.backgroundColor);
        graphics.fillPolygon(this.poly);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.poly);
    }
    
    void drawSolvedMessage() {
        this.offScreenImage.getGraphics().drawString("Solved, congratulations!!!", 115, 260);
    }
    
    void drawPivot(final double n) {
        final Graphics graphics = this.offScreenImage.getGraphics();
        this.poly = getWindmillPoly(this.xOffset, this.yOffset, this.pivotWidth, this.centerDiameter - 2 * this.diskDiameter - 2, (this.centerDiameter - this.pivotWidth) / 2 - 2, n);
        graphics.setColor(new Color(192, 192, 192));
        graphics.fillPolygon(this.poly);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.poly);
    }
    
    static Polygon getWindmillPoly(final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        final double n7 = 3.141592653589793;
        final double n8 = n3 / 2;
        final double n9 = n4 / 2;
        final double cos = Math.cos(n6 * n7 / 180.0);
        final double sin = Math.sin(n6 * n7 / 180.0);
        final double n10 = n8;
        final double n11 = -n5;
        final int[] array = { n + (int)Math.round(n10 * cos - n11 * sin) };
        final Polygon polygon = new Polygon(array, new int[] { n2 + (int)Math.round(n10 * sin + n11 * cos) }, array.length);
        double n12 = -Math.acos(n8 / n9);
        double n13 = -n7 / 3.0 - n12;
        double n14 = -n7 / 3.0;
        double n15 = 2.0 * n7 / 3.0;
        for (int i = 0; i < 3; ++i) {
            for (double n16 = (n13 - n12) / 5.0, n17 = n12; n17 < n13 + n16 / 2.0; n17 += n16) {
                final double n18 = n9 * Math.cos(n17);
                final double n19 = n9 * Math.sin(n17);
                polygon.addPoint(n + (int)Math.round(n18 * cos - n19 * sin), n2 + (int)Math.round(n18 * sin + n19 * cos));
            }
            final double n20 = n5 * Math.cos(n7 / 6.0 + i * 2 * n7 / 3.0);
            final double n21 = n5 * Math.sin(n7 / 6.0 + i * 2 * n7 / 3.0);
            for (double n22 = (n15 - n14) / 8.0, n23 = n14; n23 < n15 + n22 / 2.0; n23 += n22) {
                final double n24 = n20 + n8 * Math.cos(n23);
                final double n25 = n21 + n8 * Math.sin(n23);
                polygon.addPoint(n + (int)Math.round(n24 * cos - n25 * sin), n2 + (int)Math.round(n24 * sin + n25 * cos));
            }
            n14 += 2.0 * n7 / 3.0;
            n15 += 2.0 * n7 / 3.0;
            n12 += 2.0 * n7 / 3.0;
            n13 += 2.0 * n7 / 3.0;
        }
        return polygon;
    }
}
