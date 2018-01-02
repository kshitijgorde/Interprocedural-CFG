// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.particle.ParticleEmitter;
import nc.particle.ParticleElement;
import nc.ADSR;
import sexy.gui.SexyColor;
import nc.ImageUtils;
import java.awt.image.RGBImageFilter;
import sexy.gui.ColorFilter;
import sexy.gui.SexyGraphics;
import java.awt.Color;
import java.applet.AudioClip;
import sexy.gui.SexyApplet;
import sexy.gui.SexyFont;
import sexy.gui.SexyImage;
import sexy.res.ResLoader;

public class Res extends ResLoader
{
    static final int IMAGE_BACKGROUND = 0;
    static final int IMAGE_SUNRISE = 1;
    static final int IMAGE_POLELEFT_MASKED = 2;
    static final int IMAGE_POLERIGHT_MASKED = 3;
    static final int IMAGE_FUSES_MASKED = 4;
    static final int IMAGE_FIREWORKS_NOVA = 5;
    static final int IMAGE_FIREWORKS_ROTATE = 6;
    static final int IMAGE_SPARKLE = 7;
    static final int IMAGE_EXPLODE = 8;
    static final int IMAGE_MATCHFLAME = 9;
    static final int IMAGE_TILE = 10;
    static final int IMAGE_TILE2 = 11;
    static final int IMAGE_TALKING_HEAD = 12;
    static final int IMAGE_TALKING_HEAD_MOUTHS = 13;
    static final int IMAGE_ROCKET_1 = 14;
    static final int IMAGE_COIN_BRONZE = 15;
    static final int IMAGE_COIN_GLOW = 16;
    static final int IMAGE_GEM_1 = 17;
    static final int IMAGE_FONT_12 = 18;
    static final int IMAGE_FONT_14 = 19;
    static final int IMAGE_FONT_16 = 20;
    static final int IMAGE_FONT_18 = 21;
    static final int IMAGE_FONT_20 = 22;
    static final int IMAGE_CITY = 23;
    static final int IMAGE_GROUND = 24;
    static final int IMAGE_SIDEBAR = 25;
    static final int IMAGE_BUTTONS = 26;
    static final int IMAGE_BUTTONS_OVER = 27;
    static final int IMAGE_BUTTONS_PRESSED = 28;
    static final int IMAGE_TIME_BAR = 29;
    static final int IMAGE_SMALLSTAR = 30;
    static final int IMAGE_HELPBUTTON = 31;
    static final int IMAGE_HELPBUTTON_OVER = 32;
    static final int IMAGE_HELPBUTTON_PRESSED = 33;
    static final int IMAGE_SOUNDBUTTONS = 34;
    static final int IMAGE_STREAMING_START = 35;
    static final int IMAGE_MSG_COMBO = 35;
    static final int IMAGE_MSG_LEVELUP = 36;
    static final int IMAGE_MSG_GAMEOVER = 37;
    static final int IMAGE_MSG_GETREADY = 38;
    static final int IMAGE_ROCKET_2 = 39;
    static final int IMAGE_ROCKET_3 = 40;
    static final int IMAGE_ROCKET_4 = 41;
    static final int IMAGE_ROCKET_5 = 42;
    static final int IMAGE_ROCKET_6 = 43;
    static final int IMAGE_ROCKET_7 = 44;
    static final int IMAGE_ROCKET_8 = 45;
    static final int IMAGE_ROCKET_9 = 46;
    static final int IMAGE_ROCKET_10 = 47;
    static final int IMAGE_COIN_SILVER = 48;
    static final int IMAGE_COIN_GOLD = 49;
    static final int IMAGE_COIN_PLATINUM = 50;
    static final int IMAGE_GEM_2 = 51;
    static final int IMAGE_GEM_3 = 52;
    static final int IMAGE_GEM_4 = 53;
    static final int IMAGE_GEM_5 = 54;
    static final int IMAGE_BOMB = 55;
    static final int IMAGE_CLOCK = 56;
    static final int IMAGE_PAUSED = 57;
    static final int IMAGE_STREAMING_END = 58;
    static final int INTERIMAGE_TITLE = 0;
    static final int INTERIMAGE_TEXT = 1;
    static final int INTERIMAGE_SCREEN_1 = 2;
    static final int INTERIMAGE_SCREEN_2 = 3;
    static final int INTERIMAGE_SCREEN_3 = 4;
    static final int INTERIMAGE_DELUXE = 5;
    static final int SOUND_BLANK = 0;
    static final int SOUND_BLOCK_ROTATING = 1;
    static final int SOUND_CHING = 2;
    static final int SOUND_CROWD_APPLAUSE = 3;
    static final int SOUND_FIREWORK_BANG = 4;
    static final int SOUND_FUSE_BURNING = 5;
    static final int SOUND_ROCKET_LAUNCH = 6;
    static final int SOUND_TING = 7;
    static final int SOUND_TUBE_GLOW = 8;
    static final int SOUND_UI_BEEP = 9;
    static final int SOUND_PING = 10;
    static final int SOUND_VOICE_GET_READY = 11;
    static final int SOUND_STREAMING_START = 12;
    static final int SOUND_COCK_CROWING = 12;
    static final int SOUND_COIN_LEAVING_CHEST = 13;
    static final int SOUND_CROWD_GASPS = 14;
    static final int SOUND_CROWD_OOHS = 15;
    static final int SOUND_FIREWORK_POPS = 16;
    static final int SOUND_GEM_PICKED_UP = 17;
    static final int SOUND_MAGIC_TINKLE = 18;
    static final int SOUND_ROCKET_BOUGHT = 19;
    static final int SOUND_TIMER_DANGER = 20;
    static final int SOUND_VOICE_LEVEL_UP = 21;
    static final int SOUND_VOICE_WELL_DONE = 22;
    static final int SOUND_VOICE_OUT_OF_TIME = 23;
    static final int SOUND_STREAMING_END = 24;
    static final int PIECE_WIDTH = 34;
    static final int PIECE_HEIGHT = 34;
    String[] mImageNames;
    String[] mStreamedImageNames;
    static final int[] mStreamedImagesBackupIndex;
    String[] mInterImageNames;
    String[] mSoundNames;
    static final int[] mStreamedSoundsBackupIndex;
    SexyImage[][][] mTubeImages;
    SexyImage[] mIndirectImages;
    SexyImage[] mStreamedImages;
    SexyImage[] mImageAd;
    SexyFont mFont12;
    SexyFont mFont14;
    SexyFont mFont16;
    SexyFont mFont18;
    SexyFont mFont20;
    SexyFont mFont12Outlined;
    SexyFont mFont14Outlined;
    SexyFont mFont16Outlined;
    SexyFont mFont18Outlined;
    SexyFont mFont20Outlined;
    SexyFont mLineFontSmall;
    SexyFont mLineFont;
    SexyFont mLineFontSmallOutlined;
    SexyFont mLineFontOutlined;
    SexyImage mHighlightTile;
    
