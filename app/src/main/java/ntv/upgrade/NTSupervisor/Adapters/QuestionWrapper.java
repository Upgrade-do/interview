package ntv.upgrade.NTSupervisor.Adapters;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.NTSupervisor.constants.AppConstants;

/**
 * Created by jfrometa on 5/20/2016.
 */
public class QuestionWrapper implements Cloneable {
    private String lQuestion, lHints;
    private int type;
    private int layoutType = SIMPLE_QUESTION;

    public boolean hasValues = true;

    public List<QuestionWrapper> WrapperQuestions = new ArrayList<QuestionWrapper>();
    private QuestionWrapper question;
    //identifiers for the questions layout
    public static final int SIMPLE_QUESTION = 0,
            COMPETENCY_QUESTION = 1,
            DB = 100,
            DEV = 200,
            THEORETICAL = 300;

    private SimpleInterviewItem simpleItem = new SimpleInterviewItem();
    private CompetencyInterviewItem competencyItem = new CompetencyInterviewItem();


    public QuestionWrapper() {

    }

    public String getlHints() {
        return lHints;
    }

    public void setlHints(String lHints) {
        this.lHints = lHints;
    }

    public int getType() {
        return type;
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return lQuestion;
    }

    public void setQuestion(String question) {
        lQuestion = question;
    }

    public List<QuestionWrapper> getQAQuestionList() {
        return WrapperQuestions;
    }

    public SimpleInterviewItem getSimpleItem() {
        return simpleItem;
    }

    public void setSimpleItem(SimpleInterviewItem simpleItem) {
        this.simpleItem = simpleItem;
    }

    public CompetencyInterviewItem getCompetencyItem() {
        return competencyItem;
    }

    public void setCompetencyItem(CompetencyInterviewItem competencyItem) {
        this.competencyItem = competencyItem;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class SimpleInterviewItem implements Cloneable {
        private int spinnerOptionSelected = 0;
        private String simple_question_comment = "";

        public int getSpinnerOptionSelected() {
            return spinnerOptionSelected;
        }

        public void setspinnerOptionSelected(int option) {
            this.spinnerOptionSelected = option;
        }

        public String getSimple_question_comment() {
            return simple_question_comment;
        }

        public void setSimple_question_comment(String simple_question_comment) {
            this.simple_question_comment = simple_question_comment;
        }

        public SimpleInterviewItem getClone() throws CloneNotSupportedException {
            return (SimpleInterviewItem) this.clone();
        }
    }

    public class CompetencyInterviewItem implements Cloneable {
        private int spinnerOptionSelected0 = 0,
                spinnerOptionSelected1 = 0,
                spinnerOptionSelected2 = 0,
                spinnerOptionSelected4 = 0,
                spinnerOptionSelected5 = 0,
                spinnerOptionSelected3 = 0;
        private String competency_question_comment = "";

        public int getSpinnerOptionSelected0() {
            return spinnerOptionSelected0;
        }

        public void setSpinnerOptionSelected0(int spinnerOptionSelected0) {
            this.spinnerOptionSelected0 = spinnerOptionSelected0;
        }

        public int getSpinnerOptionSelected1() {
            return spinnerOptionSelected1;
        }

        public void setSpinnerOptionSelected1(int spinnerOptionSelected1) {
            this.spinnerOptionSelected1 = spinnerOptionSelected1;
        }

        public int getSpinnerOptionSelected2() {
            return spinnerOptionSelected2;
        }

        public void setSpinnerOptionSelected2(int spinnerOptionSelected2) {
            this.spinnerOptionSelected2 = spinnerOptionSelected2;
        }

        public int getSpinnerOptionSelected3() {
            return spinnerOptionSelected3;
        }

        public void setSpinnerOptionSelected3(int spinnerOptionSelected3) {
            this.spinnerOptionSelected3 = spinnerOptionSelected3;
        }

        public int getSpinnerOptionSelected4() {
            return spinnerOptionSelected4;
        }

        public void setSpinnerOptionSelected4(int spinnerOptionSelected4) {
            this.spinnerOptionSelected4 = spinnerOptionSelected4;
        }

        public int getSpinnerOptionSelected5() {
            return spinnerOptionSelected5;
        }

        public void setSpinnerOptionSelected5(int spinnerOptionSelected5) {
            this.spinnerOptionSelected5 = spinnerOptionSelected5;
        }

        public String getCompetency_question_comment() {
            return competency_question_comment;
        }

        public void setCompetency_question_comment(String competency_question_comment) {
            this.competency_question_comment = competency_question_comment;
        }

        public CompetencyInterviewItem getClone() throws CloneNotSupportedException {
            return (CompetencyInterviewItem) clone();
        }

        public StringBuilder getCompetencySkillDetails(int type) {
            StringBuilder skills = new StringBuilder();
            switch (type) {
                case DEV:
                    if (getSpinnerOptionSelected0() > 0){
                        skills.append(AppConstants.programingLangList.get(0));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected0()));
                        skills.append(", ");
                    }

                    if (getSpinnerOptionSelected1() > 0) {
                        skills.append(AppConstants.programingLangList.get(1));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected1()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected2() > 0) {
                        skills.append(AppConstants.programingLangList.get(2));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected2()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected3() > 0) {
                        skills.append(AppConstants.programingLangList.get(3));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected3()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected4() > 0) {
                        skills.append(AppConstants.programingLangList.get(4));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected4()));
                        skills.append(", ");
                    }

