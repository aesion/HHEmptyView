package com.mph.hhemptyview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mph.hhemptyview.widget.HHEmptyView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements HHEmptyView.OnBtnClickListener, View.OnClickListener {

    private HHEmptyView emptyView;
    private Button textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyView = (HHEmptyView) findViewById(R.id.hh_empty_view);
        textView = (Button) findViewById(R.id.name);

        //设置需要绑定的view
        emptyView.bindView(textView);
        //指定自定义的loading View
//        View view = getLayoutInflater().inflate(R.layout.loading_view, null);
//        emptyView.setCustomLoadingView(view);
        emptyView.setLoadingModel(HHEmptyView.MODEL_ALERT);

        emptyView.setOnBtnClickListener(this);

        textView.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onBtnClick() {
        loadData();
    }

    /**
     * 模拟加载网络数据
     */
    private void loadData() {
        //模拟网络加载开始
        emptyView.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int i = r.nextInt(5);
                if(i<=3){
                    //网络加载失败
                    emptyView.empty("网络连接失败");
                }else{
                    //网络加载成功
                    emptyView.success();
                }
            }
        },2000);
    }

    @Override
    public void onClick(View v) {

    }
}
