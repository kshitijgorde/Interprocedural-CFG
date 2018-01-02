import java.awt.event.TextEvent;
import java.awt.Insets;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class StudentPanel extends Panel implements ActionListener, TextListener
{
    protected static final int INSET = 5;
    protected static final int INSET_Y = 3;
    protected Button button;
    protected TextField message;
    protected GridBagLayout gridBag;
    protected GridBagConstraints constraints;
    protected int studentIndex;
    protected String studentName;
    protected String[] studentList;
    protected TextField name;
    private static final int COLUMN_0 = 0;
    private static final int COLUMN_1 = 1;
    private static final int COLUMN_2 = 2;
    
    public StudentPanel() {
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        this.studentIndex = -1;
        this.studentName = "";
        this.studentList = new String[0];
        this.setLayout(this.gridBag);
        this.setBackground(Color.white);
    }
    
    public void actionPerformed(final ActionEvent event) {
        if (event.getActionCommand().equals(this.button.getLabel())) {
            try {
                this.handleButtonClick();
            }
            catch (Exception exception) {
                this.message.setText(exception.toString());
            }
        }
    }
    
    public void addLabel(final String name, final int alignment, final int width, final int x, final int y) {
        final Label label = new Label(name, alignment);
        this.constraints.anchor = ((alignment == 0) ? 17 : 13);
        this.constraints.gridwidth = width;
        this.constraints.gridx = x;
        this.constraints.gridy = y;
        this.gridBag.setConstraints(label, this.constraints);
        this.add(label);
    }
    
    public TextField addTextField(final int fieldWidth, final boolean edit, final String value, final int x, final int y, final int width) {
        final TextField field = new TextField(fieldWidth);
        field.setEditable(edit);
        field.setText(value);
        this.constraints.anchor = 13;
        this.constraints.gridwidth = width;
        this.constraints.gridx = x;
        this.constraints.gridy = y;
        this.gridBag.setConstraints(field, this.constraints);
        this.add(field);
        return field;
    }
    
    public void getStudentNames(final String fileName) {
        final Vector sections = new Vector();
        try {
            URL url = new URL("http://nova.umuc.edu/~jarc/data/" + fileName);
            InputStream input = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            int sectionCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                sections.addElement(line);
                ++sectionCount;
            }
            final Vector studentList = new Vector();
            for (int section = 0; section < sectionCount; ++section) {
                url = new URL("http://nova.umuc.edu/~jarc/data/" + sections.elementAt(section) + ".txt");
                input = url.openStream();
                reader = new BufferedReader(new InputStreamReader(input));
                final Vector studentVector = new Vector();
                while ((line = reader.readLine()) != null) {
                    studentVector.addElement(line);
                }
                this.mergeLists(studentVector);
                reader.close();
                input.close();
            }
        }
        catch (Exception ex) {
            this.message.setText(ex.toString());
        }
    }
    
    public abstract void handleButtonClick() throws Exception;
    
    public void init(int row, final String buttonName, final boolean buttonEnabled, final String prompt, final int messageWidth) {
        this.constraints.insets = new Insets(3, 5, 3, 5);
        (this.button = new Button(buttonName)).addActionListener(this);
        this.constraints.anchor = 17;
        this.addLabel("Enter Name", 2, 1, 0, row);
        (this.name = this.addTextField(messageWidth, true, "", 1, row++, 4)).addTextListener(this);
        this.constraints.gridx = 0;
        this.constraints.gridy = row;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.gridBag.setConstraints(this.button, this.constraints);
        this.add(this.button);
        this.button.setEnabled(buttonEnabled);
        (this.message = this.addTextField(messageWidth, false, "", 1, row, 4)).setText(prompt);
    }
    
    private void mergeLists(final Vector studentVector) {
        final String[] newStudentList = new String[studentVector.size() + this.studentList.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < studentVector.size()) {
            if (j >= this.studentList.length) {
                break;
            }
            if (studentVector.elementAt(i).compareTo(this.studentList[j]) < 0) {
                newStudentList[k++] = studentVector.elementAt(i++);
            }
            else {
                newStudentList[k++] = this.studentList[j++];
            }
        }
        while (i < studentVector.size()) {
            newStudentList[k++] = studentVector.elementAt(i++);
        }
        while (j < this.studentList.length) {
            newStudentList[k++] = this.studentList[j++];
        }
        this.studentList = newStudentList;
    }
    
    public void textValueChanged(final TextEvent event) {
        final String newStudentName = this.name.getText();
        if (this.studentName.equals(newStudentName)) {
            return;
        }
        this.studentIndex = -1;
        this.studentName = newStudentName;
        int i = 0;
        while (i < this.studentList.length) {
            if (this.studentList[i].regionMatches(true, 0, this.studentName, 0, this.studentName.length())) {
                if (i < this.studentList.length && !this.studentList[i + 1].regionMatches(true, 0, this.studentName, 0, this.studentName.length())) {
                    this.studentIndex = i;
                    break;
                }
                this.studentIndex = -1;
                break;
            }
            else {
                ++i;
            }
        }
        if (this.studentIndex >= 0) {
            final int length = this.studentName.length();
            this.studentName = this.studentList[this.studentIndex];
            this.name.setText(this.studentName);
            this.name.setCaretPosition(length);
        }
    }
}
