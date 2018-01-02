import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDText extends Panel implements MouseListener, MouseMotionListener
{
    public SVDCommunicator svdApplet;
    public Cursor crNormal;
    public Cursor crHand;
    public SVDChatScroller txtMain;
    public SVDTextArea txtAlt;
    
    public SVDText(final SVDCommunicator aApplet) {
        this.svdApplet = aApplet;
        this.setLayout(null);
        this.setSize(240, 240);
        this.setBackground(Color.white);
        this.add(this.txtMain = new SVDChatScroller(aApplet, 0, 0, 216, 216));
        this.add(this.txtAlt = new SVDTextArea(aApplet, 0, 224, 191, 30));
        this.crHand = new Cursor(12);
        this.crNormal = new Cursor(0);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void mouseClicked(final MouseEvent me) {
        if (me.getX() >= 193 && me.getX() <= 216 && me.getY() >= 224 && me.getY() <= 254) {
            if (this.txtAlt.txt.getText().length() >= 255) {
                this.txtAlt.txt.setText(this.txtAlt.txt.getText().substring(0, 255));
            }
            if (this.txtAlt.txt.getText().length() > 0) {
                if (this.txtAlt.txt.getText().charAt(0) != '\n' && this.txtAlt.txt.getText().charAt(0) != '\r') {
                    final String sMessage = this.txtAlt.txt.getText();
                    if (this.svdApplet.sParamUsername.equals("Visitor") || this.svdApplet.sParamUsername.length() == 0) {
                        final String sEnc = this.svdApplet.cipher.encrypt(sMessage, this.svdApplet.sServerKey, this.svdApplet.sServerFactor);
                        this.svdApplet.sendTextMessage(sEnc, false);
                    }
                    else {
                        final String sEnc = this.svdApplet.cipher.encrypt("[" + this.svdApplet.sParamUsername + "] " + sMessage, this.svdApplet.sServerKey, this.svdApplet.sServerFactor);
                        this.svdApplet.sendTextMessage(sEnc, false);
                    }
                    this.svdApplet.svdText.txtMain.addText(this.svdApplet.sParamUsername + " -> " + sMessage, false);
                    this.svdApplet.svdText.txtMain.myScroll.repaintScrollBar();
                }
                this.txtAlt.txt.setText("");
            }
        }
    }
    
    public void mouseMoved(final MouseEvent me) {
        if (me.getX() >= 193 && me.getX() <= 216 && me.getY() >= 224 && me.getY() <= 254) {
            this.setCursor(this.crHand);
        }
        else {
            this.setCursor(this.crNormal);
        }
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.svdApplet.imgSend, 193, 224, 23, 30, this);
        g.drawImage(this.svdApplet.img06, 0, 216, this);
    }
    
    public void mousePressed(final MouseEvent mouseevent) {
    }
    
    public void mouseDragged(final MouseEvent mouseevent) {
    }
    
    public void mouseReleased(final MouseEvent mouseevent) {
    }
    
    public void mouseEntered(final MouseEvent mouseevent) {
    }
    
    public void mouseExited(final MouseEvent mouseevent) {
    }
}
