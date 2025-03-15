package com.astrapay.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Note extends BaseEntity<Integer> {
    private String title;
    private String content;
}