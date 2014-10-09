package edu.hm.cs.jenkins.monitor.view.holder;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Used to hold the views retrieved from the layout
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.JobFragment}.
 * <p/>
 * Keeping those views in the holder allows to avoid the
 * findViewById() method in a list adapter.
 *
 * @author Tobias Wochinger
 */
//CHECKSTYLE:OFF
public class JobHolder {

    public TextView name;

    public ImageView status;

    public LinearLayout layout;
}
//CHECKSTYLE:ON
