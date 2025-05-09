package com.alvarolongueira.user.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@With
public class PageableUser {

    private List<User> userList;
    private int pageNumber;
    private int size;
    private int totalPages;
    private int totalElements;
}
