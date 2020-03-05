package com.example.MyNotes.config;

import com.example.MyNotes.entities.User;
import com.example.MyNotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
//		String userName = authentication.getName();
//		User user = userService.findByUsername(userName);
//		HttpSession session = request.getSession();
//		session.setAttribute("user", user);
//		if(!request.getHeader("referer").contains("login")) {
//			response.sendRedirect(request.getHeader("referer"));
//		} else {
//			response.sendRedirect(request.getContextPath() + "/");
//		}
	}
}