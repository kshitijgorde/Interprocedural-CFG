// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import duc.cal.CalUtil;
import duc.cal.LunarDate;
import java.awt.event.MouseListener;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

public class MonthApplet extends Applet
{
    static Font arial12;
    static Font arial12b;
    static Font arial12i;
    static Font arial14;
    static Font arial14b;
    static Font arial24b;
    static Font times11;
    static Color monthLabelColor;
    static Color dayLabelColor;
    Global calculator;
    DateCanvas[] cells;
    Panel center;
    Panel north;
    Panel south;
    VietLabel dayLabel;
    VietLabel saoLabel;
    VietLabel monthLabel;
    Button go;
    int emptyCells;
    MonthModel model;
    Choice monthChoice;
    Button prevMonth;
    Button nextMonth;
    Button today;
    DateCanvas selected;
    TextField yearField;
    TextField zoneField;
    
    static {
        MonthApplet.arial12 = new Font("Arial", 0, 12);
        MonthApplet.arial12b = new Font("Arial", 1, 12);
        MonthApplet.arial12i = new Font("Arial", 2, 12);
        MonthApplet.arial14 = new Font("Arial", 0, 14);
        MonthApplet.arial14b = new Font("Arial", 1, 14);
        MonthApplet.arial24b = new Font("Arial", 1, 16);
        MonthApplet.times11 = new Font("Times", 0, 11);
        MonthApplet.monthLabelColor = new Color(204, 255, 204);
        MonthApplet.dayLabelColor = new Color(255, 255, 204);
    }
    
    public MonthApplet(final boolean useAstroCal) {
        this.calculator = Global.getSingleton();
        this.cells = new DateCanvas[42];
        this.model = new MonthModel();
        final int[] arr = getToday();
        this.model.setDay(arr[0]);
        this.model.setMonth(arr[1], arr[2]);
        this.model.setAstro(useAstroCal);
        this.setFont(MonthApplet.arial14b);
        (this.prevMonth = new Button("<<")).addActionListener(new PreviousAction());
        (this.nextMonth = new Button(">>")).addActionListener(new NextAction());
        (this.today = new Button("Today")).addActionListener(new TodayAction());
        this.monthChoice = new Choice();
        for (int i = 1; i <= 12; ++i) {
            this.monthChoice.add("Th. " + i);
        }
        this.monthChoice.select(this.model.getMonth() - 1);
        this.monthChoice.addItemListener(new IL());
        (this.yearField = new TextField("" + this.model.getYear())).addActionListener(new AL());
        (this.zoneField = new TextField("420")).addActionListener(new AL());
        (this.go = new Button("Display")).addActionListener(new AL());
        this.center = new Panel();
        for (int i = 1; i <= 7; ++i) {
            final VietLabel ngay = new VietLabel();
            if (i == 1) {
                ngay.setText("Ch.Nh\u1eadt");
            }
            else {
                ngay.setText("Th\u1ee9 " + i);
            }
            if (i == 1) {
                ngay.setForeground(Color.red);
            }
            else if (i == 7) {
                ngay.setForeground(new Color(0, 128, 48));
            }
            else {
                ngay.setForeground(Color.black);
            }
            ngay.setBackground(MonthApplet.dayLabelColor);
            ngay.setBorder(1);
            this.center.add(ngay);
        }
        final KeyListener kl = new KL();
        final MouseListener ml = new ML();
        this.center.setLayout(new GridLayout(0, 7));
        for (int j = 0; j < this.cells.length; ++j) {
            (this.cells[j] = new DateCanvas()).addMouseListener(ml);
            this.cells[j].addKeyListener(kl);
            this.center.add(this.cells[j]);
        }
        this.addKeyListener(new KL());
        this.setSize(480, 450);
        this.setLayout(new BorderLayout());
        this.south = new Panel();
        (this.dayLabel = new VietLabel()).setFont(MonthApplet.arial12b);
        this.dayLabel.setBackground(Color.darkGray);
        this.dayLabel.setForeground(Color.white);
        (this.saoLabel = new VietLabel()).setFont(MonthApplet.arial12);
        this.south.setLayout(new BorderLayout());
        this.south.add(this.dayLabel, "North");
        this.south.add(this.saoLabel, "South");
        this.add(this.center, "Center");
        this.add(this.south, "South");
        (this.north = new Panel()).setBackground(new Color(128, 128, 64));
        this.north.add(this.today);
        this.north.add(this.prevMonth);
        this.north.add(this.monthChoice);
        this.north.add(this.yearField);
        this.north.add(this.nextMonth);
        this.north.add(this.go);
        if (useAstroCal) {
            this.north.add(new Label("GMT +"));
            this.north.add(this.zoneField);
        }
        final Panel topPanel = new Panel();
        topPanel.setLayout(new GridLayout(0, 1));
        (this.monthLabel = new VietLabel()).setFont(MonthApplet.arial24b);
        this.monthLabel.setForeground(Color.blue);
        this.monthLabel.setBackground(MonthApplet.monthLabelColor);
        topPanel.add(this.north);
        topPanel.add(this.monthLabel);
        this.add(topPanel, "North");
        try {
            this.setMonth(this.model.getMonth(), this.model.getYear());
        }
        catch (Throwable t) {}
    }
    
