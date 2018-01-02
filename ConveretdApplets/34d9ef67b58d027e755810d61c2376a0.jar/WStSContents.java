import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WStSContents extends Applet
{
    private appletScrollbar theScrollbar;
    private Vector listItems;
    private int maxVisableItems;
    private int oldTop;
    private Image offImage;
    private Graphics offGraphics;
    Rectangle mySize;
    int arrowLeft;
    int itemHeight;
    int itemTopOffset;
    
    public WStSContents() {
        this.theScrollbar = null;
        this.listItems = new Vector();
        this.oldTop = 0;
        this.offImage = null;
        this.offGraphics = null;
    }
    
    @Override
    public void init() {
        this.setFont(new Font(this.getGraphics().getFont().getName(), this.getGraphics().getFont().getStyle(), 9));
        this.setBackground(Color.white);
        this.setForeground(Color.blue);
        this.mySize = new Rectangle(0, 0, this.size().width - 16, this.size().height);
        this.itemTopOffset = 1;
        this.itemHeight = this.getGraphics().getFontMetrics().getHeight() + 3;
        this.maxVisableItems = this.mySize.height / this.itemHeight;
    }
    
    @Override
    public void start() {
        final WStS_ContentsConfig wStS_ContentsConfig = new WStS_ContentsConfig();
        while (!wStS_ContentsConfig.done()) {
            final Vector nextEntry = wStS_ContentsConfig.nextEntry();
            if (nextEntry != null && nextEntry.size() > 0) {
                final navListItem handleReadEntry = this.handleReadEntry(nextEntry, Color.gray, wStS_ContentsConfig);
                if (handleReadEntry == null) {
                    continue;
                }
                this.listItems.addElement(handleReadEntry);
            }
        }
        this.offImage = this.createImage(this.mySize.width, this.mySize.height);
        this.offGraphics = this.offImage.getGraphics();
        this.theScrollbar = new appletScrollbar(this, this.size().width - 16, this.mySize.y, this.itemHeight * this.maxVisableItems + 1, this.maxVisableItems, 0);
        this.repaint();
    }
    
    @Override
    public void stop() {
        for (int i = 0; i < this.listItems.size(); ++i) {
            ((navListItem)this.listItems.elementAt(i)).selfDestruct();
        }
        this.listItems.removeAllElements();
        this.theScrollbar.stop();
        this.theScrollbar = null;
        this.offImage = null;
        this.offGraphics = null;
    }
    
    navListItem handleReadEntry(Vector nextEntry, Color color, final WStS_ContentsConfig wStS_ContentsConfig) {
        navListItem handleReadEntry = null;
        navListItem navListItem = null;
        int i = 0;
        do {
            if (nextEntry.size() > 0) {
                final String s = nextEntry.elementAt(0);
                if (s.compareTo("Folder") == 0) {
                    if (i != 0) {
                        handleReadEntry = this.handleReadEntry(nextEntry, color, wStS_ContentsConfig);
                        if (handleReadEntry != null) {
                            navListItem.addItem(handleReadEntry);
                        }
                    }
                    else {
                        i = 1;
                        if (nextEntry.size() == 3) {
                            final StringTokenizer stringTokenizer = new StringTokenizer(nextEntry.elementAt(2));
                            color = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                        }
                        navListItem = new navListItem(nextEntry.elementAt(1), "", navListItem.kClosedFolderCode, color);
                    }
                }
                else if (s.compareTo("File") == 0) {
                    handleReadEntry = new navListItem(nextEntry.elementAt(1), nextEntry.elementAt(2), navListItem.kFileCode, color);
                    if (i != 0) {
                        navListItem.addItem(handleReadEntry);
                    }
                }
                else if (s.compareTo("Folder End") == 0) {
                    i = 0;
                }
            }
            if (i != 0) {
                nextEntry = wStS_ContentsConfig.nextEntry();
                if (nextEntry == null) {
                    break;
                }
                continue;
            }
        } while (i != 0);
        if (navListItem != null) {
            return navListItem;
        }
        return handleReadEntry;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    @Override
    public void update(final Graphics graphics) {
        if (graphics == null || this.mySize == null || this.offImage == null || this.offGraphics == null || this.theScrollbar == null) {
            return;
        }
        final int y = this.mySize.y;
        final int n = this.mySize.x + 2 + graphics.getFontMetrics().getHeight() + graphics.getFontMetrics().getHeight() / 2;
        this.offGraphics.setColor(this.getBackground());
        this.offGraphics.fillRect(0, 0, this.mySize.width, this.mySize.height);
        this.offGraphics.drawRect(0, 0, this.mySize.width, this.mySize.height);
        this.offGraphics.setColor(this.getForeground());
        this.offGraphics.setFont(graphics.getFont());
        this.arrowLeft = this.mySize.x + 2;
        int drawItems = 0;
        for (int size = this.listItems.size(), i = 0; i < size; ++i) {
            drawItems = ((navListItem)this.listItems.elementAt(i)).drawItems(this.theScrollbar.getValue() + 1, this.theScrollbar.getValue() + 1 + this.maxVisableItems - 1, drawItems, y, n, this.offGraphics, this);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
        this.theScrollbar.paint(graphics);
    }
    
    @Override
    public boolean handleEvent(final Event event) {
        boolean b = false;
        if (this.theScrollbar != null) {
            b = this.theScrollbar.handleEvent(event);
        }
        if (!b && event.id == 502) {
            final int y = this.mySize.y;
            final int n = this.mySize.x + 2 + this.getGraphics().getFontMetrics().getHeight() + this.getGraphics().getFontMetrics().getHeight() / 2;
            this.arrowLeft = this.mySize.x + 2;
            int handleHit = 0;
            for (int size = this.listItems.size(), i = 0; i < size; ++i) {
                handleHit = ((navListItem)this.listItems.elementAt(i)).handleHit(this.theScrollbar.getValue() + 1, this.theScrollbar.getValue() + 1 + this.maxVisableItems - 1, handleHit, y, n, event.x, event.y, this.getGraphics(), this);
                if (handleHit == -1) {
                    this.resetScrollbar();
                    this.repaint();
                    break;
                }
            }
            b = true;
        }
        if (!b) {
            b = super.handleEvent(event);
        }
        return b;
    }
    
    void showDocument(final URL url) {
        this.getAppletContext().showDocument(url);
    }
    
    void resetScrollbar() {
        int n = 0;
        for (int size = this.listItems.size(), i = 0; i < size; ++i) {
            n += ((navListItem)this.listItems.elementAt(i)).countItems();
        }
        this.theScrollbar.setValues(this.theScrollbar.getValue(), n);
    }
    
    URL MyStringToURL(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            url = null;
        }
        return url;
    }
}
