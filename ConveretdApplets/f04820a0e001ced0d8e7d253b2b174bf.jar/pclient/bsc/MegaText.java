// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.util.Vector;
import com.pchat.sc.MsgOptions;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

public class MegaText extends Panel implements ChatPrevRenderer
{
    private BaseChat pChat;
    private ChatArea cArea;
    private boolean supportAnimation;
    private static String EMPTY_TEXT;
    private boolean nlmode;
    
    public MegaText() {
        this.cArea = null;
        this.supportAnimation = true;
    }
    
    public void setApplet(final BaseChat pChat) {
        this.pChat = pChat;
        final ChatArea cArea = new ChatArea(this.pChat);
        this.setLayout(new BorderLayout());
        this.add("Center", cArea);
        this.cArea = cArea;
        this.supportAnimation = true;
        if (this.supportAnimation) {
            this.addAnimationThread();
        }
    }
    
    public void setnl(final boolean nlmode) {
        this.nlmode = nlmode;
    }
    
    public boolean getnl() {
        return this.nlmode;
    }
    
    public void setFont(final Font font) {
        this.setFontSize(font.getSize());
    }
    
    public void setFontSize(final int fontSize) {
        if (this.cArea != null) {
            this.cArea.setFontSize(fontSize);
        }
    }
    
    public String getSelectedText() {
        return null;
    }
    
    public Color getBackground() {
        if (this.cArea != null) {
            return this.cArea.getBackground();
        }
        return super.getBackground();
    }
    
    public void setForeground(final Color color) {
        if (this.cArea != null) {
            this.cArea.setForeground(color);
        }
        super.setForeground(color);
    }
    
    public void setBackground(final Color color) {
        if (this.cArea != null) {
            this.cArea.setBackground(color);
        }
        super.setBackground(color);
    }
    
    public synchronized void appendTextWithWrap(final String s, final String s2, final String s3, final boolean b, final MsgOptions msgOptions) {
        final String s4 = "> ";
        if (this.pChat.chatModel.cmIsSelf(s2)) {
            this.cArea.addChatLine(s + s4, s3, this.pChat.getStamp(), msgOptions);
        }
        else {
            this.cArea.addChatLine(s + s4, s3, this.pChat.getStamp(), msgOptions);
        }
        if (this.nlmode) {
            this.cArea.appendText(MegaText.EMPTY_TEXT, null);
        }
    }
    
    public synchronized void appendLocalEcho(final String s, final String s2, final MsgOptions msgOptions) {
        this.cArea.addChatLine(s + ": ", s2, this.pChat.getStamp(), msgOptions);
        if (this.nlmode) {
            this.cArea.appendText(MegaText.EMPTY_TEXT, null);
        }
    }
    
    public synchronized void appendTypingText(final String s, final MsgOptions msgOptions) {
        this.cArea.appendText(s, msgOptions);
        if (this.nlmode) {
            this.cArea.appendText(MegaText.EMPTY_TEXT, null);
        }
    }
    
    public synchronized void appendTextWithWrap(final String s, final boolean b, final MsgOptions msgOptions) {
        this.cArea.appendText(s, msgOptions);
        if (this.nlmode) {
            this.cArea.appendText(MegaText.EMPTY_TEXT, null);
        }
    }
    
    public synchronized void modMsg(final String s, final String s2) {
        this.cArea.modAnswer(s, s2, this.pChat.getStamp(), false);
        this.cArea.appendText(MegaText.EMPTY_TEXT, null);
    }
    
    public synchronized void modAnswer(final String s, final String s2, final String s3, final String s4, final boolean b) {
        this.cArea.modUserQuestion(s, s2, this.pChat.getStamp());
        if (s4 != null) {
            this.cArea.modAnswer(s3, s4, this.pChat.getStamp(), b);
        }
        this.cArea.appendText(MegaText.EMPTY_TEXT, null);
    }
    
    public void stop() {
        this.cArea.stopAnimation();
    }
    
    public void saveText() {
        final String property = System.getProperty("line.separator", "\n");
        final Vector plainText = this.cArea.getPlainText();
        String string = "";
        for (int i = 0; i < plainText.size(); ++i) {
            string = string + plainText.elementAt(i) + property;
        }
        new SaveWindow(this.pChat, string).setVisible(true);
    }
    
    private void addAnimationThread() {
        new Thread(this.cArea).start();
    }
    
    static {
        MegaText.EMPTY_TEXT = " ";
    }
}
