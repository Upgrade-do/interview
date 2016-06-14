package ntv.upgrade.NTSupervisor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import ntv.upgrade.NTSupervisor.Adapters.GradeAdapter;
import ntv.upgrade.NTSupervisor.Adapters.GradeAdapterGreen;
import ntv.upgrade.NTSupervisor.Adapters.InterviewAdapter;
import ntv.upgrade.NTSupervisor.Adapters.QuestionWrapper;
import ntv.upgrade.NTSupervisor.constants.AppConstants;
import ntv.upgrade.NTSupervisor.excel.ExcelTools;

public class ActivityMain extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static final int QA_INTERVIEW = 0;
    public static final int DEV_INTERVIEW = 1;
    private static int interviewCode = 0;
    private static Activity thisActivity;
    private MenuItem itemInterviewType;
    //toolbar
    private Toolbar toolbar;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //view Elements
    private RecyclerView recyclerView;
    private InterviewAdapter nAdapter;
    private LinearLayoutManager mLayoutManager;

    //Header view Elements
    private TextInputEditText vEval, vCandidate;
    private TextView vDate;
    private Spinner vGrade;

    //header data set holder
    private NTApplication.ActivityHeader mHeader;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public static int getInterviewCode() {
        return interviewCode;
    }

    public static void setInterviewCode(int interviewCode) {
        ActivityMain.interviewCode = interviewCode;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisActivity = this;
        //push up the screen when an edit text is clicked
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);

        mHeader = NTApplication.getHeader();
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        nAdapter = new InterviewAdapter(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
       // collapsingToolbarLayout.setTitle("nInterView");
        collapsingToolbarLayout.setTitleEnabled(false);

       // collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.ic_action_nt2);

        bindLayout();
    }

    private void bindLayout() {


        //get the current date and post it.
        vDate = (TextView) findViewById(R.id.activity_date_text);
        vDate.setText(mHeader.getDate());


        recyclerView = (RecyclerView) findViewById(R.id.activity_main_supervisor_recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(nAdapter);
        }

        vCandidate = (TextInputEditText) findViewById(R.id.activity_candidate_name);
        vCandidate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mHeader.setCandidatename(String.valueOf(v.getText()));
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                return true;
            }
        });
        vCandidate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mHeader.setCandidatename(String.valueOf(vCandidate.getText()));
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }

            }
        });
        vCandidate.setText(mHeader.getCandidatename());

        vEval = (TextInputEditText) findViewById(R.id.activity_interviewer_name);
        vEval.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mHeader.setEvaluatorname(String.valueOf(v.getText()));
                }
                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
        });
        vEval.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mHeader.setCandidatename(String.valueOf(vEval.getText()));
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }

            }
        });
        vEval.setText(mHeader.getEvaluatorname());


        vGrade = (Spinner) findViewById(R.id.activity_grade_spinner);
        GradeAdapterGreen gradeAdapterGreen = new GradeAdapterGreen(this);
        vGrade.setAdapter(gradeAdapterGreen);


        //spinner listner
        vGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mHeader.setGrade(position);
                if(view != null){
                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        vGrade.setSelection(mHeader.getGrade());

    }

    public void getHeaderInfo() {
        //saves the current values of the header to be printed
        mHeader.setEvaluatorname(String.valueOf(vEval.getText()));
        mHeader.setCandidatename(String.valueOf(vCandidate.getText()));
        mHeader.setGrade(vGrade.getSelectedItemPosition());
        mHeader.setDate(String.valueOf(vDate.getText()));
    }

    public void writeToExcel() {

        getHeaderInfo();
        ExcelTools.createBlankReport(getAssets());
        AppConstants.setReportName(mHeader.getDate() + "_" + mHeader.getCandidatename());
        ExcelTools.saveChangesToReport(getAssets(), getInterViewType(), this);

    }

    //TODO fix this method
    public int getInterViewType() {
        // TextInputLayout othersText = (TextInputLayout) v.findViewById(R.id.competency_question_others_input);
        int type =0;

        if (itemInterviewType.getTitle() == "Dev") {
           type = ActivityMain.DEV_INTERVIEW;
        } else if(itemInterviewType.getTitle() == "QA") {
            type = ActivityMain.QA_INTERVIEW;
        }
        return type;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        itemInterviewType = menu.findItem(R.id.action_clipboard_switch);
        itemInterviewType.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getTitle().equals("QA")) {
                    //clean the adapter
                    nAdapter.removeAll();

                    QuestionWrapper question = new QuestionWrapper();

                    try {
                        question.createDevInterviewData();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    NTApplication.QuestionList = question.getQAQuestionList();

                    nAdapter = new InterviewAdapter(thisActivity);
                    recyclerView.setAdapter(nAdapter);
                    item.setTitle("Dev");

                } else {
                    //clean the adapter
                    nAdapter.removeAll();

                    QuestionWrapper question = new QuestionWrapper();

                    try {
                        question.createQAInterviewData();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    NTApplication.QuestionList = question.getQAQuestionList();

                    nAdapter = new InterviewAdapter(thisActivity);
                    recyclerView.setAdapter(nAdapter);
                    item.setTitle("QA");
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_clipboard_switch) {
            return true;
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.save_to_excel) {
            writeToExcel();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
