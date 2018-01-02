// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextPageCanvasEvent
{
    static final int SWITCH_TO_EDITING_MODE_GRAPHICS = 0;
    static final int SWITCH_TO_EDITING_MODE_CHARACTER = 1;
    int eventCode;
    
    public TeletextPageCanvasEvent(final int eventCode) {
        this.eventCode = eventCode;
    }
    
    public int getEventCode() {
        return this.eventCode;
    }
    
    public void finalize() throws Exception {
    }
}
