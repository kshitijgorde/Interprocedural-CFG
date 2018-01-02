import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ComponentListener;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchLimitDialog extends JDialog implements WindowListener, ActionListener, Observer, ComponentListener
{
    private JPanel cloudCoverPanel;
    private JPanel qualityPanel;
    private CloudCoverChoice ccPercent;
    private JPanel dataVersionPanel;
    private JComboBox dataVersionChoice;
    private JPanel gridColRowPanel;
    private JComboBox startGridColChoice;
    private JComboBox endGridColChoice;
    private JComboBox startGridRowChoice;
    private JComboBox endGridRowChoice;
    private JPanel mainPanel;
    private JPanel datePanel;
    private JPanel buttonPanel;
    private JComboBox startYearChoice;
    private JComboBox endYearChoice;
    private MonthChoice startMonthChoice;
    private MonthChoice endMonthChoice;
    private JButton okButton;
    private JButton cancelButton;
    private JButton clearButton;
    private JButton applyButton;
    private JCheckBox filterToSceneList;
    private JCheckBox filterToUserArea;
    private JPanel userAreaPanel;
    private JComboBox qualityChoice;
    private JLabel availScenes;
    private MosaicData md;
    private imgViewer applet;
    private NavigationModel nm;
    private int rangeStartYear;
    private int rangeEndYear;
    private int sensorStartYear;
    private int sensorEndYear;
    private int sensorMaximumColumn;
    private int sensorMinimumColumn;
    private int sensorMaximumRow;
    private int sensorMinimumRow;
    private int startYear;
    private int endYear;
    private int startMonth;
    private int endMonth;
    private int startGridCol;
    private int endGridCol;
    private int startGridRow;
    private int endGridRow;
    private boolean gridColRowFilterSet;
    private int minQuality;
    private String dataVersion;
    private int cloudCoverValue;
    private int savedResolution;
    private int savedGridCol;
    private int savedGridRow;
    private int savedSubCol;
    private int savedSubRow;
    private String savedSensorName;
    private int numOfAvailScenes;
    private boolean sceneListFilterStatus;
    private boolean userAreaFilterStatus;
    private boolean limitSet;
    private boolean limitCleared;
    private TOC[] copyOfMosaicCells;
    private String[] monthNames;
    private static final int QUAL_RANGE = 9;
    
    public int getStartYear() {
        return this.startYear;
    }
    
    public int getEndYear() {
        return this.endYear;
    }
    
    public int getStartMonth() {
        return this.startMonth;
    }
    
    public int getEndMonth() {
        return this.endMonth;
    }
    
    public int getStartGridCol() {
        return this.startGridCol;
    }
    
    public int getEndGridCol() {
        return this.endGridCol;
    }
    
    public int getStartGridRow() {
        return this.startGridRow;
    }
    
    public int getEndGridRow() {
        return this.endGridRow;
    }
    
    public int getMinQuality() {
        return this.minQuality;
    }
    
    public boolean isSceneListFilterEnabled() {
        return this.sceneListFilterStatus;
    }
    
    public boolean isUserDefinedAreaEnabled() {
        return this.userAreaFilterStatus;
    }
    
    public String getDataVersion() {
        return this.dataVersion;
    }
    
    public SearchLimitDialog(final JFrame frame, final imgViewer applet, final MosaicData md) {
        super(frame, "Set Search Limits", false);
        this.monthNames = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.applet = applet;
        this.md = md;
        this.getContentPane().setLayout(new BorderLayout());
        this.mainPanel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        this.mainPanel.setLayout(layout);
        (this.datePanel = new JPanel()).setLayout(new GridBagLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Start Year:"));
        (this.startYearChoice = new JComboBox()).setToolTipText("Set start year limit");
        this.startYearChoice.addActionListener(this);
        panel.add(this.startYearChoice);
        panel.add(new JLabel("End Year:"));
        (this.endYearChoice = new JComboBox()).setToolTipText("Set end year limit");
        this.endYearChoice.addActionListener(this);
        panel.add(this.endYearChoice);
        this.datePanel.add(panel, gridBagConstraints);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 2));
        panel2.add(new JLabel("Start Month:"));
        (this.startMonthChoice = new MonthChoice()).setToolTipText("Set start month limit");
        this.startMonthChoice.addActionListener(this);
        panel2.add(this.startMonthChoice);
        panel2.add(new JLabel("End Month:"));
        (this.endMonthChoice = new MonthChoice()).setSelectedIndex(11);
        this.endMonthChoice.setToolTipText("Set end month limit");
        this.endMonthChoice.addActionListener(this);
        panel2.add(this.endMonthChoice);
        this.datePanel.add(panel2, gridBagConstraints);
        this.mainPanel.add(this.datePanel, gridBagConstraints);
        (this.cloudCoverPanel = new JPanel()).setLayout(new GridLayout(1, 2));
        this.cloudCoverPanel.add(new JLabel("Max Cloud:"));
        (this.ccPercent = new CloudCoverChoice()).setToolTipText("Set max cloud cover limit");
        this.ccPercent.addActionListener(this);
        this.cloudCoverPanel.add(this.ccPercent);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        this.mainPanel.add(this.cloudCoverPanel, gridBagConstraints);
        (this.dataVersionPanel = new JPanel()).setLayout(new GridLayout(1, 2));
        this.dataVersionPanel.add(new JLabel("Data Version:"));
        (this.dataVersionChoice = new JComboBox()).addActionListener(this);
        this.dataVersionChoice.setToolTipText("Set data version limit");
        this.dataVersionPanel.add(this.dataVersionChoice);
        this.mainPanel.add(this.dataVersionPanel, gridBagConstraints);
        (this.gridColRowPanel = new JPanel()).setLayout(new GridLayout(4, 2));
        this.gridColRowPanel.add(new JLabel("Start Path:"));
        (this.startGridColChoice = new JComboBox()).addActionListener(this);
        this.gridColRowPanel.add(this.startGridColChoice);
        this.gridColRowPanel.add(new JLabel("End Path:"));
        (this.endGridColChoice = new JComboBox()).addActionListener(this);
        this.gridColRowPanel.add(this.endGridColChoice);
        this.gridColRowPanel.add(new JLabel("Start Row:"));
        (this.startGridRowChoice = new JComboBox()).addActionListener(this);
        this.gridColRowPanel.add(this.startGridRowChoice);
        this.gridColRowPanel.add(new JLabel("End Row:"));
        (this.endGridRowChoice = new JComboBox()).addActionListener(this);
        this.gridColRowPanel.add(this.endGridRowChoice);
        this.mainPanel.add(this.gridColRowPanel, gridBagConstraints);
        (this.qualityPanel = new JPanel()).setLayout(new GridLayout(1, 2));
        this.qualityPanel.add(new JLabel("Min Quality:"));
        this.qualityChoice = new JComboBox();
        for (int i = 9; i >= 0; --i) {
            this.qualityChoice.addItem("" + i);
        }
        this.qualityChoice.setToolTipText("Set min quality limit");
        this.qualityChoice.addActionListener(this);
        this.qualityPanel.add(this.qualityChoice);
        this.mainPanel.add(this.qualityPanel, gridBagConstraints);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 1));
        (this.filterToSceneList = new JCheckBox("Show only scenes in the scene list", false)).setToolTipText("Show only scenes in scene list");
        this.filterToSceneList.addActionListener(this);
        this.sceneListFilterStatus = false;
        panel3.add(this.filterToSceneList);
        this.mainPanel.add(panel3, gridBagConstraints);
        (this.userAreaPanel = new JPanel()).setLayout(new GridLayout(1, 1));
        (this.filterToUserArea = new JCheckBox("Show only scenes in user defined area", false)).setToolTipText("Show only scenes in the user defined area");
        this.filterToUserArea.addActionListener(this);
        this.userAreaFilterStatus = false;
        this.userAreaPanel.add(this.filterToUserArea);
        gridBagConstraints.gridwidth = 0;
        this.mainPanel.add(this.userAreaPanel, gridBagConstraints);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout());
        (this.availScenes = new JLabel("num of avail scenes")).setToolTipText("Number of Available scenes");
        panel4.add(this.availScenes);
        gridBagConstraints.gridheight = 0;
        this.mainPanel.add(this.availScenes, gridBagConstraints);
        (this.buttonPanel = new JPanel()).setLayout(new GridLayout(1, 4));
        (this.okButton = new JButton("Ok")).setMnemonic(79);
        this.okButton.setToolTipText("Set search limits & close search limits");
        this.okButton.addActionListener(this);
        (this.cancelButton = new JButton("Cancel")).setMnemonic(67);
        this.cancelButton.setToolTipText("Cancel changes to search limits");
        this.cancelButton.addActionListener(this);
        (this.clearButton = new JButton("Clear")).setMnemonic(76);
        this.clearButton.setToolTipText("Clear search limits");
        this.clearButton.addActionListener(this);
        (this.applyButton = new JButton("Apply")).setMnemonic(65);
        this.applyButton.setToolTipText("Apply search limits");
        this.applyButton.addActionListener(this);
        this.applyButton.setEnabled(false);
        this.buttonPanel.add(this.okButton);
        this.buttonPanel.add(this.clearButton);
        this.buttonPanel.add(this.applyButton);
        this.buttonPanel.add(this.cancelButton);
        this.getContentPane().add(this.mainPanel, "North");
        this.getContentPane().add(this.buttonPanel, "South");
        this.setSize(320, 350);
        this.dataVersion = "All";
        this.cloudCoverValue = md.maxCloudCover;
        this.configureForSensor(applet.sensorMenu.getCurrentSensor());
        this.addWindowListener(this);
        this.addComponentListener(this);
        applet.statusBar.showSearchLimits("No Limits Set");
    }
    
    private void populateGridColRow(final JComboBox comboBox, final int n, final int n2) {
        comboBox.removeAllItems();
        for (int i = n; i <= n2; ++i) {
            comboBox.addItem("" + i);
        }
    }
    
    private void populateYears(final JComboBox comboBox, final int rangeStartYear, final int rangeEndYear) {
        comboBox.removeAllItems();
        for (int i = rangeStartYear; i <= rangeEndYear; ++i) {
            comboBox.addItem("" + i);
        }
        this.rangeStartYear = rangeStartYear;
        this.rangeEndYear = rangeEndYear;
    }
    
    public void configureForSensor(final Sensor sensor) {
        this.sensorStartYear = sensor.getStartingYear();
        this.sensorEndYear = sensor.getEndingYear();
        if (this.sensorEndYear < 0) {
            this.sensorEndYear = new GregorianCalendar().get(1);
            if (this.sensorEndYear < 2004) {
                this.sensorEndYear = 2004;
            }
        }
        this.nm = sensor.navModel;
        this.sensorMaximumColumn = this.nm.getMaximumColumn();
        this.sensorMinimumColumn = this.nm.getMinimumColumn();
        this.sensorMaximumRow = this.nm.getMaximumRow();
        this.sensorMinimumRow = this.nm.getMinimumRow();
        if (sensor.hasGridColRowFilter) {
            this.nm = this.applet.sensorMenu.getCurrentSensor().navModel;
            final String colName = this.nm.getColName();
            final String rowName = this.nm.getRowName();
            this.startGridColChoice.setName("Start " + colName + ":");
            this.endGridColChoice.setName("End " + colName + ":");
            this.startGridRowChoice.setName("Start " + rowName + ":");
            this.endGridRowChoice.setName("End " + rowName + ":");
        }
        if (!this.gridColRowFilterSet) {
            this.startGridCol = this.sensorMinimumColumn;
            this.endGridCol = this.sensorMaximumColumn;
            this.startGridRow = this.sensorMinimumRow;
            this.endGridRow = this.sensorMaximumRow;
        }
        if (!this.limitSet) {
            this.startYear = this.sensorStartYear;
            this.endYear = this.sensorEndYear;
            this.startMonth = 0;
            this.endMonth = 11;
        }
        int n = this.sensorStartYear;
        int n2 = this.sensorEndYear;
        if (this.startYear < n) {
            n = this.startYear;
        }
        if (this.endYear > n2) {
            n2 = this.endYear;
        }
        if (this.rangeStartYear != n || this.rangeEndYear != n2) {
            this.populateYears(this.startYearChoice, n, n2);
            this.populateYears(this.endYearChoice, n, n2);
        }
        if (this.startYear < this.rangeStartYear) {
            System.out.println("Bug: Unexpected starting search year");
            this.startYearChoice.setSelectedIndex(0);
        }
        else {
            this.startYearChoice.setSelectedIndex(this.startYear - this.rangeStartYear);
        }
        if (this.endYear > this.rangeEndYear) {
            System.out.println("Bug: Unexpected ending search year");
            this.endYearChoice.setSelectedIndex(this.rangeEndYear - this.rangeStartYear);
        }
        else {
            this.endYearChoice.setSelectedIndex(this.endYear - this.rangeStartYear);
        }
        this.datePanel.setVisible(sensor.hasAcqDate);
        if (sensor.hasCloudCover) {
            this.cloudCoverPanel.setVisible(true);
        }
        else {
            this.cloudCoverPanel.setVisible(false);
        }
        if (sensor.numQualityValues > 0) {
            this.qualityPanel.setVisible(true);
        }
        else {
            this.qualityPanel.setVisible(false);
        }
        if (sensor.hasDataVersions) {
            int selectedIndex = 0;
            this.dataVersionChoice.removeAllItems();
            this.dataVersionChoice.addItem("All");
            for (int i = 0; i < sensor.dataVersions.length; ++i) {
                this.dataVersionChoice.addItem(sensor.dataVersions[i]);
                if (!this.dataVersion.equals("All") && this.dataVersion.equals(sensor.dataVersions[i])) {
                    selectedIndex = i + 1;
                }
            }
            this.dataVersionChoice.setSelectedIndex(selectedIndex);
            this.dataVersionPanel.setVisible(true);
        }
        else {
            this.dataVersionPanel.setVisible(false);
        }
        if (sensor.hasGridColRowFilter) {
            this.populateGridColRow(this.startGridColChoice, this.sensorMinimumColumn, this.sensorMaximumColumn);
            this.populateGridColRow(this.endGridColChoice, this.sensorMinimumColumn, this.sensorMaximumColumn);
            this.populateGridColRow(this.startGridRowChoice, this.sensorMinimumRow, this.sensorMaximumRow);
            this.populateGridColRow(this.endGridRowChoice, this.sensorMinimumRow, this.sensorMaximumRow);
            if (this.startGridRow > this.sensorMaximumRow || this.endGridRow < this.sensorMinimumRow) {
                this.startGridRow = this.sensorMinimumRow;
                this.endGridRow = this.sensorMaximumRow;
                this.gridColRowFilterSet = false;
            }
            this.startGridColChoice.setSelectedIndex(this.startGridCol - this.sensorMinimumColumn);
            this.endGridColChoice.setSelectedIndex(this.endGridCol - this.sensorMinimumColumn);
            this.startGridRowChoice.setSelectedIndex(this.startGridRow - this.sensorMinimumRow);
            this.endGridRowChoice.setSelectedIndex(this.endGridRow - this.sensorMinimumRow);
            this.gridColRowPanel.setVisible(true);
        }
        else {
            this.gridColRowFilterSet = false;
            this.gridColRowPanel.setVisible(false);
        }
        if (sensor.hasUserDefinedArea) {
            this.userAreaPanel.setVisible(true);
        }
        else {
            this.userAreaPanel.setVisible(false);
        }
        this.validate();
        this.buildStatusLimitMsg();
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
        this.ccPercent.setCloudCover(this.md.maxCloudCover);
        this.startYearChoice.setSelectedIndex(this.startYear - this.rangeStartYear);
        this.endYearChoice.setSelectedIndex(this.endYear - this.rangeStartYear);
        this.startMonthChoice.setSelectedIndex(this.startMonth);
        this.endMonthChoice.setSelectedIndex(this.endMonth);
        if (this.applet.sensorMenu.getCurrentSensor().hasGridColRowFilter) {
            this.startGridColChoice.setSelectedIndex(this.startGridCol - this.sensorMinimumColumn);
            this.endGridColChoice.setSelectedIndex(this.endGridCol - this.sensorMinimumColumn);
            this.startGridRowChoice.setSelectedIndex(this.startGridRow - this.sensorMinimumRow);
            this.endGridRowChoice.setSelectedIndex(this.endGridRow - this.sensorMinimumRow);
        }
        this.qualityChoice.setSelectedIndex(9 - this.minQuality);
    }
    
    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentShown(final ComponentEvent componentEvent) {
        this.updateTOC();
        this.updateNumOfAvailScenes();
    }
    
    public void updateYear() {
        final int n = this.startYearChoice.getSelectedIndex() + this.rangeStartYear;
        if (this.endYearChoice.getSelectedIndex() + this.rangeStartYear < n && this.endYearChoice.getItemCount() > 0) {
            this.endYearChoice.setSelectedIndex(n - this.rangeStartYear);
            goto Label_0054;
        }
        this.limitCleared = false;
        this.setApplyButtonState();
        if (this.isVisible()) {
            this.updateNumOfAvailScenes();
        }
    }
    
    private void setApplyButtonState() {
        boolean b = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        boolean b7 = true;
        boolean b8 = true;
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        if (this.startYear == this.startYearChoice.getSelectedIndex() + this.rangeStartYear && this.endYear == this.endYearChoice.getSelectedIndex() + this.rangeStartYear && this.startMonth == this.startMonthChoice.getSelectedIndex() && this.endMonth == this.endMonthChoice.getSelectedIndex()) {
            b = false;
        }
        if (currentSensor.hasGridColRowFilter) {
            if (this.startGridCol == this.startGridColChoice.getSelectedIndex() + this.sensorMinimumColumn && this.endGridCol == this.endGridColChoice.getSelectedIndex() + this.sensorMinimumColumn) {
                b7 = false;
            }
            if (this.startGridRow == this.startGridRowChoice.getSelectedIndex() + this.sensorMinimumRow && this.endGridRow == this.endGridRowChoice.getSelectedIndex() + this.sensorMinimumRow) {
                b8 = false;
            }
        }
        if (this.sceneListFilterStatus == this.filterToSceneList.isSelected()) {
            b5 = false;
        }
        if (this.userAreaFilterStatus == this.filterToUserArea.isSelected()) {
            b6 = false;
        }
        if (this.minQuality == 9 - this.qualityChoice.getSelectedIndex()) {
            b3 = false;
        }
        if (currentSensor.hasDataVersions) {
            if (this.dataVersionChoice.getSelectedIndex() <= 0) {
                if (this.dataVersion.equals("All")) {
                    b4 = false;
                }
            }
            else if (this.dataVersion.equals(this.dataVersionChoice.getSelectedItem().toString())) {
                b4 = false;
            }
        }
        else {
            b4 = false;
        }
        if (currentSensor.hasCloudCover) {
            if (this.cloudCoverValue == this.ccPercent.getCloudCover()) {
                b2 = false;
            }
        }
        else {
            b2 = false;
        }
        if (b || b2 || b3 || b4 || b5 || b6 || b7 || b8) {
            this.applyButton.setEnabled(true);
        }
        else {
            this.applyButton.setEnabled(false);
        }
    }
    
    public void setCloudCover(final int n) {
        this.ccPercent.setCloudCover(n);
        this.cloudCoverValue = n;
    }
    
    public void clearUserDefinedAreaEnabled() {
        this.userAreaFilterStatus = false;
    }
    
    public void applySearchLimits(final Metadata metadata) {
        metadata.clearFilter(1);
        metadata.filterToCloudCover(this.md.maxCloudCover);
        metadata.filterToDateRange(this.startYear, this.endYear, this.startMonth, this.endMonth);
        metadata.filterToSceneList(this.sceneListFilterStatus);
        metadata.filterToQuality(this.minQuality);
        metadata.filterToDataVersion(this.dataVersion);
        metadata.filterToHiddenScene();
        metadata.filterToUserArea(this.userAreaFilterStatus, this.applet.userDefinedAreaDialog);
        metadata.filterToGridColRowRange(this.startGridCol, this.endGridCol, this.startGridRow, this.endGridRow);
    }
    
    public void applyFilter() {
        this.md.setSearchLimitValues(this.startYear, this.endYear, this.startMonth, this.endMonth, this.ccPercent.getCloudCover(), this.minQuality, this.dataVersion, this.startGridCol, this.endGridCol, this.startGridRow, this.endGridRow);
    }
    
    private void buildStatusLimitMsg() {
        StringBuffer sb = new StringBuffer("");
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final int numQualityValues = currentSensor.numQualityValues;
        final boolean hasDataVersions = currentSensor.hasDataVersions;
        final boolean hasUserDefinedArea = currentSensor.hasUserDefinedArea;
        final String string = currentSensor.navModel.getColName() + "/" + currentSensor.navModel.getRowName();
        if (this.limitSet) {
            if (this.sceneListFilterStatus) {
                if (this.minQuality > 0 && numQualityValues > 0) {
                    sb.append("Date, List & Qlty Limits");
                }
                else if (hasDataVersions && !this.dataVersion.equals("All")) {
                    sb.append("Date, List & Version Limits");
                }
                else if (hasUserDefinedArea && this.userAreaFilterStatus) {
                    if (this.gridColRowFilterSet) {
                        sb.append("Various Limits");
                    }
                    else {
                        sb.append("Date, List & Area Limits");
                    }
                }
                else if (this.gridColRowFilterSet) {
                    sb.append("Various Limits");
                }
                else {
                    sb.append("Date & List Limits");
                }
            }
            else if (this.minQuality > 0 && numQualityValues > 0) {
                sb.append("Date & Qlty Limits");
            }
            else if (hasDataVersions && !this.dataVersion.equals("All")) {
                sb.append("Date & Version Limits");
            }
            else if (hasUserDefinedArea && this.userAreaFilterStatus) {
                if (this.gridColRowFilterSet) {
                    sb.append("Various Limits");
                }
                else {
                    sb.append("Date & Area Limits");
                }
            }
            else if (this.gridColRowFilterSet) {
                sb.append("Date & ");
                sb.append(string);
                sb.append(" Limits");
            }
            else {
                sb = new StringBuffer("Limits: ");
                if (this.startMonth != 0 || this.endMonth != 11) {
                    sb.append(this.monthNames[this.startMonth]);
                    if (this.startMonth != this.endMonth) {
                        sb.append("-");
                        sb.append(this.monthNames[this.endMonth]);
                    }
                    sb.append(", ");
                }
                sb.append(this.startYear);
                if (this.startYear != this.endYear) {
                    sb.append("-");
                    sb.append(this.endYear);
                }
            }
        }
        else if (this.sceneListFilterStatus) {
            if (this.minQuality > 0 && numQualityValues > 0) {
                sb.append("List & Qlty Limits");
            }
            else if (hasDataVersions && !this.dataVersion.equals("All")) {
                sb.append("List & Version Limits");
            }
            else if (hasUserDefinedArea && this.userAreaFilterStatus) {
                if (this.gridColRowFilterSet) {
                    sb.append("Various Limits");
                }
                else {
                    sb.append("List & Area Limits");
                }
            }
            else if (this.gridColRowFilterSet) {
                sb.append("List & ");
                sb.append(string);
                sb.append(" Limits");
            }
            else {
                sb.append("Scene List Only");
            }
        }
        else if (this.minQuality > 0 && numQualityValues > 0) {
            sb.append("Qlty Limit: " + this.minQuality);
        }
        else if (hasDataVersions && !this.dataVersion.equals("All")) {
            sb.append("Version Limit");
        }
        else if (hasUserDefinedArea && this.userAreaFilterStatus) {
            if (this.gridColRowFilterSet) {
                sb.append(string);
                sb.append(" & Area Limits");
            }
            else {
                sb.append("User Area Limit");
            }
        }
        else if (this.gridColRowFilterSet) {
            sb.append(string);
            sb.append(" Limit");
        }
        else {
            sb.append("No Limits Set");
        }
        this.applet.statusBar.showSearchLimits(sb.toString());
    }
    
    private void processDialogState(final boolean b) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final int startYear = this.startYearChoice.getSelectedIndex() + this.rangeStartYear;
        final int endYear = this.endYearChoice.getSelectedIndex() + this.rangeStartYear;
        final int selectedIndex = this.startMonthChoice.getSelectedIndex();
        final int selectedIndex2 = this.endMonthChoice.getSelectedIndex();
        final boolean selected = this.filterToSceneList.isSelected();
        boolean userAreaFilterStatus;
        if (this.filterToUserArea.isSelected()) {
            if (this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed()) {
                userAreaFilterStatus = true;
            }
            else {
                userAreaFilterStatus = false;
                this.filterToUserArea.setSelected(false);
                if (!this.applet.userDefinedAreaDialog.isVisible()) {
                    final Point locationOnScreen = this.getLocationOnScreen();
                    if (this.getToolkit().getScreenSize().width - 525 - locationOnScreen.x > 0) {
                        final Point point = locationOnScreen;
                        point.x += 250;
                    }
                    else {
                        final Point point2 = locationOnScreen;
                        point2.x -= 275;
                    }
                    this.applet.userDefinedAreaDialog.setLocation(locationOnScreen);
                    this.applet.userDefinedAreaDialog.setVisible(true);
                }
            }
        }
        else {
            userAreaFilterStatus = false;
        }
        final int minQuality = 9 - this.qualityChoice.getSelectedIndex();
        String string = "All";
        if (currentSensor.hasDataVersions) {
            if (this.dataVersionChoice.getSelectedIndex() <= 0) {
                string = "All";
            }
            else {
                string = this.dataVersionChoice.getSelectedItem().toString();
            }
        }
        int startGridCol = 0;
        int endGridCol = 0;
        int startGridRow = 0;
        int endGridRow = 0;
        if (currentSensor.hasGridColRowFilter) {
            startGridCol = this.startGridColChoice.getSelectedIndex() + this.sensorMinimumColumn;
            endGridCol = this.endGridColChoice.getSelectedIndex() + this.sensorMinimumColumn;
            startGridRow = this.startGridRowChoice.getSelectedIndex() + this.sensorMinimumRow;
            endGridRow = this.endGridRowChoice.getSelectedIndex() + this.sensorMinimumRow;
        }
        final int cloudCover = this.ccPercent.getCloudCover();
        if (b) {
            this.startYear = startYear;
            this.endYear = endYear;
            this.startMonth = selectedIndex;
            this.endMonth = selectedIndex2;
            this.gridColRowFilterSet = false;
            if (currentSensor.hasGridColRowFilter) {
                this.startGridCol = startGridCol;
                this.endGridCol = endGridCol;
                this.startGridRow = startGridRow;
                this.endGridRow = endGridRow;
                this.gridColRowFilterSet = (this.startGridCol != this.sensorMinimumColumn || this.endGridCol != this.sensorMaximumColumn || this.startGridRow != this.sensorMinimumRow || this.endGridRow != this.sensorMaximumRow);
            }
            this.sceneListFilterStatus = selected;
            this.userAreaFilterStatus = userAreaFilterStatus;
            this.minQuality = minQuality;
            if (currentSensor.hasDataVersions) {
                this.dataVersion = string;
            }
            this.cloudCoverValue = cloudCover;
        }
        else {
            this.numOfAvailScenes = this.getNumOfAvailScenesBySearchLimit(startYear, endYear, selectedIndex, selectedIndex2, this.ccPercent.getCloudCover(), minQuality, string, selected, userAreaFilterStatus, startGridCol, endGridCol, startGridRow, endGridRow);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        if (actionCommand.equals("Ok") || actionCommand.equals("Apply")) {
            this.processDialogState(true);
            if (this.limitCleared) {
                this.limitSet = false;
            }
            else if (this.startYear != this.sensorStartYear || this.endYear != this.sensorEndYear || this.startMonth != 0 || this.endMonth != 11) {
                this.limitSet = true;
            }
            else {
                this.limitSet = false;
            }
            this.limitCleared = false;
            this.configureForSensor(currentSensor);
            this.applyFilter();
            this.setApplyButtonState();
            if (actionCommand.equals("Ok")) {
                this.setVisible(false);
            }
        }
        else if (actionCommand.equals("Cancel")) {
            this.setVisible(false);
            this.startYearChoice.setSelectedIndex(this.startYear - this.rangeStartYear);
            this.endYearChoice.setSelectedIndex(this.endYear - this.rangeStartYear);
            this.startMonthChoice.setSelectedIndex(this.startMonth);
            this.endMonthChoice.setSelectedIndex(this.endMonth);
            this.ccPercent.setCloudCover(this.md.maxCloudCover);
            this.gridColRowFilterSet = false;
            if (currentSensor.hasGridColRowFilter) {
                this.startGridColChoice.setSelectedIndex(this.startGridCol - this.sensorMinimumColumn);
                this.endGridColChoice.setSelectedIndex(this.endGridCol - this.sensorMinimumColumn);
                this.startGridRowChoice.setSelectedIndex(this.startGridRow - this.sensorMinimumRow);
                this.endGridRowChoice.setSelectedIndex(this.endGridRow - this.sensorMinimumRow);
                this.gridColRowFilterSet = (this.startGridCol != this.sensorMinimumColumn || this.endGridCol != this.sensorMaximumColumn || this.startGridRow != this.sensorMinimumRow || this.endGridRow != this.sensorMaximumRow);
            }
            this.limitCleared = false;
            if (this.startYear != this.sensorStartYear || this.endYear != this.sensorEndYear || this.startMonth != 0 || this.endMonth != 11) {
                this.limitSet = true;
            }
            else {
                this.limitSet = false;
            }
            this.filterToSceneList.setSelected(this.sceneListFilterStatus);
            this.filterToUserArea.setSelected(this.userAreaFilterStatus);
            this.qualityChoice.setSelectedIndex(9 - this.minQuality);
            if (currentSensor.hasDataVersions) {
                if (this.dataVersion.equals("All")) {
                    this.dataVersionChoice.setSelectedIndex(0);
                }
                else {
                    this.dataVersionChoice.setSelectedItem(this.dataVersion);
                }
            }
        }
        else if (actionCommand.equals("Clear")) {
            this.limitCleared = true;
            this.limitSet = false;
            this.startYearChoice.setSelectedIndex(this.sensorStartYear - this.rangeStartYear);
            this.endYearChoice.setSelectedIndex(this.sensorEndYear - this.rangeStartYear);
            this.startMonthChoice.setSelectedIndex(0);
            this.endMonthChoice.setSelectedIndex(11);
            this.ccPercent.setCloudCover(100);
            this.filterToSceneList.setSelected(false);
            this.filterToUserArea.setSelected(false);
            this.qualityChoice.setSelectedIndex(9);
            this.gridColRowFilterSet = false;
            if (currentSensor.hasGridColRowFilter) {
                this.startGridColChoice.setSelectedIndex(this.sensorMinimumColumn - this.sensorMinimumColumn);
                this.endGridColChoice.setSelectedIndex(this.sensorMaximumColumn - this.sensorMinimumColumn);
                this.startGridRowChoice.setSelectedIndex(this.sensorMinimumRow - this.sensorMinimumRow);
                this.endGridRowChoice.setSelectedIndex(this.sensorMaximumRow - this.sensorMinimumRow);
            }
            if (currentSensor.hasDataVersions) {
                this.dataVersionChoice.setSelectedIndex(0);
            }
            this.setApplyButtonState();
            this.updateNumOfAvailScenes();
        }
        else {
            this.updateYear();
        }
    }
    
    public void updateNumOfAvailScenes() {
        this.processDialogState(false);
        if (this.numOfAvailScenes == 1) {
            this.availScenes.setText(this.numOfAvailScenes + " scene available");
        }
        else {
            this.availScenes.setText(this.numOfAvailScenes + " scenes available");
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (this.isVisible()) {
            this.updateTOC();
            this.updateNumOfAvailScenes();
        }
    }
    
    private void updateTOC() {
        final String sensorName = this.applet.sensorMenu.getCurrentSensor().sensorName;
        if (this.savedGridCol != this.md.gridCol || this.savedGridRow != this.md.gridRow || !this.savedSensorName.equals(sensorName) || this.savedSubCol != this.md.getSubCol() || this.savedSubRow != this.md.getSubRow() || this.savedResolution != this.md.pixelSize) {
            this.copyOfMosaicCells = this.md.copyTOC();
            this.savedGridCol = this.md.gridCol;
            this.savedGridRow = this.md.gridRow;
            this.savedSensorName = sensorName;
            this.savedSubCol = this.md.getSubCol();
            this.savedSubRow = this.md.getSubRow();
            this.savedResolution = this.md.pixelSize;
        }
    }
    
    private int getCountOfAvailScenes(final TOC[] array, final int n) {
        int n2 = 0;
        int activeCellIndex = 0;
        int length = array.length;
        if (n == 0) {
            activeCellIndex = this.md.getActiveCellIndex();
            length = activeCellIndex + 1;
        }
        for (int i = activeCellIndex; i < length; ++i) {
            final TOC toc = array[i];
            if (toc.valid) {
                for (int j = 0; j < toc.scenes.length; ++j) {
                    if (toc.scenes[j].visible) {
                        ++n2;
                    }
                }
            }
        }
        return n2;
    }
    
    private int getNumOfAvailScenesBySearchLimit(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final String s, final boolean b, final boolean b2, final int n7, final int n8, final int n9, final int n10) {
        this.md.applyDataLimit(this.copyOfMosaicCells, n, n2, n3, n4, n5, n6, s, b2, b, n7, n8, n9, n10);
        return this.getCountOfAvailScenes(this.copyOfMosaicCells, this.md.getCellsToDisplay());
    }
}
