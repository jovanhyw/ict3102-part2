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

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import static com.example._Zuul_server.Utils.Constants.IMAGE_HASH_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Component
public class CachePostFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(CachePostFilter.class);

    @Autowired
    CacheSingleton cacheSingleton;

    public CachePostFilter() {
        logger.info("CachePostFilter injected");
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        boolean shouldFilter = context.getRequest().getRequestURL().toString().contains("/yolo/api/uploadTest");
        logger.info("shouldFilter: " + shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() throws ZuulException {
        logger.warn("POST FILTERING");

        RequestContext context = RequestContext.getCurrentContext();
        logger.info("other one");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(context.getResponseDataStream(), baos);
            String response = new String(baos.toByteArray());
            context.setResponseBody(response);

            String imageHash = (String) context.get(IMAGE_HASH_KEY);

            if (imageHash == null) {
                logger.warn("Failed to get image");
                return null;
            }

            if (cacheSingleton.isCached(imageHash)) {
                logger.info("Something is wrong, cached but request went through");
            } else {
                cacheSingleton.cacheResponse(imageHash, response);
            }
        } catch (Exception e) {

        }
        return null;
    }
}
