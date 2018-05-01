package com.resultier.pktrackit.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.resultier.pktrackit.R;
import com.resultier.pktrackit.dialog.FinalSurveyDialogFragment;
import com.resultier.pktrackit.dialog.FinalSurveyDialogFragment2;
import com.resultier.pktrackit.dialog.SurveyDialogFragment;
import com.resultier.pktrackit.dialog.SurveyDialogFragment2;
import com.resultier.pktrackit.utils.AppConfigTags;
import com.resultier.pktrackit.utils.AppConfigURL;
import com.resultier.pktrackit.utils.AppDetailsPref;
import com.resultier.pktrackit.utils.Constants;
import com.resultier.pktrackit.utils.NetworkConnection;
import com.resultier.pktrackit.utils.SetTypeFace;
import com.resultier.pktrackit.utils.Utils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    AppDetailsPref appDetailsPref;
    TextView tvDay;
    TextView tvDate;
    TextView tvProductCode;
    
    RelativeLayout rlMain;
    RelativeLayout rlEndDay;
    
    RelativeLayout rlInstructions;
    RelativeLayout rlStartSurvey;
    
    RelativeLayout rlConclusion;
    RelativeLayout rlStartSurvey2;
    
    RelativeLayout rlLabReport;
    RelativeLayout rlLogout2;
    RelativeLayout rlSurveyComplete;
    RelativeLayout rlLogout;
    
    RelativeLayout rlDayComplete;
    
    
    int button1 = 0; // Pouch Product Provided
    int button2 = 0; // Your Own Loose Product
    int button3 = 0; // Different Tobacco Product
    ArrayList<String> button3_products_list = new ArrayList<String> (); // Different Tobacco Product
    int button4 = 0; // Your Own Pouch Product
    
    RelativeLayout rlButton1, rlButton2, rlButton3, rlButton4;
    TextView tvButton1, tvButton2, tvButton3, tvButton4;
    
    ImageView ivMinus1, ivMinus2, ivMinus3, ivMinus4, ivPlus1, ivPlus2, ivPlus3, ivPlus4;
    
    ProgressDialog progressDialog;
    CoordinatorLayout clMain;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
