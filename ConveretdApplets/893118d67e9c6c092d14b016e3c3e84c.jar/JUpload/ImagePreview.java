// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.beans.PropertyChangeEvent;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.io.File;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;

public class ImagePreview extends JComponent implements PropertyChangeListener
{
    File file;
    ImageIcon thumbnail;
    
    public ImagePreview(final JFileChooser fc) {
        this.file = null;
        this.thumbnail = null;
        this.debug("ImagePreview() created");
        this.setPreferredSize(new Dimension(100, 50));
        if (fc != null) {
            this.debug("ImagePreview() adding ImagePreview listener to filechooser");
            fc.addPropertyChangeListener(this);
        }
    }
    
    public void loadImage() {
        this.debug("ImagePreview() Loading image file " + this.file);
        if (this.file == null) {
            return;
        }
        final ImageIcon tmpIcon = new ImageIcon(this.file.getPath());
        if (tmpIcon.getIconWidth() > 90) {
            this.thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, 1));
        }
        else {
            this.thumbnail = tmpIcon;
        }
    }
    
    public void paintComponent(final Graphics g) {
        this.debug("ImagePreview() paintComponent()");
        if (this.thumbnail == null) {
            this.debug("ImagePreview() paintComponent() loading image");
            this.loadImage();
        }
        if (this.thumbnail != null) {
            this.debug("ImagePreview() paintComponent() calculating thumbnail");
            int x = this.getWidth() / 2 - this.thumbnail.getIconWidth() / 2;
            int y = this.getHeight() / 2 - this.thumbnail.getIconHeight() / 2;
            if (y < 0) {
                y = 0;
            }
            if (x < 5) {
                x = 5;
            }
            this.thumbnail.paintIcon(this, g, x, y);
        }
    }
    
    public void propertyChange(final PropertyChangeEvent e) {
        this.debug("ImagePreview() propertyChange() event = " + e);
        final String prop = e.getPropertyName();
        if (prop.equals("SelectedFileChangedProperty")) {
            this.file = (File)e.getNewValue();
            if (this.isShowing()) {
                this.loadImage();
                this.repaint();
            }
        }
    }
    
    private void debug(final String string) {
        if (Configurator.getDebug()) {
            System.out.println(string);
        }
    }
}
