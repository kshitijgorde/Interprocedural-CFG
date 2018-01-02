// 
// Decompiled by Procyon v0.5.30
// 

package newstick.menu;

import java.awt.Rectangle;
import java.awt.Point;

public class MenuItem
{
    public String Display;
    public String Command;
    public boolean Enabled;
    public Point Location;
    public Rectangle Area;
    
    public MenuItem(final String s) {
        this(s, s);
    }
    
    public MenuItem(final String display, final String command) {
        this.Display = display;
        this.Command = command;
        this.Enabled = true;
    }
}
