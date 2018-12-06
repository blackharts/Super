package com.supertec.clases;

import com.supertec.clases.Comuna;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-06T18:21:09")
@StaticMetamodel(Region.class)
public class Region_ { 

    public static volatile SingularAttribute<Region, String> codigo;
    public static volatile CollectionAttribute<Region, Comuna> comunaCollection;
    public static volatile SingularAttribute<Region, Integer> id;
    public static volatile SingularAttribute<Region, String> nombre;

}