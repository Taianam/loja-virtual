package br.com.serratec.lojavirtual.model.email;

import java.util.List;

public class MenssagemEmail {
	
	private String assunto;
	private String corpo;
	private String remetente;
	private List<String> destinatarios;
		
	public MenssagemEmail(String assunto, String corpo, String remetente, List<String> destinatarios) {
		this.assunto = assunto;
		this.corpo = corpo;
		this.remetente = remetente;
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public String getRemetente() {
		return remetente;
	}
	
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public List<String> getDestinatarios() {
		return destinatarios;
	}
	
	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	
	
}
