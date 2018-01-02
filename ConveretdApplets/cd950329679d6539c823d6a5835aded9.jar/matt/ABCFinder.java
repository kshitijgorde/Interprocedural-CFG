// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import abc.notation.Tune;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ABCFinder extends Thread
{
    FinderThread[] finderThreads;
    SetFinder setFinder;
    private boolean running;
    private String startIn;
    private String searchString;
    private String toFind;
    private PriorityQueue<ABCMatch> pq;
    private TranscribedNote[] transcribedNotes;
    
    public ABCFinder() {
        this.running = false;
        this.pq = new PriorityQueue<ABCMatch>(1000, new ABCMatch());
        this.running = false;
    }
    
    public String getStartIn() {
        return this.startIn;
    }
    
    public void setStartIn(final String startIn) {
        this.startIn = startIn;
    }
    
    public String getSearchString() {
        return this.searchString;
    }
    
    public void setSearchString(final String searchString) {
        this.searchString = searchString;
    }
    
    public void finda() {
        this.start();
    }
    
    public void run() {
        this.findFromIndex();
    }
    
    public Tune[] findFromIndex() {
        this.running = true;
        if (this.searchString.length() == 0) {
            Logger.log("Nothing to find");
            return null;
        }
        this.toFind = this.searchString;
        this.toFind = MattABCTools.expandLongNotes(this.toFind);
        this.toFind = MattABCTools.stripWhiteSpace(this.toFind);
        this.toFind = MattABCTools.stripBarDivisions(this.toFind);
        this.toFind = this.toFind.toUpperCase();
        Logger.log("Target: " + this.toFind);
        if (MattProperties.getBoolean("tansey") && SetFinder.isSet(this.toFind)) {
            Logger.log("I think it's a set, therefore using the set finder algorithm");
            this.setFinder = new SetFinder();
            final Object lock = new Object();
            this.setFinder.setToFind(this.toFind);
            this.setFinder.setLock(lock);
            this.setFinder.setPq(this.pq);
            this.setFinder.setTranscribedNotes(this.transcribedNotes);
            this.setFinder.start();
            for (boolean stillRunning = true; stillRunning; stillRunning = this.setFinder.isRunning()) {
                synchronized (lock) {
                    try {
                        lock.wait(500L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else {
            MattGuiNB.instance().clearMatches();
            final CorpusIndex index = CorpusIndex.instance();
            index.reset();
            final int numThreads = Integer.parseInt("" + MattProperties.getString("numThreads"));
            this.finderThreads = new FinderThread[numThreads];
            final Object lock2 = new Object();
            Logger.log("Staring " + numThreads + " finder threads");
            MattGuiNB.instance().getProgressBar().setValue(0);
            MattGuiNB.instance().getProgressBar().setMaximum(CorpusIndex.instance().size());
            for (int i = 0; i < numThreads; ++i) {
                (this.finderThreads[i] = new FinderThread(this.startIn, this.toFind, this.transcribedNotes, this.pq, lock2)).start();
            }
            boolean stillRunning2 = true;
            while (stillRunning2) {
                synchronized (lock2) {
                    try {
                        lock2.wait(500L);
                    }
                    catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    stillRunning2 = false;
                    for (int j = 0; j < this.finderThreads.length; ++j) {
                        if (this.finderThreads[j].isRunning()) {
                            stillRunning2 = true;
                        }
                    }
                }
            }
            this.printTop(10);
            final ABCMatch best = this.pq.peek();
            MattGuiNB.instance().setBestSoFar(best);
            Logger.log("Searched " + CorpusIndex.instance().size() + " tunes");
            this.running = false;
        }
        return null;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
        if (this.finderThreads != null) {
            for (int i = 0; i < this.finderThreads.length; ++i) {
                if (this.finderThreads[i] != null) {
                    this.finderThreads[i].setRunning(running);
                }
            }
        }
        if (this.setFinder != null) {
            this.setFinder.setRunning(running);
        }
    }
    
    public String getToFind() {
        return this.toFind;
    }
    
    public void setToFind(final String toFind) {
        this.toFind = toFind;
    }
    
    private void printTop(final int howMany) {
        if (this.pq.size() < howMany) {
            Logger.log("ERROR!!" + howMany + " matches");
        }
        Logger.log("Printing top " + howMany + " matches");
        final ABCMatch[] pushBack = new ABCMatch[howMany];
        for (int i = 0; i < howMany; ++i) {
            final ABCMatch match = this.pq.poll();
            Logger.log(match);
            MattGuiNB.instance().addMatch(match);
            pushBack[i] = match;
        }
        for (int i = 0; i < howMany; ++i) {
            this.pq.add(pushBack[i]);
        }
    }
    
    public PriorityQueue<ABCMatch> getPq() {
        return this.pq;
    }
    
    public void setPq(final PriorityQueue<ABCMatch> pq) {
        this.pq = pq;
    }
    
    public TranscribedNote[] getTranscribedNotes() {
        return this.transcribedNotes;
    }
    
    public void setTranscribedNotes(final TranscribedNote[] transcribedNotes) {
        this.transcribedNotes = transcribedNotes;
    }
}
