// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.net.URL;
import java.applet.AudioClip;

public class SoundManager implements Runnable
{
    protected static final int totalNbrSounds = 7;
    protected boolean soundLoadComplete;
    protected int nbrSoundsLoaded;
    protected AsteroidsUI asteroidsUI;
    private boolean speakerOn;
    protected Thread soundManagerThread;
    protected AudioClip bulletSound;
    protected AudioClip missileSound;
    protected Missile currentMissile;
    protected AudioClip bigExplosionSound;
    protected AudioClip smallExplosionSound;
    protected AudioClip bulletImpactSound;
    protected AudioClip thrustSound;
    protected AudioClip powerupSound;
    protected boolean thrustSoundPlaying;
    
    public SoundManager(final AsteroidsUI a) {
        this.asteroidsUI = a;
        this.soundLoadComplete = false;
        this.nbrSoundsLoaded = 0;
        this.speakerOn = true;
        this.thrustSoundPlaying = false;
        (this.soundManagerThread = new Thread(this)).setName("Andrews Asteroids Sound Manager Thread");
        this.soundManagerThread.start();
    }
    
    public AudioClip loadSound(final String url) {
        try {
            final URL soundLocation = new URL(url);
            final AudioClip ac = this.asteroidsUI.getAudioClip(soundLocation);
            ac.play();
            ac.stop();
            Thread.currentThread();
            Thread.sleep(500L);
            return ac;
        }
        catch (Exception e) {
            System.out.println("Failed to load Audio Clip: ".concat(String.valueOf(String.valueOf(url.toString()))));
            return null;
        }
    }
    
    public void run() {
        if (!this.asteroidsUI.quitGame) {
            this.bulletSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("bullet.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.missileSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("missile.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.bigExplosionSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("bigExplosion.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.smallExplosionSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("smallExplosion.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.bulletImpactSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("bulletImpact.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.thrustSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("thrust.au"));
        }
        ++this.nbrSoundsLoaded;
        if (!this.asteroidsUI.quitGame) {
            this.powerupSound = this.loadSound(String.valueOf(String.valueOf(this.asteroidsUI.getCodeBase().toString())).concat("powerup.au"));
        }
        ++this.nbrSoundsLoaded;
        this.soundLoadComplete = true;
    }
    
    public void toggleSpeaker() {
        if (!(this.speakerOn = !this.speakerOn)) {
            this.stopPlayingAllSounds();
        }
    }
    
    public void stopPlayingAllSounds() {
        this.stopPlayingMissileSound();
        this.stopPlayingThrustSound();
    }
    
    public void playBulletSound() {
        if (this.soundLoadComplete && this.speakerOn) {
            this.bulletSound.play();
        }
    }
    
    public void playBulletImpactSound() {
        if (this.soundLoadComplete && this.speakerOn) {
            this.bulletImpactSound.play();
        }
    }
    
    public void playMissileSound(final Missile m) {
        if (this.soundLoadComplete && this.speakerOn) {
            this.missileSound.play();
            this.currentMissile = m;
        }
    }
    
    public void stopPlayingMissileSound() {
        if (this.soundLoadComplete) {
            this.missileSound.stop();
            this.currentMissile = null;
        }
    }
    
    public void stopPlayingMissileSound(final Missile m) {
        if (m == this.currentMissile && this.soundLoadComplete) {
            this.missileSound.stop();
            this.currentMissile = null;
        }
    }
    
    public void playBigExplosionSound() {
        if (this.soundLoadComplete && this.speakerOn) {
            this.bigExplosionSound.play();
        }
    }
    
    public void playSmallExplosionSound() {
        if (this.soundLoadComplete && this.speakerOn) {
            this.smallExplosionSound.play();
        }
    }
    
    public void playPowerupSound() {
        if (this.soundLoadComplete && this.speakerOn) {
            this.powerupSound.play();
        }
    }
    
    public void playThrustSound() {
        if (this.soundLoadComplete && this.speakerOn && !this.thrustSoundPlaying) {
            this.thrustSound.loop();
            this.thrustSoundPlaying = true;
        }
    }
    
    public void stopPlayingThrustSound() {
        if (this.thrustSoundPlaying && this.soundLoadComplete) {
            this.thrustSound.stop();
        }
        this.thrustSoundPlaying = false;
    }
}
