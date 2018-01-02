import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BinaryLogicControl extends JPanel implements ActionListener, ChangeListener
{
    private final int AND = 0;
    private final int OR = 1;
    private final int NAND = 2;
    private final int NOR = 3;
    private final int XOR = 4;
    private final int XNOR = 5;
    private int[] operators;
    private String[] operatorsStrings;
    private int op;
    private boolean isDemoMode;
    private boolean doneShowing;
    private JComboBox numOfBits;
    private JComboBox comboOperator;
    private BitWordPanel bwPanel;
    private TestCompletePanel tcPanel;
    private JRadioButton demoMode;
    private JRadioButton testMode;
    
    public BinaryLogicControl(final BitWordPanel _bwPanel, final TestCompletePanel _tcPanel, final String[] selections) {
        this.operators = new int[] { 0, 1, 2, 3, 4, 5 };
        this.operatorsStrings = new String[] { "AND", "OR", "NAND", "NOR", "XOR", "XNOR" };
        this.op = 0;
        this.isDemoMode = true;
        this.doneShowing = false;
        this.bwPanel = _bwPanel;
        this.tcPanel = _tcPanel;
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.demoMode = new JRadioButton("Demo Mode");
        this.testMode = new JRadioButton("Test Mode");
        final ButtonGroup modes = new ButtonGroup();
        modes.add(this.demoMode);
        modes.add(this.testMode);
        this.numOfBits = new JComboBox((E[])selections);
        this.comboOperator = new JComboBox((E[])this.operatorsStrings);
        this.demoMode.setSelected(true);
        this.numOfBits.setSelectedIndex(0);
        this.numOfBits.setOpaque(false);
        this.comboOperator.setSelectedIndex(0);
        this.comboOperator.setOpaque(false);
        this.bwPanel.addComponents(Integer.parseInt(selections[0]));
        this.bwPanel.setOperatorName(this.operatorsStrings[this.op]);
        this.bwPanel.clearWords();
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 17;
        c.insets = new Insets(4, 4, 1, 4);
        this.add(this.demoMode, c);
        c.gridy = 1;
        c.insets = new Insets(1, 4, 4, 4);
        this.add(this.testMode, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.insets = new Insets(0, 10, 0, 4);
        JLabel label = new JLabel("Bits");
        label.setHorizontalTextPosition(4);
        this.add(label, c);
        c.gridy = 1;
        label = new JLabel("Operator");
        label.setHorizontalTextPosition(4);
        this.add(label, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.insets = new Insets(0, 10, 0, 4);
        this.add(this.numOfBits, c);
        c.gridy = 1;
        this.add(this.comboOperator, c);
        this.bwPanel.addChangeListenerToWords(this);
        this.tcPanel.addButtonActionListener(this);
        this.demoMode.addActionListener(this);
        this.testMode.addActionListener(this);
        this.numOfBits.addActionListener(this);
        this.comboOperator.addActionListener(this);
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.numOfBits != null) {
            this.numOfBits.setBackground(c);
        }
        if (this.demoMode != null) {
            this.demoMode.setBackground(c);
        }
        if (this.testMode != null) {
            this.testMode.setBackground(c);
        }
        if (this.comboOperator != null) {
            this.comboOperator.setBackground(c);
        }
    }
    
    private void demoMode() {
        this.isDemoMode = true;
        this.bwPanel.setActionable(1, true);
        this.bwPanel.setActionable(2, true);
        this.setDemoValues();
    }
    
    private void testMode() {
        this.isDemoMode = false;
        this.bwPanel.setActionable(1, true);
        this.bwPanel.setActionable(2, true);
        int sumValue = 0;
        if (this.operators[this.op] == 0 || this.operators[this.op] == 1 || this.operators[this.op] == 4) {
            do {
                sumValue = (int)Math.round(Math.random() * (Math.pow(2.0, this.bwPanel.getWordSize()) - 1.0));
            } while (sumValue == 0);
        }
        else {
            do {
                sumValue = (int)Math.round(Math.random() * (Math.pow(2.0, this.bwPanel.getWordSize()) - 1.0));
            } while (sumValue == this.bwPanel.getMaxWordValue());
        }
        this.bwPanel.setWordValue(3, sumValue);
        this.setTestValues();
    }
    
    private void setNextLastButtons() {
    }
    
    private void setDemoValues() {
        final int value = this.getOperatorValue();
        this.bwPanel.setWordValue(3, value);
    }
    
    private void setTestValues() {
        if (this.getOperatorValue() == this.bwPanel.getWordValue(3)) {
            this.bwPanel.setActionable(1, false);
            this.bwPanel.setActionable(2, false);
            this.tcPanel.showTheComponents(true);
            this.doneShowing = true;
        }
    }
    
    private String getNextOpString() {
        if (this.op == this.operatorsStrings.length - 1) {
            return this.operatorsStrings[0];
        }
        return this.operatorsStrings[this.op + 1];
    }
    
    private String getLastOpString() {
        if (this.op == 0) {
            return this.operatorsStrings[this.operatorsStrings.length - 1];
        }
        return this.operatorsStrings[this.op - 1];
    }
    
    private int getOperatorValue() {
        if (this.operators[this.op] == 0) {
            return this.bwPanel.getWordValue(1) & this.bwPanel.getWordValue(2);
        }
        if (this.operators[this.op] == 1) {
            return this.bwPanel.getWordValue(1) | this.bwPanel.getWordValue(2);
        }
        if (this.operators[this.op] == 2) {
            int val = ~(this.bwPanel.getWordValue(1) & this.bwPanel.getWordValue(2));
            final int mask = (int)Math.pow(2.0, this.bwPanel.getWordSize()) - 1;
            val &= mask;
            return val;
        }
        if (this.operators[this.op] == 3) {
            int val = ~(this.bwPanel.getWordValue(1) | this.bwPanel.getWordValue(2));
            final int mask = (int)Math.pow(2.0, this.bwPanel.getWordSize()) - 1;
            val &= mask;
            return val;
        }
        if (this.operators[this.op] == 4) {
            return this.bwPanel.getWordValue(1) ^ this.bwPanel.getWordValue(2);
        }
        if (this.operators[this.op] == 5) {
            int val = ~(this.bwPanel.getWordValue(1) ^ this.bwPanel.getWordValue(2));
            final int mask = (int)Math.pow(2.0, this.bwPanel.getWordSize()) - 1;
            val &= mask;
            return val;
        }
        return 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.numOfBits) {
            final String item = (String)((JComboBox)actionEvent.getSource()).getSelectedItem();
            final int num = Integer.parseInt(item);
            if (num != this.bwPanel.getWordSize()) {
                this.bwPanel.addComponents(num);
                if (this.doneShowing) {
                    this.tcPanel.showTheComponents(false);
                    this.doneShowing = false;
                }
                if (this.isDemoMode) {
                    this.demoMode();
                }
                else {
                    this.testMode();
                }
            }
        }
        else if (actionEvent.getSource() == this.demoMode) {
            if (!this.isDemoMode) {
                this.demoMode();
            }
            if (this.doneShowing) {
                this.tcPanel.showTheComponents(false);
                this.doneShowing = false;
            }
        }
        else if (actionEvent.getSource() == this.testMode) {
            if (this.isDemoMode) {
                this.testMode();
            }
        }
        else if (actionEvent.getSource() == this.comboOperator) {
            this.op = this.comboOperator.getSelectedIndex();
            this.bwPanel.setOperatorName(this.operatorsStrings[this.op]);
            if (this.doneShowing) {
                this.tcPanel.showTheComponents(false);
                this.doneShowing = false;
            }
            if (this.isDemoMode) {
                this.demoMode();
            }
            else {
                this.testMode();
            }
        }
        else if (actionEvent.getSource() instanceof JButton) {
            final JButton button = (JButton)actionEvent.getSource();
            final String buttonText = button.getText();
            if (buttonText.equals("Retry")) {
                this.testMode();
                this.tcPanel.showTheComponents(false);
                this.bwPanel.setActionable(1, true);
                this.bwPanel.setActionable(2, true);
                this.doneShowing = false;
            }
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.isDemoMode) {
            this.setDemoValues();
        }
        else {
            this.setTestValues();
        }
    }
}
