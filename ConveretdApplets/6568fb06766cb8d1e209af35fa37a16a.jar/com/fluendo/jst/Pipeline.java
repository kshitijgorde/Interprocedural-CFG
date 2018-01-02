// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Hashtable;
import java.util.NoSuchElementException;
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
    
    public Pipeline(final String s) {
        super(s);
        this.elements = new Vector();
        this.fixedClock = null;
        this.messages = new Vector();
        this.stateDirty = false;
        this.polling = false;
        this.defClock = new SystemClock();
        this.clockProvider = null;
        (this.internalBus = new Bus()).setSyncHandler(this);
        super.bus = new Bus();
        (this.busThread = new BusThread(super.bus)).start();
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
    
    public void useClock(final Clock fixedClock) {
        this.fixedClock = fixedClock;
    }
    
    public boolean add(final Element clockProvider) {
        if (clockProvider == null) {
            return false;
        }
        if (clockProvider instanceof ClockProvider) {
            this.defClock = ((ClockProvider)clockProvider).provideClock();
            this.clockProvider = clockProvider;
        }
        this.elements.addElement(clockProvider);
        clockProvider.baseTime = super.baseTime;
        clockProvider.setBus(this.internalBus);
        return true;
    }
    
    public boolean remove(final Element element) {
        if (element == null) {
            return false;
        }
        final boolean removeElement;
        if (removeElement = this.elements.removeElement(element)) {
            if (element == this.clockProvider) {
                this.defClock = new SystemClock();
                this.clockProvider = null;
            }
            element.setBus(null);
            element.setClock(null);
            synchronized (this) {
                this.stateDirty = true;
            }
        }
        return removeElement;
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
    
    private void replaceMessage(final Message message, final int n) {
        final int size = this.messages.size();
        final Object src = message.getSrc();
        for (int i = 0; i < size; ++i) {
            final Message message2 = this.messages.elementAt(i);
            if (message2.getType() == n && message2.getSrc() == src) {
                this.messages.setElementAt(message, i);
                return;
            }
        }
        this.messages.addElement(message);
    }
    
    private boolean findMessage(final Object object, final int n) {
        for (int size = this.messages.size(), i = 0; i < size; ++i) {
            final Message message = this.messages.elementAt(i);
            if (message.getType() == n && message.getSrc() == object) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isEOS() {
        final Enumeration enumSinks = this.enumSinks();
        while (enumSinks.hasMoreElements()) {
            if (!this.findMessage(enumSinks.nextElement(), 1)) {
                return false;
            }
        }
        return true;
    }
    
    public int handleSyncMessage(final Message message) {
        switch (message.getType()) {
            case 1: {
                final boolean eos;
                synchronized (this) {
                    Debug.log(3, this + " got EOS from sink: " + message.getSrc());
                    this.replaceMessage(message, 1);
                    eos = this.isEOS();
                }
                if (eos) {
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
    
    public int getState(final int[] array, final int[] array2, final long n) {
        this.reCalcState(false);
        return super.getState(array, array2, n);
    }
    
    protected void scheduleReCalcState() {
        synchronized (this) {
            this.stateDirty = true;
            this.stateThread.stateDirty();
        }
    }
    
    private void reCalcState(final boolean b) {
        int state = 1;
        Debug.debug("Pipeline.reCalcState");
        boolean b2;
        boolean b3;
        synchronized (this) {
            if (b) {
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
            b2 = false;
            b3 = false;
        }
        final Enumeration<Element> elements = this.elements.elements();
        while (elements.hasMoreElements()) {
            state = elements.nextElement().getState(null, null, 1L);
            switch (state) {
                case 2: {
                    b2 = true;
                    break;
                }
                case 3: {
                    b3 = true;
                    break;
                }
            }
            if (state == 0) {
                break;
            }
        }
        if (state != 0) {
            if (b3) {
                state = 3;
            }
            if (b2) {
                state = 2;
            }
        }
        synchronized (this) {
            this.polling = false;
        }
        switch (state) {
            case 1:
            case 3: {
                this.continueState(state);
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
    
    protected int doChildStateChange(final int n) {
        final int transitionNext = this.getTransitionNext(n);
        boolean b = false;
        boolean b2 = false;
        final Enumeration enumSorted = this.enumSorted();
        while (enumSorted.hasMoreElements()) {
            final Element element = enumSorted.nextElement();
            element.setBus(this.internalBus);
            element.setClock(this.defClock);
            element.baseTime = super.baseTime;
            Debug.log(4, this + " setting state " + Element.getStateName(transitionNext) + " on " + element);
            final int setState = element.setState(transitionNext);
            Debug.log(4, this + " " + element + " changed state, result = " + Element.getStateReturnName(setState));
            switch (setState) {
                case 2: {
                    b = true;
                    continue;
                }
                case 3: {
                    b2 = true;
                    continue;
                }
                case 0: {
                    return setState;
                }
            }
        }
        int changeState = super.changeState(n);
        if (changeState == 0) {
            return changeState;
        }
        if (b2) {
            changeState = 3;
        }
        else if (b) {
            changeState = 2;
        }
        return changeState;
    }
    
    protected int changeState(final int n) {
        switch (n) {
            case 18: {
                this.messages.setSize(0);
                break;
            }
            case 35: {
                super.baseTime = this.defClock.getTime() - this.streamTime;
                break;
            }
        }
        final int doChildStateChange = this.doChildStateChange(n);
        switch (n) {
            case 18: {
                this.streamTime = 0L;
                break;
            }
            case 50: {
                this.streamTime = this.defClock.getTime() - super.baseTime;
                this.messages.setSize(0);
                break;
            }
            case 33: {
                this.messages.setSize(0);
                break;
            }
        }
        return doChildStateChange;
    }
    
    protected boolean doSendEvent(final Event event) {
        boolean b = true;
        final Enumeration enumSinks = this.enumSinks();
        while (enumSinks.hasMoreElements()) {
            b &= enumSinks.nextElement().sendEvent(event);
        }
        return b;
    }
    
    private boolean doSeek(final Event event) {
        final int[] array = { 0 };
        this.getState(array, null, 0L);
        final boolean b = array[0] == 3;
        if (b) {
            this.setState(2);
        }
        final boolean doSendEvent = this.doSendEvent(event);
        if (doSendEvent) {
            this.streamTime = 0L;
        }
        if (b) {
            this.setState(3);
        }
        return doSendEvent;
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
        boolean query2 = true;
        final Enumeration enumSinks = this.enumSinks();
        while (enumSinks.hasMoreElements() && !(query2 = enumSinks.nextElement().query(query))) {}
        return query2;
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
                final Element next = this.e.nextElement();
                if (next.isFlagSet(32)) {
                    this.next = next;
                    break;
                }
            }
        }
        
        public boolean hasMoreElements() {
            return this.next != null;
        }
        
        public Object nextElement() throws NoSuchElementException {
            final Object next = this.next;
            if (next == null) {
                throw new NoSuchElementException();
            }
            this.queueNextElement();
            return next;
        }
    }
    
    private class SortedEnumerator implements Enumeration
    {
        private Vector queue;
        private Hashtable hash;
        private Object next;
        private int mode;
        
        private void addToQueue(final Element element) {
            this.queue.addElement(element);
            this.hash.put(element, new Integer(-1));
        }
        
        private void updateDegree(final Element element) {
            final Enumeration enumPads = element.enumPads();
            while (enumPads.hasMoreElements()) {
                final Pad pad = enumPads.nextElement();
                if (pad.direction == 2) {
                    final Pad peer = pad.peer;
                    if (peer == null) {
                        continue;
                    }
                    final Element element2 = (Element)peer.parent;
                    if (element2 == null) {
                        continue;
                    }
                    final int n = this.hash.get(element2) + this.mode;
                    if (n == 0) {
                        this.addToQueue(element2);
                    }
                    else {
                        this.hash.put(element2, new Integer(n));
                    }
                }
            }
        }
        
        public SortedEnumerator() {
            this.queue = new Vector();
            this.hash = new Hashtable();
            final Enumeration enumElements = Pipeline.this.enumElements();
            while (enumElements.hasMoreElements()) {
                final Element element = enumElements.nextElement();
                if (element.isFlagSet(32)) {
                    this.addToQueue(element);
                }
                else {
                    this.hash.put(element, new Integer(0));
                }
            }
            this.mode = 1;
            final Enumeration enumElements2 = Pipeline.this.enumElements();
            while (enumElements2.hasMoreElements()) {
                this.updateDegree(enumElements2.nextElement());
            }
            this.mode = -1;
            this.queueNextElement();
        }
        
        private void queueNextElement() {
            if (this.queue.isEmpty()) {
                int n = Integer.MAX_VALUE;
                Object next = null;
                final Enumeration enumElements = Pipeline.this.enumElements();
                while (enumElements.hasMoreElements()) {
                    final Element element = enumElements.nextElement();
                    final int intValue = this.hash.get(element);
                    if (intValue < 0) {
                        continue;
                    }
                    if (next != null && n <= intValue) {
                        continue;
                    }
                    next = element;
                    n = intValue;
                }
                if (next != null) {
                    if (n != 0) {
                        System.out.println(this + " loop detected in pipeline!!");
                    }
                    this.next = next;
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
            final Object next = this.next;
            if (next == null) {
                throw new NoSuchElementException();
            }
            this.queueNextElement();
            return next;
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
                        catch (InterruptedException ex) {}
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
}
