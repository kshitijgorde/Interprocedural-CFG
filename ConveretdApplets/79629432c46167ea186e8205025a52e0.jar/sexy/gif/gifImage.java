// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

import sexy.gui.SexyImage;

public class gifImage
{
    SexyImage i;
    int delay_ms;
    private int h_offset;
    private int v_offset;
    private int width;
    private int height;
    private int disposal;
    private int transIndex;
    public boolean transparent;
    public static final int NOT_SPECIFIED = 0;
    public static final int LEAVE_IN_BUFFER = 1;
    public static final int CLEAR_TO_BACKGROUND = 2;
    public static final int CLEAR_TO_LAST_BUFFER = 3;
    
    public int height() {
        return this.height;
    }
    
    public void set_transparent(final boolean transparent) {
        this.transparent = transparent;
    }
    
    public int horiz_offset() {
        return this.h_offset;
    }
    
    public void set_disposal(final int disposal) {
        this.disposal = disposal;
    }
    
    gifImage() {
        this.i = null;
        this.delay_ms = 0;
        this.h_offset = 0;
        this.v_offset = 0;
        this.width = 0;
        this.height = 0;
        this.disposal = 0;
        this.transIndex = -1;
        this.transparent = false;
    }
    
    gifImage(final SexyImage i, final int h_offset, final int v_offset, final int n, final int disposal, final int transIndex, final boolean transparent) {
        this.i = null;
        this.delay_ms = 0;
        this.h_offset = 0;
        this.v_offset = 0;
        this.width = 0;
        this.height = 0;
        this.disposal = 0;
        this.transIndex = -1;
        this.transparent = false;
        this.i = i;
        this.width = ((this.i != null) ? this.i.GetWidth() : 0);
        this.height = ((this.i != null) ? this.i.GetHeight() : 0);
        this.h_offset = h_offset;
        this.v_offset = v_offset;
        this.delay_ms = n * 10;
        this.disposal = disposal;
        this.transIndex = transIndex;
        this.transparent = transparent;
    }
    
    public void set_horiz_offset(final int h_offset) {
        this.h_offset = h_offset;
    }
    
    public SexyImage frame() {
        return this.i;
    }
    
    public int vert_offset() {
        return this.v_offset;
    }
    
    public int width() {
        return this.width;
    }
    
    public void set_trans_index(final int transIndex) {
        this.transIndex = transIndex;
    }
    
    public void setImage(final SexyImage i) {
        this.i = i;
        this.width = this.i.GetWidth();
        this.height = this.i.GetHeight();
    }
    
    public int delay() {
        return this.delay_ms;
    }
    
    public void set_vert_offset(final int v_offset) {
        this.v_offset = v_offset;
    }
    
    public void set_delay(final int delay_ms) {
        this.delay_ms = delay_ms;
    }
    
    public int disposal() {
        return this.disposal;
    }
    
    public int transparencyIndex() {
        return this.transIndex;
    }
    
    public boolean transparency() {
        return this.transparent;
    }
}
