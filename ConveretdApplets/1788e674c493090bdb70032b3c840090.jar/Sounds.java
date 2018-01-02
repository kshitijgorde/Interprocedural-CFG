import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sounds
{
    public static AudioClip laser;
    public static AudioClip asteroid;
    public static AudioClip enemy;
    public static AudioClip level;
    public static AudioClip hit;
    public static AudioClip die;
    public static boolean play;
    public static boolean mute;
    public static boolean loaded;
    
    public static void play(final AudioClip audioClip) {
        if (!Sounds.mute && Sounds.play && Sounds.loaded && audioClip != null) {
            audioClip.play();
        }
    }
    
    static {
        Sounds.play = true;
    }
}
