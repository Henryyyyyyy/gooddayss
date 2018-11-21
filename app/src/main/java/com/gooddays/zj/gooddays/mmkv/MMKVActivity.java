package com.gooddays.zj.gooddays.mmkv;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gooddays.zj.gooddays.R;


public class MMKVActivity extends AppCompatActivity {
    private String flag_sp = "sp";
    private String flag_mmkv = "mmkv";
    TextView tv_getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        tv_getdata = findViewById(R.id.tv_getdata);
//        tv_getdata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MMKVActivity.this,MMKV.defaultMMKV().getString(flag_mmkv+20,"0"),Toast.LENGTH_LONG).show();
//            }
//        });
//        testPreferences();
//        testMMKV();
    }

//    private void testMMKV() {
//        MMKV mmkv = MMKV.defaultMMKV();
//        long previous = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            mmkv.putString(flag_mmkv+i,"test for mmkv"+i);
//
//        }
//        long after = System.currentTimeMillis();
//        Log.d("henry" ,"mmkv消耗时间为:"+(after-previous));
//        Log.d("henry" ,"mmkv拿到第40个数据为:"
//                +mmkv.getString(flag_mmkv+40,"0"));
//
//
//    }

    private void testPreferences() {
        long previous = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString(flag_sp + i, "test for preferences" + i).commit();
        }
        long after = System.currentTimeMillis();
        Log.d("henry", "sharedpreferenced消耗时间为:" + (after - previous));
        Log.d("henry", "sharedpreferenced拿到第40个数据为:" + PreferenceManager.getDefaultSharedPreferences(this).getString(flag_sp + 40, "0"));


    }
}
