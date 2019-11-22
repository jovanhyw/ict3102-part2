package com.example._Zuul_server.Filters;

import com.example._Zuul_server.Beans.CacheSingleton;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import static com.example._Zuul_server.Utils.Constants.IMAGE_HASH_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class HashPreFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(HashPreFilter.class);

    @Autowired
    CacheSingleton cacheSingleton;

    public HashPreFilter() {
        logger.info("HashPreFilter injected");
    }

    // Applied to request before routing
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    // Filter priority
    @Override
    public int filterOrder() {
        return 1;
    }

    // Always filter
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        boolean shouldFilter = context.getRequest().getRequestURL().toString().contains("/yolo/api/uploadTest");
        logger.info("shouldFilter: " + shouldFilter);
        return shouldFilter;
    }

    // Filter action
    @Override
    public Object run() throws ZuulException {
        logger.warn("PRE FILTERING");

        RequestContext context = RequestContext.getCurrentContext();
        ContentCachingRequestWrapper cachedRequest = new ContentCachingRequestWrapper(context.getRequest());
        String imageHash = null;

        try {
            imageHash = IOUtils.toString(cachedRequest.getInputStream());
            imageHash = imageHash.split("image/jpeg")[1];
            imageHash = imageHash.split("--")[0];
        } catch (Exception exception) {
            logger.error(exception.toString());
            return null;
        }

        if (imageHash == null) {
            logger.warn("Failed to get image");
            context.setResponseStatusCode(400);
            context.setSendZuulResponse(false);
        }

        if (cacheSingleton.isCached(imageHash)) {
            logger.info("Image found, building response from cache");
            String cachedResult = cacheSingleton.getCachedResponse(imageHash);
            if (cacheSingleton == null || cachedResult == null) {
                context.setResponseStatusCode(400);
                context.setSendZuulResponse(false);
            } else {
                context.setChunkedRequestBody();
                context.setResponseStatusCode(200);
                context.getResponse().setHeader("Content-type", "application/json");
                context.getResponse().setContentLength(cachedResult.length());
                context.setResponseBody(cachedResult);
                context.setSendZuulResponse(false);
            }
        } else {
            logger.info("Forwarding");
            context.set(IMAGE_HASH_KEY, imageHash);
        }

        return null;
    }
}