    void UpdateStreamingStatus() {
        int n = 35;
        do {
            final int n2 = n - 35;
            if (this.mStreamedImages[n2] != null && this.mIndirectImages[n] != this.mStreamedImages[n2] && this.mStreamedImages[n2].IsReady()) {
                this.mIndirectImages[n] = this.mStreamedImages[n2];
            }
        } while (++n < 58);
    }
    
    boolean IsImageReady(int n) {
        if (n < 35) {
            return true;
        }
        n -= 35;
        return this.mStreamedImages[n] != null && this.mStreamedImages[n].IsReady();
    }
    
    public void PostFiltering() {
        int n = 35;
        do {
            final int n2 = n - 35;
            this.mStreamedImages[n2] = this.AsyncGetImage("images/" + this.mStreamedImageNames[n2]);
        } while (++n < 58);
        for (int i = 0; i < this.mInterImageNames.length; ++i) {
            this.mImageAd[i] = this.AsyncGetImage("images/" + this.mInterImageNames[i]);
        }
    }
    
    public Res(final RocketManiaApplet rocketManiaApplet) {
        super(rocketManiaApplet);
        this.mImageNames = new String[] { "back.jpg", "sunrise.jpg", "?poleleft.gif", "?poleright.gif", "?fuses.gif", "fireworksnova.gif", "fireworksrotate.gif", "sparkle.gif", "coinexplosion.gif", "matchflame.gif", "tile.gif", "tile2.gif", "?talkinghead.gif", "?talkingheadmouths.gif", "?rocket1.gif", "?coinbronze.gif", "coinglow.gif", "?gem01.gif", "_DomoAregato12.gif", "_DomoAregato14.gif", "_DomoAregato16.gif", "_DomoAregato18.gif", "_DomoAregato20.gif", "?city.gif", "ground.jpg", "left-wall.gif", "buttons.gif", "buttons-lit.gif", "buttons-press.gif", "timebar.jpg", "?smallstar.gif", "help.gif", "help-lit.gif", "help-press.gif", "sound.jpg" };
        this.mStreamedImageNames = new String[] { "?msg_combo.gif", "?msg_level_up.gif", "?msg_game_over.gif", "?msg_get_ready.gif", "?rocket2.gif", "?rocket3.gif", "?rocket4.gif", "?rocket5.gif", "?rocket6.gif", "?rocket7.gif", "?rocket8.gif", "?rocket9.gif", "?rocket10.gif", "?coinsilver.gif", "?coingold.gif", "?coinplatinum.gif", "?gem02.gif", "?gem03.gif", "?gem04.gif", "?gem05.gif", "?bomb.gif", "?clock.gif", "?msg_paused.gif" };
        this.mInterImageNames = new String[] { "intertitle.gif", "intertext.gif", "interscreen1.gif", "interscreen2.jpg", "interscreen3.jpg", "?interdeluxe.gif" };
        this.mSoundNames = new String[] { "blank.au", "Block_Rotating_03.au", "Ching_01.au", "Crowd_Applause_01.au", "Firework_Explosion_03.au", "Fuse_Burning_01d_short.au", "Rocket_Launch_Woosh_03b.au", "Ting_02b.au", "Tube_Glow_01.au", "UI_Little_Beep_05b.au", "Ping.au", "voice/Get_Ready.au", "Cockerel_Crowing_03.au", "Coin_Leaving_Treasure_Chest_05.au", "Crowd_Oohs_05.au", "Crowd_Oohs_11.au", "Firework_Explosion_07.au", "Gem_Picked_Up.au", "Magic_Tinkeling_Sound.au", "Rocket_Being_Bought_02.au", "Timer_Danger_06.au", "voice/Level_Up_02.au", "voice/Well_Done_01.au", "voice/Out_Of_Time_02.au" };
        this.mTubeImages = new SexyImage[5][4][8];
        this.mStreamedImages = new SexyImage[23];
        this.mIndirectImages = new SexyImage[this.mImageNames.length + 58 - 35];
    }
    
