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

class r extends Container
{
    static final Color _flddo;
    static final Color _fldfor;
    static final Color _fldtry;
    Label _fldint;
    Label _fldnew;
    Scrollbar _fldif;
    Scrollbar a;
    
    public r() {
        this._fldint = new Label();
        this._fldnew = new Label();
        this._fldif = new Scrollbar(0);
        this.a = new Scrollbar(0);
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
        this.add(this._fldif, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
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
        gridBagConstraints.weighty = 1.0;
        this.add(this.a, gridBagConstraints);
    }
    
    public void a() {
        this._fldint.setText("\uc774\ud3c9 (20\uc77c)");
        this._fldnew.setText("\uc0c1\ud558\ud55c (6%)");
        this._fldif.setValues(20, 2, 1, 202);
        this.a.setValues(6, 2, 1, 202);
    }
    
    static {
        _flddo = Color.red;
        _fldfor = Color.green;
        _fldtry = Color.blue;
    }
}
