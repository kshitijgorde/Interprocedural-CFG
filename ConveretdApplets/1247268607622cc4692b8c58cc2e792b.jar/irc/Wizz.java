// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Point;
import irc.channels.ChannelWindow;
import irc.channels.PrivateWindow;
import javax.swing.JFrame;

public class Wizz
{
    public static void vibre(final JFrame frame, final EIRC eirc) {
        if (System.getProperty("os.name").toLowerCase().indexOf("mac") != -1) {
            return;
        }
        final Object current = eirc.getChat_panel().getCurrent();
        frame.setState(0);
        frame.setAlwaysOnTop(true);
        final Point location = frame.getLocation();
        for (int i = 10; i > 0; --i) {
            for (int j = 5; j > 0; --j) {
                frame.setLocation(location.x, i + location.y);
                frame.setLocation(i + location.x, location.y);
                frame.setLocation(location.x, -i + location.y);
                frame.setLocation(-i + location.x, location.y);
            }
        }
        frame.setAlwaysOnTop(false);
        if (current instanceof PrivateWindow) {
            ((PrivateWindow)current).EntryrequestFocus();
        }
        else if (current instanceof ChannelWindow) {
            ((ChannelWindow)current).entryrequestFocus();
        }
    }
}
