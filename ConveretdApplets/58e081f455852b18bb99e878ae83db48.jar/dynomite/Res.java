// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import sexy.gui.SexyApplet;
import sexy.gui.SexyImage;
import sexy.res.ResLoader;

public class Res extends ResLoader
{
    static final int IMAGE_SIDEBARX = 7;
    static final int IMAGE_FOOT = 8;
    static final int IMAGE_SLINGSHOT = 9;
    static final int IMAGE_EGGS = 10;
    static final int IMAGE_EGGPILERIGHT = 11;
    static final int IMAGE_EGGPILELEFT = 12;
    static final int IMAGE_WALLLEFT = 13;
    static final int IMAGE_WALLRIGHT = 14;
    static final int IMAGE_OVERLAP = 15;
    static final int IMAGE_HAND = 16;
    static final int IMAGE_YOLK = 17;
    static final int IMAGE_SHELLS = 18;
    static final int IMAGE_ROPE = 19;
    static final int IMAGE_DYNAMITE = 20;
    static final int IMAGE_BOOM = 21;
    static final int IMAGE_COMBOBALLOON = 22;
    static final int IMAGE_COMBOTEXT = 23;
    static final int IMAGE_SCOREFONT = 24;
    static final int IMAGE_STEGGY = 25;
    static final int IMAGE_TANK1 = 26;
    static final int IMAGE_TANK2 = 27;
    static final int IMAGE_JUNGLE = 28;
    static final int IMAGE_SPARKS = 29;
    static final int IMAGE_KRONEY = 30;
    static final int IMAGE_HOURGLASSEMPTY = 31;
    static final int IMAGE_HOURGLASSFULL = 32;
    static final int IMAGE_KRONEYALERT = 33;
    static final int IMAGE_WHIRLEYSCOMING = 34;
    static final int IMAGE_THUNDERBOLT = 35;
    static final int IMAGE_BOMB = 36;
    static final int IMAGE_BABIES = 37;
    static final int IMAGE_BONUSEGG = 38;
    static final int IMAGE_BONUSVIEW = 39;
    static final int IMAGE_STARS = 40;
    static final int IMAGE_AIMX = 41;
    static final int IMAGE_PAUSEBUTTONS = 42;
    static final int IMAGE_SOUNDBUTTONS = 43;
    static final int IMAGE_NEWGAMEBUTTONS = 44;
    static final int IMAGE_GAMETYPEBUTTONS = 45;
    static final int IMAGE_CEILING = 46;
    static final int IMAGE_MAMAPORTRAIT = 47;
    static final int IMAGE_MAMAMETER = 48;
    static final int IMAGE_THERMOGLOW = 49;
    static final int IMAGE_INCHYMOUTH = 50;
    static final int IMAGE_INCHYBLINK = 51;
    static final int IMAGE_TUTORCIRCLE = 52;
    static final int IMAGE_TUTORCIRCLE2 = 53;
    static final int IMAGE_TUTORPICTURE1 = 54;
    static final int IMAGE_SIDEBARY = 55;
    static final int IMAGE_STREAMLEG = 56;
    static final int IMAGE_STREAMWHIRLEY = 57;
    static final int IMAGE_STREAMKRONEYFLIP = 58;
    static final int IMAGE_STREAMGAMEOVER = 59;
    static final int IMAGE_STREAMADTITLE = 60;
    static final int IMAGE_STREAMAD1 = 61;
    static final int IMAGE_STREAMAD2 = 62;
    static final int IMAGE_STREAMAD3 = 63;
    static final int IMAGE_STREAMAD4 = 64;
    String[] mImageNames;
    static final int SOUND_EGGLANDS = 1;
    static final int SOUND_BREAKEGG = 2;
    static final int SOUND_RUBBERSHOT = 3;
    static final int SOUND_EXPLODE = 4;
    static final int SOUND_BUTTONCLICK = 5;
    static final int SOUND_COMBO = 6;
    static final int SOUND_SUPERCOMBO = 7;
    static final int SOUND_AOOGA = 8;
    static final int SOUND_MAMA = 9;
    static final int SOUND_REWARD = 10;
    static final int SOUND_POPBONUS = 11;
    static final int SOUND_POPSCORE = 12;
    static final int SOUND_CAMBRIANCOMBO = 13;
    static final int SOUND_TRIASSICCOMBO = 14;
    static final int SOUND_JURASSICCOMBO = 15;
    static final int SOUND_CRETACEOUSCOMBO = 16;
    static final int SOUND_DYNOMITECOMBO = 17;
    static final int SOUND_FOOTLAND = 18;
    static final int SOUND_GRIND = 19;
    static final int SOUND_FOOTFALL = 20;
    static final int SOUND_TICKTOCK = 21;
    static final int SOUND_SIREN = 22;
    static final int SOUND_WHIRLEYSCOMING = 23;
    static final int SOUND_WHIRLEYHIT = 24;
    static final int SOUND_WARNING = 25;
    static final int SOUND_RUMBLE = 26;
    static final int SOUND_THUNDERBOLT = 27;
    static final int SOUND_CHIME = 28;
    static final int SOUND_ANNOUNCEPOP = 29;
    SexyImage[] mEggShell;
    SexyImage mImageLeg;
    SexyImage mImageWhirley;
    SexyImage mImageKroneyflip;
    SexyImage mImageGameOver;
    SexyImage mImageAdTitle;
    SexyImage[] mImageAd;
    String[] mSoundNames;
    
