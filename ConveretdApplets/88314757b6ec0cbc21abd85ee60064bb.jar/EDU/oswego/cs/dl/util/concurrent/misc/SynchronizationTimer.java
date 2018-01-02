// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.BrokenBarrierException;
import EDU.oswego.cs.dl.util.concurrent.Channel;
import java.lang.reflect.InvocationTargetException;
import EDU.oswego.cs.dl.util.concurrent.CyclicBarrier;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import EDU.oswego.cs.dl.util.concurrent.DefaultChannelCapacity;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import EDU.oswego.cs.dl.util.concurrent.WaitableInt;
import javax.swing.JButton;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedBoolean;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedRef;
import EDU.oswego.cs.dl.util.concurrent.ClockDaemon;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedInt;
import javax.swing.JComponent;

public class SynchronizationTimer
{
    static final int[] nthreadsChoices;
    static final int BLOCK_MODE = 0;
    static final int TIMEOUT_MODE = 1;
    static final int[] syncModes;
    static final int PRECISION = 10;
    final ThreadInfo[] threadInfo;
    static final int headerRows = 1;
    static final int classColumn = 0;
    static final int headerColumns = 1;
    final int tableRows;
    final int tableColumns;
    final JComponent[][] resultTable_;
    final SynchronizedInt nextClassIdx_;
    final SynchronizedInt nextThreadIdx_;
    ClockDaemon timeDaemon;
    private final SynchronizedRef contention_;
    private final SynchronizedInt loopsPerTest_;
    private final SynchronizedBoolean echoToSystemOut;
    private final JButton startstop_;
    private WaitableInt testNumber_;
    private SynchronizedBoolean running_;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$NoSynchRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$PublicSynchRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$AllSynchRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$SDelegatedRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$SynchLongRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$AClongRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$SemRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$WpSemRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$FifoRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$MutexRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$RlockRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$WpRWlockRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$ReaderPrefRWlockRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$FIFORWlockRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$ReentrantRWlockRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$LinkedQueue;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$WaitFreeQueue;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$BoundedLinkedQueue;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$BoundedBuffer;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$CVBuffer;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$BoundedPriorityQueue;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$Slot;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$SynchronousChannel;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$DirectExecutorRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$LockedSemRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$QueuedExecutorRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$ThreadedExecutorRNG;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$PooledExecutorRNG;
    
    static {
        nthreadsChoices = new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
        syncModes = new int[] { 0, 1 };
    }
    
    public SynchronizationTimer() {
        this.threadInfo = new ThreadInfo[SynchronizationTimer.nthreadsChoices.length];
        this.tableRows = TestedClass.classes.length + 1;
        this.tableColumns = SynchronizationTimer.nthreadsChoices.length + 1;
        this.resultTable_ = new JComponent[this.tableRows][this.tableColumns];
        this.nextClassIdx_ = new SynchronizedInt(0);
        this.nextThreadIdx_ = new SynchronizedInt(0);
        this.timeDaemon = new ClockDaemon();
        this.contention_ = new SynchronizedRef(null);
        this.loopsPerTest_ = new SynchronizedInt(0);
        this.echoToSystemOut = new SynchronizedBoolean(false);
        this.startstop_ = new JButton("Start");
        this.testNumber_ = new WaitableInt(1);
        this.running_ = new SynchronizedBoolean(false);
        for (int i = 0; i < this.threadInfo.length; ++i) {
            this.threadInfo[i] = new ThreadInfo(SynchronizationTimer.nthreadsChoices[i]);
        }
    }
    
    JComboBox barrierBox() {
        final int[] array = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(p2ToString(array[i])) + " iterations per barrier");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$itersPerBarrierChoices = val$itersPerBarrierChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.itersPerBarrier.set(this.val$itersPerBarrierChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.itersPerBarrier.set(array[13]);
        comboBox.setSelectedIndex(13);
        return comboBox;
    }
    