//        setAlarm ();
        initView ();
        initData ();
        initListener ();
        isLogin ();
    }
    
    private void initView () {
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        tvDay = (TextView) findViewById (R.id.tvDay);
        tvDate = (TextView) findViewById (R.id.tvDate);
        tvProductCode = (TextView) findViewById (R.id.tvProductCode);
        
        rlInstructions = (RelativeLayout) findViewById (R.id.rlInstruction);
        rlStartSurvey = (RelativeLayout) findViewById (R.id.rlStartSurvey);
        rlMain = (RelativeLayout) findViewById (R.id.rlMain);
        rlEndDay = (RelativeLayout) findViewById (R.id.rlEndDay);
        rlConclusion = (RelativeLayout) findViewById (R.id.rlConclusion);
        rlStartSurvey2 = (RelativeLayout) findViewById (R.id.rlStartSurvey2);
        rlLabReport = (RelativeLayout) findViewById (R.id.rlLabReport);
        rlSurveyComplete = (RelativeLayout) findViewById (R.id.rlSurveyComplete);
        rlLogout = (RelativeLayout) findViewById (R.id.rlLogout);
        rlLogout2 = (RelativeLayout) findViewById (R.id.rlLogout2);
        rlDayComplete = (RelativeLayout) findViewById (R.id.rlDayComplete);
        
        rlButton1 = (RelativeLayout) findViewById (R.id.rlButton1);
        rlButton2 = (RelativeLayout) findViewById (R.id.rlButton2);
        rlButton3 = (RelativeLayout) findViewById (R.id.rlButton3);
        rlButton4 = (RelativeLayout) findViewById (R.id.rlButton4);
        
        ivMinus1 = (ImageView) findViewById (R.id.ivMinus1);
        ivMinus2 = (ImageView) findViewById (R.id.ivMinus2);
        ivMinus3 = (ImageView) findViewById (R.id.ivMinus3);
        ivMinus4 = (ImageView) findViewById (R.id.ivMinus4);
        ivPlus1 = (ImageView) findViewById (R.id.ivPlus1);
        ivPlus2 = (ImageView) findViewById (R.id.ivPlus2);
        ivPlus3 = (ImageView) findViewById (R.id.ivPlus3);
        ivPlus4 = (ImageView) findViewById (R.id.ivPlus4);
        
        tvButton1 = (TextView) findViewById (R.id.tvNumber1);
        tvButton2 = (TextView) findViewById (R.id.tvNumber2);
        tvButton3 = (TextView) findViewById (R.id.tvNumber3);
        tvButton4 = (TextView) findViewById (R.id.tvNumber4);
    }
    
    private void initData () {
        appDetailsPref = AppDetailsPref.getInstance ();
        progressDialog = new ProgressDialog (this);
        Utils.setTypefaceToAllViews (this, tvDay);
        
        button1 = appDetailsPref.getIntPref (this, AppDetailsPref.BUTTON1);
        button2 = appDetailsPref.getIntPref (this, AppDetailsPref.BUTTON2);
        button3 = appDetailsPref.getIntPref (this, AppDetailsPref.BUTTON3);
        button4 = appDetailsPref.getIntPref (this, AppDetailsPref.BUTTON4);
        button3_products_list = new ArrayList<String> (Arrays.asList (appDetailsPref.getStringPref (this, AppDetailsPref.BUTTON3_PRODUCTS).split (",")));
        
        tvButton1.setText (String.valueOf (button1));
        tvButton2.setText (String.valueOf (button2));
        tvButton3.setText (String.valueOf (button3));
        tvButton4.setText (String.valueOf (button4));
        
        switch (appDetailsPref.getIntPref (this, AppDetailsPref.SURVEY_STATUS)) {
            case 0:
                rlInstructions.setVisibility (View.VISIBLE);
                break;
            case 1:
                rlMain.setVisibility (View.VISIBLE);
                break;
            case 2:
                rlConclusion.setVisibility (View.VISIBLE);
                break;
            case 3:
                rlLabReport.setVisibility (View.VISIBLE);
                break;
            case 4:
                rlSurveyComplete.setVisibility (View.VISIBLE);
                break;
        }
        
        tvProductCode.setText ("Prod # " + appDetailsPref.getStringPref (this, AppDetailsPref.PRODUCT_CODE));
        if (appDetailsPref.getIntPref (this, AppDetailsPref.SURVEY_DAY_ELAPSED) < 7) {
            tvDay.setText ("Day : " + (appDetailsPref.getIntPref (this, AppDetailsPref.SURVEY_DAY_ELAPSED) + 1) + " (Week " + appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.WEEK_NUMBER) + ")");
        } else {
            tvDay.setVisibility (View.GONE);
        }
        
        Calendar c = Calendar.getInstance ();
        SimpleDateFormat df = new SimpleDateFormat ("dd-MMM-yyyy", Locale.US);
        tvDate.setText (df.format (c.getTime ()));

