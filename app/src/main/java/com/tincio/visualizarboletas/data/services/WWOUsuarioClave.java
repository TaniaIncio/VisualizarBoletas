package com.tincio.visualizarboletas.data.services;

import org.ksoap2.serialization.AttributeContainer;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Hashtable;

public class WWOUsuarioClave extends AttributeContainer implements KvmSerializable
{

    
    public String descripcion;
    
    public Boolean resultado;
    
    public String sNombres;

    public WWOUsuarioClave()
    {
    }

    public WWOUsuarioClave(Object paramObj, WRHExtendedSoapSerializationEnvelope __envelope)
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
                if (info.name.equals("descripcion"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.descripcion = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.descripcion = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("resultado"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.resultado = new Boolean(j.toString());
                            }
                        }
                        else if (obj instanceof Boolean){
                            this.resultado = (Boolean)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("sNombres"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.sNombres = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.sNombres = (String)obj;
                        }
                    }
                    continue;
                }

            }

        }



    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.descripcion!=null?this.descripcion:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.resultado!=null?this.resultado:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.sNombres!=null?this.sNombres:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "descripcion";
            info.namespace= "http://xsd.bean.wsdocuservici.gmd.com.pe/xsd";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.BOOLEAN_CLASS;
            info.name = "resultado";
            info.namespace= "http://xsd.bean.wsdocuservici.gmd.com.pe/xsd";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "sNombres";
            info.namespace= "http://xsd.bean.wsdocuservici.gmd.com.pe/xsd";
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1)
    {
    }

}
