// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import anon.util.IReturnRunnable;
import anon.util.ProgressCapsule;
import java.util.Observer;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import gui.GUIUtils;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import java.util.Observable;

public class WorkerContentPane extends DialogContentPane implements IWizardSuitableNoWizardButtons
{
    public static final String IMG_BUSY = "busy.gif";
    public static final String MSG_PLEASE_WAIT;
    public static final String DOTS = "...";
    private Thread m_workerThread;
    private Runnable m_workerRunnable;
    private Thread m_internalThread;
    private boolean m_bInterruptThreadSafe;
    private int m_iProgressStatus;
    static /* synthetic */ Class class$gui$dialog$WorkerContentPane;
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final Runnable runnable) {
        this(japDialog, s, "", null, runnable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final Runnable runnable, final Observable observable) {
        this(japDialog, s, "", null, runnable, observable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final String s2, final Runnable runnable) {
        this(japDialog, s, s2, null, runnable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final String s2, final Runnable runnable, final Observable observable) {
        this(japDialog, s, s2, null, runnable, observable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final DialogContentPane dialogContentPane, final Runnable runnable) {
        this(japDialog, s, "", dialogContentPane, runnable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final DialogContentPane dialogContentPane, final Runnable runnable, final Observable observable) {
        this(japDialog, s, "", dialogContentPane, runnable, observable);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final String s2, final DialogContentPane dialogContentPane, final Runnable runnable) {
        this(japDialog, s, s2, dialogContentPane, runnable, null);
    }
    
    public WorkerContentPane(final JAPDialog japDialog, final String s, final String s2, final DialogContentPane dialogContentPane, final Runnable workerRunnable, final Observable observable) {
        super(japDialog, s, new Layout(s2), new DialogContentPaneOptions(-2147483647, dialogContentPane));
        this.m_bInterruptThreadSafe = true;
        this.setDefaultButtonOperation(2);
        this.m_workerRunnable = workerRunnable;
        this.m_iProgressStatus = -1;
        this.addComponentListener(new WorkerComponentListener());
        this.getContentPane().setLayout(new BorderLayout());
        if (observable == null) {
            this.getContentPane().add(new JLabel(GUIUtils.loadImageIcon("busy.gif", true)), "Center");
        }
        else {
            final JProgressBar progressBar = new JProgressBar();
            progressBar.setBorderPainted(true);
            progressBar.setStringPainted(true);
            this.getContentPane().add(progressBar, "Center");
            observable.addObserver(new Observer() {
                public void update(final Observable observable, final Object o) {
                    if (o != null && o instanceof ProgressCapsule) {
                        final ProgressCapsule progressCapsule = (ProgressCapsule)o;
                        final int value = progressCapsule.getValue();
                        final int maximum = progressCapsule.getMaximum();
                        final int minimum = progressCapsule.getMinimum();
                        final int status = progressCapsule.getStatus();
                        Label_0143: {
                            if (status == 1) {
                                synchronized (progressBar) {
                                    if (progressBar.getMaximum() != maximum) {
                                        progressBar.setMaximum(maximum);
                                    }
                                    if (progressBar.getMinimum() != minimum) {
                                        progressBar.setMinimum(minimum);
                                    }
                                    progressBar.setValue(value);
                                    progressBar.validate();
                                    break Label_0143;
                                }
                            }
                            observable.deleteObserver(this);
                        }
                        WorkerContentPane.this.m_iProgressStatus = status;
                    }
                }
            });
        }
    }
    
    public final boolean isInterruptThreadSafe() {
        return this.m_bInterruptThreadSafe;
    }
    
    public final void setInterruptThreadSafe(final boolean bInterruptThreadSafe) {
        this.m_bInterruptThreadSafe = bInterruptThreadSafe;
    }
    
    public final void joinThread() {
        try {
            if (this.m_workerThread != null) {
                this.m_workerThread.join();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean isReady() {
        return true;
    }
    
    public boolean isSkippedAsPreviousContentPane() {
        return true;
    }
    
    public CheckError[] checkCancel() {
        this.interruptWorkerThread();
        return null;
    }
    
    public synchronized void dispose() {
        super.dispose();
        this.setInterruptThreadSafe(false);
        this.interruptWorkerThread();
        this.m_internalThread = null;
    }
    
    public int getProgressStatus() {
        return this.m_iProgressStatus;
    }
    
    public void interruptWorkerThread() {
        if (this.m_workerThread != null) {
            if (this.m_workerThread.isAlive() && !this.m_workerThread.isInterrupted()) {
                this.m_workerThread.interrupt();
            }
            if (this.isInterruptThreadSafe()) {
                this.joinThread();
            }
        }
    }
    
    public Object getValue() {
        if (this.m_workerRunnable instanceof IReturnRunnable && this.m_workerRunnable != null) {
            return ((IReturnRunnable)this.m_workerRunnable).getValue();
        }
        return null;
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
        MSG_PLEASE_WAIT = ((WorkerContentPane.class$gui$dialog$WorkerContentPane == null) ? (WorkerContentPane.class$gui$dialog$WorkerContentPane = class$("gui.dialog.WorkerContentPane")) : WorkerContentPane.class$gui$dialog$WorkerContentPane).getName() + "_pleaseWait";
    }
    
    private class InternalThread extends Thread
    {
        private Runnable m_runnable;
        private boolean m_bInterrupted;
        
        public InternalThread(final Runnable runnable) {
            super(runnable, "WorkerContentPane - InternalThread");
            this.m_bInterrupted = false;
            this.m_runnable = runnable;
        }
        
        public void run() {
            this.m_runnable.run();
            this.m_bInterrupted = this.isInterrupted();
        }
        
        public boolean isInterrupted() {
            return super.isInterrupted() || this.m_bInterrupted;
        }
    }
    
    private class WorkerComponentListener extends ComponentAdapter implements Runnable
    {
        public void componentHidden(final ComponentEvent componentEvent) {
            if (WorkerContentPane.this.isReady()) {
                WorkerContentPane.this.interruptWorkerThread();
            }
        }
        
        public void componentShown(final ComponentEvent componentEvent) {
            if (WorkerContentPane.this.m_workerRunnable == null) {
                WorkerContentPane.this.setButtonValue(0);
                WorkerContentPane.this.moveToNextContentPane();
                return;
            }
            if (WorkerContentPane.this.isVisible() && WorkerContentPane.this.isReady()) {
                WorkerContentPane.this.m_internalThread = new Thread(this, "WorkerContentPane - componentShown()");
                WorkerContentPane.this.m_internalThread.setDaemon(true);
                WorkerContentPane.this.m_internalThread.start();
            }
        }
        
        public synchronized void run() {
            if (WorkerContentPane.this.m_workerRunnable == null) {
                WorkerContentPane.this.interruptWorkerThread();
                WorkerContentPane.this.setButtonValue(0);
                WorkerContentPane.this.moveToNextContentPane();
                return;
            }
            WorkerContentPane.this.setButtonValue(Integer.MIN_VALUE);
            WorkerContentPane.this.m_workerThread = new InternalThread(WorkerContentPane.this.m_workerRunnable);
            WorkerContentPane.this.m_workerThread.setPriority(1);
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {
                WorkerContentPane.this.interruptWorkerThread();
                WorkerContentPane.this.m_workerThread = null;
                WorkerContentPane.this.moveToPreviousContentPane();
                this.notifyAll();
                return;
            }
            WorkerContentPane.this.m_workerThread.start();
            try {
                WorkerContentPane.this.m_workerThread.join();
            }
            catch (InterruptedException ex2) {
                WorkerContentPane.this.interruptWorkerThread();
            }
            if (WorkerContentPane.this.m_workerThread.isInterrupted() || WorkerContentPane.this.getButtonValue() == 2 || WorkerContentPane.this.getButtonValue() == -1) {
                if (WorkerContentPane.this.getButtonValue() == Integer.MIN_VALUE) {
                    if ((WorkerContentPane.this.getDefaultButtonOperation() & 0x8002) > 0) {
                        WorkerContentPane.this.closeDialog(true);
                    }
                    else if ((WorkerContentPane.this.getDefaultButtonOperation() & 0x1001) > 0) {
                        WorkerContentPane.this.closeDialog(false);
                    }
                    else if ((WorkerContentPane.this.getDefaultButtonOperation() & 0x240) > 0) {
                        WorkerContentPane.this.moveToPreviousContentPane();
                    }
                    else if ((WorkerContentPane.this.getDefaultButtonOperation() & 0x24) > 0) {
                        WorkerContentPane.this.moveToNextContentPane();
                    }
                }
            }
            else {
                WorkerContentPane.this.interruptWorkerThread();
                WorkerContentPane.this.setButtonValue(0);
                WorkerContentPane.this.moveToNextContentPane();
            }
            WorkerContentPane.this.m_workerThread = null;
            this.notifyAll();
        }
    }
}
