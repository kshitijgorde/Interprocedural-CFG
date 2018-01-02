// 
// Decompiled by Procyon v0.5.30
// 

class Field
{
    String Name;
    int Width;
    int Type;
    String TARGET;
    String BASE_URL;
    String BASE_IMAGE;
    boolean VISIBLE;
    boolean SORTED;
    boolean INDEXED;
    boolean FILTER;
    static int DEFAULT;
    static int URL;
    static int IMAGE;
    static int MEMO;
    static int NUMBER;
    
    public Field() {
        this.TARGET = "_new";
        this.BASE_URL = "http://";
        this.BASE_IMAGE = "http://";
        this.Name = "Untitled";
        this.Width = 100;
        this.Type = 0;
        this.VISIBLE = true;
        this.SORTED = false;
        this.INDEXED = false;
        this.FILTER = false;
    }
    
    static {
        Field.DEFAULT = 0;
        Field.URL = 1;
        Field.IMAGE = 2;
        Field.MEMO = 3;
        Field.NUMBER = 4;
    }
    
    public void setSORTED() {
        this.SORTED = true;
    }
    
    public void setMemo() {
        this.Type = Field.MEMO;
        this.VISIBLE = false;
        this.Width = 0;
        this.INDEXED = false;
        this.FILTER = false;
    }
    
    public void setInVisible() {
        this.VISIBLE = false;
        this.Width = 0;
    }
}
