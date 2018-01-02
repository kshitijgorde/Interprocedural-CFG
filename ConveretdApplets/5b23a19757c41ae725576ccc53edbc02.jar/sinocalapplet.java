import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.util.Calendar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sinocalapplet extends Applet
{
    sinocal sc;
    Calendar curdate;
    Button w2cbutton;
    Button c2wbutton;
    Button nextbutton;
    Choice dayofweekchoice;
    Choice daychoice;
    Choice monthchoice;
    Choice erachoice;
    TextField yearchoice;
    Canvas chinesedisplay;
    TextField cyearchoice;
    Choice cdynastychoice;
    Choice crulerchoice;
    Choice creignchoice;
    Choice cmonthchoice;
    Choice cleapchoice;
    Choice cdaychoice;
    Choice ycyclechoice;
    Choice mcyclechoice;
    Choice dcyclechoice;
    Choice festivalchoice;
    Choice zodiacchoice;
    static final String[][] festivals;
    static final String[] months;
    static final String[] signs;
    static final String[] dayofweek;
    
    public void init() {
        this.sc = new sinocal();
        this.setLayout(new FlowLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel(new GridBagLayout());
        final Panel panel2 = new Panel(new GridBagLayout());
        final Panel panel3 = new Panel(new GridBagLayout());
        this.dayofweekchoice = new Choice();
        for (int i = 0; i < sinocalapplet.dayofweek.length; ++i) {
            this.dayofweekchoice.addItem(sinocalapplet.dayofweek[i]);
        }
        this.monthchoice = new Choice();
        for (int j = 0; j < sinocalapplet.months.length; ++j) {
            this.monthchoice.addItem(sinocalapplet.months[j]);
        }
        this.daychoice = new Choice();
        for (int k = 1; k <= 31; ++k) {
            this.daychoice.addItem(String.valueOf(k));
        }
        this.yearchoice = new TextField(4);
        (this.erachoice = new Choice()).addItem("AD");
        this.erachoice.addItem("BC");
        (this.cdynastychoice = new Choice()).addItem("Republic");
        (this.crulerchoice = new Choice()).addItem("N/A");
        (this.creignchoice = new Choice()).addItem("N/A");
        this.cyearchoice = new TextField(3);
        this.cmonthchoice = new Choice();
        for (int l = 1; l <= 12; ++l) {
            this.cmonthchoice.addItem(String.valueOf(l));
        }
        (this.cleapchoice = new Choice()).addItem("No");
        this.cleapchoice.addItem("Yes");
        this.cdaychoice = new Choice();
        for (int n = 1; n <= 30; ++n) {
            this.cdaychoice.addItem(String.valueOf(n));
        }
        this.ycyclechoice = new Choice();
        for (int n2 = 0; n2 < 60; ++n2) {
            this.ycyclechoice.addItem(sinocal.sbcycle[n2]);
        }
        this.mcyclechoice = new Choice();
        for (int n3 = 0; n3 < 60; ++n3) {
            this.mcyclechoice.addItem(sinocal.sbcycle[n3]);
        }
        this.dcyclechoice = new Choice();
        for (int n4 = 0; n4 < 60; ++n4) {
            this.dcyclechoice.addItem(sinocal.sbcycle[n4]);
        }
        this.festivalchoice = new Choice();
        for (int n5 = 0; n5 < sinocalapplet.festivals.length; ++n5) {
            this.festivalchoice.addItem(sinocalapplet.festivals[n5][0]);
        }
        this.zodiacchoice = new Choice();
        for (int n6 = 0; n6 < sinocalapplet.signs.length; ++n6) {
            this.zodiacchoice.addItem(sinocalapplet.signs[n6]);
        }
        this.w2cbutton = new Button("Western to Chinese Date");
        this.c2wbutton = new Button("Chinese to Western Date");
        this.nextbutton = new Button("Find Next Festival");
        this.curdate = Calendar.getInstance();
        this.dayofweekchoice.select(this.curdate.get(7) - 1);
        this.monthchoice.select(this.curdate.get(2));
        this.daychoice.select(this.curdate.get(5) - 1);
        this.yearchoice.setText(String.valueOf(this.curdate.get(1)));
        this.erachoice.select(0);
        final String[] jd2Chinese = this.sc.JD2Chinese(sinocal.Gregorian2JD(this.curdate.get(1), this.curdate.get(2) + 1, this.curdate.get(5)));
        this.cyearchoice.setText(jd2Chinese[4]);
        int n7 = (Integer.parseInt(jd2Chinese[7]) + 10) % 12;
        if (n7 == 0) {
            n7 = 12;
        }
        this.cmonthchoice.select(String.valueOf(n7));
        this.zodiacchoice.select((Integer.parseInt(jd2Chinese[5]) + 11) % 12);
        if (jd2Chinese[6].equals("L")) {
            this.cleapchoice.select(1);
        }
        else {
            this.cleapchoice.select(0);
        }
        this.cdaychoice.select(jd2Chinese[8]);
        this.ycyclechoice.select(Integer.parseInt(jd2Chinese[5]) - 1);
        this.mcyclechoice.select(Integer.parseInt(jd2Chinese[7]) - 1);
        this.dcyclechoice.select(Integer.parseInt(jd2Chinese[10]) - 1);
        int n8;
        for (n8 = 0; n8 < sinocalapplet.festivals.length; ++n8) {
            if (sinocalapplet.festivals[n8][1].equals(jd2Chinese[9]) && sinocalapplet.festivals[n8][2].equals(jd2Chinese[8])) {
                this.festivalchoice.select(n8);
                break;
            }
        }
        if (n8 == sinocalapplet.festivals.length) {
            this.festivalchoice.select(0);
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.setconstraints(gridBagConstraints, 1, 0, 1, 1);
        panel.add(new Label("Day of Week"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel.add(new Label("Month"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel.add(new Label("Day"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel.add(new Label(" "), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel.add(new Label("Year"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel.add(new Label("Era"), gridBagConstraints);
        gridBagConstraints.insets.bottom = 5;
        this.setconstraints(gridBagConstraints, gridBagConstraints.insets.top = 0, 1, 1, 1);
        panel.add(new Label("Western Date: "), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(this.dayofweekchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(this.monthchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(this.daychoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(new Label(", "), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(this.yearchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel.add(this.erachoice, gridBagConstraints);
        gridBagConstraints.insets.bottom = 0;
        gridBagConstraints.insets.top = 5;
        this.setconstraints(gridBagConstraints, 1, 0, 1, 1);
        panel2.add(new Label("Dynasty"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Ruler"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Reign"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Year"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Month"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Leap?"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 0, 1, 1);
        panel2.add(new Label("Day"), gridBagConstraints);
        gridBagConstraints.insets.top = 0;
        gridBagConstraints.insets.bottom = 5;
        this.setconstraints(gridBagConstraints, 0, 1, 1, 1);
        gridBagConstraints.anchor = 13;
        panel2.add(new Label("Chinese Date: "), gridBagConstraints);
        gridBagConstraints.anchor = 10;
        this.setconstraints(gridBagConstraints, 1, 1, 1, 1);
        panel2.add(this.cdynastychoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.crulerchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.creignchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.cyearchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.cmonthchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.cleapchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, -1, 1, 1, 1);
        panel2.add(this.cdaychoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, 0, 1, 1);
        panel3.add(new Label("Year Cycle"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, -1, 1, 1);
        panel3.add(this.ycyclechoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, -1, 1, 1);
        panel3.add(new Label("Month Cycle"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, -1, 1, 1);
        panel3.add(this.mcyclechoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, -1, 1, 1);
        panel3.add(new Label("Day Cycle"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, 0, -1, 1, 1);
        panel3.add(this.dcyclechoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 0, 1, 1);
        panel3.add(new Label("Zodiac Sign"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 1, 1, 1);
        panel3.add(this.zodiacchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 2, 1, 1);
        panel3.add(new Label("Lunar Calendar Festival"), gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 3, 1, 1);
        panel3.add(this.festivalchoice, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 4, 1, 1);
        panel3.add(this.w2cbutton, gridBagConstraints);
        this.setconstraints(gridBagConstraints, 2, 5, 1, 1);
        panel3.add(this.c2wbutton, gridBagConstraints);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final int selectedIndex = sinocalapplet.this.festivalchoice.getSelectedIndex();
                int n = 0;
                int int1 = 0;
                int int2 = 0;
                try {
                    Integer.parseInt(sinocalapplet.festivals[selectedIndex][1]);
                    Integer.parseInt(sinocalapplet.festivals[selectedIndex][2]);
                    int1 = Integer.parseInt(sinocalapplet.this.daychoice.getSelectedItem());
                    int2 = Integer.parseInt(sinocalapplet.this.yearchoice.getText());
                }
                catch (Exception ex) {}
                for (int i = 0; i < sinocalapplet.months.length; ++i) {
                    if (sinocalapplet.this.monthchoice.getSelectedItem().equals(sinocalapplet.months[i])) {
                        n = i + 1;
                        break;
                    }
                }
                sinocal.Gregorian2JD(int2, n, int1);
            }
        };
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                int chinese2JD = 0;
                int int1 = 0;
                int int2 = 0;
                try {
                    int1 = Integer.parseInt(sinocalapplet.this.cmonthchoice.getSelectedItem());
                    int2 = Integer.parseInt(sinocalapplet.this.cdaychoice.getSelectedItem());
                    chinese2JD = sinocalapplet.this.sc.Chinese2JD("\u4e2d\u83ef\u6c11\u570b", "N/A", "N/A", Integer.parseInt(sinocalapplet.this.cyearchoice.getText()), int1, sinocalapplet.this.cleapchoice.getSelectedItem().equals("Yes"), int2);
                }
                catch (Exception ex) {}
                final int[] jd2Gregorian = sinocal.JD2Gregorian(chinese2JD);
                sinocalapplet.this.monthchoice.select(jd2Gregorian[1] - 1);
                sinocalapplet.this.daychoice.select(jd2Gregorian[2] - 1);
                sinocalapplet.this.yearchoice.setText(String.valueOf(jd2Gregorian[0]));
                sinocalapplet.this.dayofweekchoice.select(sinocal.JD2DayofWeek(chinese2JD));
                int i;
                for (i = 0; i < sinocalapplet.festivals.length; ++i) {
                    if (sinocalapplet.festivals[i][1].equals(String.valueOf(int1)) && sinocalapplet.festivals[i][2].equals(String.valueOf(int2))) {
                        sinocalapplet.this.festivalchoice.select(i);
                        break;
                    }
                }
                if (i == sinocalapplet.festivals.length) {
                    sinocalapplet.this.festivalchoice.select(0);
                }
                sinocalapplet.this.zodiacchoice.select(sinocal.JD2cyear(jd2Gregorian[0])[1]);
            }
        };
        this.w2cbutton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                int int1 = 1;
                int n = 1;
                int int2 = 1;
                final String selectedItem = sinocalapplet.this.daychoice.getSelectedItem();
                final String selectedItem2 = sinocalapplet.this.monthchoice.getSelectedItem();
                final String text = sinocalapplet.this.yearchoice.getText();
                try {
                    int1 = Integer.parseInt(selectedItem);
                    int2 = Integer.parseInt(text);
                }
                catch (Exception ex) {}
                for (int i = 0; i < sinocalapplet.months.length; ++i) {
                    if (selectedItem2.equals(sinocalapplet.months[i])) {
                        n = i + 1;
                        break;
                    }
                }
                final int gregorian2JD = sinocal.Gregorian2JD(int2, n, int1);
                sinocalapplet.this.dayofweekchoice.select(sinocal.JD2DayofWeek(gregorian2JD));
                final String[] jd2Chinese = sinocalapplet.this.sc.JD2Chinese(gregorian2JD);
                sinocalapplet.this.cyearchoice.setText(jd2Chinese[4]);
                sinocalapplet.this.zodiacchoice.select((Integer.parseInt(jd2Chinese[5]) + 11) % 12);
                sinocalapplet.this.cmonthchoice.select(jd2Chinese[9]);
                if (jd2Chinese[6].equals("L")) {
                    sinocalapplet.this.cleapchoice.select(1);
                }
                else {
                    sinocalapplet.this.cleapchoice.select(0);
                    int j;
                    for (j = 0; j < sinocalapplet.festivals.length; ++j) {
                        if (sinocalapplet.festivals[j][1].equals(jd2Chinese[9]) && sinocalapplet.festivals[j][2].equals(jd2Chinese[8])) {
                            sinocalapplet.this.festivalchoice.select(j);
                            break;
                        }
                    }
                    if (j == sinocalapplet.festivals.length) {
                        sinocalapplet.this.festivalchoice.select(0);
                    }
                }
                sinocalapplet.this.cdaychoice.select(jd2Chinese[8]);
                sinocalapplet.this.ycyclechoice.select(Integer.parseInt(jd2Chinese[5]) - 1);
                sinocalapplet.this.mcyclechoice.select(Integer.parseInt(jd2Chinese[7]) - 1);
                sinocalapplet.this.dcyclechoice.select(Integer.parseInt(jd2Chinese[10]) - 1);
            }
        });
        this.c2wbutton.addActionListener(actionListener);
    }
    
    private void setconstraints(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
    }
    
    public void stop() {
    }
    
    static {
        festivals = new String[][] { { " ", "0", "0" }, { "Chinese New Year:  1st Month  1st Day", "1", "1" }, { "Lantern Festival:  1st Month 15th Day", "1", "15" }, { "Dragon Boat Festival:  5th Month 5th Day", "5", "5" }, { "Ghost Day:         7th Month 15th Day", "7", "15" }, { "Mid-autumn Festival:  8th Month 15th Day", "8", "15" }, { "Double Ninth Day:  9th Month 9th Day", "9", "9" } };
        months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        signs = new String[] { "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Chicken", "Dog", "Pig" };
        dayofweek = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    }
}
