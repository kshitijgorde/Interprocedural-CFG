// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DanmarksKortHistorisk extends JPanel implements ActionListener
{
    static final long serialVersionUID = 0L;
    private String servicename;
    private ScrollablePicture picture;
    private JScrollPane pictureScrollPane;
    
    public DanmarksKortHistorisk(final String servicename) {
        this.servicename = servicename;
        final ImageIcon icon = createImageIcon(String.valueOf(Constant.imageURL) + this.servicename + ".jpg");
        this.picture = new ScrollablePicture(icon, this.getIncrement());
        final Dimension dimension = new Dimension(800, 600);
        (this.pictureScrollPane = new JScrollPane(this.picture)).setPreferredSize(dimension);
        this.pictureScrollPane.setMinimumSize(dimension);
        this.pictureScrollPane.setMaximumSize(dimension);
        this.add(this.pictureScrollPane);
    }
    
    public void actionPerformed(final ActionEvent e) {
    }
    
    private static ImageIcon createImageIcon(final String path) {
        URL imgURL = null;
        if (Constant.debugMode) {
            System.out.println("createImageIcon received: " + path);
        }
        try {
            imgURL = new URL(path);
            return new ImageIcon(imgURL);
        }
        catch (MalformedURLException e) {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private int getIncrement() {
        final int INCH = Toolkit.getDefaultToolkit().getScreenResolution();
        return (int)(INCH / 2.54);
    }
}
