import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class Help
{
    static Font infoFont;
    static Font linkFont;
    static Color infoColor;
    static Color linkColor;
    public boolean visible;
    Image infoImg;
    Iridium game;
    int page;
    int aWidth;
    int aHeight;
    int lastpage;
    
    public Help(final int aWidth, final int aHeight, final Image infoImg, final Iridium game) {
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.infoImg = infoImg;
        this.game = game;
        this.page = 0;
    }
    
    public void show(final int page) {
        this.lastpage = this.page;
        this.page = page;
        this.visible = true;
    }
    
    public void hide() {
        this.visible = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.visible) {
            graphics.setColor(Color.darkGray);
            graphics.fillRect(25, 25, this.aWidth - 50, this.aHeight - 50);
            graphics.setColor(Color.gray);
            graphics.drawRect(25, 25, this.aWidth - 50, this.aHeight - 50);
            graphics.clipRect(27, 27, this.aWidth - 54, this.aHeight - 54);
            graphics.drawImage(this.infoImg, 27, 27, this.game);
            graphics.setFont(Help.infoFont);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (this.page == 0) {
                graphics.setColor(Color.green);
                final String s = "(Click mouse to continue)";
                graphics.drawString(s, (this.aWidth - fontMetrics.stringWidth(s)) / 2, this.aHeight - 30);
                graphics.setColor(Help.infoColor);
                final String s2 = "I R I D I U M";
                graphics.drawString(s2, (this.aWidth - fontMetrics.stringWidth(s2)) / 2, 50);
                graphics.drawString("Author: Daniel Ramsk\u00f6ld, Sweden", 30, 62);
                graphics.drawString("Last Modified: 20 June 2000", 30, 74);
                graphics.drawString("Keys:", 30, 98);
                graphics.drawString("4 or A:", 35, 110);
                graphics.drawString("Move ship left", 120, 110);
                graphics.drawString("5 or S:", 35, 122);
                graphics.drawString("Move ship down", 120, 122);
                graphics.drawString("6 or D:", 35, 134);
                graphics.drawString("Move ship right", 120, 134);
                graphics.drawString("8 or W:", 35, 146);
                graphics.drawString("Move ship up", 120, 146);
                graphics.drawString("Mouse-Move:", 35, 158);
                graphics.drawString("Move crosshair", 120, 158);
                graphics.drawString("Left Mouse-Click", 35, 170);
                graphics.drawString("or SPACE:", 35, 182);
                graphics.drawString("Fire lasers", 120, 182);
                graphics.drawString("P:", 35, 194);
                graphics.drawString("Pause/Resume game", 120, 194);
                graphics.drawString("M:", 35, 206);
                graphics.drawString("Mute/Play Sounds", 120, 206);
                graphics.drawString("B:", 35, 218);
                graphics.drawString("Show/Hide Shield-Bars", 120, 218);
                graphics.drawString("F2:", 35, 230);
                graphics.drawString("Restart game", 120, 230);
                graphics.drawString("F12:", 35, 242);
                graphics.drawString("Go to next level (cheat)", 120, 242);
                graphics.drawString("Further Help:", 60, 266);
                graphics.setColor(Help.linkColor);
                graphics.setFont(Help.linkFont);
                graphics.drawString("The Mission", 60, 278);
                graphics.drawString("Scoring", 60, 290);
                graphics.drawString("Enemies", 60, 302);
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                graphics.drawString("Click on a blue link to go to another topic", 30, 338);
                return;
            }
            graphics.setColor(Help.linkColor);
            graphics.setFont(Help.linkFont);
            graphics.drawString("Back", 30, 62);
            if (this.page == 1) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s3 = "SCORING";
                graphics.drawString(s3, (this.aWidth - fontMetrics.stringWidth(s3)) / 2, 50);
                graphics.drawString("Scores:", 30, 74);
                graphics.drawString("Asteroids:", 35, 86);
                graphics.drawString("Shot at asteroid:", 40, 98);
                graphics.drawString("4 points", Math.max(this.aWidth / 2, 150), 98);
                graphics.drawString("Crashing an asteroid:", 40, 110);
                graphics.drawString("50 points", Math.max(this.aWidth / 2, 150), 110);
                graphics.drawString("Enemy vessels:", 35, 122);
                graphics.drawString("Shot at enemy:", 40, 134);
                graphics.drawString("4 points", Math.max(this.aWidth / 2, 150), 134);
                graphics.drawString("Crashing an interceptor:", 40, 146);
                graphics.drawString("100 points", Math.max(this.aWidth / 2, 150), 146);
                graphics.drawString("Crashing a heavy fighter:", 40, 158);
                graphics.drawString("350 points", Math.max(this.aWidth / 2, 150), 158);
                graphics.drawString("Crashing a zephi:", 40, 170);
                graphics.drawString("50 points", Math.max(this.aWidth / 2, 150), 170);
                graphics.drawString("Bonuses:", 35, 182);
                graphics.drawString("Completing a level:", 40, 194);
                graphics.drawString("100 - 300 points", Math.max(this.aWidth / 2, 150), 194);
                graphics.drawString("Saving Irue:", 40, 206);
                graphics.drawString("1000 - 7000 points", Math.max(this.aWidth / 2, 150), 206);
                graphics.drawString("Costs:", 30, 218);
                graphics.drawString("Fire lasers:", 40, 230);
                graphics.drawString("1 point", Math.max(this.aWidth / 2, 150), 230);
                return;
            }
            if (this.page == 2) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s4 = "TABLE OF ENEMIES";
                graphics.drawString(s4, (this.aWidth - fontMetrics.stringWidth(s4)) / 2, 50);
                graphics.drawString("Enemies:", 30, 74);
                graphics.drawString("Level first seen at:", Math.max(this.aWidth / 2, 150), 74);
                graphics.setColor(Help.linkColor);
                graphics.setFont(Help.linkFont);
                graphics.drawString("Asteroid", 35, 86);
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                graphics.drawString("Level 1", Math.max(this.aWidth / 2, 150) + 5, 86);
                graphics.setColor(Help.linkColor);
                graphics.setFont(Help.linkFont);
                graphics.drawString("Interceptor", 35, 98);
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                graphics.drawString("Level 2", Math.max(this.aWidth / 2, 150) + 5, 98);
                graphics.setColor(Help.linkColor);
                graphics.setFont(Help.linkFont);
                graphics.drawString("Heavy Fighter", 35, 110);
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                graphics.drawString("Level 6", Math.max(this.aWidth / 2, 150) + 5, 110);
                graphics.setColor(Help.linkColor);
                graphics.setFont(Help.linkFont);
                graphics.drawString("Zephi", 35, 122);
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                graphics.drawString("Level 10 (Irue)", Math.max(this.aWidth / 2, 150) + 5, 122);
                return;
            }
            if (this.page == 10) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Color.green);
                final String s5 = "(Click mouse to continue)";
                graphics.drawString(s5, (this.aWidth - fontMetrics.stringWidth(s5)) / 2, this.aHeight - 30);
                graphics.setColor(Help.infoColor);
                final String s6 = "THE MISSION";
                graphics.drawString(s6, (this.aWidth - fontMetrics.stringWidth(s6)) / 2, 50);
                graphics.drawString("The planet Irue is situated far away from our bases.", 30, 74);
                graphics.drawString("But though, it is one of our most important trade partners.", 30, 86);
                graphics.drawString("Between Irue and us, there is an asteroid belt,", 30, 98);
                graphics.drawString("where enemies settled just a year ago.", 30, 110);
                graphics.drawString("Now trade can no longer be done.", 30, 122);
                graphics.drawString("Your mision is to weaken the enemy forces,", 30, 146);
                graphics.drawString("and determine wether the reports of a coming attack", 30, 158);
                graphics.drawString("on Irue are true.", 30, 170);
                graphics.drawString("We have long been afraid a Zephi attack would", 30, 182);
                graphics.drawString("entirely detroy Irue.", 30, 194);
                graphics.drawString("A Zephi is a type of unmanned kamakazie ship, ", 30, 218);
                graphics.drawString("with a light shield but else unarmed.", 30, 230);
                graphics.drawString("Even though, their numbers and large explosives", 30, 242);
                graphics.drawString("make them dangerous enough.", 30, 254);
                return;
            }
            if (this.page == 101) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s7 = "ASTEROID";
                graphics.drawString(s7, (this.aWidth - fontMetrics.stringWidth(s7)) / 2, 50);
                graphics.drawString("General notes:", 30, 74);
                graphics.drawString("Asteroids are the large rocks floating in space.", 35, 86);
                graphics.drawString("Crashing into one would damage yor ship seriously.", 35, 98);
                graphics.drawString("Faraway asteroids aren't dangerous.", 35, 110);
                graphics.drawString("Score:", 30, 134);
                graphics.drawString("50 points", 80, 134);
                graphics.drawString("Shield:", 30, 146);
                graphics.drawString("3 hits", 80, 146);
                graphics.drawString("Weapons:", 30, 158);
                graphics.drawString("None", 80, 158);
                graphics.drawString("Hints:", 30, 182);
                graphics.drawString("An effective way to crash asteroids is to", 35, 194);
                graphics.drawString("place the crosshair some centimeters in front of", 35, 206);
                graphics.drawString("your victim and fire. The lasers take up to 1 second", 35, 218);
                graphics.drawString("before they hit their target.", 35, 230);
                graphics.drawString("This definition of  ' in front of '  is the place where the object", 35, 242);
                graphics.drawString("soon will move to.", 35, 254);
                return;
            }
            if (this.page == 102) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s8 = "INTERCEPTOR";
                graphics.drawString(s8, (this.aWidth - fontMetrics.stringWidth(s8)) / 2, 50);
                graphics.drawString("General notes:", 30, 74);
                graphics.drawString("Interceptors are light spaceships armed with a single laser", 35, 86);
                graphics.drawString("as their only weapon. But be aware, it can still cause you damage.", 35, 98);
                graphics.drawString("Score:", 30, 122);
                graphics.drawString("100 points", 80, 122);
                graphics.drawString("Shield:", 30, 134);
                graphics.drawString("1 hit", 80, 134);
                graphics.drawString("Weapons:", 30, 146);
                graphics.drawString("1 Laser", 80, 146);
                graphics.drawString("Hints:", 30, 170);
                graphics.drawString("Interceptors are too fast to shoot down in the same", 35, 182);
                graphics.drawString("way as asteroids. Luckely, they don't have any shield,", 35, 194);
                graphics.drawString("like later enemies will have.", 35, 206);
                graphics.drawString("Try to keep as far away from interceptors as possible,", 35, 218);
                graphics.drawString("then they won't shoot at you.", 35, 230);
                return;
            }
            if (this.page == 103) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s9 = "HEAVY FIGHTER";
                graphics.drawString(s9, (this.aWidth - fontMetrics.stringWidth(s9)) / 2, 50);
                graphics.drawString("General notes:", 30, 74);
                graphics.drawString("Heavy Fighters are quite slow spaceships armed", 35, 86);
                graphics.drawString("with multiple lasers, and a missile.", 35, 98);
                graphics.drawString("Keep a close eye on these, or they will quickly shoot you down.", 35, 110);
                graphics.drawString("Score:", 30, 134);
                graphics.drawString("350 points", 80, 134);
                graphics.drawString("Shield:", 30, 146);
                graphics.drawString("4 hits", 80, 146);
                graphics.drawString("Weapons:", 30, 158);
                graphics.drawString("5 Lasers, 1 Missile", 80, 158);
                graphics.drawString("Hints:", 30, 182);
                graphics.drawString("Keep away from Heavy Fighters,", 35, 194);
                graphics.drawString("and especially their missiles.", 35, 206);
                graphics.drawString("Just don't forget to shoot at them, they're worth", 35, 218);
                graphics.drawString("a lot of points, and can quickly take you to the next level.", 35, 230);
                return;
            }
            if (this.page == 104) {
                graphics.setFont(Help.infoFont);
                graphics.setColor(Help.infoColor);
                final String s10 = "ZEPHI";
                graphics.drawString(s10, (this.aWidth - fontMetrics.stringWidth(s10)) / 2, 50);
                graphics.drawString("General notes:", 30, 74);
                graphics.drawString("Zephis are kamakazie vessels, set on destructing Irue.", 35, 86);
                graphics.drawString("They can't attack you, but are trying to crash into Irue.", 35, 98);
                graphics.drawString("Score:", 30, 122);
                graphics.drawString("50 points", 80, 122);
                graphics.drawString("Shield:", 30, 134);
                graphics.drawString("2 hits", 80, 134);
                graphics.drawString("Weapons:", 30, 146);
                graphics.drawString("None", 80, 146);
                graphics.drawString("Hints:", 30, 170);
                graphics.drawString("Be quick, they are in large numbers,", 35, 182);
                graphics.drawString("and moves faster than they seem to do.", 35, 194);
            }
        }
    }
    
    public boolean click(final int n, final int n2) {
        if (this.visible) {
            if (n < 25 || n2 < 25 || n > this.aWidth - 25 || n2 > this.aHeight - 25) {
                this.hide();
                return true;
            }
            if (this.page == 0) {
                if (n2 < 278 && n2 > 266 && n < 160) {
                    this.show(10);
                    return true;
                }
                if (n2 < 290 && n2 > 278 && n < 160) {
                    this.show(1);
                    return true;
                }
                if (n2 < 302 && n2 > 290 && n < 160) {
                    this.show(2);
                    return true;
                }
                this.hide();
                return true;
            }
            else {
                if (n2 < 62 && n2 > 50) {
                    this.show(this.lastpage);
                    this.lastpage = 0;
                    return true;
                }
                if (this.page == 2) {
                    if (n2 < 86 && n2 > 74) {
                        if (n < Math.max(this.aWidth / 2, 150)) {
                            this.show(101);
                            return true;
                        }
                        return false;
                    }
                    else if (n2 < 98 && n2 > 86) {
                        if (n < Math.max(this.aWidth / 2, 150)) {
                            this.show(102);
                            return true;
                        }
                        return false;
                    }
                    else if (n2 < 110 && n2 > 98) {
                        if (n < Math.max(this.aWidth / 2, 150)) {
                            this.show(103);
                            return true;
                        }
                        return false;
                    }
                    else if (n2 < 122 && n2 > 110) {
                        if (n < Math.max(this.aWidth / 2, 150)) {
                            this.show(104);
                            return true;
                        }
                        return false;
                    }
                }
                if (this.page == 10) {
                    this.hide();
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        Help.infoFont = new Font("TimesRoman", 0, 11);
        Help.linkFont = new Font("TimesRoman", 1, 11);
        Help.infoColor = Color.white;
        Help.linkColor = new Color(11184895);
    }
}
