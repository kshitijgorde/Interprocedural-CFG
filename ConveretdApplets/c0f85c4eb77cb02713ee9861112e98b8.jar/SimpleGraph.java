import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SimpleGraph extends JPanel
{
    private int low;
    private int high;
    private int basePointX;
    private int basePointY;
    private Vector[] w1;
    private Vector[] w2;
    private int[] data1;
    private int[] data2;
    private Color bgcolor;
    
    public SimpleGraph() {
        this.basePointX = 0;
        this.basePointY = 0;
        this.setSize(280, 180);
        this.w1 = new Vector[100];
        this.w2 = new Vector[100];
        this.data1 = new int[100];
        this.data2 = new int[100];
        for (int i = 0; i < 100; ++i) {
            this.data1[i] = 0;
            this.data2[i] = 0;
        }
    }
    
    public void resetDataValues() {
        this.w1 = new Vector[100];
        this.w2 = new Vector[100];
        this.data1 = new int[100];
        this.data2 = new int[100];
    }
    
    public void addData(final int place, final int c1, final int c2) {
        if (this.w1[place] == null) {
            this.w1[place] = new Vector();
        }
        if (this.w2[place] == null) {
            this.w2[place] = new Vector();
        }
        final Vector ww1 = this.w1[place];
        final Vector ww2 = this.w2[place];
        ww1.add(new Integer(c1));
        if (this.data1[place] != 0) {
            this.data1[place] = this.getAverage(ww1);
        }
        else {
            this.data1[place] = c1;
        }
        ww2.add(new Integer(c2));
        if (this.data2[place] != 0) {
            this.data2[place] = this.getAverage(ww2);
        }
        else {
            this.data2[place] = c2;
        }
        this.repaint();
    }
    
    private int getAverage(final Vector v) {
        int sum = 0;
        int i;
        for (i = 0; i < v.size(); ++i) {
            sum += v.elementAt(i);
        }
        return sum / (i + 1);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawLine(20, 120, 20, 20);
        g.drawLine(20, 121, 120, 121);
        for (int i = 0; i <= 10; ++i) {
            g.drawLine(20, 10 * i + 20, 18, 10 * i + 20);
            g.drawLine(10 * i + 20, 121, 10 * i + 20, 123);
        }
        g.drawString("10", 23, 134);
        g.drawString("10", 3, 113);
        g.drawString("100", 112, 134);
        g.drawString("100", -2, 24);
        g.drawString("# of Items", 122, 120);
        g.drawString("Comparisons", 20, 20);
        g.drawString("Sequential Search", 160, 50);
        g.drawString("Binary Search", 160, 64);
        g.setColor(Color.blue);
        g.drawOval(150, 44, 2, 2);
        for (int i = 0; i < 100; ++i) {
            if (this.data1[i] != 0) {
                g.drawOval(i + 20, 120 - this.data1[i], 1, 1);
            }
        }
        g.setColor(Color.red);
        g.drawOval(150, 58, 2, 2);
        for (int i = 0; i < 100; ++i) {
            if (this.data2[i] != 0) {
                g.drawOval(i + 20, 120 - this.data2[i], 1, 1);
            }
        }
    }
}
