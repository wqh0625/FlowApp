package com.dingtao.flowapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dingtao.flowapp.database.FlowDatabase;

import java.util.List;

public class FlowActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdit;
    private Button mSearch;
    private FlowLayout mFlowLayout;
    private FlowDatabase mFlowDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        mEdit = findViewById(R.id.edit_keys);
        mSearch = findViewById(R.id.btn_search);
        mFlowLayout = findViewById(R.id.flow_history_layout);
        mSearch.setOnClickListener(this);

        mFlowDB = new FlowDatabase(this);//新建或者初始化数据库
        List<String> data = mFlowDB.query();//查询搜索历史
        for (int i = 0; i < data.size(); i++) {
            mFlowLayout.addTextView(data.get(i));//流式布局添加历史
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_search) {//点击搜索按钮
            String keys = mEdit.getText().toString();
            mFlowDB.insert(keys);//数据库插入搜索历史

            mFlowLayout.addTextView(keys);//流式布局添加搜索内容
        }else{
            mFlowLayout.removeAllViews();//移除所有view
            mFlowDB.delete();//数据库清空所有数据
        }
    }
}
