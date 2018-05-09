/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Emm
 */
public class Contrato {
    
    private Integer codigo;
    private String objetoContrato;
    private Double orcamentoComprometido;
    private boolean ativo;
    private String contratado;
    
    
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getObjeto() {
        return objetoContrato;
    }

    public void setObjeto(String objeto) {
        this.objetoContrato = objeto;
    }

    public Double getOrcamentoComprometido() {
        return orcamentoComprometido;
    }

    public void setOrcamentoComprometido(Double orcamentoComprometido) {
        this.orcamentoComprometido = orcamentoComprometido;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getContratado() {
        return contratado;
    }

    public void setContratado(String contratado) {
        this.contratado = contratado;
    }
    
    
    
    
}
