import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class am implements ActionListener
{
    public void actionPerformed(final ActionEvent actionEvent) {
        Main.b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.xsl&XSL=xsl.billing.promo", "frameContent");
    }
}
