// 
// Decompiled by Procyon v0.5.30
// 

package sm_swedish;

import sm_common.stringTranslator;

public class searchApplet extends sm_common.searchApplet
{
    public void init() {
        new searchAppletBeanInfo();
        super.init();
    }
    
    protected void initStringTranslator() {
        super.st = new stringTranslator();
        super.st.cancelLabel = new String("Avbryt");
        super.st.copyright = new String("Copyright © 2000-2001, Rhino Software, Alla R\u00e4ttigheter Reserverade. \n");
        super.st.daysLeftToEvaluate = new String(" dagar kvar av utv\u00e4rderingstiden.");
        super.st.errorInTheFormatOfTheFilename = new String("Fel i filnamnsformatet ");
        super.st.errorReadingTheFollowingFile = new String("Kan inte l\u00e4sa f\u00f6ljande fil: ");
        super.st.evaluationVersion = new String("Utv\u00e4rderingsversion.  ");
        super.st.evaluationExpired = new String("Utv\u00e4rderingstiden \u00e4r slut, var sn\u00e4ll och registrera");
        super.st.ifYouHaveAnyCommentsOrQuestions = new String("Om du har n\u00e5gra fr\u00e5gor eller kommentarer om den h\u00e4r produkten kan du skicka ett e-brev till\nRhino Software p\u00e5 searchme@rhinosoftware.co.uk\neller bes\u00f6ka v\u00e5r webbsida p\u00e5 http://www.rhinosoftware.co.uk\nOm du vill ha svar fr\u00e5n oss, m\u00e5ste du skriva p\u00e5 engelska\n\nClient Side Search:Search Me! ");
        super.st.itShouldBeOfTheForm = new String("Formatet skall vara");
        super.st.noDescription = new String("Beskrivning saknas");
        super.st.noTitle = new String("Titel saknas");
        super.st.pleaseInformTheWebmasterForThisSite = new String("Var sn\u00e4ll och meddela webbmastern f\u00f6r den h\u00e4r webbplatsen.");
        super.st.registeredVersion = new String("Registrerad version. ");
        super.st.rhinoSoftwareWelcomesTheFeedbackYouCanProvide = new String("Rhino Software tar tacksamt emot dina synpunkter");
        super.st.search = new String("S\u00f6k...");
        super.st.searchForKeywords = new String("S\u00f6k p\u00e5 nyckelord: ");
        super.st.searchResults = new String("S\u00f6kresultat");
        super.st.theFileToBeReadMustResideOnTheSameServerAsTheSearchApplet = new String("FIlen som skall l\u00e4sas m\u00e5ste finnas p\u00e5\nsamma server som s\u00f6kappleten.");
        super.st.youHaveBrokenJavaSecurityRules = new String("Du har brutit mot Javas s\u00e4kerhetsregler.");
        super.st.yourQueryMatchedNoDocuments = new String("Din s\u00f6kning gav inga tr\u00e4ffar.");
    }
}
