package ntv.upgrade.NTSupervisor.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import ntv.upgrade.NTSupervisor.R;
import ntv.upgrade.NTSupervisor.constants.AppConstants;

/**
 * Created by jfrometa on 5/18/2016.
 */
public class InterviewAdapter extends NTAdapter<InterviewAdapter.InterviewHolder> {
    private Context mContext;

    public static final int SIMPLE_QUESTION = 0, COMPETENCY_QUESTION = 1;


    public InterviewAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public InterviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType) {
            case SIMPLE_QUESTION:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_simple_question, parent, false);
                break;
            case COMPETENCY_QUESTION:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_competency_question, parent, false);
                break;
        }

        return new InterviewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(final InterviewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case SIMPLE_QUESTION:
                final QuestionWrapper sE = items.get(position);

                holder._simple_question_title.setText(sE.getQuestion());
                holder._simple_question_hint.setText(sE.getlHints());

                holder._simple_question_spinner.setAdapter(new GradeAdapter(mContext));
                holder._simple_question_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        sE.getSimpleItem().setspinnerOptionSelected(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._simple_question_spinner.setSelection(sE.getSimpleItem().getSpinnerOptionSelected());

                if (!sE.getSimpleItem().equals(null)) {
                    holder._simple_question_comment.setText(sE.getSimpleItem().getSimple_question_comment());
                }
                holder._simple_question_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            sE.getSimpleItem().setSimple_question_comment(
                                    String.valueOf(v.getText())
                            );
                            InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                        return true;
                    }
                });
                holder._simple_question_comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                        sE.getSimpleItem().setSimple_question_comment(
                                String.valueOf(holder._simple_question_comment.getText()));}
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                });

                break;

            case COMPETENCY_QUESTION:
                final QuestionWrapper cE = items.get(position);
                //question
                holder._competency_question_title.setText(cE.getQuestion());
                // always visible : first language & rate
                if (cE.getType() == QuestionWrapper.DEV) {
                    holder._competency_question_hint_0.setText(AppConstants.programingLangList.get(0));
                    holder._competency_question_spinner_0.setAdapter(new GradeAdapter(mContext));

                    // always gone : made visible as languages & rates are added
                    holder._competency_question_frame_1.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_1.setText(AppConstants.programingLangList.get(1));
                    holder._competency_question_spinner_1.setAdapter(new GradeAdapter(mContext));

                    holder._competency_question_frame_2.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_2.setText(AppConstants.programingLangList.get(2));
                    holder._competency_question_spinner_2.setAdapter(new GradeAdapter(mContext));

                    holder._competency_question_frame_3.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_3.setText(AppConstants.programingLangList.get(3));
                    holder._competency_question_spinner_3.setAdapter(new GradeAdapter(mContext));

                    holder._competency_question_frame_4.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_4.setText(AppConstants.programingLangList.get(4));
                    holder._competency_question_spinner_4.setAdapter(new GradeAdapter(mContext));

                } else if (cE.getType() == QuestionWrapper.DB) {
                    holder._competency_question_hint_0.setText(AppConstants.DBTypeList.get(0));
                    holder._competency_question_spinner_0.setAdapter(new GradeAdapter(mContext));

                    // always gone : made visible as languages & rates are added
                    holder._competency_question_frame_1.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_1.setText(AppConstants.DBTypeList.get(1));
                    holder._competency_question_spinner_1.setAdapter(new GradeAdapter(mContext));

                    holder._competency_question_frame_2.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_2.setText(AppConstants.DBTypeList.get(2));
                    holder._competency_question_spinner_2.setAdapter(new GradeAdapter(mContext));

                    holder._competency_question_frame_3.setVisibility(View.VISIBLE);
                    holder._competency_question_hint_3.setText(AppConstants.DBTypeList.get(3));
                    holder._competency_question_spinner_3.setAdapter(new GradeAdapter(mContext));

                }


                //holder 0
                holder._competency_question_spinner_0.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected0(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_0.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected0());
                //holder 1
                holder._competency_question_spinner_1.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected1(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_1.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected1());
                //holder 2
                holder._competency_question_spinner_2.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected2(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_2.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected2());
                //holder 3
                holder._competency_question_spinner_3.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected3(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_3.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected3());
                //holder 4
                holder._competency_question_spinner_4.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected4(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_4.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected4());

                //holder 5
                holder._competency_question_spinner_5.setAdapter(new GradeAdapter(mContext));
                holder._competency_question_spinner_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cE.getCompetencyItem().setSpinnerOptionSelected5(position);
                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder._competency_question_spinner_5.setSelection(cE.getCompetencyItem().getSpinnerOptionSelected5());

                //Comment
                if (!cE.getCompetencyItem().equals(null)) {
                    holder._competency_question_comment.setText(cE.getCompetencyItem().getCompetency_question_comment());
                }
                holder._competency_question_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            cE.getCompetencyItem().setCompetency_question_comment(
                                    String.valueOf(v.getText())
                            );
                        }


                        InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        return true;
                    }
                });

                holder._competency_question_comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                        cE.getCompetencyItem().setCompetency_question_comment(
                                String.valueOf(holder._competency_question_comment.getText()));
                            InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                });

                break;
        }

    }


    public class InterviewHolder extends RecyclerView.ViewHolder {

        //simple variables
        private TextView _simple_question_title, _simple_question_hint;
        private TextInputEditText _simple_question_comment;
        private Spinner _simple_question_spinner;

        //competency variables
        private Spinner _competency_question_spinner_0, _competency_question_spinner_1, _competency_question_spinner_2,
                _competency_question_spinner_3, _competency_question_spinner_4, _competency_question_spinner_5;

        private TextView _competency_question_title, _competency_question_hint_0, _competency_question_hint_1,
                _competency_question_hint_2, _competency_question_hint_3, _competency_question_hint_4, _competency_question_hint_5;

        private LinearLayout _competency_question_frame_1, _competency_question_frame_2, _competency_question_frame_3,
                _competency_question_frame_4, _competency_question_frame_5;

        private TextInputEditText  _competency_question_comment;


        public InterviewHolder(View itemView, int type) {
            super(itemView);

            switch (type) {
                case SIMPLE_QUESTION:
                    _simple_question_title = (TextView) itemView.findViewById(R.id.simple_question_title);
                    _simple_question_hint = (TextView) itemView.findViewById(R.id.simple_question_hint);
                    _simple_question_spinner = (Spinner) itemView.findViewById(R.id.simple_question_spinner);
                    _simple_question_comment = (TextInputEditText) itemView.findViewById(R.id.simple_question_comment);

                    break;
                case COMPETENCY_QUESTION:
                    //question
                    _competency_question_title = (TextView) itemView.findViewById(R.id.competency_question_title);
                    // always visible : first language & rate
                    _competency_question_hint_0 = (TextView) itemView.findViewById(R.id.competency_question_hint_0);
                    _competency_question_spinner_0 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_0);

                    // always gone : made visible as languages & rates are added
                    _competency_question_frame_1 = (LinearLayout) itemView.findViewById(R.id.competency_question_frame_1);
                    _competency_question_hint_1 = (TextView) itemView.findViewById(R.id.competency_question_hint_1);
                    _competency_question_spinner_1 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_1);

                    _competency_question_frame_2 = (LinearLayout) itemView.findViewById(R.id.competency_question_frame_2);
                    _competency_question_hint_2 = (TextView) itemView.findViewById(R.id.competency_question_hint_2);
                    _competency_question_spinner_2 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_2);

                    _competency_question_frame_3 = (LinearLayout) itemView.findViewById(R.id.competency_question_frame_3);
                    _competency_question_hint_3 = (TextView) itemView.findViewById(R.id.competency_question_hint_3);
                    _competency_question_spinner_3 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_3);

                    _competency_question_frame_4 = (LinearLayout) itemView.findViewById(R.id.competency_question_frame_4);
                    _competency_question_hint_4 = (TextView) itemView.findViewById(R.id.competency_question_hint_4);
                    _competency_question_spinner_4 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_4);

                    _competency_question_frame_5 = (LinearLayout) itemView.findViewById(R.id.competency_question_frame_5);
                    _competency_question_hint_5 = (TextView) itemView.findViewById(R.id.competency_question_hint_5);
                    _competency_question_spinner_5 = (Spinner) itemView.findViewById(R.id.competency_question_spinner_5);


                    //interviewer comments
                    _competency_question_comment = (TextInputEditText) itemView.findViewById(R.id.competency_question_comment);
                    break;
            }

        }

    }
}
