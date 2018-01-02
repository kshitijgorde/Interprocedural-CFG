// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.b;

import java.awt.Event;
import java.awt.Cursor;
import buddysoft.SlideShow;

public class d extends c
{
    private SlideShow byte;
    
    public d(final SlideShow byte1) {
        this.byte = byte1;
        super.new = this.byte.api.b;
        super.a = this.byte.api;
    }
    
    protected void if() {
        if (super.new[super.int].a != null) {
            this.setCursor(new Cursor(12));
            this.byte.showStatus("Go to: " + super.new[super.int].a);
        }
        else {
            this.setCursor(new Cursor(0));
            this.byte.showStatus("Done");
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (super.new[super.int].a != null && !event.metaDown()) {
                    this.byte.getAppletContext().showDocument(super.new[super.int].a, super.new[super.int].new);
                }
                break;
            }
            case 504: {
                this.if();
                break;
            }
            case 505: {
                this.byte.showStatus("DONE");
                break;
            }
        }
        return super.handleEvent(event);
    }
}
