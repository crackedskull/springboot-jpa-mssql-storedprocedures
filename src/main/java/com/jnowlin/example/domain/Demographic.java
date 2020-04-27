package com.jnowlin.example.domain;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@SqlResultSetMapping(
    name = "DemographicMapping",
    classes = @ConstructorResult(
        targetClass = Demographic.class,
        columns = {
            @ColumnResult(name = "Age", type = Integer.class),
            @ColumnResult(name = "Count", type = Integer.class)}))
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "sp_getDemo",
        procedureName = "dbo.GetDemographics",
        resultSetMappings = "DemographicMapping",
        parameters = {
        }
    )
})
public class Demographic {

    @Id
    @Column(name = "Age")
    private int age;
    @Column(name = "Count")
    private int count;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
