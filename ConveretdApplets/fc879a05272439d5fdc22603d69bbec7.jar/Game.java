import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Game
{
    private String name;
    private Vector description;
    private String html;
    private int width;
    private int height;
    private int it;
    private int fw;
    private String dir;
    
    public Game() {
        this.it = 0;
        this.fw = 0;
        this.name = "Untitled";
        this.html = "index.html";
        this.width = 400;
        this.height = 300;
        this.description = new Vector();
    }
    
    public void addToDescription(final String s) {
        this.description.addElement(s);
    }
    
    public String getDir() {
        return this.dir;
    }
    
    public int getFontWidth() {
        return this.fw;
    }
    
    public String getHTML() {
        return this.html;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public boolean hasNextLine() {
        return this.it < this.description.size();
    }
    
    public String nextLine() {
        ++this.it;
        return this.description.elementAt(this.it - 1);
    }
    
    public void resetIterator() {
        this.it = 0;
    }
    
    public void setDimension(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setDir(final String dir) {
        this.dir = dir;
    }
    
    public void setFontWidth(final int fw) {
        this.fw = fw;
    }
    
    public void setHTML(final String html) {
        this.html = html;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
