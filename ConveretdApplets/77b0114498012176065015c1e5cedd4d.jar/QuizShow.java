import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.TextEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.FontMetrics;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Component;
import java.util.Random;
import java.net.URL;
import java.awt.Button;
import java.awt.TextField;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.util.Vector;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.TextListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuizShow extends Applet implements Runnable, MouseListener, KeyListener, ActionListener, ItemListener, TextListener
{
    Thread me;
    Image offI;
    Graphics offG;
    MediaTracker tracker;
    AudioClip[] buzzerSounds;
    AudioClip[] rightAnswerSounds;
    AudioClip[] wrongAnswerSounds;
    public static final int WIDTH = 395;
    public static final int HEIGHT = 195;
    public static final int TOTALIMAGES = 4;
    public static final int TOTALSOUNDS = 10;
    public static final int TICK = 50;
    public static final Color darkGreen;
    public static final Color gold;
    public static final Color darkRed;
    public static final Color darkBlue;
    public static int TOTALQUESTIONS;
    int imageCount;
    int soundCount;
    int question;
    int player;
    int choiceCount;
    int answerCount;
    int numChoices;
    int numPlayersValue;
    Vector spriteList;
    Vector gameInfo;
    Vector questionList;
    Vector masterQuestionList;
    Graphic titleImage;
    Graphic[] lightImages;
    Text message;
    Text siteText;
    Text loadText;
    Text numPlayersText;
    Text namesText;
    Text buzzersText;
    Text questionNumberText;
    Text timerCountText;
    Text[] titleText;
    Text[] buzzersKeyText;
    Text[] questionsText;
    Text[] choicesText;
    Text[] nameText;
    Text[] scoreText;
    Text[] scoreScreenText;
    Shape background;
    Shape loadRect1;
    Shape loadRect2;
    Shape messageRect1;
    Shape messageRect2;
    Shape[] questionGrid;
    Shape[] answerGrid;
    Shape[] timerArc;
    Lights lights;
    boolean start;
    boolean buzz;
    boolean randomQuestions;
    boolean rightAnswerCheck;
    boolean[] buzzIn;
    Checkbox[] numPlayersC;
    CheckboxGroup numPlayers;
    TextField[] names;
    Button startB;
    int[] buzzers;
    int[] scores;
    URL questionsURL;
    char[] answers;
    Counter timer;
    Vector startGame;
    Vector buzzedIn;
    Vector checkAnswer;
    Vector wrongAnswer;
    Vector rightAnswer;
    Vector noOneBuzzed;
    Vector outOfTime;
    Vector gameOver;
    Vector winner;
    Random random;
    
    public final void init() {
        this.offI = this.createImage(395, 195);
        this.offG = this.offI.getGraphics();
        this.tracker = new MediaTracker(this);
        this.buzzerSounds = new AudioClip[4];
        this.rightAnswerSounds = new AudioClip[3];
        this.wrongAnswerSounds = new AudioClip[3];
        String parameter;
        if ((parameter = this.getParameter("file")) == null) {
            parameter = "questions.dat";
        }
        try {
            this.questionsURL = new URL(String.valueOf(this.getDocumentBase().toString().substring(0, this.getDocumentBase().toString().lastIndexOf(47) + 1)) + parameter);
        }
        catch (MalformedURLException ex) {}
        try {
            this.randomQuestions = (this.getParameter("random").charAt(0) == 'o' || this.getParameter("random").charAt(0) == 'O');
        }
        catch (Exception ex2) {
            this.randomQuestions = false;
        }
        this.questionList = new Vector();
        this.masterQuestionList = new Vector();
        this.spriteList = new Vector();
        this.gameInfo = new Vector();
        this.buzzIn = new boolean[4];
        (this.message = new Text()).set(new int[] { 1, 2, 16, 14 }, new Object[] { new Integer(97), new Integer(395), Sprite.ONE, Text.PT20 });
        (this.messageRect1 = new Shape()).set(new int[] { 0, 1, 2, 3, 13 }, new Object[] { new Integer(40), new Integer(80), new Integer(320), new Integer(40), Shape.RECTANGLE });
        (this.messageRect2 = new Shape()).set(new int[] { 0, 1, 2, 3, 13, 14 }, new Object[] { new Integer(45), new Integer(85), new Integer(320), new Integer(40), Shape.RECTANGLE, Color.black });
        (this.siteText = new Text()).set(new int[] { 1, 2, 7, 11, 13, 16 }, new Object[] { new Integer(175), new Integer(395), Sprite.TRUE, Sprite.SOUTH, "www.javaplayground.com", Sprite.ONE });
        (this.loadRect1 = new Shape()).set(new int[] { 0, 1, 2, 3, 7, 13, 14, 15 }, new Object[] { new Integer(10), new Integer(10), new Integer(375), new Integer(20), Sprite.TRUE, Shape.RECTANGLE, Color.green, Sprite.FALSE });
        (this.loadRect2 = new Shape()).set(new int[] { 0, 1, 3, 7, 13, 14 }, new Object[] { new Integer(10), new Integer(10), new Integer(20), Sprite.TRUE, Shape.RECTANGLE, Color.green });
        (this.loadText = new Text()).set(new int[] { 1, 2, 7, 16 }, new Object[] { new Integer(20), new Integer(395), Sprite.TRUE, Sprite.ONE });
        (this.background = new Shape()).set(new int[] { 7, 2, 3, 13, 14 }, new Object[] { Sprite.TRUE, new Integer(395), new Integer(195), Shape.RECTANGLE, Color.black });
        (this.titleImage = new Graphic()).set(new int[] { 2, 3, 7 }, new Object[] { new Integer(395), new Integer(195), Sprite.TRUE });
        final String s = "Quiz Show";
        this.titleText = new Text[s.length()];
        for (int i = 0; i < this.titleText.length; ++i) {
            (this.titleText[i] = new Text()).set(new int[] { 0, 1, 2, 7, 14, 13, 15, 16 }, new Object[] { new Integer(i * 40 + 20), new Integer((i % 2 == 0) ? 77 : 97), new Integer(40), Sprite.TRUE, Text.PT80, String.valueOf(s.charAt(i)), Color.red, Sprite.TWO });
        }
        this.titleText[0].xAnchor = Sprite.WEST;
        this.titleText[this.titleText.length - 1].set(new int[] { 0, 10 }, new Object[] { new Integer(this.titleText[this.titleText.length - 1].x - 5), Sprite.EAST });
        this.lightImages = new Graphic[44];
        for (int j = 0; j < this.lightImages.length; ++j) {
            (this.lightImages[j] = new Graphic()).set(new int[] { 2, 3, 7 }, new Object[] { new Integer(20), new Integer(20), Sprite.TRUE });
            if (j < 8) {
                this.lightImages[j].y = (7 - j) * 25;
            }
            else if (j < 22) {
                this.lightImages[j].x = (j - 8) * 25 + 25;
            }
            else if (j < 30) {
                this.lightImages[j].x = 375;
                this.lightImages[j].y = (j - 22) * 25;
            }
            else {
                this.lightImages[j].x = (13 - (j - 30)) * 25 + 25;
                this.lightImages[j].y = 175;
            }
        }
        this.lights = new Lights(this.lightImages);
        (this.numPlayersText = new Text()).set(new int[] { 0, 1, 2, 11, 7, 13, 15 }, new Object[] { new Integer(40), new Integer(20), new Integer(40), Sprite.NORTH, Sprite.TRUE, "# of Players", Color.red });
        this.numPlayers = new CheckboxGroup();
        this.numPlayersC = new Checkbox[4];
        for (int k = 0; k < this.numPlayersC.length; ++k) {
            this.setComponent(this.numPlayersC[k] = new Checkbox(String.valueOf(k + 1), k == 0, this.numPlayers), Color.black, Color.white, Text.PT12, new int[] { 40, 40 + k * 20, 40, 20 });
            this.numPlayersC[k].addItemListener(this);
        }
        this.numPlayersValue = 1;
        (this.namesText = new Text()).set(new int[] { 0, 1, 2, 11, 7, 13, 15 }, new Object[] { new Integer(100), new Integer(20), new Integer(150), Sprite.NORTH, Sprite.TRUE, "Player's Names", Color.red });
        this.names = new TextField[4];
        for (int l = 0; l < this.names.length; ++l) {
            this.setComponent(this.names[l] = new TextField("Player " + (l + 1)), Color.white, Color.blue, Text.PT12, new int[] { 100, 40 + l * 20, 150, 20 });
            this.names[l].addTextListener(this);
            this.names[l].setEnabled(l == 0);
        }
        (this.buzzersText = new Text()).set(new int[] { 0, 1, 2, 11, 7, 13, 15 }, new Object[] { new Integer(270), new Integer(20), new Integer(90), Sprite.NORTH, Sprite.TRUE, "Buzzer Key", Color.red });
        (this.buzzers = new int[4])[0] = 65;
        this.buzzers[1] = 32;
        this.buzzers[2] = 40;
        this.buzzers[3] = 96;
        this.buzzersKeyText = new Text[4];
        for (int n = 0; n < this.buzzersKeyText.length; ++n) {
            (this.buzzersKeyText[n] = new Text()).set(new int[] { 0, 1, 2, 7, 11, 15, 16 }, new Object[] { new Integer(270), new Integer(40 + n * 20), new Integer(90), Sprite.TRUE, Sprite.NORTH, Color.white, Sprite.ONE });
        }
        this.setBuzzerText();
        this.setComponent(this.startB = new Button("Play!"), QuizShow.darkGreen, Color.white, Text.PT20, new int[] { 150, 130, 100, 40 });
        this.startB.setActionCommand("p");
        this.startB.addActionListener(this);
        this.questionsText = new Text[4];
        for (int n2 = 0; n2 < this.questionsText.length; ++n2) {
            (this.questionsText[n2] = new Text()).set(new int[] { 0, 1, 10, 11, 7, 16 }, new Object[] { new Integer(40), new Integer(70 + n2 * 12), Sprite.WEST, Sprite.SOUTH, Sprite.TRUE, Sprite.ONE });
        }
        this.choicesText = new Text[8];
        for (int n3 = 0; n3 < this.choicesText.length; ++n3) {
            (this.choicesText[n3] = new Text()).set(new int[] { 0, 1, 10, 11, 7, 16 }, new Object[] { new Integer((n3 % 2 == 0) ? 25 : 205), new Integer(122 + n3 / 2 * 20), Sprite.WEST, Sprite.SOUTH, Sprite.TRUE, Sprite.ONE });
        }
        this.answers = new char[4];
        (this.questionNumberText = new Text()).set(new int[] { 0, 1, 10, 11, 7, 16, 15 }, new Object[] { new Integer(40), new Integer(50), Sprite.WEST, Sprite.SOUTH, Sprite.TRUE, Sprite.ONE, Color.red });
        this.timer = new Counter();
        this.timerArc = new Shape[2];
        for (int n4 = 0; n4 < this.timerArc.length; ++n4) {
            (this.timerArc[n4] = new Shape()).set(new int[] { 0, 1, 2, 3, 7, 13, 14, 15 }, new Object[] { new Integer(335), new Integer(0), new Integer(40), new Integer(40), Sprite.TRUE, Shape.ARC, (n4 == 0) ? Color.yellow : Color.black, (n4 == 0) ? Sprite.TRUE : Sprite.FALSE });
        }
        (this.timerCountText = new Text()).set(new int[] { 0, 1, 2, 7, 14, 16 }, new Object[] { new Integer(this.timerArc[0].x), new Integer(this.timerArc[0].y + this.timerArc[0].height / 3), new Integer(this.timerArc[0].width), Sprite.TRUE, Text.PT40, Sprite.TWO });
        this.questionGrid = new Shape[6];
        for (int n5 = 0; n5 < this.questionGrid.length; ++n5) {
            this.questionGrid[n5] = new Shape();
        }
        this.questionGrid[0].set(new int[] { 0, 1, 2, 3, 7, 13, 15, 14 }, new Object[] { new Integer(20), new Integer(20), new Integer(355), new Integer(155), Sprite.TRUE, Shape.RECTANGLE, Sprite.FALSE, Color.white });
        this.questionGrid[1].set(new int[] { 0, 1, 2, 7, 13, 14 }, new Object[] { new Integer(20), new Integer(95), new Integer(355), Sprite.TRUE, Shape.LINE, Color.white });
        this.questionGrid[2].set(new int[] { 0, 1, 2, 7, 13, 14 }, new Object[] { new Integer(20), new Integer(115), new Integer(355), Sprite.TRUE, Shape.LINE, Color.white });
        this.questionGrid[3].set(new int[] { 0, 1, 2, 7, 13, 14 }, new Object[] { new Integer(20), new Integer(135), new Integer(355), Sprite.TRUE, Shape.LINE, Color.white });
        this.questionGrid[4].set(new int[] { 0, 1, 2, 7, 13, 14 }, new Object[] { new Integer(20), new Integer(155), new Integer(355), Sprite.TRUE, Shape.LINE, Color.white });
        this.questionGrid[5].set(new int[] { 0, 1, 3, 7, 13, 14 }, new Object[] { new Integer(200), new Integer(95), new Integer(80), Sprite.TRUE, Shape.LINE, Color.white });
        this.answerGrid = new Shape[8];
        for (int n6 = 0; n6 < this.answerGrid.length; ++n6) {
            (this.answerGrid[n6] = new Shape()).set(new int[] { 0, 1, 2, 3, 13, 14 }, new Object[] { new Integer((n6 % 2 == 0) ? 20 : 200), new Integer(n6 / 2 * 20 + 95), new Integer((n6 % 2 == 0) ? 180 : 175), new Integer(20), Shape.RECTANGLE, Color.blue });
        }
        this.random = new Random();
        this.scores = new int[4];
        this.scoreScreenText = new Text[2];
        (this.scoreScreenText[0] = new Text()).set(new int[] { 0, 1, 2, 7, 13, 14 }, new Object[] { new Integer(20), new Integer(60), new Integer(180), Sprite.TRUE, "Player", Text.PT20 });
        (this.scoreScreenText[1] = new Text()).set(new int[] { 0, 1, 2, 7, 13, 14, 15 }, new Object[] { new Integer(200), new Integer(60), new Integer(180), Sprite.TRUE, "Score", Text.PT20, Color.red });
        this.nameText = new Text[4];
        for (int n7 = 0; n7 < this.nameText.length; ++n7) {
            (this.nameText[n7] = new Text()).set(new int[] { 0, 1, 10 }, new Object[] { new Integer(40), new Integer(n7 * 20 + 80), Sprite.WEST });
        }
        this.scoreText = new Text[4];
        for (int n8 = 0; n8 < this.scoreText.length; ++n8) {
            (this.scoreText[n8] = new Text()).set(new int[] { 0, 1, 2, 15 }, new Object[] { new Integer(200), new Integer(n8 * 20 + 80), new Integer(180), Color.red });
        }
        (this.startGame = new Vector(3)).addElement("Let's get ready to rumble!");
        this.startGame.addElement("Are you ready?");
        this.startGame.addElement("Welcome to Quiz Show!");
        (this.buzzedIn = new Vector(3)).addElement("NAME can use the buzzer!");
        this.buzzedIn.addElement("NAME is the quickest.");
        this.buzzedIn.addElement("NAME get's the jump...");
        (this.checkAnswer = new Vector(3)).addElement("Survey says...");
        this.checkAnswer.addElement("The results are in.");
        this.checkAnswer.addElement("What say you jury?");
        (this.wrongAnswer = new Vector(3)).addElement("Oooh, not even close NAME...");
        this.wrongAnswer.addElement("NAME, you must be kidding.");
        this.wrongAnswer.addElement("Stop wasting my time NAME.");
        (this.rightAnswer = new Vector(3)).addElement("Look at the big brain on NAME!");
        this.rightAnswer.addElement("Gee, you're so smart NAME.");
        this.rightAnswer.addElement("How do you do it NAME?");
        (this.noOneBuzzed = new Vector(3)).addElement("Are we all still awake?");
        this.noOneBuzzed.addElement("Duh, hello?");
        this.noOneBuzzed.addElement("Stop picking your noses!");
        (this.outOfTime = new Vector(3)).addElement("Way to be on the ball NAME.");
        this.outOfTime.addElement("NAME needs a pill.");
        this.outOfTime.addElement("Somebody wake up NAME");
        (this.gameOver = new Vector(3)).addElement("Well, uh, that was fun...");
        this.gameOver.addElement("That's all she wrote.");
        this.gameOver.addElement("The fat lady has sung...");
        (this.winner = new Vector(3)).addElement("NAME is the champion!");
        this.winner.addElement("NAME rules the world!");
        this.winner.addElement("Bow to NAME!");
    }
    
    public final void setComponent(final Component component, final Color background, final Color foreground, final Font font, final int[] array) {
        component.setBackground(background);
        component.setForeground(foreground);
        component.setFont(font);
        component.setBounds(array[0], array[1], array[2], array[3]);
    }
    
    public final void addComponents(final Component[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.add(array[i]);
        }
    }
    
    public final void removeComponents(final Component[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.remove(array[i]);
        }
    }
    
    public final String getQuestionNumber(final String s) {
        int index = s.indexOf("n", s.indexOf("Question"));
        ++index;
        String string = "";
        for (int i = index; i < s.length(); ++i) {
            string = String.valueOf(string) + s.charAt(i);
        }
        return string;
    }
    
    public final void readFile() {
        this.spriteList.removeAllElements();
        this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, "Loading Game Questions..." });
        this.paintAndPause();
        boolean b = false;
        try {
            QuizShow.TOTALQUESTIONS = Integer.parseInt(this.getParameter("questions"));
        }
        catch (Exception ex) {
            QuizShow.TOTALQUESTIONS = 0;
            b = true;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.questionsURL.openConnection().getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.gameInfo.addElement(line);
                if (line.indexOf("Question") >= 0) {
                    if (b) {
                        ++QuizShow.TOTALQUESTIONS;
                    }
                    this.masterQuestionList.addElement(this.getQuestionNumber(line));
                }
            }
            bufferedReader.close();
            if (QuizShow.TOTALQUESTIONS > this.masterQuestionList.size() || QuizShow.TOTALQUESTIONS < 1) {
                QuizShow.TOTALQUESTIONS = this.masterQuestionList.size();
            }
        }
        catch (Exception ex2) {
            this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, "Error Reading Data File" });
            this.paintAndPause();
            this.stop();
        }
        this.message.visible = false;
    }
    
    public final void parseString(final Vector vector, final String s) {
        vector.removeAllElements();
        this.gameInfo.removeElement(s);
        int n = s.indexOf("=");
        int n2 = n + 1;
        while ((n = s.indexOf("|", n + 1)) > 0) {
            vector.addElement(this.cutString(s.substring(n2, n).trim(), this.messageRect1.width, this.message.font).elementAt(0));
            n2 = n + 1;
        }
        vector.addElement(s.substring(n2).trim());
    }
    
    public final void loadText() {
        for (int i = 0; i < this.gameInfo.size(); ++i) {
            final String s = this.gameInfo.elementAt(i);
            if (s.indexOf("STARTGAME") >= 0) {
                this.parseString(this.startGame, s);
            }
            else if (s.indexOf("BUZZEDIN") >= 0) {
                this.parseString(this.buzzedIn, s);
            }
            else if (s.indexOf("CHECKANSWER") >= 0) {
                this.parseString(this.checkAnswer, s);
            }
            else if (s.indexOf("RIGHTANSWER") >= 0) {
                this.parseString(this.rightAnswer, s);
            }
            else if (s.indexOf("WRONGANSWER") >= 0) {
                this.parseString(this.wrongAnswer, s);
            }
            else if (s.indexOf("NOONEBUZZED") >= 0) {
                this.parseString(this.noOneBuzzed, s);
            }
            else if (s.indexOf("OUTOFTIME") >= 0) {
                this.parseString(this.outOfTime, s);
            }
            else if (s.indexOf("GAMEOVER") >= 0) {
                this.parseString(this.gameOver, s);
            }
            else if (s.indexOf("WINNER") >= 0) {
                this.parseString(this.winner, s);
            }
        }
    }
    
    public final Vector cutString(final String s, final int n, final Font font) {
        final Vector<String> vector = new Vector<String>();
        int i = 0;
        int n2 = 0;
        int length = 0;
        this.offG.setFont(font);
        final FontMetrics fontMetrics = this.offG.getFontMetrics();
        while (i >= 0) {
            while (i >= 0 && fontMetrics.stringWidth(s.substring(length, i)) < n) {
                n2 = i;
                i = s.indexOf(" ", i + 1);
            }
            if (fontMetrics.stringWidth(s.substring(length, s.length())) < n) {
                vector.addElement(s.substring(length, s.length()));
                length = s.length();
            }
            else {
                vector.addElement(s.substring(length, n2));
                length = n2;
            }
            if (i >= 0) {
                i = n2;
            }
        }
        if (length < s.length()) {
            vector.addElement(s.substring(length, s.length()));
        }
        return vector;
    }
    
    public final void parseStrings(final Text[] array, final Vector vector) {
        int n;
        for (n = 0; n < array.length && n < vector.size(); ++n) {
            array[n].set(new int[] { 6, 13 }, new Object[] { Sprite.TRUE, vector.elementAt(n).trim() });
        }
        for (int i = n; i < array.length; ++i) {
            array[i].set(new int[] { 6, 13 }, new Object[] { Sprite.FALSE, "" });
        }
    }
    
    public final void trimString(final Text text, final int n) {
        this.offG.setFont(text.font);
        FontMetrics fontMetrics;
        int length;
        for (fontMetrics = this.offG.getFontMetrics(), length = text.text.length(); length > 0 && fontMetrics.stringWidth(text.text.substring(0, length)) > n; --length) {}
        text.text = text.text.substring(0, length);
    }
    
    public final void readQuestion() {
        String string = "";
        int n;
        int n2;
        for (n = 0, n2 = (this.randomQuestions ? Math.abs(this.random.nextInt() % this.questionList.size()) : this.question); n < this.gameInfo.size() && this.gameInfo.elementAt(n).indexOf("Question" + (String)this.questionList.elementAt(n2)) < 0; ++n) {}
        if (this.randomQuestions) {
            this.questionList.removeElementAt(n2);
        }
        ++n;
        String s;
        while (n < this.gameInfo.size() && (s = this.gameInfo.elementAt(n)).indexOf("Choice") < 0) {
            string = String.valueOf(string) + s;
            ++n;
        }
        this.parseStrings(this.questionsText, this.cutString(string, 315, Text.PT12));
        ++n;
        this.numChoices = 0;
        String text;
        while (n < this.gameInfo.size() && this.numChoices < this.choicesText.length && (text = this.gameInfo.elementAt(n)).indexOf("Answer") < 0) {
            this.choicesText[this.numChoices].text = text;
            ++n;
            ++this.numChoices;
        }
        final Vector vector = new Vector<Integer>(this.numChoices);
        final int[] array = new int[this.numChoices];
        final String[] array2 = new String[this.numChoices];
        for (int i = 0; i < this.numChoices; ++i) {
            vector.addElement(new Integer(i));
            array2[i] = this.choicesText[i].text;
        }
        int j;
        for (j = 0; j < this.numChoices; ++j) {
            final int abs = Math.abs(this.random.nextInt() % vector.size());
            this.choicesText[j].set(new int[] { 6, 13 }, new Object[] { Sprite.TRUE, array2[vector.elementAt(abs)] });
            array[vector.elementAt(abs)] = j;
            vector.removeElementAt(abs);
            this.trimString(this.choicesText[j], 170);
        }
        for (int k = 0; k < j; ++k) {
            this.choicesText[k].text = (char)(65 + k) + ". " + this.choicesText[k].text;
        }
        for (int l = this.numChoices; l < this.choicesText.length; ++l) {
            this.choicesText[l].set(new int[] { 6, 13 }, new Object[] { Sprite.FALSE, "" });
        }
        ++n;
        String string2 = "";
        String s2;
        while (n < this.gameInfo.size() && (s2 = this.gameInfo.elementAt(n)).indexOf("Question") < 0) {
            string2 = String.valueOf(string2) + s2;
            ++n;
        }
        this.answerCount = 0;
        while (this.answerCount < this.answers.length && this.answerCount < string2.length()) {
            final char char1 = string2.charAt(this.answerCount);
            if ((char1 >= 'a' && char1 <= 'h') || (char1 >= 'A' && char1 <= 'H')) {
                this.answers[this.answerCount] = (char)(97 + array[String.valueOf(char1).toLowerCase().charAt(0) - 'a']);
            }
            ++this.answerCount;
        }
        for (int answerCount = this.answerCount; answerCount < this.answers.length; ++answerCount) {
            this.answers[answerCount] = 'z';
        }
    }
    
    public final void loadImage(final Image image) {
        try {
            this.tracker.addImage(image, this.imageCount);
            this.tracker.waitForAll();
            ++this.imageCount;
            this.loadText.text = "Loading Images(" + 100 * this.imageCount / 4 + "%)...";
            this.loadRect2.width = this.loadRect1.width * this.imageCount / 4;
            this.paintAndPause();
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final Image cutImage(final Image image, final int n, final int n2, final int n3, final int n4) {
        return this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n, n2, n3, n4)));
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paintAndPause(final int n) {
        for (int i = 0; i < n / 50; ++i) {
            this.paintAndPause();
        }
    }
    
    public final void paintAndPause() {
        try {
            this.lights.increment();
            this.background.draw(this.offG);
            for (int i = 0; i < this.spriteList.size(); ++i) {
                ((Sprite)this.spriteList.elementAt(i)).draw(this.offG);
            }
            this.messageRect2.draw(this.offG);
            this.messageRect1.draw(this.offG);
            this.message.draw(this.offG);
            this.repaint();
            Thread.sleep(50L);
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public final void loadImages() {
        this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, "Loading Images..." });
        this.spriteList.addElement(this.loadRect1);
        this.spriteList.addElement(this.loadRect2);
        this.spriteList.addElement(this.loadText);
        this.loadImage(this.titleImage.image = this.getImage(this.getDocumentBase(), "images/title.gif"));
        final Image image;
        this.loadImage(image = this.getImage(this.getDocumentBase(), "images/lights.gif"));
        this.loadImage(this.lights.lightOff = this.cutImage(image, 0, 0, 20, 20));
        this.loadImage(this.lights.lightOn = this.cutImage(image, 0, 20, 20, 20));
        for (int i = 0; i < this.lightImages.length; ++i) {
            if (i % 2 == 0) {
                this.lightImages[i].image = this.lights.lightOff;
            }
            else {
                this.lightImages[i].image = this.lights.lightOn;
            }
        }
        this.message.visible = false;
    }
    
    public final void loadSound(final AudioClip audioClip) {
        try {
            audioClip.stop();
            ++this.soundCount;
            this.loadText.text = "Loading Sounds(" + 100 * this.soundCount / 10 + "%)...";
            this.loadRect2.width = this.loadRect1.width * this.soundCount / 10;
            this.paintAndPause();
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void loadSounds() {
        this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, "Loading Sounds..." });
        for (int i = 0; i < this.buzzerSounds.length; ++i) {
            this.loadSound(this.buzzerSounds[i] = this.getAudioClip(this.getDocumentBase(), "sounds/player" + (i + 1) + ".au"));
        }
        for (int j = 0; j < this.rightAnswerSounds.length; ++j) {
            this.loadSound(this.rightAnswerSounds[j] = this.getAudioClip(this.getDocumentBase(), "sounds/right" + (j + 1) + ".au"));
        }
        for (int k = 0; k < this.wrongAnswerSounds.length; ++k) {
            this.loadSound(this.wrongAnswerSounds[k] = this.getAudioClip(this.getDocumentBase(), "sounds/wrong" + (k + 1) + ".au"));
        }
        this.message.visible = false;
    }
    
    public final void displayStartScreen() {
        this.resetSpriteList();
        this.spriteList.addElement(this.siteText);
        for (int i = 0; i < this.titleText.length; ++i) {
            this.spriteList.addElement(this.titleText[i]);
        }
        this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, "Click Anywhere to Start..." });
    }
    
    public final void displayPlayerScreen() {
        this.resetSpriteList();
        for (int i = 0; i < this.buzzersKeyText.length; ++i) {
            this.spriteList.addElement(this.buzzersKeyText[i]);
        }
        this.spriteList.addElement(this.numPlayersText);
        this.spriteList.addElement(this.namesText);
        this.spriteList.addElement(this.buzzersText);
        this.addComponents(this.numPlayersC);
        this.addComponents(this.names);
        this.add(this.startB);
        this.message.visible = false;
    }
    
    public final void setQuestionScreen() {
        this.resetSpriteList();
        for (int i = 0; i < this.answerGrid.length; ++i) {
            this.spriteList.addElement(this.answerGrid[i]);
        }
        for (int j = 0; j < this.questionGrid.length; ++j) {
            this.spriteList.addElement(this.questionGrid[j]);
        }
        this.questionNumberText.text = "Question " + (this.question + 1);
        this.spriteList.addElement(this.questionNumberText);
        for (int k = 0; k < this.questionsText.length; ++k) {
            this.questionsText[k].visible = false;
            this.spriteList.addElement(this.questionsText[k]);
        }
        for (int l = 0; l < this.choicesText.length; ++l) {
            this.choicesText[l].visible = false;
            this.spriteList.addElement(this.choicesText[l]);
        }
        for (int n = 0; n < this.timerArc.length; ++n) {
            this.spriteList.addElement(this.timerArc[n]);
        }
        this.spriteList.addElement(this.timerCountText);
    }
    
    public final void resetSpriteList() {
        this.spriteList.removeAllElements();
        this.spriteList.addElement(this.titleImage);
        for (int i = 0; i < this.lightImages.length; ++i) {
            this.spriteList.addElement(this.lightImages[i]);
        }
    }
    
    public final void displayMessage(final Vector vector, final Color color, final int n) {
        this.displayMessage(vector.elementAt(Math.abs(this.random.nextInt() % vector.size())), color, n);
    }
    
    public final void displayMessage(String string, final Color color, final int n) {
        final int index;
        if ((index = string.indexOf("NAME")) >= 0) {
            string = String.valueOf(string.substring(0, index)) + this.names[this.player].getText() + string.substring(index + 4);
        }
        this.message.set(new int[] { 7, 13 }, new Object[] { Sprite.TRUE, string });
        this.messageRect1.set(new int[] { 7, 14 }, new Object[] { Sprite.TRUE, color });
        this.messageRect2.visible = true;
        this.paintAndPause(n);
        this.message.visible = false;
        this.messageRect1.visible = false;
        this.messageRect2.visible = false;
    }
    
    public final boolean runTimer(final int n) {
        this.timer.set(n, 20, true, true);
        this.timer.reset();
        this.timerCountText.visible = true;
        for (int i = 0; i < this.timerArc.length; ++i) {
            this.timerArc[i].visible = true;
        }
        this.addKeyListener(this);
        this.requestFocus();
        while (!this.timer.toggle) {
            this.timer.increment();
            for (int j = 0; j < this.timerArc.length; ++j) {
                this.timerArc[j].arcAngle = 360 - this.timer.count * 360 / this.timer.maxCount;
            }
            this.timerCountText.set(new int[] { 13, 15 }, new Object[] { String.valueOf(this.timer.maxCount - this.timer.count), (this.timer.count < this.timer.maxCount - 3) ? Color.green : Color.red });
            this.paintAndPause();
        }
        this.removeKeyListener(this);
        this.timerCountText.visible = false;
        for (int k = 0; k < this.timerArc.length; ++k) {
            this.timerArc[k].visible = false;
        }
        return this.timer.count < this.timer.maxCount;
    }
    
    public final boolean allPlayersWrong() {
        boolean b = true;
        for (int i = 0; i < this.buzzIn.length; ++i) {
            if (this.buzzIn[i]) {
                b = false;
            }
        }
        return b;
    }
    
    public final void waitForBuzzer() {
        this.rightAnswerCheck = false;
        while (!this.rightAnswerCheck && !this.allPlayersWrong()) {
            this.buzz = true;
            if (this.runTimer(20)) {
                this.buzz = false;
                this.displayMessage(this.buzzedIn, QuizShow.gold, 1000);
                this.rightAnswerCheck = this.waitForAnswer();
            }
            else {
                this.displayMessage(this.noOneBuzzed, QuizShow.darkRed, 1000);
                this.rightAnswerCheck = true;
            }
        }
    }
    
    public final boolean checkAnswer(final int n) {
        for (int i = 0; i < this.answers.length; ++i) {
            if (this.answers[i] - 'a' == n) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean waitForAnswer() {
        this.choiceCount = 0;
        boolean b = false;
        if (this.runTimer(10)) {
            this.paintAndPause(500);
            this.displayMessage(this.checkAnswer, QuizShow.darkBlue, 1000);
            this.paintAndPause(500);
            b = true;
            for (int i = 0; i < this.answerGrid.length; ++i) {
                if (this.answerGrid[i].visible) {
                    if (this.checkAnswer(i)) {
                        this.rightAnswerSounds[Math.abs(this.random.nextInt() % this.rightAnswerSounds.length)].play();
                        this.answerGrid[i].color = Color.green;
                    }
                    else {
                        b = false;
                        this.wrongAnswerSounds[Math.abs(this.random.nextInt() % this.wrongAnswerSounds.length)].play();
                        this.answerGrid[i].color = Color.red;
                    }
                    this.paintAndPause(500);
                }
            }
            if (b) {
                this.displayMessage(this.rightAnswer, QuizShow.darkGreen, 2000);
                final int[] scores = this.scores;
                final int player = this.player;
                ++scores[player];
                this.scoreText[this.player].text = String.valueOf(this.scores[this.player]);
                b = true;
            }
            else {
                this.displayMessage(this.wrongAnswer, QuizShow.darkRed, 2000);
                final int[] scores2 = this.scores;
                final int player2 = this.player;
                --scores2[player2];
                this.scoreText[this.player].text = String.valueOf(this.scores[this.player]);
            }
        }
        else {
            this.displayMessage(this.outOfTime, QuizShow.darkRed, 2000);
        }
        for (int j = 0; j < this.answerGrid.length; ++j) {
            this.answerGrid[j].set(new int[] { 7, 14 }, new Object[] { Sprite.FALSE, Color.blue });
        }
        this.buzzIn[this.player] = false;
        return b;
    }
    
    public final void displayScoreScreen() {
        this.resetSpriteList();
        for (int i = 0; i < this.scoreScreenText.length; ++i) {
            this.spriteList.addElement(this.scoreScreenText[i]);
        }
        final Vector vector = new Vector<Text>();
        final Vector<Integer> vector2 = new Vector<Integer>();
        final Vector<Text> vector3 = new Vector<Text>();
        for (int j = 0; j < this.numPlayersValue; ++j) {
            vector.addElement(this.nameText[j]);
            vector2.addElement(new Integer(this.scores[j]));
            vector3.addElement(this.scoreText[j]);
        }
        int n = 0;
        while (vector.size() > 0) {
            int n2 = 0;
            int n3 = vector2.elementAt(0);
            for (int k = 0; k < vector.size(); ++k) {
                if (vector2.elementAt(k) > n3) {
                    n3 = vector2.elementAt(k);
                    n2 = k;
                }
            }
            vector.elementAt(n2).y = n * 20 + 80;
            vector3.elementAt(n2).y = n * 20 + 80;
            this.spriteList.addElement(vector.elementAt(n2));
            this.spriteList.addElement(vector3.elementAt(n2));
            vector.removeElementAt(n2);
            vector3.removeElementAt(n2);
            vector2.removeElementAt(n2);
            ++n;
        }
    }
    
    public final void run() {
        try {
            this.loadImages();
            this.loadSounds();
            this.readFile();
            this.loadText();
            while (true) {
                this.lights.set(0);
                this.displayStartScreen();
                this.start = false;
                this.addMouseListener(this);
                while (!this.start) {
                    this.paintAndPause();
                }
                this.removeMouseListener(this);
                this.removeComponents(this.numPlayersC);
                this.removeComponents(this.names);
                this.remove(this.startB);
                this.resetSpriteList();
                this.displayMessage(this.startGame, QuizShow.darkBlue, 2000);
                this.lights.set(1);
                for (int i = 0; i < this.scores.length; ++i) {
                    this.scores[i] = 0;
                }
                for (int j = 0; j < this.names.length; ++j) {
                    this.nameText[j].set(new int[] { 7, 13 }, new Object[] { new Boolean(this.names[j].isEnabled()), this.names[j].getText() });
                    this.scoreText[j].set(new int[] { 7, 13 }, new Object[] { new Boolean(this.names[j].isEnabled()), "0" });
                }
                this.questionList.removeAllElements();
                for (int k = 0; k < this.masterQuestionList.size(); ++k) {
                    this.questionList.addElement(this.masterQuestionList.elementAt(k));
                }
                for (int l = 0; l < this.buzzIn.length; ++l) {
                    this.buzzIn[l] = false;
                }
                this.question = 0;
                while (this.question < QuizShow.TOTALQUESTIONS) {
                    this.readQuestion();
                    this.setQuestionScreen();
                    this.buzzIn[0] = true;
                    for (int n = 0; n < this.numPlayersValue; ++n) {
                        this.buzzIn[n] = true;
                    }
                    int n2 = 0;
                    this.paintAndPause(100);
                    while (n2 < this.questionsText.length && this.questionsText[n2].active) {
                        this.questionsText[n2].visible = true;
                        for (int n3 = 0; n3 < 5; ++n3) {
                            this.paintAndPause(100);
                        }
                        ++n2;
                    }
                    for (int n4 = 0; n4 < this.choicesText.length && this.choicesText[n4].active; ++n4) {
                        this.choicesText[n4].visible = true;
                        for (int n5 = 0; n5 < 5; ++n5) {
                            this.paintAndPause(100);
                        }
                    }
                    if (this.numPlayersValue > 1) {
                        this.waitForBuzzer();
                    }
                    else {
                        this.waitForAnswer();
                    }
                    this.displayScoreScreen();
                    this.paintAndPause(2000);
                    ++this.question;
                }
                this.player = 0;
                for (int player = 0; player < this.numPlayersValue; ++player) {
                    if (this.scores[player] > this.scores[this.player]) {
                        this.player = player;
                    }
                }
                this.displayMessage(this.winner, QuizShow.darkBlue, 2000);
                this.displayMessage(this.gameOver, QuizShow.darkBlue, 1000);
                this.displayMessage("Thanks for Playing!", QuizShow.darkBlue, 1000);
                if (this.getParameter("url") != null) {
                    try {
                        this.getAppletContext().showDocument(new URL(String.valueOf(this.getParameter("url")) + "?name=" + this.names[this.player].getText() + "&score=" + this.scores[this.player]), (this.getParameter("target") != null) ? this.getParameter("target") : "_self");
                    }
                    catch (MalformedURLException ex) {}
                }
            }
        }
        catch (Exception ex2) {
            this.stop();
        }
    }
    
    public final void checkBuzzer(final int n) {
        this.player = 0;
        while (this.player < this.numPlayersValue && n != this.buzzers[this.player]) {
            ++this.player;
        }
        if (n == this.buzzers[this.player] && this.buzzIn[this.player]) {
            this.buzzerSounds[this.player].play();
            this.timer.toggle = true;
            this.removeKeyListener(this);
        }
    }
    
    public final void lightAnswer(final char c) {
        if (c - 'a' >= '\0' && c - 'a' < this.numChoices) {
            this.answerGrid[c - 'a'].visible = !this.answerGrid[c - 'a'].visible;
            if (this.answerGrid[c - 'a'].visible) {
                ++this.choiceCount;
            }
            else {
                --this.choiceCount;
            }
            if (this.choiceCount >= this.answerCount) {
                this.timer.toggle = true;
            }
        }
    }
    
    public final void stop() {
        this.me = null;
    }
    
    public final void setBuzzerText() {
        for (int i = 0; i < this.buzzersKeyText.length; ++i) {
            this.buzzersKeyText[i].text = "'" + KeyEvent.getKeyText(this.buzzers[i]) + "'";
        }
    }
    
    public final void textValueChanged(final TextEvent textEvent) {
        final TextField textField = (TextField)textEvent.getSource();
        if (textField.getText().length() > 20) {
            textField.setText(textField.getText().substring(0, 20));
            textField.setCaretPosition(19);
        }
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        this.numPlayersValue = Integer.parseInt(this.numPlayers.getSelectedCheckbox().getLabel());
        for (int i = 0; i < this.names.length; ++i) {
            this.names[i].setEnabled(i < this.numPlayersValue);
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.start = true;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.displayPlayerScreen();
        this.removeMouseListener(this);
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (this.buzz) {
            this.checkBuzzer(keyEvent.getKeyCode());
            return;
        }
        this.lightAnswer(keyEvent.getKeyChar());
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    static {
        darkGreen = new Color(0, 128, 0);
        gold = new Color(128, 128, 0);
        darkRed = new Color(128, 0, 0);
        darkBlue = new Color(0, 0, 128);
    }
}
