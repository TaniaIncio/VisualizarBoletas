package com.tincio.visualizarboletas.data.services;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.ksoap2.serialization.AttributeContainer;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.Hashtable;

public class WWOEmpresa extends AttributeContainer implements KvmSerializable,Parcelable
{
    public String nomb_emp;
    public String id_empresa;

    public WWOEmpresa()
    {
    }
    public WWOEmpresa(Parcel in) {
        id_empresa = in.readString();
        nomb_emp = in.readString();

    }
    public WWOEmpresa(Object paramObj, WRHExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        if(inObj instanceof SoapObject)
        {
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                //if you have compilation error here, please use a ksoap2.jar and ExKsoap2.jar from libs folder (in the generated zip file)
                PropertyInfo info=soapObject.getPropertyInfo(i0);
                Object obj = info.getValue();
                if (info.name.equals("id_empresa"))
                {
                    if(obj!=null)
                    {

                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.id_empresa= j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.id_empresa= (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("nomb_empr"))
                {
                    if(obj!=null)
                    {

                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.nomb_emp= j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.nomb_emp= (String)obj;
                        }
                    }
                    continue;
                }
            }

        }



    }

    public static final Creator<WWOEmpresa> CREATOR = new Creator<WWOEmpresa>() {
        @Override
        public WWOEmpresa createFromParcel(Parcel in) {
            return new WWOEmpresa(in);
        }

        @Override
        public WWOEmpresa[] newArray(int size) {
            return new WWOEmpresa[size];
        }
    };

    @Override
    public Object getProperty(int propertyIndex) {
        if(propertyIndex==0)
        {
            return this.id_empresa!=null?this.id_empresa:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.nomb_emp!=null?this.nomb_emp:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "id_empresa";
            info.namespace= "http://xsd.bean.wsdocuservici.gmd.com.pe/xsd";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "nomb_empr";
            info.namespace= "http://xsd.bean.wsdocuservici.gmd.com.pe/xsd";
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1)
    {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_empresa);
        parcel.writeString(nomb_emp);
        //parcel.writeParcelableArray(new ArrayList<WWOEmpresa>());
    }
}
