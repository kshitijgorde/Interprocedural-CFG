import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DownTime extends Applet implements ActionListener
{
    float size;
    float sec144;
    float sec288;
    float sec336;
    float sec56k;
    float secIsdn;
    float secSat;
    float secAdsl;
    float secCable;
    float secT1;
    float secT3;
    float min144;
    float min288;
    float min336;
    float min56k;
    float minIsdn;
    float minSat;
    float minAdsl;
    float minCable;
    float minT1;
    float minT3;
    float hour144;
    float hour288;
    float hour336;
    float hour56k;
    float hourIsdn;
    float hourSat;
    float hourAdsl;
    float hourCable;
    float hourT1;
    float hourT3;
    float modsize;
    String s144;
    String s288;
    String s336;
    String s56k;
    String sIsdn;
    String sSat;
    String sAdsl;
    String sCable;
    String sT1;
    String sT3;
    String fileName;
    String incsize;
    String font;
    Color bgColor;
    Color fgColor;
    BorderLayout GL;
    BorderLayout lay;
    Panel p;
    Button xdButton;
    Button vButton;
    
    public DownTime() {
        this.bgColor = Color.gray;
        this.fgColor = Color.white;
        this.GL = new BorderLayout();
        this.lay = new BorderLayout();
        this.p = new Panel();
        this.xdButton = new Button("Get 100MB Free");
        this.vButton = new Button("v");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("xd")) {
            try {
                this.getAppletContext().showDocument(new URL("http://service.bfast.com/bfast/click?bfmid=14524098&siteid=33158853&bfpage=launch_banner"), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        else {
            try {
                this.getAppletContext().showDocument(new URL("http://applets.virtig01.cjb.net"), "_blank");
            }
            catch (MalformedURLException ex2) {}
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("size");
        if (parameter != null) {
            this.size = Float.valueOf(parameter);
        }
        this.incsize = ((this.size >= 1024.0f) ? "MB" : "KB");
        this.modsize = ((this.size >= 1024.0f) ? (this.size / 1024.0f) : this.size);
        final String parameter2 = this.getParameter("filename");
        this.fileName = ((parameter2 != null) ? parameter2 : "this file");
        this.size *= 8.0f;
        final String parameter3 = this.getParameter("bgcolor");
        if (parameter3 != null) {
            this.bgColor = new Color(Integer.parseInt(parameter3, 16));
        }
        this.setBackground(this.bgColor);
        final String parameter4 = this.getParameter("textcolor");
        if (parameter4 != null) {
            this.fgColor = new Color(Integer.parseInt(parameter4, 16));
        }
        this.sec144 = (float)(this.size / 14.0625);
        this.min144 = ((this.sec144 >= 60.0f) ? (this.sec144 / 60.0f) : 0.0f);
        this.hour144 = ((this.min144 >= 60.0f) ? (this.min144 / 60.0f) : 0.0f);
        this.s144 = ((this.hour144 > 1.0f) ? "s " : " ");
        while (this.sec144 >= 60.0f) {
            final float n = this.sec144 / 60.0f;
            this.sec144 -= 60.0f;
        }
        while (this.min144 >= 60.0f) {
            final float n2 = this.min144 / 60.0f;
            this.min144 -= 60.0f;
        }
        this.sec288 = (float)(this.size / 28.125);
        this.min288 = ((this.sec288 >= 60.0f) ? (this.sec288 / 60.0f) : 0.0f);
        this.hour288 = ((this.min288 >= 60.0f) ? (this.min288 / 60.0f) : 0.0f);
        this.s288 = ((this.hour288 > 1.0f) ? "s " : " ");
        while (this.sec288 >= 60.0f) {
            final float n3 = this.sec288 / 60.0f;
            this.sec288 -= 60.0f;
        }
        while (this.min288 >= 60.0f) {
            final float n4 = this.min288 / 60.0f;
            this.min288 -= 60.0f;
        }
        this.sec336 = (float)(this.size / 32.8125);
        this.min336 = ((this.sec336 >= 60.0f) ? (this.sec336 / 60.0f) : 0.0f);
        this.hour336 = ((this.min336 >= 60.0f) ? (this.min336 / 60.0f) : 0.0f);
        this.s336 = ((this.hour336 > 1.0f) ? "s " : " ");
        while (this.sec336 >= 60.0f) {
            final float n5 = this.sec336 / 60.0f;
            this.sec336 -= 60.0f;
        }
        while (this.min336 >= 60.0f) {
            final float n6 = this.min336 / 60.0f;
            this.min336 -= 60.0f;
        }
        this.sec56k = (float)(this.size / 51.75);
        this.min56k = ((this.sec56k >= 60.0f) ? (this.sec56k / 60.0f) : 0.0f);
        this.hour56k = ((this.min56k >= 60.0f) ? (this.min56k / 60.0f) : 0.0f);
        this.s56k = ((this.hour56k > 1.0f) ? "s " : " ");
        while (this.sec56k >= 60.0f) {
            final float n7 = this.sec144 / 60.0f;
            this.sec56k -= 60.0f;
        }
        while (this.min56k >= 60.0f) {
            final float n8 = this.min144 / 60.0f;
            this.min56k -= 60.0f;
        }
        this.secIsdn = this.size / 125.0f;
        this.minIsdn = ((this.secIsdn >= 60.0f) ? (this.secIsdn / 60.0f) : 0.0f);
        this.hourIsdn = ((this.minIsdn >= 60.0f) ? (this.minIsdn / 60.0f) : 0.0f);
        this.sIsdn = ((this.hourIsdn > 1.0f) ? "s " : " ");
        while (this.secIsdn >= 60.0f) {
            final float n9 = this.secIsdn / 60.0f;
            this.secIsdn -= 60.0f;
        }
        while (this.minIsdn >= 60.0f) {
            final float n10 = this.minIsdn / 60.0f;
            this.minIsdn -= 60.0f;
        }
        this.secSat = (float)(this.size / 390.625);
        this.minSat = ((this.secSat >= 60.0f) ? (this.secSat / 60.0f) : 0.0f);
        this.hourSat = ((this.minSat >= 60.0f) ? (this.minSat / 60.0f) : 0.0f);
        this.sSat = ((this.hourSat > 1.0f) ? "s " : " ");
        while (this.secSat >= 60.0f) {
            final float n11 = this.secSat / 60.0f;
            this.secSat -= 60.0f;
        }
        while (this.minSat >= 60.0f) {
            final float n12 = this.minSat / 60.0f;
            this.minSat -= 60.0f;
        }
        this.secAdsl = (float)(this.size / 781.25);
        this.minAdsl = ((this.secAdsl >= 60.0f) ? (this.secAdsl / 60.0f) : 0.0f);
        this.hourAdsl = ((this.minAdsl >= 60.0f) ? (this.minAdsl / 60.0f) : 0.0f);
        this.sAdsl = ((this.hourAdsl > 1.0f) ? "s " : " ");
        while (this.secAdsl >= 60.0f) {
            final float n13 = this.secAdsl / 60.0f;
            this.secAdsl -= 60.0f;
        }
        while (this.minAdsl >= 60.0f) {
            final float n14 = this.minAdsl / 60.0f;
            this.minAdsl -= 60.0f;
        }
        this.secCable = this.size / 1000.0f;
        this.minCable = ((this.secCable >= 60.0f) ? (this.secCable / 60.0f) : 0.0f);
        this.hourCable = ((this.minCable >= 60.0f) ? (this.minCable / 60.0f) : 0.0f);
        this.sCable = ((this.hourCable > 1.0f) ? "s " : " ");
        while (this.secCable >= 60.0f) {
            final float n15 = this.secCable / 60.0f;
            this.secCable -= 60.0f;
        }
        while (this.minCable >= 60.0f) {
            final float n16 = this.minCable / 60.0f;
            this.minCable -= 60.0f;
        }
        this.secT1 = (float)(this.size / 1464.84);
        this.minT1 = ((this.secT1 >= 60.0f) ? (this.secT1 / 60.0f) : 0.0f);
        this.hourT1 = ((this.minT1 >= 60.0f) ? (this.minT1 / 60.0f) : 0.0f);
        this.sT1 = ((this.hourT1 > 1.0f) ? "s " : " ");
        while (this.secT1 >= 60.0f) {
            final float n17 = this.secT1 / 60.0f;
            this.secT1 -= 60.0f;
        }
        while (this.minT1 >= 60.0f) {
            final float n18 = this.minT1 / 60.0f;
            this.minT1 -= 60.0f;
        }
        this.secT3 = (float)(this.size / 41992.1875);
        this.minT3 = ((this.secT3 >= 60.0f) ? (this.secT3 / 60.0f) : 0.0f);
        this.hourT3 = ((this.minT3 >= 60.0f) ? (this.minT3 / 60.0f) : 0.0f);
        this.sT3 = ((this.hourT3 > 1.0f) ? "s " : " ");
        while (this.secT3 >= 60.0f) {
            final float n19 = this.secT1 / 60.0f;
            this.secT3 -= 60.0f;
        }
        while (this.minT3 >= 60.0f) {
            final float n20 = this.minT1 / 60.0f;
            this.minT3 -= 60.0f;
        }
        this.setLayout(this.lay);
        this.p.setLayout(this.GL);
        this.p.setBackground(this.bgColor);
        this.xdButton.addActionListener(this);
        this.xdButton.setForeground(this.fgColor);
        this.xdButton.setBackground(this.bgColor);
        this.p.add(this.xdButton, "West");
        this.vButton.addActionListener(this);
        this.vButton.setForeground(Color.green);
        this.vButton.setBackground(Color.black);
        this.p.add(this.vButton, "East");
        this.add(this.p, "South");
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.fgColor);
        graphics.setFont(new Font("Helvetica", 0, 12));
        graphics.fillRect(15, 24, 300, 12);
        graphics.fillRect(15, 64, 300, 12);
        graphics.fillRect(15, 104, 300, 12);
        graphics.fillRect(15, 144, 300, 12);
        graphics.fillRect(15, 184, 300, 12);
        graphics.drawString("Download times for " + this.fileName + " - " + new DecimalFormat("0.00").format(this.modsize) + this.incsize, 5, 14);
        graphics.setColor(this.bgColor);
        graphics.drawString("14.4kbps - " + (int)this.hour144 + " hr" + this.s144 + (int)this.min144 + " min " + (int)this.sec144 + " sec ", 15, 34);
        graphics.drawString("33.6kbps - " + (int)this.hour336 + " hr" + this.s336 + (int)this.min336 + " min " + (int)this.sec336 + " sec ", 15, 74);
        graphics.drawString("ISDN (128kbps)- " + (int)this.hourIsdn + " hr" + this.sIsdn + (int)this.minIsdn + " min " + (int)this.secIsdn + " sec ", 15, 114);
        graphics.drawString("ADSL (800kbps)- " + (int)this.hourAdsl + " hr" + this.sAdsl + (int)this.minAdsl + " min " + (int)this.secAdsl + " sec ", 15, 154);
        graphics.drawString("T1 (1.5mbps)- " + (int)this.hourT1 + " hr" + this.sT1 + (int)this.minT1 + " min " + (int)this.secT1 + " sec ", 15, 194);
        graphics.setColor(this.fgColor);
        graphics.drawString("28.8kbps - " + (int)this.hour288 + " hr" + this.s288 + (int)this.min288 + " min " + (int)this.sec288 + " sec ", 15, 54);
        graphics.drawString("56kbps - " + (int)this.hour56k + " hr" + this.s56k + (int)this.min56k + " min " + (int)this.sec56k + " sec ", 15, 94);
        graphics.drawString("Satellite (400kbps)- " + (int)this.hourSat + " hr" + this.sSat + (int)this.minSat + " min " + (int)this.secSat + " sec ", 15, 134);
        graphics.drawString("Cable (1mbps)- " + (int)this.hourCable + " hr" + this.sCable + (int)this.minCable + " min " + (int)this.secCable + " sec ", 15, 174);
        graphics.drawString("T3 (43mbps)- " + (int)this.hourT3 + " hr" + this.sT3 + (int)this.minT3 + " min " + (int)this.secT3 + " sec ", 15, 214);
        graphics.setFont(new Font("TimesRoman", 2, 10));
        graphics.drawString("*Times approximate", 10, 232);
    }
}