    public Res(final DynomiteApplet dynomiteApplet) {
        super(dynomiteApplet);
        this.mImageNames = new String[] { "zz_cap2.gif", "zz_games1.gif", "zz_loaderbar1.gif", "zz_loaderbar2.gif", "zz_logo3.gif", "zz_pop2.gif", "zz_url1.gif", "sidebarx.jpg", "foot.gif", "slingshot.gif", "eggs.gif", "eggpile_right.gif", "eggpile_left.gif", "wall_left.gif", "wall_right.gif", "sidebaroverlap.gif", "hand.gif", "yolk.gif", "eggshellmask.gif", "rope.gif", "dynamite.gif", "boom.gif", "comboballoon.gif", "combotext.gif", "scorefont.gif", "steggy.gif", "tank1.gif", "tank2.gif", "junglebackground.jpg", "sparks.gif", "kroney.gif", "hourglass_empty.gif", "hourglass_full.gif", "kroneyalert.gif", "whirleyscoming.gif", "bolt.gif", "bomb.gif", "babies.gif", "bonus_egg.gif", "bonus_view.gif", "stars.gif", "aimx.gif", "pausebuttons.jpg", "soundbuttons.jpg", "newgamebuttons.jpg", "gametypebuttons.jpg", "ceiling.gif", "mamaportrait.gif", "mamameter.jpg", "thermoglow.gif", "inchymouth.gif", "inchyblink.gif", "tutorcircle.gif", "tutorcircle2.gif", "tutorpicture1.jpg", "sidebary.jpg", "leg.gif", "whirley.gif", "kroneyflip.gif", "gameover.gif", "ad/title.gif", "ad/ad1.jpg", "ad/ad2.jpg", "ad/ad3.jpg", "ad/ad4.jpg" };
        this.mEggShell = new SexyImage[20];
        this.mImageAd = new SexyImage[4];
        this.mSoundNames = new String[] { "blank.au", "egglands.au", "breakegg.au", "rubbershot.au", "explode.au", "buttonclick.au", "xcombo.au", "xsupercombo.au", "aooga.au", "mama.au", "reward.au", "popbonus.au", "popscore.au", "xcambriancombo.au", "xtriassiccombo.au", "xjurassiccombo.au", "xcretaceouscombo.au", "xdynamitecombo.au", "footlands.au", "grind.au", "footfall.au", "ticktock.au", "siren.au", "whirleyscoming.au", "whirleyhit.au", "warning.au", "footfallrumble.au", "thunderbolt.au", "chime.au", "announcepop.au" };
        this.mImageAd[0] = null;
        this.mImageAd[1] = null;
        this.mImageAd[2] = null;
        this.mImageAd[3] = null;
    }
    
    public int GetOptionalSoundStart() {
        return 5;
    }
    
    public String[] GetSoundFileNames() {
        return this.mSoundNames;
    }
    
    public String[] GetImageFileNames() {
        return this.mImageNames;
    }
}
