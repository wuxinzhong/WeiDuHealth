package com.wd.health_login_and_regress.util;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


public class RecordClickSpan extends ClickableSpan {
 @Override
 public void onClick(View widget) {
 
 }
 
 @Override
 public void updateDrawState(TextPaint ds) {
  ds.setColor(Color.parseColor("#ffffff"));
  ds.setUnderlineText(false);
 }
}