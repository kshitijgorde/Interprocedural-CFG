import java.awt.event.ActionEvent;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class NavigateDate extends JPanel implements ActionListener, Observer
{
    private JButton goButton;
    private JComboBox year;
    private MonthChoice month;
    private int startYear;
    private int endYear;
    private MosaicData md;
    private imgViewer applet;
    
    NavigateDate(final imgViewer applet, final MosaicData md) {
        this.md = md;
        this.applet = applet;
        this.setLayout(new BoxLayout(this, 0));
        (this.month = new MonthChoice()).setFont(applet.normalFont);
        this.month.setToolTipText("Set target month");
        this.add(this.month);
        (this.year = new JComboBox()).setFont(applet.normalFont);
        this.year.setEditable(false);
        this.year.setToolTipText("Set target year");
        this.year.addItem("1999");
        this.add(this.year);
        (this.goButton = new JButton("Go")).setToolTipText("Go to target date");
        this.goButton.addActionListener(this);
        this.add(this.goButton);
        this.startYear = 1999;
        this.endYear = 1999;
        final Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = 100;
        this.setMinimumSize(preferredSize);
        preferredSize.width = 240;
        this.setMaximumSize(preferredSize);
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final Metadata currentScene = this.md.getCurrentScene();
        if (currentScene != null) {
            final int n = currentScene.date / 10000;
            int n2 = (currentScene.date - n * 10000) / 100;
            final SceneFilter sceneFilter = this.md.sceneFilter;
            final SearchLimitDialog searchLimitDialog = this.applet.searchLimitDialog;
            int n3 = searchLimitDialog.getStartYear();
            int n4 = searchLimitDialog.getEndYear();
            if (sceneFilter.getFirstYear() != 0 && sceneFilter.getFirstYear() > n3) {
                n3 = sceneFilter.getFirstYear();
            }
            if (sceneFilter.getLastYear() != 0 && sceneFilter.getFirstYear() < n4) {
                n4 = sceneFilter.getLastYear();
            }
            if (n2 < searchLimitDialog.getStartMonth() + 1) {
                n2 = searchLimitDialog.getStartMonth() + 1;
            }
            else if (n2 > searchLimitDialog.getEndMonth() + 1) {
                n2 = searchLimitDialog.getEndMonth() + 1;
            }
            if (n3 <= n4) {
                this.trackSelectedScene(n3, n4, searchLimitDialog.getStartMonth() + 1, searchLimitDialog.getEndMonth() + 1, n2, n);
            }
        }
    }
    
    public void trackSelectedScene(final int startYear, final int endYear, final int n, final int n2, final int n3, final int n4) {
        if (startYear != this.startYear || endYear != this.endYear) {
            final int n5 = this.year.getSelectedIndex() + this.startYear;
            this.year.removeAllItems();
            for (int i = startYear; i <= endYear; ++i) {
                this.year.addItem(Integer.toString(i));
            }
            this.startYear = startYear;
            this.endYear = endYear;
            int selectedIndex;
            if (n5 < this.startYear) {
                selectedIndex = 0;
            }
            else if (n5 > endYear) {
                selectedIndex = endYear - startYear;
            }
            else {
                selectedIndex = n5 - startYear;
            }
            this.year.setSelectedIndex(selectedIndex);
        }
        if (n4 >= this.startYear && n4 <= this.endYear) {
            this.year.setSelectedIndex(n4 - this.startYear);
        }
        this.month.configure(n, n2, n3);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Go")) {
            this.md.sceneFilter.gotoDate(this.year.getSelectedIndex() + this.startYear, this.month.getSelectedMonth());
        }
    }
}
