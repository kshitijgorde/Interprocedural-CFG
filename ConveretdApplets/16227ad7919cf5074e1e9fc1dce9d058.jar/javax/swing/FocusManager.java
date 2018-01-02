// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.KeyEvent;
import java.awt.Component;

public abstract class FocusManager
{
    public static final String FOCUS_MANAGER_CLASS_PROPERTY = "FocusManagerClassName";
    private static final Object focusManagerKey;
    static /* synthetic */ Class class$javax$swing$FocusManager;
    
    static {
        focusManagerKey = ((FocusManager.class$javax$swing$FocusManager != null) ? FocusManager.class$javax$swing$FocusManager : (FocusManager.class$javax$swing$FocusManager = class$("javax.swing.FocusManager")));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public static void disableSwingFocusManager() {
        setCurrentManager(new DisabledFocusManager());
    }
    
    public abstract void focusNextComponent(final Component p0);
    
    public abstract void focusPreviousComponent(final Component p0);
    
    public static FocusManager getCurrentManager() {
        FocusManager focusManager = (FocusManager)SwingUtilities.appContextGet(FocusManager.focusManagerKey);
        if (focusManager == null) {
            final String string = UIManager.getString("FocusManagerClassName");
            try {
                final Class<?> forName = Class.forName(string);
                if (forName != null) {
                    focusManager = (FocusManager)forName.newInstance();
                }
            }
            catch (ClassNotFoundException ex) {
                System.out.println("Cannot find class " + string + " " + ex);
                focusManager = null;
            }
            catch (InstantiationException ex2) {
                System.out.println("Cannot instantiate class " + string + " " + ex2);
                focusManager = null;
            }
            catch (IllegalAccessException ex3) {
                System.out.println("Cannot access class " + string + " " + ex3);
                focusManager = null;
            }
            if (focusManager == null) {
                focusManager = new DefaultFocusManager();
            }
            SwingUtilities.appContextPut(FocusManager.focusManagerKey, focusManager);
        }
        return focusManager;
    }
    
    public static boolean isFocusManagerEnabled() {
        return getCurrentManager() instanceof DisabledFocusManager ^ true;
    }
    
    public abstract void processKeyEvent(final Component p0, final KeyEvent p1);
    
    public static void setCurrentManager(final FocusManager focusManager) {
        if (focusManager != null) {
            SwingUtilities.appContextPut(FocusManager.focusManagerKey, focusManager);
        }
        else {
            SwingUtilities.appContextRemove(FocusManager.focusManagerKey);
        }
    }
    
    static class DisabledFocusManager extends FocusManager
    {
        public void focusNextComponent(final Component component) {
        }
        
        public void focusPreviousComponent(final Component component) {
        }
        
        public void processKeyEvent(final Component component, final KeyEvent keyEvent) {
        }
    }
}
