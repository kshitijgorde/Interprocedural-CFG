// 
// Decompiled by Procyon v0.5.30
// 

package en.products;

import en.JavaFX;

public class AppletPair
{
    public static JavaFX effect;
    public static JavaFXGUI gui;
    
    public static final void setEffect(final JavaFX effect) {
        AppletPair.effect = effect;
        if (AppletPair.gui != null) {
            AppletPair.gui.effectFound(AppletPair.effect);
        }
    }
    
    public static final void setGUI(final JavaFXGUI gui) {
        AppletPair.gui = gui;
        if (AppletPair.effect != null) {
            AppletPair.gui.effectFound(AppletPair.effect);
        }
    }
}
