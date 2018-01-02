// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.awt;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.Font;
import abc.parser.TuneParser;
import abc.notation.Tune;
import java.awt.TextArea;

public class TuneEditorArea extends TextArea
{
    private static final int IDLE_TIME_BEFORE_REFRESH = 200;
    private int m_idleTimeBeforeRefresh;
    private ParsingRefresh m_refresher;
    private Tune m_tune;
    
    public TuneEditorArea() {
        this(new TuneParser());
    }
    
    public TuneEditorArea(final TuneParser parser) {
        this.m_idleTimeBeforeRefresh = 200;
        this.m_refresher = null;
        this.m_tune = null;
        this.setFont(new Font("Courier", 0, 12));
        this.m_refresher = new ParsingRefresh(this, parser);
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    
    public TuneParser getParser() {
        return this.m_refresher.getParser();
    }
    
    public Tune getTune() {
        return this.m_tune;
    }
    
    private class ParsingRefresh extends Thread implements TextListener
    {
        private TextArea m_document;
        private TuneParser m_parser;
        private int m_idleTime;
        private Object m_mutex;
        
        public ParsingRefresh(final TextArea document, final TuneParser parser) {
            super("ABC-TuneEditorAreaRefresh");
            this.m_document = null;
            this.m_parser = null;
            this.m_idleTime = 0;
            this.m_mutex = new Object();
            this.m_parser = parser;
            (this.m_document = document).addTextListener(this);
            this.start();
        }
        
        public TuneParser getParser() {
            return this.m_parser;
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        synchronized (this.m_mutex) {
                            this.m_mutex.wait();
                            do {
                                this.m_mutex.wait(10L);
                                this.m_idleTime += 10;
                            } while (this.m_idleTime <= 200);
                            try {
                                final String tuneNotation = TuneEditorArea.this.getText();
                                if (tuneNotation.equals("")) {
                                    continue;
                                }
                                TuneEditorArea.this.m_tune = this.m_parser.parse(tuneNotation);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();
                    continue;
                }
                break;
            }
        }
        
        public void textValueChanged(final TextEvent e) {
            synchronized (this.m_mutex) {
                this.m_idleTime = 0;
                this.m_mutex.notify();
            }
        }
    }
}
