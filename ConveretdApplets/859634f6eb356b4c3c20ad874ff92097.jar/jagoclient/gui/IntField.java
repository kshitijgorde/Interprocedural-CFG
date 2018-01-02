// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

public class IntField extends TextFieldAction
{
    public IntField(final DoActionListener doActionListener, final String s, final int n) {
        super(doActionListener, s, String.valueOf(n));
    }
    
    public IntField(final DoActionListener doActionListener, final String s, final int n, final int n2) {
        super(doActionListener, s, String.valueOf(n), n2);
    }
    
    public int value() {
        try {
            return Integer.parseInt(this.getText());
        }
        catch (NumberFormatException ex) {
            this.setText(String.valueOf(0));
            return 0;
        }
    }
    
    public int value(final int n, final int n2) {
        int int1;
        try {
            int1 = Integer.parseInt(this.getText());
        }
        catch (NumberFormatException ex) {
            this.setText(String.valueOf(n));
            return n;
        }
        if (int1 < n) {
            int1 = n;
            this.setText(String.valueOf(n));
        }
        if (int1 > n2) {
            int1 = n2;
            this.setText(String.valueOf(n2));
        }
        return int1;
    }
    
    public void set(final int n) {
        this.setText(String.valueOf(n));
    }
    
    public boolean valid() {
        try {
            Integer.parseInt(this.getText());
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
