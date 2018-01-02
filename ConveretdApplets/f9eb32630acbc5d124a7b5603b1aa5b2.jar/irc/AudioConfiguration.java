// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;
import java.util.Hashtable;

public class AudioConfiguration
{
    private SoundHandler _sound;
    private String _query;
    private String _beep;
    private Hashtable _word;
    
    public AudioConfiguration(final SoundHandler sound) {
        this._sound = sound;
        this._query = null;
        this._beep = null;
        this._word = new Hashtable();
    }
    
    public void play(final String s) {
        this._sound.playSound(s);
    }
    
    public void setQuery(final String query) {
        this._query = query;
    }
    
    public void setBeep(final String beep) {
        this._beep = beep;
    }
    
    public void setWord(final String s, final String s2) {
        this._word.put(s, s2);
    }
    
    public void onQuery() {
        if (this._query != null) {
            this._sound.playSound(this._query);
        }
    }
    
    public void beep() {
        if (this._beep != null) {
            this._sound.playSound(this._beep);
        }
    }
    
    public void onWord(final String s) {
        final String s2 = this._word.get(s);
        if (s2 != null) {
            this._sound.playSound(s2);
        }
    }
    
    public Enumeration getSoundWords() {
        return this._word.keys();
    }
}
