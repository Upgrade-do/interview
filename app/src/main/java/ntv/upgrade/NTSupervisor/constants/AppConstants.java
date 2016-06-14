package ntv.upgrade.NTSupervisor.constants;

import android.os.Environment;
import java.util.Arrays;
import java.util.List;

import ntv.upgrade.NTSupervisor.Adapters.QuestionWrapper;
import ntv.upgrade.NTSupervisor.R;

/**
 * Holds constants to be used all-across the APP
 *
 * Created by jfrom on 14/05/16.
 */
public class AppConstants {

    /**
     * Template Name & extension
     */
    private static final String REPORT_TEMPLATE_NAME = "InterviewTemplate";
    private static final String REPORT_EXTENSION = ".xlsx";

    /**
     * Full template & reports file path
     */
    private static final String REPORT_TEMPLATE_PATH = "/nt/Template/";
    private static final String REPORT_PATH = "/Reportes/";

    private static String REPORT_NAME = "report_name";
    public static String SELECTED_FILE = "selected_file";

    /**
     * Getters
     */
    public static String getReportTemplateName() {
        return REPORT_TEMPLATE_NAME;
    }

    public static String getReportTemplateFullName() {
        return REPORT_TEMPLATE_NAME + REPORT_EXTENSION;
    }

    public static String getReportName() {
        return REPORT_NAME;
    }

    public static String getReportFullName() {
        return REPORT_NAME + REPORT_EXTENSION;
    }

    public static String getReportExtension() {
        return REPORT_EXTENSION;
    }

    public static String getReportTemplatePath() {
        return String.format("%s%s",
                Environment.getExternalStorageDirectory(),
                REPORT_TEMPLATE_PATH);
    }

    public static String getReportPath() {
        return String.format("%s%s",
                Environment.getExternalStorageDirectory(),
                REPORT_PATH);
    }
    /**
     * Setters
     */
    public static void setReportName(String reportName) {
        REPORT_NAME = reportName;
    }


    public static final List<String> programingLangList = Arrays.asList("JAVA", "C#", "Python", "PHP","JavaScript");
    public static final List<String> DBTypeList = Arrays.asList("MSSQL", "Oracle", "MySQL", "MongoDB");
    public static final List<String> QAQuestions = Arrays.asList(
            "Que me podrias decir sobre el ciclo de vida del software?",
            "Que sabes sobre las pruebas de calidad de software?",
            "Segun tu Opinion, Cual es el mejor metodo de prueba?",
            "Tienes experiencia en programacion?",
            "Has trabajado con Bases de Datos relacionales?",
            "Que entiendes por \"calidad\" ? Por que es importante?",
            "Si tuvieras que probar la calidad de un articulo \"X\", Que Tipo de pruebas realizarias?",
            "Cuentame sobre el reto mas grande que has enfrentado en el area de software, Como superaste ese obstaculo");
    public static final List<String> QAHints = Arrays.asList(
            "Define las fases: requerimientos, analisis, diseño, codificacion, pruebas, validacion, mantenimiento y evolucion",
            "Herramientas metodologiasm, estrategias",
            "Explica tu respuesta. (Cascada, Iterativo, etc ...) ",
            "Dificultades tecnicas, manejo de estres, trabajo en equipo, fecha de entrega, etc ...");

    public static final List<String> DevQuestions = Arrays.asList(
            "Cuentame sobre tus experiencias desarrollando aplicaciones",
            "Cual era tu principal responsabilidad en esos proyectos? Explica a detalle",
            "Como era la composicion de tu equipo de desarrollo? Cual era la metodologia de trabajo?",
            "Cuales fueron los resultados de las aplicaciones y programas que desarrollaste",
            "Cuales lenguajes de programacion manejas? A que nivel?",
            "Cuales Bases de Datos relaciones has utilizado? hasta que nivel?",
            "Tus Experiencias de desarrollo han sido para aplicaciones Moviles, Desktop o Web?",
            "Cuentame sobre el reto mas grande que has enfrentado en el campo de desarrollo de software, Como superaste ese obstaculo?");
    public static final List<String> DevHints = Arrays.asList(
            "Objetivo, tipo, producto, módulo, compañia, etc...",
            "Desarrollador, Encargado de p" +
                    "ruebas, DBA, etc...",
            "Scrum, Waterfall, Agile, etc...",
            "Aumento de ventas, eficiencia de procesos, ganancia financiera, etc...",
            "Hablame de sobre los proyectos que haz realizado en cada ambiente.");



    // Declaring the Integer Array with recourse Id's of Images for the Spinners
    public static final Integer[] acceptanceIcons = {R.drawable.status_na, R.drawable.status_no, R.drawable.status_yes, R.drawable.ic_very_good};
    public static final Integer[] acceptanceIconsgreen = {R.drawable.status_na_green, R.drawable.status_no_green, R.drawable.status_yes_green, R.drawable.ic_very_good_green};
 //   public static final int[] imagesComent = { R.drawable.comment_empty, R.drawable.comment_full, R.drawable.apply_not };



   /* public static final String APP_KEY = "dug4oskbcjy0f7c";
    public static final int DBX_CHOOSER_REQUEST = 0;  // You can change this if needed*/
}