import java.awt.event.MouseAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Clock extends Applet implements Runnable
{
    long Budget;
    long Debt;
    long Since;
    long yourDebt;
    long PrettyAdjust;
    long StartDebt;
    long StartLogon;
    long NumFamily;
    long StartMilliSec;
    long BudgetRate;
    long theRate;
    long lOldmsTime;
    long lOldDebt;
    long lOldmsDate;
    long lmsDif;
    long lDifDebt;
    long lDays;
    Long loValue;
    Date StartDate;
    Date FiscalStartDate;
    Date OldDate;
    String sTheTitle;
    Color darkgreen;
    Color liteYellow;
    Color aboutBack;
    Color contactFore;
    Thread killme;
    String rawDebt;
    String prettyDebt;
    String rawBudget;
    String prettyBudget;
    String rawShare;
    String prettyShare;
    String rawSince;
    String prettySince;
    String parmDebt;
    String parmDate;
    String parmRate;
    String parmBudget;
    String parmFamily;
    String parmOldDate;
    String parmOldDebt;
    InputStream conn;
    boolean first;
    boolean bAbout;
    final long msPerYr = 3153600000L;
    final long msPerDay = 8640000L;
    final String sCurrDate = "10/26/2006";
    final String sCurrDebt = "8565801977175";
    final String sOldDate = "10/25/2006";
    final String sOldDebt = "8550350640674";
    final String sVersion = "Version 4.1";
    Image OffscreenImage;
    Graphics OffscreenGraphics;
    Font debtFont;
    Font smalldebtFont;
    Font yourFont;
    Font creditFont;
    Font sinceFont;
    Font aboutBoldFont;
    Font aboutNormFont;
    Button btnAbout;
    Button btnLink;
    
    public void ToggleAbout() {
        this.bAbout = !this.bAbout;
        if (this.bAbout) {
            this.btnAbout.setLabel("Re-Start Clock");
            return;
        }
        this.btnAbout.setLabel("About Debt Clock");
    }
    
    public long MakeOddOrEven(final long n, final boolean b) {
        long n2;
        if (n / 2L * 2L == n) {
            if (!b) {
                n2 = n;
            }
            else {
                n2 = n + 1L;
            }
        }
        else if (b) {
            n2 = n;
        }
        else {
            n2 = n - 1L;
        }
        return n2;
    }
    
    public String addCommas(final String s, final boolean b) {
        int length = s.length();
        if (b) {
            length -= 2;
        }
        int n = length / 3;
        int n2 = length - n * 3;
        if (n2 == 0 && n > 0) {
            n2 = 3;
            --n;
        }
        String s2 = s.substring(0, n2);
        int n3 = n2;
        int n4 = n3 + 3;
        for (int i = 0; i < n; ++i) {
            s2 = String.valueOf(s2) + "," + s.substring(n3, n4);
            n3 = n4;
            n4 += 3;
        }
        String s3 = String.valueOf(s2) + ".";
        if (b) {
            s3 = String.valueOf(s3) + s.substring(n3, n3 + 2);
        }
        return s3;
    }
    
    public void init() {
        this.setLayout(null);
        this.setSize(437, 332);
        this.setBackground(new Color(16776960));
        (this.btnAbout = new Button()).setLabel("About Debt Clock");
        this.btnAbout.setBounds(32, 256, 144, 32);
        this.btnAbout.setBackground(new Color(12632256));
        this.add(this.btnAbout);
        (this.btnLink = new Button()).setLabel("Fetch Debt History 1900 - 2003");
        this.btnLink.setBounds(200, 256, 200, 32);
        this.btnLink.setFont(new Font("Dialog", 0, 12));
        this.btnLink.setBackground(new Color(12632256));
        this.add(this.btnLink);
        this.OffscreenImage = this.createImage(this.size().width, this.size().height);
        this.OffscreenGraphics = this.OffscreenImage.getGraphics();
        final String parameter = this.getParameter("title");
        if (parameter == null) {
            this.sTheTitle = "U. S. National Debt Clock";
        }
        else {
            this.sTheTitle = parameter;
        }
        final String parameter2 = this.getParameter("debt");
        if (parameter2 == null) {
            this.parmDebt = "8565801977175";
        }
        else {
            this.parmDebt = parameter2;
        }
        final String parameter3 = this.getParameter("olddebt");
        if (parameter3 == null) {
            this.parmOldDebt = "8550350640674";
        }
        else {
            this.parmOldDebt = parameter3;
        }
        final String parameter4 = this.getParameter("date");
        if (parameter4 == null) {
            this.parmDate = "10/26/2006";
        }
        else {
            this.parmDate = parameter4;
        }
        final String parameter5 = this.getParameter("olddate");
        if (parameter5 == null) {
            this.parmOldDate = "10/25/2006";
        }
        else {
            this.parmOldDate = parameter5;
        }
        final String parameter6 = this.getParameter("rate");
        if (parameter6 == null) {
            this.parmRate = "629681185";
        }
        else {
            this.parmRate = parameter6;
        }
        final String parameter7 = this.getParameter("houses");
        if (parameter7 == null) {
            this.parmFamily = "62790697";
        }
        else {
            this.parmFamily = parameter7;
        }
        final String parameter8 = this.getParameter("budget");
        if (parameter8 == null) {
            this.parmBudget = "2711101000000";
        }
        else {
            this.parmBudget = parameter8;
        }
        if (this.getParameter("nolink") != null) {
            this.btnLink.setEnabled(false);
            this.btnLink.setLabel("Visit www.Toptips.com");
        }
        try {
            this.StartDebt = Long.parseLong(this.parmDebt);
        }
        catch (NumberFormatException ex) {
            System.out.println("DebtFormat:" + this.parmDebt + ":");
        }
        try {
            this.StartDate = new Date(this.parmDate);
        }
        catch (NumberFormatException ex2) {
            System.out.println("DateFormat:" + this.parmDate + ":");
        }
        try {
            this.OldDate = new Date(this.parmOldDate);
        }
        catch (NumberFormatException ex3) {
            System.out.println("DateFormat:" + this.parmOldDate + ":");
        }
        try {
            this.lOldDebt = Long.parseLong(this.parmOldDebt);
        }
        catch (NumberFormatException ex4) {
            System.out.println("OldDebtConv:" + this.parmOldDebt + ":");
        }
        try {
            this.NumFamily = Long.parseLong(this.parmFamily);
        }
        catch (NumberFormatException ex5) {
            System.out.println("FamilyFormat:" + this.parmFamily + ":");
        }
        try {
            this.BudgetRate = Long.parseLong(this.parmBudget) / 3153600000L;
        }
        catch (NumberFormatException ex6) {
            System.out.println("BudgetFormat:" + this.parmBudget + ":");
        }
        this.StartDate.setHours(9);
        this.StartDate.setMinutes(11);
        this.StartMilliSec = this.StartDate.getTime();
        this.lOldmsDate = this.OldDate.getTime();
        this.lmsDif = this.StartMilliSec - this.lOldmsDate;
        this.lDifDebt = this.StartDebt - this.lOldDebt;
        this.lDays = this.lmsDif / 8640000L;
        if (this.lDays < 1L) {
            this.lDays = 1L;
        }
        this.theRate = this.lDifDebt / this.lDays / 8640000L;
        if (this.theRate > 0L & this.theRate < 7L) {
            this.theRate = 7L;
        }
        if (this.theRate < 1L & this.theRate > -7L) {
            this.theRate = -7L;
        }
        this.first = true;
        this.prettyDebt = "6,874,630,439,176.";
        this.PrettyAdjust = 0L;
        this.btnAbout.addActionListener(new SymAction());
        this.btnLink.addMouseListener(new SymMouse());
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            final Date date = new Date();
            final long time = date.getTime();
            final long n = (time - this.StartMilliSec) / 10L;
            this.PrettyAdjust += 3L;
            if (this.PrettyAdjust > 13L) {
                this.PrettyAdjust = 0L;
            }
            this.Debt = this.theRate * n + this.StartDebt + this.PrettyAdjust;
            this.loValue = new Long(this.Debt);
            this.rawDebt = this.loValue.toString();
            this.prettyDebt = this.addCommas(this.rawDebt, false);
            this.yourDebt = this.Debt / this.NumFamily;
            this.loValue = new Long(this.yourDebt);
            this.rawShare = this.loValue.toString();
            this.prettyShare = this.addCommas(this.rawShare, false);
            (this.FiscalStartDate = new Date(98, 9, 1)).setYear(date.getYear());
            if (this.FiscalStartDate.after(date)) {
                this.FiscalStartDate.setYear(date.getYear() - 1);
            }
            this.Budget = this.BudgetRate * ((time - this.FiscalStartDate.getTime()) / 10L);
            if (this.first) {
                this.Since = this.Budget;
                this.first = false;
            }
            this.loValue = new Long(this.Budget);
            this.rawBudget = this.loValue.toString();
            this.prettyBudget = this.addCommas(this.rawBudget, false);
            this.loValue = new Long(this.Budget - this.Since);
            if (this.Budget - this.Since > 999L) {
                this.rawSince = this.loValue.toString();
                this.prettySince = this.addCommas(this.rawSince, false);
            }
            else {
                this.prettySince = this.loValue.toString();
            }
            this.repaint();
        }
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.killme != null) {
            this.killme.stop();
            this.killme = null;
        }
    }
    
    void btnAbout_ActionPerformed(final ActionEvent actionEvent) {
        this.ToggleAbout();
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.yourFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.debtFont);
        final FontMetrics fontMetrics3 = graphics.getFontMetrics(this.aboutNormFont);
        final FontMetrics fontMetrics4 = graphics.getFontMetrics(this.aboutBoldFont);
        if (this.bAbout) {
            this.OffscreenGraphics.setColor(this.aboutBack);
            this.OffscreenGraphics.fillRect(0, 0, width, this.size().height);
            this.OffscreenGraphics.setFont(this.yourFont);
            this.OffscreenGraphics.setColor(Color.red);
            final String s = "Debt Clock Java Applet - Version 4.1";
            this.OffscreenGraphics.drawString(s, (width - fontMetrics.stringWidth(s)) / 2, 26);
            this.OffscreenGraphics.setFont(this.aboutNormFont);
            this.OffscreenGraphics.setColor(Color.black);
            final String s2 = "This Debt Clock is provided as a public service by";
            this.OffscreenGraphics.drawString(s2, (width - fontMetrics3.stringWidth(s2)) / 2, 46);
            this.OffscreenGraphics.setFont(this.aboutBoldFont);
            this.OffscreenGraphics.setColor(Color.blue);
            final String s3 = "Timely Information Provider Services (TIPS)";
            this.OffscreenGraphics.drawString(s3, (width - fontMetrics4.stringWidth(s3)) / 2, 70);
            final String s4 = "www.TopTips.com";
            this.OffscreenGraphics.drawString(s4, (width - fontMetrics4.stringWidth(s4)) / 2, 102);
            final String s5 = "Howard Hulen, Author";
            this.OffscreenGraphics.drawString(s5, (width - fontMetrics4.stringWidth(s5)) / 2, 86);
            this.OffscreenGraphics.setColor(Color.black);
            final String s6 = "Visit TIPS on the Web for Freeware, Shareware,";
            this.OffscreenGraphics.drawString(s6, (width - fontMetrics4.stringWidth(s6)) / 2, 130);
            final String s7 = "Software Products and Services.";
            this.OffscreenGraphics.drawString(s7, (width - fontMetrics4.stringWidth(s7)) / 2, 148);
            this.OffscreenGraphics.setColor(Color.blue);
            final String s8 = "Click the Fetch Debt History button to see";
            this.OffscreenGraphics.drawString(s8, (width - fontMetrics4.stringWidth(s8)) / 2, 172);
            final String s9 = "prior Debt for the years 1900 - 2003.";
            this.OffscreenGraphics.drawString(s9, (width - fontMetrics4.stringWidth(s9)) / 2, 188);
            this.OffscreenGraphics.setFont(this.aboutNormFont);
            this.OffscreenGraphics.setColor(Color.black);
            final String s10 = "Copyright (c) 1996 - 2005 TIPS";
            this.OffscreenGraphics.drawString(s10, (width - fontMetrics3.stringWidth(s10)) / 2, 230);
        }
        else {
            this.OffscreenGraphics.setColor(this.liteYellow);
            this.OffscreenGraphics.fillRect(0, 0, width, this.size().height);
            this.OffscreenGraphics.setFont(this.yourFont);
            this.OffscreenGraphics.setColor(Color.blue);
            this.OffscreenGraphics.drawString(this.sTheTitle, (width - fontMetrics.stringWidth(this.sTheTitle)) / 2, 20);
            this.OffscreenGraphics.setFont(this.debtFont);
            this.OffscreenGraphics.setColor(Color.red);
            final String string = "Debt: $" + this.prettyDebt;
            int n = fontMetrics2.stringWidth(string);
            if (n < width - 20) {
                this.OffscreenGraphics.setFont(this.debtFont);
            }
            else {
                n = graphics.getFontMetrics(this.smalldebtFont).stringWidth(string);
                this.OffscreenGraphics.setFont(this.smalldebtFont);
            }
            this.OffscreenGraphics.drawString(string, (width - n) / 2, 55);
            this.OffscreenGraphics.setFont(this.yourFont);
            this.OffscreenGraphics.setColor(Color.blue);
            final String string2 = "Your family's Share: $" + this.prettyShare;
            this.OffscreenGraphics.drawString(string2, (width - fontMetrics.stringWidth(string2)) / 2, 80);
            this.OffscreenGraphics.setColor(this.darkgreen);
            this.OffscreenGraphics.drawLine(8, 90, 430, 90);
            this.OffscreenGraphics.drawString("U.S. Spending (To Date) For The", 44, 115);
            final String string3 = "Current Fiscal Yr: $" + this.prettyBudget;
            this.OffscreenGraphics.drawString(string3, (width - fontMetrics.stringWidth(string3)) / 2, 140);
            this.OffscreenGraphics.setFont(this.creditFont);
            this.OffscreenGraphics.drawString("Fiscal Yr. Starting " + this.FiscalStartDate.toLocaleString(), 100, 156);
            this.OffscreenGraphics.setFont(this.sinceFont);
            this.OffscreenGraphics.drawString("U.S. Spending Just Since You Logged-On:", 46, 184);
            this.OffscreenGraphics.setFont(this.yourFont);
            final String string4 = "$" + this.prettySince;
            this.OffscreenGraphics.drawString(string4, (width - fontMetrics.stringWidth(string4)) / 2, 208);
            this.OffscreenGraphics.drawLine(8, 222, 430, 222);
            this.OffscreenGraphics.setFont(this.creditFont);
            this.OffscreenGraphics.setColor(Color.black);
            this.OffscreenGraphics.drawString("by Howard Hulen - See: www.TopTips.com", 100, 248);
            this.OffscreenGraphics.drawString("Clock Parameters Updated: " + this.StartDate, 75, 305);
        }
        graphics.drawImage(this.OffscreenImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public String getAppletInfo() {
        return "National Debt Clock (c) TIPS 1999-2003";
    }
    
    void btnLink_MouseClicked(final MouseEvent mouseEvent) {
        URL url = null;
        try {
            url = new URL("http://www.toptips.com/debt_history.htm");
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL: " + url);
        }
        if (url != null) {
            this.getAppletContext().showDocument(url);
        }
    }
    
    public Clock() {
        this.darkgreen = new Color(0, 128, 0);
        this.liteYellow = new Color(255, 255, 158);
        this.aboutBack = new Color(186, 255, 255);
        this.contactFore = new Color(166, 0, 166);
        this.debtFont = new Font("TimesRoman", 1, 36);
        this.smalldebtFont = new Font("TimesRoman", 1, 34);
        this.yourFont = new Font("TimesRoman", 1, 24);
        this.creditFont = new Font("TimesRoman", 0, 12);
        this.sinceFont = new Font("TimesRoman", 0, 18);
        this.aboutBoldFont = new Font("TimesRoman", 1, 14);
        this.aboutNormFont = new Font("TimesRoman", 0, 14);
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == Clock.this.btnAbout) {
                Clock.this.btnAbout_ActionPerformed(actionEvent);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == Clock.this.btnLink) {
                Clock.this.btnLink_MouseClicked(mouseEvent);
            }
        }
    }
}
