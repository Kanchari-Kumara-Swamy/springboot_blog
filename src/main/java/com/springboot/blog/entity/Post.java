package com.springboot.blog.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


// @Data is not allowed to use because model mapper uses toString to map between Post and Comment entities since
// we are using dependency here which makes an infinite loop and throws stackoverflow error.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content",nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
