package zuoix.com.zoomed;

import android.content.Context;
import android.content.res.Resources;

import zuoix.com.zoomed.activities.SharedPref;
import zuoix.com.zoomed.models.CommandModel;

public class InitCommands {
    Context context;
    SharedPref sp;

    public InitCommands(Context context) {
        this.context = context;
        sp = new SharedPref(context);

        //first generation
        BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.where_is_my_car), "#smslink#password#", getResources().getDrawable(R.drawable.where_is_my_car_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.continious_locate), "#at#timeinterval(seconds)#sum#0#", getResources().getDrawable(R.drawable.continous_locate_24dp)));
        } else {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.cancel_continious_locate), "#noat#password#", getResources().getDrawable(R.drawable.cancel_continous_locate_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.speed_alert), "#speed#password#3digits)#", getResources().getDrawable(R.drawable.speed_alert_24dp)));
        } else {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.no_speed_alert), "#nospeed#password#", getResources().getDrawable(R.drawable.cancel_speed_alert_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.ignition_alert), "#ACC#ON#", getResources().getDrawable(R.drawable.ignition_alert_24dp)));
        } else {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.cancel_ignition_alert), "#ACC#OFF#", getResources().getDrawable(R.drawable.cancel_ignition_alert_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.immobilize_engine), "#stopoil#password#", getResources().getDrawable(R.drawable.immobilize_engine_24dp)));
        } else {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.activate_engine), "#supplyoil#password#", getResources().getDrawable(R.drawable.activate_engine_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.immobilize_engine_2), "#stopelec#password#", getResources().getDrawable(R.drawable.default_command)));
        } else {
            BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.activate_engine_2), "#supplyelec#password#", getResources().getDrawable(R.drawable.default_command)));
        }
        BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.voice_surveillance), "#monitor#password#", getResources().getDrawable(R.drawable.voice_surveillance_24dp)));
        BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.two_way_calling), "#call#password#", getResources().getDrawable(R.drawable.two_way_call_24dp)));
        BaseApplication.getInstance().firstGenerationCommand.add(new CommandModel(getString(R.string.sms_mode), "#tracker#password#", getResources().getDrawable(R.drawable.sms_mode_24dp)));
//second generation

        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.where_is_my_car), "SmslinkPASSWORD", getResources().getDrawable(R.drawable.where_is_my_car_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.ignition_alert), "AccPASSWORD", getResources().getDrawable(R.drawable.ignition_alert_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.cancel_ignition_alert), "NoaccPASSWORD", getResources().getDrawable(R.drawable.cancel_ignition_alert_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.immobilize_engine), "CutPASSWORD", getResources().getDrawable(R.drawable.immobilize_engine_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.activate_engine), "ResumePASSWORD", getResources().getDrawable(R.drawable.activate_engine_24dp)));
        }
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.cancel_sos_alert), "Help Me", getResources().getDrawable(R.drawable.cancel_sos_alert_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.shock_alert), "ShockPASSWORD", getResources().getDrawable(R.drawable.shork_alert_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.cancel_shock_alert), "NoshockPASSWORD", getResources().getDrawable(R.drawable.cancel_shork_alert_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.speed_alert), "SpeedPASSWORD XXX (X is any number from 000-200)", getResources().getDrawable(R.drawable.speed_alert_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.no_speed_alert), "NospeedPASSWORDXXXXXX", getResources().getDrawable(R.drawable.cancel_speed_alert_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.sensors_alerts), "ArmPASSWORD", getResources().getDrawable(R.drawable.sensors_alert_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.cancel_sensors_alerts), "NoarmPASSWORD", getResources().getDrawable(R.drawable.default_command)));
        }
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.add_user), "AdminPASSWORD XXXXXXXX (any phone number in 8\n" +
                "digits)", getResources().getDrawable(R.drawable.add_user_24dp)));
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.remove_user), "NoadminPASSWORD XXXXXXXX", getResources().getDrawable(R.drawable.remove_user_24dp)));
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.calling_mode), "MonitorPASSWORD", getResources().getDrawable(R.drawable.calling_mode_24dp)));
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.sms_mode), "TrackerPASSWORD", getResources().getDrawable(R.drawable.sms_mode_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.set_geo_fence), "MovePASSWORD", getResources().getDrawable(R.drawable.set_geo_fence_24dp)));
        } else {
            BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.cancel_geo_fence), "NomovePASSWORD", getResources().getDrawable(R.drawable.cancel_geo_fence_24dp)));
        }
        BaseApplication.getInstance().secondGenerationCommand.add(new CommandModel(getString(R.string.time_zone), "Time zonePASSWORD X (Any number in GMT Format\n" +
                "from -10 to +10)", getResources().getDrawable(R.drawable.time_zone_24dp)));
//third generation
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.where_is_my_car), "A(password),000", getResources().getDrawable(R.drawable.where_is_my_car_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.continious_locate), "A(password),003,xxx", getResources().getDrawable(R.drawable.continous_locate_24dp)));
        } else {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.cancel_continious_locate), "A(password),003,000", getResources().getDrawable(R.drawable.cancel_continous_locate_24dp)));
        }
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.gprs_interval), "A(password),506,XXXXX (XXXXXX is number in seconds)", getResources().getDrawable(R.drawable.location_position_24dp)));
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.check_gprs_interval), "A(password),507", getResources().getDrawable(R.drawable.location_position_24dp)));
        if (!sp.istrue()) {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.immobilize_engine), "A(password),007,1,1", getResources().getDrawable(R.drawable.immobilize_engine_24dp)));
        } else {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.activate_engine), "A(password),007,1,0", getResources().getDrawable(R.drawable.activate_engine_24dp)));
        }
        if (!sp.istrue()) {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.speed_alert), "A(password),005,XXX (XXX is any number from 001-099)", getResources().getDrawable(R.drawable.speed_alert_24dp)));
        } else {
            BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.no_speed_alert), "A(password),005,000", getResources().getDrawable(R.drawable.cancel_speed_alert_24dp)));
        }
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.add_user_sos), "A(password),002,1, phonenumber", getResources().getDrawable(R.drawable.add_user_24dp)));
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.add_user_monitoring), "A(password),002,2, phonenumber", getResources().getDrawable(R.drawable.add_user_24dp)));
        BaseApplication.getInstance().thirdGenerationCommand.add(new CommandModel(getString(R.string.time_zone), "A(password),008,+XX (X Any number in GMT Format\n" +
                "from -10 to +10)", getResources().getDrawable(R.drawable.time_zone_24dp)));


    }

    private String getString(int res_val) {
        return context.getString(res_val);
    }


    public Resources getResources() {
        return context.getResources();
    }
}