// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.JComponent;
import javax.swing.InputVerifier;
import javax.swing.JFormattedTextField;
import java.text.DecimalFormat;

public class JFormattedNumField extends JFormattedSelectField
{
    protected double vDoubleValue;
    protected BoundVerifier pVerifier;
    static /* synthetic */ Class class$blackmagic$swing$JFormattedNumField;
    
    public JFormattedNumField(final String s, final double double1, final int columns) {
        super(isPercentFormat(s) ? new PercentFormatter(new DecimalFormat(s)) : new DecimalFormatter(new DecimalFormat(s)));
        this.setInputVerifier(this.pVerifier = new BoundVerifier());
        this.setDouble(double1);
        this.setColumns(columns);
    }
    
    public void setDouble(final double n) {
        this.setValue(new Double(n));
    }
    
    public double getDouble() throws NullPointerException {
        double double1;
        try {
            double1 = DoubleParser.toDouble(this.getText());
        }
        catch (NullPointerException ex) {
            return 0.0;
        }
        return double1;
    }
    
    public void setLowerBound(final double lowerBound) {
        this.pVerifier.setLowerBound(lowerBound);
    }
    
    public void setUpperBound(final double upperBound) {
        this.pVerifier.setUpperBound(upperBound);
    }
    
    public void setBounds(final double n, final double n2) {
        this.pVerifier.setBounds(n, n2);
    }
    
    public static final boolean isPercentFormat(final String s) {
        final String trim = s.trim();
        return trim.charAt(trim.length() - 1) == '%';
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class BoundVerifier extends InputVerifier
    {
        protected double vLowerBound;
        protected double vUpperBound;
        
        public BoundVerifier() {
            this.setBounds(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        
        public BoundVerifier(final double n, final double n2) {
            this.setBounds(n, n2);
        }
        
        public boolean verify(final JComponent component) {
            assert component instanceof JFormattedNumField;
            final JFormattedNumField formattedNumField = (JFormattedNumField)component;
            double double1;
            try {
                double1 = DoubleParser.toDouble(formattedNumField.getText());
            }
            catch (NullPointerException ex) {
                return false;
            }
            return this.verifyBounds(double1);
        }
        
        protected boolean verifyBounds(final double n) {
            return n >= this.vLowerBound && n <= this.vUpperBound;
        }
        
        public boolean shouldYieldFocus(final JComponent component) {
            final boolean verify = this.verify(component);
            if (!verify) {
                ((JFormattedNumField)component).invalidEdit();
            }
            return verify;
        }
        
        public void setLowerBound(final double vLowerBound) {
            this.vLowerBound = vLowerBound;
        }
        
        public void setUpperBound(final double vUpperBound) {
            this.vUpperBound = vUpperBound;
        }
        
        public void setBounds(final double lowerBound, final double upperBound) {
            this.setLowerBound(lowerBound);
            this.setUpperBound(upperBound);
        }
        
        static {
            $assertionsDisabled = !((JFormattedNumField.class$blackmagic$swing$JFormattedNumField == null) ? (JFormattedNumField.class$blackmagic$swing$JFormattedNumField = JFormattedNumField.class$("blackmagic.swing.JFormattedNumField")) : JFormattedNumField.class$blackmagic$swing$JFormattedNumField).desiredAssertionStatus();
        }
    }
}
