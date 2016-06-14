package ntv.upgrade.NTSupervisor.excel;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ntv.upgrade.NTSupervisor.NTApplication;
import ntv.upgrade.NTSupervisor.constants.AppConstants;
import ntv.upgrade.NTSupervisor.tools.EnvironmentTools;

/**
 * Created by jfrom on 3/10/2016.
 */
public class ExcelTools {

    //LOG TAG
    private static final String TAG = ExcelTools.class.getSimpleName();

    /**
     * Member variables
     */
    private static final int SHEET_QA = 0;
    private static final int SHEET_DEV = 1;
    private static final int SHEET_NOTES = 2;

    //RETURN VALUE
    private boolean success = false;
    // more attributes
    private static XSSFSheet sheetRawData = null;

    private static boolean getWritingPermissions() {
        // check if available and not read only
        if (!EnvironmentTools.isExternalStorageAvailable() || EnvironmentTools.isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return false;

        }
        return true;
    }

    /**
     * Creates a Blank report from the template
     */
    public static void createBlankReport(AssetManager assetManager) {

        EnvironmentTools.copyFile(assetManager,
                AppConstants.getReportPath());

        EnvironmentTools.renameFile(
                AppConstants.getReportPath(),
                AppConstants.getReportTemplateFullName(),
                AppConstants.getReportFullName());
    }

    /**
     * Writes value to specified cell.
     *
     * @param row    to write
     * @param column to write
     * @param value  to be written
     */
    private static void writeCell(int row, int column, String value) {
        XSSFCell cell = null;


        try {
            sheetRawData.setColumnHidden(column, false);
            cell = sheetRawData.getRow(row).getCell(column);
            // cell.setAsActiveCell();
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(value);
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }
    }

    /**
     * Writes value to specified cell.
     *
     * @param row    to write
     * @param column to write
     * @param value  to be written
     */
    private static void writeCell(int row, int column, int value) {
        XSSFCell cell = null;

        cell = sheetRawData.getRow(row).getCell(column);
        cell.setAsActiveCell();
        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    /**
     * Writes value to specified cell.
     *
     * @param row    to write
     * @param column to write
     * @param value  to be written
     */
    private static void writeCell(int row, int column, boolean value) {
        XSSFCell cell = null;

        cell = sheetRawData.getRow(row).getCell(column);
        cell.setAsActiveCell();
        cell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
        cell.setCellValue(value);
    }

    public static void recalculateFormulas() {

        try {

            XSSFWorkbook wb = new XSSFWorkbook(String.format("%s%s",
                    AppConstants.getReportPath(),
                    AppConstants.getReportFullName()));
            XSSFFormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (int i = 1; i >= 0; i--) {
                XSSFSheet sheet = wb.getSheetAt(i);
                for (int j = 0; j < sheet.getLastRowNum() - 1; j++) {
                    XSSFRow row = sheet.getRow(j);
                    for (int k = 0; k < row.getLastCellNum() - 1; k++) {
                        XSSFCell cell = row.getCell(k);
                        if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
                            evaluator.evaluateFormulaCell(cell);
                        }
                    }

                }
            }

        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
            //Log.i("cell: ", sh)
        }


    }

    public static String getSpinnerText(int index) {
        String selectedOption = "";

        if (index == 0) {
            selectedOption = "N/A";
        } else if (index == 1) {
            selectedOption = "No Satisfactorio";
        } else if (index == 2) {
            selectedOption = "Satisfactorio";
        } else if (index == 3) {
            selectedOption = "Excelente";
        }
        return selectedOption;
    }