                    break;

                case DB:

                    if (getSpinnerOptionSelected0() > 0) {
                        skills.append(AppConstants.DBTypeList.get(0));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected0()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected1() > 0){
                        skills.append(AppConstants.DBTypeList.get(1));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected1()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected2() > 0){
                        skills.append(AppConstants.DBTypeList.get(2));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected2()));
                        skills.append(", ");
                    }
                    if (getSpinnerOptionSelected3() > 0){
                        skills.append(AppConstants.DBTypeList.get(3));
                        skills.append(" ");
                        skills.append(Translate(getSpinnerOptionSelected3()));
                        skills.append(", ");
                    }

                    break;
            }

            if(skills.length() >0){
                if(skills.substring(skills.length()-2, skills.length()).equals(", ")){
                    skills.delete(skills.length()-2, skills.length());
                    skills.append(".");
                }
            }

            return skills;
        }

        private String Translate(int index) {
            String skillFeedBack = " ";
            if (index == 1) {
                skillFeedBack = "(No Satisfactorio)";
            } else if (index == 2) {
                skillFeedBack = "(Satisfactorio)";
            } else if (index == 3) {
                skillFeedBack = "(Excelente)";
            }
            return skillFeedBack;
        }
    }

    public void createQAInterviewData() throws CloneNotSupportedException {


        question = new QuestionWrapper();

        question.setQuestion(AppConstants.QAQuestions.get(0));
        question.setlHints(AppConstants.QAHints.get(0));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(1));
        question.setlHints(AppConstants.QAHints.get(1));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(2));
        question.setlHints(AppConstants.QAHints.get(2));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(3));
        question.setlHints("");
        question.setType(QuestionWrapper.DEV);
        question.setLayoutType(COMPETENCY_QUESTION);
        question.setCompetencyItem(competencyItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(4));
        question.setlHints("");
        question.setType(QuestionWrapper.DB);
        question.setCompetencyItem(competencyItem.getClone());
        question.setLayoutType(COMPETENCY_QUESTION);
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(5));
        question.setlHints("");
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(6));
        question.setlHints("");
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.QAQuestions.get(7));
        question.setlHints(AppConstants.QAHints.get(3));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());


    }

    public void createDevInterviewData() throws CloneNotSupportedException {
        question = new QuestionWrapper();

        question.setQuestion(AppConstants.DevQuestions.get(0));
        question.setlHints(AppConstants.DevHints.get(0));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(1));
        question.setlHints(AppConstants.DevHints.get(1));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(2));
        question.setlHints(AppConstants.DevHints.get(2));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(3));
        question.setlHints(AppConstants.DevHints.get(3));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(4));
        question.setlHints("");
        question.setType(QuestionWrapper.DEV);
        question.setCompetencyItem(competencyItem.getClone());
        question.setLayoutType(COMPETENCY_QUESTION);
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(5));
        question.setlHints("");
        question.setType(QuestionWrapper.DB);
        question.setLayoutType(COMPETENCY_QUESTION);
        question.setCompetencyItem(competencyItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(6));
        question.setlHints(AppConstants.DevHints.get(4));
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());

        question.setQuestion(AppConstants.DevQuestions.get(7));
        question.setlHints("");
        question.setType(QuestionWrapper.THEORETICAL);
        question.setLayoutType(SIMPLE_QUESTION);
        question.setSimpleItem(simpleItem.getClone());
        WrapperQuestions.add((QuestionWrapper) question.clone());


    }
}
