import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.MenuItem;
import java.awt.Menu;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.awt.PopupMenu;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SiteMap2ech extends Applet implements MouseListener, ActionListener
{
    boolean inapplet;
    Hashtable menu_items;
    Hashtable urls;
    Hashtable targets;
    Image background;
    Image foreground;
    int width;
    int height;
    PopupMenu pu;
    String current_name;
    Thread woohoo;
    Vector ordered_menu_names;
    
    public void init() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        (this.pu = new PopupMenu()).setName(this.current_name);
        this.menu_items.put(this.current_name, this.pu);
        this.ordered_menu_names.removeAllElements();
        if (this.getParameter("BACKGROUND") != null) {
            try {
                this.background = this.getImage(new URL(this.getDocumentBase(), this.getParameter("BACKGROUND")));
            }
            catch (Exception ex) {}
        }
        if (this.getParameter("FOREGROUND") != null) {
            try {
                this.foreground = this.getImage(new URL(this.getDocumentBase(), this.getParameter("FOREGROUND")));
            }
            catch (Exception ex2) {}
        }
        if (this.getParameter("MAPFILE") != null) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), this.getParameter("MAPFILE")).openConnection().getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
                        final String nextToken = stringTokenizer.nextToken();
                        final URL url = stringTokenizer.hasMoreElements() ? new URL(this.getDocumentBase(), stringTokenizer.nextToken().trim()) : null;
                        final String s = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "_self";
                        final int inset = this.getInset(nextToken);
                        final String trim = nextToken.trim();
                        final boolean equals = trim.substring(0, 1).equals("+");
                        final String substring = trim.substring(1);
                        this.current_name = String.valueOf(this.getStub(inset)) + "_" + String.valueOf((this.current_name.length() > inset) ? (Integer.parseInt(this.getNumber(inset)) + 1) : 0);
                        this.ordered_menu_names.insertElementAt(this.current_name, 0);
                        if (equals) {
                            final Menu menu = new Menu(substring);
                            menu.setName(this.current_name);
                            menu.addActionListener(this);
                            this.menu_items.put(this.current_name, menu);
                        }
                        else {
                            final MenuItem menuItem = new MenuItem(substring);
                            menuItem.setName(this.current_name);
                            menuItem.addActionListener(this);
                            this.menu_items.put(this.current_name, menuItem);
                        }
                        if (url == null) {
                            continue;
                        }
                        this.urls.put(this.current_name, url);
                        this.targets.put(this.current_name, s);
                    }
                }
                bufferedReader.close();
            }
            catch (Exception ex3) {}
        }
        final Enumeration<String> elements = (Enumeration<String>)this.ordered_menu_names.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            ((Menu)this.menu_items.get(s2.substring(0, s2.lastIndexOf("_")))).insert((MenuItem)this.menu_items.get(s2), 0);
        }
        if (this.getParameter("COPYRIGHT").equals("SiteMap applet, Copyright 1998, Eric Harshbarger") && this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org")) {
            (this.pu = this.menu_items.get("")).addActionListener(this);
            this.add(this.pu);
            this.addMouseListener(this);
        }
    }
    
    public int getLastNumber(final String s) {
        String nextToken = "0";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "_");
        while (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
        }
        return Integer.parseInt(nextToken);
    }
    
    public String getNumber(final int n) {
        String nextToken = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(this.current_name, "_");
        for (int i = 0; i < n + 1; ++i) {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
        }
        return nextToken;
    }
    
    public String getStub(final int n) {
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(this.current_name, "_");
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            if (n2 == n) {
                return string;
            }
            string = String.valueOf(string) + "_" + stringTokenizer.nextToken();
            ++n2;
        }
        return string;
    }
    
    public int getInset(final String s) {
        int n;
        for (n = 0; s.charAt(n) == ' '; ++n) {}
        return n;
    }
    
    public void paint(final Graphics graphics) {
        if (this.inapplet) {
            if (this.background != null) {
                graphics.drawImage(this.background, 0, 0, this);
            }
            if (this.foreground != null) {
                graphics.drawImage(this.foreground, 0, 0, this);
            }
        }
        else {
            if (this.foreground != null) {
                graphics.drawImage(this.foreground, 0, 0, this);
            }
            if (this.background != null) {
                graphics.drawImage(this.background, 0, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pu.show(this, mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.inapplet = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.inapplet = false;
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem) {
            final String name = ((MenuItem)actionEvent.getSource()).getName();
            URL url = null;
            String s = null;
            if (this.urls.containsKey(name)) {
                url = this.urls.get(name);
                s = this.targets.get(name);
            }
            if (url != null) {
                try {
                    this.getAppletContext().showDocument(url, s);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public SiteMap2ech() {
        this.inapplet = false;
        this.menu_items = new Hashtable();
        this.urls = new Hashtable();
        this.targets = new Hashtable();
        this.current_name = "";
        this.ordered_menu_names = new Vector();
    }
}
