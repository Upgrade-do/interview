package ntv.upgrade.NTSupervisor.csvtools;

import android.os.Environment;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.NTSupervisor.constants.AppConstants;

/**
 * Created by paulino on 23/02/15.
 */
public class Reader {

    public static String csv = AppConstants.SELECTED_FILE;

    public static List<String[]> createReportStructure(){

        if (AppConstants.SELECTED_FILE.equals("selected_file")) {

            csv = Environment.getExternalStorageDirectory() + "/NTInterview/Reportes/" + AppConstants.getReportName() + ".ntv";
        }

        String next[] = {};
        List<String[]> list = new ArrayList<String[]>();

        try {
            CSVReader reader = new CSVReader(new FileReader(csv));
            while(true) {
                next = reader.readNext();
                if(next != null) {
                    list.add(next);

                } else {
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void fillReportFromStructure(List<String[]> list){

     /*   AppConstants.CLIENT_INFORMATION_OBJECT.setClientId(list.get(1)[0]);
        AppConstants.CLIENT_INFORMATION_OBJECT.setName(list.get(1)[1]);
        AppConstants.CLIENT_INFORMATION_OBJECT.setPhoneNumber(list.get(1)[2]);
        AppConstants.CLIENT_INFORMATION_OBJECT.seteMail(list.get(1)[3]);
        AppConstants.CLIENT_INFORMATION_OBJECT.setContact(list.get(1)[4]);

        AppConstants.REPORT_OBJECT.setReportId(list.get(2)[0]);
        AppConstants.REPORT_OBJECT.setInspector(list.get(2)[1]);
        AppConstants.REPORT_OBJECT.setInspectionNumber(list.get(2)[2]);
        AppConstants.REPORT_OBJECT.setDate(list.get(2)[3]);
        AppConstants.REPORT_OBJECT.setAuditedBusiness(list.get(2)[4]);
        AppConstants.REPORT_OBJECT.setAssociatedCompanion(list.get(2)[5]);

        for (int i = 0; i < AppConstants.REPORT_OBJECT.headerObjectArrayList.size(); i++) {
            AppConstants.REPORT_OBJECT.headerObjectArrayList.get(i).setGrade(Integer.parseInt(list.get(3)[i]));
            AppConstants.REPORT_OBJECT.headerObjectArrayList.get(i).setStatus(Integer.parseInt(list.get(4)[i]));
        }

        for (int i = 0; i < AppConstants.REPORT_OBJECT.elementObjectArrayList.size(); i++) {
            AppConstants.REPORT_OBJECT.elementObjectArrayList.get(i).setStatus(Integer.parseInt(list.get(5)[i]));
            AppConstants.REPORT_OBJECT.elementObjectArrayList.get(i).setComment(list.get(6)[i]);
            if (list.get(6)[i]!=""){
                AppConstants.REPORT_OBJECT.elementObjectArrayList.get(i).setCommentImage(1);
            }


        }*/

    }
}