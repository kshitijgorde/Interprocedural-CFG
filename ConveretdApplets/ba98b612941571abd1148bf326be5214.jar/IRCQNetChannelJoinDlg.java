import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Canvas;
import java.awt.List;
import java.awt.Label;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetChannelJoinDlg extends Panel
{
    private IRCQNet theApp;
    private GridBagLayout myLayout;
    private TextField tChanField;
    private Label lChanField;
    private List chanList;
    private IRCQNetImageButtonPanel bButtonsPanel;
    public Canvas ePost;
    private IRCQNetDialogPanel mPanel;
    private IRCQNetDialog myDialog;
    
    public IRCQNetChannelJoinDlg(final IRCQNet theApp) {
        this.myLayout = new GridBagLayout();
        this.theApp = theApp;
        this.ePost = new Canvas();
        this.initDialog();
        this.initControls();
        this.initButtonsPanel();
        this.initParam();
        this.initLayout();
    }
    
    private void initDialog() {
        (this.myDialog = new IRCQNetDialog(new Frame(), "IRCQNet", true, this)).setResizable(false);
        this.myDialog.setBackground(IRCQNetColors.controlColor);
        this.myDialog.setForeground(IRCQNetColors.controlColorFG);
        this.myDialog.setLayout(new BorderLayout());
        this.myDialog.resize(400, 300);
        this.myDialog.setTitle("IRCQNet Join Room");
        this.myDialog.add("Center", this);
    }
    
    private void initControls() {
        this.mPanel = new IRCQNetDialogPanel();
        this.tChanField = new TextField();
        this.lChanField = new Label("New Room", 0);
        this.chanList = new List(10, false);
        this.tChanField.setForeground(IRCQNetColors.controlColorLighter);
        this.chanList.setForeground(IRCQNetColors.controlColorLighter);
    }
    
    private void initParam() {
        final String channelList = this.theApp.MPanel.getParams().channelList;
        final StringTokenizer stringTokenizer = new StringTokenizer(channelList, ";");
        if (stringTokenizer.countTokens() <= 0) {
            this.chanList.addItem(channelList);
            return;
        }
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            this.chanList.addItem(stringTokenizer.nextToken());
        }
    }
    
    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.mPanel.setLayout(this.myLayout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        this.myLayout.setConstraints(this.lChanField, gridBagConstraints);
        this.mPanel.add(this.lChanField);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        this.myLayout.setConstraints(this.tChanField, gridBagConstraints);
        this.mPanel.add(this.tChanField);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new Insets(2, 30, 2, 2);
        this.myLayout.setConstraints(this.chanList, gridBagConstraints);
        this.mPanel.add(this.chanList);
        this.add("Center", this.mPanel);
        this.add("South", this.bButtonsPanel);
    }
    
    public void startDlg() {
        this.show();
        this.myDialog.show();
    }
    
    private void initButtonsPanel() {
        (this.bButtonsPanel = new IRCQNetImageButtonPanel(this.theApp)).setCenter(true);
        this.bButtonsPanel.setFontSize(10);
        this.bButtonsPanel.resize(90, 30);
        this.bButtonsPanel.setButtonsWidth(60);
        this.bButtonsPanel.NewButton("OK", 3);
        this.bButtonsPanel.NewButton("Cancel", 4);
    }
    
    private void OkButton() {
        final String text = this.tChanField.getText();
        if (text.length() > 0) {
            if (text.startsWith("#")) {
                this.ePost.postEvent(new Event(this, 10008, "!STATUS!;/JOIN " + text));
            }
            else {
                this.ePost.postEvent(new Event(this, 10008, "!STATUS!;/JOIN #" + text));
            }
        }
        else if (this.chanList.getSelectedIndex() > -1) {
            this.ePost.postEvent(new Event(this, 10008, "!STATUS!;/JOIN " + this.chanList.getSelectedItem()));
        }
        this.myDialog.dispose();
    }
    
    private void CancelButton() {
        this.myDialog.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.bButtonsPanel) {
                    final String s = (String)event.arg;
                    if (s.equalsIgnoreCase("OK")) {
                        this.OkButton();
                    }
                    if (s.equalsIgnoreCase("Cancel")) {
                        this.myDialog.dispose();
                    }
                    return true;
                }
            }
            case 401: {
                if (event.key == 10) {
                    this.OkButton();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
