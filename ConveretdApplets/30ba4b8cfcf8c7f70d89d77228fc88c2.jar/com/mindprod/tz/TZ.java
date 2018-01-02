// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.tz;

import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import com.mindprod.common15.FontFactory;
import java.util.GregorianCalendar;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;
import java.awt.Component;
import javax.swing.table.TableModel;
import java.util.Comparator;
import java.util.Arrays;
import java.util.TimeZone;
import com.mindprod.common13.CMPAboutJBox;
import com.mindprod.common13.JEButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.util.Date;
import java.awt.Color;
import javax.swing.JApplet;

public final class TZ extends JApplet
{
    private static final int APPLET_HEIGHT = 645;
    private static final int APPLET_WIDTH = 822;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2004-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-06-13";
    private static final String TITLE_STRING = "TimeZones";
    private static final String VERSION_STRING = "1.9";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Date aSummerDay;
    private static final Date aWinterDay;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final SimpleDateFormat localTimeDisplay;
    private static final SimpleDateFormat utcTimeDisplay;
    private static final int[] PREFERRED_COLUMN_WIDTH;
    private JButton about;
    private JLabel instructions;
    private JLabel localTimeLabel;
    private JLabel localTZLabel;
    private JLabel supportedLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel utcTimeLabel;
    private JScrollPane scroller;
    private JTable timeZoneTable;
    private JTextField localTimeField;
    private JTextField localTZField;
    private JTextField utcTimeField;
    private Timer timer;
    
    @Override
    public void destroy() {
        this.about = null;
        this.instructions = null;
        this.localTimeField = null;
        this.localTimeLabel = null;
        this.localTZField = null;
        this.localTZLabel = null;
        this.scroller = null;
        this.supportedLabel = null;
        this.timer = null;
        this.timeZoneTable = null;
        this.title2 = null;
        this.title = null;
        this.utcTimeField = null;
        this.utcTimeLabel = null;
    }
    
    @Override
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(TZ.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.buildComponents();
        this.layoutComponents(contentPane);
        this.validate();
    }
    
