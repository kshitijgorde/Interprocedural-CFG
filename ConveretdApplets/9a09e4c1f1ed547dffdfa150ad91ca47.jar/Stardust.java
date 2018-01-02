import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.FontMetrics;
import java.net.URL;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Stardust extends Applet implements Runnable
{
    int Width;
    int Height;
    int Xmax;
    int Ymax;
    Thread me;
    boolean suspend;
    Image im;
    private Image backdrop;
    private int m_Height;
    private int m_Hei;
    private int m_Hey;
    private int m_Hez;
    private int m_Width;
    private int m_CurrLine;
    private int m_NumLines;
    private int m_Decrement;
    private int m_Longitude;
    private String m_string;
    private String m_URL;
    private String m_face;
    private Font m_font;
    private String m_style;
    private String m_dir;
    private int m_size;
    private int m_style1;
    private int m_fps;
    private int m_Sleep;
    private int m_b;
    private String m_color;
    private String[] text;
    Graphics offscreen;
    int N_Stars;
    int mx;
    int my;
    int dx;
    int dy;
    int mxold;
    int myold;
    double crot;
    double srot;
    double rot;
    Dimension m_dimImage;
    Fly_by_Star[] FStar;
    Background_Star[] BStar;
    private final String PARAM_string = "string";
    private final String PARAM_font = "font";
    private final String PARAM_style = "style";
    private final String PARAM_size = "size";
    private final String PARAM_fps = "fps";
    private final String PARAM_URL = "URL";
    private final String PARAM_color = "color";
    private String[] urls;
    private Color[] colours;
    private Vector data;
    private URL targetURL;
    private Image[] slogan;
    private String[][] m_Ma;
    private int[] m_howMany;
    private Graphics m_bg;
    private FontMetrics fm;
    
    public void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "string", "String", "String to output in marquee" }, { "font", "String", "Font to use" }, { "style", "String", "PLAIN, BOLD or ITALIC" }, { "size", "int", "Font size [pts]" }, { "fps", "int", "Frame rate" }, { "URL", "String", "Destination URL" }, { "color", "String", "Color of the string" } };
    }
    
    public void stop() {
        if (this.me != null) {
            this.me.stop();
            this.me = null;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("Done");
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.mx = n - this.Xmax;
        this.my = n2 - this.Ymax;
        return true;
    }
    
    public Color setColour(final String s) {
        if (s == null) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        return Color.blue;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.urls[this.m_CurrLine].length() != 0) {
            try {
                this.targetURL = new URL(this.urls[this.m_CurrLine]);
                this.getAppletContext().showDocument(this.targetURL);
            }
            catch (MalformedURLException ex) {
                this.showStatus("Bad URL: " + this.urls[this.m_CurrLine]);
            }
        }
        return true;
    }
    
    public Stardust() {
        this.m_Hei = 240;
        this.m_Hey = 1;
        this.m_Width = 320;
        this.m_Decrement = 1;
        this.m_string = "This applet contains multi-line strings |" + "You can specify them in parameter \"string\" with delimeter Alt-124 |" + "You can change the parameter \"Number_of_stars\" |" + "Set parameter \"direction\" to \"up\" or \"center\" |" + "Set parameter \"font\" to desired font face |" + "Set parameter \"size\" for font size |" + "Set parameter \"color\" for font color |" + "Set parameter \"style\" to \"plain\", \"bold\" or \"italic\" |" + "Set parameter \"fps\" to desired speed (frames per second) |" + "Set parameter \"URL\" to enable visitor move by click to another page |" + "Click here to visit author's homepage |" + "Now roll over again.";
        this.m_URL = "||||||||||http://www.geocities.com/von_kotow/";
        this.m_face = "Tahoma";
        this.m_style = "PLAIN";
        this.m_dir = "center";
        this.m_size = 14;
        this.m_fps = 20;
    }
    
    public void center(final Graphics graphics) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.m_font);
        final Graphics graphics2 = this.backdrop.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.Width - 20, this.Height - 20);
        int n = 0;
        for (int i = 0; i < this.m_howMany[this.m_CurrLine]; ++i) {
            final int stringWidth = fontMetrics.stringWidth(this.m_Ma[this.m_CurrLine][i]);
            final int n2 = fontMetrics.getHeight() + 10;
            if (n < stringWidth) {
                n = stringWidth;
            }
            final int n3 = (this.m_Width - 20) / 2 - stringWidth / 2;
            final int n4 = (n3 < 0) ? 0 : n3;
            graphics2.setColor(this.colours[this.m_CurrLine]);
            graphics2.drawString(this.m_Ma[this.m_CurrLine][i], n4, (this.Height - (this.m_howMany[this.m_CurrLine] - i) * n2) / 2);
        }
        final int n5 = this.m_Width * this.m_Hey / this.m_Height;
        final int n6 = (this.m_Width - n5) / 2;
        final int n7 = (this.m_Height - this.m_Hey) / 2 + fontMetrics.getHeight();
        if (this.slogan[this.m_CurrLine] != null) {
            graphics.drawImage(this.slogan[this.m_CurrLine], n6, (this.m_Height - this.m_Hey) / 2 - this.m_Hez, n5, this.m_Hey, this);
        }
        else {
            graphics.drawImage(this.backdrop, n6, n7, n5, this.m_Hey, this);
        }
        if (this.m_Decrement == 1) {
            this.m_Hey += 2;
        }
        if (this.m_Decrement == 2) {
            ++this.m_Longitude;
            if (this.m_Longitude > 70) {
                this.m_Decrement = 3;
            }
        }
        if (this.m_Decrement == 3) {
            --this.m_Hey;
        }
        if (this.m_Decrement == 4) {
            if (this.slogan[this.m_CurrLine] != null) {
                this.m_Hez += 10;
                if (this.m_Hey + this.m_Hez > this.m_Height) {
                    this.m_Hez = 0;
                    this.m_Hey = 1;
                    this.m_Decrement = 1;
                    ++this.m_CurrLine;
                    if (this.m_CurrLine == this.m_NumLines) {
                        this.m_CurrLine = 0;
                    }
                }
            }
            else {
                this.m_Hey += 80;
            }
        }
        if (this.m_Hey > this.m_Height && this.m_Decrement == 1) {
            this.m_Decrement = 2;
            this.m_Longitude = 0;
        }
        if (this.m_Hey + 20 < this.m_Height && this.m_Decrement == 3) {
            this.m_Decrement = 4;
        }
        if (this.m_Hey - 5000 > this.m_Height) {
            ++this.m_CurrLine;
            if (this.m_CurrLine == this.m_NumLines) {
                this.m_CurrLine = 0;
            }
            this.m_Hey = 1;
            this.m_Decrement = 1;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.urls[this.m_CurrLine].length() == 0) {
            this.showStatus("Welcome to Von Kotow ");
        }
        else {
            this.showStatus(this.urls[this.m_CurrLine]);
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Name: Stardust\r\n" + "Author: Von Kotow\r\n" + "Created with Microsoft Visual J++ Version 1.0";
    }
    
    public void run() {
        while (this.me != null) {
            this.dx = this.mx - this.mxold;
            this.dy = this.my - this.myold;
            this.mxold = this.mx;
            this.myold = this.my;
            this.rot = 0.5233333333333333 * this.mx / this.Xmax * 0.0;
            this.crot = Math.cos(this.rot);
            this.srot = Math.sin(this.rot);
            try {
                Thread.sleep(this.m_Sleep);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void Toggle() {
        if (this.me != null) {
            if (this.suspend) {
                this.me.resume();
            }
            else {
                this.me.suspend();
            }
            this.suspend = !this.suspend;
        }
    }
    
    public void paintMe(final Graphics graphics) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.m_font);
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.Width, this.Height);
        if (this.m_dir.equalsIgnoreCase("CENTER") && this.slogan[this.m_CurrLine] == null) {
            this.center(graphics);
        }
        for (int i = 0; i < this.N_Stars; ++i) {
            this.BStar[i].BDraw(graphics, this.mx, this.my, this.dx, this.dy, this.crot, this.srot);
            this.FStar[i].Draw(graphics, this.mx, this.my, this.dx, this.dy, this.crot, this.srot);
        }
        if (this.m_dir.equalsIgnoreCase("CENTER") && this.slogan[this.m_CurrLine] != null) {
            this.center(graphics);
        }
        if (this.m_dir.equalsIgnoreCase("UP")) {
            graphics.setColor(this.colours[this.m_CurrLine]);
            if (this.slogan[this.m_CurrLine] != null) {
                final int n = (this.m_Width - this.slogan[this.m_CurrLine].getWidth(this)) / 2;
                graphics.drawImage(this.slogan[this.m_CurrLine], (n < 0) ? 0 : n, this.m_Hei, this);
                --this.m_Hei;
                if (this.m_Hei + this.slogan[this.m_CurrLine].getHeight(this) < 0) {
                    this.m_Hei = this.m_Height;
                    ++this.m_CurrLine;
                    if (this.m_CurrLine == this.m_NumLines) {
                        this.m_CurrLine = 0;
                    }
                }
            }
            else {
                int j;
                for (j = 0; j < this.m_howMany[this.m_CurrLine]; ++j) {
                    final int n2 = this.m_Width / 2 - fontMetrics.stringWidth(this.m_Ma[this.m_CurrLine][j]) / 2;
                    graphics.drawString(this.m_Ma[this.m_CurrLine][j], (n2 < 0) ? 0 : n2, this.m_Hei + j * 20);
                }
                --this.m_Hei;
                if (this.m_Hei + j * 20 < 0) {
                    this.m_Hei = this.m_Height;
                    ++this.m_CurrLine;
                    if (this.m_CurrLine == this.m_NumLines) {
                        this.m_CurrLine = 0;
                    }
                }
            }
        }
    }
    
    public void init() {
        this.showStatus("Retrieving data ...");
        this.Width = this.size().width;
        this.Height = this.size().height;
        final String parameter = this.getParameter("Number_of_Stars");
        this.N_Stars = ((parameter == null) ? 20 : Integer.valueOf(parameter));
        this.BStar = new Background_Star[this.N_Stars];
        this.FStar = new Fly_by_Star[this.N_Stars];
        for (int i = 0; i < this.N_Stars; ++i) {
            this.BStar[i] = new Background_Star(this.Width, this.Height);
            this.FStar[i] = new Fly_by_Star(this.Width, this.Height);
        }
        this.mxold = 0;
        this.myold = 0;
        this.Xmax = this.Width / 2;
        this.Ymax = this.Height / 2;
        final String parameter2 = this.getParameter("string");
        if (parameter2 != null) {
            this.m_string = parameter2;
        }
        final String parameter3 = this.getParameter("font");
        if (parameter3 != null) {
            this.m_face = parameter3;
        }
        final String parameter4 = this.getParameter("style");
        if (parameter4 != null) {
            this.m_style = parameter4;
        }
        final String parameter5 = this.getParameter("size");
        if (parameter5 != null) {
            this.m_size = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("fps");
        if (parameter6 != null) {
            this.m_fps = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("URL");
        if (parameter7 != null) {
            this.m_URL = parameter7;
        }
        final String parameter8 = this.getParameter("direction");
        if (parameter8 != null) {
            this.m_dir = parameter8;
        }
        this.m_style1 = 0;
        if (this.m_style.equalsIgnoreCase("BOLD")) {
            this.m_style1 = 1;
        }
        if (this.m_style.equalsIgnoreCase("ITALIC")) {
            this.m_style1 = 2;
        }
        this.setFont(this.m_font = new Font(this.m_face, this.m_style1, this.m_size));
        final String parameter9 = this.getParameter("color");
        if (parameter9 != null) {
            this.m_color = parameter9;
        }
        this.m_Sleep = 1000 / this.m_fps;
        this.m_dimImage = this.size();
        final int height = this.m_dimImage.height;
        this.m_Hei = height;
        this.m_Height = height;
        this.m_Width = this.m_dimImage.width;
        this.data = new Vector();
        int n = 0;
        int j;
        for (j = 0; j < this.m_string.length(); ++j) {
            if (this.m_string.charAt(j) == '|') {
                final String substring = this.m_string.substring(n, j);
                if (substring != null) {
                    this.data.addElement(substring);
                }
                n = j + 1;
            }
        }
        final String substring2 = this.m_string.substring(n, j);
        if (substring2 != null) {
            this.data.addElement(substring2);
        }
        this.m_NumLines = this.data.size();
        this.text = new String[this.m_NumLines];
        this.m_howMany = new int[this.m_NumLines];
        this.slogan = new Image[this.data.size()];
        int size = 0;
        final FontMetrics fontMetrics = this.getFontMetrics(this.m_font);
        for (int k = 0; k < this.data.size(); ++k) {
            this.text[k] = new String((String)this.data.elementAt(k));
            if (this.text[k].startsWith("//")) {
                this.slogan[k] = this.getImage(this.getCodeBase(), this.text[k].substring(2, this.text[k].length()));
            }
            final Vector vector = new Vector<String>();
            final String s = this.text[k];
            int n2 = 0;
            int n3 = 0;
            int l;
            for (l = 0; l < s.length(); ++l) {
                if (s.charAt(l) == ' ' && fontMetrics.stringWidth(s.substring(n2, l)) > this.m_Width - 20) {
                    if (n3 == 0) {
                        n3 = l;
                    }
                    final String substring3 = s.substring(n2, n3);
                    if (substring3.length() > 0) {
                        vector.addElement(substring3);
                    }
                    n2 = n3;
                }
                if (s.charAt(l) == ' ') {
                    n3 = l;
                }
            }
            if (l > n2) {
                vector.addElement(s.substring(n2, l));
            }
            if (size < vector.size()) {
                size = vector.size();
            }
            this.m_howMany[k] = vector.size();
        }
        this.m_Ma = new String[this.m_NumLines][size];
        for (int n4 = 0; n4 < this.data.size(); ++n4) {
            new Vector();
            final String s2 = this.text[n4];
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            int n8;
            for (n8 = 0; n8 < s2.length(); ++n8) {
                if (s2.charAt(n8) == ' ' && fontMetrics.stringWidth(s2.substring(n5, n8)) > this.m_Width - 20) {
                    if (n6 == 0) {
                        n6 = n8;
                    }
                    final String substring4 = s2.substring(n5, n6);
                    if (substring4.length() > 0) {
                        this.m_Ma[n4][n7] = substring4;
                        ++n7;
                    }
                    n5 = n6;
                }
                if (s2.charAt(n8) == ' ') {
                    n6 = n8;
                }
            }
            if (n8 > n5) {
                this.m_Ma[n4][n7] = s2.substring(n5, n8);
            }
            if (this.text[n4].startsWith("//") && this.slogan[n4] == null) {
                this.m_Ma[n4][0] = "Image not found: " + this.text[n4].substring(2, this.text[n4].length());
            }
        }
        this.urls = new String[this.m_NumLines];
        final String s3 = new String();
        int n9 = 0;
        int n10 = 0;
        for (int n11 = 0; n11 < this.m_NumLines; ++n11) {
            this.urls[n11] = null;
        }
        int n12;
        for (n12 = 0; n12 < this.m_URL.length(); ++n12) {
            if (this.m_URL.charAt(n12) == '|') {
                final String s4 = new String(this.m_URL.substring(n9, n12));
                if (s4.length() > 0) {
                    this.urls[n10] = s4;
                }
                n9 = n12 + 1;
                ++n10;
            }
        }
        if (n12 > n9 + 1) {
            this.urls[n10] = new String(this.m_URL.substring(n9, n12));
        }
        this.colours = new Color[this.m_NumLines];
        int n13 = 0;
        this.colours[0] = Color.blue;
        for (int n14 = 1; n14 < this.m_NumLines; ++n14) {
            this.colours[n14] = Color.black;
        }
        int n15 = 0;
        int n16 = 0;
        if (this.m_color != null) {
            n15 = 0;
            n16 = 0;
            while (n15 < this.m_color.length()) {
                if (this.m_color.charAt(n15) == '|') {
                    final String substring5 = this.m_color.substring(n16, n15);
                    if (substring5 != null) {
                        this.colours[n13] = this.setColour(substring5);
                    }
                    n16 = n15 + 1;
                    ++n13;
                }
                ++n15;
            }
        }
        if (n16 != n15 + 1 && n15 != 0) {
            this.colours[n13] = this.setColour(this.m_color.substring(n16, n15));
        }
        for (int n17 = 1; n17 < this.m_NumLines; ++n17) {
            if (this.colours[n17] == Color.black) {
                this.colours[n17] = this.colours[0];
            }
        }
        try {
            this.im = this.createImage(this.Width, this.Height);
            this.offscreen = this.im.getGraphics();
            this.backdrop = this.createImage(this.Width - 20, this.Height - 20);
        }
        catch (Exception ex) {
            this.offscreen = null;
        }
        this.backdrop.getGraphics();
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintMe(this.offscreen);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.paintMe(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
