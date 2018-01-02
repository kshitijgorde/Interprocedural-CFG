// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.awt.Button;
import java.awt.TextField;
import java.applet.Applet;

public class searchApplet extends Applet
{
    TextField keywordTextField;
    Button search;
    ReportDesktop rd;
    long daysLeftToEvaluate;
    protected stringTranslator st;
    Calendar expiryDateCalendar;
    boolean evaluationVersion;
    String appletVersion;
    
    public void init() {
        this.setLayout(new FlowLayout(1, 5, 5));
        this.setBackground(Color.white);
        this.setSize(300, 300);
        this.add(this.keywordTextField);
        this.keywordTextField.setBounds(96, 5, 24, 23);
        this.search.setLabel("Initialising...");
        this.add(this.search);
        this.search.setBackground(Color.lightGray);
        this.search.setBounds(125, 5, 79, 23);
        final SymAction lSymAction = new SymAction();
        this.search.addActionListener(lSymAction);
        this.keywordTextField.addActionListener(lSymAction);
        this.keywordTextField.setBackground(Color.white);
        final searchAppletBeanInfo bean = new searchAppletBeanInfo();
        this.initStringTranslator();
        this.checkRegistration();
        final String bgColour = this.getParameter("bgColour");
        final Integer bgColourI = Integer.decode(bgColour);
        this.setBackground(new Color(bgColourI));
        this.search.setLabel(this.st.search);
    }
    
    public void search_ActionPerformed(final ActionEvent event) {
        final String files = this.getParameter("searchFiles");
        final String report_backround_colour = this.getParameter("report_backround_colour");
        final String report_hyperlink_colour = this.getParameter("report_hyperlink_colour");
        final String report_text_colour = this.getParameter("report_text_colour");
        Integer reqdColour = Integer.decode(report_backround_colour);
        final Color rpt_backround_colour = new Color(reqdColour);
        reqdColour = Integer.decode(report_hyperlink_colour);
        final Color rpt_hyperlink_colour = new Color(reqdColour);
        reqdColour = Integer.decode(report_text_colour);
        final Color rpt_text_colour = new Color(reqdColour);
        this.rd = new ReportDesktop(this, files, this.keywordTextField.getText(), rpt_backround_colour, rpt_hyperlink_colour, rpt_text_colour, this.evaluationVersion, this.daysLeftToEvaluate, this.appletVersion, this.st);
    }
    
    public String getAppletInfo() {
        return "Client Side Search Engine from Rhino Software.  \nhttp://www.rhinosoftware.co.uk  \nCopyright © 2000-2001, Rhino Software, All Rights Reserved. \n";
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "bgColour", "RBG Hexidecimal", "Background colour reference of the Applet" }, { "searchFiles", "URL", "List of files to search, separated by a semicolon.  They can be relative (filename.type) or absolute (http://www.mysite.co.uk/myfile.htm)." }, { "report_backround_colour", "RBG Hexidecimal", "Background colour reference for the search report." }, { "report_hyperlink_colour", "RBG Hexidecimal", "Hyperlink colour reference for the search report." }, { "report_text_colour", "RBG Hexidecimal", "Text colour reference for the search report." } };
        return info;
    }
    
    public void keywordTextField_ActionPerformed(final ActionEvent event) {
        this.search_ActionPerformed(event);
    }
    
    public void checkRegistration() {
        final Calendar calendar = new GregorianCalendar();
        final Date timeNow = new Date();
        calendar.setTime(timeNow);
        final Date now = calendar.getTime();
        final Date evaluationExpires = this.expiryDateCalendar.getTime();
        this.daysLeftToEvaluate = (evaluationExpires.getTime() - now.getTime()) / 86400000L;
        this.keywordTextField.setText("EVALUATION");
        if (this.daysLeftToEvaluate < 0L & this.evaluationVersion) {
            this.keywordTextField.setBackground(Color.red);
            this.keywordTextField.setForeground(Color.white);
            this.keywordTextField.setText(this.st.evaluationExpired);
            this.search.setVisible(false);
        }
    }
    
    protected void initStringTranslator() {
        this.st = new stringTranslator();
    }
    
    public searchApplet() {
        this.keywordTextField = new TextField(10);
        this.search = new Button();
        this.expiryDateCalendar = new GregorianCalendar(2003, 11, 15);
        this.evaluationVersion = true;
        this.appletVersion = new String("V4.0.2");
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object object = event.getSource();
            if (object == searchApplet.this.search) {
                searchApplet.this.search_ActionPerformed(event);
            }
            else if (object == searchApplet.this.keywordTextField) {
                searchApplet.this.keywordTextField_ActionPerformed(event);
            }
        }
    }
}
