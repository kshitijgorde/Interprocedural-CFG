// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.Label;
import java.awt.Frame;

public class Score extends Frame
{
    Label PromptTxt;
    List ScoreList;
    Panel panel3;
    BorderLayout borderLayout1;
    Panel panel1;
    FlowLayout flowLayout1;
    Button SendBtn;
    Button ScoreMinusBtn;
    Button ResetAllBtn;
    Button ResetUserBtn;
    GridLayout gridLayout1;
    Button ScorePlusBtn;
    Panel ButtonPanel;
    Panel QuestionPanel;
    BorderLayout borderLayout2;
    TextArea textArea1;
    Panel panel4;
    BorderLayout borderLayout3;
    Panel panel2;
    Button CloseBtn;
    Panel panel5;
    GridLayout gridLayout2;
    Label label1;
    TextField AnswerInp;
    Label label2;
    TextField QuestionInp;
    Panel panel6;
    Panel panel7;
    Button SendABtn;
    Button SendQBtn;
    FlowLayout flowLayout2;
    Label StatusTxt;
    Button EndBtn;
    Button StartBtn;
    
    public Score() {
        this.PromptTxt = new Label();
        this.ScoreList = new List();
        this.panel3 = new Panel();
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.SendBtn = new Button();
        this.ScoreMinusBtn = new Button();
        this.ResetAllBtn = new Button();
        this.ResetUserBtn = new Button();
        this.gridLayout1 = new GridLayout();
        this.ScorePlusBtn = new Button();
        this.ButtonPanel = new Panel();
        this.QuestionPanel = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.textArea1 = new TextArea("", 12, 27, 3);
        this.panel4 = new Panel();
        this.borderLayout3 = new BorderLayout();
        this.panel2 = new Panel();
        this.CloseBtn = new Button();
        this.panel5 = new Panel();
        this.gridLayout2 = new GridLayout();
        this.label1 = new Label();
        this.AnswerInp = new TextField();
        this.label2 = new Label();
        this.QuestionInp = new TextField();
        this.panel6 = new Panel();
        this.panel7 = new Panel();
        this.SendABtn = new Button();
        this.SendQBtn = new Button();
        this.flowLayout2 = new FlowLayout();
        this.StatusTxt = new Label();
        this.EndBtn = new Button();
        this.StartBtn = new Button();
        try {
            this._$3231();
            this.textArea1.setText("To Run A Quiz Session;\n\n - Click 'Start Quiz' button.\n - Enter Question where provided.\n - Click 'Send Question' button.\n - Enter Answer where provided.\n - Click 'Send Answer' button.\n - Update the Score Board.\n - Click 'Send Scores' button.\n - Send Next Question etc..\n - Click 'End Quiz' when done.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setForeground(loader.secondaryFgColor);
        this.setBackground(loader.secondaryBgColor);
        this.setFont(new Font("Dialog", 1, 12));
        this.setSize(new Dimension(516, 403));
        this.setTitle("Quiz Controls");
        this.setLayout(this.borderLayout1);
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.PromptTxt.setFont(new Font("Dialog", 1, 14));
        this.PromptTxt.setText("    Quiz Control Panel");
        this.ScoreList.setBackground(new Color(235, 235, 255));
        this.ScoreList.setForeground(loader.secondaryFgColor);
        this.ScoreList.setFont(new Font("Dialog", 1, 14));
        this.ScoreList.addItemListener(new 2());
        this.ScoreList.addActionListener(new 3());
        this.panel3.setBackground(loader.secondaryBgColor);
        this.borderLayout1.setVgap(5);
        this.flowLayout1.setHgap(10);
        this.panel3.setLayout(this.borderLayout2);
        this.SendBtn.setLabel(" Send Scores ");
        this.ScoreMinusBtn.setLabel("Score  -1");
        this.ResetAllBtn.setLabel("Reset ALL");
        this.ResetUserBtn.setLabel("Reset User");
        this.gridLayout1.setColumns(1);
        this.gridLayout1.setVgap(5);
        this.ScorePlusBtn.addActionListener(new 4());
        this.ScorePlusBtn.setBackground(loader.buttonBgColor);
        this.ScorePlusBtn.setLabel("Score  +1");
        this.QuestionPanel.setLayout(this.borderLayout3);
        this.borderLayout2.setVgap(5);
        this.borderLayout2.setHgap(5);
        this.textArea1.setFont(new Font("Dialog", 0, 12));
        this.textArea1.setForeground(Color.darkGray);
        this.textArea1.setBackground(new Color(255, 255, 225));
        this.textArea1.setEditable(false);
        this.panel2.setLayout(this.flowLayout2);
        this.CloseBtn.setForeground(loader.buttonFgColor);
        this.CloseBtn.setBackground(loader.buttonBgColor);
        this.CloseBtn.setLabel("  CLOSE  ");
        this.CloseBtn.addActionListener(new 5());
        this.gridLayout2.setRows(5);
        this.gridLayout2.setColumns(1);
        this.label1.setText("Question (Max 400 Chars)");
        this.AnswerInp.setFont(new Font("Dialog", 0, 12));
        this.AnswerInp.setForeground(loader.inputFgColor);
        this.AnswerInp.setBackground(loader.inputBgColor);
        this.label2.setText("Answer (Max 400 Chars)");
        this.QuestionInp.setFont(new Font("Dialog", 0, 12));
        this.QuestionInp.setForeground(loader.inputFgColor);
        this.QuestionInp.setBackground(loader.inputBgColor);
        this.SendABtn.setEnabled(false);
        this.SendABtn.setForeground(loader.buttonFgColor);
        this.SendABtn.setBackground(loader.buttonBgColor);
        this.SendABtn.setLabel(" Send Answer ");
        this.SendABtn.addActionListener(new 6());
        this.SendQBtn.setEnabled(false);
        this.SendQBtn.setForeground(loader.buttonFgColor);
        this.SendQBtn.setBackground(loader.buttonBgColor);
        this.SendQBtn.setLabel(" Send Question ");
        this.SendQBtn.addActionListener(new 7());
        this.flowLayout2.setHgap(10);
        this.flowLayout2.setAlignment(2);
        this.flowLayout2.setVgap(10);
        this.StatusTxt.setAlignment(2);
        this.StatusTxt.setFont(new Font("Dialog", 1, 14));
        this.StatusTxt.setText("Click 'Start Quiz' To Get Started ");
        this.EndBtn.setEnabled(false);
        this.EndBtn.setForeground(Color.red);
        this.EndBtn.setLabel(" END QUIZ ");
        this.EndBtn.setBackground(loader.buttonBgColor);
        this.EndBtn.addActionListener(new 8());
        this.StartBtn.setForeground(Color.blue);
        this.StartBtn.setLabel(" START QUIZ ");
        this.StartBtn.setBackground(loader.buttonBgColor);
        this.StartBtn.addActionListener(new 9());
        this.panel5.setLayout(this.gridLayout2);
        this.ButtonPanel.setLayout(this.gridLayout1);
        this.ScorePlusBtn.setForeground(Color.blue);
        this.ScorePlusBtn.setEnabled(false);
        this.gridLayout1.setRows(6);
        this.gridLayout1.setHgap(5);
        this.ResetUserBtn.setForeground(Color.darkGray);
        this.ResetUserBtn.setEnabled(false);
        this.ResetUserBtn.addActionListener(new 10());
        this.ResetUserBtn.setBackground(loader.buttonBgColor);
        this.ResetAllBtn.setEnabled(false);
        this.ResetAllBtn.setForeground(Color.darkGray);
        this.ResetAllBtn.addActionListener(new 11());
        this.ResetAllBtn.setBackground(loader.buttonBgColor);
        this.ScoreMinusBtn.setForeground(Color.blue);
        this.ScoreMinusBtn.setEnabled(false);
        this.ScoreMinusBtn.addActionListener(new 12());
        this.ScoreMinusBtn.setBackground(loader.buttonBgColor);
        this.SendBtn.setEnabled(false);
        this.SendBtn.setForeground(Color.black);
        this.SendBtn.addActionListener(new 13());
        this.SendBtn.setBackground(loader.buttonBgColor);
        this.panel1.setLayout(this.flowLayout1);
        this.add(this.PromptTxt, "North");
        this.add(this.panel3, "West");
        this.panel3.add(this.textArea1, "Center");
        this.panel3.add(this.panel4, "West");
        this.add(this.ScoreList, "Center");
        this.add(this.panel1, "East");
        this.panel1.add(this.ButtonPanel, null);
        this.ButtonPanel.add(this.ScorePlusBtn, null);
        this.ButtonPanel.add(this.ScoreMinusBtn, null);
        this.ButtonPanel.add(this.ResetUserBtn, null);
        this.ButtonPanel.add(this.ResetAllBtn, null);
        this.ButtonPanel.add(this.SendBtn, null);
        this.add(this.QuestionPanel, "South");
        this.QuestionPanel.add(this.panel2, "South");
        this.panel2.add(this.StartBtn, null);
        this.panel2.add(this.EndBtn, null);
        this.panel2.add(this.SendQBtn, null);
        this.panel2.add(this.SendABtn, null);
        this.panel2.add(this.CloseBtn, null);
        this.QuestionPanel.add(this.panel5, "Center");
        this.panel5.add(this.label1, null);
        this.panel5.add(this.QuestionInp, null);
        this.panel5.add(this.label2, null);
        this.panel5.add(this.AnswerInp, null);
        this.panel5.add(this.StatusTxt, null);
        this.QuestionPanel.add(this.panel6, "East");
        this.QuestionPanel.add(this.panel7, "West");
    }
    
    void ScoreList_actionPerformed(final ActionEvent actionEvent) {
    }
    
    void SetNoSelection() {
        this.ScorePlusBtn.setEnabled(false);
        this.ScoreMinusBtn.setEnabled(false);
        this.ResetUserBtn.setEnabled(false);
        this.SendBtn.requestFocus();
    }
    
    void ScoreList_itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.ScorePlusBtn.setEnabled(true);
            this.ScoreMinusBtn.setEnabled(true);
            this.ResetUserBtn.setEnabled(true);
        }
    }
    
