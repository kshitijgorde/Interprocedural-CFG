// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Label;
import java.awt.Panel;

public class ReportRowPanel extends Panel
{
    Panel Top;
    Label percentage;
    Label percentLabel;
    Label descriptionLine1;
    Label descriptionLine2;
    Label descriptionLine3;
    hyperLinkTextArea titleLink;
    
    public ReportRowPanel(final Applet parent, final String title, final String desc, final String link, final int percentageHit, final Color rpt_backround_colour, final Color rpt_hyperlink_colour, final Color rpt_text_colour) {
        this.Top = new Panel();
        this.percentage = new Label();
        this.percentLabel = new Label();
        this.descriptionLine1 = new Label();
        this.descriptionLine2 = new Label();
        this.descriptionLine3 = new Label();
        this.setLayout(new GridLayout(4, 1, 0, 0));
        this.setBackground(Color.orange);
        final Insets ins = this.getInsets();
        this.setSize(430, 60);
        this.Top.setLayout(new FlowLayout(0, 0, 0));
        this.add(this.Top);
        this.Top.setBounds(0, 0, 430, 15);
        this.percentage.setText("percentage");
        this.percentage.setAlignment(2);
        this.Top.add(this.percentage);
        this.percentage.setBackground(Color.orange);
        this.percentage.setBounds(0, 0, 76, 23);
        this.percentLabel.setText("%");
        this.Top.add(this.percentLabel);
        this.percentLabel.setBackground(Color.orange);
        this.percentLabel.setBounds(76, 0, 25, 23);
        this.descriptionLine1.setText("text");
        this.add(this.descriptionLine1);
        this.descriptionLine1.setBounds(0, 15, 430, 15);
        this.add(this.descriptionLine2);
        this.descriptionLine2.setBounds(0, 30, 430, 15);
        this.add(this.descriptionLine3);
        this.descriptionLine3.setBounds(0, 45, 430, 15);
        this.setBackground(rpt_backround_colour);
        final Integer x = new Integer(percentageHit);
        this.percentage.setText(x.toString());
        this.percentage.setBackground(rpt_backround_colour);
        this.percentage.setForeground(rpt_text_colour);
        this.percentLabel.setFont(new Font("Dialog", 1, 12));
        this.percentLabel.setText("%");
        this.percentLabel.setBackground(rpt_backround_colour);
        this.percentLabel.setForeground(rpt_text_colour);
        (this.titleLink = new hyperLinkTextArea(parent, title, link, rpt_hyperlink_colour)).setFont(new Font("Dialog", 1, 12));
        this.titleLink.setBackground(rpt_backround_colour);
        this.Top.add(this.titleLink, 0);
        this.descriptionLine1.setForeground(rpt_text_colour);
        this.descriptionLine2.setForeground(rpt_text_colour);
        this.descriptionLine3.setForeground(rpt_text_colour);
        if (desc.length() < 120) {
            this.descriptionLine1.setText(desc.substring(0, desc.length()));
        }
        else if (desc.length() < 240) {
            this.descriptionLine1.setText(desc.substring(0, 120));
            this.descriptionLine2.setText(desc.substring(120, desc.length()));
        }
        else if (desc.length() < 360) {
            this.descriptionLine1.setText(desc.substring(0, 120));
            this.descriptionLine2.setText(desc.substring(120, 240));
            this.descriptionLine3.setText(desc.substring(240, desc.length()));
        }
        else {
            this.descriptionLine1.setText(desc.substring(0, 120));
            this.descriptionLine2.setText(desc.substring(120, 240));
            this.descriptionLine3.setText(desc.substring(240, 360));
        }
    }
}
