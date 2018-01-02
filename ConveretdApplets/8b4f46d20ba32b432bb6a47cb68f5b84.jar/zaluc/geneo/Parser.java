// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;
import zaluc.gparser200.Record100;
import zaluc.gparser200.Record;
import java.io.InputStream;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;

public class Parser
{
    static final int parsingFamily = 1;
    static final int parsingPerson = 2;
    
    public static void parseFile(final Globals globals, final ParserUpdates parserUpdates) {
        PeopleList list = null;
        Person person = null;
        Family family = null;
        Person centerPerson = null;
        String strValue = null;
        int n = 0;
        int intValue = 0;
        final int lastIndex = globals.sourceFile.lastIndexOf(".gen");
        Label_0059: {
            if (lastIndex > 0) {
                final String s = globals.sourceFile.substring(0, lastIndex);
                break Label_0059;
            }
            final String s = globals.sourceFile;
            try {
                URL url;
                Record record;
                try {
                    url = new URL(globals.documentBase, String.valueOf(s) + ".gec");
                    record = new Record(new DataInputStream(new GZIPInputStream(url.openStream())));
                }
                catch (Exception ex7) {
                    url = new URL(globals.documentBase, String.valueOf(s) + ".gen");
                    record = new Record(new DataInputStream(url.openStream()));
                }
                parserUpdates.notifyFileName(url.toString());
                if (globals.dumpStats) {
                    System.out.println("Before loading tree: total memory: " + Runtime.getRuntime().totalMemory() + " bytes, free memory: " + Runtime.getRuntime().freeMemory() + " bytes");
                }
                if (record.readRecordType() == 0) {
                    switch (record.readIntValue()) {
                        case 100: {
                            record = new Record100(record);
                        }
                        case 200: {
                            int recordType;
                            while ((recordType = record.readRecordType()) != 3) {
                                switch (recordType) {
                                    case 18: {
                                        final URL url2 = new URL(globals.documentBase, String.valueOf(s) + ".gpw");
                                        if (url2 == null) {
                                            continue;
                                        }
                                        final DataInputStream dataInputStream = new DataInputStream(url2.openStream());
                                        if (dataInputStream == null) {
                                            System.out.println("Couldn't find file " + url2.toString());
                                            continue;
                                        }
                                        final Record record2 = new Record(dataInputStream);
                                        if (record2.readRecordType() == 18) {
                                            strValue = record2.readStrValue();
                                            continue;
                                        }
                                        System.out.println("Invalid format for file " + url2.toString());
                                        continue;
                                    }
                                    case 1: {
                                        intValue = record.readIntValue();
                                        if (globals.dumpStats) {
                                            System.out.println(String.valueOf(url.toString()) + " contains " + intValue + " individuals");
                                            continue;
                                        }
                                        continue;
                                    }
                                    case 2: {
                                        final int intValue2 = record.readIntValue();
                                        list = new PeopleList(globals, intValue, intValue2);
                                        parserUpdates.notifySetup(list, strValue, intValue + intValue2);
                                        continue;
                                    }
                                    default: {
                                        System.out.println("Unknown record type (" + recordType + ") seen");
                                        continue;
                                    }
                                }
                            }
                            if (list == null) {
                                globals.statusCode = 1;
                                globals.statusDesc = String.valueOf(globals.statusDesc) + "Invalid Data File: " + url.toString();
                                break;
                            }
                            if (recordType == 3) {
                                person = list.newPerson(record.readIntValue());
                                int n2 = 2;
                                centerPerson = person;
                                if (globals.primary == -1) {
                                    list.setCenterPerson(person);
                                }
                                while (globals.statusCode == 0) {
                                    switch (record.readRecordType()) {
                                        case 0: {
                                            if (person != null) {
                                                person.complete();
                                                person = null;
                                            }
                                            if (family != null) {
                                                family.complete();
                                                family = null;
                                            }
                                            n2 = 2;
                                            parserUpdates.updateRecordCount(++n);
                                            person = list.newPerson(record.readIntValue());
                                            continue;
                                        }
                                        case 1: {
                                            person.id = record.readIntValue();
                                            if (person.id == globals.primary) {
                                                list.setCenterPerson(person);
                                                continue;
                                            }
                                            continue;
                                        }
                                        case 14: {
                                            person.hidden = true;
                                            continue;
                                        }
                                        case 2: {
                                            person.firstName = record.readStrValue();
                                            continue;
                                        }
                                        case 3: {
                                            person.lastName = record.readStrValue();
                                            continue;
                                        }
                                        case 4: {
                                            person.title = record.readStrValue();
                                            continue;
                                        }
                                        case 5: {
                                            person.nameSuffix = record.readStrValue();
                                            continue;
                                        }
                                        case 6: {
                                            person.details = record.readStrValue();
                                            continue;
                                        }
                                        case 7: {
                                            person.lifeDates = record.readStrValue();
                                            continue;
                                        }
                                        case 8: {
                                            person.sex = record.readIntValue();
                                            continue;
                                        }
                                        case 9: {
                                            if (n2 == 2) {
                                                person.father = record.readIntValue();
                                                continue;
                                            }
                                            family.father = record.readIntValue();
                                            continue;
                                        }
                                        case 10: {
                                            if (n2 == 2) {
                                                person.mother = record.readIntValue();
                                                continue;
                                            }
                                            family.mother = record.readIntValue();
                                            continue;
                                        }
                                        case 11: {
                                            person.addFamily(record.readIntValue());
                                            continue;
                                        }
                                        case 12: {
                                            if (person != null) {
                                                person.complete();
                                                person = null;
                                            }
                                            if (family != null) {
                                                family.complete();
                                                family = null;
                                            }
                                            n2 = 1;
                                            parserUpdates.updateRecordCount(++n);
                                            family = list.newFamily(record.readIntValue());
                                            continue;
                                        }
                                        case 13: {
                                            family.addChild(record.readIntValue());
                                            continue;
                                        }
                                        default: {
                                            globals.statusCode = 1;
                                            globals.statusDesc = String.valueOf(globals.statusDesc) + "Invalid Data File: " + url.toString();
                                            continue;
                                        }
                                    }
                                }
                                break;
                            }
                            globals.statusCode = 1;
                            globals.statusDesc = String.valueOf(globals.statusDesc) + "Invalid Data File: " + url.toString();
                            break;
                        }
                        default: {
                            globals.statusCode = 1;
                            globals.statusDesc = String.valueOf(globals.statusDesc) + "Invalid Data File: " + url.toString();
                            break;
                        }
                    }
                }
                else {
                    globals.statusCode = 1;
                    globals.statusDesc = String.valueOf(globals.statusDesc) + "Invalid Data File: " + url.toString();
                }
            }
            catch (EOFException ex8) {
                if (person != null) {
                    person.complete();
                }
                if (family != null) {
                    family.complete();
                }
                if (centerPerson != null) {
                    if (list.getCenterPerson() == null) {
                        list.setCenterPerson(centerPerson);
                    }
                }
                else {
                    globals.statusCode = 1;
                    globals.statusDesc = String.valueOf(globals.statusDesc) + "This tree contains no people.";
                }
                parserUpdates.updateRecordCount(++n);
            }
            catch (FileNotFoundException ex) {
                System.out.println("parseFile: File Not Found: " + ex.getMessage());
                ex.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "Data file could not be found: " + ex.getMessage();
            }
            catch (IOException ex2) {
                System.out.println("parseFile: IO Exception: " + ex2.getMessage());
                ex2.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "This tree is currently password protected.";
            }
            catch (ArrayIndexOutOfBoundsException ex3) {
                System.out.println("parseFile: ArrayIndexOutOfBoundsException: " + ex3.getMessage());
                ex3.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "parseFile: ArrayIndexOutOfBoundsException: " + ex3.getMessage();
            }
            catch (NullPointerException ex4) {
                System.out.println("parseFile: NullPointerException: " + ex4.getMessage());
                ex4.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "parseFile: NullPointerException: " + ex4.getMessage();
            }
            catch (NumberFormatException ex5) {
                System.out.println("parseFile: NumberFormatException: " + ex5.getMessage());
                ex5.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "parseFile: NumberFormatException: " + ex5.getMessage();
            }
            catch (Exception ex6) {
                System.out.println("parseFile: Exception: " + ex6.getMessage());
                ex6.printStackTrace();
                globals.statusCode = 1;
                globals.statusDesc = String.valueOf(globals.statusDesc) + "parseFile: Exception: " + ex6.getMessage();
            }
            finally {
                if (globals.dumpStats) {
                    System.out.println("After loading tree: total memory: " + Runtime.getRuntime().totalMemory() + " bytes, free memory: " + Runtime.getRuntime().freeMemory() + " bytes");
                }
                parserUpdates.notifyDone();
            }
        }
    }
}
