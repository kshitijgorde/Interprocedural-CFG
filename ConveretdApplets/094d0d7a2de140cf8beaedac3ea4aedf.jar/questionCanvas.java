import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class questionCanvas extends Canvas
{
    Image offS;
    Graphics offscreen;
    boolean first;
    
    public void initial() {
        this.offS = this.createImage(thetriv.w, thetriv.h);
        this.offscreen = this.offS.getGraphics();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.first) {
            this.initial();
            this.first = false;
        }
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            graphics.drawImage(this.offS, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        final int w = thetriv.w;
        final int h = thetriv.h;
        final Graphics graphics2 = thetriv.bufImage.getGraphics();
        final Font font = new Font("Ariel", 1, 11);
        final Font font2 = new Font("Ariel", 1, 14);
        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
        graphics2.setColor(Color.black);
        graphics2.setFont(font);
        graphics2.drawImage(thetriv.aPicture, 0, 0, w, h, this);
        if (!thetriv.trueReg && thetriv.qTotal > 5) {
            final String s = "This version can't have more than five questions";
            final String s2 = "For registration info e-mail blade25@worldnet.att.net";
            final String s3 = "With registration you gain several options:";
            final String s4 = "1: Customize Text Color, Background Color, timer   ";
            final String s5 = "speed, Opening Title, and your own scoring messages";
            final String s6 = "2: You can have unlimited trivia questions";
            final String s7 = "3: You get the unregistered stamp removed";
            graphics2.drawString(s, (w - fontMetrics.stringWidth(s)) / 2, 10);
            graphics2.drawString(s2, (w - fontMetrics.stringWidth(s2)) / 2, 20);
            graphics2.drawString(s3, (w - fontMetrics.stringWidth(s3)) / 2, 40);
            graphics2.drawString(s4, (w - fontMetrics.stringWidth(s4)) / 2, 60);
            graphics2.drawString(s5, (w - fontMetrics.stringWidth(s5)) / 2, 70);
            graphics2.drawString(s6, (w - fontMetrics.stringWidth(s6)) / 2, 90);
            graphics2.drawString(s7, (w - fontMetrics.stringWidth(s7)) / 2, 110);
            graphics.drawImage(thetriv.bufImage, 0, 0, null);
            return;
        }
        if (thetriv.inPlay && !thetriv.start) {
            if (fontMetrics.stringWidth(thetriv.currentQ) > w) {
                final String currentQ = thetriv.currentQ;
                String substring = "";
                String s8 = "";
                boolean b = false;
                int n = 0;
                while (!b) {
                    final String substring2 = currentQ.substring(0, n + 1);
                    if (substring2.substring(n, n + 1).equals(" ") || n + 1 == currentQ.length()) {
                        if (fontMetrics.stringWidth(substring2) > w || currentQ.length() == n + 1) {
                            b = true;
                        }
                        else {
                            substring = currentQ.substring(n + 1, currentQ.length());
                            s8 = substring2;
                        }
                    }
                    ++n;
                }
                if (fontMetrics.stringWidth(substring) > w) {
                    boolean b2 = false;
                    String s9 = "";
                    String substring3 = "";
                    int n2 = 0;
                    while (!b2) {
                        final String substring4 = substring.substring(0, n2 + 1);
                        if (substring4.substring(n2, n2 + 1).equals(" ") || n2 + 1 == substring.length()) {
                            if (fontMetrics.stringWidth(substring4) > w || substring.length() == n2 + 1) {
                                b2 = true;
                            }
                            else {
                                substring3 = substring.substring(n2 + 1, substring.length());
                                s9 = substring4;
                            }
                        }
                        ++n2;
                    }
                    graphics2.drawString(s8, 1, 10);
                    graphics2.drawString(s9, 1, 19);
                    graphics2.drawString(substring3, 1, 28);
                }
                else {
                    graphics2.drawString(s8, 1, 12);
                    graphics2.drawString(substring, 1, 24);
                }
            }
            else {
                final String currentQ2 = thetriv.currentQ;
                graphics2.drawString(currentQ2, (w - fontMetrics.stringWidth(currentQ2)) / 2, 20);
            }
            final trivQuestion trivQuestion = thetriv.questionSet.elementAt(thetriv.i);
            graphics2.setFont(font2);
            graphics2.drawString(trivQuestion.getAnswer(0), 30, 53);
            graphics2.drawString(trivQuestion.getAnswer(1), 30, 73);
            graphics2.drawString(trivQuestion.getAnswer(2), 30, 93);
            graphics2.drawString(trivQuestion.getAnswer(3), 30, 113);
            graphics2.setColor(Color.blue);
            graphics2.fill3DRect(0, 30, w, 5, true);
            graphics2.fill3DRect(10, 40, 15, 15, true);
            graphics2.fill3DRect(10, 60, 15, 15, true);
            graphics2.fill3DRect(10, 80, 15, 15, true);
            graphics2.fill3DRect(10, 100, 15, 15, true);
            graphics2.setColor(Color.red);
            final int currentAnswer = thetriv.currentAnswer;
            final int n3 = 10;
            final int n4 = 45 + 20 * (currentAnswer - 1);
            final int n5 = 17;
            final int n6 = 55 + 20 * (currentAnswer - 1);
            final int n7 = 25;
            final int n8 = 33 + 20 * (currentAnswer - 1);
            if (currentAnswer > 0) {
                graphics2.drawLine(n3, n4, n5, n6);
                graphics2.drawLine(n5, n6, n7, n8);
            }
        }
        else if (!thetriv.start) {
            final float n9 = thetriv.qCorrect / thetriv.qTotal * 100.0f;
            String s10 = "";
            final String string = "YOU SCORED " + n9 + "% CORRECT";
            if (n9 < 50.0f) {
                s10 = "YOU DEFINITLY NEED WORK";
            }
            else if (n9 < 70.0f) {
                s10 = "YOU GOT LUCKY ON A FEW, ANYWAY";
            }
            else if (n9 < 90.0f) {
                s10 = "NOT BAD, BUT YOU CAN DO BETTER";
            }
            else if (n9 < 100.0f) {
                s10 = "GREAT SCORE, BUT NOT PERFECT :)";
            }
            else if (n9 == 100.0f) {
                s10 = "PERFECT SCORE!  :)";
            }
            graphics2.drawString(s10, (w - fontMetrics.stringWidth(s10)) / 2, 15);
            graphics2.drawString(string, (w - fontMetrics.stringWidth(string)) / 2, 30);
        }
        if (thetriv.start) {
            final int introInc = thetriv.introInc;
            if (introInc < 25) {
                final Font font3 = new Font("Ariel", 1, introInc);
                graphics2.setFont(font3);
                final FontMetrics fontMetrics2 = graphics2.getFontMetrics(font3);
                final String introText = thetriv.introText;
                graphics2.drawString(introText, (w - fontMetrics2.stringWidth(introText)) / 2, h / 2);
            }
            else if (introInc < 40) {
                final Font font4 = new Font("Ariel", 1, 30);
                graphics2.setFont(font4);
                final FontMetrics fontMetrics3 = graphics2.getFontMetrics(font4);
                final String introText2 = thetriv.introText;
                graphics2.drawString(introText2, (w - fontMetrics3.stringWidth(introText2)) / 2, h / 2);
                final Font font5 = new Font("Ariel", 1, introInc - 24);
                graphics2.setFont(font5);
                final FontMetrics fontMetrics4 = graphics2.getFontMetrics(font5);
                final String copyRight = thetriv.copyRight;
                graphics2.drawString(copyRight, (w - fontMetrics4.stringWidth(copyRight)) / 2, h / 2 + 35);
            }
            else if (introInc >= 40) {
                final Font font6 = new Font("Ariel", 1, 30);
                graphics2.setFont(font6);
                final FontMetrics fontMetrics5 = graphics2.getFontMetrics(font6);
                final String introText3 = thetriv.introText;
                graphics2.drawString(introText3, (w - fontMetrics5.stringWidth(introText3)) / 2, h / 2);
                final Font font7 = new Font("Ariel", 1, 15);
                graphics2.setFont(font7);
                final FontMetrics fontMetrics6 = graphics2.getFontMetrics(font7);
                final String copyRight2 = thetriv.copyRight;
                graphics2.drawString(copyRight2, (w - fontMetrics6.stringWidth(copyRight2)) / 2, h / 2 + 35);
                if (!thetriv.trueReg) {
                    final String s11 = "UNREGISTERED FREE VERSION";
                    final FontMetrics fontMetrics7 = graphics2.getFontMetrics(font);
                    graphics2.setFont(font);
                    graphics2.setColor(Color.red);
                    graphics2.drawString(s11, (w - fontMetrics7.stringWidth(s11)) / 2, h / 2 + 48);
                }
            }
        }
        graphics.drawImage(thetriv.bufImage, 0, 0, null);
    }
    
    questionCanvas() {
        this.first = true;
    }
}
