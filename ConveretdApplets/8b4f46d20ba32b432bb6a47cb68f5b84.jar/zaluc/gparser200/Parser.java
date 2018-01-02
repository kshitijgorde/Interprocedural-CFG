// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.zip.GZIPOutputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Parser
{
    static BufferedReader SrcStream;
    static StringTokenizer tokenizer;
    static String curLine;
    static int curLineNum;
    static int curLevel;
    static PeopleList people;
    static boolean createHtmlDetails;
    static boolean includeDetails;
    static String password;
    static boolean verbose;
    static final int retOkay = 0;
    static final int retBadParam = 1;
    static final int retSystemError = 2;
    static final int retNumberFormatError = 3;
    static final int retUnknownError = 4;
    private static final String paramMsg = "The following parameters are allowable:                                \n\n     filename:      This parameter must be first and must exist.  It is  \n                    the name of the gedcom file that is to be parsed.    \n                                                                         \n     start person:  This parameter must be second and must exist.  It is \n                    the gedcom ID number of the person that will         \n                    initially be displayed as the center person.         \n                                                                         \n     The remaining parameters may be entered in any order:               \n                                                                         \n     D:             This parameter indicates that an individuals details \n                    should be placed in an html file in the specified    \n                    directory.  If the directory does not exist, it will \n                    be created.  No details will be placed in the        \n                    datafile.                                            \n                                                                         \n     S:             This parameter tells the parser to generate a short  \n                    data file, i.e. one that doesn't contain any details.\n                    It should be used if details are not wanted, or if   \n                    details will be provided through an HTML page.       \n                                                                         \n     Paaaaaaaa:     This parameter indicates a password to be used to    \n                    view people who are alive.  It has no meaning if the \n                    L parameter is not specified.                        \n                                                                         \n                    The password may be any length and may contain any   \n                    printable characters.                                \n                                                                         \n     L0000:         This parameter tells the parser how to handle living.\n                    A person is considered to be still alive if they do  \n                    not have a death record and they were born after a   \n                    particular year.  The year is specified as a four    \n                    digit number after the 'L'.  For example, \"L1916\"  \n                                                                         \n                    If a password is specified, living people will be    \n                    included in the data but will only be viewable if    \n                    the password is given by the user.  If no password   \n                    is specified, living people will not be included in  \n                    the data and will not be viewable.                   \n                                                                         \n     I000:          This parameter may exist any number of times after   \n                    the previous parameters.  It has no meaning unless   \n                    the L0000 parameter is specified.  It indicates      \n                    that a particular individual should be included in   \n                    the resulting tree even if they are \"alive\" by the \n                    preceding criteria.  The number following the 'I'    \n                    indicates the gedcom ID number of the individual to  \n                    include.  For example \"I25\".                       \n                                                                         \n     X000:          This parameter may exist any number of times after   \n                    the first three parameters.  It has no meaning       \n                    unless the L0000 parameter is specified.  It         \n                    indicates that a particular individual should be     \n                    excluded from the resulting tree even if they are    \n                    \"dead\" by the preceding criteria.  The number      \n                    following the 'X' indicates the gedcom ID number of  \n                    the individual to exclude.  For example \"X25\".   \n\nNote that people who have no birth year will be considered to be dead    \nand will be included in the resulting file.";
    
    public static void main(final String[] array) {
        int n = 0;
        dumpParams(array);
        try {
            if (array.length >= 2) {
                final String lowerCase = array[0].toLowerCase();
                final String substring = lowerCase.substring(0, lowerCase.lastIndexOf(".ged"));
                final int int1 = Integer.parseInt(array[1]);
                Person person = null;
                final Restrictions restrictions = new Restrictions(array.length - 3);
                if (array.length > 2) {
                    for (int n2 = 2; n == 0 && n2 < array.length; ++n2) {
                        final String s = array[n2];
                        switch (s.charAt(0)) {
                            case 'D':
                            case 'd': {
                                Parser.createHtmlDetails = true;
                                Parser.includeDetails = false;
                                break;
                            }
                            case 'S':
                            case 's': {
                                Parser.includeDetails = false;
                                break;
                            }
                            case 'P':
                            case 'p': {
                                Parser.password = s.substring(1);
                                break;
                            }
                            case 'L':
                            case 'l': {
                                restrictions.setCutoff(Integer.parseInt(s.substring(1)));
                                break;
                            }
                            case 'I':
                            case 'i': {
                                restrictions.include(Integer.parseInt(s.substring(1)));
                                break;
                            }
                            case 'X':
                            case 'x': {
                                restrictions.exclude(Integer.parseInt(s.substring(1)));
                                break;
                            }
                            case 'V':
                            case 'v': {
                                Parser.verbose = true;
                                break;
                            }
                            default: {
                                n = 1;
                                break;
                            }
                        }
                    }
                    if (!restrictions.valid()) {
                        System.out.println("No L0000 parameter was specified");
                        n = 1;
                    }
                }
                if (n == 0 && parseFile(lowerCase, restrictions)) {
                    for (int count = Parser.people.getCount(), i = 0; i < count; ++i) {
                        if (Parser.people.getPerson(i).id == int1) {
                            person = Parser.people.getPerson(i);
                            break;
                        }
                    }
                    if (person != null) {
                        writeFile(substring, person);
                    }
                    else {
                        System.out.println("Could not find start person " + int1);
                        n = 1;
                    }
                    if (Parser.createHtmlDetails) {
                        writeDetails();
                    }
                }
            }
            else {
                System.out.println("Please specify a filename and a start person as parameters");
                n = 1;
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("parseFile: File Not Found: " + ex.getMessage());
            ex.printStackTrace();
            n = 1;
        }
        catch (IOException ex2) {
            System.out.println("IOException occurred: " + ex2.getMessage());
            ex2.printStackTrace();
            n = 2;
        }
        catch (NumberFormatException ex4) {
            n = 3;
        }
        catch (Exception ex3) {
            System.out.println("Exception occurred: " + ex3.getMessage());
            System.out.println("  On line " + Parser.curLineNum + ": <" + Parser.curLine + ">");
            ex3.printStackTrace();
            n = 4;
        }
        finally {
            if (Parser.verbose) {
                System.out.println("Memory Usage: total memory: " + Runtime.getRuntime().totalMemory() + " bytes, free memory: " + Runtime.getRuntime().freeMemory() + " bytes");
            }
            if (n == 0) {
                System.out.println("All done");
                System.exit(n);
            }
            else {
                System.out.println("An error occurred.");
                System.out.println("The following parameters are allowable:                                \n\n     filename:      This parameter must be first and must exist.  It is  \n                    the name of the gedcom file that is to be parsed.    \n                                                                         \n     start person:  This parameter must be second and must exist.  It is \n                    the gedcom ID number of the person that will         \n                    initially be displayed as the center person.         \n                                                                         \n     The remaining parameters may be entered in any order:               \n                                                                         \n     D:             This parameter indicates that an individuals details \n                    should be placed in an html file in the specified    \n                    directory.  If the directory does not exist, it will \n                    be created.  No details will be placed in the        \n                    datafile.                                            \n                                                                         \n     S:             This parameter tells the parser to generate a short  \n                    data file, i.e. one that doesn't contain any details.\n                    It should be used if details are not wanted, or if   \n                    details will be provided through an HTML page.       \n                                                                         \n     Paaaaaaaa:     This parameter indicates a password to be used to    \n                    view people who are alive.  It has no meaning if the \n                    L parameter is not specified.                        \n                                                                         \n                    The password may be any length and may contain any   \n                    printable characters.                                \n                                                                         \n     L0000:         This parameter tells the parser how to handle living.\n                    A person is considered to be still alive if they do  \n                    not have a death record and they were born after a   \n                    particular year.  The year is specified as a four    \n                    digit number after the 'L'.  For example, \"L1916\"  \n                                                                         \n                    If a password is specified, living people will be    \n                    included in the data but will only be viewable if    \n                    the password is given by the user.  If no password   \n                    is specified, living people will not be included in  \n                    the data and will not be viewable.                   \n                                                                         \n     I000:          This parameter may exist any number of times after   \n                    the previous parameters.  It has no meaning unless   \n                    the L0000 parameter is specified.  It indicates      \n                    that a particular individual should be included in   \n                    the resulting tree even if they are \"alive\" by the \n                    preceding criteria.  The number following the 'I'    \n                    indicates the gedcom ID number of the individual to  \n                    include.  For example \"I25\".                       \n                                                                         \n     X000:          This parameter may exist any number of times after   \n                    the first three parameters.  It has no meaning       \n                    unless the L0000 parameter is specified.  It         \n                    indicates that a particular individual should be     \n                    excluded from the resulting tree even if they are    \n                    \"dead\" by the preceding criteria.  The number      \n                    following the 'X' indicates the gedcom ID number of  \n                    the individual to exclude.  For example \"X25\".   \n\nNote that people who have no birth year will be considered to be dead    \nand will be included in the resulting file.");
                System.exit(n);
            }
        }
    }
    
    public static boolean parseFile(final String s, final Restrictions restrictions) throws FileNotFoundException, IOException {
        boolean b = false;
        try {
            Parser.people = new PeopleList();
            Parser.SrcStream = new BufferedReader(new FileReader(s));
            nextLine();
            while (Parser.tokenizer != null) {
                if (Parser.curLevel == 0) {
                    final String nextToken = Parser.tokenizer.nextToken();
                    if (nextToken.startsWith("@I")) {
                        parsePerson(indexFromIdToken(nextToken), restrictions);
                    }
                    else if (nextToken.startsWith("@F")) {
                        parseFamily(indexFromIdToken(nextToken));
                    }
                    else {
                        nextLine();
                    }
                }
                else {
                    nextLine();
                }
            }
            Parser.people.sort();
            b = true;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("parseFile: ArrayIndexOutOfBoundsException: " + ex.getMessage() + ", curLine(" + Parser.curLineNum + ") = <" + Parser.curLine + ">");
            ex.printStackTrace();
        }
        catch (NullPointerException ex2) {
            System.out.println("parseFile: NullPointerException: " + ex2.getMessage() + ", curLine = <" + Parser.curLine + ">");
            ex2.printStackTrace();
        }
        catch (NumberFormatException ex3) {
            System.out.println("parseFile: NumberFormatException: " + ex3.getMessage() + ", curLine = <" + Parser.curLine + ">");
            ex3.printStackTrace();
        }
        return b;
    }
    
    static void parsePerson(final int id, final Restrictions restrictions) throws IOException {
        try {
            final Person person = new Person();
            Parser.people.setPerson(person, id);
            person.id = id;
            person.includeDetails = Parser.includeDetails;
            if (Parser.verbose) {
                System.out.println("Parsing person " + id);
            }
            nextLine();
            while (Parser.tokenizer != null && Parser.curLevel != 0) {
                if (Parser.curLevel == 1) {
                    final String nextToken = Parser.tokenizer.nextToken();
                    if (nextToken.compareTo("NAME") == 0) {
                        parseName(person);
                    }
                    else if (nextToken.compareTo("BIRT") == 0) {
                        person.addEvent(person.birth = parseEvent("Birth", null));
                        person.hide = restrictions.hide(person);
                    }
                    else if (nextToken.compareTo("DEAT") == 0) {
                        person.addEvent(person.death = parseEvent("Death", null));
                    }
                    else if (nextToken.compareTo("CHR") == 0) {
                        person.addEvent(parseEvent("Christened", null));
                    }
                    else if (nextToken.compareTo("NOTE") == 0) {
                        person.addNote(parseNote(restOfLine()));
                    }
                    else if (nextToken.compareTo("TITL") == 0) {
                        person.title = restOfLine();
                        nextLine();
                    }
                    else if (nextToken.compareTo("FAMC") == 0) {
                        if (person.preferredFamily == -1) {
                            final String nextToken2 = Parser.tokenizer.nextToken();
                            if (nextToken2.startsWith("@F")) {
                                person.preferredFamily = indexFromIdToken(nextToken2);
                            }
                        }
                        nextLine();
                    }
                    else if (nextToken.compareTo("SEX") == 0) {
                        if (Parser.tokenizer.nextToken().startsWith("F")) {
                            person.sex = 2;
                        }
                        else {
                            person.sex = 1;
                        }
                        nextLine();
                    }
                    else if (nextToken.startsWith("FAM")) {
                        nextLine();
                    }
                    else {
                        person.addEvent(parseEvent(null, restOfLine()));
                    }
                }
                else {
                    nextLine();
                }
            }
            person.fullName = String.valueOf((person.firstName != null) ? person.firstName : "???") + " " + ((person.lastName != null) ? person.lastName : "???");
        }
        catch (NumberFormatException ex) {
            System.out.println(String.valueOf(new StringBuffer("parsePerson: NumberFormatException: ").append(ex.getMessage()).toString()) + "curLine = <" + Parser.curLine + ">");
            ex.printStackTrace();
            nextLine();
        }
    }
    
    static void parseName(final Person person) throws IOException {
        String restOfLine = null;
        int index = 0;
        int index2 = 0;
        try {
            restOfLine = restOfLine();
            index = restOfLine.indexOf(47);
            String substring;
            String substring2;
            if (index == -1) {
                substring = restOfLine;
                substring2 = "";
            }
            else {
                index2 = restOfLine.indexOf(47, index + 1);
                if (index2 == -1) {
                    substring2 = "";
                }
                else {
                    substring2 = restOfLine.substring(index + 1, index2);
                }
                substring = restOfLine.substring(0, index);
            }
            String trim = substring.trim();
            String trim2 = substring2.trim();
            if (trim.length() == 0) {
                trim = null;
            }
            if (trim2.length() == 0) {
                trim2 = null;
            }
            String s = person.firstName;
            if (s != null) {
                final int index3 = person.firstName.indexOf(40);
                if (index3 != -1) {
                    s = person.firstName.substring(0, index3);
                }
                s = s.trim();
            }
            String s2 = person.lastName;
            if (s2 != null) {
                final int index4 = person.lastName.indexOf(40);
                if (index4 != -1) {
                    s2 = person.lastName.substring(0, index4);
                }
                s2 = s2.trim();
            }
            if (person.firstName == null) {
                person.firstName = trim;
            }
            else if (trim != null && !trim.equals(s)) {
                person.firstName = String.valueOf(person.firstName) + " (" + trim + ") ";
            }
            if (person.lastName == null) {
                person.lastName = trim2;
            }
            else if (trim2 != null && !trim2.equals(s2)) {
                person.lastName = String.valueOf(person.lastName) + " (" + trim2 + ") ";
            }
            nextLine();
            while (Parser.curLevel >= 2) {
                if (Parser.curLevel == 2 && Parser.tokenizer.nextToken().compareTo("NSFX") == 0) {
                    person.nameSuffix = restOfLine();
                }
                nextLine();
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            System.out.println("parseName: StringIndexOutOfBoundsException: " + ex.getMessage());
            System.out.println("  curLine = <" + Parser.curLine + ">");
            System.out.println("  name    = <" + restOfLine + ">");
            System.out.println("  firstSlash  = " + index);
            System.out.println("  secondSlash = " + index2);
            ex.printStackTrace();
        }
        catch (NullPointerException ex2) {
            System.out.println("parseName: NullPointerException: " + ex2.getMessage());
            ex2.printStackTrace();
        }
    }
    
    static GedcomEvent parseEvent(final String type, final String value) throws IOException {
        final GedcomEvent gedcomEvent = new GedcomEvent();
        gedcomEvent.setType(type);
        gedcomEvent.setValue(value);
        nextLine();
        while (Parser.curLevel >= 2) {
            if (Parser.curLevel == 2) {
                final String nextToken = Parser.tokenizer.nextToken();
                if (nextToken.compareTo("DATE") == 0) {
                    gedcomEvent.setDate(restOfLine());
                }
                else if (nextToken.compareTo("PLAC") == 0) {
                    gedcomEvent.setPlace(restOfLine());
                }
                else if (nextToken.compareTo("TYPE") == 0) {
                    gedcomEvent.setType(restOfLine());
                }
            }
            nextLine();
        }
        return gedcomEvent;
    }
    
    static String parseNote(final String s) throws IOException {
        nextLine();
        while (Parser.curLevel >= 2) {
            nextLine();
        }
        return s;
    }
    
    static void parseFamily(final int id) throws IOException {
        Person father = null;
        Person mother = null;
        if (Parser.verbose) {
            System.out.println("Parsing family " + id);
        }
        final Family family = new Family();
        Parser.people.setFamily(family);
        family.id = id;
        try {
            nextLine();
            while (Parser.tokenizer != null) {
                if (Parser.curLevel == 0) {
                    return;
                }
                if (Parser.curLevel == 1) {
                    final String nextToken = Parser.tokenizer.nextToken();
                    if (nextToken.compareTo("HUSB") == 0) {
                        final Family family2 = family;
                        final Person person = Parser.people.getPerson(indexFromIdToken(Parser.tokenizer.nextToken()));
                        family2.father = person;
                        father = person;
                        if (father != null) {
                            father.addFamily(family);
                        }
                        nextLine();
                    }
                    else if (nextToken.compareTo("WIFE") == 0) {
                        final Family family3 = family;
                        final Person person2 = Parser.people.getPerson(indexFromIdToken(Parser.tokenizer.nextToken()));
                        family3.mother = person2;
                        mother = person2;
                        if (mother != null) {
                            mother.addFamily(family);
                        }
                        nextLine();
                    }
                    else if (nextToken.compareTo("CHIL") == 0) {
                        final Person person3 = Parser.people.getPerson(indexFromIdToken(Parser.tokenizer.nextToken()));
                        if (person3 != null) {
                            if (person3.preferredFamily == family.id || person3.childOfFamily == null) {
                                person3.childOfFamily = family;
                                person3.father = father;
                                person3.mother = mother;
                            }
                            family.addChild(person3);
                        }
                        nextLine();
                    }
                    else if (nextToken.compareTo("MARR") == 0) {
                        GedcomEvent event = parseEvent("Marriage", null);
                        family.marriage = event;
                        if (father != null) {
                            event.setValue((mother != null) ? mother.fullName : null);
                            father.addEvent(event);
                            if (mother != null) {
                                event = new GedcomEvent(event);
                            }
                        }
                        if (mother == null) {
                            continue;
                        }
                        event.setValue((father != null) ? father.fullName : null);
                        mother.addEvent(event);
                    }
                    else {
                        nextLine();
                    }
                }
                else {
                    nextLine();
                }
            }
        }
        catch (NumberFormatException ex) {
            System.out.println(String.valueOf(new StringBuffer("parseFamily: NumberFormatException: ").append(ex.getMessage()).toString()) + "curLine = <" + Parser.curLine + ">");
            ex.printStackTrace();
            Parser.curLine = Parser.SrcStream.readLine();
        }
    }
    
    private static void nextLine() throws IOException {
        ++Parser.curLineNum;
        while ((Parser.curLine = Parser.SrcStream.readLine()) != null && !(Parser.tokenizer = new StringTokenizer(Parser.curLine)).hasMoreTokens()) {
            ++Parser.curLineNum;
        }
        if (Parser.curLine != null) {
            try {
                Parser.curLevel = Integer.parseInt(Parser.tokenizer.nextToken());
                return;
            }
            catch (NumberFormatException ex) {
                nextLine();
                return;
            }
        }
        Parser.curLevel = 0;
        Parser.tokenizer = null;
    }
    
    private static int indexFromIdToken(final String s) {
        return Integer.parseInt(s.substring(2, s.indexOf(64, 2)));
    }
    
    private static String restOfLine() {
        Object o = null;
        while (Parser.tokenizer.hasMoreTokens()) {
            if (o == null) {
                o = Parser.tokenizer.nextToken();
            }
            else {
                o = String.valueOf(o) + " " + Parser.tokenizer.nextToken();
            }
        }
        return (String)o;
    }
    
    public static void writeFile(final String s, final Person person) {
        try {
            final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(String.valueOf(s) + ".gen"));
            final DataOutputStream dataOutputStream2 = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(String.valueOf(s) + ".gec")));
            final DataOutputStream dataOutputStream3 = new DataOutputStream(new FileOutputStream(String.valueOf(s) + ".gpw"));
            final Record record = new Record(dataOutputStream);
            final Record record2 = new Record(dataOutputStream2);
            final Record record3 = new Record(dataOutputStream3);
            record.write(0, 200);
            record2.write(0, 200);
            if (Parser.password != null) {
                record.write(18);
                record2.write(18);
                record3.write(18, Parser.password);
                dataOutputStream3.close();
            }
            Parser.people.writeFrom(person, record);
            Parser.people.writeFrom(person, record2);
            dataOutputStream.close();
            dataOutputStream2.close();
        }
        catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private static void writeDetails() {
        final int count = Parser.people.getCount();
        final DecimalFormat decimalFormat = new DecimalFormat("0000");
        try {
            for (int i = 0; i < count; ++i) {
                final Person person = Parser.people.getPerson(i);
                if (person != null) {
                    final FileWriter fileWriter = new FileWriter("UHP-" + decimalFormat.format(person.id) + ".html");
                    if (fileWriter != null) {
                        fileWriter.write(buildDetailsHtmlHeader(person));
                        fileWriter.write(buildDetailsHtmlBody(person));
                        fileWriter.close();
                    }
                    else {
                        System.out.println("In writeDetails(), couldn't create file");
                    }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("writeDetails: IO Exception: " + ex);
        }
    }
    
    private static String buildDetailsHtmlHeader(final Person person) {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">   \n<HTML>   \n<HEAD>   \n   <TITLE>Genealogy Page for " + person.fullName + "</TITLE>   \n" + "   \n" + "  <SCRIPT LANGUAGE='JavaScript'>   \n" + "  <!--   \n" + "    function launch (primary)   \n" + "    {   \n" + "      if (document.InterneTree.isLoaded() == false)   \n" + "      {   \n" + "        window.status = \"Launching InterneTree with \" + primary + \" as primary\";   \n" + "        document.InterneTree.begin(\"detailsloc\",                // source file   \n" + "                                   600,                          // width   \n" + "                                   360,                          // height   \n" + "                                   \"000000\",                     // foreground   \n" + "                                   \"E0E0E0\",                     // background   \n" + "                                   \"E0E0E0\",                     // people box background   \n" + "                                   \"2\",                          // people box border width   \n" + "                                   \"../../gifs/bckgrnd7_32.jpg\", // background image   \n" + "                                   \"1\",                          // background image layout   \n" + "                                   \"0\",                          // clear background   \n" + "                                   primary,                      // primary individual   \n" + "                                   \".\",                    // detail location   \n" + "                                   \"_parent\",                     // html target   \n" + "                                   null,                     // initial zoom   \n" + "                                   null);                    // help URL   \n" + "      }   \n" + "      else   \n" + "      {   \n" + "        window.status = \"Setting primary to \" + primary;   \n" + "        document.InterneTree.setPrimary(parseInt(primary));   \n" + "        document.InterneTree.showWindow();   \n" + "      }   \n" + "    }   \n" + "   \n" + "  //-->   \n" + "  </SCRIPT>   \n" + "   \n" + "</HEAD>   \n";
    }
    
    private static String buildDetailsHtmlBody(final Person person) {
        return "<BODY>   \n   \n" + person.toHtml() + "   \n" + "<APPLET code     = \"zaluc.geneo.Geneo.class\"   \n" + "        codebase = \"../../javabin.20\"   \n" + "        archive  = \"geneo.jar\"   \n" + "        name     = \"InterneTree\"   \n" + "        width    = \"2\"   \n" + "        height   = \"2\">   \n" + "</APPLET>   \n" + "   \n" + "</BODY>\n";
    }
    
    public static void dumpParams(final String[] array) {
        System.out.println("The following parameters were specified:");
        for (int i = 0; i < array.length; ++i) {
            System.out.println("   " + array[i]);
        }
    }
    
    static {
        Parser.includeDetails = true;
        Parser.password = null;
    }
}
