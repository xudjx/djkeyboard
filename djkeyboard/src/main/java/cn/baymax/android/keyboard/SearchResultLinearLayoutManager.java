package cn.baymax.android.keyboard;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xud on 2017/3/9.
 */

public class SearchResultLinearLayoutManager extends LinearLayoutManager {

    private static final String TAG = "srll";

    private Context mContext;

    private int maxVisibleItem = 3;

    private final int[] mMeasuredDimension = new int[2];

    public SearchResultLinearLayoutManager(Context context) {
        this(context,3);
    }

    public SearchResultLinearLayoutManager(Context context, int maxVisibleItem) {
        super(context);
        init(context,maxVisibleItem);
    }

    public SearchResultLinearLayoutManager(Context context, int orientation, boolean reverseLayout, int maxVisibleItem) {
        super(context, orientation, reverseLayout);
        init(context,maxVisibleItem);
    }

    public SearchResultLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int maxVisibleItem) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,maxVisibleItem);
    }

    private void init(Context context,int maxVisibleItem) {
        mContext = context;
        this.maxVisibleItem = Math.max(maxVisibleItem,0);
    }

    @Override
    public void onMeasure(final RecyclerView.Recycler recycler, final RecyclerView.State state,
                          final int widthSpec, final int heightSpec) {

        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;
        int itemCount = Math.min(getItemCount(),maxVisibleItem);
        for (int i = 0; i < itemCount; i++) {
            measureScrapChild(recycler, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.
                            UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);


            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                height = height + mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(final RecyclerView.Recycler recycler, final int widthSpec,
                                   final int heightSpec, final int[] measuredDimension) {
        try {
            View view = recycler.getViewForPosition(0);

            if (view != null) {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();

                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                        getPaddingLeft() + getPaddingRight(), p.width);

                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                        getPaddingTop() + getPaddingBottom(), p.height);

                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Disable scrolling.
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
