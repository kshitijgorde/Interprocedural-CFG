import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Color;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends Container
{
    static final Color _fldif;
    static final Color _flddo;
    static final Color _fldbyte;
    static final Color _fldnew;
    Label _fldint;
    Label _fldtry;
    Scrollbar a;
    Scrollbar _fldfor;
    
    public v() {
        this._fldint = new Label();
        this._fldtry = new Label();
        this.a = new Scrollbar(0);
        this._fldfor = new Scrollbar(0);
        this.a();
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(this._fldint, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(this.a, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(this._fldtry, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldfor, gridBagConstraints);
    }
    
    public void a() {
        this._fldint.setText("\uc774\ud3c9 (20\uc77c)");
        this._fldtry.setText("\ud3b8\ucc28 (2\u03c3)");
        this.a.setValues(20, 2, 1, 202);
        this._fldfor.setValues(2, 2, 1, 202);
    }
    
    static {
        _fldif = Color.red;
        _flddo = Color.green;
        _fldbyte = Color.blue;
        _fldnew = Color.blue;
    }
}
