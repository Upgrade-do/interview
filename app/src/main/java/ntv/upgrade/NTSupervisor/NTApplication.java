package ntv.upgrade.NTSupervisor;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ntv.upgrade.NTSupervisor.Adapters.QuestionWrapper;

/**
 * Created by xeros on 3/19/2016.
 */
public class NTApplication extends MultiDexApplication {


    public static List<QuestionWrapper> QuestionList;
    //header data set holder
    public static ActivityHeader header ;
    //Header view Elements





    @Override
    public void onCreate() {
        super.onCreate();

        SimpleDateFormat mSimpleDate = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime =  mSimpleDate.format(new Date());
        QuestionWrapper question= new QuestionWrapper();


        try {
            question.createQAInterviewData();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        QuestionList = question.getQAQuestionList();
        header = new ActivityHeader(currentDateandTime);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static ActivityHeader getHeader() {
        return header;
    }

    public  static List<QuestionWrapper> getQuestionList(){
        return QuestionList;
    }

    public class ActivityHeader {

        public ActivityHeader(String date) {
            this.date = date;
        }

        private String candidatename="",
                evaluatorname="",
                date;
       private int grade =0;

        public String getCandidatename() {
            return candidatename;
        }

        public void setCandidatename(String candidatename) {
            this.candidatename = candidatename;
        }

        public String getEvaluatorname() {
            return evaluatorname;
        }

        public void setEvaluatorname(String evaluatorname) {
            this.evaluatorname = evaluatorname;
        }

        public int getGrade() {
            return grade;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }


    }

}
