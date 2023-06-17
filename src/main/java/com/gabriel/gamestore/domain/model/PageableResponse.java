package com.gabriel.gamestore.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageableResponse<T> {
    private PageInfo pageInfo;
    private List<T> content;

    @Data
    @Builder
    public static class PageInfo {
        private Long count;
        private Long pages;
        private Integer page;
        private Integer size;
        private String next;
        private String prev;
    }



}
