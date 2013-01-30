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

package org.solovyev.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 6/3/12
 * Time: 4:43 PM
 */
public class MaxIdLoader implements DbQuery<Integer> {

    @NotNull
    private final String tableName;

    @NotNull
    private final String columnName;

    public MaxIdLoader(@NotNull String tableName, @NotNull String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    @NotNull
    @Override
    public Cursor createCursor(@NotNull SQLiteDatabase db) {
        final StringBuilder query = new StringBuilder();
        query.append("select max(").append(columnName).append(") from ").append(tableName);
        return db.rawQuery(query.toString(), null);
    }

    @NotNull
    @Override
    public Integer retrieveData(@NotNull Cursor cursor) {
        cursor.moveToNext();
        return cursor.getInt(0);
    }
}

