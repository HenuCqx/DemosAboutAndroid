package com.example.mrc.csdndemo.PreferenceExample;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mrc.csdndemo.R;

public class PreferenceExampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_example);
    }

    public static class SettingsFragment extends PreferenceFragment {
        Context mContext;
        Preference prefAndroid ,prefIos ;
        CheckBoxPreference prefCheckBoxOne ,prefCheckBoxTwo ;
        EditTextPreference prefEditText;
        ListPreference prefList;
        MultiSelectListPreference prefMultiSelect;
        SwitchPreference prefSwitch;
        RingtonePreference prefRingtone;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settingsfragmentlayout);
            mContext = getActivity();
            init();
        }

        //点击事件
        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            if(preference==prefAndroid){
                preference.setSummary("点击了一下");
                Toast.makeText(mContext,"点击了第一个Preference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefIos){
                Toast.makeText(mContext,"点击了第二个Preference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefCheckBoxOne){
                if(preference.isSelectable()){
                    preference.setTitle("我要选中它！");
                    preference.setSummary("点击了选中！");
                }else {
                    preference.setTitle("我不想选中它！");
                    preference.setSummary("取消了选中！");
                }
                Toast.makeText(mContext,"点击了第一个CheckBoxPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefCheckBoxTwo){
                Toast.makeText(mContext,"点击了第二个CheckBoxPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefEditText){
                Toast.makeText(mContext,"点击了EditTextPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefList){
                Toast.makeText(mContext,"点击了ListPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefMultiSelect){
                Toast.makeText(mContext,"点击了MultiSelectListPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefRingtone){
                Toast.makeText(mContext,"点击了SwitchPreference",Toast.LENGTH_SHORT).show();
            }else if(preference==prefRingtone){
                Toast.makeText(mContext,"点击了RingtonePreference",Toast.LENGTH_SHORT).show();
            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        void init(){
            prefAndroid =(Preference)findPreference("prefAndroid");
            prefIos =(Preference) findPreference("prefIos");
            prefCheckBoxOne =(CheckBoxPreference)findPreference("prefCheckBoxOne");
            prefCheckBoxTwo =(CheckBoxPreference) findPreference("prefCheckBoxTwo");
            prefEditText =(EditTextPreference)findPreference("prefEditText");
            prefList =(ListPreference)findPreference("prefList");
            prefMultiSelect =(MultiSelectListPreference)findPreference("prefMultiSelect");
            prefSwitch =(SwitchPreference)findPreference("prefSwitch");
            prefRingtone =(RingtonePreference)findPreference("prefRingtone");
        }
    }
}
