import javax.swing.event.ChangeEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.event.ChangeListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import javax.swing.ScrollPaneLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanel extends JPanel
{
    public static final int MAX_EQUATIONS = 5;
    private final String[] FUNCTION_LABELS;
    private final String[] FUNCTION_NAMES;
    private final String[] OPERATOR_LABELS;
    private final String[] OPERATOR_NAMES;
    private final String[] NUMBER_LABELS;
    private final String[] OTHER_LABELS;
    private final String[] OTHER_NAMES;
    private final Font FUNCTION_FONT;
    private final Font OPERATOR_FONT;
    private final Font NUMBER_FONT;
    private final Font OTHER_FONT;
    GraphingWindow win;
    private int shiftAmt;
    private int activeField;
    private int activeColorButton;
    private JPanel equationContainerPanel;
    private JPanel graphButtonPanel;
    private JPanel zoomPanel;
    private JPanel locationPanel;
    private JPanel calcPanel;
    private JScrollPane equationPane;
    private JPanel[] equationPanel;
    private JCheckBox[] equationCheckBox;
    private JLabel[] dependentVariable;
    private JTextField[] equationInput;
    private JButton[] equationOptionButton;
    private JButton clearButton;
    private JButton graphButton;
    private JSlider zoom;
    private JLabel centerAtLabel;
    private JTextField centerXField;
    private JTextField centerYField;
    private JButton setCenterButton;
    private JPanel navigationPanel;
    private JPanel centerInputPanel;
    private ArrowButton moveUpButton;
    private ArrowButton moveDownButton;
    private ArrowButton moveLeftButton;
    private ArrowButton moveRightButton;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton[] operatorButtons;
    private JButton[] otherButtons;
    
    public ControlPanel(final GraphingWindow win) {
        this.FUNCTION_LABELS = new String[] { "log(x)", "ln(x)", "sqrt(x)", "e^(x)", "sin(x)", "cos(x)", "tan(x)", "nCr(x,r)", "asin(x)", "acos(x)", "atan(x)" };
        this.FUNCTION_NAMES = new String[] { "log", "ln", "sqrt", "e^", "sin", "cos", "tan", "ncr", "asin", "acos", "atan" };
        this.OPERATOR_LABELS = new String[] { "^", "*", "+", "/", "-" };
        this.OPERATOR_NAMES = new String[] { "power", "mult", "plus", "div", "minus" };
        this.NUMBER_LABELS = new String[] { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "." };
        this.OTHER_LABELS = new String[] { "X", "pi", ",", "(", ")" };
        this.OTHER_NAMES = new String[] { "var", "pi", "comma", "openP", "closeP" };
        this.FUNCTION_FONT = new Font("Helvetica", 0, 10);
        this.OPERATOR_FONT = new Font("Mono", 0, 14);
        this.NUMBER_FONT = new Font("Times", 1, 12);
        this.OTHER_FONT = new Font("Arial", 1, 12);
        this.setLayout(new BoxLayout(this, 1));
        this.win = win;
        this.shiftAmt = 2;
        this.activeField = 0;
        this.activeColorButton = 0;
    }
    
    public void initializeComponents() {
        this.initializeEquationPanel();
        this.initializeGraphButtonPanel();
        this.initializeZoomPanel();
        this.initializeLocationPanel();
        this.initializeCalcPanel();
        this.add(this.equationPane);
        this.add(this.graphButtonPanel);
        this.add(this.zoomPanel);
        this.add(this.locationPanel);
        this.add(this.calcPanel);
    }
    
    public void reset() {
        for (int i = 0; i < 5; ++i) {
            this.equationInput[i].setText("");
            this.win.getGraphArea().setEquation(null, i);
            this.win.getGraphArea().getCurves()[i].setColor(GraphArea.colors[i]);
        }
        this.activeField = 0;
        this.shiftAmt = 2;
        this.centerXField.setText("0");
        this.centerYField.setText("0");
        this.zoom.setValue(20);
        this.win.getGraphArea().setCenter(0, 0);
        this.win.getGraphArea().setPixelsPerUnit(20);
        this.win.getGraphArea().setBackgroundColor(new Color(255, 255, 255));
        this.win.getGraphArea().setGridVisible(true);
        this.win.getGraphArea().setGridColor(new Color(230, 230, 230));
        this.win.getGraphArea().repaint();
    }
    
    public void setCenter(final int n, final int n2) {
        this.centerXField.setText(new Integer(n).toString());
        this.centerYField.setText(new Integer(n2).toString());
    }
    
    public void setShiftAmout(final int shiftAmt) {
        this.shiftAmt = shiftAmt;
    }
    
    public void copyContents() {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final StringSelection stringSelection = new StringSelection(this.equationInput[this.activeField].getText());
        defaultToolkit.getSystemClipboard().setContents(stringSelection, stringSelection);
    }
    
    public void pasteContents() {
        final Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
        String s;
        try {
            s = (String)contents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception ex) {
            s = "";
        }
        this.display(s);
    }
    
    public GraphingWindow getGraphingWindow() {
        return this.win;
    }
    
    public int getShiftAmout() {
        return this.shiftAmt;
    }
    
    private void initializeEquationPanel() {
        this.equationContainerPanel = new JPanel(new GridLayout(5, 1));
        (this.equationPane = new JScrollPane(20, 31)).setLayout(new ScrollPaneLayout());
        this.equationPane.getViewport().add(this.equationContainerPanel);
        this.equationPane.setPreferredSize(new Dimension(260, 120));
        this.equationPane.setBorder(new TitledBorder(null, "Equations"));
        this.equationPanel = new JPanel[5];
        this.equationCheckBox = new JCheckBox[5];
        this.dependentVariable = new JLabel[5];
        this.equationInput = new JTextField[5];
        this.equationOptionButton = new JButton[5];
        for (int i = 0; i < 5; ++i) {
            this.equationPanel[i] = new JPanel(new FlowLayout(0));
            (this.equationCheckBox[i] = new JCheckBox("")).setSelected(true);
            this.equationCheckBox[i].addActionListener(new VisibleSelectionListener());
            (this.dependentVariable[i] = new JLabel("<html>y<sub>" + (i + 1) + "</sub> = </html>")).setFont(new Font("Arial", 0, 12));
            (this.equationInput[i] = new JTextField(10)).addFocusListener(new EquationFocusListener());
            this.equationInput[i].addActionListener(new EquationEnteredListener());
            (this.equationOptionButton[i] = new JButton("Colour")).setFont(new Font("Arial", 0, 10));
            this.equationOptionButton[i].setPreferredSize(new Dimension(70, 20));
            this.equationOptionButton[i].addActionListener(new ColorChooseListener());
            this.equationPanel[i].add(this.equationCheckBox[i]);
            this.equationPanel[i].add(this.dependentVariable[i]);
            this.equationPanel[i].add(this.equationInput[i]);
            this.equationPanel[i].add(this.equationOptionButton[i]);
            this.equationContainerPanel.add(this.equationPanel[i]);
        }
    }
    
    private void initializeGraphButtonPanel() {
        this.graphButtonPanel = new JPanel(new FlowLayout());
        (this.graphButton = new JButton("Graph")).setBackground(new Color(0, 0, 145));
        this.graphButton.setForeground(new Color(255, 255, 255));
        this.graphButton.setPreferredSize(new Dimension(90, 30));
        this.graphButton.setFont(new Font("Times", 1, 14));
        this.graphButton.addActionListener(new GraphButtonListener());
        (this.clearButton = new JButton("Clear")).setBackground(new Color(0, 100, 40));
        this.clearButton.setForeground(new Color(255, 255, 255));
        this.clearButton.setPreferredSize(new Dimension(90, 30));
        this.clearButton.setFont(new Font("Times", 1, 14));
        this.clearButton.addActionListener(new ClearButtonListener());
        this.graphButtonPanel.add(this.clearButton);
        this.graphButtonPanel.add(this.graphButton);
    }
    
    private void initializeZoomPanel() {
        this.zoomPanel = new JPanel(new FlowLayout());
        (this.zoom = new JSlider(0, 0, 100, 20)).setMajorTickSpacing(20);
        this.zoom.setMinorTickSpacing(10);
        this.zoom.setPaintTicks(true);
        this.zoom.setPaintLabels(true);
        this.zoom.setPaintTrack(true);
        this.zoom.addChangeListener(new ZoomListener());
        this.zoomPanel.add(this.zoom);
        this.zoomPanel.setBorder(new TitledBorder(null, "Zoom"));
    }
    
    private void initializeLocationPanel() {
        this.locationPanel = new JPanel(new FlowLayout());
        this.centerInputPanel = new JPanel(new FlowLayout());
        this.centerAtLabel = new JLabel("Center");
        (this.centerXField = new JTextField(3)).setHorizontalAlignment(4);
        this.centerXField.setText("0");
        this.centerXField.addActionListener(new CenterButtonListener());
        (this.centerYField = new JTextField(3)).setHorizontalAlignment(4);
        this.centerYField.setText("0");
        this.centerYField.addActionListener(new CenterButtonListener());
        (this.setCenterButton = new JButton("OK")).addActionListener(new CenterButtonListener());
        this.centerInputPanel.add(this.centerAtLabel);
        this.centerInputPanel.add(this.centerXField);
        this.centerInputPanel.add(this.centerYField);
        this.centerInputPanel.add(this.setCenterButton);
        this.navigationPanel = new JPanel(new BorderLayout());
        (this.moveUpButton = new ArrowButton(ArrowButton.UP_ARROW)).addActionListener(new MoveUpButtonListener());
        (this.moveDownButton = new ArrowButton(ArrowButton.DOWN_ARROW)).addActionListener(new MoveDownButtonListener());
        (this.moveLeftButton = new ArrowButton(ArrowButton.LEFT_ARROW)).addActionListener(new MoveLeftButtonListener());
        (this.moveRightButton = new ArrowButton(ArrowButton.RIGHT_ARROW)).addActionListener(new MoveRightButtonListener());
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.moveUpButton, "North");
        panel.add(this.moveDownButton, "South");
        final JPanel panel2 = new JPanel(new GridLayout(3, 1));
        panel2.add(new JLabel(""));
        panel2.add(this.moveLeftButton);
        panel2.add(new JLabel(""));
        final JPanel panel3 = new JPanel(new GridLayout(3, 1));
        panel3.add(new JLabel(""));
        panel3.add(this.moveRightButton);
        panel3.add(new JLabel(""));
        this.navigationPanel.add(panel2, "West");
        this.navigationPanel.add(panel, "Center");
        this.navigationPanel.add(panel3, "East");
        this.locationPanel.add(this.centerInputPanel);
        this.locationPanel.add(this.navigationPanel);
        this.locationPanel.setBorder(new TitledBorder(null, "Navigation"));
    }
    
    private void initializeCalcPanel() {
        (this.calcPanel = new JPanel(new GridLayout(8, 4))).setPreferredSize(new Dimension(260, 200));
        this.functionButtons = new JButton[this.FUNCTION_NAMES.length];
        for (int i = 0; i < this.FUNCTION_NAMES.length; ++i) {
            (this.functionButtons[i] = new JButton(this.FUNCTION_LABELS[i])).setName(this.FUNCTION_NAMES[i]);
            this.functionButtons[i].setFont(this.FUNCTION_FONT);
            this.functionButtons[i].setBackground(new Color(204, 204, 204));
            this.functionButtons[i].setForeground(new Color(0, 51, 51));
            this.functionButtons[i].setFocusable(false);
            this.functionButtons[i].addActionListener(new FunctionButtonListener());
            this.calcPanel.add(this.functionButtons[i]);
        }
        this.otherButtons = new JButton[this.OTHER_NAMES.length];
        for (int j = 0; j < this.OTHER_NAMES.length; ++j) {
            (this.otherButtons[j] = new JButton(this.OTHER_LABELS[j])).setName(this.OTHER_NAMES[j]);
            if (this.OTHER_LABELS[j].equals("X")) {
                this.otherButtons[j].setBackground(new Color(0, 0, 130));
            }
            else {
                this.otherButtons[j].setBackground(new Color(153, 153, 153));
            }
            this.otherButtons[j].setForeground(new Color(255, 255, 255));
            this.otherButtons[j].setFont(this.OTHER_FONT);
            this.otherButtons[j].setFocusable(false);
            this.otherButtons[j].addActionListener(new OtherButtonListener());
            this.calcPanel.add(this.otherButtons[j]);
        }
        this.numberButtons = new JButton[this.NUMBER_LABELS.length];
        this.operatorButtons = new JButton[this.OPERATOR_NAMES.length];
        int n = 0;
        int n2 = 0;
        for (int k = 0; k < this.NUMBER_LABELS.length + this.OPERATOR_NAMES.length; ++k) {
            if ((k + 1) % 4 == 0 || n == this.NUMBER_LABELS.length) {
                (this.operatorButtons[n2] = new JButton(this.OPERATOR_LABELS[n2])).setBackground(new Color(60, 60, 60));
                this.operatorButtons[n2].setForeground(new Color(255, 255, 255));
                this.operatorButtons[n2].setFont(this.OPERATOR_FONT);
                this.operatorButtons[n2].setFocusable(false);
                this.operatorButtons[n2].addActionListener(new OperatorButtonListener());
                this.calcPanel.add(this.operatorButtons[n2]);
                ++n2;
            }
            else {
                (this.numberButtons[n] = new JButton(this.NUMBER_LABELS[n])).setBackground(new Color(130, 130, 130));
                this.numberButtons[n].setForeground(new Color(255, 255, 255));
                this.numberButtons[n].setFont(this.NUMBER_FONT);
                this.numberButtons[n].setFocusable(false);
                this.numberButtons[n].addActionListener(new NumberButtonListener());
                this.calcPanel.add(this.numberButtons[n]);
                ++n;
            }
        }
    }
    
    private void display(final String s) {
        final int caretPosition = this.equationInput[this.activeField].getCaretPosition();
        final JTextField textField = this.equationInput[this.activeField];
        if (caretPosition == 0 && !textField.getCaret().isVisible()) {
            textField.setText(textField.getText() + s);
        }
        else {
            textField.setText(textField.getText().substring(0, caretPosition) + s + textField.getText().substring(caretPosition, textField.getText().length()));
            textField.setCaretPosition(caretPosition + s.length());
        }
    }
    
    private class VisibleSelectionListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            int n = 0;
            final JCheckBox checkBox = (JCheckBox)actionEvent.getSource();
            for (int i = 0; i < 5; ++i) {
                if (checkBox == ControlPanel.this.equationCheckBox[i]) {
                    n = i;
                }
            }
            if (ControlPanel.this.equationCheckBox[n].isSelected()) {
                ControlPanel.this.win.getGraphArea().getCurves()[n].setVisibility(true);
            }
            else {
                ControlPanel.this.win.getGraphArea().getCurves()[n].setVisibility(false);
            }
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class EquationFocusListener implements FocusListener
    {
        public void focusGained(final FocusEvent focusEvent) {
            for (int i = 0; i < 5; ++i) {
                if (focusEvent.getSource() == ControlPanel.this.equationInput[i]) {
                    ControlPanel.this.activeField = i;
                    break;
                }
            }
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
    }
    
    private class ColorChooseListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            for (int i = 0; i < 5; ++i) {
                if (actionEvent.getSource() == ControlPanel.this.equationOptionButton[i]) {
                    ControlPanel.this.activeColorButton = i;
                    break;
                }
            }
            final ColorFrame colorFrame = new ColorFrame(ControlPanel.this.win.getGraphArea(), ControlPanel.this.activeColorButton);
        }
    }
    
    private class ZoomListener implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            if (ControlPanel.this.zoom.getValue() > 2) {
                ControlPanel.this.win.getGraphArea().setPixelsPerUnit(ControlPanel.this.zoom.getValue());
            }
            else {
                ControlPanel.this.win.getGraphArea().setPixelsPerUnit(3);
            }
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class EquationEnteredListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextField textField = (JTextField)actionEvent.getSource();
            int n = 2;
            for (int i = 0; i < 5; ++i) {
                if (ControlPanel.this.equationInput[i] == textField) {
                    n = i;
                }
            }
            if (textField.getText().equals("") || textField.getText() == null) {
                ControlPanel.this.win.getGraphArea().setEquation(null, n);
            }
            else {
                ControlPanel.this.win.getGraphArea().setEquation(textField.getText(), n);
            }
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class GraphButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            for (int i = 0; i < 5; ++i) {
                if (ControlPanel.this.equationInput[i].getText().equals("") || ControlPanel.this.equationInput[i].getText() == null) {
                    ControlPanel.this.win.getGraphArea().setEquation(null, i);
                }
                else {
                    ControlPanel.this.win.getGraphArea().setEquation(ControlPanel.this.equationInput[i].getText(), i);
                }
            }
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class ClearButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            for (int i = 0; i < 5; ++i) {
                ControlPanel.this.equationInput[i].setText("");
                ControlPanel.this.win.getGraphArea().setEquation(null, i);
            }
            ControlPanel.this.activeField = 0;
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class CenterButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            int n;
            try {
                n = (int)Math.round(new Double(ControlPanel.this.centerXField.getText()));
                ControlPanel.this.centerXField.setText(new Integer(n).toString());
            }
            catch (Exception ex) {
                n = (int)ControlPanel.this.win.getGraphArea().getCenter().getWidth();
                ControlPanel.this.centerXField.setText(new Double(ControlPanel.this.win.getGraphArea().getCenter().getWidth()).toString());
            }
            int n2;
            try {
                n2 = (int)Math.round(new Double(ControlPanel.this.centerYField.getText()));
                ControlPanel.this.centerYField.setText(new Integer(n2).toString());
            }
            catch (Exception ex2) {
                n2 = (int)ControlPanel.this.win.getGraphArea().getCenter().getHeight();
                ControlPanel.this.centerYField.setText(new Double(ControlPanel.this.win.getGraphArea().getCenter().getHeight()).toString());
            }
            ControlPanel.this.win.getGraphArea().setCenter(n, n2);
        }
    }
    
    private class MoveUpButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Dimension center = ControlPanel.this.win.getGraphArea().getCenter();
            center.setSize(center.getWidth(), center.getHeight() + ControlPanel.this.shiftAmt);
            ControlPanel.this.centerYField.setText(new Double(center.getHeight()).toString());
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class MoveDownButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Dimension center = ControlPanel.this.win.getGraphArea().getCenter();
            center.setSize(center.getWidth(), center.getHeight() - ControlPanel.this.shiftAmt);
            ControlPanel.this.centerYField.setText(new Double(center.getHeight()).toString());
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class MoveLeftButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Dimension center = ControlPanel.this.win.getGraphArea().getCenter();
            center.setSize(center.getWidth() - ControlPanel.this.shiftAmt, center.getHeight());
            ControlPanel.this.centerXField.setText(new Double(center.getWidth()).toString());
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class MoveRightButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Dimension center = ControlPanel.this.win.getGraphArea().getCenter();
            center.setSize(center.getWidth() + ControlPanel.this.shiftAmt, center.getHeight());
            ControlPanel.this.centerXField.setText(new Double(center.getWidth()).toString());
            ControlPanel.this.win.getGraphArea().repaint();
        }
    }
    
    private class FunctionButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ControlPanel.this.display(((JButton)actionEvent.getSource()).getName() + "(");
        }
    }
    
    private class NumberButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JButton button = (JButton)actionEvent.getSource();
            for (int i = 0; i < ControlPanel.this.numberButtons.length; ++i) {
                if (button == ControlPanel.this.numberButtons[i]) {
                    ControlPanel.this.display(ControlPanel.this.NUMBER_LABELS[i]);
                    break;
                }
            }
        }
    }
    
    private class OperatorButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JButton button = (JButton)actionEvent.getSource();
            for (int i = 0; i < ControlPanel.this.operatorButtons.length; ++i) {
                if (button == ControlPanel.this.operatorButtons[i]) {
                    ControlPanel.this.display(ControlPanel.this.OPERATOR_LABELS[i]);
                    break;
                }
            }
        }
    }
    
    private class OtherButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JButton button = (JButton)actionEvent.getSource();
            int i = 0;
            while (i < ControlPanel.this.otherButtons.length) {
                if (button == ControlPanel.this.otherButtons[i]) {
                    if (ControlPanel.this.OTHER_LABELS[i].equals("X")) {
                        ControlPanel.this.display("x");
                        break;
                    }
                    ControlPanel.this.display(ControlPanel.this.OTHER_LABELS[i]);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
    }
}
