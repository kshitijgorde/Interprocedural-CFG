import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Event;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class HtmlViewer extends Frame implements Runnable
{
    private static final int GAP = 5;
    private HtmlCanvas canvas;
    private Scrollbar scrollbar;
    private Button forward;
    private Button backward;
    private Button reload;
    private Label label;
    private TextField location;
    private Vector history;
    private int historyIndex;
    private Event evt;
    private String start;
    private URL docUrl;
    private int docType;
    
    public HtmlViewer() {
        this.docType = -1;
        this.history = new Vector();
        this.historyIndex = -1;
        this.setTitle("(No document)");
        this.setLayout(null);
        this.add(this.backward = new Button("Backward"));
        this.add(this.forward = new Button("Forward"));
        this.add(this.reload = new Button("Reload"));
        this.add(this.label = new Label("Location:"));
        this.add(this.location = new TextField());
        this.add(this.canvas = new HtmlCanvas());
        this.add(this.scrollbar = new Scrollbar(1));
        this.backward.disable();
        this.forward.disable();
        this.location.setVisible(false);
        this.label.setVisible(false);
        this.canvas.setBackground(Color.white);
        this.canvas.resize(600, 500);
        this.setBackground(Color.lightGray);
        this.pack();
        this.show();
        this.setLayout(null);
        this.setSize(this.insets().left + this.insets().right + 430, this.insets().top + this.insets().bottom + 270);
        this.setTitle("Untitled");
    }
    
    public HtmlViewer(final URL url) {
        this();
        this.setCursor(3);
        try {
            this.changeNewDocument(new HtmlDocument(url));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setCursor(0);
    }
    
    public HtmlViewer(final URL docUrl, final int docType) {
        this();
        this.setCursor(3);
        try {
            this.docUrl = docUrl;
            this.docType = docType;
            this.changeNewDocument(new HtmlDocument(docUrl, docType));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setCursor(0);
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.id == 201) {
            this.dispose();
            return true;
        }
        if (evt.target == this.scrollbar) {
            switch (evt.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.canvas.setStart(this.scrollbar.getValue());
                    this.start = null;
                    return true;
                }
            }
        }
        if ((evt.target == this.canvas && evt.id == 501) || evt.id == 1001) {
            if (this.evt == null) {
                this.setCursor(3);
                this.evt = evt;
                new Thread(this).start();
            }
            return true;
        }
        return super.handleEvent(evt);
    }
    
    public void run() {
        if (this.evt.target == this.canvas && this.evt.id == 501) {
            final URL href = this.canvas.getHref(this.evt.x - this.canvas.location().x, this.evt.y - this.canvas.location().y);
            if (href != null) {
                try {
                    this.changeNewDocument(new HtmlDocument(href));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (this.evt.id == 1001) {
            if (this.evt.target == this.forward) {
                this.changeDocument(this.history.elementAt(++this.historyIndex));
            }
            if (this.evt.target == this.backward) {
                final Vector history = this.history;
                final int historyIndex = this.historyIndex - 1;
                this.historyIndex = historyIndex;
                this.changeDocument(history.elementAt(historyIndex));
            }
            if (this.evt.target == this.reload && this.historyIndex >= 0) {
                try {
                    if (this.docType != -1) {
                        this.changeNewDocument(new HtmlDocument(this.docUrl, this.docType));
                    }
                    else {
                        final HtmlDocument htmlDocument = new HtmlDocument(new URL(this.history.elementAt(this.historyIndex).getURLString()));
                        this.history.setElementAt(htmlDocument, this.historyIndex);
                        this.changeDocument(htmlDocument);
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            if (this.evt.target == this.location) {
                try {
                    this.changeNewDocument(new HtmlDocument(new URL(this.location.getText())));
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        this.setCursor(0);
        this.evt = null;
    }
    
    public void changeNewDocument(final HtmlDocument htmlDocument) {
        if (this.historyIndex < 0 || !this.history.elementAt(this.historyIndex).getURLString().equals(htmlDocument.getURLString())) {
            this.history.setSize(++this.historyIndex + 1);
            this.history.setElementAt(htmlDocument, this.historyIndex);
            this.changeDocument(htmlDocument);
        }
    }
    
    public void changeDocument(final HtmlDocument htmlDocument) {
        this.canvas.changeDocument(htmlDocument);
        if (this.historyIndex == this.history.size() - 1) {
            this.forward.disable();
        }
        else {
            this.forward.enable();
        }
        if (this.historyIndex <= 0) {
            this.backward.disable();
        }
        else {
            this.backward.enable();
        }
        this.location.setText(htmlDocument.getLocation());
        this.setTitle(htmlDocument.getTitle());
        final Scrollbar scrollbar = this.scrollbar;
        final HtmlCanvas canvas = this.canvas;
        final String start = htmlDocument.getStart();
        this.start = start;
        scrollbar.setValue(canvas.setStart(start));
        this.layout();
    }
    
    public Dimension minimumSize() {
        return this.layoutSize(true);
    }
    
    public Dimension preferredSize() {
        return this.layoutSize(false);
    }
    
    public void layout() {
        final Insets insets = this.insets();
        boolean b = false;
        final Dimension size;
        final Dimension dimension = size = this.size();
        size.width -= insets.left + insets.right;
        final Dimension dimension2 = dimension;
        dimension2.height -= insets.top + insets.bottom;
        final Dimension layoutSize = this.layoutSize(false);
        if (layoutSize.width > dimension.width || layoutSize.height > dimension.height) {
            b = true;
        }
        final int n = 5;
        int controlHeight = this.controlHeight(b);
        final Button forward = this.forward;
        final Dimension dimension3 = b ? forward.minimumSize() : forward.preferredSize();
        this.forward.reshape(n + insets.left, 5 + insets.top, dimension3.width, controlHeight);
        final int n2 = n + (dimension3.width + 5);
        final Button backward = this.backward;
        final Dimension dimension4 = b ? backward.minimumSize() : backward.preferredSize();
        this.backward.reshape(n2 + insets.left, 5 + insets.top, dimension4.width, controlHeight);
        final int n3 = n2 + (dimension4.width + 5);
        final Button reload = this.reload;
        final Dimension dimension5 = b ? reload.minimumSize() : reload.preferredSize();
        this.reload.reshape(n3 + insets.left, 5 + insets.top, dimension5.width, controlHeight);
        final int n4 = n3 + (dimension5.width + 5);
        final Label label = this.label;
        final Dimension dimension6 = b ? label.minimumSize() : label.preferredSize();
        this.label.reshape(n4 + insets.left, 5 + insets.top, dimension6.width, controlHeight);
        final int n5 = n4 + (dimension6.width + 5);
        final TextField location = this.location;
        final Dimension dimension7 = b ? location.minimumSize() : location.preferredSize();
        this.location.reshape(n5 + insets.left, 5 + insets.top, dimension.width - n5 - 5, controlHeight);
        controlHeight += 10;
        final Dimension dimension8 = dimension;
        dimension8.height -= controlHeight;
        if (this.canvas.setWidth(dimension.width) > dimension.height && dimension.height > 0) {
            final Scrollbar scrollbar = this.scrollbar;
            final Dimension dimension9 = b ? scrollbar.minimumSize() : scrollbar.preferredSize();
            final Dimension dimension10 = dimension;
            dimension10.width -= dimension9.width;
            this.scrollbar.setValues(this.scrollbar.getValue(), dimension.height, 0, this.canvas.setWidth(dimension.width));
            this.scrollbar.setPageIncrement(dimension.height);
            this.scrollbar.setLineIncrement(15);
            this.scrollbar.reshape(dimension.width + insets.left, controlHeight + insets.top, dimension9.width, dimension.height);
            this.scrollbar.show();
        }
        else {
            this.scrollbar.setValue(0);
            this.scrollbar.hide();
        }
        this.canvas.repaint(10L);
        this.canvas.reshape(insets.left, controlHeight + insets.top, dimension.width, dimension.height);
        if (this.start == null) {
            this.canvas.setStart(this.scrollbar.getValue());
            return;
        }
        this.scrollbar.setValue(this.canvas.setStart(this.start));
    }
    
    private int controlHeight(final boolean b) {
        final Button forward = this.forward;
        final int height = (b ? forward.minimumSize() : forward.preferredSize()).height;
        final Button backward = this.backward;
        final int max = Math.max(height, (b ? backward.minimumSize() : backward.preferredSize()).height);
        final Button reload = this.reload;
        final int max2 = Math.max(max, (b ? reload.minimumSize() : reload.preferredSize()).height);
        final Label label = this.label;
        final int max3 = Math.max(max2, (b ? label.minimumSize() : label.preferredSize()).height);
        final TextField location = this.location;
        return Math.max(max3, (b ? location.minimumSize() : location.preferredSize()).height);
    }
    
    private Dimension layoutSize(final boolean b) {
        final int n = 5;
        final Button forward = this.forward;
        final Dimension dimension = b ? forward.minimumSize() : forward.preferredSize();
        final int n2 = n + (dimension.width + 5);
        final int height = dimension.height;
        final Button backward = this.backward;
        final Dimension dimension2 = b ? backward.minimumSize() : backward.preferredSize();
        final int n3 = n2 + (dimension2.width + 5);
        final int max = Math.max(height, dimension2.height);
        final Button reload = this.reload;
        final Dimension dimension3 = b ? reload.minimumSize() : reload.preferredSize();
        final int n4 = n3 + (dimension3.width + 5);
        final int max2 = Math.max(max, dimension3.height);
        final Label label = this.label;
        final Dimension dimension4 = b ? label.minimumSize() : label.preferredSize();
        final int n5 = n4 + (dimension4.width + 5);
        final int max3 = Math.max(max2, dimension4.height);
        final TextField location = this.location;
        final Dimension dimension5 = b ? location.minimumSize() : location.preferredSize();
        final int n6 = n5 + (dimension5.width + 5);
        int max4 = Math.max(max3, dimension5.height);
        max4 += 10;
        final HtmlCanvas canvas = this.canvas;
        final Dimension dimension6 = b ? canvas.minimumSize() : canvas.preferredSize();
        final int max5 = Math.max(n6, dimension6.width);
        final int n7 = max4 + dimension6.height;
        final Insets insets = this.insets();
        return new Dimension(insets.left + insets.right + max5, insets.top + insets.bottom + n7);
    }
    
    private Dimension componentSize(final Component component, final boolean b) {
        if (b) {
            return component.minimumSize();
        }
        return component.preferredSize();
    }
}
