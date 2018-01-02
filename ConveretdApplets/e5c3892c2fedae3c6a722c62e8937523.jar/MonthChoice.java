import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public class MonthChoice extends JComboBox
{
    private String[] monthNames;
    private int firstMonth;
    private int lastMonth;
    
    MonthChoice(final int n, final int n2) {
        this.monthNames = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.setEditable(false);
        if (n < 1 || n2 > 12 || n > n2) {
            throw new IllegalArgumentException("Invalid Month to MonthChoice constructor");
        }
        this.configure(n, n2, n);
    }
    
    MonthChoice() {
        this(1, 12);
    }
    
    void configure(final int firstMonth, final int lastMonth, final int n) {
        if (firstMonth != this.firstMonth || lastMonth != this.lastMonth) {
            this.firstMonth = firstMonth;
            this.lastMonth = lastMonth;
            this.removeAllItems();
            if (firstMonth <= lastMonth) {
                for (int i = firstMonth - 1; i < lastMonth; ++i) {
                    this.addItem(this.monthNames[i]);
                }
            }
            else {
                for (int j = firstMonth - 1; j < 12; ++j) {
                    this.addItem(this.monthNames[j]);
                }
                for (int k = 0; k < lastMonth; ++k) {
                    this.addItem(this.monthNames[k]);
                }
            }
        }
        int selectedIndex = n - firstMonth;
        if (selectedIndex < 0) {
            selectedIndex += 12;
        }
        this.setSelectedIndex(selectedIndex);
    }
    
    int getSelectedMonth() {
        int n = this.getSelectedIndex() + this.firstMonth;
        if (n > 12) {
            n -= 12;
        }
        return n;
    }
}
