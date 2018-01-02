// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.xml.XmlWriter;
import rene.util.list.ListElement;
import java.io.PrintWriter;
import rene.util.xml.XmlReader;
import rene.util.parser.StringParser;
import rene.util.xml.XmlTagText;
import rene.util.xml.XmlReaderException;
import rene.util.xml.XmlTag;
import java.util.Enumeration;
import rene.util.xml.XmlTagPI;
import rene.util.xml.XmlTree;
import java.util.Vector;
import rene.util.list.Tree;
import java.io.IOException;
import java.io.BufferedReader;

public class SGFTree
{
    protected TreeNode History;
    final int maxbuffer = 4096;
    char[] Buffer;
    int BufferN;
    BoardInterface GF;
    static int lastnl;
    static int BoardSize;
    static String GameName;
    
    public SGFTree(final Node node) {
        this.Buffer = new char[4096];
        this.History = new TreeNode(node);
        this.History.node().main(true);
    }
    
    public TreeNode top() {
        return this.History;
    }
    
    char readnext(final BufferedReader bufferedReader) throws IOException {
        char c;
        for (c = this.readchar(bufferedReader); c == '\n' || c == '\t' || c == ' '; c = this.readchar(bufferedReader)) {
            if (c == -1) {
                throw new IOException();
            }
        }
        return c;
    }
    
    char readchar(final BufferedReader bufferedReader) throws IOException {
        while (true) {
            final int read = bufferedReader.read();
            if (read == -1) {
                throw new IOException();
            }
            if (read == 13) {
                if (SGFTree.lastnl != 10) {
                    SGFTree.lastnl = 13;
                    return '\n';
                }
                SGFTree.lastnl = 0;
            }
            else {
                if (read != 10) {
                    SGFTree.lastnl = 0;
                    return (char)read;
                }
                if (SGFTree.lastnl != 13) {
                    SGFTree.lastnl = 10;
                    return '\n';
                }
                SGFTree.lastnl = 0;
            }
        }
    }
    
    char readnode(final TreeNode treeNode, final BufferedReader bufferedReader) throws IOException {
        final boolean parameter = this.GF.getParameter("sgfcomments", false);
        char c = this.readnext(bufferedReader);
        final Node node = new Node(((Node)treeNode.content()).number());
        while (true) {
            this.BufferN = 0;
            while (true) {
                if (c >= 'A' && c <= 'Z') {
                    this.Buffer[this.BufferN++] = c;
                }
                else {
                    if (c == '(' || c == ';' || c == ')') {
                        node.main(treeNode);
                        if (((Node)treeNode.content()).actions() == null) {
                            treeNode.content(node);
                        }
                        else {
                            final TreeNode treeNode2;
                            treeNode.addchild(treeNode2 = new TreeNode(node));
                            node.main(treeNode);
                            final TreeNode treeNode3 = treeNode2;
                            if (treeNode3.parentPos() != null && treeNode3 != treeNode3.parentPos().firstChild()) {
                                ((Node)treeNode3.content()).number(2);
                            }
                        }
                        return c;
                    }
                    if (c == '[') {
                        if (this.BufferN == 0) {
                            throw new IOException();
                        }
                        final String s = new String(this.Buffer, 0, this.BufferN);
                        Action action;
                        if (s.equals("L")) {
                            action = new LabelAction(this.GF);
                        }
                        else if (s.equals("M")) {
                            action = new MarkAction(this.GF);
                        }
                        else {
                            action = new Action(s);
                        }
                        while (c == '[') {
                            this.BufferN = 0;
                            while (true) {
                                char c2 = this.readchar(bufferedReader);
                                if (c2 == '\\') {
                                    c2 = this.readchar(bufferedReader);
                                    if (parameter && c2 == '\n') {
                                        if (this.BufferN > 1 && this.Buffer[this.BufferN - 1] == ' ') {
                                            continue;
                                        }
                                        c2 = ' ';
                                    }
                                }
                                else if (c2 == ']') {
                                    break;
                                }
                                this.Buffer[this.BufferN++] = c2;
                            }
                            c = this.readnext(bufferedReader);
                            String s2;
                            if (this.BufferN > 0) {
                                s2 = new String(this.Buffer, 0, this.BufferN);
                            }
                            else {
                                s2 = "";
                            }
                            if (!this.expand(action, s2)) {
                                action.addargument(s2);
                            }
                        }
                        node.addaction(action);
                        if (action.type().equals("B") || action.type().equals("W")) {
                            node.number(node.number() + 1);
                            break;
                        }
                        break;
                    }
                    else if (c < 'a' || c > 'z') {
                        throw new IOException();
                    }
                }
                c = this.readnext(bufferedReader);
            }
        }
    }
    
