/*
 * Copyright (C) 2018 Eric Afenyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.eric.quickheadline.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by eric on 04/02/2018.
 */

public class TUtils {
    private static Toast toast;

    public static void toast(Context context, Object message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, String.valueOf(message), Toast.LENGTH_SHORT);
        toast.show();
    }
}
