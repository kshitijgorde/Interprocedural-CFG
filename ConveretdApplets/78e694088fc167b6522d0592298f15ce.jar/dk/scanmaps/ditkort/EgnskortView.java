// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class EgnskortView extends JPanel
{
    private static final long serialVersionUID = 0L;
    private ScrollablePicture picture;
    private JScrollPane pictureScrollPane;
    String imageFilename;
    String imagesize;
    
    public EgnskortView() {
        this.imageFilename = "0250427";
        this.imagesize = "";
        this.setBorder(BorderFactory.createEmptyBorder());
        final Dimension dimension = new Dimension(570, 455);
        final ImageIcon icon = createImageIcon(String.valueOf(Constant.baseEgnskortURL) + "/preview/" + this.imageFilename + ".jpg");
        this.picture = new ScrollablePicture(icon, this.getIncrement());
        (this.pictureScrollPane = new JScrollPane(this.picture)).setPreferredSize(dimension);
        this.pictureScrollPane.setMinimumSize(dimension);
        this.pictureScrollPane.setMaximumSize(dimension);
        this.add(this.pictureScrollPane);
    }
    
    public void setBorderAroundImage(final boolean set) {
        if (set) {
            this.picture.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        }
        else {
            this.picture.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    
    public void setImageSize(final int size) {
        switch (size) {
            case 49: {
                this.imagesize = "small";
                break;
            }
            case 50: {
                this.imagesize = "medium";
                break;
            }
            case 51: {
                this.imagesize = "large";
                break;
            }
        }
    }
    
    public void updateImage(final String imageFilename, final String imagesize) {
        final ImageIcon icon = createImageIcon(String.valueOf(Constant.baseEgnskortURL) + "/" + imagesize + "/" + imageFilename + ".jpg");
        if (Constant.debugMode) {
            System.out.println("Dimension of egnskort image is: " + icon.getIconWidth() + "x" + icon.getIconHeight());
        }
        this.picture.setIcon(icon);
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
