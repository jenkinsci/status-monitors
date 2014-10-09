package edu.hm.cs.jenkins.notifier.controller.communication;

import edu.hm.cs.jenkins.notifier.controller.notification.NotificationController;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ClientError;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Handles the receiving of jobs of Jenkins and retrieves the last build of
 * each job.
 *
 * @author Tobias Wochinger
 */
public class JobController implements ClientCallback<List<Job>> {

    @Inject
    private ClientFactory clientFactory;

    @Inject
    private NotificationController notificationsController;

    @Inject
    private ResourceBundle bundle;

    @Inject
    private Provider<ClientCallback<Job>> callbackProvider;

    private BuildClient buildClient;

    @Override
    public void onSuccess(final List<Job> jobs) {
        buildClient = clientFactory.createBuildClient();
        for (Job job : jobs) {
            ClientCallback<Job> callback = callbackProvider.get();
            buildClient.getLastBuild(job, callback);
        }
    }

    @Override
    public void onError(final ClientError error) {
        String errorMessage = bundle.getString("error.web.content");
        notificationsController.show(errorMessage);
    }
}
