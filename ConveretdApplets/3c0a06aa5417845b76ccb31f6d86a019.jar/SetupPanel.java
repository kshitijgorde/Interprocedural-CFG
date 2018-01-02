import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.LayoutManager;
import java.util.Vector;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetupPanel extends JPanel implements AbstractSetup
{
    private static final int _width = 698;
    private static final int _height = 485;
    private static final Color COLOR_LABEL;
    private static final String[] ARR_NUM_PROCS;
    private static final String[] ARR_MAX_PROC_TIME;
    private static final String[] ARR_QUANTUMS;
    private static final String[] ARR_SWITCHES;
    public static final String[] ARR_ALGORITHMS;
    private static final int INDEX_RR;
    private static final int TOA_FRACTION = 4;
    private static JComboBox g_comboNumProcs;
    private static JComboBox g_comboMaxProcTime;
    private JComboBox l_comboAlgorithm;
    private JComboBox r_comboAlgorithm;
    private CLabel l_labelQuantum;
    private CLabel l_labelQuantumMilliSec;
    private CLabel r_labelQuantum;
    private CLabel r_labelQuantumMilliSec;
    private JComboBox l_comboQuantum;
    private JComboBox r_comboQuantum;
    private JComboBox l_comboSwitchPenalty;
    private JComboBox r_comboSwitchPenalty;
    private Vector _vecQueue;
    
    public SetupPanel() {
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        this._vecQueue = new Vector();
        this.setupGlobals();
        this.setupLefts();
        this.setupRights();
    }
    
    private void setupGlobals() {
        final int shift = 10;
        final int margin = 20;
        final int comboMargin = 200;
        final int comboHeight = 25;
        final int comboWidth = 75;
        final JPanel panel = new JPanel(null);
        panel.setBounds(10, 10, 678, 151);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        panel.add(new CLabel("Globals: ", shift, shift, 16, true, Color.WHITE));
        panel.add(new CLabel("Globals: ", shift + 1, shift + 1, 16, true, Color.BLACK));
        panel.add(new CLabel("Number of Processes", margin, margin * 2, 16, false, SetupPanel.COLOR_LABEL));
        panel.add(new CLabel("Max Process Time", margin, margin * 4, 16, false, SetupPanel.COLOR_LABEL));
        panel.add(new CLabel("ms", comboMargin + 85, margin * 4, 16, false, SetupPanel.COLOR_LABEL));
        (SetupPanel.g_comboNumProcs = new JComboBox((E[])SetupPanel.ARR_NUM_PROCS)).setBounds(comboMargin, margin * 2, comboWidth, comboHeight);
        panel.add(SetupPanel.g_comboNumProcs);
        (SetupPanel.g_comboMaxProcTime = new JComboBox((E[])SetupPanel.ARR_MAX_PROC_TIME)).setBounds(comboMargin, margin * 4, comboWidth, comboHeight);
        panel.add(SetupPanel.g_comboMaxProcTime);
        this.add(panel);
    }
    
    public Algorithm getLeftAlgorithm() {
        final int algorithm = this.l_comboAlgorithm.getSelectedIndex();
        final int quantum = Integer.parseInt(this.l_comboQuantum.getSelectedItem().toString());
        final int penalty = Integer.parseInt(this.l_comboSwitchPenalty.getSelectedItem().toString());
        if (algorithm == 0) {
            return null;
        }
        final Vector procs = new Vector();
        for (int i = 0; i < this._vecQueue.size(); ++i) {
            procs.add(new CpuProcess(this._vecQueue.get(i)));
        }
        return new Algorithm(algorithm, quantum, penalty, procs);
    }
    
    public Algorithm getRightAlgorithm() {
        final int algorithm = this.r_comboAlgorithm.getSelectedIndex();
        final int quantum = Integer.parseInt(this.r_comboQuantum.getSelectedItem().toString());
        final int penalty = Integer.parseInt(this.r_comboSwitchPenalty.getSelectedItem().toString());
        if (algorithm == 0) {
            return null;
        }
        final Vector procs = new Vector();
        for (int i = 0; i < this._vecQueue.size(); ++i) {
            procs.add(new CpuProcess(this._vecQueue.get(i)));
        }
        return new Algorithm(algorithm, quantum, penalty, procs);
    }
    
    public void updateQueues() {
        final Vector vecQueue = new Vector();
        final int leftSwitchTime = Integer.parseInt(this.l_comboSwitchPenalty.getSelectedItem().toString());
        final int rightSwitchTime = Integer.parseInt(this.r_comboSwitchPenalty.getSelectedItem().toString());
        final int numProcs = Integer.parseInt(SetupPanel.g_comboNumProcs.getSelectedItem().toString()) + 1;
        final int maxProcTime = Integer.parseInt(SetupPanel.g_comboMaxProcTime.getSelectedItem().toString());
        final int maxTime = (numProcs - 1) * (maxProcTime + Math.max(leftSwitchTime, rightSwitchTime));
        final int maxTOA = maxTime / 4;
        final CpuProcess idle = new CpuProcess(0, 0, 0);
        vecQueue.add(idle);
        for (int i = 1; i < numProcs; ++i) {
            final int length = Main.RANDOM.nextInt(maxProcTime) + 1;
            final int toa = Main.RANDOM.nextInt(maxTOA);
            vecQueue.add(new CpuProcess(i, length, toa));
        }
        Collections.sort((List<Object>)vecQueue, new SortByToaCreateId());
        Debug.out("" + CpuProcess.toHeaderString());
        for (int i = 0; i < vecQueue.size(); ++i) {
            final CpuProcess proc = vecQueue.get(i);
            proc.setProcId(i);
            Debug.out("" + proc.toString());
        }
        Debug.out("");
        this._vecQueue = vecQueue;
    }
    
    private void setupLefts() {
        final int shift = 10;
        final int margin = 20;
        final int comboMargin = 200;
        final int comboHeight = 25;
        final JPanel panel = new JPanel(null);
        panel.setBounds(10, 171, 329, 303);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        panel.add(new CLabel("Left CPU: ", shift, shift, 16, true, Color.WHITE));
        panel.add(new CLabel("Left CPU: ", shift + 1, shift + 1, 16, true, Color.BLACK));
        panel.add(new CLabel("Algorithm", margin, margin * 2, 16, false, SetupPanel.COLOR_LABEL));
        (this.l_labelQuantum = new CLabel("Quantum", margin + shift, margin * 4, 16, false, SetupPanel.COLOR_LABEL)).setEnabled(false);
        panel.add(this.l_labelQuantum);
        (this.l_labelQuantumMilliSec = new CLabel("ms", comboMargin + 75 + shift, margin * 4, 16, false, SetupPanel.COLOR_LABEL)).setEnabled(false);
        panel.add(this.l_labelQuantumMilliSec);
        panel.add(new CLabel("Context Switch Penalty", margin, margin * 7, 16, false, SetupPanel.COLOR_LABEL));
        (this.l_comboAlgorithm = new JComboBox((E[])SetupPanel.ARR_ALGORITHMS)).setBounds(comboMargin - 100, margin * 2, 175, comboHeight);
        this.l_comboAlgorithm.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                SetupPanel.this.doLeftAlorithmClick();
            }
        });
        panel.add(this.l_comboAlgorithm);
        (this.l_comboQuantum = new JComboBox((E[])SetupPanel.ARR_QUANTUMS)).setEnabled(false);
        this.l_comboQuantum.setBounds(comboMargin, margin * 4, 75, comboHeight);
        panel.add(this.l_comboQuantum);
        (this.l_comboSwitchPenalty = new JComboBox((E[])SetupPanel.ARR_SWITCHES)).setBounds(comboMargin, margin * 7, 75, comboHeight);
        panel.add(this.l_comboSwitchPenalty);
        panel.add(new CLabel("ms", comboMargin + 85, margin * 7, 16, false, SetupPanel.COLOR_LABEL));
        this.add(panel);
    }
    
    private void doLeftAlorithmClick() {
        String lAlgo = this.l_comboAlgorithm.getSelectedItem().toString();
        if (lAlgo == null) {
            lAlgo = "";
        }
        final boolean boolRR = lAlgo.equalsIgnoreCase(SetupPanel.ARR_ALGORITHMS[SetupPanel.INDEX_RR]);
        this.l_comboQuantum.setEnabled(boolRR);
        this.l_labelQuantum.setEnabled(boolRR);
        this.l_labelQuantumMilliSec.setEnabled(boolRR);
        String rAlgo = this.r_comboAlgorithm.getSelectedItem().toString();
        if (rAlgo == null) {
            rAlgo = "";
        }
    }
    
    private void setupRights() {
        final int shift = 10;
        final int margin = 20;
        final int comboMargin = 200;
        final int comboHeight = 25;
        final JPanel panel = new JPanel(null);
        panel.setBounds(359, 171, 329, 303);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        panel.add(new CLabel("Right CPU: ", shift, shift, 16, true, Color.WHITE));
        panel.add(new CLabel("Right CPU: ", shift + 1, shift + 1, 16, true, Color.BLACK));
        panel.add(new CLabel("Algorithm", margin, margin * 2, 16, false, SetupPanel.COLOR_LABEL));
        (this.r_labelQuantum = new CLabel("Quantum", margin + shift, margin * 4, 16, false, SetupPanel.COLOR_LABEL)).setEnabled(false);
        panel.add(this.r_labelQuantum);
        (this.r_labelQuantumMilliSec = new CLabel("ms", comboMargin + 85, margin * 4, 16, false, SetupPanel.COLOR_LABEL)).setEnabled(false);
        panel.add(this.r_labelQuantumMilliSec);
        panel.add(new CLabel("Context Switch Penalty", margin, margin * 7, 16, false, SetupPanel.COLOR_LABEL));
        (this.r_comboAlgorithm = new JComboBox((E[])SetupPanel.ARR_ALGORITHMS)).setBounds(comboMargin - 100, margin * 2, 175, comboHeight);
        this.r_comboAlgorithm.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                SetupPanel.this.doRightAlorithmClick();
            }
        });
        panel.add(this.r_comboAlgorithm);
        (this.r_comboQuantum = new JComboBox((E[])SetupPanel.ARR_QUANTUMS)).setEnabled(false);
        this.r_comboQuantum.setBounds(comboMargin, margin * 4, 75, comboHeight);
        panel.add(this.r_comboQuantum);
        (this.r_comboSwitchPenalty = new JComboBox((E[])SetupPanel.ARR_SWITCHES)).setBounds(comboMargin, margin * 7, 75, comboHeight);
        panel.add(this.r_comboSwitchPenalty);
        panel.add(new CLabel("ms", comboMargin + 75 + shift, margin * 7, 16, false, SetupPanel.COLOR_LABEL));
        this.add(panel);
    }
    
    private void doRightAlorithmClick() {
        String rAlgo = this.r_comboAlgorithm.getSelectedItem().toString();
        if (rAlgo == null) {
            rAlgo = "";
        }
        final boolean boolRR = rAlgo.equalsIgnoreCase(SetupPanel.ARR_ALGORITHMS[SetupPanel.INDEX_RR]);
        this.r_comboQuantum.setEnabled(boolRR);
        this.r_labelQuantum.setEnabled(boolRR);
        this.r_labelQuantumMilliSec.setEnabled(boolRR);
        String lAlgo = this.l_comboAlgorithm.getSelectedItem().toString();
        if (lAlgo == null) {
            lAlgo = "";
        }
    }
    
    public static int getMaxProcTime() {
        final int maxProcTime = Integer.parseInt(SetupPanel.g_comboMaxProcTime.getSelectedItem().toString());
        return maxProcTime;
    }
    
    static {
        COLOR_LABEL = new Color(0, 0, 128);
        ARR_NUM_PROCS = new String[] { "10", "20", "50", "100" };
        ARR_MAX_PROC_TIME = new String[] { "10", "20", "50", "100" };
        ARR_QUANTUMS = new String[] { "1", "3", "5", "7", "10", "20", "30" };
        ARR_SWITCHES = new String[] { "0", "1", "3", "5", "7", "9" };
        ARR_ALGORITHMS = new String[] { "", "First-Come, First-Served", "Shortest-Job-First", "Preemptive S-J-F", "Round-Robin" };
        INDEX_RR = SetupPanel.ARR_ALGORITHMS.length - 1;
    }
}
