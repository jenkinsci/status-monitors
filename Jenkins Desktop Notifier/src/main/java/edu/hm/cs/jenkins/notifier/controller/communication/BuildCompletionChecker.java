package edu.hm.cs.jenkins.notifier.controller.communication;

import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Job which runs in the background to check whether
 * a build completed in the last check interval.
 *
 * @author Tobias Wochinger
 */
@Singleton
public class BuildCompletionChecker implements Runnable {

    protected static final int INTERVAL_IN_MS = 60000;

    @Inject
    private ClientFactory clientFactory;

    @Inject
    private ClientCallback<List<Job>> callback;

    private Thread job;

    private JobClient jobClient;

    private boolean interrupted = false;

    /**
     * Starts the job.
     */
    public void start() {
        initClient();
        startJob();
    }

    /**
     * Checks whether the job is running.
     *
     * @return <code>true</code> if running, else <code>false</code>
     */
    public boolean isRunning() {
        return !(job == null);
    }

    private void initClient() {
        jobClient = clientFactory.createJobClient();
    }

    private void startJob() {
        interrupted = false;
        job = new Thread(this);
        job.start();
    }

    /**
     * Refreshes clients to work with the latest credentials.
     */
    public void refreshClient() {
        initClient();
    }

    /**
     * Stops the job.
     */
    public void stop() {
        if (job != null) {
            interrupted = true;
            job.interrupt();
            job = null;
        }
    }

    @Override
    public void run() {
        while (!interrupted) {
            jobClient.getAllJobs(callback);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(INTERVAL_IN_MS);
        } catch (InterruptedException e) {
            System.out.println("Job was interrupted and will stop now.");
            //normal behaviour: Thread ends
        }
    }
}
