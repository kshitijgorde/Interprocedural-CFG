import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.util.Date;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CountDown extends Applet
{
    private static String[][] parameterInfo;
    private static final String[] digitImageFile;
    private Image backgroundImage;
    private Image[] digitImage;
    private String outputString;
    private int space;
    private int x;
    private int y;
    
    public void init() {
        final ImageProvider imageProvider = new ImageProvider(this);
        final String parameter;
        if ((parameter = this.getParameter("date")) != null) {
            this.outputString = String.valueOf(Math.max(0L, (new Date(parameter).getTime() - new Date().getTime()) / 86400000L + 1L));
        }
        else {
            this.outputString = "666";
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("bgcolor")) != null) {
            this.setBackground(new Color(Integer.parseInt(parameter2, 16)));
        }
        try {
            this.digitImage = imageProvider.get(CountDown.digitImageFile);
            final String parameter3;
            if ((parameter3 = this.getParameter("background")) != null) {
                this.backgroundImage = imageProvider.get(parameter3);
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("space")) != null) {
            this.space = Integer.parseInt(parameter4, 10);
        }
        else {
            this.space = 1;
        }
        final Dimension size = this.size();
        this.y = (size.height - 26) / 2;
        this.x = -this.space;
        for (int i = 0; i < this.outputString.length(); ++i) {
            this.x += this.digitImage[this.outputString.charAt(i) - '0'].getWidth(null) + this.space;
        }
        this.x = (size.width - this.x) / 2;
    }
    
    public void paint(final Graphics graphics) {
        int x = this.x;
        if (this.backgroundImage != null) {
            graphics.drawImage(this.backgroundImage, 0, 0, null);
        }
        for (int i = 0; i < this.outputString.length(); ++i) {
            graphics.drawImage(this.digitImage[this.outputString.charAt(i) - '0'], x, this.y, null);
            x += this.digitImage[this.outputString.charAt(i) - '0'].getWidth(null) + this.space;
        }
    }
    
    public String getAppletInfo() {
        return "CountDown\n\nWritten by Michael Kraus\nVersion 1.0\nJDK 1.0";
    }
    
    public String[][] getParameterInfo() {
        return CountDown.parameterInfo;
    }
    
    static {
        CountDown.parameterInfo = new String[][] { { "date", "String", "target date, e.g. \"1 Jan 2000\"" }, { "bgcolor", "int", "background color" }, { "background", "file", "background image" }, { "space", "int", "space pixels between digits" } };
        digitImageFile = new String[] { "zero.gif", "one.gif", "two.gif", "three.gif", "four.gif", "five.gif", "six.gif", "seven.gif", "eight.gif", "nine.gif" };
    }
}
