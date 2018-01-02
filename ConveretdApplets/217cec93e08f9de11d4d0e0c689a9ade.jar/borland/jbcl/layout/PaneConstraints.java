// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.layout;

public class PaneConstraints
{
    public float proportion;
    public String position;
    public String splitComponentName;
    public String name;
    public static final String TOP = "Top";
    public static final String BOTTOM = "Bottom";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    public static final String ROOT = "Root";
    
    public PaneConstraints() {
        this.proportion = 0.5f;
        this.position = "Top";
    }
    
    public PaneConstraints(final String name, final String splitComponentName, final String position, final float proportion) {
        this.proportion = 0.5f;
        this.position = "Top";
        this.name = name;
        this.splitComponentName = splitComponentName;
        this.position = position;
        this.proportion = proportion;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.name).concat(String.valueOf(": "))).concat(String.valueOf(this.splitComponentName))).concat(String.valueOf(","))).concat(String.valueOf(this.position))).concat(String.valueOf(" proportion:"))).concat(String.valueOf(this.proportion));
    }
}
