// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Constant;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import edu.hws.jcm.data.Value;
import java.awt.Panel;

public class Animator extends Panel implements Value, Tieable, InputObject, ActionListener, ItemListener, Runnable
{
    public static final int START_STOP_BUTTON = 1;
    public static final int START_BUTTON = 2;
    public static final int PAUSE_BUTTON = 4;
    public static final int STOP_BUTTON = 8;
    public static final int NEXT_BUTTON = 16;
    public static final int PREV_BUTTON = 32;
    public static final int SPEED_CHOICE = 64;
    public static final int LOOP_CHOICE = 128;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 0;
    public static final int ONCE = 0;
    public static final int LOOP = 1;
    public static final int BACK_AND_FORTH = 2;
    private Button startStopButton;
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    private Button nextButton;
    private Button prevButton;
    private Choice speedChoice;
    private Choice loopChoice;
    private int orientation;
    private String startButtonName;
    private String stopButtonName;
    private volatile int loopStyle;
    private boolean runningBackwards;
    private volatile int millisPerFrame;
    private volatile int frame;
    private int maxFrame;
    private double value;
    private volatile long serialNumber;
    private Computable onChange;
    private Value min;
    private Value max;
    private Value intervals;
    private boolean needsValueCheck;
    private double min_val;
    private double max_val;
    private int intervals_val;
    private static int START;
    private static int PAUSE;
    private static int NEXT;
    private static int PREV;
    private static int STOP;
    private static int RUN;
    private Thread runner;
    private volatile int status;
    private boolean undefinedWhenNotRunning;
    
    static {
        Animator.START = 0;
        Animator.PAUSE = 1;
        Animator.NEXT = 2;
        Animator.PREV = 3;
        Animator.STOP = 4;
        Animator.RUN = 5;
    }
    
    public Animator() {
        this(1, 0);
    }
    
    public Animator(final int n) {
        this(n, 0);
    }
    
    public Animator(final int n, final int n2) {
        this(n, n2, null, null, null);
    }
    
    public Animator(final int n, final int orientation, final Value min, final Value max, final Value intervals) {
        this.startButtonName = "Start";
        this.stopButtonName = "Stop";
        this.millisPerFrame = 100;
        this.serialNumber = 1L;
        this.needsValueCheck = true;
        this.status = Animator.STOP;
        this.min = min;
        this.max = max;
        this.intervals = intervals;
        this.orientation = orientation;
        if (orientation == 1) {
            this.setLayout(new GridLayout(0, 1));
        }
        else {
            this.setLayout(new GridLayout(1, 0));
        }
        for (int i = 1; i <= 128; i <<= 1) {
            if ((i & n) != 0x0) {
                this.addControl(i);
            }
        }
    }
    
    public Component getControl(final int n) {
        switch (n) {
            case 1: {
                if (this.startStopButton == null) {
                    (this.startStopButton = new Button(this.startButtonName)).setBackground(Color.lightGray);
                    this.startStopButton.addActionListener(this);
                }
                return this.startStopButton;
            }
            case 2: {
                if (this.startButton == null) {
                    (this.startButton = new Button(this.startButtonName)).setBackground(Color.lightGray);
                    this.startButton.addActionListener(this);
                }
                return this.startButton;
            }
            case 8: {
                if (this.stopButton == null) {
                    (this.stopButton = new Button(this.stopButtonName)).setBackground(Color.lightGray);
                    this.stopButton.addActionListener(this);
                    this.stopButton.setEnabled(false);
                }
                return this.stopButton;
            }
            case 4: {
                if (this.pauseButton == null) {
                    (this.pauseButton = new Button("Pause")).setBackground(Color.lightGray);
                    this.pauseButton.addActionListener(this);
                    this.pauseButton.setEnabled(false);
                }
                return this.pauseButton;
            }
            case 16: {
                if (this.nextButton == null) {
                    (this.nextButton = new Button("Next")).setBackground(Color.lightGray);
                    this.nextButton.addActionListener(this);
                }
                return this.nextButton;
            }
            case 32: {
                if (this.prevButton == null) {
                    (this.prevButton = new Button("Prev")).setBackground(Color.lightGray);
                    this.prevButton.addActionListener(this);
                }
                return this.prevButton;
            }
            case 64: {
                if (this.speedChoice == null) {
                    (this.speedChoice = new Choice()).add("Fastest");
                    this.speedChoice.add("Fast");
                    this.speedChoice.add("Moderate");
                    this.speedChoice.add("Slow");
                    this.speedChoice.add("Slower");
                    this.speedChoice.select(2);
                    this.speedChoice.addItemListener(this);
                }
                return this.speedChoice;
            }
            case 128: {
                if (this.loopChoice == null) {
                    (this.loopChoice = new Choice()).add("Play Once");
                    this.loopChoice.add("Loop");
                    this.loopChoice.add("Back and Forth");
                    this.loopChoice.addItemListener(this);
                }
                return this.loopChoice;
            }
            default: {
                return null;
            }
        }
    }
    
