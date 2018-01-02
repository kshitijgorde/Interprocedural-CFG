import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Point;
import java.util.Vector;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class RunPanel extends JPanel implements AbstractRun
{
    private static final int _width = 698;
    private static final int _height = 485;
    private static final Color COLOR_LABEL;
    private static final int _rowHt = 20;
    private static final int TIMER_DURATION = 250;
    private AbstractSetup _absSetup;
    private Algorithm _algoLeft;
    private Algorithm _algoRight;
    private JTable _tableLeft;
    private JScrollPane _scrollLeft;
    private JTable _tableRight;
    private JScrollPane _scrollRight;
    private JButton g_buttonStep;
    private JButton g_buttonRun;
    private CLabel _labelLeft;
    private CLabel _labelRight;
    private Timer _timerRun;
    private int _leftRowCount;
    private int _rightRowCount;
    private boolean _boolLoaded;
    private ImageIcon _imgRun;
    private ImageIcon _imgReRun;
    private ImageIcon _imgPause;
    private ImageIcon _imgStep;
    private ImageIcon _imgNoStep;
    private JTabbedPane _tabStats;
    private CLabel _labelCPU;
    private PlotPanel _plotJobsStarted;
    private PlotPanel _plotJobsCompleted;
    
    public RunPanel(final AbstractSetup setup) {
        this._leftRowCount = 0;
        this._rightRowCount = 0;
        this._boolLoaded = false;
        this._tabStats = null;
        this._absSetup = setup;
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        this._scrollLeft = null;
        this._scrollRight = null;
        this.setupButtons();
        this.setupLeftPanel();
        this.setupRightPanel();
        this.setupStatsPanel();
        this._timerRun = new Timer(250, new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                RunPanel.this.timerRunTimer();
            }
        });
        this._boolLoaded = true;
    }
    
    private void timerRunTimer() {
        if (this.doOneStep()) {
            this._timerRun.stop();
            this.g_buttonRun.setIcon(this._imgReRun);
            this.g_buttonRun.setActionCommand("Done");
            this.g_buttonStep.setEnabled(true);
        }
    }
    
    public void setupButtons() {
        final int margin = 20;
        final int buttonWidth = 30;
        final int buttonHeight = 30;
        final int buttonMargin = margin;
        final int buttonOffsetY = 17;
        final ImageLoader loader = new ImageLoader();
        this._imgRun = loader.getImageIcon("images/run.gif");
        this._imgReRun = loader.getImageIcon("images/rerun.gif");
        this._imgPause = loader.getImageIcon("images/pause.gif");
        this._imgStep = loader.getImageIcon("images/step.gif");
        this._imgNoStep = loader.getImageIcon("images/nostep.gif");
        (this.g_buttonRun = new JButton(this._imgRun)).setBounds(buttonMargin, margin * buttonOffsetY, buttonWidth, buttonHeight);
        this.g_buttonRun.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (RunPanel.this._timerRun.isRunning()) {
                    RunPanel.this._timerRun.stop();
                    RunPanel.this.g_buttonRun.setIcon(RunPanel.this._imgRun);
                    RunPanel.this.g_buttonRun.setActionCommand("Run");
                    RunPanel.this.g_buttonStep.setEnabled(true);
                }
                else {
                    if (RunPanel.this.g_buttonRun.getActionCommand().equalsIgnoreCase("Done")) {
                        RunPanel.this.updateQueues();
                    }
                    RunPanel.this.g_buttonRun.setIcon(RunPanel.this._imgPause);
                    RunPanel.this.g_buttonRun.setActionCommand("Pause");
                    RunPanel.this.g_buttonStep.setEnabled(false);
                    RunPanel.this._timerRun.start();
                }
            }
        });
        this.add(this.g_buttonRun);
        (this.g_buttonStep = new JButton(this._imgStep)).setDisabledIcon(this._imgNoStep);
        this.g_buttonStep.setBounds(buttonMargin, margin * (buttonOffsetY + 2), buttonWidth, buttonHeight);
        this.g_buttonStep.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (RunPanel.this.g_buttonRun.getActionCommand().equalsIgnoreCase("Done")) {
                    RunPanel.this.updateQueues();
                    RunPanel.this.g_buttonRun.setActionCommand("Run");
                }
                RunPanel.this.timerRunTimer();
            }
        });
        this.add(this.g_buttonStep);
        this.add(new CLabel("CPU total: ", buttonMargin, margin * (buttonOffsetY + 4), 16, true, Color.WHITE));
        this.add(new CLabel("CPU total: ", buttonMargin + 1, margin * (buttonOffsetY + 4) + 1, 16, true, Color.BLACK));
        this.add(this._labelCPU = new CLabel("0 ms", buttonMargin + 20, margin * (buttonOffsetY + 5), 16, true, RunPanel.COLOR_LABEL));
    }
    
    private boolean doOneStep() {
        if (this._algoLeft == null || this._algoRight == null) {
            return true;
        }
        final boolean left = this._algoLeft.incrClock1ms();
        if (this._tableLeft.getRowCount() != this._leftRowCount) {
            this._leftRowCount = this._tableLeft.getRowCount();
            this._tableLeft.setRowHeight(20);
        }
        else {
            this._tableLeft.paintImmediately(0, 0, this._tableLeft.getWidth(), this._tableLeft.getHeight());
        }
        int leftRun = this._algoLeft.getRunning();
        if (leftRun > 0) {
            final Vector procsLeft = this._algoLeft.getProcs();
            for (int i = 1; i < procsLeft.size(); ++i) {
                final CpuProcess proc = procsLeft.get(i);
                if (this._algoLeft.getCpuTime() < proc.getTOA()) {
                    break;
                }
                if (proc.getProcId() >= this._algoLeft.getRunning()) {
                    break;
                }
                if (proc.isDone() && proc.getProcId() != this._algoLeft.getRunning()) {
                    --leftRun;
                }
            }
        }
        this._tableLeft.changeSelection(leftRun, 0, false, false);
        final Point locLeft = this._plotJobsStarted.getXY(this._algoLeft.getCpuTime(), this._algoLeft.getJobsStarted());
        this._plotJobsStarted.add(new PlotPoint(locLeft.x, locLeft.y, "<html><pre>Left:\n  Time = " + this._algoLeft.getCpuTime() + "\n  Started = " + this._algoLeft.getJobsStarted() + "</html>", 1));
        final Point locLeftTp = this._plotJobsCompleted.getXY(this._algoLeft.getCpuTime(), this._algoLeft.getJobsCompleted());
        this._plotJobsCompleted.add(new PlotPoint(locLeftTp.x, locLeftTp.y, "<html><pre>Left:\n  Time = " + this._algoLeft.getCpuTime() + "\n  Completed = " + this._algoLeft.getJobsCompleted() + "</html>", 1));
        final boolean right = this._algoRight.incrClock1ms();
        if (this._tableRight.getRowCount() != this._rightRowCount) {
            this._rightRowCount = this._tableRight.getRowCount();
            this._tableRight.setRowHeight(20);
        }
        else {
            this._tableRight.paintImmediately(0, 0, this._tableRight.getWidth(), this._tableRight.getHeight());
        }
        int rightRun = this._algoRight.getRunning();
        if (rightRun > 0) {
            final Vector procsRight = this._algoRight.getProcs();
            for (int j = 1; j < procsRight.size(); ++j) {
                final CpuProcess proc2 = procsRight.get(j);
                if (proc2.getTOA() > this._algoRight.getCpuTime()) {
                    break;
                }
                if (proc2.getProcId() >= this._algoRight.getRunning()) {
                    break;
                }
                if (proc2.isDone() && proc2.getProcId() != this._algoRight.getRunning()) {
                    --rightRun;
                }
            }
        }
        this._tableRight.changeSelection(rightRun, 0, false, false);
        final Point locRight = this._plotJobsStarted.getXY(this._algoRight.getCpuTime(), this._algoRight.getJobsStarted());
        this._plotJobsStarted.add(new PlotPoint(locRight.x, locRight.y, "<html><pre>Right:\n  Time = " + this._algoRight.getCpuTime() + "\n  Started = " + this._algoRight.getJobsStarted() + "</html>", 0));
        final Point locRightTp = this._plotJobsCompleted.getXY(this._algoRight.getCpuTime(), this._algoRight.getJobsCompleted());
        this._plotJobsCompleted.add(new PlotPoint(locRightTp.x, locRightTp.y, "<html><pre>Right:\n  Time = " + this._algoRight.getCpuTime() + "\n  Completed = " + this._algoRight.getJobsCompleted() + "</html>", 0));
        this._labelCPU.setText("" + ((this._algoLeft.getCpuTime() < this._algoRight.getCpuTime()) ? this._algoRight.getCpuTime() : this._algoLeft.getCpuTime()) + " ms");
        if (this._tabStats.getSelectedIndex() == 0) {
            this._plotJobsStarted.repaint(locLeft.x - 4, locLeft.y - 4, 16, 16);
            this._plotJobsStarted.repaint(locRight.x - 4, locRight.y - 4, 16, 16);
        }
        else if (this._tabStats.getSelectedIndex() == 1) {
            this._plotJobsCompleted.repaint(locLeftTp.x - 4, locLeftTp.y - 4, 16, 16);
            this._plotJobsCompleted.repaint(locRightTp.x - 4, locRightTp.y - 4, 16, 16);
        }
        return left && right;
    }
    
    private void setupLeftPanel() {
        final int shift = 10;
        final int margin = 120;
        final int tableY = 50;
        final int tableH = 10;
        final JPanel panel = new JPanel(null);
        panel.setBounds(10, 10, 329, 313);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        panel.setBackground(Main.WINDOW_COLOR);
        panel.add(new CLabel("Left CPU: ", shift, shift, 16, true, Color.WHITE));
        panel.add(new CLabel("Left CPU: ", shift + 1, shift + 1, 16, true, Color.BLACK));
        panel.add(this._labelLeft = new CLabel("", margin, shift, 16, true, new Color(224, 96, 0)));
        (this._tableLeft = new JTable(new AlgoTableModel())).setSelectionMode(0);
        this._tableLeft.getTableHeader().setReorderingAllowed(false);
        this._tableLeft.setBackground(new Color(192, 192, 192));
        this._tableLeft.setSelectionBackground(Color.orange);
        this._tableLeft.getColumnModel().getColumn(2).setCellRenderer(new ProcCellRenderer(SetupPanel.getMaxProcTime()));
        this._tableLeft.getColumnModel().getColumn(0).setResizable(false);
        this._tableLeft.getColumnModel().getColumn(1).setResizable(false);
        this._tableLeft.getColumnModel().getColumn(2).setResizable(false);
        this._tableLeft.getColumnModel().getColumn(2).setPreferredWidth(232);
        this._tableLeft.setRowHeight(20);
        (this._scrollLeft = new JScrollPane(this._tableLeft)).setBounds(5, tableY, 319, 242 + tableH);
        panel.add(this._scrollLeft);
        this.add(panel);
    }
    
    private void setupRightPanel() {
        final int shift = 10;
        final int margin = 120;
        final int tableY = 50;
        final int tableH = 10;
        final JPanel panel = new JPanel(null);
        panel.setBounds(359, 10, 329, 313);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        panel.setBackground(Main.WINDOW_COLOR);
        panel.add(new CLabel("Right CPU: ", shift, shift, 16, true, Color.WHITE));
        panel.add(new CLabel("Right CPU: ", shift + 1, shift + 1, 16, true, Color.BLACK));
        panel.add(this._labelRight = new CLabel("", margin, shift, 16, true, RunPanel.COLOR_LABEL));
        (this._tableRight = new JTable(new AlgoTableModel())).setSelectionMode(0);
        this._tableRight.getTableHeader().setReorderingAllowed(false);
        this._tableRight.setBackground(new Color(192, 192, 192));
        this._tableRight.setSelectionBackground(Color.orange);
        this._tableRight.getColumnModel().getColumn(2).setCellRenderer(new ProcCellRenderer(SetupPanel.getMaxProcTime()));
        this._tableRight.getColumnModel().getColumn(0).setResizable(false);
        this._tableRight.getColumnModel().getColumn(1).setResizable(false);
        this._tableRight.getColumnModel().getColumn(2).setResizable(false);
        this._tableRight.getColumnModel().getColumn(2).setPreferredWidth(232);
        this._tableRight.setRowHeight(20);
        (this._scrollRight = new JScrollPane(this._tableRight)).setBounds(5, tableY, 319, 242 + tableH);
        panel.add(this._scrollRight);
        this.add(panel);
    }
    
    public void timerRunStop() {
        this._timerRun.stop();
        if (!this._boolLoaded) {
            return;
        }
        this.g_buttonRun.setIcon(this._imgRun);
        this.g_buttonRun.setActionCommand("Run");
        this.g_buttonStep.setEnabled(true);
    }
    
    public void updateQueues() {
        if (!this._boolLoaded) {
            return;
        }
        this._labelCPU.setText("0 ms");
        this._tableLeft.clearSelection();
        this._tableRight.clearSelection();
        Debug.out("Left: ");
        this._algoLeft = this._absSetup.getLeftAlgorithm();
        this._labelLeft.setText((this._algoLeft == null) ? "" : SetupPanel.ARR_ALGORITHMS[this._algoLeft.getAlgorithm()]);
        AlgoTableModel model = (AlgoTableModel)this._tableLeft.getModel();
        model.setAlogrithm(this._algoLeft);
        this._tableLeft.getColumnModel().getColumn(2).setCellRenderer(new ProcCellRenderer(SetupPanel.getMaxProcTime()));
        this._tableLeft.setRowHeight(0, 20);
        this._tableLeft.changeSelection(0, 0, false, false);
        Debug.out("Right: ");
        this._algoRight = this._absSetup.getRightAlgorithm();
        this._labelRight.setText((this._algoRight == null) ? "" : SetupPanel.ARR_ALGORITHMS[this._algoRight.getAlgorithm()]);
        model = (AlgoTableModel)this._tableRight.getModel();
        model.setAlogrithm(this._algoRight);
        this._tableRight.getColumnModel().getColumn(2).setCellRenderer(new ProcCellRenderer(SetupPanel.getMaxProcTime()));
        this._tableRight.setRowHeight(0, 20);
        this._tableRight.changeSelection(0, 0, false, false);
        Debug.out("Stats: ");
        final int selTab = this._tabStats.getSelectedIndex();
        final Algorithm leftTest = (this._absSetup.getLeftAlgorithm() == null) ? new Algorithm(0, 0, 0, new Vector()) : this._absSetup.getLeftAlgorithm();
        while (!leftTest.incrClock1ms()) {}
        final Algorithm rightTest = (this._absSetup.getRightAlgorithm() == null) ? new Algorithm(0, 0, 0, new Vector()) : this._absSetup.getRightAlgorithm();
        while (!rightTest.incrClock1ms()) {}
        final int xMax = Math.max(leftTest.getCpuTime(), rightTest.getCpuTime());
        final int yMaxWtTime = Math.max(leftTest.getJobsStarted(), rightTest.getJobsStarted());
        final int yMaxTpTime = Math.max(leftTest.getJobsCompleted(), rightTest.getJobsCompleted());
        this._plotJobsStarted = new PlotPanel(0, 0, this._tabStats.getWidth() - 100, this._tabStats.getHeight(), "Count", "CPU Time (ms)", xMax, yMaxWtTime);
        this._plotJobsCompleted = new PlotPanel(0, 0, this._tabStats.getWidth() - 100, this._tabStats.getHeight(), "Count", "CPU Time (ms)", xMax, yMaxTpTime);
        this._tabStats.removeAll();
        this._tabStats.add("<html><center>Jobs<br>Started</center></html>", this._plotJobsStarted);
        this._tabStats.add("<html><center>Jobs<br>Completed</center></html>", this._plotJobsCompleted);
        this._tabStats.setSelectedIndex(selTab);
        this.repaint(this._tabStats.getX(), this._tabStats.getY(), this._tabStats.getWidth(), this._tabStats.getHeight());
    }
    
    private void setupStatsPanel() {
        final int leftX = 174;
        final int topY = 333;
        final int height = 141;
        final int width = 449;
        (this._tabStats = new JTabbedPane()).setBounds(leftX, topY, width, height);
        this._tabStats.setTabPlacement(4);
        this._tabStats.setBackground(Main.WINDOW_COLOR);
        this._tabStats.add("<html><center>Jobs<br>Started</center></html>", this._plotJobsStarted = new PlotPanel());
        this._tabStats.add("<html><center>Jobs<br>Completed</center></html>", this._plotJobsCompleted = new PlotPanel());
        this.add(this._tabStats);
    }
    
    static {
        COLOR_LABEL = new Color(0, 0, 128);
    }
}
