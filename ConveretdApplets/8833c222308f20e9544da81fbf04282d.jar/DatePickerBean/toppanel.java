// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

final class toppanel extends Panel
{
    calendarpanel parent;
    minmaxpanel mmp;
    statuspanel sp;
    
    toppanel(final calendarpanel parent) {
        this.sp = new statuspanel(this);
        this.parent = parent;
        this.setLayout(new GridLayout(3, 1));
        this.add(this.sp);
        this.add(this.mmp = new minmaxpanel(this));
        this.add(this.parent.datelabel);
        this.parent.datelabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                toppanel.this.parent.setCurrent();
            }
        });
    }
    
    public void showInCalendar(final boolean b, final boolean b2) {
        this.removeAll();
        int n = 1;
        if (b) {
            ++n;
        }
        if (b2) {
            ++n;
        }
        this.setLayout(new GridLayout(n, 1));
        if (b) {
            this.add(this.sp);
        }
        if (b2) {
            this.add(this.mmp);
        }
        this.add(this.parent.datelabel);
    }
}
