/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.view;

/**
 * User: serso
 * Date: 9/18/11
 * Time: 10:04 PM
 */



import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageButton;

/**
 * This class exists purely to cancel long click events, that got
 * started in NumberPicker
 */
public class PickerButton extends ImageButton {

    private Picker numberPicker;

    public PickerButton(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }

    public PickerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickerButton(Context context) {
        super(context);
    }

    public void setNumberPicker(Picker picker) {
        numberPicker = picker;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cancelLongPressIfRequired(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        cancelLongPressIfRequired(event);
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
                || (keyCode == KeyEvent.KEYCODE_ENTER)) {
            cancelLongpress();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void cancelLongPressIfRequired(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_CANCEL)
                || (event.getAction() == MotionEvent.ACTION_UP)) {
            cancelLongpress();
        }
    }

    private void cancelLongpress() {
        if (R.id.increment == getId()) {
            numberPicker.cancelIncrement();
        } else if (R.id.decrement == getId()) {
            numberPicker.cancelDecrement();
        }
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus) {
            cancelLongpress();
        }
    }

}