    private LunarDate[] computeMonth(final int mm, final int yy) {
        if (this.getModel().isAstro()) {
            return this.calculator.getConverterAstro().computeLunarDates(mm, yy);
        }
        return this.calculator.getConverterVN().computeLunarDates(mm, yy);
    }
    
    private void display() {
        int year = this.getModel().getYear();
        try {
            year = Integer.parseInt(this.yearField.getText());
        }
        catch (Exception ex) {}
        final int month = this.monthChoice.getSelectedIndex() + 1;
        this.setMonth(month, year);
    }
    
    private String getDayName(final LunarDate v) {
        final StringBuffer sb = new StringBuffer();
        final String[] a = v.getCanChi();
        sb.append(this.getModel().getDay()).append('/');
        sb.append(this.getModel().getMonth()).append('/');
        if (this.getModel().getYear() > 0) {
            sb.append(this.getModel().getYear());
        }
        else {
            sb.append(1 - this.getModel().getYear()).append(" BC");
        }
        sb.append(" -- ");
        sb.append(LunarDate.NGAY).append(" ").append(a[0]);
        sb.append(", ").append(LunarDate.THANG).append(" ");
        sb.append(a[1]).append(" (").append(LunarDate.THANG_LOW).append(" ");
        sb.append(LunarDate.TENTHANG[v.getMonth() - 1]).append(")");
        if (v.isLeap()) {
            sb.append(" ").append(LunarDate.NHUAN);
        }
        sb.append(", ").append(LunarDate.NAM).append(" ").append(a[2]);
        return sb.toString();
    }
    
    MonthModel getModel() {
        return this.model;
    }
    
    private String getMonthName(final LunarDate ld) {
        final StringBuffer sb = new StringBuffer();
        sb.append(LunarDate.THANG).append(" ");
        sb.append(ld.getMonth());
        if (ld.isLeap()) {
            sb.append(" ").append(LunarDate.NHUAN);
        }
        sb.append(" ").append(LunarDate.getCanChiYear(ld.getYear()));
        return sb.toString();
    }
    
    private void moveSelected(final int n) {
        int i;
        for (i = 0; this.cells[i] != this.selected; ++i) {}
        if (i == this.cells.length) {
            return;
        }
        i += n;
        if (i >= 0 && i < this.cells.length && !this.cells[i].empty) {
            this.select(this.cells[i]);
        }
        else {
            final int[] a = CalUtil.jdToDate(this.selected.getLunarDate().getJd() + n);
            this.getModel().setDay(a[0]);
            this.setMonth(a[1], a[2]);
        }
    }
    
    private void nextMonth() {
        int mm = this.getModel().getMonth();
        int yy = this.getModel().getYear();
        if (mm == 12) {
            ++yy;
            mm = 1;
        }
        else {
            ++mm;
        }
        this.setMonth(mm, yy);
    }
    
    private void previousMonth() {
        int mm = this.getModel().getMonth();
        int yy = this.getModel().getYear();
        if (mm == 1) {
            --yy;
            mm = 12;
        }
        else {
            --mm;
        }
        this.setMonth(mm, yy);
    }
    
    private void select(final DateCanvas cell) {
        if (this.selected != null) {
            this.selected.backColor = Color.white;
            this.selected.repaint();
        }
        this.selected = cell;
        cell.backColor = DateCanvas.selectedColor;
        if (cell.empty) {
            return;
        }
        this.getModel().setDay(cell.getSolarDay());
        final LunarDate v = cell.getLunarDate();
        final String s1 = this.getDayName(v);
        this.dayLabel.setText(s1);
        String s2 = "Tr\u1ef1c: " + MonthModel.getTruc(v.getJd());
        s2 = String.valueOf(s2) + " + Sao: " + MonthModel.getSaoNTBT(v.getJd());
        s2 = String.valueOf(s2) + " + Sao H\u0110: " + MonthModel.getSaoHoangDao(v);
        s2 = String.valueOf(s2) + " + Gi\u1edd H\u0110: " + MonthModel.getGioHoangDao(v.getJd());
        this.saoLabel.setText(s2);
        cell.repaint();
        cell.requestFocus();
    }
    
