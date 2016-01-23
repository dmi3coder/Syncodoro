package github.dmi3coder.syncodoro.event.handler;

import com.google.common.eventbus.Subscribe;
import github.dmi3coder.syncodoro.event.LoginUserEvent;

/**
 * Created by dmi3coder on 1/22/16;11:53 PM.
 */
public class LoginUserSubscriber {

    @Subscribe
    public void handleLoginUserEvent(LoginUserEvent event){

    }
}