    AudioClip GetSound(int n) {
        if (n >= super.mNumSoundsLoaded) {
            n = Res.mStreamedSoundsBackupIndex[n - 12];
        }
        return super.mSounds[n];
    }
    
    public int GetOptionalSoundStart() {
        return 12;
    }
    
    public String[] GetSoundFileNames() {
        return this.mSoundNames;
    }
    
    SexyImage GetImage(final int n) {
        return this.mIndirectImages[n];
    }
    
    public void CustomFiltering() {
        int n = 0;
        do {
            this.mIndirectImages[n] = super.mImages[n];
        } while (++n < 35);
        int n2 = 35;
        do {
            final int n3 = n2 - 35;
            this.mIndirectImages[n2] = super.mImages[Res.mStreamedImagesBackupIndex[n3]];
            this.mStreamedImages[n3] = null;
        } while (++n2 < 58);
        this.mImageAd = new SexyImage[this.mInterImageNames.length];
        this.mFont12 = new SexyFont(super.mImages[18], "4 13\nL 11 e 8 v 9 l 4 0 10 1 7 2 10 3 9 4 10 5 10 6 11 7 10 8 11 9 8");
        this.mFont14 = new SexyFont(super.mImages[19], "5 15\n0 12 1 8 2 12 3 10 4 12 5 12 6 12 7 11 8 13 9 10");
        this.mFont16 = new SexyFont(super.mImages[20], "5 17\nF 14 L 14 Q 15 T 14 U 11 a 13 b 11 c 9 d 11 e 10 f 7 g 8 h 10 i 4 l 5 m 15 n 11 o 10 p 10 r 9 s 9 t 7 u 9 x 8 z 8 0 13 1 9 2 13 3 11 4 13 5 13 6 13 7 12 8 14 9 12 ! 5");
        this.mFont18 = new SexyFont(super.mImages[21], "6 19\nA 17 B 15 C 14 D 15 E 14 F 15 G 14 I 6 J 15 L 15 M 25 N 19 P 13 S 11 V 17 W 23 Y 15 a 14 b 11 c 10 d 12 e 11 f 7 g 9 h 11 i 5 j 9 k 10 l 6 m 17 n 12 o 10 p 11 q 12 r 10 s 10 t 8 u 9 v 13 w 15 x 9 y 11 z 9 * 11 ! 6");
        this.mFont20 = new SexyFont(super.mImages[22], "7 22\nB 17 C 15 D 17 G 17 L 17 O 16 P 14 R 18 U 14 W 26 a 15 b 14 c 11 d 13 e 11 g 10 i 5 k 11 l 7 m 19 n 13 o 13 p 12 r 11 s 11 t 8 u 10 v 14 ! 6");
        this.mLineFont = super.mApplet.CreateFont("SansSerif", 1, 16);
        this.mLineFontSmall = super.mApplet.CreateFont("SansSerif", 1, 14);
        final Color black = Color.black;
        final Color white = Color.white;
        this.mFont12Outlined = this.MakeOutlinedFont(this.mFont12, black, white);
        this.mFont14Outlined = this.MakeOutlinedFont(this.mFont14, black, white);
        this.mFont16Outlined = this.MakeOutlinedFont(this.mFont16, black, white);
        this.mFont18Outlined = this.MakeOutlinedFont(this.mFont18, black, white);
        this.mFont20Outlined = this.MakeOutlinedFont(this.mFont20, black, white);
        this.mLineFontOutlined = this.MakeOutlinedFont(this.mLineFont, black, white);
        this.mLineFontSmallOutlined = this.MakeOutlinedFont(this.mLineFontSmall, black, white);
        final SexyImage sexyImage = new SexyImage();
        sexyImage.Create(34, 34);
        sexyImage.SetImageMode(true, true);
        int n4 = 0;
        do {
            int n5 = 0;
            do {
                int n6 = 0;
                do {
                    final SexyImage sexyImage2 = new SexyImage();
                    sexyImage2.Create(34, 34);
                    sexyImage2.SetImageMode(true, true);
                    final SexyGraphics sexyGraphics = new SexyGraphics(sexyImage2);
                    if (n4 == 4) {
                        sexyGraphics.DrawImage(super.mImages[10], 0, 0);
                    }
                    else {
                        sexyGraphics.DrawImage(super.mImages[11], 0, 0);
                    }
                    SexyImage sexyImage3 = this.CopyAndFilter(super.mImages[4], 0, n4 * 34, 34, 34, new ColorFilter(Tube.gColours[n6][0], Tube.gColours[n6][1], Tube.gColours[n6][2]));
                    switch (n5) {
                        case 1: {
                            sexyImage3 = ImageUtils.CopyAndRotate90(sexyImage3);
                            break;
                        }
                        case 2: {
                            sexyImage3 = ImageUtils.CopyAndRotate90(ImageUtils.CopyAndRotate90(sexyImage3));
                            break;
                        }
                        case 3: {
                            sexyImage3 = ImageUtils.CopyAndRotate90(ImageUtils.CopyAndRotate90(ImageUtils.CopyAndRotate90(sexyImage3)));
                            break;
                        }
                    }
                    sexyGraphics.DrawImage(sexyImage3, 0, 0);
                    this.mTubeImages[n4][n5][n6] = sexyImage2;
                } while (++n6 < 8);
            } while (++n5 < 4);
        } while (++n4 < 5);
        (this.mHighlightTile = new SexyImage()).Create(34, 34);
        final SexyGraphics sexyGraphics2 = new SexyGraphics(this.mHighlightTile);
        sexyGraphics2.SetColor(new SexyColor(48, 48, 48));
        sexyGraphics2.FillRect(0, 0, this.mHighlightTile.GetWidth(), this.mHighlightTile.GetHeight());
        ADSR.TouchHack();
        ParticleElement.TouchHack();
        ParticleEmitter.TouchHack();
        Advertisement.TouchHack();
        BuyMe.TouchHack();
        FancyDialog.TouchHack();
        Fireworks.TouchHack();
        Points.TouchHack();
        Ranks.TouchHack();
        Scores.TouchHack();
        SpacedFont.TouchHack();
        TravellingSpark.TouchHack();
        new Stats();
        new LevelUp();
        new DelayedSounds().Add(0, 0.0);
    }
    
