import javax.swing.event.ChangeEvent;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_SortingAlgorithms extends JApplet implements ChangeListener
{
    private static final String[] STR_LABELS;
    public static final int SORT_BUBBLE = 0;
    public static final int SORT_INSERT = 1;
    public static final int SORT_MERGE = 2;
    public static final int SORT_QUICK = 3;
    public static final int SORT_SELECT = 4;
    private boolean _boolApplet;
    private boolean _boolLoaded;
    private static int _intCompPenalty;
    private static int _intSwapPenalty;
    private static int _intFuncPenalty;
    private SortInterface _leftSort;
    private SortInterface _rightSort;
    private SortingPanel _leftSortPanel;
    private SortingPanel _rightSortPanel;
    private JTabbedPane _tabMain;
    private CSlider _slider;
    private Timer _timer;
    private JButton _buttonStep;
    private JButton _buttonRun;
    private ImageIcon _imgRun;
    private ImageIcon _imgPause;
    private ImageIcon _imgStep;
    private ImageIcon _imgNoStep;
    private JComboBox _comboLeft;
    private JLabel[] _comboLeftLabels;
    private JComboBox _comboRight;
    private JLabel[] _comboRightLabels;
    private JCheckBox _checkPause;
    private boolean _boolDone;
    
    public UI_SortingAlgorithms() {
        this(true);
    }
    
    public UI_SortingAlgorithms(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        UI_SortingAlgorithms._intCompPenalty = 1;
        UI_SortingAlgorithms._intSwapPenalty = 2;
        UI_SortingAlgorithms._intFuncPenalty = 9;
        this._boolDone = false;
        this._comboLeftLabels = new JLabel[UI_SortingAlgorithms.STR_LABELS.length];
        for (int i = 0; i < UI_SortingAlgorithms.STR_LABELS.length; ++i) {
            this._comboLeftLabels[i] = new JLabel(UI_SortingAlgorithms.STR_LABELS[i]);
        }
        this._comboRightLabels = new JLabel[UI_SortingAlgorithms.STR_LABELS.length];
        for (int i = 0; i < UI_SortingAlgorithms.STR_LABELS.length; ++i) {
            this._comboRightLabels[i] = new JLabel(UI_SortingAlgorithms.STR_LABELS[i]);
        }
        this.getContentPane().setBackground(Main.WINDOW_COLOR);
        this.getContentPane().setLayout(null);
        (this._tabMain = new JTabbedPane()).setBackground(Main.WINDOW_COLOR);
        this._tabMain.setBounds(45, 20, 705, 515);
        final JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 705, 515);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.add(this._leftSortPanel = new SortingPanel(30, 20, 301, 300, 50));
        panel.add(this._rightSortPanel = new SortingPanel(370, 20, 301, 300, 50));
        this._rightSortPanel.setNumOfBars(this._leftSortPanel);
        this.setupButtons(panel);
        panel.add(this.makeSliderPanel());
        panel.add(this.makeLeftCombo());
        panel.add(this.makeRightCombo());
        final JPanel hr = new JPanel(null);
        hr.setBounds(349, 20, 3, 300);
        hr.setBorder(BorderFactory.createEtchedBorder(0));
        panel.add(hr);
        this._tabMain.add("Compare Algorithms", panel);
        this.getContentPane().add(this._tabMain);
        this.pInitTitle("Sorting Algorithms");
        if (!this._boolLoaded) {}
        this._boolLoaded = true;
    }
    
    public static int getCompPenalty() {
        return UI_SortingAlgorithms._intCompPenalty;
    }
    
    public static int getSwapPenalty() {
        return UI_SortingAlgorithms._intSwapPenalty;
    }
    
    public static int getFuncPenalty() {
        return UI_SortingAlgorithms._intFuncPenalty;
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(4);
        labelTitle.setHorizontalAlignment(4);
        labelTitle.setFont(new Font("Dialog", 1, 24));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 0, 735, 50);
        this.getContentPane().add(labelTitle);
    }
    
    public void setupButtons(final JPanel panel) {
        final int margin = 20;
        final int buttonWidth = 30;
        final int buttonHeight = 30;
        final int buttonMargin = 70;
        final int buttonOffsetY = 20;
        final ImageLoader loader = new ImageLoader();
        this._imgRun = loader.getImageIcon("images/run.gif");
        this._imgPause = loader.getImageIcon("images/pause.gif");
        this._imgStep = loader.getImageIcon("images/step.gif");
        this._imgNoStep = loader.getImageIcon("images/nostep.gif");
        (this._buttonRun = new JButton(this._imgRun)).setBounds(buttonMargin, margin * buttonOffsetY, buttonWidth, buttonHeight);
        this._buttonRun.setToolTipText("Click To Run");
        this._buttonRun.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_SortingAlgorithms.this.startSort();
            }
        });
        panel.add(this._buttonRun);
        (this._buttonStep = new JButton(this._imgStep)).setDisabledIcon(this._imgNoStep);
        this._buttonStep.setBounds(buttonMargin, margin * (buttonOffsetY + 2), buttonWidth, buttonHeight);
        this._buttonStep.setToolTipText("Click To Step");
        this._buttonStep.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_SortingAlgorithms.this.timerEvent();
            }
        });
        panel.add(this._buttonStep);
        (this._checkPause = new JCheckBox("<html>&nbsp;&nbsp;Stop on <br>&nbsp;&nbsp;first complete</html>")).setBounds(560, 420, 200, 30);
        this._checkPause.setOpaque(false);
        this._checkPause.setBackground(Main.WINDOW_COLOR);
        this._checkPause.setToolTipText("Check to pause on first to complete");
        panel.add(this._checkPause);
    }
    
    private JPanel makeSliderPanel() {
        final JPanel panel = new JPanel(null);
        final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(0), "Number Of Elements");
        title.setTitleColor(Color.WHITE);
        panel.setBorder(title);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setBounds(174, 395, 370, 80);
        (this._slider = new CSlider(10, 20, 350, 70, 5, 100, 50)).addChangeListener(this);
        this._slider.setBackground(Color.ORANGE);
        this._slider.setOpaque(false);
        panel.add(this._slider);
        return panel;
    }
    
    private JPanel makeLeftCombo() {
        final JPanel panel = new JPanel(null);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setBounds(this._leftSortPanel.getX() - 10, this._leftSortPanel.getHeight() + 20, this._leftSortPanel.getWidth(), 50);
        (this._comboLeft = new JComboBox((E[])this._comboLeftLabels)).setSelectedIndex(3);
        this._comboRightLabels[3].setEnabled(false);
        this._comboLeft.setRenderer(new JLabelRenderer());
        this._comboLeft.addActionListener(new JLabelRenderer(this._comboLeft));
        this._comboLeft.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                for (int i = 0; i < UI_SortingAlgorithms.this._comboRightLabels.length; ++i) {
                    UI_SortingAlgorithms.this._comboRightLabels[i].setEnabled(true);
                }
                UI_SortingAlgorithms.this._comboRightLabels[UI_SortingAlgorithms.this._comboLeft.getSelectedIndex()].setEnabled(false);
                UI_SortingAlgorithms.this._leftSortPanel.setNumOfBars(UI_SortingAlgorithms.this._slider.getValue());
                UI_SortingAlgorithms.this.setLeftPanel();
                UI_SortingAlgorithms.this._rightSortPanel.setNumOfBars(UI_SortingAlgorithms.this._leftSortPanel);
                UI_SortingAlgorithms.this.setRightPanel();
            }
        });
        this._comboLeft.setBounds((panel.getWidth() - 125) / 2, 10, 150, 28);
        this._comboLeft.setBackground(Main.WINDOW_COLOR);
        panel.add(this._comboLeft);
        this._leftSort = new QuickSort(this._leftSortPanel);
        return panel;
    }
    
    private JPanel makeRightCombo() {
        final JPanel panel = new JPanel(null);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setBounds(this._rightSortPanel.getX() - 10, this._rightSortPanel.getHeight() + 20, this._rightSortPanel.getWidth(), 50);
        (this._comboRight = new JComboBox((E[])this._comboRightLabels)).setSelectedIndex(0);
        this._comboLeftLabels[0].setEnabled(false);
        this._comboRight.setRenderer(new JLabelRenderer());
        this._comboRight.addActionListener(new JLabelRenderer(this._comboRight));
        this._comboRight.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                for (int i = 0; i < UI_SortingAlgorithms.this._comboLeftLabels.length; ++i) {
                    UI_SortingAlgorithms.this._comboLeftLabels[i].setEnabled(true);
                }
                UI_SortingAlgorithms.this._comboLeftLabels[UI_SortingAlgorithms.this._comboRight.getSelectedIndex()].setEnabled(false);
                UI_SortingAlgorithms.this._leftSortPanel.setNumOfBars(UI_SortingAlgorithms.this._slider.getValue());
                UI_SortingAlgorithms.this.setLeftPanel();
                UI_SortingAlgorithms.this._rightSortPanel.setNumOfBars(UI_SortingAlgorithms.this._leftSortPanel);
                UI_SortingAlgorithms.this.setRightPanel();
            }
        });
        this._comboRight.setBounds((panel.getWidth() - 125) / 2, 10, 150, 28);
        this._comboRight.setBackground(Main.WINDOW_COLOR);
        panel.add(this._comboRight);
        this._rightSort = new BubbleSort(this._rightSortPanel);
        return panel;
    }
    
    public void setLeftPanel() {
        switch (this._comboLeft.getSelectedIndex()) {
            case 0: {
                this._leftSort = new BubbleSort(this._leftSortPanel);
                break;
            }
            case 1: {
                this._leftSort = new InsertionSort(this._leftSortPanel);
                break;
            }
            case 2: {
                this._leftSort = new MergeSort(this._leftSortPanel);
                break;
            }
            case 3: {
                this._leftSort = new QuickSort(this._leftSortPanel);
                break;
            }
            case 4: {
                this._leftSort = new SelectionSort(this._leftSortPanel);
                break;
            }
        }
    }
    
    public void setRightPanel() {
        switch (this._comboRight.getSelectedIndex()) {
            case 0: {
                this._rightSort = new BubbleSort(this._rightSortPanel);
                break;
            }
            case 1: {
                this._rightSort = new InsertionSort(this._rightSortPanel);
                break;
            }
            case 2: {
                this._rightSort = new MergeSort(this._rightSortPanel);
                break;
            }
            case 3: {
                this._rightSort = new QuickSort(this._rightSortPanel);
                break;
            }
            case 4: {
                this._rightSort = new SelectionSort(this._rightSortPanel);
                break;
            }
        }
    }
    
    public void stateChanged(final ChangeEvent e) {
        if (this._timer != null && this._timer.isRunning()) {
            this._timer.stop();
        }
        if (this._slider.getValue() != this._slider.getOldValue()) {
            this._leftSortPanel.setNumOfBars(this._slider.getValue());
            this.setLeftPanel();
            this._rightSortPanel.setNumOfBars(this._leftSortPanel);
            this.setRightPanel();
        }
    }
    
    public void startSort() {
        if (this._timer != null && this._timer.isRunning()) {
            this._timer.stop();
            this._buttonRun.setIcon(this._imgRun);
            this._buttonRun.setToolTipText("Click To Run");
            this._buttonStep.setEnabled(true);
            this._comboLeft.setEnabled(true);
            this._comboRight.setEnabled(true);
            this._slider.setEnabled(true);
            this._checkPause.setEnabled(true);
            this._checkPause.setForeground(Color.BLACK);
            return;
        }
        this._buttonStep.setEnabled(false);
        this._buttonRun.setIcon(this._imgPause);
        this._buttonRun.setToolTipText("Click To Pause");
        this._comboLeft.setEnabled(false);
        this._comboRight.setEnabled(false);
        this._slider.setEnabled(false);
        this._checkPause.setEnabled(false);
        this._checkPause.setForeground(Color.LIGHT_GRAY);
        (this._timer = new Timer(2, new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_SortingAlgorithms.this.timerEvent();
            }
        })).start();
    }
    
    public void timerEvent() {
        if (this._boolDone) {
            this._leftSortPanel.setNumOfBars(this._slider.getValue());
            this.setLeftPanel();
            this._rightSortPanel.setNumOfBars(this._leftSortPanel);
            this.setRightPanel();
            this._boolDone = false;
            return;
        }
        final boolean boolBubble = this._leftSort.step();
        this._leftSortPanel.setProgress(this._leftSort.getPercentDone());
        this._leftSortPanel.repaint();
        final boolean boolSelection = this._rightSort.step();
        this._rightSortPanel.setProgress(this._rightSort.getPercentDone());
        this._rightSortPanel.repaint();
        if ((boolBubble && boolSelection) || ((boolBubble || boolSelection) && this._checkPause.isSelected())) {
            this._boolDone = true;
            if (this._timer != null && this._timer.isRunning()) {
                this._timer.stop();
            }
            this._leftSortPanel.setColorBlack();
            this._rightSortPanel.setColorBlack();
            this._buttonRun.setIcon(this._imgRun);
            this._buttonRun.setToolTipText("Click To Run");
            this._buttonStep.setEnabled(true);
            this._comboLeft.setEnabled(true);
            this._comboRight.setEnabled(true);
            this._slider.setEnabled(true);
            this._checkPause.setEnabled(true);
            this._checkPause.setForeground(Color.BLACK);
        }
    }
    
    static {
        STR_LABELS = new String[] { "Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Selection Sort" };
    }
}
