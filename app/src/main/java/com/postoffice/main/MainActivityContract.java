package com.postoffice.main;

import com.postoffice.base.BaseFragment;
import com.postoffice.mvp.BasePresenter;
import com.postoffice.mvp.BaseView;

public class MainActivityContract {

    public interface View extends BaseView {
        void pushFragment(BaseFragment fragment, boolean is_add);
    }

    public abstract class Presenter extends BasePresenter<View> {
    }
}
