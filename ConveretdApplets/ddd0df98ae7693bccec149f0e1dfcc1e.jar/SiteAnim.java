import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SiteAnim extends Applet implements Runnable
{
    private Thread m_siteAnim;
    private boolean m_fStandAlone;
    private TextField[] txtImgs;
    private TextField txtLink;
    private TextField txtTarget;
    private TextField txtLoop;
    private TextArea txtCode;
    private Button btnResetHTML;
    private Button btnCode;
    
    public void stop() {
        if (this.m_siteAnim != null) {
            this.m_siteAnim.stop();
            this.m_siteAnim = null;
        }
    }
    
    public void paint(final Graphics g) {
        g.drawString("Running: " + Math.random(), 10, 20);
    }
    
    public void destroy() {
    }
    
    public static void main(final String[] args) {
        final SiteAnimFrame frame = new SiteAnimFrame("siteAnim");
        frame.show();
        frame.hide();
        frame.resize(frame.insets().left + frame.insets().right + 600, frame.insets().top + frame.insets().bottom + 320);
        final SiteAnim applet_SiteAnim = new SiteAnim();
        frame.add("Center", applet_SiteAnim);
        applet_SiteAnim.m_fStandAlone = true;
        applet_SiteAnim.init();
        applet_SiteAnim.start();
        frame.show();
    }
    
    public void start() {
        if (this.m_siteAnim == null) {
            (this.m_siteAnim = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: siteAnim\r\n" + "Author: Uri Kerbel\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public boolean action(final Event evt, final Object obj) {
        if (evt.target == this.btnResetHTML) {
            this.txtCode.setText("");
            this.btnCode.enable();
            this.validate();
            return true;
        }
        if (evt.target == this.btnCode) {
            this.generateHTML();
            return true;
        }
        return false;
    }
    
    public void run() {
        while (true) {
            try {
                this.validate();
                Thread.sleep(50L);
            }
            catch (InterruptedException e) {
                this.stop();
            }
        }
    }
    
    public void init() {
        this.resize(600, 320);
        this.setBackground(Color.green);
        this.setLayout(new BorderLayout());
        final Panel pnlTitle = new Panel();
        pnlTitle.setLayout(new GridLayout(2, 1));
        final Label lblTitle1 = new Label("Site Animator 1.1", 1);
        lblTitle1.setFont(new Font("TimesRoman", 1, 25));
        lblTitle1.setForeground(Color.blue);
        final Label lblTitle2 = new Label("by Uri Kerbel, 5 Jan 1999", 1);
        lblTitle2.setFont(new Font("TimesRoman", 2, 18));
        lblTitle2.setForeground(Color.blue);
        pnlTitle.add(lblTitle1);
        pnlTitle.add(lblTitle2);
        this.add("North", pnlTitle);
        final Panel pnlImgs = new Panel();
        pnlImgs.setLayout(new GridLayout(4, 1));
        final Label lblImgs = new Label("Please enter the image files in the appropriate order", 1);
        lblImgs.setFont(new Font("TimesRoman", 2, 14));
        pnlImgs.add(lblImgs);
        final Panel[] imgs = new Panel[3];
        int i = 0;
        do {
            (imgs[i] = new Panel()).setLayout(new FlowLayout());
        } while (++i < 3);
        int labNum = 1;
        this.txtImgs = new TextField[9];
        int j = 0;
        do {
            (this.txtImgs[j] = new TextField(10)).setBackground(Color.white);
        } while (++j < 9);
        j = 0;
        do {
            int k = 1;
            do {
                final String curLabel = Integer.toString(labNum);
                imgs[j].add(new Label(curLabel));
                imgs[j].add(this.txtImgs[labNum - 1]);
                ++labNum;
            } while (++k < 4);
            pnlImgs.add(imgs[j]);
        } while (++j < 3);
        final Panel pnlMoreInfo = new Panel();
        pnlMoreInfo.setLayout(new GridLayout(3, 1));
        final Panel pnlOuterBottom = new Panel();
        final Panel pnlLink = new Panel();
        final Label lblLink = new Label("URL to link to");
        pnlLink.add(lblLink);
        (this.txtLink = new TextField(10)).setBackground(Color.white);
        pnlLink.add(this.txtLink);
        pnlOuterBottom.add(pnlLink);
        final Panel pnlTarget = new Panel();
        final Label lblTarget = new Label(" Frame to target");
        pnlTarget.add(lblTarget);
        (this.txtTarget = new TextField(10)).setBackground(Color.white);
        pnlTarget.add(this.txtTarget);
        pnlOuterBottom.add(pnlTarget);
        pnlMoreInfo.add(pnlOuterBottom);
        final Panel pnlLoop = new Panel();
        final Label lblLoop = new Label("Loop Time (Milliseconds)", 1);
        pnlLoop.add(lblLoop);
        (this.txtLoop = new TextField(7)).setBackground(Color.white);
        pnlLoop.add(this.txtLoop);
        final Label lblBlank = new Label("   ");
        pnlLoop.add(lblBlank);
        pnlLoop.add(this.btnResetHTML = new Button("Reset HTML"));
        pnlLoop.add(this.btnCode = new Button("Generate new HTML code"));
        pnlMoreInfo.add(pnlLoop);
        final Panel pnlCode = new Panel();
        pnlCode.setLayout(new BorderLayout());
        pnlCode.add("North", new Label("HTML & JavaScript (paste into .htm file)", 1));
        (this.txtCode = new TextArea("HTML code will appear here...", 30, 30)).setBackground(Color.blue);
        this.txtCode.setForeground(Color.white);
        pnlCode.add("Center", this.txtCode);
        this.txtCode.setEditable(false);
        this.add("Center", pnlImgs);
        this.add("South", pnlMoreInfo);
        this.add("East", pnlCode);
    }
    
    public void generateHTML() {
        int lastImgNum = 0;
        if (this.txtImgs[0].getText().equals("")) {
            this.txtCode.setText("Enter an image file in textfield 1!");
        }
        else {
            int imgsEntered = 0;
            for (int i = 0; i < this.txtImgs.length; ++i) {
                if (!this.txtImgs[i].getText().equals("")) {
                    ++imgsEntered;
                }
            }
            if (imgsEntered < 2) {
                this.txtCode.setText("Please enter more than one image!");
            }
            else {
                for (int i = 0; i < this.txtImgs.length; ++i) {
                    if (!this.txtImgs[i].getText().equals("")) {
                        lastImgNum = i;
                    }
                }
                for (int i = 1; i < lastImgNum; ++i) {
                    if (this.txtImgs[i].getText().equals("")) {
                        this.txtImgs[i].setText(this.txtImgs[i - 1].getText());
                    }
                }
                if (this.txtLoop.getText().equals("")) {
                    this.txtLoop.setText("1000");
                }
                this.txtCode.setText("<HTML>\n" + "<HEAD>\n" + "  <TITLE>Sample animation page</TITLE>\n\n" + "<!-- This banner was generated free of charge\n" + "     by 'Site Animator 1.1'\n" + "     created by Uri Kerbel\n" + "     http://www.kalbi.demon.co.uk\n" + "-->\n\n" + "<SCRIPT LANGUAGE='JavaScript'>\n" + "<!--  Hide from older browsers\n\n");
                for (int i = 0; i <= lastImgNum; ++i) {
                    this.txtCode.appendText("pic" + i + " = new Image()\n");
                    this.txtCode.appendText("pic" + i + ".src = \"" + this.txtImgs[i].getText() + "\"\n");
                }
                this.txtCode.appendText("\n");
                this.txtCode.appendText("var images = new Array(");
                for (int i = 0; i <= lastImgNum; ++i) {
                    this.txtCode.appendText("'" + this.txtImgs[i].getText() + "'");
                    if (i < lastImgNum) {
                        this.txtCode.appendText(",");
                    }
                }
                this.txtCode.appendText(");\n");
                this.txtCode.appendText("\nvar curImg=0;\n\n");
                this.txtCode.appendText("function rotateImgs() {\n");
                this.txtCode.appendText("  if (document.images) {\n");
                this.txtCode.appendText("    if (document.anim.complete) {\n");
                this.txtCode.appendText("      curImg++;\n");
                this.txtCode.appendText("      if (curImg==" + (lastImgNum + 1) + ") {\n");
                this.txtCode.appendText("        curImg=0;\n");
                this.txtCode.appendText("      }\n");
                this.txtCode.appendText("      document.anim.src=images[curImg];\n");
                this.txtCode.appendText("    }\n");
                this.txtCode.appendText("      setTimeout('rotateImgs()',");
                this.txtCode.appendText(" " + this.txtLoop.getText() + ");\n");
                this.txtCode.appendText("  }\n");
                this.txtCode.appendText("}\n");
                this.txtCode.appendText("\n// -->\n");
                this.txtCode.appendText("</SCRIPT>\n\n");
                this.txtCode.appendText("</HEAD>\n");
                this.txtCode.appendText("<BODY BGCOLOR=LIGHTBLUE onload='rotateImgs()'>\n");
                this.txtCode.appendText("<CENTER>");
                if (!this.txtLink.getText().equals("")) {
                    this.txtCode.appendText("<A HREF='" + this.txtLink.getText() + "'");
                    if (!this.txtTarget.getText().equals("")) {
                        this.txtCode.appendText(" TARGET='" + this.txtTarget.getText() + "'");
                    }
                    this.txtCode.appendText(">");
                }
                this.txtCode.appendText("<IMG SRC='" + this.txtImgs[0].getText() + "' BORDER=0 ");
                this.txtCode.appendText(" NAME='anim'></A>");
                this.txtCode.appendText("</CENTER>\n");
                this.txtCode.appendText("</BODY>\n");
                this.txtCode.appendText("</HTML>");
                this.btnCode.disable();
            }
        }
        this.validate();
    }
}
