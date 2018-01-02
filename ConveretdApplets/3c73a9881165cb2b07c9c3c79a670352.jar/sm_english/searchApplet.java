// 
// Decompiled by Procyon v0.5.30
// 

package sm_english;

import sm_common.stringTranslator;

public class searchApplet extends sm_common.searchApplet
{
    public void init() {
        new searchAppletBeanInfo();
        super.init();
    }
    
    protected void initStringTranslator() {
        super.st = new stringTranslator();
        super.st.cancelLabel = new String("Cancel");
        super.st.copyright = new String("Copyright © 2000-2001, Rhino Software, All Rights Reserved. \n");
        super.st.daysLeftToEvaluate = new String(" days left to evaluate.");
        super.st.errorInTheFormatOfTheFilename = new String("Error in the format of the filename ");
        super.st.errorReadingTheFollowingFile = new String("Error reading the following file: ");
        super.st.evaluationVersion = new String("Evaluation version.  ");
        super.st.evaluationExpired = new String("Evaluation expired, please register");
        super.st.ifYouHaveAnyCommentsOrQuestions = new String("If you have any comments or questions on this product then e-mail\nRhino Software at searchme@rhinosoftware.co.uk\nor visit our site at http://www.rhinosoftware.co.uk\nIf you would like to hear from us you must send your feedback in English\n\nClient Side Search:Search Me! ");
        super.st.itShouldBeOfTheForm = new String("It should be of the form");
        super.st.noDescription = new String("No description");
        super.st.noTitle = new String("No title");
        super.st.pleaseInformTheWebmasterForThisSite = new String("Please inform the Webmaster for this site.");
        super.st.registeredVersion = new String("Registered Version. ");
        super.st.rhinoSoftwareWelcomesTheFeedbackYouCanProvide = new String("Rhino Software welcomes the feedback you can provide.");
        super.st.search = new String("Search...");
        super.st.searchForKeywords = new String("Search for keywords: ");
        super.st.searchResults = new String("Search Results");
        super.st.theFileToBeReadMustResideOnTheSameServerAsTheSearchApplet = new String("The file to be read must reside on the\nsame server as the search Applet.");
        super.st.youHaveBrokenJavaSecurityRules = new String("You have broken Java security rules.");
        super.st.yourQueryMatchedNoDocuments = new String("Your query matched no documents.");
    }
}