    public Component addControl(final int n) {
        final Component control = this.getControl(n);
        if (control == null) {
            return null;
        }
        this.add(control);
        return control;
    }
    
    public void setStartButtonName(final String label) {
        if (label != null) {
            this.startButtonName = label;
            if (this.startStopButton != null) {
                this.startStopButton.setLabel(label);
            }
            if (this.startButton != null) {
                this.startButton.setLabel(label);
            }
        }
    }
    
    public void setStopButtonName(final String s) {
        if (s != null) {
            this.stopButtonName = s;
            if (this.stopButton != null) {
                this.stopButton.setLabel(s);
            }
        }
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != this.orientation && (orientation == 0 || orientation == 1)) {
            if ((this.orientation = orientation) == 1) {
                this.setLayout(new GridLayout(0, 1));
            }
            else {
                this.setLayout(new GridLayout(1, 0));
            }
            this.validate();
        }
    }
    
    public Value getMax() {
        return this.max;
    }
    
    public void setMax(final Value max) {
        this.max = max;
        this.needsValueCheck = true;
    }
    
    public void setMax(final double n) {
        this.setMax(new Constant(n));
    }
    
    public Value getMin() {
        return this.min;
    }
    
    public void setMin(final Value min) {
        this.min = min;
        this.needsValueCheck = true;
    }
    
    public void setMin(final double n) {
        this.setMin(new Constant(n));
    }
    
    public Value getIntervals() {
        return this.intervals;
    }
    
    public void setIntervals(final Value intervals) {
        this.intervals = intervals;
        this.needsValueCheck = true;
    }
    
    public void setIntervals(final int n) {
        this.setIntervals(new Constant(n));
    }
    
    public int getMillisPerFrame() {
        return this.millisPerFrame;
    }
    
    public void setMillisPerFrame(final int millisPerFrame) {
        this.millisPerFrame = millisPerFrame;
    }
    
    public int getLoopStyle() {
        return this.loopStyle;
    }
    
    public void setLoopStyle(final int loopStyle) {
        if (loopStyle >= 0 && loopStyle <= 2 && loopStyle != this.loopStyle) {
            this.loopStyle = loopStyle;
            if (this.loopChoice != null) {
                this.loopChoice.select(loopStyle);
            }
            this.runningBackwards = false;
        }
    }
    
    public synchronized void setVal(final double value) {
        if (this.needsValueCheck) {
            this.checkValue();
        }
        this.value = value;
        ++this.serialNumber;
        this.needsValueCheck = false;
        if (!Double.isNaN(value)) {
            if (this.min == null || this.max == null) {
                this.frame = (int)Math.round(value);
            }
            else if (!Double.isNaN(this.min_val) && !Double.isNaN(this.max_val) && this.min_val != this.max_val) {
                this.frame = (int)Math.round((value - this.min_val) / (this.max_val - this.min_val) * this.maxFrame);
            }
            if (this.frame < 0) {
                this.frame = 0;
            }
            else if (this.maxFrame > 0 && this.frame > this.maxFrame) {
                this.frame = this.maxFrame;
            }
        }
    }
    
    public double getVal() {
        if (this.needsValueCheck) {
            this.checkValue();
        }
        return this.value;
    }
    
    public Variable getValueAsVariable() {
        return this.getValueAsVariable("k");
    }
    
    public Variable getValueAsVariable(final String s) {
        return new Variable(s) {
            public void setVal(final double val) {
                Animator.this.setVal(val);
            }
            
            public double getVal() {
                return Animator.this.getVal();
            }
        };
    }
    
    public Computable getOnChange() {
        return this.onChange;
    }
    
    public void setOnChange(final Computable onChange) {
        this.onChange = onChange;
    }
    
    public void notifyControllerOnChange(final Controller onChange) {
        this.setOnChange(onChange);
    }
    
    public boolean getUndefinedWhenNotRunning() {
        return this.undefinedWhenNotRunning;
    }
    
    public void setUndefinedWhenNotRunning(final boolean undefinedWhenNotRunning) {
        this.undefinedWhenNotRunning = undefinedWhenNotRunning;
    }
    
    public synchronized void start() {
        if (this.runner != null && this.runner.isAlive() && this.status == Animator.STOP) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {}
            if (this.runner != null && this.runner.isAlive()) {
                this.runner.stop();
                this.runner = null;
            }
        }
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
            this.status = Animator.START;
            this.runner.start();
        }
        else if (this.status != Animator.RUN) {
            this.status = Animator.START;
            this.notify();
        }
    }
    
    public synchronized void pause() {
        if (this.status == Animator.RUN) {
            this.status = Animator.PAUSE;
            this.notify();
        }
    }
    
    public synchronized void next() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
            this.status = Animator.NEXT;
            this.runner.start();
        }
        else if (this.status == Animator.PAUSE) {
            this.status = Animator.NEXT;
            this.notify();
        }
    }
    
    public synchronized void prev() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
            this.status = Animator.PREV;
            this.runner.start();
        }
        else if (this.status == Animator.PAUSE) {
            this.status = Animator.PREV;
            this.notify();
        }
    }
    
    public synchronized void stop() {
        this.status = Animator.STOP;
        if (this.runner == null || !this.runner.isAlive()) {
            return;
        }
        this.notify();
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == null) {
            return;
        }
        if (source == this.startStopButton) {
            if (this.status == Animator.RUN) {
                this.stop();
            }
            else {
                this.start();
            }
        }
        else if (source == this.startButton) {
            this.start();
        }
        else if (source == this.stopButton) {
            this.stop();
        }
        else if (source == this.nextButton) {
            this.next();
        }
        else if (source == this.prevButton) {
            this.prev();
        }
        else if (source == this.pauseButton) {
            this.pause();
        }
    }
    
    public synchronized void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.loopChoice && this.loopChoice != null) {
            this.setLoopStyle(this.loopChoice.getSelectedIndex());
        }
        else if (itemEvent.getSource() == this.speedChoice && this.speedChoice != null) {
            switch (this.speedChoice.getSelectedIndex()) {
                case 0: {
                    this.millisPerFrame = 0;
                    break;
                }
                case 1: {
                    this.millisPerFrame = 30;
                    break;
                }
                case 2: {
                    this.millisPerFrame = 100;
                    break;
                }
                case 3: {
                    this.millisPerFrame = 500;
                    break;
                }
                case 4: {
                    this.millisPerFrame = 2000;
                    break;
                }
            }
        }
    }
    
    public void checkInput() {
        this.needsValueCheck = true;
    }
    
    public long getSerialNumber() {
        if (this.needsValueCheck) {
            this.checkValue();
        }
        return this.serialNumber;
    }
    
    public void sync(final Tie tie, final Tieable tieable) {
        if (tieable != this) {
            if (!(tieable instanceof Value)) {
                throw new IllegalArgumentException("Internal Error:  An Animator can only sync with Value objects.");
            }
            this.setVal(((Value)tieable).getVal());
            this.serialNumber = tieable.getSerialNumber();
        }
    }
    
    private synchronized void checkValue() {
        if (this.min != null) {
            this.min_val = this.min.getVal();
        }
        if (this.max != null) {
            this.max_val = this.max.getVal();
        }
        if (this.intervals == null) {
            this.intervals_val = 0;
        }
        else {
            final double val = this.intervals.getVal();
            if (Double.isNaN(val) || val <= 0.5) {
                this.intervals_val = 0;
            }
            else if (val > 100000.0) {
                this.intervals_val = 100000;
            }
            else {
                this.intervals_val = (int)Math.round(val);
            }
        }
        this.maxFrame = this.intervals_val;
        double value;
        if (this.min == null || this.max == null) {
            value = this.frame;
        }
        else if (Double.isNaN(this.min_val) || Double.isNaN(this.max_val) || Double.isInfinite(this.min_val) || Double.isInfinite(this.max_val)) {
            value = Double.NaN;
        }
        else if (this.intervals_val > 0) {
            value = this.min_val + this.frame * (this.max_val - this.min_val) / this.intervals_val;
        }
        else {
            this.maxFrame = 100;
            value = this.min_val + this.frame * (this.max_val - this.min_val) / 100.0;
        }
        if (this.undefinedWhenNotRunning && this.status == Animator.STOP) {
            value = Double.NaN;
        }
        this.value = value;
        this.needsValueCheck = false;
    }
    
    private void doControlStatus(final int n) {
        if (this.startStopButton != null) {
            this.startStopButton.setLabel((n == Animator.START) ? this.stopButtonName : this.startButtonName);
        }
        if (this.startButton != null) {
            this.startButton.setEnabled(n != Animator.START);
        }
        if (this.stopButton != null) {
            this.stopButton.setEnabled(n != Animator.STOP);
        }
        if (this.nextButton != null) {
            this.nextButton.setEnabled(n != Animator.START);
        }
        if (this.prevButton != null) {
            this.prevButton.setEnabled(n != Animator.START);
        }
        if (this.pauseButton != null) {
            this.pauseButton.setEnabled(n == Animator.START);
        }
    }
    
    private void doAdvanceFrame(final int n) {
        ++this.serialNumber;
        if (this.loopStyle == 2 && this.runningBackwards) {
            this.frame -= n;
        }
        else {
            this.frame += n;
        }
        if (this.frame < 0) {
            if (this.loopStyle == 1) {
                this.frame = this.maxFrame;
            }
            else if (this.loopStyle == 2) {
                this.frame = 1;
                if (n == 1) {
                    this.runningBackwards = false;
                }
                else {
                    this.runningBackwards = true;
                }
            }
            else {
                this.frame = 0;
            }
        }
        else if (this.maxFrame > 0 && this.frame > this.maxFrame) {
            if (this.loopStyle == 1) {
                this.frame = 1;
            }
            else {
                if (this.loopStyle == 0) {
                    this.frame = this.maxFrame;
                    this.status = Animator.STOP;
                    return;
                }
                this.frame = this.maxFrame - 1;
                if (n == 1) {
                    this.runningBackwards = true;
                }
                else {
                    this.runningBackwards = false;
                }
            }
        }
        if (this.onChange != null) {
            this.onChange.compute();
        }
        else {
            this.needsValueCheck = true;
        }
    }
    
    public void run() {
        int n = this.status;
        long n2 = 0L;
        this.runningBackwards = false;
        Label_0096: {
            if (this.frame == 0 && !this.undefinedWhenNotRunning) {
                break Label_0096;
            }
            this.frame = 0;
            ++this.serialNumber;
            n2 = System.currentTimeMillis();
            if (this.onChange != null) {
                this.onChange.compute();
            }
            else {
                this.needsValueCheck = true;
            }
            if (this.status != Animator.PREV && this.status != Animator.NEXT) {
                break Label_0096;
            }
            this.status = Animator.PAUSE;
            try {
                while (true) {
                    synchronized (this) {
                        while (this.status == Animator.PAUSE) {
                            if (n != Animator.PAUSE) {
                                this.doControlStatus(Animator.PAUSE);
                                n = Animator.PAUSE;
                            }
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        if (this.status == Animator.STOP) {
                            // monitorexit(this)
                            break;
                        }
                        n = this.status;
                        if (this.needsValueCheck) {
                            this.checkValue();
                        }
                    }
                    if (n == Animator.START) {
                        this.doControlStatus(Animator.START);
                        final int run = Animator.RUN;
                        this.status = run;
                        n = run;
                    }
                    if (n == Animator.RUN) {
                        long n3 = this.millisPerFrame - (System.currentTimeMillis() - n2);
                        if (n3 <= 5L) {
                            n3 = 5L;
                        }
                        try {
                            synchronized (this) {
                                this.wait(n3);
                            }
                        }
                        catch (InterruptedException ex2) {}
                        n2 = System.currentTimeMillis();
                        if (this.status != Animator.RUN) {
                            continue;
                        }
                        this.doAdvanceFrame(1);
                    }
                    else if (n == Animator.NEXT) {
                        this.doAdvanceFrame(1);
                        if (this.status == Animator.STOP) {
                            continue;
                        }
                        n = (this.status = Animator.PAUSE);
                    }
                    else {
                        if (n != Animator.PREV) {
                            continue;
                        }
                        this.doAdvanceFrame(-1);
                        if (this.status == Animator.STOP) {
                            continue;
                        }
                        n = (this.status = Animator.PAUSE);
                    }
                }
            }
            finally {
                synchronized (this) {
                    this.status = Animator.STOP;
                    this.doControlStatus(Animator.STOP);
                    this.frame = 0;
                    ++this.serialNumber;
                    if (this.onChange != null) {
                        this.onChange.compute();
                    }
                    else {
                        this.needsValueCheck = true;
                    }
                    this.runner = null;
                    this.notify();
                }
            }
        }
    }
}
