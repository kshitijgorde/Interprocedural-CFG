import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Scrollbar;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Label;
import java.awt.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class fontSelect extends Dialog implements AdjustmentListener, ItemListener, TextListener, ActionListener
{
    public Font selectedFont;
    public boolean isSelected;
    public Color selectedBackground;
    public Color selectedForeground;
    List fontList;
    Label label1;
    TextField exampleText;
    Label label2;
    Checkbox isBold;
    Checkbox isItalic;
    Label showFont;
    Choice fontSize;
    Button btnSelect;
    Button btnCancel;
    Label label3;
    Scrollbar rBackground;
    Scrollbar gBackground;
    Scrollbar bBackground;
    Label label4;
    Label label5;
    Label label6;
    Label rBackValue;
    Label gBackValue;
    Label bBackValue;
    Label label7;
    Scrollbar rForeground;
    Scrollbar gForeground;
    Scrollbar bForeground;
    Label label8;
    Label label9;
    Label label10;
    Label rForeValue;
    Label gForeValue;
    Label bForeValue;
    
    public fontSelect(final Frame frame, final boolean b) {
        super(frame, b);
        this.isSelected = false;
        final String[] fontList = this.getToolkit().getFontList();
        this.setLayout(null);
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 591, this.insets().top + this.insets().bottom + 505);
        this.setBackground(Color.lightGray);
        this.add(this.fontList = new List(0, false));
        this.fontList.setBounds(this.insets().left + 12, this.insets().top + 60, 337, 144);
        (this.label1 = new Label("Example string")).setBounds(this.insets().left + 372, this.insets().top + 36, 84, 24);
        this.add(this.label1);
        (this.exampleText = new TextField()).setText("Simple");
        this.exampleText.setBounds(this.insets().left + 372, this.insets().top + 60, 202, 21);
        this.add(this.exampleText);
        (this.label2 = new Label("Select font")).setBounds(this.insets().left + 12, this.insets().top + 36, 60, 19);
        this.add(this.label2);
        (this.isBold = new Checkbox("Bold")).setBounds(this.insets().left + 372, this.insets().top + 96, 60, 18);
        this.isBold.setFont(new Font("Dialog", 1, 12));
        this.add(this.isBold);
        (this.isItalic = new Checkbox("Italic")).setBounds(this.insets().left + 372, this.insets().top + 132, 48, 17);
        this.isItalic.setFont(new Font("Dialog", 2, 12));
        this.add(this.isItalic);
        (this.showFont = new Label("Simple", 1)).setBounds(this.insets().left + 12, this.insets().top + 324, 566, 173);
        this.showFont.setBackground(Color.white);
        this.add(this.showFont);
        this.add(this.fontSize = new Choice());
        this.fontSize.setBounds(this.insets().left + 372, this.insets().top + 156, 60, 23);
        this.fontSize.setBackground(Color.white);
        (this.btnSelect = new Button()).setLabel("Select");
        this.btnSelect.setBounds(this.insets().left + 492, this.insets().top + 108, 87, 24);
        this.add(this.btnSelect);
        (this.btnCancel = new Button()).setLabel("Cancel");
        this.btnCancel.setBounds(this.insets().left + 492, this.insets().top + 156, 91, 24);
        this.add(this.btnCancel);
        (this.label3 = new Label("Background")).setBounds(this.insets().left + 24, this.insets().top + 216, 94, 18);
        this.add(this.label3);
        (this.rBackground = new Scrollbar(0, 255, 0, 0, 255)).setBounds(this.insets().left + 24, this.insets().top + 240, 197, 21);
        this.add(this.rBackground);
        (this.gBackground = new Scrollbar(0, 255, 0, 0, 255)).setBounds(this.insets().left + 24, this.insets().top + 264, 197, 21);
        this.add(this.gBackground);
        (this.bBackground = new Scrollbar(0, 255, 0, 0, 255)).setBounds(this.insets().left + 24, this.insets().top + 288, 197, 21);
        this.add(this.bBackground);
        (this.label4 = new Label("R")).setBounds(this.insets().left + 12, this.insets().top + 240, 12, 18);
        this.label4.setFont(new Font("Dialog", 1, 12));
        this.label4.setForeground(Color.red);
        this.add(this.label4);
        (this.label5 = new Label("G")).setBounds(this.insets().left + 12, this.insets().top + 264, 12, 18);
        this.label5.setFont(new Font("Dialog", 1, 12));
        this.label5.setForeground(new Color(-16744448));
        this.add(this.label5);
        (this.label6 = new Label("B")).setBounds(this.insets().left + 12, this.insets().top + 288, 12, 18);
        this.label6.setFont(new Font("Dialog", 1, 12));
        this.label6.setForeground(Color.blue);
        this.add(this.label6);
        (this.rBackValue = new Label("255")).setBounds(this.insets().left + 228, this.insets().top + 240, 24, 12);
        this.rBackValue.setFont(new Font("Dialog", 1, 12));
        this.rBackValue.setForeground(Color.red);
        this.add(this.rBackValue);
        (this.gBackValue = new Label("255")).setBounds(this.insets().left + 228, this.insets().top + 264, 24, 12);
        this.gBackValue.setFont(new Font("Dialog", 1, 12));
        this.gBackValue.setForeground(new Color(-16744384));
        this.add(this.gBackValue);
        (this.bBackValue = new Label("255")).setBounds(this.insets().left + 228, this.insets().top + 288, 24, 12);
        this.bBackValue.setFont(new Font("Dialog", 1, 12));
        this.bBackValue.setForeground(Color.blue);
        this.add(this.bBackValue);
        (this.label7 = new Label("Foreground")).setBounds(this.insets().left + 336, this.insets().top + 216, 94, 18);
        this.add(this.label7);
        (this.rForeground = new Scrollbar(0, 0, 0, 0, 255)).setBounds(this.insets().left + 336, this.insets().top + 240, 197, 21);
        this.add(this.rForeground);
        (this.gForeground = new Scrollbar(0, 0, 0, 0, 255)).setBounds(this.insets().left + 336, this.insets().top + 264, 197, 21);
        this.add(this.gForeground);
        (this.bForeground = new Scrollbar(0, 0, 0, 0, 255)).setBounds(this.insets().left + 336, this.insets().top + 288, 197, 21);
        this.add(this.bForeground);
        (this.label8 = new Label("R")).setBounds(this.insets().left + 324, this.insets().top + 240, 12, 18);
        this.label8.setFont(new Font("Dialog", 1, 12));
        this.label8.setForeground(Color.red);
        this.add(this.label8);
        (this.label9 = new Label("G")).setBounds(this.insets().left + 324, this.insets().top + 264, 12, 18);
        this.label9.setFont(new Font("Dialog", 1, 12));
        this.label9.setForeground(new Color(-16744448));
        this.add(this.label9);
        (this.label10 = new Label("B")).setBounds(this.insets().left + 324, this.insets().top + 288, 12, 18);
        this.label10.setFont(new Font("Dialog", 1, 12));
        this.label10.setForeground(Color.blue);
        this.add(this.label10);
        (this.rForeValue = new Label("255")).setBounds(this.insets().left + 540, this.insets().top + 240, 24, 12);
        this.rForeValue.setFont(new Font("Dialog", 1, 12));
        this.rForeValue.setForeground(Color.red);
        this.add(this.rForeValue);
        (this.gForeValue = new Label("255")).setBounds(this.insets().left + 540, this.insets().top + 264, 24, 12);
        this.gForeValue.setFont(new Font("Dialog", 1, 12));
        this.gForeValue.setForeground(new Color(-16744384));
        this.add(this.gForeValue);
        (this.bForeValue = new Label("255")).setBounds(this.insets().left + 540, this.insets().top + 288, 24, 12);
        this.bForeValue.setFont(new Font("Dialog", 1, 12));
        this.bForeValue.setForeground(Color.blue);
        this.add(this.bForeValue);
        this.setTitle("Choosing font");
        this.exampleText.addTextListener(this);
        this.btnSelect.addActionListener(this);
        this.btnCancel.addActionListener(this);
        this.isBold.addItemListener(this);
        this.fontList.addItemListener(this);
        this.isItalic.addItemListener(this);
        this.fontSize.addItemListener(this);
        this.rBackground.addAdjustmentListener(this);
        this.gBackground.addAdjustmentListener(this);
        this.bBackground.addAdjustmentListener(this);
        this.rForeground.addAdjustmentListener(this);
        this.gForeground.addAdjustmentListener(this);
        this.bForeground.addAdjustmentListener(this);
        for (int i = 0; i < fontList.length; ++i) {
            this.fontList.addItem(fontList[i]);
        }
        this.showFont.setFont(new Font(fontList[0], 0, 12));
        this.selectedBackground = this.showFont.getBackground();
        this.selectedForeground = this.showFont.getForeground();
        int j = 8;
        do {
            this.fontSize.addItem(String.valueOf(j));
            if (j <= 30) {
                j += 2;
            }
            else if (j <= 70) {
                j += 4;
            }
            else {
                j += 8;
            }
        } while (j < 150);
        try {
            this.fontSize.select(2);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    private Font constructFont() {
        int int1;
        try {
            int1 = Integer.parseInt(this.fontSize.getSelectedItem());
        }
        catch (NumberFormatException ex) {
            int1 = 12;
        }
        this.selectedFont = new Font(this.fontList.getItem((this.fontList.getSelectedIndex() >= 0) ? this.fontList.getSelectedIndex() : 0), (this.isBold.getState() ? 1 : 0) + (this.isItalic.getState() ? 2 : 0), int1);
        this.selectedBackground = new Color(this.rBackground.getValue(), this.gBackground.getValue(), this.bBackground.getValue());
        this.selectedForeground = new Color(this.rForeground.getValue(), this.gForeground.getValue(), this.bForeground.getValue());
        return this.selectedFont;
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (textEvent.getSource() == this.exampleText) {
            this.showFont.setText(this.exampleText.getText());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.btnSelect) {
            this.isSelected = true;
            this.selectedFont = this.constructFont();
            this.dispose();
            return;
        }
        if (source == this.btnCancel) {
            this.selectedFont = null;
            this.selectedBackground = null;
            this.selectedForeground = null;
            this.dispose();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.isBold) {
            this.showFont.setFont(this.constructFont());
            return;
        }
        if (source == this.fontList) {
            this.showFont.setFont(this.constructFont());
            return;
        }
        if (source == this.isItalic) {
            this.showFont.setFont(this.constructFont());
            return;
        }
        if (source == this.fontSize) {
            this.showFont.setFont(this.constructFont());
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        this.constructFont();
        this.showFont.setBackground(new Color(this.rBackground.getValue(), this.gBackground.getValue(), this.bBackground.getValue()));
        this.showFont.setForeground(new Color(this.rForeground.getValue(), this.gForeground.getValue(), this.bForeground.getValue()));
        if (source == this.rBackground) {
            this.rBackValue.setText(String.valueOf(this.rBackground.getValue()));
            return;
        }
        if (source == this.gBackground) {
            this.gBackValue.setText(String.valueOf(this.gBackground.getValue()));
            return;
        }
        if (source == this.bBackground) {
            this.bBackValue.setText(String.valueOf(this.bBackground.getValue()));
            return;
        }
        if (source == this.rForeground) {
            this.rForeValue.setText(String.valueOf(this.rForeground.getValue()));
            return;
        }
        if (source == this.gForeground) {
            this.gForeValue.setText(String.valueOf(this.gForeground.getValue()));
            return;
        }
        if (source == this.bForeground) {
            this.bForeValue.setText(String.valueOf(this.bForeground.getValue()));
        }
    }
}
