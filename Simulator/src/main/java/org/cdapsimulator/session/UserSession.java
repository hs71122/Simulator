package org.cdapsimulator.session;

import java.util.ArrayList;
import java.util.Date;

import org.cdapsimulator.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *  <bean id="userSession" class="ridetrust.session.UserSession" scope="session">
 *    <aop:scoped-proxy/>
 *	</bean>
 * @author Tharanga
 *
 */
@Component
@Scope( value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession{
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
