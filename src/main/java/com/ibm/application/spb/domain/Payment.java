package com.ibm.application.spb.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ibm.application.spb.domain.enums.StatusPayment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_PAYMENT")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Integer status;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	private Order order;

	public Payment() {
	}

	public Payment(Long id, StatusPayment status, Order order) {
		this.id = id;
		this.status = status.getCode();
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPayment getStatus() {
		return StatusPayment.toEnum(status);
	}

	public void setStatus(StatusPayment status) {
		this.status = status.getCode();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", status=" + status + "]";
	}
}
