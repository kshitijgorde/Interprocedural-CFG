import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiDemo extends Applet
{
    demoPanel[] demo;
    Panel demoWhole;
    suiButton prev;
    suiButton next;
    suiButton first;
    suiButton last;
    Image iprev;
    Image inext;
    Image ifirst;
    Image ilast;
    Image background;
    Image background2;
    Image logo;
    Image bimage1;
    Image bimage2;
    Image bimage3;
    Image cimage;
    suiBarN info;
    suiLine line;
    CardLayout cardLayout;
    private int help;
    suiChoice choiceDemo1;
    int ww;
    int hh;
    int position;
    
    public void init() {
        super.init();
        this.waitForImage(this.cimage = this.getImage(this.getDocumentBase(), "cover.gif"));
        this.waitForImage(this.iprev = this.getImage(this.getDocumentBase(), "prev.gif"));
        this.waitForImage(this.inext = this.getImage(this.getDocumentBase(), "next.gif"));
        this.waitForImage(this.ifirst = this.getImage(this.getDocumentBase(), "first.gif"));
        this.waitForImage(this.ilast = this.getImage(this.getDocumentBase(), "last.gif"));
        this.waitForImage(this.background = this.getImage(this.getDocumentBase(), "background.gif"));
        this.ww = this.background.getWidth(this);
        this.hh = this.background.getHeight(this);
        this.waitForImage(this.background2 = this.getImage(this.getDocumentBase(), "background2.gif"));
        this.waitForImage(this.logo = this.getImage(this.getDocumentBase(), "logoss.gif"));
        this.waitForImage(this.bimage1 = this.getImage(this.getDocumentBase(), "button1.gif"));
        this.waitForImage(this.bimage2 = this.getImage(this.getDocumentBase(), "button2.gif"));
        this.waitForImage(this.bimage3 = this.getImage(this.getDocumentBase(), "button3.gif"));
        this.demo = new demoPanel[10];
        this.demoWhole = new Panel();
        this.cardLayout = new CardLayout();
        this.demoWhole.setLayout(this.cardLayout);
        final suiPanel suiPanel = new suiPanel(450, 280);
        suiPanel.setBGImage(this.cimage);
        suiPanel.setSpecial(2);
        this.demoWhole.add(suiPanel, "cover");
        this.demo[0] = new demoPanel("Spoint, Epoint, Width, Height, Convex, BgImage", "suiLine(), setBGImage()");
        final suiLine suiLine = new suiLine(5, 95, 100, 20, false, true);
        final suiLine suiLine2 = new suiLine(5, 95, 100, 20, true, true);
        final suiLine suiLine3 = new suiLine(5, 95, 100, 20, false, true);
        final suiLine suiLine4 = new suiLine(5, 95, 100, 20, true, true);
        final suiLine suiLine5 = new suiLine(5, 95, 100, 20, false, true);
        final suiLine suiLine6 = new suiLine(5, 95, 100, 20, true, true);
        suiLine5.setBGImage(this.background2);
        suiLine6.setBGImage(this.background2);
        suiLine.setBackground(new Color(192, 192, 192));
        suiLine2.setBackground(new Color(192, 192, 192));
        suiLine3.setBackground(new Color(192, 143, 143));
        suiLine4.setBackground(new Color(192, 143, 143));
        suiLine5.setBackground(new Color(192, 192, 192));
        suiLine6.setBackground(new Color(192, 192, 192));
        this.demo[0].dp.add(suiLine, "North");
        this.demo[0].dp.add(suiLine3, "North");
        this.demo[0].dp.add(suiLine5, "North");
        this.demo[0].dp.add(suiLine2, "Center");
        this.demo[0].dp.add(suiLine4, "Center");
        this.demo[0].dp.add(suiLine6, "Center");
        final suiLine suiLine7 = new suiLine(5, 95, 20, 100, false, false);
        final suiLine suiLine8 = new suiLine(5, 95, 20, 100, true, false);
        final suiLine suiLine9 = new suiLine(5, 95, 20, 100, false, false);
        final suiLine suiLine10 = new suiLine(5, 95, 20, 100, true, false);
        final suiLine suiLine11 = new suiLine(5, 95, 20, 100, false, false);
        final suiLine suiLine12 = new suiLine(5, 95, 20, 100, true, false);
        suiLine11.setBGImage(this.background2);
        suiLine12.setBGImage(this.background2);
        suiLine7.setBackground(new Color(192, 192, 192));
        suiLine8.setBackground(new Color(192, 192, 192));
        suiLine9.setBackground(new Color(192, 143, 143));
        suiLine10.setBackground(new Color(192, 143, 143));
        suiLine11.setBackground(new Color(192, 192, 192));
        suiLine12.setBackground(new Color(192, 192, 192));
        final Panel panel = new Panel();
        panel.add(suiLine7, "Center");
        panel.add(suiLine8, "Center");
        panel.add(suiLine9, "Center");
        panel.add(suiLine10, "Center");
        panel.add(suiLine11, "Center");
        panel.add(suiLine12, "Center");
        this.demo[0].dp.add(panel, "South");
        this.demoWhole.add(this.demo[0], "demo1");
        this.add(this.demoWhole, "North");
        this.demo[1] = new demoPanel("Spoint, Epoint, Width, Height, Convex, BgImage", "suiBar(), setBGImage()");
        final suiBar suiBar = new suiBar(5, 25, 145, 40, 150, 80, false);
        final suiBar suiBar2 = new suiBar(5, 25, 145, 40, 150, 80, true);
        final suiBar suiBar3 = new suiBar(5, 25, 145, 40, 150, 80, false);
        final suiBar suiBar4 = new suiBar(5, 25, 145, 40, 150, 80, true);
        suiBar.setBackground(new Color(192, 192, 192));
        suiBar2.setBackground(new Color(192, 192, 192));
        suiBar3.setBackground(new Color(192, 192, 192));
        suiBar4.setBackground(new Color(192, 192, 192));
        suiBar3.setBGImage(this.background2);
        suiBar4.setBGImage(this.background2);
        this.demo[1].dp.add(suiBar, "North");
        this.demo[1].dp.add(suiBar2, "North");
        this.demo[1].dp.add(suiBar3, "South");
        this.demo[1].dp.add(suiBar4, "South");
        this.demoWhole.add(this.demo[1], "demo2");
        this.demo[2] = new demoPanel("Width, Height, Convex, BgImage", "suiPanel(), setBGImage(), setSpecial()");
        final suiPanel suiPanel2 = new suiPanel(100, 80);
        final suiPanel suiPanel3 = new suiPanel(100, 80);
        final suiPanel suiPanel4 = new suiPanel(100, 80);
        final suiPanel suiPanel5 = new suiPanel(100, 80);
        final suiPanel suiPanel6 = new suiPanel(100, 80);
        final suiPanel suiPanel7 = new suiPanel(100, 80);
        suiPanel2.setBackground(new Color(192, 143, 143));
        suiPanel3.setBackground(new Color(192, 143, 143));
        suiPanel4.setBackground(new Color(192, 143, 143));
        suiPanel3.setSpecial(1);
        suiPanel4.setSpecial(2);
        suiPanel5.setBGImage(this.background2);
        suiPanel6.setBGImage(this.background2);
        suiPanel7.setBGImage(this.background2);
        suiPanel6.setSpecial(1);
        suiPanel7.setSpecial(2);
        final Button button = new Button("suiPanel");
        suiPanel2.setFont(new Font("Helvetica", 0, 10));
        suiPanel2.add(button);
        final Button button2 = new Button("suiPanel");
        suiPanel3.setFont(new Font("Helvetica", 2, 10));
        suiPanel3.add(button2);
        final Button button3 = new Button("suiPanel");
        suiPanel4.setFont(new Font("Helvetica", 1, 10));
        suiPanel4.add(button3);
        final Button button4 = new Button("suiPanel");
        suiPanel5.setFont(new Font("Helvetica", 0, 10));
        suiPanel5.add(button4);
        final Button button5 = new Button("suiPanel");
        suiPanel6.setFont(new Font("Helvetica", 2, 10));
        suiPanel6.add(button5);
        final Button button6 = new Button("suiPanel");
        suiPanel7.setFont(new Font("Helvetica", 1, 10));
        suiPanel7.add(button6);
        this.demo[2].dp.add(suiPanel2, "North");
        this.demo[2].dp.add(suiPanel3, "North");
        this.demo[2].dp.add(suiPanel4, "North");
        this.demo[2].dp.add(suiPanel5, "South");
        this.demo[2].dp.add(suiPanel6, "South");
        this.demo[2].dp.add(suiPanel7, "South");
        this.demoWhole.add(this.demo[2], "demo3");
        this.demo[3] = new demoPanel("Spoint, Epoint, Width, Height, Convex, Percent, BgImage", "suiProgress(), setPercentage(), setBGImage()");
        final suiProgress suiProgress = new suiProgress(5, 15, 145, 30, 150, 60, false);
        suiProgress.setPercentage(30);
        suiProgress.setBackground(new Color(192, 192, 192));
        suiProgress.setForeground(new Color(50, 50, 50));
        final suiProgress suiProgress2 = new suiProgress(5, 15, 145, 30, 150, 60, true);
        suiProgress2.setPercentage(80);
        suiProgress2.setBackground(new Color(192, 192, 192));
        suiProgress2.setForeground(new Color(100, 50, 50));
        final suiProgress suiProgress3 = new suiProgress(5, 15, 145, 30, 150, 60, false);
        suiProgress3.setPercentage(20);
        suiProgress3.setBackground(new Color(192, 192, 192));
        suiProgress3.setForeground(new Color(50, 50, 50));
        suiProgress3.setBGImage(this.background2);
        final suiProgress suiProgress4 = new suiProgress(5, 15, 145, 30, 150, 60, true);
        suiProgress4.setPercentage(60);
        suiProgress4.setBackground(new Color(192, 192, 192));
        suiProgress4.setForeground(new Color(100, 50, 50));
        suiProgress4.setBGImage(this.background2);
        this.demo[3].dp.add(suiProgress, "North");
        this.demo[3].dp.add(suiProgress2, "North");
        this.demo[3].dp.add(suiProgress3, "South");
        this.demo[3].dp.add(suiProgress4, "South");
        this.demoWhole.add(this.demo[3], "demo4");
        this.demo[4] = new demoPanel("Width, Height, Label, Alignment, BgImage", "suiLabel(), setBGImage(), setText(), setAlignment()");
        final suiLabel suiLabel = new suiLabel(300, 20, "I am a suiLabel!", 0);
        final suiLabel suiLabel2 = new suiLabel(300, 20, "I am a suiLabel!", 1);
        final suiLabel suiLabel3 = new suiLabel(300, 20, "I am a suiLabel!", 2);
        final suiLabel suiLabel4 = new suiLabel(300, 20, "I am a suiLabel with background image!", 0);
        final suiLabel suiLabel5 = new suiLabel(300, 20, "I am a suiLabel with background image!", 1);
        final suiLabel suiLabel6 = new suiLabel(300, 20, "I am a suiLabel with background image!", 2);
        suiLabel.setBackground(new Color(192, 192, 192));
        suiLabel2.setBackground(new Color(192, 192, 192));
        suiLabel3.setBackground(new Color(192, 192, 192));
        suiLabel4.setBGImage(this.background2);
        suiLabel5.setBGImage(this.background2);
        suiLabel6.setBGImage(this.background2);
        suiLabel.setForeground(Color.blue);
        suiLabel2.setForeground(Color.blue);
        suiLabel3.setForeground(Color.blue);
        suiLabel4.setForeground(Color.blue);
        suiLabel5.setForeground(Color.blue);
        suiLabel6.setForeground(Color.blue);
        suiLabel.setFont(new Font("Helvetica", 2, 10));
        suiLabel2.setFont(new Font("Helvetica", 2, 10));
        suiLabel3.setFont(new Font("Helvetica", 2, 10));
        suiLabel4.setFont(new Font("Helvetica", 2, 10));
        suiLabel5.setFont(new Font("Helvetica", 2, 10));
        suiLabel6.setFont(new Font("Helvetica", 2, 10));
        this.demo[4].dp.add(suiLabel, "North");
        this.demo[4].dp.add(suiLabel2, "North");
        this.demo[4].dp.add(suiLabel3, "North");
        this.demo[4].dp.add(suiLabel4, "South");
        this.demo[4].dp.add(suiLabel5, "South");
        this.demo[4].dp.add(suiLabel6, "South");
        this.demoWhole.add(this.demo[4], "demo5");
        this.demo[5] = new demoPanel("Button Image, Brightstep, Sidewidth", "suiButton(), setDisable(), setEnable()");
        final suiButton suiButton = new suiButton(this.bimage1, 20, 2);
        final suiButton suiButton2 = new suiButton(this.bimage2, 40, 3);
        final suiButton suiButton3 = new suiButton(this.bimage3, 60, 4);
        final suiButton suiButton4 = new suiButton(this.bimage1, 20, 2);
        final suiButton suiButton5 = new suiButton(this.bimage2, 40, 3);
        final suiButton suiButton6 = new suiButton(this.bimage3, 60, 4);
        suiButton4.setDisable();
        suiButton5.setDisable();
        suiButton6.setDisable();
        this.demo[5].dp.add(suiButton, "North");
        this.demo[5].dp.add(suiButton2, "North");
        this.demo[5].dp.add(suiButton3, "North");
        this.demo[5].dp.add(suiButton4, "South");
        this.demo[5].dp.add(suiButton5, "South");
        this.demo[5].dp.add(suiButton6, "South");
        this.demoWhole.add(this.demo[5], "demo6");
        this.demo[6] = new demoPanel("Tab Title, Tab Number, Width, Height", "suiTab(), addComp()");
        final suiTab suiTab = new suiTab("Tab1 Tab2 Tab3", 3, 80, 300);
        suiTab.addComp(new suiLine(5, 95, 100, 20, false, true), 0);
        suiTab.addComp(new suiButton(this.logo, 20, 2), 1);
        suiTab.addComp(new suiBar(5, 5, 145, 15, 150, 20, true), 2);
        this.demo[6].dp.add(suiTab, "Center");
        this.demoWhole.add(this.demo[6], "demo7");
        this.demo[7] = new demoPanel("List item string, List item image, Width, Height", "Almost the same as in List");
        final suiList list = new suiList(200, 100, 25, 10);
        list.setSingleSelection();
        list.addItem("test1");
        list.addItem("test2", this.logo);
        list.addItem("test2", this.logo);
        list.addItem("test1");
        list.addItem("test2", this.logo);
        list.addItem("test2", this.logo);
        this.demo[7].dp.add(list, "Center");
        this.demoWhole.add(this.demo[7], "demo8");
        this.demo[8] = new demoPanel("Checkbox string, Checkbox image", "Almost the same as in Checkbox");
        this.demo[8].dp.setFont(new Font("Helvetica", 0, 10));
        final suiCheckboxGroup suiCheckboxGroup = new suiCheckboxGroup();
        final suiCheckbox suiCheckbox = new suiCheckbox("Demo1 (No group)", this.logo);
        final suiCheckbox suiCheckbox2 = new suiCheckbox("Demo2 (In group)", this.logo, suiCheckboxGroup, true);
        final suiCheckbox suiCheckbox3 = new suiCheckbox("Demo2 (In group)", this.logo, suiCheckboxGroup, false);
        final suiCheckbox suiCheckbox4 = new suiCheckbox("Demo2 (In group)", this.logo, suiCheckboxGroup, false);
        this.demo[8].dp.add(suiCheckbox2, "West");
        this.demo[8].dp.add(suiCheckbox3, "West");
        this.demo[8].dp.add(suiCheckbox4, "West");
        this.demo[8].dp.add(suiCheckbox, "East");
        this.demoWhole.add(this.demo[8], "demo9");
        this.demo[9] = new demoPanel("Choice item string, Choice item image, Width, Height", "Almost the same as in Choice");
        (this.choiceDemo1 = new suiChoice(4, 150, 25)).addItem("test1", this.logo);
        this.choiceDemo1.addItem("test2", this.logo);
        this.choiceDemo1.addItem("test3", this.logo);
        this.choiceDemo1.addItem("test4", this.logo);
        this.demo[9].dp.add(this.choiceDemo1, "Center");
        this.demoWhole.add(this.demo[9], "demo10");
        (this.line = new suiLine(4, 495, 500, 10, false, true)).setBGImage(this.background);
        this.add(this.line, "North");
        this.add(this.prev = new suiButton(this.iprev, 70, 2), "Center");
        this.add(this.next = new suiButton(this.inext, 70, 2), "Center");
        this.add(this.last = new suiButton(this.ilast, 70, 2), "Center");
        this.add(this.first = new suiButton(this.ifirst, 70, 2), "Center");
        this.add(this.info = new suiBarN(this.logo, 3, 3, 492, 24, 495, 27, new Color(192, 192, 192), this.background), "South");
        this.repaint();
    }
    
    public void destroy() {
        this.iprev = null;
        this.inext = null;
        this.ifirst = null;
        this.ilast = null;
        this.background = null;
        this.background2 = null;
        this.logo = null;
        this.bimage1 = null;
        this.bimage2 = null;
        this.bimage3 = null;
        this.demo = null;
        this.demoWhole = null;
        this.prev = null;
        this.next = null;
        this.first = null;
        this.last = null;
        this.info = null;
        this.line = null;
        this.cardLayout = null;
        this.removeAll();
        if (this.choiceDemo1.window != null) {
            this.choiceDemo1.window.dispose();
            this.choiceDemo1.window = null;
        }
        this.choiceDemo1 = null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.prev) {
            if (this.position > 0) {
                this.cardLayout.previous(this.demoWhole);
                --this.position;
            }
        }
        else if (event.target == this.next) {
            if (this.position < 10) {
                this.cardLayout.next(this.demoWhole);
                ++this.position;
            }
        }
        else if (event.target == this.first) {
            this.cardLayout.first(this.demoWhole);
            this.position = 0;
        }
        else if (event.target == this.last) {
            this.cardLayout.last(this.demoWhole);
            this.position = 10;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.locate(n, n2) == this.prev && this.prev.isEnabled()) {
            if (this.help == 1) {
                return true;
            }
            if (this.help != 0) {
                this.info.repaint();
                this.help = 0;
                return true;
            }
            final Graphics graphics = this.info.getGraphics();
            this.drawLabel2(graphics, "Previous suiComponent");
            this.drawLabel(graphics, "Previous suiComponent", this.prev.location().x + 28, 2);
            this.help = 1;
        }
        else if (this.locate(n, n2) == this.next && this.next.isEnabled()) {
            if (this.help == 2) {
                return true;
            }
            if (this.help != 0) {
                this.info.repaint();
                this.help = 0;
                return true;
            }
            final Graphics graphics2 = this.info.getGraphics();
            this.drawLabel(graphics2, "Next suiComponent", this.next.location().x + 28, 2);
            this.drawLabel2(graphics2, "Next suiComponent");
            this.help = 2;
        }
        else if (this.locate(n, n2) == this.first && this.first.isEnabled()) {
            if (this.help == 3) {
                return true;
            }
            if (this.help != 0) {
                this.info.repaint();
                this.help = 0;
                return true;
            }
            final Graphics graphics3 = this.info.getGraphics();
            this.drawLabel(graphics3, "First suiComponent", this.first.location().x + 28, 2);
            this.drawLabel2(graphics3, "First suiComponent");
            this.help = 3;
        }
        else if (this.locate(n, n2) == this.last && this.last.isEnabled()) {
            if (this.help == 4) {
                return true;
            }
            if (this.help != 0) {
                this.info.repaint();
                this.help = 0;
                return true;
            }
            final Graphics graphics4 = this.info.getGraphics();
            this.drawLabel(graphics4, "Last suiComponent", this.last.location().x + 28, 2);
            this.drawLabel2(graphics4, "Last suiComponent");
            this.help = 4;
        }
        else if (this.help != 0) {
            this.info.repaint();
            this.help = 0;
        }
        return true;
    }
    
    void drawLabel(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.setFont(new Font("Helvetica", 0, 10));
        final int stringWidth = graphics.getFontMetrics().stringWidth(s);
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        graphics.setColor(new Color(255, 255, 210));
        graphics.fillRect(n - 3, n2 - 2, stringWidth + 5, height + 4);
        graphics.setColor(Color.black);
        graphics.drawRect(n - 3, n2 - 2, stringWidth + 5, height + 4);
        graphics.drawString(s, n, n2 + height - descent);
    }
    
    void drawLabel2(final Graphics graphics, final String s) {
        graphics.setFont(new Font("Helvetica", 0, 10));
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        graphics.setColor(Color.black);
        graphics.drawString(s, 32, 14 + height / 2 - descent);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(500, 450);
    }
    
    public Dimension preferredSize() {
        return new Dimension(500, 450);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        for (int i = 0; i < size.height - 1; i += this.hh) {
            for (int j = 0; j < size.width - 1; j += this.ww) {
                graphics.drawImage(this.background, j, i, this);
            }
        }
    }
    
    private void waitForImage(final Image image) {
        while (image.getWidth(this) <= 0) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
}
