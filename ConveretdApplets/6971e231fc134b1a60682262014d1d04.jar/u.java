import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class u extends a8
{
    an _fldnew;
    ag _fldint;
    
    public u() {
        super(false, false, false, false);
        this._fldnew = new an();
        this._fldint = new ag();
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this._fldnew.setBounds(0, 0, 105, 55);
        this.add(this._fldnew);
        this._fldint.setBounds(0, 55, 105, 205);
        this.add(this._fldint);
    }
}
