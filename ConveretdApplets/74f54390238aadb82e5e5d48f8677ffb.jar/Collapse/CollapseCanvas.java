// 
// Decompiled by Procyon v0.5.30
// 

package Collapse;

import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Component;
import sprite.FancyContainer;
import sprite.Sprite;
import java.awt.Image;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.awt.Event;
import zylom.ZylomDataGather;
import sprite.ButtonSprite;
import sprite.SpriteBase;
import java.net.URL;
import sprite.NumberSprite;
import sprite.TextSprite;
import java.awt.Color;
import sprite.AnimateSprite;
import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Font;
import sprite.GameCanvas;

public class CollapseCanvas extends GameCanvas
{
    private final int ALL_GONE_BONUS = 23;
    private final int ALL_GONE_BONUS_TEXT = 22;
    private final int BACKGROUND = 2;
    private final int BIG_CHUNK_GONE_TEXT = 24;
    final int BLOCK_FRAMES = 22;
    private final int BONUS_TEXT = 28;
    private final int BOTTOM_STRIP = 29;
    private final int FIRST_BOMB = 4;
    private final int FIRST_BONUS_ANIM_BLOCK = 14;
    private int FIRST_EXPLODER;
    private final int FIRST_EXPLOSION = 19;
    private static final int INTRO_BUTTON = 33;
    private final int LAST_BONUS_ANIM_BLOCK = 18;
    private final int LAST_EXPLOSION = 21;
    private final int LAST_SPRITE = 35;
    private final int LEVEL = 6;
    private final int LINES_LEFT = 7;
    private static final int LINK_BUTTON = 32;
    private final int NEW_BLOCKS_COVER = 13;
    private final int PAUSE_BUTTON = 9;
    private final int PAUSING_TEXT = 27;
    private static final int PROMO_SPRITE = 30;
    private static final int PROMO_SPRITE2 = 31;
    private final int SCORE = 5;
    private final int SOUND_BAD_HIT = 1;
    private final int SOUND_BLOCKS_CLEAR = 3;
    private final int SOUND_BLOCKS_DROP = 2;
    private final int SOUND_BLOCKS_SLIDE = 4;
    private final int SOUND_BOMB = 14;
    private final int SOUND_BONUS_ALL_CLEAR = 10;
    private final int SOUND_BONUS_BIG_CHUNK = 11;
    private final int SOUND_BONUS_BLOCK = 7;
    private final int SOUND_BONUS_ROW = 6;
    private final int SOUND_CLAP = 5;
    private final int SOUND_GAME_OVER = 12;
    private final int SOUND_GOOD_HIT = 0;
    private final int SOUND_NEW_BLOCK = 9;
    private final int SOUND_NEW_ROW = 8;
    private final int SOUND_WARNING = 13;
    private final int SOUND_WARNING2 = 15;
    private final int STATE_COMPUTE_BONUS = 4;
    private final int STATE_DROP_BONUS_BLOCKS = 5;
    private final int STATE_DROP_REGULAR_BLOCKS = 6;
    private final int STATE_GAME_OVER = 2;
    private final int STATE_PAUSING = 7;
    private final int STATE_PLAYING = 1;
    private final int STATE_PROMO = 8;
    private final int STATE_READY = 0;
    private final int SUPER_BOMB = 12;
    private Font m_FontSansSerif20Point;
    private FontMetrics m_FontSansSerif20PointMetrics;
    private Font m_FontSansSerif40Point;
    private Random m_RNG;
    private boolean m_bCompleteRowBonus;
    private boolean m_bDropping;
    private boolean m_bExploding;
    private boolean m_bHandCursor;
    private boolean m_bShifting;
    private AnimateSprite[] m_blockSprites;
    private Color m_clrNormal;
    private Color m_clrOver;
    private int m_nAllBlocksGoneCounter;
    private int m_nBigChunkGoneCounter;
    private int m_nBlockDim;
    private int m_nBonusColumn;
    private int m_nBonusRow;
    private int m_nBottomLeftBlockY;
    private int m_nBottomY;
    private int m_nColors;
    private int m_nColumns;
    private int m_nCyclerColumn;
    private int m_nCyclerCounter;
    private int m_nCyclerRow;
    private int m_nExplosionCounter;
    private int m_nFilledRows;
    private int m_nGameOverFramesCounter;
    private int[][] m_nGrid;
    private int m_nLastLevel;
    private int m_nLeftX;
    private int m_nLevel;
    private int m_nLevelCompleteFramesCounter;
    private int m_nLinesLeft;
    private int m_nLinesNeeded;
    private int m_nNewBlocksFilled;
    private boolean[][] m_nRemove;
    private int m_nRightX;
    private int m_nRows;
    private int m_nScore;
    private int m_nScoreSent;
    private int m_nShiftsPerformed;
    private volatile int m_nState;
    private int m_nTimingCounter;
    private int m_nTimingSpeed;
    private int m_nTopY;
    private AnimateSprite m_sprClickStart;
    private AnimateSprite m_sprGameOver;
    private AnimateSprite m_sprLevelComplete;
    private String m_strBuyURL;
    
    public CollapseCanvas() {
        this.m_nState = 0;
        this.m_nScore = 0;
        this.m_nScoreSent = 0;
        this.m_nLinesLeft = 0;
        this.m_nLinesNeeded = 0;
        this.m_nLevel = 0;
        this.m_nFilledRows = 6;
        this.m_nCyclerRow = -1;
        this.m_nCyclerColumn = -1;
        this.m_nLevelCompleteFramesCounter = 0;
        this.m_nGameOverFramesCounter = 0;
        this.m_nTimingCounter = 0;
        this.m_nBigChunkGoneCounter = 0;
        this.m_nAllBlocksGoneCounter = 0;
        this.m_nExplosionCounter = 1;
        this.m_bHandCursor = false;
        this.m_bDropping = false;
        this.m_bShifting = false;
        this.m_bExploding = false;
        this.m_FontSansSerif40Point = null;
        this.m_FontSansSerif20Point = null;
        this.m_FontSansSerif20PointMetrics = null;
        this.m_clrNormal = new Color(204, 0, 0);
        this.m_clrOver = new Color(255, 0, 0);
        this.m_blockSprites = null;
        this.m_sprClickStart = null;
        this.m_sprLevelComplete = null;
        this.m_sprGameOver = null;
    }
    
