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

class bo extends Container
{
    static final Color _fldfor;
    static final Color _fldbyte;
    static final Color _fldif;
    Checkbox _flddo;
    Checkbox _fldtry;
    Checkbox _fldcase;
    Scrollbar a;
    Scrollbar _fldint;
    Scrollbar _fldnew;
    
    public bo() {
        this._flddo = new Checkbox();
        this._fldtry = new Checkbox();
        this._fldcase = new Checkbox();
        this.a = new Scrollbar(0);
        this._fldint = new Scrollbar(0);
        this._fldnew = new Scrollbar(0);
        this.a();
        this._flddo.setForeground(bo._fldfor);
        this._fldtry.setForeground(bo._fldbyte);
        this._fldcase.setForeground(bo._fldif);
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
        this.add(this._flddo, gridBagConstraints);
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
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldnew, gridBagConstraints);
    }
    
    public void a() {
        this._flddo.setLabel("RawK (12\uc77c)");
        this._fldtry.setLabel("%K (5\uc77c)");
        this._fldcase.setLabel("%D (5\uc77c)");
        this._flddo.setState(false);
        this._fldtry.setState(true);
        this._fldcase.setState(true);
        this.a.setValues(12, 2, 1, 202);
        this._fldint.setValues(5, 2, 1, 202);
        this._fldnew.setValues(5, 2, 1, 202);
    }
    
    static {
        _fldfor = Color.red;
        _fldbyte = Color.blue;
        _fldif = Color.magenta;
    }
}
