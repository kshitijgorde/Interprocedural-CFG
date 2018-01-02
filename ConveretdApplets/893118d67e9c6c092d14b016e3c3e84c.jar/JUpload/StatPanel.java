// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import java.text.DecimalFormat;
import java.io.File;
import javax.swing.DefaultListModel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatPanel extends JPanel
{
    private JLabel statBytesLabel;
    private JLabel statBytesValue;
    private JLabel statFilesLabel;
    private JLabel statFilesValue;
    
    StatPanel() {
        this.debug("StatPanel() initialized");
        this.setLayout(new GridLayout(2, 2));
        this.createFields();
        this.setVisible(true);
        this.doLayout();
    }
    
    public void updateColor() {
        this.statFilesLabel.setBackground(Configurator.getBackgroundColor());
        this.statFilesValue.setBackground(Configurator.getBackgroundColor());
        this.statBytesLabel.setBackground(Configurator.getBackgroundColor());
        this.statBytesValue.setBackground(Configurator.getBackgroundColor());
    }
    
    public void updateModel(final DefaultListModel tModel) {
        this.statFilesLabel.setVisible(true);
        this.statBytesLabel.setVisible(true);
        long totalSize = 0L;
        for (int i = 0; i < tModel.size(); ++i) {
            final File f = tModel.getElementAt(i);
            totalSize += f.length();
        }
        this.setFiles(tModel.size());
        this.setBytes(totalSize);
        this.getParent().validate();
    }
    
    private void setBytes(final long i) {
        double size = i;
        String description = "bytes";
        String output = null;
        if (size > 1024.0) {
            size /= 1024.0;
            description = "KB";
            if (size > 1.0) {
                description = "KB";
            }
        }
        if (size > 1024.0) {
            size /= 1024.0;
            description = "MB";
            if (size > 1.0) {
                description = "MB";
            }
        }
        final DecimalFormat myFormatter = new DecimalFormat("###.##");
        output = myFormatter.format(size);
        this.statBytesValue.setText(String.valueOf(output) + " " + description);
    }
    
    private void setFiles(final long i) {
        this.statFilesValue.setText(Long.toString(i));
    }
    
    private void createFields() {
        this.statFilesLabel = new JLabel(Configurator.getLabelFiles());
        this.statFilesValue = new JLabel();
        this.statBytesLabel = new JLabel(Configurator.getLabelBytes());
        this.statBytesValue = new JLabel();
        this.statFilesLabel.setVisible(false);
        this.statBytesLabel.setVisible(false);
        this.updateColor();
        this.add(this.statFilesLabel);
        this.add(this.statFilesValue);
        this.add(this.statBytesLabel);
        this.add(this.statBytesValue);
    }
    
    private void debug(final String string) {
        if (Configurator.getDebug()) {
            System.out.println(string);
        }
    }
}
