package com.tincio.visualizarboletas.data.request;

/**
 * Created by innovagmd on 25/10/16.
 */
public class DetalleBoletasRequest {

    String sPeriodo;
    int codAplicacion;
    int idTipoDocumento;
    String dniUsuario;
    String ipUsuario;
    String clave;
    int isEmpresa;

    public String getsPeriodo() {
        return sPeriodo;
    }

    public void setsPeriodo(String sPeriodo) {
        this.sPeriodo = sPeriodo;
    }

    public int getCodAplicacion() {
        return codAplicacion;
    }

    public void setCodAplicacion(int codAplicacion) {
        this.codAplicacion = codAplicacion;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIsEmpresa() {
        return isEmpresa;
    }

    public void setIsEmpresa(int isEmpresa) {
        this.isEmpresa = isEmpresa;
    }
}
