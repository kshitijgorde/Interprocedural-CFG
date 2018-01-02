// 
// Decompiled by Procyon v0.5.30
// 

package menu3;

import java.awt.Graphics;
import java.awt.Color;

public class MenuItem
{
    protected String menuName;
    protected char type;
    protected String url;
    protected MenuItem[] sub;
    protected int index;
    protected int startx;
    protected int endx;
    protected int starty;
    protected int endy;
    protected boolean active;
    protected Color color1;
    protected Color color2;
    protected Color currentColor;
    protected int depth;
    public static int ht;
    public static char ITEM;
    public static char MENU;
    public static char END;
    
    public MenuItem(final char intype, final String inname, final String inurl, final int inindex, final Color in1, final Color in2, final int itemDepth) throws Exception {
        this.startx = 0;
        this.endx = 0;
        this.starty = 0;
        this.endy = 0;
        this.active = false;
        this.currentColor = this.color1;
        this.depth = 0;
        this.setType(intype);
        this.setMenuName(inname);
        this.setUrl(inurl);
        this.setIndex(inindex);
        this.setColor1(in1);
        this.setColor2(in2);
        this.setDepth(itemDepth);
    }
    
    public void setColor1(final Color in) {
        this.color1 = in;
    }
    
    public void setColor2(final Color in) {
        this.color2 = in;
    }
    
    public Color getColor1() {
        return this.color1;
    }
    
    public Color getColor2() {
        return this.color2;
    }
    
    public boolean overlaps(final int x, final int y) {
        return x >= this.startx && x <= this.endx && y >= this.starty && y <= this.endy;
    }
    
    public int drawMenuItem(final Graphics g, final int x, final int items, final int startY, final boolean secondColor, final int width) {
        if (secondColor) {
            g.setColor(this.getColor2());
        }
        else {
            g.setColor(this.getColor1());
        }
        MenuItem.ht = g.getFontMetrics().getHeight();
        final int realy = startY + items * MenuItem.ht;
        g.drawString(this.getMenuName(), x, realy);
        if (!secondColor) {
            this.setx(x);
            this.sety(realy - MenuItem.ht);
            this.setendy(realy);
            this.setendx(x + width);
        }
        return this.getendx();
    }
    
    public void print() {
        System.out.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.getIndex()).concat(String.valueOf("-"))).concat(String.valueOf(this.getMenuName()))).concat(String.valueOf("-"))).concat(String.valueOf(this.getType()))).concat(String.valueOf("-"))).concat(String.valueOf(this.getUrl()))).concat(String.valueOf("-"))).concat(String.valueOf(this.getx()))).concat(String.valueOf("-"))).concat(String.valueOf(this.gety()))).concat(String.valueOf("-"))).concat(String.valueOf(this.getendx()))).concat(String.valueOf("-"))).concat(String.valueOf(this.getendy())));
    }
    
    public void setMenuName(final String in) {
        this.menuName = new String(in);
    }
    
    public String getMenuName() {
        return this.menuName;
    }
    
    public void setType(final char in) throws Exception {
        if (in != MenuItem.ITEM && in != MenuItem.MENU && in != MenuItem.END) {
            throw new Exception("Invalid MenuItem type");
        }
        this.type = in;
    }
    
    public char getType() {
        return this.type;
    }
    
    public void setIndex(final int in) {
        this.index = in;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setDepth(final int in) {
        this.depth = in;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public void setx(final int in) {
        this.startx = in;
    }
    
    public int getx() {
        return this.startx;
    }
    
    public void setendx(final int in) {
        this.endx = in;
    }
    
    public int getendx() {
        return this.endx;
    }
    
    public void sety(final int in) {
        this.starty = in;
    }
    
    public int gety() {
        return this.starty;
    }
    
    public void setendy(final int in) {
        this.endy = in;
    }
    
    public int getendy() {
        return this.endy;
    }
    
    public void setUrl(final String in) {
        this.url = new String(in);
    }
    
    public String getUrl() {
        return this.url;
    }
    
    static {
        MenuItem.ITEM = 'I';
        MenuItem.MENU = 'M';
        MenuItem.END = 'E';
    }
}
