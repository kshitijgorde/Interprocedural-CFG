import java.awt.Event;
import java.text.MessageFormat;
import java.util.StringTokenizer;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.List;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class SearchFrame extends Frame
{
    SearchApplet4ech sa;
    Button list_topics;
    Button cancel;
    Button display;
    Checkbox case_sensitive;
    List doc_list;
    TextField input_tf;
    Label status;
    
    SearchFrame(final SearchApplet4ech sa) {
        this.doc_list = new List(15, false);
        this.input_tf = new TextField(40);
        this.status = new Label();
        this.sa = sa;
        this.setTitle(this.sa.getMessage("searchappletframe.search.frame_title"));
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        this.list_topics = new Button(this.sa.getMessage("searchappletframe.list_topics.button"));
        this.cancel = new Button(this.sa.getMessage("searchappletframe.cancel.button"));
        this.display = new Button(this.sa.getMessage("searchappletframe.display.button"));
        (this.case_sensitive = new Checkbox(this.sa.getMessage("searchappletframe.case_sensitive.checkbox"))).setState(true);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(new Label(this.sa.getMessage("searchappletframe.type_in_the_keyword_to_find.label")));
        panel.add(this.input_tf);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(0));
        panel3.add(this.case_sensitive);
        final Panel panel4 = new Panel();
        panel4.setLayout(new FlowLayout(2));
        panel4.add(this.list_topics);
        panel2.add(panel3);
        panel2.add(panel4);
        panel.add(panel2);
        panel.add(new Label(this.sa.getMessage("searchappletframe.select_topic_to_display.label")));
        this.add("North", panel);
        this.doc_list.setBackground(Color.white);
        this.add("Center", this.doc_list);
        final Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(2, 1));
        panel5.add(this.status);
        final Panel panel6 = new Panel();
        panel6.setLayout(new FlowLayout(2));
        panel6.add(this.cancel);
        panel6.add(this.display);
        panel5.add(panel6);
        this.add("South", panel5);
    }
    
    public void clearList() {
        this.doc_list.clear();
    }
    
    public void updateList(final String s) {
        this.doc_list.addItem(s);
    }
    
    public void setStatus(final String text) {
        this.status.setText(text);
    }
    
    private void initiateSearch() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.input_tf.getText());
        String string = "";
        while (stringTokenizer.hasMoreTokens()) {
            string = String.valueOf(string) + " " + stringTokenizer.nextToken();
        }
        final String trim = string.trim();
        this.sa.showStatus(MessageFormat.format(this.sa.getMessage("searchappletframe.searching_for"), trim));
        final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, "+");
        final int countTokens = stringTokenizer2.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = stringTokenizer2.nextToken().trim();
            if (!this.case_sensitive.getState()) {
                array[i] = array[i].toLowerCase();
            }
        }
        this.sa.search(array, this.case_sensitive.getState());
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.setVisible(false);
        }
        else if (event.id == 1001) {
            if (event.target == this.list_topics) {
                this.initiateSearch();
            }
            else if (event.target == this.cancel) {
                this.sa.setStopSearch(true);
            }
            else if (event.target == this.display) {
                if (this.doc_list.getSelectedIndex() > -1) {
                    this.sa.showDoc(this.doc_list.getSelectedIndex());
                }
            }
            else if (event.target == this.doc_list && this.doc_list.getSelectedIndex() > -1) {
                this.sa.showDoc(this.doc_list.getSelectedIndex());
            }
        }
        else if (event.id == 402 && event.target == this.input_tf && (event.key == 10 || event.key == 13)) {
            this.initiateSearch();
        }
        return super.handleEvent(event);
    }
}
