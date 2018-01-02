import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Color;
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

public class TwosComplementControl extends JPanel implements ActionListener, ChangeListener
{
    private JComboBox numOfBits;
    private int sumValue;
    private int word1Value;
    private int word2Value;
    private BitWordPanel bwPanel;
    private TestCompletePanel tcPanel;
    private JRadioButton demoMode;
    private JRadioButton testMode;
    private boolean isDemoMode;
    
    public TwosComplementControl(final BitWordPanel _bwPanel, final TestCompletePanel _tcPanel, final String[] selections) {
        this.sumValue = 0;
        this.word1Value = 0;
        this.word2Value = 0;
        this.isDemoMode = true;
        this.bwPanel = _bwPanel;
        this.tcPanel = _tcPanel;
        if (this.bwPanel == null || this.tcPanel == null) {
            return;
        }
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.demoMode = new JRadioButton("Demo Mode");
        this.testMode = new JRadioButton("Test Mode");
        final ButtonGroup modes = new ButtonGroup();
        modes.add(this.demoMode);
        modes.add(this.testMode);
        this.numOfBits = new JComboBox((E[])selections);
        this.demoMode.setSelected(true);
        this.numOfBits.setSelectedIndex(0);
        this.numOfBits.setOpaque(false);
        this.bwPanel.addComponents(Integer.parseInt(selections[0]));
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 17;
        c.insets = new Insets(4, 4, 1, 4);
        this.add(this.demoMode, c);
        c.gridy = 1;
        c.insets = new Insets(1, 4, 4, 4);
        this.add(this.testMode, c);
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(8, 10, 8, 4);
        this.add(this.numOfBits, c);
        this.bwPanel.addChangeListener(this);
        this.tcPanel.addButtonActionListener(this);
        this.numOfBits.addActionListener(this);
        this.demoMode.addActionListener(this);
        this.testMode.addActionListener(this);
    }
    
    private int generateNumToSolve() {
        double seed = Math.random();
        if (seed >= 0.5) {
            seed = (int)(Math.random() * Math.pow(2.0, this.bwPanel.getWordSize() - 1));
            if (seed == 0.0) {
                ++seed;
            }
        }
        else {
            seed = (int)(Math.random() * Math.pow(2.0, this.bwPanel.getWordSize()));
            seed *= -1.0;
        }
        System.out.println("Random num: " + seed);
        return (int)seed;
    }
    
    private void demoMode() {
        this.isDemoMode = true;
        this.bwPanel.isShowingLabels(true);
        this.bwPanel.clearWords();
        this.bwPanel.showLabels();
        this.bwPanel.setActionable(1, true);
        this.bwPanel.setActionable(2, true);
    }
    
    private void testMode() {
        final int size = this.bwPanel.getWordSize();
        this.isDemoMode = false;
        this.bwPanel.isShowingLabels(false);
        this.bwPanel.clearWords();
        this.bwPanel.clearLabels();
        this.bwPanel.setActionable(1, true);
        this.bwPanel.setActionable(2, false);
        if (Math.random() > 0.5) {
            do {
                this.word1Value = -1 * (int)(Math.random() * Math.pow(2.0, size - 1));
            } while (this.word1Value == 0);
        }
        else {
            do {
                this.word1Value = (int)(Math.random() * (Math.pow(2.0, size - 1) - 1.0));
            } while (this.word1Value == 0);
        }
        if (Math.random() > 0.5) {
            this.word2Value = -1 * (int)(Math.random() * Math.pow(2.0, size - 1));
        }
        else {
            this.word2Value = (int)(Math.random() * (Math.pow(2.0, size - 1) - 1.0));
        }
        this.sumValue = this.word1Value + this.word2Value;
        this.bwPanel.setWordValue(2, this.word2Value);
        if (this.sumValue < -1 * (int)Math.pow(2.0, size - 1)) {
            this.bwPanel.setWordBit(4, 0, true);
            final int overflowValue = 2 * (int)Math.pow(2.0, size - 1) + this.sumValue;
            this.bwPanel.setWordValue(3, overflowValue);
        }
        else if (this.sumValue > (int)(Math.pow(2.0, size - 1) - 1.0)) {
            this.bwPanel.setWordBit(4, 0, true);
            final int overflowValue = -2 * (int)Math.pow(2.0, size - 1) + this.sumValue;
            this.bwPanel.setWordValue(3, overflowValue);
        }
        else {
            this.bwPanel.setWordBit(4, 0, false);
            this.bwPanel.setWordValue(3, this.sumValue);
        }
    }
    
    private void setDemoValues() {
        final int wordsum = this.bwPanel.getSumValue();
        final int size = this.bwPanel.getWordSize();
        final int sign = size - 1;
        if (wordsum < -1 * (int)Math.pow(2.0, size - 1)) {
            this.bwPanel.setWordBit(4, 0, true);
            final int overflowValue = 2 * (int)Math.pow(2.0, size - 1) + wordsum;
            this.bwPanel.setWordValue(3, overflowValue);
        }
        else if (wordsum < 0) {
            this.bwPanel.setWordBit(4, 0, false);
            this.bwPanel.setWordValue(3, wordsum);
        }
        else if (wordsum < (int)Math.pow(2.0, size - 1)) {
            this.bwPanel.setWordBit(4, 0, false);
            this.bwPanel.setWordValue(3, wordsum);
        }
        else {
            this.bwPanel.setWordBit(4, 0, true);
            final int overflowValue = -2 * (int)Math.pow(2.0, size - 1) + wordsum;
            this.bwPanel.setWordValue(3, overflowValue);
        }
    }
    
    private void setTestValues() {
        final int wordSum = this.bwPanel.getWordValue(1) + this.bwPanel.getWordValue(2);
        if (wordSum == this.sumValue) {
            this.bwPanel.setActionable(1, false);
            this.tcPanel.showTheComponents(true);
        }
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.demoMode != null) {
            this.demoMode.setBackground(c);
        }
        if (this.testMode != null) {
            this.testMode.setBackground(c);
        }
        if (this.numOfBits != null) {
            this.numOfBits.setBackground(c);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.numOfBits) {
            final String item = (String)((JComboBox)actionEvent.getSource()).getSelectedItem();
            this.bwPanel.addComponents(Integer.parseInt(item));
            if (this.isDemoMode) {
                this.demoMode();
            }
            else {
                this.testMode();
            }
        }
        else if (actionEvent.getSource() == this.demoMode) {
            if (!this.isDemoMode) {
                this.tcPanel.showTheComponents(false);
                this.demoMode();
            }
        }
        else if (actionEvent.getSource() == this.testMode) {
            if (this.isDemoMode) {
                this.testMode();
            }
        }
        else if (actionEvent.getSource() instanceof JButton) {
            final JButton button = (JButton)actionEvent.getSource();
            final String buttonText = button.getText();
            if (buttonText.equals("Retry")) {
                this.testMode();
                this.tcPanel.showTheComponents(false);
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
