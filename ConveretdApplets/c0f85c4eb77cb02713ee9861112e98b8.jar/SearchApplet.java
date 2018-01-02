import java.awt.Dimension;
import java.util.List;
import javax.swing.SpinnerListModel;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Insets;
import javax.swing.SpinnerModel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchApplet extends JApplet implements ActionListener, ChangeListener, MouseMotionListener, MouseListener
{
    private int numOfItems;
    private int numSelections;
    private int sortWindowWidth;
    private int sortWindowHeight;
    private int demoNumOfRuns;
    private int run;
    private int animSpeed;
    private int MouseX;
    private int MouseY;
    private double numToFind;
    private double[] arr1;
    private double[] arr2;
    private boolean isSorting;
    private boolean s1Done;
    private boolean s2Done;
    private boolean sortSeq;
    private boolean isDemoMode;
    private boolean drawCtr;
    private Color bgcolor;
    private SearchItem sort1;
    private SearchItem sort2;
    private JButton searchButton;
    private JButton demoButton;
    private JSlider slider;
    private JCheckBox sortSequential;
    private SimpleGraph plot;
    private JSpinner speedSpinner;
    
    public SearchApplet() {
        this.numOfItems = 50;
        this.numSelections = this.numOfItems * 10;
        this.sortWindowWidth = 200;
        this.sortWindowHeight = 200;
        this.demoNumOfRuns = 10;
        this.run = 0;
        this.animSpeed = 10;
        this.MouseX = 0;
        this.MouseY = 0;
        this.numToFind = -1.0;
        this.isSorting = false;
        this.s1Done = false;
        this.s2Done = false;
        this.sortSeq = false;
        this.isDemoMode = false;
        this.drawCtr = false;
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        try {
            final int colorValue = Integer.decode(this.getParameter("backGroundColor"));
            this.bgcolor = new Color(colorValue);
        }
        catch (Exception e2) {
            this.bgcolor = Color.white;
            e2.printStackTrace();
        }
        this.getContentPane().setBackground(this.bgcolor);
        this.getContentPane().setLayout(null);
        final JLabel titleLabel = new JLabel("Searching Algorithms");
        titleLabel.setSize(500, 40);
        titleLabel.setLocation(0, 0);
        titleLabel.setBackground(this.bgcolor);
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setFont(new Font("Dialog", 1, 32));
        titleLabel.setForeground(Color.orange);
        this.getContentPane().add(titleLabel);
        this.createValues();
        (this.sort1 = new SearchItem("", this.arr1, this.bgcolor, this, this.numToFind)).setLocation(40, 62);
        this.sort1.setSize(this.sortWindowWidth, this.sortWindowHeight);
        this.getContentPane().add(this.sort1);
        (this.sort2 = new SearchItem("", this.arr2, this.bgcolor, this, this.numToFind)).setLocation(260, 62);
        this.sort2.setSize(this.sortWindowWidth, this.sortWindowHeight);
        this.getContentPane().add(this.sort2);
        this.searchButton = new JButton("Start");
        this.demoButton = new JButton("Demo");
        final SpinnerCircularListModel sclm = new SpinnerCircularListModel(new String[] { "Slow", "Medium", "Fast" });
        this.speedSpinner = new JSpinner(sclm);
        this.searchButton.setMargin(new Insets(2, 2, 2, 2));
        this.searchButton.setSize(60, 25);
        this.searchButton.setBackground(this.bgcolor);
        this.searchButton.addActionListener(this);
        this.demoButton.setMargin(new Insets(2, 2, 2, 2));
        this.demoButton.setSize(60, 25);
        this.demoButton.setBackground(this.bgcolor);
        this.demoButton.addActionListener(this);
        this.speedSpinner.setValue("Medium");
        this.speedSpinner.setSize(80, 25);
        this.speedSpinner.setFocusable(false);
        this.speedSpinner.setEditor(new Editor(this.speedSpinner));
        this.speedSpinner.addChangeListener(this);
        int place = (this.getWidth() - (this.searchButton.getWidth() + this.demoButton.getWidth() + this.speedSpinner.getWidth())) / 2;
        if (place <= 0) {
            place = 0;
        }
        this.searchButton.setLocation(place, 295);
        this.getContentPane().add(this.searchButton);
        place += this.searchButton.getWidth() + 10;
        this.demoButton.setLocation(place, 295);
        this.getContentPane().add(this.demoButton);
        place += this.demoButton.getWidth() + 10;
        this.speedSpinner.setLocation(place, 295);
        this.getContentPane().add(this.speedSpinner);
        (this.slider = new JSlider(0, 10, 100, this.numOfItems)).setSize(300, 20);
        this.slider.setLocation(100, 270);
        this.slider.setBackground(this.bgcolor);
        this.slider.addChangeListener(this);
        this.slider.addMouseListener(this);
        this.slider.addMouseMotionListener(this);
        this.getContentPane().add(this.slider);
        (this.plot = new SimpleGraph()).setLocation((this.getWidth() - this.plot.getWidth()) / 2, 350);
        this.plot.setBackground(this.bgcolor);
        this.getContentPane().add(this.plot);
        (this.sortSequential = new JCheckBox("Sort Sequential Items")).setSize(148, 30);
        this.sortSequential.setLocation((this.getWidth() - this.sortSequential.getWidth()) / 2, 320);
        this.sortSequential.setBackground(this.bgcolor);
        this.sortSequential.setSelected(false);
        this.sortSequential.addActionListener(this);
        this.getContentPane().add(this.sortSequential);
        this.MouseY = this.slider.getLocation().y - 10;
    }
    
    void createValues() {
        final double f = this.sortWindowHeight / this.numOfItems;
        final double[] temp = new double[this.numSelections];
        for (int i = 0; i < this.numSelections; ++i) {
            temp[i] = i + 1;
        }
        this.randomizeArray(temp);
        this.arr1 = new double[this.numOfItems];
        this.arr2 = new double[this.numOfItems];
        for (int i = 0; i < this.numOfItems; ++i) {
            this.arr1[i] = temp[i];
            this.arr2[i] = temp[i];
        }
        this.sort(this.arr2);
        if (this.sortSeq) {
            this.sort(this.arr1);
        }
        this.numToFind = this.arr1[(int)(Math.random() * (this.arr1.length - 1))];
    }
    
    private void randomizeArray(final double[] a) {
        for (int i = a.length - 1; i >= 0; --i) {
            final int idx = (int)(Math.random() * (i - 1));
            final double temp = a[idx];
            final double temp2 = a[i];
            a[i] = temp;
            a[idx] = temp2;
        }
    }
    
    void sort(final double[] a, final int lo0, final int hi0) {
        int lo = lo0;
        int hi = hi0;
        if (lo >= hi) {
            return;
        }
        if (lo == hi - 1) {
            if (a[lo] > a[hi]) {
                final double T = a[lo];
                a[lo] = a[hi];
                a[hi] = T;
            }
            return;
        }
        final double pivot = a[(lo + hi) / 2];
        a[(lo + hi) / 2] = a[hi];
        a[hi] = pivot;
        while (lo < hi) {
            while (a[lo] <= pivot && lo < hi) {
                ++lo;
            }
            while (pivot <= a[hi] && lo < hi) {
                --hi;
            }
            if (lo < hi) {
                final double T2 = a[lo];
                a[lo] = a[hi];
                a[hi] = T2;
            }
        }
        a[hi0] = a[hi];
        a[hi] = pivot;
        this.sort(a, lo0, lo - 1);
        this.sort(a, hi + 1, hi0);
    }
    
    void sort(final double[] a) {
        this.sort(a, 0, a.length - 1);
    }
    
    public void startGraphs() {
        this.getContentPane().remove(this.sort1);
        this.getContentPane().remove(this.sort2);
        this.createValues();
        (this.sort1 = new SearchItem("SequentialSearch", this.arr1, this.bgcolor, this, this.numToFind)).setLocation(40, 62);
        this.sort1.setSize(this.sortWindowWidth, this.sortWindowHeight);
        this.sort1.setAnimationSpeed(this.animSpeed);
        (this.sort2 = new SearchItem("BinarySearch", this.arr2, this.bgcolor, this, this.numToFind)).setLocation(260, 62);
        this.sort2.setSize(this.sortWindowWidth, this.sortWindowHeight);
        this.sort2.setAnimationSpeed(this.animSpeed);
        this.getContentPane().add(this.sort1);
        this.getContentPane().add(this.sort2);
        this.getContentPane().validate();
        this.sort1.repaint();
        this.sort2.repaint();
        if (!this.isDemoMode) {
            this.searchButton.setText("Stop");
            this.isSorting = true;
        }
        this.s1Done = false;
        this.s2Done = false;
        this.sort1.startSearch();
        this.sort2.startSearch();
    }
    
    public void stopGraphs() {
        this.sort1.stop();
        this.sort2.stop();
    }
    
    private void runDemo() {
        if (this.run < this.demoNumOfRuns) {
            this.startGraphs();
            ++this.run;
        }
        else {
            this.numOfItems += 10;
            if (this.numOfItems > 100) {
                this.numOfItems = this.slider.getValue();
                this.isDemoMode = false;
                this.searchButton.setEnabled(true);
                this.demoButton.setEnabled(true);
                this.slider.setEnabled(true);
                this.sortSequential.setEnabled(true);
                return;
            }
            this.run = 0;
            this.startGraphs();
            ++this.run;
        }
    }
    
    public void stopSearch(final SearchItem sort) {
        if (sort == this.sort1) {
            this.s1Done = true;
        }
        if (sort == this.sort2) {
            this.s2Done = true;
        }
        if (this.s1Done && this.s2Done) {
            this.stopGraphs();
            this.plot.addData(this.numOfItems - 1, this.sort1.comparisons, this.sort2.comparisons);
            if (!this.isDemoMode) {
                this.searchButton.setText("Start");
                this.isSorting = false;
                this.demoButton.setEnabled(true);
                this.slider.setEnabled(true);
                this.sortSequential.setEnabled(true);
            }
            else {
                this.runDemo();
            }
        }
    }
    
    public int getNumOfSelections() {
        return this.numSelections;
    }
    
    private void setAnimationSpeedFromSpinner() {
        final String speed = (String)this.speedSpinner.getValue();
        if (this.isDemoMode) {
            if (speed.equals("Slow")) {
                this.animSpeed = 40;
            }
            else if (speed.equals("Medium")) {
                this.animSpeed = 10;
            }
            else {
                this.animSpeed = 2;
            }
        }
        else if (speed.equals("Slow")) {
            this.animSpeed = 200;
        }
        else if (speed.equals("Medium")) {
            this.animSpeed = 50;
        }
        else {
            this.animSpeed = 25;
        }
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        if (this.drawCtr) {
            g.setColor(Color.white);
            g.fillRect(this.MouseX + 1, this.MouseY + 1, 39, 19);
            g.setColor(Color.black);
            g.drawRect(this.MouseX, this.MouseY, 40, 20);
            g.drawString("" + this.slider.getValue(), this.MouseX + 10, this.MouseY + 15);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            this.isDemoMode = false;
            if (!this.isSorting) {
                this.demoButton.setEnabled(false);
                this.slider.setEnabled(false);
                this.sortSequential.setEnabled(false);
                this.setAnimationSpeedFromSpinner();
                this.startGraphs();
            }
            else {
                this.demoButton.setEnabled(true);
                this.slider.setEnabled(true);
                this.stopGraphs();
            }
        }
        else if (e.getSource() == this.demoButton) {
            this.isDemoMode = true;
            this.numOfItems = 10;
            this.searchButton.setEnabled(false);
            this.demoButton.setEnabled(false);
            this.slider.setEnabled(false);
            this.sortSequential.setEnabled(false);
            this.plot.resetDataValues();
            this.setAnimationSpeedFromSpinner();
            this.runDemo();
        }
        else if (e.getSource() == this.sortSequential) {
            if (this.sortSequential.isSelected()) {
                this.sortSeq = true;
            }
            else {
                this.sortSeq = false;
            }
        }
    }
    
    public void stateChanged(final ChangeEvent e) {
        if (e.getSource() == this.slider) {
            this.numOfItems = this.slider.getValue();
            this.repaint();
        }
        else {
            this.setAnimationSpeedFromSpinner();
            this.sort1.setAnimationSpeed(this.animSpeed);
            this.sort2.setAnimationSpeed(this.animSpeed);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int temp = mouseEvent.getPoint().x;
        if (temp > 0 && temp < this.slider.getWidth() - 5) {
            this.MouseX = temp + this.slider.getLocation().x + 17;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int temp = mouseEvent.getPoint().x;
        if (temp > 0 && temp < this.slider.getWidth() - 5) {
            this.MouseX = temp + this.slider.getLocation().x + 17;
        }
        this.drawCtr = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.drawCtr = false;
        this.repaint();
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
