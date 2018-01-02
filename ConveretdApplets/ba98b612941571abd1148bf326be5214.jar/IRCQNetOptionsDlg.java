import java.awt.Event;
import java.awt.Container;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetOptionsDlg extends Panel
{
    private IRCQNet theApp;
    private GridBagLayout myLayout;
    private CardLayout cardsLayout;
    private IRCQNetTextShow textPreview;
    private Button applyButton;
    private Button cancelButton;
    private Checkbox bCheckbox;
    private Checkbox cCheckbox;
    private Checkbox colorCheckbox;
    private IRCQNetDialog myDialog;
    private Panel CardsPanel;
    private TextField tNickFild;
    private Label lNickFild;
    private TextField tICQFild;
    private Label lICQFild;
    private Checkbox cShowIp;
    private Checkbox cShowQuits;
    private Checkbox cShowJoins;
    private Checkbox cShowNickChange;
    private Checkbox cShowUserModeChange;
    private Checkbox cShowOnlyChat;
    private Checkbox cShowAll;
    private IRCQNetDialogPanel generalPanel;
    private IRCQNetDialogPanel generalPanelTop;
    private IRCQNetDialogPanel generalPanelButtum;
    private IRCQNetDialogPanel sFxPanel;
    private IRCQNetDialogPanel sFxPanelTop;
    private IRCQNetDialogPanel sFxPanelRand;
    private IRCQNetDialogPanel sFxPanelHighLight;
    private IRCQNetDialogPanel sFxPanelGrad;
    private IRCQNetDialogPanel sFxCardsPanel;
    private IRCQNetDialogPanel sFxRightPanel;
    private IRCQNetColorPanel sFXcRand;
    private IRCQNetColorPanel sFXcHighLight;
    private IRCQNetColorPanel sFXcGrad;
    private Choice sFxChoice;
    private IRCQNetSingleColorCell RandBG;
    private Label RandBGLabel;
    private IRCQNetSingleColorCell sFxHighLightBG;
    private IRCQNetSingleColorCell sFxHighLightFG;
    private Checkbox cbFxHighLightBG;
    private Checkbox cbFxHighLightFG;
    private IRCQNetSingleColorCell GradBG;
    private Label GradBGLabel;
    private Choice GradChoice;
    private CardLayout cLayout;
    private IRCQNetDialogPanel ShowItemsPanel;
    private IRCQNetToolBarPanel TabPanel;
    private IRCQNetImageButtonPanel bButtonsPanel;
    private int RtBG;
    private int RtFG;
    private int HtBG;
    private int HtFG;
    private int Ht2FG;
    private int GtFG;
    private int GtBG;
    private String PreviewText;
    
    public IRCQNetOptionsDlg(final boolean b, final IRCQNet theApp) {
        this.myLayout = new GridBagLayout();
        this.cardsLayout = new CardLayout();
        this.PreviewText = "This is your design preview";
        this.theApp = theApp;
        this.initDialog();
        this.initControls();
        this.initButtonsPanel();
        this.initTabPanel();
        this.CardsPanel.setLayout(this.cardsLayout);
        this.initGeneralPanel();
        this.initsFxPanel();
        this.initShowItemsPanel();
        this.initLayout();
    }
    
    public IRCQNetParam getParams() {
        return this.theApp.MPanel.getParams();
    }
    
    private void initButtonsPanel() {
        (this.bButtonsPanel = new IRCQNetImageButtonPanel(this.theApp)).setCenter(true);
        this.bButtonsPanel.setFontSize(10);
        this.bButtonsPanel.resize(90, 30);
        this.bButtonsPanel.setButtonsWidth(60);
        this.bButtonsPanel.NewButton("OK", 3);
        this.bButtonsPanel.NewButton("Cancel", 4);
    }
    
    private void initTabPanel() {
        (this.TabPanel = new IRCQNetToolBarPanel(this.theApp)).setButtonsWidth(60);
        this.TabPanel.setFontSize(10);
        this.TabPanel.resize(90, 50);
        this.TabPanel.setTabMode(true);
        this.TabPanel.NewButton("General", 9);
        this.TabPanel.NewButton("SpecialFX", 10);
    }
    
    private void initDialog() {
        (this.myDialog = new IRCQNetDialog(new Frame(), "IRCQNet", true, this)).setResizable(false);
        this.myDialog.setBackground(IRCQNetColors.controlColor);
        this.myDialog.setForeground(IRCQNetColors.controlColorFG);
        this.myDialog.setLayout(new BorderLayout());
        this.myDialog.resize(400, 340);
        this.myDialog.setTitle("IRCQNet Option dialog");
        this.myDialog.add("Center", this);
        this.show();
    }
    
    private void initControls() {
        this.ShowItemsPanel = new IRCQNetDialogPanel();
        this.generalPanel = new IRCQNetDialogPanel();
        this.generalPanelTop = new IRCQNetDialogPanel();
        this.generalPanelButtum = new IRCQNetDialogPanel();
        this.sFxPanel = new IRCQNetDialogPanel();
        this.sFxPanelRand = new IRCQNetDialogPanel();
        this.sFxPanelHighLight = new IRCQNetDialogPanel();
        this.sFxPanelGrad = new IRCQNetDialogPanel();
        this.sFxCardsPanel = new IRCQNetDialogPanel();
        this.sFxRightPanel = new IRCQNetDialogPanel();
        this.sFxPanelTop = new IRCQNetDialogPanel();
        (this.textPreview = new IRCQNetTextShow(this.theApp)).setPanelLabel("Text Preview");
        this.sFxPanelRand.setPanelLabel("Random text setings");
        this.sFxPanelHighLight.setPanelLabel("Initial highlight setings");
        this.sFxPanelGrad.setPanelLabel("Gradient setings");
        this.sFxRightPanel.setPanelLabel("Effect");
        this.CardsPanel = new Panel();
        new Checkbox("Show button panel");
        new Checkbox("Show channel choice box");
        new Checkbox("Show colors panel");
        new CheckboxGroup();
        (this.tNickFild = new TextField()).setText(this.theApp.MPanel.getParams().nick);
        this.tNickFild.setEditable(true);
        this.lNickFild = new Label("NickName:", 0);
        (this.tICQFild = new TextField()).setText(this.theApp.MPanel.getParams().uin);
        this.tICQFild.setEditable(true);
        this.lICQFild = new Label("ICQ Number:", 0);
        (this.cShowIp = new Checkbox("Show My Ip")).setState(this.theApp.MPanel.getParams().showIP);
        (this.cShowQuits = new Checkbox("Show Quits")).setState(this.theApp.MPanel.getParams().showQuits);
        (this.cShowJoins = new Checkbox("Show Joins")).setState(this.theApp.MPanel.getParams().showJoins);
        (this.cShowNickChange = new Checkbox("Show Nick Change")).setState(this.theApp.MPanel.getParams().showNickChange);
        (this.cShowUserModeChange = new Checkbox("Show User Mode Change")).setState(this.theApp.MPanel.getParams().showUserModeChange);
        (this.cShowOnlyChat = new Checkbox("Show Only Chat")).setState(this.theApp.MPanel.getParams().showOnlyChat);
        (this.sFxChoice = new Choice()).addItem("Random");
        this.sFxChoice.addItem("HighLight");
        this.sFxChoice.addItem("Gradient");
        this.RandBG = new IRCQNetSingleColorCell();
        this.RandBGLabel = new Label("Background", 1);
        (this.sFXcRand = new IRCQNetColorPanel(this.theApp)).senMinSize(30, 16);
        this.sFXcRand.setNoFrame(true);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.sFxHighLightBG = new IRCQNetSingleColorCell();
        this.sFxHighLightFG = new IRCQNetSingleColorCell();
        (this.sFXcHighLight = new IRCQNetColorPanel(this.theApp)).senMinSize(30, 16);
        this.sFXcHighLight.setNoFrame(true);
        this.cbFxHighLightBG = new Checkbox("Background", checkboxGroup, true);
        this.cbFxHighLightFG = new Checkbox("Forground", checkboxGroup, true);
        this.GradBG = new IRCQNetSingleColorCell();
        this.GradBGLabel = new Label("Background", 1);
        this.GradChoice = new Choice();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.theApp.MPanel.getParams().specialList, ";");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; i += 2) {
            this.GradChoice.addItem(stringTokenizer.nextToken(";"));
            stringTokenizer.nextToken(";");
        }
        this.GradChoice.select(this.theApp.MPanel.getParams().specialNum);
        (this.sFXcGrad = new IRCQNetColorPanel(this.theApp)).senMinSize(30, 16);
        this.sFXcGrad.setNoFrame(true);
        this.getSFxFromParam();
        this.applyButton = new Button("Apply");
        this.cancelButton = new Button("Cancel");
    }
    
    public void getSFxFromParam() {
        this.GradChoice.select(this.getParams().specialNum);
        this.GradBG.setColor(this.getParams().sFxGradientBG);
        this.RandBG.setColor(this.getParams().sFxRandomBG);
        this.sFxHighLightBG.setColor(this.getParams().sFxHighLightBG);
        this.sFxHighLightFG.setColor(this.getParams().sFxHighLightFG);
        this.RtBG = this.colorDecoder(this.getParams().sFxRandomBG);
        this.HtBG = this.colorDecoder(this.getParams().sFxHighLightBG);
        this.HtFG = this.colorDecoder(this.getParams().sFxHighLightFG);
        this.GtBG = this.colorDecoder(this.getParams().sFxGradientBG);
    }
    
    public int colorDecoder(final Color color) {
        int size;
        int n;
        for (size = this.getParams().colorVector.size(), n = 0; n < size && !color.equals(this.getParams().colorVector.elementAt(n)); ++n) {}
        return n;
    }
    
    private void initCardsPanel() {
        this.CardsPanel.setLayout(this.cardsLayout);
    }
    
    private void initGeneralPanel() {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagLayout layout3 = new GridBagLayout();
        this.generalPanel.setLayout(layout);
        this.generalPanelTop.setLayout(layout2);
        this.generalPanelButtum.setLayout(layout3);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints2.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.ipadx = 20;
        layout2.setConstraints(this.lNickFild, gridBagConstraints2);
        this.generalPanelTop.add(this.lNickFild);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.ipadx = 60;
        layout2.setConstraints(this.tNickFild, gridBagConstraints2);
        this.generalPanelTop.add(this.tNickFild);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.ipadx = 20;
        layout2.setConstraints(this.lICQFild, gridBagConstraints2);
        this.generalPanelTop.add(this.lICQFild);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.ipadx = 60;
        layout2.setConstraints(this.tICQFild, gridBagConstraints2);
        this.generalPanelTop.add(this.tICQFild);
        gridBagConstraints3.anchor = 10;
        gridBagConstraints3.fill = 1;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new Insets(2, 2, 2, 30);
        layout3.setConstraints(this.cShowOnlyChat, gridBagConstraints3);
        this.generalPanelButtum.add(this.cShowOnlyChat);
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        layout3.setConstraints(this.cShowQuits, gridBagConstraints3);
        this.generalPanelButtum.add(this.cShowQuits);
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        layout3.setConstraints(this.cShowJoins, gridBagConstraints3);
        this.generalPanelButtum.add(this.cShowJoins);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        layout3.setConstraints(this.cShowUserModeChange, gridBagConstraints3);
        this.generalPanelButtum.add(this.cShowUserModeChange);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        layout3.setConstraints(this.cShowNickChange, gridBagConstraints3);
        this.generalPanelButtum.add(this.cShowNickChange);
        this.syncGeneralCheckbox();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.generalPanelTop, gridBagConstraints);
        this.generalPanel.add(this.generalPanelTop);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        layout.setConstraints(this.generalPanelButtum, gridBagConstraints);
        this.generalPanel.add(this.generalPanelButtum);
    }
    
    private void initsFxPanel() {
        this.cLayout = new CardLayout();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagLayout layout3 = new GridBagLayout();
        final GridBagLayout layout4 = new GridBagLayout();
        this.sFxCardsPanel.setLayout(this.cLayout);
        this.sFxPanelTop.setLayout(layout);
        this.sFxPanelRand.setLayout(layout2);
        this.sFxPanelHighLight.setLayout(layout3);
        this.sFxPanelGrad.setLayout(layout4);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints2.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints2.anchor = 10;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.ipadx = 100;
        gridBagConstraints2.ipady = 25;
        layout2.setConstraints(this.sFXcRand, gridBagConstraints2);
        this.sFxPanelRand.add(this.sFXcRand);
        gridBagConstraints3.insets = new Insets(2, 8, 2, 0);
        gridBagConstraints3.anchor = 10;
        gridBagConstraints3.fill = 0;
        gridBagConstraints3.gridheight = 2;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.ipadx = 40;
        gridBagConstraints3.ipady = 25;
        layout3.setConstraints(this.sFXcHighLight, gridBagConstraints3);
        this.sFxPanelHighLight.add(this.sFXcHighLight);
        gridBagConstraints3.fill = 0;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.ipadx = 1;
        gridBagConstraints3.ipady = 1;
        layout3.setConstraints(this.cbFxHighLightBG, gridBagConstraints3);
        this.sFxPanelHighLight.add(this.cbFxHighLightBG);
        gridBagConstraints3.fill = 2;
        gridBagConstraints3.insets = new Insets(0, 2, 2, 2);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.ipadx = 10;
        gridBagConstraints3.ipady = 10;
        layout3.setConstraints(this.sFxHighLightBG, gridBagConstraints3);
        this.sFxPanelHighLight.add(this.sFxHighLightBG);
        gridBagConstraints3.fill = 0;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.insets = new Insets(0, 0, 0, 8);
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.ipadx = 1;
        gridBagConstraints3.ipady = 1;
        layout3.setConstraints(this.cbFxHighLightFG, gridBagConstraints3);
        this.sFxPanelHighLight.add(this.cbFxHighLightFG);
        gridBagConstraints3.fill = 2;
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.ipadx = 10;
        gridBagConstraints3.ipady = 10;
        gridBagConstraints3.insets = new Insets(0, 2, 2, 8);
        layout3.setConstraints(this.sFxHighLightFG, gridBagConstraints3);
        this.sFxPanelHighLight.add(this.sFxHighLightFG);
        gridBagConstraints4.insets = new Insets(8, 8, 8, 8);
        gridBagConstraints4.anchor = 10;
        gridBagConstraints4.fill = 0;
        gridBagConstraints4.gridheight = 2;
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        layout4.setConstraints(this.GradChoice, gridBagConstraints4);
        this.sFxPanelGrad.add(this.GradChoice);
        gridBagConstraints4.insets = new Insets(8, 8, 8, 8);
        gridBagConstraints4.anchor = 10;
        gridBagConstraints4.fill = 0;
        gridBagConstraints4.gridheight = 2;
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.ipadx = 50;
        gridBagConstraints4.ipady = 25;
        layout4.setConstraints(this.sFXcGrad, gridBagConstraints4);
        this.sFxPanelGrad.add(this.sFXcGrad);
        this.sFxCardsPanel.add("Rand", this.sFxPanelRand);
        this.sFxCardsPanel.add("HighLight", this.sFxPanelHighLight);
        this.sFxCardsPanel.add("Grad", this.sFxPanelGrad);
        final GridBagLayout layout5 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        this.sFxRightPanel.setLayout(layout5);
        gridBagConstraints5.fill = 0;
        gridBagConstraints5.anchor = 10;
        gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
        layout5.setConstraints(this.sFxChoice, gridBagConstraints5);
        this.sFxRightPanel.add(this.sFxChoice);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 2);
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.sFxRightPanel, gridBagConstraints);
        this.sFxPanelTop.add(this.sFxRightPanel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 2, 10, 10);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.sFxCardsPanel, gridBagConstraints);
        this.sFxPanelTop.add(this.sFxCardsPanel);
        final GridBagLayout layout6 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        this.sFxPanel.setLayout(layout6);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints6.anchor = 11;
        gridBagConstraints6.fill = 2;
        layout6.setConstraints(this.sFxPanelTop, gridBagConstraints6);
        this.sFxPanel.add(this.sFxPanelTop);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 5.0;
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.anchor = 10;
        gridBagConstraints6.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints6.fill = 1;
        layout6.setConstraints(this.textPreview, gridBagConstraints6);
        this.sFxPanel.add(this.textPreview);
        this.textPreview.show();
        this.cLayout.show(this.sFxCardsPanel, "HighLight");
        this.showSFxPanel(0);
    }
    
    private void initShowItemsPanel() {
        final GridBagLayout layout = new GridBagLayout();
        this.ShowItemsPanel.setLayout(layout);
        this.bCheckbox = new Checkbox("Show button panel");
        this.cCheckbox = new Checkbox("Show channel choice box");
        this.colorCheckbox = new Checkbox("Show colors panel");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.bCheckbox, gridBagConstraints);
        this.ShowItemsPanel.add(this.bCheckbox);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        layout.setConstraints(this.cCheckbox, gridBagConstraints2);
        this.ShowItemsPanel.add(this.cCheckbox);
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = 1;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.anchor = 18;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.weighty = 0.0;
        layout.setConstraints(this.colorCheckbox, gridBagConstraints3);
        this.ShowItemsPanel.add(this.colorCheckbox);
        this.bCheckbox.show();
        this.cCheckbox.show();
        this.colorCheckbox.show();
    }
    
    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.setBackground(IRCQNetColors.controlColor);
        this.setForeground(IRCQNetColors.controlColorFG);
        this.add("North", this.TabPanel);
        this.add("South", this.bButtonsPanel);
        this.CardsPanel.add("SpecialFX", this.sFxPanel);
        this.CardsPanel.add("General", this.generalPanel);
        this.cardsLayout.show(this.CardsPanel, "General");
        this.add("Center", this.CardsPanel);
        this.applyButton.show();
        this.bButtonsPanel.show();
        this.TabPanel.show();
        this.myDialog.show();
        this.syncPreview();
    }
    
    private void syncGeneralCheckbox() {
        if (this.cShowOnlyChat.getState()) {
            this.cShowQuits.disable();
            this.cShowJoins.disable();
            this.cShowNickChange.disable();
            this.cShowUserModeChange.disable();
            return;
        }
        this.cShowQuits.enable();
        this.cShowJoins.enable();
        this.cShowNickChange.enable();
        this.cShowUserModeChange.enable();
    }
    
    private void OkButton() {
        this.doGeneralCheck();
        this.doSFxCheck();
        this.myDialog.dispose();
    }
    
    private void CancelButton() {
        this.myDialog.dispose();
    }
    
    private void syncPreview() {
        switch (this.sFxChoice.getSelectedIndex()) {
            case 0: {
                this.textPreview.setBG(this.RtBG);
                this.textPreview.setText(this.PreviewText);
            }
            case 1: {
                this.textPreview.setBG(this.HtBG);
                this.textPreview.setFG(this.HtFG, this.getOppose(this.HtFG));
                this.textPreview.setText(this.PreviewText);
            }
            case 2: {
                this.textPreview.setBG(this.GtBG);
                this.textPreview.setText(this.PreviewText);
            }
            default: {}
        }
    }
    
    private int getOppose(final int n) {
        int n2 = 0;
        switch (n) {
            case 0: {
                n2 = 15;
                break;
            }
            case 1: {
                n2 = 14;
                break;
            }
            case 2: {
                n2 = 12;
                break;
            }
            case 3: {
                n2 = 9;
                break;
            }
            case 4: {
                n2 = 5;
                break;
            }
            case 5: {
                n2 = 4;
                break;
            }
            case 6: {
                n2 = 13;
                break;
            }
            case 7: {
                n2 = 8;
                break;
            }
            case 8: {
                n2 = 7;
                break;
            }
            case 9: {
                n2 = 3;
                break;
            }
            case 10: {
                n2 = 11;
                break;
            }
            case 11: {
                n2 = 10;
                break;
            }
            case 12: {
                n2 = 2;
                break;
            }
            case 13: {
                n2 = 6;
                break;
            }
            case 14: {
                n2 = 15;
                break;
            }
            case 15: {
                n2 = 14;
                break;
            }
        }
        return n2;
    }
    
    private void doSFxCheck() {
        this.getParams().specialNum = this.GradChoice.getSelectedIndex();
        this.getParams().sFxGradientBG = this.GradBG.getColor();
        this.getParams().sFxRandomBG = this.RandBG.getColor();
        this.getParams().sFxHighLightBG = this.sFxHighLightBG.getColor();
        this.getParams().sFxHighLightFG = this.sFxHighLightFG.getColor();
        this.getParams().RtBG = this.colorDecoder(this.RandBG.getColor());
        this.getParams().HtBG = this.colorDecoder(this.sFxHighLightBG.getColor());
        this.getParams().HtFG = this.colorDecoder(this.sFxHighLightFG.getColor());
        this.getParams().Ht2FG = this.getOppose(this.colorDecoder(this.sFxHighLightFG.getColor()));
        this.getParams().GtBG = this.colorDecoder(this.GradBG.getColor());
    }
    
    private void doGeneralCheck() {
        if (!this.getParams().nick.equals(this.tNickFild.getText()) && this.tNickFild.getText() != "") {
            this.theApp.MPanel.Send("NICK " + this.tNickFild.getText());
        }
        if (!this.theApp.MPanel.getParams().uin.equals(this.tICQFild.getText()) && this.tICQFild.getText() != "") {
            this.theApp.MPanel.Send("UIN " + this.tICQFild.getText());
        }
        this.theApp.MPanel.getParams().showOnlyChat = this.cShowOnlyChat.getState();
        this.theApp.MPanel.getParams().showQuits = this.cShowQuits.getState();
        this.theApp.MPanel.getParams().showJoins = this.cShowJoins.getState();
        this.theApp.MPanel.getParams().showNickChange = this.cShowNickChange.getState();
        this.theApp.MPanel.getParams().showUserModeChange = this.cShowUserModeChange.getState();
    }
    
    public void showSFxPanel(final int n) {
        switch (n) {
            case 0: {
                this.cLayout.show(this.sFxCardsPanel, "Rand");
                this.textPreview.setSNumber(n + 1);
                this.syncPreview();
            }
            case 1: {
                this.cLayout.show(this.sFxCardsPanel, "HighLight");
                this.textPreview.setSNumber(n + 1);
                this.syncPreview();
            }
            case 2: {
                this.cLayout.show(this.sFxCardsPanel, "Grad");
                this.textPreview.setSNumber(n + 1);
                this.syncPreview();
            }
            default: {}
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.TabPanel) {
                    this.cardsLayout.show(this.CardsPanel, (String)event.arg);
                    return true;
                }
                if (event.target == this.cShowOnlyChat) {
                    this.syncGeneralCheckbox();
                }
                if (event.target == this.bButtonsPanel) {
                    final String s = (String)event.arg;
                    if (s.equalsIgnoreCase("OK")) {
                        this.doGeneralCheck();
                        this.doSFxCheck();
                        this.myDialog.dispose();
                    }
                    if (s.equalsIgnoreCase("Cancel")) {
                        this.myDialog.dispose();
                    }
                    return true;
                }
                if (event.target == this.sFXcRand) {
                    final int intValue = (int)event.arg;
                    this.RandBG.setColor((Color)this.theApp.MPanel.getParams().colorVector.elementAt(intValue));
                    this.RtBG = intValue;
                    this.syncPreview();
                }
                if (event.target == this.sFXcGrad) {
                    final int intValue2 = (int)event.arg;
                    this.GradBG.setColor((Color)this.theApp.MPanel.getParams().colorVector.elementAt(intValue2));
                    this.GtBG = intValue2;
                    this.syncPreview();
                }
                if (event.target == this.GradChoice) {
                    this.textPreview.setSpecial(this.GradChoice.getSelectedIndex());
                    this.syncPreview();
                }
                if (event.target == this.sFxChoice) {
                    this.showSFxPanel(this.sFxChoice.getSelectedIndex());
                }
                if (event.target == this.sFXcHighLight) {
                    final int intValue3 = (int)event.arg;
                    if (this.cbFxHighLightBG.getState()) {
                        this.sFxHighLightBG.setColor((Color)this.theApp.MPanel.getParams().colorVector.elementAt(intValue3));
                        this.HtBG = intValue3;
                    }
                    else {
                        this.sFxHighLightFG.setColor((Color)this.theApp.MPanel.getParams().colorVector.elementAt(intValue3));
                        this.HtFG = intValue3;
                        this.Ht2FG = 2;
                    }
                    this.syncPreview();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