    private void AdjustScore(final int nBlocksRemoved, final boolean bAllowBigChunkBonus) {
        int nAmountToAddToScore = 0;
        nAmountToAddToScore += nBlocksRemoved;
        if (nBlocksRemoved > 3) {
            nAmountToAddToScore += (nBlocksRemoved - 3) * 2;
        }
        if (nBlocksRemoved > 6) {
            nAmountToAddToScore += (nBlocksRemoved - 6) * 4;
        }
        if (nBlocksRemoved > 9) {
            nAmountToAddToScore += (nBlocksRemoved - 9) * 6;
        }
        if (nBlocksRemoved > 14) {
            nAmountToAddToScore += (nBlocksRemoved - 14) * 10;
        }
        if (nBlocksRemoved > 19) {
            nAmountToAddToScore += (nBlocksRemoved - 19) * 15;
        }
        if (nBlocksRemoved > 29) {
            nAmountToAddToScore += (nBlocksRemoved - 29) * 30;
        }
        if (nBlocksRemoved > 49) {
            nAmountToAddToScore += (nBlocksRemoved - 49) * 50;
        }
        if (bAllowBigChunkBonus && ((this.m_nColors == 3 && nBlocksRemoved > 14) || (this.m_nColors == 4 && nBlocksRemoved > 11))) {
            nAmountToAddToScore *= this.m_nLevel + 2;
            if (this.m_nColors == 4) {
                nAmountToAddToScore *= 10;
            }
            ((TextSprite)super.m_sprites[24]).setText(super.m_strStrings[0] + ": " + this.formatNumber(nAmountToAddToScore) + "!!!");
            final int nMessageWidth = this.m_FontSansSerif20PointMetrics.stringWidth(((TextSprite)super.m_sprites[24]).getText());
            super.m_sprites[24].setPosition((this.m_nLeftX + this.m_nColumns * this.m_nBlockDim - nMessageWidth) / 2, 225);
            this.m_nBigChunkGoneCounter = 50;
            super.m_sprites[24].show(true);
            this.playSound(11);
        }
        this.m_nScore += nAmountToAddToScore;
        ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
    }
    
    private boolean AllBlocksGone() {
        for (int nRow = 1; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nGrid[nColumn][nRow] != -1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean AllBlocksHaveReachedDestination() {
        for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
            for (int nRow = 1; nRow < this.m_nRows - 1; ++nRow) {
                final AnimateSprite spr = this.getBlockSprite(nRow, nColumn);
                if (this.m_nGrid[nColumn][nRow] != -1 && spr.Animating()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void BombToRemove(final int nColor) {
        for (int nRow = 1; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nGrid[nColumn][nRow] == nColor || this.m_nGrid[nColumn][nRow] == 12 || this.m_nGrid[nColumn][nRow] == 4 + nColor * 2) {
                    this.m_nRemove[nColumn][nRow] = true;
                }
            }
        }
    }
    
    private void CollapseAllBlocks(final boolean bBonusBlocks) {
        for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
            for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
                if (((this.m_nGrid[nColumn][nRow] != -1 && !bBonusBlocks) || (this.m_nGrid[nColumn][nRow] == -1 && bBonusBlocks)) && this.getBlockSprite(nRow, nColumn).visible()) {
                    this.getBlockSprite(nRow, nColumn).setDestination(this.getBlockSprite(nRow, nColumn).getX(), this.m_nBottomY + 2 * this.m_nBlockDim, 2 * (nRow + 1));
                }
            }
        }
    }
    
    private boolean CollapseOneRow() {
        for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
            for (int nRow = 1; nRow < this.m_nRows - 1; ++nRow) {
                if (this.m_nGrid[nColumn][nRow + 1] != -1 && this.m_nGrid[nColumn][nRow] == -1) {
                    final int nTargetIndex = nRow * this.m_nColumns + nColumn;
                    final int nSourceIndex = nTargetIndex + this.m_nColumns;
                    final AnimateSprite spr = this.m_blockSprites[nTargetIndex];
                    spr.setFrame(this.m_blockSprites[nSourceIndex].getFrame());
                    this.positionBlock(spr, nRow + 1, nColumn, false);
                    if (this.m_nCyclerColumn == nColumn && this.m_nCyclerRow == nRow + 1) {
                        --this.m_nCyclerRow;
                    }
                    this.m_nGrid[nColumn][nRow] = this.m_nGrid[nColumn][nRow + 1];
                    this.m_nGrid[nColumn][nRow + 1] = -1;
                    this.m_blockSprites[nSourceIndex].show(false);
                    this.positionBlock(spr, nRow, nColumn, true);
                    spr.show(true);
                    this.m_bDropping = true;
                }
            }
        }
        if (!this.m_bDropping) {
            this.ShiftOneColumn();
        }
        return this.m_bDropping;
    }
    
