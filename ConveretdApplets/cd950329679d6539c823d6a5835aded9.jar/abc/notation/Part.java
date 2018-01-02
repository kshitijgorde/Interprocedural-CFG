// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class Part implements Cloneable
{
    private char m_label;
    private Tune.Music m_music;
    
    Part(final Tune tune, final char labelValue) {
        this.m_music = null;
        this.m_label = labelValue;
        this.m_music = tune.createMusic();
    }
    
    Part(final Part root) {
        this.m_music = null;
        this.m_label = root.m_label;
        this.m_music = (Tune.Music)root.m_music.clone();
    }
    
    public void setLabel(final char labelValue) {
        this.m_label = labelValue;
    }
    
    public char getLabel() {
        return this.m_label;
    }
    
    public Tune.Music getMusic() {
        return this.m_music;
    }
    
    void setMusic(final Tune.Music score) {
        this.m_music = score;
    }
    
    public Object clone() {
        return new Part(this);
    }
}
