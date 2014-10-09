package edu.hm.cs.jenkins.notifier.controller.communication;

import edu.hm.cs.jenkins.notifier.controller.notification.NotificationController;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ClientError;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;

/**
 * Handles the receiving of the last builds of the Jenkins jobs.
 * Shows a notification if a build completed in the last check interval.
 *
 * @author Tobias Wochinger
 */
public class LastBuildController implements ClientCallback<Job> {

    @Inject
    private NotificationController notificationController;

    @Override
    public void onSuccess(final Job job) {
        Build lastBuild = job.getLastBuild();
        if (lastBuild != null && isFinished(lastBuild) && isFinishedSinceLastCheck(lastBuild)
                && !isFinishTimeInFuture(lastBuild)) {

            notificationController.show(job.getDisplayName(), lastBuild.getResult());
        }
    }

    private boolean isFinished(final Build build) {
        return build.getDuration() > 0;
    }

    private boolean isFinishedSinceLastCheck(final Build build) {
        long finishingTime = build.getTimestamp() + build.getDuration();
        long lastCheck = System.currentTimeMillis() - BuildCompletionChecker.INTERVAL_IN_MS;

        return finishingTime > lastCheck;
    }


    private boolean isFinishTimeInFuture(final Build build) {
        long finishingTime = build.getTimestamp() + build.getDuration();
        return finishingTime > System.currentTimeMillis();
    }

    @Override
    public void onError(final ClientError error) {
        String errorMessage = error.getMessage();
        System.err.println(errorMessage);
    }

}
