package com.postoffice;

import com.postoffice.base.BaseFragment;

public interface FragmentNavigation {

    interface View {
        void attachNavigationPresenter(Presenter presenter);
    }

    interface Presenter {
        void pushFragment(BaseFragment fragment, boolean isAdd);
    }
}
