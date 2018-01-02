// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.awt.event.ActionEvent;
import java.util.EventListener;
import ch.randelshofer.gui.BoundedRangeModel;
import java.awt.image.ImageProducer;
import java.util.Enumeration;
import ch.randelshofer.gui.Icon;
import ch.randelshofer.gui.PolygonIcon;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import ch.randelshofer.gui.Canvas3DJ2D;
import ch.randelshofer.rubik.MiniCube3DAWT;
import ch.randelshofer.util.ConcurrentDispatcherAWT;
import ch.randelshofer.geom3d.Transform3D;
import ch.randelshofer.gui.AbstractButton;
import ch.randelshofer.gui.event.ChangeEvent;
import java.util.Vector;
import ch.randelshofer.gui.event.EventListenerList;
import ch.randelshofer.gui.MovieControlAWT;
import java.awt.Panel;
import ch.randelshofer.gui.DefaultBoundedRangeModel;
import ch.randelshofer.rubik.RubiksCubeCore;
import ch.randelshofer.rubik.AbstractCube3DAWT;
import ch.randelshofer.gui.Canvas3DAWT;
import java.awt.event.ActionListener;
import ch.randelshofer.gui.event.ChangeListener;
import ch.randelshofer.media.Player;

public class ScriptPlayer implements Player, Runnable, ChangeListener, ActionListener
{
    private Canvas3DAWT canvas;
    private AbstractCube3DAWT cube3D;
    private RubiksCubeCore model;
    private DefaultBoundedRangeModel progress;
    private Panel controlPanel;
    private MovieControlAWT controls;
    private EventListenerList listenerList;
    private ScriptNode script;
    private Vector scriptVector;
    private volatile int scriptIndex;
    private ChangeEvent changeEvent;
    private static final int STOPPED = 0;
    private static final int STARTING = 1;
    private static final int RUNNING = 2;
    private static final int STOPPING = 3;
    private int state;
    private AbstractButton resetButton;
    private Transform3D transform;
    private volatile boolean isProcessingCurrentSymbol;
    private static ConcurrentDispatcherAWT threadPool;
    static /* synthetic */ Class class$ch$randelshofer$gui$event$ChangeListener;
    
    public ScriptPlayer() {
        this.progress = new DefaultBoundedRangeModel(0, 0, 0, 0);
        this.listenerList = new EventListenerList();
        this.scriptVector = new Vector();
        this.state = 0;
        this.transform = new Transform3D();
        this.model = new RubiksCubeCore();
        this.cube3D = new MiniCube3DAWT();
        this.canvas = Canvas3DJ2D.createCanvas3D();
        this.cube3D.setAnimated(true);
        this.cube3D.setModel(this.model);
        this.cube3D.addChangeListener(this.canvas);
        this.canvas.setScene(this.cube3D.getScene());
        this.canvas.setBackground(Color.white);
        this.canvas.setSyncObject(this.model);
        this.canvas.setScaleFactor(0.02);
        this.canvas.setToIdentity();
        this.progress.addChangeListener(this);
        (this.controlPanel = new Panel()).setLayout(new BorderLayout());
        (this.controls = new MovieControlAWT()).setVisible(false);
        this.controls.setPlayer(this);
        this.controlPanel.add("Center", this.controls);
        (this.resetButton = new AbstractButton()).setIcon(new PolygonIcon(new Polygon[] { new Polygon(new int[] { 2, 3, 3, 2 }, new int[] { 2, 2, 9, 9 }, 4), new Polygon(new int[] { 9, 9, 6, 6 }, new int[] { 2, 9, 6, 5 }, 4) }, new Dimension(10, 10)));
        this.resetButton.addActionListener(this);
        this.resetButton.setPreferredSize(new Dimension(15, 15));
        this.controlPanel.add("West", this.resetButton);
    }
    
    public RubiksCubeCore getCubeModel() {
        return this.model;
    }
    
    public void setTransform(final Transform3D transform3D) {
        this.transform = transform3D;
        this.canvas.setTransform(transform3D);
    }
    
