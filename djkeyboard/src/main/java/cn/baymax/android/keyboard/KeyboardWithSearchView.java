package cn.baymax.android.keyboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by xud on 2017/3/9.
 */

public class KeyboardWithSearchView extends LinearLayout {

    private static final int MAX_VISIBLE_SIZE = 3;

    private Context mContext;

    private RecyclerView mRecyclerView;

    private BaseKeyboardView mBaseKeyboardView;

    private LinearLayout mKeyboadViewContainer;

    //private OnSizeChangedListener mOnSizeChangedListener;

    private EditText mEditText;

    public KeyboardWithSearchView(Context context) {
        super(context);
        init(context);
    }

    public KeyboardWithSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardWithSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyboardWithSearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    protected BaseKeyboardView getBaseKeyboardView() {
        return mBaseKeyboardView;
    }

    protected EditText getEditText() {
        return mEditText;
    }

//    public void setOnSizeChangedListener(OnSizeChangedListener onSizeChangedListener) {
//        mOnSizeChangedListener = onSizeChangedListener;
//    }

    protected LinearLayout getKeyboadViewContainer() {
        return mKeyboadViewContainer;
    }



    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_keyboard_view,this,true);
        mEditText = (EditText) view.findViewById(R.id.hide_edittext);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.search_recycler_view);
        mBaseKeyboardView = (BaseKeyboardView) view.findViewById(R.id.keyboard_view);
        mKeyboadViewContainer = (LinearLayout) view.findViewById(R.id.keyboard_container);
    }

    protected void initRecyclerView(KeyboardSearchBaseAdapter adapter, RecyclerView.LayoutManager manager, RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    protected void setSearchResult(List list,boolean hasFixedSize) {
        if(mRecyclerView.getAdapter() == null) {
            throw new RuntimeException("this view has not invoked init method");
        }
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        if(list == null || list.size() ==0) {
            mRecyclerView.setVisibility(GONE);
        } else {
            int height = Utils.dipToPx(mContext,Math.min(3,list.size()) * 49) +
                    Math.min(3,list.size());
            ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
            if(params != null) {
                params.height = height;
            }else {
                LinearLayout.LayoutParams newParams =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                height);
                mRecyclerView.setLayoutParams(newParams);
            }
            mRecyclerView.setVisibility(VISIBLE);
        }
        mRecyclerView.setHasFixedSize(hasFixedSize);

        KeyboardSearchBaseAdapter adapter = (KeyboardSearchBaseAdapter) mRecyclerView.getAdapter();
        adapter.setAdapterData(list);
        adapter.notifyDataSetChanged();
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        if(mOnSizeChangedListener != null) {
//            mOnSizeChangedListener.sizeChanged(w,h,oldw,oldh);
//        }
//    }
//
//    protected interface OnSizeChangedListener {
//        void sizeChanged(int w, int h, int oldw, int oldh);
//    }
}
