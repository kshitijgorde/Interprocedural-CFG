// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

import java.awt.Color;
import java.io.Serializable;

public class Node implements Serializable
{
    private double x;
    private double y;
    private Color color;
    private Color color2;
    private Color color3;
    private double dx;
    private double dy;
    private int connections;
    private String lbl;
    String annotation;
    private boolean fixed;
    private int shape;
    private int dataSpace1;
    private String url;
    private int expand;
    private boolean display;
    
    public void setURL(final String url) {
        this.url = url;
    }
    
    public String getURL() {
        return this.url;
    }
    
    public void setExpand(final int expand) {
        this.expand = expand;
    }
    
    public int getExpand() {
        return this.expand;
    }
    
    public void setDisplay(final boolean display) {
        this.display = display;
    }
    
    public boolean getDisplay() {
        return this.display;
    }
    
    public String toString() {
        return this.lbl + " " + this.x + " " + this.y + " " + this.color;
    }
    
    public String report() {
        return this.lbl + "\t" + this.x + "\t" + this.y + "\t" + this.getColorEntry() + "\t" + this.getColor2Entry() + "\t" + this.getColor3Entry() + "\ts " + this.shape + "\ta \"" + this.getAnnotation() + "\"";
    }
    
    public Node() {
        this.color = Color.red;
        this.color2 = null;
        this.color3 = null;
        this.annotation = "no annotation";
        this.fixed = false;
        this.shape = 1;
        this.display = true;
        this.lbl = "unlabeled";
        this.x = Math.random();
        this.y = Math.random();
        this.connections = 0;
        this.expand = 0;
    }
    
    public Node(final String lbl) {
        this.color = Color.red;
        this.color2 = null;
        this.color3 = null;
        this.annotation = "no annotation";
        this.fixed = false;
        this.shape = 1;
        this.display = true;
        this.lbl = lbl;
        this.x = Math.random();
        this.y = Math.random();
        this.connections = 0;
        this.expand = 0;
    }
    
    public Node(final String lbl, final double x, final double y) {
        this.color = Color.red;
        this.color2 = null;
        this.color3 = null;
        this.annotation = "no annotation";
        this.fixed = false;
        this.shape = 1;
        this.display = true;
        this.lbl = lbl;
        this.x = x;
        this.y = y;
        this.connections = 0;
        this.expand = 0;
    }
    
    public Node(final String lbl, final double x, final double y, final int shape) {
        this.color = Color.red;
        this.color2 = null;
        this.color3 = null;
        this.annotation = "no annotation";
        this.fixed = false;
        this.shape = 1;
        this.display = true;
        this.lbl = lbl;
        this.x = x;
        this.y = y;
        this.shape = shape;
        this.connections = 0;
        this.expand = 0;
    }
    
    public Node(final String lbl, final Double n, final Double n2, final Integer n3, final Color color) {
        this.color = Color.red;
        this.color2 = null;
        this.color3 = null;
        this.annotation = "no annotation";
        this.fixed = false;
        this.shape = 1;
        this.display = true;
        this.lbl = lbl;
        this.x = n;
        this.y = n2;
        this.shape = n3;
        this.color = color;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void rescale(final int n) {
        this.x *= n;
        this.y *= n;
    }
    
    public void rescale(final int n, final int n2) {
        this.x *= n;
        this.y *= n2;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getDX() {
        return this.dx;
    }
    
    public void setDX(final double dx) {
        this.dx = dx;
    }
    
    public double getDY() {
        return this.dy;
    }
    
    public void setDY(final double dy) {
        this.dy = dy;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor2() {
        return this.color2;
    }
    
    public void setColor2(final Color color2) {
        this.color2 = color2;
    }
    
    public Color getColor3() {
        return this.color3;
    }
    
    public void setColor3(final Color color3) {
        this.color3 = color3;
    }
    
    public boolean isFixed() {
        return this.fixed;
    }
    
    public void setFixed(final boolean fixed) {
        this.fixed = fixed;
    }
    
    public int getShape() {
        return this.shape;
    }
    
    public void setShape(final int shape) {
        this.shape = shape;
    }
    
    protected boolean removeConnection() {
        --this.connections;
        return this.connections > 0;
    }
    
    protected void addConnection() {
        ++this.connections;
    }
    
    public void manipulateColorElement(final int n, final int n2, final boolean b) {
        final int[] array = { this.color.getRed(), this.color.getGreen(), this.color.getBlue() };
        if (b) {
            final int n3 = array[n];
            array[n] = array[n2];
            array[n2] = n3;
        }
        else {
            array[n] = array[n2];
        }
        this.color = new Color(array[0], array[1], array[2]);
    }
    
    public void manipulateChannel(final int n, final int n2) {
        final int[] array = { this.color.getRed(), this.color.getGreen(), this.color.getBlue() };
        array[n] = n2;
        this.color = new Color(array[0], array[1], array[2]);
    }
    
    public boolean equals(final Object o) {
        return o.getClass() == this.getClass() && this.lbl.compareTo(((Node)o).lbl) == 0;
    }
    
    public String getLabel() {
        return this.lbl;
    }
    
    public void setLabel(final String lbl) {
        this.lbl = lbl;
    }
    
    public void setXY(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public int getConnections() {
        return this.connections;
    }
    
    public int hashCode() {
        return this.lbl.hashCode();
    }
    
    public void copyPosition(final Node node) {
        this.x = node.x;
        this.y = node.y;
    }
    
    public String getColorEntry() {
        return "c " + this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue();
    }
    
    public String getAppletColorEntry() {
        return this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue();
    }
    
    public String getColor2Entry() {
        if (this.color2 == null) {
            return "";
        }
        return "c2 " + this.color2.getRed() + "," + this.color2.getGreen() + "," + this.color2.getBlue();
    }
    
    public String getColor3Entry() {
        if (this.color3 == null) {
            return "";
        }
        return "c3 " + this.color3.getRed() + "," + this.color3.getGreen() + "," + this.color3.getBlue();
    }
    
    public void setDataSpace1(final int dataSpace1) {
        this.dataSpace1 = dataSpace1;
    }
    
    public int getDataSpace1() {
        return this.dataSpace1;
    }
    
    public void setAnnotation(final String annotation) {
        this.annotation = annotation;
    }
    
    public String getAnnotation() {
        return this.annotation;
    }
}
