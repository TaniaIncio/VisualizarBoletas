package com.tincio.visualizarboletas.data.services;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.5.8.1
//
// Created by Quasar Development at 20/09/2016
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class CULBAuditoria extends AttributeContainer implements KvmSerializable
{

    
    public String deTermCrea;
    
    public String deTermModi;
    
    public java.util.Date feUsuaCrea;
    
    public java.util.Date feUsuaModi;
    
    public String idUsuaCrea;
    
    public String idUsuaModi;
    
    public String perfilUsuario;
    
    public String rolUsuarioFnt;

    public CULBAuditoria ()
    {
    }

    public CULBAuditoria (Object paramObj,CULExtendedSoapSerializationEnvelope __envelope)
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
                if (info.name.equals("deTermCrea"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.deTermCrea = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.deTermCrea = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("deTermModi"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.deTermModi = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.deTermModi = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("feUsuaCrea"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.feUsuaCrea = CULHelper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.feUsuaCrea = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("feUsuaModi"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.feUsuaModi = CULHelper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.feUsuaModi = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("idUsuaCrea"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.idUsuaCrea = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.idUsuaCrea = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("idUsuaModi"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.idUsuaModi = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.idUsuaModi = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("perfilUsuario"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.perfilUsuario = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.perfilUsuario = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("rolUsuarioFnt"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.rolUsuarioFnt = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.rolUsuarioFnt = (String)obj;
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
            return this.deTermCrea!=null?this.deTermCrea:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.deTermModi!=null?this.deTermModi:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.feUsuaCrea!=null?CULHelper.getDateFormat().format(this.feUsuaCrea):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.feUsuaModi!=null?CULHelper.getDateFormat().format(this.feUsuaModi):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.idUsuaCrea!=null?this.idUsuaCrea:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.idUsuaModi!=null?this.idUsuaModi:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.perfilUsuario!=null?this.perfilUsuario:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.rolUsuarioFnt!=null?this.rolUsuarioFnt:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 8;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "deTermCrea";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "deTermModi";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "feUsuaCrea";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "feUsuaModi";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "idUsuaCrea";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "idUsuaModi";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "perfilUsuario";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "rolUsuarioFnt";
            info.namespace= "http://entity.sibe.gmd.com.pe/xsd";
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1)
    {
    }

}
