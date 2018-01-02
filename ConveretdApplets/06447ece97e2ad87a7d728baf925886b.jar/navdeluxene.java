import java.io.InputStream;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Component;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.io.DataInputStream;
import java.applet.AudioClip;
import java.util.Vector;
import java.net.URL;
import java.io.BufferedReader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class navdeluxene extends Applet
{
    private int spacing;
    private Image bgimage;
    private Font barfont;
    private Font menufont;
    private String target;
    private static int NORMAL;
    private static int HIGHLIGHTED;
    private Color bgcolor;
    private int barheight;
    private int menuheight;
    private int nb_rows;
    boolean center;
    boolean top;
    private BufferedReader in;
    private String tline;
    private String bgTarget;
    private URL bgURL;
    Vector mp;
    AudioClip ac_menuopen;
    AudioClip ac_itemselect;
    private DataInputStream dis;
    Color[] barfontcolor;
    Color[] barcolor;
    Color barbutton;
    FontMetrics headerfm;
    Color[] menucolor;
    Color[] fontcolor;
    Color panelborder;
    FontMetrics menufm;
    
    private void repositionMenus() {
        final int[] array = new int[20];
        final int[] array2 = new int[20];
        for (int i = this.mp.size() - 1; i >= 0; --i) {
            final navdeluxepanel navdeluxepanel = this.mp.elementAt(i);
            if (navdeluxepanel.menulevel == 1) {
                array2[0] = navdeluxepanel.location().x;
                array2[1] = navdeluxepanel.size().width;
            }
            else {
                int n = 0;
                array2[navdeluxepanel.menulevel] = navdeluxepanel.size().width;
                for (int j = 0; j < navdeluxepanel.menulevel; ++j) {
                    n += array2[j];
                }
                if (navdeluxepanel.column == 1) {
                    navdeluxepanel.move(n - 2 * navdeluxepanel.menulevel, navdeluxepanel.location().y);
                }
                else {
                    array2[navdeluxepanel.menulevel] = navdeluxepanel.size().width - array2[navdeluxepanel.menulevel - 1] / 2;
                    navdeluxepanel.move(n - array2[navdeluxepanel.menulevel - 1] / 2 - 2 * navdeluxepanel.menulevel, navdeluxepanel.location().y);
                }
            }
        }
        array[0] = 0;
        for (int k = this.mp.size() - 1; k >= 0; --k) {
            final navdeluxepanel navdeluxepanel2 = this.mp.elementAt(k);
            final int menulevel = navdeluxepanel2.menulevel;
            if (navdeluxepanel2.menulevel != 1) {
                navdeluxepanel2.move(navdeluxepanel2.location().x, navdeluxepanel2.location().y - array[navdeluxepanel2.menulevel]);
                int n2;
                if (this.top) {
                    n2 = navdeluxepanel2.location().y + navdeluxepanel2.size().height - this.size().height;
                }
                else {
                    n2 = navdeluxepanel2.location().y + navdeluxepanel2.size().height - this.size().height + this.barheight;
                }
                array[navdeluxepanel2.menulevel + 1] = array[navdeluxepanel2.menulevel];
                if (n2 > 0) {
                    navdeluxepanel2.move(navdeluxepanel2.location().x, navdeluxepanel2.location().y - n2);
                    final int[] array3 = array;
                    final int n3 = navdeluxepanel2.menulevel + 1;
                    array3[n3] += n2;
                }
            }
            if (navdeluxepanel2.location().x + navdeluxepanel2.size().width > this.size().width) {
                navdeluxepanel2.move(this.size().width - navdeluxepanel2.size().width, navdeluxepanel2.location().y);
            }
        }
    }
    
    private Font parseFont(final String s, final String s2) {
        String nextToken = "Arial";
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
    
    public void paint(final Graphics graphics) {
        if (this.bgimage != null) {
            if (this.center) {
                graphics.drawImage(this.bgimage, (this.size().width - this.bgimage.getWidth(null)) / 2, (this.size().height - this.bgimage.getHeight(null)) / 2, this);
            }
            else {
                graphics.drawImage(this.bgimage, 0, 0, this);
            }
        }
        graphics.setColor(this.barcolor[navdeluxene.NORMAL]);
        if (this.top) {
            graphics.fillRect(0, 0, this.size().width, this.nb_rows * this.barheight);
        }
        else {
            graphics.fillRect(0, this.size().height - this.nb_rows * this.barheight, this.size().width, this.nb_rows * this.barheight);
        }
    }
    
    public navdeluxene() {
        this.spacing = 50;
        this.bgimage = null;
        this.barfont = new Font("TimesRoman", 1, 12);
        this.menufont = new Font("TimesRoman", 1, 12);
        this.target = "_self";
        this.bgcolor = new Color(16777215);
        this.nb_rows = 1;
        this.center = true;
        this.top = true;
        this.tline = "";
        this.mp = new Vector();
        this.ac_menuopen = null;
        this.ac_itemselect = null;
        this.barfontcolor = new Color[2];
        this.barcolor = new Color[2];
        this.barbutton = new Color(0);
        this.headerfm = null;
        this.menucolor = new Color[2];
        this.fontcolor = new Color[2];
        this.panelborder = new Color(0);
        this.menufm = null;
    }
    
    public final String readLine() {
        String s = null;
        try {
            if (this.getParameter("readutf") != null) {
                s = this.dis.readUTF();
            }
            else {
                s = this.dis.readLine();
            }
        }
        catch (IOException ex) {}
        if (s != null) {
            s = s.trim();
        }
        return s;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    static {
        navdeluxene.NORMAL = 0;
        navdeluxene.HIGHLIGHTED = 1;
    }
    
    void showMenu(final boolean b) {
        for (int i = 0; i < this.mp.size(); ++i) {
            final navdeluxepanel navdeluxepanel = this.mp.elementAt(i);
            if (b) {
                navdeluxepanel.show();
            }
            else {
                navdeluxepanel.hide();
            }
        }
    }
    
    private navdeluxepanel createMenus(final int n, final int n2) {
        final navdeluxepanel navdeluxepanel = new navdeluxepanel(n2, this);
        final Vector vector = new Vector<navdeluxeheader>();
        int n3 = 1;
        int n4 = 1;
        int stringWidth = 0;
        while (this.tline != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.tline, ":", true);
            int n5 = 0;
            int n6 = 0;
            String s;
            for (s = this.tline; s.startsWith(":"); s = s.substring(1), ++n5) {}
            if (n5 <= n2) {
                break;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "|", false);
            do {
                this.tline = this.readLine();
            } while (this.tline != null && !this.tline.startsWith(":"));
            final String nextToken = stringTokenizer2.nextToken();
            String nextToken2 = null;
            String s2 = null;
            if (stringTokenizer2.hasMoreTokens()) {
                nextToken2 = stringTokenizer2.nextToken();
                s2 = this.target;
                if (stringTokenizer2.hasMoreTokens()) {
                    s2 = stringTokenizer2.nextToken();
                }
            }
            int n7;
            if (this.menuheight * n3 + this.barheight * this.nb_rows <= this.size().height) {
                n7 = n3;
                n4 = n3;
            }
            else {
                n7 = n3 - n4;
            }
            navdeluxepanel menus = this.createMenus(n + this.menuheight * (n7 - 1), n5);
            menus.setFont(this.menufont);
            if (menus.countComponents() == 0) {
                menus = null;
            }
            final navdeluxeheader navdeluxeheader = new navdeluxeheader(nextToken, menus, nextToken2, s2, false, this);
            if (this.menufm.stringWidth(navdeluxeheader.text) > stringWidth) {
                stringWidth = this.menufm.stringWidth(navdeluxeheader.text);
            }
            navdeluxepanel.add(navdeluxeheader);
            vector.addElement(navdeluxeheader);
            if (this.tline != null) {
                for (String s3 = this.tline; s3.startsWith(":"); s3 = s3.substring(1), ++n6) {}
            }
            ++n3;
            if (n6 < n5) {
                break;
            }
        }
        if (navdeluxepanel.countComponents() != 0) {
            int n8 = 0;
            if (navdeluxepanel.countComponents() * this.menuheight + this.barheight * this.nb_rows + 2 > this.size().height) {
                while (n8 * this.menuheight + this.barheight * this.nb_rows + 2 <= this.size().height) {
                    ++n8;
                }
            }
            navdeluxepanel.move(0, n);
            if (n8 == 0) {
                navdeluxepanel.resize(stringWidth + 4, this.menuheight * navdeluxepanel.countComponents() + 4);
            }
            else {
                navdeluxepanel.resize(stringWidth * 2 + 4, this.menuheight * (n8 - 1) + 4);
            }
            this.mp.addElement(navdeluxepanel);
            this.add(navdeluxepanel);
            for (int i = 0; i < vector.size(); ++i) {
                final navdeluxeheader navdeluxeheader2 = vector.elementAt(i);
                navdeluxeheader2.resize(stringWidth, this.menuheight);
                if (n8 != 0 && i + 1 >= n8) {
                    navdeluxeheader2.move(stringWidth + 2, this.menuheight * (i - n8 + 1) + 2);
                }
                else {
                    if (n8 != 0 && navdeluxeheader2.m != null) {
                        navdeluxeheader2.m.column = 2;
                    }
                    navdeluxeheader2.move(2, this.menuheight * i + 2);
                }
            }
        }
        return navdeluxepanel;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target instanceof navdeluxene && ((this.top && n2 > this.nb_rows * this.barheight) || (!this.top && n2 < this.size().height - this.nb_rows * this.barheight)) && this.getParameter("bglink") != null) {
            this.getAppletContext().showDocument(this.bgURL, this.bgTarget);
        }
        return super.mouseDown(event, n, n2);
    }
    
    private void retrieveParameters() {
        final String parameter = this.getParameter("barfont");
        if (parameter != null) {
            this.barfont = this.parseFont(parameter, ", ");
        }
        final String parameter2 = this.getParameter("menufont");
        if (parameter2 != null) {
            this.menufont = this.parseFont(parameter2, ", ");
        }
        else {
            this.menufont = this.barfont;
        }
        final String parameter3 = this.getParameter("target");
        if (parameter3 != null) {
            this.target = parameter3;
        }
        this.parseColor(this.getParameter("barcolor"), ", ", this.barcolor);
        this.parseColor(this.getParameter("barfontcolor"), ", ", this.barfontcolor);
        this.parseColor(this.getParameter("menucolor"), ", ", this.menucolor);
        this.parseColor(this.getParameter("menufontcolor"), ", ", this.fontcolor);
        final String parameter4 = this.getParameter("bordercolor");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter4, ", ");
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("no")) {
                    this.barbutton = null;
                }
                else {
                    this.barbutton = new Color(Integer.parseInt(nextToken, 16));
                }
            }
            else {
                this.barbutton = this.menucolor[0].brighter();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.equals("no")) {
                    this.panelborder = null;
                }
                else {
                    this.panelborder = new Color(Integer.parseInt(nextToken2, 16));
                }
            }
            else {
                this.panelborder = this.menucolor[0].darker();
            }
        }
        else {
            this.barbutton = this.menucolor[0].brighter();
            this.panelborder = this.menucolor[0].brighter();
        }
        final String parameter5 = this.getParameter("background");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter5, ", ");
            if (stringTokenizer2.hasMoreTokens()) {
                this.bgcolor = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
            }
            if (stringTokenizer2.hasMoreTokens()) {
                this.prepareImage(this.bgimage = this.getImage(this.getCodeBase(), stringTokenizer2.nextToken()), this);
            }
            if (stringTokenizer2.hasMoreTokens() && stringTokenizer2.nextToken().equals("no")) {
                this.center = false;
            }
        }
        final String parameter6 = this.getParameter("menuopen_sound");
        if (parameter6 != null) {
            this.ac_menuopen = this.getAudioClip(this.getCodeBase(), parameter6);
        }
        final String parameter7 = this.getParameter("itemselect_sound");
        if (parameter7 != null) {
            this.ac_itemselect = this.getAudioClip(this.getCodeBase(), parameter7);
        }
        final String parameter8 = this.getParameter("bglink");
        if (parameter8 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter8, ", ");
            if (stringTokenizer3.hasMoreTokens()) {
                try {
                    this.bgURL = new URL(this.getCodeBase(), stringTokenizer3.nextToken());
                }
                catch (MalformedURLException ex) {
                    System.out.println(ex.toString());
                }
                if (stringTokenizer3.hasMoreTokens()) {
                    this.bgTarget = stringTokenizer3.nextToken();
                }
                else {
                    this.bgTarget = this.target;
                }
            }
        }
        final String parameter9 = this.getParameter("barsettings");
        if (parameter9 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter9, ", ");
            if (stringTokenizer4.hasMoreTokens()) {
                if (stringTokenizer4.nextToken().equalsIgnoreCase("bottom")) {
                    this.top = false;
                }
                if (stringTokenizer4.hasMoreTokens()) {
                    this.spacing = Integer.parseInt(stringTokenizer4.nextToken());
                }
            }
        }
        if (this.getParameter("properties") != null) {
            System.out.println("Version : 1.5b");
            System.out.println("Author : Toh Lik Khoong");
            System.out.println("Copyright : NavSurf.com");
        }
    }
    
    public void init() {
        final Vector vector = new Vector<navdeluxeheader>();
        this.retrieveParameters();
        this.headerfm = this.getFontMetrics(this.barfont);
        this.menufm = this.getFontMetrics(this.menufont);
        this.setBackground(this.bgcolor);
        this.setFont(this.barfont);
        this.setLayout(null);
        final FontMetrics fontMetrics = this.getFontMetrics(this.barfont);
        this.menufm = this.getFontMetrics(this.menufont);
        this.barheight = fontMetrics.getAscent() + fontMetrics.getDescent() + fontMetrics.getLeading();
        this.menuheight = this.menufm.getAscent() + this.menufm.getDescent() + this.menufm.getLeading();
        int int1 = 0;
        int barheight = this.barheight;
        try {
            final InputStream openStream = new URL(this.getCodeBase(), this.getParameter("filename")).openStream();
            this.dis = new DataInputStream(openStream);
            do {
                this.tline = this.readLine();
            } while (this.tline != null && !this.tline.startsWith(":"));
            do {
                final int size = this.mp.size();
                final StringTokenizer stringTokenizer = new StringTokenizer(this.tline, ":", true);
                String s;
                for (s = this.tline; s.startsWith(":"); s = s.substring(1)) {}
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "|", false);
                final String nextToken = stringTokenizer2.nextToken();
                String s2 = null;
                String target = null;
                if (stringTokenizer2.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    if (!nextToken2.equals("~")) {
                        s2 = nextToken2;
                    }
                    target = this.target;
                    if (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken3 = stringTokenizer2.nextToken();
                        if (!nextToken3.equals("~")) {
                            target = nextToken3;
                        }
                        if (stringTokenizer2.hasMoreTokens()) {
                            int1 = Integer.parseInt(stringTokenizer2.nextToken());
                        }
                    }
                }
                do {
                    this.tline = this.readLine();
                } while (this.tline != null && !this.tline.startsWith(":"));
                if (int1 + fontMetrics.stringWidth("  " + nextToken + "  ") > this.size().width) {
                    int1 = 0;
                    ++this.nb_rows;
                    barheight += this.barheight;
                }
                navdeluxepanel menus = this.createMenus(this.barheight * this.nb_rows, 1);
                menus.setFont(this.menufont);
                if (menus.countComponents() == 0) {
                    menus = null;
                }
                else {
                    menus.move(int1, menus.location().y);
                }
                final navdeluxeheader navdeluxeheader = new navdeluxeheader(nextToken, menus, s2, target, true, this);
                navdeluxeheader.resize(fontMetrics.stringWidth("  " + nextToken + "  "), this.barheight);
                if (this.top) {
                    navdeluxeheader.move(int1, barheight - this.barheight);
                }
                else {
                    navdeluxeheader.move(int1, this.size().height - barheight);
                }
                vector.addElement(navdeluxeheader);
                if (!this.top && this.mp.size() != 0) {
                    final int n = this.size().height - 2 * this.nb_rows * this.barheight - this.mp.elementAt(this.mp.size() - 1).size().height;
                    for (int i = size; i < this.mp.size(); ++i) {
                        final navdeluxepanel navdeluxepanel = this.mp.elementAt(i);
                        navdeluxepanel.move(navdeluxepanel.location().x, navdeluxepanel.location().y + n);
                    }
                }
                int1 += fontMetrics.stringWidth(nextToken) + this.spacing;
            } while (this.tline != null);
            openStream.close();
        }
        catch (IOException ex) {}
        final int stringWidth = fontMetrics.stringWidth("  About the applet  ");
        final navdeluxeheader navdeluxeheader2 = new navdeluxeheader("About the applet", null, "http://www.navsurf.com", "_blank", true, this);
        if (this.top) {
            navdeluxeheader2.reshape(this.size().width - stringWidth, (this.nb_rows - 1) * this.barheight, stringWidth, this.barheight);
        }
        else {
            navdeluxeheader2.reshape(this.size().width - stringWidth, this.size().height - this.nb_rows * this.barheight, stringWidth, this.barheight);
        }
        vector.addElement(navdeluxeheader2);
        for (int j = 0; j < vector.size(); ++j) {
            this.add(vector.elementAt(j));
        }
        vector.removeAllElements();
        this.repositionMenus();
    }
    
    private void parseColor(final String s, final String s2, final Color[] array) {
        array[navdeluxene.NORMAL] = new Color(0);
        array[navdeluxene.HIGHLIGHTED] = new Color(255, 255, 255);
        if (s == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.hasMoreTokens()) {
            array[navdeluxene.NORMAL] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            if (stringTokenizer.hasMoreTokens()) {
                array[navdeluxene.HIGHLIGHTED] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            }
        }
    }
    
    public void hideMenu() {
        for (int i = 0; i < this.mp.size(); ++i) {
            ((navdeluxepanel)this.mp.elementAt(i)).hide();
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (event.target instanceof navdeluxene) {
            this.showMenu(false);
        }
        return super.mouseMove(event, n, n2);
    }
}
