import java.applet.AppletContext;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Linx extends Applet implements Runnable, MouseListener, MouseMotionListener, WindowListener, ActionListener
{
    public Thread Linx_t;
    public Graphics Linx_g;
    public Frame scoreF;
    public Frame codeF;
    public Frame nameF;
    public Color brown;
    public Color tan;
    public Color green;
    public Color darkGreen;
    public int stage;
    public int power;
    public int accuracy;
    public boolean swing;
    public boolean swing_up;
    public int club;
    public int[] maxDist;
    public int[] score;
    public double[] ballPos;
    public int height;
    public int hole;
    public int[][] course;
    public int[] courseOptions;
    public boolean invalid;
    public boolean in;
    public int[] cursor;
    public double xOff;
    public double yOff;
    public int distance;
    public int lie;
    public double xoff;
    public double yoff;
    public int[] grass;
    public int[] wind;
    public String message;
    public Color messageColor;
    public double[] oldPos;
    public int oldLie;
    public int base;
    public boolean OK;
    public boolean trip;
    public boolean smiley;
    public boolean tree;
    public boolean sand;
    public boolean water;
    public boolean night;
    public boolean again;
    public boolean mulligan;
    public boolean cheated;
    public boolean line;
    
    public String getAppletInfo() {
        return "Linx - by John Morris - www.jacksshack.com";
    }
    
    public void start() {
        if (this.Linx_t == null) {
            (this.Linx_t = new Thread(this)).start();
        }
    }
    
    public void init() {
        this.resize(400, 300);
        this.stage = 0;
        this.brown = new Color(128, 64, 0);
        this.tan = new Color(238, 238, 204);
        this.green = new Color(0, 128, 64);
        this.darkGreen = new Color(0, 80, 0);
        (this.maxDist = new int[16])[0] = 270;
        this.maxDist[1] = 250;
        for (int i = 2; i < 13; ++i) {
            this.maxDist[i] = 250 - i * 10;
        }
        this.maxDist[13] = 110;
        this.maxDist[14] = 90;
        this.maxDist[15] = 50;
        this.score = new int[18];
        this.hole = 0;
        this.ballPos = new double[2];
        this.invalid = false;
        this.course = new int[18][205];
        (this.courseOptions = new int[3])[1] = 1;
        this.cursor = new int[2];
        this.xOff = 0.0;
        this.yOff = 0.0;
        this.scoreF = new Frame();
        this.grass = new int[50];
        final Random random = new Random();
        for (int j = 0; j < 50; ++j) {
            this.grass[j] = Math.abs(random.nextInt() % 30) + 20;
        }
        this.wind = new int[2];
        this.height = 0;
        this.message = new String();
        this.messageColor = Color.black;
        this.oldPos = new double[2];
        this.oldLie = 0;
        this.OK = true;
        this.codeF = new Frame();
        this.nameF = new Frame();
        this.trip = false;
        this.smiley = false;
        this.tree = true;
        this.sand = true;
        this.water = true;
        this.night = false;
        this.again = false;
        this.mulligan = false;
        this.cheated = false;
        this.line = false;
        this.base = 0;
    }
    
    public Frame createScoreCard() {
        final Font font = new Font("Veranda", 1, 12);
        final Frame frame = new Frame("Score Card");
        frame.setSize(400, 210);
        frame.setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Label label = new Label("Total");
        label.setForeground(Color.white);
        label.setFont(font);
        gridBagConstraints.gridy = 0;
        while (gridBagConstraints.gridy < 6) {
            final Label label2 = new Label("");
            gridBagConstraints.gridx = 0;
            if (gridBagConstraints.gridy == 0 || gridBagConstraints.gridy == 3) {
                label2.setText("Hole");
                label2.setForeground(Color.white);
            }
            if (gridBagConstraints.gridy == 1 || gridBagConstraints.gridy == 4) {
                label2.setText("Par");
                label2.setForeground(Color.yellow);
            }
            if (gridBagConstraints.gridy == 2 || gridBagConstraints.gridy == 5) {
                label2.setText("Score");
                label2.setForeground(Color.green);
            }
            label2.setFont(font);
            frame.add(label2, gridBagConstraints);
            gridBagConstraints.gridx = 1;
            while (gridBagConstraints.gridx < 10) {
                final Label label3 = new Label("");
                label3.setForeground(Color.white);
                label3.setFont(font);
                switch (gridBagConstraints.gridy) {
                    case 0: {
                        label3.setText(Integer.toString(gridBagConstraints.gridx));
                        label3.setForeground(Color.white);
                        break;
                    }
                    case 1: {
                        label3.setText(Integer.toString(this.course[gridBagConstraints.gridx - 1][0]));
                        label3.setForeground(Color.yellow);
                        break;
                    }
                    case 2: {
                        if (this.score[gridBagConstraints.gridx - 1] == 0) {
                            break;
                        }
                        label3.setText(Integer.toString(this.score[gridBagConstraints.gridx - 1]));
                        if (this.score[gridBagConstraints.gridx - 1] < this.course[gridBagConstraints.gridx - 1][0]) {
                            label3.setForeground(Color.blue);
                        }
                        if (this.score[gridBagConstraints.gridx - 1] > this.course[gridBagConstraints.gridx - 1][0]) {
                            label3.setForeground(Color.red);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        label3.setText(Integer.toString(gridBagConstraints.gridx + 9));
                        label3.setForeground(Color.white);
                        break;
                    }
                    case 4: {
                        label3.setText(Integer.toString(this.course[gridBagConstraints.gridx + 8][0]));
                        label3.setForeground(Color.yellow);
                        break;
                    }
                    case 5: {
                        if (this.score[gridBagConstraints.gridx + 8] == 0) {
                            break;
                        }
                        label3.setText(Integer.toString(this.score[gridBagConstraints.gridx + 8]));
                        if (this.score[gridBagConstraints.gridx + 8] < this.course[gridBagConstraints.gridx + 8][0]) {
                            label3.setForeground(Color.blue);
                        }
                        if (this.score[gridBagConstraints.gridx + 8] > this.course[gridBagConstraints.gridx + 8][0]) {
                            label3.setForeground(Color.red);
                            break;
                        }
                        break;
                    }
                }
                frame.add(label3, gridBagConstraints);
                final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
                ++gridBagConstraints2.gridx;
            }
            final Label label4 = new Label("");
            label4.setForeground(Color.white);
            label4.setFont(font);
            switch (gridBagConstraints.gridy) {
                case 0: {
                    label4.setText("Total");
                    label4.setForeground(Color.yellow);
                    break;
                }
                case 1: {
                    label4.setText(Integer.toString(this.course[0][0] + this.course[1][0] + this.course[2][0] + this.course[3][0] + this.course[4][0] + this.course[5][0] + this.course[6][0] + this.course[7][0] + this.course[8][0]));
                    break;
                }
                case 2: {
                    final int n = this.score[0] + this.score[1] + this.score[2] + this.score[3] + this.score[4] + this.score[5] + this.score[6] + this.score[7] + this.score[8];
                    if (n > 0) {
                        label4.setText(Integer.toString(n));
                        break;
                    }
                    break;
                }
                case 3: {
                    label4.setText("Total");
                    label4.setForeground(Color.yellow);
                    break;
                }
                case 4: {
                    label4.setText(Integer.toString(this.course[0][0] + this.course[1][0] + this.course[2][0] + this.course[3][0] + this.course[4][0] + this.course[5][0] + this.course[6][0] + this.course[7][0] + this.course[8][0] + this.course[9][0] + this.course[10][0] + this.course[11][0] + this.course[12][0] + this.course[13][0] + this.course[14][0] + this.course[15][0] + this.course[16][0] + this.course[17][0]));
                    break;
                }
                case 5: {
                    if (this.hole > 8) {
                        label4.setText(Integer.toString(this.score[0] + this.score[1] + this.score[2] + this.score[3] + this.score[4] + this.score[5] + this.score[6] + this.score[7] + this.score[8] + this.score[9] + this.score[10] + this.score[11] + this.score[12] + this.score[13] + this.score[14] + this.score[15] + this.score[16] + this.score[17]));
                        break;
                    }
                    break;
                }
            }
            gridBagConstraints.gridx = 10;
            frame.add(label4, gridBagConstraints);
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            ++gridBagConstraints3.gridy;
        }
        final Label label5 = new Label("www.jacksshack.com");
        label5.setFont(new Font("Veranda", 3, 12));
        label5.setForeground(Color.yellow);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 11;
        frame.add(label5, gridBagConstraints);
        frame.setResizable(false);
        return frame;
    }
    
    public void updateScoreCard() {
        final Label label = new Label(Integer.toString(this.score[this.hole]));
        Label label2;
        if (this.hole < 9) {
            label2 = (Label)this.scoreF.getComponent(23 + this.hole);
        }
        else {
            label2 = (Label)this.scoreF.getComponent(47 + this.hole);
        }
        label2.setText(label.getText());
        if (this.score[this.hole] < this.course[this.hole][0]) {
            label2.setForeground(Color.blue);
        }
        if (this.score[this.hole] > this.course[this.hole][0]) {
            label2.setForeground(Color.red);
        }
        label2.setSize(label.getPreferredSize());
        Label label3;
        Label label4;
        if (this.hole < 9) {
            label3 = (Label)this.scoreF.getComponent(32);
            label4 = new Label(Integer.toString(this.score[0] + this.score[1] + this.score[2] + this.score[3] + this.score[4] + this.score[5] + this.score[6] + this.score[7] + this.score[8]));
        }
        else {
            label3 = (Label)this.scoreF.getComponent(65);
            label4 = new Label(Integer.toString(this.score[0] + this.score[1] + this.score[2] + this.score[3] + this.score[4] + this.score[5] + this.score[6] + this.score[7] + this.score[8] + this.score[9] + this.score[10] + this.score[11] + this.score[12] + this.score[13] + this.score[14] + this.score[15] + this.score[16] + this.score[17]));
        }
        label3.setText(label4.getText());
        label3.setSize(label4.getPreferredSize());
        this.scoreF.validate();
    }
    
    public Frame createCodeFrame() {
        final TextField textField = new TextField(20);
        textField.setBackground(Color.white);
        textField.setForeground(Color.black);
        final Frame frame = new Frame("Enter Code");
        frame.setSize(300, 150);
        frame.setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        final Label label = new Label("www.jacksshack.com");
        final Label label2 = new Label("Enter Code");
        label.setFont(new Font("Veranda", 1, 12));
        label2.setFont(new Font("Veranda", 1, 12));
        label.setForeground(Color.yellow);
        label2.setForeground(Color.white);
        final Button button = new Button("Enter");
        button.setFont(new Font("Veranda", 1, 12));
        button.setForeground(Color.blue);
        button.setActionCommand("enter");
        button.addActionListener(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        frame.add(textField, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        frame.add(button, gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        frame.add(label2, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        frame.add(label, gridBagConstraints);
        frame.setResizable(false);
        return frame;
    }
    
    public void changeCodeFrame(final String text, final Color foreground) {
        final Label label = (Label)this.codeF.getComponent(2);
        label.setText(text);
        label.setForeground(foreground);
        label.setSize(new Label(text).getPreferredSize());
        this.codeF.validate();
    }
    
    public Frame createNameFrame() {
        final TextField textField = new TextField(20);
        textField.setBackground(Color.white);
        textField.setForeground(Color.black);
        final Frame frame = new Frame("Enter Your Name");
        frame.setSize(300, 150);
        frame.setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        final Label label = new Label("www.jacksshack.com");
        final Label label2 = new Label("Enter Your Name");
        label.setFont(new Font("Veranda", 1, 12));
        label2.setFont(new Font("Veranda", 1, 12));
        label.setForeground(Color.yellow);
        label2.setForeground(Color.white);
        final Button button = new Button("OK");
        button.setFont(new Font("Veranda", 1, 12));
        button.setForeground(Color.blue);
        button.setActionCommand("OK");
        button.addActionListener(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        frame.add(textField, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        frame.add(button, gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        frame.add(label2, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        frame.add(label, gridBagConstraints);
        frame.setResizable(false);
        return frame;
    }
    
    public void paint(final Graphics graphics) {
        switch (this.courseOptions[0]) {
            case 1: {
                if (this.courseOptions[2] != 1) {
                    graphics.setColor(this.tan);
                    break;
                }
                graphics.setColor(Color.white);
                break;
            }
            case 2: {
                if (this.courseOptions[2] != 1) {
                    graphics.setColor(Color.blue);
                    break;
                }
                graphics.setColor(Color.cyan);
                break;
            }
            case 3: {
                graphics.setColor(Color.black);
                break;
            }
            default: {
                if (this.courseOptions[2] != 1) {
                    graphics.setColor(this.green);
                    break;
                }
                graphics.setColor(Color.white);
                break;
            }
        }
        if (this.trip) {
            graphics.setColor(Color.orange);
        }
        graphics.fillRect(0, 0, 400, 300);
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        if (this.courseOptions[0] == 3) {
            graphics.setColor(Color.yellow);
            if (this.stage == 1) {
                graphics.fillOval(120, 20, 4, 4);
                graphics.fillOval(260, 50, 4, 4);
                graphics.fillOval(330, 70, 4, 4);
                graphics.fillOval(200, 100, 4, 4);
                graphics.fillOval(160, 110, 4, 4);
                graphics.fillOval(350, 140, 4, 4);
                graphics.fillOval(250, 180, 4, 4);
                graphics.fillOval(140, 190, 4, 4);
                graphics.fillOval(190, 210, 4, 4);
                graphics.fillOval(220, 240, 4, 4);
                graphics.fillOval(290, 260, 4, 4);
                graphics.fillOval(360, 290, 4, 4);
            }
            else {
                graphics.fillOval(150 + (int)this.xOff, 40 + (int)this.yOff, 8, 8);
                graphics.fillOval(420 + (int)this.xOff, 100 + (int)this.yOff, 8, 8);
                graphics.fillOval(560 + (int)this.xOff, 140 + (int)this.yOff, 8, 8);
                graphics.fillOval(300 + (int)this.xOff, 200 + (int)this.yOff, 8, 8);
                graphics.fillOval(220 + (int)this.xOff, 220 + (int)this.yOff, 8, 8);
                graphics.fillOval(600 + (int)this.xOff, 280 + (int)this.yOff, 8, 8);
                graphics.fillOval(400 + (int)this.xOff, 360 + (int)this.yOff, 8, 8);
                graphics.fillOval(180 + (int)this.xOff, 380 + (int)this.yOff, 8, 8);
                graphics.fillOval(280 + (int)this.xOff, 420 + (int)this.yOff, 8, 8);
                graphics.fillOval(340 + (int)this.xOff, 480 + (int)this.yOff, 8, 8);
                graphics.fillOval(480 + (int)this.xOff, 520 + (int)this.yOff, 8, 8);
                graphics.fillOval(620 + (int)this.xOff, 580 + (int)this.yOff, 8, 8);
            }
        }
        if (this.stage > 0) {
            int n = 5;
            while (this.course[this.hole][n] != 0 && n < 200) {
                switch (this.course[this.hole][n]) {
                    default: {
                        continue;
                    }
                    case 1: {
                        graphics.setColor(Color.green);
                        if (this.courseOptions[2] == 1) {
                            graphics.setColor(Color.white.darker());
                        }
                        if (this.trip) {
                            graphics.setColor(Color.orange);
                        }
                        if (this.stage == 1) {
                            graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]), this.scale(this.course[this.hole][n + 3]), this.scale(this.course[this.hole][n + 4]));
                        }
                        else {
                            graphics.fillOval(this.course[this.hole][n + 1] + 100 + (int)this.xOff, this.course[this.hole][n + 2] + (int)this.yOff, this.course[this.hole][n + 3], this.course[this.hole][n + 4]);
                        }
                        n += 5;
                        continue;
                    }
                    case 2: {
                        graphics.setColor(Color.white);
                        if (this.courseOptions[2] == 1) {
                            graphics.setColor(Color.white.darker().darker().darker());
                        }
                        if (this.trip) {
                            graphics.setColor(Color.red);
                        }
                        if (this.sand) {
                            if (this.stage == 1) {
                                graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]), this.scale(this.course[this.hole][n + 3]), this.scale(this.course[this.hole][n + 4]));
                            }
                            else {
                                graphics.fillOval(this.course[this.hole][n + 1] + 100 + (int)this.xOff, this.course[this.hole][n + 2] + (int)this.yOff, this.course[this.hole][n + 3], this.course[this.hole][n + 4]);
                            }
                        }
                        n += 5;
                        continue;
                    }
                    case 3: {
                        graphics.setColor(Color.black);
                        if (this.tree) {
                            if (this.stage == 1) {
                                if (this.courseOptions[2] < 2) {
                                    graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 102, this.scale(this.course[this.hole][n + 2]) + 18, 20, 4);
                                }
                                graphics.setColor(this.darkGreen);
                                if (this.courseOptions[2] == 1) {
                                    graphics.setColor(Color.white.darker().darker());
                                }
                                if (this.trip) {
                                    graphics.setColor(Color.yellow);
                                }
                                switch (this.courseOptions[0]) {
                                    case 1: {
                                        graphics.fillRoundRect(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]) + 2, 5, 8, 2, 2);
                                        graphics.fillRoundRect(this.scale(this.course[this.hole][n + 1]) + 107, this.scale(this.course[this.hole][n + 2]), 5, 20, 2, 2);
                                        graphics.fillRoundRect(this.scale(this.course[this.hole][n + 1]) + 115, this.scale(this.course[this.hole][n + 2]) + 2, 5, 8, 2, 2);
                                        graphics.fillRoundRect(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]) + 8, 20, 5, 2, 2);
                                        break;
                                    }
                                    case 2: {
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]) + 5, 20, 2);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 109, this.scale(this.course[this.hole][n + 2]), 3, 5);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 105, this.scale(this.course[this.hole][n + 2]) + 1, this.scale(this.course[this.hole][n + 1]) + 112, this.scale(this.course[this.hole][n + 2]) + 8);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 105, this.scale(this.course[this.hole][n + 2]) + 2, this.scale(this.course[this.hole][n + 1]) + 111, this.scale(this.course[this.hole][n + 2]) + 8);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 106, this.scale(this.course[this.hole][n + 2]) + 1, this.scale(this.course[this.hole][n + 1]) + 112, this.scale(this.course[this.hole][n + 2]) + 7);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 115, this.scale(this.course[this.hole][n + 2]) + 1, this.scale(this.course[this.hole][n + 1]) + 108, this.scale(this.course[this.hole][n + 2]) + 8);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 115, this.scale(this.course[this.hole][n + 2]) + 2, this.scale(this.course[this.hole][n + 1]) + 107, this.scale(this.course[this.hole][n + 2]) + 8);
                                        graphics.drawLine(this.scale(this.course[this.hole][n + 1]) + 114, this.scale(this.course[this.hole][n + 2]) + 1, this.scale(this.course[this.hole][n + 1]) + 108, this.scale(this.course[this.hole][n + 2]) + 7);
                                        graphics.fillRect(this.scale(this.course[this.hole][n + 1]) + 102, this.scale(this.course[this.hole][n + 2]) + 1, 5, 2);
                                        graphics.fillRect(this.scale(this.course[this.hole][n + 1]) + 114, this.scale(this.course[this.hole][n + 2]) + 1, 5, 2);
                                        graphics.setColor(this.brown);
                                        graphics.fillRect(this.scale(this.course[this.hole][n + 1]) + 109, this.scale(this.course[this.hole][n + 2]) + 8, 3, 12);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 105, this.scale(this.course[this.hole][n + 2]) + 7, 3, 3);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 113, this.scale(this.course[this.hole][n + 2]) + 7, 3, 3);
                                        break;
                                    }
                                    case 3: {
                                        array[0] = this.scale(this.course[this.hole][n + 1]) + 110;
                                        array[1] = this.scale(this.course[this.hole][n + 1]) + 130;
                                        array[2] = array[0] + 10;
                                        array[3] = array[0];
                                        array2[0] = this.scale(this.course[this.hole][n + 2]);
                                        array2[1] = this.scale(this.course[this.hole][n + 2]) - 10;
                                        array2[2] = this.scale(this.course[this.hole][n + 2]) + 10;
                                        array2[3] = array2[0];
                                        graphics.setColor(Color.orange);
                                        graphics.fillPolygon(array, array2, 4);
                                        final int[] array3 = array2;
                                        final int n2 = 0;
                                        array3[n2] += 4;
                                        final int[] array4 = array2;
                                        final int n3 = 2;
                                        array4[n3] -= 4;
                                        array2[3] = array2[0];
                                        graphics.setColor(Color.yellow);
                                        graphics.fillPolygon(array, array2, 4);
                                        graphics.setColor(Color.lightGray);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]), 20, 20);
                                        graphics.setColor(Color.black);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 104, this.scale(this.course[this.hole][n + 2]) + 3, 4, 4);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 113, this.scale(this.course[this.hole][n + 2]) + 8, 4, 4);
                                        graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 104, this.scale(this.course[this.hole][n + 2]) + 13, 4, 4);
                                        break;
                                    }
                                    default: {
                                        array[0] = this.scale(this.course[this.hole][n + 1]) + 110;
                                        array[1] = this.scale(this.course[this.hole][n + 1]) + 116;
                                        array[2] = this.scale(this.course[this.hole][n + 1]) + 104;
                                        array[3] = array[0];
                                        array2[0] = this.scale(this.course[this.hole][n + 2]);
                                        array2[2] = (array2[1] = this.scale(this.course[this.hole][n + 2]) + 5);
                                        array2[3] = array2[0];
                                        for (int i = 0; i < 3; ++i) {
                                            graphics.fillPolygon(array, array2, 4);
                                            final int[] array5 = array;
                                            final int n4 = 1;
                                            array5[n4] += 2;
                                            final int[] array6 = array;
                                            final int n5 = 2;
                                            array6[n5] -= 2;
                                            final int[] array7 = array2;
                                            final int n6 = 1;
                                            array7[n6] += 5;
                                            array2[2] = array2[1];
                                            array2[3] = (array2[0] = array2[1] - 6);
                                        }
                                        graphics.setColor(this.brown);
                                        graphics.fillRect(this.scale(this.course[this.hole][n + 1]) + 109, this.scale(this.course[this.hole][n + 2]) + 15, 3, 5);
                                        break;
                                    }
                                }
                            }
                            else {
                                if (this.courseOptions[2] < 2) {
                                    graphics.fillOval(this.course[this.hole][n + 1] + 104 + (int)this.xOff, this.course[this.hole][n + 2] + (int)this.yOff + 36, 40, 8);
                                }
                                graphics.setColor(this.darkGreen);
                                if (this.courseOptions[2] == 1) {
                                    graphics.setColor(Color.white.darker().darker());
                                }
                                if (this.trip) {
                                    graphics.setColor(Color.yellow);
                                }
                                switch (this.courseOptions[0]) {
                                    case 1: {
                                        graphics.fillRoundRect(this.course[this.hole][n + 1] + (int)this.xOff + 100, this.course[this.hole][n + 2] + (int)this.yOff + 4, 10, 16, 4, 4);
                                        graphics.fillRoundRect(this.course[this.hole][n + 1] + (int)this.xOff + 114, this.course[this.hole][n + 2] + (int)this.yOff, 10, 40, 4, 4);
                                        graphics.fillRoundRect(this.course[this.hole][n + 1] + (int)this.xOff + 130, this.course[this.hole][n + 2] + (int)this.yOff + 4, 10, 16, 4, 4);
                                        graphics.fillRoundRect(this.course[this.hole][n + 1] + (int)this.xOff + 100, this.course[this.hole][n + 2] + (int)this.yOff + 16, 40, 10, 4, 4);
                                        break;
                                    }
                                    case 2: {
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 100, this.course[this.hole][n + 2] + (int)this.yOff + 10, 40, 4);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 118, this.course[this.hole][n + 2] + (int)this.yOff, 6, 10);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 110, this.course[this.hole][n + 2] + (int)this.yOff + 2, this.course[this.hole][n + 1] + (int)this.xOff + 124, this.course[this.hole][n + 2] + (int)this.yOff + 16);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 110, this.course[this.hole][n + 2] + (int)this.yOff + 4, this.course[this.hole][n + 1] + (int)this.xOff + 122, this.course[this.hole][n + 2] + (int)this.yOff + 16);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 112, this.course[this.hole][n + 2] + (int)this.yOff + 2, this.course[this.hole][n + 1] + (int)this.xOff + 124, this.course[this.hole][n + 2] + (int)this.yOff + 14);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 130, this.course[this.hole][n + 2] + (int)this.yOff + 2, this.course[this.hole][n + 1] + (int)this.xOff + 116, this.course[this.hole][n + 2] + (int)this.yOff + 16);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 130, this.course[this.hole][n + 2] + (int)this.yOff + 4, this.course[this.hole][n + 1] + (int)this.xOff + 114, this.course[this.hole][n + 2] + (int)this.yOff + 16);
                                        graphics.drawLine(this.course[this.hole][n + 1] + (int)this.xOff + 128, this.course[this.hole][n + 2] + (int)this.yOff + 2, this.course[this.hole][n + 1] + (int)this.xOff + 116, this.course[this.hole][n + 2] + (int)this.yOff + 14);
                                        graphics.fillRect(this.course[this.hole][n + 1] + (int)this.xOff + 104, this.course[this.hole][n + 2] + (int)this.yOff + 2, 10, 4);
                                        graphics.fillRect(this.course[this.hole][n + 1] + (int)this.xOff + 128, this.course[this.hole][n + 2] + (int)this.yOff + 2, 10, 4);
                                        graphics.setColor(this.brown);
                                        graphics.fillRect(this.course[this.hole][n + 1] + (int)this.xOff + 118, this.course[this.hole][n + 2] + (int)this.yOff + 16, 6, 24);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 110, this.course[this.hole][n + 2] + (int)this.yOff + 14, 6, 6);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 126, this.course[this.hole][n + 2] + (int)this.yOff + 14, 6, 6);
                                        break;
                                    }
                                    case 3: {
                                        array[0] = this.course[this.hole][n + 1] + (int)this.xOff + 120;
                                        array[1] = this.course[this.hole][n + 1] + (int)this.xOff + 160;
                                        array[2] = array[0] + 20;
                                        array[3] = array[0];
                                        array2[0] = this.course[this.hole][n + 2] + (int)this.yOff;
                                        array2[1] = this.course[this.hole][n + 2] + (int)this.yOff - 20;
                                        array2[2] = this.course[this.hole][n + 2] + (int)this.yOff + 20;
                                        array2[3] = array2[0];
                                        graphics.setColor(Color.orange);
                                        graphics.fillPolygon(array, array2, 4);
                                        final int[] array8 = array2;
                                        final int n7 = 0;
                                        array8[n7] += 8;
                                        final int[] array9 = array2;
                                        final int n8 = 2;
                                        array9[n8] -= 8;
                                        array2[3] = array2[0];
                                        graphics.setColor(Color.yellow);
                                        graphics.fillPolygon(array, array2, 4);
                                        graphics.setColor(Color.lightGray);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 100, this.course[this.hole][n + 2] + (int)this.yOff, 40, 40);
                                        graphics.setColor(Color.black);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 108, this.course[this.hole][n + 2] + (int)this.yOff + 6, 8, 8);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 126, this.course[this.hole][n + 2] + (int)this.yOff + 16, 8, 8);
                                        graphics.fillOval(this.course[this.hole][n + 1] + (int)this.xOff + 108, this.course[this.hole][n + 2] + (int)this.yOff + 26, 8, 8);
                                        break;
                                    }
                                    default: {
                                        array[0] = this.course[this.hole][n + 1] + (int)this.xOff + 120;
                                        array[1] = this.course[this.hole][n + 1] + (int)this.xOff + 132;
                                        array[2] = this.course[this.hole][n + 1] + (int)this.xOff + 108;
                                        array[3] = array[0];
                                        array2[0] = this.course[this.hole][n + 2] + (int)this.yOff;
                                        array2[2] = (array2[1] = this.course[this.hole][n + 2] + (int)this.yOff + 10);
                                        array2[3] = array2[0];
                                        for (int j = 0; j < 3; ++j) {
                                            graphics.fillPolygon(array, array2, 4);
                                            final int[] array10 = array;
                                            final int n9 = 1;
                                            array10[n9] += 4;
                                            final int[] array11 = array;
                                            final int n10 = 2;
                                            array11[n10] -= 4;
                                            final int[] array12 = array2;
                                            final int n11 = 1;
                                            array12[n11] += 10;
                                            array2[2] = array2[1];
                                            array2[3] = (array2[0] = array2[1] - 12);
                                        }
                                        graphics.setColor(this.brown);
                                        graphics.fillRect(this.course[this.hole][n + 1] + (int)this.xOff + 118, this.course[this.hole][n + 2] + (int)this.yOff + 30, 6, 10);
                                        break;
                                    }
                                }
                            }
                        }
                        n += 3;
                        continue;
                    }
                    case 4: {
                        graphics.setColor(Color.blue);
                        if (this.courseOptions[2] == 1) {
                            graphics.setColor(Color.cyan);
                        }
                        if (this.trip) {
                            graphics.setColor(Color.magenta);
                        }
                        if (this.water) {
                            if (this.stage == 1) {
                                graphics.fillOval(this.scale(this.course[this.hole][n + 1]) + 100, this.scale(this.course[this.hole][n + 2]), this.scale(this.course[this.hole][n + 3]), this.scale(this.course[this.hole][n + 4]));
                            }
                            else {
                                graphics.fillOval(this.course[this.hole][n + 1] + 100 + (int)this.xOff, this.course[this.hole][n + 2] + (int)this.yOff, this.course[this.hole][n + 3], this.course[this.hole][n + 4]);
                            }
                        }
                        n += 5;
                        continue;
                    }
                }
            }
            graphics.setColor(Color.green);
            if (this.stage == 1) {
                graphics.fillRect(this.scale(this.course[this.hole][3] - 13) + 100, this.scale(this.course[this.hole][4] - 18), this.scale(30.0), this.scale(40.0));
            }
            else {
                graphics.fillRect(this.course[this.hole][3] - 13 + 100 + (int)this.xOff, this.course[this.hole][4] - 18 + (int)this.yOff, 30, 40);
            }
            if (this.courseOptions[2] == 2) {
                graphics.setColor(Color.cyan);
                for (int k = 0; k < 300; k += 10) {
                    int n12;
                    if (k % 20 == 0) {
                        n12 = 5;
                    }
                    else {
                        n12 = 10;
                    }
                    for (int l = 100 + n12; l < 400; l += 10) {
                        graphics.drawLine(l, k, l - 5, k + 5);
                    }
                }
            }
            if (this.night) {
                graphics.setColor(Color.black);
                if (this.stage == 1) {
                    graphics.fillRect(0, 0, this.scale(this.ballPos[0]) + 90, 300);
                    graphics.fillRect(this.scale(this.ballPos[0]) + 114, 0, 400 - (this.scale(this.ballPos[0]) + 100), 300);
                    graphics.fillRect(0, 0, 400, this.scale(this.ballPos[1]) - 10);
                    graphics.fillRect(0, this.scale(this.ballPos[1]) + 14, 400, 300 - this.scale(this.ballPos[1]));
                    int n13 = this.scale(this.ballPos[0]) + 90;
                    int n14 = this.scale(this.ballPos[1]) - 10;
                    for (int n15 = 0; n15 < 5; ++n15) {
                        --n13;
                        --n14;
                        graphics.drawOval(n13, n14, 24 + n15 * 2, 24 + n15 * 2);
                    }
                }
                else {
                    graphics.fillRect(0, 0, 230, 300);
                    graphics.fillRect(270, 0, 130, 300);
                    graphics.fillRect(0, 0, 400, 130);
                    graphics.fillRect(0, 170, 400, 130);
                    int n16 = 230;
                    int n17 = 130;
                    for (int n18 = 0; n18 < 10; ++n18) {
                        --n16;
                        --n17;
                        graphics.drawOval(n16, n17, 40 + n18 * 2, 40 + n18 * 2);
                    }
                }
            }
            int n19 = 350;
            int n20 = 250;
            if (this.stage == 1) {
                if ((this.scale(this.ballPos[0]) + 100 >= 350 && this.scale(this.ballPos[1]) >= 250) || (this.scale(this.course[this.hole][1]) + 100 >= 350 && this.scale(this.course[this.hole][2]) >= 250)) {
                    n20 = 0;
                    if ((this.scale(this.ballPos[0]) + 100 >= 350 && this.scale(this.ballPos[1]) <= 50) || (this.scale(this.course[this.hole][1]) + 100 >= 350 && this.scale(this.course[this.hole][2]) <= 50)) {
                        n19 = 100;
                    }
                }
                graphics.setColor(Color.cyan);
                graphics.fillRect(n19, n20, 50, 50);
                graphics.setColor(Color.white);
                graphics.fillOval(n19 + 5, n20, 40, 40);
                graphics.setColor(Color.black);
                graphics.drawOval(n19 + 5, n20, 40, 40);
                if (this.ballPos[0] == this.course[this.hole][3] && this.ballPos[1] == this.course[this.hole][4]) {
                    graphics.setColor(this.brown);
                    graphics.fillRect(n19 + 10, n20 + 40, 30, 5);
                    graphics.fillRect(n19 + 20, n20 + 45, 10, 5);
                }
                else {
                    switch (this.base) {
                        case 0: {
                            graphics.setColor(this.green);
                            if (this.courseOptions[2] == 1) {
                                graphics.setColor(Color.white);
                            }
                            if (this.trip) {
                                graphics.setColor(Color.cyan);
                            }
                            for (int n21 = 0; n21 < 50; ++n21) {
                                graphics.drawLine(n19 + n21, n20 + 50, n19 + n21, n20 + 50 - this.grass[n21]);
                            }
                            break;
                        }
                        case 1: {
                            graphics.setColor(Color.green);
                            if (this.courseOptions[2] == 1) {
                                graphics.setColor(Color.white.darker());
                            }
                            if (this.trip) {
                                graphics.setColor(Color.orange);
                            }
                            graphics.fillRect(n19, n20 + 40, 50, 10);
                            break;
                        }
                        case 2: {
                            graphics.setColor(Color.white);
                            if (this.courseOptions[2] == 1) {
                                graphics.setColor(Color.white.darker().darker().darker());
                            }
                            if (this.trip) {
                                graphics.setColor(Color.red);
                            }
                            graphics.fillRect(n19, n20 + 30, 50, 20);
                            break;
                        }
                    }
                }
                graphics.setColor(Color.black);
                graphics.drawRect(n19, n20, 50, 50);
            }
            int n22 = 100;
            int n23 = 250;
            if (this.stage == 1) {
                if ((this.scale(this.ballPos[0]) + 100 <= 150 && this.scale(this.ballPos[1]) >= 250) || (this.scale(this.course[this.hole][1]) + 100 <= 150 && this.scale(this.course[this.hole][2]) >= 250)) {
                    n23 = 0;
                    if ((this.scale(this.ballPos[0]) + 100 <= 150 && this.scale(this.ballPos[1]) <= 50) || (this.scale(this.course[this.hole][1]) + 100 <= 150 && this.scale(this.course[this.hole][2]) <= 50)) {
                        n22 = 350;
                    }
                }
                graphics.setColor(Color.red);
                graphics.drawLine(n22 + 25, n23 + 20, n22 + 25 + this.wind[0], n23 + 20 + this.wind[1]);
                graphics.setColor(Color.black);
                if (this.night || this.courseOptions[0] == 3 || this.courseOptions[0] == 2) {
                    graphics.setColor(Color.white);
                }
                graphics.drawOval(n22 + 10, n23 + 5, 30, 30);
                graphics.setFont(new Font("Veranda", 1, 12));
                graphics.drawString(String.valueOf(Integer.toString(Math.abs((int)Math.sqrt(this.wind[0] * this.wind[0] + this.wind[1] * this.wind[1])))) + "mph", n22 + 10, n23 + 45);
                graphics.drawRect(n22, n23, 50, 50);
            }
            graphics.setColor(Color.black);
            if (this.stage == 1) {
                graphics.fillOval(this.scale(this.course[this.hole][1]) + 100, this.scale(this.course[this.hole][2]), 4, 4);
                graphics.setColor(Color.lightGray);
                graphics.drawLine(this.scale(this.course[this.hole][1]) + 102, this.scale(this.course[this.hole][2]), this.scale(this.course[this.hole][1]) + 102, this.scale(this.course[this.hole][2]) - 10);
                graphics.setColor(Color.red);
                graphics.fillRect(this.scale(this.course[this.hole][1]) + 102, this.scale(this.course[this.hole][2]) - 10, 5, 5);
            }
            else {
                graphics.fillOval(this.course[this.hole][1] + 100 + (int)this.xOff, this.course[this.hole][2] + (int)this.yOff, 4, 4);
            }
            if (this.stage == 1) {
                graphics.setColor(Color.red);
                if (this.club == 15) {
                    graphics.fillOval(this.cursor[0] - 2, this.cursor[1] - 2, 4, 4);
                }
                else if (this.line) {
                    graphics.drawOval(this.cursor[0] - 8, this.cursor[1] - 8, 16, 16);
                }
                else {
                    graphics.drawOval(this.cursor[0] - 4, this.cursor[1] - 4, 8, 8);
                }
                graphics.drawLine(this.scale(this.ballPos[0]) + 102, this.scale(this.ballPos[1]) + 2, this.cursor[0], this.cursor[1]);
            }
            graphics.setColor(Color.white);
            if (this.courseOptions[2] == 1) {
                graphics.setColor(Color.orange);
            }
            if (this.smiley) {
                graphics.setColor(Color.yellow);
            }
            if (this.stage == 1) {
                if (this.smiley) {
                    graphics.fillOval(this.scale(this.ballPos[0]) + 100, this.scale(this.ballPos[1]), 10, 10);
                }
                else {
                    graphics.fillOval(this.scale(this.ballPos[0]) + 100, this.scale(this.ballPos[1]), 4, 4);
                }
                graphics.setColor(Color.black);
                if (this.smiley) {
                    graphics.drawOval(this.scale(this.ballPos[0]) + 100, this.scale(this.ballPos[1]), 10, 10);
                    graphics.fillOval(this.scale(this.ballPos[0]) + 101, this.scale(this.ballPos[1]) + 1, 4, 4);
                    graphics.fillOval(this.scale(this.ballPos[0]) + 105, this.scale(this.ballPos[1]) + 1, 4, 4);
                    graphics.drawArc(this.scale(this.ballPos[0]) + 102, this.scale(this.ballPos[1]) + 2, 6, 6, 180, 180);
                }
                else {
                    graphics.drawOval(this.scale(this.ballPos[0]) + 100, this.scale(this.ballPos[1]), 4, 4);
                }
            }
            else {
                this.getLie();
                if (!this.in && ((this.lie != 4 && this.lie != 5) || (this.ballPos[0] == this.course[this.hole][3] && this.ballPos[1] == this.course[this.hole][4]) || this.height > 4)) {
                    if (this.smiley) {
                        graphics.fillOval(250, 150, this.height + 6, this.height + 6);
                    }
                    else {
                        graphics.fillOval(250, 150, this.height, this.height);
                    }
                    graphics.setColor(Color.black);
                    if (this.smiley) {
                        graphics.drawOval(250, 150, this.height + 6, this.height + 6);
                    }
                    else {
                        graphics.drawOval(250, 150, this.height, this.height);
                    }
                    if (this.smiley) {
                        graphics.fillOval(250 + (this.height + 6) / 8, 150 + (this.height + 6) / 8, (this.height + 6) / 2, (this.height + 6) / 2);
                        graphics.fillOval(250 + (this.height + 6) - (this.height + 6) / 8 - (this.height + 6) / 2, 150 + (this.height + 6) / 8, (this.height + 6) / 2, (this.height + 6) / 2);
                        graphics.drawArc(252, 150 + this.height / 2, this.height + 2, this.height + 2, 180, 180);
                    }
                }
                if (this.lie == 4 && this.height <= 4 && this.ballPos[0] != this.course[this.hole][3] && this.ballPos[1] != this.course[this.hole][4]) {
                    graphics.setColor(Color.cyan);
                    graphics.drawOval(248, 148, 8, 8);
                    graphics.drawOval(246, 146, 12, 12);
                    graphics.drawOval(244, 144, 16, 16);
                }
            }
            if (!this.messageColor.equals(Color.black)) {
                graphics.setFont(new Font("Veranda", 1, 20));
                graphics.setColor(Color.black);
                graphics.drawString(this.message, 250 - this.message.length() / 2 * 10, 150);
                graphics.setColor(this.messageColor);
                graphics.drawString(this.message, 250 - this.message.length() / 2 * 10 + 2, 148);
            }
            if (this.stage == 3) {
                graphics.setFont(new Font("Veranda", 1, 20));
                int n24 = 0;
                if (this.OK) {
                    for (int n25 = 0; n25 < 18; ++n25) {
                        n24 += this.score[n25] - this.course[n25][0];
                    }
                }
                else {
                    for (int n26 = 0; n26 < 3; ++n26) {
                        n24 += this.score[n26] - this.course[n26][0];
                    }
                }
                String s = new String("You are ");
                if (n24 < 0) {
                    s = s.concat(Integer.toString(n24));
                }
                if (n24 > 0) {
                    s = s.concat("+" + Integer.toString(n24));
                }
                if (n24 == 0) {
                    s = s.concat("EVEN");
                }
                final String concat = s.concat(" for the day");
                graphics.setColor(Color.black);
                graphics.drawString(concat, 130, 150);
                if (!this.OK) {
                    graphics.drawString("Register for all 18 holes at", 120, 50);
                    graphics.drawString("www.jacksshack.com", 140, 80);
                    graphics.setColor(Color.red);
                    graphics.drawString("Register for all 18 holes at", 122, 48);
                    graphics.drawString("www.jacksshack.com", 142, 78);
                }
                graphics.setColor(Color.yellow);
                graphics.drawString(concat, 132, 148);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("Veranda", 3, 15));
                graphics.drawString("Click to play again.", 190, 170);
            }
        }
        else {
            graphics.setFont(new Font("Veranda", 1, 20));
            graphics.setColor(Color.black);
            if (!this.invalid) {
                graphics.drawString("Building Course, Please Wait...", 105, 160);
                graphics.setColor(Color.yellow);
                graphics.drawString("Building Course, Please Wait...", 107, 158);
            }
            else {
                graphics.drawString("Invalid Hole #" + Integer.toString(this.hole + 1) + "!", 180, 160);
                graphics.setColor(Color.red);
                graphics.drawString("Invalid Hole #" + Integer.toString(this.hole + 1) + "!", 182, 158);
            }
        }
        graphics.setColor(this.tan);
        graphics.fillRect(0, 0, 100, 300);
        graphics.setFont(new Font("Veranda", 1, 15));
        graphics.setColor(Color.black);
        if (this.hole == 0) {
            graphics.drawString("1st Hole", 5, 15);
        }
        if (this.hole == 1) {
            graphics.drawString("2nd Hole", 5, 15);
        }
        if (this.hole == 2) {
            graphics.drawString("3rd Hole", 5, 15);
        }
        if (this.hole > 2) {
            graphics.drawString(String.valueOf(Integer.toString(this.hole + 1)) + "th Hole", 5, 15);
        }
        graphics.drawString("Par " + Integer.toString(this.course[this.hole][0]), 5, 35);
        if (this.score[this.hole] % 10 == 0 && (this.score[this.hole] < 10 || this.score[this.hole] > 19)) {
            graphics.drawString(String.valueOf(Integer.toString(this.score[this.hole] + 1)) + "st Shot", 5, 55);
        }
        else if ((this.score[this.hole] - 1) % 10 == 0 && (this.score[this.hole] < 10 || this.score[this.hole] > 19)) {
            graphics.drawString(String.valueOf(Integer.toString(this.score[this.hole] + 1)) + "nd Shot", 5, 55);
        }
        else if ((this.score[this.hole] - 2) % 10 == 0 && (this.score[this.hole] < 10 || this.score[this.hole] > 19)) {
            graphics.drawString(String.valueOf(Integer.toString(this.score[this.hole] + 1)) + "rd Shot", 5, 55);
        }
        else {
            graphics.drawString(String.valueOf(Integer.toString(this.score[this.hole] + 1)) + "th Shot", 5, 55);
        }
        this.distance = (int)Math.sqrt(new Integer(Math.abs(((int)this.ballPos[0] - this.course[this.hole][1]) * ((int)this.ballPos[0] - this.course[this.hole][1])) + Math.abs(((int)this.ballPos[1] - this.course[this.hole][2]) * ((int)this.ballPos[1] - this.course[this.hole][2]))));
        graphics.setFont(new Font("Veranda", 0, 10));
        graphics.drawString("Dist. to pin: " + Integer.toString(this.distance) + " yds", 5, 70);
        graphics.setFont(new Font("Veranda", 1, 15));
        graphics.setColor(this.brown);
        graphics.drawRect(0, 80, 100, 40);
        graphics.fillRect(0, 80, 100, 20);
        graphics.setColor(Color.white);
        graphics.drawString("Woods", 25, 95);
        for (int n27 = 0; n27 < 5; ++n27) {
            graphics.setColor(Color.black);
            if (n27 == this.club) {
                graphics.setColor(Color.yellow);
            }
            graphics.drawString(Integer.toString(n27 + 1), n27 * 20 + 5, 115);
        }
        graphics.setColor(Color.gray);
        graphics.drawRect(0, 120, 100, 60);
        graphics.fillRect(0, 120, 100, 20);
        graphics.setColor(Color.white);
        graphics.drawString("Irons", 30, 135);
        for (int n28 = 0; n28 < 8; ++n28) {
            graphics.setColor(Color.black);
            if (n28 + 5 == this.club) {
                graphics.setColor(Color.yellow);
            }
            graphics.drawString(Integer.toString(n28 + 2), n28 * 10 + 10, 155);
        }
        if (this.club == 13) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Color.black);
        }
        graphics.drawString("PW", 15, 175);
        if (this.club == 14) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Color.black);
        }
        graphics.drawString("SW", 45, 175);
        if (this.club == 15) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Color.black);
        }
        graphics.drawString("P", 75, 175);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Veranda", 0, 10));
        if (this.club != 15) {
            graphics.drawString("Max: " + Integer.toString(this.maxDist[this.club]) + " yds", 20, 190);
        }
        else {
            graphics.drawString("Max: 150 ft", 20, 190);
        }
        graphics.setColor(Color.black);
        graphics.fillArc(10, 195, 80, 80, 40, 280);
        graphics.setColor(Color.green);
        if (this.power <= 18) {
            graphics.fillArc(10, 195, 80, 80, 270, -10 * this.power);
        }
        else {
            graphics.fillArc(10, 195, 80, 80, 270, -180);
            graphics.setColor(Color.red);
            graphics.fillArc(10, 195, 80, 80, 90, -10 * (this.power - 18));
        }
        graphics.setColor(Color.yellow);
        graphics.fillArc(10, 195, 80, 80, 270, -10 * this.accuracy);
        graphics.setColor(Color.blue);
        graphics.fillRect(49, 260, 2, 15);
        graphics.fillRect(49, 195, 2, 15);
        if (this.swing) {
            graphics.setColor(Color.gray);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.fillOval(25, 210, 50, 50);
        if (this.swing) {
            graphics.setColor(Color.green);
        }
        else {
            graphics.setColor(Color.red);
        }
        graphics.setFont(new Font("Veranda", 1, 15));
        graphics.drawString("Swing", 28, 240);
        if (this.codeF.isShowing()) {
            graphics.setColor(Color.red);
        }
        else {
            graphics.setColor(Color.blue);
        }
        graphics.fill3DRect(0, 280, 100, 20, !this.codeF.isShowing());
        graphics.setColor(Color.white);
        graphics.drawString("Enter Code", 10, 295);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, 399, 299);
        graphics.drawLine(100, 0, 100, 300);
    }
    
    public void update(final Graphics graphics) {
        final Image image = this.createImage(this.getSize().width, this.getSize().height);
        this.paint(image.getGraphics());
        graphics.drawImage(image, 0, 0, null);
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public void buildCourse() {
        for (int i = 0; i < 18; ++i) {
            for (int j = 0; j < 205; ++j) {
                this.course[i][j] = 0;
            }
        }
        int hole = 0;
        if (this.getParameter("course") != null) {
            final String parameter = this.getParameter("course");
            if (parameter.length() >= 1) {
                this.courseOptions[0] = Integer.parseInt(parameter.substring(0, 1));
            }
            if (this.courseOptions[0] > 3 || this.courseOptions[0] < 0) {
                this.courseOptions[0] = 0;
            }
            if (parameter.length() >= 3) {
                this.courseOptions[1] = Integer.parseInt(parameter.substring(2, 3));
            }
            if (this.courseOptions[1] > 2 || this.courseOptions[1] < 0) {
                this.courseOptions[1] = 1;
            }
            if (parameter.length() >= 5) {
                this.courseOptions[2] = Integer.parseInt(parameter.substring(4, 5));
            }
            if (this.courseOptions[2] > 2 || this.courseOptions[2] < 0) {
                this.courseOptions[2] = 0;
            }
        }
        while (!this.invalid && hole < 18) {
            if (this.getParameter("hole" + Integer.toString(hole + 1)) != null) {
                final String parameter2 = this.getParameter("hole" + Integer.toString(hole + 1));
                int n = 0;
                int n2 = 0;
                for (int n3 = parameter2.indexOf(",", n2); n3 != -1 && n2 < parameter2.length(); n2 = n3 + 1, n3 = parameter2.indexOf(",", n2), ++n) {
                    if (parameter2.charAt(n2) != ',') {
                        this.course[hole][n] = Integer.parseInt(parameter2.substring(n2, n3));
                    }
                }
                if (n2 < parameter2.length() && parameter2.charAt(n2) != ',') {
                    this.course[hole][n] = Integer.parseInt(parameter2.substring(n2));
                }
            }
            if (this.course[hole][0] == 0 || this.course[hole][1] == 0 || this.course[hole][2] == 0 || this.course[hole][3] == 0 || this.course[hole][4] == 0) {
                this.invalid = true;
                this.hole = hole;
            }
            ++hole;
        }
    }
    
    public void updateOff(final double n, final double n2) {
        if (this.lie != 4 && this.lie != 5 && this.lie != 3 && this.ballPos[0] >= 0.0 && this.ballPos[0] <= 600.0 && this.ballPos[1] >= 0.0 && this.ballPos[1] <= 600.0) {
            this.oldPos[0] = this.ballPos[0];
            this.oldPos[1] = this.ballPos[1];
            this.oldLie = this.lie;
        }
        this.xOff += n;
        this.yOff += n2;
        final double[] ballPos = this.ballPos;
        final int n3 = 0;
        ballPos[n3] -= n;
        final double[] ballPos2 = this.ballPos;
        final int n4 = 1;
        ballPos2[n4] -= n2;
    }
    
    public void run() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.Linx_g = this.getGraphics();
        this.stage = 0;
        this.update(this.Linx_g);
        this.buildCourse();
        switch (this.courseOptions[0]) {
            case 1: {
                this.OK = this.getDocumentBase().toString().equals("http://www.jacksshack.com/dessert.htm");
                break;
            }
            case 2: {
                this.OK = this.getDocumentBase().toString().equals("http://www.jacksshack.com/island.htm");
                break;
            }
            case 3: {
                this.OK = this.getDocumentBase().toString().equals("http://www.jacksshack.com/space.htm");
                break;
            }
            default: {
                this.OK = this.getDocumentBase().toString().equals("http://www.jacksshack.com/country.htm");
                break;
            }
        }
        if (!this.invalid) {
            try {
                while (true) {
                    for (int i = 0; i < 18; ++i) {
                        this.score[i] = 0;
                    }
                    final Random random = new Random();
                    this.cheated = false;
                    while ((this.hole < 18 && this.OK) || (this.hole < 3 && !this.OK)) {
                        this.in = false;
                        this.ballPos[0] = this.course[this.hole][3];
                        this.ballPos[1] = this.course[this.hole][4];
                        this.oldPos[0] = this.ballPos[0];
                        this.oldPos[1] = this.ballPos[1];
                        while (!this.in && !this.mulligan) {
                            this.height = 4;
                            this.stage = 1;
                            this.swing = false;
                            this.power = 0;
                            this.accuracy = 0;
                            this.swing_up = true;
                            if (this.lie == 1) {
                                this.club = 15;
                            }
                            else {
                                this.club = 14;
                            }
                            this.update(this.Linx_g);
                            while (this.distance > this.maxDist[this.club] && this.club > 0) {
                                --this.club;
                            }
                            if (this.lie == 1 && (this.club == 14 || this.club == 13)) {
                                this.club = 15;
                            }
                            if (this.lie == 2) {
                                this.club = 14;
                            }
                            this.updateCursor(this.scale(this.course[this.hole][1]) + 100, this.scale(this.course[this.hole][2]));
                            this.xOff = 150.0 - this.ballPos[0];
                            this.yOff = 150.0 - this.ballPos[1];
                            if (this.courseOptions[1] > 0) {
                                this.wind[0] = random.nextInt() % (this.courseOptions[1] * 20);
                                this.wind[1] = random.nextInt() % (this.courseOptions[1] * 20);
                            }
                            while (!this.swing) {
                                if (this.mulligan) {
                                    break;
                                }
                                this.line = !this.line;
                                this.update(this.Linx_g);
                                this.pause(500);
                            }
                            while (this.swing && this.accuracy >= -4 && !this.mulligan) {
                                while (this.swing_up && this.power < 23) {
                                    ++this.power;
                                    if (this.club < 13) {
                                        this.pause(30);
                                    }
                                    else {
                                        this.pause(70);
                                    }
                                    this.update(this.Linx_g);
                                }
                                this.swing_up = false;
                                if (this.club == 15) {
                                    this.swing = false;
                                    this.accuracy = 0;
                                }
                                else {
                                    this.accuracy = this.power;
                                }
                                while (this.swing && this.accuracy >= -4) {
                                    --this.accuracy;
                                    if (this.power < 21) {
                                        if (this.club < 13) {
                                            this.pause(30);
                                        }
                                        else {
                                            this.pause(70);
                                        }
                                    }
                                    else {
                                        this.pause(10);
                                    }
                                    this.update(this.Linx_g);
                                }
                            }
                            double xoff = this.power / 21.0 * this.maxDist[this.club];
                            if ((this.power > 20 && (this.accuracy < -1 || this.accuracy > 1)) || (this.club < 12 && this.lie == 2 && this.ballPos[0] != this.course[this.hole][3] && this.ballPos[1] != this.course[this.hole][4]) || (this.club < 5 && (this.lie == 0 || this.oldLie == 0) && this.ballPos[0] != this.course[this.hole][3] && this.ballPos[1] != this.course[this.hole][4])) {
                                xoff /= 2.0;
                            }
                            final double n = Math.abs(this.cursor[0] - (this.scale(this.ballPos[0]) + 100));
                            final double n2 = Math.abs(this.cursor[1] - this.scale(this.ballPos[1]));
                            if (n2 == 0.0) {
                                this.xoff = xoff;
                                this.yoff = 0.0;
                            }
                            else {
                                this.yoff = Math.abs(Math.sqrt(xoff * xoff / (n / n2 * (n / n2) + 1.0)));
                                this.xoff = this.yoff * (n / n2);
                            }
                            if (this.cursor[0] > this.scale(this.ballPos[0]) + 100) {
                                this.xoff = -this.xoff;
                            }
                            if (this.cursor[1] > this.scale(this.ballPos[1])) {
                                this.yoff = -this.yoff;
                            }
                            if (this.club != 15) {
                                this.xoff -= this.accuracy;
                                this.yoff -= this.accuracy;
                                if (this.xoff - this.wind[0] > 0.0) {
                                    this.xoff -= this.wind[0];
                                }
                                if (this.yoff - this.wind[1] > 0.0) {
                                    this.yoff -= this.wind[1];
                                }
                            }
                            if (this.courseOptions[0] == 3) {
                                this.xoff *= 2.0;
                                this.yoff *= 2.0;
                            }
                            this.oldPos[0] = this.ballPos[0];
                            this.oldPos[1] = this.ballPos[1];
                            for (int n3 = 0; n3 < 20 && !this.in && this.club != 15 && (this.lie != 3 || this.height > 8 || n3 < 5) && !this.mulligan; ++n3) {
                                this.updateOff(this.xoff / 20.0, this.yoff / 20.0);
                                if (this.club < 12) {
                                    if (n3 % 2 == 0) {
                                        if (n3 < 10) {
                                            ++this.height;
                                        }
                                        else {
                                            --this.height;
                                        }
                                    }
                                }
                                else if (this.club != 15) {
                                    if (n3 < 10) {
                                        ++this.height;
                                    }
                                    else {
                                        --this.height;
                                    }
                                }
                                this.update(this.Linx_g);
                                this.pause(100);
                            }
                            if (this.club < 15 && this.lie < 2 && !this.mulligan) {
                                this.updateOff(this.xoff / 20.0, this.yoff / 20.0);
                                ++this.height;
                                this.update(this.Linx_g);
                                this.pause(400);
                                this.updateOff(this.xoff / 20.0, this.yoff / 20.0);
                                --this.height;
                                this.update(this.Linx_g);
                                this.pause(400);
                            }
                            int n4;
                            if (this.club == 15) {
                                n4 = 23;
                            }
                            else {
                                n4 = 2;
                            }
                            if (this.club > 12) {
                                this.checkIn();
                            }
                            if (this.lie == 3) {
                                if (this.yoff > 0.0) {
                                    this.yoff = -200.0;
                                }
                                else {
                                    this.yoff = 200.0;
                                }
                                this.xoff = 0.0;
                            }
                            if (this.courseOptions[2] == 2) {
                                this.xoff /= 2.0;
                                this.yoff /= 2.0;
                            }
                            while (!this.in && n4 > 0 && (this.lie == 1 || this.lie == 3) && !this.mulligan) {
                                --n4;
                                this.updateOff(this.xoff / 20.0, this.yoff / 20.0);
                                if (n4 < 10 && this.club > 12) {
                                    this.checkIn();
                                }
                                this.update(this.Linx_g);
                                if (this.club != 15 || n4 < 3) {
                                    this.pause(400);
                                }
                                else {
                                    this.pause(100);
                                }
                            }
                            if (this.lie == 3) {
                                this.lie = this.base;
                            }
                            if (this.lie == 4 || this.lie == 5 || this.ballPos[0] < 0.0 || this.ballPos[0] > 600.0 || this.ballPos[1] < 0.0 || this.ballPos[1] > 600.0) {
                                this.messageColor = Color.red;
                                if (this.lie == 4) {
                                    this.message = "Water Hazard, +1";
                                }
                                else if (this.lie == 5) {
                                    this.message = "Out of Orbit, +1";
                                }
                                else {
                                    this.message = "Out of Bounds, +1";
                                }
                                final int[] score = this.score;
                                final int hole = this.hole;
                                ++score[hole];
                                this.update(this.Linx_g);
                                this.pause(1000);
                                this.ballPos[0] = this.oldPos[0];
                                this.ballPos[1] = this.oldPos[1];
                                this.lie = this.oldLie;
                                this.base = this.lie;
                                this.messageColor = Color.black;
                            }
                            final int[] score2 = this.score;
                            final int hole2 = this.hole;
                            ++score2[hole2];
                        }
                        if (this.score[this.hole] > this.course[this.hole][0]) {
                            this.messageColor = Color.red;
                        }
                        if (this.score[this.hole] < this.course[this.hole][0]) {
                            this.messageColor = Color.blue;
                        }
                        if (this.score[this.hole] == this.course[this.hole][0]) {
                            this.messageColor = Color.white;
                        }
                        if (this.score[this.hole] > this.course[this.hole][0] + 2) {
                            this.message = "+" + Integer.toString(this.score[this.hole] - this.course[this.hole][0]);
                        }
                        if (this.score[this.hole] == this.course[this.hole][0] + 2) {
                            this.message = "Double Bogey";
                        }
                        if (this.score[this.hole] == this.course[this.hole][0] + 1) {
                            this.message = "Bogey";
                        }
                        if (this.score[this.hole] == this.course[this.hole][0]) {
                            this.message = "Nice Par";
                        }
                        if (this.score[this.hole] == this.course[this.hole][0] - 1) {
                            this.message = "Birdie";
                        }
                        if (this.score[this.hole] == this.course[this.hole][0] - 2) {
                            this.message = "Eagle!";
                        }
                        if (this.score[this.hole] < this.course[this.hole][0] - 2) {
                            this.message = "Unbelievable...";
                        }
                        if (this.mulligan) {
                            this.messageColor = Color.red;
                            this.message = "Mulligan";
                        }
                        this.update(this.Linx_g);
                        if (this.scoreF.isShowing()) {
                            this.updateScoreCard();
                            this.scoreF.toFront();
                        }
                        else {
                            (this.scoreF = this.createScoreCard()).addWindowListener(this);
                            this.scoreF.show();
                        }
                        if (this.messageColor != Color.black) {
                            this.pause(3000);
                        }
                        this.messageColor = Color.black;
                        if (this.mulligan) {
                            this.score[this.hole] = 0;
                            this.mulligan = false;
                        }
                        else {
                            ++this.hole;
                        }
                    }
                    this.hole = 0;
                    this.stage = 3;
                    if (this.OK) {
                        (this.nameF = this.createNameFrame()).addWindowListener(this);
                        this.nameF.show();
                    }
                    this.again = false;
                    this.update(this.Linx_g);
                    while (!this.again) {}
                    if (this.nameF.isShowing()) {
                        this.nameF.dispose();
                    }
                }
            }
            catch (Exception ex) {
                this.stop();
            }
        }
    }
    
    public void checkIn() {
        if (this.course[this.hole][1] + 100 + (int)this.xOff >= 248 && this.course[this.hole][1] + 100 + (int)this.xOff <= 256 && this.course[this.hole][2] + (int)this.yOff >= 148 && this.course[this.hole][2] + (int)this.yOff <= 156) {
            this.in = true;
        }
    }
    
    public void getLie() {
        int n = 5;
        switch (this.courseOptions[0]) {
            case 1: {
                this.lie = 2;
                break;
            }
            case 2: {
                if (this.courseOptions[2] == 1) {
                    this.lie = 1;
                    break;
                }
                this.lie = 4;
                break;
            }
            case 3: {
                this.lie = 5;
                break;
            }
            default: {
                this.lie = 0;
                break;
            }
        }
        this.base = this.lie;
        while (this.course[this.hole][n] != 0) {
            int n2;
            int n3;
            if (this.course[this.hole][n] == 3) {
                n2 = 20;
                n3 = 20;
            }
            else {
                n2 = this.course[this.hole][n + 3] / 2;
                n3 = this.course[this.hole][n + 4] / 2;
            }
            final double n4 = this.ballPos[0] + this.height / 2 - (this.course[this.hole][n + 1] + n2);
            final double n5 = 600.0 - (this.ballPos[1] + this.height / 2) - (600 - (this.course[this.hole][n + 2] + n3));
            final double n6 = n4 * n4 / (n2 * n2) + n5 * n5 / (n3 * n3);
            if (this.course[this.hole][n] == 3) {
                if (n6 < 1.0 && this.tree) {
                    this.lie = 3;
                }
                n += 3;
            }
            else {
                if (n6 < 1.0) {
                    if (this.course[this.hole][n] == 1) {
                        this.lie = 1;
                    }
                    if (this.course[this.hole][n] == 2 && this.sand) {
                        this.lie = 2;
                    }
                    if (this.course[this.hole][n] == 4 && this.water) {
                        this.lie = 4;
                    }
                    if (this.courseOptions[2] == 1 && this.lie == 4) {
                        this.lie = 1;
                    }
                    this.base = this.lie;
                }
                n += 5;
            }
        }
    }
    
    public void updateCursor(final int n, final int n2) {
        final double n3 = Math.abs(n - (this.scale(this.ballPos[0]) + 100));
        final double n4 = Math.abs(n2 - this.scale(this.ballPos[1]));
        double n5 = 0.0;
        double n6;
        if (n4 < 5.0) {
            n6 = this.maxDist[this.club];
        }
        else {
            n5 = Math.abs((int)Math.sqrt(this.maxDist[this.club] * this.maxDist[this.club] / (n3 / n4 * (n3 / n4) + 1.0)));
            n6 = n5 * (n3 / n4);
        }
        if (n < this.scale(this.ballPos[0]) + 100) {
            n6 = -n6;
        }
        if (n2 < this.scale(this.ballPos[1])) {
            n5 = -n5;
        }
        this.cursor[0] = this.scale(this.ballPos[0] + n6) + 100;
        this.cursor[1] = this.scale(this.ballPos[1] + n5);
    }
    
    public int scale(final double n) {
        return (int)(n / 2.0);
    }
    
    public String setCode(final String s) {
        String s2 = "Invalid Code - Try Again";
        final String lowerCase = s.toLowerCase();
        if (lowerCase.equals("trip")) {
            this.trip = !this.trip;
            if (this.trip) {
                s2 = "I'm Getting High";
            }
            else {
                s2 = "I'm Coming Down";
            }
        }
        if (lowerCase.equals("smile")) {
            this.smiley = !this.smiley;
            if (this.smiley) {
                s2 = "Happy Days";
            }
            else {
                s2 = "Boo Hoo Hoo";
            }
        }
        if (lowerCase.equals("no trees")) {
            this.tree = !this.tree;
            if (this.tree) {
                s2 = "Trees On";
            }
            else {
                s2 = "Trees Off";
            }
        }
        if (lowerCase.equals("no sand")) {
            this.sand = !this.sand;
            if (this.sand) {
                s2 = "Sand On";
            }
            else {
                s2 = "Sand Off";
            }
        }
        if (lowerCase.equals("no water")) {
            this.water = !this.water;
            if (this.water) {
                s2 = "Water On";
            }
            else {
                s2 = "Water Off";
            }
        }
        if (lowerCase.equals("lights off")) {
            this.night = !this.night;
            if (this.night) {
                s2 = "Good Night";
            }
            else {
                s2 = "Good Morning";
            }
        }
        if (lowerCase.equals("mulligan")) {
            this.mulligan = true;
            this.cheated = true;
            s2 = "Do Over";
        }
        return s2;
    }
    
    public void stop() {
        if (this.Linx_t != null) {
            this.Linx_t = null;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 100 && this.stage == 1) {
            this.updateCursor(x, y);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.again = true;
        if (x < 100) {
            if (y >= 195 && y <= 280) {
                if (!this.sand || !this.tree || !this.water) {
                    this.cheated = true;
                }
                this.stage = 2;
                if (this.swing_up && this.swing) {
                    this.swing_up = !this.swing_up;
                }
                else {
                    this.swing = !this.swing;
                }
            }
            if (!this.swing && y > 95 && y < 175) {
                if (y > 95 && y < 115) {
                    this.club = (x - 5) / 20;
                }
                if (y > 135 && y < 155 && x > 10) {
                    this.club = (x - 10) / 10 + 5;
                }
                if (y > 155 && y < 175) {
                    if (x > 15 && x < 45) {
                        this.club = 13;
                    }
                    if (x > 45 && x < 75) {
                        this.club = 14;
                    }
                    if (x > 75 && x < 85) {
                        this.club = 15;
                    }
                }
                this.updateCursor(this.cursor[0], this.cursor[1]);
            }
            if (!this.swing && y > 280) {
                if (this.codeF.isShowing()) {
                    this.codeF.dispose();
                }
                else {
                    (this.codeF = this.createCodeFrame()).addWindowListener(this);
                    this.codeF.show();
                }
            }
        }
        else if (this.stage == 1) {
            this.updateCursor(x, y);
        }
        this.update(this.Linx_g);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getWindow() == this.scoreF) {
            this.scoreF.dispose();
        }
        if (windowEvent.getWindow() == this.codeF) {
            this.codeF.dispose();
        }
        if (windowEvent.getWindow() == this.nameF) {
            this.nameF.dispose();
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("enter")) {
            final String setCode = this.setCode(((TextField)this.codeF.getComponent(0)).getText());
            if (setCode.equals("Invalid Code - Try Again")) {
                this.changeCodeFrame(setCode, Color.red);
            }
            else {
                this.changeCodeFrame(setCode, Color.green);
            }
        }
        if (actionEvent.getActionCommand().equals("OK")) {
            this.addScore(((TextField)this.nameF.getComponent(0)).getText());
            this.nameF.dispose();
        }
        this.update(this.Linx_g);
    }
    
    public void addScore(String substring) {
        if (substring.length() > 20) {
            substring = substring.substring(0, 20);
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 18; ++i) {
            n2 += this.course[i][0];
            n += this.score[i];
        }
        String s = null;
        switch (this.courseOptions[0]) {
            case 1: {
                s = new String("http://www.jacksshack.com/perl/dessert.cgi?score=" + Integer.toString(n - n2) + "&name=" + substring);
                break;
            }
            case 2: {
                s = new String("http://www.jacksshack.com/perl/island.cgi?score=" + Integer.toString(n - n2) + "&name=" + substring);
                break;
            }
            case 3: {
                s = new String("http://www.jacksshack.com/perl/space.cgi?score=" + Integer.toString(n - n2) + "&name=" + substring);
                break;
            }
            default: {
                s = new String("http://www.jacksshack.com/perl/country.cgi?score=" + Integer.toString(n - n2) + "&name=" + substring);
                break;
            }
        }
        final AppletContext appletContext = this.getAppletContext();
        try {
            appletContext.showDocument(new URL(s), "_self");
        }
        catch (Exception ex) {}
    }
}
