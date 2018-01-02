// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import matt.web.MattApplet;

public class Logger
{
    public static void log(final Object msg) {
        if (MattProperties.getBoolean("applet")) {
            String smsg = "" + msg;
            if (smsg.length() > 200) {
                smsg = smsg.substring(0, 25) + "...";
            }
            MattApplet.setStatus(smsg);
            System.out.println(msg);
            return;
        }
        if (MattProperties.getString("mode").equals("client")) {
            System.out.println(msg);
            MattGuiNB.log(msg);
        }
        else {
            System.out.println(msg);
        }
    }
}
