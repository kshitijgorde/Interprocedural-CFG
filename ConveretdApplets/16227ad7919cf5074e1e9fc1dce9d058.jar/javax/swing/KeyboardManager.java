// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.applet.Applet;
import java.awt.Window;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

class KeyboardManager
{
    static KeyboardManager currentManager;
    Hashtable containerMap;
    Hashtable componentKeyStrokeMap;
    static /* synthetic */ Class class$javax$swing$JMenuBar;
    
    static {
        KeyboardManager.currentManager = new KeyboardManager();
    }
    
    KeyboardManager() {
        this.containerMap = new Hashtable();
        this.componentKeyStrokeMap = new Hashtable();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    void fireBinding(final JComponent component, final KeyStroke keyStroke, final KeyEvent keyEvent) {
        final JComponent.KeyboardBinding bindingForKeyStroke = component.bindingForKeyStroke(keyStroke, 2);
        if (bindingForKeyStroke != null) {
            final ActionListener action = bindingForKeyStroke.getAction();
            if (action != null) {
                if (action instanceof Action && !((Action)action).isEnabled()) {
                    return;
                }
                action.actionPerformed(new ActionEvent(component, 1001, bindingForKeyStroke.getCommand()));
                keyEvent.consume();
            }
        }
    }
    
    public boolean fireKeyboardAction(final KeyEvent keyEvent, final boolean b, final Container container) {
        if (keyEvent.isConsumed()) {
            System.out.println("Aquired pre-used event!");
            Thread.dumpStack();
        }
        KeyStroke keyStroke;
        if (keyEvent.getID() == 400) {
            keyStroke = KeyStroke.getKeyStroke(keyEvent.getKeyChar());
        }
        else {
            keyStroke = KeyStroke.getKeyStroke(keyEvent.getKeyCode(), keyEvent.getModifiers(), b ^ true);
        }
        final Hashtable<Object, Object> hashtable = this.containerMap.get(container);
        if (hashtable != null) {
            final Object value = hashtable.get(keyStroke);
            if (value != null) {
                if (value instanceof JComponent) {
                    final JComponent component = (JComponent)value;
                    if (component.isShowing() && component.isEnabled()) {
                        this.fireBinding(component, keyStroke, keyEvent);
                    }
                }
                else if (value instanceof Vector) {
                    final Enumeration<JComponent> elements = ((Vector<JComponent>)value).elements();
                    while (elements.hasMoreElements()) {
                        final JComponent component2 = elements.nextElement();
                        if (component2.isShowing() && component2.isEnabled()) {
                            this.fireBinding(component2, keyStroke, keyEvent);
                            if (keyEvent.isConsumed()) {
                                return true;
                            }
                            continue;
                        }
                    }
                }
                else {
                    System.out.println("Unexpected condition in fireKeyboardAction " + value);
                    Thread.dumpStack();
                }
            }
        }
        if (keyEvent.isConsumed()) {
            return true;
        }
        if (hashtable != null) {
            final Vector<JComponent> vector = hashtable.get((KeyboardManager.class$javax$swing$JMenuBar != null) ? KeyboardManager.class$javax$swing$JMenuBar : (KeyboardManager.class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar")));
            if (vector != null) {
                final Enumeration<JMenuBar> elements2 = vector.elements();
                while (elements2.hasMoreElements()) {
                    final JMenuBar menuBar = elements2.nextElement();
                    if (menuBar.isShowing() && menuBar.isEnabled()) {
                        this.fireBinding(menuBar, keyStroke, keyEvent);
                        if (keyEvent.isConsumed()) {
                            return true;
                        }
                        continue;
                    }
                }
            }
        }
        if (container instanceof JInternalFrame) {
            final Container topAncestor = getTopAncestor((JComponent)container);
            if (topAncestor == null) {
                return false;
            }
            this.fireKeyboardAction(keyEvent, b, topAncestor);
        }
        return keyEvent.isConsumed();
    }
    
    public static KeyboardManager getCurrentManager() {
        return KeyboardManager.currentManager;
    }
    
    private static Container getTopAncestor(final JComponent component) {
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Window || container instanceof Applet || container instanceof JInternalFrame) {
                return container;
            }
        }
        return null;
    }
    
    public void registerKeyStroke(final KeyStroke keyStroke, final JComponent component) {
        final Container topAncestor = getTopAncestor(component);
        if (topAncestor == null) {
            return;
        }
        Hashtable<Object, Object> registerNewTopContainer = this.containerMap.get(topAncestor);
        if (registerNewTopContainer == null) {
            registerNewTopContainer = (Hashtable<Object, Object>)this.registerNewTopContainer(topAncestor);
        }
        final Object value = registerNewTopContainer.get(keyStroke);
        if (value == null) {
            registerNewTopContainer.put(keyStroke, component);
        }
        else if (value instanceof Vector) {
            final Vector<JComponent> vector = (Vector<JComponent>)value;
            if (!vector.contains(component)) {
                vector.addElement(component);
            }
        }
        else if (value instanceof JComponent) {
            if (value != component) {
                final Vector<JComponent> vector2 = new Vector<JComponent>();
                vector2.addElement((JComponent)value);
                vector2.addElement(component);
                registerNewTopContainer.put(keyStroke, vector2);
            }
        }
        else {
            System.out.println("Unexpected condition in registerKeyStroke");
            Thread.dumpStack();
        }
        this.componentKeyStrokeMap.put(new ComponentKeyStrokePair(component, keyStroke), topAncestor);
    }
    
    public void registerMenuBar(final JMenuBar menuBar) {
        final Container topAncestor = getTopAncestor(menuBar);
        Hashtable<Object, Vector<?>> registerNewTopContainer = this.containerMap.get(topAncestor);
        if (registerNewTopContainer == null) {
            registerNewTopContainer = (Hashtable<Object, Vector<?>>)this.registerNewTopContainer(topAncestor);
        }
        Vector<JMenuBar> vector = registerNewTopContainer.get((KeyboardManager.class$javax$swing$JMenuBar != null) ? KeyboardManager.class$javax$swing$JMenuBar : (KeyboardManager.class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar")));
        if (vector == null) {
            vector = new Vector<JMenuBar>();
            registerNewTopContainer.put((KeyboardManager.class$javax$swing$JMenuBar != null) ? KeyboardManager.class$javax$swing$JMenuBar : (KeyboardManager.class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar")), vector);
        }
        if (!vector.contains(menuBar)) {
            vector.addElement(menuBar);
        }
    }
    
    protected Hashtable registerNewTopContainer(final Container container) {
        final Hashtable hashtable = new Hashtable();
        this.containerMap.put(container, hashtable);
        return hashtable;
    }
    
    public static void setCurrentManager(final KeyboardManager currentManager) {
        KeyboardManager.currentManager = currentManager;
    }
    
    public void unregisterKeyStroke(final KeyStroke keyStroke, final JComponent component) {
        final ComponentKeyStrokePair componentKeyStrokePair = new ComponentKeyStrokePair(component, keyStroke);
        final Object value = this.componentKeyStrokeMap.get(componentKeyStrokePair);
        if (value == null) {
            return;
        }
        final Hashtable<Object, Object> hashtable = this.containerMap.get(value);
        if (hashtable == null) {
            Thread.dumpStack();
            return;
        }
        final Object value2 = hashtable.get(keyStroke);
        if (value2 == null) {
            Thread.dumpStack();
            return;
        }
        if (value2 instanceof JComponent && value2 == component) {
            hashtable.remove(keyStroke);
        }
        else if (value2 instanceof Vector) {
            final Vector vector = (Vector)value2;
            vector.removeElement(component);
            if (vector.isEmpty()) {
                hashtable.remove(keyStroke);
            }
        }
        if (hashtable.isEmpty()) {
            this.containerMap.remove(value);
        }
        this.componentKeyStrokeMap.remove(componentKeyStrokePair);
    }
    
    public void unregisterMenuBar(final JMenuBar menuBar) {
        final Container topAncestor = getTopAncestor(menuBar);
        final Hashtable<Object, Vector> hashtable = this.containerMap.get(topAncestor);
        if (hashtable != null) {
            final Vector vector = hashtable.get((KeyboardManager.class$javax$swing$JMenuBar != null) ? KeyboardManager.class$javax$swing$JMenuBar : (KeyboardManager.class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar")));
            if (vector != null) {
                vector.removeElement(menuBar);
                if (vector.isEmpty()) {
                    hashtable.remove((KeyboardManager.class$javax$swing$JMenuBar != null) ? KeyboardManager.class$javax$swing$JMenuBar : (KeyboardManager.class$javax$swing$JMenuBar = class$("javax.swing.JMenuBar")));
                    if (hashtable.isEmpty()) {
                        this.containerMap.remove(topAncestor);
                    }
                }
            }
        }
    }
    
    class ComponentKeyStrokePair
    {
        Object component;
        Object keyStroke;
        
        public ComponentKeyStrokePair(final Object component, final Object keyStroke) {
            this.component = component;
            this.keyStroke = keyStroke;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof ComponentKeyStrokePair)) {
                return false;
            }
            final ComponentKeyStrokePair componentKeyStrokePair = (ComponentKeyStrokePair)o;
            return this.component.equals(componentKeyStrokePair.component) && this.keyStroke.equals(componentKeyStrokePair.keyStroke);
        }
        
        public int hashCode() {
            return this.component.hashCode() * this.keyStroke.hashCode();
        }
    }
}
