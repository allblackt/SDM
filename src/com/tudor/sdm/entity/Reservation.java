package com.tudor.sdm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	private SportsSession sportsSessionType;
	@ManyToOne
	@JoinColumn(name="field")
	private Field field;
	@Column(nullable=false)
	private Date startTime;
	@Column(nullable=false)
	private Date endTime;
	private boolean isCanceled;
	private Date created;
	private Date lastUpdated;
	
	@PrePersist
	@PreUpdate
	public void checkForConflicts() {
		//used to check if two sessions overlap
		// TODO implement it 
	}

	public static class ReservationBuilder {
		private Client client;
		private SportsSession sportsSessionType;
		private Field field;
		private Date startTime;
		private Date endTime;
		private boolean isCanceled;
		private Date created;
		private Date lastUpdated;

		public ReservationBuilder client(Client client) {
			this.client = client;
			return this;
		}

		public ReservationBuilder sportsSessionType(SportsSession sportsSessionType) {
			this.sportsSessionType = sportsSessionType;
			return this;
		}
		
		public ReservationBuilder field(Field field) {
			this.field = field;
			return this;
		}

		public ReservationBuilder endTime(Date endTime) {
			this.endTime = endTime;
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
		this.field = builder.field;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.isCanceled = builder.isCanceled;
		this.created = builder.created;
		this.lastUpdated = builder.lastUpdated;
	}
}
