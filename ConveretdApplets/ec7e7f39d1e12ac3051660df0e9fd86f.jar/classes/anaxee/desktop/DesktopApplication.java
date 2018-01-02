// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.desktop;

import java.awt.event.WindowListener;
import java.security.AccessController;
import java.awt.Component;
import java.security.PrivilegedAction;
import classes.anaxee.gui.GuiComponents;
import javax.swing.JFrame;

public class DesktopApplication extends JFrame
{
    GuiComponents gui_components;
    boolean is_mode_debug;
    String gui_usage_mode;
    String bio_usage_mode;
    
    public void setUseDebugMode(final boolean useDebugMode) {
        this.gui_components.setUseDebugMode(useDebugMode);
    }
    
    public DesktopApplication(final boolean is_mode_debug, final String gui_usage_mode, final String bio_usage_mode) {
        this.is_mode_debug = true;
        this.bio_usage_mode = null;
        this.gui_usage_mode = gui_usage_mode;
        this.bio_usage_mode = bio_usage_mode;
        this.is_mode_debug = is_mode_debug;
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    DesktopApplication.this.gui_components = new GuiComponents();
                    DesktopApplication.this.setUseDebugMode(DesktopApplication.this.is_mode_debug);
                    DesktopApplication.this.setUseMode(DesktopApplication.this.gui_usage_mode);
                    DesktopApplication.this.gui_components.setBiometricMode(DesktopApplication.this.bio_usage_mode);
                    DesktopApplication.this.add(DesktopApplication.this.gui_components);
                    DesktopApplication.this.setSize(450, 250);
                    DesktopApplication.this.setVisible(true);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        });
        this.addWindowListener(new WindowMenuActions(this.is_mode_debug));
    }
    
    public String getData() {
        return this.gui_components.getData();
    }
    
    public boolean isScanned() {
        return this.gui_components.isScanned();
    }
    
    public void setUseMode(final String useMode) {
        this.gui_components.setUseMode(useMode);
    }
    
    public void setBiometricMode(final String biometricMode) {
        this.gui_components.setBiometricMode(biometricMode);
    }
}
