package mcs.gymapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;


import mcs.gymapp.dialog.LoginDialog;
import mcs.gymapp.utility.SingletonParametersBridge;
import mcs.gymapp.utility.setting.SettingsManager;


public class SettingsActivity extends ActionBarActivity {
    private SettingsManager settings;
    private SettingsActivity  _this;



    CompoundButton.OnCheckedChangeListener swListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.switch1:
                    settings.setNotifiche(isChecked);
                    break;
                case R.id.switch2:
                    settings.setTimer(isChecked);
                    break;
                case R.id.switch3:
                    settings.setAttivo(isChecked);
                    break;
            }
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        _this = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        SingletonParametersBridge singleton = SingletonParametersBridge.getInstance();
        settings = (SettingsManager) singleton.getParameter("settings");

        ToggleButton swNoti = (ToggleButton) findViewById(R.id.switch1);
        swNoti.setOnCheckedChangeListener(swListener);
        swNoti.setChecked(settings.isNotifiche());

        ToggleButton swTimer = (ToggleButton) findViewById(R.id.switch2);
        swTimer.setOnCheckedChangeListener(swListener);
        swTimer.setChecked(settings.isTimer());

        ToggleButton swAttivo = (ToggleButton) findViewById(R.id.switch3);
        swAttivo.setOnCheckedChangeListener(swListener);
        swAttivo.setChecked(settings.isAttivo());

        final FragmentManager fm = getSupportFragmentManager();
        TextView login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog alertdLogin = new LoginDialog();
                alertdLogin.show(fm,"");
            }
        });

        TextView disconnetti = (TextView) findViewById(R.id.disconnetti);
        disconnetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setSync(false);
                Intent intent =new Intent( _this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(intent);
            }
        });

    }

}