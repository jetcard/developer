package com.company.inventory.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="category")
public class Category implements Serializable{

	private static final long serialVersionUID = -4310027227752446841L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="DVALOR_BV")
	private String codigo;
	@Column(name="MONEDA")
	private String moneda;
	@Column(name="NCUOTA")
	private String nroCuota;
	@Column(name="FDESEMBOLSO", columnDefinition = "DATE")
	private Date fechaDesembolso;
	@Column(name="FPAGO", columnDefinition = "DATE")
	private Date fechaPago;
	@Column(name="MONTO")
	private String monto;
	@Column(name="TEA")
	private String tea;
	@Column(name="INTERES")
	private String interesCompensatorio;
	@Column(name="INTERESPROV")
	private String interesProvision;
	@Column(name="IGV")
	private String igv;
	@Column(name="DIASTRANS")
	private String diasTranscurridos;
	@Column(name="TOTAL")
	private String total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}

	public Date getFechaDesembolso() {
		return fechaDesembolso;
	}

	public void setFechaDesembolso(Date fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getInteresCompensatorio() {
		return interesCompensatorio;
	}

	public void setInteresCompensatorio(String interesCompensatorio) {
		this.interesCompensatorio = interesCompensatorio;
	}

	public String getInteresProvision() {
		return interesProvision;
	}

	public void setInteresProvision(String interesProvision) {
		this.interesProvision = interesProvision;
	}

	public String getIgv() {
		return igv;
	}

	public void setIgv(String igv) {
		this.igv = igv;
	}

	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}

	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	
}
