import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sounds
{
    private static AudioClip dealCardClip;
    private static AudioClip betClip;
    private static AudioClip shuffleClip;
    public static final int DEALCARDSOUND = 1;
    public static final int BETSOUND = 2;
    public static final int SHUFFLESOUND = 3;
    
    public static void setSounds(final AudioClip dealCardClip, final AudioClip betClip, final AudioClip shuffleClip) {
        Sounds.dealCardClip = dealCardClip;
        Sounds.betClip = betClip;
        Sounds.shuffleClip = shuffleClip;
    }
    
    public static void playSound(final int n) {
        switch (n) {
            case 1: {
                if (Sounds.dealCardClip != null) {
                    Sounds.dealCardClip.play();
                    return;
                }
                break;
            }
            case 2: {
                if (Sounds.betClip != null) {
                    Sounds.betClip.play();
                    return;
                }
                break;
            }
            case 3: {
                if (Sounds.shuffleClip != null) {
                    Sounds.shuffleClip.play();
                    return;
                }
                break;
            }
        }
    }
}
