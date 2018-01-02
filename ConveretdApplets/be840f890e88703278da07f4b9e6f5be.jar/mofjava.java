import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Checkbox;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mofjava extends Applet
{
    MofPanel thePanel;
    static int WindowWidth;
    static int WindowHeight;
    Image BackroundMap;
    Image ScaledBackroundMap;
    TabbingTextField LatField;
    TabbingTextField LonField;
    TabbingTextField SFField;
    TabbingTextField AField;
    TabbingTextField KField;
    TabbingTextField MonthField;
    TabbingTextField DayField;
    TabbingTextField TimeField;
    TabbingButton ComputeButton;
    TabbingButton NextHourButton;
    TabbingButton NowButton;
    Checkbox SPMOFCheckBox;
    Checkbox LPMOFCheckBox;
    Checkbox SPLOFCheckBox;
    Checkbox LPLOFCheckBox;
    Checkbox SunCheckBox;
    Checkbox QTHCheckBox;
    Checkbox PathsCheckBox;
    Checkbox RectangularCheckBox;
    Checkbox PolarQTHCheckBox;
    Checkbox PolarNCheckBox;
    Checkbox PolarSCheckBox;
    
    public synchronized void init() {
        int int1 = 15;
        int int2 = 20;
        int int3 = 460;
        int int4 = 345;
        final String parameter = this.getParameter("MAPWIDTH");
        String s = "67";
        String s2 = "0";
        String s3 = "0";
        String s4 = "0";
        String s5 = "0";
        if (parameter != null) {
            try {
                int3 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        final String parameter2 = this.getParameter("MAPHEIGHT");
        if (parameter2 != null) {
            try {
                int4 = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter3 = this.getParameter("ROWS");
        if (parameter3 != null) {
            try {
                int1 = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {}
        }
        final String parameter4 = this.getParameter("COLS");
        if (parameter4 != null) {
            try {
                int2 = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {}
        }
        final String parameter5 = this.getParameter("SF");
        if (parameter5 != null) {
            try {
                s = parameter5;
            }
            catch (NumberFormatException ex5) {}
        }
        final String parameter6 = this.getParameter("A");
        if (parameter6 != null) {
            try {
                s3 = parameter6;
            }
            catch (NumberFormatException ex6) {}
        }
        final String parameter7 = this.getParameter("K");
        if (parameter7 != null) {
            try {
                s2 = parameter7;
            }
            catch (NumberFormatException ex7) {}
        }
        final String parameter8 = this.getParameter("LAT");
        if (parameter8 != null) {
            try {
                s4 = parameter8;
            }
            catch (NumberFormatException ex8) {}
        }
        final String parameter9 = this.getParameter("LON");
        if (parameter9 != null) {
            try {
                s5 = parameter9;
            }
            catch (NumberFormatException ex9) {}
        }
        this.thePanel = new MofPanel(this, int4, int3, int1, int2);
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.BackroundMap = this.getImage(this.getDocumentBase(), "worldrev.gif");
        System.out.println("in mofjava init() getDocumentBase()=" + this.getCodeBase());
        (this.ScaledBackroundMap = this.createImage(this.thePanel.MapWidth, this.thePanel.MapHeight)).flush();
        final Graphics graphics = this.ScaledBackroundMap.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.thePanel.MapWidth, this.thePanel.MapHeight);
        while (!graphics.drawImage(this.BackroundMap, 0, 0, this.thePanel.MapWidth, this.thePanel.MapHeight, Color.black, this)) {
            System.out.println("in mofjava init() drawimage of scaledmap returns false");
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex10) {
                System.out.println("in mofjava init() sleep interrupted");
            }
        }
        if (this.prepareImage(this.ScaledBackroundMap, this)) {
            System.out.println("in mofjava init() scaledbackroundmap prepareimage==true");
        }
        int n = this.checkImage(this.ScaledBackroundMap, this);
        System.out.println("in mofjava init() initial Scaledbackroundmap checkimage loop check=" + n);
        while ((n & 0xE0) == 0x0) {
            n = this.checkImage(this.ScaledBackroundMap, this);
            System.out.println("in mofjava init() in Scaledbackroundmap checkimage loop check=" + n);
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex11) {
                System.out.println("in mofjava init() sleep interrupted");
            }
        }
        this.setLayout(new BorderLayout());
        this.add("Center", this.thePanel);
        final Panel panel = new Panel();
        this.add("South", panel);
        panel.setLayout(new GridLayout(1, 3));
        final Panel panel2 = new Panel();
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(new Label("Propagation Modes"));
        final Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(1, 2));
        panel2.add(panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(1, 2));
        panel2.add(panel4);
        panel3.add(this.SPMOFCheckBox = new Checkbox("SP MOF", checkboxGroup, true));
        panel3.add(this.LPMOFCheckBox = new Checkbox("LP MOF", checkboxGroup, false));
        panel4.add(this.SPLOFCheckBox = new Checkbox("SP LOF", checkboxGroup, false));
        panel4.add(this.LPLOFCheckBox = new Checkbox("LP LOF", checkboxGroup, false));
        panel.add(panel2);
        final Panel panel5 = new Panel();
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        panel5.setLayout(new GridLayout(3, 1));
        panel5.add(new Label("Map Projections"));
        final Panel panel6 = new Panel();
        panel6.setLayout(new GridLayout(1, 2));
        panel5.add(panel6);
        final Panel panel7 = new Panel();
        panel7.setLayout(new GridLayout(1, 2));
        panel5.add(panel7);
        panel6.add(this.RectangularCheckBox = new Checkbox("Rectangular", checkboxGroup2, true));
        panel6.add(this.PolarQTHCheckBox = new Checkbox("Polar QTH", checkboxGroup2, false));
        panel7.add(this.PolarNCheckBox = new Checkbox("North Polar", checkboxGroup2, false));
        panel7.add(this.PolarSCheckBox = new Checkbox("South Polar", checkboxGroup2, false));
        panel.add(panel5);
        final Panel panel8 = new Panel();
        panel8.setLayout(new GridLayout(3, 1));
        panel8.add(new Label("Map Overlays"));
        final Panel panel9 = new Panel();
        panel9.setLayout(new GridLayout(1, 2));
        panel8.add(panel9);
        final Panel panel10 = new Panel();
        panel10.setLayout(new GridLayout(1, 2));
        panel8.add(panel10);
        panel9.add(this.SunCheckBox = new Checkbox("Sun", null, true));
        panel9.add(this.QTHCheckBox = new Checkbox("QTH", null, true));
        panel10.add(this.PathsCheckBox = new Checkbox("Paths", null, false));
        panel.add(panel8);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+0"));
        final Panel panel11 = new Panel();
        this.add("East", panel11);
        panel11.setLayout(new GridLayout(10, 2));
        panel11.add(new Label("Lat"));
        panel11.add(this.LatField = new TabbingTextField(s4, 6));
        panel11.add(new Label("Lon"));
        panel11.add(this.LonField = new TabbingTextField(s5, 6));
        panel11.add(new Label("SF"));
        panel11.add(this.SFField = new TabbingTextField(s, 6));
        panel11.add(new Label("A index"));
        panel11.add(this.AField = new TabbingTextField(s3, 6));
        panel11.add(new Label("K index"));
        panel11.add(this.KField = new TabbingTextField(s2, 6));
        panel11.add(new Label("Month"));
        panel11.add(this.MonthField = new TabbingTextField("", 6));
        panel11.add(new Label("Day"));
        panel11.add(this.DayField = new TabbingTextField("", 6));
        panel11.add(new Label("Time"));
        panel11.add(this.TimeField = new TabbingTextField("", 6));
        this.MonthField.setText(Integer.toString(gregorianCalendar.get(2) + 1));
        this.DayField.setText(Integer.toString(gregorianCalendar.get(5)));
        this.TimeField.setText(Integer.toString(gregorianCalendar.get(11) * 100 + gregorianCalendar.get(12)));
        this.ComputeButton = new TabbingButton("Compute");
        this.NextHourButton = new TabbingButton("Next Hour");
        this.NowButton = new TabbingButton("NOW!");
        this.LatField.SetTabOrder(this.LonField, this.NowButton);
        this.LonField.SetTabOrder(this.SFField, this.LatField);
        this.SFField.SetTabOrder(this.AField, this.LonField);
        this.AField.SetTabOrder(this.KField, this.SFField);
        this.KField.SetTabOrder(this.MonthField, this.AField);
        this.MonthField.SetTabOrder(this.DayField, this.KField);
        this.DayField.SetTabOrder(this.TimeField, this.MonthField);
        this.TimeField.SetTabOrder(this.ComputeButton, this.DayField);
        this.ComputeButton.SetTabOrder(this.NextHourButton, this.TimeField);
        this.NextHourButton.SetTabOrder(this.NowButton, this.ComputeButton);
        this.NowButton.SetTabOrder(this.LatField, this.NextHourButton);
        panel11.add(this.ComputeButton);
        panel11.add(this.NextHourButton);
        panel11.add(this.NowButton);
        this.ComputeButton.requestFocus();
        this.thePanel.init();
    }
    
    public void start() {
        System.out.println("in mofjava start()");
        this.thePanel.start();
    }
    
    public void stop() {
        this.thePanel.stop();
    }
    
    public void destroy() {
        this.thePanel.destroy();
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("mofjavaFrame");
        frame.setBounds(100, 100, mofjava.WindowWidth, mofjava.WindowHeight);
        System.out.println("in mofjava main()");
        final mofjava mofjava = new mofjava();
        frame.add("Center", mofjava);
        frame.show();
        mofjava.init();
        mofjava.start();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o instanceof Boolean) {
            if (((Checkbox)event.target).getLabel().equals("SP MOF") || ((Checkbox)event.target).getLabel().equals("LP MOF") || ((Checkbox)event.target).getLabel().equals("SP LOF") || ((Checkbox)event.target).getLabel().equals("LP LOF") || ((Checkbox)event.target).getLabel().equals("Rectangular") || ((Checkbox)event.target).getLabel().equals("Polar QTH") || ((Checkbox)event.target).getLabel().equals("North Polar") || ((Checkbox)event.target).getLabel().equals("South Polar")) {
                this.thePanel.SPMOFFlag = this.SPMOFCheckBox.getState();
                this.thePanel.LPMOFFlag = this.LPMOFCheckBox.getState();
                this.thePanel.SPLOFFlag = this.SPLOFCheckBox.getState();
                this.thePanel.LPLOFFlag = this.LPLOFCheckBox.getState();
                this.thePanel.PolarQTHFlag = this.PolarQTHCheckBox.getState();
                this.thePanel.PolarNFlag = this.PolarNCheckBox.getState();
                this.thePanel.PolarSFlag = this.PolarSCheckBox.getState();
                this.thePanel.DisplayFlagsChanged();
                return true;
            }
            if (((Checkbox)event.target).getLabel().equals("Sun")) {
                this.thePanel.SunFlag = this.SunCheckBox.getState();
                this.thePanel.DisplayFlagsChanged();
                return true;
            }
            if (((Checkbox)event.target).getLabel().equals("QTH")) {
                this.thePanel.QTHFlag = this.QTHCheckBox.getState();
                this.thePanel.DisplayFlagsChanged();
                return true;
            }
            if (((Checkbox)event.target).getLabel().equals("Paths")) {
                this.thePanel.PathsFlag = this.PathsCheckBox.getState();
                this.thePanel.DisplayFlagsChanged();
                return true;
            }
        }
        if ("Compute".equals(o)) {
            this.DoIt();
            return true;
        }
        if ("Next Hour".equals(o)) {
            final int intValue = Integer.valueOf(this.TimeField.getText());
            final int n = intValue / 100;
            final int n2 = intValue - n * 100;
            int n3 = n + 1;
            if (n3 >= 24) {
                n3 = 0;
            }
            final String string = "0000" + Integer.toString(n3 * 100 + n2);
            this.TimeField.setText(string.substring(string.length() - 4));
            this.DoIt();
        }
        if ("NOW!".equals(o)) {
            final GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+0"));
            this.MonthField.setText(Integer.toString(gregorianCalendar.get(2) + 1));
            this.DayField.setText(Integer.toString(gregorianCalendar.get(5)));
            final String string2 = "0000" + Integer.toString(gregorianCalendar.get(11) * 100 + gregorianCalendar.get(12));
            this.TimeField.setText(string2.substring(string2.length() - 4));
            this.DoIt();
        }
        return false;
    }
    
    public void DoIt() {
        this.thePanel.LatDeg.SetValue(Float.valueOf(this.LatField.getText()));
        this.thePanel.LonDeg.SetValue(Float.valueOf(this.LonField.getText()));
        this.thePanel.SF.SetValue(Float.valueOf(this.SFField.getText()));
        this.thePanel.AIndex.SetValue(Float.valueOf(this.AField.getText()));
        this.thePanel.KIndex.SetValue(Float.valueOf(this.KField.getText()));
        this.thePanel.Month.SetValue(Float.valueOf(this.MonthField.getText()));
        this.thePanel.Day.SetValue(Float.valueOf(this.DayField.getText()));
        final int intValue = Integer.valueOf(this.TimeField.getText());
        final int n = intValue / 100;
        this.thePanel.Time.SetValue(n + (intValue - n * 100) / 60.0f);
        this.thePanel.SPMOFFlag = this.SPMOFCheckBox.getState();
        this.thePanel.LPMOFFlag = this.LPMOFCheckBox.getState();
        this.thePanel.SPLOFFlag = this.SPLOFCheckBox.getState();
        this.thePanel.LPLOFFlag = this.LPLOFCheckBox.getState();
        this.thePanel.SunFlag = this.SunCheckBox.getState();
        this.thePanel.QTHFlag = this.QTHCheckBox.getState();
        this.thePanel.PathsFlag = this.PathsCheckBox.getState();
        this.thePanel.DoComputation();
    }
    
    public void SetQTH(final float n, final float n2) {
        this.LatField.setText(Float.toString(n));
        this.LonField.setText(Float.toString(n2));
    }
    
    static {
        mofjava.WindowWidth = 620;
        mofjava.WindowHeight = 450;
    }
}
