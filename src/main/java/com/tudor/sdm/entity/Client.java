package com.tudor.sdm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Data
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(length = 50)
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

    @Column(length = 50, nullable = false)
    private String phoneNumber;

	public static class ClientBuilder {
		private String name;
		private String iban;
		private String personalnumber;
		private String country;
		private String city;
		private String district;
		private String street;
		private String streetNo;
		private String miscAddress;
        private String phoneNumber;

		public ClientBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ClientBuilder iban(String iban) {
			this.iban = iban;
			return this;
		}

		public ClientBuilder personalnumber(String personalnumber) {
			this.personalnumber = personalnumber;
			return this;
		}

		public ClientBuilder country(String country) {
			this.country = country;
			return this;
		}

		public ClientBuilder city(String city) {
			this.city = city;
			return this;
		}

		public ClientBuilder district(String district) {
			this.district = district;
			return this;
		}

		public ClientBuilder street(String street) {
			this.street = street;
			return this;
		}

		public ClientBuilder streetNo(String streetNo) {
			this.streetNo = streetNo;
			return this;
		}

		public ClientBuilder miscAddress(String miscAddress) {
			this.miscAddress = miscAddress;
			return this;
		}

        public ClientBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
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
            client.phoneNumber = phoneNumber;
			return client;
		}
	}
}
