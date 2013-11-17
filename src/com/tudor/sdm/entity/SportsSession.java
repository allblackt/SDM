package com.tudor.sdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sportssession")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SportsSession extends SellableItem {

	@ManyToOne
	private Client client;
	@ManyToOne
	private Field field;
	private Date startTime;
	private Date endTime;
	private boolean isCanceled = false;

	public static class SportsSessionBuilder {
		
		private Client client;
		private Field field;
		private Date startTime;
		private Date endTime;
		private boolean isCanceled;

		public SportsSessionBuilder client(Client client) {
			this.client = client;
			return this;
		}
		
		public SportsSessionBuilder field(Field field) {
			this.field = field;
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
			SportsSession sportsSession = new SportsSession();
			sportsSession.client = client;
			sportsSession.field = field;
			sportsSession.startTime = startTime;
			sportsSession.endTime = endTime;
			sportsSession.isCanceled = isCanceled;
			return sportsSession;
		}
	}
}
