package ntv.upgrade.NTSupervisor.csvtools;

import android.os.Environment;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.NTSupervisor.constants.AppConstants;

/**
 * Created by paulino on 23/02/15.
 */
public class Writer {

    public static String csv = Environment.getExternalStorageDirectory() + "/ABT/Reportes/" + AppConstants.getReportName() + ".ntv";

    public static boolean saveReportStructure() {

      /*  String[] headersGades = new String[AppConstants.REPORT_OBJECT.headerObjectArrayList.size()],
                headersStatus = new String[AppConstants.REPORT_OBJECT.headerObjectArrayList.size()],
                elementsComments = new String[AppConstants.REPORT_OBJECT.elementObjectArrayList.size()],
                elementsStatus = new String[AppConstants.REPORT_OBJECT.elementObjectArrayList.size()];*/

        if (!AppConstants.SELECTED_FILE.equals("selected_file")) {
            csv = AppConstants.SELECTED_FILE;
        }

        boolean success = false;

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csv));

            List<String[]> data = new ArrayList<String[]>();

            data.add(new String[]{AppConstants.getReportName()});
/*

            data.add(new String[]{AppConstants.CLIENT_INFORMATION_OBJECT.getClientId(),
                    AppConstants.CLIENT_INFORMATION_OBJECT.getName(),
                    AppConstants.CLIENT_INFORMATION_OBJECT.getPhoneNumber(),
                    AppConstants.CLIENT_INFORMATION_OBJECT.geteMail(),
                    AppConstants.CLIENT_INFORMATION_OBJECT.getContact()});

            data.add(new String[]{AppConstants.REPORT_OBJECT.getReportId(),
                    AppConstants.REPORT_OBJECT.getInspector(),
                    AppConstants.REPORT_OBJECT.getInspectionNumber(),
                    AppConstants.REPORT_OBJECT.getDate(),
                    AppConstants.REPORT_OBJECT.getAuditedBusiness(),
                    AppConstants.REPORT_OBJECT.getAssociatedCompanion()});

            for (int i = 0; i < AppConstants.REPORT_OBJECT.headerObjectArrayList.size(); i++) {
                headersGades[i] = ""+AppConstants.REPORT_OBJECT.getHeaderObjectArrayList().get(i).getGrade();
                headersStatus[i] = ""+AppConstants.REPORT_OBJECT.getHeaderObjectArrayList().get(i).getStatus();
            }
            data.add(headersGades);
            data.add(headersStatus);

            for (int i = 0; i < AppConstants.REPORT_OBJECT.elementObjectArrayList.size(); i++) {
                elementsComments[i] = ""+AppConstants.REPORT_OBJECT.getElementObjectArrayList().get(i).getComment();
                elementsStatus[i] = ""+AppConstants.REPORT_OBJECT.getElementObjectArrayList().get(i).getStatus();

            }
            data.add(elementsStatus);
            data.add(elementsComments);

            writer.writeAll(data);
            writer.close();
*/

            success=true;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return success;
    }
}