// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.Action;
import javax.swing.AbstractAction;

public abstract class TextAction extends AbstractAction
{
    public TextAction(final String s) {
        super(s);
    }
    
    public static final Action[] augmentList(final Action[] array, final Action[] array2) {
        final Hashtable hashtable = new Hashtable<String, Action>();
        for (int i = 0; i < array.length; ++i) {
            final Action action = array[i];
            final String s = (String)action.getValue("Name");
            hashtable.put((s != null) ? s : "", action);
        }
        for (int j = 0; j < array2.length; ++j) {
            final Action action2 = array2[j];
            final String s2 = (String)action2.getValue("Name");
            hashtable.put((s2 != null) ? s2 : "", action2);
        }
        final Action[] array3 = new Action[hashtable.size()];
        int n = 0;
        final Enumeration<Action> elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            array3[n++] = elements.nextElement();
        }
        return array3;
    }
    
    protected final JTextComponent getFocusedComponent() {
        return JTextComponent.getFocusedComponent();
    }
    
    protected final JTextComponent getTextComponent(final ActionEvent actionEvent) {
        if (actionEvent != null) {
            final Object source = actionEvent.getSource();
            if (source instanceof JTextComponent) {
                return (JTextComponent)source;
            }
        }
        return this.getFocusedComponent();
    }
}
