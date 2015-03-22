
package mcs.gymapp.utility.setting;

import android.content.Context;


public class SettingsManager extends PreferencesManager  {

    private boolean sync;
    private boolean notifiche;
    private boolean timer;
    //public boolean allarme;
    private boolean attivo;
    private boolean ricordami;
    private String mail;
    private String paswd;


    private final int SETTING_NOTIFICHE = 0;
    private final int SETTING_TIMER     = 1;
    private final int SETTING_ALLARME   = 2;
    private final int SETTING_ATTIVO    = 3;
    private final int SETTING_RICORDAMI = 4;
    private final int SETTING_MAIL      = 5;
    private final int SETTING_PSWD      = 6;
    private final int SETTING_SYNC      = 7;

    // static SettingsManager settingsManager;

    public SettingsManager(Context c) {
        super(c);
        notifiche = getBoolean(SETTING_NOTIFICHE);
        timer     = getBoolean(SETTING_TIMER);
        //allarme   = getBoolean(SETTING_ALLARME);
        attivo    = getBoolean(SETTING_ATTIVO);
    }

/*    public static SettingsManager getSettingsManager(Context c){
        if ( settingsManager == null )
            settingsManager = new SettingsManager(c);
        return settingsManager;
    }*/

    public boolean isNotifiche() {
        return notifiche;
    }

    public boolean isTimer() {
        return timer;
    }

    /*public boolean isAllarme() {
        return allarme;
    }*/

    public boolean isAttivo() {
        return attivo;
    }

    public boolean isRicordami() {
        return ricordami;
    }
    public boolean isSync() { return sync; }
    public String getMail() {
        return mail;
    }
    public String getPaswd() {
        return paswd;
    }


    public void setNotifiche(boolean notifiche) {
        this.notifiche = notifiche;
        setBoolean(SETTING_NOTIFICHE, notifiche);
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
        setBoolean(SETTING_TIMER, timer);
    }

    /*public void setAllarme(boolean allarme) {
        this.allarme = allarme;
        setBoolean(SETTING_ALLARME, timer);
    }*/
    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
        setBoolean(SETTING_ATTIVO, attivo);
    }

    public void setRicordami(boolean ricordami) {
        this.ricordami = ricordami;
        setBoolean(SETTING_RICORDAMI, ricordami);
    }

    public void setMail(String mail) {
        this.mail = mail;
        setString(SETTING_MAIL, mail);
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
        setString(SETTING_PSWD, paswd);
    }

    public void setSync(boolean sync) {
        this.sync = sync;
        setBoolean(SETTING_SYNC, sync);
    }


}