    void this_windowActivated(final WindowEvent windowEvent) {
        this.SendBtn.requestFocus();
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void ScorePlusBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.ScoreList.getSelectedIndex() == -1) {
            return;
        }
        final int selectedIndex = this.ScoreList.getSelectedIndex();
        final String item = this.ScoreList.getItem(selectedIndex);
        final String substring = item.substring(0, item.indexOf("="));
        int int1 = Integer.parseInt(item.substring(item.indexOf("=") + 1, item.length()));
        this.ScoreList.replaceItem(String.valueOf(String.valueOf(substring).concat(String.valueOf("="))).concat(String.valueOf(Integer.toString(++int1))), selectedIndex);
        this.SetNoSelection();
    }
    
    void ScoreMinusBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.ScoreList.getSelectedIndex() == -1) {
            return;
        }
        final int selectedIndex = this.ScoreList.getSelectedIndex();
        final String item = this.ScoreList.getItem(selectedIndex);
        final String substring = item.substring(0, item.indexOf("="));
        int int1 = Integer.parseInt(item.substring(item.indexOf("=") + 1, item.length()));
        this.ScoreList.replaceItem(String.valueOf(String.valueOf(substring).concat(String.valueOf("="))).concat(String.valueOf(Integer.toString(--int1))), selectedIndex);
        this.SetNoSelection();
    }
    
    void ResetUserBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.ScoreList.getSelectedIndex() == -1) {
            return;
        }
        final int selectedIndex = this.ScoreList.getSelectedIndex();
        final String item = this.ScoreList.getItem(selectedIndex);
        this.ScoreList.replaceItem(String.valueOf(String.valueOf(item.substring(0, item.indexOf("="))).concat(String.valueOf("="))).concat(String.valueOf("0")), selectedIndex);
        this.ScoreList.deselect(selectedIndex);
        this.SetNoSelection();
    }
    
    void ResetAllBtn_actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.ScoreList.getItemCount(); ++i) {
            final String item = this.ScoreList.getItem(i);
            this.ScoreList.replaceItem(String.valueOf(String.valueOf(item.substring(0, item.indexOf("="))).concat(String.valueOf("="))).concat(String.valueOf("0")), i);
        }
        this.SetNoSelection();
    }
    
    void SendBtn_actionPerformed(final ActionEvent actionEvent) {
        String concat = "";
        for (int i = 0; i < this.ScoreList.getItemCount(); ++i) {
            concat = String.valueOf(String.valueOf(concat).concat(String.valueOf(this.ScoreList.getItem(i)))).concat(String.valueOf(" "));
        }
        loader.SendLine(String.valueOf("500 500 ").concat(String.valueOf(concat)));
        this.StatusTxt.setText("Scores Sent To Room");
    }
    
    void CloseBtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void SendQBtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.QuestionInp.getText().trim();
        if (trim.length() < 1) {
            this.StatusTxt.setText("ERROR - Enter A Question First!");
            this.getToolkit().beep();
            return;
        }
        if (trim.length() > 400) {
            this.StatusTxt.setText("ERROR - Question is Too Long!");
            this.getToolkit().beep();
            return;
        }
        loader.SendLine(String.valueOf("500 300 ").concat(String.valueOf(trim)));
        this.StatusTxt.setText("Question Sent To Room");
        this.QuestionInp.setText("");
    }
    
    void SendABtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.AnswerInp.getText().trim();
        if (trim.length() < 1) {
            this.StatusTxt.setText("ERROR - Enter An Answer First!");
            this.getToolkit().beep();
            return;
        }
        if (trim.length() > 400) {
            this.StatusTxt.setText("ERROR - Answer is Too Long!");
            this.getToolkit().beep();
            return;
        }
        loader.SendLine(String.valueOf("500 400 ").concat(String.valueOf(trim)));
        this.StatusTxt.setText("Answer Sent To Room");
        this.AnswerInp.setText("");
    }
    
    void StartBtn_actionPerformed(final ActionEvent actionEvent) {
        this.StatusTxt.setText("Starting Quiz Session...");
        loader.SendLine("500 100 OK");
    }
    
    void EndBtn_actionPerformed(final ActionEvent actionEvent) {
        this.StatusTxt.setText("Ending Quiz Session...");
        loader.SendLine("500 200 OK");
    }
    
    class 1 extends WindowAdapter
    {
        public void windowActivated(final WindowEvent windowEvent) {
            Score.this.this_windowActivated(windowEvent);
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            Score.this.this_windowClosing(windowEvent);
        }
    }
    
    class 2 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            Score.this.ScoreList_itemStateChanged(itemEvent);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.ScoreList_actionPerformed(actionEvent);
        }
    }
    
    class 4 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.ScorePlusBtn_actionPerformed(actionEvent);
        }
    }
    
    class 5 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.CloseBtn_actionPerformed(actionEvent);
        }
    }
    
    class 6 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.SendABtn_actionPerformed(actionEvent);
        }
    }
    
    class 7 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.SendQBtn_actionPerformed(actionEvent);
        }
    }
    
    class 8 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.EndBtn_actionPerformed(actionEvent);
        }
    }
    
    class 9 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.StartBtn_actionPerformed(actionEvent);
        }
    }
    
    class 10 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.ResetUserBtn_actionPerformed(actionEvent);
        }
    }
    
    class 11 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.ResetAllBtn_actionPerformed(actionEvent);
        }
    }
    
    class 12 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.ScoreMinusBtn_actionPerformed(actionEvent);
        }
    }
    
    class 13 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Score.this.SendBtn_actionPerformed(actionEvent);
        }
    }
}
