package com.tudor.sdm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "field")
@Data
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	public static class SportsPassBuilder {
		private String name;

		public SportsPassBuilder name(String name) {
			this.name = name;
			return this;
		}

		public Field build() {
			return new Field(this);
		}
	}

	private Field(SportsPassBuilder builder) {
		this.name = builder.name;
	}
}
