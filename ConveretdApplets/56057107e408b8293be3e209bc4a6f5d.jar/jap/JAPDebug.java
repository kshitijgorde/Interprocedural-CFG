// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import java.util.Enumeration;
import java.util.Properties;
import anon.util.Util;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import gui.GUIUtils;
import anon.util.JAPMessages;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.Component;
import logging.LogLevel;
import java.util.Date;
import java.awt.event.WindowEvent;
import logging.LogType;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import logging.FileLog;
import gui.dialog.JAPDialog;
import javax.swing.JTextArea;
import logging.Log;
import java.awt.event.ActionListener;
import java.util.Observable;

public final class JAPDebug extends Observable implements ActionListener, Log
{
    private int m_debugType;
    private int m_debugLevel;
    private static JTextArea m_textareaConsole;
    private static JAPDialog m_frameConsole;
    private static boolean m_bConsole;
    private static volatile boolean ms_bFile;
    private static String ms_strFileName;
    private static FileLog ms_FileLog;
    private static JAPDebug debug;
    private static SimpleDateFormat dateFormatter;
    private WindowAdapter m_winAdapter;
    static /* synthetic */ Class class$java$lang$Runtime;
    
    private JAPDebug() {
        this.m_debugType = LogType.ALL;
        this.m_debugLevel = 7;
        this.m_debugType = LogType.ALL;
        this.m_debugLevel = 7;
        JAPDebug.m_bConsole = false;
        JAPDebug.ms_bFile = false;
        JAPDebug.ms_strFileName = null;
        this.m_winAdapter = new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                synchronized (JAPDebug.this) {
                    JAPDebug.m_bConsole = false;
                    JAPDebug.this.setChanged();
                    JAPDebug.this.notifyObservers();
                }
            }
            
