// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.util.Locale;

public class WordListRecognizer implements WordRecognizer
{
    private String[] _list;
    
    public WordListRecognizer() {
        this.setList(new String[0]);
    }
    
    public void setList(final String[] array) {
        this._list = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this._list[i] = array[i].toLowerCase(Locale.ENGLISH);
        }
    }
    
    public boolean recognize(final String s) {
        final String lowerCase = s.toLowerCase(Locale.ENGLISH);
        for (int i = 0; i < this._list.length; ++i) {
            if (lowerCase.equals(this._list[i])) {
                return true;
            }
        }
        return false;
    }
    
    public String getType() {
        return "wordlist";
    }
}
