// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

public class CRaceTrack
{
    private int drawPosition;
    private int oldDrawPosition;
    private int[] trackLayoutLeft;
    private int[] trackLayoutRight;
    private int[] condition;
    private int trackLength;
    private int narrowStart;
    private int narrowStop;
    public final int SNOW = 1;
    public final int NORMAL = 0;
    private int screenSizeX;
    private int screenSizeY;
    private final int maxTrackLength = 50000;
    private final int borderSize = 9;
    private final int middleStripeSize = 5;
    private int trackNextLapPosition;
    private boolean highDetail;
    private Applet ap;
    public static final int MAX_FUEL_CANS = 50;
    public static final int MAX_TIRES = 50;
    public static final int MAX_CLOCKS = 50;
    public static final int MAX_BOOSTS = 50;
    public static final int MAX_MANHOLES = 50;
    public int numberFuelCans;
    public int numberTires;
    public int numberClocks;
    public int numberBoosts;
    public int numberManholes;
    public CObjectPosition[] fuelCans;
    public CObjectPosition[] tires;
    public CObjectPosition[] clocks;
    public CObjectPosition[] boosts;
    public CObjectPosition[] manholes;
    
    public void init(final int xSize, final int ySize, final Applet a) {
        this.drawPosition = 0;
        this.oldDrawPosition = 0;
        this.screenSizeX = xSize;
        this.screenSizeY = ySize;
        this.highDetail = true;
        this.trackNextLapPosition = ySize - 70;
        this.ap = a;
        this.fuelCans = new CObjectPosition[50];
        this.tires = new CObjectPosition[50];
        this.clocks = new CObjectPosition[50];
        this.boosts = new CObjectPosition[50];
        this.manholes = new CObjectPosition[50];
        this.numberFuelCans = 0;
        this.numberTires = 0;
        this.numberClocks = 0;
        this.numberBoosts = 0;
        this.numberManholes = 0;
    }
    
    public int getTimeLimit(final int lap) {
        int factor = 5 - lap;
        if (factor < 0) {
            factor = 0;
        }
        return 900 + factor * 5 * 30;
    }
    
    public int getPosition() {
        return this.drawPosition;
    }
    
    public int getCondition(final int trackPosition) {
        if (trackPosition > 0 && trackPosition < this.trackLength) {
            return this.condition[trackPosition];
        }
        return 0;
    }
    
    public int getPreviousPosition() {
        return this.oldDrawPosition;
    }
    
    public int getLength() {
        return this.trackLength;
    }
    
    public void setHighDetail() {
        this.highDetail = true;
    }
    
    public void setLowDetail() {
        this.highDetail = false;
    }
    
    public int width() {
        return this.screenSizeX;
    }
    
    public int trackWidth(final int trackPos) {
        return this.trackLayoutRight[trackPos] - this.trackLayoutLeft[trackPos];
    }
    
    public int getLeftBorder(final int trackPos) {
        return this.trackLayoutLeft[trackPos];
    }
    
    public int getRightBorder(final int trackPos) {
        return this.trackLayoutRight[trackPos];
    }
    
    public int height() {
        return this.screenSizeY;
    }
    
    public CCollisionCode bringToBounds(final CMovingShape shape) {
        int returnValue = 0;
        int checkPosition = 0;
        final CCollisionCode ccode = new CCollisionCode();
        ccode.init();
        for (int i = 0; i < shape.height; ++i) {
            checkPosition = shape.getCurrentTrackPosition() - i - 1;
            if (checkPosition < 0) {
                checkPosition += this.trackLength;
            }
            if (this.trackLayoutLeft[checkPosition] > shape.x1) {
                if (returnValue < this.trackLayoutLeft[checkPosition]) {
                    returnValue = this.trackLayoutLeft[checkPosition];
                }
                if (i <= shape.height / 2) {
                    ccode.upperLeftTireHit = true;
                }
                else {
                    ccode.lowerLeftTireHit = true;
                }
            }
            if (this.trackLayoutRight[checkPosition] < shape.x1 + shape.width) {
                if (returnValue == 0 || returnValue > this.trackLayoutRight[checkPosition] - shape.width) {
                    returnValue = this.trackLayoutRight[checkPosition] - shape.width;
                }
                if (i <= shape.height / 2) {
                    ccode.upperRightTireHit = true;
                }
                else {
                    ccode.lowerRightTireHit = true;
                }
            }
        }
        ccode.xShift = returnValue;
        return ccode;
    }
    
