// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.AWTEventMulticaster;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class NICButton extends Component implements MouseListener, MouseMotionListener, Runnable
{
    Color backColor;
    Color textColor;
    Color borderColor;
    int borderWidth;
    String text;
    int shape;
    private Image icon;
    private Object iconLock;
    private int y;
    protected boolean me;
    protected boolean md;
    private Thread tme;
    private int tmep;
    private int atmep;
    private Object tmeLock;
    private ActionListener al;
    private String actionCommand;
    private Dimension ps;
    
    public NICButton(final String text, final int n, final int n2) {
        this.icon = null;
        this.iconLock = new Object();
        this.me = false;
        this.md = false;
        this.tme = null;
        this.tmep = 101;
        this.tmeLock = new Object();
        this.al = null;
        this.actionCommand = null;
        this.ps = new Dimension(200, 30);
        this.backColor = new Color(240, 240, 240);
        this.textColor = new Color(0, 0, 255);
        this.borderColor = new Color(240, 0, 0);
        this.setFont(new Font("Helvetica", 0, 12));
        this.text = text;
        this.shape = 0;
        this.borderWidth = 1;
        this.setSize(n, n2);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public NICButton(final String text, final int n, final int n2, final ActionListener al) {
        this.icon = null;
        this.iconLock = new Object();
        this.me = false;
        this.md = false;
        this.tme = null;
        this.tmep = 101;
        this.tmeLock = new Object();
        this.al = null;
        this.actionCommand = null;
        this.ps = new Dimension(200, 30);
        this.al = al;
        this.backColor = new Color(240, 240, 240);
        this.textColor = new Color(0, 0, 255);
        this.borderColor = new Color(240, 0, 0);
        this.setFont(new Font("Helvetica", 0, 12));
        this.text = text;
        this.shape = 0;
        this.borderWidth = 1;
        this.setSize(n, n2);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.al = AWTEventMulticaster.add(this.al, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.al = AWTEventMulticaster.remove(this.al, actionListener);
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    void setIcon(final Image icon) {
        synchronized (this.iconLock) {
            this.icon = icon;
        }
        this.repaint();
    }
    
    public void setSize(final int n, final int n2) {
        this.ps = new Dimension(n, n2);
        super.setSize(n, n2);
        if (this.getSize().height <= this.getSize().width) {
            this.y = this.getSize().height / 2;
        }
        else {
            this.y = this.getSize().width / 2;
        }
    }
    
    public Color makeLighter(final Color color, final int n, final int n2, final int n3) {
        int n4 = color.getRed() + n;
        int n5 = color.getGreen() + n2;
        int n6 = color.getBlue() + n3;
        if (n4 > 255) {
            n4 = 255;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        return new Color(n4, n5, n6);
    }
    
    public void run() {
        while (this.tme == Thread.currentThread()) {
            try {
                Thread.currentThread();
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
            synchronized (this.tmeLock) {
                this.tmep += this.atmep;
                if (this.tmep <= 0 || this.tmep > 100) {
                    this.tme = null;
                }
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setFont(this.getFont());
        Color color = this.backColor;
        if (this.md) {
            color = this.makeLighter(this.backColor, -20, -20, -20);
        }
        graphics.setColor(color);
        graphics.fillRect(0, this.y, this.getSize().width - 1, this.getSize().height - this.y - 1);
        for (int i = 0; i <= this.y; ++i) {
            graphics.setColor(new Color(this.clalcX(color.getRed(), this.y, i, true), this.clalcX(color.getGreen(), this.y, i, true), this.clalcX(color.getBlue(), this.y, i, true)));
            graphics.drawLine(0, this.y - i, this.getSize().width - 1, this.y - i);
            graphics.setColor(new Color(this.clalcX(color.getRed(), this.y, i, false), this.clalcX(color.getGreen(), this.y, i, false), this.clalcX(color.getBlue(), this.y, i, false)));
            graphics.drawLine(0, height - this.y + i - 1, width - 1, height - this.y + i - 1);
        }
        switch (this.shape) {
            case 0: {
                for (int j = 0; j <= this.y; ++j) {
                    graphics.setColor(new Color(this.clalcX(color.getRed(), this.y, j, true), this.clalcX(color.getGreen(), this.y, j, true), this.clalcX(color.getBlue(), this.y, j, true)));
                    graphics.drawLine(this.y - j, this.y - j, this.y - j, this.getSize().height - (this.y - j) - 1);
                    graphics.setColor(new Color(this.clalcX(color.getRed(), this.y, j, false), this.clalcX(color.getGreen(), this.y, j, false), this.clalcX(color.getBlue(), this.y, j, false)));
                    graphics.drawLine(width - this.y + j - 1, this.y - j, width - this.y + j - 1, height - (this.y - j) - 1);
                }
                break;
            }
            case 1: {
                final Color lighter = this.makeLighter(color, -100, -100, -100);
                final Color lighter2 = this.makeLighter(color, -50, -50, -50);
                if (this.md) {
                    graphics.setColor(lighter2);
                }
                else {
                    graphics.setColor(lighter);
                }
                graphics.drawLine(width - 1, 0, width - 1, height - 1);
                if (this.md) {
                    graphics.setColor(lighter);
                }
                else {
                    graphics.setColor(lighter2);
                }
                graphics.drawLine(0, 0, 0, height - 1);
                break;
            }
        }
        final int md = this.md ? 1 : 0;
        int n = 0;
        int n2 = 0;
        int max = 0;
        int n3 = width / 2;
        int n4 = 0;
        int height2 = 0;
        FontMetrics fontMetrics = null;
        synchronized (this.iconLock) {
            if (this.icon != null) {
                final int width2 = this.icon.getWidth(this);
                height2 = this.icon.getHeight(this);
                n2 = width2;
                n = width2 + 2;
                n3 = (width - n2) / 2;
                max = height2;
            }
            if (this.text != null) {
                fontMetrics = graphics.getFontMetrics();
                graphics.setColor(this.textColor);
                n2 = fontMetrics.stringWidth(this.text) + n;
                max = Math.max(fontMetrics.getHeight(), max);
                n3 = (width - n2) / 2;
                n4 = (height - fontMetrics.getHeight()) / 2;
            }
            if (this.icon != null) {
                final int round = (height - height2) / 2;
                graphics.drawImage(this.icon, n3 + md, round + md, this);
            }
        }
        if (this.text != null) {
            graphics.drawString(this.text, n3 + n + md, n4 + md + fontMetrics.getAscent());
        }
        if (this.tmep <= 100) {
            final int n5 = (height - max) / 2;
            final int round;
            final int round2;
            synchronized (this.tmeLock) {
                round = Math.round(this.tmep / 100.0f * n3);
                round2 = Math.round(this.tmep / 100.0f * n5);
            }
            for (int k = 1; k < this.borderWidth + 1; ++k) {
                graphics.setColor(new Color(100, 100, 100));
                graphics.drawRect(n3 - k + md - round, n5 - k + md - round2, n2 - 1 + 2 * k + 2 * round, max - 1 + 2 * k + 2 * round2);
                graphics.setColor(this.borderColor);
                graphics.drawRect(n3 - k - round, n5 - k - round2, n2 - 1 + 2 * k + 2 * round, max - 1 + 2 * k + 2 * round2);
            }
        }
    }
    
    private int clalcX(final int n, final int n2, final int n3, final boolean b) {
        int n4 = n - 80;
        if (b) {
            n4 += (this.md ? 40 : -40);
        }
        if (n4 < 0) {
            n4 = 0;
        }
        else if (n4 > 255) {
            n4 = 255;
        }
        final double n5 = n3 * (Math.sqrt(n4) / n2);
        return n - (int)(n5 * n5);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.getX() > 0 && mouseEvent.getX() < this.getSize().width - 1 && mouseEvent.getY() > 0 && mouseEvent.getY() < this.getSize().height - 1) {
            if (this.md && this.me) {
                return;
            }
            this.md = true;
            this.me = true;
        }
        else {
            if (!this.md && !this.me) {
                return;
            }
            this.md = false;
            this.me = false;
        }
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.me = true;
        this.repaint();
        synchronized (this.tmeLock) {
            this.atmep = -10;
            if (this.tme == null) {
                this.tme = new Thread(this);
                this.tmep = 100;
                this.tme.start();
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.me = false;
        this.repaint();
        synchronized (this.tmeLock) {
            this.atmep = 20;
            if (this.tme == null) {
                (this.tme = new Thread(this)).start();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.md = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.md) {
            return;
        }
        this.md = false;
        this.repaint();
        if (this.me && this.al != null) {
            this.al.actionPerformed(new ActionEvent(this, 1001, (this.actionCommand == null) ? this.text : this.actionCommand));
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public boolean isOpaque() {
        return true;
    }
    
    public Dimension getPreferredSize() {
        return this.ps;
    }
    
    public void setText(final String text) {
        this.text = text;
        this.repaint();
    }
}
