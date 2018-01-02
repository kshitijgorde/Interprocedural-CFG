import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class ca extends Container
{
    static final Color _flddo;
    static final Color _fldchar;
    static final Color _fldbyte;
    static final Color a;
    Checkbox _fldnew;
    Checkbox _fldint;
    Checkbox _fldif;
    Scrollbar _fldfor;
    Scrollbar _fldcase;
    Scrollbar _fldtry;
    
    public ca() {
        this._fldnew = new Checkbox();
        this._fldint = new Checkbox();
        this._fldif = new Checkbox();
        this._fldfor = new Scrollbar(0);
        this._fldcase = new Scrollbar(0);
        this._fldtry = new Scrollbar(0);
        this.a();
        this._fldnew.setForeground(ca._fldchar);
        this._fldint.setForeground(ca._fldbyte);
        this._fldif.setForeground(ca.a);
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
        this.add(this._fldnew, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(this._fldfor, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
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
        this.add(this._fldcase, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(this._fldif, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldtry, gridBagConstraints);
    }
    
    public void a() {
        this._fldnew.setLabel("\ub2e8\uae30 (5\uc77c)");
        this._fldint.setLabel("\uc911\uae30 (20\uc77c)");
        this._fldif.setLabel("\uc7a5\uae30 (60\uc77c)");
        this._fldnew.setState(true);
        this._fldint.setState(true);
        this._fldif.setState(true);
        this._fldfor.setValues(5, 2, 1, 202);
        this._fldcase.setValues(20, 2, 1, 202);
        this._fldtry.setValues(60, 2, 1, 202);
    }
    
    static {
        _flddo = new Color(105, 106, 98);
        _fldchar = Color.red;
        _fldbyte = Color.blue;
        a = Color.green;
    }
}
