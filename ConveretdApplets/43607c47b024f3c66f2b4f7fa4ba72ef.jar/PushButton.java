import java.util.Vector;
import java.util.StringTokenizer;
import java.applet.AppletContext;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Image;
import java.net.URL;
import java.awt.PopupMenu;
import java.applet.Applet;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class PushButton extends ImageButton implements ButtonListener, ActionListener
{
    private Applet parent;
    private PopupMenu menu;
    public String description;
    public String target;
    public URL onURL;
    public URL offURL;
    public URL pushedURL;
    public URL poppedURL;
    public URL triggerURL;
    public Image loadImage;
    public Rectangle loadBounds;
    
    public void buttonOff(final MouseEvent mouseEvent) {
        if (this.offURL != null) {
            this.play(this.offURL);
        }
        this.setCursor(new Cursor(0));
        if (this.description != null) {
            this.showStatus("");
        }
    }
    
    public PushButton() {
        this.addButtonListener(this);
    }
    
    public void buttonPopped(final MouseEvent mouseEvent) {
        if (this.poppedURL != null) {
            this.play(this.poppedURL);
        }
        this.setCursor(new Cursor(12));
    }
    
    public void buttonTriggered(final MouseEvent mouseEvent) {
        if (this.triggerURL != null) {
            this.play(this.triggerURL);
        }
        if (this.menu != null) {
            this.menu.show(this, mouseEvent.getX(), mouseEvent.getY());
            this.setCursor(new Cursor(0));
        }
    }
    
    public void addNotify() {
        super.addNotify();
        try {
            this.parent = (Applet)this.getParent();
        }
        catch (Exception ex) {}
    }
    
    private URL parseURL(final String s, final URL url, final boolean b) {
        URL url2 = url;
        if (s != null) {
            try {
                if (s.indexOf(":") != -1) {
                    url2 = new URL(s);
                }
                else {
                    url2 = new URL(b ? this.getCodeBase() : this.getDocumentBase(), s);
                }
            }
            catch (MalformedURLException ex) {}
        }
        return url2;
    }
    
    public URL getCodeBase() {
        if (this.parent != null) {
            return this.parent.getCodeBase();
        }
        return super.getCodeBase();
    }
    
    public URL getDocumentBase() {
        if (this.parent != null) {
            return this.parent.getDocumentBase();
        }
        return super.getDocumentBase();
    }
    
    public void update(final Graphics graphics) {
        super.update(graphics);
        if (this.loadImage != null && this.loadBounds.width > 0 && this.loadBounds.intersects(this.getBounds())) {
            graphics.drawImage(this.loadImage, this.loadBounds.x - this.getLocation().x, this.loadBounds.y - this.getLocation().y, null);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        String s = null;
        final AppletContext appletContext = this.getAppletContext();
        if (actionCommand != null) {
            final String[] splitValues = this.splitValues(actionCommand, "\n");
            final int index;
            if (splitValues.length == 1 && (index = this.target.toLowerCase().indexOf("applet:")) != -1) {
                s = this.target.substring(index + 7).trim();
            }
            else {
                final int index2;
                if (splitValues.length > 1 && (index2 = splitValues[1].toLowerCase().indexOf("applet:")) != -1) {
                    s = splitValues[1].substring(index2 + 7).trim();
                }
            }
            if (s != null) {
                try {
                    final Applet applet = appletContext.getApplet(s);
                    if (applet != null) {
                        ((ActionListener)applet).actionPerformed(new ActionEvent(this, 1001, splitValues[0]));
                    }
                }
                catch (Exception ex) {}
            }
            else {
                final URL url = this.parseURL(splitValues[0], null, false);
                if (url != null) {
                    appletContext.showDocument(url, (splitValues.length > 1) ? splitValues[1] : this.target);
                }
            }
        }
    }
    
    public String getAppletInfo() {
        return "Applet: PushButton for PushIt Applet\nVersion: 1.2 for Java 1.1 Platform\nAuthor: Uldarico Muico, um@mail.com\nDate: 29 July 1999";
    }
    
    public void showStatus(final String s) {
        if (this.parent != null) {
            this.parent.showStatus(s);
        }
        else {
            super.showStatus(s);
        }
    }
    
    public void buttonOn(final MouseEvent mouseEvent) {
        if (this.onURL != null) {
            this.play(this.onURL);
        }
        this.setCursor(new Cursor(12));
        if (this.description != null) {
            this.showStatus(this.description);
        }
    }
    
    private String[] splitValues(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector vector = new Vector<String>();
        String[] array = null;
        try {
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            final int size = vector.size();
            array = new String[size];
            for (int i = 0; i < size; ++i) {
                array[i] = vector.elementAt(i);
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    public void buttonPushed(final MouseEvent mouseEvent) {
        if (this.pushedURL != null) {
            this.play(this.pushedURL);
        }
        this.setCursor(new Cursor(13));
    }
    
    public void setPopupMenu(final PopupMenu menu) {
        if (menu != null) {
            this.add(menu);
        }
        this.menu = menu;
    }
    
    public AppletContext getAppletContext() {
        if (this.parent != null) {
            return this.parent.getAppletContext();
        }
        return super.getAppletContext();
    }
}
