import java.text.SimpleDateFormat;
import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.Calendar;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GetDate extends Applet
{
    private boolean isStandalone;
    Choice choiceDay;
    Choice choiceOccurrence;
    Label label3;
    Choice choiceMonth;
    FlowLayout flowLayout;
    BorderLayout borderLayout;
    Panel panelTop;
    Panel panelCenter;
    Label labelResult;
    Label label2;
    TextField textFieldYear;
    Button buttonGetDate;
    
    public GetDate() {
        this.isStandalone = false;
        this.choiceDay = new Choice();
        this.choiceOccurrence = new Choice();
        this.label3 = new Label();
        this.choiceMonth = new Choice();
        this.flowLayout = new FlowLayout();
        this.borderLayout = new BorderLayout();
        this.panelTop = new Panel();
        this.panelCenter = new Panel();
        this.labelResult = new Label();
        this.label2 = new Label();
        this.textFieldYear = new TextField();
        this.buttonGetDate = new Button();
    }
    
    public void init() {
        try {
            this.jbInit();
            this.choiceOccurrence.add("First");
            this.choiceOccurrence.add("Second");
            this.choiceOccurrence.add("Third");
            this.choiceOccurrence.add("Fourth");
            this.choiceOccurrence.add("Fifth");
            this.choiceOccurrence.add("Sixth");
            this.choiceOccurrence.add("Seventh");
            this.choiceDay.add("Sunday");
            this.choiceDay.add("Monday");
            this.choiceDay.add("Tueday");
            this.choiceDay.add("Wednesday");
            this.choiceDay.add("Thurday");
            this.choiceDay.add("Friday");
            this.choiceDay.add("Saturday");
            this.choiceMonth.add("January");
            this.choiceMonth.add("February");
            this.choiceMonth.add("March");
            this.choiceMonth.add("April");
            this.choiceMonth.add("May");
            this.choiceMonth.add("June");
            this.choiceMonth.add("July");
            this.choiceMonth.add("August");
            this.choiceMonth.add("September");
            this.choiceMonth.add("October");
            this.choiceMonth.add("November");
            this.choiceMonth.add("December");
            this.textFieldYear.setText(String.valueOf(Calendar.getInstance().get(1)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setBackground(Color.white);
        this.setLayout(this.borderLayout);
        this.label3.setText("of");
        this.labelResult.setFont(new Font("Dialog", 1, 16));
        this.labelResult.setText("Usage: Select your query and click the \"Get Date\" button.");
        this.label2.setText("year");
        this.textFieldYear.setText("textField1");
        this.buttonGetDate.setLabel("Get Date");
        this.panelTop.setLayout(this.flowLayout);
        this.panelTop.add(this.choiceOccurrence, null);
        this.panelTop.add(this.choiceDay, null);
        this.panelTop.add(this.label3, null);
        this.panelTop.add(this.choiceMonth, null);
        this.panelTop.add(this.label2, null);
        this.panelTop.add(this.textFieldYear, null);
        this.panelTop.add(this.buttonGetDate, null);
        this.panelCenter.setLayout(this.flowLayout);
        this.panelCenter.add(this.labelResult, null);
        this.add("North", this.panelTop);
        this.add("Center", this.panelCenter);
    }
    
    public String getAppletInfo() {
        return "Stupid Date Trick";
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.buttonGetDate)) {
            this.getDate();
        }
        return true;
    }
    
    private void getDate() {
        final Calendar instance = Calendar.getInstance();
        final String text = this.textFieldYear.getText();
        final int selectedIndex = this.choiceMonth.getSelectedIndex();
        final int n = this.choiceDay.getSelectedIndex() + 1;
        final int n2 = this.choiceOccurrence.getSelectedIndex() + 1;
        if (text != null && text.length() > 0) {
            try {
                instance.set(Integer.parseInt(text), selectedIndex, 1, 0, 0, 0);
                while (instance.get(7) < n) {
                    instance.add(7, 1);
                }
                for (int i = 0; i < n2 - 1; ++i) {
                    for (int j = 0; j < 7; ++j) {
                        instance.add(5, 1);
                    }
                }
                if (instance.get(2) == selectedIndex) {
                    this.setResult(new SimpleDateFormat("EEE, MMM dd, yyyy").format(instance.getTime()));
                    System.out.println(instance.getTime().toString());
                    return;
                }
                this.setResult("Date overlaps months.");
                return;
            }
            catch (Exception ex) {
                this.setResult("Invalid Year.");
                return;
            }
        }
        this.setResult("Invalid Year.");
    }
    
    private void setResult(final String s) {
        this.labelResult.setText("                                " + s);
    }
}
