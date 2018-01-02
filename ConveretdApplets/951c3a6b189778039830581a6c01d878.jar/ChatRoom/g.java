// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.awt.TextComponent;
import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;

public class g extends TextField
{
    public ChatRoomApplet a;
    
    public g(final ChatRoomApplet a, final String s) {
        super(s, 20);
        this.a = a;
        this.setFont(ChatRoomApplet.FTEXTFONT);
    }
    
    private void a(final int n, final int n2) {
        final String text = this.getText();
        if (text.length() >= n2) {
            if (n == 8 || (this.getSelectionStart() < text.length() && n == 127)) {
                System.out.println("Backspace/Delete");
                this.setEditable(true);
                return;
            }
            this.setEditable(false);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.a.ChangeUserName();
            ((Component)this.a.fTextInputArea).requestFocus();
            return true;
        }
        if (n == 9) {
            ((Component)this.a.fTextInputArea).requestFocus();
            ((TextComponent)this.a.fTextInputArea).selectAll();
            return true;
        }
        this.a(n, 20);
        return false;
    }
}
