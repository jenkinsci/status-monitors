package edu.hm.cs.jenkins.monitor.view.dialog;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by Tobias on 01.09.2014.
 */
public class ToastDialog implements Dialog {

    @Inject
    private Context context;

    @Override
    public void show(final int messageId) {
        String message = context.getString(messageId);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
