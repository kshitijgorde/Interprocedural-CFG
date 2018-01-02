// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import matt.dsp.PeakCalculator;
import java.awt.Color;
import matt.dsp.FIRFilter;
import java.util.Vector;
import java.util.PriorityQueue;

public class SetFinder extends Thread
{
    private static final int FILTER_COMP = 15;
    private boolean running;
    private String toFind;
    private Object lock;
    private PriorityQueue<ABCMatch> pq;
    private TranscribedNote[] transcribedNotes;
    
    public static boolean isSet(final String transcription) {
        final int notesInReel = 320;
        return transcription.length() >= notesInReel;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
    }
    
    public String getToFind() {
        return this.toFind;
    }
    
    public void setToFind(final String toFind) {
        this.toFind = toFind;
    }
    
    public void run() {
        this.running = true;
        final Vector<Float> turns = new Vector<Float>();
        final boolean oldDrawGraphs = Boolean.parseBoolean(MattProperties.getString("drawGraphs"));
        MattProperties.instance().setProperty("drawGraphs", "true");
        final int typicalLength = ABCTranscriber.NOTES_PER_BAR[1] * 16;
        Logger.log("Searching for a set");
        final CorpusIndex index = CorpusIndex.instance();
        int startAt = 0;
        int whichTune = 1;
        ABCMatch match = null;
        while (this.running) {
            index.reset();
            float bestEd = Float.MAX_VALUE;
            int bestIndex = 0;
            int i = 0;
            final String searchString = this.toFind.substring(startAt, startAt + typicalLength);
            while (this.running) {
                final CorpusEntry current = index.getNext();
                if (current == null) {
                    break;
                }
                final StringBuffer searchIn = new StringBuffer(current.getKey());
                boolean needsExpansion = false;
                while (searchString.length() > searchIn.length()) {
                    searchIn.append(searchIn);
                    needsExpansion = true;
                }
                if (needsExpansion) {
                    searchIn.setLength(searchString.length());
                }
                final float ed = EditDistance.minEdSubString(searchString, "" + (Object)searchIn);
                if (ed < bestEd) {
                    bestEd = ed;
                    bestIndex = i;
                }
                ++i;
            }
            if (this.running) {
                final CorpusEntry firstTune = index.get(bestIndex);
                match = new ABCMatch();
                match.setCorpusEntry(firstTune);
                match.setEditDistance(bestEd);
                match.setWhich(whichTune);
                match.setIndex(bestIndex);
                match.setNotation(firstTune.getKey());
                match.setSource(firstTune.getSource());
                match.setTitle(firstTune.getTitle());
                match.setFileName(firstTune.getFile());
                match.setX(firstTune.getX());
                Logger.log("Tune identified as: " + firstTune.getTitle());
                this.printExpanded(this.toFind);
                this.printExpanded(firstTune.getKey());
                final int[] ed2 = EditDistance.edSubString(firstTune.getKey(), this.toFind);
                final float[] fed = new float[ed2.length];
                float[] fedf = new float[ed2.length];
                float min = Float.MAX_VALUE;
                float max = Float.MIN_VALUE;
                for (int j = 0; j < ed2.length; ++j) {
                    fed[j] = ed2[j];
                    if (fed[j] < min) {
                        min = fed[j];
                    }
                    else if (fed[j] > max) {
                        max = fed[j];
                    }
                }
                final FIRFilter filter = new FIRFilter();
                filter.setFilterType(1);
                fedf = filter.filter(fed);
                for (int k = 0; k < fedf.length; ++k) {
                    System.out.print(fedf[k] + "\t");
                }
                System.out.println();
                final Graph edGraph = new Graph();
                edGraph.setBackground(Color.WHITE);
                edGraph.getDefaultSeries().setData(fed);
                MattGuiNB.instance().addFFTGraph(edGraph, "UNFILT: " + firstTune.getTitle());
                final Graph edGraphf = new Graph();
                edGraphf.setBackground(Color.WHITE);
                edGraphf.getDefaultSeries().setData(fedf);
                MattGuiNB.instance().addFFTGraph(edGraphf, "FILT: " + firstTune.getTitle());
                int repeats = 0;
                final int slope = 10;
                Vector<Integer> troughs = null;
                float threshold = 0.3f;
                while (repeats == 0 || (repeats >= 5 && this.running)) {
                    troughs = PeakCalculator.calculateTrough(fedf, slope, fedf.length, threshold, edGraphf, typicalLength);
                    repeats = troughs.size();
                    Logger.log(troughs.size() + " repeats found");
                    if (troughs.size() == 0) {
                        threshold -= 0.05f;
                        Logger.log("Trying again with a threshold of " + threshold);
                    }
                    if (troughs.size() >= 5) {
                        threshold += 0.05f;
                        Logger.log("Trying again with a threshold of " + threshold);
                    }
                }
                Logger.log("Troughs:");
                for (int l = 0; l < troughs.size(); ++l) {
                    edGraph.getDefaultSeries().addVerticalLine(troughs.elementAt(l) - 15.0f);
                    edGraphf.getDefaultSeries().addVerticalLine(troughs.elementAt(l));
                    Logger.log((float)troughs.elementAt(l));
                }
                if (this.pq.peek() != null && this.pq.peek().getX() == match.getX()) {
                    this.pq.peek().setRepititions(this.pq.peek().getRepititions() + 1);
                    Logger.log("Threshold is too low, so I'm quitting");
                    break;
                }
                match.setRepititions(repeats);
                MattGuiNB.instance().addMatch(match);
                ++whichTune;
                this.pq.add(match);
                startAt = troughs.elementAt(troughs.size() - 1);
                if (startAt + typicalLength > this.toFind.length()) {
                    this.troughsToTime(turns, troughs, fedf, firstTune.getTitle(), 15, true);
                    break;
                }
                this.troughsToTime(turns, troughs, fedf, firstTune.getTitle(), 15, false);
            }
            else {
                Logger.log("Set annotation algorithm interrupted");
            }
        }
        MattProperties.instance().setProperty("drawGraphs", "" + oldDrawGraphs);
        Logger.log("No more tunes found");
        this.printTurns(turns);
        BatchJob.results.log(this.printTurns(turns));
        this.running = false;
        synchronized (this.lock) {
            this.lock.notify();
        }
    }
    
