import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import MathFunction.MathFunction;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MathPlot extends JApplet implements ActionListener
{
    Container pane;
    private GraphCanvas graphCanvas;
    private JPanel optionsPanel;
    private JPanel functionPanel;
    private static JTextField xField;
    private static JTextField yField;
    private JTextField firstBoundField;
    private JTextField secondBoundField;
    private JTextField resultField;
    private JTextField functionTextField;
    private JTextField zoomFactorField;
    private JButton drawFunctionButton;
    private JButton zoomOutButton;
    private JButton zoomInButton;
    private JButton integralButton;
    private JButton lengthButton;
    private JMenuBar menu;
    private static MathFunction function;
    
    public void init() {
        this.setSize(713, 500);
        (this.pane = this.getContentPane()).setLayout(new BorderLayout());
        (this.functionTextField = new JTextField(15)).addActionListener(this);
        (this.drawFunctionButton = new JButton("Draw Function")).addActionListener(this);
        (this.zoomOutButton = new JButton("out")).addActionListener(this);
        (this.zoomFactorField = new JTextField(6)).setFont(new Font("Dialog", 0, 13));
        this.zoomFactorField.addActionListener(this);
        this.zoomFactorField.setText("100.0%");
        (this.zoomInButton = new JButton("in")).addActionListener(this);
        this.firstBoundField = new JTextField(4);
        this.secondBoundField = new JTextField(4);
        (this.resultField = new JTextField(7)).setEditable(false);
        (this.integralButton = new JButton("Compute Integral")).addActionListener(this);
        (this.lengthButton = new JButton("Calculate Length")).addActionListener(this);
        (MathPlot.xField = new JTextField(4)).setEditable(false);
        (MathPlot.yField = new JTextField(4)).setEditable(false);
        this.optionsPanel = new JPanel(new GridLayout(16, 1));
        final JLabel label = new JLabel("Math Plot", 0);
        label.setFont(new Font("Dialog", 1, 28));
        this.optionsPanel.add(label);
        this.optionsPanel.add(new JLabel("Applet for drawing Graphs", 0));
        this.optionsPanel.add(new JPanel());
        (this.functionPanel = new JPanel()).add(new JLabel("f(x) ="));
        this.functionPanel.add(this.functionTextField);
        this.optionsPanel.add(this.functionPanel);
        final JPanel panel = new JPanel(new FlowLayout(1));
        panel.add(this.drawFunctionButton);
        this.optionsPanel.add(panel);
        this.optionsPanel.add(new JPanel());
        final JPanel panel2 = new JPanel(new FlowLayout(1));
        panel2.add(this.zoomOutButton);
        panel2.add(this.zoomFactorField);
        panel2.add(this.zoomInButton);
        this.optionsPanel.add(panel2);
        this.optionsPanel.add(new JPanel());
        this.optionsPanel.add(new JPanel());
        final JPanel panel3 = new JPanel(new FlowLayout(1));
        panel3.add(new JLabel("x = ["));
        panel3.add(this.firstBoundField);
        panel3.add(new JLabel(", "));
        panel3.add(this.secondBoundField);
        panel3.add(new JLabel("]"));
        this.optionsPanel.add(panel3);
        final JPanel panel4 = new JPanel(new FlowLayout(1));
        panel4.add(new JLabel("Result ="));
        panel4.add(this.resultField);
        this.optionsPanel.add(panel4);
        final JPanel panel5 = new JPanel(new FlowLayout());
        panel5.add(this.integralButton);
        this.optionsPanel.add(panel5);
        final JPanel panel6 = new JPanel(new FlowLayout());
        panel6.add(this.lengthButton);
        this.optionsPanel.add(panel6);
        this.optionsPanel.add(new JPanel());
        this.optionsPanel.add(new JPanel());
        final JPanel panel7 = new JPanel(new FlowLayout(1));
        panel7.add(new JLabel("x="));
        panel7.add(MathPlot.xField);
        panel7.add(new JLabel("y="));
        panel7.add(MathPlot.yField);
        this.optionsPanel.add(panel7);
        this.pane.add(this.optionsPanel, "West");
        this.graphCanvas = new GraphCanvas();
        this.pane.add(this.graphCanvas, "Center");
        this.setContentPane(this.pane);
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == "Draw Function" || actionEvent.getSource() == this.functionTextField) {
            final String text = this.functionTextField.getText();
            if (text != null) {
                MathPlot.function = new MathFunction(text);
                this.graphCanvas.setFunction(MathPlot.function);
            }
            this.zoomInButton.setEnabled(true);
            this.zoomOutButton.setEnabled(true);
            this.zoomFactorField.setText(String.valueOf(100.0 * this.graphCanvas.zoomFactor) + "%");
            this.resultField.setText(null);
            this.graphCanvas.repaint();
        }
        else if (actionEvent.getSource() == this.zoomOutButton) {
            if (!this.graphCanvas.zoomOut()) {
                this.zoomOutButton.setEnabled(false);
            }
            this.zoomInButton.setEnabled(true);
            this.zoomFactorField.setText(String.valueOf(100.0 / this.graphCanvas.zoomFactor) + "%");
            this.graphCanvas.repaint();
        }
        else if (actionEvent.getSource() == this.zoomInButton) {
            if (!this.graphCanvas.zoomIn()) {
                this.zoomInButton.setEnabled(false);
            }
            this.zoomOutButton.setEnabled(true);
            this.zoomFactorField.setText(String.valueOf(100.0 / this.graphCanvas.zoomFactor) + "%");
            this.graphCanvas.repaint();
        }
        else {
            if (actionCommand == "Compute Integral") {
                double double2;
                double double1 = double2 = 0.0;
                try {
                    double2 = Double.parseDouble(this.firstBoundField.getText());
                    double1 = Double.parseDouble(this.secondBoundField.getText());
                    this.graphCanvas.setAreaLimits(double2, double1);
                    this.resultField.setText(String.valueOf(MathPlot.function.integrate(double2, double1)).substring(0, 9));
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Bad or missing Integral bounds", "Math Plot", 2);
                    this.graphCanvas.setAreaLimits(0.0, 0.0);
                    this.resultField.setText(null);
                }
                catch (StringIndexOutOfBoundsException ex2) {
                    final String value = String.valueOf(MathPlot.function.integrate(double2, double1));
                    this.resultField.setText(value.substring(0, value.length()));
                }
                finally {
                    this.graphCanvas.repaint();
                }
            }
            if (actionCommand == "Calculate Length") {
                double double4;
                double double3 = double4 = 0.0;
                try {
                    double4 = Double.parseDouble(this.firstBoundField.getText());
                    double3 = Double.parseDouble(this.secondBoundField.getText());
                    this.graphCanvas.setAreaLimits(0.0, 0.0);
                    this.resultField.setText(String.valueOf(MathPlot.function.length(double4, double3)).substring(0, 9));
                }
                catch (NumberFormatException ex3) {
                    JOptionPane.showMessageDialog(this, "Bad or missing bounds", "Math Plot", 2);
                    this.resultField.setText(null);
                }
                catch (StringIndexOutOfBoundsException ex4) {
                    final String value2 = String.valueOf(MathPlot.function.length(double4, double3));
                    this.resultField.setText(value2.substring(0, value2.length()));
                }
                finally {
                    this.graphCanvas.repaint();
                }
            }
            if (actionEvent.getSource() == this.zoomFactorField) {
                this.graphCanvas.zoom(100.0 / Double.parseDouble(actionCommand.replaceAll("%", "").replaceAll(" ", "")));
                this.zoomFactorField.setText(String.valueOf(100.0 / this.graphCanvas.zoomFactor) + "%");
                this.graphCanvas.repaint();
            }
        }
    }
    
    public static void setCoordinatesTextField(final String text, final String text2) {
        MathPlot.xField.setText(text);
        MathPlot.yField.setText(text2);
    }
}
