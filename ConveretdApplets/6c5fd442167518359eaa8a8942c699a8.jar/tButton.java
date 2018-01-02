import java.awt.Event;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class tButton extends Button
{
    static final int ENTER = 10;
    
    tButton(final String s) {
        super(s);
    }
    
    public boolean handleEvent(final Event event) {
        Label_0073: {
            switch (event.id) {
                case 401: {
                    switch (event.key) {
                        case 10: {
                            this.postEvent(new Event(this.getParent(), 1001, this.getLabel()));
                            return true;
                        }
                        default: {
                            break Label_0073;
                        }
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
}
