import java.awt.Event;
import java.awt.Font;
import java.awt.Component;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.net.URL;
import java.awt.Panel;
import java.awt.Label;
import java.util.Date;
import java.awt.Color;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Calendar2ech extends Applet
{
    AppletUtil3 aut;
    boolean show_arrows;
    boolean show_date;
    boolean show_weekdays;
    Button next;
    Button previous;
    Color highlight_color;
    Date start_date;
    Date current_date;
    Label date_label;
    Panel main_panel;
    String date_message;
    String[] weekdays;
    String[] months;
    String target;
    URL default_url;
    Hashtable special_days;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.setBackground(this.aut.makeColor(this.getParameter("BGCOLOR"), Color.lightGray));
        this.setForeground(this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black));
        this.setFont(this.aut.getFont());
        this.highlight_color = this.aut.makeColor(this.getParameter("HIGHLIGHT.COLOR"), Color.red);
        this.main_panel.setLayout(new GridLayout(0, 7));
        if (this.getParameter("TARGET") != null) {
            this.target = this.getParameter("TARGET");
        }
        final String parameter = this.getParameter("DEFAULT.URL");
        if (parameter != null) {
            try {
                this.default_url = new URL(this.getDocumentBase(), parameter);
            }
            catch (Exception ex) {}
        }
        final String parameter2 = this.getParameter("SPECIAL.DAYS");
        if (parameter2 != null) {
            try {
                final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), parameter2).openConnection().getInputStream());
                String line;
                while ((line = dataInputStream.readLine()) != null && !line.trim().equals("")) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line);
                    if (stringTokenizer.countTokens() > 1) {
                        this.special_days.put(stringTokenizer.nextToken(), new URL(this.getDocumentBase(), stringTokenizer.nextToken()));
                    }
                }
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
        if (this.getParameter("START.DATE") == null) {
            this.start_date = new Date();
        }
        else {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("START.DATE"), " \t,/|\\-_.:;");
            this.start_date = new Date(Integer.parseInt(stringTokenizer2.nextToken()) - 1900, Integer.parseInt(stringTokenizer2.nextToken()) - 1, 1);
        }
        this.current_date = this.start_date;
        final String parameter3 = this.getParameter("WEEKDAY.NAMES");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter3, " ,");
            int n = 0;
            while (stringTokenizer3.hasMoreTokens()) {
                this.weekdays[n] = stringTokenizer3.nextToken();
                ++n;
            }
        }
        final String parameter4 = this.getParameter("MONTH.NAMES");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter4, " ,");
            int n2 = 0;
            while (stringTokenizer4.hasMoreTokens()) {
                this.months[n2] = stringTokenizer4.nextToken();
                ++n2;
            }
        }
        final String parameter5 = this.getParameter("HEADER.FORMAT");
        if (parameter5 != null) {
            this.date_message = parameter5;
        }
        this.date_label.setAlignment(1);
        this.setDateLabel(this.current_date);
        final String parameter6 = this.getParameter("GUI");
        if (parameter6 != null) {
            final String lowerCase = parameter6.toLowerCase();
            this.show_arrows = (lowerCase.indexOf("arrows") != -1);
            this.show_date = (lowerCase.indexOf("date") != -1);
            this.show_weekdays = (lowerCase.indexOf("weekdays") != -1);
        }
        this.setLayout(new BorderLayout(0, 0));
        if (this.show_arrows || this.show_date || this.show_weekdays) {
            final Panel panel = new Panel();
            panel.setLayout(new GridLayout(0, 1, 0, 0));
            if (this.show_arrows || this.show_date) {
                final Panel panel2 = new Panel();
                panel2.setLayout(new BorderLayout(0, 0));
                if (this.show_arrows) {
                    panel2.add("East", this.next);
                    panel2.add("West", this.previous);
                }
                if (this.show_date) {
                    panel2.add("Center", this.date_label);
                }
                panel.add(panel2);
            }
            if (this.show_weekdays) {
                final Panel panel3 = new Panel();
                panel3.setLayout(new GridLayout(1, 7, 0, 0));
                for (int i = 0; i < 7; ++i) {
                    panel3.add(new Label(this.weekdays[i], 1));
                }
                panel.add(panel3);
            }
            this.add("North", panel);
        }
        this.setMainPanel(this.current_date);
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("Calendar applet, Copyright 1998, Eric Harshbarger")) {
            this.add("Center", this.main_panel);
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void setDateLabel(final Date date) {
        final String date_message = this.date_message;
        this.date_label.setText(this.aut.replaceAll(this.aut.replaceAll(this.date_message, "%year%", String.valueOf(1900 + date.getYear())), "%mon%", this.months[date.getMonth()]));
    }
    
    public void setMainPanel(final Date date) {
        this.main_panel.removeAll();
        final int year = date.getYear();
        final int month = date.getMonth();
        date.getDate();
        date.getDay();
        final int day = new Date(year, month, 1).getDay();
        int n = 28;
        for (int n2 = 29; n2 < 32 && new Date(year, month, n2).getMonth() == month; ++n2) {
            n = n2;
        }
        for (int i = 0; i < day; ++i) {
            this.main_panel.add(new Label());
        }
        final Font font = this.getFont();
        final Font font2 = new Font(font.getName(), 1, font.getSize());
        for (int j = 0; j < n; ++j) {
            final Button button = new Button(String.valueOf(j + 1));
            if (this.special_days.containsKey(String.valueOf(1900 + year) + this.pad(String.valueOf(month + 1)) + this.pad(String.valueOf(j + 1)))) {
                button.setFont(font2);
                button.setForeground(this.highlight_color);
            }
            this.main_panel.add(button);
        }
        this.validate();
        this.paintAll(this.getGraphics());
    }
    
    public String pad(final String s) {
        final String string = "0" + s;
        return string.substring(string.length() - 2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.next) {
                int year = this.current_date.getYear();
                final int n = (this.current_date.getMonth() + 1) % 12;
                if (n == 0) {
                    ++year;
                }
                this.setMainPanel(this.current_date = new Date(year, n, 1));
                this.setDateLabel(this.current_date);
            }
            else if (event.target == this.previous) {
                int year2 = this.current_date.getYear();
                final int n2 = (this.current_date.getMonth() + 11) % 12;
                if (n2 == 11) {
                    --year2;
                }
                this.setMainPanel(this.current_date = new Date(year2, n2, 1));
                this.setDateLabel(this.current_date);
            }
            else if (event.target instanceof Button) {
                final String string = String.valueOf(1900 + this.current_date.getYear()) + this.pad(String.valueOf(this.current_date.getMonth() + 1)) + this.pad(((Button)event.target).getLabel());
                try {
                    if (this.special_days.containsKey(string)) {
                        this.getAppletContext().showDocument((URL)this.special_days.get(string), this.target);
                    }
                    else if (this.default_url != null) {
                        this.getAppletContext().showDocument(this.default_url, this.target);
                    }
                }
                catch (Exception ex) {}
            }
        }
        return super.handleEvent(event);
    }
    
    public Calendar2ech() {
        this.show_arrows = true;
        this.show_date = true;
        this.show_weekdays = true;
        this.next = new Button(">>");
        this.previous = new Button("<<");
        this.highlight_color = Color.red;
        this.date_label = new Label();
        this.main_panel = new Panel();
        this.date_message = "%mon% %year%";
        this.weekdays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        this.months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.target = "_self";
        this.special_days = new Hashtable();
    }
}
