package com.supertec.Clases;

import com.supertec.Clases.Cliente;
import com.supertec.Clases.Tecnico;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-05T18:00:57")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Cliente> cliente;
    public static volatile SingularAttribute<Solicitud, String> caracteristicas;
    public static volatile SingularAttribute<Solicitud, String> estado;
    public static volatile SingularAttribute<Solicitud, Integer> id;
    public static volatile SingularAttribute<Solicitud, Tecnico> tecnico;

}