import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class SymButton extends Canvas implements MouseListener
{
    Image offImage;
    Graphics offGraphics;
    ActionListener main;
    boolean pressed;
    int centx;
    int centy;
    int width;
    int height;
    int[][] vec;
    int type;
    Color[] colors;
    
    public SymButton(final ActionListener main, final int type) {
        this.colors = new Color[] { new Color(0, 0, 0), new Color(64, 64, 64), new Color(128, 128, 128), new Color(224, 224, 224), new Color(255, 255, 255), new Color(255, 0, 0), new Color(255, 128, 128), new Color(255, 0, 255), new Color(0, 255, 255), new Color(0, 0, 255) };
        this.main = main;
        this.pressed = false;
        this.type = type;
        this.addMouseListener(this);
    }
    
    public boolean isPressed() {
        return this.pressed;
    }
    
    public void setPressed(final boolean pressed) {
        this.pressed = pressed;
        this.repaint();
    }
    
    void setType(final int n) {
        this.type = this.checkSym(n);
        this.repaint();
    }
    
    void initialise() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.offImage = this.createImage(this.width, this.height);
        this.offGraphics = this.offImage.getGraphics();
        this.centx = (this.width - 1) / 2;
        this.centy = (this.height - 1) / 2;
        int n = (int)((this.centx - 1) / 0.866);
        if (n > this.centy - 1) {
            n = this.centy - 1;
        }
        this.vec = new int[][] { { 0, n }, { (int)(-n * 0.866), -n / 2 }, { (int)(n * 0.866), -n / 2 }, { 0, n }, { (int)(-n * 0.866), -n / 2 }, { (int)(n * 0.866), -n / 2 } };
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImage == null) {
            this.initialise();
        }
        if (this.isEnabled()) {
            this.offGraphics.setColor(this.colors[this.pressed ? 1 : 4]);
            this.offGraphics.fillRect(0, 0, this.width, this.height);
            this.offGraphics.setColor(this.colors[this.pressed ? 4 : 1]);
            this.offGraphics.fillRect(1, 1, this.width - 1, this.height - 1);
            this.offGraphics.setColor(this.colors[this.pressed ? 2 : 3]);
            this.offGraphics.fillRect(1, 1, this.width - 2, this.height - 2);
            this.offGraphics.setColor(this.colors[this.pressed ? 3 : 2]);
            this.offGraphics.fillRect(2, 2, this.width - 3, this.height - 3);
            this.offGraphics.setColor(this.getBackground());
            this.offGraphics.fillRect(2, 2, this.width - 4, this.height - 4);
        }
        else {
            this.offGraphics.setColor(this.getBackground());
            this.offGraphics.fillRect(0, 0, this.width, this.height);
        }
        final int n = 1;
        if ((this.type & n) != 0x0) {
            this.offGraphics.setColor(this.colors[6]);
            final int n2 = this.vec[0][1] / 3;
            this.offGraphics.fillOval(this.centx - n2, this.centy - n2, n2 + n2, n2 + n2);
        }
        int n3 = n << 1;
        for (int i = 0; i < 3; ++i) {
            if ((this.type & n3) != 0x0) {
                this.offGraphics.setColor(this.colors[5]);
                this.offGraphics.drawPolyline(new int[] { this.centx + this.vec[i + 1][0] + this.vec[i + 2][0], this.centx, this.centx + this.vec[i][0] }, new int[] { this.centy + this.vec[i + 1][1] + this.vec[i + 2][1], this.centy, this.centy + this.vec[i][1] }, 3);
            }
            n3 <<= 1;
        }
        for (int j = 0; j < 3; ++j) {
            if ((this.type & n3) != 0x0) {
                this.offGraphics.setColor(this.colors[5]);
                this.offGraphics.drawPolyline(new int[] { this.centx + this.vec[j][0] + this.vec[j + 1][0], this.centx + this.vec[j + 1][0], this.centx + this.vec[j + 2][0], this.centx + this.vec[j + 2][0] + this.vec[j][0] }, new int[] { this.centy + this.vec[j][1] + this.vec[j + 1][1], this.centy + this.vec[j + 1][1], this.centy + this.vec[j + 2][1], this.centy + this.vec[j + 2][1] + this.vec[j][1] }, 4);
            }
            n3 <<= 1;
        }
        for (int k = 0; k < 3; ++k) {
            if ((this.type & n3) != 0x0) {
                this.offGraphics.setColor(this.colors[5]);
                this.offGraphics.drawPolyline(new int[] { this.centx + this.vec[k + 1][0] + this.vec[k][0] / 2, this.centx + this.vec[k][0] / 2, this.centx + this.vec[k + 2][0] + this.vec[k][0] / 2 }, new int[] { this.centy + this.vec[k + 1][1] + this.vec[k][1] / 2, this.centy + this.vec[k][1] / 2, this.centy + this.vec[k + 2][1] + this.vec[k][1] / 2 }, 3);
            }
            n3 <<= 1;
        }
        int n4 = n3 << 3;
        for (int l = 0; l < 3; ++l) {
            if ((this.type & n4) != 0x0) {
                this.offGraphics.setColor(this.colors[7]);
                this.offGraphics.fillPolygon(new int[] { this.centx + (4 * this.vec[l + 1][0] + 4 * this.vec[l + 2][0] + 3) / 6, this.centx + (4 * this.vec[l + 1][0] + 2 * this.vec[l + 2][0] + 3) / 6, this.centx + (2 * this.vec[l + 1][0] + 2 * this.vec[l + 2][0] + 3) / 6, this.centx + (2 * this.vec[l + 1][0] + 4 * this.vec[l + 2][0] + 3) / 6 }, new int[] { this.centy + (4 * this.vec[l + 1][1] + 4 * this.vec[l + 2][1] + 3) / 6, this.centy + (4 * this.vec[l + 1][1] + 2 * this.vec[l + 2][1] + 3) / 6, this.centy + (2 * this.vec[l + 1][1] + 2 * this.vec[l + 2][1] + 3) / 6, this.centy + (2 * this.vec[l + 1][1] + 4 * this.vec[l + 2][1] + 3) / 6 }, 4);
            }
            n4 <<= 1;
        }
        int n5 = n4 >> 6;
        for (int n6 = 0; n6 < 3; ++n6) {
            if ((this.type & n5) != 0x0) {
                this.offGraphics.setColor(this.colors[5]);
                this.offGraphics.fillPolygon(new int[] { this.centx + (4 * this.vec[n6 + 1][0] + 4 * this.vec[n6 + 2][0] + 3) / 6, this.centx + (4 * this.vec[n6 + 1][0] + 2 * this.vec[n6 + 2][0] + 3) / 6, this.centx + (2 * this.vec[n6 + 1][0] + 2 * this.vec[n6 + 2][0] + 3) / 6, this.centx + (2 * this.vec[n6 + 1][0] + 4 * this.vec[n6 + 2][0] + 3) / 6 }, new int[] { this.centy + (4 * this.vec[n6 + 1][1] + 4 * this.vec[n6 + 2][1] + 3) / 6, this.centy + (4 * this.vec[n6 + 1][1] + 2 * this.vec[n6 + 2][1] + 3) / 6, this.centy + (2 * this.vec[n6 + 1][1] + 2 * this.vec[n6 + 2][1] + 3) / 6, this.centy + (2 * this.vec[n6 + 1][1] + 4 * this.vec[n6 + 2][1] + 3) / 6 }, 4);
            }
            n5 <<= 1;
        }
        int n7 = n5 << 3;
        for (int n8 = 0; n8 < 3; ++n8) {
            if ((this.type & n7) != 0x0) {
                this.offGraphics.setColor(this.colors[9]);
                this.offGraphics.fillPolygon(new int[] { this.centx + (4 * this.vec[n8 + 1][0] + 4 * this.vec[n8 + 2][0] + 3) / 6, this.centx + (4 * this.vec[n8 + 1][0] + 2 * this.vec[n8 + 2][0] + 3) / 6, this.centx + (2 * this.vec[n8 + 1][0] + 2 * this.vec[n8 + 2][0] + 3) / 6, this.centx + (2 * this.vec[n8 + 1][0] + 4 * this.vec[n8 + 2][0] + 3) / 6 }, new int[] { this.centy + (4 * this.vec[n8 + 1][1] + 4 * this.vec[n8 + 2][1] + 3) / 6, this.centy + (4 * this.vec[n8 + 1][1] + 2 * this.vec[n8 + 2][1] + 3) / 6, this.centy + (2 * this.vec[n8 + 1][1] + 2 * this.vec[n8 + 2][1] + 3) / 6, this.centy + (2 * this.vec[n8 + 1][1] + 4 * this.vec[n8 + 2][1] + 3) / 6 }, 4);
            }
            n7 <<= 1;
        }
        int n9;
        for (n9 = 0; n9 < 3; ++n9) {
            if ((this.type & n7) != 0x0) {
                this.offGraphics.setColor(this.colors[8]);
                this.offGraphics.fillPolygon(new int[] { this.centx + this.vec[n9][0], this.centx + this.vec[n9][0] + (this.vec[n9 + 1][0] + 1) / 3, this.centx + 2 * this.vec[n9][0] / 3, this.centx + this.vec[n9][0] + (this.vec[n9 + 2][0] + 1) / 3 }, new int[] { this.centy + this.vec[n9][1], this.centy + this.vec[n9][1] + (this.vec[n9 + 1][1] + 1) / 3, this.centy + 2 * this.vec[n9][1] / 3, this.centy + this.vec[n9][1] + (this.vec[n9 + 2][1] + 1) / 3 }, 4);
                this.offGraphics.fillPolygon(new int[] { this.centx + this.vec[n9 + 1][0] + this.vec[n9 + 2][0], this.centx + (2 * this.vec[n9 + 1][0] + 3 * this.vec[n9 + 2][0]) / 3, this.centx + (3 * this.vec[n9 + 1][0] + 2 * this.vec[n9 + 2][0]) / 3 }, new int[] { this.centy + this.vec[n9 + 1][1] + this.vec[n9 + 2][1], this.centy + (2 * this.vec[n9 + 1][1] + 3 * this.vec[n9 + 2][1]) / 3, this.centy + (3 * this.vec[n9 + 1][1] + 2 * this.vec[n9 + 2][1]) / 3 }, 3);
            }
            n7 <<= 1;
        }
        if ((this.type & n7) != 0x0) {
            this.offGraphics.setColor(this.colors[8]);
            this.offGraphics.fillPolygon(new int[] { this.centx + (this.vec[n9][0] + 1) / 3, this.centx + (this.vec[n9 + 1][0] + 1) / 3, this.centx + (this.vec[n9 + 2][0] + 1) / 3 }, new int[] { this.centy + (this.vec[n9][1] + 1) / 3, this.centy + (this.vec[n9 + 1][1] + 1) / 3, this.centy + (this.vec[n9 + 2][1] + 1) / 3 }, 3);
        }
        int n10 = n7 << 1;
        this.offGraphics.setColor(this.colors[0]);
        for (int n11 = 0; n11 < 3; ++n11) {
            this.offGraphics.drawLine(this.centx, this.centy, this.centx + this.vec[n11][0], this.centy + this.vec[n11][1]);
            this.offGraphics.drawLine(this.centx + this.vec[n11][0], this.centy + this.vec[n11][1], this.centx + this.vec[n11][0] + this.vec[n11 + 1][0], this.centy + this.vec[n11][1] + this.vec[n11 + 1][1]);
            this.offGraphics.drawLine(this.centx + this.vec[n11][0] + this.vec[n11 + 1][0], this.centy + this.vec[n11][1] + this.vec[n11 + 1][1], this.centx + this.vec[n11 + 1][0], this.centy + this.vec[n11 + 1][1]);
            this.offGraphics.drawPolyline(new int[] { this.centx, this.centx + this.vec[n11][0], this.centx + this.vec[n11][0] + this.vec[n11 + 1][0], this.centx + this.vec[n11 + 1][0] }, new int[] { this.centy, this.centy + this.vec[n11][1], this.centy + this.vec[n11][1] + this.vec[n11 + 1][1], this.centy + this.vec[n11 + 1][1] }, 4);
        }
        for (int n12 = 0; n12 < 3; ++n12) {
            if ((this.type & n10) != 0x0) {
                this.offGraphics.setColor(this.colors[7]);
                this.offGraphics.drawLine(this.centx + this.vec[n12][0] / 3, this.centy + this.vec[n12][1] / 3, this.centx + 2 * this.vec[n12][0] / 3, this.centy + 2 * this.vec[n12][1] / 3);
            }
            n10 <<= 1;
        }
        for (int n13 = 0; n13 < 3; ++n13) {
            if ((this.type & n10) != 0x0) {
                this.offGraphics.setColor(this.colors[7]);
                this.offGraphics.drawLine(this.centx + this.vec[n13 + 1][0] + this.vec[n13][0] / 3, this.centy + this.vec[n13 + 1][1] + this.vec[n13][1] / 3, this.centx + this.vec[n13 + 1][0] + 2 * this.vec[n13][0] / 3, this.centy + this.vec[n13 + 1][1] + 2 * this.vec[n13][1] / 3);
                this.offGraphics.drawLine(this.centx + this.vec[n13 + 2][0] + this.vec[n13][0] / 3, this.centy + this.vec[n13 + 2][1] + this.vec[n13][1] / 3, this.centx + this.vec[n13 + 2][0] + 2 * this.vec[n13][0] / 3, this.centy + this.vec[n13 + 2][1] + 2 * this.vec[n13][1] / 3);
            }
            n10 <<= 1;
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    int checkSym(final int n) {
        final int[] array = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 8192, 16384, 32768, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 524288, 1048576, 2097152, 4194304, 9216, 18432, 36864, 73728, 147456, 294912, 57344, 75505664, 151011328, 302022656, 8388738, 16777476, 33554952, 67109008, 134218016, 268436032, 67108867, 134217733, 268435465, 8388625, 16777249, 33554497, 8321, 16641, 33281, 8210, 16420, 32840, 8960, 17024, 33152, 117964800, 177209344, 295698432, 473956352, 524289, 1048577, 2097153, 4194305, 524386, 1048660, 2097208, 4194318, 75620352, 151183360, 302309376, 75505811, 151011621, 302023241, 75507456, 151014016, 302027136, 58241, 58386, 59428, 61512, 74881, 149761, 299521, 74514, 148132, 295368, 7921664, 117964899, 177209429, 295698489, 473956367, 75622291, 151186341, 302314441, 536862720, 7922561, 7928958, 536870911 };
        if (n == 0) {
            return 0;
        }
        int n2;
        for (n2 = 0; (n & array[n2]) != n; ++n2) {}
        return array[n2];
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean b = mouseEvent.isShiftDown() || mouseEvent.isAltDown();
        final boolean controlDown = mouseEvent.isControlDown();
        this.repaint();
        mouseEvent.consume();
        if (this.main != null) {
            this.main.actionPerformed(new ActionEvent(this, 1001, b ? "s" : (controlDown ? "c" : "")));
        }
    }
}
