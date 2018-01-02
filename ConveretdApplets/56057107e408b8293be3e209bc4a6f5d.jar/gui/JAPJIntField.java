// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import anon.util.JAPMessages;
import anon.util.ClassUtil;
import javax.swing.JTextField;

public final class JAPJIntField extends JTextField
{
    public static final int NO_MAXIMUM_BOUND = -1;
    public static final int ALLOW_ZEROS_NONE = 0;
    public static final int ALLOW_ZEROS_ONE = 1;
    public static final int ALLOW_ZEROS_UNTIL_BOUND = 2;
    private static final String MSG_NO_VALID_INTEGER;
    private IIntFieldBounds m_bounds;
    private boolean b_bAutoTransferFocus;
    static /* synthetic */ Class class$gui$JAPJIntField;
    
    public JAPJIntField() {
        this(-1, false);
    }
    
    public JAPJIntField(final int n) {
        this(n, false);
    }
    
    public JAPJIntField(final int n, final boolean b) {
        this(new DefaultBounds(n), b);
    }
    
    public JAPJIntField(final IIntFieldBounds intFieldBounds) {
        this(intFieldBounds, false);
    }
    
    public JAPJIntField(final IIntFieldBounds bounds, final boolean b_bAutoTransferFocus) {
        super(parseNumberOfDigits(bounds.getMaximum()));
        if (bounds.getAllowZeros() < 0 || bounds.getAllowZeros() > 2) {
            throw new IllegalArgumentException("getAllowZeros() returned an illegal value: " + bounds.getAllowZeros());
        }
        this.m_bounds = bounds;
        this.b_bAutoTransferFocus = b_bAutoTransferFocus;
    }
    
    public void setInt(final int n) {
        this.setText(Integer.toString(n));
    }
    
    public int getInt() throws NumberFormatException {
        final Object[] array = new Object[2];
        if (this.getName() == null || this.getName().trim().length() == 0) {
            array[1] = ClassUtil.getShortClassName(this.getClass());
        }
        else {
            array[1] = this.getName();
        }
        try {
            final int int1 = Integer.parseInt(this.getText());
            if (int1 >= 0 && (this.m_bounds.getAllowZeros() != 0 || int1 != 0) && (this.m_bounds.getMaximum() < 0 || int1 <= this.m_bounds.getMaximum())) {
                return int1;
            }
            array[0] = new Integer(int1);
        }
        catch (NumberFormatException ex) {
            array[0] = this.getText();
        }
        throw new NumberFormatException(JAPMessages.getString(JAPJIntField.MSG_NO_VALID_INTEGER, array));
    }
    
    public void updateBounds() {
        try {
            if (this.getInt() > this.m_bounds.getMaximum()) {
                this.setInt(this.m_bounds.getMaximum());
            }
            if (this.m_bounds.getAllowZeros() == 0 && this.getInt() == 0) {
                this.setInt(1);
            }
        }
        catch (NumberFormatException ex) {
            if (this.m_bounds.getAllowZeros() > 0) {
                this.setInt(0);
            }
            else {
                this.setInt(1);
            }
        }
    }
    
    protected final Document createDefaultModel() {
        return new IntDocument();
    }
    
    private static int parseNumberOfDigits(int i) {
        int n = 0;
        while (i > 0) {
            ++n;
            i /= 10;
        }
        if (n == 0) {
            n = 1;
        }
        return n;
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
        MSG_NO_VALID_INTEGER = ((JAPJIntField.class$gui$JAPJIntField == null) ? (JAPJIntField.class$gui$JAPJIntField = class$("gui.JAPJIntField")) : JAPJIntField.class$gui$JAPJIntField).getName() + "_noValidInteger";
    }
    
    public abstract static class AbstractIntFieldBounds implements IIntFieldBounds
    {
        private int m_maxValue;
        
        public AbstractIntFieldBounds(final int maxValue) {
            this.m_maxValue = maxValue;
        }
        
        public final int getMaximum() {
            return this.m_maxValue;
        }
        
        public abstract int getAllowZeros();
    }
    
    public static final class IntFieldUnlimitedZerosBounds extends AbstractIntFieldBounds
    {
        public IntFieldUnlimitedZerosBounds(final int n) {
            super(n);
        }
        
        public int getAllowZeros() {
            return 2;
        }
    }
    
    public static final class IntFieldWithoutZeroBounds extends AbstractIntFieldBounds
    {
        public IntFieldWithoutZeroBounds(final int n) {
            super(n);
        }
        
        public int getAllowZeros() {
            return 0;
        }
    }
    
    private static final class DefaultBounds extends AbstractIntFieldBounds
    {
        public DefaultBounds(final int n) {
            super(n);
        }
        
        public int getAllowZeros() {
            return 1;
        }
    }
    
    private final class IntDocument extends PlainDocument
    {
        public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
            if (s == null || s.trim().length() == 0) {
                return;
            }
            int int1;
            try {
                int1 = Integer.parseInt(this.getText(0, this.getLength()) + s);
            }
            catch (NumberFormatException ex) {
                return;
            }
            if (JAPJIntField.this.m_bounds.getAllowZeros() < 2) {
                if (int1 < 10 && n > 0) {
                    return;
                }
            }
            else if (JAPJIntField.this.m_bounds.getMaximum() >= 0 && parseNumberOfDigits(JAPJIntField.this.m_bounds.getMaximum()) < n + 1) {
                return;
            }
            if ((JAPJIntField.this.m_bounds.getMaximum() < 0 || int1 <= JAPJIntField.this.m_bounds.getMaximum()) && (JAPJIntField.this.m_bounds.getAllowZeros() > 0 || (JAPJIntField.this.m_bounds.getAllowZeros() == 0 && int1 > 0))) {
                super.insertString(n, s, set);
            }
            if (JAPJIntField.this.m_bounds.getMaximum() >= 0 && JAPJIntField.this.b_bAutoTransferFocus && this.getLength() > 0 && n + 1 == parseNumberOfDigits(JAPJIntField.this.m_bounds.getMaximum())) {
                JAPJIntField.this.transferFocus();
            }
        }
    }
    
    public interface IIntFieldBounds
    {
        int getAllowZeros();
        
        int getMaximum();
    }
}
