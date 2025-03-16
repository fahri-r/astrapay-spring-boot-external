package com.astrapay.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "notes")
public class Note extends BaseEntity<Integer> {
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
}