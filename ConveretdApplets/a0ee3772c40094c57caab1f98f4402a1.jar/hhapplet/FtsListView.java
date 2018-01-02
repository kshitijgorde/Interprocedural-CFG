// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.util.Enumeration;
import java.net.URL;
import XMLConsumer.FtsTData;
import XMLConsumer.Fts;
import XMLConsumer.IEntry;
import java.awt.Image;
import java.awt.Graphics;
import XMLConsumer.FtsEntry;
import XMLConsumer.FtsWData;
import java.util.Vector;

public class FtsListView extends ListView implements IChunkedDataListener
{
    private static final String m_sFtsBreakChars = "\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·";
    private static final int m_nFtsBreakCharsNum;
    private Vector m_chunkedInfos;
    private String m_sSearchingKey;
    private String m_sBackSearchingKey;
    private int m_nCurrentOp;
    private boolean m_bFirst;
    private boolean m_bNot;
    private boolean m_bReady;
    private String m_sCurrentWord;
    private String m_sCheckKey;
    private int m_nIndexNum;
    private FtsWData[] m_aFtsContentsCon;
    private int[] m_aProj;
    private int m_nCheck;
    private Vector m_aTopicInfo;
    private Vector m_aTopicCheckInfo;
    private int m_nTopicCheck;
    
    private Vector mergeTopics(final Vector vector, final Vector vector2) {
        int n = 0;
        int n2 = 0;
        int size = 0;
        if (vector != null) {
            size = vector.size();
        }
        int size2 = 0;
        if (vector2 != null) {
            size2 = vector2.size();
        }
        final Vector<FtsEntry> vector3 = new Vector<FtsEntry>();
        while (n < size || n2 < size2) {
            if (n < size && n2 < size2) {
                if (Language.compare(vector.elementAt(n).getName(), vector2.elementAt(n2).getName()) < 0) {
                    vector3.addElement(vector.elementAt(n++));
                }
                else {
                    vector3.addElement(vector2.elementAt(n2++));
                }
            }
            else if (n < size) {
                vector3.addElement(vector.elementAt(n++));
            }
            else {
                if (n2 >= size2) {
                    continue;
                }
                vector3.addElement(vector2.elementAt(n2++));
            }
        }
        return vector3;
    }
    
    private void clearList() {
        this.setVerticalMax(0);
        this.m_aTopicInfo.removeAllElements();
        this.clearHightLighted();
        this.clearSelected();
    }
    
    private void findFTSKey() {
        this.clearList();
        this.m_nCurrentOp = 1;
        this.m_bNot = false;
        if (this.m_sSearchingKey != "") {
            this.m_bFirst = true;
            this.findOneKey();
        }
    }
    
    private void findOneKey() {
        if (this.m_sSearchingKey != "") {
            final String sSearchingKey = this.m_sSearchingKey;
            int n = -1;
            int n2 = -1;
            for (int i = 0; i < FtsListView.m_nFtsBreakCharsNum; ++i) {
                final int index = sSearchingKey.indexOf("\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·".charAt(i));
                if (index != -1 && (n == -1 || index < n)) {
                    n = index;
                    n2 = i;
                }
            }
            String substring;
            String substring2;
            if (n == -1) {
                substring = sSearchingKey;
                substring2 = "";
            }
            else {
                substring = sSearchingKey.substring(0, n);
                substring2 = sSearchingKey.substring(n + 1);
            }
            this.m_sSearchingKey = substring2;
            if (substring.compareTo("or") == 0 || (n2 >= 0 && "\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·".charAt(n2) == '|')) {
                this.m_nCurrentOp = 0;
                this.m_bNot = false;
            }
            else if (substring.compareTo("and") == 0 || (n2 >= 0 && "\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·".charAt(n2) == '&')) {
                this.m_nCurrentOp = 1;
                this.m_bNot = false;
            }
            else if (substring.compareTo("not") == 0 || (n2 >= 0 && "\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·".charAt(n2) == '~')) {
                this.m_bNot = !this.m_bNot;
            }
            else if (substring.length() != 0 && !Language.isStopWord(substring)) {
                this.m_sCurrentWord = Language.getStem(substring);
                this.ftsFindKeyword();
                return;
            }
            this.findOneKey();
            return;
        }
        this.displayTopics();
        this.checkAgain();
    }
    
    public void search(final String s) {
        if (this.m_sSearchingKey == null) {
            this.m_sSearchingKey = s.toLowerCase();
            this.setTimeout("findFTSKey", 1);
            return;
        }
        this.m_sBackSearchingKey = s.toLowerCase();
    }
    
    protected void listPaint(final Graphics graphics, final Image image) {
        this.setHorizontalMax(this.getWidth(graphics));
        int i;
        final int n = i = this.getTop();
        while (i < this.m_aTopicInfo.size()) {
            this.m_aTopicInfo.elementAt(i).display(graphics, i - n, this.getUnitHeight(), this.getBackground(), image);
            if (++i >= n + this.getVisible()) {
                break;
            }
        }
    }
    
