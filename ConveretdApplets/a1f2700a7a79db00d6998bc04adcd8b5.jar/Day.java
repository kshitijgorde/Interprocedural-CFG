import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Day extends Applet
{
    int[] monthRemainders;
    int finalRemainder;
    int iter;
    int DD;
    int MM;
    int YY;
    String[] days;
    TextField textDD;
    TextField textMM;
    TextField textYY;
    TextField textDay;
    Label labelTitle;
    Label labelCopyright;
    Label labelDate;
    Label labelInfo;
    Label labelDD;
    Label labelMM;
    Label labelYY;
    Label labelDay;
    Label labelBlank1;
    Label labelBlank2;
    Label labelStatus;
    Button buttonRun;
    Button buttonClear;
    Panel pane1;
    Panel pane2;
    FlowLayout fl;
    GridLayout gl1;
    GridLayout gl2;
    
    public void init() {
        this.pane1.setLayout(this.gl1);
        this.pane1.add(this.labelTitle);
        this.pane1.add(this.labelCopyright);
        this.pane1.add(this.labelDate);
        this.pane1.add(this.labelInfo);
        this.pane2.setLayout(this.gl2);
        this.pane2.add(this.labelDD);
        this.pane2.add(this.textDD);
        this.pane2.add(this.labelMM);
        this.pane2.add(this.textMM);
        this.pane2.add(this.labelYY);
        this.pane2.add(this.textYY);
        this.pane2.add(this.buttonRun);
        this.pane2.add(this.buttonClear);
        this.pane2.add(this.labelBlank1);
        this.pane2.add(this.labelBlank2);
        this.pane2.add(this.labelDay);
        this.textDay.setEditable(false);
        this.pane2.add(this.textDay);
        this.setLayout(this.fl);
        this.add(this.pane1);
        this.add(this.pane2);
        this.add(this.labelStatus);
    }
    
    public void paint(final Graphics graphics) {
        this.setBackground(new Color(65, 105, 225));
        this.pane1.setBackground(new Color(65, 105, 225));
        this.pane2.setBackground(new Color(65, 105, 225));
        this.labelTitle.setBackground(new Color(65, 105, 225));
        this.labelCopyright.setBackground(new Color(65, 105, 225));
        this.labelDate.setBackground(new Color(65, 105, 225));
        this.labelInfo.setBackground(new Color(65, 105, 225));
        this.labelDD.setBackground(new Color(65, 105, 225));
        this.labelMM.setBackground(new Color(65, 105, 225));
        this.labelYY.setBackground(new Color(65, 105, 225));
        this.labelDay.setBackground(new Color(65, 105, 225));
        this.labelBlank1.setBackground(new Color(65, 105, 225));
        this.labelBlank2.setBackground(new Color(65, 105, 225));
        this.labelStatus.setBackground(new Color(65, 105, 225));
        this.textDD.setBackground(new Color(255, 255, 255));
        this.textMM.setBackground(new Color(255, 255, 255));
        this.textYY.setBackground(new Color(255, 255, 255));
        this.textDay.setBackground(new Color(255, 255, 255));
        this.labelTitle.setFont(new Font("Arial", 1, 17));
        this.labelCopyright.setFont(new Font("Arial", 2, 9));
        this.labelDate.setFont(new Font("Arial", 1, 15));
        this.labelInfo.setFont(new Font("Arial", 1, 9));
        this.labelDD.setFont(new Font("Arial", 1, 12));
        this.labelMM.setFont(new Font("Arial", 1, 12));
        this.labelYY.setFont(new Font("Arial", 1, 12));
        this.labelDay.setFont(new Font("Arial", 1, 15));
        this.labelStatus.setFont(new Font("Arial", 1, 12));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.buttonRun) {
            this.DD = Integer.parseInt(this.textDD.getText());
            this.MM = Integer.parseInt(this.textMM.getText());
            this.YY = Integer.parseInt(this.textYY.getText());
            if (this.DD < 1 || this.DD > 31) {
                this.clearAll();
                this.labelStatus.setText("Invalid DATE!");
            }
            else if (this.MM < 1 || this.MM > 12) {
                this.clearAll();
                this.labelStatus.setText("Invalid MONTH!");
            }
            else if (this.YY < 1900 || this.YY > 9999) {
                this.clearAll();
                this.labelStatus.setText("Invalid YEAR!");
            }
            else if ((this.MM == 1 || this.MM == 3 || this.MM == 5 || this.MM == 7 || this.MM == 8 || this.MM == 10 || this.MM == 12) && this.DD > 31) {
                this.clearAll();
                this.labelStatus.setText("Invalid DATE!");
            }
            else if ((this.MM == 4 || this.MM == 6 || this.MM == 9 || this.MM == 11) && this.DD > 30) {
                this.clearAll();
                this.labelStatus.setText("Invalid DATE!");
            }
            else if (this.MM == 2 && this.DD > 29) {
                this.clearAll();
                this.labelStatus.setText("Invalid DATE!");
            }
            else if (this.MM == 2 && this.DD == 29 && (this.YY - 1900) % 4 != 0) {
                this.clearAll();
                this.labelStatus.setText("Invalid DATE!");
            }
            else {
                this.finalRemainder = this.YY - 1900;
                this.finalRemainder += (this.finalRemainder - 1) / 4;
                this.iter = 0;
                while (this.iter < this.MM - 1) {
                    this.finalRemainder += this.monthRemainders[this.iter];
                    ++this.iter;
                }
                if ((this.YY - 1900) % 4 == 0 && this.MM > 2) {
                    ++this.finalRemainder;
                }
                this.finalRemainder += this.DD;
                this.finalRemainder %= 7;
                this.textDay.setText(this.days[this.finalRemainder]);
                this.labelStatus.setText("Press CLEAR...");
            }
        }
        else {
            if (event.target != this.buttonClear) {
                return false;
            }
            this.clearAll();
        }
        return true;
    }
    
    public void clearAll() {
        this.textDD.setText("");
        this.textMM.setText("");
        this.textYY.setText("");
        this.textDay.setText("");
        this.labelStatus.setText(" Enter DATE... ");
    }
    
    public Day() {
        this.monthRemainders = new int[] { 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3 };
        this.days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        this.textDD = new TextField(2);
        this.textMM = new TextField(2);
        this.textYY = new TextField(4);
        this.textDay = new TextField(9);
        this.labelTitle = new Label("DATE-2-DAY", 1);
        this.labelCopyright = new Label("© Copyright CSK.", 1);
        this.labelDate = new Label(" Enter Date :", 0);
        this.labelInfo = new Label(" (Between 1900 A.D. and 9999 A.D.)", 0);
        this.labelDD = new Label(" (DD) : ", 2);
        this.labelMM = new Label(" (MM) : ", 2);
        this.labelYY = new Label(" (YYYY) : ", 2);
        this.labelDay = new Label(" Day : ", 2);
        this.labelBlank1 = new Label(" ");
        this.labelBlank2 = new Label(" ");
        this.labelStatus = new Label(" Enter DATE... ", 1);
        this.buttonRun = new Button("Run");
        this.buttonClear = new Button("Clear");
        this.pane1 = new Panel();
        this.pane2 = new Panel();
        this.fl = new FlowLayout(1, 0, 0);
        this.gl1 = new GridLayout(6, 1);
        this.gl2 = new GridLayout(8, 2);
    }
}
