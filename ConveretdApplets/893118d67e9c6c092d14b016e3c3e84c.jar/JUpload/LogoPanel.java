// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class LogoPanel extends JPanel implements ImageObserver
{
    Image myLogo;
    URL imageURL;
    startup applet;
    private String imageFileName;
    
    LogoPanel(final startup applet) {
        this.myLogo = null;
        this.imageURL = null;
        this.applet = null;
        this.debug("LogoPanel() constructor");
        this.applet = applet;
    }
    
    public void debug(final String s) {
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        this.debug("LogoPanel() imageUpdate()");
        this.debug(" img=" + img);
        this.debug(" infoflags=" + infoflags + " (should be " + 32);
        this.debug(" x=" + x + " y=" + y + " width=" + width + " height=" + height);
        this.debug("LogoPanel() imageUpdate() dimension = " + this.myLogo.getWidth(this) + "x" + this.myLogo.getHeight(this));
        this.setSize(this.myLogo.getWidth(this), this.myLogo.getHeight(this));
        this.setPreferredSize(new Dimension(this.myLogo.getWidth(this), this.myLogo.getHeight(this)));
        this.setVisible(true);
        this.invalidate();
        this.applet.validate();
        if ((infoflags & 0xC0) != 0x0) {
            this.debug("LogoPanel() image loading is not done yet: " + infoflags);
            return false;
        }
        if (infoflags == 32) {
            this.updateInfos();
        }
        return true;
    }
    
    public void init() {
        this.debug("LogoPanel() init()");
        this.myLogo = null;
        this.imageURL = null;
        try {
            this.imageFileName = Configurator.getImageURL();
            if (this.imageFileName != null) {
                if (!this.imageFileName.equalsIgnoreCase("")) {
                    this.imageURL = new URL(this.applet.getDocumentBase(), this.imageFileName);
                    this.myLogo = this.applet.getImage(this.imageURL);
                    this.debug("LogoPanel() init() imageURL = " + this.imageURL);
                    this.debug("LogoPanel() init() myLogo = " + this.myLogo);
                    this.setSize(this.myLogo.getWidth(this), this.myLogo.getHeight(this));
                    this.setVisible(true);
                    this.doLayout();
                    this.validate();
                    this.revalidate();
                    this.invalidate();
                }
                else {
                    this.setVisible(false);
                }
            }
            else {
                this.setVisible(false);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        this.debug("LogoPanel() paint() graphics=" + g);
        if (this.myLogo != null) {
            this.debug("LogoPanel() paint() logo " + this.myLogo);
            g.drawImage(this.myLogo, 0, 0, this);
            this.debug("LogoPanel() paint() checking size of image");
            return;
        }
        this.debug("LogoPanel() paint() logo is null");
    }
    
    private void updateInfos() {
        this.debug("LogoPanel() updateInfos()");
        final Dimension dimSize = new Dimension(this.myLogo.getWidth(this), this.myLogo.getHeight(this));
        this.setMinimumSize(dimSize);
        this.setMaximumSize(dimSize);
        this.setSize(dimSize);
        this.setPreferredSize(dimSize);
        this.setVisible(true);
        this.invalidate();
        this.repaint();
        this.applet.validate();
        this.applet.repaint();
    }
}
