import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Algorithm
{
    public static final int ALGORITM_FCFS = 1;
    public static final int ALGORITM_SJF = 2;
    public static final int ALGORITM_SRTF = 3;
    public static final int ALGORITM_RR = 4;
    private Vector _vecProcs;
    private int _intAlgoritm;
    private int _intQuantum;
    private int _intPenalty;
    private int _cpuTime;
    private int _runProcId;
    private int _oldProcId;
    private int _remPenaltyTime;
    private int _remQuantumTime;
    private boolean _boolInThePenalty;
    
    public Algorithm(final int algorithm, final int quantum, final int penalty, final Vector procs) {
        this._cpuTime = 0;
        this._runProcId = 0;
        this._remPenaltyTime = 0;
        this._remQuantumTime = 0;
        this._vecProcs = procs;
        this._intAlgoritm = algorithm;
        this._intQuantum = quantum;
        this._intPenalty = penalty;
    }
    
    public boolean incrClock1ms() {
        switch (this._intAlgoritm) {
            case 1: {
                return this.incrFCFS();
            }
            case 2: {
                return this.incrSJF();
            }
            case 3: {
                return this.incrSRTF();
            }
            case 4: {
                return this.incrRR();
            }
            default: {
                return true;
            }
        }
    }
    
    private boolean incrFCFS() {
        CpuProcess running = this._vecProcs.get(0);
        boolean allDone = true;
        if (this._remPenaltyTime == 0) {
            for (int i = 1; i < this._vecProcs.size(); ++i) {
                final CpuProcess proc = this._vecProcs.get(i);
                if (!proc.isDone()) {
                    allDone = false;
                    if (this._cpuTime >= proc.getTOA()) {
                        running = proc;
                        break;
                    }
                }
            }
            if (running.getProcId() == 0 && allDone) {
                this._runProcId = 0;
                this._remPenaltyTime = 0;
                this._boolInThePenalty = false;
                return true;
            }
        }
        this.processRunning(running);
        return allDone;
    }
    
    private boolean incrSRTF() {
        CpuProcess running = this._vecProcs.get(0);
        boolean allDone = true;
        if (this._remPenaltyTime == 0) {
            final Vector readyProcs = new Vector();
            for (int i = 1; i < this._vecProcs.size(); ++i) {
                final CpuProcess proc = this._vecProcs.get(i);
                if (!proc.isDone()) {
                    allDone = false;
                    if (this._cpuTime >= proc.getTOA()) {
                        readyProcs.add(proc);
                    }
                }
            }
            if (readyProcs.size() > 0) {
                Collections.sort((List<Object>)readyProcs, new SortBySRTF());
                running = readyProcs.get(0);
            }
            else if (allDone) {
                this._runProcId = 0;
                this._remPenaltyTime = 0;
                this._boolInThePenalty = false;
                return true;
            }
        }
        this.processRunning(running);
        return false;
    }
    
    private boolean incrSJF() {
        CpuProcess running = this._vecProcs.get(0);
        boolean allDone = true;
        if (this._remPenaltyTime == 0) {
            running = this._vecProcs.get(this._runProcId);
            if (running.isDone() || this._runProcId == 0) {
                final Vector readyProcs = new Vector();
                for (int i = 1; i < this._vecProcs.size(); ++i) {
                    final CpuProcess proc = this._vecProcs.get(i);
                    if (!proc.isDone()) {
                        allDone = false;
                        if (this._cpuTime >= proc.getTOA()) {
                            readyProcs.add(proc);
                        }
                    }
                }
                if (readyProcs.size() > 0) {
                    Collections.sort((List<Object>)readyProcs, new SortBySJF());
                    running = readyProcs.get(0);
                }
                else if (running.isDone() && !allDone) {
                    running = this._vecProcs.get(0);
                }
                else if (allDone) {
                    this._runProcId = 0;
                    this._remPenaltyTime = 0;
                    this._boolInThePenalty = false;
                    return true;
                }
            }
        }
        this.processRunning(running);
        return false;
    }
    
    private boolean incrRR() {
        CpuProcess running = this._vecProcs.get(0);
        boolean allDone = true;
        if (this._remPenaltyTime == 0) {
            for (int i = 1; i < this._vecProcs.size(); ++i) {
                final CpuProcess proc = this._vecProcs.get(i);
                if (!proc.isDone()) {
                    allDone = false;
                    if (this._cpuTime < proc.getTOA()) {}
                }
            }
            running = this._vecProcs.get(this._oldProcId);
            if (allDone) {
                this._runProcId = 0;
                this._remPenaltyTime = 0;
                this._remQuantumTime = 0;
                this._boolInThePenalty = false;
                return true;
            }
            if (this._remQuantumTime == 0 || running.isDone()) {
                int nextProc;
                for (nextProc = ((this._oldProcId == this._vecProcs.size() - 1) ? 1 : (this._oldProcId + 1)), running = this._vecProcs.get(nextProc); running.isDone() && ++nextProc <= this._vecProcs.size() - 1; running = this._vecProcs.get(nextProc)) {}
                if (this._cpuTime < running.getTOA() || running.isDone()) {
                    for (nextProc = 1, running = this._vecProcs.get(nextProc); running.isDone() && ++nextProc <= this._vecProcs.size() - 1; running = this._vecProcs.get(nextProc)) {}
                    if (this._cpuTime < running.getTOA() || running.isDone()) {
                        running = this._vecProcs.get(0);
                    }
                }
            }
        }
        this.processRunning(running);
        return false;
    }
    
    private void processRunning(CpuProcess running) {
        ++this._cpuTime;
        final boolean procChange = this._runProcId > 0 && this._runProcId != running.getProcId();
        if (procChange && this._intPenalty > 0) {
            running = this._vecProcs.get(0);
            this._remPenaltyTime = this._intPenalty;
            this._remQuantumTime = 0;
        }
        this._runProcId = running.getProcId();
        running.incrRunTime();
        if (this._runProcId > 0) {
            if (this._oldProcId != this._runProcId) {
                this._remQuantumTime = this._intQuantum;
                this._oldProcId = this._runProcId;
            }
            if (running.getRunTime() == 1) {
                running.setStartTime(this._cpuTime - 1);
            }
            if (running.isDone()) {
                running.setEndTime(this._cpuTime);
            }
            if (this._remQuantumTime > 0) {
                --this._remQuantumTime;
            }
        }
        else {
            this._boolInThePenalty = (this._remPenaltyTime > 0);
            if (this._boolInThePenalty) {
                --this._remPenaltyTime;
            }
        }
        for (int i = 0; i < this._vecProcs.size(); ++i) {
            final CpuProcess proc = this._vecProcs.get(i);
            if (i != 0 || !this._boolInThePenalty) {
                if (this._runProcId != proc.getProcId()) {
                    if (i != 0 && this._runProcId != proc.getProcId() && !proc.isDone() && this._cpuTime > proc.getTOA()) {
                        proc.incrWaitTime();
                    }
                }
            }
        }
    }
    
    public Vector getProcs() {
        return this._vecProcs;
    }
    
    public int getRunning() {
        return this._runProcId;
    }
    
    public int getCpuTime() {
        return this._cpuTime;
    }
    
    public int getAlgorithm() {
        return this._intAlgoritm;
    }
    
    public int getJobsStarted() {
        int time = 0;
        for (int i = 1; i < this._vecProcs.size(); ++i) {
            final CpuProcess proc = this._vecProcs.get(i);
            if (proc.getRunTime() > 0) {
                ++time;
            }
        }
        return time;
    }
    
    public int getJobsCompleted() {
        int time = 0;
        for (int i = 1; i < this._vecProcs.size(); ++i) {
            final CpuProcess proc = this._vecProcs.get(i);
            if (proc.isDone()) {
                ++time;
            }
        }
        return time;
    }
    
    public String toString() {
        final StringBuffer results = new StringBuffer(CpuProcess.toHeaderString() + "\n");
        for (int i = 0; i < this._vecProcs.size(); ++i) {
            final CpuProcess proc = this._vecProcs.get(i);
            results.append(proc.toString());
            if (i == 0 && this._boolInThePenalty) {
                results.append("..Switching");
            }
            else if (this._runProcId == proc.getProcId()) {
                results.append("..Running");
            }
            results.append('\n');
        }
        results.append("Total CPU Time = " + this._cpuTime + "\n");
        return results.toString();
    }
}
