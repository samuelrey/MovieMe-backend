package com.movie.me.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label="USER")
public class User {
	@GraphId
	private Long id;

	@JsonIgnore
	@Property(name="NAME")
	private String name;

	@Property(name="PHOTO_URI")
	private String photoURI;

	@Property(name="AGE")
	private String age;

    @JsonIgnore
	@Property(name="EMAIL")
	private String email;

	@Relationship(type="LIKES")
	private Set<Movie> moviesLiked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Movie> getMoviesLiked() {
		return moviesLiked;
	}

	public void setMoviesLiked(Set<Movie> moviesLiked) {
		this.moviesLiked = moviesLiked;
	}

	@Override
	public String toString() {
		return this.email;
	}
}