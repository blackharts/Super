package com.supertec.clases;

import com.supertec.clases.Tecnico;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-06T18:21:09")
@StaticMetamodel(TipoTecnico.class)
public class TipoTecnico_ { 

    public static volatile CollectionAttribute<TipoTecnico, Tecnico> tecnicoCollection;
    public static volatile SingularAttribute<TipoTecnico, Integer> id;
    public static volatile SingularAttribute<TipoTecnico, String> especialidad;

}