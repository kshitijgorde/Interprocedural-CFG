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

class av extends Container
{
    static final Color _flddo;
    Label _fldif;
    Label _fldint;
    Scrollbar a;
    Scrollbar _fldfor;
    
    public av() {
        this._fldif = new Label();
        this._fldint = new Label();
        this.a = new Scrollbar(0);
        this._fldfor = new Scrollbar(0);
        this.a();
        this._fldif.setForeground(av._flddo);
        this._fldint.setForeground(av._flddo);
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
        this.add(this._fldif, gridBagConstraints);
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
        this.add(this._fldint, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldfor, gridBagConstraints);
    }
    
    public void a() {
        this._fldif.setText("\ub2e8\uae30 (5\uc77c)");
        this._fldint.setText("\uc7a5\uae30 (20\uc77c)");
        this.a.setValues(5, 2, 1, 202);
        this._fldfor.setValues(20, 2, 1, 202);
    }
    
    static {
        _flddo = Color.blue;
    }
}
