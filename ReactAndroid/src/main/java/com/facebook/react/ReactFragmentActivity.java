/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react;

import javax.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.microsoft.intune.mam.client.support.v4.app.MAMFragmentActivity;

/**
 * Base Activity for React Native applications. Like {@link ReactActivity} but extends
 * {@link FragmentActivity} instead of {@link android.app.Activity}.
 */
public abstract class ReactFragmentActivity extends MAMFragmentActivity implements
  DefaultHardwareBackBtnHandler, PermissionAwareActivity {

  private final ReactActivityDelegate mDelegate;

  protected ReactFragmentActivity() {
    mDelegate = createReactActivityDelegate();
  }

  /**
   * Returns the name of the main component registered from JavaScript.
   * This is used to schedule rendering of the component.
   * e.g. "MoviesApp"
   */
  protected @Nullable String getMainComponentName() {
    return null;
  }

  /**
   * Called at construction time, override if you have a custom delegate implementation.
   */
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new ReactActivityDelegate(this, getMainComponentName());
  }

  @Override
  public void onMAMCreate(Bundle savedInstanceState) {
    super.onMAMCreate(savedInstanceState);
    mDelegate.onCreate(savedInstanceState);
  }

  @Override
  public void onMAMPause() {
    super.onMAMPause();
    mDelegate.onPause();
  }

  @Override
  public void onMAMResume() {
    super.onMAMResume();
    mDelegate.onResume();
  }

  @Override
  public void onMAMDestroy() {
    super.onMAMDestroy();
    mDelegate.onDestroy();
  }

  @Override
  public void onMAMActivityResult(int requestCode, int resultCode, Intent data) {
    mDelegate.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    return mDelegate.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);
  }

  @Override
  public void onBackPressed() {
    if (!mDelegate.onBackPressed()) {
      super.onBackPressed();
    }
  }

  @Override
  public void invokeDefaultOnBackPressed() {
    super.onBackPressed();
  }

  @Override
  public void onMAMNewIntent(Intent intent) {
    if (!mDelegate.onNewIntent(intent)) {
      super.onMAMNewIntent(intent);
    }
  }

  @Override
  public void requestPermissions(
    String[] permissions,
    int requestCode,
    PermissionListener listener) {
    mDelegate.requestPermissions(permissions, requestCode, listener);
  }

  @Override
  public void onRequestPermissionsResult(
    int requestCode,
    String[] permissions,
    int[] grantResults) {
    mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  protected final ReactNativeHost getReactNativeHost() {
    return mDelegate.getReactNativeHost();
  }

  protected final ReactInstanceManager getReactInstanceManager() {
    return mDelegate.getReactInstanceManager();
  }

  protected final void loadApp(String appKey) {
    mDelegate.loadApp(appKey);
  }
}
