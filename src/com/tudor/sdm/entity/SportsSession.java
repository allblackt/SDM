package com.tudor.sdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="client")
	private Client client;
	private Date startTime;
	private Date endTime;
	private boolean isCanceled = false;

	@NoArgsConstructor
	public static class SportsSessionBuilder extends
			SellableItem.SellableItemBuilder<SportsSessionBuilder> {

		private Client client;
		private Date startTime;
		private Date endTime;
		private boolean isCanceled;

		public SportsSessionBuilder client(Client client) {
			this.client = client;
			return this;
		}

		public SportsSessionBuilder startTime(Date startTime) {
			this.startTime = startTime;
			return this;
		}

		public SportsSessionBuilder endTime(Date endTime) {
			this.endTime = endTime;
			return this;
		}

		public SportsSessionBuilder isCanceled(boolean isCanceled) {
			this.isCanceled = isCanceled;
			return this;
		}

		public SportsSession build() {
			return new SportsSession(this);
		}
	}

	private SportsSession(SportsSessionBuilder builder) {
		super(builder);
		this.client = builder.client;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.isCanceled = builder.isCanceled;
	}
}
