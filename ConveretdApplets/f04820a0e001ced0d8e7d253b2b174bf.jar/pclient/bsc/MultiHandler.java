// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MultiHandler implements KeyListener
{
    private BaseChat pChat;
    
    public MultiHandler(final BaseChat pChat) {
        this.pChat = pChat;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            if (keyEvent.getSource() == this.pChat.pUserMultiLineInput) {
                this.pChat.doMultiInput();
            }
            else if (keyEvent.getSource() == this.pChat.pSecondMulti) {
                this.pChat.inputxMulti();
            }
            keyEvent.consume();
        }
        else {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.pChat.myTyping < this.pChat.typingGap) {
                return;
            }
            this.pChat.chatModel.cmTypingPub();
            this.pChat.myTyping = currentTimeMillis;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
}
