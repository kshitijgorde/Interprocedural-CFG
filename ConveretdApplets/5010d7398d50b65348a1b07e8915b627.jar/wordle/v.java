// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.Iterator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import wordle.core.c.d;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import wordle.core.fitness.OverallShape;
import wordle.core.z;
import wordle.core.c.a;
import java.util.Map;
import wordle.core.c.f;
import java.awt.Component;
import javax.swing.JOptionPane;

final class v implements Runnable
{
    private /* synthetic */ l a;
    
    v(final l a) {
        this.a = a;
    }
    
    public final void run() {
        final f a;
        final String value = String.valueOf((a = this.a.c.a()).b.e);
        String showInputDialog;
        while ((showInputDialog = JOptionPane.showInputDialog(this.a.b, "Maximum words to layout:", value)) != null && !showInputDialog.equals(value)) {
            try {
                this.a.b.a("Change Max Words", a.b.a(Integer.parseInt(showInputDialog)), a.c);
            }
            catch (NumberFormatException ex) {}
        }
    }
}
