// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.util.StringTokenizer;
import com.daysofwonder.util.t;
import com.daysofwonder.util.UIProperties;
import java.awt.Frame;

public class aO
{
    public static D a(final Frame frame, final UIProperties uiProperties) {
        if (a()) {
            try {
                return (D)Class.forName("com.daysofwonder.applet.TablePasswordWindow14").getConstructor(Frame.class, UIProperties.class).newInstance(frame, uiProperties);
            }
            catch (Exception ex) {
                t.a(ex);
                return new S(frame, uiProperties);
            }
        }
        return new S(frame, uiProperties);
    }
    
    private static boolean a() {
        int int1 = 0;
        int int2 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.version"), "._-");
        if (stringTokenizer.hasMoreTokens()) {
            int1 = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            int2 = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            Integer.parseInt(stringTokenizer.nextToken());
        }
        return (int1 >= 1 && int2 >= 4) || int1 > 1;
    }
}
