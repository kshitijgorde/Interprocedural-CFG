// 
// Decompiled by Procyon v0.5.30
// 

package jlog.applet;

import java.util.Dictionary;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Component;
import java.applet.AppletStub;

public class $GMC implements AppletStub
{
    public Component $OU;
    public AppletContext $YID;
    public URL $DMC;
    public URL $EMC;
    public Dictionary $IEC;
    public boolean active;
    
    public $GMC(final Component $ou, final AppletContext $yid, final URL $dmc, final URL $emc, final Dictionary $iec) {
        this.active = true;
        this.$OU = $ou;
        this.$YID = $yid;
        this.$DMC = $dmc;
        this.$EMC = $emc;
        this.$IEC = $iec;
    }
    
    public void appletResize(final int n, final int n2) {
        try {
            this.$OU.setSize(n, n2);
        }
        catch (Exception ex) {}
    }
    
    public AppletContext getAppletContext() {
        return this.$YID;
    }
    
    public URL getCodeBase() {
        return this.$DMC;
    }
    
    public URL getDocumentBase() {
        return this.$EMC;
    }
    
    public String getParameter(final String s) {
        final Dictionary $iec = this.$IEC;
        if ($iec != null) {
            try {
                return $iec.get(s);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public boolean isActive() {
        return this.active;
    }
}