    JComboBox biasBox() {
        final int[] array = { -1, 0, 1 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(biasToString(array[i]));
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$biasChoices = val$biasChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.bias.set(this.val$biasChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.bias.set(array[1]);
        comboBox.setSelectedIndex(1);
        return comboBox;
    }
    
    static String biasToString(final int n) {
        String s;
        if (n < 0) {
            s = "slower producer";
        }
        else if (n == 0) {
            s = "balanced prod/cons rate";
        }
        else if (n > 0) {
            s = "slower consumer";
        }
        else {
            s = "No such bias";
        }
        return s;
    }
    
    void cancel() {
        synchronized (RNG.constructionLock) {
            try {
                Threads.pool.interruptAll();
            }
            catch (Exception ex) {
                System.out.println("\nException during cancel:\n" + ex);
                // monitorexit(RNG.constructionLock)
                return;
            }
        }
        // monitorexit(RNG.constructionLock)
    }
    
    JComboBox capacityBox() {
        final int[] array = { 1, 4, 64, 256, 1024, 4096, 16384, 65536, 262144, 1048576 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(p2ToString(array[i])) + " element bounded buffers");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$bufferCapacityChoices = val$bufferCapacityChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                DefaultChannelCapacity.set(this.val$bufferCapacityChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        DefaultChannelCapacity.set(array[3]);
        comboBox.setSelectedIndex(3);
        return comboBox;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    void clearTable() {
        for (int i = 1; i < this.tableRows; ++i) {
            for (int j = 1; j < this.tableColumns; ++j) {
                ((JLabel)this.resultTable_[i][j]).setText("");
            }
        }
    }
    
    JComboBox cloopBox() {
        final int[] array = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(p2ToString(array[i])) + " computations per call");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$computationsPerCallChoices = val$computationsPerCallChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.computeLoops.set(this.val$computationsPerCallChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.computeLoops.set(array[3]);
        comboBox.setSelectedIndex(3);
        return comboBox;
    }
    
    JComboBox consumerSyncModePanel() {
        final JComboBox<String> comboBox = new JComboBox<String>();
        for (int i = 0; i < SynchronizationTimer.syncModes.length; ++i) {
            comboBox.addItem("Consumers: " + modeToString(SynchronizationTimer.syncModes[i]));
        }
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.consumerMode.set(SynchronizationTimer.syncModes[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.consumerMode.set(SynchronizationTimer.syncModes[0]);
        comboBox.setSelectedIndex(0);
        return comboBox;
    }
    
    JComboBox contentionBox() {
        final Fraction[] array = { new Fraction(0L, 1L), new Fraction(1L, 16L), new Fraction(1L, 8L), new Fraction(1L, 4L), new Fraction(1L, 2L), new Fraction(1L, 1L) };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(array[i].asDouble() * 100.0) + "% contention/sharing");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ Fraction[] val$contentionChoices = val$contentionChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                SynchronizationTimer.this.contention_.set(this.val$contentionChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        this.contention_.set(array[3]);
        comboBox.setSelectedIndex(3);
        return comboBox;
    }
    
    private void endOneTest() {
        this.testNumber_.increment();
    }
    
    void endTestSeries() {
        this.running_.set(false);
        SwingUtilities.invokeLater(new PrintStart());
    }
    
    JComboBox exchangeBox() {
        final int[] array = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(p2ToString(array[i])) + " max threads per barrier");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$exchangerChoices = val$exchangerChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.exchangeParties.set(this.val$exchangerChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.exchangeParties.set(array[1]);
        comboBox.setSelectedIndex(1);
        return comboBox;
    }
    
    static String formatTime(final long n, final boolean b) {
        long n2 = n / 10L;
        final long n3 = n % 10L;
        if (!b) {
            if (n3 >= 5L) {
                ++n2;
            }
            return Long.toString(n2);
        }
        final String string = Long.toString(n2);
        String s = Long.toString(n3);
        if (n3 == 0L) {
            for (int i = 10; i > 10; i /= 10) {
                s = "0" + s;
            }
        }
        return String.valueOf(string) + "." + s;
    }
    
    JComboBox itersBox() {
        final int[] array = { 1, 16, 256, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(p2ToString(array[i])) + " calls per thread per test");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ int[] val$loopsPerTestChoices = val$loopsPerTestChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                SynchronizationTimer.this.loopsPerTest_.set(this.val$loopsPerTestChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        this.loopsPerTest_.set(array[8]);
        comboBox.setSelectedIndex(8);
        return comboBox;
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame("Times per call in microseconds");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(new SynchronizationTimer().mainPanel());
        frame.pack();
        frame.setVisible(true);
    }
    
    JPanel mainPanel() {
        new PrintStart();
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3));
        new JPanel().setLayout(new GridLayout(1, 3));
        this.startstop_.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (SynchronizationTimer.this.running_.get()) {
                    SynchronizationTimer.this.cancel();
                }
                else {
                    try {
                        SynchronizationTimer.this.startTestSeries(new TestSeries());
                    }
                    catch (InterruptedException ex) {
                        SynchronizationTimer.this.endTestSeries();
                    }
                }
            }
        });
        panel.add(this.startstop_);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        final JButton button = new JButton("Continue");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (!SynchronizationTimer.this.running_.get()) {
                    try {
                        SynchronizationTimer.this.startTestSeries(new TestSeries(SynchronizationTimer.this.nextClassIdx_.get(), SynchronizationTimer.this.nextThreadIdx_.get()));
                    }
                    catch (InterruptedException ex) {
                        SynchronizationTimer.this.endTestSeries();
                    }
                }
            }
        });
        panel2.add(button);
        final JButton button2 = new JButton("Clear cells");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SynchronizationTimer.this.clearTable();
            }
        });
        panel2.add(button2);
        panel.add(panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 2));
        final JButton button3 = new JButton("All classes");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SynchronizationTimer.this.setChecks(true);
            }
        });
        panel3.add(button3);
        final JButton button4 = new JButton("No classes");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SynchronizationTimer.this.setChecks(false);
            }
        });
        panel3.add(button4);
        panel.add(panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        final JCheckBox checkBox = new JCheckBox("Console echo");
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                SynchronizationTimer.this.echoToSystemOut.complement();
            }
        });
        final JLabel label = new JLabel("Active threads:      0");
        panel4.add(label);
        panel4.add(checkBox);
        panel.add(panel4);
        panel.add(this.contentionBox());
        panel.add(this.itersBox());
        panel.add(this.cloopBox());
        panel.add(this.barrierBox());
        panel.add(this.exchangeBox());
        panel.add(this.biasBox());
        panel.add(this.capacityBox());
        panel.add(this.timeoutBox());
        panel.add(this.syncModePanel());
        panel.add(this.producerSyncModePanel());
        panel.add(this.consumerSyncModePanel());
        this.startPoolStatus(label);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, 1));
        panel5.add(this.resultPanel());
        panel5.add(panel);
        return panel5;
    }
    
    static String modeToString(final int n) {
        String s;
        if (n == 0) {
            s = "block";
        }
        else if (n == 1) {
            s = "timeout";
        }
        else {
            s = "No such mode";
        }
        return s;
    }
    
    static String p2ToString(int n) {
        String s = "";
        if (n >= 1024) {
            n /= 1024;
            s = "K";
            if (n >= 1024) {
                n /= 1024;
                s = "M";
            }
        }
        return String.valueOf(n) + s;
    }
    
    JComboBox producerSyncModePanel() {
        final JComboBox<String> comboBox = new JComboBox<String>();
        for (int i = 0; i < SynchronizationTimer.syncModes.length; ++i) {
            comboBox.addItem("Producers: " + modeToString(SynchronizationTimer.syncModes[i]));
        }
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.producerMode.set(SynchronizationTimer.syncModes[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.producerMode.set(SynchronizationTimer.syncModes[0]);
        comboBox.setSelectedIndex(0);
        return comboBox;
    }
    
    JPanel resultPanel() {
        final JPanel[] array = new JPanel[this.tableColumns];
        for (int i = 0; i < this.tableColumns; ++i) {
            (array[i] = new JPanel()).setLayout(new GridLayout(this.tableRows, 1));
            if (i != 0) {
                array[i].setBackground(Color.white);
            }
        }
        final Color background = array[0].getBackground();
        final LineBorder border = new LineBorder(background);
        final Font font = new Font("Dialog", 0, 12);
        final Dimension dimension = new Dimension(40, 16);
        final Dimension dimension2 = new Dimension(154, 16);
        final JLabel label = new JLabel(" Classes      \\      Threads");
        label.setMinimumSize(dimension2);
        label.setPreferredSize(dimension2);
        label.setFont(font);
        this.resultTable_[0][0] = label;
        array[0].add(label);
        for (int j = 1; j < this.tableColumns; ++j) {
            final int n = j - 1;
            final JCheckBox checkBox = new JCheckBox(this.threadInfo[n].name, true);
            checkBox.addActionListener(new ActionListener() {
                private final /* synthetic */ int val$nthreads = val$nthreads;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    SynchronizationTimer.this.threadInfo[this.val$nthreads].toggleEnabled();
                }
            });
            checkBox.setMinimumSize(dimension);
            checkBox.setPreferredSize(dimension);
            checkBox.setFont(font);
            checkBox.setBackground(background);
            this.resultTable_[0][j] = checkBox;
            array[j].add(checkBox);
        }
        for (int k = 1; k < this.tableRows; ++k) {
            final int n2 = k - 1;
            final JCheckBox checkBox2 = new JCheckBox(TestedClass.classes[n2].name, true);
            checkBox2.addActionListener(new ActionListener() {
                private final /* synthetic */ int val$cls = val$cls;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    TestedClass.classes[this.val$cls].toggleEnabled();
                }
            });
            (this.resultTable_[k][0] = checkBox2).setMinimumSize(dimension2);
            checkBox2.setPreferredSize(dimension2);
            checkBox2.setFont(font);
            array[0].add(checkBox2);
            for (int l = 1; l < this.tableColumns; ++l) {
                final JLabel label2 = new JLabel("");
                (this.resultTable_[k][l] = label2).setMinimumSize(dimension);
                label2.setPreferredSize(dimension);
                label2.setBorder(border);
                label2.setFont(font);
                label2.setBackground(Color.white);
                label2.setForeground(Color.black);
                label2.setHorizontalAlignment(4);
                array[l].add(label2);
            }
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        for (int n3 = 0; n3 < this.tableColumns; ++n3) {
            panel.add(array[n3]);
        }
        return panel;
    }
    
    private void runOneTest(final Runnable runnable) throws InterruptedException {
        final int value = this.testNumber_.get();
        Threads.pool.execute(runnable);
        this.testNumber_.whenNotEqual(value, null);
    }
    
    void setChecks(final boolean selected) {
        for (int i = 0; i < TestedClass.classes.length; ++i) {
            TestedClass.classes[i].setEnabled(new Boolean(selected));
            ((JCheckBox)this.resultTable_[i + 1][0]).setSelected(selected);
        }
    }
    
    void setTime(final long n, final int n2, final int n3) {
        SwingUtilities.invokeLater(new Runnable() {
            private final /* synthetic */ JLabel val$cell = val$cell;
            
            public void run() {
                this.val$cell.setText(SynchronizationTimer.formatTime(n, true));
            }
        });
    }
    
    void startPoolStatus(final JLabel label) {
        this.timeDaemon.executePeriodically(250L, new Runnable() {
            private final /* synthetic */ JLabel val$status = val$status;
            int lastps = 0;
            
            public void run() {
                final int value = Threads.activeThreads.get();
                if (this.lastps != value) {
                    this.lastps = value;
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ int val$ps = val$ps;
                        private final /* synthetic */ JLabel val$status = this.val$status;
                        
                        public void run() {
                            this.val$status.setText("Active threads: " + this.val$ps);
                        }
                    });
                }
            }
        }, false);
    }
    
    void startTestSeries(final Runnable runnable) throws InterruptedException {
        this.running_.set(true);
        this.startstop_.setText("Stop");
        Threads.pool.execute(runnable);
    }
    
    JComboBox syncModePanel() {
        final JComboBox<String> comboBox = new JComboBox<String>();
        for (int i = 0; i < SynchronizationTimer.syncModes.length; ++i) {
            comboBox.addItem("Locks: " + modeToString(SynchronizationTimer.syncModes[i]));
        }
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.syncMode.set(SynchronizationTimer.syncModes[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.syncMode.set(SynchronizationTimer.syncModes[0]);
        comboBox.setSelectedIndex(0);
        return comboBox;
    }
    
    boolean threadEnabled(final int n) {
        return this.threadInfo[n].getEnabled();
    }
    
    JComboBox timeoutBox() {
        final long[] array = { 0L, 1L, 10L, 100L, 1000L, 10000L, 100000L };
        final JComboBox comboBox = new JComboBox<String>();
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(String.valueOf(array[i]) + " msec timeouts");
        }
        comboBox.addItemListener(new ItemListener() {
            private final /* synthetic */ long[] val$timeoutChoices = val$timeoutChoices;
            
            public void itemStateChanged(final ItemEvent itemEvent) {
                RNG.timeout.set(this.val$timeoutChoices[((JComboBox)itemEvent.getItemSelectable()).getSelectedIndex()]);
            }
        });
        RNG.timeout.set(array[3]);
        comboBox.setSelectedIndex(3);
        return comboBox;
    }
    
    static class TestedClass
    {
        final String name;
        final Class cls;
        final boolean multipleOK;
        final boolean singleOK;
        final Class buffCls;
        Boolean enabled_;
        static final TestedClass dummy;
        static final TestedClass[] classes;
        
        static {
            dummy = new TestedClass("", null, false, false);
            classes = new TestedClass[] { new TestedClass("NoSynchronization", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$NoSynchRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$NoSynchRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$NoSynchRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.NoSynchRNG")), false, true), new TestedClass("PublicSynchronization", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PublicSynchRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PublicSynchRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PublicSynchRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.PublicSynchRNG")), true, true), new TestedClass("NestedSynchronization", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AllSynchRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AllSynchRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AllSynchRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.AllSynchRNG")), true, true), new TestedClass("SDelegated", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SDelegatedRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SDelegatedRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SDelegatedRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.SDelegatedRNG")), true, true), new TestedClass("SynchLongUsingSet", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SynchLongRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SynchLongRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SynchLongRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.SynchLongRNG")), true, true), new TestedClass("SynchLongUsingCommit", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AClongRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AClongRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$AClongRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.AClongRNG")), true, true), new TestedClass("Semaphore", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SemRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SemRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$SemRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.SemRNG")), true, true), new TestedClass("WaiterPrefSemaphore", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpSemRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpSemRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpSemRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.WpSemRNG")), true, true), new TestedClass("FIFOSemaphore", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FifoRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FifoRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FifoRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.FifoRNG")), true, true), new TestedClass("PrioritySemaphore", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.PrioritySemRNG")), true, true), new TestedClass("Mutex", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$MutexRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$MutexRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$MutexRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.MutexRNG")), true, true), new TestedClass("ReentrantLock", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$RlockRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$RlockRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$RlockRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.RlockRNG")), true, true), new TestedClass("WriterPrefRWLock", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpRWlockRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpRWlockRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$WpRWlockRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.WpRWlockRNG")), true, true), new TestedClass("ReaderPrefRWLock", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReaderPrefRWlockRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReaderPrefRWlockRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReaderPrefRWlockRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ReaderPrefRWlockRNG")), true, true), new TestedClass("FIFORWLock", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FIFORWlockRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FIFORWlockRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$FIFORWlockRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.FIFORWlockRNG")), true, true), new TestedClass("ReentrantRWL", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReentrantRWlockRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReentrantRWlockRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ReentrantRWlockRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ReentrantRWlockRNG")), true, true), new TestedClass("LinkedQueue", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$LinkedQueue != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$LinkedQueue : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$LinkedQueue = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.LinkedQueue"))), new TestedClass("WaitFreeQueue", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$WaitFreeQueue != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$WaitFreeQueue : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$WaitFreeQueue = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.WaitFreeQueue"))), new TestedClass("BoundedLinkedQueue", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedLinkedQueue != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedLinkedQueue : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedLinkedQueue = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.BoundedLinkedQueue"))), new TestedClass("BoundedBuffer", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedBuffer != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedBuffer : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedBuffer = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.BoundedBuffer"))), new TestedClass("CondVarBoundedBuffer", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$CVBuffer != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$CVBuffer : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$CVBuffer = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.CVBuffer"))), new TestedClass("BoundedPriorityQueue", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedPriorityQueue != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedPriorityQueue : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$BoundedPriorityQueue = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.BoundedPriorityQueue"))), new TestedClass("Slot", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, true, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$Slot != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$Slot : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$Slot = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.Slot"))), new TestedClass("SynchronousChannel", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ChanRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ChanRNG")), true, false, (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$SynchronousChannel != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$SynchronousChannel : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$SynchronousChannel = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.SynchronousChannel"))), new TestedClass("DirectExecutor", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$DirectExecutorRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$DirectExecutorRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$DirectExecutorRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.DirectExecutorRNG")), true, true), new TestedClass("SemaphoreLckExecutor", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$LockedSemRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$LockedSemRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$LockedSemRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.LockedSemRNG")), true, true), new TestedClass("QueuedExecutor", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$QueuedExecutorRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$QueuedExecutorRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$QueuedExecutorRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.QueuedExecutorRNG")), true, true), new TestedClass("ThreadedExecutor", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ThreadedExecutorRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ThreadedExecutorRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$ThreadedExecutorRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.ThreadedExecutorRNG")), true, true), new TestedClass("PooledExecutor", (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PooledExecutorRNG != null) ? SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PooledExecutorRNG : (SynchronizationTimer.class$EDU$oswego$cs$dl$util$concurrent$misc$PooledExecutorRNG = SynchronizationTimer.class$("EDU.oswego.cs.dl.util.concurrent.misc.PooledExecutorRNG")), true, true) };
        }
        
        TestedClass(final String name, final Class cls, final boolean multipleOK, final boolean singleOK) {
            this.enabled_ = new Boolean(true);
            this.name = name;
            this.cls = cls;
            this.multipleOK = multipleOK;
            this.singleOK = singleOK;
            this.buffCls = null;
        }
        
        TestedClass(final String name, final Class cls, final boolean multipleOK, final boolean singleOK, final Class buffCls) {
            this.enabled_ = new Boolean(true);
            this.name = name;
            this.cls = cls;
            this.multipleOK = multipleOK;
            this.singleOK = singleOK;
            this.buffCls = buffCls;
        }
        
        synchronized Boolean getEnabled() {
            return this.enabled_;
        }
        
        synchronized boolean isEnabled(final int n, final Fraction fraction) {
            return this.enabled_ && (this.singleOK || n > 1) && (this.multipleOK || n <= 1 || fraction.compareTo(0L) <= 0);
        }
        
        synchronized void setEnabled(final Boolean enabled_) {
            this.enabled_ = enabled_;
        }
        
        synchronized void toggleEnabled() {
            this.enabled_ = new Boolean(this.enabled_ ^ true);
        }
    }
    
    static class ThreadInfo
    {
        final String name;
        final int number;
        Boolean enabled;
        
        ThreadInfo(final int number) {
            this.number = number;
            this.name = SynchronizationTimer.p2ToString(number);
            this.enabled = new Boolean(true);
        }
        
        synchronized Boolean getEnabled() {
            return this.enabled;
        }
        
        synchronized void setEnabled(final Boolean enabled) {
            this.enabled = enabled;
        }
        
        synchronized void toggleEnabled() {
            this.enabled = new Boolean(this.enabled ^ true);
        }
    }
    
    class PrintStart implements Runnable
    {
        public void run() {
            SynchronizationTimer.this.startstop_.setText("Start");
        }
    }
    
    class TestSeries implements Runnable
    {
        final int firstclass;
        final int firstnthreads;
        
        TestSeries() {
            this.firstclass = 0;
            this.firstnthreads = 0;
        }
        
        TestSeries(final int firstclass, final int firstnthreads) {
            this.firstclass = firstclass;
            this.firstnthreads = firstnthreads;
        }
        
        public void run() {
            Thread.currentThread().setPriority(5);
            try {
                int firstnthreads = this.firstnthreads;
                int firstclass = this.firstclass;
                if (firstnthreads < SynchronizationTimer.nthreadsChoices.length && firstclass < TestedClass.classes.length) {
                    while (true) {
                        if (SynchronizationTimer.this.threadEnabled(firstnthreads)) {
                            final TestedClass testedClass = TestedClass.classes[firstclass];
                            final int n = SynchronizationTimer.nthreadsChoices[firstnthreads];
                            SynchronizationTimer.this.loopsPerTest_.get();
                            if (testedClass.isEnabled(n, (Fraction)SynchronizationTimer.this.contention_.get())) {
                                SynchronizationTimer.this.runOneTest(new OneTest(firstclass, firstnthreads));
                            }
                        }
                        if (++firstclass >= TestedClass.classes.length) {
                            firstclass = 0;
                            if (++firstnthreads >= SynchronizationTimer.nthreadsChoices.length) {
                                break;
                            }
                        }
                        SynchronizationTimer.this.nextClassIdx_.set(firstclass);
                        SynchronizationTimer.this.nextThreadIdx_.set(firstnthreads);
                    }
                }
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            finally {
                SynchronizationTimer.this.endTestSeries();
            }
        }
    }
    
    static class BarrierTimer implements Runnable
    {
        private long startTime_;
        private long endTime_;
        
        BarrierTimer() {
            this.startTime_ = 0L;
            this.endTime_ = 0L;
        }
        
        public synchronized long getTime() {
            return this.endTime_ - this.startTime_;
        }
        
        public synchronized void run() {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.startTime_ == 0L) {
                this.startTime_ = currentTimeMillis;
            }
            else {
                this.endTime_ = currentTimeMillis;
            }
        }
    }
    
    class OneTest implements Runnable
    {
        final int clsIdx;
        final int nthreadsIdx;
        
        OneTest(final int clsIdx, final int nthreadsIdx) {
            this.clsIdx = clsIdx;
            this.nthreadsIdx = nthreadsIdx;
        }
        
        public void run() {
            Thread.currentThread().setPriority(2);
            boolean b = false;
            final TestedClass testedClass = TestedClass.classes[this.clsIdx];
            final JLabel label = (JLabel)SynchronizationTimer.this.resultTable_[this.clsIdx + 1][this.nthreadsIdx + 1];
            final Color foreground = label.getForeground();
            try {
                if (Thread.interrupted()) {
                    return;
                }
                if (!SynchronizationTimer.this.threadEnabled(this.nthreadsIdx)) {
                    return;
                }
                final int n = SynchronizationTimer.nthreadsChoices[this.nthreadsIdx];
                final int value = SynchronizationTimer.this.loopsPerTest_.get();
                final Fraction fraction = (Fraction)SynchronizationTimer.this.contention_.get();
                if (!testedClass.isEnabled(n, fraction)) {
                    return;
                }
                final BarrierTimer barrierTimer = new BarrierTimer();
                final CyclicBarrier cyclicBarrier = new CyclicBarrier(n + 1, barrierTimer);
                final Class cls = testedClass.cls;
                final Class buffCls = testedClass.buffCls;
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        private final /* synthetic */ JLabel val$cell = val$cell;
                        
                        public void run() {
                            this.val$cell.setForeground(Color.blue);
                            this.val$cell.setText("RUN");
                            this.val$cell.repaint();
                        }
                    });
                }
                catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    System.exit(-1);
                }
                synchronized (RNG.constructionLock) {
                    RNG.reset(n);
                    if (buffCls == null) {
                        final RNG rng = cls.newInstance();
                        for (int i = 0; i < n; ++i) {
                            Threads.pool.execute(new TestLoop(rng, cls.newInstance(), fraction, value, cyclicBarrier).testLoop());
                        }
                    }
                    else {
                        final Channel channel = buffCls.newInstance();
                        if (n == 1) {
                            final ChanRNG chanRNG = cls.newInstance();
                            chanRNG.setSingle(true);
                            Threads.pool.execute(new PCTestLoop(chanRNG.getDelegate(), chanRNG, fraction, value, cyclicBarrier, channel, channel).testLoop(true));
                        }
                        else {
                            if (n % 2 != 0) {
                                throw new Error("Must have even number of threads!");
                            }
                            for (int n2 = n / 2, j = 0; j < n2; ++j) {
                                final ChanRNG chanRNG2 = cls.newInstance();
                                chanRNG2.setSingle(false);
                                final PCTestLoop pcTestLoop = new PCTestLoop(chanRNG2.getDelegate(), chanRNG2, fraction, value, cyclicBarrier, channel, buffCls.newInstance());
                                Threads.pool.execute(pcTestLoop.testLoop(false));
                                Threads.pool.execute(pcTestLoop.testLoop(true));
                            }
                        }
                    }
                    if (SynchronizationTimer.this.echoToSystemOut.get()) {
                        System.out.print(String.valueOf(testedClass.name) + " " + n + "T " + fraction + "S " + RNG.computeLoops.get() + "I " + RNG.syncMode.get() + "Lm " + RNG.timeout.get() + "TO " + RNG.producerMode.get() + "Pm " + RNG.consumerMode.get() + "Cm " + RNG.bias.get() + "B " + DefaultChannelCapacity.get() + "C " + RNG.exchangeParties.get() + "Xp " + RNG.itersPerBarrier.get() + "Ib : ");
                    }
                }
                // monitorexit(RNG.constructionLock)
                cyclicBarrier.barrier();
                cyclicBarrier.barrier();
                final long round = Math.round(barrierTimer.getTime() * 1000.0 * 10.0 / (n * value));
                SynchronizationTimer.this.setTime(round, this.clsIdx, this.nthreadsIdx);
                if (SynchronizationTimer.this.echoToSystemOut.get()) {
                    System.out.println(SynchronizationTimer.formatTime(round, true));
                }
            }
            catch (BrokenBarrierException ex3) {
                b = true;
            }
            catch (InterruptedException ex4) {
                b = true;
                Thread.currentThread().interrupt();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                System.out.println("Construction Exception?");
                System.exit(-1);
            }
            finally {
                SwingUtilities.invokeLater(new Runnable() {
                    private final /* synthetic */ JLabel val$cell = val$cell;
                    
                    public void run() {
                        if (b) {
                            this.val$cell.setText("");
                        }
                        this.val$cell.setForeground(foreground);
                        this.val$cell.repaint();
                    }
                });
                Thread.currentThread().setPriority(5);
                SynchronizationTimer.this.endOneTest();
            }
        }
    }
}
