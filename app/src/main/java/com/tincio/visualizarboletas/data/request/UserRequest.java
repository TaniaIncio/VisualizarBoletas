package com.tincio.visualizarboletas.data.request;

/**
 * Created by innovagmd on 11/10/16.
 */
public class UserRequest {

    int codAplicacion;
    int idEmpresa;
    String username;
    String clave;
    String ip;
    String keyIos;
    String keyAndroid;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKeyIos() {
        return keyIos;
    }

    public void setKeyIos(String keyIos) {
        this.keyIos = keyIos;
    }

    public String getKeyAndroid() {
        return keyAndroid;
    }

    public void setKeyAndroid(String keyAndroid) {
        this.keyAndroid = keyAndroid;
    }
}
