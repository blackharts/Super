package com.supertec.clases;

import com.supertec.clases.Comuna;
import com.supertec.clases.Tecnico;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-06T18:21:09")
@StaticMetamodel(Local.class)
public class Local_ { 

    public static volatile SingularAttribute<Local, String> ubicacion;
    public static volatile SingularAttribute<Local, Comuna> comuna;
    public static volatile SingularAttribute<Local, Integer> id;
    public static volatile SingularAttribute<Local, Tecnico> tecnico;
    public static volatile SingularAttribute<Local, String> nombre;

}