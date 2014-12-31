package com.cms.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.cms.system.util.UserContext;

public class AuthorityInterceptor extends HandlerInterceptorAdapter  {

	Map<String, Integer> whiteList = new HashMap<String, Integer>();

	public AuthorityInterceptor() {
		whiteList.put("/loginCtl/login", 1);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UserContext UserContext = (UserContext) WebUtils.getSessionAttribute(request, "userContext");

		String url = request.getServletPath();
		if (UserContext == null&&whiteList.get(url) == null) {
			response.sendRedirect("/loginCtl/login") ;
			return false;
		} 

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
