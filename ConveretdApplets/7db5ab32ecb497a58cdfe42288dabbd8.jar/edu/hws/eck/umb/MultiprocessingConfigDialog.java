// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import edu.hws.eck.umb.util.Util;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import edu.hws.eck.umb.util.I18n;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import edu.hws.eck.umb.comp.TaskManager;
import javax.swing.JDialog;

public class MultiprocessingConfigDialog extends JDialog
{
    private MandelbrotDisplay display;
    private TaskManager taskManager;
    private JCheckBox defaultProcessCountCheckBox;
    private JCheckBox enableNetworkCheckBox;
    private JTextField processCountInput;
    private JButton okButton;
    private JButton applyButton;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;
    private JList networkWorkerList;
    private NetworkWorkerListModel networkWorkerModel;
    private Timer updatetimer;
    
    public static void showDialog(final MandelbrotDisplay mandelbrotDisplay) {
        Container container;
        for (container = mandelbrotDisplay.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        new MultiprocessingConfigDialog((Frame)container, mandelbrotDisplay).setVisible(true);
    }
    
    private MultiprocessingConfigDialog(final Frame frame, final MandelbrotDisplay display) {
        super(frame, I18n.tr("multiprocessingConfigDialog.title", new Object[0]), false);
        this.display = display;
        this.taskManager = display.getTaskManager();
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(3, 3));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(I18n.tr("multiprocessingConfigDialog.paneTitle.LocalConfig", new Object[0])), BorderFactory.createEmptyBorder(3, 5, 3, 3)));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(1, 5, 5));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(5, 5));
        panel3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(I18n.tr("multiprocessingConfigDialog.paneTitle.NetworkConfig", new Object[0])), BorderFactory.createEmptyBorder(3, 5, 3, 3)));
        contentPane.add(panel, "North");
        contentPane.add(panel2, "Center");
        contentPane.add(panel3, "South");
        final ButtonHandler buttonHandler = new ButtonHandler();
        (this.okButton = new JButton(I18n.tr("buttonName.OK", new Object[0]))).addActionListener(buttonHandler);
        (this.cancelButton = new JButton(I18n.tr("buttonName.Cancel", new Object[0]))).addActionListener(buttonHandler);
        panel2.add(this.cancelButton);
        panel2.add(this.okButton);
        (this.addButton = new JButton(I18n.tr("multiprocessingConfigDialog.buttonName.AddNetworkHost", new Object[0]))).addActionListener(buttonHandler);
        (this.deleteButton = new JButton(I18n.tr("multiprocessingConfigDialog.buttonName.DeleteSelected", new Object[0]))).addActionListener(buttonHandler);
        (this.applyButton = new JButton(I18n.tr("multiprocessingConfigDialog.buttonName.ApplyNetworkConfig", new Object[0]))).addActionListener(buttonHandler);
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final int threadPoolSize = this.taskManager.getThreadPoolSize();
        final boolean selected = availableProcessors == threadPoolSize;
        (this.defaultProcessCountCheckBox = new JCheckBox(I18n.tr("multiprocessingConfigDialog.buttonName.UseDefaultProcessCount", "" + availableProcessors))).addActionListener(buttonHandler);
        this.defaultProcessCountCheckBox.setSelected(selected);
        (this.processCountInput = new JTextField("" + threadPoolSize, 3)).setEditable(!selected);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(4, 4));
        panel4.add(this.processCountInput, "East");
        panel4.add(new JLabel(I18n.tr("multiprocessingConfigDialog.buttonName.NumberOfProcesses", new Object[0]) + ":", 4), "Center");
        panel.add(new JLabel(I18n.tr("multiprocessingConfigDialog.buttonName.DefaultProcessCountInfo", new Object[0])), "North");
        panel.add(this.defaultProcessCountCheckBox, "Center");
        panel.add(panel4, "South");
        (this.enableNetworkCheckBox = new JCheckBox(I18n.tr("multiprocessingConfigDialog.buttonName.EnableNetworking", new Object[0]))).setSelected(this.taskManager.getNetworkingEnabled());
        this.enableNetworkCheckBox.addActionListener(buttonHandler);
        final boolean networkingEnabled = this.taskManager.getNetworkingEnabled();
        this.enableNetworkCheckBox.setSelected(networkingEnabled);
        TaskManager.NetworkWorkerInfo[] array;
        if (networkingEnabled) {
            array = this.taskManager.getAllNetworkWorkerInfo();
        }
        else {
            array = this.getNetworkPrefs();
        }
        this.networkWorkerModel = new NetworkWorkerListModel(array);
        (this.networkWorkerList = new JList(this.networkWorkerModel)).setVisibleRowCount(7);
        this.networkWorkerList.setEnabled(networkingEnabled);
        final JScrollPane scrollPane = new JScrollPane(this.networkWorkerList);
        scrollPane.setVerticalScrollBarPolicy(22);
        panel3.add(scrollPane, "Center");
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout(5, 5));
        panel5.add(new JLabel(I18n.tr("multiprocessingConfigDialog.buttonName.NetworkConfigInfo", new Object[0])), "North");
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout(5, 5));
        panel6.add(this.enableNetworkCheckBox, "West");
        panel6.add(new JLabel(), "Center");
        panel6.add(this.applyButton, "East");
        panel5.add(panel6, "Center");
        panel3.add(panel5, "North");
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(1, 5, 5));
        panel7.add(this.addButton);
        panel7.add(this.deleteButton);
        this.addButton.setEnabled(networkingEnabled);
        this.deleteButton.setEnabled(false);
        this.applyButton.setEnabled(false);
        panel3.add(panel7, "South");
        this.networkWorkerList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                MultiprocessingConfigDialog.this.deleteButton.setEnabled(MultiprocessingConfigDialog.this.enableNetworkCheckBox.isSelected() && MultiprocessingConfigDialog.this.networkWorkerList.getSelectedIndices().length > 0);
            }
        });
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                if (!MultiprocessingConfigDialog.this.applyButton.isEnabled()) {
                    MultiprocessingConfigDialog.this.dispose();
                }
                else {
                    final int showConfirmDialog = JOptionPane.showConfirmDialog(MultiprocessingConfigDialog.this, "You have unsaved network configuration changes.\nDo you want to save them?", "Save Network Config?", 1);
                    if (showConfirmDialog == 0) {
                        MultiprocessingConfigDialog.this.doApply();
                    }
                    if (showConfirmDialog != 2) {
                        MultiprocessingConfigDialog.this.dispose();
                    }
                }
            }
        });
        this.pack();
        this.setResizable(false);
        if (frame != null) {
            this.setLocation(frame.getX() + 20, frame.getY() + 50);
        }
        contentPane.getInputMap(2).put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        contentPane.getActionMap().put("cancel", new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MultiprocessingConfigDialog.this.cancelButton.doClick();
            }
        });
        if (networkingEnabled) {
            this.startUpdateTimer(5000);
        }
    }
    
    private void doApply() {
        if (this.enableNetworkCheckBox.isSelected()) {
            this.taskManager.setNetworkingEnabled(true);
            final TaskManager.NetworkWorkerInfo[] allNetworkWorkerInfo = this.taskManager.getAllNetworkWorkerInfo();
            final ArrayList<TaskManager.NetworkWorkerInfo> items = this.networkWorkerModel.items;
            for (final TaskManager.NetworkWorkerInfo networkWorkerInfo : allNetworkWorkerInfo) {
                boolean b = false;
                final Iterator<TaskManager.NetworkWorkerInfo> iterator = items.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().workerID == networkWorkerInfo.workerID) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    this.taskManager.removeNetworkWorker(networkWorkerInfo.workerID);
                }
            }
            final ArrayList<TaskManager.NetworkWorkerInfo> data = new ArrayList<TaskManager.NetworkWorkerInfo>();
            for (final TaskManager.NetworkWorkerInfo networkWorkerInfo2 : items) {
                if (networkWorkerInfo2.workerID == -1) {
                    data.add(this.taskManager.getNetworkWorkerInfo(this.taskManager.addNetworkWorker(networkWorkerInfo2.host, networkWorkerInfo2.port)));
                }
                else {
                    final TaskManager.NetworkWorkerInfo networkWorkerInfo3 = this.taskManager.getNetworkWorkerInfo(networkWorkerInfo2.workerID);
                    if (networkWorkerInfo3 == null) {
                        data.add(this.taskManager.getNetworkWorkerInfo(this.taskManager.addNetworkWorker(networkWorkerInfo2.host, networkWorkerInfo2.port)));
                    }
                    else if (networkWorkerInfo3.status != TaskManager.NET_STATUS_CONNECTED) {
                        this.taskManager.removeNetworkWorker(networkWorkerInfo2.workerID);
                        data.add(this.taskManager.getNetworkWorkerInfo(this.taskManager.addNetworkWorker(networkWorkerInfo2.host, networkWorkerInfo2.port)));
                    }
                    else {
                        data.add(networkWorkerInfo3);
                    }
                }
            }
            this.networkWorkerModel.setData(data);
            this.startUpdateTimer(500);
        }
        else {
            if (this.updatetimer != null) {
                this.updatetimer.stop();
                this.updatetimer = null;
            }
            this.taskManager.setNetworkingEnabled(false);
            this.networkWorkerModel.inactivateAll();
        }
        this.applyButton.setEnabled(false);
        this.saveNetworkPrefs(this.networkWorkerModel.items);
    }
    
    private void startUpdateTimer(final int initialDelay) {
        if (this.updatetimer != null) {
            this.updatetimer.stop();
        }
        (this.updatetimer = new Timer(5000, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (!MultiprocessingConfigDialog.this.taskManager.getNetworkingEnabled()) {
                    return;
                }
                final ArrayList<TaskManager.NetworkWorkerInfo> items = MultiprocessingConfigDialog.this.networkWorkerModel.items;
                final ArrayList<TaskManager.NetworkWorkerInfo> data = new ArrayList<TaskManager.NetworkWorkerInfo>();
                boolean b = false;
                for (final TaskManager.NetworkWorkerInfo networkWorkerInfo : items) {
                    if (networkWorkerInfo.workerID == -1) {
                        data.add(networkWorkerInfo);
                    }
                    else {
                        final TaskManager.NetworkWorkerInfo networkWorkerInfo2 = MultiprocessingConfigDialog.this.taskManager.getNetworkWorkerInfo(networkWorkerInfo.workerID);
                        if (networkWorkerInfo2 == null) {
                            b = true;
                        }
                        else {
                            data.add(networkWorkerInfo2);
                            if (networkWorkerInfo2.status == networkWorkerInfo.status && networkWorkerInfo2.tasksDone == networkWorkerInfo.tasksDone) {
                                continue;
                            }
                            b = true;
                        }
                    }
                }
                if (b) {
                    MultiprocessingConfigDialog.this.networkWorkerModel.setData(data);
                }
            }
        })).setInitialDelay(initialDelay);
        this.updatetimer.start();
    }
    
    private void saveNetworkPrefs(final ArrayList<TaskManager.NetworkWorkerInfo> list) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); ++i) {
            final TaskManager.NetworkWorkerInfo networkWorkerInfo = list.get(i);
            sb.append(networkWorkerInfo.host);
            sb.append(':');
            sb.append(networkWorkerInfo.port);
            if (i < list.size() - 1) {
                sb.append(';');
            }
        }
        Util.setPref("MultiprocessingConfigDialog.networkWorkerList", sb.toString());
    }
    
    private TaskManager.NetworkWorkerInfo[] getNetworkPrefs() {
        final String pref = Util.getPref("MultiprocessingConfigDialog.networkWorkerList");
        if (pref == null) {
            return null;
        }
        final ArrayList<TaskManager.NetworkWorkerInfo> list = new ArrayList<TaskManager.NetworkWorkerInfo>();
        try {
            final String[] split = pref.split(";");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String[] split2 = split[i].split(":");
                final String trim = split2[0].trim();
                final int int1 = Integer.parseInt(split2[1]);
                if (trim.length() == 0 || int1 <= 0) {
                    return null;
                }
                list.add(new TaskManager.NetworkWorkerInfo(trim, int1, TaskManager.NET_STATUS_INACTIVE, 0, -1));
            }
        }
        catch (Exception ex) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        final TaskManager.NetworkWorkerInfo[] array = new TaskManager.NetworkWorkerInfo[list.size()];
        for (int j = 0; j < array.length; ++j) {
            array[j] = list.get(j);
        }
        return array;
    }
    
    private String makeListItem(final TaskManager.NetworkWorkerInfo networkWorkerInfo) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        if (networkWorkerInfo.status == TaskManager.NET_STATUS_INACTIVE) {
            sb.append("<font color='#AAAAAA'>");
        }
        sb.append(networkWorkerInfo.host);
        sb.append(':');
        sb.append(networkWorkerInfo.port);
        sb.append(' ');
        if (networkWorkerInfo.status == TaskManager.NET_STATUS_INACTIVE) {
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.INACTIVE", new Object[0]));
            sb.append("</font>");
        }
        else if (networkWorkerInfo.status < 0) {
            sb.append("<font color=red>");
            if (networkWorkerInfo.status == TaskManager.NET_STATUS_ERROR_CANT_CONNECT) {
                sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.CANTCONNECT", new Object[0]));
            }
            else {
                sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.ERROR", new Object[0]));
            }
            sb.append("</font>");
        }
        else if (networkWorkerInfo.status == TaskManager.NET_STATUS_CONNECTED) {
            sb.append("<font color='#00BB00'>");
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.CONNECTED", new Object[0]));
            sb.append("</font>");
        }
        else if (networkWorkerInfo.status == TaskManager.NET_STATUS_CONNECTING) {
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.CONNECTING", new Object[0]));
        }
        else if (networkWorkerInfo.status == TaskManager.NET_STATUS_CLOSING) {
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.CLOSING", new Object[0]));
        }
        else if (networkWorkerInfo.status == TaskManager.NET_STATUS_CLOSED) {
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.CLOSED", new Object[0]));
        }
        if (networkWorkerInfo.tasksDone > 0) {
            sb.append(' ');
            sb.append(I18n.tr("multiprocessingConfigDialog.netStatus.tasksDone", "" + networkWorkerInfo.tasksDone));
        }
        sb.append("</html>");
        return sb.toString();
    }
    
    TaskManager.NetworkWorkerInfo showAddDialog() {
        final JPanel panel = new JPanel();
        final int n = 17071;
        final JTextField textField = new JTextField(30);
        final JTextField textField2 = new JTextField("" + n, 5);
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel(I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.info", "" + n)), "North");
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(0, 5, 5));
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(new JLabel(I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.HostName", new Object[0]) + ": "));
        panel2.add(textField);
        panel.add(panel2, "Center");
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(0, 5, 5));
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(new JLabel(I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.PortNumber", new Object[0]) + ": "));
        panel3.add(textField2);
        panel.add(panel3, "South");
        if (JOptionPane.showConfirmDialog(this.display, panel, I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.title", new Object[0]), 2) != 0) {
            return null;
        }
        final String trim = textField.getText().trim();
        if (trim.length() == 0) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.error.EmptyHost", new Object[0]));
            return null;
        }
        if (trim.indexOf(59) >= 0) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.error.NoSemicolonInHost", new Object[0]));
            return null;
        }
        if (trim.indexOf(58) >= 0) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.error.NoColonInHost", new Object[0]));
            return null;
        }
        int int1;
        try {
            int1 = Integer.parseInt(textField2.getText().trim());
            if (int1 <= 0) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.display, I18n.tr("multiprocessingConfigdialog.addNetworkHostDialog.error.BadPortNumber", new Object[0]));
            return null;
        }
        return new TaskManager.NetworkWorkerInfo(trim, int1, TaskManager.NET_STATUS_INACTIVE, 0, -1);
    }
    
    private class NetworkWorkerListModel extends AbstractListModel
    {
        ArrayList<TaskManager.NetworkWorkerInfo> items;
        
        NetworkWorkerListModel(final TaskManager.NetworkWorkerInfo[] array) {
            this.items = new ArrayList<TaskManager.NetworkWorkerInfo>();
            if (array != null) {
                for (int length = array.length, i = 0; i < length; ++i) {
                    this.items.add(array[i]);
                }
            }
        }
        
        public void add(final TaskManager.NetworkWorkerInfo networkWorkerInfo) {
            this.items.add(networkWorkerInfo);
            this.fireIntervalAdded(this, this.items.size() - 1, this.items.size() - 1);
        }
        
        public void remove(final int n) {
            this.items.remove(n);
            this.fireIntervalRemoved(this, n, n);
        }
        
        public void inactivateAll() {
            if (this.items.size() == 0) {
                return;
            }
            for (int i = 0; i < this.items.size(); ++i) {
                final TaskManager.NetworkWorkerInfo networkWorkerInfo = this.items.get(i);
                this.items.set(i, new TaskManager.NetworkWorkerInfo(networkWorkerInfo.host, networkWorkerInfo.port, TaskManager.NET_STATUS_INACTIVE, 0, -1));
            }
            this.fireContentsChanged(this, 0, this.items.size() - 1);
        }
        
        public Object getElementAt(final int n) {
            return MultiprocessingConfigDialog.this.makeListItem(this.items.get(n));
        }
        
        public int getSize() {
            return this.items.size();
        }
        
        public void setData(final ArrayList<TaskManager.NetworkWorkerInfo> items) {
            final int size = this.items.size();
            if (size > 0) {
                this.items.clear();
                this.fireIntervalRemoved(this, 0, size - 1);
            }
            this.items = items;
            if (this.items.size() > 0) {
                this.fireIntervalAdded(this, 0, this.items.size() - 1);
            }
        }
    }
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == MultiprocessingConfigDialog.this.defaultProcessCountCheckBox) {
                if (MultiprocessingConfigDialog.this.defaultProcessCountCheckBox.isSelected()) {
                    final int availableProcessors = Runtime.getRuntime().availableProcessors();
                    MultiprocessingConfigDialog.this.processCountInput.setEditable(false);
                    MultiprocessingConfigDialog.this.processCountInput.setText("" + availableProcessors);
                }
                else {
                    MultiprocessingConfigDialog.this.processCountInput.setEditable(true);
                    MultiprocessingConfigDialog.this.processCountInput.selectAll();
                    MultiprocessingConfigDialog.this.processCountInput.requestFocus();
                }
            }
            else if (source == MultiprocessingConfigDialog.this.enableNetworkCheckBox) {
                if (MultiprocessingConfigDialog.this.enableNetworkCheckBox.isSelected()) {
                    MultiprocessingConfigDialog.this.networkWorkerList.setEnabled(true);
                    MultiprocessingConfigDialog.this.addButton.setEnabled(true);
                    MultiprocessingConfigDialog.this.applyButton.setEnabled(!MultiprocessingConfigDialog.this.taskManager.getNetworkingEnabled() && MultiprocessingConfigDialog.this.networkWorkerModel.getSize() > 0);
                }
                else {
                    MultiprocessingConfigDialog.this.networkWorkerList.setEnabled(false);
                    MultiprocessingConfigDialog.this.networkWorkerList.clearSelection();
                    MultiprocessingConfigDialog.this.applyButton.setEnabled(MultiprocessingConfigDialog.this.taskManager.getNetworkingEnabled());
                    MultiprocessingConfigDialog.this.addButton.setEnabled(false);
                    MultiprocessingConfigDialog.this.deleteButton.setEnabled(false);
                }
            }
            else if (source == MultiprocessingConfigDialog.this.deleteButton) {
                final int[] selectedIndices = MultiprocessingConfigDialog.this.networkWorkerList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; --i) {
                    MultiprocessingConfigDialog.this.networkWorkerModel.remove(selectedIndices[i]);
                }
                if (selectedIndices.length > 0) {
                    MultiprocessingConfigDialog.this.applyButton.setEnabled(true);
                }
            }
            else if (source == MultiprocessingConfigDialog.this.applyButton) {
                MultiprocessingConfigDialog.this.doApply();
            }
            else if (source == MultiprocessingConfigDialog.this.addButton) {
                final TaskManager.NetworkWorkerInfo showAddDialog = MultiprocessingConfigDialog.this.showAddDialog();
                if (showAddDialog != null) {
                    MultiprocessingConfigDialog.this.networkWorkerModel.add(showAddDialog);
                    MultiprocessingConfigDialog.this.applyButton.setEnabled(true);
                }
            }
            else if (source == MultiprocessingConfigDialog.this.cancelButton) {
                MultiprocessingConfigDialog.this.dispose();
            }
            else if (source == MultiprocessingConfigDialog.this.okButton) {
                int threadPoolSize;
                if (MultiprocessingConfigDialog.this.defaultProcessCountCheckBox.isSelected()) {
                    threadPoolSize = Runtime.getRuntime().availableProcessors();
                }
                else {
                    try {
                        threadPoolSize = Integer.parseInt(MultiprocessingConfigDialog.this.processCountInput.getText().trim());
                        if (threadPoolSize <= 0) {
                            throw new NumberFormatException();
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MultiprocessingConfigDialog.this, I18n.tr("multiprocessingConfigDialog.error.BadNumberOfProcesses", new Object[0]));
                        MultiprocessingConfigDialog.this.processCountInput.selectAll();
                        MultiprocessingConfigDialog.this.processCountInput.requestFocus();
                        return;
                    }
                }
                MultiprocessingConfigDialog.this.taskManager.setThreadPoolSize(threadPoolSize);
                MultiprocessingConfigDialog.this.doApply();
                MultiprocessingConfigDialog.this.dispose();
            }
        }
    }
}
