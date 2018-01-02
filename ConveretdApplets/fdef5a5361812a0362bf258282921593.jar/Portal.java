import java.awt.image.ImageObserver;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Portal extends Applet implements Runnable
{
    Thread me;
    Image offI;
    Graphics offG;
    int width;
    int height;
    int max;
    int speed;
    FontMetrics metrics;
    Text text;
    int[][] arcs;
    Color background;
    Color foreground;
    Color fontColor;
    Color shadowColor;
    Font font;
    
    public final void init() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        if (this.height > this.width) {
            this.max = this.height;
        }
        else {
            this.max = this.width;
        }
        this.offI = this.createImage(this.width, this.height);
        this.offG = this.offI.getGraphics();
        this.speed = 2;
        this.text = new Text(this.width);
        this.background = Color.red;
        this.foreground = Color.black;
        this.fontColor = Color.white;
        this.shadowColor = Color.black;
        this.font = new Font("Verdana", 1, 12);
        if (this.getParameter("created by John Morris - www.javaplayground.com") != null) {
            if (this.getParameter("file") != null) {
                final String string = this.getDocumentBase().toString();
                this.loadFile(String.valueOf(string.substring(0, string.lastIndexOf("/") + 1)) + this.getParameter("file"));
            }
            else {
                this.text.addLine("No File Specified");
            }
        }
        else {
            this.text.addLine("Author Tag Not Found");
        }
        this.text.setFontSize(this.font.getSize());
        this.arcs = new int[50][4];
        final Random random = new Random();
        for (int i = 0; i < 50; ++i) {
            this.arcs[i][0] = Math.abs(random.nextInt() % 360);
            this.arcs[i][1] = Math.abs(random.nextInt() % 5) + 1;
            this.arcs[i][2] = Math.abs(random.nextInt() % 100) + 1;
            this.arcs[i][3] = Math.abs(random.nextInt() % 360) + Math.abs(random.nextInt() % 100) + 1;
        }
        this.text.start();
    }
    
    public final void loadFile(final String s) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openConnection().getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.parseLine(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {}
    }
    
    public final void parseLine(final String s) {
        if (s.indexOf("=") >= 0) {
            final String trim = s.substring(0, s.indexOf("=")).trim();
            final String trim2 = s.substring(s.indexOf("=") + 1).trim();
            if (trim.equalsIgnoreCase("speed")) {
                this.speed = this.parseSpeed(trim2);
                this.text.setSpeed(this.speed);
                return;
            }
            if (trim.equalsIgnoreCase("background")) {
                this.background = this.parseColor(trim2);
                return;
            }
            if (trim.equalsIgnoreCase("foreground")) {
                this.foreground = this.parseColor(trim2);
                return;
            }
            if (trim.equalsIgnoreCase("fontcolor")) {
                this.fontColor = this.parseColor(trim2);
                return;
            }
            if (trim.equalsIgnoreCase("fontsize")) {
                this.font = new Font(this.font.getName(), this.font.getStyle(), this.parseSize(trim2));
                return;
            }
            if (trim.equalsIgnoreCase("fontname")) {
                try {
                    this.font = new Font(trim2, this.font.getStyle(), this.font.getSize());
                    return;
                }
                catch (Exception ex) {
                    this.font = new Font("Verdana", this.font.getStyle(), this.font.getSize());
                    return;
                }
            }
            if (trim.equalsIgnoreCase("fontstyle")) {
                this.font = new Font(this.font.getName(), this.parseStyle(trim2), this.font.getSize());
                return;
            }
            if (trim.equalsIgnoreCase("shadowColor")) {
                this.shadowColor = this.parseColor(trim2);
            }
        }
        else {
            this.text.addLine(s);
        }
    }
    
    public final int parseStyle(final String s) {
        if (s.equalsIgnoreCase("bold")) {
            return 1;
        }
        if (s.equalsIgnoreCase("italic")) {
            return 2;
        }
        if (s.equalsIgnoreCase("both")) {
            return 3;
        }
        return 0;
    }
    
    public final int parseSize(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = 12;
        }
        return int1;
    }
    
    public final Color parseColor(final String s) {
        int int1;
        int int2;
        int int3;
        try {
            final int index = s.indexOf(",");
            int1 = Integer.parseInt(s.substring(0, index));
            int2 = Integer.parseInt(s.substring(index + 1, s.indexOf(",", index + 1)));
            int3 = Integer.parseInt(s.substring(s.indexOf(",", index + 1) + 1));
        }
        catch (Exception ex) {
            int1 = 0;
            int2 = 0;
            int3 = 0;
        }
        return new Color(int1, int2, int3);
    }
    
    public final int parseSpeed(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
            if (int1 < 0 || int1 > 5) {
                int1 = 2;
            }
        }
        catch (Exception ex) {
            int1 = 2;
        }
        return int1;
    }
    
    public final void draw() {
        this.offG.setColor(this.background);
        this.offG.fillRect(0, 0, this.width, this.height);
        this.offG.setColor(this.foreground);
        this.offG.fillOval(this.width / 2 - this.max / 20, this.height / 2 - this.max / 20, this.max / 10, this.max / 10);
        for (int i = 0; i < 50; ++i) {
            this.offG.drawArc(this.width / 2 - this.max / 20 - (this.max / 2 - this.max / 20) * i / 50, this.height / 2 - this.max / 20 - (this.max / 2 - this.max / 20) * i / 50, this.max / 10 + (this.max - this.max / 10) * i / 50, this.max / 10 + (this.max - this.max / 10) * i / 50, this.arcs[i][0], this.arcs[i][3]);
        }
        this.offG.setFont(new Font(this.font.getName(), this.font.getStyle(), this.text.getFontSize()));
        this.metrics = this.offG.getFontMetrics();
        this.offG.setColor(this.shadowColor);
        this.offG.drawString(this.text.getText(), (this.width - this.metrics.stringWidth(this.text.getText())) / 2 + 2, (this.height + this.text.getFontSize()) / 2 + 2);
        this.offG.setColor(this.fontColor);
        this.offG.drawString(this.text.getText(), (this.width - this.metrics.stringWidth(this.text.getText())) / 2, (this.height + this.text.getFontSize()) / 2);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void destoy() {
        this.arcs = null;
        this.text = null;
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void run() {
        try {
            while (true) {
                for (int i = 0; i < 50; ++i) {
                    final int[] array = this.arcs[i];
                    final int n = 0;
                    array[n] += this.arcs[i][1];
                    if (this.arcs[i][0] > 360) {
                        this.arcs[i][0] -= 360;
                    }
                    this.arcs[i][3] = this.arcs[i][0] + this.arcs[i][2];
                    if (this.arcs[i][3] > 360) {
                        this.arcs[i][3] -= 360;
                    }
                }
                this.draw();
                this.repaint();
                Thread.sleep(this.speed * 50);
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void stop() {
        this.me = null;
    }
}
