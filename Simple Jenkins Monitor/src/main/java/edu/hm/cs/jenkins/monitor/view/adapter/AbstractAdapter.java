package edu.hm.cs.jenkins.monitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Defines common behaviour for the list adapters {@link JobAdapter}
 * and {@link BuildAdapter}.
 * <p/>
 * List adapters are used to map Java objects to an Android list layout.
 *
 * @param <T> Class which should be adapted
 * @author Tobias Wochinger
 */
public abstract class AbstractAdapter<T> extends ArrayAdapter<T> {

    /**
     * Creates an adapter.
     *
     * @param context  current application context
     * @param resource resourceId of the list row
     */
    public AbstractAdapter(final Context context, final int resource) {
        super(context, resource);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = createView(parent);
        }

        fillView(rowView, position);
        return rowView;
    }

    /**
     * Inflates the row of the list.
     *
     * @param parent parent of the listView
     * @return row which can be filled
     */
    protected abstract View createView(final ViewGroup parent);

    /**
     * Fills the row with the data provided by the object at the
     * related position in the list.
     *
     * @param view     view of the row which contains the TextViews, ImageViews etc.
     * @param position index of the row which should be filled
     */
    protected abstract void fillView(final View view, final int position);
}