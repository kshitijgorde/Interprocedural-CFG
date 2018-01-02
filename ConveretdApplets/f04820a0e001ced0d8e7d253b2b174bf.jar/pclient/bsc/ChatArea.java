// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.event.AdjustmentEvent;
import java.util.StringTokenizer;
import com.pchat.sc.MsgOptions;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class ChatArea extends Panel implements AdjustmentListener, Runnable
{
    private Scrollbar vBar;
    private RichCanvas rCanvas;
    private static final int MAX_BAR = 1000;
    private static final int BUBBLE_SIZE = 64;
    private boolean keepRunning;
    private BaseChat pChat;
    
    public ChatArea(final BaseChat pChat) {
        this.vBar = null;
        this.rCanvas = null;
        this.keepRunning = true;
        this.pChat = pChat;
        this.buildGUI();
        if (!this.pChat.paraConf.getBool("Op.Clickable", true)) {
            this.rCanvas.ignoreURL();
        }
    }
    
    public void setForeground(final Color color) {
        if (this.rCanvas != null) {
            this.rCanvas.setForeground(color);
        }
        super.setForeground(color);
    }
    
    public Color getBackground() {
        if (this.rCanvas != null) {
            return this.rCanvas.getBackground();
        }
        return super.getBackground();
    }
    
    public void setBackground(final Color color) {
        if (this.rCanvas != null) {
            this.rCanvas.setBackground(color);
        }
        super.setBackground(color);
    }
    
    public void setFontSize(final int fontSize) {
        if (this.rCanvas != null) {
            this.rCanvas.setFontSize(fontSize);
        }
    }
    
    public void run() {
        this.pChat.doze(9900);
        final int n = 671;
        while (this.keepRunning) {
            this.rCanvas.refresh();
            this.pChat.doze(n);
        }
    }
    
    public void stopAnimation() {
        this.keepRunning = false;
    }
    
    public void addChatLine(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        this.rCanvas.addChatLine(s, s2, s3, msgOptions);
    }
    
    public void appendText(final String s, final MsgOptions msgOptions) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            this.rCanvas.appendText(stringTokenizer.nextToken(), msgOptions);
        }
    }
    
    public void modUserQuestion(String string, final String s, final String s2) {
        string += ": ";
        this.rCanvas.modUserQuestion(string, s, s2);
    }
    
    public void modAnswer(final String s, final String s2, final String s3, final boolean b) {
        this.rCanvas.modAnswer(s + ": ", s2, s3, b);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.rCanvas.setPercentage(1.0f * adjustmentEvent.getValue() / 1000.0f);
    }
    
    public Vector getPlainText() {
        return this.rCanvas.getPlainText();
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        (this.vBar = new Scrollbar(1, 1000, 64, 0, 1064)).addAdjustmentListener(this);
        this.add("Center", this.rCanvas = new RichCanvas(this.pChat.paraConf));
        this.add("East", this.vBar);
        this.vBar.setValue(this.vBar.getMaximum());
    }
}
