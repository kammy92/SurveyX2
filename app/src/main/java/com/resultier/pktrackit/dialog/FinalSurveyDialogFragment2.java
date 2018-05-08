package com.resultier.pktrackit.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.resultier.pktrackit.R;
import com.resultier.pktrackit.activity.MainActivity;
import com.resultier.pktrackit.utils.AppConfigTags;
import com.resultier.pktrackit.utils.AppConfigURL;
import com.resultier.pktrackit.utils.AppDetailsPref;
import com.resultier.pktrackit.utils.Constants;
import com.resultier.pktrackit.utils.NetworkConnection;
import com.resultier.pktrackit.utils.Utils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;


public class FinalSurveyDialogFragment2 extends DialogFragment {
    ImageView ivCancel;
    LinearLayout llAnswer14;
    LinearLayout llAnswer15;
    
    LinearLayout llAnswer16;
    LinearLayout llAnswer17;
    
    LinearLayout llAnswer9;
    LinearLayout llAnswer9a;
    LinearLayout llAnswer9b;
    LinearLayout llAnswer9c;
    LinearLayout llAnswer9d;
    LinearLayout llAnswer9e;
    LinearLayout llAnswer9f;
    String answer9 = "";
    String answer9a = "";
    String answer9b = "";
    String answer9c = "";
    String answer9d = "";
    String answer9e = "";
    String answer9f = "";
    
    String answer14 = "";
    String answer15 = "";
    
    String answer16 = "";
    String answer17 = "";
    
    
    AppDetailsPref appDetailsPref;
    ProgressDialog progressDialog;
    private RadioButton rbNotAtAll;
    private RadioButton rbVeryLittle;
    private RadioButton rbALittle;
    private RadioButton rbModerately;
    private RadioButton rbALot;
    private RadioButton rbQuiteALot;
    private RadioButton rbExtremely;
    
    private RadioButton rbNotAtAll1;
    private RadioButton rbVeryLittle1;
    private RadioButton rbALittle1;
    private RadioButton rbModerately1;
    private RadioButton rbALot1;
    private RadioButton rbQuiteALot1;
    private RadioButton rbExtremely1;
    
    private RadioButton rbNotAtAll2;
    private RadioButton rbVeryLittle2;
    private RadioButton rbALittle2;
    private RadioButton rbModerately2;
    private RadioButton rbALot2;
    private RadioButton rbQuiteALot2;
    private RadioButton rbExtremely2;
    
    private RadioButton rbNotAtAll3;
    private RadioButton rbVeryLittle3;
    private RadioButton rbALittle3;
    private RadioButton rbModerately3;
    private RadioButton rbALot3;
    private RadioButton rbQuiteALot3;
    private RadioButton rbExtremely3;
    
    private RadioButton rbNotAtAll4;
    private RadioButton rbVeryLittle4;
    private RadioButton rbALittle4;
    private RadioButton rbModerately4;
    private RadioButton rbALot4;
    private RadioButton rbQuiteALot4;
    private RadioButton rbExtremely4;
    
    private RadioButton rbNotAtAll5;
    private RadioButton rbVeryLittle5;
    private RadioButton rbALittle5;
    private RadioButton rbModerately5;
    private RadioButton rbALot5;
    private RadioButton rbQuiteALot5;
    private RadioButton rbExtremely5;
    
    private RadioButton rbNotAtAll6;
    private RadioButton rbVeryLittle6;
    private RadioButton rbALittle6;
    private RadioButton rbModerately6;
    private RadioButton rbALot6;
    private RadioButton rbQuiteALot6;
    private RadioButton rbExtremely6;
    
    
    private RadioButton rb16Yes;
    private RadioButton rb16No;
    private RadioButton rb17Yes;
    private RadioButton rb17No;
    
    
    private EditText etAnswer14;
    private EditText etAnswer15;
    
    
    private EditText etAnswer9a;
    private EditText etAnswer9b;
    private EditText etAnswer9c;
    private EditText etAnswer9d;
    private EditText etAnswer9e;
    private EditText etAnswer9f;
    private TextView tvSubmit;
    
