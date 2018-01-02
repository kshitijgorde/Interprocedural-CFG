// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.client;

import java.util.Date;
import com.postx.util.logging.Logger;
import java.applet.Applet;

public class PostXTools extends Applet
{
    public static final String Ident = "$Id: PostXTools.java,v 1.54 2011/01/25 21:51:19 blm Exp $";
    public static final int build = 55;
    public static final long buildTime = 1296930517000L;
    private static final Logger log;
    private EnvelopeTools tools;
    
    public void close() {
        if (this.tools != null) {
            this.tools.close();
        }
    }
    
    public PostXTools() {
        this.tools = null;
    }
    
    public void destroy() {
        this.close();
    }
    
    static {
        log = Logger.global;
    }
    
    public void init() {
        PostXTools.log.info("Ident: " + "$Id: PostXTools.java,v 1.54 2011/01/25 21:51:19 blm Exp $");
        PostXTools.log.info("build: " + 55);
        PostXTools.log.info("build time: " + new Date(1296930517000L));
        try {
            Class.forName("sun.misc.BASE64Encoder");
        }
        catch (Exception ex) {
            if (System.getProperty("os.name").equals("Windows Vista")) {
                PostXTools.log.severe("Your installed Java is too old, upgrade to Java version 1.5 or higher.");
                return;
            }
            PostXTools.log.severe("A required system package (sun.misc) isn't available, upgrade to Java version 1.3 or higher");
            return;
        }
        (this.tools = new EnvelopeTools(this)).run();
    }
}
