import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class popupURL extends Applet
{
    PopupMenu popup;
    MenuItem menu;
    Image image;
    String webpage;
    String pageURL;
    String[] holdPage;
    String[] holdURL;
    int pagenumber;
    int count;
    int index;
    int i;
    int in;
    int x;
    
    public popupURL() {
        this.pagenumber = 20;
        this.count = 0;
        this.index = 0;
    }
    
    public void init() {
        this.image = this.getImage(this.getDocumentBase(), "image.gif");
        this.enableEvents(176L);
        this.holdPage = new String[this.pagenumber];
        this.holdURL = new String[this.pagenumber];
        this.popup = new PopupMenu();
        this.in = 0;
        while (this.in < this.pagenumber) {
            this.webpage = this.getParameter("page" + this.in);
            this.pageURL = this.getParameter("url" + this.in);
            if (this.webpage == null) {
                break;
            }
            if (this.pageURL == null) {
                break;
            }
            ++this.count;
            ++this.index;
            this.holdPage[this.in] = this.webpage;
            this.holdURL[this.in] = this.pageURL;
            ++this.in;
        }
        this.i = 0;
        while (this.i < this.count) {
            (this.menu = new MenuItem(this.holdPage[this.i])).setActionCommand(this.holdPage[this.i]);
            this.popup.add(this.menu);
            if (this.i == this.count - 1) {
                break;
            }
            this.popup.addSeparator();
            ++this.i;
        }
        this.add(this.popup);
        this.popup.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String actionCommand = actionEvent.getActionCommand();
                System.out.println(actionCommand);
                popupURL.this.x = 0;
                while (popupURL.this.x < popupURL.this.index) {
                    if (actionCommand.equals(popupURL.this.holdPage[popupURL.this.x])) {
                        popupURL.this.newPage(popupURL.this.holdURL[popupURL.this.x]);
                        break;
                    }
                    final popupURL this$0 = popupURL.this;
                    ++this$0.x;
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getID() == 500) {
                    popupURL.this.popup.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
    }
    
    public void newPage(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {
            System.err.println(ex);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image, 10, 10, this);
    }
}
