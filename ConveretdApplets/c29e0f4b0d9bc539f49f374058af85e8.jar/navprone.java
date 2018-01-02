import java.io.Reader;
import java.awt.LayoutManager;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class navprone extends Applet implements AdjustmentListener
{
    protected static final int reginfo = 0;
    private Font menufont;
    private String target;
    private Color bgcolor;
    private String tline;
    private Image bgimage;
    private boolean tile;
    private Color[][] fontcolor;
    private Color[][] menucolor;
    private Image[][] menuimage;
    private int scrollbar_width;
    boolean scrollbar_on;
    Vector nbv;
    Scrollbar scroller;
    navpropanel mpanel;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    
    private final void initMenus() {
        int n = 0;
        for (int i = 0; i < this.nbv.size(); ++i) {
            final navprobutton navprobutton = this.nbv.elementAt(i);
            if (navprobutton.isVisible()) {
                navprobutton.move(0, n);
                n += navprobutton.size().height + navprobutton.vspacing;
            }
        }
        if (n > this.size().height && this.scrollbar_on) {
            this.scroller.show();
            this.scroller.setMinimum(0);
            this.scroller.setMaximum(n - this.size().height + 10);
            this.mpanel.resize(this.mpanel.size().width, n);
        }
        else {
            this.scroller.hide();
            this.mpanel.resize(this.mpanel.size().width, this.size().height);
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.mpanel.move(0, -this.scroller.getValue());
    }
    
    public navprone() {
        this.menufont = null;
        this.target = null;
        this.bgcolor = null;
        this.tline = null;
        this.bgimage = null;
        this.tile = false;
        this.fontcolor = null;
        this.menucolor = null;
        this.menuimage = null;
        this.scrollbar_width = 0;
        this.scrollbar_on = false;
        this.nbv = null;
        this.scroller = null;
        this.mpanel = null;
        this.is = null;
        this.isr = null;
        this.br = null;
        this.menufont = null;
        this.target = "_self";
        this.bgcolor = new Color(16777215);
        this.is = null;
        this.tline = new String();
        this.bgimage = null;
        this.tile = false;
        this.fontcolor = null;
        this.menucolor = null;
        this.menuimage = null;
        this.scrollbar_width = 15;
        this.scrollbar_on = true;
        this.nbv = new Vector();
        this.scroller = new Scrollbar(1);
        this.mpanel = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgimage != null && this.bgimage.getHeight(null) > 0 && this.bgimage.getWidth(null) > 0) {
            if (this.tile) {
                for (int i = 0; i < this.size().height; i += this.bgimage.getHeight(null)) {
                    for (int j = 0; j < this.size().width; j += this.bgimage.getWidth(null)) {
                        graphics.drawImage(this.bgimage, j, i, this);
                    }
                }
            }
            else {
                graphics.drawImage(this.bgimage, 0, 0, this);
            }
        }
    }
    
    private final Font parseFont(final String s, final String s2) {
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
    
    private final int[] parseOpenMenu(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", \t");
        final int[] array = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.countTokens() != 0) {
            array[n] = Integer.parseInt(stringTokenizer.nextToken());
            ++n;
        }
        return array;
    }
    
    private void parseMenuImage(String s, final String s2, final Image[] array) {
        array[1] = (array[0] = null);
        if (s == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        try {
            if (stringTokenizer.hasMoreTokens()) {
                s = stringTokenizer.nextToken();
                if (!s.equals("")) {
                    array[0] = this.getImage(new URL(this.getCodeBase(), s));
                }
                this.prepareImage(array[0], this);
            }
            if (stringTokenizer.hasMoreTokens()) {
                s = stringTokenizer.nextToken();
                if (!s.equals("")) {
                    array[1] = this.getImage(new URL(this.getCodeBase(), s));
                }
                this.prepareImage(array[1], this);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public final String readLine() {
        String s = null;
        try {
            s = this.br.readLine();
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        if (s != null) {
            s = s.trim();
        }
        return s;
    }
    
    private final void parseButtonParam(String buttonshape, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(buttonshape, s);
        if (stringTokenizer.hasMoreTokens()) {
            buttonshape = stringTokenizer.nextToken();
            if (!buttonshape.equals("") && buttonshape.equalsIgnoreCase("no")) {
                navprobutton.button = false;
            }
            else {
                navprobutton.button = true;
            }
            navprobutton.setButtonImage(null);
        }
        if (stringTokenizer.hasMoreTokens()) {
            buttonshape = stringTokenizer.nextToken();
            if (!buttonshape.equals("")) {
                try {
                    navprobutton.buttoncolor = new Color(Integer.parseInt(buttonshape, 16));
                }
                catch (NumberFormatException ex) {
                    try {
                        final Image image = this.getImage(new URL(this.getCodeBase(), buttonshape));
                        this.prepareImage(image, this);
                        navprobutton.setButtonImage(image);
                    }
                    catch (MalformedURLException ex2) {}
                    return;
                }
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            buttonshape = stringTokenizer.nextToken();
            if (buttonshape.equalsIgnoreCase("square") || buttonshape.equalsIgnoreCase("triangle")) {
                navprobutton.buttonshape = buttonshape;
            }
        }
    }
    
    public void start() {
        this.initMenus();
    }
    
    private final Vector createMenus(final int n) {
        final Vector<navprobutton> vector = new Vector<navprobutton>();
        while (this.tline != null) {
            int n2 = 0;
            int n3 = 0;
            String s;
            for (s = this.tline; s.startsWith(":"); s = s.substring(1), ++n2) {}
            if (n2 <= n) {
                break;
            }
            do {
                this.tline = this.readLine();
            } while (this.tline != null && this.tline.equals(""));
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "|", false);
            final int size = this.nbv.size();
            final String nextToken = stringTokenizer.nextToken();
            String nextToken2 = null;
            String s2 = null;
            final Color[] array = new Color[2];
            final Color[] array2 = new Color[2];
            array[0] = this.menucolor[n2][0];
            array[1] = this.menucolor[n2][1];
            array2[0] = this.fontcolor[n2][0];
            array2[1] = this.fontcolor[n2][1];
            Vector menus = this.createMenus(n2);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.equals("~")) {
                    nextToken2 = null;
                }
                s2 = this.target;
                if (stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                    if (s2.equals("~")) {
                        s2 = this.target;
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken3 = stringTokenizer.nextToken();
                    if (!nextToken3.equals("~")) {
                        array[0] = new Color(Integer.parseInt(nextToken3, 16));
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken4 = stringTokenizer.nextToken();
                    if (!nextToken4.equals("~")) {
                        array2[0] = new Color(Integer.parseInt(nextToken4, 16));
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken5 = stringTokenizer.nextToken();
                    if (!nextToken5.equals("~")) {
                        array[1] = new Color(Integer.parseInt(nextToken5, 16));
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken6 = stringTokenizer.nextToken();
                    if (!nextToken6.equals("~")) {
                        array2[1] = new Color(Integer.parseInt(nextToken6, 16));
                    }
                }
            }
            if (menus.size() == 0) {
                menus = null;
            }
            final navprobutton navprobutton = new navprobutton(nextToken, menus, nextToken2, n2, s2, array, array2, this);
            navprobutton.bgimage_out = this.menuimage[n2][0];
            navprobutton.bgimage_over = this.menuimage[n2][1];
            navprobutton.resize(this.size().width - this.scrollbar_width, navprobutton.fm.getHeight() * new StringTokenizer(nextToken, navprobutton.linebreak).countTokens() + 2);
            this.mpanel.add(navprobutton);
            vector.addElement(navprobutton);
            this.nbv.insertElementAt(navprobutton, size);
            if (this.tline != null) {
                for (String s3 = this.tline; s3.startsWith(":"); s3 = s3.substring(1), ++n3) {}
            }
            if (n3 < n2) {
                break;
            }
        }
        return vector;
    }
    
    private final void parseScrollbar(String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.hasMoreTokens()) {
            s = stringTokenizer.nextToken();
            if (!s.equals("") && s.equalsIgnoreCase("no")) {
                this.scrollbar_on = false;
            }
            else {
                this.scrollbar_on = true;
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            s = stringTokenizer.nextToken();
            if (!s.equals("")) {
                this.scrollbar_width = Integer.parseInt(s);
            }
        }
    }
    
    private final void retrieveParameters() {
        int int1 = 9;
        final String parameter = this.getParameter("font");
        if (parameter != null) {
            this.menufont = this.parseFont(parameter, ", ");
        }
        final String parameter2 = this.getParameter("border");
        if (parameter2 != null && parameter2.equalsIgnoreCase("no")) {
            navprobutton.Blnborder = false;
        }
        else {
            navprobutton.Blnborder = true;
        }
        final String parameter3 = this.getParameter("level");
        if (parameter3 != null) {
            int1 = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("target");
        if (parameter4 != null) {
            this.target = parameter4;
        }
        final String parameter5 = this.getParameter("bgcolor");
        if (parameter5 != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter5, 16));
        }
        final String parameter6 = this.getParameter("vspacing");
        if (parameter6 != null) {
            navprobutton.vspacing = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("linebreak");
        if (parameter7 != null) {
            navprobutton.linebreak = parameter7;
        }
        final String parameter8 = this.getParameter("tile");
        if (parameter8 != null && parameter8.equals("yes")) {
            this.tile = true;
        }
        else {
            this.tile = false;
        }
        final String parameter9 = this.getParameter("textalignment");
        if (parameter9 != null) {
            navprobutton.textalignment = parameter9;
        }
        final String parameter10 = this.getParameter("button");
        if (parameter10 != null) {
            this.parseButtonParam(parameter10, ", ");
        }
        final String parameter11 = this.getParameter("scrollbar");
        if (parameter11 != null) {
            this.parseScrollbar(parameter11, ", ");
        }
        final String parameter12 = this.getParameter("bgimage");
        if (parameter12 != null) {
            this.prepareImage(this.bgimage = this.getImage(this.getCodeBase(), parameter12), this);
        }
        this.menuimage = new Image[int1 + 1][2];
        this.fontcolor = new Color[int1 + 1][2];
        this.menucolor = new Color[int1 + 1][2];
        navprobutton.fm = this.getFontMetrics(this.menufont);
        for (int i = 1; i <= int1; ++i) {
            this.parseColor(this.getParameter("fontcolor" + i), ", ", this.fontcolor[i]);
            this.parseColor(this.getParameter("menucolor" + i), ", ", this.menucolor[i]);
            this.parseMenuImage(this.getParameter("menuimage" + i), ", ", this.menuimage[i]);
        }
        if (this.getParameter("appletinfo") != null) {
            System.out.println("Applet: NavMenu Pro NE");
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
            System.out.println("Version: 2.0");
            System.out.println("Author: Toh Lik Khoong");
            System.out.println("Copyright: NavSurf.com");
        }
    }
    
    public final void init() {
        this.retrieveParameters();
        (this.mpanel = new navpropanel(this.bgimage, this.tile)).setBackground(this.bgcolor);
        this.mpanel.setLayout(null);
        if (!this.scrollbar_on) {
            this.scrollbar_width = 0;
        }
        this.mpanel.resize(this.size().width - this.scrollbar_width, this.size().height);
        this.add(this.mpanel);
        this.setLayout(null);
        this.setFont(this.menufont);
        this.setBackground(this.bgcolor);
        try {
            this.is = new URL(this.getCodeBase(), this.getParameter("filename")).openStream();
            if (this.getParameter("encoding") != null && this.getParameter("encoding") != "") {
                this.isr = new InputStreamReader(this.is, this.getParameter("encoding"));
            }
            else {
                this.isr = new InputStreamReader(this.is);
            }
            this.br = new BufferedReader(this.isr);
            do {
                this.tline = this.readLine();
            } while (this.tline != null && this.tline.equals(""));
            this.createMenus(0);
            this.is.close();
            this.isr.close();
            this.br.close();
        }
        catch (IOException ex) {}
        final navprobutton navprobutton = new navprobutton("About the applet", null, "http://www.navsurf.com", 1, "_blank", this.menucolor[1], this.fontcolor[1], this);
        navprobutton.bgimage_out = this.menuimage[1][0];
        navprobutton.bgimage_over = this.menuimage[1][1];
        navprobutton.resize(this.size().width - this.scrollbar_width, navprobutton.fm.getHeight() + 2);
        this.mpanel.add(navprobutton);
        this.nbv.addElement(navprobutton);
        if (this.getParameter("openmenu") != null) {
            final int[] openMenu = this.parseOpenMenu(this.getParameter("openmenu"));
            int n = 0;
            for (int i = 0; i < this.nbv.size(); ++i) {
                navprobutton navprobutton2 = this.nbv.elementAt(i);
                if (navprobutton2.level == 1) {
                    ++n;
                }
                if (n == openMenu[0]) {
                    for (int j = 1; j <= openMenu.length; ++j) {
                        if (navprobutton2.Vtrsubmenu != null) {
                            navprobutton2.Blncurrent = true;
                            for (int k = 0; k < navprobutton2.Vtrsubmenu.size(); ++k) {
                                ((navprobutton)navprobutton2.Vtrsubmenu.elementAt(k)).show();
                            }
                            if (j < openMenu.length && navprobutton2.Vtrsubmenu.size() > openMenu[j] - 1) {
                                navprobutton2 = (navprobutton)navprobutton2.Vtrsubmenu.elementAt(openMenu[j] - 1);
                            }
                        }
                        else {
                            navprobutton2.Blncurrent = true;
                            navprobutton2.repaint();
                            System.out.println("Error Opening Level " + j + " Menu!");
                        }
                    }
                    break;
                }
            }
        }
        if (this.scrollbar_on) {
            this.scroller.move(this.size().width - this.scrollbar_width, 0);
            this.scroller.resize(this.scrollbar_width, this.size().height);
            this.scroller.setUnitIncrement(navprobutton.fm.getHeight() + 2);
            this.scroller.setPageIncrement(this.size().height);
            this.scroller.addAdjustmentListener(this);
            this.add(this.scroller);
        }
    }
    
    private void parseColor(final String s, final String s2, final Color[] array) {
        array[0] = new Color(255, 255, 255);
        array[1] = new Color(0);
        if (s == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.hasMoreTokens()) {
            array[0] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            if (stringTokenizer.hasMoreTokens()) {
                array[1] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            }
        }
    }
}
