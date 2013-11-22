package com.tudor.sdm.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sportssession")
@PrimaryKeyJoinColumn(name = "id")
@ToString
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SportsSession extends SellableItem {

	private int duration;

	@NoArgsConstructor
	public static class SportsSessionBuilder extends
			SellableItem.SellableItemBuilder<SportsSessionBuilder> {

		private int duration;

		public SportsSessionBuilder duration(int duration) {
			this.duration = duration;
			return this;
		}
		
		public SportsSession build() {
			return new SportsSession(this);
		}
	}

	private SportsSession(SportsSessionBuilder builder) {
		super(builder);
		this.duration = builder.duration;
	}
}
