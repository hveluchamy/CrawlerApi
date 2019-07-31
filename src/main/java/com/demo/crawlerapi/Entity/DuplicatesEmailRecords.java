package com.demo.crawlerapi.Entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "duplicates_email_records")
public class DuplicatesEmailRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Ultimate_Account_Customer_Matched;
    private String contact_Id_For_Mc;
    private String full_Name;
    private String email;
    private Date created_date_formatted;
    private String ultimate_Account_Winc_Id;
    private String first_Name;
    private String last_Name;
    private String fullName_Email_Address;
    private Boolean key_contact;
    private Boolean toBeDeleted;
    private String contactClassification;
    private String mappedOfficemaxRoles;
    private String group_email;
    private String phone;
    private Integer opt_out_from_all_winc;
    private Integer temp_exclude_from_marketing_automa_winc;
    private Integer special_offers_promotions_winc;
    private Integer catalogue_release_winc;
    private Integer sales_phone_call_winc;
    private Integer email_opt_out_winc;
    private Boolean master_copy;
    private String roles;
   // private String Account_Name_WINC_Account_Owner_Id;
    //private String Account_Name_WINC_ACC_id;

    public String getUltimate_Account_Customer_Matched() {
        return Ultimate_Account_Customer_Matched;
    }

    public void setUltimate_Account_Customer_Matched(String Ultimate_Account_Customer_Matched) {
        this.Ultimate_Account_Customer_Matched = Ultimate_Account_Customer_Matched;
    }


    public String getContact_Id_For_Mc() {
        return contact_Id_For_Mc;
    }

    public void setContact_Id_For_Mc(String contact_Id_For_Mc) {
        this.contact_Id_For_Mc = contact_Id_For_Mc;
    }


    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCreated_date_formatted() {
        return created_date_formatted;
    }

    public void setCreated_date_formatted(Date created_date_formatted) {
        this.created_date_formatted = created_date_formatted;
    }


    public String getUltimate_Account_Winc_Id() {
        return ultimate_Account_Winc_Id;
    }

    public void setUltimate_Account_Winc_Id(String ultimate_Account_Winc_Id) {
        this.ultimate_Account_Winc_Id = ultimate_Account_Winc_Id;
    }


    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }


    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }


    public String getFullName_Email_Address() {
        return fullName_Email_Address;
    }

    public void setFullName_Email_Address(String fullName_Email_Address) {
        this.fullName_Email_Address = fullName_Email_Address;
    }





    public Boolean getToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(Boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }


    public String getContactClassification() {
        return contactClassification;
    }

    public void setContactClassification(String contactClassification) {
        this.contactClassification = contactClassification;
    }


    public String getMappedOfficemaxRoles() {
        return mappedOfficemaxRoles;
    }

    public void setMappedOfficemaxRoles(String mappedOfficemaxRoles) {
        this.mappedOfficemaxRoles = mappedOfficemaxRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup_email() {
        return group_email;
    }

    public void setGroup_email(String group_email) {
        this.group_email = group_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOpt_out_from_all_winc() {
        return opt_out_from_all_winc;
    }

    public void setOpt_out_from_all_winc(Integer opt_out_from_all_winc) {
        this.opt_out_from_all_winc = opt_out_from_all_winc;
    }

    public Integer getTemp_exclude_from_marketing_automa_winc() {
        return temp_exclude_from_marketing_automa_winc;
    }

    public void setTemp_exclude_from_marketing_automa_winc(Integer temp_exclude_from_marketing_automa_winc) {
        this.temp_exclude_from_marketing_automa_winc = temp_exclude_from_marketing_automa_winc;
    }

    public Integer getSpecial_offers_promotions_winc() {
        return special_offers_promotions_winc;
    }

    public void setSpecial_offers_promotions_winc(Integer special_offers_promotions_winc) {
        this.special_offers_promotions_winc = special_offers_promotions_winc;
    }

    public Integer getCatalogue_release_winc() {
        return catalogue_release_winc;
    }

    public void setCatalogue_release_winc(Integer catalogue_release_winc) {
        this.catalogue_release_winc = catalogue_release_winc;
    }

    public Integer getSales_phone_call_winc() {
        return sales_phone_call_winc;
    }

    public void setSales_phone_call_winc(Integer sales_phone_call_winc) {
        this.sales_phone_call_winc = sales_phone_call_winc;
    }

    public Integer getEmail_opt_out_winc() {
        return email_opt_out_winc;
    }

    public void setEmail_opt_out_winc(Integer email_opt_out_winc) {
        this.email_opt_out_winc = email_opt_out_winc;
    }

    public Boolean getMaster_copy() {
        return master_copy;
    }

    public void setMaster_copy(Boolean master_copy) {
        this.master_copy = master_copy;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getKey_contact() {
        return key_contact;
    }

    public void setKey_contact(Boolean key_contact) {
        this.key_contact = key_contact;
    }

}

