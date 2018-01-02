// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.lang.reflect.Method;
import javax.swing.plaf.ComponentUI;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
import java.beans.PropertyChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;
import java.util.Hashtable;

public class UIDefaults extends Hashtable
{
    private static final Object PENDING;
    private SwingPropertyChangeSupport changeSupport;
    static /* synthetic */ Class class$javax$swing$JComponent;
    
    static {
        PENDING = new String("Pending");
    }
    
    public UIDefaults() {
    }
    
    public UIDefaults(final Object[] array) {
        super(array.length / 2);
        for (int i = 0; i < array.length; i += 2) {
            super.put(array[i], array[i + 1]);
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport == null) {
            this.changeSupport = new SwingPropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        if (this.changeSupport != null) {
            this.changeSupport.firePropertyChange(s, o, o2);
        }
    }
    
    public Object get(final Object o) {
        Object o2 = super.get(o);
        if (o2 != UIDefaults.PENDING && !(o2 instanceof ActiveValue) && !(o2 instanceof LazyValue)) {
            return o2;
        }
        synchronized (this) {
            o2 = super.get(o);
            if (o2 == UIDefaults.PENDING) {
                do {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                    o2 = super.get(o);
                } while (o2 == UIDefaults.PENDING);
                // monitorexit(this)
                return o2;
            }
            if (o2 instanceof LazyValue) {
                super.put(o, UIDefaults.PENDING);
            }
            else if (!(o2 instanceof ActiveValue)) {
                // monitorexit(this)
                return o2;
            }
        }
        if (o2 instanceof LazyValue) {
            try {
                o2 = ((LazyValue)o2).createValue(this);
                return o2;
            }
            finally {
                synchronized (this) {
                    if (o2 == null) {
                        super.remove(o);
                    }
                    else {
                        super.put(o, (LazyValue)o2);
                    }
                    this.notify();
                }
            }
        }
        o2 = ((ActiveValue)o2).createValue(this);
        return o2;
    }
    
    public Border getBorder(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Border) ? ((Border)value) : null;
    }
    
    public Color getColor(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Color) ? ((Color)value) : null;
    }
    
    public Dimension getDimension(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Dimension) ? ((Dimension)value) : null;
    }
    
    public Font getFont(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Font) ? ((Font)value) : null;
    }
    
    public Icon getIcon(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Icon) ? ((Icon)value) : null;
    }
    
    public Insets getInsets(final Object o) {
        final Object value = this.get(o);
        return (value instanceof Insets) ? ((Insets)value) : null;
    }
    
    public int getInt(final Object o) {
        final Object value = this.get(o);
        return (int)((value instanceof Integer) ? value : 0);
    }
    
    public String getString(final Object o) {
        final Object value = this.get(o);
        return (value instanceof String) ? ((String)value) : null;
    }
    
    public ComponentUI getUI(final JComponent component) {
        final Object value = this.get("ClassLoader");
        final Class uiClass = this.getUIClass(component.getUIClassID(), (value != null) ? ((ClassLoader)value) : component.getClass().getClassLoader());
        Object invoke = null;
        if (uiClass == null) {
            this.getUIError("no ComponentUI class for: " + component);
        }
        else {
            try {
                Method method = (Method)this.get(uiClass);
                if (method == null) {
                    method = uiClass.getMethod("createUI", (UIDefaults.class$javax$swing$JComponent != null) ? UIDefaults.class$javax$swing$JComponent : (UIDefaults.class$javax$swing$JComponent = class$("javax.swing.JComponent")));
                    this.put(uiClass, method);
                }
                invoke = method.invoke(null, component);
            }
            catch (NoSuchMethodException ex2) {
                this.getUIError("static createUI() method not found in " + uiClass);
            }
            catch (Exception ex) {
                this.getUIError("createUI() failed for " + component + " " + ex);
            }
        }
        return (ComponentUI)invoke;
    }
    
    public Class getUIClass(final String s) {
        return this.getUIClass(s, null);
    }
    
    public Class getUIClass(final String s, final ClassLoader classLoader) {
        try {
            final String s2 = (String)this.get(s);
            Class<?> clazz = (Class<?>)this.get(s2);
            if (clazz == null) {
                if (classLoader == null) {
                    clazz = (Class<?>)SwingUtilities.loadSystemClass(s2);
                }
                else {
                    clazz = classLoader.loadClass(s2);
                }
                if (clazz != null) {
                    this.put(s2, clazz);
                }
            }
            return clazz;
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
        catch (ClassCastException ex2) {
            return null;
        }
    }
    
    protected void getUIError(final String s) {
        System.err.println("UIDefaults.getUI() failed: " + s);
        try {
            throw new Error();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public Object put(final Object o, final Object o2) {
        final Object o3 = (o2 == null) ? super.remove(o) : super.put(o, o2);
        if (o instanceof String) {
            this.firePropertyChange((String)o, o3, o2);
        }
        return o3;
    }
    
    public void putDefaults(final Object[] array) {
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i + 1];
            if (o == null) {
                super.remove(array[i]);
            }
            else {
                super.put(array[i], o);
            }
        }
        this.firePropertyChange("UIDefaults", null, null);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport != null) {
            this.changeSupport.removePropertyChangeListener(propertyChangeListener);
        }
    }
    
    public interface LazyValue
    {
        Object createValue(final UIDefaults p0);
    }
    
    public interface ActiveValue
    {
        Object createValue(final UIDefaults p0);
    }
}
