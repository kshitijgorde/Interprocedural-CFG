import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.TextEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class const extends TextField
{
    private int Rqa;
    private int width;
    private int height;
    private boolean Sqa;
    private String text;
    
    public const(final int rqa, final boolean sqa) {
        super(rqa);
        this.Rqa = 3;
        this.width = -1;
        this.height = -1;
        this.Sqa = true;
        this.text = "";
        this.Rqa = rqa;
        this.Sqa = sqa;
        this.enableEvents(1024L);
    }
    
    private void S() {
        this.width = 28;
        try {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            String s;
            if (this.Sqa) {
                s = "0123456789";
            }
            else {
                s = "ABCYXZ";
            }
            this.width = fontMetrics.stringWidth(s);
            this.width = 6 + Math.round(this.Rqa * this.width / s.length());
        }
        catch (Exception ex) {}
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.S();
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.width == -1 || this.height == -1) {
            this.height = preferredSize.height;
            this.S();
        }
        preferredSize.width = this.width;
        preferredSize.height = this.height;
        return preferredSize;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public void processTextEvent(final TextEvent textEvent) {
        final int caretPosition = this.getCaretPosition();
        if (!this.g()) {
            final int n = this.getText().length() - this.text.length();
            this.setText(this.text);
            int caretPosition2;
            if (n == 1) {
                caretPosition2 = Math.max(caretPosition - 1, 0);
            }
            else {
                caretPosition2 = this.text.length();
            }
            this.setCaretPosition(caretPosition2);
            Toolkit.getDefaultToolkit().beep();
        }
        else {
            this.text = this.getText();
        }
    }
    
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        if (this.c(text)) {
            super.setText(text);
            this.text = text;
        }
        else {
            super.setText(text);
        }
    }
    
    protected boolean c(final String s) {
        if (s == null) {
            return false;
        }
        if (s.length() > this.Rqa) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (!this.a(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean g() {
        return this.c(this.getText());
    }
    
    protected boolean a(final char c) {
        if (this.Sqa) {
            return Character.isDigit(c);
        }
        return Character.isJavaIdentifierPart(c) || c == ' ' || c == '_' || c == '%' || c == '^' || c == '-' || c == '.' || c == ':' || c == '(' || c == ')' || c == '&' || c == '=';
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getID() == 401) {
            final char keyChar = keyEvent.getKeyChar();
            final int keyCode = keyEvent.getKeyCode();
            final int length = this.getText().length();
            if (keyCode == 9 || keyCode == 10 || keyEvent.isActionKey() || keyCode == 16 || keyCode == 17 || keyCode == 18 || keyCode == 8 || keyCode == 127 || (length < this.Rqa && this.a(keyChar))) {
                super.processKeyEvent(keyEvent);
            }
            else {
                keyEvent.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
