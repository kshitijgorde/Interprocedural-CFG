// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Font;
import java.awt.Color;
import com.pchat.sc.MsgOptions;

public interface ChatPrevRenderer
{
    String getSelectedText();
    
    void appendTextWithWrap(final String p0, final boolean p1, final MsgOptions p2);
    
    void appendTextWithWrap(final String p0, final String p1, final String p2, final boolean p3, final MsgOptions p4);
    
    void appendLocalEcho(final String p0, final String p1, final MsgOptions p2);
    
    void appendTypingText(final String p0, final MsgOptions p1);
    
    void setApplet(final BaseChat p0);
    
    void setnl(final boolean p0);
    
    boolean getnl();
    
    Color getBackground();
    
    void setBackground(final Color p0);
    
    Color getForeground();
    
    void setForeground(final Color p0);
    
    Font getFont();
    
    void setFont(final Font p0);
    
    void modMsg(final String p0, final String p1);
    
    void modAnswer(final String p0, final String p1, final String p2, final String p3, final boolean p4);
    
    void stop();
    
    void saveText();
}
