package edu.hm.cs.jenkins.monitor.controller.web.callback;

import android.widget.ArrayAdapter;
import edu.hm.cs.jenkins.monitor.BaseTest;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * /**
 * Basic behaviour for tests of callbacks which pass the values to an {@link android.widget.ArrayAdapter}.
 * @param <T> class to adapt
 *
 * @author Tobias Wochinger
 */
public class BaseAdapterCallbackTest<T> extends BaseTest {

    @Mock
    private ArrayAdapter<T> adapter;

    /**
     * Verifies that the adapter was cleared and marked as changed.
     */
    protected void verifyBasicListCallbackBehaviour() {
        verify(adapter).clear();
        verify(adapter).notifyDataSetChanged();
    }

    /**
     * Returns the adapter.
     * @return arrayAdapter
     */
    protected ArrayAdapter<T> getAdapter() {
        return adapter;
    }
}
