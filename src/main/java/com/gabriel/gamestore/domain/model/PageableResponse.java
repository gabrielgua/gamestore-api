package com.gabriel.gamestore.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@Builder
public class PageableResponse<T> {

    private Long count;
    private Integer page;
    private Integer size;
    private String next;
    private String prev;

    private List<T> content;



}
