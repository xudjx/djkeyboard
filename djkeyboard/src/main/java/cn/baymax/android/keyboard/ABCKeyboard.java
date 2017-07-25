package cn.baymax.android.keyboard;

import android.content.Context;

/**
 * Created by xud on 2017/3/2.
 */

public class ABCKeyboard extends BaseKeyboard {

    public static final int DEFAULT_ABC_XML_LAYOUT = R.xml.keyboard_abc;

    public ABCKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    public ABCKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
    }

    public ABCKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
    }

    public ABCKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }

    @Override
    public boolean handleSpecialKey(int primaryCode) {
        return false;
    }

    @Override
    public Padding getPadding() {
        return new Padding(10,0,10,0);
    }
}
