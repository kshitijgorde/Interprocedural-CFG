// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkListener;
import gui.JAPHyperlinkAdapter;
import javax.swing.JEditorPane;
import anon.util.ResourceLoader;
import gui.dialog.DialogContentPaneOptions;
import gui.dialog.DialogContentPane;
import gui.JAPDll;
import anon.util.JAPMessages;
import java.awt.Component;
import gui.dialog.JAPDialog;

public class JAPAboutNew extends JAPDialog
{
    private static final String MSG_VERSION;
    private static final String MSG_DLL_VERSION;
    static /* synthetic */ Class class$jap$JAPAboutNew;
    
    public JAPAboutNew(final Component component) {
        super(component, JAPMessages.getString(JAPAboutNew.MSG_VERSION) + ": " + "00.12.005" + "" + ((JAPDll.getDllVersion() != null) ? (" (" + JAPMessages.getString(JAPAboutNew.MSG_DLL_VERSION) + ": " + JAPDll.getDllVersion() + ")") : ""));
        final DialogContentPane dialogContentPane = new DialogContentPane(this, (DialogContentPane.Layout)null, new DialogContentPaneOptions(-1));
        dialogContentPane.setDefaultButtonOperation(2);
        final String s = new String(ResourceLoader.loadResource(JAPMessages.getString("htmlfileAbout")));
        final JEditorPane editorPane = new JEditorPane();
        final JEditorPane editorPane2 = new JEditorPane();
        editorPane2.setEditable(false);
        editorPane2.addHyperlinkListener(new JAPHyperlinkAdapter());
        editorPane2.setDoubleBuffered(false);
        this.setResizable(false);
        editorPane2.setContentType("text/html");
        editorPane2.setText(s.trim());
        final JScrollPane scrollPane = new JScrollPane(editorPane2, 20, 31);
        dialogContentPane.getContentPane().setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        dialogContentPane.getContentPane().add(scrollPane, gridBagConstraints);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        dialogContentPane.updateDialog();
        this.pack();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_VERSION = ((JAPAboutNew.class$jap$JAPAboutNew == null) ? (JAPAboutNew.class$jap$JAPAboutNew = class$("jap.JAPAboutNew")) : JAPAboutNew.class$jap$JAPAboutNew).getName() + "_version";
        MSG_DLL_VERSION = ((JAPAboutNew.class$jap$JAPAboutNew == null) ? (JAPAboutNew.class$jap$JAPAboutNew = class$("jap.JAPAboutNew")) : JAPAboutNew.class$jap$JAPAboutNew).getName() + "_dllVersion";
    }
}