            public void windowClosed(final WindowEvent windowEvent) {
                synchronized (JAPDebug.this) {
                    JAPDebug.m_bConsole = false;
                    JAPDebug.this.setChanged();
                    JAPDebug.this.notifyObservers();
                }
            }
        };
    }
    
    public void finalize() {
        JAPDebug.ms_bFile = false;
        try {
            super.finalize();
        }
        catch (Throwable t) {}
    }
    
    public static JAPDebug getInstance() {
        if (JAPDebug.debug == null) {
            JAPDebug.debug = new JAPDebug();
        }
        return JAPDebug.debug;
    }
    
    public void log(final int n, final int n2, final String s) {
        try {
            if (n <= this.m_debugLevel && (this.m_debugType & n2) != 0x0) {
                synchronized (this) {
                    final String string = "[" + JAPDebug.dateFormatter.format(new Date()) + LogLevel.getLevelName(n) + "] " + s + "\n";
                    if (!JAPDebug.m_bConsole) {
                        System.err.print(string);
                    }
                    else {
                        JAPDebug.m_textareaConsole.append(string);
                        JAPDebug.m_textareaConsole.setCaretPosition(JAPDebug.m_textareaConsole.getText().length());
                    }
                    if (JAPDebug.ms_bFile) {
                        JAPDebug.ms_FileLog.log(n, n2, s);
                    }
                }
            }
        }
        catch (Throwable t) {}
    }
    
    public void setLogType(final int n) {
        this.m_debugType = n;
        if (JAPDebug.ms_bFile) {
            JAPDebug.ms_FileLog.setLogType(n);
        }
    }
    
    public int getLogType() {
        return this.m_debugType;
    }
    
    public void setLogLevel(final int n) {
        if (n < 0 || n > 7) {
            return;
        }
        this.m_debugLevel = n;
        if (JAPDebug.ms_bFile) {
            JAPDebug.ms_FileLog.setLogLevel(n);
        }
    }
    
    public int getLogLevel() {
        if (JAPDebug.debug == null) {
            getInstance();
        }
        return JAPDebug.debug.m_debugLevel;
    }
    
    public static void showConsole(final boolean b, final Component component) {
        JAPDebug.debug.internal_showConsole(b, component);
    }
    
    public static void setLogToFile(final String ms_strFileName) {
        if (ms_strFileName == null || ms_strFileName.trim().equals("")) {
            JAPDebug.ms_bFile = false;
            JAPDebug.ms_FileLog = null;
        }
        else {
            (JAPDebug.ms_FileLog = new FileLog(ms_strFileName, 10000000, 2)).setLogLevel(getInstance().m_debugLevel);
            JAPDebug.ms_FileLog.setLogType(getInstance().m_debugType);
            JAPDebug.ms_bFile = true;
        }
        JAPDebug.ms_strFileName = ms_strFileName;
    }
    
    public static boolean isShowConsole() {
        return JAPDebug.m_bConsole;
    }
    
    public static boolean isLogToFile() {
        return JAPDebug.ms_bFile;
    }
    
    public static String getLogFilename() {
        return JAPDebug.ms_strFileName;
    }
    
    public void internal_showConsole(final boolean b, final Component component) {
        if (!b && JAPDebug.m_bConsole) {
            JAPDebug.m_frameConsole.dispose();
            JAPDebug.m_frameConsole.removeWindowListener(this.m_winAdapter);
            JAPDebug.m_textareaConsole = null;
            JAPDebug.m_frameConsole = null;
            JAPDebug.m_bConsole = false;
        }
        else if (b && !JAPDebug.m_bConsole) {
            JAPDebug.m_frameConsole = new JAPDialog(component, "Debug-Console", false);
            (JAPDebug.m_textareaConsole = new JTextArea(null, 20, 30)).setEditable(false);
            final Font decode = Font.decode("Courier");
            if (decode != null) {
                JAPDebug.m_textareaConsole.setFont(decode);
            }
            final JPanel panel = new JPanel();
            final JButton button = new JButton(JAPMessages.getString("bttnSaveAs") + "...", GUIUtils.loadImageIcon("saveicon.gif", true));
            button.setActionCommand("saveas");
            button.addActionListener(JAPDebug.debug);
            final JButton button2 = new JButton(JAPMessages.getString("bttnCopy"), GUIUtils.loadImageIcon("copyicon.gif", true));
            button2.setActionCommand("copy");
            button2.addActionListener(JAPDebug.debug);
            final JButton button3 = new JButton(JAPMessages.getString("bttnInsertConfig"), GUIUtils.loadImageIcon("copyintoicon.gif", true));
            button3.setActionCommand("insertConfig");
            button3.addActionListener(JAPDebug.debug);
            final JButton button4 = new JButton(JAPMessages.getString("bttnDelete"), GUIUtils.loadImageIcon("deleteicon.gif", true));
            button4.setActionCommand("delete");
            button4.addActionListener(JAPDebug.debug);
            final JButton button5 = new JButton(JAPMessages.getString("bttnClose"), GUIUtils.loadImageIcon("exiticon.gif", true));
            button5.setActionCommand("close");
            button5.addActionListener(JAPDebug.debug);
            final GridBagLayout layout = new GridBagLayout();
            panel.setLayout(layout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 0.0;
            layout.setConstraints(button, gridBagConstraints);
            panel.add(button);
            gridBagConstraints.gridx = 2;
            layout.setConstraints(button2, gridBagConstraints);
            panel.add(button2);
            gridBagConstraints.gridx = 3;
            layout.setConstraints(button3, gridBagConstraints);
            panel.add(button3);
            gridBagConstraints.gridx = 4;
            layout.setConstraints(button4, gridBagConstraints);
            panel.add(button4);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.anchor = 13;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridx = 5;
            layout.setConstraints(button5, gridBagConstraints);
            panel.add(button5);
            JAPDebug.m_frameConsole.getContentPane().add("North", panel);
            JAPDebug.m_frameConsole.getContentPane().add("Center", new JScrollPane(JAPDebug.m_textareaConsole));
            JAPDebug.m_frameConsole.addWindowListener(this.m_winAdapter);
            JAPDebug.m_frameConsole.pack();
            JAPDebug.m_frameConsole.moveToUpRightCorner();
            JAPDebug.m_frameConsole.setVisible(true);
            JAPDebug.m_bConsole = true;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("saveas")) {
            this.saveLog();
        }
        else if (actionEvent.getActionCommand().equals("copy")) {
            JAPDebug.m_textareaConsole.selectAll();
            JAPDebug.m_textareaConsole.copy();
            JAPDebug.m_textareaConsole.moveCaretPosition(JAPDebug.m_textareaConsole.getCaretPosition());
        }
        else if (actionEvent.getActionCommand().equals("delete")) {
            JAPDebug.m_textareaConsole.setText("");
        }
        else if (actionEvent.getActionCommand().equals("insertConfig")) {
            try {
                final Properties properties = System.getProperties();
                final Enumeration<?> propertyNames = properties.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    final String s = (String)propertyNames.nextElement();
                    JAPDebug.m_textareaConsole.append(s + ": " + properties.getProperty(s) + "\n");
                }
            }
            catch (Exception ex) {}
            JAPDebug.m_textareaConsole.append("TotalMemory: " + Util.formatBytesValueWithUnit(Runtime.getRuntime().totalMemory()) + "\n");
            try {
                JAPDebug.m_textareaConsole.append("MaxMemory: " + Util.formatBytesValueWithUnit((long)((JAPDebug.class$java$lang$Runtime == null) ? (JAPDebug.class$java$lang$Runtime = class$("java.lang.Runtime")) : JAPDebug.class$java$lang$Runtime).getMethod("maxMemory", (Class[])new Class[0]).invoke(Runtime.getRuntime(), new Object[0])) + "\n");
            }
            catch (Exception ex2) {}
            JAPDebug.m_textareaConsole.append("FreeMemory: " + Util.formatBytesValueWithUnit(Runtime.getRuntime().freeMemory()) + "\n");
            JAPDebug.m_textareaConsole.append("\n");
            JAPDebug.m_textareaConsole.append(JAPModel.getInstance().toString());
        }
        else {
            JAPDebug.m_frameConsole.dispose();
            JAPDebug.m_bConsole = false;
        }
    }
    
    private void saveLog() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(1);
        if (fileChooser.showDialog(JAPDebug.m_frameConsole.getRootPane(), null) == 0) {
            final File selectedFile = fileChooser.getSelectedFile();
            try {
                final FileWriter fileWriter = new FileWriter(selectedFile);
                JAPDebug.m_textareaConsole.write(fileWriter);
                fileWriter.flush();
                fileWriter.close();
            }
            catch (Exception ex) {
                JAPDialog.showErrorDialog(JAPDebug.m_frameConsole, JAPMessages.getString("errWritingLog"), LogType.MISC);
            }
        }
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
        JAPDebug.m_bConsole = false;
        JAPDebug.ms_bFile = false;
        JAPDebug.ms_strFileName = null;
        JAPDebug.ms_FileLog = null;
        JAPDebug.dateFormatter = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss, ");
    }
}
