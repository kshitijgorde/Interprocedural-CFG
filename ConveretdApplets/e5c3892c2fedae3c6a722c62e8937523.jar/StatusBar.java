import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class StatusBar extends JPanel
{
    private JTextField resolution;
    private JTextField limits;
    private JTextField status;
    public ProgressIndicator progress;
    
    StatusBar(final imgViewer imgViewer) {
        this.setBackground(Color.LIGHT_GRAY);
        (this.resolution = new JTextField("", 6)).setEditable(false);
        this.resolution.setFocusable(false);
        final Dimension preferredSize = this.resolution.getPreferredSize();
        preferredSize.width = 60;
        this.resolution.setMinimumSize(preferredSize);
        this.resolution.setMaximumSize(preferredSize);
        (this.limits = new JTextField("", 20)).setEditable(false);
        this.limits.setFocusable(false);
        final Dimension preferredSize2 = this.limits.getPreferredSize();
        preferredSize2.width = 165;
        this.limits.setMinimumSize(preferredSize2);
        this.limits.setMaximumSize(preferredSize2);
        (this.status = new JTextField("", 70)).setEditable(false);
        this.status.setFocusable(false);
        this.progress = new ProgressIndicator(imgViewer);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 1;
        this.add(this.resolution, gridBagConstraints);
        this.add(this.limits, gridBagConstraints);
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 1;
        this.add(this.status, gridBagConstraints);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.add(this.progress, gridBagConstraints);
    }
    
    public void showStatus(final String text) {
        this.status.setText(text);
        this.status.setCaretPosition(0);
    }
    
    public void showSearchLimits(final String text) {
        this.limits.setText(text);
    }
    
    public void showResolution(final String text) {
        this.resolution.setText(text);
    }
}