    public boolean inBounds(final CMovingShape shape) {
        boolean collide = false;
        int checkPosition = 0;
        for (int i = 0; i < shape.height; ++i) {
            checkPosition = shape.getCurrentTrackPosition() - i - 1;
            if (checkPosition < 0) {
                checkPosition += this.trackLength;
            }
            if (this.trackLayoutLeft[checkPosition] >= shape.x1) {
                collide = true;
            }
            if (this.trackLayoutRight[checkPosition] <= shape.x1 + shape.width) {
                collide = true;
            }
        }
        return !collide;
    }
    
    public boolean inRightBounds(final CMovingShape shape, final int xOffset) {
        boolean collide = false;
        int checkPosition = 0;
        for (int i = 0; i < shape.height; ++i) {
            checkPosition = shape.getCurrentTrackPosition() - i - 1;
            if (checkPosition < 0) {
                checkPosition += this.trackLength;
            }
            if (this.trackLayoutRight[checkPosition] <= shape.x1 + shape.width + xOffset) {
                collide = true;
            }
        }
        return !collide;
    }
    
    public boolean inLeftBounds(final CMovingShape shape, final int xOffset) {
        boolean collide = false;
        int checkPosition = 0;
        for (int i = 0; i < shape.height; ++i) {
            checkPosition = shape.getCurrentTrackPosition() - i - 1;
            if (checkPosition < 0) {
                checkPosition += this.trackLength;
            }
            if (this.trackLayoutLeft[checkPosition] >= shape.x1 - xOffset) {
                collide = true;
            }
        }
        return !collide;
    }
    
