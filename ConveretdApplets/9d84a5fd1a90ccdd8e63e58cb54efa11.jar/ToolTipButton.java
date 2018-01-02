import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class ToolTipButton extends Container implements MouseListener
{
    protected ButtonCtrl m_parent;
    protected Rosa2000 m_applet;
    private Point m_Position;
    protected Image m_offScreenImage;
    protected Graphics m_offScreenGraphics;
    private Dimension m_dimension;
    private Vector m_aStrings;
    DelayThread delayThread;
    Font hintFont;
    
    public ToolTipButton(final Rosa2000 applet, final ButtonCtrl parent) {
        this.m_parent = null;
        this.m_applet = null;
        this.m_Position = new Point(0, 0);
        this.m_offScreenImage = null;
        this.m_offScreenGraphics = null;
        this.m_dimension = new Dimension(-1, -1);
        this.m_aStrings = new Vector();
        this.delayThread = new DelayThread();
        this.m_parent = parent;
        this.m_applet = applet;
        this.hintFont = new Font("Courier", 0, 12);
        this.delayThread.start();
    }
    
    public String getToolTip(final MouseEvent e) {
        return this.m_parent.getHint();
    }
    
    public synchronized void mouseEntered(final MouseEvent e) {
        if (this.m_parent.getHint() != null) {
            this.determineSize();
            this.m_Position = this.m_parent.getHintPosition();
            this.delayThread.arm();
        }
    }
    
    public synchronized void mouseExited(final MouseEvent e) {
        if (this.m_parent.getHint() != null) {
            this.delayThread.disarm();
            this.hideToolTip();
        }
    }
    
    public synchronized void mousePressed(final MouseEvent evt) {
        if (this.m_parent.getHint() != null) {
            this.delayThread.disarm();
            this.hideToolTip();
        }
    }
    
    public void paint(final Graphics g) {
        if (this.m_parent.getHint() != null) {
            final Dimension oSize = this.getSize();
            if (this.m_offScreenImage == null) {
                this.m_offScreenImage = this.m_applet.createImage(oSize.width, oSize.height);
                this.m_offScreenGraphics = this.m_offScreenImage.getGraphics();
            }
            this.m_offScreenGraphics.setColor(new Color(255, 255, 204));
            this.m_offScreenGraphics.fillRect(0, 0, oSize.width, oSize.height);
            this.m_offScreenGraphics.setColor(new Color(0, 0, 0));
            this.m_offScreenGraphics.drawRect(0, 0, oSize.width - 1, oSize.height - 1);
            this.m_offScreenGraphics.setColor(new Color(0, 0, 0));
            this.m_offScreenGraphics.setFont(this.hintFont);
            final Enumeration enum1 = this.m_aStrings.elements();
            double i = 1.0;
            final double dfTotal = this.m_aStrings.size();
            while (enum1.hasMoreElements()) {
                final int nHeight = (int)(oSize.height * (i / dfTotal) - 4.0);
                this.m_offScreenGraphics.drawString(enum1.nextElement(), 2, nHeight);
                ++i;
            }
            g.drawImage(this.m_offScreenImage, this.m_Position.x, this.m_Position.y, this);
        }
    }
    
    private void determineSize() {
        final Dimension result = null;
        int nWidth = 0;
        int nHeight = 0;
        String sHint = null;
        int nMaxWidth = -1;
        if (this.m_dimension.width < 0 || this.m_dimension.height < 0) {
            sHint = this.m_parent.getHint();
            if (sHint != null) {
                final StringTokenizer st = new StringTokenizer(sHint, "|");
                while (st.hasMoreTokens()) {
                    final String sTmp = st.nextToken();
                    this.m_aStrings.addElement(new String(sTmp));
                }
                final Enumeration enum1 = this.m_aStrings.elements();
                while (enum1.hasMoreElements()) {
                    final String sTmp2 = enum1.nextElement();
                    nWidth = this.m_applet.getGraphics().getFontMetrics(this.hintFont).stringWidth(sTmp2);
                    if (nWidth > nMaxWidth) {
                        nMaxWidth = nWidth;
                    }
                }
                nMaxWidth += 4;
                nHeight = this.m_applet.getGraphics().getFontMetrics(this.hintFont).getHeight() * this.m_aStrings.size();
            }
            this.m_dimension.width = nMaxWidth;
            this.m_dimension.height = nHeight;
            this.setSize(this.m_dimension);
        }
    }
    
    public Dimension getSize() {
        if (this.m_dimension.width <= 0 || this.m_dimension.height <= 0) {
            this.determineSize();
        }
        return this.m_dimension;
    }
    
    private void showToolTip() {
        this.paint(this.m_applet.getGraphics());
    }
    
    private void hideToolTip() {
        this.m_applet.repaint(this.m_Position.x, this.m_Position.y, this.getSize().width, this.getSize().height);
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    class DelayThread extends Thread
    {
        boolean armed;
        boolean reset;
        
        DelayThread() {
            this.armed = false;
            this.reset = false;
        }
        
        public void run() {
            while (true) {
                if (this.armed) {
                    try {
                        Thread.sleep(250L);
                    }
                    catch (InterruptedException ex) {}
                    if (this.armed && !this.reset) {
                        this.armed = false;
                        this.fire();
                    }
                    this.reset = false;
                }
                else {
                    try {
                        Thread.sleep(250L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
        
        protected void arm() {
            this.armed = true;
            this.reset = true;
            this.interrupt();
        }
        
        protected void disarm() {
            this.armed = false;
            this.interrupt();
        }
        
        protected void reset() {
            this.reset = true;
            this.interrupt();
        }
        
        protected void fire() {
            ToolTipButton.this.showToolTip();
        }
    }
}