    public static void saveChangesToReport(AssetManager assetManager, int interviewType, Context contex) {
        getWritingPermissions();
        try {
            // Creating Input Stream
            InputStream myReport = assetManager.open(AppConstants.getReportTemplateFullName());
            //Get the workbook instance for XLSX file
            XSSFWorkbook workbook = new XSSFWorkbook(myReport);

            switch (interviewType) {
                case SHEET_QA:

                    // TODO: make sure the sheet exists
                    if (workbook.isSheetHidden(SHEET_QA)) {
                        workbook.setSheetHidden(SHEET_QA, false);
                    }
                    sheetRawData = workbook.getSheetAt(SHEET_QA);

                    //header to excel
                    writeCell(8, 0, "Evaluador: " + NTApplication.header.getEvaluatorname());
                    writeCell(9, 0, "Nombre del candidato: " + NTApplication.header.getCandidatename());
                    writeCell(10, 0, "Fecha: " + NTApplication.header.getDate());
                    writeCell(9, 4, getSpinnerText(NTApplication.header.getGrade()));

                    //interview to excel
                    //question 1
                    writeCell(17, 2, getSpinnerText(NTApplication.QuestionList.get(0).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(15, 0, "Comentarios: " + NTApplication.QuestionList.get(0).getSimpleItem().getSimple_question_comment());

                    //question 2
                    writeCell(24, 2, getSpinnerText(NTApplication.QuestionList.get(1).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(22, 0, "Comentarios: " + NTApplication.QuestionList.get(1).getSimpleItem().getSimple_question_comment());

                    //question 3
                    writeCell(31, 2, getSpinnerText(NTApplication.QuestionList.get(2).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(29, 0, "Comentarios: " + NTApplication.QuestionList.get(2).getSimpleItem().getSimple_question_comment());

                    //question 4
                   // writeCell(38, 2, getSpinnerText(NTApplication.QuestionList.get(3).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(36, 0, "Comentarios: " + NTApplication.QuestionList.get(3).getCompetencyItem().getCompetencySkillDetails(NTApplication.QuestionList.get(3).getType()) + " " + NTApplication.QuestionList.get(3).getCompetencyItem().getCompetency_question_comment());

                    //question 5
                   // writeCell(17, 7, getSpinnerText(NTApplication.QuestionList.get(4).getCompetencyItem().getSpinnerOptionSelected0()));
                    writeCell(12, 5, "Comentarios: " + NTApplication.QuestionList.get(4).getCompetencyItem().getCompetencySkillDetails(NTApplication.QuestionList.get(4)
                            .getType()) + " " + NTApplication.QuestionList.get(4).getCompetencyItem().getCompetency_question_comment());

                    //question 6
                    writeCell(24, 7, getSpinnerText(NTApplication.QuestionList.get(5).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(22, 5, "Comentarios: " + NTApplication.QuestionList.get(5).getSimpleItem().getSimple_question_comment());

                    //question 7
                    writeCell(31, 7, getSpinnerText(NTApplication.QuestionList.get(6).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(29, 5, "Comentarios: " + NTApplication.QuestionList.get(6).getSimpleItem().getSimple_question_comment());

                    //question 8
                    writeCell(38, 7, getSpinnerText(NTApplication.QuestionList.get(7).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(36, 5, "Comentarios: " + NTApplication.QuestionList.get(7).getSimpleItem().getSimple_question_comment());

                    if (!workbook.isSheetHidden(SHEET_DEV)) {
                        workbook.setSheetHidden(SHEET_DEV, true);
                    }
                    if (!workbook.isSheetHidden(SHEET_NOTES)) {
                        workbook.setSheetHidden(SHEET_NOTES, true);
                    }

                    break;

                case SHEET_DEV:

                    if (workbook.isSheetHidden(SHEET_DEV)) {
                        workbook.setSheetHidden(SHEET_DEV, false);
                    }
                    sheetRawData = workbook.getSheetAt(SHEET_DEV);

                    //header to excel
                    writeCell(8, 0, "Evaluador: " + NTApplication.header.getEvaluatorname());
                    writeCell(9, 0, "Candidato: " + NTApplication.header.getCandidatename());
                    writeCell(10, 0, "Fecha: " + NTApplication.header.getDate());
                    writeCell(9, 4, getSpinnerText(NTApplication.header.getGrade()));

                    //interview to excel
                    //question 1
                    writeCell(17, 2, getSpinnerText(NTApplication.QuestionList.get(0).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(15, 0, "Comentarios: " + NTApplication.QuestionList.get(0).getSimpleItem().getSimple_question_comment());

                    //question 2
                    writeCell(24, 2, getSpinnerText(NTApplication.QuestionList.get(1).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(22, 0, "Comentarios: " + NTApplication.QuestionList.get(1).getSimpleItem().getSimple_question_comment());

                    //question 3
                    writeCell(31, 2, getSpinnerText(NTApplication.QuestionList.get(2).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(29, 0, "Comentarios: " + NTApplication.QuestionList.get(2).getSimpleItem().getSimple_question_comment());

                    //question 4
                    writeCell(38, 2, getSpinnerText(NTApplication.QuestionList.get(3).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(36, 0, "Comentarios: " + NTApplication.QuestionList.get(3).getSimpleItem().getSimple_question_comment());

                    //question 5
                   // writeCell(17, 7, getSpinnerText(NTApplication.QuestionList.get(4).getCompetencyItem().getSpinnerOptionSelected0()));
                    writeCell(12, 5, "Comentarios: " + NTApplication.QuestionList.get(4).getCompetencyItem().getCompetencySkillDetails(NTApplication.QuestionList.get(4)
                            .getType()) + " " + NTApplication.QuestionList.get(4).getCompetencyItem().getCompetency_question_comment());

                    //question 6
                   // writeCell(24, 7, getSpinnerText(NTApplication.QuestionList.get(5).getCompetencyItem().getSpinnerOptionSelected0()));
                    writeCell(22, 5, "Comentarios: " + NTApplication.QuestionList.get(5).getCompetencyItem().getCompetencySkillDetails(NTApplication.QuestionList.get(5).getType()) + " " + NTApplication.QuestionList.get(5).getCompetencyItem().getCompetency_question_comment());

                    //question 7
                    writeCell(31, 7, getSpinnerText(NTApplication.QuestionList.get(6).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(29, 5, "Comentarios: " + NTApplication.QuestionList.get(6).getSimpleItem().getSimple_question_comment());

                    //question 8
                    writeCell(38, 7, getSpinnerText(NTApplication.QuestionList.get(7).getSimpleItem().getSpinnerOptionSelected()));
                    writeCell(36, 5, "Comentarios: " + NTApplication.QuestionList.get(7).getSimpleItem().getSimple_question_comment());

                    if (!workbook.isSheetHidden(SHEET_QA)) {
                        workbook.setSheetHidden(SHEET_QA, true);
                    }
                    if (!workbook.isSheetHidden(SHEET_NOTES)) {
                        workbook.setSheetHidden(SHEET_NOTES, true);
                    }
                    break;

            }

            workbook.setForceFormulaRecalculation(true);

            try {
                FileOutputStream outputStream = new FileOutputStream(String.format("%s%s",
                        AppConstants.getReportPath(),
                        AppConstants.getReportFullName()));
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                workbook.close();

                Toast.makeText(contex, "reporte creado como: " + AppConstants.getReportFullName(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
            /* proper exception handling to be here */
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
