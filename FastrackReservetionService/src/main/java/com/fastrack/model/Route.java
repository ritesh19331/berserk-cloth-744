package com.fastrack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
	
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	@NotNull(message = "Start point cannot be null!")
	private String routeFrom;
	@NotNull(message = "Destination point cannot be null!")
	private String routeTo;
	@NotNull(message = "Distance cannot be null!")
	private Integer distance;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busRoute")
	private List<Bus> buses = new ArrayList<>();
	

}
