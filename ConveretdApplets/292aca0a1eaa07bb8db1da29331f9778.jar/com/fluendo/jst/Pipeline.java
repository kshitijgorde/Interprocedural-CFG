// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.NoSuchElementException;
import java.util.Hashtable;
import com.fluendo.utils.Debug;
import java.util.Enumeration;
import java.util.Vector;

public class Pipeline extends Element implements BusSyncHandler
{
    protected Vector elements;
    protected Clock defClock;
    protected Clock fixedClock;
    protected Element clockProvider;
    protected Vector messages;
    protected Bus internalBus;
    private BusThread busThread;
    private StateThread stateThread;
    private boolean stateDirty;
    private boolean polling;
    protected long streamTime;
    
    public Pipeline() {
        this(null);
    }
    
    public String getFactoryName() {
        return "pipeline";
    }
    
    public Pipeline(final String name) {
        super(name);
        this.elements = new Vector();
        this.fixedClock = null;
        this.messages = new Vector();
        this.stateDirty = false;
        this.polling = false;
        this.defClock = new SystemClock();
        this.clockProvider = null;
        (this.internalBus = new Bus()).setSyncHandler(this);
        this.bus = new Bus();
        (this.busThread = new BusThread(this.bus)).start();
        (this.stateThread = new StateThread()).start();
    }
    
    public synchronized void shutDown() {
        if (this.stateThread != null) {
            this.stateThread.shutDown();
            this.stateThread = null;
        }
        if (this.busThread != null) {
            this.busThread.shutDown();
            this.busThread = null;
        }
    }
    
    public void useClock(final Clock clock) {
        this.fixedClock = clock;
    }
    
    public boolean add(final Element elem) {
        if (elem == null) {
            return false;
        }
        if (elem instanceof ClockProvider) {
            this.defClock = ((ClockProvider)elem).provideClock();
            this.clockProvider = elem;
        }
        this.elements.addElement(elem);
        elem.baseTime = this.baseTime;
        elem.setBus(this.internalBus);
        return true;
    }
    
    public boolean remove(final Element elem) {
        if (elem == null) {
            return false;
        }
        final boolean res;
        if (res = this.elements.removeElement(elem)) {
            if (elem == this.clockProvider) {
                this.defClock = new SystemClock();
                this.clockProvider = null;
            }
            elem.setBus(null);
            elem.setClock(null);
            synchronized (this) {
                this.stateDirty = true;
            }
        }
        return res;
    }
    
    public Enumeration enumElements() {
        return this.elements.elements();
    }
    
    public Enumeration enumSorted() {
        return new SortedEnumerator();
    }
    
    public Enumeration enumSinks() {
        return new SinkEnumerator();
    }
    
    private void replaceMessage(final Message message, final int type) {
        final int len = this.messages.size();
        final Object src = message.getSrc();
        for (int i = 0; i < len; ++i) {
            final Message msg = this.messages.elementAt(i);
            if (msg.getType() == type && msg.getSrc() == src) {
                this.messages.setElementAt(message, i);
                return;
            }
        }
        this.messages.addElement(message);
    }
    
