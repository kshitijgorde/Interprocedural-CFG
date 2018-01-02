import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ISCBlinkButton extends Applet implements Runnable
{
    private Image osi;
    private Image back_image;
    private Dimension oss;
    private Graphics osg;
    private Font f;
    private FontMetrics fm;
    private MediaTracker tracker;
    private Thread runner;
    private int width;
    private int height;
    private int total_button;
    private int button_width;
    private int button_height;
    private int current_x;
    private int font_size;
    private int current_b;
    private String[] button;
    private String[] url;
    private String frame_name;
    private String[] tips_text;
    private Color out_color;
    private Color in_color;
    private Color line_color;
    private Color back_color;
    private Color blink_color;
    private boolean vertical;
    private boolean blink;
    private final boolean register = false;
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.tracker = new MediaTracker(this);
        try {
            this.font_size = Integer.valueOf(this.getParameter("FONT_SIZE"));
        }
        catch (NumberFormatException ex) {
            this.font_size = 12;
        }
        this.f = new Font("Helvetica", 1, this.font_size);
        if (this.getParameter("STYLE").toUpperCase().startsWith("V")) {
            this.vertical = true;
        }
        else {
            this.vertical = false;
        }
        final String parameter = this.getParameter("BACK_IMAGE");
        if (parameter != null) {
            this.back_image = this.getImage(this.getCodeBase(), parameter);
            this.tracker.addImage(this.back_image, 0);
            try {
                this.tracker.waitForAll(30000L);
            }
            catch (InterruptedException ex2) {}
        }
        if ((this.tracker.statusAll(false) & 0x4) != 0x0) {
            this.back_image = null;
        }
        this.frame_name = this.getParameter("FRAME_NAME");
        if (this.frame_name == null) {
            this.frame_name = "_top";
        }
        if (this.getColorParameter("BACK_COLOR") == null) {
            this.back_color = Color.white;
        }
        else {
            this.back_color = this.getColorParameter("BACK_COLOR");
        }
        if (this.getColorParameter("FONT_COLOR_IN") == null) {
            this.in_color = Color.black;
        }
        else {
            this.in_color = this.getColorParameter("FONT_COLOR_IN");
        }
        if (this.getColorParameter("FONT_COLOR_OUT") == null) {
            this.out_color = Color.black;
        }
        else {
            this.out_color = this.getColorParameter("FONT_COLOR_OUT");
        }
        if (this.getColorParameter("LINE_COLOR") == null) {
            this.line_color = Color.yellow;
        }
        else {
            this.line_color = this.getColorParameter("LINE_COLOR");
        }
        if (this.getColorParameter("BLINKER_COLOR") == null) {
            this.blink_color = Color.green;
        }
        else {
            this.blink_color = this.getColorParameter("BLINKER_COLOR");
        }
        this.total_button = 0;
        while (this.getParameter("BUTTON" + (this.total_button + 1)) != null) {
            ++this.total_button;
        }
        this.button = new String[this.total_button];
        this.url = new String[this.total_button];
        this.tips_text = new String[this.total_button];
        for (int i = 0; i < this.total_button; ++i) {
            final String[] array = new String[3];
            final String[] param = this.parseParam(this.getParameter("BUTTON" + (i + 1)), "|");
            if (param == null) {
                break;
            }
            this.button[i] = param[0];
            this.url[i] = param[1];
            this.tips_text[i] = param[2];
        }
        if (!this.vertical) {
            this.button_width = this.width / this.total_button;
            this.button_height = this.height;
            return;
        }
        this.button_width = this.width;
        this.button_height = this.height / this.total_button;
    }
    
    public void paint(final Graphics graphics) {
        if (this.getCodeBase().toString().startsWith("http")) {
            graphics.setFont(new Font("Helvetica", 0, 10));
            graphics.setColor(Color.black);
            graphics.drawString("Unregistered Version", 5, 10);
            graphics.drawString("Please register at", 5, 20);
            graphics.drawString("http://www.iscomplete.com/", 5, 30);
            return;
        }
        if (this.back_image != null) {
            for (int i = 0; i < this.size().height; i += this.back_image.getHeight(this)) {
                for (int j = 0; j < this.size().width; j += this.back_image.getWidth(this)) {
                    graphics.drawImage(this.back_image, j, i, this);
                }
            }
        }
        else {
            graphics.setColor(this.back_color);
            graphics.fillRect(0, 0, this.width, this.height);
        }
        for (int k = 0; k < this.button.length; ++k) {
            if (k == this.current_b) {
                graphics.setColor(this.in_color);
            }
            else {
                graphics.setColor(this.out_color);
            }
            graphics.setFont(this.f);
            this.fm = graphics.getFontMetrics(this.f);
            final double n = (this.button_height + this.fm.getHeight()) / 2.0 - this.fm.getMaxDescent();
            if (!this.vertical) {
                graphics.drawString(this.button[k], 10 + this.font_size + this.button_width * k, (int)n);
                graphics.setColor(this.blink_color);
                if (k == this.current_b) {
                    if (this.blink) {
                        graphics.fillOval(this.button_width * k + 5, (this.button_height - this.font_size) / 2, this.font_size, this.font_size);
                    }
                }
                else {
                    graphics.fillOval(this.button_width * k + 5, (this.button_height - this.font_size) / 2, this.font_size, this.font_size);
                }
            }
            else {
                graphics.drawString(this.button[k], this.font_size + 10, (int)n + this.button_height * k);
                graphics.setColor(this.blink_color);
                if (k == this.current_b) {
                    if (this.blink) {
                        graphics.fillOval(5, (this.button_height - this.font_size) / 2 + this.button_height * k, this.font_size, this.font_size);
                    }
                }
                else {
                    graphics.fillOval(5, (this.button_height - this.font_size) / 2 + this.button_height * k, this.font_size, this.font_size);
                }
            }
        }
        graphics.setColor(this.line_color);
        if (!this.vertical && this.current_b != -1) {
            graphics.fillRect(this.current_x - this.button_width / 2, 3, this.button_width, 2);
            graphics.fillRect(this.current_x - this.button_width / 2, this.button_height - 5, this.button_width, 2);
            return;
        }
        if (this.vertical && this.current_b != -1) {
            graphics.fillRect(2, this.button_height * this.current_b + 2, this.button_width - 4, 2);
            graphics.fillRect(2, this.button_height * (this.current_b + 1) - 4, this.button_width - 4, 2);
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.runner = null;
    }
    
    public void run() {
        while (this.runner != null) {
            this.blink = !this.blink;
            final long n = System.currentTimeMillis() + 500L;
            if (this.current_b != -1) {
                this.repaint();
            }
            try {
                Thread.sleep(n - System.currentTimeMillis());
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseMove(final Event event, final int current_x, final int n) {
        if (this.getCodeBase().toString().startsWith("http")) {
            return true;
        }
        if (!this.vertical) {
            this.current_x = current_x;
            this.current_b = current_x / this.button_width;
        }
        else {
            this.current_b = n / this.button_height;
        }
        this.showStatus(this.url[this.current_b]);
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.current_b = -1;
        this.showStatus("Unregistered version. Created by IS/Complete, Inc. http://www.iscomplete.com/");
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.getCodeBase().toString().startsWith("http")) {
            return true;
        }
        try {
            this.getAppletContext().showDocument(new URL(this.url[this.current_b]), this.frame_name);
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public String getAppletInfo() {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append("Title: ISCBlinkButton\r\n").toString())).append("Author: Jay Tze\r\n").toString())).append("E-mail: jay.tze@iscomplete.com\r\n").toString())).append("Web Site: http://www.iscomplete.com/\r\n").toString()) + "Copyright Jay Tze 1998";
    }
    
    protected String[] parseParam(final String s, final String s2) {
        int n = 0;
        if (s == null) {
            return null;
        }
        final String[] array = new String[3];
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        return array;
    }
    
    protected Color getColorParameter(final String s) {
        final String parameter = this.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter, 16);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return new Color(int1);
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.osi == null || size.width != this.oss.width || size.height != this.oss.height) {
            this.osi = this.createImage(size.width, size.height);
            this.oss = size;
            this.osg = this.osi.getGraphics();
        }
        this.osg.clearRect(0, 0, size.width, size.height);
        this.paint(this.osg);
        graphics.drawImage(this.osi, 0, 0, null);
    }
    
    public ISCBlinkButton() {
        this.current_b = -1;
    }
}
