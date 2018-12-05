package com.supertec.Clases;

import com.supertec.Clases.Local;
import com.supertec.Clases.Region;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-12-05T18:00:57")
@StaticMetamodel(Comuna.class)
public class Comuna_ { 

    public static volatile CollectionAttribute<Comuna, Local> localCollection;
    public static volatile SingularAttribute<Comuna, String> codigo;
    public static volatile SingularAttribute<Comuna, Integer> id;
    public static volatile SingularAttribute<Comuna, Region> region;
    public static volatile SingularAttribute<Comuna, String> nombre;

}