//        SimpleDateFormat df2 = new SimpleDateFormat ("yyyy-MM-dd", Locale.US);
//        Log.e ("karman", "" + Utils.getDaysBetweenDates (Utils.convertTimeFormat (appDetailsPref.getStringPref (this, AppDetailsPref.SURVEY_STARTED_AT),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"), df2.format (c.getTime ())));
    }
    
    private void initListener () {
        rlStartSurvey2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                FragmentTransaction ft = getFragmentManager ().beginTransaction ();
                switch (appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.USER_GROUP)) {
                    case 1:
                        FinalSurveyDialogFragment frag = FinalSurveyDialogFragment.newInstance ();
                        frag.show (ft, "4");
                        break;
                    case 2:
                        FinalSurveyDialogFragment2 frag2 = FinalSurveyDialogFragment2.newInstance ();
                        frag2.show (ft, "4");
                        break;
                }
            }
        });
        
        ivMinus1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (button1 > 0) {
                    button1--;
                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1, button1);
                    tvButton1.setText (String.valueOf (button1));
                }
            }
        });
        
        ivMinus2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (button2 > 0) {
                    button2--;
                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON2, button2);
                    tvButton2.setText (String.valueOf (button2));
                }
            }
        });
        ivMinus3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (button3 > 0) {
                    button3--;
                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, button3);
                    tvButton3.setText (String.valueOf (button3));
                }
            }
        });
        ivMinus4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (button4 > 0) {
                    button4--;
                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4, button4);
                    tvButton4.setText (String.valueOf (button4));
                }
            }
        });
        
        ivPlus1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                new MaterialDialog.Builder (MainActivity.this)
                        .content (R.string.dialog_title_1)
                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                        .inputType (InputType.TYPE_CLASS_NUMBER)
                        .input ("", "", new MaterialDialog.InputCallback () {
                            @Override
                            public void onInput (MaterialDialog dialog, CharSequence input) {
                                if (input.length () > 0) {
                                    button1++;
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1_POUCHES, appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.BUTTON1_POUCHES) + Integer.parseInt (input.toString ()));
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1, button1);
                                    tvButton1.setText (String.valueOf (button1));
                                }
                            }
                        }).show ();
            }
        });
        
        ivPlus2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                button2++;
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON2, button2);
                tvButton2.setText (String.valueOf (button2));
            }
        });
        
        ivPlus3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
