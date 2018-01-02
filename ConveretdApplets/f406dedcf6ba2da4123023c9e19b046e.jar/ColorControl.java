import java.awt.Event;
import java.awt.Color;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorControl extends Checkbox
{
    protected String id;
    protected String tip;
    protected Viewer parent;
    protected Color c;
    protected int i;
    
    public ColorControl(final String s, final String tip, final boolean state, final Color color, final int i, final Viewer parent) {
        super(s);
        final StringBuffer sb = new StringBuffer(s.length());
        for (int j = 0; j < s.length(); ++j) {
            if (!Character.isSpace(s.charAt(j))) {
                sb.append(s.charAt(j));
            }
        }
        this.id = sb.toString();
        if (color != Color.black) {
            this.setBackground(color);
        }
        this.tip = tip;
        this.c = color;
        this.i = i;
        this.parent = parent;
        this.setState(state);
        this.action(null, null);
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.getState()) {
            this.parent.put(this.i, this.c);
        }
        else {
            this.parent.put(this.i, null);
        }
        return true;
    }
    
    public String idVRML() {
        return this.id;
    }
    
    public String vrmlPROTO() {
        return "DEF " + this.idVRML() + " Material {\n" + "    diffuseColor " + ((this.c.getRed() == 0 && this.c.getGreen() == 0 && this.c.getBlue() == 0) ? "1 1 1" : (this.c.getRed() / 255.0 + " " + this.c.getGreen() / 255.0 + " " + this.c.getBlue() / 255.0)) + "\n" + "}\n";
    }
}