    public void setCubeModel(final RubiksCubeCore model) {
        this.model = model;
        this.canvas.setSyncObject(model);
        this.cube3D.setModel(model);
    }
    
    public AbstractCube3DAWT getCube3D() {
        return this.cube3D;
    }
    
    public void setCube3D(final AbstractCube3DAWT cube3D) {
        if (this.cube3D != null) {
            this.cube3D.removeChangeListener(this.canvas);
            this.cube3D.setModel(new RubiksCubeCore());
        }
        this.cube3D = cube3D;
        if (this.cube3D != null) {
            this.cube3D.setAnimated(true);
            this.cube3D.addChangeListener(this.canvas);
            this.cube3D.setModel(this.getCubeModel());
            this.canvas.setScene(this.cube3D.getScene());
        }
    }
    
    public ScriptNode getScript() {
        return this.script;
    }
    
    public synchronized void setScript(final ScriptNode script) {
        this.stop();
        this.script = script;
        this.scriptVector.removeAllElements();
        this.scriptIndex = 0;
        this.controls.setVisible(script != null);
        if (script == null) {
            this.progress.setRangeProperties(0, 0, 0, 0, false);
        }
        else {
            final Enumeration resolvedEnumeration = script.resolvedEnumeration(false);
            while (resolvedEnumeration.hasMoreElements()) {
                final ScriptNode scriptNode = resolvedEnumeration.nextElement();
                if ((scriptNode instanceof TwistNode && ((TwistNode)scriptNode).getSymbol() != 84) || scriptNode instanceof PermutationNode) {
                    this.scriptVector.addElement(scriptNode);
                }
            }
            this.progress.setRangeProperties(0, 0, 0, this.scriptVector.size(), false);
        }
        this.updateEnabled();
    }
    
    public void moveToCaret(final int n) {
        for (int i = 0; i < this.scriptVector.size(); ++i) {
            final ScriptNode scriptNode = this.scriptVector.elementAt(i);
            if (scriptNode.getStartPosition() <= n && scriptNode.getEndPosition() >= n) {
                this.progress.setValue(i);
                return;
            }
        }
    }
    
    private void updateEnabled() {
        if (this.script != null) {
            this.controls.enable();
        }
        else {
            this.controls.disable();
        }
    }
    
    public ImageProducer getImageProducer() {
        return null;
    }
    
    public BoundedRangeModel getBoundedRangeModel() {
        return this.progress;
    }
    
    public void start() {
        boolean b = false;
        synchronized (this) {
            if (this.state == 0) {
                this.state = 1;
                b = true;
            }
        }
        if (b) {
            this.cube3D.getDispatcher().dispatch(this, ScriptPlayer.threadPool);
            this.fireStateChanged();
        }
    }
    
    public void run() {
        synchronized (this) {
            if (this.state != 1) {
                return;
            }
            this.state = 2;
        }
        this.fireStateChanged();
        if (this.progress.getMaximum() > 0) {
            if (this.progress.getValue() == this.progress.getMaximum()) {
                this.progress.setValue(0);
                this.model.setQuiet(true);
                while (this.scriptIndex > 0) {
                    final Vector scriptVector = this.scriptVector;
                    final int scriptIndex = this.scriptIndex - 1;
                    this.scriptIndex = scriptIndex;
                    scriptVector.elementAt(scriptIndex).applyInverseTo(this.model);
                }
                this.model.setQuiet(false);
            }
            while ((this.state == 2 && this.progress.getValue() != this.progress.getMaximum()) || this.scriptIndex != this.progress.getValue()) {
                this.isProcessingCurrentSymbol = true;
                this.fireStateChanged();
                final int min = Math.min(this.progress.getValue() + 1, this.progress.getMaximum());
                if (this.scriptIndex == min - 1) {
                    this.scriptVector.elementAt(this.scriptIndex++).applyTo(this.model);
                }
                else if (this.scriptIndex == min + 1) {
                    final Vector scriptVector2 = this.scriptVector;
                    final int scriptIndex2 = this.scriptIndex - 1;
                    this.scriptIndex = scriptIndex2;
                    scriptVector2.elementAt(scriptIndex2).applyInverseTo(this.model);
                }
                else {
                    this.model.setQuiet(true);
                    while (this.scriptIndex < min) {
                        this.scriptVector.elementAt(this.scriptIndex++).applyTo(this.model);
                    }
                    while (this.scriptIndex > min) {
                        final Vector scriptVector3 = this.scriptVector;
                        final int scriptIndex3 = this.scriptIndex - 1;
                        this.scriptIndex = scriptIndex3;
                        scriptVector3.elementAt(scriptIndex3).applyInverseTo(this.model);
                    }
                    this.model.setQuiet(false);
                }
                this.isProcessingCurrentSymbol = false;
                this.progress.setValue(this.progress.getValue() + 1);
                this.fireStateChanged();
            }
        }
        synchronized (this) {
            this.state = 0;
            this.notifyAll();
        }
        this.fireStateChanged();
    }
    
