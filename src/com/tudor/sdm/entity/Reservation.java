package com.tudor.sdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "sportssessiontype")
	private SellableItem sportsSessionType;
	private Date startTime;
	private boolean isCanceled;
	private Date created;
	private Date lastUpdated;

	public static class ReservationBuilder {
		private Client client;
		private SellableItem sportsSessionType;
		private Date startTime;
		private boolean isCanceled;
		private Date created;
		private Date lastUpdated;

		public ReservationBuilder client(Client client) {
			this.client = client;
			return this;
		}

		public ReservationBuilder sportsSessionType(SellableItem sportsSessionType) {
			this.sportsSessionType = sportsSessionType;
			return this;
		}

		public ReservationBuilder startTime(Date startTime) {
			this.startTime = startTime;
			return this;
		}

		public ReservationBuilder isCanceled(boolean isCanceled) {
			this.isCanceled = isCanceled;
			return this;
		}

		public ReservationBuilder created(Date created) {
			this.created = created;
			return this;
		}

		public ReservationBuilder lastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
			return this;
		}

		public Reservation build() {
			return new Reservation(this);
		}
	}

	private Reservation(ReservationBuilder builder) {
		this.client = builder.client;
		this.sportsSessionType = builder.sportsSessionType;
		this.startTime = builder.startTime;
		this.isCanceled = builder.isCanceled;
		this.created = builder.created;
		this.lastUpdated = builder.lastUpdated;
	}
}
