import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.AWTKeyStroke;
import java.util.Set;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CVSPanel extends JPanel
{
    private static String[] TEXT_DESCRIPTION;
    private JComboBox _comboProject;
    private JButton _buttonCheckOut;
    private JButton _buttonCheckIn;
    private JButton _buttonRelease;
    private JTextArea _textDescription;
    private JTextArea _textComments;
    private CVSTable _tableCVS;
    private static Vector _versionControl;
    private static int _intSelectedIndex;
    
    public CVSPanel() {
        CVSPanel._versionControl = new Vector();
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.orange, 1));
        this.setBackground(UI_SoftEng.WINOW_COLOR);
        JLabel labelX = new JLabel("Project");
        labelX.setBounds(10, 25, 75, 20);
        labelX.setForeground(Color.white);
        this.add(labelX);
        (this._comboProject = new JComboBox()).setModel(new DefaultComboBoxModel<String>(new String[] { "", "NOT Operator", "Square Root", "Batting Average" }));
        this._comboProject.setBounds(95, 20, 150, 30);
        this._comboProject.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CVSPanel.this.doProjectClick();
            }
        });
        this.add(this._comboProject);
        CVSPanel._versionControl.add(new VersionControl());
        CVSPanel._versionControl.add(new VersionControl("NOT Operator", new NotOperator()));
        CVSPanel._versionControl.add(new VersionControl("Square Root", new SquareRoot()));
        CVSPanel._versionControl.add(new VersionControl("Batting Average", new BattingAvg()));
        labelX = new JLabel("Description");
        labelX.setBounds(10, 65, 75, 20);
        labelX.setForeground(Color.white);
        this.add(labelX);
        (this._textDescription = new JTextArea()).setBackground(UI_SoftEng.WINOW_COLOR);
        this._textDescription.setEditable(false);
        this._textDescription.setLineWrap(true);
        this._textDescription.setWrapStyleWord(true);
        this._textDescription.setMargin(new Insets(5, 5, 5, 5));
        this._textDescription.setTabSize(5);
        this._textDescription.setForeground(Color.white);
        JScrollPane scroll = new JScrollPane(this._textDescription);
        scroll.setHorizontalScrollBarPolicy(31);
        scroll.setBorder(new LineBorder(Color.orange, 1));
        scroll.setBounds(this._comboProject.getX(), 65, 270, 150);
        this.add(scroll);
        (this._buttonCheckOut = new JButton("Check Out")).setBounds(scroll.getX() + scroll.getWidth() - 100, this._comboProject.getY(), 100, 30);
        this._buttonCheckOut.setEnabled(false);
        this._buttonCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CVSPanel.this.doCheckOutClick();
            }
        });
        this.add(this._buttonCheckOut);
        labelX = new JLabel("Comments");
        labelX.setBounds(390, 65, 75, 20);
        labelX.setForeground(Color.white);
        this.add(labelX);
        (this._buttonCheckIn = new JButton("Check In")).setEnabled(false);
        this._buttonCheckIn.setBounds(475, this._comboProject.getY(), 100, 30);
        this._buttonCheckIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CVSPanel.this.doCheckInClick();
            }
        });
        this.add(this._buttonCheckIn);
        (this._textComments = new JTextArea()).setBackground(UI_SoftEng.WINOW_COLOR);
        this._textComments.setLineWrap(true);
        this._textComments.setWrapStyleWord(true);
        this._textComments.setMargin(new Insets(5, 5, 5, 5));
        this._textComments.setForeground(Color.white);
        this._textComments.setCaretColor(Color.orange);
        this._textComments.setFocusTraversalKeys(0, null);
        this._textComments.setFocusTraversalKeys(1, null);
        scroll = new JScrollPane(this._textComments);
        scroll.setHorizontalScrollBarPolicy(31);
        scroll.setBorder(new LineBorder(Color.orange, 1));
        scroll.setBounds(this._buttonCheckIn.getX(), 65, 280, 150);
        this.add(scroll);
        (this._buttonRelease = new JButton("Release")).setEnabled(false);
        this._buttonRelease.setBounds(scroll.getX() + scroll.getWidth() - 100, this._buttonCheckIn.getY(), 100, 30);
        this._buttonRelease.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                CVSPanel.this.doReleaseClick();
            }
        });
        this.add(this._buttonRelease);
        this._tableCVS = new CVSTable();
        final JScrollPane scrollTable = new JScrollPane(this._tableCVS.getTable());
        scrollTable.setBounds(10, 280, 750, 210);
        scrollTable.setVerticalScrollBarPolicy(22);
        this.add(scrollTable);
    }
    
    private void doProjectClick() {
        final String project = this._comboProject.getSelectedItem().toString().trim();
        if (project.length() == 0) {
            this._buttonCheckOut.setEnabled(false);
            this._buttonCheckIn.setEnabled(false);
            this._buttonRelease.setEnabled(false);
            this._textDescription.setText("");
            CVSPanel._intSelectedIndex = 0;
        }
        else {
            this._buttonCheckOut.setEnabled(true);
            this._buttonCheckIn.setEnabled(false);
            this._buttonRelease.setEnabled(false);
            this._textDescription.setText(CVSPanel.TEXT_DESCRIPTION[this._comboProject.getSelectedIndex()]);
            this._textDescription.setTabSize(5);
            this._textDescription.setCaretPosition(0);
            CVSPanel._intSelectedIndex = this._comboProject.getSelectedIndex();
        }
        final VersionControl ctrl = CVSPanel._versionControl.elementAt(CVSPanel._intSelectedIndex);
        this._tableCVS.update(ctrl.getAll());
    }
    
    private void doCheckOutClick() {
        final String project = (String)this._comboProject.getSelectedItem();
        if (project.trim().length() > 0) {
            this._comboProject.setEnabled(false);
            this._buttonCheckOut.setEnabled(false);
            this._buttonCheckIn.setEnabled(true);
            this._buttonRelease.setEnabled(true);
            final VersionControl ctrl = CVSPanel._versionControl.elementAt(this._comboProject.getSelectedIndex());
            ctrl.checkOut();
            this._tableCVS.update(ctrl.getAll());
            BlackBoxPanel.getTable().update(ctrl.getAllTestsClean());
            UI_SoftEng.showBlackBox();
            UI_SoftEng.setProject("Project: " + project);
        }
    }
    
    private void doCheckInClick() {
        this._comboProject.setEnabled(true);
        this._buttonCheckOut.setEnabled(true);
        this._buttonCheckIn.setEnabled(false);
        this._buttonRelease.setEnabled(false);
        UI_SoftEng.setProject("Project: N/A");
        UI_SoftEng.disableBlackBox();
        final VersionControl ctrl = CVSPanel._versionControl.elementAt(this._comboProject.getSelectedIndex());
        ctrl.checkIn(this._textComments.getText());
        this._tableCVS.update(ctrl.getAll());
        final JTable table = BlackBoxPanel.getTable().getTable();
        final TableModel modelTable = table.getModel();
        for (int i = 0; i < modelTable.getRowCount(); ++i) {
            final String strInput = modelTable.getValueAt(i, 1).toString();
            final boolean boolFail = modelTable.getValueAt(i, 3).toString().equals("F");
            if (boolFail) {
                ctrl.fix(strInput);
            }
        }
        ctrl.ready();
        this._tableCVS.update(ctrl.getAll());
    }
    
    private void doReleaseClick() {
        this._comboProject.setEnabled(true);
        this._buttonCheckOut.setEnabled(true);
        this._buttonCheckIn.setEnabled(false);
        this._buttonRelease.setEnabled(false);
        UI_SoftEng.setProject("Project: N/A");
        UI_SoftEng.disableBlackBox();
        final VersionControl ctrl = CVSPanel._versionControl.elementAt(this._comboProject.getSelectedIndex());
        ctrl.release();
        ctrl.feedback();
        this._tableCVS.update(ctrl.getAll());
    }
    
    public static VersionControl getCurrentVC() {
        final VersionControl ctrl = CVSPanel._versionControl.elementAt(CVSPanel._intSelectedIndex);
        return ctrl;
    }
    
    static {
        CVSPanel.TEXT_DESCRIPTION = new String[] { "", "Input \n\t Binary Number \nOutput \n\t Binary Number \nComments \n\t Output is the NOT value of the input", "Input \n\t Positive number (real or integer) \n\t |input| < 1,000,000 \nOutput \n\t Positive number (real) \n\t Accurate to 3 decimals \nComments \n\t Output is the square root of the \n\t input", "Input \n    Characters (H, W, O, K, S,  ) \n\t H - hit \n\t W - walk \n\t O - out \n\t K - strike out \n\t S - sacrifice \n\t     - ignored \n    Example \n\t HHH WSKO\nOutput \n\t Number (real) or \n\t No at bats \nComments \n\t Output is the calculated batting \n\t average (H / (H+K+O))\n" };
        CVSPanel._intSelectedIndex = -1;
    }
}
