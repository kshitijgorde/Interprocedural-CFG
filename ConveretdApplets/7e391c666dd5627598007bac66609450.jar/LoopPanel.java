import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LoopPanel extends JPanel
{
    public static final int INT_CODE_START = 80;
    private Loop _loop;
    private boolean _boolWhile;
    private boolean _boolChoices;
    private JLabel[] _labels;
    private JComboBox[] _combos;
    private JPanel _code;
    
    public LoopPanel(final Loop loop, final boolean bWhile, final boolean bChoices) {
        this._code = null;
        this._boolWhile = bWhile;
        this._boolChoices = bChoices;
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        this.setSize(340, 465);
        this.setLocation(this._boolChoices ? 350 : 10, 10);
        final JLabel labelTitle = new JLabel(this._boolWhile ? "While Loop" : "Repeat Loop");
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setVerticalTextPosition(0);
        labelTitle.setVerticalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 20));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 10, this.getWidth() - 10, this._boolChoices ? 20 : 50);
        this.add(labelTitle);
        final JLabel labelDirections = new JLabel(this._boolChoices ? "Select Correct Choices" : "");
        labelDirections.setHorizontalTextPosition(0);
        labelDirections.setHorizontalAlignment(0);
        labelDirections.setVerticalTextPosition(0);
        labelDirections.setVerticalAlignment(0);
        labelDirections.setFont(new Font("Dialog", 1, 14));
        labelDirections.setForeground(Color.green);
        labelDirections.setBounds(0, 40, this.getWidth() - 10, 20);
        this.add(labelDirections);
        this.setLoop(loop);
    }
    
    private void doComboClick(final int index) {
        if (index < 0) {
            return;
        }
        if (this._combos == null || index >= this._combos.length) {
            return;
        }
        if (this._labels == null || index >= this._labels.length) {
            return;
        }
        if (this._combos[index].getSelectedItem().toString().equalsIgnoreCase(this._labels[index].getText())) {
            this._labels[index].setForeground(Color.green);
            this._labels[index].setVisible(true);
            this._combos[index].setVisible(false);
        }
    }
    
    public void setLoop(final Loop loop) {
        if (this._code != null) {
            this.remove(this._code);
        }
        this._loop = loop;
        (this._code = new JPanel()).setLayout(null);
        this._code.setBackground(Main.WINDOW_COLOR);
        this._code.setSize(this.getWidth(), this.getHeight() - 80);
        this._code.setBorder(new LineBorder(Color.orange, 1));
        this._code.setLocation(0, 80);
        final Vector lines = this._boolWhile ? this._loop.getWhiles() : this._loop.getRepeats();
        if (lines == null || lines.size() == 0) {
            return;
        }
        this._labels = new JLabel[lines.size()];
        this._combos = new JComboBox[lines.size()];
        for (int i = 0; i < lines.size(); ++i) {
            final LoopLine line = lines.get(i);
            final Vector choices = line.getChoices();
            this._labels[i] = new JLabel(line.getAnswer());
            this._combos[i] = new JComboBox(choices);
            this._labels[i].setHorizontalAlignment(2);
            this._labels[i].setHorizontalTextPosition(2);
            this._labels[i].setVerticalAlignment(0);
            this._labels[i].setVerticalTextPosition(0);
            this._labels[i].setForeground(Color.white);
            this._labels[i].setFont(new Font("New Courier", 1, 14));
            this._labels[i].setBounds(20 + 20 * line.getIndent(), 30 + 30 * i, this.getWidth() - 20 + 20 * line.getIndent(), 30);
            this._labels[i].setVisible(!this._boolChoices || choices.size() == 0);
            this._code.add(this._labels[i]);
            (this._combos[i] = new JComboBox(choices)).setBounds(20 + 20 * line.getIndent(), 30 + 30 * i, 175, 30);
            this._combos[i].setAlignmentX(0.5f);
            this._combos[i].setActionCommand("" + i);
            this._combos[i].addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    final JComboBox src = (JComboBox)evt.getSource();
                    int index = -1;
                    try {
                        index = Integer.parseInt(src.getActionCommand());
                    }
                    catch (NumberFormatException ex) {}
                    LoopPanel.this.doComboClick(index);
                }
            });
            this._combos[i].setVisible(this._boolChoices && choices.size() > 0);
            this._code.add(this._combos[i]);
        }
        this.add(this._code);
        this.repaint();
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "Loop Panel";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 14));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 10, this.getWidth(), 50);
        this.add(labelTitle);
    }
}
