import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetSearchPanel extends Panel
{
    private IRCQNetImageButtonPanel buttonsPanel;
    private IRCQNetDialog myDialog;
    private IRCQNetDialogPanel thePanel;
    private IRCQNetDialogPanel theRadio;
    private IRCQNet theApp;
    private TextField searchInput;
    private Checkbox sTheWeb;
    private Checkbox sIcq;
    private Checkbox sRoom;
    private Frame myFrame;
    public Canvas ePost;
    
    public IRCQNetSearchPanel(final IRCQNet theApp) {
        this.thePanel = new IRCQNetDialogPanel();
        this.theRadio = new IRCQNetDialogPanel();
        this.theApp = theApp;
        this.ePost = new Canvas();
        this.initButtons();
        this.initPanel();
        this.initsLayout();
        this.initDialog();
    }
    
    private void initDialog() {
        (this.myDialog = new IRCQNetDialog(this.myFrame = new Frame("IrCQNet Search"), "IrCQNet Search", false, this)).setResizable(false);
        this.myDialog.setBackground(Color.lightGray);
        this.myDialog.setForeground(Color.black);
        this.myDialog.setLayout(new BorderLayout());
        this.myDialog.resize(450, 170);
        this.myDialog.setTitle("IrCQNet Search");
        this.myDialog.add("Center", this);
        this.show();
        this.myDialog.show();
    }
    
    private void initButtons() {
        (this.buttonsPanel = new IRCQNetImageButtonPanel(this.theApp)).setCenter(true);
        this.buttonsPanel.setFontSize(10);
        this.buttonsPanel.resize(90, 30);
        this.buttonsPanel.setButtonsWidth(60);
        this.buttonsPanel.NewButton("GO!", 6);
        this.buttonsPanel.NewButton("Close", 4);
    }
    
    private void initPanel() {
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.searchInput = new TextField();
        this.theRadio.setLayout(new FlowLayout());
        this.sTheWeb = new Checkbox("Search The Web", checkboxGroup, true);
        this.sIcq = new Checkbox("Search ICQ site", checkboxGroup, false);
        this.sRoom = new Checkbox("Search Room", checkboxGroup, false);
        this.theRadio.add(this.sTheWeb);
        this.theRadio.add(this.sIcq);
        this.theRadio.add(this.sRoom);
    }
    
    private void initsLayout() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.thePanel);
        this.add("South", this.buttonsPanel);
        final GridBagLayout layout = new GridBagLayout();
        this.thePanel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.theRadio, gridBagConstraints);
        this.thePanel.add(this.theRadio);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.searchInput, gridBagConstraints);
        this.thePanel.add(this.searchInput);
    }
    
    public boolean handleEvent(final Event event) {
        Label_0111: {
            switch (event.id) {
                case 401: {
                    switch (event.key) {
                        case 10: {
                            this.doGOButton();
                            return true;
                        }
                        default: {
                            break Label_0111;
                        }
                    }
                    break;
                }
                case 1001: {
                    if (event.target != this.buttonsPanel) {
                        break;
                    }
                    final String s = (String)event.arg;
                    if (s.equals("GO!")) {
                        this.doGOButton();
                        return true;
                    }
                    if (s.equals("Close")) {
                        this.closeWindow();
                        return true;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    private void doGOButton() {
        final String text = this.searchInput.getText();
        if (this.sTheWeb.getState()) {
            final String string = "http://web.icq.com/redirect/search?KEYWORDS=" + this.getQuery(text, "+") + "&range=web";
            try {
                this.theApp.getAppletContext().showDocument(new URL(string), "_blank");
                return;
            }
            catch (MalformedURLException ex) {
                return;
            }
        }
        if (this.sIcq.getState()) {
            final String string2 = "http://web.icq.com/redirect/search?KEYWORDS=" + this.getQuery(text, "+") + "&range=site";
            try {
                this.theApp.getAppletContext().showDocument(new URL(string2), "_blank");
                return;
            }
            catch (MalformedURLException ex2) {
                return;
            }
        }
        if (this.sRoom.getState()) {
            this.ePost.postEvent(new Event(this, 10017, this.getQuery(text, ";")));
        }
    }
    
    private void doCloseButton() {
        this.closeWindow();
    }
    
    private String getQuery(final String s, final String s2) {
        String s3 = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken(" ");
            if (nextToken != null && nextToken != "") {
                if (s3.equals("")) {
                    s3 += nextToken;
                }
                else {
                    s3 = s3 + s2 + nextToken;
                }
            }
        }
        return s3;
    }
    
    public void closeWindow() {
        this.ePost.postEvent(new Event(this, 10019, null));
        this.myDialog.dispose();
        System.gc();
    }
    
    public void getFocus() {
        this.requestFocus();
    }
}
