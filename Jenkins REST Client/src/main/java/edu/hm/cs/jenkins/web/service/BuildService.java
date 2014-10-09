package edu.hm.cs.jenkins.web.service;

import edu.hm.cs.jenkins.web.model.Job;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Interface which Retrofit extends for the Jenkins-REST-API access.
 * Used for operations concerning the builds of a
 * {@link edu.hm.cs.jenkins.web.model.Job}.
 *
 * @author Tobias Wochinger
 */
public interface BuildService {

    /**
     * Does a request to get all builds of a job with url, id,
     * number, timestamp, duration and result.
     *
     * @param jobname  name of the job
     *                 whose builds should be retrieved
     * @param callback callback for retrieving
     *                 the result of the operation
     */
    @GET("/job/{jobname}/api/json?tree=builds" + "[url,id,number,timestamp,duration,result]")
    void getAllBuilds(@Path("jobname") final String jobname, final Callback<Job> callback);

    /**
     * Does a request to get the last buld of a job
     * (no matter whether it was successfull or not).
     *
     * @param jobname  name of the job whose last build should be retrieved
     * @param callback callback for the result
     */
    @GET("/job/{jobname}/api/json?tree=lastBuild" + "[url,id,number,timestamp,duration,result]")
    void getLastBuild(@Path("jobname") final String jobname, final Callback<Job> callback);

}