    boolean expand(final Action action, final String s) {
        final String type = action.type();
        if (!type.equals("MA") && !type.equals("SQ") && !type.equals("TR") && !type.equals("CR") && !type.equals("AW") && !type.equals("AB") && !type.equals("AE") && !type.equals("SL")) {
            return false;
        }
        if (s.length() != 5 || s.charAt(2) != ':') {
            return false;
        }
        final String substring = s.substring(0, 2);
        final String substring2 = s.substring(3);
        final int i = Field.i(substring);
        final int j = Field.j(substring);
        final int k = Field.i(substring2);
        final int l = Field.j(substring2);
        if (k < i || l < j) {
            return false;
        }
        for (int n = i; n <= k; ++n) {
            for (int n2 = j; n2 <= l; ++n2) {
                action.addargument(Field.string(n, n2));
            }
        }
        return true;
    }
    
    void readnodes(TreeNode lastChild, final BufferedReader bufferedReader) throws IOException {
        char c = this.readnext(bufferedReader);
        while (true) {
            if (c == ';') {
                c = this.readnode(lastChild, bufferedReader);
                if (!lastChild.haschildren()) {
                    continue;
                }
                lastChild = lastChild.lastChild();
            }
            else {
                if (c == '(') {
                    this.readnodes(lastChild, bufferedReader);
                }
                else if (c == ')') {
                    break;
                }
                c = this.readnext(bufferedReader);
            }
        }
    }
    
    public static Vector load(final BufferedReader bufferedReader, final BoardInterface gf) throws IOException {
        final Vector<SGFTree> vector = new Vector<SGFTree>();
        int n = 1;
    Label_0096:
        while (true) {
            final SGFTree sgfTree = new SGFTree(new Node(1));
            while (true) {
                char readchar;
                try {
                    readchar = sgfTree.readchar(bufferedReader);
                }
                catch (IOException ex) {
                    break Label_0096;
                }
                if (n != 0 && readchar == '(') {
                    sgfTree.GF = gf;
                    sgfTree.readnodes(sgfTree.History, bufferedReader);
                    vector.addElement(sgfTree);
                    break;
                }
                if (readchar == '\n') {
                    n = 1;
                }
                else {
                    n = 0;
                }
            }
        }
        return vector;
    }
    
    static Vector readnodes(XmlTree xmlTree, final BoardInterface gf) throws XmlReaderException {
        final Vector<SGFTree> vector = new Vector<SGFTree>();
        final Enumeration content = xmlTree.getContent();
        while (content.hasMoreElements()) {
            xmlTree = content.nextElement();
            final XmlTag tag = xmlTree.getTag();
            if (!(tag instanceof XmlTagPI)) {
                testTag(tag, "Go");
                final Enumeration content2 = xmlTree.getContent();
                while (content2.hasMoreElements()) {
                    xmlTree = content2.nextElement();
                    final XmlTag tag2 = xmlTree.getTag();
                    testTag(tag2, "GoGame");
                    if (tag2.hasParam("name")) {
                        SGFTree.GameName = tag2.getValue("name");
                    }
                    final Enumeration content3 = xmlTree.getContent();
                    if (!content3.hasMoreElements()) {
                        xmlMissing("Information");
                    }
                    final XmlTree xmlTree2 = content3.nextElement();
                    testTag(xmlTree2.getTag(), "Information");
                    getBoardSize(xmlTree2);
                    final SGFTree sgfTree = new SGFTree(new Node(1));
                    sgfTree.GF = gf;
                    final TreeNode readnodes = sgfTree.readnodes(content3, null, xmlTree, true, 1);
                    if (readnodes != null) {
                        setInformation(readnodes, xmlTree2);
                    }
                    if ((sgfTree.History = readnodes) != null) {
                        vector.addElement(sgfTree);
                    }
                }
            }
        }
        return vector;
    }
    
