package com.gabriel.gamestore.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageableResponseService {

    public String getNextUrl(String providerUrl, String controllerUrl, Long count, Pageable pageable) {
        return pageable.next().getOffset() >= count
                ? null
                : buildUrlString(providerUrl, controllerUrl, pageable.next().getPageNumber());
    }


    public String getPrevUrl(String providerUrl, String controllerUrl, Pageable pageable) {
        return pageable.getPageNumber() > 0 ? buildUrlString(providerUrl, controllerUrl, pageable.getPageNumber() - 1) : null;
    }

    private String buildUrlString(String providerUrl, String controllerUrl, Integer pageNumber) {
        return String.format("%s/%s?page=%d", providerUrl, controllerUrl, pageNumber);

    }
}
