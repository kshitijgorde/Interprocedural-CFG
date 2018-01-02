import java.util.Calendar;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.KeyListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDTextArea extends Panel implements KeyListener
{
    int nXPos;
    int nYPos;
    int nWidth;
    int nHeight;
    TextArea txt;
    SVDCommunicator svdApplet;
    
    public SVDTextArea(final SVDCommunicator aApplet, final int aXPos, final int aYPos, final int aWidth, final int aHeight) {
        this.svdApplet = aApplet;
        this.nWidth = aWidth;
        this.nHeight = aHeight;
        this.setBounds(aXPos, aYPos, this.nWidth, this.nHeight);
        this.setLayout(null);
        this.add(this.txt = new TextArea("", 10, 10, 3));
        this.txt.setBounds(-2, -2, this.nWidth + 4, this.nHeight + 4);
        this.txt.setFont(new Font("Dialog", 0, 11));
        this.txt.setForeground(Color.black);
        this.txt.setBackground(Color.white);
        this.txt.setEditable(true);
        this.txt.addKeyListener(this);
    }
    
    public void keyPressed(final KeyEvent ke) {
        if (ke.getKeyCode() >= 32) {
            final Calendar calendar = Calendar.getInstance();
            this.svdApplet.dateLast = calendar.getTime();
            if (!this.svdApplet.bIsWriting) {
                final SVDMessage msg = new SVDMessage(this.svdApplet);
                msg.btCommand = 101;
                msg.btReserved = 121;
                msg.idSesiune = this.svdApplet.nSessionHandle;
                msg.idSender = this.svdApplet.nSessionID;
                msg.idDest = 0;
                msg.nSizeOfMessage = 20;
                msg.writeHeader();
                msg.sendMessage(this.svdApplet.netText);
            }
            this.svdApplet.bIsWriting = true;
        }
        if (this.txt.getText().length() >= 255 && ke.getKeyChar() != '\n' && ke.getKeyChar() != '\n') {
            this.txt.setText(this.txt.getText().substring(0, 255));
            this.txt.setCaretPosition(300);
            return;
        }
        if (ke.getKeyChar() == '\n' && this.txt.getText().length() > 0) {
            if (this.txt.getText().charAt(0) != '\n' && this.txt.getText().charAt(0) != '\r') {
                final String sMessage = this.txt.getText();
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
            this.txt.setText("");
        }
    }
    
    public void keyTyped(final KeyEvent ke) {
        if (this.txt.getText().length() >= 255 && ke.getKeyChar() != '\n' && ke.getKeyChar() != '\n') {
            this.txt.setText(this.txt.getText().substring(0, 255));
            this.txt.setCaretPosition(300);
            return;
        }
        if (ke.getKeyChar() == '\n' || ke.getKeyChar() == '\r') {
            this.txt.setText("");
        }
    }
    
    public void keyReleased(final KeyEvent ke) {
        if (this.txt.getText().length() >= 255 && ke.getKeyChar() != '\n' && ke.getKeyChar() != '\n') {
            this.txt.setText(this.txt.getText().substring(0, 255));
            this.txt.setCaretPosition(300);
            return;
        }
        if (ke.getKeyChar() == '\n') {
            this.txt.setText("");
        }
    }
}
