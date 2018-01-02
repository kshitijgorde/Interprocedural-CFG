// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Button;
import pclient.bsc.BaseChat;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class TextColor extends Panel implements ActionListener, ColorListener, AdjustmentListener
{
    private static final String SAMPLE_TEXT = "Sample Text";
    private BaseChat pChat;
    private Button pSetButton;
    private Label pSampleText;
    private Scrollbar pRedBar;
    private Scrollbar pGreenBar;
    private Scrollbar pBlueBar;
    private Label pColorLabel;
    private Color currentColor;
    
    public TextColor(final BaseChat pChat) {
        this.currentColor = null;
        this.pChat = pChat;
        this.currentColor = pChat.parentComp.userChoice.inputColor;
        if (this.currentColor == null) {
            this.currentColor = Color.black;
        }
        this.buildGUI();
    }
    
    public void processColor(final int value, final int value2, final int value3) {
        this.pRedBar.setValue(value);
        this.pGreenBar.setValue(value2);
        this.pBlueBar.setValue(value3);
        this.changeColor(value, value2, value3);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pSetButton) {
            this.pChat.changeUserColor(this.currentColor);
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.changeColor(this.round(this.pRedBar.getValue()), this.round(this.pGreenBar.getValue()), this.round(this.pBlueBar.getValue()));
    }
    
    private int round(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 255) {
            n = 255;
        }
        return n;
    }
    
    private void changeColor(final int n, final int n2, final int n3) {
        final Color color = new Color(n, n2, n3);
        this.pSampleText.setForeground(color);
        this.pSampleText.setText("Sample Text");
        this.pColorLabel.setText(n + ", " + n2 + ", " + n3);
        this.currentColor = color;
    }
    
    private void buildGUI() {
        final ColorPanel colorPanel = new ColorPanel(this);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        panel.add(colorPanel);
        panel.add(this.buildEast());
        this.setLayout(new BorderLayout());
        this.add("Center", panel);
        this.add("South", this.buildButton());
    }
    
    private Panel buildText() {
        final Font font = new Font("Dialog", 1, 16);
        final Label pSampleText = new Label("Sample Text", 1);
        if (font != null) {
            pSampleText.setFont(font);
        }
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", pSampleText);
        this.pSampleText = pSampleText;
        return panel;
    }
    
    private Panel buildButton() {
        final Font font = new Font("Dialog", 0, 14);
        final Button pSetButton = new Button("OK");
        if (font != null) {
            pSetButton.setFont(font);
        }
        pSetButton.addActionListener(this);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        panel.add(pSetButton);
        this.pSetButton = pSetButton;
        return panel;
    }
    
    private Panel buildEast() {
        final Panel buildScrollbars = this.buildScrollbars();
        final Panel buildText = this.buildText();
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", buildScrollbars);
        panel.add("South", buildText);
        return panel;
    }
    
    private Panel buildScrollbars() {
        final Font font = new Font("Dialog", 1, 14);
        final int n = 50;
        final Scrollbar pRedBar = new Scrollbar(0, 0, n, 0, n + 255);
        final Scrollbar pGreenBar = new Scrollbar(0, 0, n, 0, n + 255);
        final Scrollbar pBlueBar = new Scrollbar(0, 0, n, 0, n + 255);
        pRedBar.setSize(200, 16);
        pGreenBar.setSize(200, 16);
        pBlueBar.setSize(200, 16);
        final Color brighter = Color.gray.brighter();
        pRedBar.setBackground(brighter);
        pGreenBar.setBackground(brighter);
        pBlueBar.setBackground(brighter);
        final Label label = new Label("R");
        final Label label2 = new Label("G");
        final Label label3 = new Label("B");
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", pRedBar);
        panel.add("West", label);
        panel2.setLayout(new BorderLayout());
        panel2.add("Center", pGreenBar);
        panel2.add("West", label2);
        panel3.setLayout(new BorderLayout());
        panel3.add("Center", pBlueBar);
        panel3.add("West", label3);
        final Label pColorLabel = new Label(" ", 1);
        final MarginPanel marginPanel = new MarginPanel(7, 14, 7, 14);
        marginPanel.setLayout(new GridLayout(4, 1, 3, 12));
        marginPanel.add(panel);
        marginPanel.add(panel2);
        marginPanel.add(panel3);
        marginPanel.add(pColorLabel);
        pRedBar.addAdjustmentListener(this);
        pGreenBar.addAdjustmentListener(this);
        pBlueBar.addAdjustmentListener(this);
        this.pRedBar = pRedBar;
        this.pGreenBar = pGreenBar;
        this.pBlueBar = pBlueBar;
        this.pColorLabel = pColorLabel;
        return marginPanel;
    }
}
