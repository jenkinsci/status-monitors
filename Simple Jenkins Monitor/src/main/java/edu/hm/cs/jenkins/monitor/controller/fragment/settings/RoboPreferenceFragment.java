package edu.hm.cs.jenkins.monitor.controller.fragment.settings;

import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import android.view.View;
import roboguice.RoboGuice;

/**
 * RoboGuice version of an
 * {@link android.support.v4.preference.PreferenceFragment} which allows
 * to inject views etc.
 * <p/>
 * Can be removed in later versions of RoboGuice (likely > 3.0),
 * because then it's provided as default.
 *
 * @author Tobias Wochinger
 */
public abstract class RoboPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
    }
}