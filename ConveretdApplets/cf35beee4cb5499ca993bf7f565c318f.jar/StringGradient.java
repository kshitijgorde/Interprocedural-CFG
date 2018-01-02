import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class StringGradient extends Component
{
    private Font sgFont;
    private String sgString;
    private Color sgBgcolor;
    private Color sgStartcolor;
    private Color currentColor;
    private String sgCyclecolor;
    private int strength;
    private int width;
    private int height;
    private Image sgImage;
    private Graphics sgGraphics;
    private boolean start;
    private InfoCanvas parent;
    private int red;
    private int blue;
    private int green;
    private int bgred;
    private int bgblue;
    private int bggreen;
    private Image[] line;
    private Graphics[] lineGraph;
    private FontMetrics fontmetrics;
    private int fontsize;
    private int fontwidth;
    
    public StringGradient(final InfoCanvas parent) {
        this.start = true;
        this.parent = parent;
        this.sgFont = new Font("Arial", 0, 16);
        this.sgString = "StringGradient";
        this.sgBgcolor = Color.red;
        this.sgStartcolor = Color.blue;
        this.currentColor = Color.red;
        this.sgCyclecolor = "all";
        this.strength = 10;
    }
    
    public void flush() {
    }
    
    public Image getGradient(final String s) {
        this.makeGradient(this.sgString = new String(s), this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, this.strength);
        return this.sgImage;
    }
    
    public Image getGradient(final String s, final Font sgFont) {
        this.sgString = new String(s);
        this.sgFont = sgFont;
        this.makeGradient(this.sgString, this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, this.strength);
        return this.sgImage;
    }
    
    public Image getGradient(final String s, final Font sgFont, final Color color) {
        this.sgString = new String(s);
        this.sgFont = sgFont;
        this.sgBgcolor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        this.makeGradient(this.sgString, this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, this.strength);
        return this.sgImage;
    }
    
    public Image getGradient(final String s, final Font sgFont, final Color color, final Color sgStartcolor) {
        this.sgString = new String(s);
        this.sgFont = sgFont;
        this.sgBgcolor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        this.sgStartcolor = sgStartcolor;
        this.makeGradient(this.sgString, this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, this.strength);
        return this.sgImage;
    }
    
    public Image getGradient(final String s, final Font sgFont, final Color color, final Color sgStartcolor, final String sgCyclecolor) {
        this.sgString = new String(s);
        this.sgFont = sgFont;
        this.sgBgcolor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        this.sgStartcolor = sgStartcolor;
        this.sgCyclecolor = sgCyclecolor;
        this.makeGradient(this.sgString, this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, this.strength);
        return this.sgImage;
    }
    
    public Image getGradient(final String s, final Font sgFont, final Color color, final Color sgStartcolor, final String sgCyclecolor, final int strength) {
        this.sgString = new String(s);
        this.sgFont = sgFont;
        this.sgBgcolor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        this.sgStartcolor = sgStartcolor;
        this.sgCyclecolor = sgCyclecolor;
        this.strength = strength;
        this.makeGradient(this.sgString, this.sgFont, this.sgBgcolor, this.sgStartcolor, this.sgCyclecolor, strength);
        return this.sgImage;
    }
    
    private void makeGradient(final String s, final Font font, final Color color, final Color color2, final String s2, final int n) {
        try {
            this.sgInit();
            if (this.sgImage == null) {
                System.out.println("sgImage is null at makeGradient()");
            }
            for (int i = 0; i < this.fontsize + this.fontsize / 10; ++i) {
                this.line[i] = this.parent.fi2f.getEmptyImage(this.fontwidth + 10, 1);
                (this.lineGraph[i] = this.line[i].getGraphics()).setColor(color);
                this.lineGraph[i].fillRect(0, 0, this.fontwidth + 10, 1);
                if (s2.equals("red")) {
                    this.red += n;
                    if (this.red > 255) {
                        this.red = 0;
                    }
                }
                else if (s2.equals("green")) {
                    this.green += n;
                    if (this.green > 255) {
                        this.green = 0;
                    }
                }
                else if (s2.equals("blue")) {
                    this.blue += n;
                    if (this.blue > 255) {
                        this.blue = 0;
                    }
                }
                else if (s2.equals("all")) {
                    this.blue += n;
                    this.red += n;
                    this.green += n;
                    if (this.blue > 255) {
                        this.blue = 0;
                    }
                    if (this.red > 255) {
                        this.red = 0;
                    }
                    if (this.green > 255) {
                        this.green = 0;
                    }
                }
                this.lineGraph[i].setColor(new Color(this.red, this.green, this.blue));
                this.lineGraph[i].setFont(font);
            }
            for (int j = 0; j < this.fontsize + this.fontsize / 10 - 1; ++j) {
                this.lineGraph[j].drawString(s, 10, j);
                this.sgGraphics.drawImage(this.line[j], 0, this.fontsize + this.fontsize / 10 - j - 5, this);
            }
        }
        catch (Exception ex) {
            System.out.println("Little Error");
        }
    }
    
    public void setBackground(final Color sgBgcolor) {
        this.sgBgcolor = sgBgcolor;
    }
    
    public void setColor(final Color sgStartcolor) {
        this.sgStartcolor = sgStartcolor;
    }
    
    public void setFont(final Font sgFont) {
        this.sgFont = sgFont;
    }
    
    public void setString(final String s) {
        this.sgString = new String(s);
    }
    
    private void sgInit() {
        this.red = this.sgStartcolor.getRed();
        this.green = this.sgStartcolor.getGreen();
        this.blue = this.sgStartcolor.getBlue();
        this.bgred = this.sgBgcolor.getRed();
        this.bggreen = this.sgBgcolor.getGreen();
        this.bgblue = this.sgBgcolor.getBlue();
        this.fontmetrics = this.getFontMetrics(this.sgFont);
        this.fontwidth = this.fontmetrics.stringWidth(this.sgString);
        this.fontsize = this.fontmetrics.getAscent() + this.fontmetrics.getDescent();
        this.line = new Image[this.fontsize + this.fontsize / 10];
        this.lineGraph = new Graphics[this.fontsize + this.fontsize / 10];
        this.sgImage = this.parent.fi2f.getEmptyImage(this.fontwidth + 10, this.fontsize + this.fontsize / 10);
        if (this.sgImage == null) {
            System.out.println("sgImage is null at Start");
        }
        (this.sgGraphics = this.sgImage.getGraphics()).setColor(this.sgBgcolor);
        this.sgGraphics.fillRect(0, 0, this.fontwidth + 10, this.fontsize + this.fontsize / 10);
    }
    
    public String toString() {
        return this.sgString;
    }
}
