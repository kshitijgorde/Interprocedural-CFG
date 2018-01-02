// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.AbstractAction;

public class HelpAction extends AbstractAction
{
    private Component m_parent;
    
    public HelpAction(final String name, final String description, final int shortcurt, final Component parent) {
        this.m_parent = null;
        this.putValue("Name", name);
        this.putValue("ShortDescription", description);
        this.putValue("MnemonicKey", new Integer(shortcurt));
        this.m_parent = parent;
    }
    
    public void actionPerformed(final ActionEvent e) {
        JOptionPane.showMessageDialog(this.m_parent, "ABCynth - The abc4j demonstrator\n2003-2008 by Lionel GUEGANTON\nFor feedback, help or whatever...\nlionel.gueganton@libertysurf.fr\nhttp://abc4j.googlecode.com\nhttp://groups.google.com/group/abc4j", "About ABCynth", 1);
    }
}
