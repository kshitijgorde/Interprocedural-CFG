// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.util.Enumeration;
import java.util.Vector;

public class MultipleWordCatcher implements WordCatcher
{
    private Vector _recognizers;
    
    public MultipleWordCatcher() {
        this._recognizers = new Vector();
    }
    
    public void addRecognizer(final WordRecognizer wordRecognizer) {
        this._recognizers.insertElementAt(wordRecognizer, this._recognizers.size());
    }
    
    public String getType(final String s) {
        final Enumeration<WordRecognizer> elements = this._recognizers.elements();
        while (elements.hasMoreElements()) {
            final WordRecognizer wordRecognizer = elements.nextElement();
            if (wordRecognizer.recognize(s)) {
                return wordRecognizer.getType();
            }
        }
        return null;
    }
}
