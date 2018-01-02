import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CloudCoverLimit extends JPanel implements ActionListener
{
    private CloudCoverChoice ccPercent;
    private MosaicData md;
    
    CloudCoverLimit(final imgViewer imgViewer, final MosaicData md) {
        this.md = md;
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JLabel label = new JLabel("Max Cloud:");
        label.setFont(imgViewer.boldFont);
        label.setDisplayedMnemonic('x');
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridheight = 1;
        this.add(label, gridBagConstraints);
        (this.ccPercent = new CloudCoverChoice()).setFont(imgViewer.normalFont);
        this.ccPercent.setToolTipText("Set cloud limit");
        this.ccPercent.addActionListener(this);
        label.setLabelFor(this.ccPercent);
        this.add(this.ccPercent, gridBagConstraints);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.md.setCCLimit(this.ccPercent.getCloudCover());
    }
    
    public void setCloudCover(final int cloudCover) {
        this.ccPercent.setCloudCover(cloudCover);
    }
}