    private void setMonth(final int mm, final int yy) {
        this.getModel().setMonth(mm, yy);
        for (int i = 0; i < this.cells.length; ++i) {
            this.cells[i].clear();
            this.cells[i].repaint();
        }
        try {
            this.calculator.setTimeZone(Integer.parseInt(this.zoneField.getText()));
        }
        catch (Throwable t) {}
        final LunarDate[] dates = this.getModel().getLunarDates();
        final int firstDay = dates[0].getJd();
        this.emptyCells = (firstDay + 1) % 7;
        if (this.emptyCells < 0) {
            this.emptyCells += 7;
        }
        for (int len = dates.length, k = 0; k < len; ++k) {
            final DateCanvas dc = this.cells[this.emptyCells + k];
            dc.setLunarDate(dates[k]);
        }
        final LunarDate[] datesAlt = this.getModel().getLunarDatesAlt();
        if (datesAlt != null) {
            for (int j = 0; j < datesAlt.length; ++j) {
                this.cells[this.emptyCells + j].setLunarDateAlt(datesAlt[j]);
            }
        }
        this.yearField.setText("" + yy);
        this.monthChoice.select(this.getModel().getMonth() - 1);
        final LunarDate v1 = dates[0];
        final LunarDate v2 = dates[dates.length - 1];
        this.monthLabel.setText(String.valueOf(this.getMonthName(v1)) + " - " + this.getMonthName(v2));
        final int[] solarTerms = this.getModel().getSolarTerms();
        final String[] solarTermNames = this.getModel().getSolarTermNames();
        for (int l = 0; l < solarTerms.length; ++l) {
            this.cells[solarTerms[l] - firstDay + this.emptyCells].setTerm(solarTermNames[l]);
        }
        int dd = this.getModel().getDay();
        if (dd < 0) {
            dd = 1;
        }
        else if (dd > dates.length) {
            dd = dates.length;
        }
        this.select(this.cells[this.emptyCells + dd - 1]);
        this.repaint();
    }
    
    private static int[] getToday() {
        final Calendar c = Calendar.getInstance();
        final int dd = c.get(5);
        final int mm = c.get(2) + 1;
        final int yy = c.get(1);
        return new int[] { dd, mm, yy };
    }
    
    public void start() {
        if (this.selected != null) {
            this.selected.requestFocus();
        }
    }
    
    class AL implements ActionListener
    {
        public void actionPerformed(final ActionEvent arg0) {
            MonthApplet.this.display();
        }
    }
    
    class IL implements ItemListener
    {
        public void itemStateChanged(final ItemEvent e) {
            final int month = MonthApplet.this.monthChoice.getSelectedIndex() + 1;
            int year = 2004;
            try {
                year = Integer.parseInt(MonthApplet.this.yearField.getText());
            }
            catch (Throwable t) {}
            MonthApplet.this.setMonth(month, year);
        }
    }
    
    class KL extends KeyAdapter
    {
        public void keyPressed(final KeyEvent e) {
            final int k = e.getKeyCode();
            if (k == 34) {
                MonthApplet.this.nextMonth();
            }
            else if (k == 33) {
                MonthApplet.this.previousMonth();
            }
            else if (k == 37) {
                MonthApplet.this.moveSelected(-1);
            }
            else if (k == 39) {
                MonthApplet.this.moveSelected(1);
            }
            else if (k == 38) {
                MonthApplet.this.moveSelected(-7);
            }
            else if (k == 40) {
                MonthApplet.this.moveSelected(7);
            }
            else if (k == 36) {
                MonthApplet.this.setMonth(MonthApplet.this.getModel().getMonth(), MonthApplet.this.getModel().getYear() - 1);
            }
            else if (k == 35) {
                MonthApplet.this.setMonth(MonthApplet.this.getModel().getMonth(), MonthApplet.this.getModel().getYear() + 1);
            }
        }
    }
    
    class ML extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent arg0) {
            final DateCanvas cell = (DateCanvas)arg0.getSource();
            if (cell.empty) {
                return;
            }
            MonthApplet.this.select(cell);
        }
    }
    
    class NextAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent arg0) {
            MonthApplet.this.nextMonth();
        }
    }
    
    class PreviousAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent arg0) {
            MonthApplet.this.previousMonth();
        }
    }
    
    class TodayAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent arg0) {
            final int[] a = getToday();
            MonthApplet.this.getModel().setDay(a[0]);
            MonthApplet.this.setMonth(a[1], a[2]);
        }
    }
}
