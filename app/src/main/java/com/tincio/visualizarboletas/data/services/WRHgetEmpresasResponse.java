package com.tincio.visualizarboletas.data.services;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 5.0.0.2
//
// Created by Quasar Development at 11/10/2016
//
//---------------------------------------------------


import org.ksoap2.serialization.AttributeContainer;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Hashtable;
import java.util.Vector;


public class WRHgetEmpresasResponse extends Vector< WWOEmpresa> implements KvmSerializable
{

    public WRHgetEmpresasResponse(){}

    public WRHgetEmpresasResponse(Object inObj, WRHExtendedSoapSerializationEnvelope __envelope)
    {
        if (inObj == null)
            return;
        SoapObject soapObject=(SoapObject)inObj;
        int size = soapObject.getPropertyCount();
        for (int i0=0;i0< size;i0++)
        {
            Object obj = soapObject.getProperty(i0);
            if (obj!=null && obj instanceof AttributeContainer)
            {
                AttributeContainer j =(AttributeContainer) soapObject.getProperty(i0);
                WWOEmpresa j1= (WWOEmpresa)__envelope.get(j,WWOEmpresa.class,false);
                add(j1);
            }
        }
}
    
    @Override
    public Object getProperty(int arg0) {
        return this.get(arg0)!=null?this.get(arg0):SoapPrimitive.NullNilElement;
    }
    
    @Override
    public int getPropertyCount() {
        return this.size();
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        info.name = "return";
        info.type = WWOEmpresa.class;
    	info.namespace= "http://wsdocuservutil.gmd.com.pe";
    }
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }

    
}