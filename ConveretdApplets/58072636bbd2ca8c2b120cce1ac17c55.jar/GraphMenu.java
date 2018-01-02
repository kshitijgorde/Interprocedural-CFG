import javax.swing.JColorChooser;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphMenu extends JMenuBar
{
    private final String[] fileMenuLabels;
    private final int[] fileMenuKeyEvents;
    private final String[] editMenuLabels;
    private final int[] editMenuKeyEvents;
    private final String[] helpMenuLabels;
    private final int[] helpMenuKeyEvents;
    private GraphingWindow win;
    
    public GraphMenu(final GraphingWindow win) {
        this.fileMenuLabels = new String[] { "Reset", "Open", "Save", "Print", "Exit" };
        this.fileMenuKeyEvents = new int[] { 82, 79, 83, 80, 88 };
        this.editMenuLabels = new String[] { "Copy", "Paste", "Options" };
        this.editMenuKeyEvents = new int[] { 67, 86, 84 };
        this.helpMenuLabels = new String[] { "Help", "About UBO-SOFT GraphingCalc" };
        this.helpMenuKeyEvents = new int[] { 72, 65 };
        this.win = win;
        this.initComponents();
    }
    
    private void initComponents() {
        final JMenu menu = new JMenu("File");
        final JMenu menu2 = new JMenu("Edit");
        final JMenu menu3 = new JMenu("Help");
        final JMenuItem[] array = new JMenuItem[this.fileMenuLabels.length];
        final JMenuItem[] array2 = new JMenuItem[this.editMenuLabels.length];
        final JMenuItem[] array3 = new JMenuItem[this.helpMenuLabels.length];
        for (int i = 0; i < this.fileMenuLabels.length; ++i) {
            (array[i] = new JMenuItem(this.fileMenuLabels[i])).setMnemonic(this.fileMenuKeyEvents[i]);
            array[i].setAccelerator(KeyStroke.getKeyStroke(this.fileMenuKeyEvents[i], 2));
            array[i].addActionListener(new MenuSelectedListener());
            menu.add(array[i]);
            if (i == 0) {
                menu.add(new JSeparator(0));
            }
            else if (this.win.isApplet()) {
                array[i].setEnabled(false);
            }
        }
        for (int j = 0; j < this.editMenuLabels.length; ++j) {
            (array2[j] = new JMenuItem(this.editMenuLabels[j])).setMnemonic(this.editMenuKeyEvents[j]);
            array2[j].setAccelerator(KeyStroke.getKeyStroke(this.editMenuKeyEvents[j], 2));
            array2[j].addActionListener(new MenuSelectedListener());
            menu2.add(array2[j]);
            if (j == 1) {
                menu2.add(new JSeparator(0));
            }
            if (j < 2 && this.win.isApplet()) {
                array2[j].setEnabled(false);
            }
        }
        for (int k = 0; k < this.helpMenuLabels.length; ++k) {
            (array3[k] = new JMenuItem(this.helpMenuLabels[k])).setMnemonic(this.helpMenuKeyEvents[k]);
            array3[k].setAccelerator(KeyStroke.getKeyStroke(this.helpMenuKeyEvents[k], 2));
            array3[k].addActionListener(new MenuSelectedListener());
            menu3.add(array3[k]);
        }
        this.add(menu);
        this.add(menu2);
        this.add(menu3);
    }
    
    private class MenuSelectedListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
            if (menuItem.getText().equals("Reset")) {
                GraphMenu.this.win.getControlPanel().reset();
            }
            else if (menuItem.getText().equals("Exit")) {
                if (!GraphMenu.this.win.isApplet()) {
                    System.exit(0);
                }
            }
            else if (menuItem.getText().equals("Copy")) {
                GraphMenu.this.win.getControlPanel().copyContents();
            }
            else if (menuItem.getText().equals("Paste")) {
                GraphMenu.this.win.getControlPanel().pasteContents();
            }
            else if (menuItem.getText().equals("Options")) {
                final OptionsBox optionsBox = new OptionsBox();
            }
            else if (menuItem.getText().equals("Help")) {
                final HelpWindow helpWindow = new HelpWindow();
            }
            else if (menuItem.getText().length() > 0 && menuItem.getText().substring(0, 5).equals("About")) {
                final AboutBox aboutBox = new AboutBox();
            }
        }
    }
    
    private class HelpWindow extends JPanel
    {
        JLabel msg;
        JLabel address;
        JFrame window;
        JButton closeButton;
        
        public HelpWindow() {
            this.window = new JFrame("Help");
            this.init();
            this.window.getContentPane().add(this);
            if (GraphMenu.this.win.isApplet()) {
                this.window.setSize(new Dimension(300, 150));
            }
            else {
                this.window.setSize(new Dimension(300, 130));
            }
            this.window.setResizable(false);
            this.window.setVisible(true);
        }
        
        private void init() {
            final Font font = new Font("Arial", 0, 12);
            (this.msg = new JLabel("For Help and Documentation Visit:")).setFont(font);
            (this.address = new JLabel("<html><font color='#003399'><u>www.student.math.uwaterloo.ca/~nsenarat</u></font></html>")).setFont(font);
            (this.closeButton = new JButton("Close")).addActionListener(new CloseListener(this.window));
            this.closeButton.setPreferredSize(new Dimension(100, 25));
            final JPanel panel = new JPanel(new FlowLayout());
            panel.setSize(115, 30);
            panel.add(this.closeButton);
            this.setLayout(new BorderLayout());
            final JPanel panel2 = new JPanel(new GridLayout(3, 1));
            panel2.add(this.msg);
            panel2.add(this.address);
            panel2.add(panel);
            this.add(new JLabel("  "), "West");
            this.add(panel2, "Center");
        }
    }
    
    private class AboutBox extends JPanel
    {
        JFrame window;
        
        public AboutBox() {
            this.window = new JFrame("About UBO-SOFT GraphingCalc 1.0");
            this.init();
            if (GraphMenu.this.win.isApplet()) {
                this.window.setSize(365, 260);
            }
            else {
                this.window.setSize(365, 240);
            }
            this.window.getContentPane().add(this);
            this.window.setResizable(false);
            this.window.setVisible(true);
        }
        
        private void init() {
            final Font font = new Font("Arial", 0, 12);
            this.setLayout(new BorderLayout());
            final JPanel panel = new JPanel(new GridLayout(7, 1));
            final JLabel label = new JLabel("UBO-SOFT GraphingCalc 1.0");
            label.setFont(new Font("Arial", 1, 14));
            final JLabel label2 = new JLabel("<html>Programmer : <b>Udesh Senaratne</b></html>");
            label2.setFont(font);
            final JLabel label3 = new JLabel("Copyright (C) 2004 Udesh Senaratne, All Rights Reserved");
            label3.setFont(font);
            final JLabel label4 = new JLabel("<html>Website: <font color='#003399'><u>www.student.math.uwaterloo.ca/~nsenarat</u></font></html>");
            label4.setFont(font);
            final JLabel label5 = new JLabel("<html>Email: <font color='#003399'><u>nsenarat@uwaterloo.ca</u></font></html>");
            label5.setFont(font);
            final JButton button = new JButton("Close");
            button.setSize(new Dimension(100, 25));
            button.setPreferredSize(new Dimension(100, 25));
            button.addActionListener(new CloseListener(this.window));
            final JPanel panel2 = new JPanel(new FlowLayout());
            panel2.setSize(115, 30);
            panel2.add(button);
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(panel2);
            this.add(new JLabel("  "), "West");
            this.add(panel, "Center");
        }
    }
    
    private class OptionsBox extends JPanel
    {
        private JPanel graphAreaPanel;
        private JPanel navigationPanel;
        private JCheckBox gridLineChoice;
        private JTextField shitAmtField;
        private SquareButton gridColorButton;
        private SquareButton backgroundColorButton;
        private JFrame window;
        
        public OptionsBox() {
            this.window = new JFrame("Select Options");
            this.init();
            this.window.setSize(350, 400);
            this.window.getContentPane().add(this);
            this.window.setResizable(false);
            this.window.setVisible(true);
        }
        
        private void init() {
            final Font font = new Font("Arial", 0, 12);
            this.setLayout(new BoxLayout(this, 1));
            this.graphAreaPanel = new JPanel(new GridLayout(4, 1));
            this.navigationPanel = new JPanel(new FlowLayout(0));
            final JPanel panel = new JPanel(new FlowLayout(0));
            (this.gridLineChoice = new JCheckBox()).setSelected(GraphMenu.this.win.getGraphArea().getGridLineVisible());
            final JLabel label = new JLabel("Grid Lines Visible");
            label.setFont(font);
            panel.add(label);
            panel.add(this.gridLineChoice);
            final JPanel panel2 = new JPanel(new FlowLayout(0));
            final JLabel label2 = new JLabel("Select the color of the grid lines");
            label2.setFont(font);
            panel2.setSize(300, 60);
            (this.gridColorButton = new SquareButton(50, 25, GraphMenu.this.win.getGraphArea().getGridColor())).addActionListener(new ColorSelectionListener(this, 0));
            panel2.add(label2);
            panel2.add(this.gridColorButton);
            final JPanel panel3 = new JPanel(new FlowLayout(0));
            final JLabel label3 = new JLabel("Select the background color");
            label3.setFont(font);
            (this.backgroundColorButton = new SquareButton(50, 25, GraphMenu.this.win.getGraphArea().getBackgroundColor())).addActionListener(new ColorSelectionListener(this, 1));
            panel3.add(label3);
            panel3.add(this.backgroundColorButton);
            final JPanel panel4 = new JPanel(new FlowLayout(0));
            this.shitAmtField = new JTextField(3);
            final JLabel label4 = new JLabel("Units to shift when using directional navigation");
            label4.setFont(font);
            this.shitAmtField.setText(new Integer(GraphMenu.this.win.getControlPanel().getShiftAmout()).toString());
            panel4.add(label4);
            panel4.add(this.shitAmtField);
            final JPanel panel5 = new JPanel(new FlowLayout(1));
            final JButton button = new JButton("Apply");
            button.addActionListener(new ApplyOptionsListener(this));
            final JButton button2 = new JButton("Defaults");
            button2.addActionListener(new DefaultOptionsClicked(this));
            final JButton button3 = new JButton("Close");
            button3.addActionListener(new CloseListener(this.window));
            panel5.add(button);
            panel5.add(button2);
            panel5.add(button3);
            this.graphAreaPanel.add(panel);
            this.graphAreaPanel.add(panel2);
            this.graphAreaPanel.add(panel3);
            this.graphAreaPanel.setBorder(new TitledBorder(null, "Graph Area Options"));
            this.navigationPanel.add(panel4);
            this.navigationPanel.setBorder(new TitledBorder(null, "Navigation Options"));
            this.add(this.graphAreaPanel);
            this.add(this.navigationPanel);
            this.add(panel5);
        }
        
        public void setGridVisibility(final boolean selected) {
            this.gridLineChoice.setSelected(selected);
        }
        
        public void setGridColor(final Color color) {
            this.gridColorButton.setColor(color);
        }
        
        public void setBackgroundColor(final Color color) {
            this.backgroundColorButton.setColor(color);
        }
        
        public void setShiftAmt(final int n) {
            this.shitAmtField.setText(new Integer(n).toString());
        }
        
        public boolean getGridVisible() {
            return this.gridLineChoice.isSelected();
        }
        
        public Color getGridColor() {
            return this.gridColorButton.getColor();
        }
        
        public Color getBackgroundColor() {
            return this.backgroundColorButton.getColor();
        }
        
        public int getShiftAmt() {
            int n;
            try {
                n = (int)Math.round(new Double(this.shitAmtField.getText()));
            }
            catch (Exception ex) {
                n = 2;
            }
            return n;
        }
        
        public JFrame getWindow() {
            return this.window;
        }
    }
    
    private class SquareButton extends JButton
    {
        private int border;
        private int width;
        private int height;
        private Color c;
        
        public SquareButton(final int width, final int height, final Color c) {
            this.border = 5;
            this.width = width;
            this.height = height;
            this.c = c;
            this.setPreferredSize(new Dimension(this.width, this.height));
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            array[0] = this.border;
            array[1] = this.width - this.border;
            array[2] = this.width - this.border;
            array[3] = this.border;
            array2[0] = this.border;
            array2[1] = this.border;
            array2[2] = this.height - this.border;
            array2[3] = this.height - this.border;
            graphics.setColor(this.c);
            graphics.fillPolygon(array, array2, 4);
        }
        
        public void setColor(final Color c) {
            this.c = c;
            this.repaint();
        }
        
        public Color getColor() {
            return this.c;
        }
    }
    
    private class ColorChoice extends JPanel
    {
        private JFrame window;
        private JColorChooser colorChooser;
        private JButton ok;
        private JButton cancel;
        private OptionsBox optionsBox;
        private int index;
        
        public ColorChoice(final OptionsBox optionsBox, final int index) {
            this.optionsBox = optionsBox;
            this.index = index;
            this.initialize();
        }
        
        private void initialize() {
            this.window = new JFrame();
            this.colorChooser = new JColorChooser();
            if (this.index == 0) {
                this.colorChooser.setColor(this.optionsBox.getGridColor());
            }
            else {
                this.colorChooser.setColor(this.optionsBox.getBackgroundColor());
            }
            this.ok = new JButton("Ok");
            this.cancel = new JButton("Cancel");
            this.ok.addActionListener(new ColorOkClicked(this, this.optionsBox, this.index));
            this.cancel.addActionListener(new CloseListener(this.window));
            this.setLayout(new FlowLayout());
            this.add(this.colorChooser);
            this.add(this.ok);
            this.add(this.cancel);
            this.window.getContentPane().add(this);
            this.window.setSize(480, 480);
            if (this.index == 0) {
                this.window.setTitle("Select Grid Color");
            }
            else {
                this.window.setTitle("Select Background Color");
            }
            this.window.setResizable(false);
            this.window.setVisible(true);
        }
        
        public Color getColor() {
            return this.colorChooser.getColor();
        }
        
        public JFrame getWindow() {
            return this.window;
        }
    }
    
    private class ColorSelectionListener implements ActionListener
    {
        private OptionsBox optionsBox;
        private int index;
        
        public ColorSelectionListener(final OptionsBox optionsBox, final int index) {
            this.optionsBox = optionsBox;
            this.index = index;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final ColorChoice colorChoice = new ColorChoice(this.optionsBox, this.index);
        }
    }
    
    private class ColorOkClicked implements ActionListener
    {
        ColorChoice colorChoice;
        private OptionsBox optionsBox;
        private int index;
        
        public ColorOkClicked(final ColorChoice colorChoice, final OptionsBox optionsBox, final int index) {
            this.colorChoice = colorChoice;
            this.optionsBox = optionsBox;
            this.index = index;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (this.index == 0) {
                this.optionsBox.setGridColor(this.colorChoice.getColor());
            }
            else {
                this.optionsBox.setBackgroundColor(this.colorChoice.getColor());
            }
            this.colorChoice.getWindow().dispose();
        }
    }
    
    private class ApplyOptionsListener implements ActionListener
    {
        OptionsBox optionsBox;
        
        ApplyOptionsListener(final OptionsBox optionsBox) {
            this.optionsBox = optionsBox;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            GraphMenu.this.win.getControlPanel().setShiftAmout(this.optionsBox.getShiftAmt());
            GraphMenu.this.win.getGraphArea().setGridVisible(this.optionsBox.getGridVisible());
            GraphMenu.this.win.getGraphArea().setGridColor(this.optionsBox.getGridColor());
            GraphMenu.this.win.getGraphArea().setBackgroundColor(this.optionsBox.getBackgroundColor());
            GraphMenu.this.win.getGraphArea().repaint();
            this.optionsBox.getWindow().dispose();
        }
    }
    
    private class DefaultOptionsClicked implements ActionListener
    {
        OptionsBox optionsBox;
        
        public DefaultOptionsClicked(final OptionsBox optionsBox) {
            this.optionsBox = optionsBox;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.optionsBox.setGridVisibility(true);
            this.optionsBox.setGridColor(new Color(230, 230, 230));
            this.optionsBox.setBackgroundColor(new Color(255, 255, 255));
            this.optionsBox.setShiftAmt(2);
        }
    }
    
    private class CloseListener implements ActionListener
    {
        JFrame window;
        
        public CloseListener(final JFrame window) {
            this.window = window;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.window.dispose();
        }
    }
}
