/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.container.external.servlet;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.ExternalContext;

/**
 * Servlet用の {@link ExternalContext}です。
 * 
 * @author higa
 * 
 */
public class HttpServletExternalContext implements ExternalContext {

    private ThreadLocal requests = new ThreadLocal();

    private ThreadLocal responses = new ThreadLocal();

    private ThreadLocal requestMaps = new ThreadLocal();

    private ThreadLocal requestHeaderMaps = new ThreadLocal();

    private ThreadLocal requestHeaderValuesMaps = new ThreadLocal();

    private ThreadLocal requestParameterMaps = new ThreadLocal();

    private ThreadLocal requestParameterValuesMaps = new ThreadLocal();

    private ThreadLocal requestCookieMaps = new ThreadLocal();

    private ThreadLocal sessionMaps = new ThreadLocal();

    private ServletContext application;

    public Object getRequest() {
        return getHttpServletRequest();
    }

    /**
     * {@link HttpServletRequest}を返します。
     * 
     * @return {@link HttpServletRequest}
     */
    protected HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) requests.get();
    }

    public void setRequest(Object request) {
        requests.set(request);
        if (request == null) {
            requestMaps.set(Collections.EMPTY_MAP);
            requestHeaderMaps.set(Collections.EMPTY_MAP);
            requestHeaderValuesMaps.set(Collections.EMPTY_MAP);
            requestParameterMaps.set(Collections.EMPTY_MAP);
            requestParameterValuesMaps.set(Collections.EMPTY_MAP);
            requestCookieMaps.set(Collections.EMPTY_MAP);
            sessionMaps.set(Collections.EMPTY_MAP);
        } else {
            final HttpServletRequest req = (HttpServletRequest) request;
            requestMaps.set(new ServletRequestMap(req));
            requestHeaderMaps.set(new ServletRequestHeaderMap(req));
            requestHeaderValuesMaps.set(new ServletRequestHeaderValuesMap(req));
            requestParameterMaps.set(new ServletRequestParameterMap(req));
            requestParameterValuesMaps
                    .set(new ServletRequestParameterValuesMap(req));
            requestCookieMaps.set(new CookieMap(req));
            sessionMaps.set(null);
        }
    }

    public Object getResponse() {
        return responses.get();
    }

    public void setResponse(Object response) {
        responses.set(response);
    }

    public Object getSession() {
        return getHttpSession();
    }

    /**
     * {@link HttpSession}を返します。
     * 
     * @return {@link HttpSession}
     */
    protected HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return null;
        }
        return request.getSession();
    }

    public Object getApplication() {
        return application;
    }

    public void setApplication(Object application) {
        if (!(application instanceof ServletContext)) {
            throw new IllegalArgumentException("application:" + application);
        }
        this.application = (ServletContext) application;
    }

    public Map getApplicationMap() {
        return new ServletApplicationMap(application);
    }

    public Map getInitParameterMap() {
        return new ServletInitParameterMap(application);
    }

    public Map getRequestCookieMap() {
        return (Map) requestCookieMaps.get();
    }

    public Map getRequestHeaderMap() {
        return (Map) requestHeaderMaps.get();
    }

    public Map getRequestHeaderValuesMap() {
        return (Map) requestHeaderValuesMaps.get();
    }

    public Map getRequestMap() {
        return (Map) requestMaps.get();
    }

    public Map getRequestParameterMap() {
        return (Map) requestParameterMaps.get();
    }

    public Map getRequestParameterValuesMap() {
        return (Map) requestParameterValuesMaps.get();
    }

    public Map getSessionMap() {
        Map sessionMap = (Map) sessionMaps.get();
        if (sessionMap != null) {
            return sessionMap;
        }
        sessionMap = new HttpSessionMap(getHttpServletRequest());
        sessionMaps.set(sessionMap);
        return sessionMap;
    }
}