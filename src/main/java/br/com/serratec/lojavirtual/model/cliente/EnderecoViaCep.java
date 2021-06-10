package br.com.serratec.lojavirtual.model.cliente;

public class EnderecoViaCep {

		private Integer id;
		private String cep;
		private String logradouro;
		private String complemento;
		private String bairro;
		private String localidade;
		private String uf;
			

		public EnderecoViaCep() {}
		

		public EnderecoViaCep(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
			this.cep = cep;
			this.logradouro = logradouro;
			this.complemento = complemento;
			this.bairro = bairro;
			this.localidade = localidade;
			this.uf = uf;
		}


		public EnderecoViaCep(Integer id, String cep, String logradouro, String complemento, String bairro, String localidade,
				String uf) {
			this.id = id;
			this.cep = cep;
			this.logradouro = logradouro;
			this.complemento = complemento;
			this.bairro = bairro;
			this.localidade = localidade;
			this.uf = uf;
		}


		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getCep() {
			return cep;
		}
		
		public void setCep(String cep) {
			this.cep = cep;
		}
		
		public String getLogradouro() {
			return logradouro;
		}
		
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}
		
		public String getComplemento() {
			return complemento;
		}
		
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		
		public String getBairro() {
			return bairro;
		}
		
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		
		public String getLocalidade() {
			return localidade;
		}
		
		public void setLocalidade(String localidade) {
			this.localidade = localidade;
		}
		
		public String getUf() {
			return uf;
		}
		
		public void setUf(String uf) {
			this.uf = uf;
		}
		
		
	}
