import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetActionsPanel extends Panel
{
    private GridBagLayout myLayout;
    private Button bBold;
    private Button bUnderline;
    private Choice lSpecial;
    private Choice lEmotions;
    private IRCQNetColorPanel cPanel;
    private Vector vEmotions;
    private IRCQNet theApp;
    private Label textStyle;
    private Label textColor;
    private Label textEmotion;
    private IRCQNetSpeekButton speakButton;
    private String Label;
    private int width;
    private int height;
    private IRCQNetLineInput Input;
    
    public IRCQNetActionsPanel(final IRCQNet theApp, final String label) {
        this.myLayout = new GridBagLayout();
        this.vEmotions = new Vector(10, 10);
        this.textStyle = new Label("Choose a Text Style");
        this.textColor = new Label("Text Color");
        this.textEmotion = new Label("Send an Emotion");
        this.theApp = theApp;
        this.Label = label;
        this.initControls();
        this.initParams();
        this.initMembers();
    }
    
    private void initControls() {
        this.bBold = new Button("B");
        this.bUnderline = new Button("U");
        this.lSpecial = new Choice();
        this.lEmotions = new Choice();
        (this.cPanel = new IRCQNetColorPanel(this.theApp)).setModeLine(true);
        this.cPanel.setNoFrame(true);
        this.Input = new IRCQNetLineInput(this.Label, this.theApp);
        if (this.theApp.MPanel.getParams().disableLineInput) {
            this.Input.disable();
        }
        else {
            this.Input.enable();
        }
        this.speakButton = new IRCQNetSpeekButton(this.theApp);
    }
    
    public void initShowHide() {
        if (this.theApp.MPanel.getParams().showButtonsPanel) {
            this.bBold.show();
            this.bUnderline.show();
        }
        else {
            this.bBold.hide();
            this.bUnderline.hide();
        }
        if (this.theApp.MPanel.getParams().showSpecialsPanel) {
            this.lSpecial.show();
        }
        else {
            this.lSpecial.hide();
        }
        if (this.theApp.MPanel.getParams().showEmotionsPanel) {
            this.lEmotions.show();
        }
        else {
            this.lEmotions.hide();
        }
        if (this.theApp.MPanel.getParams().showColorsPanel) {
            this.cPanel.show();
            return;
        }
        this.cPanel.hide();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(Color.white);
        graphics.drawRect(2, 4, this.width - 2, this.height - 6);
        graphics.setColor(Color.gray);
        graphics.drawRect(1, 3, this.width - 2, this.height - 6);
    }
    
    public void setLabel(final String label) {
        this.Label = label;
    }
    
    public String getLabel() {
        return this.Label;
    }
    
    public void initParams() {
        final String emotionsList = this.theApp.MPanel.getParams().emotionsList;
        final StringTokenizer stringTokenizer = new StringTokenizer(emotionsList, ";");
        if (stringTokenizer.countTokens() <= 0) {
            this.lEmotions.addItem(emotionsList);
            return;
        }
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; i += 2) {
            this.lEmotions.addItem(stringTokenizer.nextToken());
            this.vEmotions.addElement(stringTokenizer.nextToken());
        }
        this.lSpecial.addItem("Normal");
        this.lSpecial.addItem("Random");
        this.lSpecial.addItem("HighLight");
        this.lSpecial.addItem("Gradient");
    }
    
    private void initMembers() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        this.setLayout(this.myLayout);
        this.setBackground(IRCQNetColors.controlColor);
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.gridwidth = 4;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.anchor = 10;
        gridBagConstraints6.weightx = 70.0;
        gridBagConstraints6.weighty = 0.0;
        gridBagConstraints6.fill = 2;
        gridBagConstraints6.insets = new Insets(5, 4, 0, 0);
        this.myLayout.setConstraints(this.cPanel, gridBagConstraints6);
        this.add(this.cPanel);
        gridBagConstraints8.gridx = 4;
        gridBagConstraints8.gridy = 0;
        gridBagConstraints8.gridwidth = 1;
        gridBagConstraints8.gridheight = 1;
        gridBagConstraints8.anchor = 10;
        gridBagConstraints8.weightx = 0.0;
        gridBagConstraints8.weighty = 0.0;
        gridBagConstraints8.insets = new Insets(5, 4, 0, 0);
        this.myLayout.setConstraints(this.textColor, gridBagConstraints8);
        this.textColor.setAlignment(0);
        this.add(this.textColor);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        this.myLayout.setConstraints(this.bBold, gridBagConstraints);
        this.add(this.bBold);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.anchor = 10;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        this.myLayout.setConstraints(this.bUnderline, gridBagConstraints2);
        this.add(this.bUnderline);
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 1;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.anchor = 10;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.weighty = 0.0;
        this.myLayout.setConstraints(this.lSpecial, gridBagConstraints3);
        this.add(this.lSpecial);
        gridBagConstraints5.gridx = 3;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.anchor = 10;
        gridBagConstraints5.weightx = 100.0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.fill = 2;
        gridBagConstraints5.fill = 1;
        this.myLayout.setConstraints(this.Input, gridBagConstraints5);
        this.add(this.Input);
        gridBagConstraints9.gridx = 4;
        gridBagConstraints9.gridy = 1;
        gridBagConstraints9.gridwidth = 1;
        gridBagConstraints9.gridheight = 1;
        gridBagConstraints9.anchor = 10;
        gridBagConstraints9.weightx = 0.0;
        gridBagConstraints9.weighty = 0.0;
        this.myLayout.setConstraints(this.speakButton, gridBagConstraints9);
        this.add(this.speakButton);
        gridBagConstraints4.gridx = 5;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.gridwidth = 0;
        gridBagConstraints4.gridheight = 1;
        gridBagConstraints4.anchor = 10;
        gridBagConstraints4.weightx = 0.0;
        gridBagConstraints4.weighty = 0.0;
        gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
        this.myLayout.setConstraints(this.lEmotions, gridBagConstraints4);
        this.add(this.lEmotions);
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 2;
        gridBagConstraints7.gridwidth = 0;
        gridBagConstraints7.gridheight = 1;
        gridBagConstraints7.anchor = 17;
        gridBagConstraints7.weightx = 0.0;
        gridBagConstraints7.weighty = 0.0;
        gridBagConstraints7.insets = new Insets(0, 4, 5, 0);
        this.myLayout.setConstraints(this.textStyle, gridBagConstraints7);
        this.add(this.textStyle);
        gridBagConstraints10.gridx = 4;
        gridBagConstraints10.gridy = 2;
        gridBagConstraints10.gridwidth = 0;
        gridBagConstraints10.gridheight = 1;
        gridBagConstraints10.anchor = 17;
        gridBagConstraints10.weightx = 0.0;
        gridBagConstraints10.weighty = 0.0;
        gridBagConstraints10.ipadx = 25;
        gridBagConstraints10.insets = new Insets(0, 4, 5, 0);
        this.textEmotion.setAlignment(2);
        this.myLayout.setConstraints(this.textEmotion, gridBagConstraints10);
        this.add(this.textEmotion);
        this.syncItems();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.lEmotions) {
            String s = null;
            for (int i = 0; i < this.lEmotions.countItems(); ++i) {
                if (((String)o).equalsIgnoreCase(this.lEmotions.getItem(i))) {
                    s = (String)this.vEmotions.elementAt(i);
                    break;
                }
            }
            if (s != null) {
                this.postEvent(new Event(this, 10008, this.Label + ";/me " + s));
            }
            return true;
        }
        if (event.target == this.bBold) {
            this.Input.postEvent(new Event(this, 0L, 401, 0, 0, 2, 0));
        }
        if (event.target == this.bUnderline) {
            this.Input.postEvent(new Event(this, 0L, 401, 0, 0, 21, 0));
        }
        if (event.target == this.cPanel) {
            this.Input.postEvent(new Event(this, 10014, event.arg));
        }
        return super.action(event, o);
    }
    
    public void initWriteMode() {
        this.theApp.MPanel.getParams().writeMode = this.lSpecial.getSelectedIndex();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 10020: {
                this.speakButton.changeStage();
                return true;
            }
            case 10022: {
                this.Input.postEvent(new Event(this, 0L, 401, 0, 0, 10, 1));
                return true;
            }
            default: {
                if (event.target == this.lSpecial) {
                    this.theApp.MPanel.getParams().writeMode = this.lSpecial.getSelectedIndex();
                    return true;
                }
                return super.handleEvent(event);
            }
        }
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
        this.syncItems();
    }
    
    public void syncItems() {
        this.validate();
        int n = 0;
        final int width = this.lEmotions.size().width;
        final int width2 = this.lSpecial.size().width;
        final int n2 = this.bBold.size().width + this.bUnderline.size().width;
        if (this.theApp.MPanel.getParams().showButtonsPanel) {
            n = n + this.bBold.size().width + this.bUnderline.size().width;
        }
        if (this.theApp.MPanel.getParams().showSpecialsPanel) {
            n += this.lSpecial.size().width;
        }
        if (this.theApp.MPanel.getParams().showEmotionsPanel) {
            n += this.lEmotions.size().width;
        }
        this.initShowHide();
        if (n > this.width) {
            if (this.theApp.MPanel.getParams().showSpecialsPanel) {
                n -= width2;
                this.lSpecial.hide();
            }
            if (n <= this.width) {
                return;
            }
            if (this.theApp.MPanel.getParams().showEmotionsPanel) {
                n -= width;
                this.lEmotions.hide();
            }
            if (n <= this.width) {
                return;
            }
            if (n <= this.width) {
                return;
            }
            if (this.theApp.MPanel.getParams().showButtonsPanel) {
                this.bBold.hide();
                this.bUnderline.hide();
            }
        }
    }
}
