package com.example.aspectdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-18 15:37
 * @depiction ：
 */
@SuppressLint("AppCompatCustomView")
public class ComposEditText extends EditText {
    public ComposEditText(Context context) {
        super(context);
    }

    public ComposEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ComposEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ComposEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private OnFinishComposingListener mFinishComposingListener;

    public void setOnFinishComposingListener(OnFinishComposingListener listener) {
        this.mFinishComposingListener = listener;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new EditInputConnection(super.onCreateInputConnection(outAttrs), false, this);
    }

    private class EditInputConnection extends InputConnectionWrapper {
        private EditText editText;

        public EditInputConnection(InputConnection target, boolean mutable, EditText editText) {
            super(target, mutable);
            this.editText = editText;
        }

        @Override
        public boolean finishComposingText() {
            boolean finishComposing = super.finishComposingText();
            if (mFinishComposingListener != null) {
                mFinishComposingListener.finishComposing(editText);
            } else
                new EditTextFinishComposingListener().finishComposing(editText);

            return finishComposing;
        }
    }

    public static interface OnFinishComposingListener {
        public void finishComposing(EditText editText);
    }

    private class EditTextFinishComposingListener implements OnFinishComposingListener {

        @Override
        public void finishComposing(EditText editText) {

        }
    }
}

