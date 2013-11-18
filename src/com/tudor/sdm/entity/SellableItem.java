package com.tudor.sdm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sellableEntity")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SellableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long cost;

	public static class SellableItemBuilder {
		private Long id;
		private String name;
		private Long cost;

		public SellableItemBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public SellableItemBuilder name(String name) {
			this.name = name;
			return this;
		}

		public SellableItemBuilder cost(Long cost) {
			this.cost = cost;
			return this;
		}

		public SellableItem build() {
			return new SellableItem(this);
		}
	}

	private SellableItem(SellableItemBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.cost = builder.cost;
	}
}