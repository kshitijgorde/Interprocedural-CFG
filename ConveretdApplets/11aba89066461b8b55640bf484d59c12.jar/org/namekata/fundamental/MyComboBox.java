// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class MyComboBox extends JComboBox
{
    private int first;
    private int number;
    
    private void init(final int first, final int number) {
        this.first = ((first >= 0) ? first : 0);
        this.number = ((number > 0) ? number : 1);
        final Object[] items = new Integer[number];
        for (int i = 0; i < number; ++i) {
            items[i] = new Integer(i + first);
        }
        final DefaultComboBoxModel dcbModel = new DefaultComboBoxModel((E[])items);
        this.setModel(dcbModel);
    }
    
    public MyComboBox(final int first, final int number) {
        this.first = 0;
        this.number = 1;
        this.init(first, number);
    }
    
    public MyComboBox() {
        this.first = 0;
        this.number = 1;
        this.init(0, 10);
    }
    
    public void setRange(final int first, final int number) {
        final int oldindex = this.getSelectedIndex();
        this.init(first, number);
        if (oldindex == -1) {
            return;
        }
        if (oldindex >= number) {
            this.setSelectedIndex(number - 1);
            return;
        }
        this.setSelectedIndex(oldindex);
    }
}
