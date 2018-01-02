// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;
import anon.util.JAPMessages;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class JapCouponField extends JTextField
{
    private static final long serialVersionUID = 1L;
    private static final int NR_OF_CHARACTERS = 4;
    private static final char[] ACCEPTED_CHARS;
    private JapCouponField m_nextCouponField;
    private static final String MSG_INSERT_FROM_CLIP;
    static /* synthetic */ Class class$gui$JapCouponField;
    
    public JapCouponField() {
        super(4);
        final JPopupMenu popupMenu = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(JapCouponField.MSG_INSERT_FROM_CLIP));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (GUIUtils.isMouseButton(mouseEvent, 8) || GUIUtils.isMouseButton(mouseEvent, 4)) {
                    popupMenu.show(JapCouponField.this, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Transferable contents = GUIUtils.getSystemClipboard().getContents(this);
                if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        JapCouponField.this.setText((String)contents.getTransferData(DataFlavor.stringFlavor));
                    }
                    catch (Exception ex) {}
                }
            }
        });
        popupMenu.add(menuItem);
    }
    
    public void setNextCouponField(final JapCouponField nextCouponField) {
        this.m_nextCouponField = nextCouponField;
    }
    
    protected final Document createDefaultModel() {
        return new CouponDocument();
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
        ACCEPTED_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        MSG_INSERT_FROM_CLIP = ((JapCouponField.class$gui$JapCouponField == null) ? (JapCouponField.class$gui$JapCouponField = class$("gui.JapCouponField")) : JapCouponField.class$gui$JapCouponField).getName() + "_insertFromClip";
    }
    
    private final class CouponDocument extends PlainDocument
    {
        private static final long serialVersionUID = 1L;
        
        public void insertString(final int n, String s, final AttributeSet set) throws BadLocationException {
            s = s.toUpperCase();
            final char[] charArray = s.toCharArray();
            final char[] array = new char[charArray.length];
            int n2 = 0;
            for (int i = 0; i < charArray.length; ++i) {
                if (this.isCharacterAccepted(charArray[i])) {
                    array[n2] = charArray[i];
                    ++n2;
                }
            }
            s = new String(array, 0, n2);
            if (s.length() + this.getLength() > 4) {
                if (4 <= s.length()) {
                    if (JapCouponField.this.m_nextCouponField != null && 4 < s.length()) {
                        JapCouponField.this.m_nextCouponField.setText(s.substring(4, s.length()));
                    }
                    s = s.substring(0, 4);
                    super.insertString(0, s, set);
                }
                else if (n + s.length() <= 4) {
                    super.writeLock();
                    super.remove(n, s.length());
                    super.insertString(n, s, set);
                    super.writeUnlock();
                }
                else if (n < 4) {
                    super.writeLock();
                    super.remove(n, 4 - n);
                    super.insertString(n, s.substring(0, 4 - n), set);
                    super.writeUnlock();
                }
            }
            else {
                super.insertString(n, s, set);
            }
            if (this.getLength() >= 4) {
                if (this.getLength() > 4) {
                    super.remove(4, this.getLength() - 4);
                }
                if (JapCouponField.this.getCaretPosition() >= 4) {
                    JapCouponField.this.setCaretPosition(0);
                    JapCouponField.this.transferFocus();
                }
            }
        }
        
        private boolean isCharacterAccepted(final char c) {
            for (int i = 0; i < JapCouponField.ACCEPTED_CHARS.length; ++i) {
                if (c == JapCouponField.ACCEPTED_CHARS[i]) {
                    return true;
                }
            }
            return false;
        }
    }
}
