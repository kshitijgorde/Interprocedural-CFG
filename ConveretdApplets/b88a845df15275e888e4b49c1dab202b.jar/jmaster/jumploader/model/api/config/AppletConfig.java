// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.config;

import java.util.MissingResourceException;
import jmaster.util.property.C;
import jmaster.jumploader.model.api.B;

public class AppletConfig
{
    private static final String K = "AppletConfig.properties";
    public static final String MODE_FRAMED = "framed";
    public static final String MODE_EMBEDDED = "embedded";
    private String D;
    private boolean A;
    private boolean H;
    private boolean F;
    private boolean I;
    private boolean B;
    private boolean C;
    private boolean E;
    private boolean J;
    private String G;
    
    public AppletConfig(final B b) {
        this.D = "embedded";
        this.A = false;
        this.H = false;
        this.F = false;
        this.I = false;
        this.B = false;
        this.C = false;
        this.E = false;
        this.J = false;
        this.G = null;
        try {
            jmaster.util.property.C.A().A(this, jmaster.util.property.B.C().G("AppletConfig.properties"), null);
        }
        catch (MissingResourceException ex) {}
    }
    
    public String toString() {
        return "mode=" + this.D + "\r\n" + "fireAppletInitialized=" + this.A + "\r\n" + "fireUploaderFileAdded=" + this.H + "\r\n" + "fireUploaderFileRemoved=" + this.F + "\r\n" + "fireUploaderFileMoved=" + this.I + "\r\n" + "fireUploaderFileStatusChanged=" + this.B + "\r\n" + "fireUploaderFilesReset=" + this.C + "\r\n" + "fireUploaderStatusChanged=" + this.E + "\r\n" + "fireUploaderSelectionChanged=" + this.J + "\r\n" + "properties=" + this.G + "\r\n" + "";
    }
    
    public boolean isFireUploaderFileAdded() {
        return this.H;
    }
    
    public void setFireUploaderFileAdded(final boolean h) {
        this.H = h;
    }
    
    public boolean isFireUploaderFileRemoved() {
        return this.F;
    }
    
    public void setFireUploaderFileRemoved(final boolean f) {
        this.F = f;
    }
    
    public boolean isFireUploaderFilesReset() {
        return this.C;
    }
    
    public void setFireUploaderFilesReset(final boolean c) {
        this.C = c;
    }
    
    public boolean isFireUploaderFileStatusChanged() {
        return this.B;
    }
    
    public void setFireUploaderFileStatusChanged(final boolean b) {
        this.B = b;
    }
    
    public boolean isFireUploaderStatusChanged() {
        return this.E;
    }
    
    public void setFireUploaderStatusChanged(final boolean e) {
        this.E = e;
    }
    
    public String getMode() {
        return this.D;
    }
    
    public void setMode(final String d) {
        this.D = d;
    }
    
    public boolean isFireUploaderSelectionChanged() {
        return this.J;
    }
    
    public void setFireUploaderSelectionChanged(final boolean j) {
        this.J = j;
    }
    
    public boolean isFireAppletInitialized() {
        return this.A;
    }
    
    public void setFireAppletInitialized(final boolean a) {
        this.A = a;
    }
    
    public String getProperties() {
        return this.G;
    }
    
    public void setProperties(final String g) {
        this.G = g;
    }
    
    public boolean isFireUploaderFileMoved() {
        return this.I;
    }
    
    public void setFireUploaderFileMoved(final boolean i) {
        this.I = i;
    }
}