/*
                List<String> productList = new ArrayList<> ();
                productList.add (getResources ().getString (R.string.question7a1));
                productList.add (getResources ().getString (R.string.question7a2));
                productList.add (getResources ().getString (R.string.question7a3));
                productList.add (getResources ().getString (R.string.question7a4));
                productList.add (getResources ().getString (R.string.question7a5));
                
                new MaterialDialog.Builder (MainActivity.this)
                        .content (getResources ().getString (R.string.dialog_title_2))
                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                        .items (productList)
                        .itemsCallbackSingleChoice (0, new MaterialDialog.ListCallbackSingleChoice () {
                            @Override
                            public boolean onSelection (MaterialDialog dialog, View view, int which, CharSequence text) {
                                button3++;
                                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, button3);
                                tvButton3.setText (String.valueOf (button3));
                                
                                if (text.toString ().length ()>0){
                                    if (! button3_products_list.contains (text.toString ())) {
                                        button3_products_list.add (text.toString ());
                                    }
                                }
//                                String str = "";
//                                if (button3_products_list.size () == 1) {
//                                    str = button3_products_list.get (0);
//                                } else {
//                                    for (int i = 0; i < button3_products_list.size (); i++) {
//                                        if (i == 0) {
//                                            str = button3_products_list.get (i);
//                                        } else {
//                                            str = str + "," + button3_products_list.get (i);
//                                        }
//                                    }
//                                }
                                
                                String str = android.text.TextUtils.join (",", button3_products_list);
                                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.BUTTON3_PRODUCTS, str);
                                Log.e ("karman", "str " + str);
                                return true;
                            }
                        })
                        .positiveText ("OK")
                        .onPositive (new MaterialDialog.SingleButtonCallback () {
                            @Override
                            public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            
                            }
                        })
                        .show ();
*/
                
                button3++;
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, button3);
                tvButton3.setText (String.valueOf (button3));
            }
        });
        
        ivPlus4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                new MaterialDialog.Builder (MainActivity.this)
                        .content (R.string.dialog_title_1)
                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                        .inputType (InputType.TYPE_CLASS_NUMBER)
                        .input ("", "", new MaterialDialog.InputCallback () {
                            @Override
                            public void onInput (MaterialDialog dialog, CharSequence input) {
                                if (input.length () > 0) {
                                    button4++;
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4_POUCHES, appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.BUTTON4_POUCHES) + Integer.parseInt (input.toString ()));
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4, button4);
                                    tvButton4.setText (String.valueOf (button4));
                                }
                            }
                        }).show ();
            }
        });
        
        rlEndDay.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                FragmentTransaction ft = getFragmentManager ().beginTransaction ();
                switch (appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.USER_GROUP)) {
                    case 1:
                        SurveyDialogFragment frag = SurveyDialogFragment.newInstance ();
                        frag.show (ft, "4");
                        break;
                    case 2:
                        SurveyDialogFragment2 frag2 = SurveyDialogFragment2.newInstance ();
                        frag2.show (ft, "4");
                        break;
                }
            }
        });
        
        rlStartSurvey.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                startSurvey (appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.SURVEY_ID));
            }
        });
        
        rlLogout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_NAME, "");
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_MOBILE, "");
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_LOGIN_KEY, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_ID, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_NUMBER, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_STATUS, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_DAY_ELAPSED, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_STARTED_AT, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.PRODUCT_ID, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.PRODUCT_CODE, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON2, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4, 0);
                Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                startActivity (intent);
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        
        rlLogout2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_NAME, "");
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_MOBILE, "");
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_LOGIN_KEY, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_ID, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_NUMBER, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_STATUS, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_DAY_ELAPSED, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_STARTED_AT, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.PRODUCT_ID, 0);
                appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.PRODUCT_CODE, "");
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON2, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, 0);
                appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4, 0);
                Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                startActivity (intent);
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    
    private void isLogin () {
        if (appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.USER_LOGIN_KEY).length () == 0) {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity (intent);
            finish ();
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            initApplication ();
        }
    }
    
    private void initApplication () {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager ().getPackageInfo (getPackageName (), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace ();
        }
        
        if (NetworkConnection.isNetworkAvailable (this)) {
            final PackageInfo finalPInfo = pInfo;
            Utils.showProgressDialog (this, progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.INIT_APPLICATION, true);
            StringRequest strRequest = new StringRequest (Request.Method.POST, AppConfigURL.INIT_APPLICATION,
                    new Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! error) {
                                        if (jsonObj.getInt (AppConfigTags.VERSION_UPDATE) > 0) {
                                            MaterialDialog dialog = new MaterialDialog.Builder (MainActivity.this)
                                                    .title ("New Update Available")
                                                    .content (jsonObj.getString (AppConfigTags.UPDATE_MESSAGE))
                                                    .titleColor (getResources ().getColor (R.color.primary_text))
                                                    .positiveColor (getResources ().getColor (R.color.primary_text))
                                                    .contentColor (getResources ().getColor (R.color.primary_text))
                                                    .negativeColor (getResources ().getColor (R.color.primary_text))
                                                    .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                                                    .canceledOnTouchOutside (false)
                                                    .cancelable (false)
                                                    .positiveText (R.string.dialog_action_update)
                                                    .negativeText (R.string.dialog_action_exit)
                                                    .onPositive (new MaterialDialog.SingleButtonCallback () {
                                                        @Override
                                                        public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                            final String appPackageName = getPackageName ();
                                                            try {
                                                                startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("market://details?id=" + appPackageName)));
                                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                                startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                                            }
                                                        }
                                                    })
                                                    .onNegative (new MaterialDialog.SingleButtonCallback () {
                                                        @Override
                                                        public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                            finish ();
                                                            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                                                        }
                                                    }).build ();
    
                                            dialog.getActionButton (DialogAction.POSITIVE).setOnClickListener (new CustomListener (MainActivity.this, dialog, DialogAction.POSITIVE));
                                            dialog.getActionButton (DialogAction.NEGATIVE).setOnClickListener (new CustomListener (MainActivity.this, dialog, DialogAction.NEGATIVE));
    
                                            dialog.show ();
                                        }
                                        
                                        
                                        appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_STATUS, jsonObj.getInt (AppConfigTags.SURVEY_STATUS));
                                        if (jsonObj.getInt (AppConfigTags.SURVEY_DAY_ELAPSED) < 7) {
                                            appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_DAY_ELAPSED, jsonObj.getInt (AppConfigTags.SURVEY_DAY_ELAPSED));
                                            tvDay.setText ("Day : " + (appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.SURVEY_DAY_ELAPSED) + 1) + " (Week " + appDetailsPref.getIntPref (MainActivity.this, AppDetailsPref.WEEK_NUMBER) + ")");
                                        } else {
                                            tvDay.setVisibility (View.GONE);
                                        }
                                        
                                        switch (jsonObj.getInt (AppConfigTags.SURVEY_STATUS)) {
                                            case 0:
                                                rlInstructions.setVisibility (View.VISIBLE);
                                                rlMain.setVisibility (View.GONE);
                                                rlConclusion.setVisibility (View.GONE);
                                                rlLabReport.setVisibility (View.GONE);
                                                rlSurveyComplete.setVisibility (View.GONE);
                                                rlDayComplete.setVisibility (View.GONE);
                                                break;
                                            case 1:
//                                                if (jsonObj.getString (AppConfigTags.SURVEY_STARTED_AT) != null) {
//                                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_STARTED_AT, jsonObj.getString (AppConfigTags.SURVEY_STARTED_AT));
//                                                    Calendar c = Calendar.getInstance ();
//                                                    SimpleDateFormat df2 = new SimpleDateFormat ("yyyy-MM-dd", Locale.US);
////                                                    Log.e ("karman", "diff time : " + (Utils.getDaysBetweenDates (Utils.convertTimeFormat (jsonObj.getString (AppConfigTags.SURVEY_STARTED_AT), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"), df2.format (c.getTime ())) + 1));
////                                                    Log.e ("karman", "days elapsed  : " + jsonObj.getInt (AppConfigTags.SURVEY_DAY_ELAPSED));
//                                                    if ((Utils.getDaysBetweenDates (Utils.convertTimeFormat (jsonObj.getString (AppConfigTags.SURVEY_STARTED_AT), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"), df2.format (c.getTime ())) + 1) <= jsonObj.getInt (AppConfigTags.SURVEY_DAY_ELAPSED)) {
//                                                        rlInstructions.setVisibility (View.GONE);
//                                                        rlMain.setVisibility (View.GONE);
//                                                        rlConclusion.setVisibility (View.GONE);
//                                                        rlLabReport.setVisibility (View.GONE);
//                                                        rlSurveyComplete.setVisibility (View.GONE);
//                                                        rlDayComplete.setVisibility (View.VISIBLE);
//                                                    } else {
//                                                        rlInstructions.setVisibility (View.GONE);
//                                                        rlMain.setVisibility (View.VISIBLE);
//                                                        rlConclusion.setVisibility (View.GONE);
//                                                        rlLabReport.setVisibility (View.GONE);
//                                                        rlSurveyComplete.setVisibility (View.GONE);
//                                                        rlDayComplete.setVisibility (View.GONE);
//                                                    }
//                                                } else {
                                                rlInstructions.setVisibility (View.GONE);
                                                rlMain.setVisibility (View.VISIBLE);
                                                rlConclusion.setVisibility (View.GONE);
                                                rlLabReport.setVisibility (View.GONE);
                                                rlSurveyComplete.setVisibility (View.GONE);
                                                rlDayComplete.setVisibility (View.GONE);
//                                                }
                                                break;
                                            case 2:
                                                rlInstructions.setVisibility (View.GONE);
                                                rlMain.setVisibility (View.GONE);
                                                rlConclusion.setVisibility (View.VISIBLE);
                                                rlLabReport.setVisibility (View.GONE);
                                                rlSurveyComplete.setVisibility (View.GONE);
                                                rlDayComplete.setVisibility (View.GONE);
                                                break;
                                            case 3:
                                                rlInstructions.setVisibility (View.GONE);
                                                rlMain.setVisibility (View.GONE);
                                                rlConclusion.setVisibility (View.GONE);
                                                rlLabReport.setVisibility (View.VISIBLE);
                                                rlSurveyComplete.setVisibility (View.GONE);
                                                rlDayComplete.setVisibility (View.GONE);
                                                break;
                                            case 4:
                                                rlInstructions.setVisibility (View.GONE);
                                                rlMain.setVisibility (View.GONE);
                                                rlConclusion.setVisibility (View.GONE);
                                                rlLabReport.setVisibility (View.GONE);
                                                rlSurveyComplete.setVisibility (View.VISIBLE);
                                                rlDayComplete.setVisibility (View.GONE);
                                                break;
                                        }
                                    }
                                    progressDialog.dismiss ();
                                } catch (Exception e) {
                                    e.printStackTrace ();
                                    progressDialog.dismiss ();
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_NAME, "");
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_MOBILE, "");
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.USER_LOGIN_KEY, "");
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_ID, 0);
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_NUMBER, "");
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_STATUS, 0);
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_DAY_ELAPSED, 0);
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.SURVEY_STARTED_AT, "");
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.PRODUCT_ID, 0);
                                    appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.PRODUCT_CODE, "");
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON1, 0);
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON2, 0);
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON3, 0);
                                    appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.BUTTON4, 0);
                                    Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                                    startActivity (intent);
                                    finish ();
                                    overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                            } else {
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                                progressDialog.dismiss ();
                            }
                            progressDialog.dismiss ();
                        }
                    },
                    new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            progressDialog.dismiss ();
                        }
                    }) {
                
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<> ();
                    params.put (AppConfigTags.SURVEY_NUMBER, appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.SURVEY_NUMBER));
                    params.put (AppConfigTags.APP_VERSION, String.valueOf (finalPInfo.versionCode));
                    params.put (AppConfigTags.DEVICE, "ANDROID");
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }
                
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    params.put (AppConfigTags.HEADER_USER_LOGIN_KEY, appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.USER_LOGIN_KEY));
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            strRequest.setRetryPolicy (new DefaultRetryPolicy (DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Utils.sendRequest (strRequest, 30);
        } else {
        }
    }
    
    private void startSurvey (final int survey_id) {
        if (NetworkConnection.isNetworkAvailable (MainActivity.this)) {
            Utils.showProgressDialog (this, progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL, AppConfigURL.START_SURVEY, true);
            StringRequest strRequest1 = new StringRequest (Request.Method.POST, AppConfigURL.START_SURVEY,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! error) {
                                        rlInstructions.setVisibility (View.GONE);
                                        rlMain.setVisibility (View.VISIBLE);
                                        appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.SURVEY_STATUS, jsonObj.getInt (AppConfigTags.SURVEY_STATUS));
                                    } else {
                                        Utils.showSnackBar (MainActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                    progressDialog.dismiss ();
                                } catch (Exception e) {
                                    progressDialog.dismiss ();
                                    Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss ();
                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                Utils.showLog (Log.ERROR, AppConfigTags.ERROR, new String (response.data), true);
                            }
                            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                            progressDialog.dismiss ();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    params.put (AppConfigTags.SURVEY_ID, String.valueOf (survey_id));
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }
                
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest (strRequest1, 60);
        } else {
            Utils.showSnackBar (this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }
    }
    
    class CustomListener implements View.OnClickListener {
        private final MaterialDialog dialog;
        Activity activity;
        DialogAction dialogAction;
        
        public CustomListener (Activity activity, MaterialDialog dialog, DialogAction dialogAction) {
            this.dialog = dialog;
            this.activity = activity;
            this.dialogAction = dialogAction;
        }
        
        @Override
        public void onClick (View v) {
            if (dialogAction == DialogAction.POSITIVE) {
                final String appPackageName = getPackageName ();
                try {
                    startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
            if (dialogAction == DialogAction.NEGATIVE) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                
            }
        }
    }
}