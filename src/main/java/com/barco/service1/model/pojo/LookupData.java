package com.barco.service1.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * This LookupData use store the all scheduler detail
 * like last scheduler run
 * and how much chuck data fetch at 1 time
 * */
/**
 * @author Nabeel Ahmed
 */
@Entity
@Table(name = "lookup_data")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupData {

    @GenericGenerator(
        name = "lookupDataSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "lookup_id_Seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Id
    @Column(name = "lookup_id")
    @GeneratedValue(generator = "lookupDataSequenceGenerator")
    private Long lookupId;

    @Column(name = "lookup_value",
        columnDefinition = "text")
    private String lookupValue;

    @Column(name = "lookup_type",
        unique = true)
    private String lookupType;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created",
        nullable = false)
    private Timestamp dateCreated;

    @ManyToOne
    @JoinColumn(name = "parentLookupId")
    protected LookupData parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    protected Set<LookupData> children;

    public LookupData() { }

    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public Long getLookupId() {
        return lookupId;
    }

    public void setLookupId(Long lookupId) {
        this.lookupId = lookupId;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LookupData getParent() {
        return parent;
    }

    public void setParent(LookupData parent) {
        this.parent = parent;
    }

    public Set<LookupData> getChildren() {
        return children;
    }

    public void setChildren(Set<LookupData> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}