    public static void setInformation(final TreeNode treeNode, final XmlTree xmlTree) throws XmlReaderException {
        final Enumeration content = xmlTree.getContent();
        while (content.hasMoreElements()) {
            final XmlTree xmlTree2 = content.nextElement();
            final XmlTag tag = xmlTree2.getTag();
            if (tag.name().equals("BoardSize")) {
                treeNode.addaction(new Action("SZ", String.valueOf(SGFTree.BoardSize)));
            }
            else if (tag.name().equals("BlackPlayer")) {
                treeNode.addaction(new Action("PB", getText(xmlTree2)));
            }
            else if (tag.name().equals("BlackRank")) {
                treeNode.addaction(new Action("BR", getText(xmlTree2)));
            }
            else if (tag.name().equals("WhitePlayer")) {
                treeNode.addaction(new Action("PW", getText(xmlTree2)));
            }
            else if (tag.name().equals("WhiteRank")) {
                treeNode.addaction(new Action("WR", getText(xmlTree2)));
            }
            else if (tag.name().equals("Date")) {
                treeNode.addaction(new Action("DT", getText(xmlTree2)));
            }
            else if (tag.name().equals("Time")) {
                treeNode.addaction(new Action("TM", getText(xmlTree2)));
            }
            else if (tag.name().equals("Komi")) {
                treeNode.addaction(new Action("KM", getText(xmlTree2)));
            }
            else if (tag.name().equals("Result")) {
                treeNode.addaction(new Action("RE", getText(xmlTree2)));
            }
            else if (tag.name().equals("Handicap")) {
                treeNode.addaction(new Action("HA", getText(xmlTree2)));
            }
            else if (tag.name().equals("User")) {
                treeNode.addaction(new Action("US", getText(xmlTree2)));
            }
            else {
                if (!tag.name().equals("Copyright")) {
                    continue;
                }
                treeNode.addaction(new Action("CP", parseComment(xmlTree2)));
            }
        }
        if (!SGFTree.GameName.equals("")) {
            treeNode.addaction(new Action("GN", SGFTree.GameName));
        }
    }
    
    public static String getText(final XmlTree xmlTree) throws XmlReaderException {
        final Enumeration content = xmlTree.getContent();
        if (!content.hasMoreElements()) {
            return "";
        }
        final XmlTag tag = content.nextElement().getTag();
        if (!(tag instanceof XmlTagText) || content.hasMoreElements()) {
            throw new XmlReaderException("<" + xmlTree.getTag().name() + "> has wrong content.");
        }
        return ((XmlTagText)tag).getContent();
    }
    
    public static void getBoardSize(XmlTree xmlFirstContent) throws XmlReaderException {
        final Enumeration content = xmlFirstContent.getContent();
        SGFTree.BoardSize = 19;
        while (content.hasMoreElements()) {
            xmlFirstContent = content.nextElement();
            if (xmlFirstContent.getTag().name().equals("BoardSize")) {
                xmlFirstContent = xmlFirstContent.xmlFirstContent();
                final XmlTag tag = xmlFirstContent.getTag();
                if (tag instanceof XmlTagText) {
                    try {
                        SGFTree.BoardSize = Integer.parseInt(((XmlTagText)tag).getContent());
                        return;
                    }
                    catch (Exception ex) {
                        throw new XmlReaderException("Illegal <BoardSize>");
                    }
                }
                throw new XmlReaderException("Illegal <BoardSize>");
            }
        }
    }
    
