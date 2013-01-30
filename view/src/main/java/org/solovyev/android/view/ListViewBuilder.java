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

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: serso
 * Date: 4/26/12
 * Time: 1:05 PM
 */
public class ListViewBuilder implements ViewBuilder<ListView> {

    @Nullable
    private Integer layoutId;

    @NotNull
    private ListAdapter listAdapter;

    private ListViewBuilder() {
    }

    @NotNull
    public static ViewBuilder<ListView> newInstance(@NotNull ListAdapter listAdapter) {
        final ListViewBuilder result = new ListViewBuilder();

        result.layoutId = null;
        result.listAdapter = listAdapter;

        return result;
    }

    @NotNull
    public static ViewBuilder<ListView> newInstance(int layoutId, @NotNull ListAdapter listAdapter) {
        final ListViewBuilder result = new ListViewBuilder();

        result.layoutId = layoutId;
        result.listAdapter = listAdapter;

        return result;
    }

    @NotNull
    @Override
    public ListView build(@NotNull Context context) {
        final ListView result;
        if (layoutId != null) {
            result = ViewFromLayoutBuilder.<ListView>newInstance(layoutId).build(context);
        } else {
            result = new ListView(context);
        }

        result.setAdapter(listAdapter);

        return result;
    }
}
