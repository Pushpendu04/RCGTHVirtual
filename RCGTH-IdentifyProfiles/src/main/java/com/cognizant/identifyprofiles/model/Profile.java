package com.cognizant.identifyprofiles.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String profiletype;
	private String returnpolicy;
	private String fragility;
	private String expiry;
	private String warranty;
}
