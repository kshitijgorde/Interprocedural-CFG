// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;

public class CStatusPanel
{
    protected Graphics background;
    protected Image backgroundImage;
    private Random randomGenerator;
    
    public CStatusPanel() {
        this.background = null;
        this.backgroundImage = null;
    }
    
    public void paintTextString(final Graphics g, final String s, final int y, final FontMetrics fm, final Color c) {
        g.setColor(Color.darkGray);
        g.drawString(s, (200 - fm.stringWidth(s)) / 2, y);
        g.setColor(c);
        g.drawString(s, (200 - fm.stringWidth(s)) / 2, y);
    }
    
    public void paintIntroStatus(final Applet ap, final CGraphicsConstants gfxElements, final Graphics g) {
        this.randomGenerator = new Random();
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        for (int i = 0; i <= 450 / gfxElements.trackTextureImage[9].getHeight(ap); ++i) {
            for (int k = 0; k <= 200 / gfxElements.trackTextureImage[9].getWidth(ap); ++k) {
                g.drawImage(gfxElements.trackTextureImage[9], k * gfxElements.trackTextureImage[9].getWidth(ap), i * gfxElements.trackTextureImage[9].getHeight(ap), ap);
            }
        }
        FontMetrics fm = ap.getFontMetrics(CGraphicsConstants.FONT_BIGMONOBOLD);
        int i = this.randomGenerator.nextInt(3);
        int k = this.randomGenerator.nextInt(3);
        g.setColor(Color.orange);
        g.drawString("JRacers", (200 - fm.stringWidth("JRacers")) / 2 + i, fm.getHeight() / 2 + 5 + k);
        g.setColor(Color.yellow);
        g.drawString("JRacers", (200 - fm.stringWidth("JRacers")) / 2 - 1 + i, fm.getHeight() / 2 - 1 + 5 + k);
        final int secondsInIntro = gfxElements.frameNumber / 30;
        g.setColor(Color.darkGray);
        g.drawLine(10, 30, 190, 30);
        fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
        g.setFont(CGraphicsConstants.FONT_MONO);
        if (secondsInIntro < 4) {
            this.paintTextString(g, "Click on the Applet", 45, fm, Color.green);
            this.paintTextString(g, "to activate JRacers.", 60, fm, Color.green);
        }
        else if (secondsInIntro < 8) {
            this.paintTextString(g, "Steer your convertible", 45, fm, Color.yellow);
            this.paintTextString(g, "with the cursor keys.", 60, fm, Color.yellow);
        }
        else if (secondsInIntro < 12) {
            this.paintTextString(g, "During play, press P", 45, fm, Color.green);
            this.paintTextString(g, "to Pause the game.", 60, fm, Color.green);
        }
        else if (secondsInIntro < 16) {
            this.paintTextString(g, "During play, press S", 45, fm, Color.green);
            this.paintTextString(g, "to turn sound on/off.", 60, fm, Color.green);
        }
        else {
            this.paintTextString(g, "Press R to start JRacers", 45, fm, Color.white);
            this.paintTextString(g, "or to restart a game.", 60, fm, Color.white);
        }
        if (secondsInIntro > 1) {
            g.setColor(Color.darkGray);
            g.drawLine(10, 70, 190, 70);
            final int w = gfxElements.canisterImage.getWidth(ap);
            final int h = gfxElements.canisterImage.getHeight(ap);
            for (i = 0; i < 5; ++i) {
                g.drawImage(gfxElements.canisterImage, 30 + i * 30, 75, 50 + i * 30, 95, 0, 0, w, h, ap);
            }
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "Pick up canisters", 110, fm, Color.yellow);
            this.paintTextString(g, "to refuel your car.", 125, fm, Color.yellow);
        }
        if (secondsInIntro > 3) {
            g.setColor(Color.darkGray);
            g.drawLine(10, 135, 190, 135);
            final int w = gfxElements.tiresImage.getWidth(ap);
            final int h = gfxElements.tiresImage.getHeight(ap);
            for (i = 0; i < 5; ++i) {
                g.drawImage(gfxElements.tiresImage, 30 + i * 30, 140, 50 + i * 30, 160, 0, 0, w, h, ap);
            }
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "Pick up tire piles to", 175, fm, Color.yellow);
            this.paintTextString(g, "replace your tires.", 190, fm, Color.yellow);
        }
        if (secondsInIntro > 5) {
            g.setColor(Color.darkGray);
            g.drawLine(10, 200, 190, 200);
            final int w = gfxElements.clockImage.getWidth(ap);
            final int h = gfxElements.clockImage.getHeight(ap);
            for (i = 0; i < 5; ++i) {
                g.drawImage(gfxElements.clockImage, 30 + i * 30, 205, 50 + i * 30, 225, 0, 0, w, h, ap);
            }
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "Clocks extend the time", 240, fm, Color.yellow);
            this.paintTextString(g, "limit by 5 seconds.", 255, fm, Color.yellow);
        }
        if (secondsInIntro > 7) {
            g.setColor(Color.darkGray);
            g.drawLine(10, 265, 190, 265);
            final int w = gfxElements.boostImage.getWidth(ap);
            final int h = gfxElements.boostImage.getHeight(ap);
            for (i = 0; i < 5; ++i) {
                g.drawImage(gfxElements.boostImage, 30 + i * 30, 270, 50 + i * 30, 290, 0, 0, w, h, ap);
            }
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "Picking up a boost symbol", 305, fm, Color.yellow);
            this.paintTextString(g, "increases your speed.", 320, fm, Color.yellow);
        }
        if (secondsInIntro > 9) {
            g.setColor(Color.darkGray);
            g.drawLine(10, 330, 190, 330);
            final int w = gfxElements.manholeImage.getWidth(ap);
            final int h = gfxElements.manholeImage.getHeight(ap);
            for (i = 0; i < 5; ++i) {
                g.drawImage(gfxElements.manholeImage, 30 + i * 30, 335, 50 + i * 30, 355, 0, 0, w, h, ap);
            }
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "Avoid manholes. Manholes", 370, fm, Color.yellow);
            this.paintTextString(g, "damage your tires.", 385, fm, Color.yellow);
        }
        if (secondsInIntro > 11) {
            fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
            g.setFont(CGraphicsConstants.FONT_MONO);
            this.paintTextString(g, "JRacers (c) 2003", 410, fm, Color.white);
            this.paintTextString(g, "Tobias Eckert", 425, fm, Color.white);
            this.paintTextString(g, "www.LunchBreakGames.com", 440, fm, Color.white);
        }
        if (secondsInIntro > 20) {
            gfxElements.frameNumber = 0;
        }
    }
    
    public void drawStatusPanel(final Applet ap, final CGraphicsConstants gfxElements, final Graphics g, final CRaceCar car, final CRaceTrack track, final int remainingCanisters, final int canisterDelay) {
        g.drawImage(this.backgroundImage, 0, 0, Color.black, ap);
        if (car != null) {
            g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
            g.setColor(Color.yellow);
            g.drawString(String.valueOf(car.getSpeed()), 100, 50);
            g.drawString(String.valueOf((car.getRemainingTime() + 30 - 1) / 30), 100, 82);
            FontMetrics fm = ap.getFontMetrics(CGraphicsConstants.FONT_BIGMONOBOLD);
            g.drawString(String.valueOf(car.inLap()), 152 - fm.stringWidth(String.valueOf(car.inLap())), 360);
            String scoreString = new String(String.valueOf(car.getTotalScore()));
            for (int i = String.valueOf(car.getTotalScore()).length(); i < 6; ++i) {
                scoreString = "0".concat(String.valueOf(String.valueOf(scoreString)));
            }
            g.drawString(scoreString, 80, 327);
            int w = (int)(150 * car.getBoostCounter() / 300.0);
            g.setColor(Color.black);
            g.fillRect(10 + w, 100, 150 - w, 10);
            g.setColor(Color.black);
            w = remainingCanisters - 5;
            if (w < 0) {
                w = 0;
            }
            if (w > 5) {
                w = 5;
            }
            int h;
            if (remainingCanisters > 5) {
                h = 5;
            }
            else {
                h = remainingCanisters;
            }
            g.drawImage(gfxElements.trackTextureImage[9], 10 + h * 30, 130, 150, 150, 0, 0, gfxElements.trackTextureImage[9].getWidth(ap), gfxElements.trackTextureImage[9].getHeight(ap), ap);
            g.drawImage(gfxElements.trackTextureImage[9], 10 + w * 30, 160, 150, 180, 0, 0, gfxElements.trackTextureImage[9].getWidth(ap), gfxElements.trackTextureImage[9].getHeight(ap), ap);
            if (remainingCanisters == 0 && canisterDelay > 0) {
                g.setFont(CGraphicsConstants.FONT_BIGTIME);
                w = (int)(5.0 * (canisterDelay / 150.0)) + 1;
                final int i = this.randomGenerator.nextInt(5);
                final int k = this.randomGenerator.nextInt(5);
                fm = ap.getFontMetrics(CGraphicsConstants.FONT_BIGTIME);
                g.setColor(Color.green);
                g.drawString(String.valueOf(w), (200 - fm.stringWidth(String.valueOf(w))) / 2 + i - 20, 170 + k);
                g.setColor(Color.yellow);
                g.drawString(String.valueOf(w), (200 - fm.stringWidth(String.valueOf(w))) / 2 + i - 2 - 20, 170 + k - 2);
            }
            if (car.giveTireStatus(0) > 100.0 * 0.2 || gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                final int paintFactor = (int)(255.0 * (1 - car.giveTireStatus(0) / 100.0));
                g.setColor(new Color(paintFactor, paintFactor / 5, paintFactor / 5));
                g.fillRect(42, 204, 8, 20);
            }
            if (car.giveTireStatus(2) > 100.0 * 0.2 || gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                final int paintFactor = (int)(255.0 * (1 - car.giveTireStatus(2) / 100.0));
                g.setColor(new Color(paintFactor, paintFactor / 5, paintFactor / 5));
                g.fillRect(110, 204, 8, 20);
            }
            if (car.giveTireStatus(1) > 100.0 * 0.2 || gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                final int paintFactor = (int)(255.0 * (1 - car.giveTireStatus(1) / 100.0));
                g.setColor(new Color(paintFactor, paintFactor / 5, paintFactor / 5));
                g.fillRect(42, 276, 8, 20);
            }
            if (car.giveTireStatus(3) > 100.0 * 0.2 || gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                final int paintFactor = (int)(255.0 * (1 - car.giveTireStatus(3) / 100.0));
                g.setColor(new Color(paintFactor, paintFactor / 5, paintFactor / 5));
                g.fillRect(110, 276, 8, 20);
            }
            final int fuelDrawOffset = (int)((1 - car.fuel / 2000.0) * 400);
            final int fuelHeight = 400 - fuelDrawOffset;
            g.setColor(Color.darkGray);
            g.fillRect(169, 19, 12, 402);
            if (car.fuel > 2000 * 0.1 || gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                if (car.fuel > 2000 * 0.1) {
                    g.setColor(Color.yellow);
                }
                else {
                    g.setColor(Color.orange);
                }
                g.fillRect(170, 20 + fuelDrawOffset, 2, fuelHeight);
                if (car.fuel > 2000 * 0.1) {
                    g.setColor(Color.white);
                }
                else {
                    g.setColor(Color.orange);
                }
                g.fillRect(172, 20 + fuelDrawOffset, 1, fuelHeight);
                if (car.fuel > 2000 * 0.1) {
                    g.setColor(Color.yellow);
                }
                else {
                    g.setColor(Color.red);
                }
                g.fillRect(173, 20 + fuelDrawOffset, 2, fuelHeight);
                if (car.fuel > 2000 * 0.1) {
                    g.setColor(Color.orange);
                }
                else {
                    g.setColor(Color.red);
                }
                g.fillRect(175, 20 + fuelDrawOffset, 5, fuelHeight);
            }
        }
    }
    
    public void paintStatusBackground(final Applet ap, final CGraphicsConstants gfxElements, final Graphics g, final Image image) {
        this.randomGenerator = new Random();
        this.background = g;
        this.backgroundImage = image;
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        for (int i = 0; i <= 450 / gfxElements.trackTextureImage[9].getHeight(ap); ++i) {
            for (int k = 0; k <= 200 / gfxElements.trackTextureImage[9].getWidth(ap); ++k) {
                g.drawImage(gfxElements.trackTextureImage[9], k * gfxElements.trackTextureImage[9].getWidth(ap), i * gfxElements.trackTextureImage[9].getHeight(ap), ap);
            }
        }
        FontMetrics fm = ap.getFontMetrics(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.orange);
        g.drawString("JRacers", (160 - fm.stringWidth("JRacers")) / 2, fm.getHeight() / 2 + 5);
        g.setColor(Color.yellow);
        g.drawString("JRacers", (160 - fm.stringWidth("JRacers")) / 2 - 1, fm.getHeight() / 2 - 1 + 5);
        g.setColor(Color.darkGray);
        g.drawLine(10, 28, 150, 28);
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.green);
        g.drawString("SPEED: ", 10, 50);
        g.setColor(Color.darkGray);
        g.drawLine(10, 60, 150, 60);
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.green);
        g.drawString("TIME: ", 10, 82);
        g.setColor(Color.darkGray);
        g.drawLine(10, 92, 150, 92);
        for (int i = 0; i < 10; ++i) {
            g.drawImage(gfxElements.trackTextureImage[6], 10 + i * 15, 100, 20 + i * 15, 110, 0, 0, 10, 10, ap);
        }
        g.setColor(Color.darkGray);
        g.drawLine(10, 120, 150, 120);
        final int w = gfxElements.canisterImage.getWidth(ap);
        final int h = gfxElements.canisterImage.getHeight(ap);
        for (int i = 0; i < 5; ++i) {
            g.drawImage(gfxElements.canisterImage, 10 + i * 30, 130, 30 + i * 30, 150, 0, 0, w, h, ap);
            g.drawImage(gfxElements.canisterImage, 10 + i * 30, 160, 30 + i * 30, 180, 0, 0, w, h, ap);
        }
        g.setColor(Color.darkGray);
        g.drawLine(10, 190, 150, 190);
        g.drawImage(gfxElements.carImage, 55, 200, 105, 300, 0, 0, gfxElements.carImage.getWidth(ap), gfxElements.carImage.getHeight(ap), ap);
        g.setColor(Color.darkGray);
        g.fillRect(40, 202, 12, 24);
        g.fillRect(108, 202, 12, 24);
        g.fillRect(40, 274, 12, 24);
        g.fillRect(108, 274, 12, 24);
        g.setColor(Color.darkGray);
        g.drawLine(10, 305, 150, 305);
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.green);
        g.drawString("SCORE", 10, 327);
        g.setColor(Color.darkGray);
        g.drawLine(10, 337, 150, 337);
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.green);
        g.drawString("LAP: ", 10, 360);
        g.setColor(Color.darkGray);
        g.drawLine(10, 370, 150, 370);
        fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONO);
        g.setFont(CGraphicsConstants.FONT_MONO);
        g.setColor(Color.darkGray);
        g.drawString("JRacers (c) 2003", (160 - fm.stringWidth("JRacers (c) 2003")) / 2, 397);
        g.setColor(Color.white);
        g.drawString("JRacers (c) 2003", (160 - fm.stringWidth("JRacers (c) 2003")) / 2, 395);
        g.setColor(Color.darkGray);
        g.drawString("Brought to you by", (160 - fm.stringWidth("Brought to you by")) / 2, 412);
        g.setColor(Color.white);
        g.drawString("Brought to you by", (160 - fm.stringWidth("Brought to you by")) / 2, 410);
        g.setColor(Color.darkGray);
        g.drawString("LunchBreakGames.com", (160 - fm.stringWidth("LunchBreakGames.com")) / 2, 427);
        g.setColor(Color.white);
        g.drawString("LunchBreakGames.com", (160 - fm.stringWidth("LunchBreakGames.com")) / 2, 425);
        g.setFont(CGraphicsConstants.FONT_MONOBOLD);
        fm = ap.getFontMetrics(CGraphicsConstants.FONT_MONOBOLD);
        g.setColor(Color.red);
        g.drawString("F", 166 - fm.stringWidth("F"), 20 + fm.getHeight() / 2);
        g.drawString("E", 166 - fm.stringWidth("E"), 410 + fm.getHeight() / 2);
        g.drawString("FUEL", 175 - fm.stringWidth("FUEL") / 2, 425 + fm.getHeight() / 2);
        g.setColor(Color.darkGray);
        g.fillRect(169, 19, 12, 402);
    }
}
