package edu.hm.cs.jenkins.monitor.view.settings;

import android.content.Context;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * More friendly {@link android.preference.EditTextPreference} which shows the current value as summary and if there
 * is no current value, it shows the hint as summary.
 *
 * @author Tobias Wochinger
 */
public class ExtendedTextPreference extends EditTextPreference {

    /**
     * Creates an ExtendedTextPreference.
     * @param context current application context
     * @param attrs attributes
     * @param defStyle defStyle
     */
    public ExtendedTextPreference(final Context context, final AttributeSet attrs, final  int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Creates an ExtendedTextPreference.
     * @param context current application context
     * @param attrs attributes
     */
    public ExtendedTextPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Creates an ExtendedTextPreference.
     * @param context current application context
     */
    public ExtendedTextPreference(final Context context) {
        super(context);
    }

    @Override
    public CharSequence getSummary() {
        String text = getText();
        if (TextUtils.isEmpty(text)) {
            return getEditText().getHint();
        } else {
            CharSequence summary = super.getSummary();
            if (summary != null) {
                return String.format(summary.toString(), text);
            }
        }
        return null;
    }
}
