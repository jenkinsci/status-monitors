package edu.hm.cs.jenkins.monitor.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.util.ColorUtil;
import edu.hm.cs.jenkins.monitor.view.holder.JobHolder;
import edu.hm.cs.jenkins.web.model.Job;
import roboguice.inject.ContextSingleton;

import javax.inject.Inject;

/**
 * Maps a list of {@link edu.hm.cs.jenkins.web.model.Job}s
 * to an Android list layout.
 *
 * @author Tobias Wochinger
 */
@ContextSingleton
public class JobAdapter extends AbstractAdapter<Job> {

    private static final int RESOURCE = R.layout.job_list;

    @Inject
    private LayoutInflater inflater;

    /**
     * Creates a job adapter.
     *
     * @param context current application context.
     */
    @Inject
    public JobAdapter(final Context context) {
        super(context, RESOURCE);
    }

    @Override
    protected View createView(final ViewGroup parent) {
        View view = inflater.inflate(RESOURCE, parent, false);
        JobHolder jobHolder = fillJobHolder(view);
        view.setTag(jobHolder);
        return view;
    }

    /**
     * Retrieves the views to hold from a given parent view.
     *
     * @param view view which contains the other views
     * @return holder which has references to the needed views
     * (e.g. TextViews, ImageViews)
     */
    private JobHolder fillJobHolder(final View view) {
        JobHolder jobHolder = new JobHolder();
        jobHolder.name = (TextView) view.findViewById(R.id.jobname);
        jobHolder.status = (ImageView) view.findViewById(R.id.jobstatus_icon);
        jobHolder.layout = (LinearLayout) view.findViewById(R.id.jobs_layout);
        return jobHolder;
    }

    @Override
    protected void fillView(final View rowView, final int position) {
        JobHolder jobHolder = (JobHolder) rowView.getTag();
        Job jobAtPosition = getItem(position);
        jobHolder.name.setText(jobAtPosition.getDisplayName());
        setImage(jobHolder, jobAtPosition);
    }

    /**
     * Sets the status icon depending on the status of a job.
     *
     * @param jobHolder     holder which contains the
     *                      {@link android.widget.ImageView}
     * @param jobAtPosition job depending on whose status the image is set
     */
    private void setImage(final JobHolder jobHolder, final Job jobAtPosition) {
        switch (jobAtPosition.getColor()) {
            case BLUE:
                setColorAndImage(jobHolder, ColorUtil.LIGHT_BLUE, R.drawable.blue_ball);
                break;
            case YELLOW:
                setColorAndImage(jobHolder, ColorUtil.LIGHT_YELLOW, R.drawable.yellow_ball);
                break;
            case RED:
                setColorAndImage(jobHolder, ColorUtil.LIGHT_RED, R.drawable.red_ball);
                break;
            case GREY:
            case NOTBUILT:
            case ABORTED:
            case DISABLED:
                setColorAndImage(jobHolder, ColorUtil.LIGHT_GREY, R.drawable.grey_ball);
                break;
            default:
                jobHolder.status.setImageResource(R.drawable.in_progress);
        }
    }

    /**
     * Sets the background color and the result icon.
     *
     * @param holder     holder which contains the views
     * @param color      background color which should be set
     * @param drawableId id of the icon which should be used
     */
    private void setColorAndImage(final JobHolder holder, final int color, final int drawableId) {
        holder.layout.setBackgroundColor(color);
        holder.status.setImageResource(drawableId);
    }
}