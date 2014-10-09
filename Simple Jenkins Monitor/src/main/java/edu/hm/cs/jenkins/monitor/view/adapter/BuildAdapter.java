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
import edu.hm.cs.jenkins.monitor.controller.util.DurationUtil;
import edu.hm.cs.jenkins.monitor.view.holder.BuildHolder;
import edu.hm.cs.jenkins.web.model.Build;
import roboguice.inject.ContextSingleton;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Maps a list of {@link edu.hm.cs.jenkins.web.model.Build}s to
 * an Android list layout.
 *
 * @author Tobias Wochinger
 */
@ContextSingleton
public class BuildAdapter extends AbstractAdapter<Build> {

    private static final int RESOURCE = R.layout.build_list;

    private static final String NUMBER_ABBREVIATION = "#";

    private SimpleDateFormat dateFormatter;

    @Inject
    private DurationUtil durationUtil;

    @Inject
    private LayoutInflater inflater;

    /**
     * Creates a build adapter.
     *
     * @param context current application context.
     */
    @Inject
    public BuildAdapter(final Context context) {
        super(context, RESOURCE);
        initDateFormatter();
    }

    private void initDateFormatter() {
        String dateFormat = getContext().getString(R.string.date_format);
        dateFormatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
    }

    @Override
    protected View createView(final ViewGroup parent) {
        View view = inflater.inflate(RESOURCE, parent, false);
        BuildHolder buildHolder = fillBuildHolder(view);
        view.setTag(buildHolder);
        return view;
    }

    /**
     * Retrieves the views to hold from a given parent view.
     *
     * @param view view which contains the other views
     * @return holder which has references to the needed views
     * (e.g. TextViews, ImageViews)
     */
    private BuildHolder fillBuildHolder(final View view) {
        BuildHolder buildHolder = new BuildHolder();
        buildHolder.date = (TextView) view.findViewById(R.id.build_date);
        buildHolder.number = (TextView) view.findViewById(R.id.build_number);
        buildHolder.duration = (TextView) view.findViewById(R.id.build_duration);
        buildHolder.result = (ImageView) view.findViewById(R.id.build_result_icon);
        buildHolder.layout = (LinearLayout) view.findViewById(R.id.builds_layout);
        return buildHolder;
    }

    @Override
    protected void fillView(final View rowView, final int position) {
        BuildHolder buildHolder = (BuildHolder) rowView.getTag();
        Build buildAtPosition = getItem(position);

        Date date = buildAtPosition.getTimestampAsDate();
        String dateAsText = dateFormatter.format(date);
        buildHolder.date.setText(dateAsText);

        buildHolder.number.setText(NUMBER_ABBREVIATION + buildAtPosition.getNumber());

        long duration = buildAtPosition.getDuration();
        String readableDuration = durationUtil.getReadableDuration(duration);
        buildHolder.duration.setText(readableDuration);

        setImage(buildHolder, buildAtPosition);
    }

    /**
     * Sets the status icon depending on the result of a build.
     *
     * @param buildHolder holder which contains the
     *                    {@link android.widget.ImageView}
     * @param build       build depending on whose result the image is set
     */
    private void setImage(final BuildHolder buildHolder, final Build build) {
        if (build.getResult() == null) {
            buildHolder.result.setImageResource(R.drawable.in_progress);
        } else {
            switch (build.getResult()) {
                case SUCCESS:
                    setColorAndImage(buildHolder, ColorUtil.LIGHT_BLUE, R.drawable.blue_ball);
                    break;
                case UNSTABLE:
                    setColorAndImage(buildHolder, ColorUtil.LIGHT_YELLOW, R.drawable.yellow_ball);
                    break;
                case FAILURE:
                    setColorAndImage(buildHolder, ColorUtil.LIGHT_RED, R.drawable.red_ball);
                    break;
                default:
                    setColorAndImage(buildHolder, ColorUtil.LIGHT_GREY, R.drawable.grey_ball);
            }
        }
    }

    /**
     * Sets the background color and the result icon.
     *
     * @param holder     holder which contains the views
     * @param color      background color which should be set
     * @param drawableId id of the icon which should be used
     */
    private void setColorAndImage(final BuildHolder holder, final int color, final int drawableId) {
        holder.layout.setBackgroundColor(color);
        holder.result.setImageResource(drawableId);
    }
}