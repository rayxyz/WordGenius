package com.wordgen.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(MainInterceptor.class);  
	
	private List<String> freeURLMappings;
	
	public MainInterceptor() {
		logger.info("In constructor of MainInterceptor.");
		if(freeURLMappings == null) {
			freeURLMappings = new ArrayList<String>();
		}
	}

	public void setFreeURLMappings(List<String> freeURLMappings) {
		this.freeURLMappings = freeURLMappings;
	}

	@Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
//        String url = request.getRequestURL().toString();  
        String uri = request.getRequestURI();
//        String contextPath = request.getContextPath();
        String suffix = uri.substring(request.getContextPath().length());
//        logger.info("request uri: " + uri + ", suffix: " + suffix);
        Object user = request.getSession().getAttribute("user");
        
        logger.info("uri.endsWith(/login): " + uri.endsWith("/login"));
        
        if(user == null) {
			if (!uri.endsWith("/login")) {
//				response.setContentType("text/html;charset=utf-8");
//				String msg = "<script>try{$.afui.popup('无用户会话或会话已过期，请重新登录！');window.location.href = '" + contextPath + "/view/mobile/login.jsp';}catch(error){alert('无用户会话或会话已过期，请重新登录！');window.location.href = '" + contextPath + "/view/mobile/login.jsp';}</script>";
//				PrintWriter writer = response.getWriter();
//				writer.write(msg);
//				writer.flush();
//				writer.close();
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无用户会话或会话已过期，请重新登录！"); // 401
				return false;
			}
        }
        if("/".equals(suffix) || (suffix.length() >= 2 && this.freeURLMappings.contains(suffix))) {
        	return true;
        }
        return true;  
    }  
  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
    	super.postHandle(request, response, handler, modelAndView);    
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        if(null != ex) {
//        	String msg = ex.getMessage();
        	String msg = "出错了，请查看日志。";
        	JSONObject json = new JSONObject();
        	json.put("success", false);
        	json.put("msg", msg);
        	response.setContentType("text/plain;charset=UTF-8");
        	PrintWriter writer = response.getWriter();
        	writer.print(json);
			writer.flush();
			writer.close();
        }
    } 
	
}
