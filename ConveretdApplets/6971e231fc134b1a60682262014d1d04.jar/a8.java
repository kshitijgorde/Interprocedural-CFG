import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class a8 extends Panel
{
    boolean _fldfor;
    boolean a;
    boolean _flddo;
    boolean _fldif;
    
    public a8() {
        this(true, true, true, true);
    }
    
    public a8(final boolean fldfor, final boolean flddo, final boolean a, final boolean fldif) {
        this._fldfor = fldfor;
        this._flddo = flddo;
        this.a = a;
        this._fldif = fldif;
    }
    
    public a8(final LayoutManager layoutManager) {
        this(layoutManager, true, true, true, true);
    }
    
    public a8(final LayoutManager layoutManager, final boolean fldfor, final boolean flddo, final boolean a, final boolean fldif) {
        super(layoutManager);
        this._fldfor = fldfor;
        this._flddo = flddo;
        this.a = a;
        this._fldif = fldif;
    }
    
    public Insets getInsets() {
        return new Insets(this._fldfor ? 1 : 0, this._flddo ? 1 : 0, this.a ? 1 : 0, this._fldif ? 1 : 0);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.black);
        if (this._fldfor) {
            graphics.drawLine(0, 0, size.width - 1, 0);
        }
        if (this.a) {
            graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
        }
        if (this._flddo) {
            graphics.drawLine(0, 0, 0, size.height - 1);
        }
        if (this._fldif) {
            graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
        }
        this.paintComponents(graphics);
    }
}
