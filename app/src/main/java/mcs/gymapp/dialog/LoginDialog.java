package mcs.gymapp.dialog;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mcs.gymapp.Main;
import mcs.gymapp.R;
import mcs.gymapp.utility.SingletonParametersBridge;
import mcs.gymapp.utility.connection.Connect;
import mcs.gymapp.utility.setting.SettingsManager;


/**
 * A login screen that offers login via email/password.
 */
public class LoginDialog extends DialogFragment {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private SettingsManager settings;
    private Context context;
    private Connect connection;
    private SingletonParametersBridge singleton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.dia_login, container);
        getDialog().setTitle(getString(R.string.sincronizza));


        singleton = SingletonParametersBridge.getInstance();
        settings = (SettingsManager) singleton.getParameter("settings");


        // Set up the login form.
        mEmailView = (EditText) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        final CheckBox cbRicordamiView = (CheckBox) view.findViewById(R.id.cbRicordami);

        if(settings.isRicordami()) {
            //mail
            mEmailView.setText(settings.getMail());
            //passwd
            mPasswordView.setText(settings.getPaswd());
            //ricordami
            cbRicordamiView.setChecked(settings.isRicordami());
        }
        cbRicordamiView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settings.setRicordami(isChecked);
                settings.setMail(mEmailView.getText().toString());
                settings.setPaswd(mPasswordView.getText().toString());
            }
        });

        //login
        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbRicordamiView.isChecked()) {
                    settings.setMail(mEmailView.getText().toString());
                    settings.setPaswd(mPasswordView.getText().toString());
                    settings.setRicordami(true);
                }
                attemptLogin();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
      /*  if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            connection = new Connect(email, password, this);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    public void loginStatus(Boolean stato) {
        showProgress(false);
        if (stato){
            singleton.addParameter("connessione", connection);
            dismiss();
        }else
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
    }

}