    TreeNode readnodes(final Enumeration enumeration, TreeNode treeNode, XmlTree xmlTree, final boolean b, int number) throws XmlReaderException {
        TreeNode treeNode2 = null;
        while (enumeration.hasMoreElements()) {
            xmlTree = enumeration.nextElement();
            final XmlTag tag = xmlTree.getTag();
            if (tag.name().equals("Node")) {
                if (treeNode != null) {
                    number = ((Node)treeNode.content()).number();
                }
                final Node readnode = this.readnode(number, xmlTree);
                readnode.main(b);
                final TreeNode treeNode3 = new TreeNode(readnode);
                if (treeNode == null) {
                    treeNode2 = treeNode3;
                }
                if (treeNode != null) {
                    treeNode.addchild(treeNode3);
                }
                treeNode = treeNode3;
            }
            else {
                if (!tag.name().equals("Variation")) {
                    throw new XmlReaderException("Illegal Node or Variation <" + tag.name() + ">");
                }
                final TreeNode treeNode4 = (TreeNode)treeNode.parent();
                if (treeNode4 == null) {
                    throw new XmlReaderException("Root node cannot have variation");
                }
                treeNode4.addchild(this.readnodes(xmlTree.getContent(), null, xmlTree, false, 1));
            }
        }
        return treeNode2;
    }
    
    public Node readnode(final int n, final XmlTree xmlTree) throws XmlReaderException {
        final Node node = new Node(n);
        final XmlTag tag = xmlTree.getTag();
        if (tag.hasParam("name")) {
            node.addaction(new Action("N", tag.getValue("name")));
        }
        final Enumeration content = xmlTree.getContent();
        while (content.hasMoreElements()) {
            final XmlTree xmlTree2 = content.nextElement();
            final XmlTag tag2 = xmlTree2.getTag();
            if (tag2.name().equals("Black")) {
                node.addaction(new Action("B", this.xmlToSgf(xmlTree2)));
                node.number(node.number() + 1);
            }
            else if (tag2.name().equals("White")) {
                node.addaction(new Action("W", this.xmlToSgf(xmlTree2)));
                node.number(node.number() + 1);
            }
            else if (tag2.name().equals("AddBlack")) {
                node.expandaction(new Action("AB", this.xmlToSgf(xmlTree2)));
            }
            else if (tag2.name().equals("AddWhite")) {
                node.expandaction(new Action("AW", this.xmlToSgf(xmlTree2)));
            }
            else if (tag2.name().equals("Delete")) {
                node.expandaction(new Action("AE", this.xmlToSgf(xmlTree2)));
            }
            else if (tag2.name().equals("Mark")) {
                if (tag2.hasParam("type")) {
                    final String value = tag2.getValue("type");
                    if (value.equals("triangle")) {
                        node.expandaction(new Action("TR", this.xmlToSgf(xmlTree2)));
                    }
                    else if (value.equals("square")) {
                        node.expandaction(new Action("SQ", this.xmlToSgf(xmlTree2)));
                    }
                    else {
                        if (!value.equals("circle")) {
                            continue;
                        }
                        node.expandaction(new Action("CR", this.xmlToSgf(xmlTree2)));
                    }
                }
                else if (tag2.hasParam("label")) {
                    node.expandaction(new Action("LB", String.valueOf(this.xmlToSgf(xmlTree2)) + ":" + tag2.getValue("label")));
                }
                else if (tag2.hasParam("territory")) {
                    final String value2 = tag2.getValue("territory");
                    if (value2.equals("white")) {
                        node.expandaction(new Action("TW", this.xmlToSgf(xmlTree2)));
                    }
                    else {
                        if (!value2.equals("black")) {
                            continue;
                        }
                        node.expandaction(new Action("TB", this.xmlToSgf(xmlTree2)));
                    }
                }
                else {
                    node.expandaction(new MarkAction(this.xmlToSgf(xmlTree2), this.GF));
                }
            }
            else if (tag2.name().equals("BlackTimeLeft")) {
                node.addaction(new Action("BL", getText(xmlTree2)));
            }
            else if (tag2.name().equals("WhiteTimeLeft")) {
                node.addaction(new Action("WL", getText(xmlTree2)));
            }
            else if (tag2.name().equals("Comment")) {
                node.addaction(new Action("C", parseComment(xmlTree2)));
            }
            else {
                if (!tag2.name().equals("SGF")) {
                    continue;
                }
                if (!tag2.hasParam("type")) {
                    throw new XmlReaderException("Illegal <SGF> tag.");
                }
                Action action;
                if (tag2.getValue("type").equals("M")) {
                    action = new MarkAction(this.GF);
                }
                else {
                    action = new Action(tag2.getValue("type"));
                }
                final Enumeration content2 = xmlTree2.getContent();
                while (content2.hasMoreElements()) {
                    final XmlTree xmlTree3 = content2.nextElement();
                    if (!xmlTree3.getTag().name().equals("Arg")) {
                        throw new XmlReaderException("Illegal <SGF> tag.");
                    }
                    if (!xmlTree3.isText()) {
                        throw new XmlReaderException("Illegal <SGF> tag.");
                    }
                    action.addargument(xmlTree3.getText());
                }
                node.addaction(action);
            }
        }
        return node;
    }
    
