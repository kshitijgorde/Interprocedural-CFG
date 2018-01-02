import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class l extends Container
{
    static final Color _fldnew;
    static final Color _fldint;
    static final Color _fldtry;
    static final Color a;
    Checkbox _fldbyte;
    Checkbox _fldif;
    Checkbox _fldcase;
    Label _fldfor;
    Scrollbar _flddo;
    
    public l() {
        this._fldbyte = new Checkbox("+DI \uace1\uc120");
        this._fldif = new Checkbox("-DI \uace1\uc120");
        this._fldcase = new Checkbox("ADX \uace1\uc120");
        this._fldfor = new Label();
        this._flddo = new Scrollbar(0);
        this.a();
        final Label label = new Label("");
        this._fldbyte.setForeground(l._fldnew);
        this._fldif.setForeground(l._fldint);
        this._fldcase.setForeground(l._fldtry);
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
        this.add(this._fldbyte, gridBagConstraints);
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
        gridBagConstraints.weighty = 0.0;
        this.add(this._fldcase, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.add(label, gridBagConstraints);
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
        gridBagConstraints.weighty = 1.0;
        this.add(this._flddo, gridBagConstraints);
    }
    
    public void a() {
        this._fldbyte.setState(true);
        this._fldif.setState(true);
        this._fldcase.setState(true);
        this._fldfor.setText("\uc774\ud3c9 (14\uc77c)");
        this._flddo.setValues(14, 2, 1, 202);
    }
    
    static {
        _fldnew = Color.red;
        _fldint = Color.blue;
        _fldtry = Color.magenta;
        a = Color.blue;
    }
}
