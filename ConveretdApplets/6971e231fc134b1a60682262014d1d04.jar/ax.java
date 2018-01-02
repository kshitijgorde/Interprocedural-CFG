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

class ax extends Container
{
    static final Color _flddo;
    Label _fldif;
    Scrollbar a;
    
    public ax() {
        this._fldif = new Label();
        this.a = new Scrollbar(0);
        this.a();
        this._fldif.setForeground(ax._flddo);
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
        this.add(this.a, gridBagConstraints);
    }
    
    public void a() {
        this._fldif.setText("\uae30\uac04 (9\uc77c)");
        this.a.setValues(9, 2, 1, 202);
    }
    
    static {
        _flddo = Color.blue;
    }
}
