// 
// Decompiled by Procyon v0.5.30
// 

class Word
{
    private int xco;
    private int yco;
    String txt;
    boolean isLink;
    boolean isActiveLink;
    boolean isImage;
    String link;
    int w;
    String target;
    
    public Word(final String s, final int xco, final int yco, final int w) {
        this.target = "_self";
        this.txt = String.valueOf(s) + " ";
        this.yco = yco;
        this.xco = xco;
        this.w = w;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public String getText() {
        return this.txt;
    }
    
    public int getW() {
        return this.w;
    }
    
    public int getXco() {
        return this.xco;
    }
    
    public int getYco() {
        return this.yco;
    }
    
    public boolean isActiveLink() {
        return this.isActiveLink;
    }
    
    public boolean isImage() {
        return this.isImage;
    }
    
    public boolean isLink() {
        return this.isLink;
    }
    
    public void setActiveLink(final boolean isActiveLink) {
        this.isActiveLink = isActiveLink;
    }
    
    public void setImage(final boolean isImage) {
        this.isImage = isImage;
    }
    
    public void setLink(final String link) {
        this.isLink = true;
        this.link = link;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
}
