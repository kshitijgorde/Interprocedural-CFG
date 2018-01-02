import java.awt.Event;
import java.awt.Dimension;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_TextFieldFixed extends TextField
{
    private PGC owner;
    private Dimension dimension;
    
    public PGC_TextFieldFixed(final PGC owner) {
        this.owner = owner;
    }
    
    public PGC_TextFieldFixed(final PGC owner, final String s) {
        super(s);
        this.owner = owner;
    }
    
    public PGC_TextFieldFixed(final PGC owner, final Dimension minimumSize) {
        this.setMinimumSize(minimumSize);
        this.owner = owner;
    }
    
    public PGC_TextFieldFixed(final PGC owner, final String s, final Dimension minimumSize) {
        super(s);
        this.setMinimumSize(minimumSize);
        this.owner = owner;
    }
    
    public void setMinimumSize(final Dimension dimension) {
        this.dimension = dimension;
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public Dimension getMaximumSize() {
        return this.minimumSize();
    }
    
    public Dimension getPreferredSize() {
        return this.minimumSize();
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        final Dimension minimumSize = super.minimumSize();
        if (this.dimension != null) {
            if (this.dimension.width != -1) {
                minimumSize.width = this.dimension.width;
            }
            if (this.dimension.height != -1) {
                minimumSize.height = this.dimension.height;
            }
        }
        return minimumSize;
    }
    
    public void setText(final String text) {
        if (!text.equals(super.getText())) {
            super.setText(text);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean keyDown;
        if (n == 10) {
            this.owner.TextFieldsInput(this);
            keyDown = true;
        }
        else if (n == 27) {
            this.owner.GraphFocus();
            keyDown = true;
        }
        else {
            keyDown = super.keyDown(event, n);
        }
        return keyDown;
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        return super.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        if (this.owner != null) {
            this.owner.TextFieldsInput(this);
        }
        return super.lostFocus(event, o);
    }
}
