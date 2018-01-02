// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Image;
import borland.jbcl.util.ImageLoader;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.awt.Component;
import java.awt.Frame;

public class DecoratedFrame extends Frame
{
    protected Component client;
    protected boolean exitOnClose;
    protected String imageName;
    protected URL url;
    
    public DecoratedFrame() {
        this.exitOnClose = true;
        this.setLayout(new BorderLayout());
        this.setBackground(SystemColor.control);
        this.enableEvents(64L);
    }
    
    public Component getClient() {
        return this.client;
    }
    
    public void setClient(final Component client) {
        if (this.client != null) {
            this.remove(this.client);
        }
        super.add(this.client = client, "Center");
        final Dimension size = client.getSize();
        if (size.width > 0 && size.height > 0) {
            this.setSize(size.width + this.getInsets().left + this.getInsets().right, size.height + this.getInsets().top + this.getInsets().bottom);
        }
    }
    
    public void setExitOnClose(final boolean exitOnClose) {
        this.exitOnClose = exitOnClose;
    }
    
    public boolean isExitOnClose() {
        return this.exitOnClose;
    }
    
    public void setIconImageName(final String s) throws IOException {
        if (s != null && !s.equals("")) {
            this.setupImage(ImageLoader.load(s, this), s);
        }
        else {
            this.imageName = null;
            super.setIconImage(null);
        }
    }
    
    public String getIconImageName() {
        return this.imageName;
    }
    
    public void setIconImage(final Image image) {
        try {
            this.setupImage(image, "");
        }
        catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void setIconImageURL(final URL url) throws IOException {
        this.url = url;
        this.setupImage(ImageLoader.load(url, this), url.toString());
    }
    
    public URL getIconImageURL() {
        return this.url;
    }
    
    protected void setupImage(final Image iconImage, final String imageName) throws IOException {
        this.prepareImage(iconImage, this);
        if ((this.checkImage(iconImage, this) & 0x40) != 0x0) {
            throw new IOException(Res.format(19, new String[] { imageName }));
        }
        this.imageName = imageName;
        super.setIconImage(iconImage);
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        super.processWindowEvent(windowEvent);
        if (windowEvent.getID() == 201 && this.getParent() == null && this.exitOnClose) {
            System.exit(0);
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        final MenuBar menuBar = this.getMenuBar();
        if (menuBar != null) {
            final Font font = menuBar.getFont();
            final FontMetrics fontMetrics = (font != null) ? this.getFontMetrics(font) : null;
            final int n = (fontMetrics != null) ? fontMetrics.getHeight() : 0;
            final Dimension dimension = preferredSize;
            dimension.height += n;
        }
        return preferredSize;
    }
}
