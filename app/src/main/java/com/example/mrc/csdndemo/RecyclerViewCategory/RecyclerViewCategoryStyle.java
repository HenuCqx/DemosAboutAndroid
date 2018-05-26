package com.example.mrc.csdndemo.RecyclerViewCategory;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.mrc.csdndemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewCategoryStyle extends AppCompatActivity {
    RecyclerView mRecyclerView ;
    LinearLayoutManager mLayoutManager;
    RecyclerViewAdapter mRecyclerViewAdapter;

    int IS_TITLE_OR_NOT =1;
    int MESSAGE = 2;

    List<Map<Integer, String>> mData =new ArrayList<>();
    Map<Integer, String> map = new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_category_style);

        findView();
        init();
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mData, 2);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    void findView(){
        mRecyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        //2表示列数为2，LinearLayoutManager.VERTICAL表示
        mLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(12));//item之间的间距
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    void init(){
        //对item的数据进行初始化
        for(int i=0;i<15 ;i++){
            map = new HashMap<Integer, String>();
            map.put(IS_TITLE_OR_NOT , "false");
            map.put(MESSAGE , "item "+(i+1));
            mData.add(map);
        }

        //对分类标题进行初始化
        map = new HashMap<Integer, String>();
        map.put(IS_TITLE_OR_NOT , "true");
        map.put(MESSAGE , "1-3的分类标题");
        mData.add(0,map);

       map = new HashMap<Integer, String>();
        map.put(IS_TITLE_OR_NOT , "true");
        map.put(MESSAGE , "4-7的分类标题");
        mData.add( 4,map);

        map = new HashMap<Integer, String>();
        map.put(IS_TITLE_OR_NOT , "true");
        map.put(MESSAGE , "8-15的分类标题");
        mData.add(9,map);
        for (int i=0;i<mData.size();i++){
            Log.d("Title",mData.get(i).get(IS_TITLE_OR_NOT));
            Log.d("Title_message",mData.get(i).get(MESSAGE));
        }
    }


    //设置recyclerView中item的上下左右间距
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //分别设置item的间距
            if (parent.getChildViewHolder(view).getItemViewType() == 0) {
                outRect.bottom = 0;
                outRect.top = space / 2;
            } else {
                outRect.bottom = space;
                outRect.top = space;
            }
            outRect.right = space;
            outRect.left = space;

        }
    }

}
