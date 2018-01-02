// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import abc.parser.TuneBook;
import javax.swing.AbstractAction;

public abstract class TuneBookActionAbstract extends AbstractAction
{
    private TuneBook m_tuneBook;
    
    public TuneBookActionAbstract(final TuneBook tuneBook) {
        this.m_tuneBook = null;
        this.m_tuneBook = tuneBook;
    }
    
    public TuneBookActionAbstract() {
        this.m_tuneBook = null;
    }
    
    public TuneBook getTuneBook() {
        return this.m_tuneBook;
    }
    
    public void setTuneBook(final TuneBook t) {
        this.m_tuneBook = t;
    }
}
