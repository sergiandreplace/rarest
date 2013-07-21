package com.sergiandreplace.rarest.service;

import com.sergiandreplace.rarest.exception.ParamAliasNotFoundException;
import com.sergiandreplace.rarest.exception.ParamNotFoundException;
import com.sergiandreplace.rarest.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class Service {




	private Logger logger;
	private String name;
	private String url;
	private HttpVerb verb;
	private String parent;
	private boolean defaultParent=false;
    private String contentType;
	private List<Param> params=new ArrayList<Param>();
	private List<Pre> pres=new ArrayList<Pre>();
	private List<Post> posts=new ArrayList<Post>();

	public Service() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HttpVerb getVerb() {
		return verb;
	}
	public void setVerb(HttpVerb verb) {
		this.verb = verb;
	}
	public List<Param> getParams() {
		return params;
	}
	public void setParams(List<Param> params) {
		this.params = params;
	}

	public String toString() {
		StringBuilder output=new StringBuilder();
		output.append(String.format("    Service.start name=%1$s url=%2$s verb=%3$s parent=%4$s defaultParent=%5$s contentType=%6$s \n", name, url, verb, parent, defaultParent,contentType));
		
		for (Param param:params) {
			output.append("        ");
			output.append(param.toString());
			output.append("\n");
		}
		for (Pre pre:pres) {
			output.append("        ");
			output.append("Pre: "+pre.getName());
			output.append("\n");
		}
		for (Post post:posts) {
			output.append("        ");
			output.append("Post: "+post.getName());
			output.append("\n");
		}
		output.append("    Service.end");
		return output.toString();
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public boolean isDefaultParent() {
		return defaultParent;
	}
	public void setDefaultParent(boolean defaultParent) {
		this.defaultParent = defaultParent;
	}
	
	public Service clone() {
		Service cloned=new Service();
		cloned.setName(name);
		cloned.setUrl(url);
		cloned.setVerb(verb);
		cloned.setDefaultParent(defaultParent);
		cloned.setParent(parent);
		for(Param param:params) {
			cloned.getParams().add(param.clone());
		}
		for (Pre pre:pres) {
			cloned.getPres().add(pre.clone());
		}
		for (Post post:posts) {
			cloned.getPosts().add(post.clone());
		}
		return cloned;
	}
	
	public static Service merge(Service template, Service service) {
		Service result;
		
		result=template.clone();
		result.setName(service.getName());
		result.setParent(service.getParent());
		result.setDefaultParent(service.isDefaultParent());
		if (service.getUrl()!=null) {
			result.setUrl(service.getUrl());
		}
		if (service.getVerb()!=null) {
			result.setVerb(service.getVerb());
		}
		
		for (Param param:service.getParams()) {
			if (result.hasParam(param.getName())) {
				result.getParam(param.getName()).merge(param);
			}else{
				result.getParams().add(param);
			}
		}
		for (Pre pre:service.getPres()) {
			if(!result.hasPre(pre.getName())) {
				result.getPres().add(pre);
			}
		}
		for (Post post:service.getPosts()) {
			if(!result.hasPost(post.getName())) {
				result.getPosts().add(post);
			}
		}
		return result;
		
	}
	
	public boolean hasParam(String paramName) {
		boolean found=false;
		for (Param param:params) {
			if (paramName.equals(param.getName())) {
				found=true;
				break;
			}
		}
		return found;
	}
	public boolean hasParamByAlias(String paramAlias) {
		boolean found=false;
		for (Param param:params) {
			if (paramAlias.equals(param.getAlias())) {
				found=true;
				break;
			}
		}
		return found;
	}
	public boolean hasPre(String preName) {
		boolean found=false;
		for (Pre pre:pres) {
			if (preName.equals(pre.getName())) {
				found=true;
				break;
			}
		}
		return found;
	}
	public boolean hasPost(String postName) {
		boolean found=false;
		for (Post post:posts) {
			if (postName.equals(post.getName())) {
				found=true;
				break;
			}
		}
		return found;
	}
	public Param getParam(String paramName) {
		Param found=null;
		for (Param param:params) {
			if (paramName.equals(param.getName())) {
				found=param;
				break;
			}
		}
		if (found==null) {
			throw new ParamNotFoundException(paramName);
		}
		return found;
	}
	public Param getParamByAlias(String paramAlias) {
		Param found=null;
		for (Param param:params) {
			if (paramAlias.equals(param.getAlias())) {
				found=param;
				break;
			}
		}
		if (found==null) {
			throw new ParamAliasNotFoundException(paramAlias);
		}
		return found;
	}

	public List<Pre> getPres() {
		return pres;
	}

	public void setPres(List<Pre> pres) {
		this.pres = pres;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
