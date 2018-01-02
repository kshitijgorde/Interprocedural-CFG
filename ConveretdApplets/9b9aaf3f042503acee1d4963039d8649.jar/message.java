import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class message extends Applet implements Runnable
{
    Thread display;
    String message;
    int message_pos;
    int caption_pos;
    int pos;
    int offset;
    int select_index;
    Vector all_urls;
    Vector all_news;
    Vector all_caps;
    Vector all_rects;
    Vector back_rects;
    Vector news_group;
    boolean legal;
    Color cap_fgcolor;
    Font capfont;
    Color bgcolor;
    Color fgcolor;
    Font txtfont;
    Color bdcolor;
    Color mocolor;
    int speed;
    int delay;
    String window;
    int font_size;
    FontMetrics fm;
    boolean mouse_in;
    int max_select_index;
    int total_height;
    int total_chars;
    int nor_h;
    int nor_a;
    int cap_h;
    int cap_a;
    boolean done;
    Dimension d;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    Point mouse_point;
    
    public void init() {
        this.pos = 0;
        this.d = this.size();
        try {
            final String parameter = this.getParameter("notice");
            if (parameter.indexOf("MW6 Technologies") < 0) {
                this.legal = false;
            }
            if (parameter.indexOf("Copyright") < 0) {
                this.legal = false;
            }
            if (!this.getParameter("license").equals("Demo")) {
                this.legal = false;
            }
        }
        catch (Exception ex2) {
            this.legal = false;
        }
        final Color get_color = this.get_color("bgcolor");
        if (get_color != null) {
            this.bgcolor = get_color;
        }
        final Color get_color2 = this.get_color("txt_color");
        if (get_color2 != null) {
            this.fgcolor = get_color2;
        }
        final Color get_color3 = this.get_color("cap_color");
        if (get_color3 != null) {
            this.cap_fgcolor = get_color3;
        }
        final Color get_color4 = this.get_color("bdcolor");
        if (get_color4 != null) {
            this.bdcolor = get_color4;
        }
        final Color get_color5 = this.get_color("mocolor");
        if (get_color5 != null) {
            this.mocolor = get_color5;
        }
        final Font get_font = this.get_font("txt_font");
        if (get_font != null) {
            this.txtfont = get_font;
        }
        final Font get_font2 = this.get_font("cap_font");
        if (get_font2 != null) {
            this.capfont = get_font2;
        }
        final String parameter2 = this.getParameter("speed");
        if (parameter2 != null) {
            this.speed = new Integer(this.remove_space(parameter2));
        }
        final String parameter3 = this.getParameter("delay");
        if (parameter3 != null) {
            this.delay = new Integer(this.remove_space(parameter3));
        }
        final String parameter4 = this.getParameter("window");
        if (parameter4 != null) {
            this.window = parameter4;
        }
        this.setBackground(this.bgcolor);
        this.fm = this.getToolkit().getFontMetrics(this.txtfont);
        this.nor_h = this.fm.getHeight();
        this.nor_a = this.fm.getAscent();
        this.fm = this.getToolkit().getFontMetrics(this.capfont);
        this.cap_h = this.fm.getHeight();
        this.cap_a = this.fm.getAscent();
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new URL(this.getCodeBase(), "conf.dat").openStream());
        }
        catch (Exception ex3) {}
        try {
            for (String s = dataInputStream.readLine(); s != null; s = dataInputStream.readLine()) {
                final int index = s.indexOf("<news ");
                if (index >= 0) {
                    this.news_group.addElement(new String(" "));
                    final String substring = s.substring(index + 5);
                    final String substring2 = substring.substring(0, substring.lastIndexOf(">"));
                    final String substring3 = substring2.substring(substring2.indexOf("caption="));
                    this.all_caps.addElement(this.get_next_string(substring3));
                    final String substring4 = substring3.substring(substring3.indexOf("content="));
                    this.all_news.addElement(this.get_next_string(substring4));
                    this.all_urls.addElement(this.parse_url(this.get_next_string(substring4.substring(substring4.indexOf("url=")))));
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.mouse_point = new Point(-1, -1);
    }
    
    public String get_next_string(final String s) {
        final String substring = s.substring(s.indexOf(34) + 1);
        return substring.substring(0, substring.indexOf(34));
    }
    
    public boolean handleEvent(final Event event) {
        if (!this.done) {
            return super.handleEvent(event);
        }
        switch (event.id) {
            case 504: {
                this.mouse_in = true;
                Thread.yield();
                this.repaint();
                this.display.resume();
                break;
            }
            case 505: {
                this.mouse_in = false;
                Thread.yield();
                this.repaint();
                this.display.resume();
                break;
            }
            case 501: {
                this.mouse_pressed(event);
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void mouse_pressed(final Event event) {
        this.mouse_point = new Point(event.x, event.y);
        final String s = this.all_urls.elementAt(this.pos);
        try {
            this.getAppletContext().showDocument(new URL(s), this.window);
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (!this.legal) {
            return;
        }
        if (this.all_news.size() == 0) {
            return;
        }
        this.d = this.size();
        if (this.offImage == null) {
            this.offDimension = this.size();
            this.offImage = this.createImage(this.d.width, this.d.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.clearRect(0, 0, this.d.width, this.d.height);
        this.offGraphics.setColor(this.bgcolor);
        this.offGraphics.fillRect(0, 0, this.d.width, this.d.height);
        this.offGraphics.setColor(this.bdcolor);
        this.offGraphics.drawRect(0, 0, this.d.width - 1, this.d.height - 1);
        final int n = (this.pos + 1) % this.all_caps.size();
        final String s = this.all_caps.elementAt(n);
        final String s2 = this.all_news.elementAt(n);
        final Vector vector = new Vector();
        final Vector parse_lines = this.parse_lines(this.parse_words(s2));
        parse_lines.insertElementAt(s, 0);
        int n2 = (this.d.height - ((parse_lines.size() - 1) * this.nor_h + this.cap_h)) / 2 + this.nor_h;
        for (int i = 0; i < parse_lines.size(); ++i) {
            final String s3 = parse_lines.elementAt(i);
            if (i == 0) {
                n2 += this.cap_a;
                this.fm = this.getToolkit().getFontMetrics(this.capfont);
                final int n3 = (this.d.width - this.fm.stringWidth(s3)) / 2;
                this.offGraphics.setFont(this.capfont);
                this.offGraphics.setColor(this.cap_fgcolor);
                this.offGraphics.drawString(s3, n3, n2);
            }
            else {
                n2 += this.nor_a;
                this.fm = this.getToolkit().getFontMetrics(this.txtfont);
                final int n4 = (this.d.width - this.fm.stringWidth(s3)) / 2;
                this.offGraphics.setFont(this.txtfont);
                if (this.mouse_in) {
                    this.offGraphics.setColor(this.mocolor);
                }
                else {
                    this.offGraphics.setColor(this.fgcolor);
                }
                this.offGraphics.drawString(s3, n4, n2);
            }
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public String parse_url(String s) {
        if (s.indexOf("http://") >= 0) {
            return s;
        }
        URL codeBase = null;
        try {
            codeBase = this.getCodeBase();
        }
        catch (Exception ex) {}
        final String host = codeBase.getHost();
        String s2 = codeBase.getFile();
        s = this.remove_space(s);
        if (s.startsWith("/")) {
            return "http://" + host + s;
        }
        if (s.startsWith("../")) {
            for (int i = s.indexOf("../"); i >= 0; i = s.indexOf("../")) {
                s = s.substring(i + 3);
                s2 = s2.substring(0, s2.lastIndexOf("/"));
            }
            return "http://" + host + s2.substring(0, s2.lastIndexOf("/")) + "/" + s;
        }
        return "http://" + host + s2.substring(0, s2.lastIndexOf("/") + 1) + s;
    }
    
    public Vector parse_words(final String s) {
        final Vector<String> vector = new Vector<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                sb.append(char1);
            }
            else {
                vector.addElement(new String(sb));
                sb = new StringBuffer();
            }
        }
        vector.addElement(new String(sb));
        return vector;
    }
    
    public void refresh(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        this.d = this.size();
        if (this.offImage == null) {
            this.offDimension = this.size();
            this.offImage = this.createImage(this.d.width, this.d.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.clearRect(0, 0, this.size().width, this.size().height);
        final String s = this.all_caps.elementAt(this.pos);
        final String s2 = this.all_news.elementAt(this.pos);
        final Vector vector = new Vector();
        final Vector parse_lines = this.parse_lines(this.parse_words(s2));
        parse_lines.insertElementAt(s, 0);
        int n3 = (this.d.height - ((parse_lines.size() - 1) * this.nor_h + this.cap_h)) / 2 + this.nor_h;
        for (int i = 0; i < parse_lines.size(); ++i) {
            final String s3 = parse_lines.elementAt(i);
            if (i == 0) {
                n3 += this.cap_a;
                this.fm = this.getToolkit().getFontMetrics(this.capfont);
                final int n4 = (this.d.width - this.fm.stringWidth(s3)) / 2;
                this.offGraphics.setFont(this.capfont);
                this.offGraphics.setColor(this.cap_fgcolor);
                this.offGraphics.drawString(s3, n4, n3);
            }
            else {
                n3 += this.nor_a;
                this.fm = this.getToolkit().getFontMetrics(this.txtfont);
                final int n5 = (this.d.width - this.fm.stringWidth(s3)) / 2;
                this.offGraphics.setFont(this.txtfont);
                if (this.mouse_in) {
                    this.offGraphics.setColor(this.mocolor);
                }
                else {
                    this.offGraphics.setColor(this.fgcolor);
                }
                this.offGraphics.drawString(s3, n5, n3);
            }
        }
        final int n6 = (this.pos + 1) % this.all_news.size();
        final String s4 = this.all_caps.elementAt(n6);
        final String s5 = this.all_news.elementAt(n6);
        final Vector vector2 = new Vector();
        final Vector parse_lines2 = this.parse_lines(this.parse_words(s5));
        parse_lines2.insertElementAt(s4, 0);
        int n7 = (this.d.height - ((parse_lines2.size() - 1) * this.nor_h + this.cap_h)) / 2 + this.nor_h;
        int n8 = 0;
        int n9 = 0;
        switch (n2) {
            case 0: {
                n8 = this.d.width - n * this.d.width / 10;
                n9 = this.d.height - n * this.d.height / 10;
                break;
            }
            case 1: {
                n8 = this.d.width - n * this.d.width / 10;
                n9 = 0;
                break;
            }
            case 2: {
                n8 = this.d.width - n * this.d.width / 10;
                n9 = -this.d.height + n * this.d.height / 10;
                break;
            }
            case 3: {
                n8 = 0;
                n9 = -this.d.height + n * this.d.height / 10;
                break;
            }
            case 4: {
                n8 = -this.d.width + n * this.d.width / 10;
                n9 = -this.d.height + n * this.d.height / 10;
                break;
            }
            case 5: {
                n8 = -this.d.width + n * this.d.width / 10;
                n9 = 0;
                break;
            }
            case 6: {
                n8 = -this.d.width + n * this.d.width / 10;
                n9 = this.d.height - n * this.d.height / 10;
                break;
            }
            case 7: {
                n8 = 0;
                n9 = this.d.height - n * this.d.height / 10;
                break;
            }
        }
        this.offGraphics.setColor(this.bdcolor);
        this.offGraphics.drawRect(n8, n9, this.d.width - 1, this.d.height - 1);
        this.offGraphics.setColor(this.bgcolor);
        this.offGraphics.fillRect(n8 + 1, n9 + 1, this.d.width - 2, this.d.height - 2);
        for (int j = 0; j < parse_lines2.size(); ++j) {
            final String s6 = parse_lines2.elementAt(j);
            if (j == 0) {
                n7 += this.cap_a;
                this.fm = this.getToolkit().getFontMetrics(this.capfont);
                final int n10 = (this.d.width - this.fm.stringWidth(s6)) / 2;
                this.offGraphics.setFont(this.capfont);
                this.offGraphics.setColor(this.cap_fgcolor);
                this.offGraphics.drawString(s6, n8 + n10, n9 + n7);
            }
            else {
                n7 += this.nor_a;
                this.fm = this.getToolkit().getFontMetrics(this.txtfont);
                final int n11 = (this.d.width - this.fm.stringWidth(s6)) / 2;
                this.offGraphics.setFont(this.txtfont);
                if (this.mouse_in) {
                    this.offGraphics.setColor(this.mocolor);
                }
                else {
                    this.offGraphics.setColor(this.fgcolor);
                }
                this.offGraphics.drawString(s6, n8 + n11, n9 + n7);
            }
        }
        this.offGraphics.setColor(this.bdcolor);
        this.offGraphics.drawRect(0, 0, this.d.width - 1, this.d.height - 1);
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public String remove_space(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                sb.append(char1);
            }
        }
        return new String(sb);
    }
    
    public void run() {
        this.pos = 0;
        int n = 0;
        while (this.display != null) {
            this.done = false;
            for (int i = 0; i <= 10; ++i) {
                this.refresh(i, n);
                try {
                    Thread.sleep(this.speed);
                }
                catch (Exception ex) {}
            }
            this.done = true;
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex2) {}
            this.pos = (this.pos + 1) % this.all_news.size();
            n = (n + 1) % 8;
        }
    }
    
    public void start() {
        if (this.display == null) {
            (this.display = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.display = null;
    }
    
    public Color get_color(final String s) {
        Integer n = null;
        Integer n2 = null;
        Integer n3 = null;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            if (stringTokenizer.hasMoreTokens()) {
                n = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                n2 = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                n3 = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            return new Color(n, n2, n3);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Font get_font(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            String s2 = null;
            String s3 = null;
            Integer n = null;
            if (stringTokenizer.hasMoreTokens()) {
                s2 = new String(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                s3 = new String(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                n = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            int n2;
            if (s3.equals("BOLD")) {
                n2 = 1;
            }
            else if (s3.equals("ITALIC")) {
                n2 = 2;
            }
            else {
                n2 = 0;
            }
            return new Font(s2, n2, n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Vector parse_lines(final Vector vector) {
        this.d = this.size();
        final Vector<String> vector2 = new Vector<String>();
        int i = 0;
        final int n = this.d.height / this.txtfont.getSize();
        this.fm = this.getToolkit().getFontMetrics(this.txtfont);
        StringBuffer sb = new StringBuffer();
        while (i < vector.size()) {
            sb.append(vector.elementAt(i));
            final int stringWidth = this.fm.stringWidth(sb.toString());
            int stringWidth2 = -1;
            if (i < vector.size() - 1) {
                final String s = vector.elementAt(i + 1);
                final StringBuffer sb2 = new StringBuffer(sb.toString());
                sb2.append(" ");
                sb2.append(s);
                stringWidth2 = this.fm.stringWidth(sb2.toString());
            }
            if (stringWidth <= this.d.width - 8 && stringWidth2 >= this.d.width - 8 && i < vector.size()) {
                vector2.addElement(new String(sb));
                sb = new StringBuffer();
            }
            else {
                if (stringWidth <= this.d.width - 8 && i == vector.size() - 1) {
                    vector2.addElement(new String(sb));
                    final StringBuffer sb3 = new StringBuffer();
                    break;
                }
                if (stringWidth >= this.d.width - 8) {
                    final String s2 = new String(sb);
                    for (int j = s2.length() - 1; j >= 0; --j) {
                        final String substring = s2.substring(0, j);
                        if (this.fm.stringWidth(substring) <= this.d.width - 8) {
                            vector2.addElement(String.valueOf(substring) + "-");
                            sb = new StringBuffer(s2.substring(j));
                            sb.append(" ");
                            break;
                        }
                    }
                }
                else {
                    sb.append(" ");
                }
            }
            ++i;
        }
        return vector2;
    }
    
    public message() {
        this.select_index = -1;
        this.all_urls = new Vector();
        this.all_news = new Vector();
        this.all_caps = new Vector();
        this.all_rects = new Vector();
        this.back_rects = new Vector();
        this.news_group = new Vector();
        this.legal = true;
        this.cap_fgcolor = Color.red;
        this.capfont = new Font("serif", 0, 22);
        this.bgcolor = new Color(255, 255, 255);
        this.fgcolor = Color.black;
        this.txtfont = new Font("serif", 0, 14);
        this.bdcolor = Color.red;
        this.mocolor = Color.pink;
        this.speed = 10;
        this.delay = 2000;
        this.window = "_self";
        this.mouse_in = false;
        this.done = true;
    }
}
