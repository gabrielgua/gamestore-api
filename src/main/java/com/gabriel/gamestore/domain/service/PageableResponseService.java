package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.api.security.AuthProperties;
import com.gabriel.gamestore.domain.exception.PageNaoEncontradaException;
import com.gabriel.gamestore.domain.model.PageableResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PageableResponseService<T> {

    private AuthProperties properties;

    private Long getNumberOfPages(Pageable pageable, Long count) {
        var totalPages = count / pageable.getPageSize();
        return count % pageable.getPageSize() == 0
                ? totalPages
                : totalPages + 1;
    }

    private String getNextUrl(String providerUrl, String controllerUrl, Long count, Pageable pageable) {
        return pageable.next().getOffset() >= count
                ? null
                : buildUrlString(providerUrl, controllerUrl, pageable.next().getPageNumber());
    }

    private String getPrevUrl(String providerUrl, String controllerUrl, Pageable pageable) {
        return pageable.getPageNumber() > 0 ? buildUrlString(providerUrl, controllerUrl, pageable.getPageNumber() - 1) : null;
    }

    private String buildUrlString(String providerUrl, String controllerUrl, Integer pageNumber) {
        return String.format("%s/%s?page=%d", providerUrl, controllerUrl, pageNumber);

    }

    public PageableResponse<T> buildResponse(String controllerUrl, Page<T> page, Pageable pageable, List<T> content) {
        if (page.getNumber() == page.getTotalPages() && !page.isFirst()) {
            pageNaoExiste(page.getNumber());
        }

        var pageInfo = PageableResponse.PageInfo.builder()
                .count(page.getTotalElements())
                .pages(page.getTotalPages())
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .next(getNextUrl(properties.getProviderUrl(), controllerUrl, page.getTotalElements(), pageable))
                .prev(getPrevUrl(properties.getProviderUrl(), controllerUrl, pageable))
                .build();


        return PageableResponse.<T>builder()
                .pageInfo(pageInfo)
                .content(content)
                .build();
    }

    private boolean hasContent(List<T> content) {
        return !content.isEmpty();
    }

    private void pageNaoExiste(Integer page) {
        throw new PageNaoEncontradaException(page);
    }
}
