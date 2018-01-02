import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fX extends rp_bW
{
    private Color a;
    
    public rp_fX(final String s) {
        super(s);
        this.a = null;
        this.a();
    }
    
    public final void a() {
        super.a();
        final JPanel panel = new JPanel();
        this.getContentPane().add(panel, "Center");
        this.a(panel);
        this.pack();
    }
    
    protected final boolean c() {
        this.dispose();
        return true;
    }
    
    public final void a(final JPanel panel) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        final rp_cr rp_cr = new rp_cr(this);
        final Color[] array;
        (array = new Color[12])[0] = Color.white;
        array[1] = Color.black;
        array[2] = Color.blue;
        array[3] = Color.cyan;
        array[4] = Color.gray;
        array[5] = Color.green;
        array[6] = Color.lightGray;
        array[7] = Color.magenta;
        array[8] = Color.orange;
        array[9] = Color.pink;
        array[10] = Color.red;
        array[11] = Color.yellow;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                final rp_aT rp_aT = new rp_aT(this, array[j + (i << 2)]);
                layout.setConstraints(rp_aT, gridBagConstraints);
                this.add(rp_aT);
                rp_aT.addActionListener(rp_cr);
            }
        }
    }
    
    final void a(final Color a) {
        this.a = a;
    }
    
    public final Color a() {
        return this.a;
    }
}
