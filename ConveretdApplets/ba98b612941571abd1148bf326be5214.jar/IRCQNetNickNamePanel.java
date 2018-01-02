import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetNickNamePanel extends IRCQNetPanel
{
    private IRCQNetImageButtonPanel buttonsPanel;
    private IRCQNetDialogPanel thePanel;
    private String sText;
    private Label lText;
    private Label titleText;
    private String sLabel;
    private String sNick;
    private TextField tNick;
    private IRCQNet theApp;
    private boolean exitable;
    
    public IRCQNetNickNamePanel(final IRCQNet theApp, final String sText, final String sNick, final boolean exitable) {
        super(theApp);
        this.exitable = exitable;
        this.theApp = theApp;
        this.sText = sText;
        this.sNick = sNick;
        this.initPanel();
        this.initButtons();
        this.initsLayout();
    }
    
    private void initPanel() {
        this.setForeground(IRCQNetColors.controlColorFG);
        this.setLayout(new BorderLayout());
        this.thePanel = new IRCQNetDialogPanel();
        this.lText = new Label("Nickname", 0);
        this.titleText = new Label(this.sText, 0);
        (this.tNick = new TextField()).setText(this.sNick);
        this.titleText.setAlignment(0);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.show();
    }
    
    public void paint(final Graphics graphics) {
        final Font font = this.titleText.getFont();
        if (font == null) {
            return;
        }
        this.titleText.setFont(new Font(font.getName(), 1, font.getSize()));
    }
    
    private void initButtons() {
        (this.buttonsPanel = new IRCQNetImageButtonPanel(this.theApp)).setCenter(true);
        this.buttonsPanel.setFontSize(10);
        this.buttonsPanel.resize(160, 30);
        this.buttonsPanel.setButtonsWidth(60);
        this.buttonsPanel.SetSystemColors(false);
        this.buttonsPanel.setBackground(Color.white);
        this.buttonsPanel.setForeground(Color.black);
        this.buttonsPanel.NewButton("OK", 3);
        if (this.exitable) {
            this.buttonsPanel.NewButton("Cancel", 4);
        }
    }
    
    private void initsLayout() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.thePanel);
        final GridBagLayout layout = new GridBagLayout();
        this.thePanel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.anchor = 17;
        layout.setConstraints(this.titleText, gridBagConstraints);
        this.thePanel.add(this.titleText);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.insets = new Insets(0, 0, 0, 2);
        layout.setConstraints(this.lText, gridBagConstraints3);
        this.thePanel.add(this.lText);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = 3;
        gridBagConstraints4.ipadx = 80;
        layout.setConstraints(this.tNick, gridBagConstraints4);
        this.thePanel.add(this.tNick);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.fill = 2;
        gridBagConstraints5.insets = new Insets(10, 0, 0, 0);
        layout.setConstraints(this.buttonsPanel, gridBagConstraints5);
        this.thePanel.add(this.buttonsPanel);
    }
    
    public boolean handleEvent(final Event event) {
        Label_0213: {
            switch (event.id) {
                case 401: {
                    switch (event.key) {
                        case 10: {
                            this.theApp.MPanel.getClient().ChangeNick(this.tNick.getText());
                            this.theApp.MPanel.CloseNickNameTab();
                            return true;
                        }
                        default: {
                            break Label_0213;
                        }
                    }
                    break;
                }
                case 1001: {
                    final String s = (String)event.arg;
                    if (s.equals("OK")) {
                        if (this.tNick.getText().length() > 1) {
                            this.theApp.MPanel.getParams().nick = this.tNick.getText();
                        }
                        if (this.theApp.appletStart) {
                            this.theApp.MPanel.getClient().ChangeNick(this.tNick.getText());
                            this.theApp.MPanel.CloseNickNameTab();
                            break;
                        }
                        this.theApp.connect();
                        break;
                    }
                    else {
                        if (s.equals("Cancel")) {
                            this.theApp.MPanel.CloseNickNameTab();
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
}
