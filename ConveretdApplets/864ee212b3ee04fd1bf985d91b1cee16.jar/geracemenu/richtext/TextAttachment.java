// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.Visualizable;
import geracemenu.VWrapper;

public class TextAttachment extends VWrapper
{
    public static final int TOP = 0;
    public static final int MIDDLE = 1;
    public static final int BOTTOM = 2;
    private int alignment;
    private boolean animated;
    
    public final int getAlignment() {
        return this.alignment;
    }
    
    public final boolean isAnimated() {
        return this.animated;
    }
    
    public void setAlignment(final int alignment) {
        switch (alignment) {
            case 0:
            case 1:
            case 2: {
                this.alignment = alignment;
            }
            default: {
                throw new IllegalArgumentException("TextAttachment, setAlignment(): unknow alignment mode: " + alignment);
            }
        }
    }
    
    public TextAttachment(final Visualizable visualizable) {
        this(visualizable, 2, false);
    }
    
    public TextAttachment(final Visualizable visualizable, final int n) {
        this(visualizable, n, false);
    }
    
    public TextAttachment(final Visualizable visualizable, final boolean b) {
        this(visualizable, 2, b);
    }
    
    public TextAttachment(final Visualizable visualizable, final int alignment, final boolean animated) {
        super(visualizable);
        this.animated = false;
        this.alignment = alignment;
        this.animated = animated;
    }
}
