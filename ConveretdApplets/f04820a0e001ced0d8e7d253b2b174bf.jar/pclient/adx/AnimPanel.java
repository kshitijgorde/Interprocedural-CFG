// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import pclient.anim.AnimRenderer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;
import pclient.adv.AppletSpice;
import pclient.anim.FacText;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class AnimPanel extends JPanel implements Runnable, MouseListener
{
    private FacText textFactory;
    private TextAttribute[] textArray;
    private boolean keepGoing;
    private long displayTime;
    private int numberRounds;
    private int fontSize;
    private int currentIndex;
    private AppletSpice pChat;
    
    public AnimPanel(final AppletSpice pChat, final TextAttribute[] textArray, final int n, final int numberRounds, final int fontSize) {
        this.textFactory = null;
        this.textArray = null;
        this.keepGoing = true;
        this.displayTime = 20000L;
        this.numberRounds = 0;
        this.fontSize = 14;
        this.currentIndex = 0;
        this.pChat = pChat;
        this.textArray = textArray;
        if (n > 0) {
            this.displayTime = n * 1000;
        }
        if (fontSize > 0) {
            this.fontSize = fontSize;
        }
        this.addMouseListener(this);
        this.numberRounds = numberRounds;
        new Thread(this).start();
    }
    
    public void stopIt() {
        this.keepGoing = false;
    }
    
    public Dimension getPreferredSize() {
        final Font font = this.createFont();
        final int n = 300;
        if (font == null) {
            return new Dimension(n, 16);
        }
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return new Dimension(n, 2 + fontMetrics.getAscent() + fontMetrics.getDescent());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.currentIndex >= this.textArray.length) {
            return;
        }
        if (this.textArray[this.currentIndex].linkText != null) {
            this.pChat.paraConf.loadPage(this.textArray[this.currentIndex].linkText);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.currentIndex >= this.textArray.length) {
            return;
        }
        if (this.textArray[this.currentIndex].linkText != null) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void run() {
        int n = 0;
        this.pause(5000L);
        this.keepGoing = true;
        while (this.keepGoing) {
            if (this.textArray == null) {
                break;
            }
            if (this.textFactory == null) {
                this.textFactory = new FacText();
            }
            for (int i = 0; i < this.textArray.length; ++i) {
                this.currentIndex = i;
                if (this.textArray[i].isActive) {
                    try {
                        this.showText(this.textArray[i], this.textFactory);
                    }
                    catch (Exception ex) {
                        System.out.println("Err AM3764.");
                        ex.printStackTrace();
                    }
                }
                this.pause(1000L);
            }
            if (this.numberRounds > 0 && ++n >= this.numberRounds) {
                break;
            }
            this.pause(300L);
        }
        this.showLast();
        this.repaint();
    }
    
    private void showLast() {
        if (this.numberRounds <= 0) {
            return;
        }
        if (this.textArray.length == 0) {
            return;
        }
        int n = -1;
        for (int i = this.textArray.length - 1; i >= 0; --i) {
            if (this.textArray[i].isActive) {
                n = i;
                break;
            }
        }
        if (n < 0) {
            return;
        }
        final TextHyper textHyper = new TextHyper();
        this.setLayout(new BorderLayout());
        this.add("Center", textHyper);
        textHyper.setApplet(this.pChat);
        if (this.textArray[n].foreground != null) {
            textHyper.setForeground(this.textArray[n].foreground);
        }
        if (this.textArray[n].background != null) {
            textHyper.setBackground(this.textArray[n].background);
        }
        textHyper.setFontSize(this.fontSize);
        textHyper.setHyper(this.textArray[n].dText, this.textArray[n].linkText);
        textHyper.invalidate();
        this.validate();
    }
    
    private void showText(final TextAttribute textAttribute, final FacText facText) {
        if (!this.isShowing()) {
            return;
        }
        final Font font = this.createFont();
        final Graphics graphics = this.getGraphics();
        if (font == null) {
            System.out.println("#Err447,font null.");
            return;
        }
        if (graphics == null) {
            System.out.println("gc null.");
            return;
        }
        this.pChat.paraConf.printer().print("anim GC=" + graphics);
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        Color color = textAttribute.foreground;
        Color color2 = textAttribute.background;
        this.pChat.paraConf.printer().print("anim, fg and bg," + color + " " + color2);
        if (color == null) {
            color = Color.black;
        }
        if (color2 == null) {
            color2 = Color.white;
        }
        this.pChat.paraConf.printer().print("anim, fg and bg " + color + " " + color2);
        this.loopIt(facText.obtainRandomType(this.displayTime, this, graphics, font, textAttribute.dText, width, height, color, color2));
    }
    
    private Font createFont() {
        Font font2;
        final Font font = font2 = this.getFont();
        if (font != null) {
            font2 = new Font(font.getName(), 1, this.fontSize);
        }
        if (font2 != null) {
            return font2;
        }
        return font;
    }
    
    private void loopIt(final AnimRenderer animRenderer) {
        animRenderer.initialize();
        while (this.isShowing()) {
            final long runOneStep = animRenderer.runOneStep(this.getGraphics());
            if (runOneStep == 0L) {
                return;
            }
            this.pause(runOneStep);
        }
    }
    
    private void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
}
