import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GPACalc extends JApplet implements ActionListener
{
    int letterGrade;
    double attempted;
    double points;
    double[] gradeArray;
    String[] letterGradeArray;
    JButton nextButton;
    JButton figGPAButton;
    JButton clearAllButton;
    JButton clearLastButton;
    JButton authorInfo;
    JTextField hours;
    JLabel gradeLabel;
    JLabel hoursLabel;
    JTextArea tArea;
    JScrollPane scroll;
    JComboBox grade;
    
    public GPACalc() {
        this.letterGrade = 0;
        this.gradeArray = new double[] { 4.0, 3.0, 2.0, 1.0, 0.0 };
        this.letterGradeArray = new String[] { "A", "B", "C", "D", "F" };
        this.nextButton = new JButton("Next");
        this.figGPAButton = new JButton("GPA");
        this.clearAllButton = new JButton("Clear All");
        this.clearLastButton = new JButton("Clear Last");
        this.hours = new JTextField(2);
        this.gradeLabel = new JLabel("Grade:");
        this.hoursLabel = new JLabel("Hours:");
        this.tArea = new JTextArea(5, 5);
        this.scroll = new JScrollPane(this.tArea, 22, 31);
        this.grade = new JComboBox();
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        this.grade.addItem("A");
        this.grade.addItem("B");
        this.grade.addItem("C");
        this.grade.addItem("D");
        this.grade.addItem("F");
        contentPane.setBackground(Color.white);
        contentPane.add(this.gradeLabel);
        contentPane.add(this.grade);
        contentPane.add(this.hoursLabel);
        contentPane.add(this.hours);
        contentPane.add(this.nextButton);
        contentPane.add(this.clearLastButton);
        contentPane.add(this.figGPAButton);
        contentPane.add(this.clearAllButton);
        contentPane.add(this.scroll);
        this.nextButton.addActionListener(this);
        this.clearLastButton.addActionListener(this);
        this.clearAllButton.addActionListener(this);
        this.figGPAButton.addActionListener(this);
        this.grade.addActionListener(this);
        final Insets insets = contentPane.getInsets();
        this.gradeLabel.setBounds(50 + insets.left, 20 + insets.top, 75, 20);
        this.grade.setBounds(95 + insets.left, 20 + insets.top, 40, 20);
        this.hoursLabel.setBounds(50 + insets.left, 55 + insets.top, 75, 20);
        this.hours.setBounds(95 + insets.left, 55 + insets.top, 40, 20);
        this.nextButton.setBounds(200 + insets.left, 55 + insets.top, 120, 20);
        this.clearLastButton.setBounds(200 + insets.left, 90 + insets.top, 120, 20);
        this.figGPAButton.setBounds(200 + insets.left, 125 + insets.top, 120, 20);
        this.clearAllButton.setBounds(200 + insets.left, 20 + insets.top, 120, 20);
        this.scroll.setBounds(50 + insets.left, 90 + insets.top, 120, 120);
        this.tArea.setEditable(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.nextButton) {
            if (this.hours.getText().length() < 1) {
                JOptionPane.showMessageDialog(null, "No Data in Hours Field.", "Error", 0);
            }
            else if (!this.checkHoursField(this.hours.getText())) {
                JOptionPane.showMessageDialog(null, "Please only numbers in the Hours Field.\nOne Decimal may also be used.", "Error", 0);
            }
            else {
                this.tArea.append(this.grade.getSelectedItem() + "-" + this.hours.getText() + "\n");
                this.hours.setText("");
            }
        }
        else if (source == this.clearLastButton) {
            final String text = this.tArea.getText();
            final int n = text.lastIndexOf("-") - 1;
            if (n != -2) {
                this.tArea.setText(text.substring(0, n));
            }
            this.hours.setText("");
            this.grade.setSelectedIndex(0);
        }
        else if (source == this.clearAllButton) {
            this.tArea.setText("");
            this.hours.setText("");
            this.grade.setSelectedIndex(0);
            this.attempted = 0.0;
            this.points = 0.0;
        }
        else if (source == this.figGPAButton) {
            this.calcGPA(this.tArea.getText());
        }
        else if (source == this.grade) {
            this.letterGrade = this.grade.getSelectedIndex();
        }
    }
    
    public boolean checkHoursField(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                if (s.charAt(i) != '.') {
                    return false;
                }
                if (++n > 1 || s.length() == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void calcGPA(final String s) {
        final String[] split = s.split("\n");
        for (int i = 0; i < split.length; ++i) {
            final String[] split2 = split[i].split("-");
            for (int j = 0; j < 5; ++j) {
                if (split2[0].compareTo(this.letterGradeArray[j]) == 0) {
                    final Double value = Double.valueOf(split2[1]);
                    this.attempted += value;
                    this.points += value * this.gradeArray[j];
                }
            }
        }
        this.tArea.setText("Attempted: " + this.attempted + "\n" + "Points: " + this.points + "\n\n" + "GPA: " + this.points / this.attempted);
    }
}
