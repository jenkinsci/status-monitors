package edu.hm.cs.jenkins.monitor.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import edu.hm.cs.jenkins.monitor.R;
import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectResource;

import javax.inject.Inject;

/**
 * Class to show dialogs to the user which inform the user
 * about an occurred error.
 *
 * @author Tobias Wochinger
 */
public class ErrorDialog extends RoboDialogFragment implements Dialog {


    public static final String TAG = ErrorDialog.class.getSimpleName();

    @InjectResource(R.string.error_dialog_title)
    private String dialogTitle;

    private int errorMessage;

    @Inject
    private FragmentManager fragmentManager;

    @Inject
    private Context context;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(final Bundle savedInstanceState) {
        setRetainInstance(true);
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(dialogTitle);
        builder.setMessage(errorMessage);
        builder.setNeutralButton(R.string.ok, new NeutralButtonListener());
        return builder.create();
    }

    @Override
    public void show(final int messageId) {
        if (!isAdded()) {
            this.errorMessage = messageId;
            show(fragmentManager, TAG);
        }
    }

    /**
     * Handles a click on the "Ok" button of an error dialog.
     */
    public class NeutralButtonListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            fragmentManager.beginTransaction().remove(ErrorDialog.this).commit();
            ErrorDialog.this.dismiss();
        }
    }
}