    void printExpanded(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            System.out.print(s.charAt(i) + "\t");
        }
        System.out.println();
    }
    
    String printTurns(final Vector<Float> turns) {
        final StringBuffer sb = new StringBuffer();
        Logger.log("Turns:");
        for (int i = 0; i < turns.size(); ++i) {
            sb.append((float)turns.elementAt(i) + "\t");
        }
        Logger.log("" + (Object)sb);
        return "" + (Object)sb;
    }
    
    void troughsToTime(final Vector<Float> turns, final Vector<Integer> v, final float[] swEd, final String title, final int filterComp, final boolean lastTune) {
        if (this.transcribedNotes == null) {
            return;
        }
        for (int i = 0; i < v.size(); ++i) {
            int troughIndex = v.elementAt(i);
            if (!lastTune || i != v.size() - 1) {
                troughIndex -= filterComp;
            }
            int sti;
            for (sti = troughIndex; swEd[sti - 1] == swEd[sti]; --sti) {}
            int j;
            for (j = 0; j < this.transcribedNotes.length && sti > this.transcribedNotes[j].getQuaverQ(); ++j) {}
            if (j >= this.transcribedNotes.length) {
                j = this.transcribedNotes.length - 1;
            }
            turns.add(new Float(this.transcribedNotes[j].getStart()));
            Logger.log(title + "\t" + sti + "\t" + this.transcribedNotes[j].getStart());
        }
    }
    
    public Object getLock() {
        return this.lock;
    }
    
    public void setLock(final Object lock) {
        this.lock = lock;
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
