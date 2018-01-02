// 
// Decompiled by Procyon v0.5.30
// 

package speedometer;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Frame;
import speedometer.A.F;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import speedometer.A.B;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class C extends JPanel implements MouseListener
{
    private static final long C = -166754009179952153L;
    protected C L;
    JPanel E;
    JLabel N;
    JProgressBar D;
    JLabel J;
    JLabel K;
    JProgressBar B;
    JLabel F;
    JButton M;
    JButton H;
    JButton G;
    protected JLabel I;
    protected SpeedometerApplet A;
    
    public C() {
        (this.L = this).L();
    }
    
    protected void L() {
        final F d = speedometer.A.C.A().D("resources/MainView.properties");
        final B a = speedometer.A.B.A();
        a.A(this, d, "");
        this.E = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.E.setLayout(layout);
        this.N = a.A(d, "lbUpload.");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.N, gridBagConstraints);
        this.E.add(this.N);
        this.D = new JProgressBar();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.D, gridBagConstraints);
        this.E.add(this.D);
        this.J = new JLabel("0 KbS");
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.J, gridBagConstraints);
        this.E.add(this.J);
        this.K = a.A(d, "lbDownload.");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.K, gridBagConstraints);
        this.E.add(this.K);
        this.B = new JProgressBar();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.B, gridBagConstraints);
        this.E.add(this.B);
        this.F = new JLabel("0 KbS");
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.F, gridBagConstraints);
        this.E.add(this.F);
        a.A(this.K, d, "lbDownload.");
        a.A(this.F, d, "lbDownloadResult.");
        a.A(this.N, d, "lbUpload.");
        a.A(this.J, d, "lbUploadResult.");
        final JPanel panel = new JPanel();
        panel.add(this.G = a.D(d, "cmdTest."));
        panel.add(this.H = a.D(d, "cmdTestDownload."));
        panel.add(this.M = a.D(d, "cmdTestUpload."));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        (this.I = new JLabel("<html><body><font style='font:normal 9px Verdana; color:#4444ff; text-decoration:underline'>more...</font>&nbsp;&nbsp;</body></html>")).setCursor(new Cursor(12));
        panel2.add(this.I, "East");
        this.A(false);
        this.setLayout(new BorderLayout());
        this.add(panel2, "North");
        this.add(this.E, "Center");
        this.add(panel, "South");
        this.I.addMouseListener(this);
    }
    
    public JButton M() {
        return this.G;
    }
    
    public void A(final JButton g) {
        this.G = g;
    }
    
    public JButton F() {
        return this.H;
    }
    
    public void C(final JButton h) {
        this.H = h;
    }
    
    public JButton I() {
        return this.M;
    }
    
    public void B(final JButton m) {
        this.M = m;
    }
    
    public JLabel B() {
        return this.K;
    }
    
    public void A(final JLabel k) {
        this.K = k;
    }
    
    public JLabel H() {
        return this.F;
    }
    
    public void D(final JLabel f) {
        this.F = f;
    }
    
    public JLabel G() {
        return this.J;
    }
    
    public void B(final JLabel j) {
        this.J = j;
    }
    
    public JLabel K() {
        return this.N;
    }
    
    public void C(final JLabel n) {
        this.N = n;
    }
    
    public C D() {
        return this.L;
    }
    
    public void A(final C l) {
        this.L = l;
    }
    
    public JProgressBar C() {
        return this.B;
    }
    
    public void A(final JProgressBar b) {
        this.B = b;
    }
    
    public JProgressBar A() {
        return this.D;
    }
    
    public void B(final JProgressBar d) {
        this.D = d;
    }
    
    public JPanel E() {
        return this.E;
    }
    
    public void A(final JPanel e) {
        this.E = e;
    }
    
    public SpeedometerApplet J() {
        return this.A;
    }
    
    public void A(final SpeedometerApplet a) {
        this.A = a;
    }
    
    public Frame A(final Component component) {
        final Container parent = component.getParent();
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        return this.A(parent);
    }
    
    public void A(final String s) {
        JOptionPane.showMessageDialog(this, s, "Error", 0);
    }
    
    public void B(final boolean enabled) {
        this.G.setEnabled(enabled);
        this.M.setEnabled(enabled);
        this.H.setEnabled(enabled);
        this.A(enabled);
    }
    
    public void A(final boolean b) {
        this.I.setText("<html><body><font style='font:normal 9px Verdana; color:#4444ff; text-decoration:underline'>" + (b ? "more..." : "") + "</font>&nbsp;&nbsp;</body></html>");
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.A != null) {
            this.A.A("http://www.auditmypc.com/speedtest.asp");
        }
        else {
            System.out.println("Applet not set");
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