    @Override
    public void start() {
        (this.timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                TZ.this.localTimeField.setText(TZ.localTimeDisplay.format(new Date()));
                TZ.this.utcTimeField.setText(TZ.utcTimeDisplay.format(new Date()));
            }
        })).setRepeats(true);
        this.timer.start();
    }
    
    @Override
    public void stop() {
        this.timer.stop();
        this.timer = null;
    }
    
    private void buildComponents() {
        (this.title = new JLabel("TimeZones 1.9")).setFont(TZ.FONT_FOR_TITLE);
        this.title.setForeground(TZ.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2008-06-13 build:9411")).setFont(TZ.FONT_FOR_TITLE2);
        this.title2.setForeground(TZ.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About TimeZones 1.9");
        this.about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("TimeZones", "1.9", "Display all Timezones that Java supports on this machine.", "", "freeware", "2008-06-13", 2004, "Roedy Green", "TZ", "1.6");
            }
        });
        (this.utcTimeLabel = new JLabel("UTC")).setForeground(TZ.FOREGROUND_FOR_LABEL);
        (this.utcTimeField = new JTextField(TZ.localTimeDisplay.format(new Date()))).setEditable(false);
        (this.localTimeLabel = new JLabel("local time")).setForeground(TZ.FOREGROUND_FOR_LABEL);
        (this.localTimeField = new JTextField(TZ.localTimeDisplay.format(new Date()))).setEditable(false);
        (this.localTZLabel = new JLabel("local TZ")).setForeground(TZ.FOREGROUND_FOR_LABEL);
        (this.localTZField = new JTextField(TimeZone.getDefault().getDisplayName())).setEditable(false);
        (this.supportedLabel = new JLabel("Supported TimeZones", 0)).setForeground(TZ.FOREGROUND_FOR_LABEL);
        final String[] allTimeZoneIDs = TimeZone.getAvailableIDs();
        Arrays.sort(allTimeZoneIDs, new ByOffset());
        final TZTableModel model = new TZTableModel(allTimeZoneIDs);
        this.timeZoneTable = new JTable(model);
        final TableColumnModel tableColumnModel = this.timeZoneTable.getColumnModel();
        tableColumnModel.setColumnMargin(5);
        final TableCellRenderer headerRenderer = new HeaderRenderer();
        for (int i = 0; i < 4; ++i) {
            final TableColumn tableColumn = tableColumnModel.getColumn(i);
            tableColumn.setHeaderRenderer(headerRenderer);
            tableColumn.setPreferredWidth(TZ.PREFERRED_COLUMN_WIDTH[i]);
        }
        this.scroller = new JScrollPane(this.timeZoneTable, 22, 30);
        this.timeZoneTable.setRowSorter((RowSorter<? extends TableModel>)new TableRowSorter(model));
        (this.instructions = new JLabel("Click column heading to sort.", 0)).setForeground(TZ.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        contentPane.add(this.utcTimeLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.utcTimeField, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, 17, 2, new Insets(10, 5, 0, 10), 0, 0));
        contentPane.add(this.localTimeLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.localTimeField, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0, 17, 2, new Insets(10, 5, 0, 10), 0, 0));
        contentPane.add(this.localTZLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.localTZField, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0, 17, 2, new Insets(10, 5, 0, 10), 0, 0));
        contentPane.add(this.supportedLabel, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0, 10, 1, new Insets(10, 10, 0, 10), 0, 0));
        contentPane.add(this.scroller, new GridBagConstraints(0, 5, 4, 1, 1.0, 1.0, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 6, 4, 1, 0.0, 0.0, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new TZ(), "TimeZones 1.9", 822, 645);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(13948159);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        aSummerDay = new GregorianCalendar(2007, 5, 21).getTime();
        aWinterDay = new GregorianCalendar(2007, 11, 21).getTime();
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        localTimeDisplay = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa zz : zzzzzz EEEE");
        utcTimeDisplay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zz : zzzzzz EEEE");
        PREFERRED_COLUMN_WIDTH = new int[] { 45, 45, 215, 330 };
        TZ.utcTimeDisplay.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    private static class ByOffset implements Comparator<String>
    {
        @Override
        public final int compare(final String a, final String b) {
            final int diff = TimeZone.getTimeZone(a).getRawOffset() - TimeZone.getTimeZone(b).getRawOffset();
            if (diff != 0) {
                return diff;
            }
            return a.compareTo(b);
        }
    }
    
    static final class HeaderRenderer implements TableCellRenderer
    {
        private static final JLabel headerTemplate;
        
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            HeaderRenderer.headerTemplate.setText(value.toString());
            return HeaderRenderer.headerTemplate;
        }
        
        static {
            (headerTemplate = new JLabel("", 0)).setForeground(TZ.FOREGROUND_FOR_LABEL);
            HeaderRenderer.headerTemplate.setBackground(TZ.BACKGROUND_FOR_APPLET);
            HeaderRenderer.headerTemplate.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            HeaderRenderer.headerTemplate.setOpaque(true);
        }
    }
    
    static final class TZTableModel extends AbstractTableModel implements TableModel
    {
        static final String[] columnNames;
        private final String[] timeZoneIDs;
        
        @Override
        public Class<?> getColumnClass(final int columnIndex) {
            switch (columnIndex) {
                case 0:
                case 1: {
                    return Double.class;
                }
                case 2:
                case 3: {
                    return String.class;
                }
                default: {
                    throw new ArrayIndexOutOfBoundsException("column index out of range in get ColumnClass");
                }
            }
        }
        
        @Override
        public int getColumnCount() {
            return 4;
        }
        
        @Override
        public String getColumnName(final int column) {
            return TZTableModel.columnNames[column];
        }
        
        @Override
        public int getRowCount() {
            return this.timeZoneIDs.length;
        }
        
        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            final String timeZoneID = this.timeZoneIDs[rowIndex];
            final TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
            switch (columnIndex) {
                case 0: {
                    return timeZone.getRawOffset() / 3600000.0;
                }
                case 1: {
                    final double raw = timeZone.getRawOffset() / 3600000.0;
                    final double summer = timeZone.getOffset(TZ.aSummerDay.getTime()) / 3600000.0;
                    final double winter = timeZone.getOffset(TZ.aWinterDay.getTime()) / 3600000.0;
                    return (raw == summer) ? winter : summer;
                }
                case 2: {
                    return timeZoneID;
                }
                case 3: {
                    return timeZone.getDisplayName();
                }
                default: {
                    throw new ArrayIndexOutOfBoundsException("col index out of range in get getValueAt");
                }
            }
        }
        
        TZTableModel(final String[] timeZoneIDs) {
            this.timeZoneIDs = timeZoneIDs;
        }
        
        static {
            columnNames = new String[] { "Offset", "DST", "ID", "Display Name" };
        }
    }
}
