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
@SuppressWarnings("unchecked")
public class SellableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long price;

	public static class SellableItemBuilder<T extends SellableItemBuilder<?>> {
		private Long id;
		private String name;
		private Long price;

		public T id(Long id) {
			this.id = id;
			return (T)this;
		}

		public T name(String name) {
			this.name = name;
			return (T)this;
		}

		public T price(Long price) {
			this.price = price;
			return (T)this;
		}

		public SellableItem build() {
			return new SellableItem(this);
		}
	}

	protected <T> SellableItem(SellableItemBuilder<?> builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.price = builder.price;
	}
}
