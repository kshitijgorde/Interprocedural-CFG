import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.PopupMenu;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BookmarkMenuApplet extends Applet
{
    final PopupMenu m;
    final Button b;
    String target;
    String filename;
    final Applet a;
    ActionListener jumpToUrl;
    
    public BookmarkMenuApplet() {
        this.m = new PopupMenu();
        this.b = new Button();
        this.target = "j1menu";
        this.filename = "bookmark.htm";
        this.a = this;
        this.jumpToUrl = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    BookmarkMenuApplet.this.a.getAppletContext().showDocument(new URL(actionEvent.getActionCommand()), BookmarkMenuApplet.this.target);
                }
                catch (MalformedURLException ex) {
                    BookmarkMenuApplet.this.showStatus("Malformed URL: " + actionEvent.getActionCommand());
                }
            }
        };
    }
    
    static void addAboutMenuItem(final PopupMenu popupMenu, final ActionListener actionListener, final String actionCommand) {
        final MenuItem menuItem = new MenuItem("About \"Bookmark Menu Applet\"");
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(actionListener);
        popupMenu.insertSeparator(0);
        popupMenu.insert(menuItem, 0);
    }
    
    void debug(final String s) {
        System.out.println(s);
    }
    
    Reader getBookmarksReader(final String s) throws IOException {
        return new InputStreamReader(new URL(this.getDocumentBase(), s).openStream());
    }
    
    void getParameters() {
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            this.target = parameter;
        }
        final String parameter2 = this.getParameter("filename");
        if (parameter2 != null) {
            this.filename = parameter2;
        }
    }
    
    public void init() {
        this.setLayout(new BorderLayout(0, 0));
        this.b.setLabel("Loading...");
        this.b.setSize(this.getSize());
        this.add(this.b);
        this.add(this.m);
        this.validate();
        this.b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Rectangle bounds = BookmarkMenuApplet.this.b.getBounds();
                BookmarkMenuApplet.this.m.show(BookmarkMenuApplet.this.a, bounds.x, bounds.y + bounds.height);
            }
        });
        this.getParameters();
        try {
            final MenuLoader menuLoader = new MenuLoader(this.m, this.getBookmarksReader(this.filename), this.jumpToUrl);
            addAboutMenuItem(this.m, this.jumpToUrl, String.valueOf(this.getCodeBase().toString()) + "readme.html");
            menuLoader.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (actionEvent.getActionCommand().equals("done")) {
                        BookmarkMenuApplet.this.b.setLabel("Bookmarks");
                    }
                    else {
                        BookmarkMenuApplet.this.b.setLabel("Error");
                    }
                }
            });
            menuLoader.start();
        }
        catch (IOException ex) {
            this.showStatus("IOException:" + ex);
            this.b.setLabel("Loading Error");
            this.b.setEnabled(false);
        }
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
}
