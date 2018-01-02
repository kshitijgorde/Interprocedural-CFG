// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Image;
import java.awt.Canvas;

public final class JAPAboutAutoScroller extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Image m_imgOffScreen;
    private Image m_imgBackground;
    private Image m_imgDoubleBuffer;
    private Image m_imgBackgroundPicture;
    private int m_iScrollAreaWidth;
    private int m_iScrollAreaHeight;
    private int m_iScrollAreaX;
    private int m_iScrollAreaY;
    private int m_iaktY;
    private int m_iTextHeight;
    private int m_iWidth;
    private int m_iHeight;
    private JEditorPane m_textArea;
    private Thread m_Thread;
    private int m_msSleep;
    private volatile boolean m_bRun;
    private Object oSync;
    private boolean isPainting;
    private JButton m_bttnOk;
    
    public JAPAboutAutoScroller(final int iWidth, final int iHeight, final Image imgBackgroundPicture, final int iScrollAreaX, final int iScrollAreaY, final int iScrollAreaWidth, final int iScrollAreaHeight, final String s) {
        this.oSync = new Object();
        this.isPainting = false;
        this.m_iScrollAreaWidth = iScrollAreaWidth;
        this.m_iScrollAreaHeight = iScrollAreaHeight;
        this.m_iScrollAreaX = iScrollAreaX;
        this.m_iScrollAreaY = iScrollAreaY;
        this.setSize(this.m_iWidth = iWidth, this.m_iHeight = iHeight);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (JAPAboutAutoScroller.this.m_bttnOk.getBounds().contains(mouseEvent.getPoint())) {
                    JAPAboutAutoScroller.this.m_bttnOk.doClick();
                }
            }
        });
        this.m_imgBackgroundPicture = imgBackgroundPicture;
        (this.m_textArea = new JEditorPane()).setEditable(false);
        this.m_textArea.setDoubleBuffered(false);
        this.m_textArea.setBackground(new Color(204, 204, 204));
        this.m_textArea.setSize(this.m_iScrollAreaWidth, 10000);
        this.m_textArea.setContentType("text/html");
        this.m_textArea.setText(s.trim());
        this.m_iTextHeight = this.m_textArea.getPreferredSize().height;
        (this.m_bttnOk = new JButton("Ok")).setMnemonic('O');
        this.m_bttnOk.setOpaque(false);
        this.m_bttnOk.setSelected(true);
        final Dimension preferredSize = this.m_bttnOk.getPreferredSize();
        if (preferredSize.width > 76) {
            preferredSize.width = 76;
        }
        this.m_bttnOk.setSize(preferredSize);
        this.m_Thread = new Thread(this, "JAP - AboutScroller");
        this.m_bRun = false;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.m_bttnOk.addActionListener(actionListener);
    }
    
    public synchronized void startScrolling(final int msSleep) {
        if (this.m_bRun) {
            return;
        }
        this.m_msSleep = msSleep;
        this.m_Thread.start();
    }
    
    public synchronized void stopScrolling() {
        this.m_bRun = false;
        try {
            this.m_Thread.join();
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        synchronized (this.oSync) {
            if (this.isPainting) {
                return;
            }
            this.isPainting = true;
        }
        if (this.m_imgOffScreen == null) {
            this.m_imgOffScreen = this.createImage(this.m_iScrollAreaWidth, this.m_iTextHeight + 2 * this.m_iScrollAreaHeight);
            final Graphics graphics2 = this.m_imgOffScreen.getGraphics();
            try {
                this.m_textArea.paint(graphics2);
            }
            catch (Exception ex) {
                this.m_imgOffScreen = null;
            }
            if (graphics2 != null) {
                graphics2.dispose();
            }
        }
        if (this.m_imgBackground == null) {
            this.m_imgBackground = this.createImage(this.m_iWidth, this.m_iHeight);
            final Graphics graphics3 = this.m_imgBackground.getGraphics();
            graphics3.drawImage(this.m_imgBackgroundPicture, 0, 0, null);
            final int n = this.m_iWidth - 5 - this.m_bttnOk.getSize().width;
            final int n2 = this.m_iHeight - 5 - this.m_bttnOk.getSize().height;
            this.m_bttnOk.setLocation(n, n2);
            graphics3.setFont(new Font("Sans", 0, 9));
            graphics3.setColor(Color.black);
            final FontMetrics fontMetrics = graphics3.getFontMetrics();
            graphics3.drawString("Version", n - 5 - fontMetrics.stringWidth("Version:"), n2);
            graphics3.drawString("00.12.005", n - 5 - fontMetrics.stringWidth("00.12.005"), this.m_iHeight - 5 - fontMetrics.getHeight());
            graphics3.translate(n, n2);
            this.m_bttnOk.paint(graphics3);
            graphics3.dispose();
            this.m_imgDoubleBuffer = this.createImage(this.m_iWidth, this.m_iHeight);
        }
        final Graphics graphics4 = this.m_imgDoubleBuffer.getGraphics();
        graphics4.drawImage(this.m_imgBackground, 0, 0, null);
        if (this.m_imgOffScreen != null) {
            ++this.m_iaktY;
            if (this.m_iaktY <= this.m_iScrollAreaHeight) {
                graphics4.drawImage(this.m_imgOffScreen, this.m_iScrollAreaX, this.m_iScrollAreaY + this.m_iScrollAreaHeight - this.m_iaktY, this.m_iScrollAreaX + this.m_iScrollAreaWidth, this.m_iScrollAreaY + this.m_iScrollAreaHeight, 0, 0, this.m_iScrollAreaWidth, this.m_iaktY, null);
            }
            else {
                graphics4.drawImage(this.m_imgOffScreen, this.m_iScrollAreaX, this.m_iScrollAreaY, this.m_iScrollAreaWidth + this.m_iScrollAreaX, this.m_iScrollAreaHeight + this.m_iScrollAreaY, 0, this.m_iaktY - this.m_iScrollAreaHeight, this.m_iScrollAreaWidth, this.m_iaktY, null);
            }
        }
        graphics4.dispose();
        graphics.drawImage(this.m_imgDoubleBuffer, 0, 0, null);
        this.isPainting = false;
    }
    
    public void run() {
        this.m_iaktY = 0;
        this.m_bRun = true;
        while (this.m_bRun) {
            this.paint(this.getGraphics());
            try {
                Thread.sleep(this.m_msSleep);
            }
            catch (Exception ex) {}
            if (this.m_iaktY > this.m_iTextHeight + this.m_iScrollAreaHeight) {
                this.m_iaktY = 0;
            }
        }
    }
}
