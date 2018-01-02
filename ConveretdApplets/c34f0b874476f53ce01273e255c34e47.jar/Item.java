import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class Item
{
    private MenuSet ms;
    private String text;
    private URL url;
    private String target;
    private boolean hasSub;
    private boolean hasAction;
    private boolean isHead;
    private Image icon;
    
    public Item(final String text, final URL url, final String target, final boolean isHead, final Image icon) {
        this.hasAction = true;
        this.hasSub = false;
        if (icon != null) {
            this.icon = icon;
        }
        this.text = text;
        this.target = target;
        this.url = url;
        this.ms = this.ms;
        this.isHead = isHead;
        if (url == null) {
            this.hasAction = false;
        }
    }
    
    public URL getAction() {
        return this.url;
    }
    
    public Image getIcon() {
        return this.icon;
    }
    
    public MenuSet getMenuSet() {
        return this.ms;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void giveSub(final MenuSet ms) {
        this.ms = ms;
        this.hasSub = true;
    }
    
    public boolean hasAction() {
        return this.hasAction;
    }
    
    public boolean hasSub() {
        return this.hasSub;
    }
    
    public boolean isHead() {
        return this.isHead;
    }
    
    public String toString() {
        return String.valueOf(this.getText()) + " " + this.getAction() + " " + this.getTarget() + " " + this.hasSub();
    }
}
