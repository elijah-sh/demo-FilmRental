package com.hand.config;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 public class MyFilter implements Filter {


     private final static Logger logger = Logger.getLogger(MyFilter.class.getName());

    //不需要登录就可以访问的路径(比如:注册登录等)

    String[] includeUrls = new String[]{"/","/login.do","/checkLogin.do"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {



        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath  =  request.getContextPath();   // 获取项目名

        HttpSession session = request.getSession();
        HttpServletRequest hsRequest = (HttpServletRequest) request;
        String servletPath = hsRequest.getServletPath();

        String uri = request.getRequestURI();

    	logger.info("filter url:"+contextPath+" --> "+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri,contextPath);

            if (!needFilter) {         //不需要过滤直接传给下一个过滤器

                filterChain.doFilter(servletRequest, servletResponse);
                 } else { //需要过滤器
                // session中包含user对象,则是登录状态
                  if(session!=null&&session.getAttribute("user") != null){
                       // filterChain.doFilter(request, response);

                     filterChain.doFilter(servletRequest, servletResponse);
                  }else{
                                logger.info("被拦截 filter url:"+uri);
                                this.redirectTo(contextPath+"/login.do", response);
                         return;
                 }
             }
    }

    /**
     * @Description: 是否需要过滤
     * @param uri
     */
    public boolean isNeedFilter(String uri,String contextPath) {
        int uriLength = contextPath.length();
        for (String includeUrl : includeUrls) {
            if((contextPath+includeUrl).equals(uri)) {
                return false;
            }
        }
        return true;
    }

     @Override
     public void init(FilterConfig arg0) throws ServletException {
     }

    @Override
    public void destroy() {
    }
    
	private void redirectTo(String href, HttpServletResponse response) throws IOException {
		String script = "<script>alert('您长时间未操作，为确保安全，请重新登录。'); "
				+ "window.top.location.href='" +href + "';</script>";
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(script.getBytes("UTF-8"));
		response.flushBuffer();
	}
}