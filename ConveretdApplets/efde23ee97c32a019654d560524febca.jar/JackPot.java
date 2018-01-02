import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Button;
import java.awt.Label;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JackPot extends Applet
{
    int width;
    int height;
    int chips;
    int pseudo_chips;
    int level;
    int betsvalue;
    Image[] pic_at;
    Image[] pictures;
    boolean clicked;
    boolean initial_state;
    boolean hit;
    boolean max_score;
    String msg;
    Label chips_counter;
    Button reset;
    Button bets;
    Button go;
    Image jp;
    
    public void init() {
        this.jp = this.getImage(this.getDocumentBase(), "jp.gif");
        for (int i = 0; i < 7; ++i) {
            this.pictures[i] = this.createImage(new FilteredImageSource(this.jp.getSource(), new CropImageFilter(60 * i, 0, 60, 45)));
        }
        this.setFont(new Font("Helvetica", 2, 11));
        this.setForeground(Color.black);
        this.setBackground(Color.gray);
        this.resize(this.width, this.height);
        this.setLayout(new BorderLayout());
        final Font font = new Font("TimesRoman", 0, 11);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel2.setFont(font);
        panel2.setLayout(new FlowLayout(0));
        (this.chips_counter = new Label("$" + this.chips, 0)).setFont(font);
        panel2.add(this.chips_counter);
        final Panel panel3 = new Panel();
        panel3.setFont(font);
        panel3.setLayout(new FlowLayout(1));
        (this.reset = new Button("Reset")).setFont(font);
        panel3.add(this.reset);
        this.reset.enable(false);
        (this.bets = new Button("+-")).setFont(font);
        panel3.add(this.bets);
        this.bets.enable(true);
        (this.go = new Button("Spin")).setFont(font);
        panel3.add(this.go);
        this.go.enable(true);
        panel.add("West", panel2);
        panel.add("East", panel3);
        this.add("South", panel);
    }
    
    public void paint(final Graphics graphics) {
        final int n = 62;
        final int n2 = 45;
        final Dimension size = this.size();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.blue);
        graphics.drawRect(size.width / 2 - 60, 0, 121, 10);
        graphics.setColor(Color.red);
        graphics.fillRect(size.width / 2 - 59, 2, (this.chips - 1440 * this.level) / 12, 7);
        if (this.max_score) {
            if (this.level >= 1) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2 - 60, 12, 10, 10);
            }
            if (this.level >= 2) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2 - 48, 12, 10, 10);
            }
            if (this.level >= 3) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2 - 36, 12, 10, 10);
            }
            if (this.level >= 4) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2 - 24, 12, 10, 10);
            }
            if (this.level >= 5) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2 - 12, 12, 10, 10);
            }
            if (this.level == 6) {
                graphics.setColor(Color.magenta);
                graphics.fillOval(size.width / 2, 12, 10, 10);
            }
        }
        for (int i = 0; i < 3; ++i) {
            graphics.setColor(Color.gray);
            graphics.fillRect(size.width / 2 - n / 2 * 3 + n * i, size.height / 2 - n2 / 2, n, n2 + 2);
            graphics.setColor(Color.white);
            graphics.fillRect((size.width + 2) / 2 - n / 2 * 3 + n * i, (size.height + 2) / 2 - n2 / 2, n - 2, n2);
            if (this.initial_state) {
                graphics.drawImage(this.pictures[0], (size.width + 2) / 2 - n / 2 * 3 + n * i, (size.height + 2) / 2 - n2 / 2, this);
            }
            if (this.clicked) {
                graphics.drawImage(this.pic_at[i], (size.width + 2) / 2 - n / 2 * 3 + n * i, (size.height + 2) / 2 - n2 / 2, this);
            }
        }
        if (this.hit) {
            graphics.setColor(Color.white);
            this.drawCenteredString(this.msg, size.width, size.height - 75, graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.go) {
            this.initial_state = false;
            this.hit = false;
            this.clicked = true;
            this.reset.enable(true);
            this.getNum();
        }
        if (event.target == this.reset) {
            this.chips = 20;
            this.pseudo_chips = 0;
            this.level = 0;
            this.betsvalue = 1;
            this.msg = "";
            this.chips_counter.setText("$ " + this.chips);
            this.clicked = false;
            this.max_score = false;
            this.initial_state = true;
            this.go.enable(true);
            this.bets.enable(true);
            this.repaint();
        }
        if (event.target == this.bets) {
            if (this.betsvalue < 2) {
                ++this.betsvalue;
                this.hit = true;
                this.msg = "Bets doubled to " + this.betsvalue;
                this.repaint();
            }
            else {
                --this.betsvalue;
                this.hit = true;
                this.msg = "Normal bets " + this.betsvalue;
                this.repaint();
            }
        }
        return true;
    }
    
    public void getNum() {
        final int[] array = new int[3];
        for (int i = 0; i < 3; ++i) {
            array[i] = (int)(Math.random() * 20.0) + 1;
            if (array[i] == 1) {
                this.pic_at[i] = this.pictures[0];
            }
            else if (array[i] == 2) {
                this.pic_at[i] = this.pictures[1];
            }
            else if (array[i] == 3) {
                this.pic_at[i] = this.pictures[2];
            }
            else if (array[i] == 4) {
                this.pic_at[i] = this.pictures[3];
            }
            else if (array[i] == 5) {
                this.pic_at[i] = this.pictures[4];
            }
            else if (array[i] == 6) {
                this.pic_at[i] = this.pictures[5];
            }
            else if (array[i] == 7) {
                this.pic_at[i] = this.pictures[6];
            }
            else if (array[i] >= 8 && array[i] <= 10) {
                array[i] = 1;
                this.pic_at[i] = this.pictures[0];
            }
            else if (array[i] >= 11 && array[i] <= 13) {
                array[i] = 2;
                this.pic_at[i] = this.pictures[1];
            }
            else if (array[i] >= 14 && array[i] <= 15) {
                array[i] = 3;
                this.pic_at[i] = this.pictures[2];
            }
            else if (array[i] >= 16 && array[i] <= 17) {
                array[i] = 4;
                this.pic_at[i] = this.pictures[3];
            }
            else if (array[i] >= 18 && array[i] <= 19) {
                array[i] = 5;
                this.pic_at[i] = this.pictures[4];
            }
            else {
                array[i] = 6;
                this.pic_at[i] = this.pictures[5];
            }
        }
        if (array[0] == 7 && array[1] == 7 && array[2] == 7) {
            this.chips += 2000 * this.betsvalue;
            this.hit = true;
            this.msg = "JACKPOT HIT!";
        }
        else if (array[0] == 6 && array[1] == 6 && array[2] == 6) {
            this.chips += 240 * this.betsvalue;
            this.hit = true;
            this.msg = "excellent! " + 240 * this.betsvalue + " up!";
        }
        else if ((array[0] == 7 && array[1] == 7) || (array[1] == 7 && array[2] == 7) || (array[0] == 7 && array[2] == 7)) {
            this.chips += 80 * this.betsvalue;
            this.hit = true;
            this.msg = "yeah! " + 80 * this.betsvalue + " up!";
        }
        else if (array[0] == array[1] && array[1] == array[2]) {
            if (array[0] == 3 || array[0] == 4 || array[0] == 5) {
                this.chips += 60 * this.betsvalue;
                this.hit = true;
                this.msg = "yes! " + 60 * this.betsvalue + " up!";
            }
            else if (array[0] == 1 || array[0] == 2) {
                this.chips += 20 * this.betsvalue;
                this.hit = true;
                this.msg = String.valueOf(20 * this.betsvalue) + " up!";
            }
        }
        else if ((array[0] == 6 && array[1] == 6) || (array[1] == 6 && array[2] == 6) || (array[0] == 6 && array[2] == 6)) {
            this.chips += 14 * this.betsvalue;
            this.hit = true;
            this.msg = String.valueOf(14 * this.betsvalue) + " up!";
        }
        else if ((array[0] == 5 && array[1] == 5) || (array[1] == 5 && array[2] == 5) || (array[0] == 5 && array[2] == 5)) {
            this.chips += 6 * this.betsvalue;
            this.hit = true;
            this.msg = String.valueOf(6 * this.betsvalue) + " up!";
        }
        else if (array[0] == 7 || array[1] == 7 || array[2] == 7) {
            this.chips += 2 * this.betsvalue;
            this.hit = true;
            this.msg = String.valueOf(2 * this.betsvalue) + " up!";
        }
        else {
            this.chips -= this.betsvalue;
        }
        this.pseudo_chips = this.chips - 1440 * this.level;
        if (this.pseudo_chips >= 1440) {
            ++this.level;
            this.hit = true;
            this.msg = "one medal up!";
            this.max_score = true;
        }
        else if (this.chips > 0 && this.chips <= 5) {
            this.hit = true;
            this.msg = "low!";
        }
        else if (this.chips <= 0) {
            this.hit = true;
            this.msg = "game over";
            this.go.enable(false);
            this.bets.enable(false);
        }
        if (this.level == 6) {
            this.hit = true;
            this.msg = "mission accomplished!";
            this.go.enable(false);
            this.bets.enable(false);
        }
        this.repaint();
        this.chips_counter.setText("$" + this.chips);
    }
    
    void drawCenteredString(final String s, final int n, final int n2, final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2, fontMetrics.getAscent() + (n2 - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);
    }
    
    public JackPot() {
        this.width = 186;
        this.height = 140;
        this.chips = 20;
        this.betsvalue = 1;
        this.pic_at = new Image[3];
        this.pictures = new Image[7];
        this.clicked = false;
        this.initial_state = true;
        this.hit = false;
        this.max_score = false;
        this.msg = "";
    }
}
