import java.awt.Event;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CalendarPanel extends Panel
{
    protected final String[] strMONTHS;
    protected final String[] strDAYS;
    private final int BASE_YEAR = 1900;
    private final int MAX_YEAR = 150;
    private final int nDAYS = 42;
    protected Button[] m_btnDays;
    protected Choice m_chMonths;
    protected Choice m_chYears;
    protected XDate m_date;
    private Font m_fNormal;
    private Font m_fSelected;
    
    public CalendarPanel() {
        this.strMONTHS = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.strDAYS = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(7, 7));
        this.m_btnDays = new Button[42];
        this.m_chMonths = new Choice();
        this.m_chYears = new Choice();
        for (int i = 0; i < 150; ++i) {
            this.m_chYears.addItem(Integer.toString(i + 1900));
        }
        for (int j = 0; j < 12; ++j) {
            this.m_chMonths.addItem(this.strMONTHS[j]);
        }
        for (int k = 0; k < 7; ++k) {
            final Button button = new Button(this.strDAYS[k]);
            button.setFont(new Font("TimesRoman", 0, 12));
            panel2.add(button);
        }
        for (int l = 0; l < 42; ++l) {
            panel2.add(this.m_btnDays[l] = new Button(""));
        }
        panel.add(this.m_chMonths);
        panel.add(this.m_chYears);
        panel.add(new Button("<<"));
        panel.add(new Button(">>"));
        this.add("North", panel);
        this.add("Center", panel2);
        this.m_fNormal = new Font("TimesRoman", 0, 12);
        this.m_fSelected = new Font("TimesRoman", 1, 14);
        this.reset();
    }
    
    public XDate getDate() {
        return this.m_date;
    }
    
    public void reset() {
        this.m_date = new XDate();
        this.m_chMonths.select(this.m_date.getMonth());
        this.m_chYears.select(this.m_date.getYear());
        this.setDays();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (o == ">>") {
                this.m_chYears.select(this.m_chYears.getSelectedIndex() + 1);
                this.m_date.setYear(this.m_date.getYear() + 1);
                this.setDays();
                return true;
            }
            if (o != "<<") {
                this.m_date.setDate(Integer.parseInt(((Button)event.target).getLabel()));
                this.setDays();
                return true;
            }
            if (this.m_date.getYear() == 1900) {
                return true;
            }
            this.m_chYears.select(this.m_chYears.getSelectedIndex() - 1);
            this.m_date.setYear(this.m_date.getYear() - 1);
            this.setDays();
            return true;
        }
        else {
            if (event.target instanceof Choice) {
                this.m_date = new XDate(this.m_chYears.getSelectedIndex(), this.m_chMonths.getSelectedIndex(), 1);
                this.setDays();
                return true;
            }
            return false;
        }
    }
    
    protected void setDays() {
        for (int i = 0; i < 42; ++i) {
            this.m_btnDays[i].setLabel("");
        }
        int date = 1;
        final XDate xDate = new XDate(this.m_date.getYear(), this.m_date.getMonth(), date);
        final int dayOfWeek = xDate.getDayOfWeek();
        do {
            this.m_btnDays[date - 1 + dayOfWeek].setLabel(Integer.toString(date));
            this.m_btnDays[date - 1 + dayOfWeek].setFont(this.m_fNormal);
            ++date;
            xDate.setDate(date);
        } while (xDate.getDate() > 1);
        this.m_btnDays[this.m_date.getDate() - 1 + dayOfWeek].setFont(this.m_fSelected);
    }
}
