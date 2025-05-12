package com.mbe.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.mbe.dao.ArCondicionadoDAO;
import com.mbe.enitity.ArCondicionado;

@ManagedBean
public class ArCondicionadoBean {
	
	private ArCondicionado arCondicionado = new ArCondicionado();
	private List<ArCondicionado> arCondicionadoList = null;
	
	private final int temperaturaAtualPadrao = 20;
	
	public List<ArCondicionado> listar() {
		arCondicionadoList = ArCondicionadoDAO.listar();
		return null;
	}
	
	public String salvar() {
		
		Date currentDate = new Date();
		Integer tempMin = arCondicionado.getTemperaturaMinima();
		Integer tempMax = arCondicionado.getTemperaturaMaxima();
		Date dataManutencao = arCondicionado.getDataManutencao();
		
	
		// Validação Mínima deve ser um número entre 10 e 16
		if (tempMin < 10 || tempMin > 16) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "A Temperatura Mínima deve estar entre os valores 10 e 16.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return null;
	    }
		
		// Validação Máxima deve ser um número entre 20 e 25
		if (tempMax < 20 || tempMax > 25) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "A Temperatura Máaxima deve estar entre os valores 20 e 25.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return null;
	    }
		
		// Validação Data de Manutenção deve ser superior à data de cadastro deste Registro 
		if (dataManutencao.before(currentDate)) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "A Data de Manutenção deve ser posterior à data de hoje.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return null;
	    }
		
		arCondicionado.setTemperaturaAtual(temperaturaAtualPadrao);
		ArCondicionadoDAO.salvar(arCondicionado);
		arCondicionado = new ArCondicionado();
		FacesMessage msg = new FacesMessage("Sucesso", "Manutenção cadastrada com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	public String excluir(ArCondicionado arCondicionado) {
		ArCondicionadoDAO.remove(arCondicionado);
	    listar();
	
	    FacesMessage msg = new FacesMessage("Info", "Manutenção Excluída!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	    
	    return null;
	}
	
	/** 
	 * 
	 * Método para recuperar a quantidade de dias em que a Manutenção do Ar Condicionado ocorrerá
	 * */
	public String getDiasParaManutencao(ArCondicionado arCondicionado) {
		int diasParaManutencao = arCondicionado.getDataManutencao().getDay();
		
	    FacesMessage msg = new FacesMessage("Info", "A manutenção ocorrerá em "+diasParaManutencao+ " dias");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;
	}
	
	/**
	 *  Método que recuperar o ArCondicionado com maior data de Manutenção (com uso de namedQuery) *
	 */
	public String getMaxDataManutencao() {
		ArCondicionado arCondicionado = ArCondicionadoDAO.maxDataManutencao();
		
	    FacesMessage msg = new FacesMessage("Info", "Maior data de Manutenção: "+ arCondicionado.getDescricao() + " - " + arCondicionado.getDataManutencao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;
	}
	
	public ArCondicionado getArCondicionado() {
		return arCondicionado;
	}
	
	public void setArCondicionado(ArCondicionado arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	
	public List<ArCondicionado> getArCondicionadoList() {
		if (arCondicionadoList == null) { // Caso a lista esteja vazia, buscar registros
			arCondicionadoList = ArCondicionadoDAO.listar();
		}
		return arCondicionadoList;
	}
	
	public void setArCondicionadoList(List<ArCondicionado> arCondicionadoList) {
		this.arCondicionadoList = arCondicionadoList;
	}
	
}
