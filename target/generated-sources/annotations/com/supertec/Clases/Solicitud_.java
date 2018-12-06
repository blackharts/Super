package com.supertec.clases;

import com.supertec.clases.Cliente;
import com.supertec.clases.Tecnico;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-06T18:21:09")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Cliente> cliente;
    public static volatile SingularAttribute<Solicitud, String> caracteristicas;
    public static volatile SingularAttribute<Solicitud, String> estado;
    public static volatile SingularAttribute<Solicitud, Integer> id;
    public static volatile SingularAttribute<Solicitud, Tecnico> tecnico;

}