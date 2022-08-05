package com.bolsadeideas.springboot.backend.apirest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="facturas")
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_facs")
	@SequenceGenerator(name = "id_Sequence_facs", sequenceName = "SQ_JJ_FACS", allocationSize = 1, initialValue = 1)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="OBSERVACION")
	private String observacion;
	
	@Column(name="CREATE_AT")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura>items;
	
	public Factura() {
		items = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public Double getTotal() {
		Double total = 0.00;
		for (ItemFactura item: items) {
			total = total + item.getImporte();
		}
		return total;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
