// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import com.pchat.sc.MsgOptions;
import java.awt.Font;
import java.awt.Color;
import pclient.bsc.BaseChat;
import pclient.bsc.ChatPrevRenderer;
import java.awt.TextArea;

public class ChatPanel extends TextArea implements ChatPrevRenderer
{
    private BaseChat pChat;
    private int textLen;
    private int max_text_len;
    private String lineSeparator;
    private static final int DEFAULT_MAX_LEN = 24000;
    private boolean nlmode;
    
    public ChatPanel() {
        super("", 12, 72, 1);
        this.textLen = 0;
        this.nlmode = false;
    }
    
    public void setApplet(final BaseChat pChat) {
        this.pChat = pChat;
        this.max_text_len = 24000;
        this.lineSeparator = System.getProperty("line.separator", "\n");
        this.setEditable(false);
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setFont(new Font("Dialog", 0, 10));
    }
    
    public synchronized void modMsg(final String s, final String s2) {
        this.appendTextWithWrap(s + "> " + s2, true, null);
    }
    
    public synchronized void modAnswer(final String s, final String s2, final String s3, final String s4, final boolean b) {
        this.appendTextWithWrap(s + ": " + s2, true, null);
        if (s4 == null) {
            return;
        }
        final String string = s3 + "> " + s4;
        if (!b) {
            this.appendTextWithWrap(string, true, null);
        }
        else {
            this.appendTypingText(string, null);
        }
    }
    
    public void stop() {
    }
    
    public void setnl(final boolean nlmode) {
        this.nlmode = nlmode;
    }
    
    public boolean getnl() {
        return this.nlmode;
    }
    
    public synchronized void appendTextWithWrap(final String s, final String s2, final String s3, final boolean b, final MsgOptions msgOptions) {
        if (this.pChat.chatModel.cmIsSelf(s2)) {
            this.appendTextWithWrap(this.pChat.getStamp() + "[PRIVATE FROM " + s + "] " + s3, b, msgOptions);
        }
        else {
            this.appendTextWithWrap(this.pChat.getStamp() + "" + s + "> " + s3, b, msgOptions);
        }
    }
    
    public synchronized void appendTypingText(final String s, final MsgOptions msgOptions) {
        this.appendTextToPanel(this.pChat.getStamp() + s, true, true);
    }
    
    public void appendLocalEcho(final String s, final String s2, final MsgOptions msgOptions) {
        this.appendTextWithWrap(this.pChat.getStamp() + s + ": " + s2, true, msgOptions);
    }
    
    public synchronized void appendTextWithWrap(final String s, final boolean b, final MsgOptions msgOptions) {
        this.appendTextToPanel(s, b, false);
    }
    
    public void saveText() {
    }
    
    private void appendTextToPanel(final String s, final boolean b, final boolean b2) {
        if (this.textLen > this.max_text_len) {
            this.setText(this.getText().substring(this.max_text_len / 10));
            this.textLen = this.getText().length();
        }
        this.textLen += s.length();
        if (!b2) {
            this.addMoreText(s);
        }
        else {
            this.appendTyping(s);
        }
        if (b) {
            this.addLineBreak();
        }
        if (this.nlmode) {
            this.addLineBreak();
        }
    }
    
    private void appendTyping(final String s) {
        final int length = s.length();
        final char[] array = { '\0' };
        for (int i = 0; i < length; ++i) {
            array[0] = s.charAt(i);
            this.pChat.doze(80);
            this.addMoreText(new String(array));
        }
    }
    
    private void addMoreText(final String s) {
        this.append(s);
    }
    
    private void addLineBreak() {
        this.addMoreText(this.lineSeparator);
    }
}
