// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.Segment;
import javax.accessibility.AccessibleContext;
import javax.swing.text.Document;

public class JPasswordField extends JTextField
{
    private static final String uiClassID = "PasswordFieldUI";
    private char echoChar;
    
    public JPasswordField() {
        this(null, null, 0);
    }
    
    public JPasswordField(final int n) {
        this(null, null, n);
    }
    
    public JPasswordField(final String s) {
        this(null, s, 0);
    }
    
    public JPasswordField(final String s, final int n) {
        this(null, s, n);
    }
    
    public JPasswordField(final Document document, final String s, final int n) {
        super(document, s, n);
        this.echoChar = '*';
    }
    
    public void copy() {
        this.getToolkit().beep();
    }
    
    public void cut() {
        this.getToolkit().beep();
    }
    
    public boolean echoCharIsSet() {
        return this.echoChar != '\0';
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJPasswordField();
        }
        return super.accessibleContext;
    }
    
    public char getEchoChar() {
        return this.echoChar;
    }
    
    public char[] getPassword() {
        final Document document = this.getDocument();
        final Segment segment = new Segment();
        try {
            document.getText(0, document.getLength(), segment);
        }
        catch (BadLocationException ex) {
            return null;
        }
        final char[] array = new char[segment.count];
        System.arraycopy(segment.array, segment.offset, array, 0, segment.count);
        return array;
    }
    
    public String getText() {
        return super.getText();
    }
    
    public String getText(final int n, final int n2) throws BadLocationException {
        return super.getText(n, n2);
    }
    
    public String getUIClassID() {
        return "PasswordFieldUI";
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",echoChar=" + this.echoChar;
    }
    
    public void setEchoChar(final char echoChar) {
        this.echoChar = echoChar;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("PasswordFieldUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJPasswordField extends AccessibleJTextField
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PASSWORD_TEXT;
        }
    }
}
