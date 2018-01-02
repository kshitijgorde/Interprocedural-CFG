import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Loops extends XmlClass
{
    public static final String TAG_LOOPS = "loops";
    public static final String TAG_LOOP = "loop";
    public static final String TAG_TITLE = "title";
    public static final String TAG_WHILE_LINE = "whileline";
    public static final String TAG_REPEAT_LINE = "repeatline";
    public static final String TAG_LINE_NUMBER = "line";
    public static final String TAG_INDENT_VALUE = "indent";
    public static final String TAG_ANSWER = "answer";
    public static final String TAG_CHOICES = "choices";
    public static final String TAG_CHOICE = "choice";
    private Vector _vecLoops;
    
    public Loops() {
        this.fromFile("loops.xml");
    }
    
    public Loop getLoop(final int index) {
        if (this._vecLoops == null || index >= this._vecLoops.size()) {
            return null;
        }
        return this._vecLoops.get(index);
    }
    
    public int getSize() {
        return (this._vecLoops == null) ? -1 : this._vecLoops.size();
    }
    
    protected void createDOM() {
        this._document = null;
        try {
            this._document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        final Element root = this._document.createElement(this.TAG_ROOT);
        root.appendChild(this._document.createTextNode("\n  "));
        this._document.appendChild(root);
        root.appendChild(this._document.createComment("start of loops"));
        root.appendChild(this._document.createTextNode("\n  "));
        for (int i = 0; i < this._vecLoops.size(); ++i) {
            final Loop loop = this._vecLoops.get(i);
            final Element child = this._document.createElement("loop");
            child.appendChild(this._document.createTextNode("\n    "));
            child.appendChild(this._document.createComment("start of loop"));
            child.appendChild(this._document.createTextNode("\n    "));
            this.createTag(child, "title", loop.getTitle(), 4);
            final Vector vecWhiles = loop.getWhiles();
            if (vecWhiles != null) {
                for (int j = 0; j < vecWhiles.size(); ++j) {
                    this.createLine(child, vecWhiles, "whileline", j);
                }
            }
            final Vector vecRepeats = loop.getRepeats();
            if (vecRepeats != null) {
                for (int k = 0; k < vecRepeats.size(); ++k) {
                    this.createLine(child, vecRepeats, "repeatline", k);
                }
            }
            child.appendChild(this._document.createComment("end of loop"));
            child.appendChild(this._document.createTextNode("\n  "));
            root.appendChild(child);
            root.appendChild(this._document.createTextNode("\n  "));
        }
        root.appendChild(this._document.createComment("end of loops"));
        root.appendChild(this._document.createTextNode("\n"));
    }
    
    protected void setMembers() {
        if (this._document == null) {
            return;
        }
        this._vecLoops = new Vector();
        Loop loop = null;
        NodeList lstLoops = this._document.getElementsByTagName("loops");
        if (lstLoops == null || lstLoops.getLength() == 0) {
            return;
        }
        lstLoops = lstLoops.item(0).getChildNodes();
        if (lstLoops == null || lstLoops.getLength() == 0) {
            return;
        }
        for (int i = 0; i < lstLoops.getLength(); ++i) {
            if (lstLoops.item(i).getNodeType() == 1) {
                final NodeList lstLoop = lstLoops.item(i).getChildNodes();
                if (lstLoop != null) {
                    if (lstLoop.getLength() != 0) {
                        for (int j = 0; j < lstLoop.getLength(); ++j) {
                            if (lstLoop.item(j).getNodeType() == 1) {
                                final Node node = lstLoop.item(j);
                                if (node.getNodeName().equalsIgnoreCase("title")) {
                                    if (loop != null) {
                                        this._vecLoops.add(loop);
                                    }
                                    loop = new Loop(this.getValue(node));
                                }
                                else if (node.getNodeName().equalsIgnoreCase("whileline")) {
                                    this.setLines(loop, node, true);
                                }
                                else if (node.getNodeName().equalsIgnoreCase("repeatline")) {
                                    this.setLines(loop, node, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (loop != null) {
            this._vecLoops.add(loop);
        }
    }
    
    private void setLines(final Loop loop, final Node node, final boolean bWhile) {
        if (loop == null || node == null) {
            return;
        }
        LoopLine line = null;
        final NodeList kids = node.getChildNodes();
        if (kids == null || kids.getLength() == 0) {
            return;
        }
        for (int i = 0; i < kids.getLength(); ++i) {
            if (kids.item(i).getNodeType() == 1) {
                final Node data = kids.item(i);
                final String name = data.getNodeName();
                if (name.equalsIgnoreCase("line")) {
                    if (line != null) {
                        if (bWhile) {
                            loop.addWhile(line);
                        }
                        else {
                            loop.addRepeat(line);
                        }
                    }
                    line = new LoopLine();
                }
                else if (name.equalsIgnoreCase("indent")) {
                    int indent = 0;
                    try {
                        indent = Integer.parseInt(this.getValue(data));
                    }
                    catch (NumberFormatException ex) {}
                    line.setIndent(indent);
                }
                else if (name.equalsIgnoreCase("answer")) {
                    line.setAnswer(this.getValue(data));
                }
                else if (name.equalsIgnoreCase("choices")) {
                    this.setChoices(line, data);
                }
            }
        }
        if (line != null) {
            if (bWhile) {
                loop.addWhile(line);
            }
            else {
                loop.addRepeat(line);
            }
        }
    }
    
    private void setChoices(final LoopLine line, final Node data) {
        if (line == null || data == null) {
            return;
        }
        final NodeList kids = data.getChildNodes();
        if (kids == null || kids.getLength() == 0) {
            return;
        }
        for (int i = 0; i < kids.getLength(); ++i) {
            if (kids.item(i).getNodeType() == 1) {
                line.addChoice(this.getValue(kids.item(i)));
            }
        }
    }
    
    private void createLine(final Element parent, final Vector vector, final String tag, final int index) {
        if (vector == null || index >= vector.size()) {
            return;
        }
        final LoopLine line = vector.get(index);
        final Element child = this._document.createElement(tag);
        child.appendChild(this._document.createTextNode("\n      "));
        final Vector choices = line.getChoices();
        this.createTag(child, "line", "" + index, 6);
        this.createTag(child, "indent", "" + line.getIndent(), 6);
        this.createTag(child, "answer", line.getAnswer(), (choices != null) ? 6 : 4);
        if (choices != null || choices.size() > 0) {
            final Element grandChild = this._document.createElement("choices");
            grandChild.appendChild(this._document.createTextNode("\n      "));
            for (int i = 1; i < choices.size(); ++i) {
                grandChild.appendChild(this._document.createTextNode("  "));
                this.createTag(grandChild, "choice", choices.get(i).toString(), 6);
            }
            child.appendChild(grandChild);
            child.appendChild(this._document.createTextNode("\n    "));
        }
        parent.appendChild(child);
        parent.appendChild(this._document.createTextNode("\n    "));
    }
}
