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

class be extends Container
{
    static final Color _fldtry;
    static final Color _fldnew;
    Label _fldif;
    Label _fldint;
    Label a;
    Scrollbar _fldfor;
    Scrollbar _flddo;
    Scrollbar _fldbyte;
    
    public be() {
        this._fldif = new Label();
        this._fldint = new Label();
        this.a = new Label();
        this._fldfor = new Scrollbar(0);
        this._flddo = new Scrollbar(0);
        this._fldbyte = new Scrollbar(0);
        this.a();
        this._fldint.setForeground(be._fldtry);
        this.a.setForeground(be._fldnew);
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
        gridBagConstraints.weighty = 1.0;
        this.add(this._fldbyte, gridBagConstraints);
    }
    
    public void a() {
        this._fldif.setText("\uae30\uac04 (6\uc77c)");
        this._fldint.setText("SonMtm (9\uc77c)");
        this.a.setText("SonSgl (9\uc77c)");
        this._fldfor.setValues(6, 2, 1, 202);
        this._flddo.setValues(9, 2, 1, 202);
        this._fldbyte.setValues(9, 2, 1, 202);
    }
    
    static {
        _fldtry = Color.red;
        _fldnew = Color.blue;
    }
}
