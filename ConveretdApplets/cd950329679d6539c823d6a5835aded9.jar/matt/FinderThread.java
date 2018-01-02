// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.PriorityQueue;

public class FinderThread extends Thread
{
    private boolean running;
    String searchIn;
    String toFind;
    private PriorityQueue<ABCMatch> pq;
    private float edThreshold;
    Object lock;
    TranscribedNote[] notes;
    
    public FinderThread(final String searchIn, final String toFind, final TranscribedNote[] notes, final PriorityQueue<ABCMatch> pq, final Object lock) {
        this.searchIn = searchIn;
        this.toFind = toFind;
        this.pq = pq;
        this.lock = lock;
        this.notes = notes;
    }
    
    public void run() {
        this.running = true;
        synchronized (this.lock) {
            this.lock.notify();
        }
        final CorpusIndex index = CorpusIndex.instance();
        this.edThreshold = Float.parseFloat("" + MattProperties.getString("editDistanceThreshold"));
        while (this.running) {
            final CorpusEntry current = index.getNext();
            MattGuiNB.instance().getProgressBar().setValue(index.getCurrentIndex());
            if (current == null) {
                this.running = false;
                synchronized (this.lock) {
                    this.lock.notify();
                }
            }
            else {
                this.searchIndexEntry(current);
            }
        }
        Logger.log("Thread finished");
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
    }
    
    private void searchIndexEntry(final CorpusEntry entry) {
        final boolean logSearches = Boolean.parseBoolean("" + MattProperties.getString("logSearches"));
        final boolean expandShortTunes = Boolean.parseBoolean("" + MattProperties.getString("expandShortTunes"));
        if (logSearches) {
            Logger.log("Searching tune: " + entry.getX() + " " + entry.getTitle());
        }
        try {
            final String searchIn = entry.getKey();
            if (logSearches) {
                Logger.log("Source: " + searchIn);
            }
            float bestEditdistance = Float.MAX_VALUE;
            String bestBit = "";
            final StringBuffer nlSearchIn = new StringBuffer(searchIn);
            boolean needsExpansion = false;
            if (expandShortTunes && MattProperties.getString("searchMethod").equalsIgnoreCase("bryan")) {
                while (this.toFind.length() > nlSearchIn.length()) {
                    needsExpansion = true;
                    nlSearchIn.append(searchIn);
                }
            }
            if (needsExpansion) {
                nlSearchIn.setLength(this.toFind.length());
            }
            float ed;
            if (MattProperties.getString("searchMethod").equalsIgnoreCase("semex")) {
                ed = EditDistance.minEdSemex(MIDITools.instance().toMIDISequence(this.notes), entry.getMidiSequence());
            }
            else {
                ed = EditDistance.minEdSubString(this.toFind, "" + (Object)nlSearchIn);
            }
            if (ed < bestEditdistance) {
                bestEditdistance = ed;
                bestBit = "" + (Object)nlSearchIn;
            }
            final ABCMatch match = new ABCMatch();
            match.setLine(bestBit);
            match.setTunepalid(entry.getTunePalID());
            match.setEditDistance(bestEditdistance);
            match.setFileName(entry.getFile());
            match.setX(entry.getX());
            match.setTitle(entry.getTitle());
            match.setSource(entry.getSource());
            match.setIndex(entry.getIndex());
            match.setCorpusEntry(entry);
            this.pq.add(match);
            final ABCMatch best = this.pq.peek();
            MattGuiNB.instance().setBestSoFar(best);
            if (logSearches) {
                Logger.log("Best edit distance: " + bestEditdistance + " for search: " + bestBit);
            }
        }
        catch (Exception e) {
            Logger.log("Exception parsing tune: " + entry.getTitle());
            e.printStackTrace();
        }
    }
}
