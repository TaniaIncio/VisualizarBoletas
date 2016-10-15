package com.tincio.visualizarboletas.data.request;

/**
 * Created by innovagmd on 11/10/16.
 */
public class CambioClaveRequest {

    String codAplicacion;
    String idEmpresa;
    String nomUsuario;
    String clave;
    String nuevaClave;

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getCodAplicacion() {
        return codAplicacion;
    }

    public void setCodAplicacion(String codAplicacion) {
        this.codAplicacion = codAplicacion;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
