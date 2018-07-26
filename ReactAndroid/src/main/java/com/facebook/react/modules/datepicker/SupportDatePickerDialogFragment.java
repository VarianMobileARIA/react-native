/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.modules.datepicker;

import javax.annotation.Nullable;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.microsoft.intune.mam.client.support.v4.app.MAMDialogFragment;

@SuppressLint("ValidFragment")
public class SupportDatePickerDialogFragment extends MAMDialogFragment {

  @Nullable
  private OnDateSetListener mOnDateSetListener;
  @Nullable
  private OnDismissListener mOnDismissListener;

  @Override
  public Dialog onMAMCreateDialog(Bundle savedInstanceState) {
    final Bundle args = getArguments();
    return DatePickerDialogFragment.createDialog(args, getActivity(), mOnDateSetListener);
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    if (mOnDismissListener != null) {
      mOnDismissListener.onDismiss(dialog);
    }
  }

  /*package*/ void setOnDateSetListener(@Nullable OnDateSetListener onDateSetListener) {
    mOnDateSetListener = onDateSetListener;
  }

  /*package*/ void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
    mOnDismissListener = onDismissListener;
  }
}
