import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class ProgressPanel extends StudentPanel
{
    private static final int MESSAGE_WIDTH = 28;
    private static final int TEXT_WIDTH = 3;
    private static final int NAME_WIDTH = 20;
    private static final int LABEL_X = 0;
    private static final int SHOWN_X = 1;
    private static final int RIGHT_X = 2;
    private static final int WRONG_X = 3;
    private static final int NEED_X = 4;
    private int first;
    private int buttonCount;
    private int amountNeeded;
    private int[] shown;
    private int[] right;
    private int[] wrong;
    private int[] need;
    private TextField[] shownField;
    private TextField[] rightField;
    private TextField[] wrongField;
    private TextField[] needField;
    private String[] buttonNames;
    
    public ProgressPanel(final String[] buttonNames, final int first, final int amountNeeded) {
        this.first = first;
        this.amountNeeded = amountNeeded;
        this.buttonNames = buttonNames;
        this.init();
    }
    
    public String getLog() {
        String log = "A:";
        for (int buttonNo = 0; buttonNo < this.buttonCount; ++buttonNo) {
            log = String.valueOf(log) + this.shown[buttonNo] + "_";
            log = String.valueOf(log) + this.right[buttonNo] + "_";
            log = String.valueOf(log) + this.wrong[buttonNo] + "_";
        }
        return log;
    }
    
    public void handleButtonClick() throws Exception {
        if (super.studentIndex < 0 || super.studentIndex >= super.studentList.length) {
            super.message.setText("Not a registered student.");
            return;
        }
        final String studentName = super.studentList[super.studentIndex];
        final String data = String.valueOf(Screen.panelNo) + ":" + studentName.replace(' ', '_');
        try {
            final Socket socket = new Socket("nova.umuc.edu", 80);
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes("GET /cgi-bin/cgiwrap/jarc/idsv-submit?" + data + "\n\n");
            super.message.setText("Your assignment has been accepted");
            try {
                out.close();
                socket.close();
            }
            catch (Exception ex) {}
            for (int buttonNo = 0; buttonNo < this.buttonCount; ++buttonNo) {
                this.need[buttonNo] = this.amountNeeded;
                this.needField[buttonNo].setText(String.valueOf(this.need[buttonNo]));
            }
            super.button.setEnabled(false);
        }
        catch (UnknownHostException ex2) {
            super.message.setText("Unknown Server");
        }
        catch (IOException ex3) {
            super.message.setText("IO Error");
        }
    }
    
    public void init() {
        this.buttonCount = this.buttonNames.length - this.first;
        this.setLayout(super.gridBag);
        this.setBackground(Color.white);
        this.shownField = new TextField[this.buttonCount];
        this.rightField = new TextField[this.buttonCount];
        this.wrongField = new TextField[this.buttonCount];
        this.needField = new TextField[this.buttonCount];
        this.shown = new int[this.buttonCount];
        this.right = new int[this.buttonCount];
        this.wrong = new int[this.buttonCount];
        this.need = new int[this.buttonCount];
        super.constraints.insets = new Insets(5, 5, 5, 5);
        this.addLabel("Shown", 1, 1, 1, 0);
        this.addLabel("Right", 1, 1, 2, 0);
        this.addLabel("Wrong", 1, 1, 3, 0);
        this.addLabel("Need", 1, 1, 4, 0);
        for (int button = 0; button < this.buttonCount; ++button) {
            this.shown[button] = 0;
            this.right[button] = 0;
            this.wrong[button] = 0;
            this.need[button] = this.amountNeeded;
            this.addLabel(this.buttonNames[button + this.first], 0, 1, 0, button + 1);
            this.shownField[button] = this.addTextField(3, false, String.valueOf(this.shown[button]), 1, button + 1, 1);
            this.rightField[button] = this.addTextField(3, false, String.valueOf(this.right[button]), 2, button + 1, 1);
            this.wrongField[button] = this.addTextField(3, false, String.valueOf(this.wrong[button]), 3, button + 1, 1);
            this.needField[button] = this.addTextField(3, false, String.valueOf(this.need[button]), 4, button + 1, 1);
        }
        super.init(this.buttonCount + 1, "Submit", false, "Submit when \"Need\" column is all zero", 28);
    }
    
    public void oneMoreRight(int buttonNo) {
        buttonNo -= this.first;
        final int[] right = this.right;
        final int n = buttonNo;
        ++right[n];
        this.rightField[buttonNo].setText(String.valueOf(this.right[buttonNo]));
        if (this.need[buttonNo] > 0) {
            final int[] need = this.need;
            final int n2 = buttonNo;
            --need[n2];
            this.needField[buttonNo].setText(String.valueOf(this.need[buttonNo]));
        }
        for (int buttonIndex = 0; buttonIndex < this.buttonCount; ++buttonIndex) {
            if (this.need[buttonIndex] > 0) {
                return;
            }
        }
        super.button.setEnabled(true);
        super.message.setText("Enter name (last, first) Click submit.");
    }
    
    public void oneMoreShown(int buttonNo) {
        buttonNo -= this.first;
        final int[] shown = this.shown;
        final int n = buttonNo;
        ++shown[n];
        this.shownField[buttonNo].setText(String.valueOf(this.shown[buttonNo]));
    }
    
    public void oneMoreWrong(int buttonNo) {
        buttonNo -= this.first;
        final int[] wrong = this.wrong;
        final int n = buttonNo;
        ++wrong[n];
        final int[] need = this.need;
        final int n2 = buttonNo;
        need[n2] += 2;
        this.wrongField[buttonNo].setText(String.valueOf(this.wrong[buttonNo]));
        this.needField[buttonNo].setText(String.valueOf(this.need[buttonNo]));
    }
}
