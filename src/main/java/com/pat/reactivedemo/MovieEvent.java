package com.pat.reactivedemo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
class MovieEvent {
    private Movie movie;
    private Date when;
    private String user;

    MovieEvent(final Movie movie, final Date when, final String user) {
        this.movie = movie;
        this.when = when;
        this.user = user;
    }
}
