package com.tincio.visualizarboletas.data.request;

/**
 * Created by innovagmd on 11/10/16.
 */
public class ListadoBoletasRequest {

    int codAplicacion;
    int idEmpresa;
    String nroDocumentoIdentificacion;
    String idTipoDocumento;

    public int getCodAplicacion() {
        return codAplicacion;
    }

    public void setCodAplicacion(int codAplicacion) {
        this.codAplicacion = codAplicacion;
    }

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
}
