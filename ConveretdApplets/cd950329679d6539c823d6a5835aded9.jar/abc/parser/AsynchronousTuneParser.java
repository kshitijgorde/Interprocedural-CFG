// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import java.io.Reader;
import java.io.StringReader;
import abc.notation.Tune;
import java.util.Vector;

public class AsynchronousTuneParser extends TuneParser
{
    private Object m_mutex;
    private Thread m_parsingThread;
    private Vector m_queue;
    private boolean m_isQueueManagementEnabled;
    
    public AsynchronousTuneParser() {
        this.m_mutex = new Object();
        this.m_parsingThread = null;
        this.m_queue = null;
        this.m_isQueueManagementEnabled = true;
        this.m_queue = new Vector();
        (this.m_parsingThread = new Thread(new ParsingRunnable())).start();
    }
    
    public AsynchronousTuneParser(final boolean isQueueManagementEnabled) {
        this.m_mutex = new Object();
        this.m_parsingThread = null;
        this.m_queue = null;
        this.m_isQueueManagementEnabled = true;
        this.m_isQueueManagementEnabled = isQueueManagementEnabled;
        this.m_queue = new Vector();
        (this.m_parsingThread = new Thread(new ParsingRunnable())).start();
    }
    
    public Tune parse(final String tune) {
        synchronized (this.m_mutex) {
            if (!this.m_isQueueManagementEnabled) {
                this.m_queue.removeAllElements();
            }
            this.m_queue.addElement(new QueueElement(new StringReader(tune), false));
            this.m_mutex.notify();
        }
        return null;
    }
    
    public Tune parse(final Reader charStream) {
        synchronized (this.m_mutex) {
            if (!this.m_isQueueManagementEnabled) {
                this.m_queue.removeAllElements();
            }
            this.m_queue.addElement(new QueueElement(charStream, false));
            this.m_mutex.notify();
        }
        return null;
    }
    
    public Tune parseHeader(final String tune) {
        synchronized (this.m_mutex) {
            if (!this.m_isQueueManagementEnabled) {
                this.m_queue.removeAllElements();
            }
            this.m_queue.addElement(new QueueElement(new StringReader(tune), true));
            this.m_mutex.notify();
        }
        return null;
    }
    
    public Tune parseHeader(final Reader charStream) {
        synchronized (this.m_mutex) {
            if (!this.m_isQueueManagementEnabled) {
                this.m_queue.removeAllElements();
            }
            this.m_queue.addElement(new QueueElement(charStream, true));
            this.m_mutex.notify();
        }
        return null;
    }
    
    private Vector getQueue() {
        return this.m_queue;
    }
    
    public void superParse(final Reader r) {
        super.parse(r);
    }
    
    public void superParseHeader(final Reader r) {
        super.parseHeader(r);
    }
    
    private class ParsingRunnable implements Runnable
    {
        public void run() {
            try {
                synchronized (AsynchronousTuneParser.this.m_mutex) {
                    while (true) {
                        if (!AsynchronousTuneParser.this.m_queue.isEmpty()) {
                            final QueueElement q = AsynchronousTuneParser.this.getQueue().elementAt(0);
                            AsynchronousTuneParser.this.getQueue().removeElementAt(0);
                            if (q.headerOnly()) {
                                AsynchronousTuneParser.this.superParseHeader(q.getNotation());
                            }
                            else {
                                AsynchronousTuneParser.this.superParse(q.getNotation());
                            }
                        }
                        else {
                            AsynchronousTuneParser.this.m_mutex.wait();
                        }
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private class QueueElement
    {
        private Reader m_notation;
        private boolean m_headerOnly;
        
        public QueueElement(final Reader notation, final boolean headerOnly) {
            this.m_notation = null;
            this.m_headerOnly = false;
            this.m_notation = notation;
            this.m_headerOnly = headerOnly;
        }
        
        public Reader getNotation() {
            return this.m_notation;
        }
        
        public boolean headerOnly() {
            return this.m_headerOnly;
        }
    }
}
