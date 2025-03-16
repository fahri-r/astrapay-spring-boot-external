package com.astrapay.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Entity
@Table(name = "notes")
public class Note extends BaseEntity<Integer> {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Content is required")
    @Column(columnDefinition = "TEXT")
    private String content;
}