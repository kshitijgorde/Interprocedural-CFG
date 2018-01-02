import netscape.javascript.JSObject;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.Event;
import java.awt.MediaTracker;
import java.io.BufferedReader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class navwinbutton extends Applet implements Runnable
{
    protected static final int reginfo = 0;
    Image bgimage;
    private Font ft;
    Font menufont;
    private String target;
    private Color bgcolor;
    Color fontcolor;
    private Color btnbgcolor;
    private boolean verticallayout;
    private int btnwidth;
    private int btnheight;
    private int btnverticalspacing;
    private int btnhorizontalspacing;
    private boolean autosize;
    private boolean bottom;
    private boolean left;
    private BufferedReader in;
    private String tline;
    boolean ndmode;
    private boolean link_added;
    boolean popupright;
    boolean grayscale;
    int textalign;
    private String gblURL;
    private String gbltarget;
    MediaTracker tracker;
    private Thread scroller;
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("More applets available at http://navsurf.com");
        return true;
    }
    
    public void stop() {
        if (this.scroller != null) {
            this.scroller.stop();
            this.scroller = null;
        }
    }
    
    private PopupMenu createPopupMenu() {
        final PopupMenu popupMenu = new PopupMenu();
        this.createItems(popupMenu, 1);
        if (!this.link_added) {
            final nsmenuitem nsmenuitem = new nsmenuitem("About the applet", "http://navsurf.com", "_blank", this);
            nsmenuitem.addActionListener(nsmenuitem);
            popupMenu.add(nsmenuitem);
            this.link_added = true;
        }
        return popupMenu;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgimage != null) {
            graphics.drawImage(this.bgimage, 0, 0, this);
        }
    }
    
    private Font parseFont(final String s, final String s2) {
        String nextToken = "TimesRoman";
        int int1 = 12;
        int int2 = 0;
        if (s == null) {
            return new Font(nextToken, int2, int1);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                int2 = Integer.parseInt(stringTokenizer.nextToken());
                if (int2 < 0 || int2 > 3) {
                    int2 = 0;
                }
                if (stringTokenizer.hasMoreTokens()) {
                    int1 = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        }
        return new Font(nextToken, int2, int1);
    }
    
    public navwinbutton() {
        this.bgimage = null;
        this.ft = null;
        this.menufont = null;
        this.target = null;
        this.bgcolor = null;
        this.fontcolor = null;
        this.btnbgcolor = null;
        this.verticallayout = false;
        this.btnwidth = 0;
        this.btnheight = 0;
        this.btnverticalspacing = 0;
        this.btnhorizontalspacing = 0;
        this.autosize = false;
        this.bottom = false;
        this.left = false;
        this.in = null;
        this.tline = null;
        this.ndmode = false;
        this.link_added = false;
        this.popupright = false;
        this.grayscale = false;
        this.textalign = 0;
        this.gblURL = null;
        this.gbltarget = null;
        this.tracker = null;
        this.scroller = null;
        this.bgimage = null;
        this.target = "_blank";
        this.bgcolor = new Color(255, 255, 255);
        this.fontcolor = new Color(0, 0, 0);
        this.verticallayout = false;
        this.btnwidth = 100;
        this.btnheight = 100;
        this.btnverticalspacing = 0;
        this.btnhorizontalspacing = 0;
        this.autosize = true;
        this.bottom = true;
        this.left = true;
        this.tline = "";
        this.ndmode = false;
        this.link_added = false;
        this.popupright = true;
        this.grayscale = true;
        this.textalign = 0;
        this.tracker = new MediaTracker(this);
        this.scroller = null;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void start() {
        if (this.scroller == null) {
            (this.scroller = new Thread(this)).start();
        }
    }
    
    public boolean handleEvent(final Event event) {
        System.out.println(event.id);
        return super.handleEvent(event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    private void retrieveParameters() {
        this.ft = this.parseFont(this.getParameter("buttonfont"), ", ");
        this.menufont = this.parseFont(this.getParameter("menufont"), ", ");
        final String parameter = this.getParameter("background");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ", ");
            if (stringTokenizer.hasMoreTokens()) {
                this.bgcolor = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.prepareImage(this.bgimage = this.getImage(this.getCodeBase(), stringTokenizer.nextToken()), this);
            }
        }
        final String parameter2 = this.getParameter("buttoncolor");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2, ", ");
            if (stringTokenizer2.hasMoreTokens()) {
                this.fontcolor = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
                if (stringTokenizer2.hasMoreTokens()) {
                    this.btnbgcolor = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
                }
                else {
                    this.btnbgcolor = this.bgcolor;
                }
            }
        }
        final String parameter3 = this.getParameter("buttonsize");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter3, ", ");
            if (stringTokenizer3.hasMoreTokens()) {
                final String nextToken = stringTokenizer3.nextToken();
                if (nextToken != null && nextToken.equals("no")) {
                    this.autosize = false;
                    if (stringTokenizer3.hasMoreTokens()) {
                        this.btnwidth = Integer.parseInt(stringTokenizer3.nextToken());
                    }
                    if (stringTokenizer3.hasMoreTokens()) {
                        this.btnheight = Integer.parseInt(stringTokenizer3.nextToken());
                    }
                }
            }
        }
        final String parameter4 = this.getParameter("generalsettings");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter4, ", ");
            if (stringTokenizer4.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer4.nextToken();
                if (nextToken2 != null && nextToken2.equals("bottom")) {
                    this.popupright = false;
                }
            }
            if (stringTokenizer4.hasMoreTokens()) {
                final String nextToken3 = stringTokenizer4.nextToken();
                if (nextToken3 != null && nextToken3.equals("no")) {
                    this.grayscale = false;
                }
            }
            if (stringTokenizer4.hasMoreTokens()) {
                final String nextToken4 = stringTokenizer4.nextToken();
                if (nextToken4 != null) {
                    if (nextToken4.equalsIgnoreCase("left")) {
                        this.textalign = 1;
                    }
                    if (nextToken4.equalsIgnoreCase("right")) {
                        this.textalign = 2;
                    }
                }
            }
        }
        final String parameter5 = this.getParameter("buttonlayout");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter5, ", ");
            if (stringTokenizer5.hasMoreTokens()) {
                final String nextToken5 = stringTokenizer5.nextToken();
                if (nextToken5 != null) {
                    if (nextToken5.equals("top")) {
                        this.verticallayout = true;
                        this.bottom = false;
                    }
                    if (nextToken5.equals("bottom")) {
                        this.verticallayout = true;
                        this.bottom = true;
                    }
                    if (nextToken5.equals("right")) {
                        this.verticallayout = false;
                        this.left = false;
                    }
                    if (nextToken5.equals("left")) {
                        this.verticallayout = false;
                        this.left = true;
                    }
                }
            }
        }
        final String parameter6 = this.getParameter("buttonspacing");
        if (parameter6 != null) {
            final StringTokenizer stringTokenizer6 = new StringTokenizer(parameter6, ", ");
            if (stringTokenizer6.hasMoreTokens()) {
                this.btnhorizontalspacing = Integer.parseInt(stringTokenizer6.nextToken());
            }
            if (stringTokenizer6.hasMoreTokens()) {
                this.btnverticalspacing = Integer.parseInt(stringTokenizer6.nextToken());
            }
        }
        final String parameter7 = this.getParameter("target");
        if (parameter7 != null) {
            this.target = parameter7;
        }
        final String parameter8 = this.getParameter("ndmode");
        if (parameter8 != null && parameter8.equalsIgnoreCase("yes")) {
            this.ndmode = true;
        }
        if (this.getParameter("appletinfo") != null) {
            System.out.println("Applet: NavWinButton");
            switch (false) {
                case 0: {
                    System.out.println("Licence: Evaluation");
                    break;
                }
                case 1: {
                    System.out.println("Licence: Registered");
                    break;
                }
                case 2: {
                    System.out.println("Licence: Professional");
                    break;
                }
            }
            System.out.println("Version: 1.5");
            System.out.println("Author: Toh Lik Khoong");
            System.out.println("Copyright: NavSurf.com");
        }
    }
    
    public void run() {
        try {
            this.tracker.waitForAll();
            int n = 0;
            for (int i = 0; i < this.countComponents(); ++i) {
                final nsbutton nsbutton = (nsbutton)this.getComponent(i);
                nsbutton.addMouseListener(nsbutton);
                if (this.grayscale && nsbutton.image != null) {
                    nsbutton.greyimage = nsbutton.createGreyScaleImage(nsbutton.image);
                }
                if (nsbutton.autosize) {
                    nsbutton.setOptimumSize();
                }
                nsbutton.repaint();
                this.validate();
                this.invalidate();
                if (this.ndmode) {
                    nsbutton.move(this.btnhorizontalspacing * i + n, 0);
                    n += nsbutton.size().width;
                }
            }
            this.repaint();
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        this.retrieveParameters();
        if (this.ndmode) {
            this.autosize = true;
            this.setLayout(null);
        }
        else {
            this.setLayout(new FlowLayout(1, this.btnhorizontalspacing, this.btnverticalspacing));
        }
        this.setFont(this.ft);
        this.setBackground(this.bgcolor);
        try {
            final URL url = new URL(this.getCodeBase(), this.getParameter("filename"));
            try {
                this.in = new BufferedReader(new InputStreamReader(url.openStream()));
                do {
                    this.tline = this.in.readLine();
                } while (this.tline != null && !this.tline.startsWith(":"));
                while (this.tline != null) {
                    String s;
                    for (s = this.tline; s.startsWith(":"); s = s.substring(1)) {}
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, "|", false);
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.equals("~")) {
                        nextToken = "";
                    }
                    Image image = null;
                    do {
                        this.tline = this.in.readLine();
                    } while (this.tline != null && !this.tline.startsWith(":"));
                    if (stringTokenizer.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer.nextToken();
                        if (!nextToken2.equals("~")) {
                            image = this.getImage(this.getCodeBase(), nextToken2);
                        }
                    }
                    PopupMenu popupMenu = this.createPopupMenu();
                    if (popupMenu.countItems() == 0) {
                        popupMenu = null;
                    }
                    else {
                        this.add(popupMenu);
                    }
                    String s2 = this.target;
                    String nextToken3 = null;
                    if (stringTokenizer.hasMoreTokens()) {
                        nextToken3 = stringTokenizer.nextToken();
                        if (nextToken3.equals("~")) {
                            nextToken3 = null;
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            s2 = stringTokenizer.nextToken();
                            if (s2.equals("~")) {
                                s2 = this.target;
                            }
                        }
                    }
                    final nsbutton nsbutton = new nsbutton(nextToken, image, nextToken3, s2, popupMenu, this);
                    if (this.ndmode) {
                        nsbutton.setBorder(1);
                        nsbutton.setSpacing(0);
                        this.verticallayout = false;
                    }
                    if (!this.autosize) {
                        nsbutton.setAutosize(false);
                        nsbutton.resize(this.btnwidth, this.btnheight);
                    }
                    if (this.verticallayout) {
                        nsbutton.setVerticalLayout(this.bottom);
                    }
                    else {
                        nsbutton.setHorizontalLayout(this.left);
                    }
                    nsbutton.setBackground(this.btnbgcolor);
                    this.add(nsbutton);
                    if (this.tline == null) {
                        return;
                    }
                }
            }
            catch (NoSuchElementException ex) {}
        }
        catch (IOException ex2) {}
    }
    
    private void createItems(final Menu menu, final int n) {
        try {
            while (this.tline != null) {
                int n2 = 0;
                int n3 = 0;
                String s;
                for (s = this.tline; s.startsWith(":"); s = s.substring(1), ++n2) {}
                if (n2 <= n) {
                    break;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "|", false);
                do {
                    this.tline = this.in.readLine();
                } while (this.tline != null && !this.tline.startsWith(":"));
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("-")) {
                    menu.addSeparator();
                }
                else {
                    String gbltarget = this.target;
                    final Menu menu2 = this.createMenu(nextToken, n2);
                    if (menu2.countItems() == 0) {
                        String nextToken2 = null;
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken2 = stringTokenizer.nextToken();
                            if (stringTokenizer.hasMoreTokens()) {
                                gbltarget = stringTokenizer.nextToken();
                                if (gbltarget.equals("~")) {
                                    gbltarget = this.target;
                                }
                            }
                        }
                        if (nextToken2 != null) {
                            this.gbltarget = gbltarget;
                            this.gblURL = nextToken2;
                        }
                        final nsmenuitem nsmenuitem = new nsmenuitem(nextToken, nextToken2, gbltarget, this);
                        nsmenuitem.addActionListener(nsmenuitem);
                        menu.add(nsmenuitem);
                    }
                    else {
                        menu.add(menu2);
                    }
                }
                if (this.tline != null) {
                    for (String s2 = this.tline; s2.startsWith(":"); s2 = s2.substring(1), ++n3) {}
                }
                if (n3 < n2) {
                    break;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("ioe");
        }
    }
    
    private Menu createMenu(final String s, final int n) {
        final Menu menu = new Menu(s);
        menu.setFont(this.menufont);
        this.createItems(menu, n);
        return menu;
    }
    
    protected boolean callJavascript(String s) {
        if (s.startsWith("javascript:")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(11), "(),' ");
            if (stringTokenizer.hasMoreTokens()) {
                s = stringTokenizer.nextToken().trim();
                if (s.startsWith("this.")) {
                    s = s.substring(5);
                }
                if (s.startsWith("window.")) {
                    s = s.substring(7);
                }
                final JSObject window = JSObject.getWindow((Applet)this);
                final String[] array = new String[10];
                int n = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    array[n++] = stringTokenizer.nextToken().trim();
                }
                window.call(s, (Object[])array);
            }
            return true;
        }
        return false;
    }
}
