package cn.baymax.android.keyboard;

import android.content.Context;
import android.text.Editable;

/**
 * Created by xud on 2017/3/2.
 */

public class NumberKeyboard extends BaseKeyboard {

    public static final int DEFAULT_NUMBER_XML_LAYOUT = R.xml.keyboard_number;

    public ActionDoneClickListener mActionDoneClickListener;

    public NumberKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    public NumberKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
    }

    public NumberKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
    }

    public NumberKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }

    public void setActionDoneClickListener(ActionDoneClickListener actionDoneClickListener) {
        mActionDoneClickListener = actionDoneClickListener;
    }

    @Override
    public boolean handleSpecialKey(int primaryCode) {
        Editable editable = getEditText().getText();
        int start = getEditText().getSelectionStart();
        //小数点
        if(primaryCode == 46) {
            if(!editable.toString().contains(".")){
                if(!editable.toString().startsWith(".")) {
                    editable.insert(start, Character.toString((char) primaryCode));
                }else {
                    editable.insert(start, "0"+Character.toString((char) primaryCode));
                }
            }
            return true;
        }
        if(primaryCode == getKeyCode(R.integer.action_done)) {
            if(mActionDoneClickListener != null) {
                mActionDoneClickListener.onActionDone(editable);
            }else {
                hideKeyboard();
            }
            return true;
        }
        return false;
    }

    public interface ActionDoneClickListener {
        void onActionDone(CharSequence charSequence);
    }

}
