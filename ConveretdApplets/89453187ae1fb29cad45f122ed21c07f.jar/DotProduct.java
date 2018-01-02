import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.NumberFormat;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DotProduct extends Applet implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    Button swapButton;
    double[][] vecs;
    int selection;
    DotProductCanvas cv;
    
    public DotProduct() {
        this.engine = null;
        this.selection = -1;
    }
    
    public String getAppletInfo() {
        return "DotProduct by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    public void init() {
        this.setLayout(new DotProductLayout());
        (this.cv = new DotProductCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.swapButton = new Button("Swap"));
        this.swapButton.addActionListener(this);
        this.add(this.cv);
        this.setBackground(Color.black);
        this.setForeground(Color.lightGray);
        this.random = new Random();
        this.vecs = new double[2][2];
        this.vecs[0][0] = 0.0;
        this.vecs[0][1] = 1.0;
        this.vecs[1][0] = 1.0;
        this.vecs[1][1] = 1.0;
        this.reinit();
        this.repaint();
    }
    
    void reinit() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    void findVecCoords(final double n, final double n2, final int[] array) {
        final int n3 = this.winSize.height / 4;
        array[0] = (int)(n3 * (n + 2.0));
        array[1] = (int)(n3 * (2.0 - n2));
    }
    
    void findVecCoords(final int n, final int[] array) {
        this.findVecCoords(this.vecs[n][0], this.vecs[n][1], array);
    }
    
    void drawArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final double n5) {
        graphics.drawLine(n, n2, n3, n4);
        if (n5 > 0.05) {
            final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
            final double n6 = (n3 - n) / sqrt;
            final double n7 = (n4 - n2) / sqrt;
            final int n8 = 10;
            graphics.drawLine(n3, n4, (int)(n7 * n8 - n6 * n8 + n3), (int)(-n6 * n8 - n7 * n8 + n4));
            graphics.drawLine(n3, n4, (int)(-n7 * n8 - n6 * n8 + n3), (int)(n6 * n8 - n7 * n8 + n4));
        }
    }
    
    void drawBar(final Graphics graphics, final int n, final double n2) {
        final int n3 = (int)(this.winSize.width * n2 / 6.0);
        final int n4 = this.winSize.width / 2;
        final int n5 = 5;
        final int n6 = this.winSize.height + n5 * n;
        if (n2 < 0.0) {
            graphics.fillRect(n4 + n3, n6, -n3, n5);
        }
        else {
            graphics.fillRect(n4, n6, n3, n5);
        }
    }
    
    public void updateDotProduct(final Graphics graphics) {
        final double sqrt = Math.sqrt(this.vecs[0][0] * this.vecs[0][0] + this.vecs[0][1] * this.vecs[0][1]);
        final double sqrt2 = Math.sqrt(this.vecs[1][0] * this.vecs[1][0] + this.vecs[1][1] * this.vecs[1][1]);
        final double n = 57.29577957855229;
        final double n2 = this.vecs[0][0] * this.vecs[1][0] + this.vecs[0][1] * this.vecs[1][1];
        final double n3 = (sqrt2 > 0.0) ? (n2 / sqrt2) : 0.0;
        final double n4 = (sqrt > 0.0) ? (n3 / sqrt) : 0.0;
        final double n5 = Math.acos(n4) * n;
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(Color.gray);
        for (int i = -2; i <= 2; ++i) {
            final int n6 = this.winSize.height * (i + 2) / 4;
            graphics2.drawLine(n6, 0, n6, this.winSize.height);
            graphics2.drawLine(0, n6, this.winSize.height, n6);
        }
        final int n8;
        final int n7 = n8 = this.winSize.height / 2;
        final int[] array = new int[2];
        if (sqrt2 > 0.0) {
            final int[] array2 = new int[2];
            this.findVecCoords(this.vecs[1][0] * n3 / sqrt2, this.vecs[1][1] * n3 / sqrt2, array);
            this.findVecCoords(0, array2);
            graphics2.setColor(Color.gray);
            graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        }
        if (sqrt > 0.1 && sqrt2 > 0.1) {
            final int n9 = n8 / 10;
            final int n10 = n7 / 10;
            int n11 = (int)(n * Math.atan2(this.vecs[0][1], this.vecs[0][0]));
            int n12 = (int)(n * Math.atan2(this.vecs[1][1], this.vecs[1][0]));
            if (n11 > n12 && n11 < n12 + 180) {
                final int n13 = n11;
                n11 = n12;
                n12 = n13;
            }
            if (n12 < n11) {
                n12 += 360;
            }
            graphics2.setColor(Color.orange);
            graphics2.drawArc(n8 - n9, n7 - n10, n9 * 2, n10 * 2, n11, n12 - n11);
        }
        this.findVecCoords(0, array);
        graphics2.setColor(Color.red);
        this.drawArrow(graphics2, n8, n7, array[0], array[1], sqrt);
        this.findVecCoords(1, array);
        graphics2.setColor(Color.cyan);
        this.drawArrow(graphics2, n8, n7, array[0], array[1], sqrt2);
        final int height;
        final int n14 = height = graphics2.getFontMetrics().getHeight();
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        graphics2.setColor(Color.red);
        final int n15;
        this.displayString(graphics2, "A = (" + instance.format(this.vecs[0][0]) + ", " + instance.format(this.vecs[0][1]) + ")", n15 = height + n14);
        final int n16;
        this.displayString(graphics2, "|A| = " + instance.format(sqrt), n16 = n15 + n14);
        this.drawBar(graphics2, -4, sqrt);
        graphics2.setColor(Color.cyan);
        final int n17;
        this.displayString(graphics2, "B = (" + instance.format(this.vecs[1][0]) + ", " + instance.format(this.vecs[1][1]) + ")", n17 = n16 + n14);
        final int n18;
        this.displayString(graphics2, "|B| = " + instance.format(sqrt2), n18 = n17 + n14);
        this.drawBar(graphics2, -3, sqrt2);
        graphics2.setColor(Color.yellow);
        final int n19;
        this.displayString(graphics2, "|A| cos theta = " + instance.format(n3), n19 = n18 + n14);
        this.drawBar(graphics2, -2, n3);
        if (sqrt2 > 0.0) {
            this.findVecCoords(this.vecs[1][0] * n3 / sqrt2, this.vecs[1][1] * n3 / sqrt2, array);
            graphics2.setColor(Color.yellow);
            graphics2.drawLine(n8, n7, array[0], array[1]);
        }
        graphics2.setColor(Color.white);
        final int n20;
        this.displayString(graphics2, "cos theta = " + instance.format(n4), n20 = n19 + n14);
        graphics2.setColor(Color.orange);
        final int n21;
        this.displayString(graphics2, "theta = " + instance.format(n5) + "°", n21 = n20 + n14);
        graphics2.setColor(Color.green);
        this.displayString(graphics2, "A dot B = " + instance.format(n2), n21 + n14);
        this.drawBar(graphics2, -1, n2);
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    void displayString(final Graphics graphics, final String s, final int n) {
        final int height = this.winSize.height;
        graphics.drawString(s, height + (this.winSize.width - height - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == -1) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final double n = this.winSize.height / 4;
        double n2 = x / n - 2.0;
        double n3 = 2.0 - y / n;
        if (n2 < -2.0) {
            n2 = -2.0;
        }
        if (n3 < -2.0) {
            n3 = -2.0;
        }
        if (n2 > 2.0) {
            n2 = 2.0;
        }
        if (n3 > 2.0) {
            n3 = 2.0;
        }
        this.vecs[this.selection][0] = n2;
        this.vecs[this.selection][1] = n3;
        this.cv.repaint();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.reinit();
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.swapButton) {
            for (int i = 0; i < 2; ++i) {
                final double n = this.vecs[0][i];
                this.vecs[0][i] = this.vecs[1][i];
                this.vecs[1][i] = n;
            }
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        this.edit(mouseEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int[] array = new int[2];
        this.selection = -1;
        for (int i = 0; i != 2; ++i) {
            this.findVecCoords(i, array);
            final int n = 10;
            if (array[0] >= x - n && array[0] <= x + n && array[1] >= y - n && array[1] <= y + n) {
                this.selection = i;
                break;
            }
        }
        if (this.selection != -1) {
            this.edit(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.selection = -1;
    }
}
