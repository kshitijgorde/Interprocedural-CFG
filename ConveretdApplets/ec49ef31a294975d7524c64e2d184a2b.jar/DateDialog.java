import java.util.Date;
import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import astro.ATime;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class DateDialog extends Frame
{
    protected TextField tfYear;
    protected TextField tfDate;
    protected Choice choiceMonth;
    protected Button buttonOk;
    protected Button buttonCancel;
    protected Button buttonToday;
    protected OrbitViewer objectOrbit;
    
    public DateDialog(final OrbitViewer objectOrbit, final ATime aTime) {
        this.objectOrbit = objectOrbit;
        this.setLayout(new GridLayout(2, 3, 4, 4));
        this.setFont(new Font("Dialog", 0, 14));
        this.choiceMonth = new Choice();
        for (int i = 0; i < 12; ++i) {
            this.choiceMonth.addItem(ATime.getMonthAbbr(i + 1));
        }
        this.choiceMonth.select(aTime.getMonth() - 1);
        this.add(this.choiceMonth);
        this.add(this.tfDate = new TextField(new Integer(aTime.getDay()).toString(), 2));
        this.add(this.tfYear = new TextField(new Integer(aTime.getYear()).toString(), 4));
        this.add(this.buttonToday = new Button("Today"));
        this.add(this.buttonOk = new Button("OK"));
        this.add(this.buttonCancel = new Button("Cancel"));
        this.pack();
        this.setTitle("Date");
        this.setResizable(false);
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            ATime aTime = null;
            if (event.target == this.buttonOk) {
                final int intValue = Integer.valueOf(this.tfYear.getText());
                final int n = this.choiceMonth.getSelectedIndex() + 1;
                final int intValue2 = Integer.valueOf(this.tfDate.getText());
                if (1600 <= intValue && intValue <= 2199 && 1 <= n && n <= 12 && 1 <= intValue2 && intValue2 <= 31) {
                    aTime = new ATime(intValue, n, intValue2, 0.0);
                }
            }
            else {
                if (event.target == this.buttonToday) {
                    final Date date = new Date();
                    this.choiceMonth.select(date.getMonth());
                    this.tfDate.setText(Integer.toString(date.getDate()));
                    this.tfYear.setText(Integer.toString(date.getYear() + 1900));
                    return false;
                }
                if (event.target != this.buttonCancel) {
                    return false;
                }
            }
            this.dispose();
            this.objectOrbit.endDateDialog(aTime);
            return true;
        }
        return false;
    }
}
