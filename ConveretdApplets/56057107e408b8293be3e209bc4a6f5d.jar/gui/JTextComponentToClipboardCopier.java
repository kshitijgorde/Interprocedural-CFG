// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.MouseListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import anon.util.JAPMessages;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.text.JTextComponent;
import javax.swing.JPopupMenu;

public class JTextComponentToClipboardCopier
{
    private static final String MSG_COPY_TO_CLIP;
    private static final String MSG_COPY_SELECTED_TO_CLIP;
    private JPopupMenu m_popup;
    private JTextComponent m_currentPopup;
    private MouseAdapter m_popupListener;
    static /* synthetic */ Class class$gui$JTextComponentToClipboardCopier;
    
    public JTextComponentToClipboardCopier(final boolean b) {
        this.m_popup = new JPopupMenu();
        this.m_popupListener = new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    JTextComponentToClipboardCopier.this.m_currentPopup = (JTextComponent)mouseEvent.getComponent();
                    JTextComponentToClipboardCopier.this.m_popup.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        };
        JMenuItem menuItem;
        if (b) {
            menuItem = new JMenuItem(JAPMessages.getString(JTextComponentToClipboardCopier.MSG_COPY_SELECTED_TO_CLIP));
        }
        else {
            menuItem = new JMenuItem(JAPMessages.getString(JTextComponentToClipboardCopier.MSG_COPY_TO_CLIP));
        }
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Clipboard systemClipboard = GUIUtils.getSystemClipboard();
                String s;
                if (b) {
                    s = JTextComponentToClipboardCopier.this.m_currentPopup.getSelectedText();
                }
                else {
                    s = JTextComponentToClipboardCopier.this.m_currentPopup.getText();
                }
                if (s != null) {
                    systemClipboard.setContents(new StringSelection(s), new ClipboardOwner() {
                        public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
                        }
                    });
                }
            }
        });
        this.m_popup.add(menuItem);
    }
    
    public void registerTextComponent(final JTextComponent textComponent) {
        if (textComponent != null) {
            textComponent.addMouseListener(this.m_popupListener);
        }
    }
    
    public void unregisterTextComponent(final JTextComponent textComponent) {
        if (textComponent != null) {
            textComponent.removeMouseListener(this.m_popupListener);
        }
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
        MSG_COPY_TO_CLIP = ((JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier == null) ? (JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier = class$("gui.JTextComponentToClipboardCopier")) : JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier).getName() + "_copyToClip";
        MSG_COPY_SELECTED_TO_CLIP = ((JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier == null) ? (JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier = class$("gui.JTextComponentToClipboardCopier")) : JTextComponentToClipboardCopier.class$gui$JTextComponentToClipboardCopier).getName() + "_copySelectedToClip";
    }
}
