package edu.hm.cs.jenkins.monitor.controller.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import edu.hm.cs.jenkins.monitor.R;

import javax.inject.Inject;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Handles unexpected exceptions.
 * Asks the user whether to send a mail with stacktrace or not.
 * After that it kills the app so that it can be started again from scratch.
 *
 * @author Tobias Wochinger
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Inject
    private Activity current;

    private Throwable cause;

    private static String[] recipient = new String[] {"wochinge@hm.edu"};

    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        this.cause = ex;
        final AlertDialog.Builder builder = new AlertDialog.Builder(current);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                builder.setTitle(R.string.exception_dialog_title);
                builder.create();
                builder.setNegativeButton(R.string.cancel, new CancelListener());
                builder.setPositiveButton(R.string.send_email, new SendEmailListener());
                builder.setMessage(R.string.exception_dialog_message);
                builder.show();

                Looper.loop();
            }
        } .start();
    }

    /**
     * Handles it when the user does not want to send a mail to the developer.
     */
    private class CancelListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            dialog.dismiss();
            finishApp();
        }
    }

    /**
     * Handles the case when the user wants to send a mail to the developer.
     */
    private class SendEmailListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(final DialogInterface dialog, final int which) {
            sendReport();
            finishApp();
        }
    }

    private void sendReport() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        email.putExtra(Intent.EXTRA_EMAIL, recipient);
        email.putExtra(Intent.EXTRA_SUBJECT, current.getString(R.string.exception_mail_subject));
        email.putExtra(Intent.EXTRA_TEXT, causeAsString());
        current.startActivity(email);
    }

    private String causeAsString() {
        StringWriter writer = new StringWriter();
        cause.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    private void finishApp() {
        current.finish();
        Process.killProcess(Process.myPid());
    }
}
