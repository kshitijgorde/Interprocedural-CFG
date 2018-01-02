// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

public class JFormattedPercentField extends JFormattedNumField
{
    static /* synthetic */ Class class$blackmagic$swing$JFormattedPercentField;
    
    public JFormattedPercentField(final String s, final double n, final int n2) {
        super(s, n, n2);
        assert JFormattedNumField.isPercentFormat(s) : "Format String parameter is not a percentage format";
    }
    
    public String getText() {
        String text = super.getText();
        if (text.charAt(text.length() - 1) != '%') {
            text += '%';
            this.setText(text);
        }
        return text;
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
        $assertionsDisabled = !((JFormattedPercentField.class$blackmagic$swing$JFormattedPercentField == null) ? (JFormattedPercentField.class$blackmagic$swing$JFormattedPercentField = class$("blackmagic.swing.JFormattedPercentField")) : JFormattedPercentField.class$blackmagic$swing$JFormattedPercentField).desiredAssertionStatus();
    }
}
