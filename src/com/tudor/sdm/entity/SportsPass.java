package com.tudor.sdm.entity;

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

import com.tudor.sdm.Constants.StringNames;
import com.tudor.sdm.Language;
import com.tudor.sdm.dao.SportsPassDAO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sportspass")
@Data
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SportsPass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client")
	private Client client;
	private String barcode;
	private String title;
	@ManyToOne
	private SellableItem sportsSessionType;
	private int maxSessions;
	private int remainingSessions;

	public static class Builder {
		private Long id;
		private Client client;
		private String barcode;
		private String title;
		private SellableItem sportsSessionType;
		private int maxSessions;
		private int remainingSessions;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder client(Client client) {
			this.client = client;
			return this;
		}

		public Builder barcode(String barcode) {
			this.barcode = barcode;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder sportsSessionType(SellableItem sportsSessionType) {
			this.sportsSessionType = sportsSessionType;
			return this;
		}

		public Builder maxSessions(int maxSessions) {
			this.maxSessions = maxSessions;
			return this;
		}

		public Builder remainingSessions(int remainingSessions) {
			this.remainingSessions = remainingSessions;
			return this;
		}

		public SportsPass build() {
			return new SportsPass(this);
		}
	}

	private SportsPass(Builder builder) {
		this.id = builder.id;
		this.client = builder.client;
		this.barcode = builder.barcode;
		this.title = builder.title;
		this.sportsSessionType = builder.sportsSessionType;
		this.maxSessions = builder.maxSessions;
		this.remainingSessions = builder.remainingSessions;
	}
	
	public void decreseRemaining() {
		if(remainingSessions > 0) {
			remainingSessions--;
			SportsPassDAO.get().save(this);
		} else {
			throw new IllegalStateException(Language.get().getString(StringNames.ERR_NOT_ENOUGH_SESSIONS_LEFT_ON_PASS));
		}
			
	}
	
	@PrePersist
	@PreUpdate
	private void validateSessionPassState(){
		if(maxSessions < remainingSessions) {
			throw new IllegalStateException(Language.get().getString(StringNames.ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_REMAININGSESSIONS));
		}
		if (maxSessions < 1 ) {
			throw new IllegalStateException(Language.get().getString(StringNames.ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_ZERO));
		}
		if (remainingSessions < 0) {
			throw new IllegalStateException(Language.get().getString(StringNames.ERR_REMAININGSESSIONS_MUST_BE_A_POSITIVE_NUMBER));
		}
	}
}
