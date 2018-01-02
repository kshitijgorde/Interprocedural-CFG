// 
// Decompiled by Procyon v0.5.30
// 

package sm_spanish;

import sm_common.stringTranslator;

public class searchApplet extends sm_common.searchApplet
{
    public void init() {
        new searchAppletBeanInfo();
        super.init();
    }
    
    protected void initStringTranslator() {
        super.st = new stringTranslator();
        super.st.cancelLabel = new String("Cancelar");
        super.st.copyright = new String("Copyright © 2000-2001, Rhino Software, Todos los derechos resrvados. \n");
        super.st.daysLeftToEvaluate = new String(" d\u00edas restantes para finalizar periodo de prueba.");
        super.st.errorInTheFormatOfTheFilename = new String("Error en el formato del archivo: ");
        super.st.errorReadingTheFollowingFile = new String("Error al leer el siguiente archivo: ");
        super.st.evaluationVersion = new String("Versi\u00f3n de prueba.  ");
        super.st.evaluationExpired = new String("El per\u00edodo de prueba ha expirado. Por favor registre. ");
        super.st.ifYouHaveAnyCommentsOrQuestions = new String("Si usted tiene algun comentario o pregunta sobre este producto por favor escribanos a \nRhino Software a searchme@rhinosoftware.co.uk\no visitenos directamente a nuestro site http://www.rhinosoftware.co.uk\nSi Usted desea recibir respuesta por parte de nosotros Usted deber\u00e1 enviar sus comentarios en ingles\n\nClient Side Search:Search Me! ");
        super.st.itShouldBeOfTheForm = new String("Este deviera de estar en el siguiente formato");
        super.st.noDescription = new String("No descriptor");
        super.st.noTitle = new String("No portada");
        super.st.pleaseInformTheWebmasterForThisSite = new String("Por favor notifique al Webmaster sobre este error.");
        super.st.registeredVersion = new String("Versi\u00f3n registrada. ");
        super.st.rhinoSoftwareWelcomesTheFeedbackYouCanProvide = new String("Rhino Software agradece cualquier opinion que usted pueda suministrarnos");
        super.st.search = new String("Buscar...");
        super.st.searchForKeywords = new String("Buscando Palabra: ");
        super.st.searchResults = new String("Resultados de la busqueda");
        super.st.theFileToBeReadMustResideOnTheSameServerAsTheSearchApplet = new String("Para poder leer el archivo este debe estar en el mismo servidor que el Search Applet.");
        super.st.youHaveBrokenJavaSecurityRules = new String("Usted ha atentado contra las normas de seguridad de Java.");
        super.st.yourQueryMatchedNoDocuments = new String("No se ha encontrado documento con la palabra seleccionada.");
    }
}
