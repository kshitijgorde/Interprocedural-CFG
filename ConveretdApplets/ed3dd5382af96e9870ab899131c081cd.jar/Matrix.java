import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.text.NumberFormat;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
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

public class Matrix extends Applet implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    Button identityButton;
    Button transposeButton;
    Button invertButton;
    Button rotateCWButton;
    Button rotateCCWButton;
    Button reflectXButton;
    Button reflectYButton;
    double[][] vecs;
    double[][] origmatrix;
    double[][] eigens;
    double pointX;
    double pointY;
    boolean showPoint;
    boolean dragging;
    int selection;
    Color darkCyan;
    Color darkRed;
    Color purple;
    MatrixCanvas cv;
    
    public Matrix() {
        this.engine = null;
        this.showPoint = false;
        this.dragging = false;
        this.selection = -1;
    }
    
    public String getAppletInfo() {
        return "Matrix by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    public void init() {
        this.darkCyan = new Color(0, 128, 128);
        this.darkRed = new Color(128, 0, 0);
        this.purple = new Color(192, 60, 206);
        this.setLayout(new MatrixLayout());
        (this.cv = new MatrixCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.identityButton = new Button("Identity"));
        this.identityButton.addActionListener(this);
        this.add(this.transposeButton = new Button("Transpose"));
        this.transposeButton.addActionListener(this);
        this.add(this.invertButton = new Button("Invert"));
        this.invertButton.addActionListener(this);
        this.add(this.rotateCWButton = new Button("Rotate CW"));
        this.rotateCWButton.addActionListener(this);
        this.add(this.rotateCCWButton = new Button("Rotate CCW"));
        this.rotateCCWButton.addActionListener(this);
        this.add(this.reflectXButton = new Button("Reflect X"));
        this.reflectXButton.addActionListener(this);
        this.add(this.reflectYButton = new Button("Reflect Y"));
        this.reflectYButton.addActionListener(this);
        this.add(this.cv);
        this.setBackground(Color.black);
        this.setForeground(Color.lightGray);
        this.random = new Random();
        this.vecs = new double[2][2];
        this.vecs[0][0] = 1.0;
        this.vecs[0][1] = 0.0;
        this.vecs[1][0] = 0.0;
        this.vecs[1][1] = 1.0;
        this.eigens = new double[2][2];
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
    
    void findXformVecCoords(final double n, final double n2, final int[] array) {
        this.findVecCoords(this.vecs[0][0] * n + this.vecs[1][0] * n2, this.vecs[0][1] * n + this.vecs[1][1] * n2, array);
    }
    
    void drawArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2, n3, n4);
        final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        if (sqrt > 10.0) {
            final double n5 = (n3 - n) / sqrt;
            final double n6 = (n4 - n2) / sqrt;
            final int n7 = 10;
            graphics.drawLine(n3, n4, (int)(n6 * n7 - n5 * n7 + n3), (int)(-n5 * n7 - n6 * n7 + n4));
            graphics.drawLine(n3, n4, (int)(-n6 * n7 - n5 * n7 + n3), (int)(n5 * n7 - n6 * n7 + n4));
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
    
    boolean isZero(final double n) {
        return n >= -1.0E-5 && n <= 1.0E-5;
    }
    
    void eigenfind(final double n, final int n2) {
        double n3 = this.vecs[0][0] - n;
        double n4 = -this.vecs[1][0];
        if (this.isZero(n4) && this.isZero(n3)) {
            n3 = -this.vecs[0][1];
            n4 = this.vecs[1][1] - n;
        }
        double sqrt = Math.sqrt(n4 * n4 + n3 * n3);
        if ((n4 < 0.0 && n3 < 0.0) || n4 < -n3 || n3 < -n4) {
            sqrt = -sqrt;
        }
        if (this.isZero(sqrt)) {
            this.eigens[n2][0] = (this.eigens[n2][1] = 0.0);
        }
        else {
            this.eigens[n2][0] = n4 / sqrt;
            this.eigens[n2][1] = n3 / sqrt;
        }
    }
    
    String eigenstring(final int n, final NumberFormat numberFormat, final double n2, final boolean b, final double n3, final double n4) {
        if (b) {
            return "lambda" + n + " = " + numberFormat.format(n2);
        }
        if (n == 1) {
            return "lambda1 = " + numberFormat.format(n3 * 0.5) + "+" + numberFormat.format(n4 * 0.5) + "i";
        }
        return "lambda2 = " + numberFormat.format(n3 * 0.5) + "-" + numberFormat.format(n4 * 0.5) + "i";
    }
    
    public void updateMatrix(final Graphics graphics) {
        for (int i = 0; i != 2; ++i) {
            for (int j = 0; j != 2; ++j) {
                if (this.isZero(this.vecs[i][j])) {
                    this.vecs[i][j] = 0.0;
                }
            }
        }
        Math.sqrt(this.vecs[0][0] * this.vecs[0][0] + this.vecs[0][1] * this.vecs[0][1]);
        Math.sqrt(this.vecs[1][0] * this.vecs[1][0] + this.vecs[1][1] * this.vecs[1][1]);
        final double n = this.vecs[0][0] * this.vecs[1][1] - this.vecs[0][1] * this.vecs[1][0];
        if (this.isZero(n)) {
            this.invertButton.disable();
        }
        else {
            this.invertButton.enable();
        }
        final double n2 = this.vecs[0][0] + this.vecs[1][1];
        double n3 = n2 * n2 - 4.0 * n;
        boolean b = true;
        if (n3 < 0.0) {
            b = false;
            n3 = -n3;
        }
        final double sqrt = Math.sqrt(n3);
        final double n4 = 0.5 * (n2 + sqrt);
        final double n5 = 0.5 * (n2 - sqrt);
        int n6 = b ? 2 : 0;
        if (b && n4 == n5) {
            n6 = ((this.vecs[0][1] != 0.0 || this.vecs[1][0] != 0.0) ? 1 : 0);
        }
        if (n6 > 0) {
            this.eigenfind(n4, 0);
        }
        if (n6 > 1) {
            this.eigenfind(n5, 1);
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(Color.gray);
        for (int k = -2; k <= 2; ++k) {
            final int n7 = this.winSize.height * (k + 2) / 4;
            graphics2.drawLine(n7, 0, n7, this.winSize.height);
            graphics2.drawLine(0, n7, this.winSize.height, n7);
        }
        final int n9;
        final int n8 = n9 = this.winSize.height / 2;
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        final int n10 = 5;
        if (this.showPoint) {
            graphics2.setColor(Color.gray);
            this.findXformVecCoords(this.pointX, this.pointY, array);
            this.findVecCoords(this.pointX, this.pointY, array2);
            this.drawArrow(graphics2, array2[0], array2[1], array[0], array[1]);
        }
        graphics2.setColor(Color.green);
        this.findXformVecCoords(1.0, 1.0, array);
        this.findXformVecCoords(-1.0, 1.0, array2);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(-1.0, -1.0, array);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(1.0, -1.0, array2);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(1.0, 1.0, array);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(-0.5, 0.5, array);
        this.findXformVecCoords(0.5, 0.5, array2);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(-0.5, -0.5, array);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(0.5, -0.5, array2);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        this.findXformVecCoords(-0.5, -0.6, array);
        this.findXformVecCoords(0.5, -0.6, array2);
        graphics2.drawLine(array[0], array[1], array2[0], array2[1]);
        if (this.showPoint) {
            graphics2.setColor(this.purple);
            this.findXformVecCoords(this.pointX, this.pointY, array);
            graphics2.fillOval(array[0] - n10 / 2, array[1] - n10 / 2, n10, n10);
        }
        if (n6 > 0) {
            graphics2.setColor(Color.yellow);
            this.findVecCoords(this.eigens[0][0], this.eigens[0][1], array);
            this.drawArrow(graphics2, n9, n8, array[0], array[1]);
        }
        if (n6 > 1) {
            graphics2.setColor(Color.orange);
            this.findVecCoords(this.eigens[1][0], this.eigens[1][1], array);
            this.drawArrow(graphics2, n9, n8, array[0], array[1]);
        }
        if (!this.isZero(n)) {
            graphics2.setColor(this.darkRed);
            this.findVecCoords(this.vecs[1][1] / n, -this.vecs[0][1] / n, array);
            graphics2.fillOval(array[0] - n10 / 2, array[1] - n10 / 2, n10, n10);
            graphics2.setColor(this.darkCyan);
            this.findVecCoords(-this.vecs[1][0] / n, this.vecs[0][0] / n, array);
            graphics2.fillOval(array[0] - n10 / 2, array[1] - n10 / 2, n10, n10);
        }
        this.findVecCoords(0, array);
        graphics2.setColor(Color.red);
        this.drawArrow(graphics2, n9, n8, array[0], array[1]);
        this.findVecCoords(1, array);
        graphics2.setColor(Color.cyan);
        this.drawArrow(graphics2, n9, n8, array[0], array[1]);
        final FontMetrics fontMetrics = graphics2.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int n11 = this.reflectYButton.getY() + this.reflectYButton.getHeight() + 5 + fontMetrics.getMaxAscent();
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        final String format = instance.format(this.vecs[0][0]);
        final String format2 = instance.format(this.vecs[1][0]);
        final String format3 = instance.format(this.vecs[0][1]);
        final String format4 = instance.format(this.vecs[1][1]);
        int stringWidth = fontMetrics.stringWidth(format);
        final int stringWidth2 = fontMetrics.stringWidth(format3);
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        int stringWidth3 = fontMetrics.stringWidth(format2);
        final int stringWidth4 = fontMetrics.stringWidth(format4);
        if (stringWidth4 > stringWidth3) {
            stringWidth3 = stringWidth4;
        }
        final int n12 = 10;
        final int n13 = stringWidth + n12 + stringWidth3;
        final int n14 = this.winSize.height + (this.winSize.width - this.winSize.height - n13) / 2;
        graphics2.setColor(Color.red);
        graphics2.drawString(format, n14 + (stringWidth - fontMetrics.stringWidth(format)) / 2, n11);
        graphics2.setColor(Color.cyan);
        graphics2.drawString(format2, n14 + stringWidth + n12 + (stringWidth3 - fontMetrics.stringWidth(format2)) / 2, n11);
        final int n15 = n11 + height;
        graphics2.setColor(Color.red);
        graphics2.drawString(format3, n14 + (stringWidth - fontMetrics.stringWidth(format3)) / 2, n15);
        graphics2.setColor(Color.cyan);
        graphics2.drawString(format4, n14 + stringWidth + n12 + (stringWidth3 - fontMetrics.stringWidth(format4)) / 2, n15);
        graphics2.setColor(Color.white);
        final int n16 = n15 - height - fontMetrics.getMaxAscent();
        final int n17 = n15 + fontMetrics.getMaxDescent();
        graphics2.drawLine(n14 - 5, n16, n14 - 5, n17);
        graphics2.drawLine(n14 - 5, n16, n14, n16);
        graphics2.drawLine(n14 - 5, n17, n14, n17);
        graphics2.drawLine(n14 + n13 + 5, n16, n14 + n13 + 5, n17);
        graphics2.drawLine(n14 + n13, n16, n14 + n13 + 5, n16);
        graphics2.drawLine(n14 + n13, n17, n14 + n13 + 5, n17);
        final int n18;
        this.displayString(graphics2, "det M = " + instance.format(n), n18 = n15 + height);
        final int n19;
        this.displayString(graphics2, "tr M = " + instance.format(n2), n19 = n18 + height);
        graphics2.setColor(Color.yellow);
        final int n20;
        this.displayString(graphics2, this.eigenstring(1, instance, n4, b, n2, sqrt), n20 = n19 + height);
        graphics2.setColor(Color.orange);
        this.displayString(graphics2, this.eigenstring(2, instance, n5, b, n2, sqrt), n20 + height);
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    void displayString(final Graphics graphics, final String s, final int n) {
        final int height = this.winSize.height;
        graphics.drawString(s, height + (this.winSize.width - height - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (!this.dragging) {
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
        if (this.selection != -1) {
            this.vecs[this.selection][0] = n2;
            this.vecs[this.selection][1] = n3;
        }
        else {
            final double atan2 = Math.atan2(this.pointY, this.pointX);
            final double atan3 = Math.atan2(n3, n2);
            final double n4 = Math.sqrt(n2 * n2 + n3 * n3) / Math.sqrt(this.pointX * this.pointX + this.pointY * this.pointY);
            final double n5 = Math.cos(atan3 - atan2) * n4;
            final double n6 = Math.sin(atan3 - atan2) * n4;
            this.vecs[0][0] = this.origmatrix[0][0] * n5 + this.origmatrix[1][0] * n6;
            this.vecs[0][1] = this.origmatrix[0][1] * n5 + this.origmatrix[1][1] * n6;
            this.vecs[1][0] = this.origmatrix[0][0] * -n6 + this.origmatrix[1][0] * n5;
            this.vecs[1][1] = this.origmatrix[0][1] * -n6 + this.origmatrix[1][1] * n5;
        }
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
        if (actionEvent.getSource() == this.identityButton) {
            this.vecs[0][0] = 1.0;
            this.vecs[0][1] = 0.0;
            this.vecs[1][0] = 0.0;
            this.vecs[1][1] = 1.0;
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.transposeButton) {
            final double n = this.vecs[1][0];
            this.vecs[1][0] = this.vecs[0][1];
            this.vecs[0][1] = n;
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.invertButton) {
            final double n2 = this.vecs[0][0] * this.vecs[1][1] - this.vecs[0][1] * this.vecs[1][0];
            if (n2 != 0.0) {
                final double n3 = this.vecs[0][0];
                this.vecs[0][0] = this.vecs[1][1] / n2;
                this.vecs[1][1] = n3 / n2;
                final double[] array = this.vecs[1];
                final int n4 = 0;
                array[n4] /= -n2;
                final double[] array2 = this.vecs[0];
                final int n5 = 1;
                array2[n5] /= -n2;
            }
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.rotateCCWButton) {
            final double n6 = 0.8660254;
            this.copyMatrix();
            this.vecs[0][0] = this.origmatrix[0][0] * n6 + this.origmatrix[1][0] * 0.5;
            this.vecs[0][1] = this.origmatrix[0][1] * n6 + this.origmatrix[1][1] * 0.5;
            this.vecs[1][0] = this.origmatrix[0][0] * -0.5 + this.origmatrix[1][0] * n6;
            this.vecs[1][1] = this.origmatrix[0][1] * -0.5 + this.origmatrix[1][1] * n6;
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.rotateCWButton) {
            final double n7 = 0.8660254;
            this.copyMatrix();
            this.vecs[0][0] = this.origmatrix[0][0] * n7 + this.origmatrix[1][0] * -0.5;
            this.vecs[0][1] = this.origmatrix[0][1] * n7 + this.origmatrix[1][1] * -0.5;
            this.vecs[1][0] = this.origmatrix[0][0] * 0.5 + this.origmatrix[1][0] * n7;
            this.vecs[1][1] = this.origmatrix[0][1] * 0.5 + this.origmatrix[1][1] * n7;
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.reflectXButton) {
            this.vecs[0][1] = -this.vecs[0][1];
            this.vecs[1][1] = -this.vecs[1][1];
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.reflectYButton) {
            this.vecs[0][0] = -this.vecs[0][0];
            this.vecs[1][0] = -this.vecs[1][0];
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            final double n = this.winSize.height / 4;
            final double pointX = mouseEvent.getX() / n - 2.0;
            final double pointY = 2.0 - mouseEvent.getY() / n;
            this.showPoint = true;
            if (pointX < -2.0) {
                this.showPoint = false;
            }
            if (pointY < -2.0) {
                this.showPoint = false;
            }
            if (pointX > 2.0) {
                this.showPoint = false;
            }
            if (pointY > 2.0) {
                this.showPoint = false;
            }
            this.pointX = pointX;
            this.pointY = pointY;
            this.cv.repaint();
            return;
        }
        this.edit(mouseEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.showPoint = false;
        this.cv.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int[] array = new int[2];
        this.selection = -1;
        this.showPoint = false;
        for (int i = 0; i != 2; ++i) {
            this.findVecCoords(i, array);
            final int n = 10;
            if (array[0] >= x - n && array[0] <= x + n && array[1] >= y - n && array[1] <= y + n) {
                this.selection = i;
                break;
            }
        }
        if (this.selection != -1) {
            this.dragging = true;
            this.edit(mouseEvent);
            return;
        }
        final double n2 = this.winSize.height / 4;
        final double pointX = mouseEvent.getX() / n2 - 2.0;
        final double pointY = 2.0 - mouseEvent.getY() / n2;
        if (pointX < -2.0) {
            return;
        }
        if (pointY < -2.0) {
            return;
        }
        if (pointX > 2.0) {
            return;
        }
        if (pointY > 2.0) {
            return;
        }
        if (this.isZero(pointX) && this.isZero(pointY)) {
            return;
        }
        this.dragging = true;
        this.pointX = pointX;
        this.pointY = pointY;
        this.copyMatrix();
    }
    
    void copyMatrix() {
        this.origmatrix = new double[2][2];
        for (int i = 0; i != 2; ++i) {
            for (int j = 0; j != 2; ++j) {
                this.origmatrix[i][j] = this.vecs[i][j];
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragging = false;
        this.selection = -1;
    }
}
