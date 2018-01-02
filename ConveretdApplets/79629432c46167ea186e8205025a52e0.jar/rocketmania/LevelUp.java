// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyColor;
import sexy.gui.WidgetManager;
import java.util.Vector;
import sexy.gui.Widget;

public class LevelUp extends Widget
{
    static final int COINSPERROCKETUPGRADE = 5;
    static final int COINTRAVELTIME = 22;
    static final int COINLIFETIME = 33;
    static final int MESSAGE_UPGRADING_ROCKETS = 0;
    static final int MESSAGE_BONUS_POINTS = 1;
    static final int MESSAGE_NONE = 2;
    Vector mCoinAnimationVector;
    RocketManiaApplet mApplet;
    int mCreationCount;
    boolean mUpgradesMaxedOut;
    boolean mGameOver;
    int mNumRocketsToUpgrade;
    int mMessage;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
    }
    
    public boolean HasTransparencies() {
        return true;
    }
    
    public void Update() {
        super.Update();
        if (this.mApplet.mAd == null && this.mApplet.mDialogWidget == null) {
            if (this.mApplet.mBoard.UpdateFlames()) {
                this.MarkDirty();
            }
            if (this.mApplet.mBoard.mParticleSystem.Update(0.02f)) {
                this.MarkDirty();
            }
            int n = 0;
            boolean b = false;
            for (int i = 0; i < this.mCoinAnimationVector.size(); ++i) {
                final CoinAnimation coinAnimation = this.mCoinAnimationVector.elementAt(i);
                if (coinAnimation.mTick == 0) {
                    coinAnimation.nStartX = 56;
                    if (this.mUpgradesMaxedOut || this.mGameOver) {
                        this.mApplet.mBoard.mCoins = coinAnimation.mNumLeftAfterMove;
                    }
                    else {
                        final Board mBoard = this.mApplet.mBoard;
                        --mBoard.mCoins;
                    }
                }
                else if (coinAnimation.mTick == 22) {
                    if (coinAnimation.nUpgradeRow != -1) {
                        this.mApplet.mBoard.mProgression.UpgradeRocketAtRow(coinAnimation.nUpgradeRow);
                        this.mApplet.mBoard.mParticleSystem.CreateParticleEmitter(this.mApplet.mBoard.mParticleDatabase.mForegroundSplash, this.mApplet.mBoard.mParticleDatabase.mStar, Pickup.skCoinColours[0], coinAnimation.nEndX, coinAnimation.nEndY, 0.0f, 0.0f);
                        ++n;
                    }
                    else {
                        b = true;
                        this.mApplet.mBoard.mParticleSystem.CreateParticleEmitter(this.mApplet.mBoard.mParticleDatabase.mForegroundSplash, this.mApplet.mBoard.mParticleDatabase.mStar, new SexyColor(255, 32, 128), coinAnimation.nEndX, coinAnimation.nEndY, 0.0f, 0.0f);
                    }
                    if ((this.mUpgradesMaxedOut || this.mGameOver) && coinAnimation.nScoreAdd != 0) {
                        this.mApplet.mBoard.AddScore(coinAnimation.nScoreAdd);
                    }
                }
                if (++coinAnimation.mTick == 33) {
                    this.mCoinAnimationVector.removeElementAt(i);
                    --i;
                }
            }
            if (b) {
                this.mApplet.PlaySound(2);
            }
            if (n > 0) {
                this.mApplet.PlaySound(19);
                this.mApplet.mBoard.AddScreenShake(n / 2);
            }
            if (this.mNumRocketsToUpgrade == 0 && !this.mUpgradesMaxedOut && !this.mGameOver) {
                if (super.mUpdateCnt - this.mCreationCount > 1) {
                    this.mApplet.KillLevelUpScreen();
                }
            }
            else if (this.mCoinAnimationVector.size() == 0) {
                this.mApplet.KillLevelUpScreen();
            }
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mApplet.mAd == null && this.mApplet.mDialogWidget == null) {
            sexyGraphics.SetColor(Color.orange);
            sexyGraphics.SetFont(this.mApplet.mRes.mFont20Outlined);
            switch (this.mMessage) {
                case 0: {
                    sexyGraphics.DrawString("Upgrading", Board.GetColX(3) - sexyGraphics.GetFont().StringWidth("Upgrading") / 2, 100);
                    sexyGraphics.DrawString("Rockets", Board.GetColX(3) - sexyGraphics.GetFont().StringWidth("Rockets") / 2, 140);
                    break;
                }
                case 1: {
                    sexyGraphics.DrawString("Bonus Points!", Board.GetColX(3) - sexyGraphics.GetFont().StringWidth("Bonus Points!") / 2, 100);
                    break;
                }
            }
            for (int i = 0; i < this.mCoinAnimationVector.size(); ++i) {
                final CoinAnimation coinAnimation = this.mCoinAnimationVector.elementAt(i);
                if (coinAnimation.mTick >= 0 && coinAnimation.mTick <= 22) {
                    final double n = coinAnimation.mTick / 22.0;
                    Assert.assert(n >= 0.0 && n <= 1.0);
                    sexyGraphics.DrawImage(this.mApplet.mRes.GetImage(15), (int)(coinAnimation.nStartX + n * (coinAnimation.nEndX - coinAnimation.nStartX)) - 17, (int)(coinAnimation.nStartY + (-((1.0 - n) * (1.0 - n)) + 1.0) * (coinAnimation.nEndY - coinAnimation.nStartY)) - 17);
                }
                if (coinAnimation.mTick > 22 && coinAnimation.nRocketImage > 0) {
                    final double n2 = (coinAnimation.mTick - 22) / 11.0;
                    Assert.assert(n2 >= 0.0 && n2 <= 1.0);
                    sexyGraphics.SetColorizeImages(true);
                    sexyGraphics.SetColor(new Color((int)(n2 * 128.0) + 128, 255 - (int)(n2 * 128.0), (int)(n2 * 64.0) + 192));
                    sexyGraphics.DrawImage(this.mApplet.mRes.GetImage(coinAnimation.nRocketImage), 404, -16 + 34 * coinAnimation.nEndRow);
                    sexyGraphics.SetColorizeImages(false);
                }
            }
        }
    }
    
    LevelUp() {
        this.mCoinAnimationVector = new Vector();
        new CoinAnimation(0);
    }
    
    public LevelUp(final RocketManiaApplet mApplet, final boolean mGameOver) {
        super(mApplet.mWidgetManager);
        this.mCoinAnimationVector = new Vector();
        this.mApplet = mApplet;
        this.mCreationCount = super.mUpdateCnt;
        final int mCoins = this.mApplet.mBoard.mCoins;
        this.mUpgradesMaxedOut = this.mApplet.mBoard.mProgression.RocketsAllAtMaxUpgrade();
        if (!(this.mGameOver = mGameOver)) {
            this.mApplet.mBoard.Empty();
        }
        if (this.mUpgradesMaxedOut || this.mGameOver) {
            this.mNumRocketsToUpgrade = 0;
            double n = mCoins;
            double n2 = 0.0;
            if (mCoins > 0) {
                this.mMessage = 1;
                n2 = Math.max(15.0, n) / 15.0;
            }
            else {
                this.mMessage = 2;
            }
            for (int i = 0; i < mCoins; ++i) {
                if (i >= 15) {
                    return;
                }
                final CoinAnimation coinAnimation = new CoinAnimation(0 - i * 14);
                coinAnimation.nEndRow = -1;
                coinAnimation.nRocketImage = 0;
                coinAnimation.nUpgradeRow = -1;
                n -= n2;
                coinAnimation.mNumLeftAfterMove = (int)n;
                coinAnimation.mNumLeftAfterMove = Math.max(0, coinAnimation.mNumLeftAfterMove);
                coinAnimation.nStartX = 56;
                coinAnimation.nStartY = 177;
                coinAnimation.nEndX = 63 + mApplet.mBoard.mRand.Next() % 60 - 30;
                coinAnimation.nEndY = 88;
                if (i == 0) {
                    coinAnimation.nScoreAdd = mCoins * 500;
                }
                else {
                    coinAnimation.nScoreAdd = 0;
                }
                this.mCoinAnimationVector.addElement(coinAnimation);
            }
        }
        else {
            this.mNumRocketsToUpgrade = this.mApplet.mBoard.mProgression.PossiblyUpgradeRockets(mCoins, 0, -1);
            if (this.mNumRocketsToUpgrade > 0) {
                this.mMessage = 0;
            }
            else {
                this.mMessage = 2;
                this.mApplet.mHints.InstantHint(this.mApplet, 5);
            }
            for (int j = 0; j < this.mNumRocketsToUpgrade; ++j) {
                final int possiblyUpgradeRockets = this.mApplet.mBoard.mProgression.PossiblyUpgradeRockets(mCoins, 1, j);
                final int nEndX = Board.GetColX(6) + 34;
                final int nEndY = Board.GetRowY(possiblyUpgradeRockets) - 12;
                int n3 = 0;
                do {
                    final CoinAnimation coinAnimation2 = new CoinAnimation(0 - n3 * 25);
                    coinAnimation2.nStartX = 56;
                    coinAnimation2.nStartY = 177;
                    coinAnimation2.nEndX = nEndX;
                    coinAnimation2.nEndY = nEndY;
                    coinAnimation2.nEndRow = possiblyUpgradeRockets;
                    coinAnimation2.nRocketImage = Rocket.skRocketImages[this.mApplet.mBoard.mRockets[possiblyUpgradeRockets].mSize];
                    if (n3 == 4) {
                        coinAnimation2.nUpgradeRow = possiblyUpgradeRockets;
                    }
                    else {
                        coinAnimation2.nUpgradeRow = -1;
                    }
                    coinAnimation2.nScoreAdd = 0;
                    this.mCoinAnimationVector.addElement(coinAnimation2);
                } while (++n3 < 5);
            }
        }
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
    }
    
    public class CoinAnimation
    {
        int mTick;
        int nStartX;
        int nStartY;
        int nEndX;
        int nEndY;
        int nEndRow;
        int nUpgradeRow;
        int nScoreAdd;
        int nRocketImage;
        int mNumLeftAfterMove;
        
        public CoinAnimation(final int mTick) {
            LevelUp.this.getClass();
            this.mTick = mTick;
        }
    }
}
