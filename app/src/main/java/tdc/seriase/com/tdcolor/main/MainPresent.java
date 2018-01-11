package tdc.seriase.com.tdcolor.main;

/**
 * Created by chen jc on 2018/1/3.
 */

public class MainPresent implements MainContact.MainPresent {

    private MainContact.MainView view;

    public MainPresent(MainContact.MainView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
