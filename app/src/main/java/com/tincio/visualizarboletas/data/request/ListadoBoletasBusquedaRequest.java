package com.tincio.visualizarboletas.data.request;

/**
 * Created by innovagmd on 11/10/16.
 */
public class ListadoBoletasBusquedaRequest {

    int idEmpresa;
    String nroDocumentoIdentificacion;
    String idTipoDocumento;
    String periodo;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNroDocumentoIdentificacion() {
        return nroDocumentoIdentificacion;
    }

    public void setNroDocumentoIdentificacion(String nroDocumentoIdentificacion) {
        this.nroDocumentoIdentificacion = nroDocumentoIdentificacion;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
