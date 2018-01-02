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

class br extends Container
{
    static final Color _fldfor;
    static final int a = 3;
    Label _fldif;
    Scrollbar _flddo;
    
    public br() {
        this._fldif = new Label();
        this._flddo = new Scrollbar(0);
        this.a();
        this._fldif.setForeground(br._fldfor);
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
        gridBagConstraints.weighty = 1.0;
        this.add(this._flddo, gridBagConstraints);
    }
    
    public void a() {
        this._fldif.setText("\uac00\uc18d\ub3c4(0.02)");
        this._flddo.setValues(20, 2, 1, 102);
    }
    
    static {
        _fldfor = Color.magenta;
    }
}
