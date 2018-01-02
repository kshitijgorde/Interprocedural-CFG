// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.net.URI;
import java.awt.Desktop;

public class IEAutoStart
{
    public static void run(String replaceAll) {
        replaceAll = replaceAll.replaceAll("]", "");
        try {
            Desktop.getDesktop().browse(new URI(replaceAll.trim()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
