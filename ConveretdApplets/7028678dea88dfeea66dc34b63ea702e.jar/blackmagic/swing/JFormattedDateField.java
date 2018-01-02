// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.text.ParseException;
import javax.swing.JComponent;
import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;
import javax.swing.InputVerifier;
import javax.swing.JFormattedTextField;
import java.util.Date;

public class JFormattedDateField extends JFormattedSelectField
{
    protected Date vDateValue;
    protected BoundVerifier pVerifier;
    static /* synthetic */ Class class$blackmagic$swing$JFormattedDateField;
    
    public JFormattedDateField(final String s, final int n) {
        super(getDateFormatter(s));
        this.initialise(s, new Date(), n);
    }
    
    public JFormattedDateField(final String s, final Date date, final int n) {
        super(getDateFormatter(s));
        this.initialise(s, date, n);
    }
    
    private void initialise(final String s, final Date value, final int columns) {
        assert isDateFormat(s) : "Format String parameter is not a date format";
        this.setInputVerifier(this.pVerifier = new BoundVerifier());
        this.setValue(value);
        this.setColumns(columns);
    }
    
    public Date getDate() {
        return (Date)this.getValue();
    }
    
    public void setDate(final Date value) {
        this.setValue(value);
    }
    
    private static boolean isDateFormat(final String s) {
        return true;
    }
    
    private static DateFormatter getDateFormatter(final String s) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
        simpleDateFormat.set2DigitYearStart(new Date(0L));
        return new StrictDateFormatter(simpleDateFormat);
    }
    
    public void setLowerBound(final Date lowerBound) {
        this.pVerifier.setLowerBound(lowerBound);
    }
    
    public void setUpperBound(final Date upperBound) {
        this.pVerifier.setUpperBound(upperBound);
    }
    
    public void setBounds(final Date date, final Date date2) {
        this.pVerifier.setBounds(date, date2);
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
        $assertionsDisabled = !((JFormattedDateField.class$blackmagic$swing$JFormattedDateField == null) ? (JFormattedDateField.class$blackmagic$swing$JFormattedDateField = class$("blackmagic.swing.JFormattedDateField")) : JFormattedDateField.class$blackmagic$swing$JFormattedDateField).desiredAssertionStatus();
    }
    
    class BoundVerifier extends InputVerifier
    {
        protected Date vLowerBound;
        protected Date vUpperBound;
        
        public BoundVerifier() {
            this.setBounds(new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
        }
        
        public BoundVerifier(final Date date, final Date date2) {
            this.setBounds(date, date2);
        }
        
        public boolean verify(final JComponent component) {
            assert component instanceof JFormattedDateField;
            final JFormattedDateField formattedDateField = (JFormattedDateField)component;
            Date parse;
            try {
                parse = ((SimpleDateFormat)((DateFormatter)formattedDateField.getFormatter()).getFormat()).parse(formattedDateField.getText());
            }
            catch (ParseException ex) {
                return false;
            }
            return this.verifyBounds(parse);
        }
        
        protected boolean verifyBounds(final Date date) {
            return !date.before(this.vLowerBound) && !date.after(this.vUpperBound);
        }
        
        public boolean shouldYieldFocus(final JComponent component) {
            final boolean verify = this.verify(component);
            if (!verify) {
                ((JFormattedDateField)component).invalidEdit();
            }
            return verify;
        }
        
        public void setLowerBound(final Date vLowerBound) {
            this.vLowerBound = vLowerBound;
        }
        
        public void setUpperBound(final Date vUpperBound) {
            this.vUpperBound = vUpperBound;
        }
        
        public void setBounds(final Date lowerBound, final Date upperBound) {
            this.setLowerBound(lowerBound);
            this.setUpperBound(upperBound);
        }
        
        static {
            $assertionsDisabled = !((JFormattedDateField.class$blackmagic$swing$JFormattedDateField == null) ? (JFormattedDateField.class$blackmagic$swing$JFormattedDateField = JFormattedDateField.class$("blackmagic.swing.JFormattedDateField")) : JFormattedDateField.class$blackmagic$swing$JFormattedDateField).desiredAssertionStatus();
        }
    }
}
