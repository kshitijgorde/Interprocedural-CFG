import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PulseText extends Applet implements Runnable, MouseListener
{
    String[] lines;
    int iBufferWidth;
    int iBufferHeight;
    FontMetrics metrics;
    Image iBuffer;
    Graphics gBuffer;
    Thread ticker;
    boolean tickerActive;
    int currentLine;
    int ticks;
    int runningTime;
    int liney;
    int lineHeight;
    int[] charWidths;
    int[] charOffsets;
    String[] chars;
    int pulseMagnitude;
    int pulsey;
    Color[] charFadeColors;
    Color[] pulseFadeColors;
    Color bkdColor;
    Color charColor;
    Color pulseColor;
    int charsStart_ticks;
    int charsSpeed_ticks;
    int charsFade_ticks;
    int pulseStart_ticks;
    int pulseSpeed_ticks;
    int pulseFade_ticks;
    boolean displayStatus;
    static final String[] HTMLColorNames;
    static final int[] HTMLColorCodes;
    
    public void init() {
        this.addMouseListener(this);
        this.displayStatus = false;
        this.iBufferWidth = this.getBounds().width;
        this.iBufferHeight = this.getBounds().height;
        this.iBuffer = this.createImage(this.iBufferWidth, this.iBufferHeight);
        this.gBuffer = this.iBuffer.getGraphics();
        this.charsStart_ticks = this.parseIntParameter("text-start", 4);
        this.charsSpeed_ticks = this.parseIntParameter("text-speed", 1);
        this.charsFade_ticks = this.parseIntParameter("text-fade", 32);
        this.pulseStart_ticks = this.parseIntParameter("pulse-start", 0);
        this.pulseSpeed_ticks = this.parseIntParameter("pulse-speed", 1);
        this.pulseFade_ticks = this.parseIntParameter("pulse-fade", 8);
        this.bkdColor = this.parseColorParameter("bkd-color", this.getBackground());
        this.charColor = this.parseColorParameter("text-color", this.getForeground());
        this.pulseColor = this.parseColorParameter("pulse-color", this.getForeground());
        this.calculateFadeValues();
        this.gBuffer.setFont(Font.decode(this.getParameter("font")));
        this.metrics = this.gBuffer.getFontMetrics();
        this.lines = this.parseTextParameter();
        this.parsePaddingParameter();
        this.lineHeight = this.metrics.getAscent();
        this.liney = this.iBufferHeight / 2 + this.lineHeight / 2 - (this.metrics.getLeading() + this.metrics.getDescent()) / 2;
        this.pulsey = this.iBufferHeight / 2 + this.lineHeight / 2;
        this.pulseMagnitude = this.metrics.getAscent();
        this.ticker = null;
        this.ticks = 0;
        this.runningTime = 0;
        this.currentLine = 0;
        this.initLine();
    }
    
    public void start() {
        this.ticker = new Thread(this);
        this.tickerActive = true;
        this.ticker.start();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.iBuffer, 0, 0, this);
    }
    
    public void stop() {
        this.tickerActive = false;
    }
    
    public void run() {
        while (this.tickerActive) {
            if (this.displayStatus) {
                this.showStatus("PulseText applet by David Coldwell, Click to visit the PulseText homepage");
            }
            if (this.ticks >= this.runningTime) {
                this.ticks = 0;
                ++this.currentLine;
                this.currentLine %= this.lines.length;
                this.initLine();
            }
            this.renderFrame();
            this.repaint();
            ++this.ticks;
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void calculateFadeValues() {
        this.charFadeColors = new Color[this.charsFade_ticks];
        this.pulseFadeColors = new Color[this.pulseFade_ticks];
        for (int i = 0; i < this.charsFade_ticks; ++i) {
            this.charFadeColors[i] = new Color((this.bkdColor.getRed() * i + this.charColor.getRed() * (this.charsFade_ticks - i)) / this.charsFade_ticks, (this.bkdColor.getGreen() * i + this.charColor.getGreen() * (this.charsFade_ticks - i)) / this.charsFade_ticks, (this.bkdColor.getBlue() * i + this.charColor.getBlue() * (this.charsFade_ticks - i)) / this.charsFade_ticks);
        }
        for (int j = 0; j < this.pulseFade_ticks; ++j) {
            this.pulseFadeColors[j] = new Color((this.bkdColor.getRed() * j + this.pulseColor.getRed() * (this.pulseFade_ticks - j)) / this.pulseFade_ticks, (this.bkdColor.getGreen() * j + this.pulseColor.getGreen() * (this.pulseFade_ticks - j)) / this.pulseFade_ticks, (this.bkdColor.getBlue() * j + this.pulseColor.getBlue() * (this.pulseFade_ticks - j)) / this.pulseFade_ticks);
        }
    }
    
    public void renderFrame() {
        this.gBuffer.setColor(this.bkdColor);
        this.gBuffer.fillRect(0, 0, this.iBufferWidth, this.iBufferHeight);
        final int n = (this.ticks - this.pulseStart_ticks - (this.pulseFade_ticks - this.pulseSpeed_ticks)) / this.pulseSpeed_ticks;
        final int n2 = (this.ticks - this.pulseStart_ticks) / this.pulseSpeed_ticks;
        final int n3 = (n < 0) ? 0 : n;
        for (int n4 = (n2 >= this.chars.length) ? (this.chars.length - 1) : n2, i = n3; i <= n4; ++i) {
            this.gBuffer.setColor(this.pulseFadeColors[this.ticks - this.pulseStart_ticks - i * this.pulseSpeed_ticks]);
            final int n5 = this.chars[i].equals(" ") ? (this.pulsey - this.pulseMagnitude / 2) : (this.pulsey - this.pulseMagnitude * (i % 2));
            this.gBuffer.drawLine(this.charOffsets[i], this.pulsey - this.pulseMagnitude / 2, this.charOffsets[i] + this.charWidths[i] / 2, n5);
            this.gBuffer.drawLine(this.charOffsets[i] + this.charWidths[i] / 2, n5, this.charOffsets[i] + this.charWidths[i], this.pulsey - this.pulseMagnitude / 2);
        }
        final int n6 = (this.ticks - this.charsStart_ticks - (this.charsFade_ticks - this.charsSpeed_ticks)) / this.charsSpeed_ticks;
        final int n7 = (this.ticks - this.charsStart_ticks) / this.charsSpeed_ticks;
        final int n8 = (n6 < 0) ? 0 : n6;
        for (int n9 = (n7 >= this.chars.length) ? (this.chars.length - 1) : n7, j = n8; j <= n9; ++j) {
            final int n10 = this.ticks - this.charsStart_ticks - j * this.charsSpeed_ticks;
            this.gBuffer.setColor(this.charFadeColors[(n10 >= 0) ? n10 : false]);
            this.gBuffer.drawString(this.chars[j], this.charOffsets[j], this.liney);
        }
    }
    
    public void initLine() {
        this.charOffsets = new int[this.lines[this.currentLine].length()];
        this.chars = new String[this.lines[this.currentLine].length()];
        this.charWidths = new int[this.lines[this.currentLine].length()];
        final int n = this.iBufferWidth / 2 - this.metrics.stringWidth(this.lines[this.currentLine]) / 2;
        for (int i = 0; i < this.lines[this.currentLine].length(); ++i) {
            this.charOffsets[i] = n + this.metrics.stringWidth(this.lines[this.currentLine].substring(0, i));
            this.chars[i] = this.lines[this.currentLine].substring(i, i + 1);
            this.charWidths[i] = this.metrics.stringWidth(this.chars[i]);
        }
        final int n2 = (this.lines[this.currentLine].length() - 1) * this.charsSpeed_ticks + this.charsFade_ticks + this.charsStart_ticks;
        final int n3 = (this.lines[this.currentLine].length() - 1) * this.pulseSpeed_ticks + this.pulseFade_ticks + this.pulseStart_ticks;
        this.runningTime = ((n2 > n3) ? n2 : n3);
    }
    
    public Color parseColorParameter(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return color;
        }
        try {
            return Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return this.decodeColorName(parameter, color);
        }
    }
    
    public int parseIntParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return n;
        }
        try {
            return Integer.parseInt(parameter);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public Color decodeColorName(final String s, final Color color) {
        for (int i = 0; i < PulseText.HTMLColorNames.length; ++i) {
            if (s.equalsIgnoreCase(PulseText.HTMLColorNames[i])) {
                return new Color(PulseText.HTMLColorCodes[i]);
            }
        }
        return color;
    }
    
    public String[] parseTextParameter() {
        final String parameter = this.getParameter("text");
        if (parameter == null) {
            return new String[] { "PulseText Applet v1.0", "By David Coldwell", "Click to visit the PulseText homepage" };
        }
        final Vector vector = new Vector<String>();
        int n = 0;
        int i;
        do {
            final int index = parameter.indexOf("\\n", n);
            i = ((index == -1) ? parameter.length() : index);
            final String trim = parameter.substring(n, i).trim();
            if (!trim.equals("")) {
                vector.addElement(trim);
            }
            n = i + 2;
        } while (i < parameter.length());
        final String[] array = new String[vector.size()];
        for (int j = 0; j < array.length; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    public void parsePaddingParameter() {
        final String parameter = this.getParameter("padding");
        int int1 = 0;
        boolean b = false;
        if (parameter == null) {
            int1 = 10;
        }
        else if (parameter.equalsIgnoreCase("extents")) {
            b = true;
        }
        else {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                int1 = 10;
            }
        }
        final int stringWidth = this.metrics.stringWidth(" ");
        for (int i = 0; i < this.lines.length; ++i) {
            final int stringWidth2 = this.metrics.stringWidth(this.lines[i]);
            final int n = stringWidth * int1 * 2;
            if (stringWidth2 < this.iBufferWidth) {
                if (b || n + stringWidth2 > this.iBufferWidth) {
                    int1 = (this.iBufferWidth - stringWidth2) / stringWidth / 2;
                }
                this.lines[i] = String.valueOf(this.insertPadding(int1)) + this.lines[i] + this.insertPadding(int1);
            }
        }
    }
    
    public String insertPadding(final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string = String.valueOf(string) + " ";
        }
        return string;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            try {
                System.out.println(mouseEvent.getModifiers());
                this.getAppletContext().showDocument(new URL("http://www.dcoldwell.pwp.blueyonder.co.uk/pulsetext"));
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.displayStatus = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.displayStatus = false;
        this.showStatus("");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    static {
        HTMLColorNames = new String[] { "aqua", "black", "blue", "fuschia", "gray", "green", "lime", "maroon", "navy", "olive", "purple", "red", "silver", "teal", "white", "yellow" };
        HTMLColorCodes = new int[] { 65535, 0, 255, 16711935, 12500670, 32768, 65280, 8388608, 128, 8421376, 8388736, 16711680, 12632256, 32896, 16777215, 16776960 };
    }
}
