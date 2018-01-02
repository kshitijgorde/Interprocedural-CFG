// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.awt.Component;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class e extends Panel
{
    public ChatRoomApplet a;
    
    public e(final ChatRoomApplet a) {
        this.a = a;
    }
    
    public void paint(final Graphics graphics) {
        this.a.PaintChatLabels(graphics);
        this.paintComponents(graphics);
        graphics.setColor(Color.black);
        final Rectangle bounds = this.a.bounds();
        graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("Submit")) {
            this.a.SubmitPost();
            ((Component)this.a.fTextInputArea).requestFocus();
            return false;
        }
        if (o.equals("Change")) {
            this.a.ChangeUserName();
            ((Component)this.a.fTextInputArea).requestFocus();
        }
        return true;
    }
}
