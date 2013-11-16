package com.tudor.sdm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String iban;

	@Column(length = 50)
	private String personalnumber;

	@Column(length = 50, nullable = false)
	private String country;

	@Column(length = 50, nullable = false)
	private String city;

	@Column(length = 50)
	private String district;

	@Column(length = 255)
	private String street;

	@Column(length = 20)
	private String streetNo;

	@Column(length = 255)
	private String miscAddress;

	public static class Builder {
		private String name;
		private String iban;
		private String personalnumber;
		private String country;
		private String city;
		private String district;
		private String street;
		private String streetNo;
		private String miscAddress;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder iban(String iban) {
			this.iban = iban;
			return this;
		}

		public Builder personalnumber(String personalnumber) {
			this.personalnumber = personalnumber;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder district(String district) {
			this.district = district;
			return this;
		}

		public Builder street(String street) {
			this.street = street;
			return this;
		}

		public Builder streetNo(String streetNo) {
			this.streetNo = streetNo;
			return this;
		}

		public Builder miscAddress(String miscAddress) {
			this.miscAddress = miscAddress;
			return this;
		}

		public Client build() {
			Client client = new Client();
			client.name = name;
			client.iban = iban;
			client.personalnumber = personalnumber;
			client.country = country;
			client.city = city;
			client.district = district;
			client.street = street;
			client.streetNo = streetNo;
			client.miscAddress = miscAddress;
			return client;
		}
	}
}
