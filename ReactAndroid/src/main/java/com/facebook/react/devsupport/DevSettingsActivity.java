/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.facebook.react.R;
import com.facebook.react.common.DebugServerException;
import com.microsoft.intune.mam.client.preference.MAMPreferenceActivity;

/**
 * Activity that display developers settings. Should be added to the debug manifest of the app. Can
 * be triggered through the developers option menu displayed by {@link DevSupportManager}.
 */
public class DevSettingsActivity extends MAMPreferenceActivity {

  @Override
  public void onMAMCreate(Bundle savedInstanceState) {
    super.onMAMCreate(savedInstanceState);
    setTitle(getApplication().getResources().getString(R.string.catalyst_settings_title));
    addPreferencesFromResource(R.xml.preferences);
  }
}
