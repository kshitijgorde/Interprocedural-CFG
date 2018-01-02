// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.EventObject;

public class HeaderEvent extends EventObject
{
    public static int HEADER_RESIZED;
    public static int HEADER_STATECHANGED;
    private int id;
    private int prevWidth;
    
    public HeaderEvent(final Header header, final int n) {
        this(header, n, 0);
    }
    
    public HeaderEvent(final Header header, final int id, final int prevWidth) {
        super(header);
        this.id = -1;
        this.prevWidth = -1;
        this.id = id;
        this.prevWidth = prevWidth;
    }
    
    public Header getHeader() {
        return (Header)this.getSource();
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getPreviousWidth() {
        return this.prevWidth;
    }
    
    public int getNewWidth() {
        return this.getHeader().getWidth();
    }
    
    static {
        HeaderEvent.HEADER_RESIZED = 1;
        HeaderEvent.HEADER_STATECHANGED = 2;
    }
}