    private boolean findMessage(final Object obj, final int type) {
        for (int len = this.messages.size(), i = 0; i < len; ++i) {
            final Message msg = this.messages.elementAt(i);
            if (msg.getType() == type && msg.getSrc() == obj) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isEOS() {
        final Enumeration e = this.enumSinks();
        while (e.hasMoreElements()) {
            final Object obj = e.nextElement();
            if (!this.findMessage(obj, 1)) {
                return false;
            }
        }
        return true;
    }
    
    public int handleSyncMessage(final Message message) {
        switch (message.getType()) {
            case 1: {
                final boolean isEOS;
                synchronized (this) {
                    Debug.log(3, this + " got EOS from sink: " + message.getSrc());
                    this.replaceMessage(message, 1);
                    isEOS = this.isEOS();
                }
                if (isEOS) {
                    Debug.log(3, "all sinks posted EOS " + this);
                    this.postMessage(Message.newEOS(this));
                    break;
                }
                break;
            }
            case 128: {
                this.scheduleReCalcState();
                break;
            }
            default: {
                this.postMessage(message);
                break;
            }
        }
        return 0;
    }
    
    public int getState(final int[] resState, final int[] resPending, final long timeout) {
        this.reCalcState(false);
        return super.getState(resState, resPending, timeout);
    }
    
    protected void scheduleReCalcState() {
        synchronized (this) {
            this.stateDirty = true;
            this.stateThread.stateDirty();
        }
    }
    
    private void reCalcState(final boolean force) {
        int res = 1;
        boolean haveAsync;
        boolean haveNoPreroll;
        synchronized (this) {
            if (force) {
                this.stateDirty = true;
            }
            if (!this.stateDirty) {
                return;
            }
            if (this.polling) {
                return;
            }
            this.polling = true;
            this.stateDirty = false;
            haveAsync = false;
            haveNoPreroll = false;
        }
        final Enumeration e = this.elements.elements();
        while (e.hasMoreElements()) {
            final Element elem = e.nextElement();
            res = elem.getState(null, null, 1L);
            switch (res) {
                case 2: {
                    haveAsync = true;
                    break;
                }
                case 3: {
                    haveNoPreroll = true;
                    break;
                }
            }
            if (res == 0) {
                break;
            }
        }
        if (res != 0) {
            if (haveNoPreroll) {
                res = 3;
            }
            if (haveAsync) {
                res = 2;
            }
        }
        synchronized (this) {
            this.polling = false;
        }
        switch (res) {
            case 1:
            case 3: {
                res = this.continueState(res);
                break;
            }
            case 2: {
                this.lostState();
                break;
            }
            case 0: {
                this.abortState();
                break;
            }
        }
    }
    
    protected int doChildStateChange(final int transition) {
        final int next = this.getTransitionNext(transition);
        boolean haveAsync = false;
        boolean haveNoPreroll = false;
        final Enumeration e = this.enumSorted();
        while (e.hasMoreElements()) {
            final Element elem = e.nextElement();
            elem.setBus(this.internalBus);
            elem.setClock(this.defClock);
            elem.baseTime = this.baseTime;
            Debug.log(4, this + " setting state " + next + " on " + elem);
            final int result = elem.setState(next);
            Debug.log(4, this + " " + elem + " changed state " + result);
            switch (result) {
                case 2: {
                    haveAsync = true;
                    continue;
                }
                case 3: {
                    haveNoPreroll = true;
                    continue;
                }
                case 0: {
                    return result;
                }
            }
        }
        int result = super.changeState(transition);
        if (result == 0) {
            return result;
        }
        if (haveNoPreroll) {
            result = 3;
        }
        else if (haveAsync) {
            result = 2;
        }
        return result;
    }
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                this.messages.setSize(0);
                break;
            }
            case 35: {
                final long now = this.defClock.getTime();
                this.baseTime = now - this.streamTime;
                break;
            }
        }
        final int result = this.doChildStateChange(transition);
        switch (transition) {
            case 18: {
                this.streamTime = 0L;
                break;
            }
            case 50: {
                final long now = this.defClock.getTime();
                this.streamTime = now - this.baseTime;
                this.messages.setSize(0);
                break;
            }
            case 33: {
                this.messages.setSize(0);
                break;
            }
        }
        return result;
    }
    
    protected boolean doSendEvent(final Event event) {
        boolean res = true;
        final Enumeration e = this.enumSinks();
        while (e.hasMoreElements()) {
            final Element elem = e.nextElement();
            res &= elem.sendEvent(event);
        }
        return res;
    }
    
    private boolean doSeek(final Event event) {
        final int[] state = { 0 };
        this.getState(state, null, 0L);
        final boolean wasPlaying = state[0] == 3;
        if (wasPlaying) {
            this.setState(2);
        }
        final boolean ret = this.doSendEvent(event);
        if (ret) {
            this.streamTime = 0L;
        }
        if (wasPlaying) {
            this.setState(3);
        }
        return ret;
    }
    
    public boolean sendEvent(final Event event) {
        switch (event.getType()) {
            case 5: {
                return this.doSeek(event);
            }
            default: {
                return this.doSendEvent(event);
            }
        }
    }
    
    public boolean query(final Query query) {
        boolean res = true;
        final Enumeration e = this.enumSinks();
        while (e.hasMoreElements()) {
            final Element elem = e.nextElement();
            if (res = elem.query(query)) {
                break;
            }
        }
        return res;
    }
    
    private class BusThread extends Thread
    {
        private Bus bus;
        private boolean stopping;
        
        public BusThread(final Bus bus) {
            super("cortado-BusThread-" + Debug.genId());
            this.bus = bus;
            this.stopping = false;
        }
        
        public void run() {
            while (!this.stopping) {
                this.bus.waitAndDispatch();
            }
        }
        
        public void shutDown() {
            this.stopping = true;
            this.bus.setFlushing(true);
        }
    }
    
    private class StateThread extends Thread
    {
        private boolean stopping;
        private boolean stateDirty;
        
        public StateThread() {
            super("cortado-StateThread-" + Debug.genId());
            this.stopping = false;
            this.stateDirty = false;
        }
        
        public void run() {
            while (!this.stopping) {
                synchronized (this) {
                    while (!this.stateDirty && !this.stopping) {
                        try {
                            this.wait();
                        }
                        catch (InterruptedException e) {}
                    }
                    this.stateDirty = false;
                }
                if (!this.stopping) {
                    synchronized (Pipeline.this.stateLock) {
                        Pipeline.this.reCalcState(false);
                    }
                }
            }
        }
        
        public synchronized void stateDirty() {
            this.stateDirty = true;
            this.notifyAll();
        }
        
        public synchronized void shutDown() {
            this.stopping = true;
            this.notifyAll();
        }
    }
    
    private class SortedEnumerator implements Enumeration
    {
        private Vector queue;
        private Hashtable hash;
        private Object next;
        private int mode;
        
        private void addToQueue(final Element elem) {
            this.queue.addElement(elem);
            this.hash.put(elem, new Integer(-1));
        }
        
        private void updateDegree(final Element elem) {
            final Enumeration p = elem.enumPads();
            while (p.hasMoreElements()) {
                final Pad pad = p.nextElement();
                if (pad.direction == 2) {
                    final Pad peer = pad.peer;
                    if (peer == null) {
                        continue;
                    }
                    final Element peerParent = (Element)peer.parent;
                    if (peerParent == null) {
                        continue;
                    }
                    final int oldDeg = this.hash.get(peerParent);
                    final int newDeg = oldDeg + this.mode;
                    if (newDeg == 0) {
                        this.addToQueue(peerParent);
                    }
                    else {
                        this.hash.put(peerParent, new Integer(newDeg));
                    }
                }
            }
        }
        
        public SortedEnumerator() {
            this.queue = new Vector();
            this.hash = new Hashtable();
            Enumeration e = Pipeline.this.enumElements();
            while (e.hasMoreElements()) {
                final Element elem = e.nextElement();
                if (elem.isFlagSet(32)) {
                    this.addToQueue(elem);
                }
                else {
                    this.hash.put(elem, new Integer(0));
                }
            }
            this.mode = 1;
            e = Pipeline.this.enumElements();
            while (e.hasMoreElements()) {
                this.updateDegree(e.nextElement());
            }
            this.mode = -1;
            this.queueNextElement();
        }
        
        private void queueNextElement() {
            if (this.queue.isEmpty()) {
                int bestDeg = Integer.MAX_VALUE;
                Element bestElem = null;
                final Enumeration e = Pipeline.this.enumElements();
                while (e.hasMoreElements()) {
                    final Element elem = e.nextElement();
                    final int deg = this.hash.get(elem);
                    if (deg < 0) {
                        continue;
                    }
                    if (bestElem != null && bestDeg <= deg) {
                        continue;
                    }
                    bestElem = elem;
                    bestDeg = deg;
                }
                if (bestElem != null) {
                    if (bestDeg != 0) {
                        System.out.println(this + " loop detected in pipeline!!");
                    }
                    this.next = bestElem;
                    this.hash.put(this.next, new Integer(-1));
                }
                else {
                    this.next = null;
                }
            }
            else {
                this.next = this.queue.elementAt(0);
                this.queue.removeElementAt(0);
            }
            if (this.next != null) {
                this.updateDegree((Element)this.next);
            }
        }
        
        public boolean hasMoreElements() {
            return this.next != null;
        }
        
        public Object nextElement() throws NoSuchElementException {
            final Object result = this.next;
            if (result == null) {
                throw new NoSuchElementException();
            }
            this.queueNextElement();
            return result;
        }
    }
    
    private class SinkEnumerator implements Enumeration
    {
        private Enumeration e;
        private Object next;
        
        public SinkEnumerator() {
            this.e = Pipeline.this.enumElements();
            this.queueNextElement();
        }
        
        private void queueNextElement() {
            this.next = null;
            while (this.e.hasMoreElements()) {
                final Element elem = this.e.nextElement();
                if (elem.isFlagSet(32)) {
                    this.next = elem;
                    break;
                }
            }
        }
        
        public boolean hasMoreElements() {
            return this.next != null;
        }
        
        public Object nextElement() throws NoSuchElementException {
            final Object result = this.next;
            if (result == null) {
                throw new NoSuchElementException();
            }
            this.queueNextElement();
            return result;
        }
    }
}