    public void loadDefaultTrack() {
        this.trackLength = 0;
        this.trackLayoutLeft = new int[50000];
        this.trackLayoutRight = new int[50000];
        this.condition = new int[50000];
        int currentPosition = 0;
        for (int i = 0; i < 944; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 8;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 8 * 7;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < (this.screenSizeX / 5 - this.screenSizeX / 8) * 2; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 8 + i / 2;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 8 * 7 - i / 2;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 1888; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 1888; ++i) {
            final double offset = (Math.cos(i / 50) - 1) * 30;
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 5 + offset);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 5 * 4 + offset);
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 472; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 472; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 1888; ++i) {
            final double offset = (Math.cos(i / 50) - 1) * 30;
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 5 + offset);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 5 * 4 + offset);
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 472; ++i) {
            final double offset = (Math.cos(i / 50) - 1) * 30;
            final double offset2 = i / 472 * 60;
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 5 + offset + offset2);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 5 * 4 + offset - offset2);
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 400; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 - 120;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 120; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 - 120 + i;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 400; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 120; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + i / 2;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 - i / 2;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 200; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + 60;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 - 60;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 120; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + 60 - i / 2;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 - 60 + i / 2;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 300; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 300; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 120; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + i;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 400; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + 120;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 120; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 + 120 - i;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 1;
            ++currentPosition;
        }
        for (int i = 0; i < 400; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 944; ++i) {
            final double offset = Math.sin(i / 50) * (30 + i / 944 * 50);
            final double offset2 = i / 944 * (this.screenSizeX / 4 - this.screenSizeX / 5);
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 5 + offset + offset2);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 5 * 4 + offset - offset2);
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        this.narrowStart = currentPosition - 100;
        for (int i = 0; i < 944; ++i) {
            final double offset = Math.sin(i / 50) * 70;
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 4 + offset);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 4 * 3 + offset);
            if (i < 236) {
                this.condition[currentPosition] = 0;
            }
            else if (i < 708) {
                this.condition[currentPosition] = 1;
            }
            else {
                this.condition[currentPosition] = 0;
            }
            ++currentPosition;
        }
        this.narrowStop = this.narrowStart;
        for (int i = 0; i < 944; ++i) {
            final double offset = Math.sin(i / 50) * (80 - i / 944 * 50);
            final double offset2 = i / 944 * (this.screenSizeX / 4 - this.screenSizeX / 5);
            this.trackLayoutLeft[currentPosition] = (int)(this.screenSizeX / 4 + offset - offset2);
            this.trackLayoutRight[currentPosition] = (int)(this.screenSizeX / 4 * 3 + offset + offset2);
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < 200; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        for (int i = 0; i < (this.screenSizeX / 5 - this.screenSizeX / 8) * 2; ++i) {
            this.trackLayoutLeft[currentPosition] = this.screenSizeX / 5 - i / 2;
            this.trackLayoutRight[currentPosition] = this.screenSizeX / 5 * 4 + i / 2;
            this.condition[currentPosition] = 0;
            ++currentPosition;
        }
        this.trackLength = currentPosition;
        this.fuelCans[0] = new CObjectPosition(200, 4000, 1);
        this.fuelCans[1] = new CObjectPosition(140, 6000, 1);
        this.fuelCans[2] = new CObjectPosition(200, 8000, 1);
        this.fuelCans[3] = new CObjectPosition(160, 12000, 1);
        this.fuelCans[4] = new CObjectPosition(160, 14050, 1);
        this.numberFuelCans = 5;
        this.clocks[0] = new CObjectPosition(200, 7000, 1);
        this.clocks[1] = new CObjectPosition(180, 13000, 1);
        this.clocks[2] = new CObjectPosition(100, 2000, 2);
        this.clocks[3] = new CObjectPosition(200, 5000, 2);
        this.clocks[4] = new CObjectPosition(250, 7000, 3);
        this.clocks[5] = new CObjectPosition(140, 2000, 4);
        this.clocks[6] = new CObjectPosition(200, 6800, 4);
        this.clocks[7] = new CObjectPosition(200, 7200, 5);
        this.clocks[8] = new CObjectPosition(220, 2000, 6);
        this.clocks[9] = new CObjectPosition(180, 14100, 1);
        this.clocks[10] = new CObjectPosition(140, 14000, 7);
        this.clocks[11] = new CObjectPosition(180, 14000, 7);
        this.clocks[12] = new CObjectPosition(220, 14000, 8);
        this.clocks[13] = new CObjectPosition(260, 14000, 6);
        this.clocks[14] = new CObjectPosition(140, 1100, 8);
        this.numberClocks = 15;
        this.tires[0] = new CObjectPosition(180, 2000, 1);
        this.tires[1] = new CObjectPosition(140, 14100, 3);
        this.tires[2] = new CObjectPosition(140, 5000, 5);
        this.tires[3] = new CObjectPosition(140, 3000, 7);
        this.tires[4] = new CObjectPosition(180, 3000, 9);
        this.tires[5] = new CObjectPosition(120, 2100, 2);
        this.tires[6] = new CObjectPosition(160, 2100, 4);
        this.tires[7] = new CObjectPosition(200, 2100, 6);
        this.numberTires = 8;
        this.boosts[0] = new CObjectPosition(100, 14100, 1);
        this.boosts[1] = new CObjectPosition(220, 14100, 1);
        this.boosts[2] = new CObjectPosition(200, 6900, 2);
        this.boosts[3] = new CObjectPosition(200, 1000, 3);
        this.boosts[4] = new CObjectPosition(140, 6900, 4);
        this.boosts[5] = new CObjectPosition(140, 1000, 5);
        this.boosts[6] = new CObjectPosition(140, 700, 6);
        this.boosts[7] = new CObjectPosition(180, 700, 7);
        this.boosts[8] = new CObjectPosition(220, 700, 8);
        this.numberBoosts = 9;
        this.manholes[0] = new CObjectPosition(100, 7000, 1);
        this.manholes[1] = new CObjectPosition(140, 7000, 5);
        this.manholes[2] = new CObjectPosition(180, 7000, 3);
        this.manholes[3] = new CObjectPosition(160, 7400, 2);
        this.manholes[4] = new CObjectPosition(200, 7400, 4);
        this.manholes[5] = new CObjectPosition(240, 7400, 6);
        this.manholes[6] = new CObjectPosition(100, 5500, 8);
        this.manholes[7] = new CObjectPosition(140, 5500, 7);
        this.manholes[8] = new CObjectPosition(180, 5500, 6);
        this.manholes[9] = new CObjectPosition(130, 5900, 4);
        this.manholes[10] = new CObjectPosition(170, 5900, 2);
        this.manholes[11] = new CObjectPosition(210, 5900, 9);
        this.manholes[12] = new CObjectPosition(80, 550, 2);
        this.manholes[13] = new CObjectPosition(120, 550, 3);
        this.manholes[14] = new CObjectPosition(160, 550, 5);
        this.manholes[15] = new CObjectPosition(200, 600, 4);
        this.manholes[16] = new CObjectPosition(160, 2900, 6);
        this.manholes[17] = new CObjectPosition(120, 3300, 7);
        this.manholes[18] = new CObjectPosition(100, 3600, 5);
        this.manholes[19] = new CObjectPosition(160, 3800, 8);
        this.manholes[20] = new CObjectPosition(220, 4200, 6);
        this.manholes[21] = new CObjectPosition(180, 4400, 3);
        this.manholes[22] = new CObjectPosition(120, 10000, 5);
        this.manholes[23] = new CObjectPosition(240, 10100, 4);
        this.manholes[24] = new CObjectPosition(220, 10200, 6);
        this.numberManholes = 25;
    }
    
    public boolean inNarrow(final int trackPosition) {
        return trackPosition > this.narrowStart && trackPosition < this.narrowStop;
    }
    
    public void scrollDown(final Graphics g1, final Graphics g2, final Image trackImage1, final Image trackImage2, int validGraphics, final Applet a, final int yStep, final Image[] trackTextureImage) {
        if (validGraphics == 1) {
            g2.drawImage(trackImage1, 0, 0, this.screenSizeX, this.screenSizeY - yStep, 0, yStep, this.screenSizeX, this.screenSizeY, a);
            validGraphics = 2;
        }
        else {
            g1.drawImage(trackImage2, 0, 0, this.screenSizeX, this.screenSizeY - yStep, 0, yStep, this.screenSizeX, this.screenSizeY, a);
            validGraphics = 1;
        }
        if (validGraphics == 1) {}
        this.oldDrawPosition = this.drawPosition;
        for (int i = this.screenSizeY - yStep; i < this.screenSizeY; ++i) {
            int backDraw = this.drawPosition-- - this.screenSizeY;
            if (backDraw < 0) {
                backDraw += this.trackLength;
            }
            this.drawTrackLine(g1, g2, validGraphics, i, backDraw, trackTextureImage, a);
            if (this.drawPosition == -1) {
                this.drawPosition = this.trackLength;
            }
        }
    }
    
    public void scrollUp(final Graphics g1, final Graphics g2, final Image trackImage1, final Image trackImage2, int validGraphics, final Applet a, final int yStep, final Image[] trackTextureImage) {
        if (validGraphics == 1) {
            g2.drawImage(trackImage1, 0, yStep, this.screenSizeX, this.screenSizeY, 0, 0, this.screenSizeX, this.screenSizeY - yStep, a);
            validGraphics = 2;
        }
        else {
            g1.drawImage(trackImage2, 0, yStep, this.screenSizeX, this.screenSizeY, 0, 0, this.screenSizeX, this.screenSizeY - yStep, a);
            validGraphics = 1;
        }
        if (validGraphics == 1) {}
        this.oldDrawPosition = this.drawPosition;
        for (int i = 0; i < yStep; ++i) {
            this.drawTrackLine(g1, g2, validGraphics, yStep - i - 1, this.drawPosition++, trackTextureImage, a);
            if (this.drawPosition == this.trackLength) {
                this.drawPosition = 0;
            }
        }
    }
    
    public void drawFull(final Graphics g1, final Graphics g2, final int validGraphics, final int startPosition, final Image[] trackTextureImage, final Applet a) {
        for (int i = 0; i < this.screenSizeY; ++i) {
            this.drawTrackLine(g1, g2, validGraphics, this.screenSizeY - i - 1, startPosition + i, trackTextureImage, a);
            this.drawTrackLine(g2, g1, validGraphics, this.screenSizeY - i - 1, startPosition + i, trackTextureImage, a);
        }
        this.drawPosition = startPosition + this.screenSizeY;
        this.oldDrawPosition = this.drawPosition;
    }
    
    public void drawTextureLine(final Graphics g, final int x1, final int y1, final int x2, final int y2, final Image trackImage, final Applet a, final int trackPosition) {
        g.drawImage(trackImage, x1, y1, x2, y2 + 1, 0, trackPosition % trackImage.getHeight(a), trackImage.getWidth(a), trackPosition % trackImage.getHeight(a) + 1, Color.green, a);
    }
    
    public void drawTrackLine(final Graphics g1, final Graphics g2, final int validGraphics, final int yOffset, final int trackPosition, final Image[] trackTextureImage, final Applet a) {
        Graphics g3;
        if (validGraphics == 1) {
            g3 = g1;
        }
        else {
            g3 = g2;
        }
        if (this.condition[trackPosition] == 0) {
            this.drawTextureLine(g3, 0, yOffset, this.trackLayoutLeft[trackPosition], yOffset, trackTextureImage[0], a, trackPosition);
        }
        else if (this.condition[trackPosition] == 1) {
            this.drawTextureLine(g3, 0, yOffset, this.trackLayoutLeft[trackPosition], yOffset, trackTextureImage[7], a, trackPosition);
        }
        this.drawTextureLine(g3, this.trackLayoutLeft[trackPosition], yOffset, this.trackLayoutLeft[trackPosition] + 9, yOffset, trackTextureImage[2], a, trackPosition);
        if (trackPosition < 26) {
            this.drawTextureLine(g3, this.trackLayoutLeft[trackPosition] + 9, yOffset, this.trackLayoutRight[trackPosition] - 9, yOffset, trackTextureImage[6], a, trackPosition);
        }
        else if (this.condition[trackPosition] == 0) {
            if (!this.highDetail) {
                this.drawTextureLine(g3, this.trackLayoutLeft[trackPosition] + 9, yOffset, this.trackLayoutRight[trackPosition] - 9, yOffset, trackTextureImage[1], a, trackPosition);
            }
            else {
                this.drawTextureLine(g3, this.trackLayoutLeft[trackPosition] + 9, yOffset, (this.trackLayoutRight[trackPosition] - this.trackLayoutLeft[trackPosition]) / 2 + this.trackLayoutLeft[trackPosition] - 2, yOffset, trackTextureImage[1], a, trackPosition);
                this.drawTextureLine(g3, (this.trackLayoutRight[trackPosition] - this.trackLayoutLeft[trackPosition]) / 2 + this.trackLayoutLeft[trackPosition] - 2, yOffset, (this.trackLayoutRight[trackPosition] - this.trackLayoutLeft[trackPosition]) / 2 + this.trackLayoutLeft[trackPosition] + 2, yOffset, trackTextureImage[3], a, trackPosition);
                this.drawTextureLine(g3, (this.trackLayoutRight[trackPosition] - this.trackLayoutLeft[trackPosition]) / 2 + this.trackLayoutLeft[trackPosition] + 2, yOffset, this.trackLayoutRight[trackPosition] - 9, yOffset, trackTextureImage[5], a, trackPosition);
            }
        }
        else if (this.condition[trackPosition] == 1) {
            this.drawTextureLine(g3, this.trackLayoutLeft[trackPosition] + 9, yOffset, this.trackLayoutRight[trackPosition] - 9, yOffset, trackTextureImage[8], a, trackPosition);
        }
        this.drawTextureLine(g3, this.trackLayoutRight[trackPosition] - 9, yOffset, this.trackLayoutRight[trackPosition], yOffset, trackTextureImage[4], a, trackPosition);
        if (this.condition[trackPosition] == 0) {
            this.drawTextureLine(g3, this.trackLayoutRight[trackPosition], yOffset, this.screenSizeX, yOffset, trackTextureImage[0], a, trackPosition);
        }
        else if (this.condition[trackPosition] == 1) {
            this.drawTextureLine(g3, this.trackLayoutRight[trackPosition], yOffset, this.screenSizeX, yOffset, trackTextureImage[7], a, trackPosition);
        }
    }
}
