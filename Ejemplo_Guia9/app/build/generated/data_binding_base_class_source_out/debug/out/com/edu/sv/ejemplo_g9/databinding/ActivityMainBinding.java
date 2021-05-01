// Generated by view binder compiler. Do not edit!
package com.edu.sv.ejemplo_g9.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.edu.sv.ejemplo_g9.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView listDogs;

  @NonNull
  public final SearchView searchDogs;

  @NonNull
  public final ConstraintLayout viewRoot;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull RecyclerView listDogs,
      @NonNull SearchView searchDogs, @NonNull ConstraintLayout viewRoot) {
    this.rootView = rootView;
    this.listDogs = listDogs;
    this.searchDogs = searchDogs;
    this.viewRoot = viewRoot;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.listDogs;
      RecyclerView listDogs = rootView.findViewById(id);
      if (listDogs == null) {
        break missingId;
      }

      id = R.id.searchDogs;
      SearchView searchDogs = rootView.findViewById(id);
      if (searchDogs == null) {
        break missingId;
      }

      ConstraintLayout viewRoot = (ConstraintLayout) rootView;

      return new ActivityMainBinding((ConstraintLayout) rootView, listDogs, searchDogs, viewRoot);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
