import java.awt.Container;
import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.applet.AppletContext;
import java.awt.Label;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BiorithmApp extends Applet
{
    private BiorithmGraph m_BiorithmGraph;
    private CalendarPanel m_calendar;
    private Panel m_panBiorithm;
    private CardLayout m_clLayout;
    private XDate m_dateStart;
    private XDate m_dateBirth;
    private Button m_btnBirthDate;
    private Button m_btnStartDate;
    private Button m_btnBiorithm;
    private Button m_btnOK;
    private Label m_lbBirthDate;
    private Label m_lbStartDate;
    private static final int NULL_STATE = 0;
    private static final int BIRTH_DATE = 1;
    private static final int START_DATE = 2;
    private int m_nState;
    
    public void init() {
        SplashPanel splashPanel;
        try {
            splashPanel = new SplashPanel("Biorhythm", 50, "1.1.1", this.getAppletContext());
        }
        catch (Exception ex) {
            splashPanel = new SplashPanel("Biorhythm", 50, "1.1.1", null);
        }
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 3, 10, 0));
        final Font font = new Font("TimesRoman", 1, 16);
        (this.m_lbBirthDate = new Label("Birth: ######", 1)).setFont(font);
        this.m_lbBirthDate.setForeground(Color.white);
        (this.m_lbStartDate = new Label("Start: ######", 1)).setFont(font);
        this.m_lbStartDate.setForeground(Color.white);
        final Label label = new Label("Biorhythm", 1);
        label.setFont(font);
        label.setForeground(Color.white);
        panel.add(this.m_lbBirthDate);
        panel.add(this.m_lbStartDate);
        panel.add(label);
        this.m_btnBirthDate = new Button("Set Birth Date");
        this.m_btnStartDate = new Button("Set Start Date");
        this.m_btnBiorithm = new Button("Biorhythm");
        panel.add(this.m_btnBirthDate);
        panel.add(this.m_btnStartDate);
        panel.add(this.m_btnBiorithm);
        this.m_btnBiorithm.disable();
        panel.setBackground(splashPanel.getBackground().darker());
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add("Center", splashPanel);
        panel2.add("South", panel);
        this.m_calendar = new CalendarPanel();
        this.m_btnOK = new Button("OK");
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("Center", this.m_calendar);
        panel3.add("South", this.m_btnOK);
        this.m_BiorithmGraph = new BiorithmGraph();
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout());
        panel4.add("Center", this.m_BiorithmGraph);
        panel4.add("South", new Button("Reset"));
        this.m_panBiorithm = new Panel();
        this.m_clLayout = new CardLayout();
        this.m_panBiorithm.setLayout(this.m_clLayout);
        this.m_panBiorithm.add("Buttons", panel2);
        this.m_panBiorithm.add("Calendar", panel3);
        this.m_panBiorithm.add("Graph", panel4);
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(splashPanel.getBackground().darker());
        this.add(this.m_panBiorithm);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (event.target == this.m_btnBirthDate) {
                this.m_nState = 1;
                this.m_clLayout.show(this.m_panBiorithm, "Calendar");
                return true;
            }
            if (event.target == this.m_btnStartDate) {
                this.m_nState = 2;
                this.m_clLayout.show(this.m_panBiorithm, "Calendar");
                return true;
            }
            if (event.target == this.m_btnOK) {
                switch (this.m_nState) {
                    case 1: {
                        this.m_dateBirth = new XDate(this.m_calendar.getDate());
                        this.m_lbBirthDate.setText("Birth: " + new String(String.valueOf(Integer.toString(this.m_dateBirth.getDate())) + "/" + Integer.toString(this.m_dateBirth.getMonth() + 1) + "/" + Integer.toString(this.m_dateBirth.getYear() + 1900)));
                        if (this.m_dateStart != null) {
                            this.m_btnBiorithm.enable();
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.m_dateStart = new XDate(this.m_calendar.getDate());
                        this.m_lbStartDate.setText("Start: " + new String(String.valueOf(Integer.toString(this.m_dateStart.getDate())) + "/" + Integer.toString(this.m_dateStart.getMonth() + 1) + "/" + Integer.toString(this.m_dateStart.getYear() + 1900)));
                        if (this.m_dateBirth != null) {
                            this.m_btnBiorithm.enable();
                            break;
                        }
                        break;
                    }
                }
                this.m_clLayout.show(this.m_panBiorithm, "Buttons");
                return true;
            }
            if (o == "Biorhythm") {
                this.m_BiorithmGraph.setDates(this.m_dateBirth, this.m_dateStart);
                this.m_clLayout.show(this.m_panBiorithm, "Graph");
                this.m_BiorithmGraph.repaint();
                return true;
            }
            if (o == "Reset") {
                this.m_calendar.reset();
                this.m_lbBirthDate.setText("Birth: ######");
                this.m_lbStartDate.setText("Start: ######");
                this.m_dateBirth = null;
                this.m_dateStart = null;
                this.m_btnBiorithm.disable();
                this.m_clLayout.show(this.m_panBiorithm, "Buttons");
                this.m_nState = 0;
                return true;
            }
        }
        return super.action(event, o);
    }
    
    public String getAppletInfo() {
        return "Biorhythm - Version 1.1.0\nAuthor: Ugo Chirico, Home page: http://www.ugosweb.com\nPublic Domain";
    }
    
    public BiorithmApp() {
        this.m_nState = 0;
    }
}
