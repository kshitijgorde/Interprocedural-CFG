// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import sexy.gui.SexyColor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.WidgetManager;
import sexy.gui.OutlineButtonWidget;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class GameWizard extends Widget implements ButtonListener
{
    DynomiteApplet mApplet;
    OutlineButtonWidget mNextButton;
    OutlineButtonWidget mBackButton;
    Button mEndlessButton;
    Button mStompedButton;
    Button mTutorialButton;
    Button mEasyButton;
    Button mNormalButton;
    Button mHardButton;
    int mLevel;
    static final int BUTTON_NEXT = 1;
    static final int BUTTON_BACK = 2;
    static final int BUTTON_ENDLESS = 3;
    static final int BUTTON_STOMPED = 4;
    static final int BUTTON_TUTORIAL = 5;
    static final int BUTTON_EASY = 6;
    static final int BUTTON_NORMAL = 7;
    static final int BUTTON_HARD = 8;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mNextButton);
        widgetManager.RemoveWidget(this.mBackButton);
        widgetManager.RemoveWidget(this.mEndlessButton);
        widgetManager.RemoveWidget(this.mStompedButton);
        widgetManager.RemoveWidget(this.mTutorialButton);
        widgetManager.RemoveWidget(this.mEasyButton);
        widgetManager.RemoveWidget(this.mNormalButton);
        widgetManager.RemoveWidget(this.mHardButton);
    }
    
    public void Update() {
        if (this.mLevel == 4) {
            this.MarkDirty();
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        switch (this.mLevel) {
            case 1: {
                final int n = 90;
                sexyGraphics.SetColor(new Color(43, 30, 0));
                sexyGraphics.FillRect(0, 0, super.mWidth, n);
                sexyGraphics.SetColor(new Color(86, 60, 1));
                sexyGraphics.FillRect(0, n, super.mWidth, super.mHeight - n);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                sexyGraphics.DrawString("Welcome to Dynomite!", super.mWidth / 2 - this.mApplet.mAnnounceFont.StringWidth("Welcome to Dynomite!") / 2, 30);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Choose the type of game you", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("Choose the type of game you") / 2, 50);
                sexyGraphics.DrawString("want to play, and click next!", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("Choose the type of game you") / 2, 65);
                if (this.mApplet.mGameType == 0) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 105, 255, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 110, new Rectangle(61, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Endless Puzzle", 116, 124);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("Pit your wits against an endless", 116, 134);
                sexyGraphics.DrawString("wall of eggs!  Watch the timekeeper...", 116, 144);
                sexyGraphics.DrawString("when his sand runs out, Whirley, will", 116, 154);
                sexyGraphics.DrawString("try to add an egg to the puzzle!", 116, 164);
                if (this.mApplet.mGameType == 1) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 175, 255, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 180, new Rectangle(0, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Stomped Puzzle", 116, 194);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("Clear screens of mind-bending egg", 116, 204);
                sexyGraphics.DrawString("arrangements!  But keep an eye on", 116, 214);
                sexyGraphics.DrawString("Mama's mood meter!  Every egg you", 116, 224);
                sexyGraphics.DrawString("fire makes her madder!", 116, 234);
                if (this.mApplet.mGameType == 2) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 245, 190, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 250, new Rectangle(122, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("How To Play", 116, 264);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("Learn to play Dynomite!", 116, 274);
                break;
            }
            case 2: {
                final int n2 = 80;
                sexyGraphics.SetColor(new Color(43, 30, 0));
                sexyGraphics.FillRect(0, 0, super.mWidth, n2);
                sexyGraphics.SetColor(new Color(86, 60, 1));
                sexyGraphics.FillRect(0, n2, super.mWidth, super.mHeight - n2);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                sexyGraphics.DrawString("Welcome to Dynomite!", super.mWidth / 2 - this.mApplet.mAnnounceFont.StringWidth("Welcome to Dynomite!") / 2, 30);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Select your skill level.  The harder", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("the game, the more points you'll score!") / 2, 50);
                sexyGraphics.DrawString("the game, the more points you'll score!", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("the game, the more points you'll score!") / 2, 65);
                final int n3 = 18;
                if (this.mApplet.mSkill == 0) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 105 - n3, 255, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 110 - n3, new Rectangle(183, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Easy", 116, 124 - n3);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("For beginners!  The challenges are", 116, 134 - n3);
                sexyGraphics.DrawString("kept to a minimum, but so are the", 116, 144 - n3);
                sexyGraphics.DrawString("opportunities for scoring.", 116, 154 - n3);
                if (this.mApplet.mSkill == 1) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 175 - n3, 255, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 180 - n3, new Rectangle(244, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Normal", 116, 194 - n3);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("Dynomite as it's meant to be", 116, 204 - n3);
                sexyGraphics.DrawString("played!  A good balance between", 116, 214 - n3);
                sexyGraphics.DrawString("difficulty and scoring.", 116, 224 - n3);
                if (this.mApplet.mSkill == 2) {
                    sexyGraphics.SetColor(new Color(129, 90, 2));
                    sexyGraphics.FillRect(45, 245 - n3, 255, 70);
                }
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[45], 50, 250 - n3, new Rectangle(305, 0, 61, 61));
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("Hard", 116, 264 - n3);
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawString("For heroes only!  The puzzle", 116, 274 - n3);
                sexyGraphics.DrawString("will make you sweat, but your", 116, 284 - n3);
                sexyGraphics.DrawString("score will soar and soar!", 116, 294 - n3);
                break;
            }
            case 3: {
                final int n4 = 85;
                sexyGraphics.SetColor(new Color(43, 30, 0));
                sexyGraphics.FillRect(0, 0, super.mWidth, n4);
                sexyGraphics.SetColor(new Color(86, 60, 1));
                sexyGraphics.FillRect(0, n4, super.mWidth, super.mHeight - n4);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                sexyGraphics.DrawString("HOW TO PLAY!", super.mWidth / 2 - this.mApplet.mAnnounceFont.StringWidth("HOW TO PLAY!") / 2, 30);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("This brief tutorial will explain", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 50);
                sexyGraphics.DrawString("the basics of Dynomite!", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 65);
                final SexyGraphics create = sexyGraphics.create();
                final Point point = new Point(20, 95);
                create.ClipRect(point.x, point.y, 114, 115);
                create.SetColor(new SexyColor(0, 0, 0, 255));
                create.FillRect(point.x, point.y, 114, 115);
                create.DrawImage(this.mApplet.mRes.mImages[54], point.x + 4, point.y + 4);
                create.DrawImage(this.mApplet.mRes.mImages[53], point.x, point.y);
                final SexyGraphics create2 = sexyGraphics.create();
                final Point point2 = new Point(220, 210);
                create2.ClipRect(point2.x, point2.y, 79, 81);
                create2.SetColor(new SexyColor(0, 0, 0, 255));
                create2.FillRect(point2.x, point2.y, 79, 81);
                this.DrawEgg(create2, point2.x + 8 + 12, point2.y + 8 - 23, 0, 0);
                this.DrawEgg(create2, point2.x + 8 + 12 + 23, point2.y + 8 - 23, 0, 0);
                this.DrawEgg(create2, point2.x + 8, point2.y + 8, 1, 0);
                this.DrawEgg(create2, point2.x + 8 + 23, point2.y + 8, 2, 0);
                this.DrawEgg(create2, point2.x + 8 + 23 + 23, point2.y + 8, 3, 0);
                this.DrawEgg(create2, point2.x + 8 + 12, point2.y + 8 + 23, 1, 0);
                this.DrawEgg(create2, point2.x + 8 + 12 + 23, point2.y + 8 + 23, 1, 0);
                this.DrawEgg(create2, point2.x + 8 + 12 + 12, point2.y + 8 + 23 + 23, 0, 0);
                create2.DrawImage(this.mApplet.mRes.mImages[52], point2.x, point2.y);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mTinyFont);
                sexyGraphics.DrawString("Use your mouse to aim and", 140, 120);
                sexyGraphics.DrawString("fire eggs from the slingshot.", 140, 134);
                sexyGraphics.DrawString("Make groups of three or more", 140, 160);
                sexyGraphics.DrawString("eggs to detonate them and", 140, 174);
                sexyGraphics.DrawString("score points!", 140, 188);
                sexyGraphics.DrawString("Break several groups in a row", 40, 230);
                sexyGraphics.DrawString("to accumulate point combos.", 40, 244);
                sexyGraphics.DrawString("Eggs left unsupported will fall", 40, 270);
                sexyGraphics.DrawString("down, multiplying your score!", 40, 284);
                break;
            }
            case 4: {
                final int n5 = 85;
                sexyGraphics.SetColor(new Color(43, 30, 0));
                sexyGraphics.FillRect(0, 0, super.mWidth, n5);
                sexyGraphics.SetColor(new Color(86, 60, 1));
                sexyGraphics.FillRect(0, n5, super.mWidth, super.mHeight - n5);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                sexyGraphics.DrawString("HOW TO PLAY!", super.mWidth / 2 - this.mApplet.mAnnounceFont.StringWidth("HOW TO PLAY!") / 2, 30);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("This brief tutorial will explain", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 50);
                sexyGraphics.DrawString("the basics of Dynomite!", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 65);
                final SexyGraphics create3 = sexyGraphics.create();
                final Point point3 = new Point(230, 95);
                create3.ClipRect(point3.x, point3.y, 79, 81);
                create3.SetColor(new SexyColor(0, 0, 0, 255));
                create3.FillRect(point3.x, point3.y, 79, 81);
                this.DrawEgg(create3, point3.x + 8 + 12, point3.y + 8 - 23, 3, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + 23, point3.y + 8 - 23, 0, 0);
                this.DrawEgg(create3, point3.x + 8, point3.y + 8, 4, 0);
                this.DrawEgg(create3, point3.x + 8 - 23 + 12, point3.y + 8 + 23, 0, 0);
                this.DrawEgg(create3, point3.x + 8 + 23, point3.y + 8, 1, 0);
                this.DrawEgg(create3, point3.x + 8 + 23 + 23, point3.y + 8, 3, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + (this.mApplet.mSidebar.mRand.Next() % 3 - 1), point3.y + 8 + 23 + (this.mApplet.mSidebar.mRand.Next() % 3 - 1), 1, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + 23, point3.y + 8 + 23, 3, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + 12, point3.y + 8 + 23 + 23, 0, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + 12 - 23, point3.y + 8 + 23 + 23, 1, 0);
                this.DrawEgg(create3, point3.x + 8 + 12 + 12 - 23 - 23, point3.y + 8 + 23 + 23, 1, 0);
                create3.DrawImage(this.mApplet.mRes.mImages[52], point3.x, point3.y);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mTinyFont);
                sexyGraphics.DrawString("Break shaking eggs to release", 40, 120);
                sexyGraphics.DrawString("a bonus baby and get a special", 40, 134);
                sexyGraphics.DrawString("bonus item!", 40, 148);
                sexyGraphics.DrawString("When a bonus item", 40, 176);
                sexyGraphics.DrawString("is on an egg, blast", 40, 190);
                sexyGraphics.DrawString("it to release a helper", 40, 204);
                sexyGraphics.DrawString("bonus!", 40, 218);
                sexyGraphics.SetColorizeImages(true);
                sexyGraphics.SetColor(new SexyColor(0, 0, 0, 255));
                this.DrawEgg(sexyGraphics, 179, 169, 1, 0);
                this.DrawEgg(sexyGraphics, 199, 199, 2, 0);
                this.DrawEgg(sexyGraphics, 219, 179, 2, 0);
                sexyGraphics.SetColorizeImages(false);
                this.DrawEgg(sexyGraphics, 175, 165, 1, 1);
                this.DrawEgg(sexyGraphics, 195, 195, 0, 2);
                this.DrawEgg(sexyGraphics, 215, 175, 2, 5);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 50, 240, new Rectangle(0, 0, 23, 24));
                sexyGraphics.DrawString("Lightbulb", 40, 275);
                sexyGraphics.DrawString("Guide", 48, 284);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 90, 240, new Rectangle(23, 0, 23, 24));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 105, 240, new Rectangle(46, 0, 23, 24));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 120, 240, new Rectangle(69, 0, 23, 24));
                sexyGraphics.DrawString("Score", 103, 275);
                sexyGraphics.DrawString("Multiplier", 95, 284);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 160, 240, new Rectangle(92, 0, 23, 24));
                sexyGraphics.DrawString("Lift", 164, 275);
                sexyGraphics.DrawString("Puzzle", 156, 284);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 200, 240, new Rectangle(115, 0, 23, 24));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 220, 240, new Rectangle(138, 0, 23, 24));
                sexyGraphics.DrawString("Move", 208, 275);
                sexyGraphics.DrawString("Walls", 209, 284);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 255, 240, new Rectangle(161, 0, 23, 24));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 270, 240, new Rectangle(184, 0, 23, 24));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], 285, 240, new Rectangle(207, 0, 23, 24));
                sexyGraphics.DrawString("Virtual", 266, 275);
                sexyGraphics.DrawString("Egg Points", 257, 284);
                break;
            }
            case 5: {
                final int n6 = 85;
                sexyGraphics.SetColor(new Color(43, 30, 0));
                sexyGraphics.FillRect(0, 0, super.mWidth, n6);
                sexyGraphics.SetColor(new Color(86, 60, 1));
                sexyGraphics.FillRect(0, n6, super.mWidth, super.mHeight - n6);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                sexyGraphics.DrawString("HOW TO PLAY!", super.mWidth / 2 - this.mApplet.mAnnounceFont.StringWidth("HOW TO PLAY!") / 2, 30);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.DrawString("This brief tutorial will explain", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 50);
                sexyGraphics.DrawString("the basics of Dynomite!", super.mWidth / 2 - this.mApplet.mSmallFont.StringWidth("This brief tutorial will explain") / 2, 65);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.SetFont(this.mApplet.mTinyFont);
                sexyGraphics.DrawString("In Endless puzzles, shoot", 140, 120);
                sexyGraphics.DrawString("Whirley before he gets to the", 140, 134);
                sexyGraphics.DrawString("top of the screen to prevent", 140, 148);
                sexyGraphics.DrawString("him from adding a new egg", 140, 162);
                sexyGraphics.DrawString("color to the puzzle!", 140, 176);
                final SexyGraphics create4 = sexyGraphics.create();
                final Point point4 = new Point(50, 100);
                create4.ClipRect(point4.x, point4.y, 79, 81);
                create4.SetColor(new SexyColor(0, 0, 0, 255));
                create4.FillRect(point4.x, point4.y, 79, 81);
                this.DrawEgg(create4, point4.x + 28, point4.y + 45, 1, 0);
                if (this.mApplet.mRes.mImageWhirley.IsReady()) {
                    create4.DrawImage(this.mApplet.mRes.mImageWhirley, point4.x + 15, point4.y + 10, new Rectangle(0, 0, 41, 42));
                }
                create4.DrawImage(this.mApplet.mRes.mImages[52], point4.x, point4.y);
                final SexyGraphics create5 = sexyGraphics.create();
                final Point point5 = new Point(220, 200);
                create5.ClipRect(point5.x, point5.y, 79, 81);
                create5.SetColor(new SexyColor(0, 0, 0, 255));
                create5.FillRect(point5.x, point5.y, 79, 81);
                create5.DrawImage(this.mApplet.mRes.mImages[47], point5.x + 10, point5.y);
                create5.DrawImage(this.mApplet.mRes.mImages[52], point5.x, point5.y);
                int n7 = 210;
                sexyGraphics.DrawString("In Stomped puzzles, clear the", 40, n7);
                n7 += 14;
                sexyGraphics.DrawString("entire puzzle to advance.  But", 40, n7);
                n7 += 14;
                sexyGraphics.DrawString("watch Mama Brontosaurus'", 40, n7);
                n7 += 14;
                sexyGraphics.DrawString("mood meter!  Every egg you", 40, n7);
                n7 += 14;
                sexyGraphics.DrawString("fire makes her madder, until", 40, n7);
                n7 += 14;
                sexyGraphics.DrawString("she puts her foot down!", 40, n7);
                n7 += 14;
                break;
            }
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[15], 0, 0);
    }
    
    public GameWizard(final DynomiteApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mApplet = mApplet;
        this.mNextButton = new OutlineButtonWidget(super.mWidgetManager, 1, this);
        this.mNextButton.mDoFinger = true;
        this.mNextButton.mFont = this.mApplet.mSmallFont;
        this.mNextButton.mLabel = "Next";
        this.mNextButton.SetColors(DynomiteApplet.mButtonColors);
        this.mNextButton.Resize(360, 300, 60, 18);
        this.mBackButton = new OutlineButtonWidget(super.mWidgetManager, 2, this);
        this.mBackButton.mDoFinger = true;
        this.mBackButton.mFont = this.mApplet.mSmallFont;
        this.mBackButton.mLabel = "Back";
        this.mBackButton.SetColors(DynomiteApplet.mButtonColors);
        this.mBackButton.Resize(290, 300, 60, 18);
        this.mEndlessButton = new Button(this, super.mWidgetManager, 3, this);
        this.mEndlessButton.mDoFinger = true;
        this.mEndlessButton.Resize(148, 105, 255, 70);
        this.mStompedButton = new Button(this, super.mWidgetManager, 4, this);
        this.mStompedButton.mDoFinger = true;
        this.mStompedButton.Resize(148, 175, 255, 70);
        this.mTutorialButton = new Button(this, super.mWidgetManager, 5, this);
        this.mTutorialButton.mDoFinger = true;
        this.mTutorialButton.Resize(148, 245, 190, 70);
        final int n = 18;
        this.mEasyButton = new Button(this, super.mWidgetManager, 6, this);
        this.mEasyButton.mDoFinger = true;
        this.mEasyButton.Resize(148, 105 - n, 255, 70);
        this.mNormalButton = new Button(this, super.mWidgetManager, 7, this);
        this.mNormalButton.mDoFinger = true;
        this.mNormalButton.Resize(148, 175 - n, 255, 70);
        this.mHardButton = new Button(this, super.mWidgetManager, 8, this);
        this.mHardButton.mDoFinger = true;
        this.mHardButton.Resize(148, 245 - n, 255, 70);
        this.mLevel = 1;
        this.SetupLevel();
    }
    
    public void ButtonPress(final int n) {
        this.mApplet.PlaySound(5);
        switch (this.mLevel) {
            case 1: {
                final int mGameType = this.mApplet.mGameType;
                boolean b = false;
                switch (n) {
                    case 3: {
                        this.mApplet.mGameType = 0;
                        b = true;
                        break;
                    }
                    case 4: {
                        this.mApplet.mGameType = 1;
                        b = true;
                        break;
                    }
                    case 5: {
                        this.mApplet.mGameType = 2;
                        b = true;
                        break;
                    }
                }
                if (b && mGameType == this.mApplet.mGameType) {
                    this.ButtonDepress(1);
                    break;
                }
                break;
            }
            case 2: {
                final int mSkill = this.mApplet.mSkill;
                boolean b2 = false;
                switch (n) {
                    case 6: {
                        this.mApplet.mSkill = 0;
                        b2 = true;
                        break;
                    }
                    case 7: {
                        this.mApplet.mSkill = 1;
                        b2 = true;
                        break;
                    }
                    case 8: {
                        this.mApplet.mSkill = 2;
                        b2 = true;
                        break;
                    }
                }
                if (b2 && mSkill == this.mApplet.mSkill) {
                    this.ButtonDepress(1);
                    break;
                }
                break;
            }
        }
        this.MarkDirty();
    }
    
    public void DrawEgg(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3, final int n4) {
        if (n3 < 0) {
            return;
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[10], n, n2, new Rectangle(n3 * 24, 0, 24, 27));
        if (n4 > 0) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[38], n + 3, n2 + 3, new Rectangle((n4 - 1) * 18, 0, 18, 20));
        }
    }
    
    public void SetupLevel() {
        switch (this.mLevel) {
            case 1: {
                this.mNextButton.mLabel = "Next";
                this.mNextButton.SetVisible(true);
                this.mBackButton.SetVisible(false);
                this.mEndlessButton.SetVisible(true);
                this.mStompedButton.SetVisible(true);
                this.mTutorialButton.SetVisible(true);
                this.mEasyButton.SetVisible(false);
                this.mNormalButton.SetVisible(false);
                this.mHardButton.SetVisible(false);
            }
            case 2: {
                this.mNextButton.mLabel = "Play!";
                this.mNextButton.SetVisible(true);
                this.mBackButton.SetVisible(true);
                this.mEndlessButton.SetVisible(false);
                this.mStompedButton.SetVisible(false);
                this.mTutorialButton.SetVisible(false);
                this.mEasyButton.SetVisible(true);
                this.mNormalButton.SetVisible(true);
                this.mHardButton.SetVisible(true);
            }
            case 3:
            case 4: {
                this.mNextButton.mLabel = "More!";
                this.mNextButton.SetVisible(true);
                this.mBackButton.SetVisible(true);
                this.mEndlessButton.SetVisible(false);
                this.mStompedButton.SetVisible(false);
                this.mTutorialButton.SetVisible(false);
                this.mEasyButton.SetVisible(false);
                this.mNormalButton.SetVisible(false);
                this.mHardButton.SetVisible(false);
            }
            case 5: {
                this.mNextButton.mLabel = "Okay!";
                this.mNextButton.SetVisible(true);
                this.mBackButton.SetVisible(true);
                this.mEndlessButton.SetVisible(false);
                this.mStompedButton.SetVisible(false);
                this.mTutorialButton.SetVisible(false);
                this.mEasyButton.SetVisible(false);
                this.mNormalButton.SetVisible(false);
                this.mHardButton.SetVisible(false);
            }
            default: {}
        }
    }
    
    public void ButtonDepress(final int n) {
        switch (n) {
            case 1: {
                if (this.mLevel == 1 && this.mApplet.mGameType == 2) {
                    this.mLevel = 3;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel == 3) {
                    this.mLevel = 4;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel == 4) {
                    this.mLevel = 5;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel == 5) {
                    this.mLevel = 1;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel == 2) {
                    this.mApplet.NewGame();
                    break;
                }
                this.mLevel = 2;
                this.SetupLevel();
                break;
            }
            case 2: {
                if (this.mLevel == 2) {
                    this.mLevel = 1;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel == 3) {
                    this.mLevel = 1;
                    this.SetupLevel();
                    break;
                }
                if (this.mLevel > 3) {
                    --this.mLevel;
                    this.SetupLevel();
                    break;
                }
                --this.mLevel;
                break;
            }
        }
        this.MarkDirty();
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        widgetManager.AddWidget(this.mNextButton);
        widgetManager.AddWidget(this.mBackButton);
        widgetManager.AddWidget(this.mEndlessButton);
        widgetManager.AddWidget(this.mStompedButton);
        widgetManager.AddWidget(this.mTutorialButton);
        widgetManager.AddWidget(this.mEasyButton);
        widgetManager.AddWidget(this.mNormalButton);
        widgetManager.AddWidget(this.mHardButton);
    }
    
    public void ButtonDownTick(final int n) {
    }
}
