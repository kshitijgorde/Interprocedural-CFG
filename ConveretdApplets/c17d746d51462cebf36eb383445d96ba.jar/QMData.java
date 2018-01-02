import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Image;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMData implements Runnable
{
    private Thread loper;
    private int fileEncoding;
    private int status;
    private int atQuestion;
    private volatile int loaded;
    private volatile int totalQuestions;
    private Vector questions;
    private Vector scores;
    private Vector loadingApplet;
    private Vector loadingQuestion;
    private Vector loadingScore;
    private Image backgroundPicture;
    private QMButton ralink;
    private QMButton[] buttons;
    public QuizMaster qm;
    public Point startLoc;
    public Point startImage;
    public int maximumWidth;
    public int numberOfColumns;
    
    public QMData(final QuizMaster qm) {
        this.fileEncoding = 0;
        this.status = 0;
        this.atQuestion = 0;
        this.loaded = -1;
        this.totalQuestions = -1;
        this.questions = new Vector(10, 10);
        this.scores = new Vector(10, 10);
        this.loadingApplet = new Vector(10, 10);
        this.loadingQuestion = new Vector(10, 10);
        this.loadingScore = new Vector(10, 10);
        this.buttons = new QMButton[4];
        this.maximumWidth = 30;
        this.numberOfColumns = 1;
        this.qm = qm;
        final String parameter = qm.getParameter("FileEncoding");
        if (parameter != null && parameter.equalsIgnoreCase("Unicode")) {
            this.fileEncoding = 1;
        }
        final String parameter2 = qm.getParameter("ShowAnswersStatus");
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("AfterEachQuestion") || parameter2.equalsIgnoreCase("After Each Question")) {
                this.status = 1;
            }
            if (parameter2.equalsIgnoreCase("Not")) {
                this.status = 2;
            }
        }
        final String parameter3 = qm.getParameter("ShowRestartQuizButton");
        if (parameter3 != null && (parameter3.equalsIgnoreCase("Yes") || parameter3.equalsIgnoreCase("True"))) {
            this.status += 10;
        }
        int int1 = 10;
        int int2 = 10;
        try {
            int1 = Integer.parseInt(qm.getParameter("TextXPosition"));
        }
        catch (Exception ex) {}
        try {
            int2 = Integer.parseInt(qm.getParameter("TextYPosition"));
        }
        catch (Exception ex2) {}
        this.startLoc = new Point(int1, int2);
        int int3 = 10;
        int int4 = 10;
        try {
            int3 = Integer.parseInt(qm.getParameter("ImageXPosition"));
        }
        catch (Exception ex3) {}
        try {
            int4 = Integer.parseInt(qm.getParameter("ImageYPosition"));
        }
        catch (Exception ex4) {}
        this.startImage = new Point(int3, int4);
        try {
            this.numberOfColumns = Integer.parseInt(qm.getParameter("NumberOfColumns"));
        }
        catch (Exception ex5) {}
        if (this.numberOfColumns <= 0) {
            this.numberOfColumns = 1;
        }
        try {
            this.maximumWidth = Integer.parseInt(qm.getParameter("TextMaxWidth"));
        }
        catch (Exception ex6) {
            this.maximumWidth = qm.dim.width - 10 - this.startLoc.x;
        }
        QMLine.addLines(this.loadingApplet, qm.getParameter("TagLoadingApplet"), this.startLoc, this.maximumWidth, QuizMaster.fonts[0], QuizMaster.fms[0]);
        QMLine.addLines(this.loadingQuestion, qm.getParameter("TagLoadingQuestion"), this.startLoc, this.maximumWidth, QuizMaster.fonts[0], QuizMaster.fms[0]);
        QMLine.addLines(this.loadingScore, qm.getParameter("TagLoadingSolution"), this.startLoc, this.maximumWidth, QuizMaster.fonts[3], QuizMaster.fms[3]);
        (this.loper = new Thread(this)).start();
    }
    
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this.qm);
        final String parameter = this.qm.getParameter("BackgroundSkinFile");
        if (parameter != null && !parameter.equalsIgnoreCase("none")) {
            mediaTracker.addImage(this.backgroundPicture = this.qm.getImage(this.qm.getCodeBase(), parameter), 0);
        }
        final Image image = this.qm.getImage(this.qm.getCodeBase(), this.qm.getParameter("ButtonSkinFile"));
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(0);
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            this.qm.errorString = "Error loading BackgroundSkinFile or ButtonSkinFile";
            System.out.println(this.qm.errorString);
        }
        for (int i = 0; i < 3; ++i) {
            QMAnswer.pics[i] = this.qm.createImage(20, 20);
            QMAnswer.pics[i].getGraphics().drawImage(image, 0, 0, 20, 20, i * 20, 0, (i + 1) * 20, 20, this.qm);
        }
        int width = image.getWidth(this.qm);
        if (width <= 0) {
            width = 20;
            this.qm.errorString = "Error loading ButtonSkinFile";
            System.out.println(this.qm.errorString);
        }
        for (int j = 0; j < 2; ++j) {
            QMButton.pics[j] = this.qm.createImage(width, 20);
            QMButton.pics[j].getGraphics().drawImage(image, 0, 0, width, 20, 0, (j + 1) * 20, width, (j + 2) * 20, this.qm);
        }
        int int1 = this.qm.dim.width - width - 10;
        int int2 = this.qm.dim.height - 30;
        try {
            int1 = Integer.parseInt(this.qm.getParameter("ButtonsXPosition"));
        }
        catch (Exception ex3) {}
        try {
            int2 = Integer.parseInt(this.qm.getParameter("ButtonsYPosition"));
        }
        catch (Exception ex4) {}
        if (int1 > this.qm.dim.width - 20) {
            int1 = this.qm.dim.width - 20;
        }
        if (int2 > this.qm.dim.height - 20) {
            int2 = this.qm.dim.height - 20;
        }
        if (!this.qm.registered) {
            final QMButton qmButton = new QMButton(this, width);
            qmButton.setLocation(int1, int2);
            int2 -= 30;
            this.qm.add(qmButton);
            qmButton.repaint();
        }
        final String parameter2 = this.qm.getParameter("TagShowSolution");
        this.buttons[0] = new QMButton(this, (parameter2 == null) ? "" : parameter2, width, 0);
        final String parameter3 = this.qm.getParameter("TagNextSolution");
        this.buttons[1] = new QMButton(this, (parameter3 == null) ? "" : parameter3, width, 1);
        final String parameter4 = this.qm.getParameter("TagNextQuestion");
        this.buttons[2] = new QMButton(this, (parameter4 == null) ? "" : parameter4, width, 2);
        final String parameter5 = this.qm.getParameter("TagRestartQuiz");
        this.buttons[3] = new QMButton(this, (parameter5 == null) ? "" : parameter5, width, 3);
        for (int k = 0; k < this.buttons.length; ++k) {
            this.buttons[k].setLocation(int1, int2);
        }
        this.loaded = 0;
        this.qm.repaint();
        BufferedReader bufferedReader = null;
        final String parameter6 = this.qm.getParameter("QuizFile");
        try {
            if (parameter6 == null || parameter6.equals("")) {
                throw new Exception("Please fill in the QuizFile parameter");
            }
            switch (this.fileEncoding) {
                case 1: {
                    bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.qm.getCodeBase(), parameter6).openStream(), "Unicode"));
                    break;
                }
                default: {
                    bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.qm.getCodeBase(), parameter6).openStream()));
                    break;
                }
            }
            boolean b = true;
            this.readPart(bufferedReader, '<');
            final char c = (char)bufferedReader.read();
            if (c != 'Q' && c != 'q') {
                throw new Exception(parameter6 + " must begin with <Q>");
            }
            bufferedReader.read();
            while (b) {
                final Vector<String> vector = new Vector<String>(10, 10);
                Image image2 = null;
                int n = 0;
                String s = this.readPart(bufferedReader, '<');
                char c2 = (char)bufferedReader.read();
                if (c2 == 'I' || c2 == 'i') {
                    this.readPart(bufferedReader, '\"');
                    final String part = this.readPart(bufferedReader, '\"');
                    bufferedReader.read();
                    if (part == null || part.equals("") || part.indexOf(10) >= 0) {
                        throw new Exception("Picture name of question in wrong format");
                    }
                    try {
                        image2 = this.qm.getImage(this.qm.getCodeBase(), part);
                        mediaTracker.addImage(image2, this.questions.size() + 2);
                        mediaTracker.waitForID(this.questions.size() + 2);
                    }
                    catch (Exception ex5) {
                        throw new Exception("Error loading Picture: " + part);
                    }
                    s = this.readPart(bufferedReader, '<');
                    c2 = (char)bufferedReader.read();
                }
                if (c2 != 'A' && c2 != 'a') {
                    throw new Exception("Every <Q> needs a <A> inside");
                }
                this.readPart(bufferedReader, '<');
                boolean b2 = true;
                int n2 = 0;
                char c3 = (char)bufferedReader.read();
                while (b2) {
                    if (c3 == 'C' || c3 == 'c') {
                        n = n2;
                    }
                    bufferedReader.read();
                    bufferedReader.read();
                    vector.addElement(this.readPart(bufferedReader, '<'));
                    c3 = (char)bufferedReader.read();
                    if (c3 == '/') {
                        b2 = false;
                    }
                    else {
                        ++n2;
                    }
                }
                final char c4 = (char)bufferedReader.read();
                if (c4 != 'A' && c4 != 'a') {
                    throw new Exception("Every <A> needs to end with a </A>");
                }
                bufferedReader.read();
                this.questions.addElement(new QMQuestion(this, image2, s, vector, this.readPart(bufferedReader, '<'), n));
                this.readPart(bufferedReader, '<');
                final char c5 = (char)bufferedReader.read();
                if (c5 != 'Q' && c5 != 'q') {
                    b = false;
                    if (c5 != 'S' && c5 != 's') {
                        throw new Exception("Please end a question with </Q> and then start <Q> or <S>");
                    }
                    this.totalQuestions = this.loaded + 1;
                }
                ++this.loaded;
                this.qm.repaint();
                bufferedReader.read();
            }
            boolean b3 = true;
            while (b3) {
                Image image3 = null;
                String s2 = this.readPartNoTrim(bufferedReader, '<');
                char c6 = (char)bufferedReader.read();
                if (c6 == 'I' || c6 == 'i') {
                    this.readPart(bufferedReader, '\"');
                    final String part2 = this.readPart(bufferedReader, '\"');
                    bufferedReader.read();
                    if (part2 == null || part2.equals("") || part2.indexOf(10) >= 0) {
                        throw new Exception("Picture name of solution in wrong format");
                    }
                    try {
                        image3 = this.qm.getImage(this.qm.getCodeBase(), part2);
                        mediaTracker.addImage(image3, this.questions.size() + this.scores.size() + 2);
                        mediaTracker.waitForID(this.questions.size() + this.scores.size() + 2);
                    }
                    catch (Exception ex6) {
                        throw new Exception("Error loading Picture: " + part2);
                    }
                    s2 = this.readPartNoTrim(bufferedReader, '<');
                    c6 = (char)bufferedReader.read();
                }
                boolean b4 = true;
                if (c6 == '/' || c6 == 'M' || c6 == 'm') {
                    b4 = false;
                }
                else {
                    s2 = s2 + "<" + c6;
                }
                while (b4) {
                    s2 += this.readPartNoTrim(bufferedReader, '<');
                    c6 = (char)bufferedReader.read();
                    if (c6 == '/' || c6 == 'M' || c6 == 'm') {
                        b4 = false;
                    }
                    else {
                        s2 = s2 + "<" + c6;
                    }
                }
                if (s2.length() > 0 && s2.charAt(0) == '\n') {
                    s2 = s2.substring(1, s2.length());
                }
                if (s2.length() > 0 && s2.charAt(s2.length() - 1) == '\n') {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                int int3;
                if (c6 == '/') {
                    b3 = false;
                    int3 = this.totalQuestions + 1;
                }
                else {
                    this.readPart(bufferedReader, '\"');
                    try {
                        int3 = Integer.parseInt(this.readPart(bufferedReader, '\"'));
                        bufferedReader.read();
                    }
                    catch (Exception ex7) {
                        throw new Exception("Marks can only be numbers.");
                    }
                }
                this.scores.addElement(new QMScore(this, image3, s2, int3));
            }
        }
        catch (Exception ex) {
            this.qm.errorString = "Error loading File: " + parameter6 + " near " + ((this.totalQuestions >= 0) ? "solution" : ("question " + (this.questions.size() + 1))) + "\n" + ex;
            System.out.println(this.qm.errorString);
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException ex8) {}
        }
        this.loper = null;
        this.qm.repaint();
    }
    
    private String readPart(final BufferedReader bufferedReader, final char c) throws Exception {
        String s = this.readPartNoTrim(bufferedReader, c);
        if (s.length() > 0 && s.charAt(0) == '\n') {
            s = s.substring(1, s.length());
        }
        if (s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    private String readPartNoTrim(final BufferedReader bufferedReader, final char c) throws Exception {
        final StringBuffer sb = new StringBuffer();
        char c2 = (char)bufferedReader.read();
        while (c2 >= '\0' && c2 != c) {
            if (c2 != '&') {
                if (c2 != '\r') {
                    sb.append(c2);
                }
                c2 = (char)bufferedReader.read();
            }
            else {
                String s = "&";
                c2 = (char)bufferedReader.read();
                if (c2 == 'l') {
                    c2 = (char)bufferedReader.read();
                    if (c2 == 't') {
                        c2 = (char)bufferedReader.read();
                        if (c2 == ';') {
                            s = "<";
                            c2 = (char)bufferedReader.read();
                        }
                        else {
                            s = "&lt";
                        }
                    }
                    else {
                        s = "&l";
                    }
                }
                else if (c2 == 'a') {
                    c2 = (char)bufferedReader.read();
                    if (c2 == 'm') {
                        c2 = (char)bufferedReader.read();
                        if (c2 == 'p') {
                            c2 = (char)bufferedReader.read();
                            if (c2 == ';') {
                                c2 = (char)bufferedReader.read();
                            }
                            else {
                                s = "&amp";
                            }
                        }
                        else {
                            s = "&am";
                        }
                    }
                    else {
                        s = "&a";
                    }
                }
                sb.append(s);
            }
        }
        if (c2 < '\0') {
            throw new Exception("File not ended with </S>");
        }
        return sb.toString();
    }
    
    public void paint(final Graphics graphics) {
        if (this.loaded >= 0) {
            if (this.backgroundPicture != null) {
                graphics.drawImage(this.backgroundPicture, 0, 0, this.qm.dim.width, this.qm.dim.height, this.qm);
            }
            else {
                graphics.setColor(QuizMaster.colors[0]);
                graphics.fillRect(0, 0, this.qm.dim.width, this.qm.dim.height);
            }
            if (this.totalQuestions >= 0) {
                if (this.atQuestion < this.loaded) {
                    this.questions.elementAt(this.atQuestion).paint(graphics);
                }
                else if (this.loper == null) {
                    int n;
                    int score;
                    for (n = 0, score = this.getScore(); this.scores.elementAt(n).isNotScore(score); ++n) {}
                    this.scores.elementAt(n).paint(graphics, score, this.totalQuestions);
                }
                else {
                    for (int i = 0; i < this.loadingScore.size(); ++i) {
                        ((QMLine)this.loadingScore.elementAt(i)).paint(graphics, QuizMaster.colors[7]);
                    }
                }
            }
            else if (this.atQuestion < this.loaded) {
                this.questions.elementAt(this.atQuestion).paint(graphics);
            }
            else {
                for (int j = 0; j < this.loadingQuestion.size(); ++j) {
                    ((QMLine)this.loadingQuestion.elementAt(j)).paint(graphics, QuizMaster.colors[7]);
                }
            }
        }
        else {
            graphics.setColor(QuizMaster.colors[0]);
            graphics.fillRect(0, 0, this.qm.dim.width, this.qm.dim.height);
            for (int k = 0; k < this.loadingApplet.size(); ++k) {
                ((QMLine)this.loadingApplet.elementAt(k)).paint(graphics, QuizMaster.colors[7]);
            }
        }
    }
    
    public boolean mouseMoved(final Point point) {
        return this.atQuestion < this.loaded && this.questions.elementAt(this.atQuestion).mouseMoved(point);
    }
    
    public boolean mousePressed(final Point point) {
        if (this.atQuestion >= this.loaded) {
            return false;
        }
        if (this.questions.elementAt(this.atQuestion).mousePressed(point)) {
            synchronized (this) {
                switch (this.status % 10) {
                    case 0: {
                        ++this.atQuestion;
                        if (this.atQuestion == this.totalQuestions) {
                            this.qm.add(this.buttons[0]);
                            this.buttons[0].repaint();
                            break;
                        }
                        break;
                    }
                    case 1: {
                        this.qm.add(this.buttons[2]);
                        this.buttons[2].repaint();
                        break;
                    }
                    case 2: {
                        ++this.atQuestion;
                        if (this.atQuestion == this.totalQuestions && this.status >= 10) {
                            this.qm.add(this.buttons[3]);
                            this.buttons[3].repaint();
                            break;
                        }
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public void actionPerformed(final int n) {
        synchronized (this) {
            switch (n) {
                case 0: {
                    this.qm.remove(this.buttons[0]);
                    this.buttons[0].mouseExited(null);
                    this.qm.add(this.buttons[1]);
                    this.buttons[1].repaint();
                    this.atQuestion = 0;
                    break;
                }
                case 1: {
                    ++this.atQuestion;
                    if (this.atQuestion != this.totalQuestions) {
                        break;
                    }
                    this.qm.remove(this.buttons[1]);
                    this.buttons[1].mouseExited(null);
                    if (this.status >= 10) {
                        this.qm.add(this.buttons[3]);
                        this.buttons[3].repaint();
                        break;
                    }
                    break;
                }
                case 2: {
                    this.qm.remove(this.buttons[2]);
                    this.buttons[2].mouseExited(null);
                    ++this.atQuestion;
                    if (this.atQuestion == this.totalQuestions && this.status >= 10) {
                        this.qm.add(this.buttons[3]);
                        this.buttons[3].repaint();
                        break;
                    }
                    break;
                }
                case 3: {
                    this.qm.remove(this.buttons[3]);
                    this.buttons[3].mouseExited(null);
                    for (int i = 0; i < this.questions.size(); ++i) {
                        ((QMQuestion)this.questions.elementAt(i)).reset();
                    }
                    this.atQuestion = 0;
                    break;
                }
            }
        }
        this.qm.repaint();
    }
    
    private int getScore() {
        int n = 0;
        for (int i = 0; i < this.questions.size(); ++i) {
            n += ((QMQuestion)this.questions.elementAt(i)).getScore();
        }
        return n;
    }
}
