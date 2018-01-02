// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import javax.swing.JButton;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

final class TransparencyDialogSetup
{
    private final TransparencyDialog bindToClass;
    
    public TransparencyDialogSetup(final TransparencyDialog _bindToClass) {
        this.bindToClass = _bindToClass;
        this._initGeneratedUICode();
    }
    
    private void _initGeneratedUICode() {
        (this.bindToClass.contentPane = new JPanel()).setLayout(new GridBagLayout());
        (this.bindToClass.panel2 = new JPanel()).setLayout(new GridBagLayout());
        this.bindToClass.contentPane.add(this.bindToClass.panel2, new MyGridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        final Box.Filler tmpSpacer = new Box.Filler(new Dimension(-1, -1), new Dimension(-1, -1), new Dimension(-1, -1));
        this.bindToClass.panel2.add(tmpSpacer, new MyGridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
        (this.bindToClass.buttonPanel = new JPanel()).setLayout(new GridBagLayout());
        this.bindToClass.panel2.add(this.bindToClass.buttonPanel, new MyGridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        (this.bindToClass.buttonOK = new JButton()).setText("OK");
        this.bindToClass.buttonPanel.add(this.bindToClass.buttonOK, new MyGridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
        (this.bindToClass.buttonCancel = new JButton()).setText("Cancel");
        this.bindToClass.buttonPanel.add(this.bindToClass.buttonCancel, new MyGridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
        (this.bindToClass.panel1 = new JPanel()).setLayout(new GridBagLayout());
        this.bindToClass.contentPane.add(this.bindToClass.panel1, new MyGridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
    }
}
