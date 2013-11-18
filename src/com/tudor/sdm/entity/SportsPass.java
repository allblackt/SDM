package com.tudor.sdm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String barcode;
	@OneToMany
	private List<SportsSession> sessions;
	private int maxSessions;
	private int usedSessions;

	public static class Builder {
		private Long id;
		private String barcode;
		private List<SportsSession> sessions;
		private int maxSessions;
		private int usedSessions;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder barcode(String barcode) {
			this.barcode = barcode;
			return this;
		}

		public Builder sessions(List<SportsSession> sessions) {
			this.sessions = sessions;
			return this;
		}

		public Builder maxSessions(int maxSessions) {
			this.maxSessions = maxSessions;
			return this;
		}

		public Builder usedSessions(int usedSessions) {
			this.usedSessions = usedSessions;
			return this;
		}

		public SportsPass build() {
			return new SportsPass(this);
		}
	}

	private SportsPass(Builder builder) {
		this.id = builder.id;
		this.barcode = builder.barcode;
		this.sessions = builder.sessions;
		this.maxSessions = builder.maxSessions;
		this.usedSessions = builder.usedSessions;
	}
}
