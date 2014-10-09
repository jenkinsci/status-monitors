package edu.hm.cs.jenkins.monitor.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import edu.hm.cs.jenkins.monitor.BaseTest;
import org.junit.Before;
import org.robolectric.shadows.ShadowImageView;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Robolectric.shadowOf;

/**
 * Defines common behaviour for adapter tests.
 *
 * @author Tobias Wochinger
 */
public class BaseAdapterTest extends BaseTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers();
    }

    /**
     * Asserts that a textview contains the correct text.
     *
     * @param view       view which contains the textView
     * @param textViewId id of the textView
     * @param expected   expected text
     */
    protected void assertCorrectTextView(final View view, final int textViewId, final String expected) {
        TextView textView = (TextView) view.findViewById(textViewId);
        String actual = textView.getText().toString();
        assertEquals("The view must contain " + expected, expected, actual);
    }

    /**
     * Asserts that a imageView contains the correct image.
     *
     * @param view               view which contains the textView
     * @param imageViewId        id of the textView
     * @param expectedDrawableId id of the expected drawable
     */
    protected void assertCorrectImageView(final View view, final int imageViewId, final int expectedDrawableId) {
        ImageView imageView = (ImageView) view.findViewById(imageViewId);
        ShadowImageView shadowed = shadowOf(imageView);
        int actual = shadowed.getImageResourceId();
        assertEquals("The view must contain drawable with resourceId " + expectedDrawableId, expectedDrawableId,
                actual);
    }
}
