// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.awt.Color;
import java.awt.Rectangle;
import sexy.gui.SexyGraphics;
import java.awt.Point;

public class Animator
{
    static final int ANIM_YOLK = 0;
    static final int ANIM_FALLEGG = 1;
    static final int ANIM_SHELL = 2;
    static final int ANIM_TEXT = 3;
    static final int ANIM_DYNAMITESTICK = 4;
    static final int ANIM_BOOM = 5;
    static final int ANIM_BABY = 6;
    static final int ANIM_BONUS = 7;
    static final int ANIM_STAR = 8;
    Board mBoard;
    int mType;
    double mPosX;
    double mPosY;
    double mXDir;
    double mYDir;
    int mValue;
    int mValue2;
    int mCountdown;
    String mText;
    int mTextOscillateCountdown;
    boolean mTextOscillate;
    boolean mInitialized;
    
    boolean Update() {
        switch (this.mType) {
            case 8: {
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                if (--this.mCountdown < 0) {
                    return false;
                }
                break;
            }
            case 7: {
                if (!this.mInitialized) {
                    final Point getBonusPoint = this.mBoard.GetBonusPoint();
                    this.mCountdown = (int)Math.sqrt((getBonusPoint.x - this.mPosX) * (getBonusPoint.x - this.mPosX) + (getBonusPoint.y - this.mPosY) * (getBonusPoint.y - this.mPosY));
                    this.mXDir = (float)(getBonusPoint.x - this.mPosX) / this.mCountdown;
                    this.mYDir = (float)(getBonusPoint.y - this.mPosY) / this.mCountdown;
                    this.mYDir /= this.mXDir;
                    this.mYDir *= 10.0;
                    this.mXDir = 10.0;
                    this.mCountdown = (int)((getBonusPoint.x - this.mPosX) / 10.0);
                    this.mInitialized = true;
                }
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                if (--this.mCountdown < 0) {
                    this.mBoard.AddBonus(this.mValue);
                    return false;
                }
                break;
            }
            case 6: {
                if (--this.mCountdown > 0) {
                    break;
                }
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                this.mYDir *= 1.15;
                if (this.mPosY > 380.0) {
                    this.mBoard.NewRandomBonus();
                    this.mBoard.mApplet.PlaySound(10);
                    return false;
                }
                break;
            }
            case 0:
            case 1:
            case 4: {
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                this.mYDir *= 1.15;
                if (this.mPosY > 380.0) {
                    return false;
                }
                break;
            }
            case 2: {
                if (!this.mInitialized) {
                    switch (this.mValue2) {
                        case 0: {
                            this.mXDir = -3.5;
                            this.mYDir = -5.0;
                            break;
                        }
                        case 1: {
                            this.mXDir = 3.5;
                            this.mYDir = -5.0;
                            break;
                        }
                        case 2: {
                            this.mXDir = -3.0;
                            this.mYDir = 1.0;
                            break;
                        }
                        case 3: {
                            this.mXDir = 3.0;
                            this.mYDir = 1.0;
                            break;
                        }
                        default: {
                            this.mYDir = 5.0;
                            break;
                        }
                    }
                    this.mYDir += this.mBoard.mRack.mRand.Next() % 10.0f / 10.0;
                    this.mInitialized = true;
                }
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                this.mXDir *= 0.99;
                ++this.mYDir;
                if (this.mPosY > 350.0) {
                    return false;
                }
                break;
            }
            case 5: {
                if (--this.mCountdown <= 0) {
                    return false;
                }
                break;
            }
            case 3: {
                if (--this.mTextOscillateCountdown < 0) {
                    if (this.mTextOscillate) {
                        this.mTextOscillate = false;
                    }
                    else {
                        this.mTextOscillate = true;
                    }
                    this.mTextOscillateCountdown = 10;
                }
                this.mPosX += this.mXDir;
                this.mPosY += this.mYDir;
                this.mBoard.MarkDirty();
                if (--this.mCountdown <= 0) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    void Draw(final SexyGraphics sexyGraphics) {
        switch (this.mType) {
            case 0: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mImages[17], (int)this.mPosX - 11, (int)this.mPosY - 18);
            }
            case 1: {
                this.mBoard.mRack.DrawEgg(sexyGraphics, (int)this.mPosX - 12, (int)this.mPosY - 14, this.mValue, this.mValue2);
            }
            case 2: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mEggShell[this.mValue], (int)this.mPosX - 12, (int)this.mPosY - 14, new Rectangle(this.mValue2 * 24, 0, 24, 27));
            }
            case 5: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mImages[21], (int)this.mPosX - 21, (int)this.mPosY - 26);
            }
            case 3: {
                sexyGraphics.SetFont(this.mBoard.mApplet.mAnnounceFont);
                sexyGraphics.SetColor(new Color(0, 0, 0));
                sexyGraphics.DrawString(this.mText, (int)(this.mPosX - sexyGraphics.GetFont().StringWidth(this.mText) / 2) + 2, (int)this.mPosY + 2);
                if (this.mTextOscillate) {
                    sexyGraphics.SetColor(new Color(128, 128, 255));
                }
                else {
                    sexyGraphics.SetColor(new Color(255, 255, 0));
                }
                sexyGraphics.DrawString(this.mText, (int)(this.mPosX - sexyGraphics.GetFont().StringWidth(this.mText) / 2), (int)this.mPosY);
            }
            case 4: {
                this.mBoard.mRack.DrawDynamite(sexyGraphics, (int)this.mPosX - 12, (int)this.mPosY - 14, this.mValue);
            }
            case 6: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mImages[37], (int)this.mPosX - 22, (int)this.mPosY - 22, new Rectangle(this.mValue * 44, 0, 44, 44));
            }
            case 7: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mImages[39], (int)this.mPosX - 12, (int)this.mPosY - 12, new Rectangle((this.mValue - 1) * 23, 0, 23, 24));
            }
            case 8: {
                sexyGraphics.DrawImage(this.mBoard.mApplet.mRes.mImages[40], (int)this.mPosX - 4, (int)this.mPosY - 4, new Rectangle(this.mValue * 8, 0, 8, 8));
            }
            default: {}
        }
    }
    
    Animator(final Board mBoard, final int mType) {
        this.mBoard = mBoard;
        this.mType = mType;
        this.mBoard.MarkDirty();
        this.mTextOscillateCountdown = 10;
        this.mTextOscillate = false;
        this.mInitialized = false;
    }
}
