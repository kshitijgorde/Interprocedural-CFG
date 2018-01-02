// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UploadStatus extends JPanel
{
    private JLabel filenamebar;
    private JProgressBar progressbar;
    private long currentTime;
    private long startTime;
    
    UploadStatus() {
        this.filenamebar = new JLabel();
        this.progressbar = new JProgressBar();
        this.startTime = System.currentTimeMillis();
        this.setBackground(Configurator.getBackgroundColor());
        this.progressbar.setBackground(Configurator.getBackgroundColor());
        this.filenamebar.setBackground(Configurator.getBackgroundColor());
        this.setLayout(new GridLayout(2, 1));
        this.add(this.progressbar);
        this.add(this.filenamebar);
    }
    
    public void setFilename(final String filename) {
        this.filenamebar.setText(filename);
        this.revalidate();
    }
    
    public void setValue(final int i, final int max) {
        this.progressbar.setMaximum(max);
        this.progressbar.setStringPainted(true);
        this.progressbar.setValue(i);
        final double prozent = i / max * 100.0;
        final String strProzent = String.valueOf(Math.round(prozent)) + "%";
        final long duration = (System.currentTimeMillis() - this.startTime) / 1000L;
        if (duration > 0L) {
            int speed = (int)(i / duration);
            speed /= 1024;
            final String strSpeed = String.valueOf(speed) + " kb/s";
            long lETA = Math.round(100.0 / prozent * duration) - duration;
            String strETA;
            if (lETA > 60L) {
                lETA = Math.round(lETA / 60L);
                strETA = String.valueOf(lETA) + "min";
            }
            else {
                strETA = String.valueOf(lETA) + "s";
            }
            this.progressbar.setString(String.valueOf(strProzent) + " - " + strSpeed + " - " + strETA);
        }
        else {
            this.progressbar.setString(strProzent);
        }
        this.validate();
    }
    
    public void close() {
        this.setVisible(false);
    }
}