    public synchronized void stop() {
        if (this.state == 2) {
            this.state = 3;
            while (this.state != 0) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
        else {
            this.state = 0;
            this.cube3D.getDispatcher().reassign();
            this.update();
        }
    }
    
    public void reset() {
        this.stop();
        this.scriptIndex = 0;
        this.progress.setValue(0);
        this.model.reset();
        this.canvas.setTransform(this.transform);
        this.fireStateChanged();
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener == null) ? (ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public boolean isActive() {
        return this.state != 0;
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener == null) ? (ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public Component getVisualComponent() {
        return this.canvas;
    }
    
    public Component getControlPanelComponent() {
        return this.controlPanel;
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener == null) ? (ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : ScriptPlayer.class$ch$randelshofer$gui$event$ChangeListener)) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public ScriptNode getCurrentSymbol() {
        final int scriptIndex = this.scriptIndex;
        return (scriptIndex < this.scriptVector.size()) ? ((ScriptNode)this.scriptVector.elementAt(scriptIndex)) : null;
    }
    
    public void setResetButtonVisible(final boolean visible) {
        this.resetButton.setVisible(visible);
    }
    
    public boolean isProcessingCurrentSymbol() {
        return this.isProcessingCurrentSymbol;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.progress && !this.isActive()) {
            this.update();
        }
    }
    
    private void update() {
        final Runnable runnable = new Runnable() {
            public void run() {
                final int value = ScriptPlayer.this.progress.getValue();
                ScriptPlayer.this.isProcessingCurrentSymbol = true;
                if (ScriptPlayer.this.scriptIndex == value - 1) {
                    ScriptPlayer.this.fireStateChanged();
                    ScriptPlayer.this.scriptVector.elementAt(ScriptPlayer.this.scriptIndex++).applyTo(ScriptPlayer.this.model);
                }
                else if (ScriptPlayer.this.scriptIndex == value + 1) {
                    final ScriptNode scriptNode = ScriptPlayer.this.scriptVector.elementAt(--ScriptPlayer.this.scriptIndex);
                    ScriptPlayer.this.fireStateChanged();
                    scriptNode.applyInverseTo(ScriptPlayer.this.model);
                }
                else {
                    ScriptPlayer.this.model.setQuiet(true);
                    while (ScriptPlayer.this.scriptIndex < value) {
                        ScriptPlayer.this.scriptVector.elementAt(ScriptPlayer.this.scriptIndex++).applyTo(ScriptPlayer.this.model);
                    }
                    while (ScriptPlayer.this.scriptIndex > value) {
                        ScriptPlayer.this.scriptVector.elementAt(--ScriptPlayer.this.scriptIndex).applyInverseTo(ScriptPlayer.this.model);
                    }
                    ScriptPlayer.this.model.setQuiet(false);
                }
                ScriptPlayer.this.isProcessingCurrentSymbol = false;
                ScriptPlayer.this.fireStateChanged();
            }
        };
        if (this.cube3D.isAnimated()) {
            this.cube3D.getDispatcher().dispatch(runnable);
        }
        else {
            runnable.run();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.resetButton) {
            this.reset();
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ScriptPlayer.threadPool = new ConcurrentDispatcherAWT();
    }
}
