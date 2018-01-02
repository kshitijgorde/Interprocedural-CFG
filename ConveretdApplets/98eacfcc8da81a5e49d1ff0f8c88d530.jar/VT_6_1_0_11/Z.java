// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.dnd.DropTargetDragEvent;

public final class Z
{
    private static String a;
    
    public static int a(final DropTargetDragEvent dropTargetDragEvent) {
        final String lowerCase;
        if (((lowerCase = System.getProperty("os.name").toLowerCase()).equalsIgnoreCase("windows xp") ? 1 : (lowerCase.equalsIgnoreCase("windows 2000") ? 4 : (lowerCase.equalsIgnoreCase("windows nt") ? 3 : (lowerCase.startsWith("windows") ? 2 : (lowerCase.startsWith("mac os") ? 0 : 99))))) != 0 || !Z.a.startsWith("1.4.1")) {
            return dropTargetDragEvent.getDropAction();
        }
        switch (dropTargetDragEvent.getDropAction()) {
            case 0: {
                return 1;
            }
            case 1: {
                return 2;
            }
            default: {
                return 1;
            }
        }
    }
    
    static {
        Z.a = System.getProperty("java.version");
    }
}
