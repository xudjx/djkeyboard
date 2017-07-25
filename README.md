# djkeyboard
Customize the keyboard, both number keyboard and alphabet keyboard.

Please import the module.

### Number Keyboard

You can use as follow:

```
KeyboardManager keyboardManagerNumber = new KeyboardManager(this);
NumberKeyboard numberKeyboard = new NumberKeyboard(context,NumberKeyboard.DEFAULT_NUMBER_XML_LAYOUT);
keyboardManagerNumber.bindToEditor(editText2, numberKeyboard);
```

![Number Keyboard](http://7xopuh.dl1.z0.glb.clouddn.com/number.png)




### Alphabet Keyboard

You can use as follow:

```
KeyboardManager keyboardManagerAbc = new KeyboardManager(this); 
keyboardManagerAbc.bindToEditor(editText1, new ABCKeyboard(context, ABCKeyboard.DEFAULT_ABC_XML_LAYOUT));
```

![Alphabet Keyboard](http://7xopuh.dl1.z0.glb.clouddn.com/label.png)


By the way, the module also has some useful interface for customized requirements.

```
public interface KeyStyle {

    public Drawable getKeyBackound(Key key);

    public Float getKeyTextSize(Key key);

    public Integer getKeyTextColor(Key key);

    public CharSequence getKeyLabel(Key key);
}

```

```
public interface ActionDoneClickListener {
    void onActionDone(CharSequence charSequence);
}
```