    public static String parseComment(final XmlTree xmlTree) throws XmlReaderException {
        final StringBuffer sb = new StringBuffer();
        final Enumeration content = xmlTree.getContent();
        while (content.hasMoreElements()) {
            final XmlTree xmlTree2 = content.nextElement();
            final XmlTag tag = xmlTree2.getTag();
            if (tag.name().equals("P")) {
                if (!xmlTree2.haschildren()) {
                    sb.append("\n");
                }
                else {
                    final Vector wraplines = new StringParser(((XmlTagText)xmlTree2.xmlFirstContent().getTag()).getContent().replace('\n', ' ')).wraplines(1000);
                    for (int i = 0; i < wraplines.size(); ++i) {
                        sb.append(wraplines.elementAt(i));
                        sb.append("\n");
                    }
                }
            }
            else {
                if (!(tag instanceof XmlTagText)) {
                    throw new XmlReaderException("<" + tag.name() + "> not proper here.");
                }
                continue;
            }
        }
        return sb.toString();
    }
    
    public String xmlToSgf(final XmlTree xmlTree) throws XmlReaderException {
        final Enumeration content = xmlTree.getContent();
        if (!content.hasMoreElements()) {
            throw new XmlReaderException("Missing board position.");
        }
        final XmlTag tag = content.nextElement().getTag();
        if (!(tag instanceof XmlTagText)) {
            throw new XmlReaderException(String.valueOf(tag.name()) + " contains wrong board position.");
        }
        final String content2 = ((XmlTagText)tag).getContent();
        if (content2.length() < 2) {
            this.wrongBoardPosition(content2);
        }
        char upperCase = Character.toUpperCase(content2.charAt(0));
        if (upperCase >= 'J') {
            --upperCase;
        }
        final char c = (char)(upperCase - 'A');
        int int1 = 0;
        try {
            int1 = Integer.parseInt(content2.substring(1));
        }
        catch (Exception ex) {
            this.wrongBoardPosition(content2);
        }
        final int n = SGFTree.BoardSize - int1;
        if (c < '\0' || c >= SGFTree.BoardSize || n < 0 || n >= SGFTree.BoardSize) {
            this.wrongBoardPosition(content2);
        }
        return Field.string(c, n);
    }
    
    public void wrongBoardPosition(final String s) throws XmlReaderException {
        throw new XmlReaderException("Wrong Board Position " + s);
    }
    
    public static void xmlMissing(final String s) throws XmlReaderException {
        throw new XmlReaderException("Missing <" + s + ">");
    }
    
    public static void testTag(final XmlTag xmlTag, final String s) throws XmlReaderException {
        if (!xmlTag.name().equals(s)) {
            throw new XmlReaderException("<" + s + "> expected instead of <" + xmlTag.name() + ">");
        }
    }
    
    public static Vector load(final XmlReader xmlReader, final BoardInterface boardInterface) throws XmlReaderException {
        final XmlTree scan = xmlReader.scan();
        if (scan == null) {
            throw new XmlReaderException("Illegal file format");
        }
        return readnodes(scan, boardInterface);
    }
    
