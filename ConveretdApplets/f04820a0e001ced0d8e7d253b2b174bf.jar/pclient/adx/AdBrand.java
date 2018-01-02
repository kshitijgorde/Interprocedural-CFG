// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import com.pchat.sc.StringUtil;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.net.URL;
import java.awt.image.ImageObserver;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.util.Properties;
import pclient.adv.AppletSpice;
import java.awt.event.MouseListener;
import pclient.adv.ComInter;
import javax.swing.JPanel;

public class AdBrand extends JPanel implements ComInter, MouseListener
{
    private static final int AD_W = 468;
    private static final int AD_H = 60;
    private AppletSpice paraApplet;
    private Properties adConf;
    private JLabel imageLabel;
    private Dimension idealDim;
    private String clickablePage;
    
    public AdBrand() {
        this.adConf = null;
        this.clickablePage = null;
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        int int1 = this.paraApplet.paraConf.getInt("Ad.BrandW", 468);
        int int2 = this.paraApplet.paraConf.getInt("Ad.BrandH", 60);
        if (int1 <= 0) {
            int1 = 468;
        }
        if (int2 <= 0) {
            int2 = 60;
        }
        this.setMaximumSize(this.idealDim = new Dimension(int1, int2));
        this.setPreferredSize(this.idealDim);
        this.buildGUI();
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        if (this.adConf != null) {
            return;
        }
        final URL codeBase = this.paraApplet.paraConf.getApplet().getCodeBase();
        final String value = this.paraApplet.paraConf.get("Net.Site");
        Image image = null;
        if (this.loadPropSite(codeBase, value)) {
            image = this.loadImageSite(codeBase, value);
        }
        else {
            this.paraApplet.paraConf.printer().print("ad brand conf not loaded, site,");
            if (this.loadPropServer(codeBase)) {
                this.paraApplet.paraConf.printer().print("server ad brand conf loaded, OK.");
                image = this.loadImageServer(codeBase);
            }
        }
        this.clickablePage = this.adConf.getProperty("Ad.BrandPage");
        if (image != null) {
            if (this.paraApplet.paraConf.getBool("Ad.Scale", false)) {
                image = this.scaleImage(image);
            }
            this.imageLabel.setIcon(new ImageIcon(image));
            this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            this.imageLabel.invalidate();
        }
    }
    
    public void destroy() {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.paraApplet.paraConf.printer().print("brand clicked.");
        if (!StringUtil.isTrimmedEmpty(this.clickablePage)) {
            this.paraApplet.paraConf.loadPage(this.clickablePage);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!StringUtil.isTrimmedEmpty(this.clickablePage)) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private boolean loadPropSite(final URL url, final String s) {
        this.adConf = new Properties();
        final Properties loadAdPropSite = AppletSpice.loadAdPropSite(url, s, this.paraApplet);
        if (loadAdPropSite == null) {
            return false;
        }
        this.adConf = loadAdPropSite;
        return true;
    }
    
    private Image loadImageSite(final URL url, final String s) {
        final String property = this.adConf.getProperty("Ad.BrandImg");
        if (property == null) {
            return null;
        }
        return AppletSpice.loadImageSite(url, s, this.paraApplet, property);
    }
    
    private boolean loadPropServer(final URL url) {
        this.adConf = new Properties();
        final Properties loadAdPropServer = AppletSpice.loadAdPropServer(url, this.paraApplet);
        if (loadAdPropServer == null) {
            return false;
        }
        this.adConf = loadAdPropServer;
        return true;
    }
    
    private Image loadImageServer(final URL url) {
        final String property = this.adConf.getProperty("Ad.BrandImg");
        if (property == null) {
            return null;
        }
        return AppletSpice.loadImageServer(url, this.paraApplet, property);
    }
    
    private Image scaleImage(final Image image) {
        if (image.getWidth(null) <= this.idealDim.width && image.getHeight(null) <= this.idealDim.height) {
            return image;
        }
        int n = image.getWidth(null);
        int n2 = image.getHeight(null);
        if (image.getWidth(null) > this.idealDim.width) {
            n = this.idealDim.width;
        }
        if (image.getHeight(null) > this.idealDim.height) {
            n2 = this.idealDim.height;
        }
        return image.getScaledInstance(n, n2, 1);
    }
    
    private void buildGUI() {
        final JLabel imageLabel = new JLabel("", 0);
        imageLabel.addMouseListener(this);
        this.setLayout(new BorderLayout(0, 1));
        final String value = this.paraApplet.paraConf.get("Ad.Align", "center");
        if (value.equalsIgnoreCase("left")) {
            imageLabel.setHorizontalAlignment(2);
        }
        else if (value.equalsIgnoreCase("right")) {
            imageLabel.setHorizontalAlignment(4);
        }
        this.add("Center", imageLabel);
        this.imageLabel = imageLabel;
    }
}
