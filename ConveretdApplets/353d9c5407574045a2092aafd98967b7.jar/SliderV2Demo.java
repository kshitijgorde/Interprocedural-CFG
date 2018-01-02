import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SliderV2Demo extends Applet implements Runnable
{
    private static String[] str;
    private static String type;
    private static Graphics g;
    private static Image offScreenImage;
    private static Font font;
    private FontMetrics metrics;
    private Thread runner;
    private int[] xstart;
    private int[] strwidth;
    private static int xloc;
    private static int yloc;
    private static int number;
    private static int option;
    private static int sleeptime;
    private static int width;
    private static int height;
    private static int strheight;
    private static int ctdelay;
    private static int fontsize;
    private static int style;
    private static int direction;
    private static int nostrings;
    private static int bgcolor1;
    private static int bgcolor2;
    private static int bgcolor3;
    private static int fgcoloron1;
    private static int fgcoloron2;
    private static int fgcoloron3;
    private static int fgcoloroff1;
    private static int fgcoloroff2;
    private static int fgcoloroff3;
    private static int borderon1;
    private static int borderon2;
    private static int borderon3;
    private static int borderoff1;
    private static int borderoff2;
    private static int borderoff3;
    static boolean running;
    static boolean status;
    static boolean iscenter;
    static boolean urlnone;
    static boolean filedata;
    private String url;
    private String sourcefile;
    private Color back;
    private boolean isRound;
    
    public void init() {
        try {
            final String parameter = this.getParameter("nostrings");
            if (parameter != null) {
                SliderV2Demo.nostrings = Integer.parseInt(parameter);
            }
            else {
                SliderV2Demo.nostrings = 0;
            }
        }
        catch (Exception ex2) {
            SliderV2Demo.nostrings = 0;
        }
        try {
            final String parameter2 = this.getParameter("bordershape");
            if (!parameter2.equals("")) {
                if (parameter2.equals("round")) {
                    this.isRound = true;
                }
                else if (parameter2.equals("square")) {
                    this.isRound = false;
                }
                else {
                    this.isRound = true;
                }
            }
            else {
                this.isRound = true;
            }
        }
        catch (Exception ex3) {
            this.isRound = true;
        }
        try {
            final String parameter3 = this.getParameter("sourcefile");
            if (!parameter3.equals("")) {
                this.sourcefile = parameter3;
                SliderV2Demo.filedata = true;
            }
            else {
                SliderV2Demo.filedata = false;
            }
        }
        catch (Exception ex4) {
            SliderV2Demo.filedata = false;
        }
        SliderV2Demo.str = new String[SliderV2Demo.nostrings];
        if (!SliderV2Demo.filedata) {
            try {
                for (int i = 0; i < SliderV2Demo.nostrings; ++i) {
                    SliderV2Demo.str[i] = this.getParameter("string" + String.valueOf(i + 1));
                }
            }
            catch (Exception ex5) {}
        }
        else {
            try {
                final DataInputStream dataInputStream = new DataInputStream(new URL(this.getCodeBase(), this.sourcefile).openStream());
                String s = dataInputStream.readLine();
                int n = 0;
                while (s != null) {
                    if (n >= SliderV2Demo.nostrings) {
                        break;
                    }
                    SliderV2Demo.str[n++] = s.trim();
                    s = dataInputStream.readLine();
                }
            }
            catch (Exception ex) {
                this.showStatus(ex.toString());
            }
        }
        try {
            final String parameter4 = this.getParameter("URL");
            if (parameter4 == null) {
                this.url = "none";
            }
            else {
                this.url = parameter4;
            }
        }
        catch (Exception ex6) {
            SliderV2Demo.urlnone = true;
        }
        try {
            final String parameter5 = this.getParameter("fillcolor");
            if (parameter5 == null) {
                SliderV2Demo.bgcolor1 = 0;
                SliderV2Demo.bgcolor2 = 0;
                SliderV2Demo.bgcolor3 = 0;
            }
            else {
                final int index = parameter5.indexOf(",");
                final int index2 = parameter5.indexOf(",", index + 1);
                SliderV2Demo.bgcolor1 = Integer.parseInt(parameter5.substring(0, index));
                SliderV2Demo.bgcolor2 = Integer.parseInt(parameter5.substring(index + 1, index2));
                SliderV2Demo.bgcolor3 = Integer.parseInt(parameter5.substring(index2 + 1, parameter5.length()));
            }
        }
        catch (Exception ex7) {
            SliderV2Demo.bgcolor1 = 0;
            SliderV2Demo.bgcolor2 = 0;
            SliderV2Demo.bgcolor3 = 0;
        }
        try {
            final String parameter6 = this.getParameter("fgcoloron");
            if (parameter6 == null) {
                SliderV2Demo.fgcoloron1 = 255;
                SliderV2Demo.fgcoloron2 = 255;
                SliderV2Demo.fgcoloron3 = 255;
            }
            else {
                final int index3 = parameter6.indexOf(",");
                final int index4 = parameter6.indexOf(",", index3 + 1);
                SliderV2Demo.fgcoloron1 = Integer.parseInt(parameter6.substring(0, index3));
                SliderV2Demo.fgcoloron2 = Integer.parseInt(parameter6.substring(index3 + 1, index4));
                SliderV2Demo.fgcoloron3 = Integer.parseInt(parameter6.substring(index4 + 1, parameter6.length()));
            }
        }
        catch (Exception ex8) {
            SliderV2Demo.fgcoloron1 = 255;
            SliderV2Demo.fgcoloron2 = 255;
            SliderV2Demo.fgcoloron3 = 255;
        }
        try {
            final String parameter7 = this.getParameter("fgcoloroff");
            if (parameter7 == null) {
                SliderV2Demo.fgcoloroff1 = 255;
                SliderV2Demo.fgcoloroff2 = 255;
                SliderV2Demo.fgcoloroff3 = 255;
            }
            else {
                final int index5 = parameter7.indexOf(",");
                final int index6 = parameter7.indexOf(",", index5 + 1);
                SliderV2Demo.fgcoloroff1 = Integer.parseInt(parameter7.substring(0, index5));
                SliderV2Demo.fgcoloroff2 = Integer.parseInt(parameter7.substring(index5 + 1, index6));
                SliderV2Demo.fgcoloroff3 = Integer.parseInt(parameter7.substring(index6 + 1, parameter7.length()));
            }
        }
        catch (Exception ex9) {
            SliderV2Demo.fgcoloroff1 = 255;
            SliderV2Demo.fgcoloroff2 = 255;
            SliderV2Demo.fgcoloroff3 = 255;
        }
        try {
            final String parameter8 = this.getParameter("fontsize");
            if (parameter8 == null) {
                SliderV2Demo.fontsize = 14;
            }
            else {
                SliderV2Demo.fontsize = Integer.parseInt(parameter8);
            }
        }
        catch (Exception ex10) {
            SliderV2Demo.fontsize = 14;
        }
        try {
            final String parameter9 = this.getParameter("fontstyle");
            if (parameter9.compareTo("Font.PLAIN") == 0) {
                SliderV2Demo.style = 0;
            }
            else if (parameter9.compareTo("Font.BOLD") == 0) {
                SliderV2Demo.style = 1;
            }
            else if (parameter9.compareTo("Font.ITALIC") == 0) {
                SliderV2Demo.style = 2;
            }
            else if (parameter9.compareTo("Font.ITALIC+Font.BOLD") == 0 || parameter9.compareTo("Font.BOLD+Font.ITALIC") == 0) {
                SliderV2Demo.style = 3;
            }
            else if (parameter9.compareTo("random") == 0) {
                SliderV2Demo.style = 4;
            }
            else {
                SliderV2Demo.style = 4;
            }
        }
        catch (Exception ex11) {
            SliderV2Demo.style = 4;
        }
        try {
            final String parameter10 = this.getParameter("direction");
            if (parameter10.compareTo("LeftRight") == 0) {
                SliderV2Demo.direction = 0;
            }
            else if (parameter10.compareTo("RightLeft") == 0) {
                SliderV2Demo.direction = 1;
            }
            else if (parameter10.compareTo("TopBottom") == 0) {
                SliderV2Demo.direction = 2;
            }
            else if (parameter10.compareTo("BottomTop") == 0) {
                SliderV2Demo.direction = 3;
            }
            else if (parameter10.compareTo("random") == 0) {
                SliderV2Demo.direction = 4;
            }
            else if (parameter10.compareTo("Horizontal") == 0) {
                SliderV2Demo.direction = 5;
            }
            else if (parameter10.compareTo("Vertical") == 0) {
                SliderV2Demo.direction = 6;
            }
            else {
                SliderV2Demo.direction = 4;
            }
        }
        catch (Exception ex12) {
            SliderV2Demo.direction = 4;
        }
        try {
            final String parameter11 = this.getParameter("sleep");
            if (parameter11 == null) {
                SliderV2Demo.sleeptime = 100;
            }
            else {
                SliderV2Demo.sleeptime = Integer.parseInt(parameter11);
            }
        }
        catch (Exception ex13) {
            SliderV2Demo.sleeptime = 100;
        }
        try {
            final String parameter12 = this.getParameter("centerdelay");
            if (parameter12 == null) {
                SliderV2Demo.ctdelay = 0;
            }
            else {
                SliderV2Demo.ctdelay = Integer.parseInt(parameter12);
            }
        }
        catch (Exception ex14) {
            SliderV2Demo.ctdelay = 0;
        }
        try {
            final String parameter13 = this.getParameter("borderon");
            if (parameter13 == null) {
                SliderV2Demo.borderon1 = 0;
                SliderV2Demo.borderon2 = 0;
                SliderV2Demo.borderon3 = 0;
            }
            else {
                final int index7 = parameter13.indexOf(",");
                final int index8 = parameter13.indexOf(",", index7 + 1);
                SliderV2Demo.borderon1 = Integer.parseInt(parameter13.substring(0, index7));
                SliderV2Demo.borderon2 = Integer.parseInt(parameter13.substring(index7 + 1, index8));
                SliderV2Demo.borderon3 = Integer.parseInt(parameter13.substring(index8 + 1, parameter13.length()));
            }
        }
        catch (Exception ex15) {
            SliderV2Demo.borderon1 = 0;
            SliderV2Demo.borderon2 = 0;
            SliderV2Demo.borderon3 = 0;
        }
        try {
            final String parameter14 = this.getParameter("borderoff");
            if (parameter14 == null) {
                SliderV2Demo.borderoff1 = 0;
                SliderV2Demo.borderoff2 = 0;
                SliderV2Demo.borderoff3 = 0;
            }
            else {
                final int index9 = parameter14.indexOf(",");
                final int index10 = parameter14.indexOf(",", index9 + 1);
                SliderV2Demo.borderoff1 = Integer.parseInt(parameter14.substring(0, index9));
                SliderV2Demo.borderoff2 = Integer.parseInt(parameter14.substring(index9 + 1, index10));
                SliderV2Demo.borderoff3 = Integer.parseInt(parameter14.substring(index10 + 1, parameter14.length()));
            }
        }
        catch (Exception ex16) {
            SliderV2Demo.borderoff1 = 0;
            SliderV2Demo.borderoff2 = 0;
            SliderV2Demo.borderoff3 = 0;
        }
        try {
            final String parameter15 = this.getParameter("bgcolor");
            if (!parameter15.equals("")) {
                final int index11 = parameter15.indexOf(",");
                final int index12 = parameter15.indexOf(",", index11 + 1);
                this.back = new Color(Integer.parseInt(parameter15.substring(0, index11)), Integer.parseInt(parameter15.substring(index11 + 1, index12)), Integer.parseInt(parameter15.substring(index12 + 1, parameter15.length())));
            }
        }
        catch (Exception ex17) {
            this.back = new Color(0, 0, 0);
        }
        try {
            final String parameter16 = this.getParameter("fontname");
            if (parameter16 == null || parameter16 == "") {
                SliderV2Demo.type = "TimesNewRoman";
            }
            else if (parameter16.equals("Dialog")) {
                SliderV2Demo.type = "TimesNewRoman";
            }
            else {
                SliderV2Demo.type = parameter16;
            }
        }
        catch (Exception ex18) {
            SliderV2Demo.type = "TimesNewRoman";
        }
        SliderV2Demo.g = this.getGraphics();
        SliderV2Demo.running = false;
        SliderV2Demo.status = true;
        this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
        SliderV2Demo.width = this.size().width;
        SliderV2Demo.height = this.size().height;
        this.resize(SliderV2Demo.width, SliderV2Demo.height);
        SliderV2Demo.offScreenImage = this.createImage(SliderV2Demo.width, SliderV2Demo.height);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            SliderV2Demo.running = true;
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
            this.runner = null;
            SliderV2Demo.running = false;
        }
    }
    
    public void run() {
        while (SliderV2Demo.running) {
            SliderV2Demo.number = 0;
            while (SliderV2Demo.number < SliderV2Demo.nostrings) {
                if (SliderV2Demo.direction == 4) {
                    this.xstart = new int[SliderV2Demo.nostrings];
                    final double n = Math.random() * 10.0;
                    if (n < 2.5) {
                        SliderV2Demo.option = 0;
                    }
                    else if (n < 5.0) {
                        SliderV2Demo.option = 1;
                    }
                    else if (n < 7.5) {
                        SliderV2Demo.option = 2;
                    }
                    else {
                        SliderV2Demo.option = 3;
                    }
                }
                else if (SliderV2Demo.direction == 5) {
                    SliderV2Demo.option = (int)(Math.random() * 2.0);
                }
                else if (SliderV2Demo.direction == 6) {
                    SliderV2Demo.option = (int)(Math.random() * 2.0) + 2;
                }
                else {
                    SliderV2Demo.option = SliderV2Demo.direction;
                }
                int style;
                if (SliderV2Demo.style == 4) {
                    final double n2 = Math.random() * 10.0;
                    if (n2 < 2.5) {
                        style = 0;
                    }
                    else if (n2 < 5.0) {
                        style = 1;
                    }
                    else if (n2 < 7.5) {
                        style = 2;
                    }
                    else {
                        style = 3;
                    }
                }
                else {
                    style = SliderV2Demo.style;
                }
                SliderV2Demo.font = new Font(SliderV2Demo.type, style, SliderV2Demo.fontsize);
                this.metrics = this.getFontMetrics(SliderV2Demo.font);
                SliderV2Demo.strheight = this.metrics.getHeight();
                this.strwidth = new int[SliderV2Demo.nostrings];
                for (int i = 0; i < SliderV2Demo.nostrings; ++i) {
                    this.strwidth[i] = this.metrics.stringWidth(SliderV2Demo.str[i]);
                }
                if (SliderV2Demo.option == 0) {
                    SliderV2Demo.yloc = (SliderV2Demo.height + this.metrics.getAscent()) / 2;
                    final int n3 = this.strwidth[SliderV2Demo.number];
                    final int xloc = -(n3 + 5);
                    final int n4 = (SliderV2Demo.width - n3) / 2;
                    SliderV2Demo.xloc = xloc;
                    while (SliderV2Demo.xloc < SliderV2Demo.width + 5) {
                        if (!SliderV2Demo.status) {
                            --SliderV2Demo.xloc;
                        }
                        if (n4 == SliderV2Demo.xloc) {
                            SliderV2Demo.iscenter = true;
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                            for (int j = 0; j < SliderV2Demo.ctdelay; ++j) {
                                this.update(SliderV2Demo.g);
                                try {
                                    Thread.sleep(SliderV2Demo.ctdelay);
                                }
                                catch (Exception ex) {
                                    this.showStatus("Insomnia while sleeping...");
                                    this.update(SliderV2Demo.g);
                                }
                            }
                            SliderV2Demo.iscenter = false;
                        }
                        else {
                            SliderV2Demo.iscenter = false;
                        }
                        if (SliderV2Demo.status && !SliderV2Demo.iscenter) {
                            this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
                        }
                        else {
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                        }
                        this.update(SliderV2Demo.g);
                        this.sleeping();
                        ++SliderV2Demo.xloc;
                    }
                }
                else if (SliderV2Demo.option == 1) {
                    SliderV2Demo.yloc = (SliderV2Demo.height + this.metrics.getAscent()) / 2;
                    final int n5 = this.strwidth[SliderV2Demo.number];
                    final int n6 = -(n5 + 5);
                    final int n7 = (SliderV2Demo.width - n5) / 2;
                    SliderV2Demo.xloc = SliderV2Demo.width + 5;
                    while (SliderV2Demo.xloc > n6) {
                        if (!SliderV2Demo.status) {
                            ++SliderV2Demo.xloc;
                        }
                        if (n7 == SliderV2Demo.xloc) {
                            SliderV2Demo.iscenter = true;
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                            for (int k = 0; k < SliderV2Demo.ctdelay; ++k) {
                                this.update(SliderV2Demo.g);
                                try {
                                    Thread.sleep(SliderV2Demo.ctdelay);
                                }
                                catch (Exception ex2) {
                                    this.showStatus("Error");
                                    this.update(SliderV2Demo.g);
                                }
                            }
                            SliderV2Demo.iscenter = false;
                        }
                        else {
                            SliderV2Demo.iscenter = false;
                        }
                        if (SliderV2Demo.status && !SliderV2Demo.iscenter) {
                            this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
                        }
                        else {
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                        }
                        this.update(SliderV2Demo.g);
                        this.sleeping();
                        --SliderV2Demo.xloc;
                    }
                }
                else if (SliderV2Demo.option == 2) {
                    SliderV2Demo.xloc = (SliderV2Demo.width - this.strwidth[SliderV2Demo.number]) / 2;
                    final int n8 = (SliderV2Demo.height + this.metrics.getAscent()) / 2;
                    SliderV2Demo.yloc = -SliderV2Demo.strheight;
                    while (SliderV2Demo.yloc < SliderV2Demo.strheight + SliderV2Demo.height) {
                        if (!SliderV2Demo.status) {
                            --SliderV2Demo.yloc;
                        }
                        if (n8 == SliderV2Demo.yloc) {
                            SliderV2Demo.iscenter = true;
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                            for (int l = 0; l < SliderV2Demo.ctdelay; ++l) {
                                this.update(SliderV2Demo.g);
                                try {
                                    Thread.sleep(SliderV2Demo.ctdelay);
                                }
                                catch (Exception ex3) {
                                    this.showStatus("Error");
                                    this.update(SliderV2Demo.g);
                                }
                            }
                            SliderV2Demo.iscenter = false;
                        }
                        else {
                            SliderV2Demo.iscenter = false;
                        }
                        if (SliderV2Demo.status) {
                            this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
                        }
                        else {
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                        }
                        this.update(SliderV2Demo.g);
                        this.sleeping();
                        ++SliderV2Demo.yloc;
                    }
                }
                else if (SliderV2Demo.option == 3) {
                    SliderV2Demo.xloc = (SliderV2Demo.width - this.strwidth[SliderV2Demo.number]) / 2;
                    final int n9 = (SliderV2Demo.height + this.metrics.getAscent()) / 2;
                    SliderV2Demo.yloc = SliderV2Demo.height + SliderV2Demo.strheight;
                    while (SliderV2Demo.yloc > -SliderV2Demo.strheight) {
                        if (!SliderV2Demo.status) {
                            ++SliderV2Demo.yloc;
                        }
                        if (n9 == SliderV2Demo.yloc) {
                            SliderV2Demo.iscenter = true;
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                            for (int n10 = 0; n10 < SliderV2Demo.ctdelay; ++n10) {
                                this.update(SliderV2Demo.g);
                                try {
                                    Thread.sleep(SliderV2Demo.ctdelay);
                                }
                                catch (Exception ex4) {
                                    this.showStatus("Error");
                                    this.update(SliderV2Demo.g);
                                }
                            }
                            SliderV2Demo.iscenter = false;
                        }
                        else {
                            SliderV2Demo.iscenter = false;
                        }
                        if (SliderV2Demo.status) {
                            this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
                        }
                        else {
                            this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
                        }
                        this.update(SliderV2Demo.g);
                        this.sleeping();
                        --SliderV2Demo.yloc;
                    }
                }
                ++SliderV2Demo.number;
            }
        }
    }
    
    public void sleeping() {
        try {
            int sleeptime;
            if (SliderV2Demo.option == 2 || SliderV2Demo.option == 3) {
                sleeptime = SliderV2Demo.sleeptime;
            }
            else {
                sleeptime = ((SliderV2Demo.sleeptime - 5 > 0) ? (SliderV2Demo.sleeptime - 5) : 0);
            }
            Thread.sleep(sleeptime);
        }
        catch (Exception ex) {
            this.showStatus("Insomnia while sleeping...r");
            this.update(SliderV2Demo.g);
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            final Graphics graphics2 = SliderV2Demo.offScreenImage.getGraphics();
            int height;
            int n;
            if (this.isRound) {
                height = SliderV2Demo.height;
                n = SliderV2Demo.height - 2;
            }
            else {
                height = 0;
                n = 0;
            }
            if (!SliderV2Demo.status) {
                graphics2.setColor(this.back);
                graphics2.fillRect(0, 0, SliderV2Demo.width, SliderV2Demo.height);
                graphics2.setColor(new Color(SliderV2Demo.borderon1, SliderV2Demo.borderon2, SliderV2Demo.borderon3));
                graphics2.fillRoundRect(0, 0, SliderV2Demo.width, SliderV2Demo.height, height, height);
                graphics2.setColor(new Color(SliderV2Demo.bgcolor1, SliderV2Demo.bgcolor2, SliderV2Demo.bgcolor3));
                graphics2.fillRoundRect(2, 2, SliderV2Demo.width - 4, SliderV2Demo.height - 4, n, n);
            }
            else {
                graphics2.setColor(this.back);
                graphics2.fillRect(0, 0, SliderV2Demo.width, SliderV2Demo.height);
                graphics2.setColor(new Color(SliderV2Demo.borderoff1, SliderV2Demo.borderoff2, SliderV2Demo.borderoff3));
                graphics2.fillRoundRect(0, 0, SliderV2Demo.width, SliderV2Demo.height, height, height);
                graphics2.setColor(new Color(SliderV2Demo.bgcolor1, SliderV2Demo.bgcolor2, SliderV2Demo.bgcolor3));
                graphics2.fillRoundRect(2, 2, SliderV2Demo.width - 4, SliderV2Demo.height - 4, n, n);
            }
            graphics2.setFont(SliderV2Demo.font);
            graphics2.setColor(this.getForeground());
            graphics2.clipRect(2, 2, SliderV2Demo.width - 4, SliderV2Demo.height - 4);
            graphics2.drawString(SliderV2Demo.str[SliderV2Demo.number], SliderV2Demo.xloc, SliderV2Demo.yloc);
            graphics.drawImage(SliderV2Demo.offScreenImage, 0, 0, null);
        }
        catch (Exception ex) {
            this.showStatus("Insomnia while sleeping");
            this.update(graphics);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        SliderV2Demo.status = false;
        this.setForeground(new Color(SliderV2Demo.fgcoloron1, SliderV2Demo.fgcoloron2, SliderV2Demo.fgcoloron3));
        this.showStatus("Free applets at www.consultcom.com");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        SliderV2Demo.status = true;
        if (!SliderV2Demo.iscenter) {
            this.setForeground(new Color(SliderV2Demo.fgcoloroff1, SliderV2Demo.fgcoloroff2, SliderV2Demo.fgcoloroff3));
        }
        this.showStatus("");
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!SliderV2Demo.urlnone) {
            try {
                this.getAppletContext().showDocument(new URL(this.url));
            }
            catch (Exception ex) {
                this.showStatus(ex.toString());
            }
        }
        return true;
    }
}
