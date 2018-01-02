// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;

final class minmaxpanel extends Label
{
    toppanel parent;
    private Color col;
    int lang;
    
    minmaxpanel(final toppanel parent) {
        this.col = Color.lightGray;
        this.parent = parent;
        this.setAlignment(1);
        this.setFont(new Font("SansSerif", 0, 10));
        this.lang = DPLanguage.LANG_ENGLISH;
    }
    
    public void paint(final Graphics graphics) {
        this.refresh();
    }
    
    public void refresh() {
        this.setForeground(this.col);
        this.setBackground(this.parent.parent.getBackground());
        this.setText(String.valueOf(DPLanguage.getDateRangeTranslation(this.lang)) + " : " + this.parent.parent.getMinMaxRange());
    }
    
    void setColor(final Color col) {
        this.col = col;
    }
}