    public static FinalSurveyDialogFragment2 newInstance () {
        FinalSurveyDialogFragment2 f = new FinalSurveyDialogFragment2 ();
        Bundle args = new Bundle ();
        f.setArguments (args);
        return f;
    }
    
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setStyle (DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }
    
    @Override
    public void onActivityCreated (Bundle arg0) {
        super.onActivityCreated (arg0);
        Window window = getDialog ().getWindow ();
        window.getAttributes ().windowAnimations = R.style.DialogAnimation;
    }
    
    @Override
    public void onStart () {
        super.onStart ();
        Dialog d = getDialog ();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow ().setLayout (width, height);
        }
    }
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate (R.layout.fd_final_survey2, container, false);
        initView (root);
        initBundle ();
        initData ();
        initListener ();
        return root;
    }
    
    private void initView (View root) {
        ivCancel = (ImageView) root.findViewById (R.id.ivCancel);
        rbNotAtAll = (RadioButton) root.findViewById (R.id.rbNotAtAll);
        rbVeryLittle = (RadioButton) root.findViewById (R.id.rbVeryLittle);
        rbALittle = (RadioButton) root.findViewById (R.id.rbALittle);
        rbModerately = (RadioButton) root.findViewById (R.id.rbModerately);
        rbALot = (RadioButton) root.findViewById (R.id.rbALot);
        rbQuiteALot = (RadioButton) root.findViewById (R.id.rbQuiteALot);
        rbExtremely = (RadioButton) root.findViewById (R.id.rbExtremely);
        
        rbNotAtAll1 = (RadioButton) root.findViewById (R.id.rbNotAtAll1);
        rbVeryLittle1 = (RadioButton) root.findViewById (R.id.rbVeryLittle1);
        rbALittle1 = (RadioButton) root.findViewById (R.id.rbALittle1);
        rbModerately1 = (RadioButton) root.findViewById (R.id.rbModerately1);
        rbALot1 = (RadioButton) root.findViewById (R.id.rbALot1);
        rbQuiteALot1 = (RadioButton) root.findViewById (R.id.rbQuiteALot1);
        rbExtremely1 = (RadioButton) root.findViewById (R.id.rbExtremely1);
        
        rbNotAtAll2 = (RadioButton) root.findViewById (R.id.rbNotAtAll2);
        rbVeryLittle2 = (RadioButton) root.findViewById (R.id.rbVeryLittle2);
        rbALittle2 = (RadioButton) root.findViewById (R.id.rbALittle2);
        rbModerately2 = (RadioButton) root.findViewById (R.id.rbModerately2);
        rbALot2 = (RadioButton) root.findViewById (R.id.rbALot2);
        rbQuiteALot2 = (RadioButton) root.findViewById (R.id.rbQuiteALot2);
        rbExtremely2 = (RadioButton) root.findViewById (R.id.rbExtremely2);
        
        rbNotAtAll3 = (RadioButton) root.findViewById (R.id.rbNotAtAll3);
        rbVeryLittle3 = (RadioButton) root.findViewById (R.id.rbVeryLittle3);
        rbALittle3 = (RadioButton) root.findViewById (R.id.rbALittle3);
        rbModerately3 = (RadioButton) root.findViewById (R.id.rbModerately3);
        rbALot3 = (RadioButton) root.findViewById (R.id.rbALot3);
        rbQuiteALot3 = (RadioButton) root.findViewById (R.id.rbQuiteALot3);
        rbExtremely3 = (RadioButton) root.findViewById (R.id.rbExtremely3);
        
        rbNotAtAll4 = (RadioButton) root.findViewById (R.id.rbNotAtAll4);
        rbVeryLittle4 = (RadioButton) root.findViewById (R.id.rbVeryLittle4);
        rbALittle4 = (RadioButton) root.findViewById (R.id.rbALittle4);
        rbModerately4 = (RadioButton) root.findViewById (R.id.rbModerately4);
        rbALot4 = (RadioButton) root.findViewById (R.id.rbALot4);
        rbQuiteALot4 = (RadioButton) root.findViewById (R.id.rbQuiteALot4);
        rbExtremely4 = (RadioButton) root.findViewById (R.id.rbExtremely4);
        
        rbNotAtAll5 = (RadioButton) root.findViewById (R.id.rbNotAtAll5);
        rbVeryLittle5 = (RadioButton) root.findViewById (R.id.rbVeryLittle5);
        rbALittle5 = (RadioButton) root.findViewById (R.id.rbALittle5);
        rbModerately5 = (RadioButton) root.findViewById (R.id.rbModerately5);
        rbALot5 = (RadioButton) root.findViewById (R.id.rbALot5);
        rbQuiteALot5 = (RadioButton) root.findViewById (R.id.rbQuiteALot5);
        rbExtremely5 = (RadioButton) root.findViewById (R.id.rbExtremely5);
        
        rbNotAtAll6 = (RadioButton) root.findViewById (R.id.rbNotAtAll6);
        rbVeryLittle6 = (RadioButton) root.findViewById (R.id.rbVeryLittle6);
        rbALittle6 = (RadioButton) root.findViewById (R.id.rbALittle6);
        rbModerately6 = (RadioButton) root.findViewById (R.id.rbModerately6);
        rbALot6 = (RadioButton) root.findViewById (R.id.rbALot6);
        rbQuiteALot6 = (RadioButton) root.findViewById (R.id.rbQuiteALot6);
        rbExtremely6 = (RadioButton) root.findViewById (R.id.rbExtremely6);
        
        
        etAnswer9a = (EditText) root.findViewById (R.id.etAnswer9a);
        etAnswer9b = (EditText) root.findViewById (R.id.etAnswer9b);
        etAnswer9c = (EditText) root.findViewById (R.id.etAnswer9c);
        etAnswer9d = (EditText) root.findViewById (R.id.etAnswer9d);
        etAnswer9e = (EditText) root.findViewById (R.id.etAnswer9e);
        etAnswer9f = (EditText) root.findViewById (R.id.etAnswer9f);
        
        llAnswer9 = (LinearLayout) root.findViewById (R.id.llAnswer9);
        llAnswer9a = (LinearLayout) root.findViewById (R.id.llAnswer9a);
        llAnswer9b = (LinearLayout) root.findViewById (R.id.llAnswer9b);
        llAnswer9c = (LinearLayout) root.findViewById (R.id.llAnswer9c);
        llAnswer9d = (LinearLayout) root.findViewById (R.id.llAnswer9d);
        llAnswer9e = (LinearLayout) root.findViewById (R.id.llAnswer9e);
        llAnswer9f = (LinearLayout) root.findViewById (R.id.llAnswer9f);
        
        llAnswer14 = (LinearLayout) root.findViewById (R.id.llAnswer14);
        etAnswer14 = (EditText) root.findViewById (R.id.etAnswer14);
        
        llAnswer15 = (LinearLayout) root.findViewById (R.id.llAnswer15);
        etAnswer15 = (EditText) root.findViewById (R.id.etAnswer15);
        
        llAnswer16 = (LinearLayout) root.findViewById (R.id.llAnswer16);
        rb16Yes = (RadioButton) root.findViewById (R.id.rb16Yes);
        rb16No = (RadioButton) root.findViewById (R.id.rb16No);
        
        llAnswer17 = (LinearLayout) root.findViewById (R.id.llAnswer17);
        rb17Yes = (RadioButton) root.findViewById (R.id.rb17Yes);
        rb17No = (RadioButton) root.findViewById (R.id.rb17No);
        
        
        tvSubmit = (TextView) root.findViewById (R.id.tvSubmit);
    }
    
    private void initBundle () {
        Bundle bundle = this.getArguments ();
    }
    
    private void initData () {
        appDetailsPref = AppDetailsPref.getInstance ();
        progressDialog = new ProgressDialog (getActivity ());
        Utils.setTypefaceToAllViews (getActivity (), ivCancel);
    
        if (appDetailsPref.getIntPref (getActivity (), AppDetailsPref.WEEK_NUMBER) == 5) {
            llAnswer16.setVisibility (View.VISIBLE);
        } else {
            llAnswer16.setVisibility (View.GONE);
            llAnswer17.setVisibility (View.GONE);
        }
    }
    
    private void initListener () {
        ivCancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                getDialog ().dismiss ();
            }
        });
        
        rbNotAtAll.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbNotAtAll.getText ().toString ().trim ();
                rbVeryLittle.setChecked (false);
                rbALittle.setChecked (false);
                rbModerately.setChecked (false);
                rbALot.setChecked (false);
                rbQuiteALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbVeryLittle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbVeryLittle.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbALittle.setChecked (false);
                rbModerately.setChecked (false);
                rbALot.setChecked (false);
                rbQuiteALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbALittle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbALittle.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbVeryLittle.setChecked (false);
                rbModerately.setChecked (false);
                rbALot.setChecked (false);
                rbQuiteALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbModerately.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbModerately.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbVeryLittle.setChecked (false);
                rbALittle.setChecked (false);
                rbALot.setChecked (false);
                rbQuiteALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbALot.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbALot.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbVeryLittle.setChecked (false);
                rbALittle.setChecked (false);
                rbModerately.setChecked (false);
                rbQuiteALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbQuiteALot.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbQuiteALot.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbVeryLittle.setChecked (false);
                rbALittle.setChecked (false);
                rbModerately.setChecked (false);
                rbALot.setChecked (false);
                rbExtremely.setChecked (false);
            }
        });
        rbExtremely.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9 = rbExtremely.getText ().toString ().trim ();
                rbNotAtAll.setChecked (false);
                rbVeryLittle.setChecked (false);
                rbALittle.setChecked (false);
                rbModerately.setChecked (false);
                rbALot.setChecked (false);
                rbQuiteALot.setChecked (false);
                
            }
        });
        
        
        rbNotAtAll1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbNotAtAll1.getText ().toString ().trim ();
                rbVeryLittle1.setChecked (false);
                rbALittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbALot1.setChecked (false);
                rbQuiteALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbVeryLittle1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbVeryLittle1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbALittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbALot1.setChecked (false);
                rbQuiteALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbALittle1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbALittle1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbVeryLittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbALot1.setChecked (false);
                rbQuiteALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbModerately1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbModerately1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbVeryLittle1.setChecked (false);
                rbALittle1.setChecked (false);
                rbALot1.setChecked (false);
                rbQuiteALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbALot1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbALot1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbVeryLittle1.setChecked (false);
                rbALittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbQuiteALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbQuiteALot1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbQuiteALot1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbVeryLittle1.setChecked (false);
                rbALittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbALot1.setChecked (false);
                rbExtremely1.setChecked (false);
            }
        });
        rbExtremely1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9a = rbExtremely1.getText ().toString ().trim ();
                rbNotAtAll1.setChecked (false);
                rbVeryLittle1.setChecked (false);
                rbALittle1.setChecked (false);
                rbModerately1.setChecked (false);
                rbALot1.setChecked (false);
                rbQuiteALot1.setChecked (false);
            }
        });
        
        
        rbNotAtAll2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbNotAtAll2.getText ().toString ().trim ();
                rbVeryLittle2.setChecked (false);
                rbALittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbALot2.setChecked (false);
                rbQuiteALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbVeryLittle2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbVeryLittle2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbALittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbALot2.setChecked (false);
                rbQuiteALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbALittle2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbALittle2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbVeryLittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbALot2.setChecked (false);
                rbQuiteALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbModerately2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbModerately2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbVeryLittle2.setChecked (false);
                rbALittle2.setChecked (false);
                rbALot2.setChecked (false);
                rbQuiteALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbALot2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbALot2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbVeryLittle2.setChecked (false);
                rbALittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbQuiteALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbQuiteALot2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbQuiteALot2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbVeryLittle2.setChecked (false);
                rbALittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbALot2.setChecked (false);
                rbExtremely2.setChecked (false);
            }
        });
        rbExtremely2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9b = rbExtremely2.getText ().toString ().trim ();
                rbNotAtAll2.setChecked (false);
                rbVeryLittle2.setChecked (false);
                rbALittle2.setChecked (false);
                rbModerately2.setChecked (false);
                rbALot2.setChecked (false);
                rbQuiteALot2.setChecked (false);
            }
        });
        
        
        rbNotAtAll3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbNotAtAll3.getText ().toString ().trim ();
                rbVeryLittle3.setChecked (false);
                rbALittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbALot3.setChecked (false);
                rbQuiteALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbVeryLittle3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbVeryLittle3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbALittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbALot3.setChecked (false);
                rbQuiteALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbALittle3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbALittle3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbVeryLittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbALot3.setChecked (false);
                rbQuiteALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbModerately3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbModerately3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbVeryLittle3.setChecked (false);
                rbALittle3.setChecked (false);
                rbALot3.setChecked (false);
                rbQuiteALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbALot3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbALot3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbVeryLittle3.setChecked (false);
                rbALittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbQuiteALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbQuiteALot3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbQuiteALot3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbVeryLittle3.setChecked (false);
                rbALittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbALot3.setChecked (false);
                rbExtremely3.setChecked (false);
            }
        });
        rbExtremely3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9c = rbExtremely3.getText ().toString ().trim ();
                rbNotAtAll3.setChecked (false);
                rbVeryLittle3.setChecked (false);
                rbALittle3.setChecked (false);
                rbModerately3.setChecked (false);
                rbALot3.setChecked (false);
                rbQuiteALot3.setChecked (false);
            }
        });
        
        
        rbNotAtAll4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbNotAtAll4.getText ().toString ().trim ();
                rbVeryLittle4.setChecked (false);
                rbALittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbALot4.setChecked (false);
                rbQuiteALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbVeryLittle4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbVeryLittle4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbALittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbALot4.setChecked (false);
                rbQuiteALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbALittle4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbALittle4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbVeryLittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbALot4.setChecked (false);
                rbQuiteALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbModerately4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbModerately4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbVeryLittle4.setChecked (false);
                rbALittle4.setChecked (false);
                rbALot4.setChecked (false);
                rbQuiteALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbALot4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbALot4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbVeryLittle4.setChecked (false);
                rbALittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbQuiteALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbQuiteALot4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbQuiteALot4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbVeryLittle4.setChecked (false);
                rbALittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbALot4.setChecked (false);
                rbExtremely4.setChecked (false);
            }
        });
        rbExtremely4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9d = rbExtremely4.getText ().toString ().trim ();
                rbNotAtAll4.setChecked (false);
                rbVeryLittle4.setChecked (false);
                rbALittle4.setChecked (false);
                rbModerately4.setChecked (false);
                rbALot4.setChecked (false);
                rbQuiteALot4.setChecked (false);
            }
        });
        
        
        rbNotAtAll5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbNotAtAll5.getText ().toString ().trim ();
                rbVeryLittle5.setChecked (false);
                rbALittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbALot5.setChecked (false);
                rbQuiteALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbVeryLittle5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbVeryLittle5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbALittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbALot5.setChecked (false);
                rbQuiteALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbALittle5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbALittle5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbVeryLittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbALot5.setChecked (false);
                rbQuiteALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbModerately5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbModerately5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbVeryLittle5.setChecked (false);
                rbALittle5.setChecked (false);
                rbALot5.setChecked (false);
                rbQuiteALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbALot5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbALot5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbVeryLittle5.setChecked (false);
                rbALittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbQuiteALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbQuiteALot5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbQuiteALot5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbVeryLittle5.setChecked (false);
                rbALittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbALot5.setChecked (false);
                rbExtremely5.setChecked (false);
            }
        });
        rbExtremely5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9e = rbExtremely5.getText ().toString ().trim ();
                rbNotAtAll5.setChecked (false);
                rbVeryLittle5.setChecked (false);
                rbALittle5.setChecked (false);
                rbModerately5.setChecked (false);
                rbALot5.setChecked (false);
                rbQuiteALot5.setChecked (false);
            }
        });
        
        rbNotAtAll6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbNotAtAll6.getText ().toString ().trim ();
                rbVeryLittle6.setChecked (false);
                rbALittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbALot6.setChecked (false);
                rbQuiteALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbVeryLittle6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbVeryLittle6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbALittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbALot6.setChecked (false);
                rbQuiteALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbALittle6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbALittle6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbVeryLittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbALot6.setChecked (false);
                rbQuiteALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbModerately6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbModerately6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbVeryLittle6.setChecked (false);
                rbALittle6.setChecked (false);
                rbALot6.setChecked (false);
                rbQuiteALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbALot6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbALot6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbVeryLittle6.setChecked (false);
                rbALittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbQuiteALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbQuiteALot6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbQuiteALot6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbVeryLittle6.setChecked (false);
                rbALittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbALot6.setChecked (false);
                rbExtremely6.setChecked (false);
            }
        });
        rbExtremely6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer9f = rbExtremely6.getText ().toString ().trim ();
                rbNotAtAll6.setChecked (false);
                rbVeryLittle6.setChecked (false);
                rbALittle6.setChecked (false);
                rbModerately6.setChecked (false);
                rbALot6.setChecked (false);
                rbQuiteALot6.setChecked (false);
            }
        });
        
        rb16Yes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer16 = rb16Yes.getText ().toString ().trim ();
                rb16Yes.setChecked (true);
                rb16No.setChecked (false);
                llAnswer17.setVisibility (View.VISIBLE);
            }
        });
        rb16No.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer16 = rb16No.getText ().toString ().trim ();
                rb16Yes.setChecked (false);
                rb16No.setChecked (true);
                llAnswer17.setVisibility (View.GONE);
            }
        });
        
        rb17Yes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer17 = rb17Yes.getText ().toString ().trim ();
                rb17Yes.setChecked (true);
                rb17No.setChecked (false);
            }
        });
        rb17No.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer17 = rb17No.getText ().toString ().trim ();
                rb17Yes.setChecked (false);
                rb17No.setChecked (true);
            }
        });
        
        
        tvSubmit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                answer14 = etAnswer14.getText ().toString ().trim ();
                answer15 = etAnswer15.getText ().toString ().trim ();
                
                if (answer14.length () == 0) {
                    llAnswer14.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer14.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer15.length () == 0) {
                    llAnswer15.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer15.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
    
                if (appDetailsPref.getIntPref (getActivity (), AppDetailsPref.WEEK_NUMBER) == 5) {
                    if (answer16.length () == 0) {
                        llAnswer16.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                    } else {
                        llAnswer16.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                    }
                    if (answer17.length () == 0) {
                        llAnswer17.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                    } else {
                        llAnswer17.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                    }
                }
                
                
                if (answer9.length () == 0) {
                    llAnswer9.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9a.length () == 0) {
                    llAnswer9a.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9a.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9b.length () == 0) {
                    llAnswer9b.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9b.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9c.length () == 0) {
                    llAnswer9c.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9c.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9d.length () == 0) {
                    llAnswer9d.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9d.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9e.length () == 0) {
                    llAnswer9e.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9e.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
                
                if (answer9f.length () == 0) {
                    llAnswer9f.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                } else {
                    llAnswer9f.setBackground (getResources ().getDrawable (R.drawable.bg_question));
                }
    
    
                int vl2 = 0; // validation for least time in answer 5
    
    
                if (answer14.length () > 0 && answer15.length () > 0) {
                    if (Integer.parseInt (answer14) > Integer.parseInt (answer15)) {
                        vl2 = 2;
                        llAnswer14.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                        llAnswer15.setBackground (getResources ().getDrawable (R.drawable.bg_question_red));
                        Utils.showToast (getActivity (), "Least time cannot be greater than most time", false);
                    } else {
                        vl2 = 1;
                    }
                }
    
    
                if (appDetailsPref.getIntPref (getActivity (), AppDetailsPref.WEEK_NUMBER) == 5) {
                    if (answer14.length () > 0 &&
                            answer15.length () > 0 &&
                            answer9.length () > 0 &&
                            answer9a.length () > 0 &&
                            answer9b.length () > 0 &&
                            answer9c.length () > 0 &&
                            answer9d.length () > 0 &&
                            answer9e.length () > 0 &&
                            answer9f.length () > 0 &&
                            answer16.length () > 0 && vl2 != 2) {
                        if (answer16.equalsIgnoreCase (rb16Yes.getText ().toString ().trim ()) && answer17.length () > 0) {
                            submitConclusionSurvey (answer14, answer15, answer9, answer9a, answer9b, answer9c, answer9d, answer9e, answer9f, answer16, answer17);
                        } else if (answer16.equalsIgnoreCase (rb16No.getText ().toString ().trim ())) {
                            submitConclusionSurvey (answer14, answer15, answer9, answer9a, answer9b, answer9c, answer9d, answer9e, answer9f, answer16, answer17);
                        }
                    }
                } else {
                    if (answer14.length () > 0 &&
                            answer15.length () > 0 &&
                            answer9.length () > 0 &&
                            answer9a.length () > 0 &&
                            answer9b.length () > 0 &&
                            answer9c.length () > 0 &&
                            answer9d.length () > 0 &&
                            answer9e.length () > 0 &&
                            answer9f.length () > 0 && vl2 != 2) {
                        submitConclusionSurvey (answer14, answer15, answer9, answer9a, answer9b, answer9c, answer9d, answer9e, answer9f, answer16, answer17);
                    }
                }
            }
        });
    }
    
    private void submitConclusionSurvey (final String answer14, final String answer15, final String answer9, final String answer9a, final String answer9b, final String answer9c, final String answer9d, final String answer9e, final String answer9f, final String answer16, final String answer17) {
        if (NetworkConnection.isNetworkAvailable (getActivity ())) {
            Utils.showProgressDialog (getActivity (), progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), false);
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.CONCLUSION_SURVEY_2, true);
            StringRequest strRequest = new StringRequest (Request.Method.POST, AppConfigURL.CONCLUSION_SURVEY_2,
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
                                        progressDialog.dismiss ();
                                        getDialog ().dismiss ();
                                        
                                        appDetailsPref.putIntPref (getActivity (), AppDetailsPref.SURVEY_STATUS, jsonObj.getInt (AppConfigTags.SURVEY_STATUS));
                                        
                                        Intent newIntent = new Intent (getActivity (), MainActivity.class);
                                        newIntent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        newIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity (newIntent);
                                    }
                                } catch (Exception e) {
                                    progressDialog.dismiss ();
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                        }
                    },
                    new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                        }
                    }) {
                
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<> ();
                    Calendar c = Calendar.getInstance ();
                    SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd", Locale.US);
                    
                    params.put (AppConfigTags.SURVEY_ID, String.valueOf (appDetailsPref.getIntPref (getActivity (), AppDetailsPref.SURVEY_ID)));
                    params.put (AppConfigTags.DATE, df.format (c.getTime ()));
                    params.put (AppConfigTags.ANSWER_14, answer14);
                    params.put (AppConfigTags.ANSWER_15, answer15);
                    params.put (AppConfigTags.ANSWER_9, answer9);
                    params.put (AppConfigTags.ANSWER_9A, answer9a);
                    params.put (AppConfigTags.ANSWER_9B, answer9b);
                    params.put (AppConfigTags.ANSWER_9C, answer9c);
                    params.put (AppConfigTags.ANSWER_9D, answer9d);
                    params.put (AppConfigTags.ANSWER_9E, answer9e);
                    params.put (AppConfigTags.ANSWER_9F, answer9f);
                    params.put (AppConfigTags.ANSWER_16, answer16);
                    params.put (AppConfigTags.ANSWER_17, answer17);
                    
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }
                
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    params.put (AppConfigTags.HEADER_USER_LOGIN_KEY, appDetailsPref.getStringPref (getActivity (), AppDetailsPref.USER_LOGIN_KEY));
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            strRequest.setRetryPolicy (new DefaultRetryPolicy (DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Utils.sendRequest (strRequest, 30);
        } else {
        }
    }
}