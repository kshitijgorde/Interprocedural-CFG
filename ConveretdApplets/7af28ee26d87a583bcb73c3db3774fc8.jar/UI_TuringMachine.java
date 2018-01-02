import javax.swing.event.ChangeEvent;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;
import java.util.List;
import javax.swing.SpinnerListModel;
import java.awt.Container;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.util.Vector;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_TuringMachine extends JApplet implements ActionListener
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private boolean _boolApplet;
    private boolean _boolLoaded;
    private Vector _vectorProgram;
    private StateClass _currentState;
    private TapePanel _tapePanel;
    private ProgramInterface _programPanel;
    private Timer _timer;
    private JButton _buttonStart;
    private JButton _buttonResume;
    private JButton _buttonStep;
    private JLabel _labelSet;
    private JTextField _textSet;
    private JButton _buttonSet;
    private JSpinner _spinSpeed;
    private JRadioButton _radioAdder;
    private JRadioButton _radioBetter;
    private JRadioButton _radioOther1;
    private JRadioButton _radioOther2;
    private static final int BINARY_ADDER = 1;
    private static final int BETTER_BINARY_ADDER = 2;
    private static final int PALINDROME = 3;
    private static final int OTHER_TWO = 4;
    
    public UI_TuringMachine() {
        this(true);
    }
    
    public UI_TuringMachine(final boolean applet) {
        this._boolApplet = applet;
        if (applet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    private String pGetParameter(final String key, final String def) {
        if (this._boolApplet) {
            final String param = this.getParameter(key);
            return (param == null) ? def : param;
        }
        return System.getProperty(key, def);
    }
    
    public void init() {
        this._boolLoaded = false;
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(123, 123, 123));
        this.pInitTitle("Turing Machine");
        this.pInitButtons();
        this.pInitSpeed();
        this._tapePanel = new TapePanel(150, 120, this.getContentPane().getBackground());
        this.pAdderProgram();
        this._programPanel = new ProgramPanel(150, 240, 500, 280, this.getContentPane().getBackground(), this._vectorProgram);
        this.pCreatePopupMenu();
        this.getContentPane().add((Component)this._programPanel);
        this.getContentPane().add(this._tapePanel);
        this._boolLoaded = true;
    }
    
    private void pInitButtons() {
        (this._buttonStart = new JButton("Start")).setBounds(150, 80, 90, 20);
        this._buttonStart.setFocusable(false);
        this._buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TuringMachine.this.doStart();
            }
        });
        this.getContentPane().add(this._buttonStart);
        (this._buttonStep = new JButton("Step")).setBounds(250, 80, 90, 20);
        this._buttonStep.setFocusable(false);
        this._buttonStep.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TuringMachine.this.doStep();
            }
        });
        this.getContentPane().add(this._buttonStep);
        (this._labelSet = new JLabel("Enter Initial Tape Characters")).setForeground(Color.white);
        this._labelSet.setBounds(150, 225, 200, 30);
        this.getContentPane().add(this._labelSet);
        (this._textSet = new JTextField("")).setBounds(320, 225, 250, 30);
        this.getContentPane().add(this._textSet);
        (this._buttonSet = new JButton("Set")).setBounds(580, 230, 60, 20);
        this._buttonSet.setFocusable(false);
        this._buttonSet.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TuringMachine.this.doSet();
            }
        });
        this.getContentPane().add(this._buttonSet);
    }
    
    private void doSet() {
        final String chars = this._textSet.getText();
        while (this._tapePanel.moveRight()) {}
        for (int i = 0; i < chars.length(); ++i) {
            this._tapePanel.setTape("" + chars.charAt(i));
            this._tapePanel.moveLeft();
        }
        this._tapePanel.setTape("");
        while (this._tapePanel.moveLeft()) {
            this._tapePanel.setTape("");
        }
        while (this._tapePanel.getTape().equals("")) {
            this._tapePanel.moveRight();
        }
        this._tapePanel.moveLeft();
    }
    
    private boolean doStep() {
        final StateClass actualState = this._programPanel.setState(new StateClass(this._currentState.getStateCurrent(), this._tapePanel.getTape(), "", 0, ""));
        this._currentState = ((actualState == null) ? this._currentState : actualState);
        this._tapePanel.setTape(this._currentState.getValueNext());
        final boolean boolMove = this._tapePanel.move(this._currentState.getDirection(null));
        if (!boolMove) {
            System.out.println("End of Tape!!!");
            while (!this._tapePanel.getTape().equals("")) {
                this._tapePanel.moveLeft();
            }
            this._currentState = this._programPanel.setState(this._vectorProgram.elementAt(this._vectorProgram.size() - 1));
            return false;
        }
        final String currentState = this._currentState.getStateNext();
        final String currentValue = this._tapePanel.getTape();
        this._currentState = this._programPanel.setState(new StateClass(currentState, currentValue, "", 0, ""));
        if (this._currentState == null) {
            this._currentState = this._programPanel.setState(this._vectorProgram.elementAt(0));
            return false;
        }
        return true;
    }
    
    private void doResume() {
        try {
            this._timer.restart();
        }
        catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
    
    private void doStart() {
        if (this._buttonStart.getText().equals("Start")) {
            this._buttonStart.setText("Stop");
            this._buttonStep.setEnabled(false);
            this._spinSpeed.setEnabled(false);
            this._buttonSet.setEnabled(false);
            int interval = 200;
            final String speed = (String)this._spinSpeed.getValue();
            if (speed.equalsIgnoreCase("Slow")) {
                interval = 400;
            }
            else if (speed.equalsIgnoreCase("Fast")) {
                interval = 100;
            }
            (this._timer = new Timer(interval, this)).start();
        }
        else {
            this._buttonStart.setText("Start");
            this._buttonStep.setEnabled(true);
            this._spinSpeed.setEnabled(true);
            this._buttonSet.setEnabled(true);
            this._timer.stop();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (!this.doStep()) {
            this._buttonStart.setText("Start");
            this._buttonStep.setEnabled(true);
            this._spinSpeed.setEnabled(true);
            this._buttonSet.setEnabled(true);
            this._timer.stop();
        }
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 10, 800, 50);
        this.getContentPane().add(labelTitle);
    }
    
    private void pAdderProgram() {
        this._vectorProgram = new Vector();
        StateClass state = new StateClass("START", "", "", -1, "ADD");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "0", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "1", "0", -1, "CARRY");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "", "", 1, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "0", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "1", "0", -1, "CARRY");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "", "1", -1, "OVERFLOW");
        this._vectorProgram.add(state);
        state = new StateClass("OVERFLOW", "", "", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "0", "0", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "1", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("ERROR!", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        this._currentState = this._vectorProgram.elementAt(0);
        this._textSet.setText("  101");
        this.doSet();
    }
    
    private void pBetterAdderProgram() {
        this._vectorProgram = new Vector();
        StateClass state = new StateClass("START", "", "", -1, "ADD");
        this._vectorProgram.add(state);
        state = new StateClass("START", "1", "1", 1, "START");
        this._vectorProgram.add(state);
        state = new StateClass("START", "0", "0", 1, "START");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "0", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "1", "0", -1, "CARRY");
        this._vectorProgram.add(state);
        state = new StateClass("ADD", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "0", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "1", "0", -1, "CARRY");
        this._vectorProgram.add(state);
        state = new StateClass("CARRY", "", "1", 0, "OVERFLOW");
        this._vectorProgram.add(state);
        state = new StateClass("OVERFLOW", "1", "1", 0, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "0", "0", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "1", "1", 1, "RETURN");
        this._vectorProgram.add(state);
        state = new StateClass("RETURN", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("ERROR!", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        this._currentState = this._vectorProgram.elementAt(0);
        this._textSet.setText("  101");
        this.doSet();
    }
    
    private void pPalidromeProgram() {
        this._vectorProgram = new Vector();
        StateClass state = new StateClass("START", "", "", -1, "STEP 1");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 1", "B", "B", -1, "STEP 1");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 1", "A", "A", -1, "STEP 1");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 1", "", "#", 1, "STEP 2");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 2", "A", "", 1, "STEP 3");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 2", "B", "", 1, "STEP 4");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 2", "", "", -1, "STEP 7");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 3", "A", "A", 1, "STEP 3");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 3", "B", "B", 1, "STEP 3");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 3", "", "", -1, "STEP 5");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 4", "A", "A", 1, "STEP 4");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 4", "B", "B", 1, "STEP 4");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 4", "", "", -1, "STEP 6");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 5", "A", "", -1, "STEP 11");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 5", "B", "", -1, "STEP 12");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 5", "", "", -1, "STEP 7");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 6", "A", "", -1, "STEP 12");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 6", "B", "", -1, "STEP 11");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 6", "", "", -1, "STEP 7");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 7", "", "", -1, "STEP 7");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 7", "#", "", 1, "STEP 8");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 8", "", "Y", 1, "STEP 9");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 9", "", "E", 1, "STEP 10");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 10", "", "S", 1, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 11", "A", "A", -1, "STEP 11");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 11", "B", "B", -1, "STEP 11");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 11", "", "", 1, "STEP 2");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 12", "A", "", -1, "STEP 12");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 12", "B", "", -1, "STEP 12");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 12", "", "", -1, "STEP 12");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 12", "#", "", 1, "STEP 13");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 13", "", "N", 1, "STEP 14");
        this._vectorProgram.add(state);
        state = new StateClass("STEP 14", "", "O", 1, "HALT");
        this._vectorProgram.add(state);
        state = new StateClass("ERROR!", "", "", 0, "HALT");
        this._vectorProgram.add(state);
        this._currentState = this._vectorProgram.elementAt(0);
        this._textSet.setText(" BABBBAABBBAB");
        this.doSet();
    }
    
    private void pInitSpeed() {
        final JLabel labelSpeed = new JLabel("Speed");
        labelSpeed.setBounds(500, 76, 100, 28);
        labelSpeed.setForeground(Color.white);
        this.getContentPane().add(labelSpeed);
        final SpinnerCircularListModel sclm = new SpinnerCircularListModel(new String[] { "Slow", "Medium", "Fast" });
        (this._spinSpeed = new JSpinner(sclm)).setValue("Medium");
        this._spinSpeed.setBounds(550, 76, 100, 28);
        this._spinSpeed.setFocusable(false);
        this._spinSpeed.setEditor(new Editor(this._spinSpeed));
        this.getContentPane().add(this._spinSpeed);
    }
    
    private void pInitRadios() {
        final JPanel panel = new JPanel();
        panel.setBounds(150, 520, 500, 35);
        panel.setBorder(new BorderUIResource.EtchedBorderUIResource(1));
        (this._radioAdder = new JRadioButton("Adder")).setFocusable(false);
        panel.add(this._radioAdder);
        (this._radioBetter = new JRadioButton("Better")).setFocusable(false);
        panel.add(this._radioBetter);
        (this._radioOther1 = new JRadioButton("Other 1")).setFocusable(false);
        panel.add(this._radioOther1);
        (this._radioOther2 = new JRadioButton("Other 2")).setFocusable(false);
        panel.add(this._radioOther2);
        this.getContentPane().add(panel);
    }
    
    public void pCreatePopupMenu() {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setFont(new Font("Dialog", 0, 12));
        JMenuItem menuItem = new JMenuItem("Binary Adder");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TuringMachine.this.changeProgram(1);
            }
        });
        popupMenu.add(menuItem);
        menuItem = new JMenuItem("Palindrome Detector");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TuringMachine.this.changeProgram(3);
            }
        });
        popupMenu.add(menuItem);
        this.pMouseListeners((Component)this._programPanel, popupMenu);
        final JLabel labelPopup = new JLabel("Note: To change programs, 'RIGHT-CLICK' on table.");
        labelPopup.setBounds(150, 520, 500, 35);
        labelPopup.setForeground(Color.white);
        labelPopup.setHorizontalAlignment(0);
        labelPopup.setHorizontalTextPosition(0);
        this.getContentPane().add(labelPopup);
    }
    
    private void pMouseListeners(final Component c, final JPopupMenu popupMenu) {
        c.addMouseListener(new PopupListener(popupMenu));
        if (c instanceof Container) {
            final Container cont = (Container)c;
            final Component[] children = cont.getComponents();
            for (int i = 0; i < children.length; ++i) {
                this.pMouseListeners(children[i], popupMenu);
            }
        }
    }
    
    private void changeProgram(final int program) {
        this.getContentPane().remove((Component)this._programPanel);
        switch (program) {
            case 1: {
                this.pAdderProgram();
                this._programPanel = new ProgramPanel(150, 240, 500, 280, this.getContentPane().getBackground(), this._vectorProgram);
                this.pCreatePopupMenu();
                break;
            }
            case 2: {
                this.pBetterAdderProgram();
                this._programPanel = new ProgramPanel(150, 240, 500, 280, this.getContentPane().getBackground(), this._vectorProgram);
                this.pCreatePopupMenu();
                break;
            }
            case 3: {
                this.pPalidromeProgram();
                this._programPanel = new ProgramPanel(150, 240, 500, 280, this.getContentPane().getBackground(), this._vectorProgram);
                this.pCreatePopupMenu();
                break;
            }
        }
        this.getContentPane().add((Component)this._programPanel);
        this.getContentPane().repaint();
    }
    
    class SpinnerCircularListModel extends SpinnerListModel
    {
        public SpinnerCircularListModel(final Object[] items) {
            super(items);
        }
        
        public Object getNextValue() {
            final List list = this.getList();
            int index = list.indexOf(this.getValue());
            index = ((index >= list.size() - 1) ? 0 : (index + 1));
            return list.get(index);
        }
        
        public Object getPreviousValue() {
            final List list = this.getList();
            int index = list.indexOf(this.getValue());
            index = ((index <= 0) ? (list.size() - 1) : (index - 1));
            return list.get(index);
        }
    }
    
    class Editor extends JLabel implements ChangeListener
    {
        Editor(final JSpinner spinner) {
            spinner.addChangeListener(this);
            this.setPreferredSize(new Dimension(spinner.getWidth(), spinner.getHeight()));
            this.setHorizontalTextPosition(0);
            this.setHorizontalAlignment(0);
            this.setOpaque(true);
            this.setText((String)spinner.getValue());
        }
        
        public void stateChanged(final ChangeEvent evt) {
            final JSpinner spinner = (JSpinner)evt.getSource();
            final String value = (String)spinner.getValue();
            this.setText(value);
        }
    }
}