    public FtsListView(final Vector chunkedInfos) {
        this.m_bFirst = true;
        this.m_bNot = false;
        this.m_bReady = false;
        this.m_chunkedInfos = chunkedInfos;
        this.m_aTopicInfo = new Vector();
        this.clearList();
    }
    
    private void checkAgain() {
        this.m_sCheckKey = "";
        this.m_nIndexNum = 0;
        this.m_sSearchingKey = this.m_sBackSearchingKey;
        this.m_sBackSearchingKey = null;
        if (this.m_sSearchingKey != null) {
            this.setTimeout("findFTSKey", 1);
        }
    }
    
    private Vector getTopics(final FtsWData ftsWData, final int n, final String s) {
        final Vector<Object> vector = new Vector<Object>();
        if (ftsWData != null) {
            final Vector topics = ftsWData.getTopics(s);
            if (topics != null) {
                for (int i = 0; i < topics.size(); ++i) {
                    final FtsTData chunkedTopicData = this.m_chunkedInfos.elementAt(n).getChunkedTopicData(topics.elementAt(i));
                    if (chunkedTopicData != null) {
                        if (!chunkedTopicData.isLoaded()) {
                            chunkedTopicData.load(this);
                            return null;
                        }
                        final Vector topics2 = chunkedTopicData.getTopics();
                        try {
                            if (topics2 != null) {
                                vector.addElement(topics2.elementAt(topics.elementAt(i) - chunkedTopicData.getBegin()));
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        return vector;
    }
    
    public void accept(final Vector vector) {
        if (vector.size() == 1) {
            BsscHelpRedirector.showDoc(vector.elementAt(0));
        }
    }
    
    public void dispatchToDo(final String s) {
        if (s.equals("findFTSKey")) {
            this.findFTSKey();
            return;
        }
        if (s.equals("ftsFindKeyword")) {
            this.ftsFindKeyword();
            return;
        }
        super.dispatchToDo(s);
    }
    
    public void putData(final IChunkedData chunkedData) {
        this.setTimeout("ftsFindKeyword", 1);
    }
    
    protected IEntry getEntryByPos(final int n) {
        if (this.m_aTopicInfo.size() > n) {
            return this.m_aTopicInfo.elementAt(n);
        }
        return null;
    }
    
    static {
        m_nFtsBreakCharsNum = "\t\r\n\"\\ .,!@#$%^&*()~'`:;<>?/{}[]|+-=\u0085\u0092\u0093\u0094\u0095\u0096\u0097\u0099©®·".length();
    }
    
    public void displayError(final String s) {
        this.clearList();
        this.m_aTopicInfo.addElement(new ErrEntry(s));
        this.setVerticalMax(this.m_aTopicInfo.size());
        this.repaint();
    }
    
    private void insertTopic(final FtsEntry ftsEntry) {
        int i = 0;
        int n = this.m_aTopicInfo.size() - 1;
        if (n < 0) {
            this.m_aTopicInfo.addElement(ftsEntry);
            return;
        }
        boolean b = false;
        int n2;
        FtsEntry ftsEntry2;
        do {
            n2 = i + n >> 1;
            ftsEntry2 = this.m_aTopicInfo.elementAt(n2);
            if (Language.compare(ftsEntry2.getName(), ftsEntry.getName()) > 0) {
                n = n2 - 1;
            }
            else {
                if (Language.compare(ftsEntry2.getName(), ftsEntry.getName()) >= 0) {
                    b = true;
                    break;
                }
                i = n2 + 1;
            }
        } while (i <= n);
        if (b) {
            this.m_aTopicInfo.insertElementAt(ftsEntry, n2);
            return;
        }
        if (Language.compare(ftsEntry2.getName(), ftsEntry.getName()) < 0) {
            this.m_aTopicInfo.insertElementAt(ftsEntry, n2 + 1);
            return;
        }
        this.m_aTopicInfo.insertElementAt(ftsEntry, n2);
    }
    
    protected int getWidth(final Graphics graphics) {
        int n = 0;
        final Enumeration<IEntry> elements = this.m_aTopicInfo.elements();
        while (elements.hasMoreElements()) {
            final int width = elements.nextElement().getWidth(graphics);
            if (width > n) {
                n = width;
            }
        }
        return n;
    }
    
    private void displayTopics() {
        if (this.m_aTopicInfo.size() == 0) {
            this.m_aTopicInfo.addElement(new ErrEntry(ResourceLib.GetRes("TopicsNotFound")));
        }
        this.repaint();
    }
    
    private boolean mergewithPreviousResult(final Vector vector) {
        if (vector != null && vector.size()) {
            final int size = vector.size();
            if (this.m_nCurrentOp == 0 || this.m_bFirst) {
                if (this.m_bNot) {
                    this.displayError(ResourceLib.GetRes("CantSearch"));
                    return false;
                }
                int n = 0;
                for (int i = 0; i < size; ++i) {
                    boolean b = false;
                    final FtsEntry ftsEntry = vector.elementAt(i);
                    for (int j = n; j < this.m_aTopicInfo.size(); ++j) {
                        if (ftsEntry.equalTo((FtsEntry)this.m_aTopicInfo.elementAt(j))) {
                            b = true;
                            n = j;
                            break;
                        }
                    }
                    if (!b) {
                        this.insertTopic(ftsEntry);
                    }
                }
            }
            else if (this.m_nCurrentOp == 1) {
                if (this.m_bNot) {
                    int n2 = 0;
                    for (int k = 0; k < size; ++k) {
                        final FtsEntry ftsEntry2 = vector.elementAt(k);
                        for (int l = n2; l < this.m_aTopicInfo.size(); ++l) {
                            if (ftsEntry2.equalTo((FtsEntry)this.m_aTopicInfo.elementAt(l))) {
                                this.m_aTopicInfo.removeElementAt(l);
                                n2 = l;
                                break;
                            }
                        }
                    }
                }
                else {
                    int n3 = 0;
                    for (int n4 = 0; n4 < this.m_aTopicInfo.size(); ++n4) {
                        boolean b2 = false;
                        final FtsEntry ftsEntry3 = this.m_aTopicInfo.elementAt(n4);
                        for (int n5 = n3; n5 < size; ++n5) {
                            if (ftsEntry3.equalTo(vector.elementAt(n5))) {
                                b2 = true;
                                n3 = n5;
                            }
                        }
                        if (!b2) {
                            this.m_aTopicInfo.removeElementAt(n4);
                            --n4;
                        }
                    }
                }
            }
            this.setVerticalMax(this.m_aTopicInfo.size());
        }
        else if (this.m_nCurrentOp == 1 && !this.m_bNot) {
            this.clearList();
        }
        else if (this.m_nCurrentOp == 0 && this.m_bNot) {
            this.displayError(ResourceLib.GetRes("CantSearch"));
            return false;
        }
        return true;
    }
    
    private void ftsFindKeyword() {
        final String sCurrentWord = this.m_sCurrentWord;
        final int size = this.m_chunkedInfos.size();
        boolean b = false;
        int nIndexNum = 0;
        if (sCurrentWord == null) {
            return;
        }
        FtsWData[] aFtsContentsCon;
        int[] aProj;
        if (this.m_sCheckKey == null || !sCurrentWord.equals(this.m_sCheckKey) || this.m_nIndexNum == 0) {
            aFtsContentsCon = new FtsWData[size];
            aProj = new int[size];
            this.m_nCheck = 0;
            this.m_nTopicCheck = 0;
            this.m_aTopicCheckInfo = null;
            this.m_sCheckKey = sCurrentWord;
        }
        else {
            nIndexNum = this.m_nIndexNum;
            aFtsContentsCon = this.m_aFtsContentsCon;
            aProj = this.m_aProj;
        }
        for (int i = this.m_nCheck; i < size; ++i) {
            final FtsWData chunkedData = this.m_chunkedInfos.elementAt(i).getChunkedData(sCurrentWord);
            if (chunkedData != null) {
                if (!chunkedData.isLoaded()) {
                    b = true;
                    this.m_nIndexNum = nIndexNum;
                    this.m_nCheck = i;
                    this.m_aFtsContentsCon = aFtsContentsCon;
                    this.m_aProj = aProj;
                    chunkedData.load(this);
                    break;
                }
                aProj[nIndexNum] = i;
                aFtsContentsCon[nIndexNum++] = chunkedData;
            }
        }
        if (!b) {
            Vector aTopicCheckInfo = this.m_aTopicCheckInfo;
            for (int j = this.m_nTopicCheck; j < size; ++j) {
                final Vector topics = this.getTopics(aFtsContentsCon[j], aProj[j], sCurrentWord);
                if (topics == null) {
                    this.m_nCheck = this.m_chunkedInfos.size();
                    this.m_nTopicCheck = j;
                    this.m_aTopicCheckInfo = aTopicCheckInfo;
                    this.m_aFtsContentsCon = aFtsContentsCon;
                    return;
                }
                if (j == 0) {
                    aTopicCheckInfo = topics;
                }
                else {
                    aTopicCheckInfo = this.mergeTopics(aTopicCheckInfo, topics);
                }
            }
            if (this.mergewithPreviousResult(aTopicCheckInfo)) {
                this.m_bFirst = false;
                this.findOneKey();
                return;
            }
            this.checkAgain();
        }
    }
}
