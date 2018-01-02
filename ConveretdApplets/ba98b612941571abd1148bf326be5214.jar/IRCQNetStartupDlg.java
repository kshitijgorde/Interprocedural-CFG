import java.net.URL;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Dialog;
import java.util.StringTokenizer;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetStartupDlg extends Panel
{
    private Panel ivjContentsPane;
    private Label ivjLabel1;
    private IRCQNetDialog ivjStartUpDlg;
    private Label ivjLabel2;
    private Label ivjLabel3;
    private Label ivjLabel4;
    private Label ivjLabel5;
    private Label ivjLabel6;
    private TextField ivjAltNickName;
    private Choice ivjChannel;
    private Button ivjChatButton;
    private TextField ivjEmail;
    private TextField ivjFullName;
    private Button ivjHelpButton;
    private TextField ivjIcqNumber;
    private TextField ivjNickName;
    private Checkbox ivjShowIP;
    IRCQNet theApp;
    
    public IRCQNetStartupDlg(final Panel panel, final String s, final boolean b, final IRCQNet theApp) {
        this.theApp = theApp;
    }
    
    public void initControls() {
        boolean b = false;
        this.ivjNickName.setText(this.theApp.MPanel.getParams().nick);
        this.ivjFullName.setText(this.theApp.MPanel.getParams().user);
        this.ivjIcqNumber.setText(this.theApp.MPanel.getParams().uin);
        this.ivjEmail.setText(this.theApp.MPanel.getParams().userInfo);
        this.ivjAltNickName.setText(this.theApp.MPanel.getParams().altNick);
        final String channelList = this.theApp.MPanel.getParams().channelList;
        final StringTokenizer stringTokenizer = new StringTokenizer(channelList, ";");
        if (stringTokenizer.countTokens() <= 0) {
            this.ivjChannel.addItem(channelList);
            return;
        }
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            this.ivjChannel.addItem(stringTokenizer.nextToken());
        }
        for (int j = 0; j < this.ivjChannel.countItems(); ++j) {
            if (this.ivjChannel.getItem(j).equalsIgnoreCase(this.theApp.MPanel.getParams().firstJoin)) {
                b = true;
            }
        }
        if (!b) {
            this.ivjChannel.addItem(this.theApp.MPanel.getParams().firstJoin);
        }
        this.ivjChannel.select(this.theApp.MPanel.getParams().firstJoin);
        this.ivjShowIP.setState(this.theApp.MPanel.getParams().showIP);
    }
    
    private TextField getAltNickName() {
        if (this.ivjAltNickName == null) {
            try {
                (this.ivjAltNickName = new TextField()).setText("");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjAltNickName;
    }
    
    private Choice getChannel() {
        if (this.ivjChannel == null) {
            try {
                this.ivjChannel = new Choice();
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjChannel;
    }
    
    private Button getChatButton() {
        if (this.ivjChatButton == null) {
            try {
                (this.ivjChatButton = new Button()).setLabel("Chat");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjChatButton;
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                this.ivjContentsPane = new Panel();
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjContentsPane;
    }
    
    private TextField getEmail() {
        if (this.ivjEmail == null) {
            try {
                this.ivjEmail = new TextField();
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjEmail;
    }
    
    private TextField getFullName() {
        if (this.ivjFullName == null) {
            try {
                (this.ivjFullName = new TextField()).setText("");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjFullName;
    }
    
    private Button getHelpButton() {
        if (this.ivjHelpButton == null) {
            try {
                (this.ivjHelpButton = new Button()).setLabel("Help");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjHelpButton;
    }
    
    private TextField getIcqNumber() {
        if (this.ivjIcqNumber == null) {
            try {
                this.ivjIcqNumber = new TextField();
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjIcqNumber;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setAlignment(0);
                this.ivjLabel1.setText("Nick Name");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabel2() {
        if (this.ivjLabel2 == null) {
            try {
                (this.ivjLabel2 = new Label()).setAlignment(1);
                this.ivjLabel2.setText("ICQ#");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel2;
    }
    
    private Label getLabel3() {
        if (this.ivjLabel3 == null) {
            try {
                (this.ivjLabel3 = new Label()).setAlignment(0);
                this.ivjLabel3.setText("Email Address");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel3;
    }
    
    private Label getLabel4() {
        if (this.ivjLabel4 == null) {
            try {
                (this.ivjLabel4 = new Label()).setAlignment(0);
                this.ivjLabel4.setText("Channel");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel4;
    }
    
    private Label getLabel5() {
        if (this.ivjLabel5 == null) {
            try {
                (this.ivjLabel5 = new Label()).setAlignment(0);
                this.ivjLabel5.setText("Full Name");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel5;
    }
    
    private Label getLabel6() {
        if (this.ivjLabel6 == null) {
            try {
                (this.ivjLabel6 = new Label()).setAlignment(0);
                this.ivjLabel6.setText("Alt. Nick Name");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjLabel6;
    }
    
    private TextField getNickName() {
        if (this.ivjNickName == null) {
            try {
                (this.ivjNickName = new TextField()).setText("IrCQ-Net");
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjNickName;
    }
    
    private Checkbox getShowIP() {
        if (this.ivjShowIP == null) {
            try {
                (this.ivjShowIP = new Checkbox()).setLabel("Show my IP");
                this.ivjShowIP.setState(true);
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return this.ivjShowIP;
    }
    
    public boolean displayControl() {
        this.getStartUpDlg().show();
        return true;
    }
    
    private Dialog getStartUpDlg() {
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
        final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        if (this.ivjStartUpDlg == null) {
            try {
                (this.ivjStartUpDlg = new IRCQNetDialog(new Frame(), "IRCQNet", true, this)).setResizable(false);
                this.ivjStartUpDlg.setBackground(IRCQNetColors.controlColor);
                this.ivjStartUpDlg.setForeground(IRCQNetColors.controlColorFG);
                final GridBagLayout gridBagLayout;
                this.setLayout(gridBagLayout = new GridBagLayout());
                this.ivjStartUpDlg.setLayout(new BorderLayout());
                this.ivjStartUpDlg.resize(334, 243);
                this.ivjStartUpDlg.setTitle("Fill in your details");
                gridBagConstraints.gridx = 5;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.getContentsPane(), gridBagConstraints);
                this.add(this.getContentsPane());
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 8;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.anchor = 15;
                gridBagConstraints2.weightx = 0.0;
                gridBagConstraints2.weighty = 0.0;
                gridBagLayout.setConstraints(this.getShowIP(), gridBagConstraints2);
                this.add(this.getShowIP());
                gridBagConstraints3.gridx = 1;
                gridBagConstraints3.gridy = 1;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 17;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagLayout.setConstraints(this.getNickName(), gridBagConstraints3);
                this.add(this.getNickName());
                gridBagConstraints4.gridx = 0;
                gridBagConstraints4.gridy = 0;
                gridBagConstraints4.gridwidth = 2;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.fill = 2;
                gridBagConstraints4.anchor = 10;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                gridBagLayout.setConstraints(this.getLabel1(), gridBagConstraints4);
                this.add(this.getLabel1());
                gridBagConstraints5.gridx = 5;
                gridBagConstraints5.gridy = 0;
                gridBagConstraints5.gridwidth = 1;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.anchor = 10;
                gridBagConstraints5.weightx = 0.0;
                gridBagConstraints5.weighty = 0.0;
                gridBagLayout.setConstraints(this.getLabel2(), gridBagConstraints5);
                this.add(this.getLabel2());
                gridBagConstraints6.gridx = 5;
                gridBagConstraints6.gridy = 1;
                gridBagConstraints6.gridwidth = 2;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.fill = 2;
                gridBagConstraints6.anchor = 10;
                gridBagConstraints6.weightx = 0.0;
                gridBagConstraints6.weighty = 0.0;
                gridBagConstraints6.insets = new Insets(0, 10, 0, 0);
                gridBagLayout.setConstraints(this.getIcqNumber(), gridBagConstraints6);
                this.add(this.getIcqNumber());
                gridBagConstraints7.gridx = 1;
                gridBagConstraints7.gridy = 4;
                gridBagConstraints7.gridwidth = 5;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.fill = 2;
                gridBagConstraints7.anchor = 10;
                gridBagConstraints7.weightx = 0.0;
                gridBagConstraints7.weighty = 0.0;
                gridBagLayout.setConstraints(this.getLabel3(), gridBagConstraints7);
                this.add(this.getLabel3());
                gridBagConstraints8.gridx = 1;
                gridBagConstraints8.gridy = 5;
                gridBagConstraints8.gridwidth = 6;
                gridBagConstraints8.gridheight = 1;
                gridBagConstraints8.fill = 2;
                gridBagConstraints8.anchor = 10;
                gridBagConstraints8.weightx = 0.0;
                gridBagConstraints8.weighty = 0.0;
                gridBagLayout.setConstraints(this.getEmail(), gridBagConstraints8);
                this.add(this.getEmail());
                gridBagConstraints9.gridx = 1;
                gridBagConstraints9.gridy = 6;
                gridBagConstraints9.gridwidth = 2;
                gridBagConstraints9.gridheight = 1;
                gridBagConstraints9.fill = 2;
                gridBagConstraints9.anchor = 10;
                gridBagConstraints9.weightx = 0.0;
                gridBagConstraints9.weighty = 0.0;
                gridBagLayout.setConstraints(this.getLabel4(), gridBagConstraints9);
                this.add(this.getLabel4());
                gridBagConstraints10.gridx = 1;
                gridBagConstraints10.gridy = 7;
                gridBagConstraints10.gridwidth = 1;
                gridBagConstraints10.gridheight = 1;
                gridBagConstraints10.fill = 2;
                gridBagConstraints10.anchor = 10;
                gridBagConstraints10.weightx = 0.0;
                gridBagConstraints10.weighty = 0.0;
                gridBagLayout.setConstraints(this.getChannel(), gridBagConstraints10);
                this.add(this.getChannel());
                gridBagConstraints11.gridx = 5;
                gridBagConstraints11.gridy = 2;
                gridBagConstraints11.gridwidth = 2;
                gridBagConstraints11.gridheight = 1;
                gridBagConstraints11.fill = 2;
                gridBagConstraints11.anchor = 10;
                gridBagConstraints11.weightx = 0.0;
                gridBagConstraints11.weighty = 0.0;
                gridBagConstraints11.insets = new Insets(0, 10, 0, 0);
                gridBagLayout.setConstraints(this.getLabel5(), gridBagConstraints11);
                this.add(this.getLabel5());
                gridBagConstraints12.gridx = 5;
                gridBagConstraints12.gridy = 3;
                gridBagConstraints12.gridwidth = 2;
                gridBagConstraints12.gridheight = 1;
                gridBagConstraints12.fill = 2;
                gridBagConstraints12.anchor = 17;
                gridBagConstraints12.weightx = 0.0;
                gridBagConstraints12.weighty = 0.0;
                gridBagConstraints12.insets = new Insets(0, 10, 0, 0);
                gridBagLayout.setConstraints(this.getFullName(), gridBagConstraints12);
                this.add(this.getFullName());
                gridBagConstraints13.gridx = 1;
                gridBagConstraints13.gridy = 2;
                gridBagConstraints13.gridwidth = 1;
                gridBagConstraints13.gridheight = 1;
                gridBagConstraints13.anchor = 10;
                gridBagConstraints13.weightx = 0.0;
                gridBagConstraints13.weighty = 0.0;
                gridBagLayout.setConstraints(this.getLabel6(), gridBagConstraints13);
                this.add(this.getLabel6());
                gridBagConstraints14.gridx = 1;
                gridBagConstraints14.gridy = 3;
                gridBagConstraints14.gridwidth = 1;
                gridBagConstraints14.gridheight = 1;
                gridBagConstraints14.fill = 2;
                gridBagConstraints14.anchor = 10;
                gridBagConstraints14.weightx = 0.0;
                gridBagConstraints14.weighty = 0.0;
                gridBagLayout.setConstraints(this.getAltNickName(), gridBagConstraints14);
                this.add(this.getAltNickName());
                gridBagConstraints15.gridx = 6;
                gridBagConstraints15.gridy = 7;
                gridBagConstraints15.gridwidth = 1;
                gridBagConstraints15.gridheight = 1;
                gridBagConstraints15.anchor = 10;
                gridBagConstraints15.weightx = 0.0;
                gridBagConstraints15.weighty = 0.0;
                gridBagLayout.setConstraints(this.getChatButton(), gridBagConstraints15);
                this.add(this.getChatButton());
                gridBagConstraints16.gridx = 5;
                gridBagConstraints16.gridy = 7;
                gridBagConstraints16.gridwidth = 1;
                gridBagConstraints16.gridheight = 1;
                gridBagConstraints16.anchor = 10;
                gridBagConstraints16.weightx = 0.0;
                gridBagConstraints16.weighty = 0.0;
                gridBagConstraints16.insets = new Insets(0, 5, 0, 5);
                gridBagLayout.setConstraints(this.getHelpButton(), gridBagConstraints16);
                this.add(this.getHelpButton());
                this.ivjStartUpDlg.add("Center", this);
            }
            catch (Throwable t) {
                t.printStackTrace(System.out);
            }
            this.initControls();
        }
        return this.ivjStartUpDlg;
    }
    
    private void handleException(final Throwable t) {
        t.printStackTrace(System.out);
    }
    
    private void initialize() {
    }
    
    public boolean handleEvent(final Event event) {
        Label_0160: {
            switch (event.id) {
                case 401: {
                    switch (event.key) {
                        case 10: {
                            this.doConnectbutton();
                            this.theApp.MPanel.ConnectToServer();
                            this.theApp.MPanel.requestFocus();
                            return true;
                        }
                        default: {
                            break Label_0160;
                        }
                    }
                    break;
                }
                case 1001: {
                    if (event.arg == null) {
                        return false;
                    }
                    String s;
                    try {
                        s = (String)event.arg;
                    }
                    catch (ClassCastException ex) {
                        return false;
                    }
                    if (s.equals("Help")) {
                        this.doHelpbutton();
                        return true;
                    }
                    if (s.equals("Chat")) {
                        this.doConnectbutton();
                        this.theApp.MPanel.ConnectToServer();
                        this.theApp.MPanel.requestFocus();
                        return true;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    private void doConnectbutton() {
        this.theApp.MPanel.getParams().nick = this.ivjNickName.getText();
        this.theApp.MPanel.getParams().user = this.ivjFullName.getText();
        this.theApp.MPanel.getParams().uin = this.ivjIcqNumber.getText();
        this.theApp.MPanel.getParams().userInfo = this.ivjEmail.getText();
        this.theApp.MPanel.getParams().altNick = this.ivjAltNickName.getText();
        this.theApp.MPanel.getParams().firstJoin = this.ivjChannel.getSelectedItem();
        this.theApp.MPanel.getParams().showIP = this.ivjShowIP.getState();
        this.ivjStartUpDlg.dispose();
    }
    
    private void doHelpbutton() {
        try {
            this.theApp.getAppletContext().showDocument(new URL(new String("http://www.icq.com")), "_blank");
        }
        catch (Exception ex) {}
    }
}