    void printtree(TreeNode firstChild, final PrintWriter printWriter) {
        printWriter.println("(");
        while (true) {
            firstChild.node().print(printWriter);
            if (!firstChild.haschildren()) {
                break;
            }
            if (firstChild.lastChild() != firstChild.firstChild()) {
                for (ListElement listElement = firstChild.children().first(); listElement != null; listElement = listElement.next()) {
                    this.printtree((TreeNode)listElement.content(), printWriter);
                }
                break;
            }
            firstChild = firstChild.firstChild();
        }
        printWriter.println(")");
    }
    
    void printtree(TreeNode treeNode, final XmlWriter xmlWriter, final int n, final boolean b) {
        if (b) {
            final String getaction = treeNode.getaction("GN");
            if (getaction != null && !getaction.equals("")) {
                xmlWriter.startTagNewLine("GoGame", "name", getaction);
            }
            else {
                xmlWriter.startTagNewLine("GoGame");
            }
            xmlWriter.startTagNewLine("Information");
            this.printInformation(xmlWriter, treeNode, "AP", "Application");
            this.printInformation(xmlWriter, treeNode, "SZ", "BoardSize");
            this.printInformation(xmlWriter, treeNode, "PB", "BlackPlayer");
            this.printInformation(xmlWriter, treeNode, "BR", "BlackRank");
            this.printInformation(xmlWriter, treeNode, "PW", "WhitePlayer");
            this.printInformation(xmlWriter, treeNode, "WR", "WhiteRank");
            this.printInformation(xmlWriter, treeNode, "DT", "Date");
            this.printInformation(xmlWriter, treeNode, "TM", "Time");
            this.printInformation(xmlWriter, treeNode, "KM", "Komi");
            this.printInformation(xmlWriter, treeNode, "RE", "Result");
            this.printInformation(xmlWriter, treeNode, "HA", "Handicap");
            this.printInformation(xmlWriter, treeNode, "US", "User");
            this.printInformationText(xmlWriter, treeNode, "CP", "Copyright");
            xmlWriter.endTagNewLine("Information");
        }
        else {
            xmlWriter.startTagNewLine("Variation");
        }
        while (true) {
            treeNode.node().print(xmlWriter, n);
            if (!treeNode.haschildren()) {
                break;
            }
            if (treeNode.lastChild() != treeNode.firstChild()) {
                final ListElement first = treeNode.children().first();
                treeNode = treeNode.firstChild();
                treeNode.node().print(xmlWriter, n);
                for (ListElement listElement = first.next(); listElement != null; listElement = listElement.next()) {
                    this.printtree((TreeNode)listElement.content(), xmlWriter, n, false);
                }
                if (!treeNode.haschildren()) {
                    break;
                }
            }
            treeNode = treeNode.firstChild();
        }
        if (b) {
            xmlWriter.endTagNewLine("GoGame");
            return;
        }
        xmlWriter.endTagNewLine("Variation");
    }
    
    public void printInformation(final XmlWriter xmlWriter, final TreeNode treeNode, final String s, final String s2) {
        final String getaction = treeNode.getaction(s);
        if (getaction != null && !getaction.equals("")) {
            xmlWriter.printTagNewLine(s2, getaction);
        }
    }
    
    public void printInformationText(final XmlWriter xmlWriter, final TreeNode treeNode, final String s, final String s2) {
        final String getaction = treeNode.getaction(s);
        if (getaction != null && !getaction.equals("")) {
            xmlWriter.startTagNewLine(s2);
            xmlWriter.printParagraphs(getaction, 60);
            xmlWriter.endTagNewLine(s2);
        }
    }
    
    public void print(final PrintWriter printWriter) {
        this.printtree(this.History, printWriter);
    }
    
    public void printXML(final XmlWriter xmlWriter) {
        this.printtree(this.History, xmlWriter, this.getSize(), true);
    }
    
    public int getSize() {
        try {
            return Integer.parseInt(this.History.getaction("SZ"));
        }
        catch (Exception ex) {
            return 19;
        }
    }
    
    static {
        SGFTree.BoardSize = 19;
        SGFTree.GameName = "";
    }
}
