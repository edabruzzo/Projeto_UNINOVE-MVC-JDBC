/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

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

    public Contrato(Integer codigo, String objetoContrato, Double orcamentoComprometido,
            boolean ativo, String contratado) {
        this.codigo = codigo;
        this.objetoContrato = objetoContrato;
        this.orcamentoComprometido = orcamentoComprometido;
        this.ativo = ativo;
        this.contratado = contratado;
    }

    public Contrato() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
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
