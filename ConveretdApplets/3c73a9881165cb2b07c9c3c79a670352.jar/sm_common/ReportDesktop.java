// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.List;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.applet.Applet;
import java.awt.ScrollPane;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;

public class ReportDesktop extends Frame
{
    boolean fComponentsAdjusted;
    Panel Control;
    Button Cancel;
    Panel Info;
    Label title;
    Button InfoButton;
    ScrollPane Report;
    boolean evalVersion;
    long evalDaysLeft;
    String ver;
    stringTranslator st;
    private Applet par;
    private String listOfFiles;
    private String key;
    private reportThread doSearchThread;
    Color rpt_Bg_Colour;
    Color rpt_Hl_Colour;
    Color rpt_Text_Colour;
    
    public ReportDesktop(final Applet parent, final String fList, final String keywords, final Color rpt_backround_colour, final Color rpt_hyperlink_colour, final Color rpt_text_colour, final boolean evaluationVersion, final long daysLeftToEvaluate, final String versionNumber, final stringTranslator strT) {
        this.fComponentsAdjusted = false;
        this.Control = new Panel();
        this.Cancel = new Button();
        this.Info = new Panel();
        this.title = new Label();
        this.InfoButton = new Button();
        this.Report = new ScrollPane(0);
        this.listOfFiles = fList;
        this.par = parent;
        this.key = keywords;
        this.rpt_Bg_Colour = rpt_backround_colour;
        this.rpt_Hl_Colour = rpt_hyperlink_colour;
        this.rpt_Text_Colour = rpt_text_colour;
        this.evalVersion = evaluationVersion;
        this.evalDaysLeft = daysLeftToEvaluate;
        this.ver = versionNumber;
        this.st = strT;
        this.setLayout(new BorderLayout(0, 0));
        this.setSize(700, 400);
        this.setVisible(false);
        this.Control.setLayout(new FlowLayout(1, 5, 5));
        this.add("South", this.Control);
        this.Control.setBackground(Color.lightGray);
        this.Control.setBounds(0, 367, 700, 33);
        this.Cancel.setLabel("Cancel");
        this.Control.add(this.Cancel);
        this.Cancel.setBounds(323, 5, 53, 23);
        this.Info.setLayout(new BorderLayout(0, 0));
        this.add("North", this.Info);
        this.Info.setBounds(0, 0, 700, 23);
        this.Info.add("Center", this.title);
        this.title.setFont(new Font("Dialog", 1, 12));
        this.title.setBounds(17, 0, 683, 23);
        this.InfoButton.setLabel("i");
        this.Info.add("West", this.InfoButton);
        this.InfoButton.setFont(new Font("Dialog", 3, 12));
        this.InfoButton.setBounds(0, 0, 17, 23);
        this.add("Center", this.Report);
        this.Report.setForeground(Color.black);
        this.Report.setBounds(0, 23, 700, 344);
        final SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        final SymMouse aSymMouse = new SymMouse();
        final SymAction lSymAction = new SymAction();
        this.Cancel.addActionListener(lSymAction);
        this.InfoButton.addActionListener(lSymAction);
        this.title.setBackground(this.rpt_Bg_Colour);
        this.setBackground(this.rpt_Bg_Colour);
        this.Cancel.setLabel(this.st.cancelLabel);
        this.setTitle(this.st.searchResults);
        this.title.setText(String.valueOf(this.st.searchForKeywords) + this.key);
        this.setVisible(true);
        (this.doSearchThread = new reportThread(this)).start();
    }
    
    public void setVisible(final boolean b) {
        if (b) {
            this.setLocation(50, 50);
        }
        super.setVisible(b);
    }
    
    public void addNotify() {
        final Dimension d = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets ins = this.getInsets();
        this.setSize(ins.left + ins.right + d.width, ins.top + ins.bottom + d.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point p = components[i].getLocation();
            p.translate(ins.left, ins.top);
            components[i].setLocation(p);
        }
        this.fComponentsAdjusted = true;
    }
    
    void ReportDesktop_WindowClosing(final WindowEvent event) {
        this.dispose();
    }
    
    public void doSearch() {
        final List searchFiles = new List();
        char nextChar = ' ';
        StringBuffer filename = new StringBuffer(30);
        for (int n = 0; n != this.listOfFiles.length(); ++n) {
            nextChar = this.listOfFiles.charAt(n);
            if (nextChar == ';') {
                searchFiles.addItem(filename.toString());
                filename = new StringBuffer(30);
            }
            else {
                filename.append(nextChar);
            }
        }
        if (!filename.toString().equals("")) {
            searchFiles.addItem(filename.toString());
        }
        final ReportBodyPanel rb = new ReportBodyPanel(this.par, this, searchFiles, this.key, this.rpt_Bg_Colour, this.rpt_Hl_Colour, this.rpt_Text_Colour, this.st);
        this.Report.add(rb);
    }
    
    void Cancel_ActionPerformed(final ActionEvent event) {
        this.dispose();
    }
    
    void InfoButton_ActionPerformed(final ActionEvent event) {
        String evalNotice;
        if (this.evalVersion) {
            evalNotice = new String(String.valueOf(this.st.evaluationVersion) + this.evalDaysLeft + this.st.daysLeftToEvaluate);
        }
        else {
            evalNotice = new String(this.st.registeredVersion);
        }
        final String infoText = new String(String.valueOf(this.st.ifYouHaveAnyCommentsOrQuestions) + this.ver + "\n" + evalNotice + "\n" + this.st.copyright);
        final AttentionDialog ad = new AttentionDialog(this, this.st.rhinoSoftwareWelcomesTheFeedbackYouCanProvide, infoText);
        ad.show();
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent event) {
            final Object object = event.getSource();
            if (object == ReportDesktop.this) {
                ReportDesktop.this.ReportDesktop_WindowClosing(event);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent event) {
        }
        
        public void mouseExited(final MouseEvent event) {
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object object = event.getSource();
            if (object == ReportDesktop.this.Cancel) {
                ReportDesktop.this.Cancel_ActionPerformed(event);
            }
            else if (object == ReportDesktop.this.InfoButton) {
                ReportDesktop.this.InfoButton_ActionPerformed(event);
            }
        }
    }
}
