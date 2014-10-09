package edu.hm.cs.jenkins.web.callback;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Encapsulates detailed information about an error which occurs during a
 * webservice call.
 */
public class ClientError {

    private final String message;

    private int responseStatus;

    /**
     * Creates a ClientError.
     *
     * @param message        message which describes the error
     * @param responseStatus http status returned
     */
    public ClientError(final String message, final int responseStatus) {
        this.message = message;
        this.responseStatus = responseStatus;
    }

    /**
     * Adapts the information out of a {@link retrofit.RetrofitError} to
     * an ClientError.
     *
     * @param error given error
     */
    public ClientError(final RetrofitError error) {
        this.message = error.getMessage();
        Response response = error.getResponse();
        if (response != null) {
            this.responseStatus = response.getStatus();
        }
    }

    public String getMessage() {
        return message;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
