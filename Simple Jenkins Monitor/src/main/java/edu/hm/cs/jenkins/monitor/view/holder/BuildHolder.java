package edu.hm.cs.jenkins.monitor.view.holder;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Used to hold the views retrieved from the layout
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.BuildFragment}.
 * <p/>
 * Keeping those views in the holder allows to avoid the
 * findViewById() method in a list adapter.
 *
 * @author Tobias Wochinger
 */
//CHECKSTYLE:OFF
public class BuildHolder {

    public TextView date;

    public TextView number;

    public TextView duration;

    public ImageView result;

    public LinearLayout layout;
}
//CHECKSTYLE:ON