    private int CountRemovableBlocks() {
        int nCount = 0;
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nRemove[nColumn][nRow]) {
                    ++nCount;
                }
            }
        }
        return nCount;
    }
    
    private synchronized void ExplosionUpdate() {
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.getExploderSprite(nRow, nColumn).visible()) {
                    final int nCurrentFrame = this.getExploderSprite(nRow, nColumn).getFrame();
                    if (nCurrentFrame >= 21) {
                        this.getExploderSprite(nRow, nColumn).show(false);
                    }
                    else {
                        this.getExploderSprite(nRow, nColumn).setFrame(nCurrentFrame + 1);
                    }
                }
            }
        }
    }
    
    public void InitCanvas(final URL url, final String strDataFile, final int nGameID) {
        super.InitCanvas(url, strDataFile, nGameID);
        if (strDataFile == null || strDataFile.length() == 0) {
            super.m_strDataFile = "Collapse.dat";
        }
        super.m_sprites = new SpriteBase[36];
        this.loadDataFile(url);
    }
    
    private void MarkToRemove(final int nColumn, final int nRow, final int nColor) {
        this.m_nRemove[nColumn][nRow] = true;
        if (nRow > 1 && (this.m_nGrid[nColumn][nRow - 1] == nColor || this.m_nGrid[nColumn][nRow - 1] == 12 || this.m_nGrid[nColumn][nRow - 1] == 4 + nColor * 2) && !this.m_nRemove[nColumn][nRow - 1]) {
            this.MarkToRemove(nColumn, nRow - 1, nColor);
        }
        if (nRow < this.m_nRows - 1 && (this.m_nGrid[nColumn][nRow + 1] == nColor || this.m_nGrid[nColumn][nRow + 1] == 12 || this.m_nGrid[nColumn][nRow + 1] == 4 + nColor * 2) && !this.m_nRemove[nColumn][nRow + 1]) {
            this.MarkToRemove(nColumn, nRow + 1, nColor);
        }
        if (nColumn > 0 && (this.m_nGrid[nColumn - 1][nRow] == nColor || this.m_nGrid[nColumn - 1][nRow] == 12 || this.m_nGrid[nColumn - 1][nRow] == 4 + nColor * 2) && !this.m_nRemove[nColumn - 1][nRow]) {
            this.MarkToRemove(nColumn - 1, nRow, nColor);
        }
        if (nColumn < this.m_nColumns - 1 && (this.m_nGrid[nColumn + 1][nRow] == nColor || this.m_nGrid[nColumn + 1][nRow] == 12 || this.m_nGrid[nColumn + 1][nRow] == 4 + nColor * 2) && !this.m_nRemove[nColumn + 1][nRow]) {
            this.MarkToRemove(nColumn + 1, nRow, nColor);
        }
    }
    
    private boolean NextLine() {
        --this.m_nLinesLeft;
        if (this.m_nLinesLeft == 0) {
            switch (++this.m_nLevel) {
                case 1: {
                    this.m_nLinesLeft = 25;
                    this.m_nTimingSpeed = 10;
                    this.m_nColors = 3;
                    this.m_nFilledRows = 5;
                    break;
                }
                case 2: {
                    this.m_nLinesLeft = 30;
                    this.m_nTimingSpeed = 8;
                    this.m_nFilledRows = 6;
                    break;
                }
                case 3: {
                    this.m_nLinesLeft = 30;
                    this.m_nTimingSpeed = 6;
                    this.m_nFilledRows = 7;
                    break;
                }
                case 4: {
                    this.m_nLinesLeft = 30;
                    this.m_nTimingSpeed = 8;
                    this.m_nColors = 4;
                    this.m_nFilledRows = 6;
                    break;
                }
                case 5: {
                    this.m_nTimingSpeed = 6;
                    this.m_nLinesLeft = 35;
                    this.m_nFilledRows = 7;
                    break;
                }
                case 6: {
                    this.m_nTimingSpeed = 6;
                    this.m_nLinesLeft = 50;
                    this.m_nFilledRows = 8;
                    break;
                }
                case 7: {
                    this.m_nLinesLeft = 50;
                    this.m_nTimingSpeed = 5;
                    break;
                }
                case 8: {
                    this.m_nLinesLeft = 75;
                    this.m_nTimingSpeed = 5;
                    break;
                }
                case 9: {
                    this.m_nLinesLeft = 100;
                    this.m_nTimingSpeed = 4;
                    break;
                }
                case 10: {
                    this.m_nLinesLeft = 100;
                    this.m_nTimingSpeed = 3;
                    break;
                }
                default: {
                    this.m_nTimingSpeed = 2;
                    this.m_nLinesLeft = 100 + 50 * (this.m_nLevel - 10);
                    break;
                }
            }
            this.m_nLinesNeeded = this.m_nLinesLeft;
            this.m_nTimingCounter = this.m_nTimingSpeed;
            ((NumberSprite)super.m_sprites[7]).setNumber(0);
            return true;
        }
        if (this.m_nLinesNeeded - this.m_nLinesLeft > 20 && this.m_nTimingSpeed > 1) {
            this.m_nLinesNeeded = this.m_nLinesLeft;
            --this.m_nTimingSpeed;
        }
        else if (this.m_nLinesLeft < 4 && this.m_nLinesNeeded > 0 && this.m_nTimingSpeed > 1) {
            this.playSound(15);
            this.m_nLinesNeeded = 0;
            --this.m_nTimingSpeed;
        }
        ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
        return false;
    }
    
    private void RemoveBlocks() {
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nRemove[nColumn][nRow]) {
                    this.getExploderSprite(nRow, nColumn).setFrame(19);
                    this.getExploderSprite(nRow, nColumn).show(true);
                    if (this.m_nCyclerColumn == nColumn && this.m_nCyclerRow == nRow) {
                        this.m_nCyclerRow = -1;
                        this.m_nCyclerColumn = -1;
                    }
                    this.m_nGrid[nColumn][nRow] = -1;
                    this.getBlockSprite(nRow, nColumn).show(false);
                    this.m_nRemove[nColumn][nRow] = false;
                }
            }
        }
    }
    
    private void ResetGrid() {
        this.m_nNewBlocksFilled = 0;
        this.m_nCyclerRow = -1;
        this.m_nCyclerColumn = -1;
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                final AnimateSprite spr = this.getBlockSprite(nRow, nColumn);
                this.m_nRemove[nColumn][nRow] = false;
                if (nRow < this.m_nFilledRows && nRow != 0) {
                    final int nColorValue = Math.abs(this.m_RNG.nextInt()) % this.m_nColors;
                    spr.setFrame(this.m_nGrid[nColumn][nRow] = nColorValue);
                    spr.show(true);
                }
                else {
                    this.m_nGrid[nColumn][nRow] = -1;
                    spr.show(false);
                }
                this.positionBlock(spr, nRow, nColumn, false);
            }
        }
    }
    
    private void ResetRemovableBlocks() {
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                this.m_nRemove[nColumn][nRow] = false;
            }
        }
    }
    
    private boolean ShiftOneColumn() {
        for (int i = 0; i < 2; ++i) {
            int nColumn;
            int nPlusMinusOne;
            if (i == 0) {
                nColumn = this.m_nColumns / 2;
                nPlusMinusOne = 1;
            }
            else {
                nColumn = this.m_nColumns / 2 - 1;
                nPlusMinusOne = -1;
            }
            boolean bShiftingIn = false;
            while (true) {
                if (i == 0) {
                    if (nColumn >= this.m_nColumns - this.m_nShiftsPerformed - 1) {
                        break;
                    }
                }
                else if (nColumn <= this.m_nShiftsPerformed) {
                    break;
                }
                if (!bShiftingIn) {
                    boolean bEmptyColumn = true;
                    for (int nRow = 1; nRow < this.m_nRows; ++nRow) {
                        if (this.m_nGrid[nColumn][nRow] != -1) {
                            bEmptyColumn = false;
                            break;
                        }
                    }
                    if (bEmptyColumn) {
                        bShiftingIn = true;
                    }
                }
                if (bShiftingIn) {
                    for (int nRow = 1; nRow < this.m_nRows; ++nRow) {
                        if (this.m_nGrid[nColumn + nPlusMinusOne][nRow] != -1 && this.m_nGrid[nColumn][nRow] == -1) {
                            final int nTargetIndex = nRow * this.m_nColumns + nColumn;
                            final int nSourceIndex = nTargetIndex + nPlusMinusOne;
                            final AnimateSprite spr = this.m_blockSprites[nTargetIndex];
                            spr.setFrame(this.m_blockSprites[nSourceIndex].getFrame());
                            this.positionBlock(spr, nRow, nColumn + nPlusMinusOne, false);
                            if (this.m_nCyclerColumn == nColumn + nPlusMinusOne && this.m_nCyclerRow == nRow) {
                                this.m_nCyclerColumn -= nPlusMinusOne;
                            }
                            this.m_nGrid[nColumn][nRow] = this.m_nGrid[nColumn + nPlusMinusOne][nRow];
                            this.m_nGrid[nColumn + nPlusMinusOne][nRow] = -1;
                            this.m_blockSprites[nSourceIndex].show(false);
                            this.positionBlock(spr, nRow, nColumn, true);
                            spr.show(true);
                        }
                    }
                }
                nColumn += nPlusMinusOne;
            }
            if (bShiftingIn) {
                this.m_bShifting = true;
            }
        }
        if (this.m_bShifting) {
            ++this.m_nShiftsPerformed;
        }
        return this.m_bShifting;
    }
    
    private void SuperBombToRemove(final int nBombColumn, final int nBombRow) {
        for (int nRow = 1; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nGrid[nColumn][nRow] != -1 && Math.abs(nBombColumn - nColumn) * Math.abs(nBombColumn - nColumn) + Math.abs(nBombRow - nRow) * Math.abs(nBombRow - nRow) < 20) {
                    this.m_nRemove[nColumn][nRow] = true;
                }
            }
        }
    }
    
    private void SwitchState(final int nNewState) {
        switch (nNewState) {
            case 0: {
                this.m_sprClickStart.show(true);
                this.m_sprLevelComplete.show(false);
                if (this.m_strBuyURL != null) {
                    ((ButtonSprite)super.m_sprites[33]).enable(false);
                    ((ButtonSprite)super.m_sprites[32]).enable(false);
                    super.m_sprites[30].show(false);
                    super.m_sprites[31].show(false);
                    break;
                }
                break;
            }
            case 1: {
                this.m_sprClickStart.show(false);
                super.m_sprites[9].show(true);
                this.m_sprLevelComplete.show(false);
                break;
            }
            case 4: {
                this.m_sprLevelComplete.show(true);
                this.playSound(5);
                super.m_sprites[9].show(false);
                break;
            }
            case 5: {
                super.m_sprites[28].show(false);
                this.m_nLevelCompleteFramesCounter = 60;
                this.playSound(3);
                break;
            }
            case 6: {
                this.m_nLevelCompleteFramesCounter = 60;
                this.playSound(3);
            }
            case 8: {
                if (this.m_strBuyURL != null) {
                    ((ButtonSprite)super.m_sprites[33]).enable(true);
                    ((ButtonSprite)super.m_sprites[32]).enable(true);
                    super.m_sprites[30].show(true);
                    break;
                }
                this.SwitchState(0);
                break;
            }
            case 2: {
                this.m_sprGameOver.setColorBalance(0, 128, 128, 128);
                this.m_sprGameOver.setAlphaDestination(128, 10);
                this.m_sprGameOver.show(true);
                this.m_nGameOverFramesCounter = 100;
                this.playSound(12);
                super.m_sprites[9].show(false);
                System.out.println("play=false submit highscore");
                ZylomDataGather.GetHelper().indicateGameFinished();
                ZylomDataGather.GetHelper().stopPlayTimer();
                ZylomDataGather.playing = false;
                ZylomDataGather.GetHelper().submitHighscore(this.m_nScore, this.m_nLevel);
                break;
            }
        }
        this.m_nState = nNewState;
    }
    
    private String formatNumber(int nNumber) {
        final StringBuffer strb = new StringBuffer(10);
        while (nNumber >= 1000) {
            final int nRem = nNumber % 1000;
            nNumber /= 1000;
            strb.insert(0, nRem);
            if (nRem < 10) {
                strb.insert(0, "00");
            }
            else if (nRem < 100) {
                strb.insert(0, "0");
            }
            strb.insert(0, super.m_strStrings[2]);
        }
        strb.insert(0, nNumber);
        return strb.toString();
    }
    
    private AnimateSprite getBlockSprite(final int row, final int col) {
        return this.m_blockSprites[row * this.m_nColumns + col];
    }
    
    private AnimateSprite getExploderSprite(final int row, final int col) {
        return this.m_blockSprites[row * this.m_nColumns + col + this.FIRST_EXPLODER];
    }
    
    private synchronized boolean handleMouseDown(final Event evt) {
        switch (this.m_nState) {
            case 1: {
                if (evt.x >= this.m_nLeftX && evt.x <= this.m_nRightX && evt.y >= this.m_nTopY && evt.y <= this.m_nBottomY - this.m_nBlockDim) {
                    final int nColumn = (evt.x - this.m_nLeftX) / this.m_nBlockDim;
                    final int nRow = (this.m_nBottomY - evt.y) / this.m_nBlockDim;
                    boolean bBombHit = false;
                    if (this.m_nGrid[nColumn][nRow] != -1) {
                        if (this.m_nGrid[nColumn][nRow] >= 4 && this.m_nGrid[nColumn][nRow] < 12) {
                            final int nColorToRemove = (this.m_nGrid[nColumn][nRow] - 4) / 2;
                            this.BombToRemove(nColorToRemove);
                            bBombHit = true;
                        }
                        else if (this.m_nGrid[nColumn][nRow] == 12) {
                            this.SuperBombToRemove(nColumn, nRow);
                            bBombHit = true;
                        }
                        else {
                            this.MarkToRemove(nColumn, nRow, this.m_nGrid[nColumn][nRow]);
                        }
                    }
                    final int nNumberToRemove = this.CountRemovableBlocks();
                    if (bBombHit || nNumberToRemove >= 3) {
                        this.RemoveBlocks();
                        this.m_bExploding = true;
                        if (bBombHit) {
                            this.playSound(14);
                            this.AdjustScore(nNumberToRemove, false);
                        }
                        else {
                            this.playSound(0);
                            this.AdjustScore(nNumberToRemove, true);
                        }
                        this.m_bDropping = false;
                        this.m_bShifting = false;
                        this.m_nShiftsPerformed = 0;
                        if (this.AllBlocksGone()) {
                            this.playSound(10);
                            this.m_nAllBlocksGoneCounter = 50;
                            final int nAmountToAddToScore = 10000 * this.m_nLevel;
                            this.m_nScore += nAmountToAddToScore;
                            ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                            super.m_sprites[23].show(true);
                            ((TextSprite)super.m_sprites[22]).setText(super.m_strStrings[0] + ": " + this.formatNumber(nAmountToAddToScore) + "!!!");
                            final int nMessageWidth = this.m_FontSansSerif20PointMetrics.stringWidth(((TextSprite)super.m_sprites[22]).getText());
                            super.m_sprites[22].setPosition((this.m_nLeftX + this.m_nColumns * this.m_nBlockDim - nMessageWidth) / 2, 175);
                            super.m_sprites[22].show(true);
                        }
                        this.CollapseOneRow();
                    }
                    else {
                        this.playSound(1);
                        this.ResetRemovableBlocks();
                    }
                    break;
                }
                if (super.m_sprites[9].hittest(evt.x, evt.y)) {
                    this.togglepause();
                    break;
                }
                break;
            }
            case 0: {
                this.SwitchState(1);
                this.m_nScore = 0;
                this.m_nScoreSent = 0;
                this.m_nLevel = 0;
                this.m_nLinesLeft = 1;
                this.NextLine();
                if (!ZylomDataGather.playing) {
                    System.out.println("zylom start game timer");
                    ZylomDataGather.GetHelper().indicateGameStart();
                    ZylomDataGather.playing = true;
                    ZylomDataGather.level = 0;
                }
                ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                ((NumberSprite)super.m_sprites[6]).setNumber(this.m_nLevel);
                ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
                ((AnimateSprite)super.m_sprites[9]).setFrame(0);
                this.ResetGrid();
                this.requestFocus();
                break;
            }
            case 7: {
                this.requestFocus();
                if (super.m_sprites[9].hittest(evt.x, evt.y)) {
                    this.togglepause();
                    break;
                }
                break;
            }
            case 8: {
                if (this.m_strBuyURL != null) {
                    if (super.m_sprites[33].hittest(evt.x, evt.y)) {
                        this.SwitchState(0);
                    }
                    else if (super.m_sprites[32].hittest(evt.x, evt.y)) {
                        this.waitCursor();
                        System.out.println("download btn pressed");
                        final AppletContext appletContext = ZylomDataGather.gameapplet.getAppletContext();
                        if (appletContext != null) {
                            try {
                                appletContext.showDocument(new URL(ZylomDataGather.gameapplet.getDocumentBase(), ZylomDataGather.gameapplet.getParameter("adUrl")), "_blank");
                            }
                            catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        this.m_bHandCursor = false;
                        this.normalCursor();
                        this.SwitchState(0);
                    }
                    break;
                }
                this.SwitchState(0);
                break;
            }
        }
        return true;
    }
    
    private void handleMouseMove(final Event evt) {
        boolean bHand = false;
        if (super.m_sprites[9].visible() && super.m_sprites[9].hittest(evt.x, evt.y)) {
            bHand = true;
        }
        else if (this.m_nState == 8) {
            if (this.m_strBuyURL != null) {
                if (((ButtonSprite)super.m_sprites[32]).enabled()) {
                    bHand = ((ButtonSprite)super.m_sprites[32]).onMouseMove(evt.x, evt.y);
                }
                if (((ButtonSprite)super.m_sprites[33]).enabled() && !bHand) {
                    bHand = ((ButtonSprite)super.m_sprites[33]).onMouseMove(evt.x, evt.y);
                }
            }
        }
        if (bHand != this.m_bHandCursor) {
            if (bHand) {
                this.handCursor();
            }
            else {
                this.normalCursor();
            }
            this.m_bHandCursor = bHand;
        }
    }
    
    private void hideMessage() {
    }
    
    public boolean initGame() {
        return true;
    }
    
    public boolean keyDown(final Event evt, final int nKey) {
        if (!super.m_bInitDone) {
            return false;
        }
        switch (nKey) {
            case 83:
            case 115: {
                this.enableSound(!super.m_bSoundEnabled);
                return true;
            }
            default: {
                switch (this.m_nState) {
                    case 1: {
                        switch (nKey) {
                            case 52: {
                                if (this.m_nLevel < 4) {
                                    this.playSound(5);
                                    synchronized (this) {
                                        if (this.m_nState == 1) {
                                            this.m_nScore += 3000 * (4 - this.m_nLevel);
                                            ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                                            this.m_nLevel = 3;
                                            this.m_nLinesLeft = 1;
                                            this.NextLine();
                                            this.ResetGrid();
                                            ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
                                            ((NumberSprite)super.m_sprites[6]).setNumber(this.m_nLevel);
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            case 56: {
                                if (this.m_nLevel < 8) {
                                    this.playSound(5);
                                    synchronized (this) {
                                        if (this.m_nState == 1) {
                                            this.m_nScore += 3000 * (8 - this.m_nLevel);
                                            ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                                            this.m_nLevel = 7;
                                            this.m_nLinesLeft = 1;
                                            this.m_nColors = 4;
                                            this.NextLine();
                                            this.ResetGrid();
                                            ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
                                            ((NumberSprite)super.m_sprites[6]).setNumber(this.m_nLevel);
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            case 57: {
                                if (this.m_nLevel < 10) {
                                    this.playSound(5);
                                    synchronized (this) {
                                        if (this.m_nState == 1) {
                                            this.m_nScore += 3000 * (8 - this.m_nLevel);
                                            ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                                            this.m_nLevel = 11;
                                            this.m_nLinesLeft = 1000;
                                            this.m_nColors = 2;
                                            this.m_nTimingSpeed = 1;
                                            this.NextLine();
                                            this.ResetGrid();
                                            ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
                                            ((NumberSprite)super.m_sprites[6]).setNumber(this.m_nLevel);
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            case 80:
                            case 112: {
                                this.togglepause();
                                return true;
                            }
                        }
                        break;
                    }
                    case 7: {
                        this.togglepause();
                        return true;
                    }
                }
                return false;
            }
        }
    }
    
    public synchronized void nextFrame() {
        --this.m_nExplosionCounter;
        if (this.m_nExplosionCounter == 0) {
            this.ExplosionUpdate();
            this.m_nExplosionCounter = 3;
        }
        if (this.m_nBigChunkGoneCounter > 0) {
            --this.m_nBigChunkGoneCounter;
            if (this.m_nBigChunkGoneCounter == 0) {
                super.m_sprites[24].show(false);
            }
        }
        if (this.m_nAllBlocksGoneCounter > 0) {
            --this.m_nAllBlocksGoneCounter;
            if (this.m_nAllBlocksGoneCounter <= 0) {
                super.m_sprites[23].show(false);
                super.m_sprites[22].show(false);
            }
        }
        if (this.m_nState == 5 || this.m_nState == 4) {
            for (int nRow = this.m_nRows - 1; nRow > 0; --nRow) {
                for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                    final int nBlockFrame = this.getBlockSprite(nRow, nColumn).getFrame();
                    if (nBlockFrame >= 14 && nBlockFrame < 18) {
                        this.getBlockSprite(nRow, nColumn).setFrame(nBlockFrame + 1);
                    }
                }
            }
        }
        if (this.m_nState == 1) {
            if (super.m_nFrameCount % 16 == 0 && this.m_nScore > this.m_nScoreSent) {
                this.setEvent(new Event(this, 1001, "score"));
            }
            for (int nRow = this.m_nRows - 1; nRow >= 0; --nRow) {
                for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                    final int nBlockFrame = this.getBlockSprite(nRow, nColumn).getFrame();
                    if (nBlockFrame >= 4) {
                        if (nBlockFrame % 2 == 0) {
                            this.getBlockSprite(nRow, nColumn).setFrame(nBlockFrame + 1);
                        }
                        else {
                            this.getBlockSprite(nRow, nColumn).setFrame(nBlockFrame - 1);
                        }
                    }
                }
            }
        }
        switch (this.m_nState) {
            case 1: {
                if (this.m_nCyclerRow != -1) {
                    --this.m_nCyclerCounter;
                    if (this.m_nCyclerCounter == 0) {
                        this.m_nGrid[this.m_nCyclerColumn][this.m_nCyclerRow] = (this.m_nGrid[this.m_nCyclerColumn][this.m_nCyclerRow] + 1) % this.m_nColors;
                        this.getBlockSprite(this.m_nCyclerRow, this.m_nCyclerColumn).setFrame(this.m_nGrid[this.m_nCyclerColumn][this.m_nCyclerRow]);
                        this.m_nCyclerCounter = 10;
                    }
                }
                if ((this.m_bDropping || this.m_bShifting) && this.AllBlocksHaveReachedDestination()) {
                    Label_0546: {
                        if (this.m_bDropping) {
                            this.m_bDropping = false;
                            Label_0523: {
                                if (!this.CollapseOneRow()) {
                                    this.playSound(2);
                                    break Label_0523;
                                }
                                break Label_0523;
                            }
                            break Label_0546;
                        }
                        this.m_bShifting = false;
                        Label_0545: {
                            if (!this.ShiftOneColumn()) {
                                this.playSound(4);
                                break Label_0545;
                            }
                            break Label_0545;
                        }
                        break Label_0546;
                    }
                    goto Label_0547;
                }
                --this.m_nTimingCounter;
                if (this.m_nTimingCounter == 0) {
                    if (this.m_nNewBlocksFilled < this.m_nColumns) {
                        boolean bColorBomb = false;
                        boolean bSuperBomb = false;
                        if ((this.m_nLevel > 6 && Math.abs(this.m_RNG.nextInt()) % 125 == 0) || (this.m_nLevel > 3 && this.m_nLevel < 6 && Math.abs(this.m_RNG.nextInt()) % 150 == 0) || (this.m_nLevel > 1 && this.m_nLevel < 4 && Math.abs(this.m_RNG.nextInt()) % 175 == 0)) {
                            bSuperBomb = true;
                        }
                        else if ((this.m_nLevel > 5 && Math.abs(this.m_RNG.nextInt()) % 75 == 0) || (this.m_nLevel > 3 && this.m_nLevel < 6 && Math.abs(this.m_RNG.nextInt()) % 125 == 0)) {
                            bColorBomb = true;
                        }
                        else if (this.m_nCyclerRow == -1 && this.m_nLevel > 5 && Math.abs(this.m_RNG.nextInt()) % 10 == 0) {
                            this.m_nCyclerCounter = 10;
                            this.m_nCyclerRow = 0;
                            this.m_nCyclerColumn = this.m_nNewBlocksFilled;
                        }
                        final int nColorValue = Math.abs(this.m_RNG.nextInt()) % this.m_nColors;
                        if (bColorBomb) {
                            this.m_nGrid[this.m_nNewBlocksFilled][0] = 4 + nColorValue * 2;
                        }
                        else if (bSuperBomb) {
                            this.m_nGrid[this.m_nNewBlocksFilled][0] = 12;
                        }
                        else {
                            this.m_nGrid[this.m_nNewBlocksFilled][0] = nColorValue;
                        }
                        this.m_blockSprites[this.m_nNewBlocksFilled].setFrame(this.m_nGrid[this.m_nNewBlocksFilled][0]);
                        this.m_blockSprites[this.m_nNewBlocksFilled].show(true);
                        ++this.m_nNewBlocksFilled;
                        this.playSound(9);
                    }
                    else if (!this.m_bDropping && !this.m_bShifting) {
                        boolean bGameShouldEnd = false;
                        for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                            if (this.m_nGrid[nColumn][this.m_nRows - 1] != -1) {
                                bGameShouldEnd = true;
                            }
                        }
                        this.playSound(8);
                        for (int nRow = this.m_nRows - 1; nRow > 0; --nRow) {
                            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                                if (nColumn == this.m_nCyclerColumn && this.m_nCyclerRow == nRow - 1) {
                                    ++this.m_nCyclerRow;
                                }
                                this.m_nGrid[nColumn][nRow] = this.m_nGrid[nColumn][nRow - 1];
                                if (this.m_nGrid[nColumn][nRow] != -1) {
                                    this.getBlockSprite(nRow, nColumn).setFrame(this.m_nGrid[nColumn][nRow]);
                                    this.getBlockSprite(nRow, nColumn).show(true);
                                }
                                else {
                                    this.getBlockSprite(nRow, nColumn).show(false);
                                }
                            }
                        }
                        for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                            this.m_nGrid[nColumn][0] = -1;
                            this.getBlockSprite(0, nColumn).show(false);
                        }
                        this.m_nNewBlocksFilled = 0;
                        if (!bGameShouldEnd) {
                            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                                if (this.m_nGrid[nColumn][this.m_nRows - 1] != -1) {
                                    this.playSound(13);
                                    break;
                                }
                            }
                        }
                        if (bGameShouldEnd) {
                            this.SwitchState(2);
                        }
                        else if (this.NextLine()) {
                            this.SwitchState(4);
                            this.m_nBonusRow = this.m_nRows - 1;
                            this.m_nBonusColumn = 0;
                            this.m_bCompleteRowBonus = true;
                        }
                    }
                    this.m_nTimingCounter = this.m_nTimingSpeed;
                    break;
                }
                break;
            }
            case 4: {
                boolean bNoBonusBlockShown = true;
                final int nRowBonus = (this.m_nLevel - 1) * 100 + (this.m_nColors - 3) * (this.m_nLevel - 4) * 200;
                while (bNoBonusBlockShown && this.m_nBonusRow > 0 && this.m_nBonusColumn < this.m_nColumns) {
                    if (this.m_nGrid[this.m_nBonusColumn][this.m_nBonusRow] == -1) {
                        this.getBlockSprite(this.m_nBonusRow, this.m_nBonusColumn).setFrame(14);
                        this.getBlockSprite(this.m_nBonusRow, this.m_nBonusColumn).show(true);
                        bNoBonusBlockShown = false;
                        this.playSound(7);
                        this.m_nScore += this.m_nLevel;
                        ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                    }
                    else {
                        this.m_bCompleteRowBonus = false;
                    }
                    if (this.m_nBonusColumn == this.m_nColumns - 1) {
                        if (this.m_bCompleteRowBonus) {
                            this.playSound(6);
                            this.m_nScore += nRowBonus;
                            ((NumberSprite)super.m_sprites[5]).setNumber(this.m_nScore);
                            ((TextSprite)super.m_sprites[28]).setText(super.m_strStrings[0] + " + " + this.formatNumber(nRowBonus));
                            super.m_sprites[28].setPosition(20, this.m_nBottomY - this.m_nBonusRow * this.m_nBlockDim);
                            super.m_sprites[28].show(true);
                        }
                        else {
                            super.m_sprites[28].show(false);
                        }
                        this.m_nBonusColumn = 0;
                        --this.m_nBonusRow;
                        this.m_bCompleteRowBonus = true;
                    }
                    else {
                        ++this.m_nBonusColumn;
                    }
                }
                if (bNoBonusBlockShown) {
                    this.SwitchState(5);
                    this.CollapseAllBlocks(true);
                    break;
                }
                break;
            }
            case 5: {
                --this.m_nLevelCompleteFramesCounter;
                if (this.m_nLevelCompleteFramesCounter == 0) {
                    this.CollapseAllBlocks(false);
                    this.SwitchState(6);
                    break;
                }
                break;
            }
            case 6: {
                --this.m_nLevelCompleteFramesCounter;
                if (this.m_nLevelCompleteFramesCounter == 0) {
                    if (this.m_nLevel > this.m_nLastLevel && this.m_nLastLevel > 0) {
                        if (this.m_strBuyURL != null) {
                            ((ButtonSprite)super.m_sprites[33]).enable(true);
                            ((ButtonSprite)super.m_sprites[32]).enable(true);
                            super.m_sprites[31].show(true);
                            this.m_sprLevelComplete.show(false);
                            this.m_nState = 8;
                        }
                        else {
                            this.SwitchState(0);
                        }
                        System.out.println("play=false submit highscore(2)");
                        ZylomDataGather.GetHelper().indicateGameFinished();
                        ZylomDataGather.GetHelper().stopPlayTimer();
                        ZylomDataGather.playing = false;
                        ZylomDataGather.GetHelper().submitHighscore(this.m_nScore, this.m_nLevel);
                    }
                    else {
                        this.ResetGrid();
                        this.SwitchState(1);
                        ((NumberSprite)super.m_sprites[7]).setNumber(this.m_nLinesLeft);
                        ((NumberSprite)super.m_sprites[6]).setNumber(this.m_nLevel);
                    }
                    break;
                }
                break;
            }
            case 2: {
                --this.m_nGameOverFramesCounter;
                if (this.m_nGameOverFramesCounter == 0) {
                    this.m_sprGameOver.show(false);
                    if (this.m_strBuyURL != null) {
                        this.SwitchState(8);
                    }
                    else {
                        this.SwitchState(0);
                    }
                    break;
                }
                break;
            }
        }
        super.nextFrame();
    }
    
    private void positionBlock(final AnimateSprite spr, final int row, final int column, final boolean bAnimate) {
        final int x = this.m_nLeftX + column * this.m_nBlockDim;
        final int y = this.m_nBottomLeftBlockY - row * this.m_nBlockDim;
        if (bAnimate) {
            spr.setDestination(x, y, 2);
        }
        else {
            spr.setPosition(x, y);
        }
    }
    
    public boolean processEvent() {
        if (super.m_saveEvent != null) {
            final Event evt = super.m_saveEvent;
            if (evt.id == 501) {
                this.handleMouseDown(evt);
            }
            else if (evt.id == 503 || evt.id == 506) {
                this.handleMouseMove(evt);
            }
            else if (evt.id == 1001) {
                if (this.m_nScore > this.m_nScoreSent && this.m_nState != 0 && this.m_nState != 2 && this.m_nState != 8) {
                    this.m_nScoreSent = this.m_nScore;
                }
            }
        }
        return true;
    }
    
    public void setImages(final Image[] image, final Object[] clips) {
        int nDataFileIndex = 0;
        super.m_nTempSprite = 36;
        super.m_clips = clips;
        if (super.m_nDataFile == null) {
            return;
        }
        final Dimension d = this.size();
        this.setImageSize(d.width, d.height);
        int nImageIndex = 0;
        int iSprite = 0;
        this.m_nBlockDim = super.m_nDataFile[nDataFileIndex++];
        this.m_nRows = super.m_nDataFile[nDataFileIndex++];
        this.m_nColumns = super.m_nDataFile[nDataFileIndex++];
        this.m_nLeftX = super.m_nDataFile[nDataFileIndex++];
        this.m_nRightX = this.m_nLeftX + this.m_nColumns * this.m_nBlockDim - 1;
        this.m_nBottomLeftBlockY = super.m_nDataFile[nDataFileIndex++];
        this.m_nTopY = this.m_nBottomLeftBlockY - (this.m_nRows - 1) * this.m_nBlockDim + 1;
        this.m_nBottomY = this.m_nBottomLeftBlockY + this.m_nBlockDim - 1;
        this.m_nGrid = new int[this.m_nColumns][this.m_nRows];
        this.m_nRemove = new boolean[this.m_nColumns][this.m_nRows];
        this.m_RNG = new Random();
        Sprite aSprite = new Sprite();
        aSprite.setImage(image[nImageIndex++]);
        aSprite.setPosition(0, 0);
        final FancyContainer fancy = new FancyContainer(d.width, d.height, this);
        fancy.add(aSprite);
        aSprite.getFancy();
        aSprite.flushImage();
        fancy.setBGColor(Color.white);
        super.m_sprites[2] = fancy;
        aSprite = new Sprite();
        aSprite.setImage(image[nImageIndex++]);
        aSprite.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        super.m_sprites[13] = aSprite;
        (this.m_sprClickStart = new AnimateSprite(image[nImageIndex++], 1, 1, null)).setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        this.m_sprClickStart.setShadowOffset(10, 10, true);
        this.m_sprClickStart.getFancy();
        this.m_sprClickStart.flushImage();
        (this.m_sprGameOver = new AnimateSprite(image[nImageIndex++], 1, 1, null)).setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        this.m_sprGameOver.setShadowOffset(10, 10, true);
        this.m_sprGameOver.show(false);
        this.m_sprGameOver.getFancy();
        this.m_sprGameOver.flushImage();
        (this.m_sprLevelComplete = new AnimateSprite(image[nImageIndex++], 1, 1, null)).setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        this.m_sprLevelComplete.setShadowOffset(10, 10, true);
        this.m_sprLevelComplete.show(false);
        this.m_sprLevelComplete.getFancy();
        this.m_sprLevelComplete.flushImage();
        aSprite = new Sprite();
        aSprite.setImage(image[nImageIndex++]);
        aSprite.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        (super.m_sprites[23] = aSprite).show(false);
        aSprite = new Sprite();
        aSprite.setImage(image[nImageIndex++]);
        aSprite.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        super.m_sprites[29] = aSprite;
        this.m_blockSprites = new AnimateSprite[this.m_nRows * this.m_nColumns * 2];
        AnimateSprite anAnimateSprite = new AnimateSprite(image[nImageIndex], 1, 22, null);
        anAnimateSprite.show(false);
        anAnimateSprite.getFancy();
        anAnimateSprite.flushImage();
        iSprite = 0;
        this.FIRST_EXPLODER = this.m_nRows * this.m_nColumns;
        while (iSprite < this.m_nRows * this.m_nColumns) {
            aSprite = anAnimateSprite.copy();
            aSprite.setPosition(this.m_nLeftX + iSprite % this.m_nColumns * this.m_nBlockDim, this.m_nBottomLeftBlockY - iSprite / this.m_nColumns * this.m_nBlockDim);
            aSprite.show(false);
            fancy.add(aSprite);
            this.m_blockSprites[this.FIRST_EXPLODER + iSprite] = (AnimateSprite)aSprite;
            ++iSprite;
        }
        for (iSprite = 0; iSprite < this.m_nRows * this.m_nColumns; ++iSprite) {
            aSprite = anAnimateSprite.copy();
            aSprite.setPosition(this.m_nLeftX + iSprite % this.m_nColumns * this.m_nBlockDim, this.m_nBottomLeftBlockY - iSprite / this.m_nColumns * this.m_nBlockDim);
            fancy.add(aSprite);
            this.m_blockSprites[iSprite] = (AnimateSprite)aSprite;
        }
        ++nImageIndex;
        this.m_nFilledRows = this.m_nRows;
        this.m_nColors = 4;
        this.ResetGrid();
        fancy.add(this.m_sprClickStart);
        fancy.add(this.m_sprLevelComplete);
        fancy.add(this.m_sprGameOver);
        final int nLeftNumberX = super.m_nDataFile[nDataFileIndex++];
        NumberSprite aNumberSprite = new NumberSprite(false, false);
        aNumberSprite.setImage(image[nImageIndex]);
        aNumberSprite.setNumber(this.m_nScore);
        aNumberSprite.setPosition(nLeftNumberX, super.m_nDataFile[nDataFileIndex++]);
        aNumberSprite.setAlignment(1);
        super.m_sprites[5] = aNumberSprite;
        aNumberSprite = new NumberSprite(false, false);
        aNumberSprite.setImage(image[nImageIndex]);
        aNumberSprite.setNumber(this.m_nLevel);
        aNumberSprite.setPosition(nLeftNumberX, super.m_nDataFile[nDataFileIndex++]);
        aNumberSprite.setAlignment(1);
        super.m_sprites[6] = aNumberSprite;
        aNumberSprite = new NumberSprite(false, false);
        aNumberSprite.setImage(image[nImageIndex++]);
        aNumberSprite.setNumber(this.m_nLinesLeft);
        aNumberSprite.setPosition(nLeftNumberX, super.m_nDataFile[nDataFileIndex++]);
        aNumberSprite.setAlignment(1);
        super.m_sprites[7] = aNumberSprite;
        anAnimateSprite = new AnimateSprite(image[nImageIndex++], 1, 2, null);
        anAnimateSprite.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        anAnimateSprite.show(false);
        anAnimateSprite.setFrame(0);
        super.m_sprites[9] = anAnimateSprite;
        this.m_FontSansSerif20Point = new Font("Sans Serif", 0, 20);
        this.m_FontSansSerif20PointMetrics = this.getFontMetrics(this.m_FontSansSerif20Point);
        (super.m_sprites[28] = new TextSprite(this.m_FontSansSerif20Point, Color.orange, Color.black, true)).show(false);
        (super.m_sprites[24] = new TextSprite(this.m_FontSansSerif20Point, Color.orange, Color.black, true)).show(false);
        (super.m_sprites[22] = new TextSprite(this.m_FontSansSerif20Point, Color.orange, Color.black, true)).show(false);
        this.m_FontSansSerif40Point = new Font("Sans Serif", 0, 40);
        super.m_sprites[27] = new TextSprite(this.m_FontSansSerif40Point, Color.yellow, Color.black, true);
        ((TextSprite)super.m_sprites[27]).setText(super.m_strStrings[1]);
        super.m_sprites[27].setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
        nDataFileIndex += 2;
        super.m_sprites[27].show(false);
        this.m_strBuyURL = "";
        if (this.m_strBuyURL != null) {
            Sprite spr = new Sprite();
            spr.setImage(image[nImageIndex++]);
            spr.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
            spr.show(false);
            super.m_sprites[30] = spr;
            spr = new Sprite();
            spr.setImage(image[nImageIndex++]);
            spr.setPosition(super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1]);
            spr.show(false);
            super.m_sprites[31] = spr;
            nDataFileIndex += 2;
            spr = new ButtonSprite(image[nImageIndex++], super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1], super.m_nDataFile[nDataFileIndex + 2], super.m_nDataFile[nDataFileIndex + 3], super.m_nDataFile[nDataFileIndex + 4]);
            ((ButtonSprite)spr).enable(false);
            ((Sprite)(super.m_sprites[33] = spr)).setPosition(super.m_nDataFile[nDataFileIndex + 5], super.m_nDataFile[nDataFileIndex + 6]);
            nDataFileIndex += 7;
            spr = new ButtonSprite(image[nImageIndex++], super.m_nDataFile[nDataFileIndex], super.m_nDataFile[nDataFileIndex + 1], super.m_nDataFile[nDataFileIndex + 2], super.m_nDataFile[nDataFileIndex + 3], super.m_nDataFile[nDataFileIndex + 4]);
            ((ButtonSprite)spr).enable(false);
            ((Sprite)(super.m_sprites[32] = spr)).setPosition(super.m_nDataFile[nDataFileIndex + 5], super.m_nDataFile[nDataFileIndex + 6]);
            nDataFileIndex += 7;
        }
        else {
            nImageIndex += 4;
            nDataFileIndex += 16;
        }
        if (super.m_nDataFile.length > nDataFileIndex) {
            this.m_nLastLevel = super.m_nDataFile[nDataFileIndex++];
        }
        else {
            this.m_nLastLevel = 0;
        }
        this.SwitchState(0);
        super.setImages(image, clips);
    }
    
    private void togglepause() {
        boolean bShow = false;
        if (this.m_nState == 1) {
            if (ZylomDataGather.playing) {
                System.out.println("zylom pauzed");
                ZylomDataGather.GetHelper().stopPlayTimer();
            }
            this.SwitchState(7);
            ((AnimateSprite)super.m_sprites[9]).setFrame(1);
        }
        else if (this.m_nState == 7) {
            if (ZylomDataGather.playing) {
                System.out.println("zylom resumed play.");
                ZylomDataGather.GetHelper().startPlayTimer();
            }
            this.SwitchState(1);
            ((AnimateSprite)super.m_sprites[9]).setFrame(0);
            bShow = true;
        }
        super.m_sprites[27].show(!bShow);
        for (int nRow = 0; nRow < this.m_nRows; ++nRow) {
            for (int nColumn = 0; nColumn < this.m_nColumns; ++nColumn) {
                if (this.m_nGrid[nColumn][nRow] != -1) {
                    this.getBlockSprite(nRow, nColumn).show(bShow);
                }
            }
        }
    }
}
