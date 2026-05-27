package com.test_junit_1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
		
		private long id;
		
		@NotBlank(message = "Name can't be blank!")
		@Size(min = 2, message = "Name should be at least of 2 characters!")
		private String name;
		
		@Email(message = "Invalid Email!!")
		private String email;
		
		@Size(min = 10, max = 10, message = "Invalid Mobile No.!")
		private String mobile;
		
		@NotBlank(message = "City can't be blank!")
		private String city;
		

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

}