    static {
        mStreamedImagesBackupIndex = new int[] { 30, 30, 30, 30, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 17, 17, 17, 17, 15, 15, 30 };
        mStreamedSoundsBackupIndex = new int[] { 0, 10, 3, 3, 4, 7, 0, 2, 9, 0, 0, 0 };
    }
    
    public String[] GetImageFileNames() {
        return this.mImageNames;
    }
    
    private SexyFont MakeOutlinedFont(final SexyFont sexyFont, final Color color, final Color color2) {
        final SexyFont sexyFont2 = new SexyFont();
        sexyFont2.mHeight = sexyFont.mHeight + 2;
        sexyFont2.mAscent = sexyFont.mAscent + 1;
        int n = 0;
        int n2 = 0;
        do {
            if (sexyFont.mCharWidths[n2] > 0) {
                n += sexyFont.mCharWidths[n2] + 3;
            }
            sexyFont2.mCharStarts[n2] = 0;
            sexyFont2.mCharWidths[n2] = 0;
        } while (++n2 < 256);
        final SexyImage mImage = new SexyImage();
        mImage.Create(n, sexyFont.mHeight + 2);
        final SexyGraphics sexyGraphics = new SexyGraphics(mImage);
        sexyGraphics.SetColor(new SexyColor(0, 0, 0, 0));
        sexyGraphics.FillRect(0, 0, mImage.GetWidth(), mImage.GetHeight());
        sexyGraphics.SetFont(sexyFont);
        int n3 = 0;
        int n4 = 0;
        do {
            if (sexyFont.mCharWidths[n4] > 0) {
                final Character c = new Character((char)n4);
                sexyGraphics.SetColor(color);
                int n5 = 0;
                do {
                    int n6 = 0;
                    do {
                        sexyGraphics.DrawString(c.toString(), n3 + n5, n6 + sexyFont.mAscent);
                    } while (++n6 < 3);
                } while (++n5 < 3);
                sexyGraphics.SetColor(color2);
                sexyGraphics.DrawString(c.toString(), n3 + 1, 1 + sexyFont.mAscent);
                sexyFont2.mCharStarts[n4] = n3;
                sexyFont2.mCharWidths[n4] = sexyFont.mCharWidths[n4] + 2;
                n3 += sexyFont.mCharWidths[n4] + 3;
            }
        } while (++n4 < 256);
        sexyFont2.mImage = mImage;
        return sexyFont2;
    }
}
