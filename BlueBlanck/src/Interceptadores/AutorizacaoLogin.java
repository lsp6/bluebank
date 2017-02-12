package Interceptadores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizacaoLogin extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();
		if(uri.contains("resources")){
			return true;
		}
		if (uri.endsWith("login") && request.getSession().getAttribute("Usuario") == null) {
			return true;
		}else if(uri.endsWith("login") && request.getSession().getAttribute("Usuario") != null){
			response.sendRedirect("conta");
		}

		if (request.getSession().getAttribute("Usuario") != null) {
			return true;
		}
		response.sendRedirect("login");
		return false;
	}

}
