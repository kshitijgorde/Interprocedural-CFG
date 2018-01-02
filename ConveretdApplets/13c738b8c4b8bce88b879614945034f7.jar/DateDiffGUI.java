import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.util.Date;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

final class DateDiffGUI extends JPanel
{
    Date dt;
    static JFrame frame;
    static String[] m;
    static String[] d;
    JComboBox m1;
    JComboBox m2;
    JComboBox d1;
    JComboBox d2;
    JTextField y1;
    JTextField y2;
    JLabel r;
    JLabel i;
    JButton z;
    
    public DateDiffGUI() {
        this.dt = null;
        this.z = new JButton("Calculate Difference");
        this.i = new JLabel("Please select two dates and click Calculate Difference");
        (this.r = new JLabel("The difference is...")).setAlignmentX(0.0f);
        this.r.setHorizontalAlignment(0);
        this.m1 = new JComboBox((E[])DateDiffGUI.m);
        this.m2 = new JComboBox((E[])DateDiffGUI.m);
        this.d1 = new JComboBox((E[])DateDiffGUI.d);
        this.d2 = new JComboBox((E[])DateDiffGUI.d);
        this.y1 = new JTextField("2004");
        this.y2 = new JTextField("2004");
        final JPanel panel = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        this.z.setAlignmentX(0.0f);
        this.z.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DateDiffGUI.this.calculate();
            }
        });
        panel3.setLayout(new GridLayout(2, 1));
        panel3.add(this.z);
        panel3.add(this.r);
        panel.add(this.i);
        panel2.setLayout(new GridLayout(2, 3));
        panel2.add(this.m1);
        panel2.add(this.d1);
        panel2.add(this.y1);
        panel2.add(this.m2);
        panel2.add(this.d2);
        panel2.add(this.y2);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
    }
    
    public static void main(final String[] array) {
        (DateDiffGUI.frame = new JFrame("Date Difference")).addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        DateDiffGUI.frame.setContentPane(new DateDiffGUI());
        DateDiffGUI.frame.pack();
        DateDiffGUI.frame.setVisible(true);
        DateDiffGUI.frame.setSize(350, 200);
    }
    
    public void calculate() {
        int int2;
        int int4;
        do {
            try {
                final int n = this.m1.getSelectedIndex() + 1;
                final int int1 = Integer.parseInt(String.valueOf(this.d1.getSelectedItem()));
                int2 = Integer.parseInt(this.y1.getText());
                this.validate(n, int1, int2);
                final int n2 = this.m2.getSelectedIndex() + 1;
                final int int3 = Integer.parseInt(String.valueOf(this.d2.getSelectedItem()));
                int4 = Integer.parseInt(this.y2.getText());
                this.validate(n2, int3, int4);
                final String string = n + "/" + int1 + "/" + int2;
                final String string2 = n2 + "/" + int3 + "/" + int4;
                final Date dt = this.dt;
                final long parse = Date.parse(string);
                final Date dt2 = this.dt;
                final int n3 = (int)Math.abs((parse - Date.parse(string2)) / 24L / 60L / 60L / 1000L);
                String text;
                if (n3 >= 365) {
                    text = n3 / 365 + " year(s) and " + n3 % 365 + " day(s)";
                }
                else {
                    text = n3 + " day(s)";
                }
                this.r.setText(text);
            }
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "That isn't a valid date");
                break;
            }
        } while (int2 == 0 || int4 == 0);
    }
    
    public boolean IsLeapYear(final int n) {
        return n % 400 == 0 || ((n <= 1582 || n % 100 != 0) && n % 4 == 0);
    }
    
    public void validate(final int n, final int n2, final int n3) {
        final boolean isLeapYear = this.IsLeapYear(n3);
        if (n3 < 100) {
            throw new IllegalArgumentException();
        }
        if (n == 4 || n == 6 || n == 7 || n == 11) {
            if (n2 == 31) {
                throw new IllegalArgumentException();
            }
        }
        else if (n == 2) {
            if (!isLeapYear) {
                if (n2 > 28) {
                    throw new IllegalArgumentException();
                }
            }
            else if (n2 > 29) {
                throw new IllegalArgumentException();
            }
        }
    }
    
    static {
        DateDiffGUI.m = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        DateDiffGUI.d = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
    }
}
