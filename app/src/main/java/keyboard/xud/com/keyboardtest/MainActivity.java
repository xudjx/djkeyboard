package keyboard.xud.com.keyboardtest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.Toast;

import cn.baymax.android.keyboard.ABCKeyboard;
import cn.baymax.android.keyboard.BaseKeyboard;
import cn.baymax.android.keyboard.KeyboardManager;
import cn.baymax.android.keyboard.NumberKeyboard;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;

    private Context context;
    private KeyboardManager keyboardManagerNumber;
    private NumberKeyboard numberKeyboard;

    private KeyboardManager keyboardManagerAbc;
    private ABCKeyboard abcKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        editText1 = (EditText) findViewById(R.id.edit1);
        editText2 = (EditText) findViewById(R.id.edit2);
        editText1.setInputType(InputType.TYPE_CLASS_TEXT);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        
        keyboardManagerNumber = new KeyboardManager(this);
        initNumberKeyboard();
        keyboardManagerNumber.bindToEditor(editText2, numberKeyboard);

        keyboardManagerAbc = new KeyboardManager(this);
        keyboardManagerAbc.bindToEditor(editText1, new ABCKeyboard(context, ABCKeyboard.DEFAULT_ABC_XML_LAYOUT));
    }

    private void initNumberKeyboard() {
        numberKeyboard = new NumberKeyboard(context,NumberKeyboard.DEFAULT_NUMBER_XML_LAYOUT);
        numberKeyboard.setEnableDotInput(true);
        numberKeyboard.setActionDoneClickListener(new NumberKeyboard.ActionDoneClickListener() {
            @Override
            public void onActionDone(CharSequence charSequence) {
                if(TextUtils.isEmpty(charSequence) || charSequence.toString().equals("0") || charSequence.toString().equals("0.0")) {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                }else {
                    onNumberkeyActionDone();
                }
            }
        });

        numberKeyboard.setKeyStyle(new BaseKeyboard.KeyStyle() {
            @Override
            public Drawable getKeyBackound(Keyboard.Key key) {
                if(key.iconPreview != null) {
                    return key.iconPreview;
                } else {
                    return ContextCompat.getDrawable(context,R.drawable.key_number_bg);
                }
            }

            @Override
            public Float getKeyTextSize(Keyboard.Key key) {
                if(key.codes[0] == context.getResources().getInteger(R.integer.action_done)) {
                    return convertSpToPixels(context, 20f);
                }
                return convertSpToPixels(context, 24f);
            }

            @Override
            public Integer getKeyTextColor(Keyboard.Key key) {
                if(key.codes[0] == context.getResources().getInteger(R.integer.action_done)) {
                    return Color.WHITE;
                }
                return null;
            }

            @Override
            public CharSequence getKeyLabel(Keyboard.Key key) {
                return null;
            }
        });
    }

    public float convertSpToPixels(Context context, float sp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public void onNumberkeyActionDone() {
        editText1.requestFocus();
    }
}
