package com.mbe.enitity;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name = "ArCondicionado.maxDataManutencao", query = "SELECT ac FROM ArCondicionado ac ORDER BY ac.dataManutencao DESC")
@Entity
public class ArCondicionado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 225)
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Date dataManutencao;
	private Integer temperaturaAtual;
	private Integer temperaturaMinima;
	private Integer temperaturaMaxima;
	
	public Integer calcDiasParaManutencao() {
		
		LocalDate dataManutencaoLD = dataManutencao.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
		System.out.println("o "+ dataManutencao.toString());
		LocalDate dataAtual = LocalDate.now();

		// Calcula a diferença entre as datas
		Period periodo = Period.between(dataAtual, dataManutencaoLD);
		return periodo.getDays();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataManutencao() {
		return dataManutencao;
	}
	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}
	public Integer getTemperaturaAtual() {
		return temperaturaAtual;
	}
	public void setTemperaturaAtual(Integer temperaturaAtual) {
		this.temperaturaAtual = temperaturaAtual;
	}
	public Integer getTemperaturaMinima() {
		return temperaturaMinima;
	}
	public void setTemperaturaMinima(Integer temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}
	public Integer getTemperaturaMaxima() {
		return temperaturaMaxima;
	}
	public void setTemperaturaMaxima(Integer temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	
}
