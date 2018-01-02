import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.io.IOException;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.io.DataOutputStream;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class bboard_gui extends Panel implements groupboard_consts
{
    private Object mutex;
    private frame_bboard frame;
    private List articles;
    TextArea article_text;
    private Button post_new_button;
    private Button post_reply_button;
    private Button email_reply_button;
    private Button close_button;
    private Button del_button;
    private Button refresh_button;
    private Hashtable msg_index;
    private DataOutputStream os;
    private int num_articles;
    groupboard parent;
    
    void set_os(final DataOutputStream os) {
        this.os = os;
    }
    
    bboard_gui(final DataOutputStream os, final boolean b, final groupboard parent, final frame_bboard frame) {
        this.mutex = new Object();
        this.frame = frame;
        this.parent = parent;
        this.os = os;
        this.msg_index = new Hashtable();
        this.num_articles = 0;
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.articles = new List(8, false);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(this.articles, gridBagConstraints);
        this.add(this.articles);
        gridBagConstraints.weighty = 0.6;
        if (parent.new_jdk) {
            this.article_text = new TextArea("", parent.get_int_arg("BBOARD_ROWS", 20), 45, 1);
        }
        else {
            this.article_text = new TextArea(parent.get_int_arg("BBOARD_ROWS", 20), 45);
        }
        this.article_text.setEditable(false);
        layout.setConstraints(this.article_text, gridBagConstraints);
        this.add(this.article_text);
        final Panel panel = new Panel();
        this.post_new_button = new Button("Post New Article");
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.post_new_button, gridBagConstraints2);
        panel.add(this.post_new_button);
        layout.setConstraints(this.post_reply_button = new Button("Post Reply"), gridBagConstraints2);
        panel.add(this.post_reply_button);
        layout.setConstraints(this.email_reply_button = new Button("Email Reply"), gridBagConstraints2);
        panel.add(this.email_reply_button);
        if (b) {
            layout.setConstraints(this.del_button = new Button("Delete"), gridBagConstraints2);
            panel.add(this.del_button);
        }
        layout.setConstraints(this.refresh_button = new Button("Refresh"), gridBagConstraints2);
        panel.add(this.refresh_button);
        if (null != frame) {
            layout.setConstraints(this.close_button = new Button("Close"), gridBagConstraints2);
            panel.add(this.close_button);
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
    
    void get_article(final int n) {
        synchronized (this.mutex) {
            try {
                synchronized (this.os) {
                    this.os.writeByte(101);
                    this.os.writeInt(n);
                }
            }
            catch (IOException ex) {
                this.parent.dataError();
            }
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
    }
    
    void del_article(final int n) {
        synchronized (this.mutex) {
            try {
                synchronized (this.os) {
                    this.os.writeByte(107);
                    this.os.writeInt(n);
                }
            }
            catch (IOException ex) {
                this.parent.dataError();
            }
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        synchronized (this.mutex) {
            switch (event.id) {
                case 701: {
                    if (event.target != this.articles) {
                        break;
                    }
                    final int selectedIndex = this.articles.getSelectedIndex();
                    if (selectedIndex < 0) {
                        break;
                    }
                    final Integer n = this.msg_index.get(new Integer(selectedIndex));
                    if (null != n) {
                        this.get_article(n);
                        this.article_text.setText("Downloading article...");
                        break;
                    }
                    break;
                }
                case 1001: {
                    if (event.target == this.post_new_button) {
                        String prev_name = "";
                        if (null != this.parent.gui.chat && null != this.parent.gui.chat.prev_name) {
                            prev_name = this.parent.gui.chat.prev_name;
                        }
                        new msg_window(this.parent, prev_name, this.os, "", "");
                        break;
                    }
                    if (event.target == this.post_reply_button) {
                        if (null != this.article_text) {
                            final int index = this.article_text.getText().indexOf("Subject:");
                            String s = "";
                            if (index != -1) {
                                s = this.article_text.getText().substring(index + "Subject".length() + 1, this.article_text.getText().indexOf(10, index + "Subject".length() + 1));
                                if (s.startsWith("Re:")) {
                                    s = s.substring("Re".length() + 1);
                                }
                                if (s.startsWith(" Re:")) {
                                    s = s.substring("Re".length() + 2);
                                }
                            }
                            int i = this.article_text.getText().indexOf(10, this.article_text.getText().indexOf("Date"));
                            String string = "";
                            while (i != -1) {
                                ++i;
                                final int index2 = this.article_text.getText().indexOf(10, i);
                                String s2;
                                if (index2 > 0) {
                                    s2 = this.article_text.getText().substring(i, index2);
                                }
                                else {
                                    s2 = this.article_text.getText().substring(i);
                                }
                                if (null != s2) {
                                    string = string + ">" + s2 + "\n";
                                }
                                i = index2;
                            }
                            String prev_name2 = "";
                            if (null != this.parent.gui.chat && null != this.parent.gui.chat.prev_name) {
                                prev_name2 = this.parent.gui.chat.prev_name;
                            }
                            final msg_window msg_window = new msg_window(this.parent, prev_name2, this.os, "Re:" + s, string);
                            break;
                        }
                        break;
                    }
                    else if (event.target == this.email_reply_button) {
                        if (null == this.article_text) {
                            break;
                        }
                        final int index3 = this.article_text.getText().indexOf("From:");
                        if (index3 != -1) {
                            final String substring = this.article_text.getText().substring(index3 + "From".length() + 1, this.article_text.getText().indexOf(10, index3 + "From".length() + 1));
                            final AppletContext appletContext = this.parent.getAppletContext();
                            try {
                                appletContext.showDocument(new URL("mailto:" + substring));
                            }
                            catch (MalformedURLException ex) {
                                System.out.println("Malformed url: mailto: " + substring);
                            }
                            break;
                        }
                        break;
                    }
                    else {
                        if (event.target == this.refresh_button) {
                            if (this.articles.countItems() != 0) {
                                this.msg_index.clear();
                                this.articles.clear();
                                this.num_articles = 0;
                            }
                            this.get_article_index();
                            break;
                        }
                        if (event.target == this.close_button) {
                            if (null != this.frame) {
                                this.frame.dispose();
                            }
                            this.parent.bbwin = null;
                            this.parent.bb = null;
                            break;
                        }
                        if (event.target != this.del_button) {
                            break;
                        }
                        final int selectedIndex2 = this.articles.getSelectedIndex();
                        if (selectedIndex2 < 0) {
                            break;
                        }
                        final Integer n2 = this.msg_index.get(new Integer(selectedIndex2));
                        if (null != n2) {
                            this.articles.delItem(selectedIndex2);
                            this.del_article(n2);
                            --this.num_articles;
                            for (int j = selectedIndex2; j < this.num_articles; ++j) {
                                final Integer n3 = this.msg_index.get(new Integer(j + 1));
                                if (null != n3) {
                                    this.msg_index.remove(new Integer(j + 1));
                                    this.msg_index.put(new Integer(j), n3);
                                }
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 201: {
                    if (null != this.frame) {
                        this.frame.dispose();
                    }
                    this.parent.bbwin = null;
                    this.parent.bb = null;
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    void get_article_index() {
        if (this.articles.countItems() != 0) {
            this.msg_index.clear();
            this.articles.clear();
            this.num_articles = 0;
        }
        try {
            this.os.writeByte(100);
        }
        catch (IOException ex) {
            this.parent.dataError();
        }
        if (this.parent.use_tunnel) {
            this.parent.t.force_post();
        }
    }
    
    void add_article(final int n, final String s) {
        synchronized (this.mutex) {
            this.articles.addItem(s);
            if (null != this.frame) {
                this.frame.pack();
            }
            this.msg_index.put(new Integer(this.num_articles), new Integer(n));
            ++this.num_articles;
        }
    }
}
