package com.github.tianma8023.smscode.app.base.back;

import androidx.fragment.app.Fragment;

/**
 * Fragment that handled back pressed event.
 */
public class BackPressFragment extends Fragment implements BackPressedListener {

    @Override
    public boolean onInterceptBackPressed() {
        return false;
    }

    @Override
    public void onBackPressed() {
        BackPressEventDispatchHelper.dispatchBackPressedEvent(this);
    }
}
