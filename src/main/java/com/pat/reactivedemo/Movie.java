package com.pat.reactivedemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Movie {

    @Id
    private String id;

    private String title, genre;

}
