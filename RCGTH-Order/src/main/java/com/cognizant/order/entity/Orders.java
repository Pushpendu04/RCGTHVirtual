package com.cognizant.order.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int orderId;
	private String orderName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private List<Product> products;
	private int total;
	
	public Orders(int orderId,String orderName,int total)
	{
		this.orderId=orderId;this.orderName=orderName;this.total=total;
	}

}
