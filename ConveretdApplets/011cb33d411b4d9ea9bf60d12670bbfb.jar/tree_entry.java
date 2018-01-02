import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class tree_entry
{
    private String name;
    private String url;
    int level;
    int grid_x;
    int grid_y;
    Vector children;
    boolean in_use;
    boolean publish;
    
    tree_entry(final String name, final String url, final int level, final boolean publish) {
        this.name = name;
        this.url = url;
        this.level = level;
        this.children = null;
        this.in_use = true;
        this.publish = publish;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_url() {
        return this.url;
    }
    
    public int get_level() {
        return this.level;
    }
}
