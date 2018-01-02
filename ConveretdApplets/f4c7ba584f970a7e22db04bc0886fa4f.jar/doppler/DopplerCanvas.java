// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.awt.Event;
import edu.davidson.display.Format;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;
import java.awt.Canvas;

final class DopplerCanvas extends Canvas
{
    Applet applet;
    private double xs;
    private double ys;
    private double vxs;
    private double vys;
    private Vector genVec;
    private double sourceTime;
    private double labTime;
    private Image buff_image;
    private int buff_width;
    private int buff_height;
    private boolean relativistic;
    private boolean showCoord;
    private int xCoord;
    private int yCoord;
    int pixPerUnit;
    String caption;
    Font f;
    boolean timeDisplay;
    
    public DopplerCanvas(final Applet applet) {
        this.applet = null;
        this.xs = 0.0;
        this.genVec = new Vector();
        this.sourceTime = 0.0;
        this.labTime = 0.0;
        this.buff_image = null;
        this.buff_width = 0;
        this.buff_height = 0;
        this.relativistic = false;
        this.showCoord = false;
        this.pixPerUnit = 10;
        this.caption = "Doppler";
        this.f = new Font("Helvetica", 1, 14);
        this.timeDisplay = true;
        this.applet = applet;
    }
    
    public void reset() {
        this.xs = 0.0;
        this.ys = this.size().height / 2;
        this.labTime = 0.0;
        this.sourceTime = 10.0;
        this.genVec.removeAllElements();
    }
    
    public synchronized void resetSource() {
        this.xs = 0.0;
        this.ys = this.size().height / 2;
        this.labTime = 0.0;
        this.sourceTime = 0.0;
        this.genVec.removeAllElements();
    }
    
    public synchronized void setX(final int n) {
        this.xs = n;
        this.ys = this.size().height / 2;
        this.labTime = 0.0;
        this.sourceTime = 0.0;
        this.genVec.removeAllElements();
    }
    
    public synchronized void translateSource(final int n) {
        final int size = this.genVec.size();
        this.xs += n;
        this.ys = this.size().height / 2;
        if (size > 0) {
            for (int i = 0; i < size; ++i) {
                ((DopplerWaveCrest)this.genVec.elementAt(i)).translate(n);
            }
        }
    }
    
    public void setSpeed(final double vxs, final double vys) {
        this.vxs = vxs;
        this.vys = vys;
    }
    
    public void setRelativistic(final boolean relativistic) {
        if (this.relativistic != relativistic) {
            this.reset();
        }
        this.relativistic = relativistic;
    }
    
    public synchronized void incTime(final double n) {
        ++this.labTime;
        if (this.relativistic) {
            this.sourceTime += 1.0 * Math.sqrt(1 - n * n);
        }
        else {
            ++this.sourceTime;
        }
        this.xs += this.vxs;
        if (this.xs > this.size().width) {
            this.translateSource(-this.size().width);
        }
        if (this.sourceTime > this.pixPerUnit) {
            this.sourceTime -= this.pixPerUnit;
            if (this.genVec.size() > 10) {
                this.genVec.removeElementAt(0);
            }
            this.genVec.addElement(new DopplerWaveCrest(this.labTime, (int)this.xs, (int)this.ys, this.vxs, this.vxs));
        }
        final Graphics graphics = this.getGraphics();
        this.paint(graphics);
        graphics.dispose();
    }
    
    private void calcBuffImage() {
        final int size = this.genVec.size();
        final Graphics graphics = this.buff_image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.buff_width, this.buff_height);
        if (size > 0) {
            for (int i = 0; i < size; ++i) {
                graphics.setColor(new Color(255 * (size - i) / size, 255 * (size - i) / size, 255 * (size - i) / size));
                ((DopplerWaveCrest)this.genVec.elementAt(i)).draw(this.labTime, graphics);
            }
        }
        graphics.setColor(Color.red);
        graphics.fillOval((int)(this.xs - 2), (int)(this.ys - 2), 4, 4);
        graphics.setColor(Color.black);
        graphics.setFont(this.f);
        graphics.drawString(this.caption, (this.buff_width - graphics.getFontMetrics(this.f).stringWidth(this.caption)) / 2, 20);
        graphics.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.buff_image == null || this.size().width != this.buff_width || this.size().height != this.buff_height) {
            this.ys = this.size().height / 2;
            this.buff_width = this.size().width;
            this.buff_height = this.size().height;
            this.buff_image = this.createImage(this.buff_width, this.buff_height);
            this.reset();
        }
        this.calcBuffImage();
        graphics.drawImage(this.buff_image, 0, 0, this);
        final String form = new Format("%7.4g").form(this.labTime / this.pixPerUnit);
        final String form2 = new Format("%7.4g").form(this.pixToX((int)this.xs));
        if (this.timeDisplay) {
            graphics.drawString(String.valueOf("Time: ").concat(String.valueOf(form)), 10, 15);
            if (!this.showCoord) {
                graphics.drawString(String.valueOf("Source x: ").concat(String.valueOf(form2)), 10, this.buff_height - 15);
            }
        }
    }
    
    private double pixToX(final int n) {
        return (n - this.buff_width / 2) / (1.0 * this.pixPerUnit);
    }
    
    private double pixToY(final int n) {
        return -(n - this.buff_height / 2) / (1.0 * this.pixPerUnit);
    }
    
    public void startDrawCoord(final int xCoord, final int yCoord) {
        this.applet.stop();
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        final Graphics graphics = this.getGraphics();
        this.paint(graphics);
        graphics.clearRect(8, this.buff_height - 30, 150, 20);
        graphics.drawString(String.valueOf(String.valueOf(String.valueOf("X: ").concat(String.valueOf(this.pixToX(this.xCoord)))).concat(String.valueOf("  Y: "))).concat(String.valueOf(this.pixToY(this.yCoord))), 10, this.buff_height - 15);
        graphics.setColor(Color.red);
        graphics.drawLine(this.xCoord - 10, this.yCoord, this.xCoord + 10, this.yCoord);
        graphics.drawLine(this.xCoord, this.yCoord - 10, this.xCoord, this.yCoord + 10);
        graphics.dispose();
        this.showCoord = true;
    }
    
    public void endDrawCoord(final int n, final int n2) {
        this.applet.start();
        this.showCoord = false;
        this.repaint();
    }
    
    public void drawCoord(final int xCoord, final int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        if (this.showCoord) {
            final Graphics graphics = this.getGraphics();
            this.paint(graphics);
            graphics.clearRect(8, this.buff_height - 30, 150, 20);
            graphics.drawString(String.valueOf(String.valueOf(String.valueOf("X: ").concat(String.valueOf(this.pixToX(this.xCoord)))).concat(String.valueOf("  Y: "))).concat(String.valueOf(this.pixToY(this.yCoord))), 10, this.buff_height - 15);
            graphics.setColor(Color.red);
            graphics.drawLine(this.xCoord - 10, this.yCoord, this.xCoord + 10, this.yCoord);
            graphics.drawLine(this.xCoord, this.yCoord - 10, this.xCoord, this.yCoord + 10);
            graphics.dispose();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x4) != 0x0 || (event.modifiers & 0x8) != 0x0) {
            return false;
        }
        this.startDrawCoord(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x4) != 0x0 || (event.modifiers & 0x8) != 0x0) {
            return false;
        }
        this.endDrawCoord(n, n2);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x4) != 0x0 || (event.modifiers & 0x8) != 0x0) {
            return false;
        }
        this.drawCoord(n, n2);
        return true;
    }
    
    public void setCaption(final String caption) {
        this.caption = caption;
        this.repaint();
    }
}
