package org.cdapsimulator.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.cdapsimulator.configurations.CommonConfig;
import org.cdapsimulator.dao.UserDAO;
import org.cdapsimulator.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

@Controller
@RequestMapping("/fb")
public class FBApiController {
	
	
	private String AppID = "1579800698938028";
	private String AppSecret = "4a2e2bce4ab119348db9cdb689fec5af";
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Sign in
	 * @param request
	 * @param response
	 * @param userId
	 * @param password
	 */
	@RequestMapping(value="/sign_in",method=RequestMethod.GET)
	public void signIn(HttpServletRequest request,HttpServletResponse response){
		
		if(userSession.getUser() == null){
			try{
				request.setAttribute(CommonConfig.MESSAGE, prepareJSONOBJ(-1, "Please loging..."));
				response.sendRedirect(request.getContextPath() + "/");
				
			}catch(Exception e){
				
			}
		}
		
		try{
			Facebook facebook = new FacebookFactory().getInstance();
	        facebook.setOAuthAppId(AppID, AppSecret);
	        facebook.setOAuthPermissions(getPermissions());
	        
	        request.getSession().setAttribute("facebook", facebook);
	        StringBuffer callbackURL = request.getRequestURL();
	        int index = callbackURL.lastIndexOf("/");
	        callbackURL.replace(index, callbackURL.length(), "").append("/callback");
	        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
	        
		}catch(Exception e){
			System.out.print("signIN : " + e.getMessage());
		}
		
	}
	
	
	/**
	 * this is call back method.After successfully sign in with fb.fb redirect to this method
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/callback",method = RequestMethod.GET)
	public ModelAndView signCallBack(HttpServletRequest request,RedirectAttributes redirectAttrs) {
		
		request.getSession().setAttribute("start_linked_fb", 1);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/");
        try {
        	
        	if(userSession.getUser() == null){
				modelAndView.addObject("message", prepareJSONOBJ(-1, "Please loging..."));
        		return modelAndView;
        	}
        	
        	Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
            String oauthCode = request.getParameter("code");
            facebook.getOAuthAccessToken(oauthCode);
            
            //FaceBookCommentAnalysisService service = new FaceBookCommentAnalysisService();
            //service.startService(userSession.getUser().getUserId(),facebook);
			
            
            
        } catch (Exception e) {
        	System.out.print("callback : " + e.getMessage());
        	modelAndView.addObject(CommonConfig.MESSAGE, prepareJSONOBJ(-1, "Error link fb account"));
        }
        
        return modelAndView;
        
	}
	
	@RequestMapping(value="/app",method=RequestMethod.POST)
	public ModelAndView loadRefApp(){
		
		ModelAndView view = new ModelAndView("fb-app/app_home");
		return view;
	}
	
	@RequestMapping(value="/app",method=RequestMethod.GET)
	public ModelAndView loadRefAppGET(){
		
		ModelAndView view = new ModelAndView("fb-app/app_home");
		return view;
	}
	
	@RequestMapping(value="/app_ref",method=RequestMethod.POST)
	public @ResponseBody String addAppRefDetails(HttpServletRequest request,
					String requesterUserId,
					String approvedFbUserId,
					String approvedFbName,
					String approvedFbProfilePic,
					String howKnow,
					String note){
		
		if(	requesterUserId.equals("null") || approvedFbUserId.equals("null") ){
			return prepareJSONresult(-1, CommonConfig.REQUEST_PARAMETERS_ARE_NULL); 
		}
		
		System.out.println(approvedFbProfilePic);
		
		JSONObject appRef = new JSONObject();
		appRef.put("approvedFbUserId",approvedFbUserId);
		appRef.put("approvedFbName",approvedFbName);
		appRef.put("approvedFbProfilePic",approvedFbProfilePic);
		appRef.put("howKnow",howKnow);
		appRef.put("note",note);
		appRef.put("createdDate",new SimpleDateFormat("dd/M/yyyy HH:mm:ss").format(new Date()));
		
		try{
			boolean result = userDAO.addAppReference(requesterUserId, appRef);
			
			return prepareJSONresult(1,String.valueOf(result));
			
		}catch(Exception e){
			return prepareJSONresult(-1, CommonConfig.DB_ERROR);
		}
		
		
		
	}
	
	@RequestMapping(value="/app_ref_success",method=RequestMethod.GET)
	public ModelAndView loadAppRefSuccess(HttpServletRequest request,String requesterUserId){
		ModelAndView modelAndView = new ModelAndView("/fb-app/app_success");
		modelAndView.addObject("requesterUserId", requesterUserId);
		return modelAndView;
	}
	

	/**
	 * get permission for the APP
	 * @return
	 */
	private String getPermissions(){
		String permissions = "user_friends,"
        		+ "email,"
				+"user_posts,"
				+ "user_photos";
		return permissions;
	}
	
	private String prepareJSONresult(int status, String value) {

		JSONObject jsonResponce = new JSONObject();
		jsonResponce.put(CommonConfig.STATUS, status);
		jsonResponce.put(CommonConfig.VALUE, value);
		return jsonResponce.toString();

	}
	
	/**
	 * Prepare JSON Object
	 * @param status
	 * @param value
	 * @return
	 */
	private JSONObject prepareJSONOBJ(int status, String value) {

		JSONObject jsonResponce = new JSONObject();
		jsonResponce.put(CommonConfig.STATUS, status);
		jsonResponce.put(CommonConfig.VALUE, value);
		return jsonResponce;

	}
	
	
}
