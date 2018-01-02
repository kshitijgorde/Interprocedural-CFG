import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuizMaster extends Applet implements MouseListener, MouseMotionListener
{
    public boolean registered;
    private Image gbuffer;
    private Graphics gbuf;
    private QMData qmd;
    public static Color[] colors;
    public static Font[] fonts;
    public static FontMetrics[] fms;
    public static String[] strings;
    public Dimension dim;
    public String errorString;
    
    public QuizMaster() {
        this.registered = false;
    }
    
    public void init() {
        System.out.println("\nQuizMaster Version 1.1.0");
        System.out.println("************************\n");
        String host = this.getDocumentBase().getHost();
        if (host == null) {
            host = "";
        }
        this.registered = host.equalsIgnoreCase("www.realapplets.com");
        System.out.println(" Unregistered applet, written by Geoffrey from RealApplets");
        System.out.println(" Get your applets from http://www.realapplets.com");
        System.out.println(" Removal of add without registration is punishable in a court of law.\n");
        this.dim = this.getSize();
        this.setLayout(null);
        QuizMaster.strings[0] = this.getParameter("TagCorrect");
        if (QuizMaster.strings[0] == null) {
            QuizMaster.strings[0] = "";
        }
        final StringBuffer sb = new StringBuffer();
        final String[] strings = QuizMaster.strings;
        final int n = 0;
        strings[n] = sb.append(strings[n]).append(" ").toString();
        QuizMaster.strings[1] = this.getParameter("TagWrong");
        if (QuizMaster.strings[1] == null) {
            QuizMaster.strings[1] = "";
        }
        final StringBuffer sb2 = new StringBuffer();
        final String[] strings2 = QuizMaster.strings;
        final int n2 = 1;
        strings2[n2] = sb2.append(strings2[n2]).append(" ").toString();
        try {
            QuizMaster.fonts[0] = new Font(this.getParameter("QuestionFontStyle"), 0, Integer.parseInt(this.getParameter("QuestionFontSize")));
            QuizMaster.fonts[1] = new Font(this.getParameter("AnswerFontStyle"), 0, Integer.parseInt(this.getParameter("AnswerFontSize")));
            QuizMaster.fonts[2] = new Font(this.getParameter("ExplanationFontStyle"), 0, Integer.parseInt(this.getParameter("ExplanationFontSize")));
            QuizMaster.fonts[3] = new Font(this.getParameter("ScoreFontStyle"), 0, Integer.parseInt(this.getParameter("ScoreFontSize")));
        }
        catch (Exception ex) {
            this.errorString = "One of the FontStyle or FontSize parameters is wrong";
            System.out.println(this.errorString + "\n" + ex);
        }
        try {
            this.setBackground(QuizMaster.colors[0] = new Color(Integer.parseInt(this.getParameter("BackgroundColor"), 16)));
            QuizMaster.colors[1] = new Color(Integer.parseInt(this.getParameter("QuestionColor"), 16));
            QuizMaster.colors[2] = new Color(Integer.parseInt(this.getParameter("AnswerColorOut"), 16));
            QuizMaster.colors[3] = new Color(Integer.parseInt(this.getParameter("AnswerColorOver"), 16));
            QuizMaster.colors[4] = new Color(Integer.parseInt(this.getParameter("AnswerColorSolution"), 16));
            QuizMaster.colors[5] = new Color(Integer.parseInt(this.getParameter("ExplanationColorCorrect"), 16));
            QuizMaster.colors[6] = new Color(Integer.parseInt(this.getParameter("ExplanationColorWrong"), 16));
            QuizMaster.colors[7] = new Color(Integer.parseInt(this.getParameter("ScoreColor"), 16));
            QuizMaster.colors[8] = new Color(Integer.parseInt(this.getParameter("ButtonColorOut"), 16));
            QuizMaster.colors[9] = new Color(Integer.parseInt(this.getParameter("ButtonColorOver"), 16));
        }
        catch (Exception ex2) {
            this.errorString = "One of the Color parameters is wrong";
            System.out.println(this.errorString + "\n" + ex2);
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.errorString != null) {
            gbuf.setColor(Color.white);
            gbuf.fillRect(0, 0, this.dim.width, this.dim.height);
            gbuf.setColor(Color.black);
            gbuf.setFont(new Font("Arial", 0, 16));
            if (this.errorString.indexOf("\n") > 0) {
                gbuf.drawString(this.errorString.substring(0, this.errorString.indexOf("\n")), 10, 20);
                gbuf.drawString(this.errorString.substring(this.errorString.indexOf("\n") + 1, this.errorString.length()), 10, 50);
            }
            else {
                gbuf.drawString(this.errorString, 10, 20);
            }
        }
        else {
            if (this.gbuffer == null) {
                try {
                    this.gbuffer = this.createImage(this.dim.width, this.dim.height);
                    this.gbuf = this.gbuffer.getGraphics();
                }
                catch (Exception ex) {
                    this.gbuf = gbuf;
                    this.gbuffer = null;
                }
                for (int i = 0; i < QuizMaster.fonts.length; ++i) {
                    this.gbuf.setFont(QuizMaster.fonts[i]);
                    QuizMaster.fms[i] = this.gbuf.getFontMetrics();
                }
                if (this.qmd == null) {
                    this.qmd = new QMData(this);
                }
            }
            this.qmd.paint(this.gbuf);
            if (this.gbuffer != null) {
                gbuf.drawImage(this.gbuffer, 0, 0, this.dim.width, this.dim.height, this);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.qmd != null && this.qmd.mousePressed(mouseEvent.getPoint())) {
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.qmd != null && this.qmd.mouseMoved(new Point(-1, -1))) {
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.qmd != null && this.qmd.mouseMoved(mouseEvent.getPoint())) {
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.qmd != null && this.qmd.mouseMoved(mouseEvent.getPoint())) {
            this.repaint();
        }
    }
    
    static {
        QuizMaster.colors = new Color[10];
        QuizMaster.fonts = new Font[4];
        QuizMaster.fms = new FontMetrics[4];
        QuizMaster.strings = new String[2];
    }
}
