// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import java.awt.Cursor;
import jmaster.util.property.D;
import java.util.HashMap;
import jmaster.util.property.A;

public class CursorPropertyLoader implements A
{
    public static final String[] CURSOR_NAMES;
    public static final int[] CURSOR_IDS;
    protected HashMap B;
    static /* synthetic */ Class class$java$awt$Cursor;
    
    public CursorPropertyLoader() {
        this.B = new HashMap();
    }
    
    public synchronized Object loadProperty(final String s, final D d, final String s2, final int n) {
        Cursor cursor;
        if ((cursor = this.B.get(s)) == null) {
            for (int i = 0; i < CursorPropertyLoader.CURSOR_NAMES.length; ++i) {
                if (CursorPropertyLoader.CURSOR_NAMES[i].equals(s)) {
                    this.B.put(s, cursor = new Cursor(CursorPropertyLoader.CURSOR_IDS[i]));
                }
            }
        }
        if (cursor == null) {
            throw new IllegalArgumentException("Unknown cursor name: " + s);
        }
        return cursor;
    }
    
    public Class getPropertyClass() {
        return (CursorPropertyLoader.class$java$awt$Cursor == null) ? (CursorPropertyLoader.class$java$awt$Cursor = class$("java.awt.Cursor")) : CursorPropertyLoader.class$java$awt$Cursor;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        CURSOR_NAMES = new String[] { "CROSSHAIR_CURSOR", "CUSTOM_CURSOR", "DEFAULT_CURSOR", "E_RESIZE_CURSOR", "HAND_CURSOR", "MOVE_CURSOR", "N_RESIZE_CURSOR", "NE_RESIZE_CURSOR", "NW_RESIZE_CURSOR", "S_RESIZE_CURSOR", "SE_RESIZE_CURSOR", "SW_RESIZE_CURSOR", "TEXT_CURSOR", "W_RESIZE_CURSOR", "WAIT_CURSOR" };
        CURSOR_IDS = new int[] { 1, -1, 0, 11, 12, 13, 8, 7, 6, 9, 5, 4, 2, 10, 3 };
    }
}
