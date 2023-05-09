package com.secprog.starter.model;

import jakarta.annotation.Nonnull;


public class PhoneBookEntry {

		@Nonnull
		private String name;


		@Nonnull
		private String phoneNumber;


		public PhoneBookEntry(String name, String phoneNumber) {
			super();
			this.name = name;
			this.phoneNumber = phoneNumber;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		


}
