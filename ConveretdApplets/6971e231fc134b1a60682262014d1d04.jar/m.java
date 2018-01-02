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

class m extends Container
{
    static final Color _fldgoto;
    static final Color _fldchar;
    static final Color a;
    static final Color _fldfor;
    Checkbox _fldnew;
    Checkbox _fldint;
    Checkbox _fldif;
    Checkbox _fldcase;
    Scrollbar _flddo;
    Scrollbar _fldelse;
    Scrollbar _fldbyte;
    Scrollbar _fldtry;
    
    public m() {
        this._fldnew = new Checkbox();
        this._fldint = new Checkbox();
        this._fldif = new Checkbox();
        this._fldcase = new Checkbox();
        this._flddo = new Scrollbar(0);
        this._fldelse = new Scrollbar(0);
        this._fldbyte = new Scrollbar(0);
        this._fldtry = new Scrollbar(0);
        this.a();
        this._fldnew.setForeground(m._fldgoto);
        this._fldint.setForeground(m._fldchar);
        this._fldif.setForeground(m.a);
        this._fldcase.setForeground(m._fldfor);
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
        this.add(this._flddo, gridBagConstraints);
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
        this.add(this._fldelse, gridBagConstraints);
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
        this.add(this._fldbyte, gridBagConstraints);
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
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldtry, gridBagConstraints);
    }
    
    public void a() {
        this._fldnew.setLabel("\ub2e8\uae30 (5\uc77c)");
        this._fldint.setLabel("\uc911\uae30 (20\uc77c)");
        this._fldif.setLabel("\uc7a5\uae301 (60\uc77c)");
        this._fldcase.setLabel("\uc7a5\uae302 (120\uc77c)");
        this._fldnew.setState(true);
        this._fldint.setState(true);
        this._fldif.setState(true);
        this._fldcase.setState(true);
        this._flddo.setValues(5, 2, 1, 202);
        this._fldelse.setValues(20, 2, 1, 202);
        this._fldbyte.setValues(60, 2, 1, 202);
        this._fldtry.setValues(120, 2, 1, 202);
    }
    
    static {
        _fldgoto = Color.red;
        _fldchar = Color.blue;
        a = Color.green;
        _fldfor = Color.magenta;
    }
}
