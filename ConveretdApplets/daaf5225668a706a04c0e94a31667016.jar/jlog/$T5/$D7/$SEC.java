// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Button;
import java.util.ResourceBundle;
import java.awt.Container;
import jlog.$H4;

public class $SEC implements $H4
{
    public static void setLanguage(final Container container, final ResourceBundle resourceBundle) {
        final Component[] components = container.getComponents();
        int length = components.length;
        while (length-- != 0) {
            final Component component = components[length];
            try {
                if (component instanceof Button) {
                    final Button button = (Button)component;
                    final String trim = button.getActionCommand().trim();
                    if (trim.length() == 0) {
                        continue;
                    }
                    String string = trim;
                    try {
                        string = resourceBundle.getString(trim);
                    }
                    catch (Exception ex) {}
                    button.setLabel(string);
                    button.setActionCommand(trim);
                }
                else if (component instanceof Label) {
                    final Label label = (Label)component;
                    String text = label.getText().trim();
                    if (text.length() == 0) {
                        continue;
                    }
                    try {
                        text = resourceBundle.getString(text);
                    }
                    catch (Exception ex2) {}
                    label.setText(text);
                }
                else if (component instanceof Checkbox) {
                    final Checkbox checkbox = (Checkbox)component;
                    String label2 = checkbox.getLabel().trim();
                    if (label2.length() == 0) {
                        continue;
                    }
                    try {
                        label2 = resourceBundle.getString(label2);
                    }
                    catch (Exception ex3) {}
                    checkbox.setLabel(label2);
                }
                else {
                    if (!(component instanceof Container)) {
                        continue;
                    }
                    setLanguage((Container)component, resourceBundle);
                }
            }
            catch (Exception ex4) {}
        }
    }
}
