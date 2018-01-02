import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bE extends rp_fG implements ActionListener, ChangeListener
{
    private JColorChooser a;
    Color a;
    Color b;
    private boolean a;
    private rp_aP a;
    
    public rp_bE(final String s, final Color a, final Color b) {
        super(s);
        this.a = false;
        this.a(rp_au.a.a());
        this.a = a;
        this.b = b;
        this.a();
        this.b();
        this.a(false);
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        this.a = new JColorChooser(this.a);
        this.a.getSelectionModel().addChangeListener(this);
        this.a.setPreviewPanel(new JPanel());
        panel.add(this.a, "Center");
        (this.a = new rp_aP(this, this, this.a, this.b)).setBorder(BorderFactory.createTitledBorder("Select colors"));
        panel.add(this.a, "Last");
    }
    
    public final boolean a() {
        return true;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("CB")) {
            this.a(true);
            return;
        }
        if (actionEvent.getActionCommand().equals("CF")) {
            this.a(false);
            return;
        }
        super.actionPerformed(actionEvent);
    }
    
    private void a(final boolean b) {
        if (b) {
            ((JColorChooser)(this.a = true)).setColor(this.a);
            return;
        }
        ((JColorChooser)(this.a = false)).setColor(this.b);
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        final Color color = this.a.getColor();
        if (this.a) {
            this.a = color;
            final rp_aP a = this.a;
            final Color color2 = color;
            final rp_Z a2 = a.a;
            final Color a3 = color2;
            final rp_Z rp_Z = a2;
            a2.a = a3;
            rp_Z.repaint();
            return;
        }
        this.b = color;
        final rp_aP a4 = this.a;
        final Color color3 = color;
        final rp_Z a5 = a4.a;
        final Color b = color3;
        final rp_Z rp_Z2 = a5;
        a5.b = b;
        rp_Z2.repaint();
    }
}
